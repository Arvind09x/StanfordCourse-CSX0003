import java.util.*;
import java.io.*;
class Graph1
{
    static class Edge
    {
        int source;
        int neighbour;

        Edge(int s, int n) 
        {
            source = s;
            neighbour = n;

        }

    }
    static LinkedList <Edge> adj[];
    static int n;
    static Edge ar[];
    Graph1(int nodes)
    {
        n = nodes;
        adj = new LinkedList[n];
        for(int i = 1;i<n;i++)
            adj[i] = new LinkedList();
    }

    public static void addEdge(int source, int neighbour)
    {
        Edge edge = new Edge(source,neighbour);
        adj[source].add(edge);
    }

    public static int randomgen(int max, boolean zerobased)
    {
        Random generator = new Random();
        int number = generator.nextInt(max);
        if(zerobased == true)
            return number;

        
        number++;
        return number;
    }

    public static void add(int vertex1, int vertex2)
    {
        n++;
        adj = Arrays.copyOf(adj,n);
        adj[n-1] = new LinkedList();
        for(Edge e: adj[vertex1])
        {
            if(e.neighbour == vertex2)
                continue;

            Edge temp = new Edge(n-1,e.neighbour);
            Edge temp2 = new Edge(e.neighbour, n-1);
            adj[n-1].add(temp);
            adj[e.neighbour].add(temp2);

        }
        for(Edge e  : adj[vertex2])
        {
            if(e.neighbour == vertex1)
                continue;

            Edge temp = new Edge(n-1,e.neighbour);
            Edge temp2 = new Edge(e.neighbour, n-1);
            adj[n-1].add(temp);
            adj[e.neighbour].add(temp2);
        }

    }

    public static void del(int vertex)
    {

        if(adj[vertex].size() == 0) 
            return;

        int size = adj[vertex].size();
            
        for(int j = 0;j<size;j++)
        {

            if(adj[vertex].size() == 0)
                break;
            Edge e = adj[vertex].get(0);

            deleteedge(e.source,e.neighbour);
                    
        }
            
        
        

    }

    public static Edge randomedge()
    {

        int rdm = randomgen((n-1), false);

        while(adj[rdm].size() == 0 )
        {
            rdm = randomgen(n-1,false);

        }

        int temp = adj[rdm].size();

        int ra = randomgen(temp, true);

        Edge ans = adj[rdm].get(ra);

        
        return ans;
    }

    public static int contraction(int noofvertices)
    {
        while(noofvertices>2)
        {

            Edge current = randomedge();

            add(current.source, current.neighbour);

            del(current.source);

            del(current.neighbour);

            
            noofvertices--;

        }
        return numberofedges();
    }

    public static boolean deledge(int u, int i,int v)
    {

        if (adj[u].get(i).neighbour == v)
        {
            adj[u].remove(i);
            return true;
        }

        return false;
    }

    public static void deleteedge(int u, int v)
    {

        for (int i = 0; i < adj[u].size(); i++)
        {
            if(deledge(u, i,v))
            {
                i =-1;
                continue;
            }
        }

       
        for (int i = 0; i < adj[v].size(); i++)
        {
            if(deledge(v, i,u))
            {
                i =-1;
                continue;
            }
        }
    }

    public static void main() throws IOException
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of vertices in the graph");
        int n = in.nextInt();  
        n++;
        int trials = 10;//(int) (n*Math.log(n));
        int ans = Integer.MAX_VALUE;

        for(int trys = 0;trys<trials;trys++)
        {

            System.out.println(trys + "  yy  " + trials);

            Graph1 graph = new Graph1(n);

            FileReader file = new FileReader("kargerMinCut.txt");
            BufferedReader fileInput = new BufferedReader(file);
            String text;

            while((text = fileInput.readLine())!=null)
            {
                StringTokenizer s = new StringTokenizer(text);
                String a[] = new String[s.countTokens()];

                for(int k = 0;k<a.length;k++)
                    a[k] = s.nextToken();

                for(int f = 1;f<a.length;f++)
                    graph.addEdge(Integer.parseInt(a[0]),Integer.parseInt(a[f]));

            }
           
            
            int min = contraction(n-1);
            if(min<ans)
                ans = min;

        }

        System.out.println(ans);
    }

    public static int numberofedges()
    {
        int number = 0;

        for(int i = 1;i<n;i++)
        {

            if(adj[i].size() == 0) 
                continue;

            number = number + adj[i].size();

            break;
        }
        return (number);
    }
}

