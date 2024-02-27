import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-post-upload',
  templateUrl: './post-upload.component.html',
  styleUrls: ['./post-upload.component.css']
})
export class PostUploadComponent {
  comments: string = '';
  picture: File | null = null;

  constructor(private http: HttpClient) {}

  onFileSelected(event: any) {
    this.picture = event.target.files[0];
  }

  uploadPost() {
    if (!this.comments || !this.picture) {
      console.error('Comments and picture are required.');
      return;
    }

    const formData = new FormData();
    console.log("picture collected", this.picture)
    formData.append('comments', this.comments);
    formData.append('picture', this.picture);

    this.http.post<any>('/api/post', formData).subscribe(
      response => {
        console.log('Post uploaded successfully!', response);
      },
      error => {
        console.error('Failed to upload post!', error);
      }
    );
  }
}
