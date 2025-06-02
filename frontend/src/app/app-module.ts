import { NgModule, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { CompanyForm } from './components/company-form/company-form';
import { CompanyList } from './components/company-list/company-list';

@NgModule({
  declarations: [
    App,
    CompanyList
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    CompanyForm,

    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection()
  ],
  bootstrap: [App]
})
export class AppModule { }
