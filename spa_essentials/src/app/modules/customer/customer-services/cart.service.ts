import { Injectable } from '@angular/core';

export interface CartItem {
  id: number;
  name: string;
  price: number;
  quantity: number;
  processedImg?: string;
}

@Injectable({ providedIn: 'root' })
export class CartService {
  private storageKey = 'cart_items';

  getItems(): CartItem[] {
    const raw = localStorage.getItem(this.storageKey);
    return raw ? JSON.parse(raw) : [];
  }

  saveItems(items: CartItem[]): void {
    localStorage.setItem(this.storageKey, JSON.stringify(items));
  }

  add(item: CartItem): void {
    const items = this.getItems();
    const existing = items.find(i => i.id === item.id);
    if (existing) {
      existing.quantity += item.quantity;
    } else {
      items.push(item);
    }
    this.saveItems(items);
  }

  remove(id: number): void {
    const items = this.getItems().filter(i => i.id !== id);
    this.saveItems(items);
  }

  clear(): void {
    this.saveItems([]);
  }

  total(): number {
    return this.getItems().reduce((sum, i) => sum + i.price * i.quantity, 0);
  }
}


