package bg.irusev.lectures;

import java.io.Serializable;
import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import bg.irusev.lectures.model.Lecture;
import bg.irusev.lectures.storage.LecturesStorage;

@ManagedBean
@SessionScoped
public class AddView implements Serializable {
	private static final long serialVersionUID = -1326405824405955224L;
	
	private Lecture lecture;
	private LecturesStorage lecturesStorage;

	
	public AddView() {
		lecture = new Lecture();
		lecturesStorage = LecturesStorage.getInstance();
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}
	
	public void reset() {
		this.lecture = new Lecture();
	}
	
	public void save() {
		lecture.setPublishDate(LocalDate.now());
		lecturesStorage.getLectures().add(lecture);
		reset();
		FacesContext context = FacesContext.getCurrentInstance();
        
		context.addMessage(null, new FacesMessage("Successful",  "Lecture was saved!") );
	}
	
}
