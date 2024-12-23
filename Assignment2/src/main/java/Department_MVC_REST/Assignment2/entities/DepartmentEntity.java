package Department_MVC_REST.Assignment2.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonProperty("isActive")
    private Boolean isActive;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAt;

//    public DepartmentEntity(Long id, String title, Boolean isActive, LocalDate createdAt) {
//        this.id = id;
//        this.title = title;
//        this.isActive = isActive;
//        this.createdAt = createdAt;
//    }
//
//    public DepartmentEntity() {
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setIsActive(Boolean active) {
//        isActive = active;
//    }
//
//    public LocalDate getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDate createdAt) {
//        this.createdAt = createdAt;
//    }
}
