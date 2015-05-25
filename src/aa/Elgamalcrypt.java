/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aa;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
/**
 *
 * @author TAD
 */
public class Elgamalcrypt {
    
    public static void main(String[] args) {
        double x[]={3,5,4};
        double y[]={8,4,4};
        double X[]=new double[3];
        double Y[]=new double[3];
        double Xsum=1;
        double Ysum=1;
        double d[]={1,0,1};
        double G;
        double M;
        double m[]=new double[10];
        double h[]=new double[10];
        double g=9;
        double r;
        
        
        for(int i=0;i<3;++i)
        {
            X[i]=Math.pow(g,x[i]);
            Y[i]=Math.pow(g,y[i]);
            Xsum*=X[i];
            Ysum*=Y[i];
            System.out.println("\n----\nx " +x[i] +" \ny "+y[i]+"\nX "+X[i]+"\nY "+Y[i]+"\nXsum "+Xsum+"\nYsum= "+Ysum);
        }
        for(int i=0;i<3;++i)
        {
            
            
            G=Math.pow(g,d[i]);
            M=Math.pow(Xsum,y[i]);
            m[i]=(G*M);
            h[i]=Math.pow(Ysum,x[i]);
           System.out.println("\n----\nx " +G +" \ny "+M+"\nX "+m[i]+"\nY "+h[i]);
            /*System.out.println("0+++++++++c= " + X[i] );
            System.out.println("m["+i+"]= " + m[i] );
            System.out.println("x sum  " + Xsum);
            System.out.println("y sum= " + Ysum);
            System.out.println("abc= " + Math.pow(9,13)); */
        }
        
        r= ((m[0]*m[1]*m[2])/(h[0]*h[1]*h[2])) ;
        
        System.out.println("r= " +r + " mod= " +(g*g));
       /* BigInteger g1 = new BigInteger("9");
        BigInteger k = new BigInteger("13");
        BigInteger p = new BigInteger("97");
        BigInteger c1 = g1.modPow(k, p);
        System.out.println("ccc1= " + c1);
        BigInteger aa[][] = new BigInteger[1000000][5];
        aa[10][3] = new BigInteger("63333234234234235235234234");
        aa[90000][4] = new BigInteger("5235234234");
    //    aa[1] = 
        System.out.println("ccc1= " + aa[10][3]+ "qưe" + aa[90000][4]);
      System.out.println("ccc1= ");*/ 
    }
}
