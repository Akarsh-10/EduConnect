import { Component } from "@angular/core";
import { Student } from "../../models/Student";

@Component({
    selector:`student-component`,
    templateUrl:`./studentsample.component.html`,
    styleUrls:[`./studentsample.component.scss`]
})

export class StudentSampleComponent {
  student: Student;
  // successMessage: string;
  // errorMessage: string;

  constructor(student: Student)//, successMessage: string , errorMessage: string)
  {
    this.student=student;
    // this.successMessage=successMessage;
    // this.errorMessage=errorMessage;
  }
  onSubmit(): void
  {

  }

  resetForm(): void
  {
    
  }
}
