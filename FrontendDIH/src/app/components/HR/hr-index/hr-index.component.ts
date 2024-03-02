import { Component, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-hr-index',
  templateUrl: './hr-index.component.html',
  styleUrl: './hr-index.component.css'
})
export class HrIndexComponent {
  odjava() {
    this.router.navigate(["/login-index"]);
  }
  constructor(private router: Router, private route: ActivatedRoute) { }

  @ViewChild('mysidebar') sidebar!: ElementRef;
  @ViewChild('mytable') table!: ElementRef;

  isCurrentRoute(routePath: string): boolean {
    return this.router.url.includes(routePath);
  }

  openNav() {
    this.sidebar.nativeElement.style.width = "100%";
    this.table.nativeElement.style.marginLeft = "100%";
  }

  closeNav() {
    this.sidebar.nativeElement.style.width = "0";
    this.table.nativeElement.style.marginLeft = "0";
  }
}
