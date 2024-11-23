package com.ikedi.Employee_Managment.model;

import com.ikedi.Employee_Managment.enums.Absent;
import com.ikedi.Employee_Managment.enums.Present;
import com.ikedi.Employee_Managment.enums.Reasons;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE employee SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = true)
    private LocalTime clockIn;

    @Column(nullable = true)
    private LocalTime clockOut;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Presence status is required")
    private Present present;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Absence status is required")
    private Absent absent;

    @Enumerated(EnumType.STRING)
    private Reasons reason;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deletedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
