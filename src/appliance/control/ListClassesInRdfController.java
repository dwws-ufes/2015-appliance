package appliance.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appliance.application.ClassesService;
import appliance.domain.Classes;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

@WebServlet(urlPatterns = { "/data/addclasses" })
public class ListClassesInRdfController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ClassesService classesService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/xml");
		List<Classes> classes = classesService.getList();
		Model model = ModelFactory.createDefaultModel();
		String myNS = "http://localhost:8080/Appliance/data/addclasses/";
		String grNS = "http://purl.org/goodrelations/v1#";
		model.setNsPrefix("gr", grNS);
		Resource grOffering = ResourceFactory.createResource(grNS + "Offering");
		for (Classes classe : classes) {
			model.createResource(myNS + classe.getId())
			.addProperty(RDF.type, grOffering)
			.addProperty(RDFS.label, classe.getName())
			.addProperty(RDFS.comment, classe.getDescription())
			.addProperty(RDFS.comment, classe.getTeachername());
		}
		try (PrintWriter out = resp.getWriter()) 
		{
			model.write(out, "RDF/XML");
		}
	}
}