import { Component, OnInit,ViewChild } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {PostService} from '../services/post.service';

@Component({
  selector: 'app-fake-news-user-dashboard',
  templateUrl: './fake-news-user-dashboard.component.html',
  styleUrls: ['./fake-news-user-dashboard.component.css']
})
export class FakeNewsUserDashboardComponent implements OnInit {
  Data:{
    title:any,
    description:any,
    category:any
  }
  showMessage="";
  content: string = '<p>Add Description</p>';  config: any = {
    allowedContent: true,
    toolbar: [['Bold', 'Italic', 'Underline', '-', 'NumberedList', 'BulletedList', 'Link', '-', 'CreatePlaceholder','Image']],
    removePlugins: 'elementspath',
    resize_enabled:false,
    extraPlugins: 'font,divarea,placeholder',
    contentsCss: ["body {font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;}"],
    autoParagraph: false,
    enterMode: 2
  };
  @ViewChild('file',{static:false}) moment;
  @ViewChild('title',{static:false}) title;
  @ViewChild('category',{static:false}) category;
  filesToUpload: Array<File>;
  files=[];
  imageName=[];
  path;
  constructor(private cookieService:CookieService,private postService:PostService) { 
    this.filesToUpload = [];
  }

  ngOnInit() {
  }
  makeFileRequest(url: string, params: Array<string>, files: Array<File>) {
    this.imageName = ['path'];
    return new Promise((resolve, reject) => {
        const formData: any = new FormData();
        const xhr = new XMLHttpRequest();
        for (let i = 0; i < files.length; i++) {
            formData.append(this.imageName[i], files[i]);
        }
        formData.append("title",this.title.nativeElement.value);
        formData.append("description",this.content);
        formData.append("category",this.category);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    resolve(JSON.parse(xhr.response));
                } else {
                    reject(xhr.response);
                }
            }
        };
        xhr.open('POST', url, true);
        xhr.send(formData);
    });
  }

  UploadNews(){
    this.path=this.moment.nativeElement.files[0];
   this.files=[this.path];
   console.log(this.files);
   this.Data={
     title:this.title.nativeElement.value,
     description:this.content,
     category:this.category.nativeElement.value
   }
   console.log(this.Data);
    this.imageName=['path'];
    for (let i = 0 ; i < this.files.length; i++ ) {
     this.filesToUpload.push(<File> this.files[i]);
   }
   
  //  this.makeFileRequest('', [], this.filesToUpload)
  //  .then((result) => {   
  //  }, (error) => {
  //    console.error(error);
  //    if (error) {
   
  //    }
  //  });
  }

  AddNews(){
    let title=this.title.nativeElement.value;
    let description=this.content;
    let category=this.category.nativeElement.value;
    let userid=this.cookieService.get('userId');
    this.postService.AddNews(title,description,userid).subscribe(res=>{
      console.log(res);
      if(res){
        this.showMessage="News Uploded Successfully";
      }
    })
  }

}
