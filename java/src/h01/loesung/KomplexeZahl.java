package h01.loesung;

/**
 * Jedes Objekt dieser Klasse stellt eine komplexe Zahl dar, wie man sie
 * aus der Mathematik kennt. Sie besteht aus einem Paar reeller Zahlen,
 * dem sogenannten Realteil und dem sogenannten Imaginaerteil.
 * Laut Aufgabenstellung (java-h01 im WS 2020) werden hier auch die
 * wichtigsten Operationen mit komplexen Zahlen implementiert.
 * Autor: Juergen Dietel, ITC/CSE/MATSE, MI 02.10.2019 - MI 14.10.2020
 */
public class KomplexeZahl {

  /** Realteil */
  private double x;
  /** Imaginaerteil */
  private double y;

  /** die komplexe Null als symbolische Konstante  (war nicht verlangt!)*/
  private final static KomplexeZahl NULL = new KomplexeZahl(0, 0);
  /** die komplexe Eins als symbolische Konstante (war nicht verlangt!)*/
  private final static KomplexeZahl EINS = new KomplexeZahl(1, 0);
  /** die negative komplexe Eins als symb. Konst. (war nicht verlangt!)*/
  private final static KomplexeZahl MINUSEINS = new KomplexeZahl(-1, 0);

  /**
   * Konstruktor aus Real- und Imaginaerteil
   *
   * @param  x  Realteil      der zu erzeugenden komplexen Zahl z := x+y*i
   * @param  y  Imaginaerteil der zu erzeugenden komplexen Zahl z := x+y*i
   */
  public KomplexeZahl(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * den Realteil der aktuellen komplexen Zahl liefern
   *
   * @return  Realteil der aktuellen komplexen Zahl
   */
  public double getX() {
    return this.x;
  }

  /**
   * den Imaginaerteil der aktuellen komplexen Zahl liefern
   *
   * @return  Imaginaerteil der aktuellen komplexen Zahl
   */
  public double getY() {
    return this.y;
  }

  /**
   * die uebergebene komplexe Zahl z zur aktuellen hinzuaddieren:<br>
   *   aktuelle Zahl += z
   *
   * @param  z  zu addierende komplexe Zahl
   */
  public void addiere(KomplexeZahl z) {
    this.x += z.getX();
    this.y += z.getY();
  }

  /**
   * die uebergebene komplexe Zahl z auf die aktuelle draufmultiplizieren:<br>
   *   aktuelle Zahl *= z
   *
   * @param  z  dazuzumultiplizierende komplexe Zahl
   */
  public void multipliziere(KomplexeZahl z) {
    // nach normalen reellen Rechenregeln gilt, wenn man i*i=-1 bedenkt:
    // this = x  + i*y  \ => this*z = (x  + i*y) * (x2 + i*y2)
    // z    = x2 + i*y2 /           = (x*x2 - y*y2) + i*(x*y2 + y*x2)
    double x  = this.getX();  // Real- und Imaginaerteil lokal speichern,
    double y  = this.getY();  // um zumindest den alten Realteil zweimal
    double x2 = z.getX();     // nutzen zu koennen, der im folgenden ja
    double y2 = z.getY();     // als erstes ueberschrieben wird:
    this.x = x*x2 - y*y2;     // this *= z
    this.y = x*y2 + y*x2;
  }

  /**
   * den Betrag der aktuellen komplexen Zahl liefern
   *
   * @return  Betrag der aktuellen komplexen Zahl
   */
  public double getBetrag() {
    double x = Math.abs(this.getX());          // => |z| = sqrt(x*x+y*y)
    double y = Math.abs(this.getY());
    // eine theoretisch aequivalente Formel dazu benutzen, die weniger
    // anfaellig fuer Rundungsfehler ist (war nicht verlangt!):
    if (x == 0.0 && y == 0.0)
      return 0.0;
    else if (x < y)
      return y*Math.sqrt(1 + (x/y)*(x/y));
    else
      return x*Math.sqrt(1 + (y/x)*(y/x));
  }

  /**
   * Die aktuelle komplexe Zahl als String liefern.
   * Dazu wird die Darstellung z = x + y i gewaehlt.
   *
   * @return  die Stringdarstellung der aktuellen komplexen Zahl
   */
  public String toString() {
    // Optimierung: Falls der Imaginaerteil Null ist, wird der Teil
    // `+ y i' weggelassen, so dass die komplexe Zahl dann zu einer
    // reellen Zahl entartet; analog beim Realteil; Ausnahme z==0.
    // Ausserdem wird z. B. `1i' zu `i' und `+ -4i' zu `-4i' optimiert:
    String ergebnis  = "";
    double y         = this.getY();  // Imaginaerteil kopieren
    if (x != 0 || y == 0)            // Realteil da oder komplexe Null?
      ergebnis += this.getX();       // => Realteil eintragen
    String plusminus = " + ";        // Addition oder Subtraktion?
    if (ergebnis.isEmpty())          // kein Realteil da?
      plusminus = "";                // => kein Operator noetig
    if (y < 0.0 && x != 0) {         // Imaginaerteil negativ und Realteil da?
      plusminus =  " - ";            // y als zu subtrahieren eintragen
      y         = -y;                // und daher scheinbar positiv machen
    }
    if (y == 1.0)                       // 1i?
      ergebnis += plusminus + "i";      // --> "i"
    else if (y != 0.0)                  // Imaginaerteil nicht Null?
      ergebnis += plusminus + y + "i";  // --> "y i"
    return ergebnis;
  }

  /**
   * herausfinden, ob die aktuelle komplexe Zahl und die uebergebene z
   * identisch sind (war nicht verlangt im Aufgabentext!):
   *
   * @param   z  zu vergleichende komplexe Zahl
   * @return     true, falls identische Zahlen vorliegen, sonst false
   */
  public boolean equals(KomplexeZahl z) {
    return this.getX() == z.getX() && this.getY() == z.getY();
  }

  /** Hilfsfunktion zur Bestimmung des Vorzeichens einer double-Zahl */
  private static double sgn(double d) {
    return (d > 0) ? 1 : -1;
  }

  /**
   * Die beiden moeglichen Quadratwurzeln der aktuellen komplexen Zahl
   * liefern. Wenn sie Null ist, ist das Ergebnis nicht eindeutig und
   * soll daher aus zwei komplexen Nullen bestehen.
   *
   * @return  beide Quadratwurzeln einer komplexen Zahl als Array
   */
  public KomplexeZahl[] getWurzel() {
    KomplexeZahl[] ergebnis = new KomplexeZahl[2];
    if (this.equals(NULL))                // Sonderfall Wurzel aus Null?
      return new KomplexeZahl[] {NULL, NULL};
    // Hier wird jetzt erstmal die `Standardwurzel' bestimmt, die sich
    // am einfachsten aus den Polarkoordinaten der aktuellen komplexen
    // Zahl ergibt: z = r*exp(i*phi) => sqrt(z) = sqrt(r)*exp(i*phi/2)
    // = sqrt(r)*(cos(phi/2) + i*sin(phi/2)). Die andere Wurzel ist dann
    // wie im Reellen einfach die Negation der Standardwurzel. Laut
    // Wikipedia gibt es aber auch eine Formel ohne Polarkoordinaten:
    double       r      = this.getBetrag();
    KomplexeZahl sqrt_z = new KomplexeZahl(Math.sqrt((r+x)/2),
                                           Math.sqrt((r-x)/2)*sgn(y));
    ergebnis[0] = sqrt_z;                          // `Standardwurzel'
    ergebnis[1] = sqrt_z.getProdukt(MINUSEINS);    // negierte 1. Wurzel
    return ergebnis;
  }

  /**
   * die Summe aus aktueller komplexer Zahl und uebergebener liefern:<br>
   *   summe = aktuelle Zahl + z
   *
   * @param   z  zu addierende komplexe Zahl
   * @return     Summe aus aktueller komplexer Zahl und uebergebener
   */
  public KomplexeZahl getSumme(KomplexeZahl z) {
    KomplexeZahl summe = new KomplexeZahl(this.getX(), this.getY());
    summe.addiere(z);          // auf die andere Addition zurueckfuehren
    return summe;
  }

  /**
   * das Produkt aus aktueller komplexer Zahl und uebergebener liefern:<br>
   *   produkt = Multiplikator * Multiplikand = aktuelle Zahl * z
   *
   * @param  z   Multiplikand im zu bestimmenden komplexen Produkt
   * @return     Produkt aus aktueller komplexer Zahl und uebergebener
   */
  public KomplexeZahl getProdukt(KomplexeZahl z) {
    KomplexeZahl produkt = new KomplexeZahl(this.getX(), this.getY());
    produkt.multipliziere(z);     // um die Multiplikation nicht nochmal
    return produkt;               // implementieren zu muessen
  }
}
