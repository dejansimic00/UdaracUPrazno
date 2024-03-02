import { AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { HttpService } from '../../../services/http/http.service';
import { Radnik } from '../../../models/radnik';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-smjena-index',
  templateUrl: './smjena-index.component.html',
  styleUrls: ['./smjena-index.component.css']
})
export class SmjenaIndexComponent implements OnInit, AfterViewInit {

  
createRow() {
throw new Error('Method not implemented.');
}
onSearch(arg0: string) {
throw new Error('Method not implemented.');
}

  radnici: Radnik[] = [];
  dtOptions: DataTables.Settings = {};
  @ViewChild(DataTableDirective, { static: false })
  dtElement!: DataTableDirective;

  constructor(private service: HttpService, private el: ElementRef) { }

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
