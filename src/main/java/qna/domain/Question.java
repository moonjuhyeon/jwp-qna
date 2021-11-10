package qna.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Question extends BaseTimeEntity {

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Lob
	@Column(name = "contents")
	private String contents;

	@Column(name = "writer_id")
	private Long writerId;

	@Column(name = "deleted", nullable = false)
	private boolean deleted = false;

	protected Question() {
	}

	public Question(String title, String contents) {
		this(null, title, contents);
	}

	public Question(Long id, String title, String contents) {
		this.setId(id);
		this.title = title;
		this.contents = contents;
	}

	public Question writeBy(User writer) {
		this.writerId = writer.getId();
		return this;
	}

	public boolean isOwner(User writer) {
		return this.writerId.equals(writer.getId());
	}

	public void addAnswer(Answer answer) {
		answer.toQuestion(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Long getWriterId() {
		return writerId;
	}

	public void setWriterId(Long writerId) {
		this.writerId = writerId;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Question{" +
			"id=" + getId() +
			", title='" + title + '\'' +
			", contents='" + contents + '\'' +
			", writerId=" + writerId +
			", deleted=" + deleted +
			'}';
	}
}
