import { HttpErrorResponse } from "@angular/common/http";
import { Message } from "@angular/compiler/src/i18n/i18n_ast";
import { Component } from "@angular/core";
import { MessageService } from "../message.service";


@Component({templateUrl : 'receive.component.html'})
export class ReceiveComponent{
    public receivedMessage : Message | undefined;

    constructor(private messageService: MessageService){}

    public receive() : void{
        this.messageService.receiveMessage().subscribe(
            (result) => {
                if(result === null){
                    alert("There are no messages in the mailbox.");
                }else{
            
            (<HTMLInputElement>document.getElementById("receivedmessagetext")).value = result.text;
                }
        })
    }
}