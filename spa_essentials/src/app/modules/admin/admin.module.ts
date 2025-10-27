import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './admin-components/dashboard/dashboard.component';
import { AddCategoryComponent } from './admin-components/add-category/add-category.component';
import { DemoNgZorroAntdModule } from "src/app/DemoNgZorroAntdModule";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';
import { PostProductComponent } from './admin-components/post-product/post-product.component';
import { NzResizeObserverModule } from "ng-zorro-antd/cdk/resize-observer";


@NgModule({
  declarations: [
    DashboardComponent,
    AddCategoryComponent,
    PostProductComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NzResizeObserverModule
]
})
export class AdminModule { }
