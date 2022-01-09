import java.util.Scanner;
import java.io.*;
class inversion
{
    
    public static long merge(int x[],int le,int m, int r)
    {
        long count = 0;
        int length1 =  m-le+1;
        int length2 =  r-m;
        int y[] = new int[length1];
        int z[] = new int[length2];
        for(int i = 0;i<length1;i++)
            y[i] = x[le+i];
        for(int j = 0;j<length2;j++)
            z[j] = x[m+j+1];
        int i = 0;
        int j = 0;
        int k = le;
        while(i<length1&&j<length2)
        {
            if(y[i]<z[j])
            {
                x[k] = y[i];
                i++;
                k++;
            }
            else
            {
                x[k] = z[j];
                j++;
                k++;
                count = count+(length1-i);
            }
        }
        while(i<length1)
        {
            x[k] = y[i];
            k++;
            i++;
        }
        while(j<length2)
        {
            x[k] = z[j];
            k++;
            j++;
        }
        return count;
    }
    public static long mergesort(int a[],int l,int h)
    {
        long count = 0;
        if(l < h)
        {
            
            int mid = l+(h-l)/2;
            count = mergesort(a,l,mid);
            count = count + mergesort(a,mid+1,h);
            count = count + merge(a,l,mid,h);
        }
        return count;
    }
    public static void main(String [] args) throws IOException
    {
        Scanner in = new Scanner(System.in);
        FileReader file = new FileReader("IntegerArray.txt");
        BufferedReader fileInput = new BufferedReader(file);
        String text;
        System.out.println("Enter the length of the array");
        int n= in.nextInt();
        int A[] = new int[n];
        for(int i = 0;i<n;i++)
        {
           text = fileInput.readLine();
           int temporary = Integer.parseInt(text);
           A[i] = temporary;
        } 
        
        System.out.println("The number of inversions are " + mergesort(A,0,n-1) );
    }
}