import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { CustomerRoutingModule } from './customer-routing.module';
import { DashboardComponent } from './customer-components/dashboard/dashboard.component';
import { DemoNgZorroAntdModule } from 'src/app/DemoNgZorroAntdModule';


@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    FormsModule,
    DemoNgZorroAntdModule
  ]
})
export class CustomerModule { }
