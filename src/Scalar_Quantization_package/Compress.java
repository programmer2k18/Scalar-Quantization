package Scalar_Quantization_package;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Compress {
	public ArrayList<Integer> Avgs = new ArrayList<Integer>();
	public int levels=0;
	public float avg=0;
    Vector<TableNode> Table = new Vector<>();
    File file;
    PrintWriter out;
    public void GetAverages(int level, Vector<Integer> data) {
    	if(level<=1) {
    		level=this.levels;
    		if(!Avgs.contains((int)this.avg)) {
    			Avgs.add((int)this.avg);
    		}
    		return;
    	}
    	NodeTable node=new NodeTable();
    	for(int i=0;i<data.size();i++) { 
    		node.NodeData.add(data.get(i));
    	}
    	node.avarage=node.CalculateAverage();
    	this.avg=node.avarage;
    	int leftvalue=(int) (node.avarage-1);
    	int rightvalue=(int) (node.avarage+1);
    	Vector<Integer> leftarr=new Vector<Integer>();
    	Vector<Integer> rightarr=new Vector<Integer>();
    	for(int i=0;i<data.size();i++) {
    		if(data.get(i)<=node.avarage) {
    			leftarr.add(data.get(i));
    		}
    		else
    			rightarr.add(data.get(i));
    	}
    	level--;
    	GetAverages(level,leftarr);
    	GetAverages(level,rightarr);
    }
    public void buildQuantizier() {
    	TableNode node=new TableNode();
    	node.low=0;
    	node.high=(Avgs.get(0)+Avgs.get(1))/2;
    	node.Q=0;
    	node.Q_1=Avgs.get(0);
    	Table.add(node);
    	for(int i=1,j=0;i<Avgs.size()-1;i++,j++) {
    		TableNode node2=new TableNode();
    		node2.low=Table.get(j).high;
    		node2.high=(Avgs.get(i)+Avgs.get(i+1))/2;
    		node2.Q=i;
    		node2.Q_1=Avgs.get(i);
    		Table.add(node2);
    	}
    	TableNode node3=new TableNode();
    	node3.low=Table.get(Table.size()-1).high;
		node3.high=127;
		node3.Q=this.levels-1;
		node3.Q_1=Avgs.get(Avgs.size()-1);
		Table.add(node3);
    }
    public void saveQuantiziar() {
    	try {
    		file=new File("quantizier.txt");
    		out=new PrintWriter(file);
    	}catch(FileNotFoundException e) {
    		System.out.println("Error to open quantizier file");
    	}
    	for(int i=0;i<Table.size();i++) {
    		out.println(Table.get(i).Q + " " + Table.get(i).low+ " "+Table.get(i).high + " "+ Table.get(i).Q_1);
    	}
    	out.close();
    }
    public void BigTable(Vector<Integer> data) {
    	try {
    		file=new File("finaltable.txt");
    		out=new PrintWriter(file);
    	}catch(FileNotFoundException e) {
    		System.out.println("Error to open quantizier file");
    	}
    	for(int i=0;i<data.size();i++) {
    		for(int j=0;j<Table.size();j++) {
    			if(data.get(i)>=Table.get(j).low&&data.get(i)<Table.get(j).high) {
    				int error = Math.abs(data.get(i)-Table.get(j).Q_1);
    				out.println(data.get(i) + " "+ Table.get(j).Q + " "+ Table.get(j).Q_1  +" "+ error +" "+ error*error);
    			}
    		}
    	}
    	out.close();
    }
    public void printdata() {
    	for(int i=0;i<Avgs.size();i++) {
    		//System.out.println("Avg : " + Avgs.get(i));
    		System.out.println(Table.get(i).Q + " " + Table.get(i).low+ " "+Table.get(i).high + " "+ Table.get(i).Q_1);
    	}
    }
    
}
