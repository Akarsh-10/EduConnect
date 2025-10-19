// src/app/educonnect/components/studentedit/studentedit.component.ts

import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Student } from "../../models/Student";
import { User } from "../../models/User";
import { ActivatedRoute } from "@angular/router";
import { EduConnectService } from "../../services/educonnect.service";
import { StudentDTO } from "../../models/StudentDTO";

@Component({
  selector: 'app-studentedit',
  templateUrl: './studentedit.component.html',
  styleUrls: ['./studentedit.component.scss']
})
export class StudentEditComponent implements OnInit {
  studentForm: FormGroup;
  student: Student;
  user: User;
  userId: number;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private eduConnectService: EduConnectService
  ) {
    // Initialize the form
    this.studentForm = this.fb.group({
      studentId: [null],
      fullName: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      contactNumber: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required]
    });

    // Get studentId from route params
    const id = this.route.snapshot.paramMap.get('id');
    this.userId = id ? +id : 1; // Default for testing, should be dynamic
  }

  ngOnInit(): void {
    this.loadStudentDetails();
  }

  loadStudentDetails(): void {
    // Fetch student details
    this.eduConnectService.getStudentById(this.userId).subscribe({
      next: (student: Student) => {
        this.student = student;
        this.studentForm.patchValue({
          studentId: student.studentId,
          fullName: student.fullName,
          dateOfBirth: student.dateOfBirth,
          contactNumber: student.contactNumber,
          email: student.email,
          address: student.address
        });
      },
      error: (error) => {
        console.error('Error fetching student details:', error);
        this.errorMessage = 'Failed to load student details.';
      }
    });

    // Fetch user details
    this.eduConnectService.getUserById(this.userId).subscribe({
      next: (user: User) => {
        this.user = user;
        this.studentForm.patchValue({
          username: user.username,
          password: user.password
        });
      },
      error: (error) => {
        console.error('Error fetching user details:', error);
        this.errorMessage = 'Failed to load user details.';
      }
    });
  }

  onSubmit(): void {
    if (this.studentForm.valid) {
      const studentDTO: StudentDTO = this.studentForm.value;
      this.eduConnectService.updateStudent(studentDTO).subscribe({
        next: (updatedStudent: Student) => {
          this.student = updatedStudent;
          this.successMessage = 'Student updated successfully!';
          this.errorMessage = null;
        },
        error: (error) => {
          console.error('Error updating student:', error);
          this.errorMessage = 'Failed to update student.';
          this.successMessage = null;
        }
      });
    } else {
      this.errorMessage = 'Please fill out the form correctly.';
      this.successMessage = null;
    }
  }
}