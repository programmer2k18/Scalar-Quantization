package Scalar_Quantization_package;

public class TableNode {
	//my class of the table node
    double low,high;
    int Q,Q_1;
    public TableNode() {}
    public TableNode(double low, double hieght, int Q, int Q_1) {
        this.low = low;
        this.high = hieght;
        this.Q = Q;
        this.Q_1 = Q_1;
    }
}
