import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module'; // Import your routing module
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { LoginIndexComponent } from './components/login-index/login-index.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminHomeComponent,
    LoginIndexComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})


export class AppModule { }



