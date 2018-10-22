package caida.xinxi.Takingorders;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShowOrderingRecord extends JDialog {

	private static final long serialVersionUID = 1L;
	private File file;
	private JTextArea showArea;
	public ShowOrderingRecord(File file){
		this.file=file;
		showArea=new JTextArea(4,2);
		showArea.setFont(new Font("楷体",Font.BOLD ,20));
		add(new JScrollPane(showArea),BorderLayout.CENTER);
		setBounds(200,200,300,400);
		
	}
	
	
	public void showRecord(){
		showArea.setText(null);
		showArea.append("----菜名-------------价格----------");
		double totalPrice=0;
		try {
			RandomAccessFile in=new RandomAccessFile(file,"r");
			String mealName=null;
			
			while((mealName=in.readUTF())!=null){
				showArea.append("\n"+mealName);
				double mealPrice=in.readDouble();
				showArea.append("             "+mealPrice);
				totalPrice=totalPrice+mealPrice;
				
				
			}
		} catch (Exception e) {
		}
		showArea.append("\n------------------------");
		showArea.append("\n结账：         "+totalPrice);
		
		
	}

}
