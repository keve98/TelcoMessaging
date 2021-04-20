import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MessageService } from './message.service';
import {HttpClientModule} from "@angular/common/http";
import { HomeComponent } from './home';
import { SendComponent } from './send';
import { ReceiveComponent } from './receive';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SendComponent,
    ReceiveComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
