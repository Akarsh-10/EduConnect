import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { EduConnectRoutingModule } from "./educonnect-routing.module";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
// import { StudentSampleComponent } from "./components/studentsample/studentsample.component";
@NgModule({
  declarations: [
  // StudentSampleComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  exports: [
    
  ]
})
export class EduconnectModule {}
