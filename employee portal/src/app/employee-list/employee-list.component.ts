import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import{FormsModule} from '@angular/forms'
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Employee[];
  EnteredID!:number;
  page:number=0;
  pageSize:number=10;
  employeeCount=0;


  sort:string='id';
  sortOrder:string='desc';
  constructor(private employeeService: EmployeeService,  private router: Router) {
    this.employees=[];
   
   }

  ngOnInit(): void {
    

    
    this.getEmployees();
  }

  onPageChange(pageNum:number){
  
    this.page=pageNum;
    this.getEmployees();
   
  }
  onSortChange(sort: string) {
    console.log("changeeee");
    this.sort = sort;
    // Toggle sort order if the same column is clicked again
    this.sortOrder= (this.sortOrder === 'asc') ? 'desc' : 'asc';
    this.employeeService.getEmployees(this.page, this.sort, this.sortOrder).subscribe((data) =>  {
      this.employees = data.content;});

  }
  
  goToEmployee(){

    
    console.log(this.EnteredID); 
    this.router.navigate(['details-of-employee',this.EnteredID]);
  }

  getEmployees(){
    console.log("ggggg");
    this.employeeService.getEmployees(this.page, this.sort, this.sortOrder).subscribe((data) =>  {
      this.employees = data.content;
    this.employeeCount = data.totalElements;});
    
  }

  updateEmployee(id: number){

    
    this.router.navigate(['update-employee', id]);

  }




  deleteEmployee(id: number){

    if(confirm("Are you sure to delete Employee ID: "+id)){
    this.employeeService.deleteEmployee(id).subscribe( data => {
      console.log(data);
      this.getEmployees();
    })}
  }

  
}
