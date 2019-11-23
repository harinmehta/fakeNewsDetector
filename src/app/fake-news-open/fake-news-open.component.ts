import { Component, OnInit, NgModule,ViewChild } from '@angular/core';
import { CKEditorModule } from 'ng2-ckeditor';
import { FormsModule } from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {PostService} from '../services/post.service';
import {CookieService} from 'ngx-cookie-service';
import * as CanvasJS from '../canvasjs.min';
@Component({
  selector: 'app-fake-news-open',
  templateUrl: './fake-news-open.component.html',
  styleUrls: ['./fake-news-open.component.css']
})


export class FakeNewsOpenComponent implements OnInit {
  @ViewChild('file',{static:false}) moment;
  filesToUpload: Array<File>;
  files=[];
  singleNews=[];
  newsId;
  headLine;
  newsData;
  createdDate;
  IsActive;
  views;
  imageName=[];
  path;
  comments=[];
  CommentData:{
    comment:any
  }
  upvoteString="Upvote";
  downvoteString="Downvote";
  voted:boolean=true;
  fakeNewsPercentage;
  RealNewsPercentage;
  content: string = '<p>Some html</p>';  config: any = {
    allowedContent: true,
    toolbar: [['Bold', 'Italic', 'Underline', '-', 'NumberedList', 'BulletedList', 'Link', '-', 'CreatePlaceholder','Image']],
    removePlugins: 'elementspath',
    resize_enabled:false,
    extraPlugins: 'font,divarea,placeholder',
    contentsCss: ["body {font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;}"],
    autoParagraph: false,
    enterMode: 2
  };
  constructor(private route:ActivatedRoute,private postService:PostService,private cookieService:CookieService) {
    this.filesToUpload = [];
   }
  onSubmit(){
    console.log(this.content);
  }
  ngOnInit() {
    this.receiveIdfromUrl()
    this.GetAllComments();
    this.UserHasVoted();
    this.postService.GetFinalScore(this.newsId).subscribe(res=>{
      console.log(res);
      let upvotescore=res.totalUpScore;
      let downvotescore=res.totalDownScore;
      this.RealNewsPercentage=((parseInt(upvotescore)/(parseInt(upvotescore)+parseInt(downvotescore))*100))
      this.fakeNewsPercentage=((parseInt(downvotescore)/(parseInt(upvotescore)+parseInt(downvotescore))*100))
      console.log(this.RealNewsPercentage, this.fakeNewsPercentage);
      let chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        exportEnabled: true,
        title: {
          text: "Upvotes Downvotes Chart"
        },
        data: [{
          type: "column",
          dataPoints: [
            { y: Math.floor(this.RealNewsPercentage), label: "Upvote" },
            { y: Math.floor(this.fakeNewsPercentage), label: "Downvote" }
          ]
        }]
      });
      chart.render();
    }) 
    if(!this.cookieService.get('userId')){
      this.voted=false;
    }
    // let chart = new CanvasJS.Chart("chartContainer", {
    //   animationEnabled: true,
    //   exportEnabled: true,
    //   title: {
    //     text: "Upvotes Downvotes Chart"
    //   },
    //   data: [{
    //     type: "column",
    //     dataPoints: [
    //       { y: Math.floor(this.RealNewsPercentage), label: "Upvote" },
    //       { y: Math.floor(this.fakeNewsPercentage), label: "Downvote" }
    //     ]
    //   }]
    // });
      
    // chart.render();
  }
   
  receiveIdfromUrl(){
    this.route.params.subscribe(
      (params) => {
        //console.log(params);
        this.newsId=params.id
      }) 
     this.postService.GetSingleNews(this.newsId).subscribe(res=>{
       //console.log(res);
        this.headLine=res.headLine;
        this.newsData=res.newsData;
        this.createdDate=res.createdDate;
        this.IsActive=res.isActive;
        this.views=res.noOfViews;
     })
    //  this.postService.GetFinalScore(this.newsId).subscribe(res=>{
    //    console.log(res);
    //    let upvotescore=res.totalUpScore;
    //    let downvotescore=res.totalDownScore;
    //    this.RealNewsPercentage=((parseInt(upvotescore)/(parseInt(upvotescore)+parseInt(downvotescore))*100))
    //    this.fakeNewsPercentage=((parseInt(downvotescore)/(parseInt(upvotescore)+parseInt(downvotescore))*100))
    //    console.log(this.RealNewsPercentage, this.fakeNewsPercentage);
    //  })  
  }

  makeFileRequest(url: string, params: Array<string>, files: Array<File>) {
    this.imageName = ['path'];
    return new Promise((resolve, reject) => {
        const formData: any = new FormData();
        const xhr = new XMLHttpRequest();
        for (let i = 0; i < files.length; i++) {
            formData.append(this.imageName[i], files[i]);
        }
        formData.append("comment",this.content);
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
  Upvote(){
    let idnews;
    this.route.params.subscribe(
      (params) => {
        //console.log(params);
        idnews=params.id
      })
    let userId=this.cookieService.get('userId');
    this.postService.UpvoteDownvote(userId,idnews,1).subscribe(res=>{
      //console.log(res);
      if(res){
        this.voted=false;
        this.upvoteString="Upvoted";
      }
    })
  }
  Downvote(){
    let idnews;
    this.route.params.subscribe(
      (params) => {
        //console.log(params);
        idnews=params.id
      })
    let userId=this.cookieService.get('userId');
    console.log(userId,idnews,false);
    this.postService.UpvoteDownvote(userId,idnews,0).subscribe(res=>{
      //console.log(res);
      if(res){
        this.voted=false;
        this.downvoteString="Downvoted";
      }
    })
  }
  comment(){
    let idnews;
    this.route.params.subscribe(
      (params) => {
        //console.log(params);
        idnews=params.id
      })
    let userId=this.cookieService.get('userId');
    this.postService.AddCommentOnNews(this.content,idnews,userId).subscribe(res=>{
      //console.log(res);
      if(res){
        this.content="";
        this.postService.GetCommentOnNews(idnews).subscribe(res=>{
          //console.log(res);
          this.comments=[];
          for(const i in res){
            this.comments.push(res[i]);
          }
        })
      }
    })
  }

  UserHasVoted(){
    let idnews;
    this.route.params.subscribe(
      (params) => {
        //console.log(params);
        idnews=params.id
      })
    let userId=this.cookieService.get('userId');
    this.postService.UserHasVoted(userId,idnews).subscribe(res=>{
      //console.log("Hello",res);
      if(res.hasVoted=="Yes"){
        this.voted=false;
        if(res.votedFor=="true"){
          this.upvoteString="Upvoted";
        }
        if(res.votedFor=="false"){
          this.downvoteString="Downvoted";
        }
      }
    })
  }

  GetAllComments(){
    let idnews;
    this.route.params.subscribe(
      (params) => {
        //console.log(params);
        idnews=params.id
      })
    this.postService.GetCommentOnNews(idnews).subscribe(res=>{
      //console.log(res);
      for(const i in res){
        this.comments.push(res[i]);
      }
    })
  }

  upload(){           
   this.path=this.moment.nativeElement.files[0];
   this.files=[this.path];
   console.log(this.files);
   this.CommentData={
     comment:this.content
   }
   console.log(this.CommentData);
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
  
}
