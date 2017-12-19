export class DataModel {
  collName: string;
  fieldName: string;
  sortable = false;

  constructor(collName: string, fieldName: string, sortable?: boolean) {
    this.collName = collName;
    this.fieldName = fieldName;
    if (typeof sortable !== 'undefined' ) {
      this.sortable = sortable;
    }

}

}
