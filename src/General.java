public class General {
    static int population;  // numero di persone esistenti all'inizio della simulazione
    static int resources;   //quantità risorse inizialmente disponibili
    static int careCost;    //costo tampone

    //Aspetti sanitari
    static double infectivity = 0.5;  //probabilità (maggiore di 0) che un individuo sano venga infettato a seguito di un incontro con un contagiato asintomatico o sintomatico;
    static double symptomaticity = 0.3; //probabilità che un contagiato sviluppi sintomi
    static double lethality = 0.03;  //probabilità che un malato sintomatico muoia
    static int recoveryTime = 14;    //numero giorni trascorsi tra contagio e guarigione

    static int incubationPeriod = 7; //giorni di incubazione prima che l'individuo infettato diventi contagioso (cioè cambi colore)
}
