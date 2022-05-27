package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructorDemo {
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

            // create objects
            Instructor tempInstructor = new Instructor("Susan", "R", "susan@gmail.com");

            InstructorDetails instructorDetails = new InstructorDetails("youtube.com/channelSusan", "running");

            // associate objects
            tempInstructor.setInstructorDetails(instructorDetails);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            //Note: this will ALSO save the details object
            // because of CascadeType.ALL
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        }
    }
}
