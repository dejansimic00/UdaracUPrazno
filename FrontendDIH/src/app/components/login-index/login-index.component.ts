import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login-index',
  templateUrl: './login-index.component.html',
  styleUrl: './login-index.component.css'
})
export class LoginIndexComponent {
  constructor(private http: HttpClient) { }

  onSubmit(): void {
    const url = `https://example.com/login?username=${encodeURIComponent(this.username)}&password=${encodeURIComponent(this.password)}`;
    this.http.get(url)
      .subscribe(
        data => {
          console.log(data);
          // Handle the response data here
        },
        error => {
          console.error('There was an error:', error);
          // Handle errors here
        }
      );
  }
}


