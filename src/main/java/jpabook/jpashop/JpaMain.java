package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            em.persist(book);

            Member member = new Member();
            member.setName("herry");
            em.persist(member);

            Category category = new Category();
            category.setName("메뉴A");
            em.persist(category);

            Category category2 = new Category();
            category2.setName("메뉴A-1");
            category2.setParent(category);
            em.persist(category2);

            Category category3 = new Category();
            category3.setName("메뉴A-2");
            category3.setParent(category);
            em.persist(category3);

            Category category4 = new Category();
            category4.setName("메뉴B");

//            em.flush();
            Category findCategory = em.find(Category.class, category.getId());
            System.out.println("@@@ " + findCategory.getChild());
            for (Category c : findCategory.getChild()) {
                System.out.println(c.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}
