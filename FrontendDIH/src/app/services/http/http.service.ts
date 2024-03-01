import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  GetCountMale(arg0: string) {
    throw new Error('Method not implemented.');
  }

  constructor(private httpClient: HttpClient) { }

  create(url: string, item: any, options?: {}): Observable<any> {
    return this.httpClient.post<any>(url, JSON.stringify(item), options)
      .pipe()
  }

  update(url: string, item: any, options?: {}): Observable<any> {
    return this.httpClient.put<any>(url, JSON.stringify(item), options)
      .pipe()
  }

  delete(url: string, options?: {}) {
    return this.httpClient.delete<any>(url, options)
      .pipe()
  }

  

  getList(url: string, options?: {}): Observable<any[]> {
    return this.httpClient
      .get<any[]>(url, options)
      .pipe()
  }

  getObject(url: string, options?: {}): Observable<any> {
    return this.httpClient
      .get<any>(url, options)
      .pipe()
  }

  resetPassword(url:string, item:any, options?: {}){
    return this.httpClient.post<any>(url, JSON.stringify(item), options)
    .pipe()

  }
}
