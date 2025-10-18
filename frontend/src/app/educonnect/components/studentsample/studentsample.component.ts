import { Component } from "@angular/core";
import { Student } from "../../models/Student";

@Component({
    selector:`student-sample`,
    templateUrl:`./studentsample.component.html`,
    styleUrls:[`./studentsample.component.scss`],
    standalone: false
})

export class StudentSampleComponent {
  student: Student;
  // successMessage: string;
  // errorMessage: string;

  constructor()//, successMessage: string , errorMessage: string)
  {
    this.student=new Student(1,"John Doe",null,"98765432","a@y.com","23231,asdasdas");
    // this.successMessage=successMessage;
    // this.errorMessage=errorMessage;
  }
  onSubmit(): void
  {

  }

  resetForm(): void
  {
    
  }
  logStudentAttributes()
  {
    console.log("student:",this.student);
  }
}
