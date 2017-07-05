import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-dynamic-input-list',
  templateUrl: './dynamic-input-list.component.html',
  styleUrls: ['./dynamic-input-list.component.css']
})
export class DynamicInputListComponent implements OnInit {

  @Input() translations: any[];
  public newTranslation: string;
  constructor() { }

  ngOnInit() {
    console.log('', this.translations);
  }

  public removeTranslation(transltion: any) {
    const index = this.translations.indexOf(transltion);
    this.translations.splice(index, 1);
  }

   public addTranslation() {
    this.translations.push(Object.create({id: null, value: this.newTranslation}));
    this.newTranslation = '';
  }
}