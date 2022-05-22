package jpamapping.mapping.highLevel.strategy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapping");

        EntityManager em = emf.createEntityManager();

        //트랜잭션
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {

            Movie movie = new Movie();
            movie.setActor("박지연");
            movie.setDirector("박지선");
            movie.setName("닥스2");
            movie.setPrice(10000);
            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            et.commit();

        } catch (Exception e){
            et.rollback();
        } finally {

            em.close();
            emf.close();
        }


    }

}
