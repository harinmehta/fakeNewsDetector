import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FakeNewsLandingPageComponent } from './fake-news-landing-page/fake-news-landing-page.component';
import { FakeNewsLoginComponent } from './fake-news-login/fake-news-login.component';
import { FakeNewsSignupComponent } from './fake-news-signup/fake-news-signup.component';
import { FakeNewsBrowseComponent } from './fake-news-browse/fake-news-browse.component';
import { FakeNewsOpenComponent } from './fake-news-open/fake-news-open.component';
import { FakeNewsEditorPanelComponent } from './fake-news-editor-panel/fake-news-editor-panel.component';
import { FakeNewsAdminDashboardComponent } from './fake-news-admin-dashboard/fake-news-admin-dashboard.component';
import { FakeNewsUserDashboardComponent } from './fake-news-user-dashboard/fake-news-user-dashboard.component';
import { FakeNewsAdminReviewComponent } from './fake-news-admin-review/fake-news-admin-review.component';


const routes: Routes = [
  {path:'',component:FakeNewsLandingPageComponent},
  {path:'login',component:FakeNewsSignupComponent},
  //{path:'signup',component:FakeNewsSignupComponent}
  {path:'browse',component:FakeNewsBrowseComponent},
  {path:'browse/news/:name/:id',component:FakeNewsOpenComponent},
  {path:'editor-panel',component:FakeNewsEditorPanelComponent},
  {path:'expert-panel',component:FakeNewsAdminDashboardComponent},
  {path:'user-dashboard/:name/:id',component:FakeNewsUserDashboardComponent},
  {path:'review/Virat-Kohli-breaks-Sachin-record/1',component:FakeNewsAdminReviewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
