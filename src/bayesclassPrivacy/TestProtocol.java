/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bayesclassPrivacy;


import java.math.*;
import java.util.Random;

/**
 *
 * @author TAD
 */
public class TestProtocol {
    private final static BigInteger one = new BigInteger("1");
    
    
    static BigInteger P;
    static BigInteger M;
    static BigInteger r,r1,r2;
    static BigInteger m1[]=new BigInteger[4];
    static BigInteger m[]=new BigInteger[4];
    static BigInteger h[]=new BigInteger[4];
 
    static BigInteger x[]=new BigInteger[4];
    static BigInteger y[]=new BigInteger[4];
    
    static BigInteger X[]=new BigInteger[4];
    static BigInteger Y[]=new BigInteger[4];
    static BigInteger Xsum=new BigInteger("1");
    static BigInteger Ysum=new BigInteger("1");
    static BigInteger G;
static int d[]={1,1,0,1};
    
    public static void main(String[] args) {
        

        x[1]=new BigInteger("58");
        x[2]=new BigInteger("58");
        x[3]=new BigInteger("58");
        y[1]=new BigInteger("36");
        y[2]=new BigInteger("55");
        y[3]=new BigInteger("58");
        //d[1]=new BigInteger("1");
        //d[2]=new BigInteger("0");
        //d[3]=new BigInteger("1");
        BigInteger g = new BigInteger("23");
        Random rnd1 = new Random();
        P = BigInteger.probablePrime(8,rnd1);
         P = new BigInteger("97");
        System.out.println("g= "+g+"\tP"+P);
        for(int i=1;i<4;++i)
        {
     
            X[i] = g.modPow(x[i],P);
            Y[i] = g.modPow(y[i],P);
            Xsum= Xsum.multiply(X[i]);
            Ysum= Ysum.multiply(Y[i]);
            System.out.println("\n----\nx " +x[i] +" \ny "+y[i]+"\nX "+X[i]+"\nY "+Y[i]+"\nXsum "+Xsum+"\nYsum= "+Ysum);
        }
        Xsum= Xsum.mod(P);
        Ysum= Ysum.mod(P);
        for(int i=1;i<4;++i){
           // G = g.pow(d[i]);
            //M = Xsum.modPow(y[i],P);
            m[i]=((g.pow(d[i])).multiply(Xsum.modPow(y[i],P))).mod(P);
           
            h[i]=Ysum.modPow(x[i],P);
            System.out.println("\n----\nG " +G +" \nM "+M+"\nm[i] "+m[i]+"\nh[i] "+h[i]);
        }
    //BigInteger result = new BigInteger(n.bitLength(), new Random());
        r1= (m[1].multiply(m[2].multiply(m[3])) ).mod(P);
        r2= (h[1].multiply(h[2].multiply(h[3])) ).mod(P);
        r2=r2.modInverse(P);
        r= (r1.multiply(r2)).mod(P);
       
        System.out.println(" \nrRRR "+ r+ " \nr "+ r.mod(P) +"ket qua aa"+ g.pow(2).mod(P) + "ket qua bbb"+g.modPow(new BigInteger("2"),P) );

                
    }
    
}
