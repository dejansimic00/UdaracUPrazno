import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpService } from '../../services/http/http.service';
import { LoginModel } from '../../models/loginModel';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login-index',
  templateUrl: './login-index.component.html',
  styleUrl: './login-index.component.css'
})
export class LoginIndexComponent {
  username="";
  password="";
  lmVar: LoginModel = {
    id_osoba: 0,
    uloga: 0
  };
  onSubmit() {

    var url="evidencijaRadnogVremena/login.php"+"?korisnicko_ime="+this.username+"&lozinka="+this.password;
    this.service.getObject(url).subscribe(
      result => {
        console.log(result);
        this.router.navigate(["/home-hr"]);
        //this.notifyService.showSuccess(this.translate.instant('DodavanjePodatakaUspjesno'), this.translate.instant('Uspjesno'));
      },
      error => {
        console.log(error);
        //this.notifyService.showError(this.translate.instant('DodavanjePodatakaGreska'), this.translate.instant('Greska'));
      }
    );
  }
  constructor(private service: HttpService,private router: Router) { }



  
}


