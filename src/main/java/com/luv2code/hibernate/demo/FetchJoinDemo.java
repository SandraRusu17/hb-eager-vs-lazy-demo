package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {
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

            // Option 2: Hibernate query with HQL

            // get the instructor from the db
            int id = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId ",
                            Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId",id);

            // execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("luv2code : Instructor: " + tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            //get the course for the instructor
            System.out.println("love2code : Courses :" + tempInstructor.getCourses());

            System.out.println("love2code : Done !");

        }
    }
}
