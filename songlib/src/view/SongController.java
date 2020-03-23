
//Kanhang Li and Nobel Ibe

package view;


import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Song;

public class SongController {
	@FXML ListView<Song> songList;                
	int size=0;
	private ObservableList<Song> obsList;         
	@FXML Button add;
	@FXML Button delete;
	@FXML Button edit;

	@FXML TextField name;
	@FXML TextField artist;
	@FXML TextField album;
	@FXML TextField year;


	public void start(Stage mainStage) {                
		// create an ObservableList 
		// from an ArrayList  
		obsList = FXCollections.observableArrayList(                               
				); 
		add.setOnAction((e)->{
			click(e);
		});
		delete.setOnAction((e)->{
			click(e);
		});
	
		edit.setOnAction((e)->{
			click(e);
		});
	
		
		read();
		songList.setItems(obsList); 

		// select the first item
	      songList.getSelectionModel().select(0);
	   
	      // set listener for the items
	      songList
	        .getSelectionModel()
	        .selectedIndexProperty()
	        .addListener(
	           (obs, oldVal, newVal) -> 
	           display());

	}
	

	public void display() {
		 int index = songList.getSelectionModel().getSelectedIndex();
		 
		if (size ==0||index==-1) {
			name.setText("");
			artist.setText("");
			year.setText("");
			album.setText("");
	
		}
		else {
			name.setText(obsList.get(index).getName());
			artist.setText(obsList.get(index).getArtist());
		year.setText(obsList.get(index).getYear());
			album.setText(obsList.get(index).getAlbum());
	
		}
		
	}
	public void click(ActionEvent e) {
	
		Button b= (Button)e.getSource();
		if (b==add) {
			add();
		}else if(b==delete) {
			delete();
		}
		else if(b==edit ) {
			edit();
		}
		
	
		Collections.sort(obsList, Song.artistSort);
		Collections.sort(obsList, Song.nameSort);
		
	
		songList.setItems(obsList);
		write();}
	public boolean check(ObservableList<Song>obsList,Song newSong) {
		for (int i=0;i<obsList.size();i++) {
			if (obsList.get(i).getName().equals(newSong.getName()) &&obsList.get(i).getArtist().equals(newSong.getArtist())) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public void write() {
		String path="Songlibary.txt";
		File file=new File(path);
		if (file.exists())
				{
			file.delete();
				}
		try {
			file.createNewFile();
		
		FileWriter write= new FileWriter("Songlibary.txt",false);
		PrintWriter printwrite=new PrintWriter(write);
		for(int i=0; i<obsList.size();i++) {
			printwrite.print(obsList.get(i));
			printwrite.print("\n");
		
		}	printwrite.close();} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void read() {
		File file= new File("Songlibary.txt");
		try {
			Scanner s= new Scanner(file);
		while (s.hasNext()) {
		
			String name=(s.next()).split(":")[1];
			String artist=(s.next()).split(":")[1];
			String album=(s.next()).split(":")[1];
			String year=(s.next()).split(":")[1];
		
		Song newsong=new Song(name,artist,album,year);
		
		obsList.add(newsong);
		size++;
		}} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("no data in the library");
		}
	}
	public void add() {
		Alert error = new Alert(AlertType.ERROR);
		String newName=name.getText();
		String newArtist=artist.getText();
		String newYear=year.getText();
		String newAlbum=album.getText();
		Song newSong =new Song(newName,newArtist,newAlbum,newYear);
		if(newName.equals("")||newArtist.equals("")) {
			error.setTitle("ERROR");
			error.setContentText("Name or artist cannot be empty");
			error.showAndWait();
			return;
		}
		else if (check(obsList,newSong)) {
			
			error.setTitle("ERROR");
			error.setContentText("Existing song with same name and artist");
			error.showAndWait();
			return;
		}
		else {
		obsList.add(newSong);
		songList.getSelectionModel().select(size);
		songList.scrollTo(size);
		size++;
		
	}}
	public void delete() {
		Alert error = new Alert(AlertType.ERROR);
		 int index = songList.getSelectionModel().getSelectedIndex();
		 if (index ==-1) {
				error.setTitle("ERROR");
				error.setContentText("Please select one song");
				error.showAndWait();
		 }else {
		 obsList.remove(index);
		 if((index==size-1)&&index-1>=0)
			songList.getSelectionModel().select(index-1);
		 else if (index<size-1)
			 songList.getSelectionModel().select(index);
		 else {name.setText("");
		 		artist.setText("");
		 		year.setText("");
		 		album.setText("");}
		 size--;}
	}
	public void edit() {
		String newName=name.getText();
		String newArtist=artist.getText();
		String newYear=year.getText();
		Alert error = new Alert(AlertType.ERROR);
		String newAlbum=album.getText();
		Song newSong =new Song(newName,newArtist,newAlbum,newYear);
		if(newName.equals("")||newArtist.equals("")) {
			error.setTitle("ERROR");
			error.setContentText("Name or artist cannot be empty");
			error.showAndWait();
			return;
		}
		else if (check(obsList,newSong)) {
			
			error.setTitle("ERROR");
			error.setContentText("Existing song with same name and artist");
			error.showAndWait();
			return;
		}
			else { int index = songList.getSelectionModel().getSelectedIndex();
		 obsList.get(index).setAlbum(newAlbum);
		 obsList.get(index).setName(newName);
		 obsList.get(index).setYear(newYear);
	
		 obsList.get(index).setArtist(newArtist);
	}}

}
