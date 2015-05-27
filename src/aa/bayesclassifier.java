package aa;
import java.util.*;
import java.sql.*;
import java.math.*;
import java.math.BigInteger;

class bayesclassifier
{
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://192.168.124.99/KMA";

   //  Database credentials
   static final String USER = "tad";
   static final String PASS = "123456";
   
   static String outlook[]=new String[10000];
   static String temperature[]=new String[10000];
   static String humidity[]=new String[10000];
   static String windy[]=new String[10000];
   static String class1[]=new String[10000];
   static String tableDB[][]=new String[10000][5];
   static int[][] DTBplay,DTBnoplay;

    static int[] CountDPlay,CountDnoPlay;
    static int x[][]=new int[10000][10];
    static int y[][]=new int[10000][10];
    static BigInteger m[]=new BigInteger[14];
    static BigInteger h[]=new BigInteger[14];
    static double d[][]=new double[10000][10];
    static BigInteger r=new BigInteger("1");
    static BigInteger r1=new BigInteger("1");
    
    static BigInteger[][] m0,m1,h0,h1;
    static BigInteger g;
    static BigInteger X[][]=new BigInteger[10000][5];
    static BigInteger Y[][]=new BigInteger[10000][5];
    static BigInteger[] Xsum,X0sum,X1sum;
    static BigInteger[]Ysum,Y0sum,Y1sum;   

    static int countU=0;
    static int NumReC; //NumReC= Number Records = countU
    static int NumColumn;

    static double NumPlay;
    static double NumNoPlay;
    static double prob[][]=new double[4][2];
    static double prob1[][]=new double[4][2];

    static double pp;
    static double npp;

    static int flag=0;
    static int flag1=0;

    static double play_N=1;
    static double notplay_N=1;





public static void main(String args[]){

// connect database
    Connection conn = null;
    Statement stmt = null;
    NumColumn=5;
    Xsum=DefaultONE(Xsum,NumColumn);
    Ysum=DefaultONE(Ysum,NumColumn);
    g=RandomBigInt(3);
    

   
  
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      //for(int i=0;i<99;++i){
      ResultSet rs = stmt.executeQuery("SELECT * FROM bayes");
      //STEP 5: Extract data from result set
      System.out.println("Table\n");
      System.out.println("ID\t   Outlook\t   Temperature\t    Humidity\t      Windy     \tClass");

      while(rs.next()){
         //Retrieve by column name
         for(int i=0;i<5;++i){
             x[countU][i]=RandomInt(64);
             y[countU][i]=RandomInt(64);
             X[countU][i]=g.pow(x[countU][i]);
             Y[countU][i]=g.pow(y[countU][i]);
             Xsum[i]= Xsum[i].multiply(X[countU][i]);
             Ysum[i]= Ysum[i].multiply(Y[countU][i]);
         }
               
         int id  = rs.getInt("id");
         tableDB[countU][0]=outlook[countU] = rs.getString("Outlook");
         tableDB[countU][1]=temperature[countU] = rs.getString("Temperature");
         tableDB[countU][2]=humidity[countU] = rs.getString("Humidity");
         tableDB[countU][3]=windy[countU] = rs.getString("Windy");
         tableDB[countU][4]=class1[countU] = rs.getString("Class");
         System.out.print(id+"\t\t"+outlook[countU]+"\t\t"+temperature[countU]+"\t\t"+humidity[countU]+"\t\t"+windy[countU]+"\t\t"+class1[countU]+countU);
         System.out.println();
         ++countU;

      }
      //STEP 6: Clean-up environment
      rs.close();
   //}
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
//close database
   NumReC= countU; //NumReC= Number Records = 14 in this case 
   
   
   System.out.println("Goodbye!"+NumReC);
   System.out.println("Menu:\nOutlook: Sunny=S Overcast=O Rain=R\tTemperature: Hot=H Mild=M Cool=C\n");
   System.out.println("Humidity: Peak=P Normal=N\t\tWindy: True=T False=F\n\nYour input should belong to one of these classes.\n");
   System.out.println("class1: Play=P   class2:Not Play=NP");
   Scanner scr=new Scanner(System.in);
    System.out.println("\nEnter your input: example. t={rain,hot,peak,false} input will be R,H,P,F"); 
    String ch;
    String S[]= new String[9];
    for(int i=0;i<4;++i){
        System.out.println("Nhap gia trị "+(i+1));
        S[i]=scr.nextLine();
    }
    
   
    int d1[]=Count_classVL(class1,"P",NumReC);  
    DTBplay= Count_D(tableDB,S,"P",5,NumReC);  
    DTBnoplay= Count_D(tableDB,S,"N",5,NumReC); 
    m0= EcryptionDM(DTBplay,Xsum,y,g,5,NumReC);
    h0= EcryptionDH(DTBplay,Ysum,x,g,5,NumReC);
    CountDPlay = DecryptionHM(m0,h0,g,5,NumReC);
    
    m1= EcryptionDM(DTBnoplay,Xsum,y,g,5,NumReC);
    h1= EcryptionDH(DTBnoplay,Ysum,x,g,5,NumReC);
    CountDnoPlay = DecryptionHM(m1,h1,g,5,NumReC);
    
    for(int i=0;i<14;++i)
    {
        for(int j=0;j<5;++j)
        {
            //System.out.println("h[ "+i+j+ "] "+DTBplay[i][j] );
            System.out.print("\td "+DTBplay[i][j] );
        }
        System.out.println();
    }
    
    //--------------------

    NumPlay= (double) CountDPlay[4];
    NumNoPlay= (double) CountDnoPlay[4];
    pp= (double) NumPlay/NumReC;
    npp=(double) NumNoPlay/NumReC;
   
    for(int i=0;i<4;++i){
        
        
        prob[i][0]=CountDPlay[i]/NumPlay;
        prob[i][1]=CountDnoPlay[i]/NumNoPlay;
        System.out.println(" count99="+i + "  ky tu99 =" + CountDPlay[i] + "  prob99[" + i + "][0]=" + prob1[i][0] + "  prob99[i][1]=" + prob1[i][1] );
       
        
    }

    cal_N(1);
    cal_N(2);

    double pt=play_N+notplay_N;
    System.out.println("play_N+notplay_N :" + play_N + "+" + notplay_N);
    System.out.println( "pt =" + pt );
    double prob_of_play=0;
    double prob_of_noplay=0;

    prob_of_play=play_N/pt;
    prob_of_noplay=notplay_N/pt;

    System.out.println("\nProbability of play "+prob_of_play);
    System.out.println("\nProbability of NO play "+prob_of_noplay );


    if(prob_of_play>prob_of_noplay)
    System.out.println("\nThe new tuple classified under \"PLAY\" category.Hence there will be play!!!");
    else
    System.out.println("\nThe new tuple classified under \"NO PLAY\" category.Hence there will be NO play.");

}

static BigInteger [][] EcryptionDM(int d[][],BigInteger Xsum[],int y[][],BigInteger g,int cl,int n){
    BigInteger[][] m= new BigInteger [n][cl];
    for(int i=0;i<5;++i){
        for(int j=0;j<14;++j){
                BigInteger G = g.pow(d[j][i]);
                BigInteger M = Xsum[i].pow(y[j][i]);
                m[j][i]=G.multiply(M);
               //System.out.println("\nd1"+d1[i]+M);
      //        System.out.println("\n----\ngg " +g +" \nXsum "+Xsum[i]+"\nm[i] "+m[j][i]);
            } 
    }
    return m;
}

static BigInteger [][] EcryptionDH(int d[][],BigInteger Ysum[],int x[][],BigInteger g,int cl,int n){
    BigInteger[][] h= new BigInteger [n][cl];
    for(int i=0;i<cl;++i){
        for(int j=0;j<14;++j){

              //  h[i]=Ysum[4].pow(x[i][4]);
                h[j][i]=Ysum[i].pow(x[j][i]);//System.out.println("\nd1"+d1[i]+M);
          //    System.out.println("\n----\nhhhh " +h[j][i]);
            } 
    }
    return h;
}

public static int [] DecryptionHM (BigInteger m[][],BigInteger h[][] ,BigInteger g,int cl,int n){
    BigInteger[] r = new BigInteger[cl];
    BigInteger[] r1 = new BigInteger[cl];
    r= DefaultONE(r,cl);
    r1 = DefaultONE(r1,cl);
    int[] KG= new int [cl];
    for(int i=0;i<cl;++i)
    {
        for(int j=0;j<n;++j)
        {
           r[i]=r[i].multiply(m[j][i]);
           r1[i]=r1[i].multiply(h[j][i]);
        }
        r[i]=r[i].divide(r1[i]);
      //  System.out.println("\nKET QUa TINH "+r[i]+"\n"+g.pow(3)+"\n"+g.pow(2)+"\n"+g.pow(3)+"\n"+g.pow(6));
        for(int j=0;j<n;++j)
        {    
            if (r[i].equals(g.pow(j))) 
            {
                KG[i]=j;
          //      System.out.println("KG "+KG[i]+"i "+i+"j"+j);
                break;
            }
        }
    }
    return KG;
}


static int [] Count_classVL(String a[],String b,int n)
{
    int d1[]= new int [n];
    for(int i=0;i<n;++i)
    {
    if(a[i].equals(b))
    { 
        d1[i]=1;
    }
    else d1[i]=0;
   // System.out.println("\ndi "+ d[i][0]);
    }
    return d1;
}

static int [][] Count_D(String tableDB[][],String stadin[],String b,int column,int num){
    int d[][]= new int [num][column];
    
    for(int j=0;j<num;++j)
    {
        for(int i=0;i<column-1;++i)
        {
            
            if(tableDB[j][i].equals(stadin[i]) && tableDB[j][column-1].equals(b))
            {
                d[j][i]=1;
            }
            else d[j][i]=0;
            if(tableDB[j][column-1].equals(b))
                {
                d[j][column-1]=1;
                }
            else d[j][column-1]=0;
        }
    }
    return d;
}

static void cal_N(int a)
{
  if(a==1)
  {
    for(int i=0;i<4;++i)
    play_N*=prob[i][0];
  System.out.println("\n11Value of N of play \n"+play_N);
    play_N*=pp;
  System.out.println("\nValue of N of play \n"+play_N);
  }
  else
  {
    for(int i=0;i<4;++i)
    notplay_N*=prob[i][1];
  System.out.println("\n11Value of N of play \n"+play_N);
    notplay_N*=npp;
  System.out.println("\nValue of N of No play \n"+notplay_N);
  }
}

/*static double cal_play_prob(String ch){
    double prob=0;
    double count=0;


    if(flag==0){
        
        for(int i=0;i<NumReC;++i){
    
            if(outlook[i].equals(ch) && class1[i].equals("P"))
            {
                ++count;
                d[i][flag]=1;
            }
            else d[i][flag]=0;
   // System.out.println("\ndi "+ d[i][0]);
        }

        prob=count/NumPlay;

    

        flag=1;
    }
    else if(flag==1){
        for(int i=0;i<NumReC;++i)
        if(temperature[i].equals(ch) && class1[i].equals("P"))
        ++count;

        prob=count/NumPlay;

        flag=2;
    }
    else if(flag==2){       
        for(int i=0;i<NumReC;++i)
        if(humidity[i].equals(ch) && class1[i].equals("P"))
        ++count;
        prob=count/NumPlay;

        flag=3;
    }
    else
    if(flag==3){
        for(int i=0;i<NumReC;++i)
        if(windy[i].equals(ch) && class1[i].equals("P"))
        ++count;

        prob=count/NumPlay;
    }
    return prob;
}*/






/*static double cal_noplay_prob(String ch){
    
    double prob=0;
    double count=0;

    if(flag1==0)
    {
        for(int i=0;i<NumReC;++i)
        if(outlook[i].equals(ch) && class1[i].equals("N"))
        ++count;

        prob=count/NumNoPlay;

        flag1=1;
    }
    else if(flag1==1){
        for(int i=0;i<NumReC;++i)
        if(temperature[i].equals(ch) && class1[i].equals("N"))
        ++count;

        prob=count/NumNoPlay;

        flag1=2;
    }
    else if(flag1==2){
        
        for(int i=0;i<NumReC;++i)
        if(humidity[i].equals(ch) && class1[i].equals("N"))
        ++count;

        prob=count/NumNoPlay;

        flag1=3;
    }
    else
    if(flag1==3){
        for(int i=0;i<NumReC;++i)
        if(windy[i].equals(ch) && class1[i].equals("N"))
        ++count;

        prob=count/NumNoPlay;
    }
return prob;
} */

public static int RandomInt (int n){
         Random rnd1 = new Random();   
         int rnd = rnd1.nextInt(n) +1 ;
         
         return rnd;
         
     }

public static BigInteger RandomBigInt (int a){
         //int a[][]= new int[n][];
         BigInteger b;
         do{
             b = new BigInteger(a, new Random());
         }while ( b.compareTo(new BigInteger("2")) < 0);
         return b;
     }

public static BigInteger[] DefaultONE (BigInteger a[],int n){
    a = new BigInteger[n];
    for(int i=0;i<n;++i){
        a[i]= new BigInteger("1");
    }
    
    return a;
}

public static int Decryption (BigInteger m[],BigInteger h[] ,BigInteger g,int n){
    BigInteger r = new BigInteger("1");
    BigInteger r1 = new BigInteger("1");
    int i=0;
    for( i=0;i<n;++i)
    {
       r=r.multiply(m[i]);
       r1=r1.multiply(h[i]);
    }
    r=r.divide(r1);
    System.out.println("\nRRRRRRRRRRRRRRR "+r+"\n"+g.pow(9));
    for( i=0;i<n;++i)
    {  
        if (r.equals(g.pow(i))) 
        {
            break;
        }
    }
    return i;
}

}
