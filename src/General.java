public class General {
    static int initPopulation = 100;
    static int population = initPopulation;  // numero di persone esistenti all'inizio della simulazione
    static int swabCost = 1;    //costo tampone
    static int careCost = 3*swabCost;    //costo giornaliero cure mediche per i malati (rossi)

    //Aspetti sanitari
    static double infectivity = 0.5;  //probabilità (maggiore di 0) che un individuo sano venga infettato a seguito di un incontro con un contagiato asintomatico o sintomatico;
    static double symptomaticity = 0.3; //probabilità che un contagiato sviluppi sintomi
    static double lethality = 0.03;  //probabilità che un malato sintomatico muoia

    static int recoveryTime = 14;    //numero giorni trascorsi tra contagio e guarigione, DURATA DEL VIRUS

    static int incubationPeriod = recoveryTime/6; //giorni di incubazione prima che l'individuo infettato diventi contagioso (cioè cambi colore)
    static int symptomaticityPeriod = recoveryTime/3; //giorni di incubazione prima che l'individuo infettato diventi contagioso (cioè cambi colore)

    static int resources = Math.min(10*initPopulation*careCost, initPopulation*recoveryTime) - 1;   //quantità risorse inizialmente disponibili
}
