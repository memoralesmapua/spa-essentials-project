import { Component } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.scss']
})
export class ViewProductsComponent {
  categoryId: any = this.activatedroute.snapshot.params['categoryId'];
  Products: any = [];
  isSpinning: boolean;

  constructor(private adminService: AdminService,
    private activatedroute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getProductsByCategory();
  }

  getProductsByCategory(){
    this.Products = [];
    this.adminService.getProductsByCategory(this.categoryId).subscribe((res) => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.Products.push(element);
      });
    });
  }

}
