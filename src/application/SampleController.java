package application;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

class Test{
	public Double cal(Double num1,Double num2,String operator) {
		switch(operator) {
			case "+":
				return (num1+num2);
			case "-":
				return num1-num2;
			case "*":
				return num1*num2;
			case "/":
					if(num2==0.0)
						return 0.0;
				return num1/num2;
			case "%":
				return num1%num2;
			default:
				break;
		}
		return 0.0;		
	}
}


public class SampleController extends Application{
	
	
	@FXML
	private TextField label;
	@FXML
	private Label lab;
	@FXML
	private Double num1=null;
	@FXML
	private String operator="";
	@FXML
	private boolean flag=true;
	@FXML
	private ListView list;
	
	
	public String str="";
	double output;
	String val1;
	
	@FXML
	Label history;
	
	Test t1=new Test();
	
	@FXML
	public void clearAll(ActionEvent e) {
		
		label.setText("");
	}
	
	
	@FXML
	public void numbers(ActionEvent e) {
		
		if(flag) {
			if(output==0.0) {
				label.setText(String.valueOf(""));
				flag=false;
			}
			else {
				
				label.setText(String.valueOf(output));
				
				flag=false;
				label.setText("");
			}
		}
		
		String val=((Button)e.getSource()).getText();
		
		if(val.equals("Del")) {
			if(label.getText().isBlank()) {
				
			}
			else
			{
				str=label.getText().substring(0,label.getLength()-1);
				label.setText(str);
			}
		}
		else {			
			label.setText(label.getText()+val);
		}
	}
	
	@FXML
	public void operators(ActionEvent e) {
		String val=((Button)e.getSource()).getText();
		if(!val.equals("=")) {	
			
			if(!operator.isEmpty())
				return;			
			operator=val;
			
			num1=Double.parseDouble(label.getText());
			label.setText("");
			history.setText(num1+" "+val);
			val1=val;
		}
		
		else {
			
			if(operator.isEmpty())
				return;
			Double num2=Double.parseDouble(label.getText());
			
			output=t1.cal(num1, num2, operator);
			
			list.getItems().add("                    "+num1+" "+operator+" "+num2+"  = "+output);
			
			history.setText(num1+" "+val1+" "+num2);
			label.setText(String.valueOf(output));
			num1=output;
			num2=0.0;
			operator="";
			
			flag=true;
		}				
	}
		
	

	public void closeApp(MouseEvent e) {
		Platform.exit();
		System.exit(0);
	}
	@Override
	public void start(Stage arg0) throws Exception {
		
	}

}
