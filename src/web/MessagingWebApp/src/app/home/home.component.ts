import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Message } from '../message';
import { MessageService } from '../message.service';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent implements OnInit{
    public messages: Message[] | undefined;


    constructor(private messageService : MessageService){}
  
    ngOnInit(){
      this.getMessages();
    }
  
    public getMessages(): void{
      this.messageService.getMessages().subscribe(
        (response: Message[]) => {
          this.messages = response;
      
            if(this.messages?.length == 0){
              (<HTMLInputElement>document.getElementById('messagesinlist')).innerText = 'There are no messages in the database.'
            }else{
              (<HTMLInputElement>document.getElementById('messagesinlist')).innerText = '';
            }
            
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }

    public deleteList(): void {
      if(this.messages?.length == 0){
        alert("There is no data in the list");
      }else{
      this.messageService.deleteAll().subscribe();
      this.messages = [];
      alert("List deleted.");
      window.location.reload();
      }
    }


}