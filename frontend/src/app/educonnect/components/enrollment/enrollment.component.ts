import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Course } from "../../models/Course";

@Component({
    selector:'app-enrollment',
    templateUrl: './enrollment.component.html',
    styleUrls:['./enrollment.component.scss']
})

export class EnrollmentComponent {
    enrollmentForm: FormGroup;
    successMessage: string;
    errorMessage:string;
    
    constructor(private fb: FormBuilder)
    {
        this.enrollmentForm= this.fb.group({
            enrollmentId: [null],
            studentId: [null, Validators.required],
            courseId: [null, Validators.required],
            enrollmentDate: [null, Validators.required]
        });
    }

    onSubmit(): void
    {
        if(this.enrollmentForm.valid)
        {
            this.successMessage= 'Enrollment created successfully!';
            this.errorMessage='';
            this.enrollmentForm.reset();
        }
        else
        {
            this.errorMessage='Please fill in all required fields.';
            this.successMessage='';
        }
    }
    resetForm(): void
    {
        this.enrollmentForm.reset();
        this.successMessage='';
        this.errorMessage='';
    }
}
