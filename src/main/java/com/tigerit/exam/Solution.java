package com.tigerit.exam;


import static com.tigerit.exam.IO.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {

    static void formatcolumn(String g) {
            String x1 = new String();
            String x2 = new String();
            int i = 0;
            int id = 0; 
     
            for (i = 0;; i++) {
                if (g.charAt(i) == '.') {
                    id = Name.get(x1);
                    break;
                }
                x1 = x1 + g.charAt(i);
            }
     
            for (int j = i + 1; j < g.length(); j++) {
                x2 = x2 + g.charAt(j);
            }
            for (i = 0; i < Col[id]; i++) {
                if (ColumnName[id][i].equals(x2)) {
                    b = i;
                    a = id;
                    break;
                }
            }
     
        }

        static void print(int r, int c) {
            for(int i = 0; i < r-1; i++)
            {
                for(int j = i+1; j < r; j++)
                {
                    for(int k = 0; k < c; k++)
                    {
                        if(FinalTable[i][k] > FinalTable[j][k])
                        {
                            for(int l = 0; l < c; l++)
                            {
                                temp[l] = FinalTable[i][l];
                            }
                            for(int l = 0; l < c; l++)
                            {
                                FinalTable[i][l] = FinalTable[j][l];
                            }
                            for(int l = 0; l < c; l++)
                            {
                                FinalTable[j][l] = temp[l];
                            }
                            break;
                        }
                    }
                }
            }
            for(int i = 0; i < r; i++)
            {
                for(int j = 0; j < c; j++)
                {
                    System.out.print(FinalTable[i][j]+" ");
                }
                System.out.println();
            }
        }
     
    @Override
    public void run() {
        // your application entry point

        // sample input process
       // String string = readLine();

        //Integer integer = readLineAsInteger();

        // sample output process
        //printLine(string);
        //printLine(integer);

        static String[] TableName = new String[15];
        static int[][][] Table = new int[15][105][105];
        static String[][] ColumnName = new String[15][105];
        static int[] Row = new int[15];
        static int[] Col = new int[15];
        static String[] Current = new String[205];
        static int[][] FinalTable = new int[10005][205];
        static int[][] Temp = new int[10005][205];
        static int[] temp = new int[205];
        static int a, b;
        static HashMap<String, Integer> NameReverse = new HashMap<String, Integer>();
        static HashMap<String, Integer> Name = new HashMap<String, Integer>();
     
        
        
     
     
            //Input Part
            Scanner input = new Scanner(System.in);
            Integer test = input.nextInt();
            Integer t;
            for (t = 1; t <= test; t = t + 1) {
                System.out.println("Test: " + t);
     
                //Table input start
                Integer n = input.nextInt();
                NameReverse.clear();
                String s, ss;
                for (int i = 0; i < n; i++) {
                    s = input.nextLine();
                    TableName[i] = input.nextLine();
                    NameReverse.put(TableName[i], i);
                    Row[i] = input.nextInt();
                    Col[i] = input.nextInt();
                    s = input.nextLine();
                    for (int j = 0; j < Col[i]; j++) {
                        ss = input.next();
                        ColumnName[i][j] = ss;
                    }
                    s = input.nextLine();
                    for (int j = 0; j < Row[i]; j++) {
                        int l;
                        for (int k = 0; k < Col[i]; k++) {
                            l = input.nextInt();
                            Table[i][j][k] = l;
                        }
                    }
                }
                //Table input end
     
                //Query input
                int q;
                q = input.nextInt();
                s = input.nextLine();
                String s1, s2, s3, s4;
                int flag = 0;
                for (int qq = 0; qq < q; qq++) {
                    Name.clear();
                    if(flag != 0)
                    {
                        s  = input.nextLine();
                    }
                    flag = 1;
                    //Input first line of query
                    s1 = input.nextLine();
                    int f = 0, all = 0;
                    String[] fst = s1.split("[' ',]");
                    for (String x : fst) {
                        if (x.equals("Select") || x.equals("select") || x.equals("SELECT") || x.equals(",") || x.equals(" ") || x.equals("=") || x.length() == 0) {
                            continue;
                        }
                        if (x.equals("*")) {
                            all = 1;
                            break;
                        }
                        Current[f] = x;
                        f = f + 1;
                    }
     
                    //Input second line of query
                    s2 = input.nextLine();
                    String[] ft = s2.split("[' ',]");
                    int tg = -1, ck = 0;
                    for (String x : ft) {
                        ck++;
                        if (ck == 1) {
                            continue;
                        }
                        if (x.equals(",") || x.equals(" ") || x.equals("=") || x.equals("from") || x.equals("FROM") || x.equals("From")) {
                            continue;
                        }
                        if (x.length() > 0) {
                            if (tg == -1) {
                                tg = NameReverse.get(x);
                                Name.put(x, tg);
                            } else {
                                Name.put(x, tg);
                            }
                        }
                    }
     
                    //Input third line of query
                    s3 = input.nextLine();
                    int tgg = -1;
                    ck = 0;
                    String[] st = s3.split("[' ',]");
                    for (String x : st) {
                        ck++;
                        if (ck == 1) {
                            continue;
                        }
                        if (x.equals(",") || x.equals(" ") || x.equals("=") || x.equals("join") || x.equals("Join") || x.equals("JOIN")) {
                            continue;
                        }
                        if (x.length() > 0) {
                            if (tgg == -1) {
                                tgg = NameReverse.get(x);
                                Name.put(x, tgg);
                            } else {
                                Name.put(x, tgg);
                            }
                        }
     
                    }
     
                    //Input last line of query
                    s4 = input.nextLine();
                    String[] condition = s4.split("[' ',=]");
                    ck = 0;
                    int[] t1 = new int[3];
                    int[] t2 = new int[3];
                    int sh = 0;
                    for (String x : condition) {
                        ck++;
                        if (ck == 1) {
                            continue;
                        }
                        if (x.equals(",") || x.equals(" ") || x.equals("=") || x.equals("on") || x.equals("On") || x.equals("ON")) {
                            continue;
                        }
                        if (x.length() > 0) {
                            formatcolumn(x);
                            t1[sh] = a;
                            t2[sh] = b;
                            sh ^= 1;
                        }
                    }
     
                    //Printing start
                    int x1 = t1[0];
                    int x2 = t1[1];
                    int y1 = t2[0];
                    int y2 = t2[1];
                    int rw = 0;
                    int col = Col[x1] + Col[x2];
                    for (int i = 0; i < Row[x1]; i++) {
                        for (int j = 0; j < Row[x2]; j++) {
                            if (Table[x1][i][y1] == Table[x2][j][y2]) {
                                for (int h = 0; h < Col[x1]; h++) {
                                    FinalTable[rw][h] = Table[x1][i][h];
                                }
                                for (int h = 0; h < Col[x2]; h++) {
                                    int fe = h + Col[x1];
                                    FinalTable[rw][fe] = Table[x2][j][h];
                                }
                                rw = rw + 1;
                            }
                        }
                    }
                    ///System.out.println(strfound);
                    if (all == 1) {
                        for (int h = 0; h < Col[x1]; h++) {
                            System.out.print(ColumnName[x1][h] + " ");
                        }
                        for (int h = 0; h < Col[x2]; h++) {
                            System.out.print(ColumnName[x2][h] + " ");
                        }
                        System.out.println();
                        print(rw, col);
                        System.out.println();
                    } else {
                        for (int i = 0; i < f; i++) {
                            formatcolumn(Current[i]);
                            if (a == x1) {
                                System.out.print(ColumnName[x1][b] + " ");
                                for (int j = 0; j < rw; j++) {
                                    Temp[j][i] = FinalTable[j][b];
                                }
                            } else {
                                System.out.print(ColumnName[x2][b] + " ");
                                for (int j = 0; j < rw; j++) {
                                    Temp[j][i] = FinalTable[j][b + Col[x1]];
                                }
                            }
                        }
                        System.out.println();
                        for(int i = 0; i < rw; i++)
                        {
                            for(int j = 0; j < f; j++)
                            {
                                FinalTable[i][j] = Temp[i][j];
                            }
     
                        }
                        print(rw,f);
                        System.out.println();
     
     
                    }
     
                }
     
            }
        
    }
}
