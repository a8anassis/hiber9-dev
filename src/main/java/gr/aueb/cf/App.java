package gr.aueb.cf;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.w3c.dom.css.Counter;

public class App {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolPU");
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        Teacher alice = new Teacher("Alice", "W.");
        Course java = new Course("Java");
        alice.addCourse(java);

        em.getTransaction().begin();

//        em.persist(alice);
//        em.persist(java);

        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
