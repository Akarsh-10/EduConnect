import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Teacher } from "../../models/Teacher";
import { User } from "../../models/User";
import { EduConnectService } from "../../services/educonnect.service";

@Component({
  selector: 'app-teacheredit',
  templateUrl: './teacheredit.component.html',
  styleUrls: ['./teacheredit.component.scss']
})
export class TeacherEditComponent implements OnInit {
  teacherForm: FormGroup;
  teacher: Teacher | undefined;
  user: User | undefined;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  teacherId: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private eduConnectService: EduConnectService
  ) {
    // Initialize the form
    this.teacherForm = this.fb.group({
      fullName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      subject: ['', Validators.required],
      contactNumber: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      yearsOfExperience: [0, [Validators.required, Validators.min(0)]]
    });

    // Get teacherId from route params or localStorage
    const id = this.route.snapshot.paramMap.get('id') || localStorage.getItem('user_id');
    this.teacherId = id ? +id : 1; // Default for testing
  }

  ngOnInit(): void {
    this.loadTeacherDetails();
  }

  loadTeacherDetails(): void {
    // Fetch teacher details
    this.eduConnectService.getTeacherById(this.teacherId).subscribe({
      next: (teacher: Teacher) => {
        this.teacher = teacher;
        this.teacherForm.patchValue({
          fullName: teacher.fullName,
          subject: teacher.subject,
          contactNumber: teacher.contactNumber,
          email: teacher.email,
          yearsOfExperience: teacher.yearsOfExperience
        });
      },
      error: (error) => {
        console.error('Error fetching teacher details:', error);
        this.teacher = undefined;
        this.errorMessage = 'Failed to load teacher details.';
      }
    });

    // Fetch user details
    this.eduConnectService.getUserById(this.teacherId).subscribe({
      next: (user: User) => {
        this.user = user;
        this.teacherForm.patchValue({
          username: user.username,
          password: user.password
        });
      },
      error: (error) => {
        console.error('Error fetching user details:', error);
        this.user = undefined;
        this.errorMessage = 'Failed to load user details.';
      }
    });
  }

  onSubmit(): void {
    if (!this.teacherForm.valid) {
      const teacherData = {
        ...this.teacherForm.value,
        teacherId: this.teacherId
      };
      this.eduConnectService.updateTeacher(teacherData).subscribe({
        next: (updatedTeacher: Teacher) => {
          this.teacher = updatedTeacher;
          this.successMessage = 'Teacher updated successfully!';
          this.errorMessage = null;
        },
        error: (error) => {
          console.error('Error updating teacher:', error);
          this.errorMessage = 'Failed to update teacher.';
          this.successMessage = null;
        }
      });
    } else {
      this.errorMessage = 'Please fill out the form correctly.';
      this.successMessage = null;
    }
  }
}