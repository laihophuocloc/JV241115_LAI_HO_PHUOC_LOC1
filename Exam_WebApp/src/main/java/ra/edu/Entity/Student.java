package ra.edu.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @Column(name = "student_id", length = 5)
    private String studentId;
    @Column(name = "student_name", length = 200, nullable = false)
    private String studentName;
    @Column(columnDefinition = "text", nullable = false, unique = true)
    private String email;
    @Column(name = "phone", length = 15, nullable = false, unique = true)
    private String phone;
    @Column(name = "sex", nullable = false)
    private Boolean sex;
    @Column(name = "bod", nullable = false)
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "status", nullable = false)
    private Boolean status;

}
