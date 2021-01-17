import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 

public class main_interface extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	databaseManager DBM=new databaseManager();
	
	Container cont=getContentPane();
	JLabel background=new JLabel("");
	JLabel instructions=new JLabel("Login or Register");
	//screen landing
	JButton Login=new JButton("Login");
	JButton Register=new JButton("Register");
	//screen login/register
	JLabel cardLabel=new JLabel("Card number:");
	JLabel passLabel=new JLabel("Password:");
	JTextField cardno=new JTextField();
	JPasswordField pass=new JPasswordField();
	JTextField UserName=new JTextField();
	JButton submit=new JButton("submit");
	
	String screen="Landing";
	
	String drink="";
	String drinkType="";
	
	String regCardNo="";
	
	main_interface() throws IOException{
		cont.setLayout(null);
		setLocationSize();
		init();
		setStyle();
		addComps();
		addAct();
	}
	
	public void setupScreen() {
		if (screen=="Login") {
			Login.setVisible(false);
			Register.setVisible(false);
			cardLabel.setVisible(true);
			passLabel.setVisible(true);
			cardno.setVisible(true);
			pass.setVisible(true);
			submit.setVisible(true);
			instructions.setText("Login");
			instructions.setBounds(180, 2, 90, 50);
			cardLabel.setBounds(5, 60, 150, 20);
			passLabel.setBounds(5,100,140,20);
			pass.setBounds(150,100,200,30);
			cardno.setBounds(150,60,200,30);
			submit.setBounds(140,190,120,40);
		}
		else if (screen=="Register") {
			Login.setVisible(false);
			Register.setVisible(false);
			cardLabel.setVisible(true);
			passLabel.setVisible(true);
			cardno.setVisible(false);
			pass.setVisible(true);
			submit.setVisible(true);
			instructions.setText("Register");
		}
	}
	public void init() throws IOException{
		background.setIcon(new ImageIcon(ImageIO.read(new File("C:/Users/AYAN CHATTERJEE/Desktop/vending machine/bin/wall.jpg"))));
		cardLabel.setVisible(false);
		passLabel.setVisible(false);
		cardno.setVisible(false);
		pass.setVisible(false);
		submit.setVisible(false);
	}
	public void setStyle() {
		instructions.setFont(new Font("Tahoma", Font.BOLD, 20));
		cardLabel.setFont(new Font("Tahoma",Font.BOLD,17));
		passLabel.setFont(new Font("Tahoma",Font.BOLD,17));
	}
	public void setLocationSize(){
		Login.setBounds(50, 150, 100, 40);
		Register.setBounds(250, 150, 100, 40);
		instructions.setBounds(100, 10, 190, 50);
		background.setBounds(0,0,400,300);
		cardLabel.setBounds(600,600,10,10);
		passLabel.setBounds(600,600,10,10);
		cardno.setBounds(600,600,10,10);
		pass.setBounds(600,600,10,10);
		submit.setBounds(600,600,10,10);
		
	}
	public void addComps(){
		cont.add(Login);
		cont.add(Register);
		cont.add(instructions);
		cont.add(cardLabel);
		cont.add(passLabel);
		cont.add(cardno);
		cont.add(pass);
		cont.add(submit);
		cont.add(background);
	}
	public void addAct() {
		Login.addActionListener(this);
		Register.addActionListener(this);
		submit.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Login) {
			screen="Login";
		}
		else if(e.getSource()==Register) {
			screen="Register";
		}
		else if(e.getSource()==submit) {
			if (screen=="Login") {
				JsonObject cardsObj=new Gson().fromJson(DBM.get_json("mongodb+srv://Java:JVA-VM@clusteridk.yw6ax.mongodb.net/ppp?retryWrites=true&w=majority","Java","Vending Machine","DocTitle","Cards"), JsonObject.class);
				String cardNumber=cardno.getText();
				char[] EPasschar=pass.getPassword(); 
				String EPass=new String(EPasschar);
				JsonObject userDetails=new Gson().fromJson(cardsObj.get(cardNumber), JsonObject.class);
				String passwordpass=userDetails.get("pass").toString();
				passwordpass=passwordpass.substring(1, passwordpass.length()-1);
				if(EPass.equals(passwordpass)) {
					System.out.println("good");
					screen="Dash";
				}
			}
			else if(screen=="Register") {
				JsonObject cardsObj=new Gson().fromJson(DBM.get_json("mongodb+srv://Java:JVA-VM@clusteridk.yw6ax.mongodb.net/ppp?retryWrites=true&w=majority","Java","Vending Machine","DocTitle","Cards"), JsonObject.class);
				JsonObject sysObj=new Gson().fromJson(DBM.get_json("mongodb+srv://Java:JVA-VM@clusteridk.yw6ax.mongodb.net/ppp?retryWrites=true&w=majority","Java","Vending Machine","DocTitle","System"), JsonObject.class);
				char[] EPasschar=pass.getPassword(); 
				String EPass=new String(EPasschar);
				String UName=UserName.getText();
				Bson filter = new Document("DocTitle", "Cards");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDateTime now = LocalDateTime.now();
				Document val=new Document("LDA",dtf.format(now).split("/"));
				val.put("balance",100);
				val.put("name",UName);
				val.put("membership","Basic");
				val.put("pass",EPass);
				Bson newValue = new Document(regCardNo,val);
				Bson update = new Document("$set", newValue);
			}
		}
		setupScreen();
	}
}
