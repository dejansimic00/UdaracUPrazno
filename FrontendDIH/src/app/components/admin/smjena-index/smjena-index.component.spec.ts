import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmjenaIndexComponent } from './smjena-index.component';

describe('SmjenaIndexComponent', () => {
  let component: SmjenaIndexComponent;
  let fixture: ComponentFixture<SmjenaIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SmjenaIndexComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SmjenaIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
