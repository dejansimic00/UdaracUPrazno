import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { AppComponent } from './app.component';
import { DataTablesModule } from 'angular-datatables';

import { LoginIndexComponent } from './components/login-index/login-index.component';
import { DodajSmjenuComponent } from './components/admin/dodaj-smjenu/dodaj-smjenu.component';
import { DodajSektorComponent } from './components/admin/dodaj-sektor/dodaj-sektor.component';
import { DodajHrComponent } from './components/admin/dodaj-hr/dodaj-hr.component';
import { SmjenaIndexComponent } from './components/admin/smjena-index/smjena-index.component';
import { HrIndexComponent } from './components/HR/hr-index/hr-index.component';
import { RadnikIndexComponent } from './components/HR/radnik-index/radnik-index.component';
import { DodajRadnikaComponent } from './components/HR/dodaj-radnika/dodaj-radnika.component';
import { NadredjeniComponent } from './components/nadredjeni/nadredjeni.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home-admin', pathMatch: 'full' },
    { path: 'home-admin', component: AdminHomeComponent, children:[
      {path: 'smjena-index', component: SmjenaIndexComponent}
    ] },
    { path: 'home-hr', component: HrIndexComponent, children:[
      {path: 'radnik-index', component: RadnikIndexComponent},
      {path: 'dodaj-radnika', component: DodajRadnikaComponent}
    ] },
    {path:'login-index', component:LoginIndexComponent },
    {path: 'dodaj-smjenu', component:DodajSmjenuComponent},
    {path: 'dodaj-sektor', component:DodajSektorComponent},
    {path: 'dodaj-hr',component:DodajHrComponent},
    {path: 'nadredjeni',component:NadredjeniComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes), DataTablesModule.forRoot()],
  exports: [RouterModule]
})

export class AppRoutingModule { }
