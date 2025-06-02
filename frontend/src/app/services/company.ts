import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export interface Company {
  id: number;
  name: string;
  email: string;
  address: string;
}

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private baseUrl = 'http://localhost:8080/api'; 

  constructor(private http: HttpClient) {}

  getFormFields() {
    return this.http.get<any[]>(`${this.baseUrl}/custom-fields?moduleId=112`);
  }

  saveCompany(data: any) {
    return this.http.post(`${this.baseUrl}/companies`, data);
  }

  getAllCompanies(page: number = 0, size: number = 10) {
    return this.http.get(`${this.baseUrl}/companies?page=${page}&size=${size}`);
  }

  updateCompany(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/companies/${id}`, data);
  }
}
