import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Employee } from './employee';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {


 
  private baseURL = "http://localhost:8600/api/v1/employees";

  constructor(private httpClient: HttpClient) { }
  getEmployees( page:number,sort:string,sortDirection:string):Observable<any> {
    let queryParams:string='/?page='+page+'&size=5';
    if(sort){
      queryParams+='&sort='+sort;
      if(sortDirection){
       queryParams+='&dir='+sortDirection;
      }
    }
    return this.httpClient.get(this.baseURL+queryParams);
  }


  addEmployee(employee: Employee): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/`, employee);
  }

  getEmployeeById(id: number): Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.baseURL}/${id}`);
  }


  updateEmployee(id: number, employee: Employee): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
