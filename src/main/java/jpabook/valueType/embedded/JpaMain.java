package jpabook.valueType.embedded;


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

            /* 임베디드 */
            /*Member member = new Member();
            member.setUsername("지연");
            member.setHomeAddress(new Address("c","s","z1000"));
            member.setWorkperiod(new Period());
            em.persist(member);*/

            /* 값 타입 공유 */
            Address address = new Address("c","s","z1000");

            Member member = new Member();
            member.setUsername("지연");
            member.setHomeAddress(address);
            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            /*불변객체를 사용하는 방법*/
            Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());
            member.setHomeAddress(newAddress);


            Member member2 = new Member();
            member2.setUsername("지연");
            //member2.setHomeAddress(address); /값 타입의 실제 인스턴스인 값을 공유하는 것은 위험
            member2.setHomeAddress(copyAddress); //인스턴스를 복사해서 사용
            em.persist(member2);

            //member.getHomeAddress().setCity("newC"); //부작용 발생가능성!

            et.commit();

        } catch (Exception e){
            et.rollback();
            e.printStackTrace();
        } finally {

            em.close();
            emf.close();
        }


    }

}
