package nkodirip;


import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
//import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/***
 * 
 * @author Nithin
 * This class saves the survey data to the database.
 */
public class SaveData {

	@PersistenceContext
	public static final EntityManagerFactory emfact = Persistence.createEntityManagerFactory("student");
	EntityManager em;

	/**
	 * @return
	 */
	public static EntityManager getEntityManager() {
		EntityManager entityManager = emfact.createEntityManager();
		return entityManager;
	}

	/**
	 * 
	 */
	public SaveData() {
		super();
	}

	/**
	 * @param stu
	 */
	public static void studentsData(Student stu) {

		EntityManager etm = getEntityManager();
		etm.getTransaction().begin();
		etm.persist(stu);
		etm.getTransaction().commit();
		etm.close();

	}

}
