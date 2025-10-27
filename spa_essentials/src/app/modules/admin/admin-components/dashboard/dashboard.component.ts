import { Component } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

    categories: any = [];

    constructor(
    private adminService: AdminService,
  ) { }

  ngOnInit(): void {
    this.getAllCategories();
  }

  getAllCategories(){
    this.categories = [];
    this.adminService.getAllCategories().subscribe((res)=>{
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.categories.push(element);
      });
    });
  }
}
