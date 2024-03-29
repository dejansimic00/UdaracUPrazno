
import { Component, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-nadredjeni',
  templateUrl: './nadredjeni.component.html',
  styleUrl: './nadredjeni.component.css'
})
export class NadredjeniComponent {
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

