package Scalar_Quantization_package;

import java.util.Vector;

public class NodeTable {
	//public static NodeTable leftNode=null;
    //public static NodeTable rightNode=null;
    public float avarage;
    public Vector<Integer> NodeData=new Vector<Integer>();
    public NodeTable() {
    	//leftNode=new NodeTable();
    	//rightNode=new NodeTable();
    }
    public float CalculateAverage(){
        float sum=0;
        for(int i=0;i<NodeData.size();i++){
          sum+=NodeData.get(i);
        }
        avarage=Math.round(sum/NodeData.size());
        return  avarage;
    }
}
