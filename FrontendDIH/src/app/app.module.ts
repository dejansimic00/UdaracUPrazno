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
import { DodajHrComponent } from './components/admin/dodaj-hr/dodaj-hr.component';
import { DodajSektorComponent } from './components/admin/dodaj-sektor/dodaj-sektor.component';

import { SmjenaIndexComponent } from './components/admin/smjena-index/smjena-index.component';




@NgModule({
  declarations: [
    AppComponent,
    AdminHomeComponent,
    LoginIndexComponent,
    DodajSmjenuComponent,
    DodajSektorComponent,
    DodajHrComponent
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



