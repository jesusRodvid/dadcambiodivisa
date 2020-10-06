package dad.cambio.divisa;

import javafx.application.Application; 
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField; 
import javafx.stage.Stage; 
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Cajas extends Application {   
  
	private Divisa euro = new Divisa ("Euro",1.0);
	private Divisa libra = new Divisa ("Libra",0.9);
	private Divisa dolar = new Divisa ("Dolar", 1.2);
	private Divisa yen = new Divisa ("Yen", 133.6);
	private Divisa [] divisas = {euro, libra, dolar, yen};
	private TextField textfield1;
	private TextField textfield2;
	private ComboBox<Divisa> combobox1;
	private ComboBox<Divisa> combobox2;
	private Button cambio;
     
	   @Override 
	   public void start(Stage stage) throws Exception {       
		    
		   //textfield
		   	textfield1 = new TextField("0");
	        textfield1.setPrefColumnCount(4);
	        textfield2 = new TextField("0");
	        textfield2.setPrefColumnCount(4);
	        textfield2.setEditable(false);
	       
	        //combobox
	        combobox1 = new ComboBox<>();
	        combobox1.getItems().addAll(divisas);
	        combobox1.getSelectionModel().select(euro);
	        combobox1.getSelectionModel().selectFirst();

	        combobox2 = new ComboBox<>();
	        combobox2.getItems().addAll(divisas);
	        combobox2.getSelectionModel().select(libra);
	        //boton
	        cambio = new Button("Cambiar"); 
	        cambio.setOnAction(e-> oncambioAction(e));
	        //Hbox
	        HBox hbox1 = new HBox();
	        hbox1.setAlignment(Pos.BASELINE_CENTER);
	        hbox1.setSpacing(5);
	        hbox1.getChildren().addAll(textfield1, combobox1);
	        

	        HBox hbox2 = new HBox();
	        hbox2.setAlignment(Pos.BASELINE_CENTER);
	        hbox2.setSpacing(5);
	        hbox2.getChildren().addAll(textfield2, combobox2);
	        
	        
	       //Vbox 
	        VBox root = new VBox();
	        root.setSpacing(5);
	        root.setAlignment(Pos.CENTER);
	        root.getChildren().addAll(hbox1, hbox2, cambio);
	        
	        Scene scene = new Scene(root, 320, 200);
	        
	        stage.setTitle("CambioDivisa");
	        stage.setScene(scene);
	        stage.show();
	  
	   } 
   private void oncambioAction(ActionEvent e) {
		Double cantidadOrigen = Double.parseDouble(textfield1.getText());
		Divisa divisaOrigen = combobox1.getSelectionModel().getSelectedItem();
		Divisa divisaDestino = combobox2.getSelectionModel().getSelectedItem();
		
		Double enEuros = divisaOrigen.toEuro(cantidadOrigen);
		Double cantidadDestino = divisaDestino.fromEuro(enEuros);
		
		textfield2.setText("" + cantidadDestino);
	}
   public static void main(String args[]){ 
	      launch(args); 
	   } 
}