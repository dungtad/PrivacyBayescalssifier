
package bayesclassPrivacy;


import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author TAD
 */
public class Crypto {
    
    public static BigInteger [][] EcryptionDM(int d[][],BigInteger Xsum[],int y[][],BigInteger g,int cl,int n){
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

public static BigInteger [][] EcryptionDH(int d[][],BigInteger Ysum[],int x[][],BigInteger g,int cl,int n){
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
  
public static int RandomInt (double n){
         int b= (int) Math.pow(2, n); 
         //System.out.println("mu n b n"+ b);
         Random rnd1 = new Random();   
         int rnd = rnd1.nextInt(b) +1 ;
         
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


}
