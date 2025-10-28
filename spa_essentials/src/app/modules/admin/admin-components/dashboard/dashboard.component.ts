import { Component } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzButtonSize } from 'ng-zorro-antd/button';
import { Title } from '@angular/platform-browser';
import { NzModalService } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

    categories: any = [];
    validateForm!: FormGroup;
    updateForm!: FormGroup;
    size: NzButtonSize = 'large';
    isSpinning: boolean;
    isUpdateModalVisible = false;
    selectedCategory: any = null;

    constructor(
    private adminService: AdminService,
    private fb: FormBuilder,
    private modal: NzModalService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      title: [null, [Validators.required]]
    });
    
    this.updateForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]]
    });
    
    this.getAllCategories();
  }

  submitForm(){
    this.isSpinning = true;
    this.categories = [];
    this.adminService.getAllCategoriesByTitle(this.validateForm.get(['title'])!.value).subscribe((res) => {
      console.log(res);
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.categories.push(element);
        this.isSpinning = false;
      })
    })
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

  deleteCategory(categoryId: number) {
    this.modal.confirm({
      nzTitle: 'Confirm Delete',
      nzContent: 'Are you sure you want to delete this category?',
      nzOkText: 'Yes',
      nzCancelText: 'No',
      nzOnOk: () => {
        this.adminService.deleteCategory(categoryId).subscribe(() => {
          this.categories = this.categories.filter(c => c.id !== categoryId);
        });
      }
    });
  }

  showUpdateModal(category: any) {
    this.selectedCategory = category;
    this.updateForm.patchValue({
      name: category.name,
      description: category.description
    });
    this.isUpdateModalVisible = true;
  }

  handleUpdateCancel() {
    this.isUpdateModalVisible = false;
    this.selectedCategory = null;
  }

  handleUpdateOk() {
    if (this.updateForm.valid && this.selectedCategory) {
      const formData = new FormData();
      formData.append('name', this.updateForm.get('name')?.value);
      formData.append('description', this.updateForm.get('description')?.value);
      
      this.adminService.updateCategory(this.selectedCategory.id, formData).subscribe(
        () => {
          this.isUpdateModalVisible = false;
          this.getAllCategories();
          this.selectedCategory = null;
        }
      );
    }
  }
  
}
