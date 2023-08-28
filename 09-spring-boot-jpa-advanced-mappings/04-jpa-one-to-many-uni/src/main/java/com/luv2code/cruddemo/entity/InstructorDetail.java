package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "instructor_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
// toString metotları sürekli birbirini çağırdığı için sonsuz döngü oluyor
// bu da stackoverflow hatasına sebep oluyor
// bu yüzden to stringte instructor'ı çıkartıyoruz
@ToString(exclude = {"instructor"})
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    // Bi-directional ilişki için bu alanı ekledik
    // eğer instructor_detail silindiğinde instructor da silinsin istemiyorsak
    // cascade = CascadeType.ALL kısmını kaldırıyoruz
    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public void setInstructor(Instructor instructor) {
        instructor.setInstructorDetail(this);
    }


}
