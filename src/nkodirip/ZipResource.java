package nkodirip;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @author  Nithin
 * This class holds the data of zipcode and their respective city, state.  
 *
 */
@Path("/zips")
public class ZipResource {

	Map<String, String> zipsc;
	public ZipResource() {
		super();
		zipsc = new HashMap<String, String>();
		zipsc.put("22312", "VA,Alexandria,22312");
		zipsc.put("22030", "VA,Fairfax,22030");
		zipsc.put("22301", "MD,Tysons Corner,22301");
		zipsc.put("20148", "VA,Ashburn,20148");

	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{zip}")
	public String LookUp(@PathParam("zip") String zip) {
		String match = zipsc.get(zip);
		String[] value = match.split(",");
		String cS = new String(value[1] + "," + value[0]);
		return cS;
	}

}
