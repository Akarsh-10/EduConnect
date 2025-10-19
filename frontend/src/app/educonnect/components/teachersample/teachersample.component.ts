import { Component } from "@angular/core";
import { Teacher } from "../../models/Teacher";

@Component({
    selector:"teacher-sample",
    templateUrl:"./teachersample.component.html",
    styleUrls:["./teachersample.component.scss"],
    standalone: true
})

export class TeacherSampleComponent  {
    teacher:Teacher;

    constructor()
    {
        this.teacher=new Teacher(10,"Dough","98765432","werwer@try.com","English",20);
    }

    logTeacherAttributes() :void
    {
        console.log("teacher:",this.teacher);
    }
  
}
