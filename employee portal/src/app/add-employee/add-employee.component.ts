import { Component } from '@angular/core';
import { Employee } from '../employee';
import { Observable } from 'rxjs';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';





@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html'
  ,
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent
{
  


  id: number;
  employee: Employee = new Employee();
  constructor(private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router) { 
      this.id=0
    }
  

  submitform!: NgForm;

  saveEmployee() {
    console.log("create");
    this.employeeService.addEmployee(this.employee).subscribe((data) =>  {
      console.log(data);
      console.log(this.employee);
      this.goToEmployeeList();
    },
      error => console.log(error));
  }

  goToEmployeeList() {
    this.router.navigate(['/all-employees']);
  }

  ngOnInit(): void { 

    this.id = this.route.snapshot.params['id'];
  
    if(this.id !== 0 && this.id != undefined){
 
      this.employeeService.getEmployeeById(this.id).subscribe(data => {
        this.employee = data;
      }, error => console.log(error));
   
    }
   
 
  }
  onSubmit() {

    if(this.id !== 0 && this.id != undefined){
 
    this.employeeService.updateEmployee(this.id, this.employee).subscribe( data =>{
      this.goToEmployeeList();
    }
    , error => console.log(error));

  }
  else{
    this.saveEmployee();
  }
  


    
  }


}









