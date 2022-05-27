package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateDemo {
    public static void main(String[] args) {
        // create Session Factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetails.class)
                                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // create objects
//            Instructor tempInstructor = new Instructor("Sandra", "Rusu", "sandra@gmail.com");
//
//            InstructorDetails instructorDetails = new InstructorDetails("youtube.com/channel","swimming");

            Instructor tempInstructor = new Instructor("Alexandra", "M", "alexandra@gmail.com");

            InstructorDetails instructorDetails = new InstructorDetails("youtube.com/channelAlexandra","dancing");

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

        } finally {
            factory.close();
        }
    }
}
