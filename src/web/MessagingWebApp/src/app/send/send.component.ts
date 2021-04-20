import { HttpErrorResponse } from "@angular/common/http";
import { Component, ElementRef, ViewChild } from "@angular/core";
import { Message } from "../message";
import { MessageService } from "../message.service";


@Component({templateUrl : 'send.component.html'})
export class SendComponent{
    public mess: Message | undefined;

    constructor(private messageService: MessageService){
    }


    public send(): void{
        var messagetosend = (<HTMLInputElement>document.getElementById('mts')).value;
        if(messagetosend === ""){
            alert("Type a message into the field");
        }else{
            this.messageService.sendMessage(messagetosend).subscribe(
                (response)=>{
                    this.mess = response;
                    alert("'" + messagetosend+ "'" + " sent.");
                },
                (error: HttpErrorResponse) => {
                    alert(error.message);
                  }
            );
        }
    }
    

}