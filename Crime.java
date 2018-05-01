import java.io.*;
import java.util.*;
import java.math.*;
import java.text.DecimalFormat;


public class Crime {

    public static void dendogram(String arr[])
    {
        HashSet<String> den1 = new HashSet<String>();
        int size = 0;
        double accuracy = 0;
        System.out.println(" \n Clusters without repetition :: \n");
        for(int i = 0 ; i<arr.length ; i++)
        {
            den1.add(arr[i]);
        }
        for(String i:den1)
        {
            System.out.println(i);
            size++;
        }
        double size1 = size;
        accuracy = (size1/12.0)*100.0;
        System.out.println("\n Total number of clusters in Rstudio dendogram : 12 \n");
        System.out.println("Accuracy in comparison to Rstudio : \t" + df1.format(accuracy));

        
    }

    public static double[] euclidean_distance(int m1[] , int f1[])
    {
        int m1_length = m1.length;
        int f1_length = f1.length;
        double temp = 0.0;
        double dist[] = new double[200];
        int a = 0;
        for(int i = 0 ; i<m1_length ; i++)
        {
            for(int j = 0 ; j<f1_length ; j++)
            {   
                temp = Math.sqrt(Math.pow(Math.abs(m1[i]-m1[j]),2)+Math.pow(Math.abs(f1[i]-f1[j]),2));
                dist[a] = temp;
                temp = 0.0;
                a++;
            }
        }
        return dist;
    }

    public static double[][] putting_0(int size)
    {
        double arr[][] = new double[size][size];
        for(int i = 0 ; i<size ; i++)
        {
            for(int j = 0 ; j<size ; j++)
            {
                if(i == j)
                {
                    arr[i][j] = 0.0;
                }
            }
        }
        return arr;
    }

    public static double find_max(double a , double b)
    {
        if(a>=b)
        {
            return a;
        }
        else
        {
            return b;
        }

    }

    public static int[] find_indexs(double arr[][] , int size)
    {
        double min = 1000000.0;
        int index[] = new int[2];
        for(int i = 0 ; i<size ; i++)
        {
            for(int j = 0 ; j<size ; j++)
            {
                if(arr[i][j] <= min && arr[i][j]>0.0)
                {
                    min = arr[i][j];
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }
    
    public static int[][] clusters(double arr[][] , int size_r , int size_c)
    {
        int clus[][] = new int[14][2];
        int size = size_r;
        //finding initial minimum
        int index_i;
        int index_j;
        int[] min = find_indexs(arr,size_r);
        index_i = min[0];
        index_j = min[1];
        clus[0][0] = index_i;
        clus[0][1] = index_j;
        int a = 1;
        double min2 = 10000000.0;
        double temp[][] = new double[size][size]; 
        while(size>1)
        {
            System.out.println("\n");
            for(int i = 0 ; i<size ; i++)
            {
                for(int j = 0 ; j<size ; j++)
                {
                    if(i==j)
                    {
                        temp[i][j] = 0.0;
                    }
                    else if(i==index_i || i==index_j)
                    {
                        temp[i][j] = find_max(arr[i][index_i],arr[i][index_j]);
                    }
                    else
                    {
                        temp[i][j] = arr[i][j];
                    }
                    System.out.print(temp[i][j] + "\t");
               }
               System.out.println("\n");
            }
            size--;
            for(int i = 0 ; i<size ; i++)
            {
                for(int j = 0 ; j<size ; j++)
                {
                    if(i!=index_j || j!=index_j)
                    {
                        if(temp[i][j]<=min2 && temp[i][j]>0.0)
                        {
                            min2 = arr[i][j];
                            clus[a][0] = i;
                            clus[a][1] = j;
                            index_i = i;
                            index_j = j;
                        }
                    }
                }
            }
            min2 = 100000000.0;
            a++;   
        }
        return clus;
        
    }
    
    private static DecimalFormat df1 = new DecimalFormat(".#");
    public static void main(String[] args) {

        String csvFile = "E:/Study/Data Mining Dataset/plot_final_2/child_crime_updated_14thMarch.csv";
        String  m[] = new String[15];
        String f[] = new String[15];
        int male[] = new int[14];
        int female[] = new int[14];
        double matrix[][] = new double[14][14];
        int i = 0;
        int index_x = 0 , index_y = 0;
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
         {

            while ((line = br.readLine()) != null) 
            {
                String[] temp = line.split(cvsSplitBy);
                m[i] = temp[2];
                f[i] = temp[3];
                i++;
            }
            i = 0;
            for(int j  = 1; j<15 ; j++)
            {
                male[i] = Integer.parseInt(m[j]);
                female[i] = Integer.parseInt(f[j]);
                i++;
            }
            i = 0;
            //Getting euclidean distance from the function
            double[] distance = euclidean_distance(male,female); 
            String alpha[] = {"Andra Pradesh","Assam","Chattisgharh","Gujrat","Haryana","Karnataka","Kerla","Madhya Pradesh","Maharashtra","Odhisa","Rajisthan","Telangana","Uttar Pradesh","Delhi","N"};
            int count = 0;
            // Creating euclidean distance matrix
            System.out.println("Euclidean Distance \n");
            for(i = 0 ; i<14 ; i++)
            {
                for(int j = 0 ; j<14 ; j++)
                {
                    matrix[i][j] = Double.parseDouble(df1.format(distance[count]));
                    System.out.print(matrix[i][j] + "\t");
                    count++;
                }
                System.out.println("\n");
            }
            count = 0;
            int s_r = matrix.length;
            int s_c = matrix[0].length;
            int[][] clusters_index = clusters(matrix,s_r,s_c);
            int clus_a = 0;
            int clus_b = 0;
            String dendo[] = new String[clusters_index.length];
            String dd = "";
            String dd_temp = "";
            System.out.println("The clusters that are produced from starting to last are ::: \n");
            for(i = 0 ; i<clusters_index.length ; i++)
            {
                clus_a = clusters_index[i][0];
                clus_b = clusters_index[i][1];
                System.out.println(alpha[clus_a] + "-" + alpha[clus_b]);
                dd = alpha[clus_a] + "-" + alpha[clus_b];
                dendo[i] = dd;
                dd = "";
            }
            dendogram(dendo);
            
        } 
        catch (IOException e)
         {
            e.printStackTrace();
        }

    }

}