package bg.irusev.lectures;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import bg.irusev.lectures.model.Lecture;
import bg.irusev.lectures.storage.LecturesStorage;

@ManagedBean
@ViewScoped
public class BrowseView implements Serializable {
	private static final long serialVersionUID = -7704479475708943494L;
	
	private List<Lecture> lectures;
	private LecturesStorage lecturesStorage;
	
	private List<String> allAuthors;
	private List<String> allTitles;
	
	private String author;
	private String title;
	private Date publishDate;

	public BrowseView() {
		lecturesStorage = LecturesStorage.getInstance();
		lectures = lecturesStorage.getLectures();
		
		allAuthors = lecturesStorage.getLectures().stream().map(lecutre -> {
			return lecutre.getAuthor();
		}).collect(Collectors.toList());
		
		allTitles = lecturesStorage.getLectures().stream().map(lectures -> {
			return lectures.getTitle();
		}).collect(Collectors.toList());
	}
	
	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}
	
	public void applyFilters() {
		this.lectures = lecturesStorage.getFiltered(title, author, convertToLocalDateViaInstant(publishDate));
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public List<String> getTitlesAutocomplete(String query) {
		List<String> suggestions = new ArrayList<String>(allTitles.stream().filter(title -> {
			return title.startsWith(query);
		}).collect(Collectors.toSet()));
		
		return suggestions;
	}
	
	public List<String> getAuthorsAutocomplete(String query) {
		List<String> suggestions = new ArrayList<String>(this.allAuthors.stream().filter(author -> {
			return author.startsWith(query);
		}).collect(Collectors.toSet()));
		
		return suggestions;
	}
	
	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		if (dateToConvert == null) {
			return null;
		}
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}

}
