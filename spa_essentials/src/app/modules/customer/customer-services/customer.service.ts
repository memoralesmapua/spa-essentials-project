import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth-services/storage-service/storage.service';

const BASIC_URL = ["http://localhost:8080/"]

@Injectable({ providedIn: 'root' })
export class CustomerService {
  constructor(private http: HttpClient) {}

  getCategories(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/customer/categories', {
      headers: this.createAuthorizationHeader()
    });
  }

  getProductsByCategory(categoryId: number): Observable<any> {
    return this.http.get(BASIC_URL + `api/customer/categories/${categoryId}/products`, {
      headers: this.createAuthorizationHeader()
    });
  }

  getReviews(productId: number): Observable<any> {
    return this.http.get(BASIC_URL + `api/customer/products/${productId}/reviews`, {
      headers: this.createAuthorizationHeader()
    });
  }

  addReview(productId: number, payload: { rating: number; comment: string }): Observable<any> {
    return this.http.post(BASIC_URL + `api/customer/products/${productId}/reviews`, payload, {
      headers: this.createAuthorizationHeader()
    });
  }

  private createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set('Authorization', 'Bearer ' + StorageService.getToken());
  }
}


