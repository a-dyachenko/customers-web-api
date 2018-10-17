package customers_web_api.data;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class CustomerCommentJSON {

	private int id;
	private String commentText;
	private Date createdDate;

	
	public CustomerCommentJSON(int id, String commentText, Date createdDate) {
		this.setId(id);
		this.setCommentText(commentText);
		this.setCreatedDate(createdDate);
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
