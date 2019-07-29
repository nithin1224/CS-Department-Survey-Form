
package nkodirip;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
//import javax.ws.rs.client.Client;
import java.io.Serializable;
import java.util.*;

/***
 * 
 * @author Nithin
 * This class holds the properties that match fields in the student survey form.
 */
@NamedQueries(value = { @NamedQuery(name = "selectall", query = "SELECT s FROM Student s"),
		@NamedQuery(name = "searchfirstname", query = "SELECT s FROM Student s WHERE s.firstname=:firstnames"),
		@NamedQuery(name = "searchlastname", query = "SELECT s FROM Student s WHERE s.lastname=:lastnames"),
		@NamedQuery(name = "searchcity", query = "SELECT s FROM Student s WHERE s.city=:city"),
		@NamedQuery(name = "searchstate", query = "SELECT s FROM Student s WHERE s.state=:state"),
		@NamedQuery(name = "searchall", query = "SELECT s FROM Student s WHERE s.city=:city AND s.state=:state AND s.firstname=:firstnames AND s.lastname=:lastnames"),
		@NamedQuery(name = "searchfirstnameslastnames", query = "SELECT s FROM Student s WHERE s.firstname=:firstnames AND s.lastname=:lastnames"),
		@NamedQuery(name = "searchfirstnamescity", query = "SELECT s FROM Student s WHERE s.firstname=:firstnames AND s.city=:city"),
		@NamedQuery(name = "searchfirstnamestate", query = "SELECT s FROM Student s WHERE s.state=:state AND s.firstname=:firstname")

})
@Entity
@Table

public class Student implements Serializable {
	static final long serialVersionUID = 1L;
	int sid;
	String firstname;
	String lastname;
	String address;
	String city;
	String state;
	String zip;
	String email;
	String phone;
	List<String> campusOptions;
	String interestedIn;
	String recommendation;
	String raffle;
	String surveyDate;
	String semStartDate;
	String comments;
	double mean;
	double deviation;

	/**
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sid")
	public int getSid() {
		return sid;
	}

	/**
	 * @param sid
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}

	/**
	 * @return
	 */
	@Column
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
	@Column
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
	@Column
	public String getAddress() {
		return address;
	}

	/**
	 * @param saddress
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return
	 */
	@Column
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
	 * @return
	 */
	@Column
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
	@Column
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return
	 */
	@Column
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	@Column
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 */
	public void setPhone(String telephone) {
		this.phone = telephone;
	}

	/**
	 * @return
	 */
	@Column
	public String getSurveyDate() {
		return surveyDate;
	}

	/**
	 * @param sdate
	 */
	public void setSurveyDate(String surveydate) {
		this.surveyDate = surveydate;
	}

	/**
	 * @return
	 */
	@Column
	public String getSemStartDate() {
		return semStartDate;
	}

	/**
	 * @param semdate
	 */
	public void setSemStartDate(String semdate) {
		this.semStartDate = semdate;
	}

	/**
	 * @return
	 */
	@Column
	@ElementCollection(fetch = FetchType.EAGER)
	public List<String> getCampusOptions() {

		return campusOptions;
	}

	/**
	 * @param chekbox
	 */
	public void setCampusOptions(List<String> campusOptions) {
		this.campusOptions = campusOptions;
	}

	/**
	 * @return
	 */
	@Column
	public String getInterestedIn() {
		return interestedIn;
	}

	/**
	 * @param radiob
	 */
	public void setInterestedIn(String interestedIn) {
		this.interestedIn = interestedIn;
	}

	/**
	 * @return
	 */
	@Column
	public String getRecommendation() {
		return recommendation;
	}

	/**
	 * @param dropd
	 */
	public void setRecommendation(String dropd) {
		this.recommendation = dropd;
	}

	/**
	 * @return
	 */
	@Column
	public String getRaffle() {
		return raffle;
	}

	/**
	 * @param raffleT
	 */
	public void setRaffle(String raffle) {
		this.raffle = raffle;
	}

	/**
	 * @return
	 */
	@Column
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return
	 */
	public double getMean() {
		return mean;
	}

	/**
	 * @param mean
	 */
	public void setMean(double mean) {
		this.mean = mean;
	}

	/**
	 * @return
	 */
	public double getDeviation() {
		return deviation;
	}

	/**
	 * @param deviation
	 */
	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}

	/**
	 * method to check whether entered raffle sequence is valid or not
	 * 
	 */
	public void validateRaffle(FacesContext context, UIComponent componenttobeValidated,Object value){
		String stringvalid = (String)value; 
		context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("Invalid raffle sequence, Enter 10 comma separated values between 1 and 100");
		if(!(validateRaffleno(stringvalid)))
			throw new ValidatorException(message);
	}
	/**
	 * method to verify the sequence of raffle
	 * @param validateString
	 * @return true or false
	 */
	public boolean validateRaffleno(String validateString){

		String[] integerArr = validateString.split(",");
		for(int k=0 ;k<integerArr.length; k++){
			if(integerArr[k] == "")
				continue;
			else
				integerArr[k] = integerArr[k].trim();
		}
		try{
			if(integerArr.length > 10){
				return false;
			}else{
			for(int m=0; m<integerArr.length; m++){
				if(Integer.parseInt(integerArr[m]) > 100){
				   return false;
				}
			}
				return true;
			}
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
