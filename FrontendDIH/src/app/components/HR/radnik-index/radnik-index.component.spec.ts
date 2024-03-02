import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RadnikIndexComponent } from './radnik-index.component';

describe('RadnikIndexComponent', () => {
  let component: RadnikIndexComponent;
  let fixture: ComponentFixture<RadnikIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RadnikIndexComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RadnikIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
