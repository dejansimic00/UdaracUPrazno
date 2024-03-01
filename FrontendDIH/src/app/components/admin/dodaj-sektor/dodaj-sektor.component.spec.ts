import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajSektorComponent } from './dodaj-sektor.component';

describe('DodajSektorComponent', () => {
  let component: DodajSektorComponent;
  let fixture: ComponentFixture<DodajSektorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajSektorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajSektorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
