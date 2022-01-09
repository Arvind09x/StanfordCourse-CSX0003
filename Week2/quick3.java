import java.util.*;
import java.io.*;
class quick3
{
    static long count = 0;
    public static void swap(int a[], int i, int j)
    {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static int meadian(int a[], int k, int b, int c)
    {
        int q[] = {a[k],a[b],a[c]};

        Arrays.sort(q);

        
        int x = q[1];
        if(x==a[k])
            return k;

        else if(x==a[b])
            return b;

        else
            return c;

    }
    public static int partition(int a[], int low, int high)
    {
        int pivot;
        int piv;
        count = count+high- low;
        int d = high - low;
        d = d+1;
        if(d>=3)
        {
            int mi;
            if(d%2 == 0)
                mi =d/2;

            else
                mi = (d+1)/2;

            mi = mi -1;
 
            piv = meadian(a,low, high,low + mi);

        }
        else
        {

            piv = low;
        }

        pivot = a[piv];
        swap(a,low,piv);
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