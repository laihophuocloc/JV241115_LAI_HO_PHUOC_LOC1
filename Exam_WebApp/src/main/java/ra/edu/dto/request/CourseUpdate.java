package ra.edu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import ra.edu.validator.CourseUnique;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseUpdate {
    @NotBlank(message = "Không được để trống")
    @CourseUnique
    private String courseName;
    @NotBlank(message = "Không được để trống")
    private String description;
}
