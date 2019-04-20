package Scalar_Quantization_package;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class OptionOneImageClass {
	int ImageValues[][];
	String path;
	File file;
	PrintWriter out;
	public OptionOneImageClass(String imagePath) {
		 ImageValues=readImage(imagePath);
		 writeImage(ImageValues, "F:\\New folder\\Scalar_Quantization\\comm_photo.jpg");
		 WriteonFile("compressedPhotoOptionOne.txt");
	}
	public  int[][] readImage(String path){
        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));
            int hieght=img.getHeight();
            int width=img.getWidth();

            int[][] imagePixels=new int[hieght][width];
            for(int x=0;x<width;x++){
                for(int y=0;y<hieght;y++){

                    int pixel=img.getRGB(x, y);

                    int red=(pixel  & 0x00ff0000) >> 16;
                    int grean=(pixel  & 0x0000ff00) >> 8;
                    int blue=pixel  & 0x000000ff;
                    int alpha=(pixel & 0xff000000) >> 24;
                    imagePixels[y][x]=red;
                }
            }

            return imagePixels;
        } catch (IOException e) {
            System.out.println("Error to perform this operation");
            return null;
        }
		
    }

    public  void writeImage(int[][] imagePixels,String outPath){

        BufferedImage image = new BufferedImage(imagePixels.length, imagePixels[0].length, BufferedImage.TYPE_INT_RGB);
        for (int x= 0; x < imagePixels.length; x++) {
            for (int y = 0; y < imagePixels[x].length; y++) {
                //int value =-1 << 24;
                //value= 0xff000000 | (imagePixels[y][x]<<16) | (imagePixels[y][x]<<8) | (imagePixels[y][x]);
                //image.setRGB(x, y, value);
            	image.setRGB(x, y, (imagePixels[y][x] << 16) | (imagePixels[y][x] << 8) | (imagePixels[y][x]));
            }
        }
        File ImageFile = new File(outPath);
        try {
            ImageIO.write(image, "jpg", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void WriteonFile(String pathname) {
    	try {
    		 file =new File(pathname);
    		 out=new PrintWriter(file);
    	}
    	catch (Exception e) {
            System.out.println("Can't Open this File ");
        }
    	for(int i=0;i<ImageValues.length;i++) {
    		for(int j=0;j<ImageValues[i].length;j++) {
    			out.print(ImageValues[i][j] + " ");
    		}
    		out.println();
    	}
    	out.close();
    }
}
