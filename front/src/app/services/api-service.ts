import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs/internal/Observable';
import { catchError } from 'rxjs/internal/operators/catchError';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private http = inject(HttpClient);
  private baseUrl = environment.apiUrl;

  // Métodos genéricos GET, POST, PUT, DELETE [cite: 471]
  get<T>(endpoint: string): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}${endpoint}`).pipe(catchError(this.handleError));
  }

  post<T>(endpoint: string, body: any): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}${endpoint}`, body).pipe(catchError(this.handleError));
  }

  put<T>(endpoint: string, body: any): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}${endpoint}`, body).pipe(catchError(this.handleError));
  }

  delete<T>(endpoint: string): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}${endpoint}`).pipe(catchError(this.handleError));
  }

  // Manejo de errores HTTP global [cite: 472]
  private handleError(error: HttpErrorResponse) {
    console.error('Ocurrió un error en la API:', error);
    // Aquí podríamos integrar un Toast de PrimeNG para mostrar el error al usuario
    return throwError(() => new Error('Algo salió mal; por favor intenta de nuevo más tarde.'));
  }
}
