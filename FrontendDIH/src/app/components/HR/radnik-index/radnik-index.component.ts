import { Component, ElementRef, ViewChild } from '@angular/core';
import { Radnik } from '../../../models/radnik';
import { DataTableDirective } from 'angular-datatables';
import { HttpService } from '../../../services/http/http.service';
import { routes } from '../../../app-routing.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-radnik-index',
  templateUrl: './radnik-index.component.html',
  styleUrl: './radnik-index.component.css'
})
export class RadnikIndexComponent {
  createRow() {
    this.router.navigate(['/home-hr/dodaj-radnika']);
    }
    onSearch(value: string) {
      console.log(value);
      this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
        console.log(this.dtElement);
        dtInstance.search(value).draw();
      });
    }
    
      radnici: Radnik[] = [];
      dtOptions: DataTables.Settings = {};
      @ViewChild(DataTableDirective, { static: false })
      dtElement!: DataTableDirective;
    
      constructor(private service: HttpService, private el: ElementRef, private router: Router) { }
    
      ngOnInit(): void {
        this.dtOptions = {
          searching: true,
          responsive: true,
          pagingType: 'full_numbers',
          destroy: true,
          dom: '<"tableHeder">rt<"tableFooter"lpi><"clear">',
          columnDefs: [
            {
              targets: 3
            }
          ],
          ajax: (dataTablesParameters: any, callback) => {
            this.service
              .getList(
                'evidencijaRadnogVremena/dajSveRadnike.php?user_id=111111111').subscribe((resp: any) => {
                  callback({
                    recordsTotal: resp.recordsTotal,
                    recordsFiltered: resp.recordsFiltered,
                    data: resp
                  });
                });
          },
          order: [[1, "asc"]],
          columns: [
            { data: "id_osoba", title: "ID", visible: false },
            { data: "ime", title: "Ime", },
            { data: "prezime", title: "Prezime", },
            {
              className: "align-middle",
              width: "40px",
              data: null,
              orderable: false,
              render: function (data, type, row) {
                var html = '<div class="buttons">';
                html += '<a data-action="details" href="javascript:;" class="font-weight-bold text details"><i class="material-icons me-2"></i></a>';
                html += '<a data-action="edit" href="javascript:;" class="font-weight-bold text-success edit" ><i class="material-icons me-2"></i></a>';
                html += '<a data-action="delete" href="javascript:;" class="font-weight-bold text-danger delete" ><i class="material-icons me-2"></i></a>';
                html += '</div>'
    
                return html;
    
              }
            }
          ],
        }
      }
    
      ngAfterViewInit(): void {
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
          // Additional configuration or actions can be performed here
        });
      }
}
