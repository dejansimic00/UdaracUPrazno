import { Component } from '@angular/core';
import { Radnik } from '../../../models/radnik';
import { HttpService } from '../../../services/http/http.service';


@Component({
  selector: 'app-dodaj-radnika',
  templateUrl: './dodaj-radnika.component.html',
  styleUrl: './dodaj-radnika.component.css'
})
export class DodajRadnikaComponent {
  selectedFile: File | null = null;
  uloge = [
    { id: 1, name: 'Radnik' },
    { id: 2, name: 'grupa' },
    { id: 3, name: 'hr' },
    { id: 4, name: 'Admin' },
];
selectedUloga=1;
 formData = new FormData();
  constructor(private service: HttpService){}

  onSubmit() {
    console.log(this.radnik);
    
    this.formData.append('ime', this.radnik.ime);
    this.formData.append('prezime', this.radnik.prezime); 
    this.formData.append('jmbg', this.radnik.jmbg);
    this.formData.append('korisnicko_ime', this.radnik.korisnicko_ime);
    this.formData.append('lozinka', this.radnik.lozinka);
    this.formData.append('uloga', this.radnik.uloga);
    if (this.selectedFile) {
      this.formData.append('slika', this.selectedFile);
    }
    //zakucana vrijednost kako bi prikazivao u testu
    this.formData.append('nadredjeni', '111111111');

    console.log(this.formData.get('ime'));
    this.service.create("evidencijaRadnogVremena/dodajRadnika.php", this.formData).subscribe(
      result => {
        //this.notifyService.showSuccess(this.translate.instant('DodavanjePodatakaUspjesno'), this.translate.instant('Uspjesno'));
      },
      error => {
        console.log(error);
        //this.notifyService.showError(this.translate.instant('DodavanjePodatakaGreska'), this.translate.instant('Greska'));
      }
    );
  }

  onFileSelected(event: any) {
    // Capture the selected file
    this.selectedFile = event.target.files[0];
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
    uloga: '0',
    id_sef: 0
  };

  
}
