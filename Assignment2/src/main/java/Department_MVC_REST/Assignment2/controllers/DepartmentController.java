package Department_MVC_REST.Assignment2.controllers;


import Department_MVC_REST.Assignment2.dto.DepartmentDto;
import Department_MVC_REST.Assignment2.exceptions.ResourceNotFoundException;
import Department_MVC_REST.Assignment2.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path="/departments")
public class DepartmentController  {

    @Autowired
    private  DepartmentService departmentService;

    @GetMapping(path="{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name="departmentId") Long id){

        Optional<DepartmentDto> departmentDto =departmentService.getDepartmentById(id);
        return departmentDto.map(DepartmentDto1 -> ResponseEntity.ok(DepartmentDto1))
                .orElseThrow(()-> new ResourceNotFoundException("Department Not Found By Id "+  id));

    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createNewDepartment(@RequestBody DepartmentDto inputDepartment){

        DepartmentDto savedDepartment =departmentService.createNewDepartment(inputDepartment);

        return new ResponseEntity<DepartmentDto>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping(path="/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@RequestBody DepartmentDto departmentDto,@PathVariable Long departmentId){
        return  ResponseEntity.ok(departmentService.updateDepartmentById(departmentId,departmentDto));
    }

    @DeleteMapping(path="/{departmentId}")
    public void deleteEmployeeById(Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
    }

    @PatchMapping(path="{departmentId}")
    public ResponseEntity<DepartmentDto> updatePartialDepartmentById(@RequestBody Map<String, Object> updates, @PathVariable Long departmentId){

        DepartmentDto departmentDto =departmentService.updatePartialDepartmentById(departmentId,updates);

        return ResponseEntity.ok(departmentDto);
    }

}
