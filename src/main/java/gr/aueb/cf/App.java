package gr.aueb.cf;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.w3c.dom.css.Counter;

import java.util.List;

public class App {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolPU");
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

//        Teacher alice = new Teacher("Alice", "W.");
//        Course java = new Course("Java");
//        alice.addCourse(java);

        em.getTransaction().begin();

        // JPQL

        // Select all teachers
//        String jpql = "SELECT t FROM Teacher t";
//        List<Teacher> teachers = em.createQuery(jpql, Teacher.class).getResultList();
//        teachers.forEach(System.out::println);

        // Select courses of teacher 'Μόσχος'
        // SQL Injection Attack
//        String jpql2 = "SELECT c FROM Course c WHERE c.teacher.lastname = 'Μόσχος'";
//        List<Course> courses = em.createQuery(jpql2, Course.class).getResultList();
//        courses.forEach(System.out::println);

        // SQL Injection-free
//        String jpql3 = "SELECT c FROM Course c WHERE c.teacher.lastname = :lastname";
//        List<Course> courses2 = em.createQuery(jpql3, Course.class)
//                .setParameter("lastname", "Μόσχος")
//                .getResultList();
//        courses2.forEach(System.out::println);


        // Criteria API

        // Get all courses
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);
//        Root<Course> root = criteriaQuery.from(Course.class);
//        criteriaQuery.select(root);
//
//        List<Course> courses3 = em.createQuery(criteriaQuery).getResultList();
//        courses3.forEach(System.out::println);

        // Select all teachers με lastname 'Καπέτης'
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> criteriaQuery = cb.createQuery(Teacher.class);
//        Root<Teacher> teacherRoot = criteriaQuery.from(Teacher.class);
//        criteriaQuery.select(teacherRoot).where(cb.equal(teacherRoot.get("lastname"), "Καπέτης")); // SQL Injection
//        List<Teacher> teachers4 = em.createQuery(criteriaQuery).getResultList();
//        teachers4.forEach(System.out::println);

        // SQL Injection-free
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = cb.createQuery(Teacher.class);
        Root<Teacher> teacherRoot = criteriaQuery.from(Teacher.class);
        ParameterExpression<String> lastname = cb.parameter(String.class);
        criteriaQuery.select(teacherRoot).where(cb.equal(teacherRoot.get("lastname"), lastname)); // SQL Injection
        List<Teacher> teachers4 = em.createQuery(criteriaQuery)
                .setParameter(lastname, "Καπέτης")
                .getResultList();
        teachers4.forEach(System.out::println);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}


//        em.persist(alice);
//        em.persist(java);

//        Teacher alice = em.find(Teacher.class, 1);
//        alice.setLastname("Wonderland");
