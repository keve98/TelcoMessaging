import { Message } from './message';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http'
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})

export class MessageService{
    private apiServerUrl=environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public getMessages():Observable<Message[]>{
        return this.http.get<Message[]>(`${this.apiServerUrl}/messages`);
    }

    public receiveMessage():Observable<Message>{
        return this.http.get<Message>(`${this.apiServerUrl}/receive`);
    }

    public sendMessage(message: String): Observable<Message>{
        return this.http.post<Message>(`${this.apiServerUrl}/send/${message}`, message);
    }

    public deleteAll(): Observable<void>{
        return this.http.delete<void>(`${this.apiServerUrl}/messages/delete`);
    }

    public deleteById(id: number): Observable<Message>{
        return this.http.delete<Message>(`${this.apiServerUrl}/messages/delete/${id}`);
    }
}
