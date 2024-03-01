import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module'; // Import your routing module
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { LoginIndexComponent } from './components/login-index/login-index.component';

import { DodajSmjenuComponent } from './components/admin/dodaj-smjenu/dodaj-smjenu.component';
<<<<<<< HEAD
import { DodajSektorComponent } from './components/admin/dodaj-sektor/dodaj-sektor.component';
=======
import { SmjenaIndexComponent } from './components/admin/smjena-index/smjena-index.component';
>>>>>>> a228111e6019c19a0827e7a867e0e0bf31af6c67



@NgModule({
  declarations: [
    AppComponent,
    AdminHomeComponent,
    LoginIndexComponent,
    DodajSmjenuComponent,
<<<<<<< HEAD
    DodajSektorComponent
=======
    SmjenaIndexComponent
>>>>>>> a228111e6019c19a0827e7a867e0e0bf31af6c67
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})


export class AppModule { }



