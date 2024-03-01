import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajHrComponent } from './dodaj-hr.component';

describe('DodajHrComponent', () => {
  let component: DodajHrComponent;
  let fixture: ComponentFixture<DodajHrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajHrComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajHrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
