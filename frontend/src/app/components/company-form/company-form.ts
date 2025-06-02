import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { CompanyService } from '../../services/company';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-company-form',
  standalone: true,
  templateUrl: './company-form.html',
  styleUrls: ['./company-form.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class CompanyForm implements OnInit {
  companyForm!: FormGroup;
  formFields: any[] = [];
  loading = true;
  message = '';

  constructor(private fb: FormBuilder, private companyService: CompanyService) {}

 ngOnInit(): void {
  this.companyService.getFormFields().subscribe({
    next: (fields) => {
      console.log('Fetched fields:', fields);
      setTimeout(() => {
        this.formFields = fields;
        this.initForm(fields);
        this.loading = false;
      }, 5000);
    },
    error: (err) => {
      console.error('Error fetching fields:', err);
      this.loading = false;
    }
  });
}


  isFileField(field: any): boolean {
    return field.fieldType === 'file' || field.fieldName.toLowerCase().includes('file') || field.fieldName.toLowerCase().includes('logo');
  }

  groupFields(fields: any[]) {
    const uniqueFields: any[] = [];
    const groups: { [key: string]: any[] } = {};

    fields.forEach(field => {
      if (field.section) {
        if (!groups[field.section]) groups[field.section] = [];
        groups[field.section].push(field);
      } else {
        uniqueFields.push(field);
      }
    });

    return { uniqueFields, groups };
  }

initForm(fields: any[]) {
  const group: any = {};
  const { uniqueFields, groups } = this.groupFields(fields);

  uniqueFields.forEach(field => {
    group[field.fieldName] = ['', this.getValidators(field)];
  });

  Object.keys(groups).forEach(section => {
    group[section] = this.fb.array(
      groups[section].map(field =>
        this.fb.group({
          [field.fieldName]: ['', this.getValidators(field)]
        })
      )
    );
  });

  this.companyForm = this.fb.group(group);

  fields.forEach(field => {
    if ((field.fieldType === '3' || field.fieldType === '4') && Array.isArray(field.options) && field.options.length > 0) {
      const randomIndex = Math.floor(Math.random() * field.options.length);
      const randomValue = field.options[randomIndex];
      if (this.companyForm.get(field.fieldName)) {
        this.companyForm.get(field.fieldName)?.setValue(randomValue);
      }
    }
  });

  console.log('Form group created:', this.companyForm.value, this.companyForm);
}


  getValidators(field: any) {
    const validators = [];
    if (field.isMandatory === 'required') validators.push(Validators.required);
    if (field.pattern) validators.push(Validators.pattern(field.pattern));
    return validators;
  }

  onFileChange(event: any, fieldName: string) {
    const file = event.target.files[0];
    if (file) {
      this.companyForm.get(fieldName)?.setValue(file);
    }
  }

  onSubmit() {
    if (this.companyForm.valid) {
      const formData = new FormData();
      Object.keys(this.companyForm.controls).forEach(key => {
        const value = this.companyForm.get(key)?.value;
        if (value instanceof File) {
          formData.append(key, value);
        } else {
          formData.append(key, value);
        }
      });


      this.companyService.saveCompany(formData).subscribe({
        next: () => this.message = 'Company saved successfully!',
        error: (err) => {
          console.error(err);
          this.message = 'Error saving company.';
        }
      });
    } else {
      this.message = 'Please fill all required fields.';
    }
  }
}
