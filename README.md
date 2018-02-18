# Alarming
Simple Android Alarm Clock app that uses RSA encryption to dismiss alarm

When an alarm is triggered, the user is given a public key in the form (e,n) and must solve for the private ke (d,n) to dismiss the alarm.  

 RSA Theorem (RSA) (taken from Reading, Discovering and Writing Proofs Version 1.0; Faculty of Mathematics University of Waterloo)
 
Let p and q be two distinct primes. If we define the following variables
1. n = pq and φ(n) = (p − 1)(q − 1), and
2. e is a positive integer, 2 ≤ e < φ(n), such that gcd(e, φ(n)) = 1, and
3. d is a positive integer, 2 ≤ d < φ(n), such that ed ≡ 1 (mod φ(n)), and
4. M is an integer such that 0 ≤ M < n, and
5. C is an integer, 0 ≤ C < n, such that C ≡ Me
(mod n), and
6. R is an integer, 0 ≤ R < n, such that R ≡ C
d
(mod n),
then R = M.
