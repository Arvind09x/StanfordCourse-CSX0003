import java.io.*;
import java.util.*;

class two
{
    static int count = 0;
    public static void main() throws IOException
    {
        Scanner in = new Scanner(System.in);
        
        int l = in.nextInt();
        HashSet <Long> map = new HashSet<>();
        FileReader filee = new FileReader("2sum.txt");
        BufferedReader a = new BufferedReader(filee);
        String text;
        Scanner read = new Scanner(filee);
        long ar[] = new long[l];
        int i = 0;
        while((text = a.readLine())!=null)
        {
            long temp = Long.parseLong(text);
            if(!map.contains(temp))
            {
                map.add(temp);
                ar[i] = temp;
                i++;
            }
        }
        int n = map.size();
    
        for(int t = -10000;t<=10000;t++)
        {
            
            for(int j = 0;j<map.size();j++)
            {

                if(map.contains(t-ar[j]))
                {
                    count++;
                    System.out.println(t);
                    break;
                    
                }
                
            }
            
        }
        
        System.out.println(count);
    }
}
