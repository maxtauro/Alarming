package maxtauro.alarming;

import java.util.Random;

/**
 * Created by maxtauro on 2018-02-13.
 */

public class RSAScheme {
    public int n;
    public int p;
    public int q;
    public int phi_n;
    public int encryption_key;
    public int decryption_key;

    public RSAScheme(){
        p = generateRandomPrime();
        q = generateRandomPrime();

        while(p == q){ //ensure p and q are distinct
            q = generateRandomPrime();
        }

        n = p*q;
        phi_n = (p-1)*(q-1);

        encryption_key = setEncryptionKey(phi_n);
        decryption_key = setDecryptionKey(encryption_key, phi_n);

    }

    public int generateRandomPrime(){
        int num;
        Random randomNum = new Random(); //generate a random number
        num = randomNum.nextInt(1000) + 1;

        while(!isPrime(num)){
            num = randomNum.nextInt(1000) + 1;
        }

        return num;
    }

    public String rsaAsText(){
        StringBuilder sb = new StringBuilder();
        sb.append("Given the public encryption key (e,n) = (");
        sb.append(String.valueOf(this.encryption_key) + "," + String.valueOf(this.n) +")\n");
        sb.append("Find the corresponding decryption key (d,n) to dimiss the alarm");
        return sb.toString();
    }

    private boolean isPrime(int n){
        if (n <= 3 || n%2 == 0) return n == 2 || n == 3;

        int divisor = 3;
        while(divisor <= Math.sqrt(n) && n%divisor != 0){
            divisor+=2;
        }

        return n%divisor != 0;
    }

    private int setEncryptionKey(int phi_n){
        Random randomNum = new Random();
        int e = randomNum.nextInt(phi_n-2) + 2;
        int gd = gcd(e,phi_n);

        while(gd != 1){
            e = randomNum.nextInt(phi_n-2) + 2;
            gd = gcd(e,phi_n);
        }
        return e;
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

    private int gcd(int a, int b){ //using EEA
        while (b != 0) {
            int t = a;
            a = b;
            b = t % b;
        }
        return a;
    }

    private int solveLinearCongruence(int _e, int _phi_n){

        Row row1 = new Row(1,0,_phi_n,1);
        Row row2 = new Row(0,1,_e,1);

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
