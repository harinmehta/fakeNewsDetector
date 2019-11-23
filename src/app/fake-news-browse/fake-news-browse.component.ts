import { Component, OnInit } from '@angular/core';
import {PostService} from '../services/post.service';


@Component({
  selector: 'app-fake-news-browse',
  templateUrl: './fake-news-browse.component.html',
  styleUrls: ['./fake-news-browse.component.css']
})
export class FakeNewsBrowseComponent implements OnInit {
  news=[];
  trendingnews=[];
  constructor(private postService:PostService) { }

  ngOnInit() {
    this.GetAllNews();
    this.GetTrendingNews();
  }

  GetAllNews(){
    this.postService.GetAllNews().subscribe(res=>{
      //console.log(res);
      // for(const i in res){
      //   this.news.push(res[i]);
      // }
      for(const i in res){
        this.news.push({
          id:res[i].id,
          headLine:res[i].headLine,
          newsData:res[i].newsData,
          createdDate:res[i].createdDate
        })
      }
      console.log(this.news);
    })
  }

  GetTrendingNews(){
    this.postService.GetTrendingNews().subscribe(res=>{
      for(let i=0;i<5;i++){
        this.trendingnews.push({
          id:res[i].id,
          headLine:res[i].headLine,
          newsData:res[i].newsData,
          createdDate:res[i].createdDate
        })
      }
      console.log(this.trendingnews);
    })
  }

}
