import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { AppComponent } from './app.component';

import { LoginIndexComponent } from './components/login-index/login-index.component';
import { DodajSmjenuComponent } from './components/admin/dodaj-smjenu/dodaj-smjenu.component';
import { SmjenaIndexComponent } from './components/admin/smjena-index/smjena-index.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home-admin', pathMatch: 'full' },
    { path: 'home-admin', component: AdminHomeComponent, children:[
      {path: 'smjena-index', component: SmjenaIndexComponent}
    ] },
    {path:'login-index', component:LoginIndexComponent },
    {path: 'dodaj-smjenu', component:DodajSmjenuComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
