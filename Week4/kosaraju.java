import java.io.*;
import java.util.*;
import java.util.Stack;
class kosaraju
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
    static LinkedList <Edge> adj[],revadj[];
    static int n;
    static int sizes[];
    static int sizeofscc = 0;
    static int greatesttime[];
    static boolean [] visited;
    static boolean [] visited2;
    static int s;
    static int time;
    static int leader[];
    static int numberofscc;
    static Stack<Integer> stack;

    kosaraju(int nodes)
    {
        n = nodes;
        adj = new LinkedList[n];
        revadj = new LinkedList[n];
        visited = new boolean[n];
        visited2 = new boolean[n];
        for(int i = 1;i<n;i++)
        {
            adj[i] = new LinkedList();
            revadj[i] = new LinkedList();
        }
    }

    public static void addEdge(int source, int neighbour)
    {
        Edge edge = new Edge(source, neighbour);
        Edge reve = new Edge(neighbour, source);
        adj[source].add(edge);
        revadj[neighbour].add(reve);
        
    }
   
    public static void dfsloop(int option)
    {
        leader = new int[n];
        numberofscc = 0;
        if(option==0)
        {
            for(int i = n-1;i>=1;i--)
            {
                int great = greatesttime[i];

                if(visited2[great]==false)
                {
                    s = great;
                    sizeofscc = 0;
                    DFS_iterative(great,option);
                    sizes[great] = sizeofscc;
                    numberofscc++;
                }

            }
        }

        else
        {
            for(int i = n-1;i>=1;i--)
            {
                if(visited[i]==false)
                    DFS_iterative(i,option);
            }
        }
    }

    public static void solve()
    {
        time = 0;

        greatesttime = new int[n];
        dfsloop(1);

        
        sizes = new int[n];
        dfsloop(0);
        Arrays.sort(sizes);

        for(int i = n-1;i>=n-5;i--)
        {
            if(sizes[i]!=0)
                System.out.println(sizes[i]);
        }

    }
    
    public static void DFS_iterative(int source, int option)
    {

        stack = new Stack();
        stack.push(source);
        if(option== 1)
        {

            int cur = source;

            come:
            while(stack.size()>0)
            {

                cur = stack.peek();

                visited[cur] = true;
                for(Edge e: revadj[cur])
                {
                    if(visited[e.neighbour] == false)
                    {

                        stack.push(e.neighbour);
                        continue come;
                    }
                }
                int vert = stack.pop();
                time++;
                greatesttime[time] = vert;
            }

        }
        else
        {

            while(stack.size()>0)
            {
                int  cur = stack.pop();
                leader[cur] = s;
                
                if(visited2[cur] == false)
                {
                    sizeofscc++;
                    visited2[cur] = true;
                    for(Edge e: adj[cur])
                    {
                        if(visited2[e.neighbour] == false)
                            stack.push(e.neighbour);
                    }
                }

            }
        }
    }

    public static void main() throws IOException
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        kosaraju graph = new kosaraju(n);
        FileReader file = new FileReader("SCC (2).txt");
        BufferedReader fileInput = new BufferedReader(file);
        String text;
        while((text=fileInput.readLine())!=null)
        {
            StringTokenizer stoken = new StringTokenizer(text);
            if(stoken.countTokens()<=0)
                break;

            String a = stoken.nextToken();
            String b = stoken.nextToken();
            graph.addEdge(Integer.parseInt(a),Integer.parseInt(b));      
        }

        solve();
    }
}