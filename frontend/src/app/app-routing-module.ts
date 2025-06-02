import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyForm } from './components/company-form/company-form';
import { CompanyList } from './components/company-list/company-list';

const routes: Routes = [
  { path: '', component: CompanyForm },
  { path: 'list', component: CompanyList },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
