package nkodirip;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

/**
 * @author Nithin
 * This class stores the survey data given in the survey form.    
 *
 */
@ManagedBean
public class Survey implements Serializable {

	private static final long serialVersionUID = 1L;
	public String choice = "VeryLikely,Likely,UnLikely";
	public String[] choicearray = choice.split(",");

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	Student student = new Student();

	/**
	 * @return student(Student)
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param stud
	 */
	public void setStudent(Student stud) {
		this.student = stud;
	}

	Searchbean search = new Searchbean();

	/**
	 * @return search(Sbean)
	 */
	public Searchbean getS() {
		return search;
	}

	/**
	 * @param s
	 */
	public void setS(Searchbean s) {
		this.search = s;
	}

	StudentService s1 = new StudentService();

	/**
	 * @return
	 */
	public StudentService getS1() {
		return s1;
	}

	/**
	 * @param s1
	 */
	public void setS1(StudentService s1) {
		this.s1 = s1;
	}

	WinningResult wr = new WinningResult();

	/**
	 * @return
	 */
	public WinningResult getWr() {
		return wr;
	}

	/**
	 * @param wr
	 */
	public void setWr(WinningResult wr) {
		this.wr = wr;
	}

	/**
	 * @param choices
	 * @return
	 */
	public List<String> display(String choices) {
		List<String> abc = new ArrayList<String>();
		for (String i : choicearray) {
			if (i.toUpperCase().startsWith(choices.toUpperCase())) {
				abc.add(i);
			}
		}
		return (abc);
	}

	String ack[] = { "Acknowledgement" };

	/**
	 * @return
	 */
	public String[] getack() {
		return ack;
	}

	/**
	 * @param ack
	 */
	public void setack(String[] ack) {
		this.ack = ack;
	}

	ArrayList<Student> studentsarray = new ArrayList<Student>();

	/**
	 * @return
	 */
	public ArrayList<Student> getStudentarray() {
		return studentsarray;
	}

	/**
	 * @param sarray
	 */
	public void setStudentarray(ArrayList<Student> studentarray) {
		this.studentsarray = studentarray;
	}

	/**
	 * @return
	 */
	public String submitAction() {

		StudentService ss = new StudentService();
		double mean = ss.calculateMean(student);
		double deviation = ss.calculateSDeviation(student);
		wr.setMean(mean);
		student.setMean(mean);
		wr.setDeviation(deviation);
		student.setDeviation(deviation);
		student.setDeviation(wr.getDeviation());
		student.setMean(wr.getMean());
		try {
			SaveData.studentsData(student);
		} catch (Exception e) {
			e.printStackTrace();

		}
		if (wr.getMean() > 90) {
			return "winningResult";
		} else {
			return "SimpleAcknowledgement";
		}
	}

	public void renderStateCity() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/test/webresources/zips/");
		WebTarget resourceWebTarget;
		resourceWebTarget = target.path(new String(this.student.getZip() + ""));
		Invocation.Builder invocationBuilder;
		invocationBuilder = resourceWebTarget.request(MediaType.TEXT_PLAIN);
		System.out.println(resourceWebTarget.getUri());
		Response response = invocationBuilder.get();
		System.out.println(response.getStatus());
		String s = response.readEntity(String.class);
		System.out.println("City" + s.split("-")[0]);
		System.out.println("State" + s.split("-")[1]);
		student.setCity(s.split(",")[0]);
		student.setState(s.split(",")[1]);
	}

	/**
	 * @return
	 */
	public ArrayList<Student> getStudentsarray() {
		EntityManager em = SaveData.getEntityManager();
		return (ArrayList<Student>) em.createNamedQuery("selectall", Student.class).getResultList();
	}

	/**
	 * 
	 */
	public void renderAll() {
		ZipResource zipC = new ZipResource();
		String cityS = "";

		cityS = zipC.LookUp(student.getZip());
		if (cityS == null) {
			return;
		}
		student.setCity(cityS.split(",")[0]);
		student.setState(cityS.split(",")[1]);
	}

	/**
	 * @param context
	 * @param componentToValidate
	 * @param value
	 * @throws ValidatorException
	 */
	public void dateAfter(FacesContext context, UIComponent componentToValidate, Object value)
			throws ValidatorException {
		Date SemStartDate = ((Date) value);
		Object surveyDateValue = componentToValidate.getAttributes().get("dateOfSurvey");
		Date SurveyDate = (Date) ((org.primefaces.component.calendar.Calendar) surveyDateValue).getValue();
		System.out.println(" semDate= " + SemStartDate);
		if (SemStartDate == null)
			return;
		if (SemStartDate.before(SurveyDate)) {
			FacesMessage message = new FacesMessage("Semester Start date cannot be before above Survey date.");
			throw new ValidatorException(message);
		}

	}

}
