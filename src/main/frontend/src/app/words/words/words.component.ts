import { Word } from './../../models/word';
import { WordsService } from './../../services/words.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-words',
  templateUrl: './words.component.html'
})
export class WordsComponent implements OnInit {

  words: Word[];

  constructor(private wordsService: WordsService) { }

  ngOnInit() {
    this.wordsService.getAll().subscribe(words => {
      this.words = words;

    });

  }

}
