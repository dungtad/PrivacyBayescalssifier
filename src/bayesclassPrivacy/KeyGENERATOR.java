/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bayesclassPrivacy;


import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author TAD
 */
public class KeyGENERATOR {
    private final static BigInteger one = new BigInteger("1");
    
    
    static BigInteger P;
    static BigInteger M;
    static BigInteger r,r1,r2;
    static BigInteger m1[]=new BigInteger[4];
    static BigInteger m[]=new BigInteger[4];
    static BigInteger h[]=new BigInteger[4];
    
    //static BigInteger x[]=new BigInteger[4];
    //static BigInteger y[]=new BigInteger[4];
    static int x[]={3,7,7};
    static int y[]={5,7,3};
    static int d[]={1,1,1};
    
    
    static BigInteger X[]=new BigInteger[4];
    static BigInteger Y[]=new BigInteger[4];
    static BigInteger Xsum=new BigInteger("1");
    static BigInteger Ysum=new BigInteger("1");
    static BigInteger G;
    //static BigInteger d[]=new BigInteger[4];
    /*
    public BigInteger KeyGENERATOR() {
    BigInteger E;
    do {
    E = new BigInteger(8, new Random());
    } while (E.compareTo(BigInteger.ZERO)>0);
    //BigInteger result = new BigInteger(n.bitLength(), new Random());
    return E;
    }
    */
    public static int[] ast (int b[]){
        int[] y = new int[99];
        for(int i=0;i<3;++i){
            System.out.println("\n----\nx " +b[i]);
            y[i]=b[i]; 
        }
        return y;
    }
     public static int RandomInt (int n){
         Random rnd1 = new Random();   
         int rnd = rnd1.nextInt(n) +1 ;
         return rnd;
     }
     public int[] RandomArray (int n){
         int a[]= new int[n];
         for(int i=1;i<n;++i)
        {
        a[i] = RandomInt(255);
        //System.out.println("aa = "+a[i]);
        }
        return a;
     }
    public static void main(String[] args) {
        
        //BigInteger x[]=new BigInteger[3];
       //BigInteger g = new BigInteger(9, new Random());

        //int j[]= new int[255];
     
       
        BigInteger g = new BigInteger("9");
        Random rnd1 = new Random();
        P = BigInteger.probablePrime(10,rnd1);
        P = new BigInteger("17");
        System.out.println("g= "+g+"\tP"+P);
        for(int i=0;i<3;++i)
        {
            //x[i] = new BigInteger(9, new Random());
            //y[i] = new BigInteger(9, new Random());
            X[i] = g.pow(x[i]).mod(P);
            Y[i] = g.pow(y[i]).mod(P);
            Xsum= Xsum.multiply(X[i]);
            Ysum= Ysum.multiply(Y[i]);
            System.out.println("\n----\nx " +x[i] +" \ny "+y[i]+"\nX "+X[i]+"\nY "+Y[i]+"\nXsum "+Xsum+"\nYsum= "+Ysum);
        }
        Xsum= Xsum.mod(P);
        Ysum= Ysum.mod(P);
        for(int i=0;i<3;++i){
            G = g.pow(d[i]);
            M = Xsum.pow(y[i]).mod(P);
            m[i]=(G.multiply(M)).mod(P) ;

            h[i]=Ysum.pow(x[i]).mod(P);
            System.out.println("\n----\nG " +G +" \nM "+M+"\nm[i] "+m[i]+"\nh[i] "+h[i]);
        }
    //BigInteger result = new BigInteger(n.bitLength(), new Random());
        r1= ( m[0].multiply(m[1].multiply(m[2])) ).mod(P) ;
        r2= ( h[0].multiply(h[1].multiply(h[2])) ).mod(P) ;
        r2=r2.modInverse(P);
        r= (r1.multiply(r2)).mod(P);

        System.out.println("\n-ketqua---\nr1 "+ r1 +"\nr2 "+ r2+  " \nr "+ r +"ket qua"+ g.pow(3).mod(P) );
       
        
         BigInteger a1=new BigInteger("8");
         BigInteger a2=new BigInteger("4294967296");
         BigInteger b1= new BigInteger("4294967296");
         BigInteger c = (a1.multiply(a2)).mod(P);
         BigInteger d = b1.mod(P);
         b1=d.modInverse(P);
          BigInteger cc = (c.multiply(b1)).mod(P);
          System.out.println("\n-ketquaccc" + cc);
                
    }
    
}
