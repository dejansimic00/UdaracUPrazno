import { Component } from '@angular/core';
import { Radnik } from '../../../models/radnik';
import { HttpService } from '../../../services/http/http.service';

@Component({
  selector: 'app-dodaj-radnika',
  templateUrl: './dodaj-radnika.component.html',
  styleUrl: './dodaj-radnika.component.css'
})
export class DodajRadnikaComponent {
  uloge = [
    { id: 1, name: 'Radnik' },
    { id: 2, name: 'grupa' },
    { id: 3, name: 'hr' },
    { id: 4, name: 'Admin' },
];
selectedUloga=1;
  constructor(private service: HttpService){}

  onSubmit() {
    console.log(this.radnik);
    this.service.create("dodajRadnika.php", this.radnik).subscribe(
      result => {
        //this.notifyService.showSuccess(this.translate.instant('DodavanjePodatakaUspjesno'), this.translate.instant('Uspjesno'));
      },
      error => {
        console.log(error);
        //this.notifyService.showError(this.translate.instant('DodavanjePodatakaGreska'), this.translate.instant('Greska'));
      }
    );
  }

  radnik: Radnik = {
    id_osoba: 0,
    ime: '',
    prezime: '',
    jmbg: '',
    slika: '',
    sef: 0,
    korisnicko_ime: '',
    lozinka: '',
    uloga: 0,
    id_sef: 0
  };

  
}
