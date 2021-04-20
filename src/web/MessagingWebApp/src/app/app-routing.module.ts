import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home';
import { ReceiveComponent } from './receive';
import { SendComponent } from './send';

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'send', component: SendComponent},
    {path: 'receive', component: ReceiveComponent},
    {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
