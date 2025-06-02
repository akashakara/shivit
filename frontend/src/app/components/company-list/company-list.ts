import { Component, OnInit } from '@angular/core';
import { CompanyService, Company } from '../../services/company';

@Component({
  selector: 'app-company-list',
  standalone: false,
  templateUrl: './company-list.html',
  styleUrl: './company-list.scss'
})


export class CompanyList implements OnInit {
  companies: Company[] = [];

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.companyService.getAllCompanies().subscribe((data:any) => {
      this.companies = data;
    });
  }
}
