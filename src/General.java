public class General {
    static int initPopulation = 200;
    static int population = initPopulation;  // numero di persone esistenti all'inizio della simulazione
    static int speed = 3;
    static int velocity = 5; //numero di incontri di un individuo (in media) in un giorno
    static int swabCost = 1;    //costo tampone
    static int careCost = 3*swabCost;    //costo giornaliero cure mediche per i malati (rossi)

    //Aspetti sanitari
    static double infectivity = 0.5;  //probabilità (maggiore di 0) che un individuo sano venga infettato a seguito di un incontro con un contagiato asintomatico o sintomatico;
    static double symptomaticity = 0.4; //probabilità che un contagiato sviluppi sintomi
    static double lethality = 0.3;  //probabilità che un malato sintomatico muoia

    static int recoveryTime = 14;    //numero giorni trascorsi tra contagio e guarigione, DURATA DEL VIRUS

    static int incubationPeriod = recoveryTime/6; //giorni di incubazione prima che l'individuo infettato diventi contagioso (diventa giallo)
    static int symptomaticityPeriod = recoveryTime/3; //periodo entro il quale si manifestano i sintomi (diventa rosso)

    static int resources = Math.min(10*initPopulation*careCost, initPopulation*recoveryTime) - 1;   //quantità risorse inizialmente disponibili

    //static double r0 = vd * recoveryTime * infectivity; //fattore di contagiosità R0
}
