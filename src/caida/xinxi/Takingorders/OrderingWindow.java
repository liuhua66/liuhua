package caida.xinxi.Takingorders;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class OrderingWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel pNorth,pCenter,pSouth;
	private JButton meatButton,vegetarianButton,stapleFoodButton,soupAndPorridgeButton,dessertButton,specialtyButton,showButton,stopOrderingButton;
	private JTextField idTextField,dateTextField;
	private MealMenu mealMenus;
	private File file;
	private HashSet<String> idSet;
	
	public OrderingWindow(){
		setTitle("顾客点菜界面");
		idSet=new HashSet<String>();
		
		
		pNorth=new JPanel();
		idTextField=new JTextField(10);
		idTextField.addActionListener(this);
		dateTextField=new JTextField(20);
		dateTextField.setEditable(false);
		pNorth.add(new JLabel("请输入您的桌号："));
		pNorth.add(idTextField);
		pNorth.add(new JLabel("点餐日期和时间："));
		pNorth.add(dateTextField);
		
		pCenter=new JPanel();
		pCenter.setBorder(new TitledBorder(new LineBorder(Color.BLUE),"分类点菜",TitledBorder.LEFT,TitledBorder.TOP));
		meatButton=new JButton("荤菜");
		meatButton.addActionListener(this);
		meatButton.setEnabled(false);
		vegetarianButton=new JButton("素菜");
		vegetarianButton.addActionListener(this);
		vegetarianButton.setEnabled(false);
		stapleFoodButton=new JButton("主食");
		stapleFoodButton.addActionListener(this);
		stapleFoodButton.setEnabled(false);
		soupAndPorridgeButton=new JButton("汤粥");
		soupAndPorridgeButton.addActionListener(this);
		soupAndPorridgeButton.setEnabled(false);
		dessertButton=new JButton("点心");
		dessertButton.addActionListener(this);
		dessertButton.setEnabled(false);
		specialtyButton=new JButton("特色");
		specialtyButton.addActionListener(this);
		specialtyButton.setEnabled(false);
		Box baseBox=Box.createHorizontalBox();
		Box box1=Box.createVerticalBox();
		box1.add(meatButton);
		box1.add(Box.createVerticalStrut(20));
		box1.add(stapleFoodButton);
		Box box2=Box.createVerticalBox();
		box2.add(vegetarianButton);
		box2.add(Box.createVerticalStrut(20));
		box2.add(soupAndPorridgeButton);
		Box box3=Box.createVerticalBox();
		box3.add(dessertButton);
		box3.add(Box.createVerticalStrut(20));
		box3.add(specialtyButton);
		baseBox.add(box1);
		baseBox.add(Box.createHorizontalStrut(100));
		baseBox.add(box2);
		baseBox.add(Box.createHorizontalStrut(100));
		baseBox.add(box3);
		pCenter.add(baseBox);

		pSouth=new JPanel();
		this.showButton=new JButton("显示点菜明细、结账");
		showButton.addActionListener(this);
		showButton.setEnabled(false);
		this.stopOrderingButton=new JButton("结束本次点菜");
		stopOrderingButton.addActionListener(this);
		pSouth.add(showButton);
		pSouth.add(stopOrderingButton);
		
		add(pNorth,"North");
		add(pCenter,"Center");
		add(pSouth,"South");
		
		setBounds(100,100,600,230);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.idTextField){
			if(idTextField!=null){
				if(!idSet.add(idTextField.getText())){
					JOptionPane.showMessageDialog(this, "此桌已有客人，请重新选桌！");
				}
				else {
					idTextField.setEditable(false);
					this.meatButton.setEnabled(true);
					this.vegetarianButton.setEnabled(true);
					this.stapleFoodButton.setEnabled(true);
					this.soupAndPorridgeButton.setEnabled(true);
					this.dessertButton.setEnabled(true);
					this.specialtyButton.setEnabled(true);
					this.showButton.setEnabled(true);
					Date nowTime=new Date();
					SimpleDateFormat matter=new SimpleDateFormat("点菜时间：yyyy-MM-dd HH:mm:ss");
					String date=matter.format(nowTime);
					this.dateTextField.setText(date);	
					String filename=idTextField.getText()+"号桌点菜清单.txt";
					file=new File(filename);
				}
			}
		}
		else if(e.getSource()==this.showButton){
			ShowOrderingRecord showOrdering=new ShowOrderingRecord(file);
			showOrdering.setVisible(true);
			showOrdering.showRecord();
		}
		else if(e.getSource()==this.stopOrderingButton){
			this.idTextField.setText(null);
			this.idTextField.setEditable(true);
			this.dateTextField.setText(null);
			this.meatButton.setEnabled(false);
			this.vegetarianButton.setEnabled(false);
			this.stapleFoodButton.setEnabled(false);
			this.soupAndPorridgeButton.setEnabled(false);
			this.dessertButton.setEnabled(true);
			this.specialtyButton.setEnabled(true);
			this.showButton.setEnabled(false);
			
		}
		else{
			String menusName=null;
			if(e.getSource()==this.meatButton){
				mealMenus=new MeatMenu();
				menusName="荤菜　点菜";
				this.meatButton.setEnabled(false);
			}
			else if(e.getSource()==this.vegetarianButton){
				mealMenus=new VegetarianMenu();
				menusName="素菜　点菜";
				this.vegetarianButton.setEnabled(false);
			}
			else if(e.getSource()==this.stapleFoodButton){
				mealMenus=new StapleFoodMenu();
				menusName="主食　点菜";
				this.stapleFoodButton.setEnabled(false);
			}
			else if(e.getSource()==this.soupAndPorridgeButton){
				mealMenus=new SoupAndPorridgeMenu();
				menusName="汤粥　点菜";
				this.soupAndPorridgeButton.setEnabled(false);
			}
			else if(e.getSource()==this.dessertButton){
				
				mealMenus=new dessertmenu();
				menusName="点心　点菜";
				this.dessertButton.setEnabled(false);
			}
			else if(e.getSource()==this.specialtyButton){
				mealMenus=new specialtymenu();
				menusName="特色　点菜";
				this.specialtyButton.setEnabled(false);
			}
			new OrderDishes(mealMenus,menusName,file).setVisible(true);
			
		}
	}
	
	public static void main(String[] args) {
		new OrderingWindow();
	}
}