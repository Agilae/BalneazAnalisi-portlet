package facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class combofacade implements Serializable {

	String id;
	String description;
	String otherId;
	
	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	

}