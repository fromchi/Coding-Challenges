import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.*;
import java.util.*;
import java.util.Vector;
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.Map; 
import java.util.Map.Entry; 
import java.util.Set;



public class Matrix {
  static boolean walk;
  static boolean closed_walk;
  static boolean open_walk;
  static boolean path;
  static boolean cycle;
  static boolean circuit; //TODO
  static boolean trail;   //TODO
  
  /*! \brief Brief description.
   *         MatrixPropertiesDetermination function
   *         is given the an arraylist of the matrix and a list of points and verifies if said points in that matrix represent an walk/closed walk/open walk/path/cycle/circuit/trail
   */
  static void MatrixPropertiesDetermination(List<Integer> points,  ArrayList<int[]> matrixes) {
      int starting_point, next_point = 0;
      for (int i = 0; i < points.size(); i++ ) {
        if ((i+1)== points.size()){
            if(points.get(0) == points.get(i)) {
                closed_walk = true;
                Set<Integer> determine_cycle = new HashSet<>();
                for (int point : points) {
                    if (determine_cycle.add(point) == true) 
                    { 
                         cycle = true;
                    } 
                    else {
                        break;
                    }
                }
            }
            else {
                open_walk = true;
                Set<Integer> set = new HashSet<>();
                for (int point : points) {
                    if (set.add(point) == true) 
                    { 
                         path = true;
                    } 
                    else {
                        break;
                    }
                }
                break;
            }
        }
        else {
            starting_point = points.get(i);
            next_point = points.get(i+1);
            int[] matrixtopeek = matrixes.get(starting_point);
            System.out.println(matrixtopeek[next_point]);
            if(matrixtopeek[next_point] == 0) {
                walk = false;
                
            }
        }
    }
    walk = true;
      
  }
  
  /*! \brief Brief description.
   *         PrintMatrixProperties function
   *         prints the propterties of the matrix based on which statements define the matrix
   */
  static void PrintMatrixProperties() {
      if(walk == true) {
          System.out.println("Is a walk");
      }
      if(closed_walk == true) {
          System.out.println("Is a closed walk");
      }
       if(open_walk== true) {
          System.out.println("Is an open walk");
      }
      if(path == true) {
          System.out.println("Is a path");
      }
      if(cycle == true) {
          System.out.println("Is a cycle");
      }
      if(circuit == true) {
          System.out.println("Is a circuit");
      }
      if(trail == true) {
          System.out.println("Is a trail");
      }

  }
  
    /*! \brief Brief description.
     *         FileHandler function
     *         reads in a matrix text file and returns a populated Arraylist of int arrays with the given matrix data
     */
    static ArrayList<int[]> FileHandler(){ 
        
        ArrayList<int[]> matrixes = new ArrayList<int[]>();  //arraylist of int arrays to iterate through the matrixes
        
        try {
            File matrix_data_file = new File("/uploads/matrix2.txt");
            Scanner file_reader = new Scanner(matrix_data_file);
            int matrix_size = 0;
            while (file_reader.hasNextLine()) { //reads the entire file populating an array per line with array data
                String input_line[]= file_reader.nextLine().split(" ");
                int[] matrix_row = new int[6];
                for(int i =0 ; i < input_line.length; i++){
                    matrix_row[i]= Integer.parseInt(input_line[i]);
                    
                }
                matrixes.add(matrix_row);
                matrix_size++;
                
            }
            for (int i[] : matrixes) {
            System.out.println(Arrays.toString(i));
                
            }
            System.out.println(matrix_size-1);
            file_reader.close();
            
        }
        catch (FileNotFoundException file_not_found) { //in case of issues with reading in the file
            System.out.println("An error occurred.");
            file_not_found.printStackTrace();
            
        }
        return matrixes;
    }

    
    public static void main(String[] args) { //function main
     
      
    List<Integer> vectors = new LinkedList<>(); //list of points to analyze the properties of the given matrix
    
    //points are added 
	vectors.add(0,0);
	vectors.add(1,2);
	vectors.add(2,4);
	vectors.add(3,0);
	//vectors.add(4,4);
	
	var matrixes = FileHandler(); //file is read and function returns a structure containing the matrix points
    MatrixPropertiesDetermination(vectors, matrixes); //matrix is analyzed to see if it is a walk/path/etc
    PrintMatrixProperties(); //properties that the matrix has are printed out
  }
}