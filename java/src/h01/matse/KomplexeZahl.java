package h01.matse;

/**
 * 
 * Klasse einer Komplexen Zahl mit double-Werten.
 * 
 * @author Georgios Diamantis, Steffen Carstensen, Manuel Habbel, Gruppe 33
 * 
 */
public class KomplexeZahl {
	private double real, imag, r, phi;

	/**
	 * Erzeugt ein Komplexe Zahl mit double Werten.
	 * 
	 * @param real Der Real-Teil der Komplexen Zahl.
	 * @param imag Der Imaginaer-Teil der Komplexen Zahl
	 */
	public KomplexeZahl(double real, double imag) {
		this.real = real;
		this.imag = imag;
		setR();
		setPhi();
	}

	/**
	 * Gibt den Imaginaer-Teil der Komplexen Zahl zurueck.
	 * 
	 * @return Gibt den Imaginaer-Teil als Double zurueck.
	 */
	public double getImag() {
		return imag;
	}

	/**
	 * Gibt den Real-Teil der Komplexen Zahl zurueck.
	 * 
	 * @return Gibt den Real-Teil als Double zurueck.
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Berechnet und setzt den Radius der Komplexen Zahl.
	 */
	private void setR() {
		this.r = Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imag, 2));
	}

	/**
	 * Berechnet und setzt den Winkel(PHI) der Komplexen Zahl.
	 */
	private void setPhi() {
		if (this.real == 0 && this.imag < 0) {
			this.phi = (3 / 2) * Math.PI;
		} else if (this.real == 0 && this.imag > 0) {
			this.phi = Math.PI / 2;
		} else if (this.imag == 0 && this.real < 0) {
			this.phi = Math.PI;
		} else if (this.imag == 0 && this.real >= 0) {
			this.phi = 0;
		} else if (this.real < 0) {
			this.phi = Math.atan(this.imag / this.real) + Math.PI;
		} else if (this.imag > 0) {
			this.phi = Math.atan(this.imag / this.real);
		} else {
			this.phi = Math.atan(this.imag / this.real) + 2 * Math.PI;
		}
	}

	/**
	 * Addiert die Komplexe Zahl z auf die Komplexe Zahl.
	 * 
	 * @param z Komplexe Zahl die addiert werden soll.
	 */
	public void addiere(KomplexeZahl z) {
		this.real += z.real;
		this.imag += z.imag;
		setR();
		setPhi();
	}

	/**
	 * Multipliziert die Komplexe Zahl z mit der Komplexen Zahl.
	 * 
	 * @param z Komplexe Zahl die multipliziert werden soll.
	 */
	public void multipliziere(KomplexeZahl z) {
		double temp = (this.real * z.imag) + (this.imag * z.real);
		this.real = (this.real * z.real) - (this.imag * z.imag);
		this.imag = temp;
		setR();
		setPhi();
	}

	/**
	 * Gibt den Betrag der Komplexen Zahl zurueck.
	 * 
	 * @return Gibt den Betrag als Double zurueck.
	 */
	public double getBetrag() {
		return this.r;
	}

	/**
	 * Gibt die Komplexe Zahl als String zurueck.
	 * 
	 * @return Gibt die Komplexe Zahl als String zurueck
	 */
	public String toString() {
		if (this.imag == 0) {
			return this.real + "";
		} else {
			if (this.imag < 0 && this.imag != 1) {
				return this.real + " - " + this.imag * (-1) + "i";
			}
			if (this.imag < 0 && this.imag == 1) {
				return this.real + " - i";
			}
			if (this.imag > 0 && this.imag != 1) {
				return this.real + " + " + this.imag + "i";
			} else if (this.imag > 0 && this.imag == 1) {
				return this.real + " + i";
			}
		}
		return null;
	}

	/**
	 * Berechnet die Wurzel einer Komplexen Zahl
	 * 
	 * @return Gibt die Wurzel in Form eines Arrays zurueck.
	 */
	public KomplexeZahl[] getWurzel() {
		KomplexeZahl[] erg = new KomplexeZahl[2];
		double ergReal = Math.sqrt(r) * Math.cos(phi / 2);
		double ergImag = Math.sqrt(r) * Math.sin(phi / 2);
		erg[0] = new KomplexeZahl(ergReal, ergImag);

		ergReal *= -1;
		ergImag *= -1;
		erg[1] = new KomplexeZahl(ergReal, ergImag);

		return erg;
	}

	/**
	 * Berechet die Addition von 2 Komplexen Zahlen.
	 * 
	 * @param z Komplexe Zahl die addiert werden soll.
	 * @return Gibt das Ergebnis einer Addition als neues Objekt zurueck.
	 */
	public KomplexeZahl getSumme(KomplexeZahl z) {
		KomplexeZahl erg = new KomplexeZahl(this.real, this.imag);
		erg.addiere(z);
		return erg;
	}

	/**
	 * Berechet die Multiplikation von 2 Komplexen Zahlen.
	 * 
	 * @param z Komplexe Zahl die multipliziert werden soll.
	 * @return Gibt das Ergebnis einer Multiplikation als neues Objekt zurueck.
	 */
	public KomplexeZahl getProdukt(KomplexeZahl z) {
		KomplexeZahl erg = new KomplexeZahl(this.real, this.imag);
		erg.multipliziere(z);
		return erg;
	}
}
