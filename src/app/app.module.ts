import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FakeNewsHeaderComponent } from './fake-news-header/fake-news-header.component';
import { FakeNewsLandingPageComponent } from './fake-news-landing-page/fake-news-landing-page.component';
import { FakeNewsLoginComponent } from './fake-news-login/fake-news-login.component';
import { FakeNewsSignupComponent } from './fake-news-signup/fake-news-signup.component';
import { FakeNewsFooterComponent } from './fake-news-footer/fake-news-footer.component';
import { FakeNewsBrowseComponent } from './fake-news-browse/fake-news-browse.component';
import { FakeNewsOpenComponent } from './fake-news-open/fake-news-open.component';
import { CKEditorModule } from 'ng2-ckeditor';
import { FormsModule } from '@angular/forms';
import { FakeNewsEditorPanelComponent } from './fake-news-editor-panel/fake-news-editor-panel.component';
import { CookieService } from 'ngx-cookie-service';
import {AuthService} from './services/auth.service';
import {AuthGuard} from './services/auth-guard.service';
import { PostService } from './services/post.service';
import { TimeService } from './services/time.service';
import { FakeNewsAdminDashboardComponent } from './fake-news-admin-dashboard/fake-news-admin-dashboard.component';
import { FakeNewsUserDashboardComponent } from './fake-news-user-dashboard/fake-news-user-dashboard.component';
import { FakeNewsAdminReviewComponent } from './fake-news-admin-review/fake-news-admin-review.component';
@NgModule({
  declarations: [
    AppComponent,
    FakeNewsHeaderComponent,
    FakeNewsLandingPageComponent,
    FakeNewsLoginComponent,
    FakeNewsSignupComponent,
    FakeNewsFooterComponent,
    FakeNewsBrowseComponent,
    FakeNewsOpenComponent,
    FakeNewsEditorPanelComponent,
    FakeNewsAdminDashboardComponent,
    FakeNewsUserDashboardComponent,
    FakeNewsAdminReviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CKEditorModule, 
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthService,
    AuthGuard,
    CookieService,
    PostService,
    TimeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
