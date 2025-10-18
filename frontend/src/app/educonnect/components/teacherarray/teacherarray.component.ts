import { Component } from "@angular/core";
import { Teacher } from "../../models/Teacher";

@Component({
    selector:'teacher-array',
    templateUrl: './teacherarray.component.html',
    styleUrls: ['./teacherarray.component.scss']
})

export class TeacherArrayComponent  {
    teachers: Teacher[]=[new Teacher(10,"Dough","98765432","werwer@try.com","English",20),
        new Teacher(11,"Dough","98765432","werwer@try.com","English",20),
        new Teacher(12,"Dough","98765432","werwer@try.com","English",20)
    ];
    showDetails: boolean=true;
    constructor()
    {

    }

    toggleDetails()
    {
        this.showDetails=!this.showDetails;
    }
 

}
