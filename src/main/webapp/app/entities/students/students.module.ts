import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CoursesSharedModule } from 'app/shared/shared.module';
import { StudentsComponent } from './students.component';
import { StudentsDetailComponent } from './students-detail.component';
import { StudentsUpdateComponent } from './students-update.component';
import { StudentsDeleteDialogComponent } from './students-delete-dialog.component';
import { studentsRoute } from './students.route';

@NgModule({
  imports: [CoursesSharedModule, RouterModule.forChild(studentsRoute)],
  declarations: [StudentsComponent, StudentsDetailComponent, StudentsUpdateComponent, StudentsDeleteDialogComponent],
  entryComponents: [StudentsDeleteDialogComponent]
})
export class CoursesStudentsModule {}
