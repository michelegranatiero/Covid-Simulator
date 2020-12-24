public class General {
    static int initPopulation = 500;
    static int population = initPopulation;  // numero di persone esistenti all'inizio della simulazione
    static int speed = 0;
    static int velocity = 3; //numero di incontri di un individuo (in media) in un giorno
    static int swabCost = 1;    //costo tampone
    static int careCost = 3*swabCost;    //costo giornaliero cure mediche per i malati (rossi)

    //Aspetti sanitari
    static double infectivity = 0.45;  //probabilità (maggiore di 0) che un individuo sano venga infettato a seguito di un incontro con un contagiato asintomatico o sintomatico;
    static double symptomaticity = 0.34; //probabilità che un contagiato sviluppi sintomi
    static double lethality = 0.234;  //probabilità che un malato sintomatico muoia

    static int recoveryTime = 10;    //numero giorni trascorsi tra contagio e guarigione, DURATA DEL VIRUS

    static int incubationPeriod = recoveryTime/6; //giorni di incubazione prima che l'individuo infettato diventi contagioso (diventa giallo)
    static int symptomaticityPeriod = recoveryTime/3; //periodo entro il quale si manifestano i sintomi (diventa rosso)

    static int resources = Math.min(10*initPopulation*careCost, initPopulation*recoveryTime) - 1;   //quantità risorse inizialmente disponibili

    static double r0 = 0; //fattore di contagiosità R0 iniziale

    static int strategy = 1;

    static void updateRes(){
        resources = Math.min(10*initPopulation*careCost, initPopulation*recoveryTime) - 1;
    }
}
