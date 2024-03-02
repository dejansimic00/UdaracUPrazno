import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NadredjeniComponent } from './nadredjeni.component';

describe('NadredjeniComponent', () => {
  let component: NadredjeniComponent;
  let fixture: ComponentFixture<NadredjeniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NadredjeniComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NadredjeniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
