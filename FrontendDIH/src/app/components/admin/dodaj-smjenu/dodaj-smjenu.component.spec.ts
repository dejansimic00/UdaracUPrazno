import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajSmjenuComponent } from './dodaj-smjenu.component';

describe('DodajSmjenuComponent', () => {
  let component: DodajSmjenuComponent;
  let fixture: ComponentFixture<DodajSmjenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajSmjenuComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajSmjenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
