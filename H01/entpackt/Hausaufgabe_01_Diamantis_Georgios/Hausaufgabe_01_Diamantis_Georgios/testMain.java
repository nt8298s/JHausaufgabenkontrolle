import java.util.Arrays;

/**
 * Test-Main zur Klasse "KomplexeZahl"
 * 
 * @author Georgios Diamantis, Steffen Carstensen, Manuel Habbel, Gruppe 33
 *
 */
public class testMain {
	public static void main(String[] args) {
		KomplexeZahl z = new KomplexeZahl(0, 0); // z := 0
		System.out.println("z = " + z);
		z = new KomplexeZahl(1, 0); // z := 1
		System.out.println("z = " + z);
		z = new KomplexeZahl(0, 1); // z := i
		System.out.println("z = " + z);
		z = new KomplexeZahl(-4, 0); // z := -4
		System.out.println("z = " + z);
		KomplexeZahl[] wurzeln = z.getWurzel(); // => 2i und -2i
		System.out.println("sqrt(z) = " + Arrays.toString(wurzeln));
		z = new KomplexeZahl(1, 1); // z := 1+i
		System.out.println("z = " + z);
		double betrag = z.getBetrag();
		System.out.println("|z| = " + betrag); // => sqrt(2) = 1.41...
		KomplexeZahl z2 = new KomplexeZahl(2, 1); // z2 := 2+i
		System.out.println("z2 = " + z2);
		z.addiere(z2);
		System.out.println("z nach Addition von z2 = " + z);
		z.multipliziere(z2);
		System.out.println("z nach Multiplikation von z2 = " + z);
		z2 = z.getProdukt(new KomplexeZahl(-1, 0)); // z2 := -z
		System.out.println("z2 = " + z2);
		KomplexeZahl summe = z.getSumme(z2); // z := z - z2 = 0
		System.out.println("summe = " + summe);
	}
}