package simulation;

public class General {


    //Popolazione e incontri
    static int initPopulation = 500;

    static int speed = 0;   //velocità delle persone (0 è la velocità di base e non è nulla)
    static int velocity = 3; //numero di incontri di un individuo (in media) in un giorno


    //Aspetti sanitari
    static double infectivity = 0.5;  //probabilità (maggiore di 0) che un individuo sano venga infettato a seguito di un incontro con un contagiato asintomatico o sintomatico;
    static double symptomaticity = 0.6; //probabilità che un contagiato sviluppi sintomi
    static double lethality = 0.1;  //probabilità che un malato sintomatico muoia

    static int recoveryTime = 7;    //numero giorni trascorsi tra contagio e guarigione, DURATA DEL VIRUS

    static int incubationPeriod = recoveryTime/6; //giorni di incubazione prima che l'individuo infettato diventi contagioso (diventa giallo)
    static int symptomaticityPeriod = recoveryTime/3; //periodo entro il quale si manifestano i sintomi (diventa rosso)

    //Aspetti economici e strategia
    static int swabCost = 1;    //costo tampone
    static int careCost = 3*swabCost;    //costo giornaliero cure mediche per i malati (rossi)
    static int resMax = Math.min(10*initPopulation*swabCost, initPopulation*recoveryTime) - 1;   //quantità max di risorse inizialmente disponibili
    static int resources = resMax;   //quantità risorse inizialmente disponibili
    static double r0 = 0; //fattore r0 iniziale
    static int strategy = 1;


    //Metodo per aggiornare le risorse (utile al menù iniziale)
    static void updateRes(){
        resMax = Math.min(10*initPopulation*careCost, initPopulation*recoveryTime) - 1;
        if(resources>resMax){
            resources = resMax;
        }
    }



    public static void setInitPopulation(int initPopulation) {
        General.initPopulation = initPopulation;
    }

    public static void setVelocity(int velocity) {
        General.velocity = velocity;
    }

    public static void setRecoveryTime(int recoveryTime) {
        General.recoveryTime = recoveryTime;
    }

    public static int getResMax() {
        return resMax;
    }

    public static void setResources(int resources) {
        General.resources = resources;
    }

}
