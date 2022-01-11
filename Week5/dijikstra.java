import java.io.*;
import java.util.*;
class dijikstra
{
    static class Edge implements Comparable <Edge> 
    {
        int source;
        int neighbour;
        int weight;

        Edge(int s, int n, int w) 
        {
            source = s;
            neighbour = n;
            weight = w;
        }

        public int compareTo(Edge e) 
        {
            return this.weight - e.weight;
        }
    }

    static class Pair implements Comparable< Pair> 
    {
        int v;
        int wsf;

        Pair(int v, int wsf) 
        {
            this.v = v;
            this.wsf = wsf;
        }

        public int compareTo(Pair o) 
        {
            return this.wsf - o.wsf;
        }
    }
    static LinkedList <Edge> adj[];
    static int n;
    static boolean visited[];
    static int weights[];
    static int ans[];
    dijikstra(int nodes)
    {
        n = nodes;
        adj = new LinkedList[n];
        visited = new boolean[n];
        ans = new int[n];
        for(int i = 1;i<n;i++)
            adj[i] = new LinkedList();
    }

    public static void addEdge(int source, int neighbour, int weight)
    {
        Edge edge = new Edge(source,neighbour,weight);
        adj[source].add(edge);
    }
    
    public static void solve(int s)
    {
        visited = new boolean[n];
        weights = new int[n];
        ans[s] = 0;
        for(int i = 1;i<n;i++)
            weights[i] = Integer.MAX_VALUE;

        weights[s] = 0;

        PriorityQueue <Pair> queue= new PriorityQueue<>();
        queue.add(new Pair(s, weights[s]));
        while(queue.size()>0)
        {
            Pair removed = queue.remove();
            if(visited[removed.v] == false)
            {
                visited[removed.v] = true;
                ans[removed.v] = removed.wsf;
                for(Edge e: adj[removed.v])
                {
                    if(visited[e.neighbour] == false)
                    {
                        queue.add(new Pair(e.neighbour, removed.wsf+e.weight));
                    }
                }
            }
        }
        
        
        for(int i = 1;i<n;i++)
        {
            if(ans[i] == Integer.MAX_VALUE)
            {
                ans[i] = 1000000;
            }
        }
        
        System.out.println(ans[7]);
        System.out.println(ans[37]);
        System.out.println(ans[59]);
        System.out.println(ans[82]);
        System.out.println(ans[99]);
        System.out.println(ans[115]);
        System.out.println(ans[133]);
        System.out.println(ans[165]);
        System.out.println(ans[188]);
        System.out.println(ans[197]);
        
    }


    public static void main() throws IOException
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of vertices in the graph");
        int n = in.nextInt();  
        n++;
        dijikstra graph = new dijikstra(n);
        
        FileReader file = new FileReader("dd.txt");
        
        BufferedReader fileInput = new BufferedReader(file);
        String text;
        
        while((text = fileInput.readLine())!=null)
        {
            Scanner read = new Scanner(text);
            int so = read.nextInt();
            
            int i = 0;
            
            while(read.hasNext() && i<2)
            {
                String total = read.next();
                StringTokenizer too = new StringTokenizer(total, ",");
                int neigh = Integer.parseInt(too.nextToken());
                int we = Integer.parseInt(too.nextToken());
                graph.addEdge(so,neigh,we);
            }
            
        }
       
        solve(1);
        
        
    }
}