import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostUploadComponent } from './components/post-upload.component';

const routes: Routes = [
  { path: 'upload', component: PostUploadComponent },
  { path: '**', redirectTo: '/upload', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
