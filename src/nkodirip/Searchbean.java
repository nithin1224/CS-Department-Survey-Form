package nkodirip;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

/**
 * @author    Nithin
 * This class searches for the survey records based on the given first name , last name, city or state.
 *
 */
@ManagedBean
@SessionScoped
public class Searchbean implements Serializable {

	private static final long serialVersionUID = 1L;

	String firstname;
	String lastname;
	String city;
	String state;
	ArrayList<Student> stuList;

	/**
	 * @return
	 */
	public ArrayList<Student> getStudentList() {
		return stuList;
	}

	/**
	 * @param studentList
	 */
	public void setStudentList(ArrayList<Student> stuList) {
		this.stuList = stuList;
	}

	/**
	 * @return
	 */

	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param fname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param studentid
	 */
	public void deleteRows(int id) {

		EntityManager em = SaveData.getEntityManager();

		Map<String, String> strmap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String action = strmap.get("student");
		Student student = em.find(Student.class, id);
		System.out.println("begin");
		em.getTransaction().begin();
		em.remove(student);
		em.getTransaction().commit();
		System.out.println("studentid" + id);

	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String searchStudentSurvey() {

		EntityManager em2 = SaveData.getEntityManager();

		CriteriaBuilder builder = em2.getCriteriaBuilder();
		CriteriaQuery<Student> criteria = builder.createQuery(Student.class);

		Root<Student> stu = criteria.from(Student.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!(firstname.trim().equals(""))) {
			System.out.println("firstnames");
			predicates.add(builder.equal(stu.get("firstname"), getFirstname()));
		}

		if (!(lastname.trim().equals(""))) {
			System.out.println("lastnames");
			predicates.add(builder.equal(stu.get("lastname"), getLastname()));
		}

		if (!(city.trim().equals(""))) {
			System.out.println("city");
			predicates.add(builder.equal(stu.get("city"), getCity()));
		}
		if (!(state.trim().equals(""))) {
			System.out.println("state");
			predicates.add(builder.equal(stu.get("state"), getState()));
		}

		criteria.select(stu).where(predicates.toArray(new Predicate[] {}));
		stuList = (ArrayList<Student>) em2.createQuery(criteria).getResultList();
		for (Student stu2 : stuList) {
			System.out.println(stu2.getFirstname());
		}

		return "ResultLists";
	}

}
