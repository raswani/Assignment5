package DatabaseAsn5Rest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.FetchType;


@Entity
public class tower {

	@Id
	private int id;
	private String name;
	private double height;
	private int sides;
	private int siteId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getSides() {
		return sides;
	}
	public void setSides(int sides) {
		this.sides = sides;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public tower(int id, String name, double height, int sides, int siteId) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.sides = sides;
		this.siteId = siteId;
	}
	public tower() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
