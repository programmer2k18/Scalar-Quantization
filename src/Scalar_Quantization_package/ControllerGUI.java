package Scalar_Quantization_package;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ControllerGUI {
	String pathname;
	
	public void GUI()
	 {
	 	JButton ImageButton ;
	 	JButton Option2 ;
	 	JButton btnToBrowse ;
	 	JFrame frame ;
	 	JPanel panl ;
	 	JLabel lab ;

	 	frame = new JFrame(" Compress And Decompress ") ;
	 	
	 	panl = new JPanel() ;
	 	panl.setLayout(new BoxLayout(panl , BoxLayout.LINE_AXIS));
	 	
	 	btnToBrowse = new JButton("Select Image") ;
	 	ImageButton = new JButton(" Compress Image") ;
	 	Option2 = new JButton (" Compress Data") ;
	 	
	 	lab = new JLabel("******************************** Plesea Choose An Action ******************************** ") ;
	 	panl.add(btnToBrowse  ) ;
	 	panl.add(Box.createHorizontalGlue()) ;
	 	panl.add(ImageButton) ;
	 	panl.add(Box.createHorizontalGlue()) ;
	 	panl.add(Option2) ;
	 	
	 	
	 	/************************************** FRAM *********************************************/
	 	frame.add(lab ,BorderLayout.CENTER ) ;
	 	frame.add(panl , BorderLayout.SOUTH) ;
	 	frame.pack();
	 	frame.setSize(500 , 250);
	 	frame.setVisible(true);

	 	/************** BUTTON TO PROWSE THE DATA FILE ******************************/ 
	 	btnToBrowse.addMouseListener(new MouseAdapter(){
	 		public void mouseClicked(MouseEvent arg0) {
	 			// TODO Auto-generated method stub
	 			super.mouseClicked(arg0);
	 			
	 			final JFileChooser gh = new JFileChooser() ;
	 			int val = gh.showOpenDialog(null) ;
	 			if(val == JFileChooser.APPROVE_OPTION )
	 			{
	 				pathname = gh.getSelectedFile().getAbsolutePath(); 	
	 				JOptionPane.showMessageDialog(null, " Done :D ");
	 			}
	 			else
	 			{
	 				JOptionPane.showMessageDialog(null, " Process Canceled ");
	 			}
	 		}
	 	});
	 	
	 	/*********************************** BUTTON TO COMPRESS *********************************/
	 	ImageButton.addMouseListener(new MouseAdapter(){
	 		@Override
	 		public void mouseClicked(MouseEvent arg0) {
	 			// TODO Auto-generated method stub
	 			super.mouseClicked(arg0);
	 			if(pathname!="")
	 			{
	 				OptionOneImageClass obj=new OptionOneImageClass(pathname);
	 			}
	 			else
	 				JOptionPane.showMessageDialog(null, " You Should Choose A File First If You Please ");			
	 		}
	 	}) ;
	 	
	 	/***************************** BUTTON TO DECOMPRESS **************************/
	 	Option2.addMouseListener(new MouseAdapter(){
	 		@Override
	 		public void mouseClicked(MouseEvent arg0) {
	 			// TODO Auto-generated method stub
	 			super.mouseClicked(arg0);
	 				System.out.println("Enter number of levels :");
	 				int numOfLevels = new Scanner(System.in).nextInt();
	 				System.out.println("Enter your data seperated by spaces ' ' : ");
					String input=new Scanner(System.in).nextLine();
					String[] arrdata=input.split(" ");
					Vector<Integer> data=new Vector<Integer>();
					for(int i=0;i<arrdata.length;i++) {
						data.add(Integer.parseInt(arrdata[i]));
					}
					try {
						Compress obj=new Compress();
						obj.levels=numOfLevels;
						obj.GetAverages(numOfLevels, data);
						obj.buildQuantizier();
						obj.saveQuantiziar();
						obj.BigTable(data);
						obj.printdata();
					}catch(Exception e) {
						System.out.println("Error to Invoke methode compress1");
					}
	 		}
	 	});
	 }

}
