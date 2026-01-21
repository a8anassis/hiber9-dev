package gr.aueb.cf.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", length = 255, nullable = false, unique = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)    // default is LAZY
    private Set<Course> courses = new HashSet<>();

//    public Teacher() {
//
//    }

    public Teacher(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }

//    protected Set<Course> getCourses() {
//        return courses;
//    }

    public Set<Course> getAllCourses() {
        return Collections.unmodifiableSet(courses);
    }

//    private void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }

    public void addCourse(Course course) {
        if (courses == null) courses = new HashSet<>();
        courses.add(course);
        course.setTeacher(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setTeacher(null);
    }

    @Override
    public String toString() {
        return String.format("%d %s %s", id, firstname, lastname);
    }
}
