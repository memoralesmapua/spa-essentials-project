import { Component } from '@angular/core';
import { CustomerService } from '../../customer-services/customer.service';
import { CartService, CartItem } from '../../customer-services/cart.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  categories: any[] = [];
  products: any[] = [];
  selectedCategoryId: number | null = null;
  cartItems: CartItem[] = [];
  productReviews: { [productId: number]: any[] } = {};
  reviewDraft: { [productId: number]: { rating: number; comment: string } } = {};

  constructor(private customerService: CustomerService, private cartService: CartService) {}

  ngOnInit(): void {
    this.loadCategories();
    this.refreshCart();
  }

  loadCategories(): void {
    this.customerService.getCategories().subscribe(res => {
      res.forEach((c: any) => {
        c.processedImg = 'data:image/jpeg;base64,' + c.returnedImg;
      });
      this.categories = res;
    });
  }

  selectCategory(categoryId: number): void {
    this.selectedCategoryId = categoryId;
    this.products = [];
    this.customerService.getProductsByCategory(categoryId).subscribe(res => {
      res.forEach((p: any) => {
        p.processedImg = 'data:image/jpeg;base64,' + p.returnedImg;
      });
      this.products = res;
      // Load reviews for each product
      this.products.forEach(p => this.loadReviews(p.id));
      // Initialize review drafts for template safety
      this.products.forEach(p => {
        if (!this.reviewDraft[p.id]) {
          this.reviewDraft[p.id] = { rating: 0, comment: '' };
        }
      });
    });
  }

  addToCart(product: any): void {
    const item: CartItem = {
      id: product.id,
      name: product.name,
      price: product.price ?? 0,
      quantity: 1,
      processedImg: product.processedImg
    };
    this.cartService.add(item);
    this.refreshCart();
  }

  addCategoryToCart(category: any): void {
    const item: CartItem = {
      id: category.id,
      name: category.name,
      price: category.price ?? 0,
      quantity: 1,
      processedImg: category.processedImg
    };
    this.cartService.add(item);
    this.refreshCart();
  }

  buyCategory(category: any): void {
    this.addCategoryToCart(category);
    this.checkout();
  }

  removeFromCart(id: number): void {
    this.cartService.remove(id);
    this.refreshCart();
  }

  refreshCart(): void {
    this.cartItems = this.cartService.getItems();
  }

  checkout(): void {
    const total = this.cartService.total();
    const formatted = new Intl.NumberFormat('en-PH', { style: 'currency', currency: 'PHP' }).format(total);
    alert(`Purchase successful! Total: ${formatted}`);
    this.cartService.clear();
    this.refreshCart();
  }

  cartTotal(): number {
    return this.cartService.total();
  }

  loadReviews(productId: number): void {
    this.customerService.getReviews(productId).subscribe(res => {
      this.productReviews[productId] = res;
    });
  }

  submitReview(productId: number): void {
    const draft = this.reviewDraft[productId];
    if (!draft || !draft.rating || draft.rating < 1 || draft.rating > 5) {
      return;
    }
    this.customerService.addReview(productId, { rating: draft.rating, comment: draft.comment || '' }).subscribe(() => {
      this.reviewDraft[productId] = { rating: 0, comment: '' };
      this.loadReviews(productId);
    });
  }
}
