import java.util.*;
import java.io.*;
class quick2
{
    static long count = 0;
    public static void swap(int a[], int i, int j)
    {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static int partition(int a[], int low, int high)
    {
        int pivot;
        count = count+high- low;
        pivot = a[high];
        swap(a,low,high);
        int i = low+1;
        for(int j = low+1;j<=high;j++)
        {
            if(a[j]<pivot)
            {

                swap(a,j,i);
                i++;
            }
        }

        swap(a,i-1,low);
        

        return i-1;
    }

    public static void quicksort(int a[],int low,int high)
    {
        if(low<high)
        {
            int parti = partition(a,low,high);
            quicksort(a,low,parti-1);
            quicksort(a,parti+1,high);
        }
    }

    public static void main() throws IOException
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the lenght of the array");
        int n = in.nextInt();
        int a[] = new int[n];
        FileReader file = new FileReader("QuickSort.txt");
        BufferedReader fileInput = new BufferedReader(file);
        String text;
        for(int i = 0;i<n;i++)
        {
            text = fileInput.readLine();
            int temporary = Integer.parseInt(text);
            a[i] = temporary;
        }

        quicksort(a,0,n-1);
        System.out.println(count);
    }
}