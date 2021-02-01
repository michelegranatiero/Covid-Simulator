package simulation;

public class General {


    //Popolazione e incontri
    static int initPopulation = 500;
    static int population = initPopulation;  // numero di persone esistenti all'inizio della simulazione
    static int speed = 1;   //velocità delle persone (0 è la velocità di base e non è nulla)
    static int velocity = 3; //numero di incontri di un individuo (in media) in un giorno


    //Aspetti sanitari
    static double infectivity = 0.45;  //probabilità (maggiore di 0) che un individuo sano venga infettato a seguito di un incontro con un contagiato asintomatico o sintomatico;
    static double symptomaticity = 0.34; //probabilità che un contagiato sviluppi sintomi
    static double lethality = 0.234;  //probabilità che un malato sintomatico muoia

    static int recoveryTime = 10;    //numero giorni trascorsi tra contagio e guarigione, DURATA DEL VIRUS

    static int incubationPeriod = recoveryTime/6; //giorni di incubazione prima che l'individuo infettato diventi contagioso (diventa giallo)
    static int symptomaticityPeriod = recoveryTime/3; //periodo entro il quale si manifestano i sintomi (diventa rosso)

    //Aspetti economici e strategia
    static int swabCost = 1;    //costo tampone
    static int careCost = 3*swabCost;    //costo giornaliero cure mediche per i malati (rossi)
    static int resMax = Math.min(10*initPopulation*swabCost, initPopulation*recoveryTime) - 1;   //quantità risorse inizialmente disponibili
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













    public static int getInitPopulation() {
        return initPopulation;
    }

    public static void setInitPopulation(int initPopulation) {
        General.initPopulation = initPopulation;
    }

    public static int getPopulation() {
        return population;
    }

    public static void setPopulation(int population) {
        General.population = population;
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        General.speed = speed;
    }

    public static int getVelocity() {
        return velocity;
    }

    public static void setVelocity(int velocity) {
        General.velocity = velocity;
    }

    public static double getInfectivity() {
        return infectivity;
    }

    public static void setInfectivity(double infectivity) {
        General.infectivity = infectivity;
    }

    public static double getSymptomaticity() {
        return symptomaticity;
    }

    public static void setSymptomaticity(double symptomaticity) {
        General.symptomaticity = symptomaticity;
    }

    public static double getLethality() {
        return lethality;
    }

    public static void setLethality(double lethality) {
        General.lethality = lethality;
    }

    public static int getRecoveryTime() {
        return recoveryTime;
    }

    public static void setRecoveryTime(int recoveryTime) {
        General.recoveryTime = recoveryTime;
    }

    public static int getIncubationPeriod() {
        return incubationPeriod;
    }

    public static void setIncubationPeriod(int incubationPeriod) {
        General.incubationPeriod = incubationPeriod;
    }

    public static int getSymptomaticityPeriod() {
        return symptomaticityPeriod;
    }

    public static void setSymptomaticityPeriod(int symptomaticityPeriod) {
        General.symptomaticityPeriod = symptomaticityPeriod;
    }

    public static int getSwabCost() {
        return swabCost;
    }

    public static void setSwabCost(int swabCost) {
        General.swabCost = swabCost;
    }

    public static int getCareCost() {
        return careCost;
    }

    public static void setCareCost(int careCost) {
        General.careCost = careCost;
    }

    public static int getResMax() {
        return resMax;
    }

    public static void setResMax(int resMax) {
        General.resMax = resMax;
    }

    public static int getResources() {
        return resources;
    }

    public static void setResources(int resources) {
        General.resources = resources;
    }

    public static double getR0() {
        return r0;
    }

    public static void setR0(double r0) {
        General.r0 = r0;
    }

    public static int getStrategy() {
        return strategy;
    }

    public static void setStrategy(int strategy) {
        General.strategy = strategy;
    }
}
