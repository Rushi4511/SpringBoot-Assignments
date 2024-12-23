package Department_MVC_REST.Assignment2.services;

import Department_MVC_REST.Assignment2.dto.DepartmentDto;
import Department_MVC_REST.Assignment2.entities.DepartmentEntity;
import Department_MVC_REST.Assignment2.exceptions.ResourceNotFoundException;
import Department_MVC_REST.Assignment2.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private  DepartmentRepository departmentRepository;

    @Autowired
    private  ModelMapper modelMapper;

    public Optional<DepartmentDto> getDepartmentById(Long id){

        Optional<DepartmentEntity> departmentEntity =departmentRepository.findById(id);
        return departmentEntity.map(departmentEntity1 -> modelMapper.map(departmentEntity1,DepartmentDto.class));
    }

    public List<DepartmentDto> getAllDepartments(){
        List<DepartmentEntity> departmentEntities =departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(employeeEntity ->modelMapper.map(departmentEntities,DepartmentDto.class))
                .collect(Collectors.toList());
    }

    public DepartmentDto createNewDepartment(DepartmentDto inputDepartment){

        DepartmentEntity toSaveEntity = modelMapper.map(inputDepartment,DepartmentEntity.class);

        DepartmentEntity savedDepartmentEntity =departmentRepository.save(toSaveEntity);
        return modelMapper.map(savedDepartmentEntity,DepartmentDto.class);
    }


    public void isExistsByDepartmentId(Long departmentId){
        boolean isExists = departmentRepository.existsById(departmentId);
        if(!isExists) throw new ResourceNotFoundException("Department Not Found with ID"+departmentId);
    }

    public DepartmentDto updateDepartmentById(Long departmentId,DepartmentDto departmentDto){

        isExistsByDepartmentId(departmentId);
        DepartmentEntity departmentEntity =modelMapper.map(departmentDto,DepartmentEntity.class);
        //departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartmmentEntity =departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartmmentEntity,DepartmentDto.class);
    }

    public boolean deleteDepartmentById(Long departmentId){
        isExistsByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);

        return true;
    }

    public DepartmentDto updatePartialDepartmentById(Long departmentId, Map<String ,Object> updates){
        isExistsByDepartmentId(departmentId);

        DepartmentEntity departmentEntity =departmentRepository.findById(departmentId).get();

        updates.forEach((field,value)-> {

            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,departmentId,DepartmentDto.class);

        });
        return modelMapper.map(departmentRepository.save(departmentEntity),DepartmentDto.class);
    }

}
