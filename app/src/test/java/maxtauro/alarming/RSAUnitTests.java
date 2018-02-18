package maxtauro.alarming;

import org.junit.Test;

import static org.junit.Assert.*;

public class RSAUnitTests {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(gcd(7,12),1);
        assertEquals(gcd(20680, 200232),8);
        assertEquals(gcd(122740, 76337),1);
        assertEquals(gcd(10,10),10);
    }

    @Test
    public void linearCongruenceTest(){
        assertEquals(setDecryptionKey(146867,460720), 379403);
    }

    private int gcd(int a, int b){ //using EEA
        while (b != 0) {
            int t = a;
            a = b;
            b = t % b;
        }
        return a;
    }

    private int setDecryptionKey(int e, int phi_n){
        int d_0 =solveLinearCongruence(e,phi_n);
        int d = d_0;

        while (d > phi_n){
            d-=phi_n;
        }

        while (d < 2){
            d+=phi_n;
        }
        return d;
    }

    private int solveLinearCongruence(int _e, int _phi_n){

        Row row1 = new Row(1,0,_phi_n,1);
        Row row2 = new Row(0,1,_e,1);
        Row currRow = row1;

        int curr_a;
        int curr_b;
        int curr_r = 2;
        int curr_q;

        while(curr_r > 0){
            curr_q = row1.r/row2.r;
            curr_a = row1.a-curr_q*row2.a;
            curr_b = row1.b-curr_q*row2.b;
            curr_r = row1.r - curr_q*row2.r;
            row1 = new Row(row2.a,row2.b,row2.r, row2.q);
            row2 = new Row(curr_a,curr_b,curr_r,curr_q);
            System.out.println(String.valueOf(curr_a) + "    "+ String.valueOf(curr_b) + "     " + String.valueOf(curr_r) + "     " + String.valueOf(curr_r));

        }
        return row1.b;
    }

    class Row{
        int a;
        int b;
        int r;
        int q;

        Row(int a, int b, int r, int q){
            this.a = a;
            this.b = b;
            this.r = r;
            this.q = q;
        }
    }
}