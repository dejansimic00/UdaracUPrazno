import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrIndexComponent } from './hr-index.component';

describe('HrIndexComponent', () => {
  let component: HrIndexComponent;
  let fixture: ComponentFixture<HrIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HrIndexComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HrIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
