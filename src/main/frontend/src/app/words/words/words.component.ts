import { DataModel } from './../../data-table/data-model';
import { DataTableComponent } from './../../data-table/data-table.component';
import { AlertService } from './../../services/alert.service';
import { Response } from '@angular/http';
import { Word } from './../../models/word';
import { WordsService } from './../../services/words.service';
import { Component, OnInit, ViewChild, EventEmitter} from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-words',
  templateUrl: './words.component.html',
  styleUrls: ['./words.component.css']
})
export class WordsComponent implements OnInit {

  public words: Word[];
  public editedWord: Word;
  public dataTableModel: DataModel[];

  @ViewChild('childModal')
  public childModal: ModalDirective;

  @ViewChild('childTable')
  public childTable: DataTableComponent<Word>;

  constructor(
    private wordsService: WordsService,
    private alertService: AlertService) { }

  ngOnInit() {
    this.editedWord = new Word();
    this.dataTableModel = [new DataModel('Słówko', 'name', true), new DataModel('Tłumaczenie', 'translations', false)];
    this.wordsService.getAll().subscribe(words => {
      this.words = words;
    });
  }

  onEdit(word: Word): void {
    console.log(word);
    if (word == null) {
      this.editedWord =  new Word();
      this.editedWord.translations = [];
    }else {
      this.editedWord =  JSON.parse(JSON.stringify(word));
    }

    this.childModal.show();
  }

  public hideEditModal(): void {
    this.editedWord = new Word();
    this.childModal.hide();
  }

  public saveEditedWord() {
    this.wordsService.saveOrUpdate(this.editedWord).subscribe((response: Response) => {
      this.hideEditModal()
      this.alertService.success('Dodano slowo');
      this.wordsService.getAll().subscribe(words => {
      this.words = words;
      this.childTable.onDataSave(this.words);
    });
    }, error => {
      this.alertService.error('Blad dodania slowa!');
    }) ;
  }

}
