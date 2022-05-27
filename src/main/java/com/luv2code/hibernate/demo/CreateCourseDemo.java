package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseDemo {
    public static void main(String[] args) {
        // create Session Factory
        // create session
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetails.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()) {

            // start a transaction
            session.beginTransaction();

            // get the instructor from the db
            int id = 1;
            Instructor tempInstructor = session.get(Instructor.class, id);

            // create some courses
            Course course1 = new Course("mathematics");
            Course course2 = new Course("informatics");

            // add courses to the instructor
            tempInstructor.add(course1);
            tempInstructor.add(course2);

            // save the courses
            session.save(course1);
            session.save(course2);


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        }
    }
}
