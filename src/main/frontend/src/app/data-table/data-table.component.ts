import { Word } from './../models/word';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { DoCheck } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit {

  @Input() data = [];
  @Input() pagination: Boolean = false;
  @Input() rowsPerPage = 10;
  @Input() start = 0;

  @Output() onEdit = new EventEmitter<Word>();

  private tableData: Word[];
  private totalPages = [];
  private currentPage = 1;
  private sortBy = '';
  private orderBy = 'asc';

  constructor() {

  }


  ngOnInit() {
    console.log('init', this.data.length);
    this.sort();

    let i = this.start.valueOf();
    this.tableData = new Array();
    this.totalPages = [];
    this.totalPages.push(1);

    for (let j = 0; j < this.data.length; j++) {
      if (j % this.rowsPerPage === 0 && j !== 0) {
        this.totalPages.push(this.totalPages.length + 1);
      }
    }
    for (i; i < this.rowsPerPage; i++) {
      this.tableData.push(this.data[i]);
    }

    console.log('init', this.totalPages);
  }

  public changePage(page: number, event: Event): void {
    event.preventDefault();
    if (page < 1 || page > this.totalPages.length) {
      return;
    }
    this.currentPage = page;
    let last = page * this.rowsPerPage;
    if (last > this.data.length) {
      last = this.data.length;
    }
    let i = (page * this.rowsPerPage) - this.rowsPerPage;
    this.tableData = [];
    for (i; i < last; i++) {
      this.tableData.push(this.data[i]);
    }

  }
  public onSort(fieldName: string, event: Event) {
    event.preventDefault();
    this.sortBy = fieldName;
    if (this.orderBy === 'desc') {
      this.orderBy = 'asc';
    }else {
      this.orderBy = 'desc';
    }
    this.sort();
    this.changePage(this.currentPage, event);
  }

  private sort() {
    let order = 1;
    if (this.orderBy === 'desc') {
      order = -1;
    }

    if (this.sortBy !== null) {
      this.data.sort((obj1, obj2) => {
        if (obj1[this.sortBy] > obj2[this.sortBy]) {
          return order;
        }
        if (obj2[this.sortBy] > obj1[this.sortBy]) {
          return order * -1;
        }
        return 0;
      });
    }

    this.data.map((value, index) => {
      value.index = index + 1;
    });
  }

  public onDataSave(inputData) {
    this.data = inputData;
    this.ngOnInit();
  }

  onDataEdit(word: Word) {
    this.onEdit.emit(word);
  }


}
