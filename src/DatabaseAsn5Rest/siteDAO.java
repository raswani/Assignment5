package DatabaseAsn5Rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;

import DatabaseAsn5Rest.site;

@Path("/api")
public class siteDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DatabaseAsn5Rest");
	EntityManager em = factory.createEntityManager();
	
	@GET
	@Path("/site/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	
	
	public site findSite(@PathParam("id") int siteId){
		
		site si = null;
		
		try{
			
			Query query = em.createQuery("select si from site si where si.sid = :id ");
			query.setParameter("id", siteId);
			si = (site) query.getSingleResult();
			System.out.println(si.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return si;
		
	}
	
	@GET
	@Path("/site")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<site> findAllSites() {
		
		Query query = em.createQuery("select si from site si");
		return (List<site>) query.getResultList();	
	}
	
	@PUT
	@Path("/site/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<site> updateSite(@PathParam("id") int siteId, site si){
		
		em.getTransaction().begin();
		si.setId(siteId);
		em.merge(si);
		em.getTransaction().commit();
		
		
		Query query = em.createQuery("select si from site si");
		return (List<site>) query.getResultList();
	}
	
	@DELETE
	@Path("/site/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<site> removeSite(@QueryParam("id") int siteId){
		
		
		Query query = em.createQuery("select si from site si where si.sid = :id ");
		query.setParameter("id", siteId);
		site si = (site) query.getSingleResult();
		em.getTransaction().begin();
		em.remove(si);
		em.getTransaction().commit();
		
		
		Query querydel = em.createQuery("select si from site si");
		return (List<site>) querydel.getResultList();
	}
	
	
	//Post method to create site
	@POST
	@Path("/site")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<site> createSite (site si){
		em.getTransaction().begin();
		em.persist(si);
		em.getTransaction().commit();
		
		Query querypost = em.createQuery("select si from site si");
		return (List<site>) querypost.getResultList();
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		
		siteDAO dao=new siteDAO();
		
		
		//find all sites
		List <site> listallsites = dao.findAllSites();
		System.out.println(listallsites.size());
				
		/*
		//Find site by id
		site si = dao.findSite(1);
		
		//create site
		site si1 = new site(0, "Site 4", 1.0, 2.0);
		List <site> listallsites1 = dao.createSite(si1);
		System.out.println(listallsites1.size());
				
		
		//delete size
		List<site> listallsites2 = dao.removeSite(2);
		System.out.println(listallsites2.size());
		
		//update site
		site site1 = dao.findSite(1);
		site1.setName("Site2");
		List<site> listallsites3 = dao.updateSite(1, site1);
		System.out.println(listallsites3.size());
		*/

	}

}