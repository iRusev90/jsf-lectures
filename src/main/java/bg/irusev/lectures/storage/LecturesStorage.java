package bg.irusev.lectures.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import bg.irusev.lectures.model.Lecture;

public class LecturesStorage {
	private static LecturesStorage lecturesStorage = null;
	
	private List<Lecture> lectures;
	
	private LecturesStorage() {
		lectures = new ArrayList<>();
		insertDefaultLectures();
	}

	public static synchronized LecturesStorage getInstance() {
		if (lecturesStorage == null) {
			lecturesStorage = new LecturesStorage();
		}
		
		return lecturesStorage;
	}
	
	public List<Lecture> getLectures() {
		return lectures;
	}
	
	public List<Lecture> getFiltered(String title, String author, LocalDate localDate) {
		List<Lecture> filtered = lectures.stream().filter(lecture -> {
			boolean isTitleMatch = true;
			boolean isAuthorMatch = true;
			boolean isLocalDateMatch = true;
			
			if (title != null && !title.trim().equals("")) {
				isTitleMatch = title.equals(lecture.getTitle());
			}
			
			if (author != null && !author.trim().equals(""))  {
				isAuthorMatch = author.equals(lecture.getAuthor());
			}
			
			if (localDate != null) {
				isLocalDateMatch = localDate.equals(lecture.getPublishDate());
			}
			
			return isAuthorMatch && isTitleMatch && isLocalDateMatch;
		}).collect(Collectors.toList());
		
		return filtered;
	}
	
	private void insertDefaultLectures() {
		Lecture cakeLecture = new Lecture();
		cakeLecture.setAuthor("admin");
		cakeLecture.setPublishDate(LocalDate.of(2017, 5, 3));
		cakeLecture.setPictureUrl("https://www.bbcgoodfood.com/sites/default/files/recipe-collections/collection-image/2013/05/carrot-cake.jpg");
		cakeLecture.setTags(Arrays.asList("cake", "cooking", "pastry"));
		cakeLecture.setTitle("cake");
		cakeLecture.setText("Cake is a form of sweet food that is usually baked. In their oldest forms, cakes were modifications of breads, but cakes now cover a wide range of preparations that can be simple or elaborate, and that share features with other desserts such as pastries, meringues, custards, and pies. The most commonly used cake ingredients include flour, sugar, eggs, butter or oil or margarine, a liquid, and leavening agents, such as baking soda or baking powder. Common additional ingredients and flavourings include dried, candied, or fresh fruit, nuts, cocoa, and extracts such as vanilla, with numerous substitutions for the primary ingredients. Cakes can also be filled with fruit preserves, nuts or dessert sauces (like pastry cream), iced with buttercream or other icings, and decorated with marzipan, piped borders, or candied fruit.[1]");
		
		Lecture clorineLecture = new Lecture();
		clorineLecture.setAuthor("ivan");
		clorineLecture.setPublishDate(LocalDate.of(2018, 6, 15));
		clorineLecture.setPictureUrl("https://img.purch.com/h/1000/aHR0cDovL3d3dy5saXZlc2NpZW5jZS5jb20vaW1hZ2VzL2kvMDAwLzAzOS8zNjMvb3JpZ2luYWwvc2h1dHRlcnN0b2NrXzYxNjQwNDEwLmpwZw==");
		clorineLecture.setTitle("clorine");
		clorineLecture.setText("Chlorine is a chemical element with the symbol Cl and atomic number 17. The second-lightest of the halogens, it appears between fluorine and bromine in the periodic table and its properties are mostly intermediate between them. Chlorine is a yellow-green gas at room temperature. It is an extremely reactive element and a strong oxidising agent: among the elements, it has the highest electron affinity and the third-highest electronegativity on the Pauling scale, behind only oxygen and fluorine.");
		
		Lecture starcraftLecture = new Lecture();
		starcraftLecture.setAuthor("ivan");
		starcraftLecture.setPictureUrl("https://upload.wikimedia.org/wikipedia/ru/thumb/f/fe/StarcraftBW.jpg/270px-StarcraftBW.jpg");
		starcraftLecture.setTitle("starcraft");
		starcraftLecture.setText("StarCraft is a military science fiction media franchise, created by Chris Metzen and James Phinney and owned by Blizzard Entertainment. The series, set in the beginning of the 26th century, centers on a galactic struggle for dominance among four species—the adaptable and mobile Terrans, the ever-evolving insectoid Zerg, the powerfully enigmatic Protoss, and the \"god-like\" Xel'Naga creator race—in a distant part of the Milky Way galaxy known as the Koprulu Sector. The series debuted with the video game StarCraft in 1998. It has grown to include a number of other games as well as eight novelizations, two Amazing Stories articles, a board game, and other licensed merchandise such as collectible statues and toys.");
		starcraftLecture.setPublishDate(LocalDate.now());
		
		this.lectures.add(cakeLecture);
		this.lectures.add(clorineLecture);
		this.lectures.add(starcraftLecture);
	}

}
