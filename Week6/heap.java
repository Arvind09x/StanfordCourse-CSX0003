import java.io.*;
import java.util.*;
class heap
{
    public static void main() throws IOException
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        FileReader filee = new FileReader("Median.txt");
        BufferedReader a = new BufferedReader(filee);
        String text;
        int ar[] = new int[n];
        int ans[] = new int[n];
        int sum = 0;
        int i = 1;
        int g = 0;
        while((text = a.readLine())!=null)
        {
            i =1;
            g = 0;
            ar= new int[n];
            queue.add(Integer.parseInt(text));
            while(queue.size()>0)
            {
                ar[i] = queue.poll();
                i++;
                g++;
            }
            int mid;
            if(g%2==0)
            {
                mid = (g)/2;
                ans[g] = ar[mid];
            }
            else
            {
                mid = (g+1)/2;
                ans[g] = ar[mid];
            }
            for(int j = 1;j<i;j++)
                queue.add(ar[j]);
        }
        for(int index = 1;index<n;index++)
            sum = sum + ans[index];
        System.out.println(sum);
    }
}