import { Component } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-post-product',
  templateUrl: './post-product.component.html',
  styleUrls: ['./post-product.component.scss']
})
export class PostProductComponent {

  constructor(
    private service: AdminService,
  ) { }

  ngOnInit(){
    this.getAllCategories();
  }

  getAllCategories(){
    this.service.getAllCategories().subscribe((res)=>{
      console.log(res);
    })
  }

}
