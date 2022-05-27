package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {
        // create Session Factory
        // create session
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetails.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()) {

            // start a transaction
            session.beginTransaction();

            // get instructor detail object
            int id = 4;
            InstructorDetails instructorDetails = session.get(InstructorDetails.class, id);

            // print the instructor detail
            System.out.println("The selected instructor details :" + instructorDetails);

            // print the associate instructor
            System.out.println("The associated instructor :" + instructorDetails.getInstructor());

            // now lets delete the instructor detail
            System.out.println("Deleting the instructor detail :" + instructorDetails);
            session.delete(instructorDetails);

            // remove the associated object reference
            // break bi-directional link
            instructorDetails.getInstructor().setInstructorDetails(null);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
