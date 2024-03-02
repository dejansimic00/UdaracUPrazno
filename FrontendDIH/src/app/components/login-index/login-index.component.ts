import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login-index',
  templateUrl: './login-index.component.html',
  styleUrl: './login-index.component.css'
})
export class LoginIndexComponent {
  constructor(private http: HttpClient) { }

  
}


