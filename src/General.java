public class General {
    static int population;  // numero di persone esistenti all'inizio della simulazione
    static int resources;   //quantità risorse inizialmente disponibili
    static int careCost;    //costo tampone
    //static int days;

    //Aspetti sanitari
    static double infectivity = 0.5;  //probabilità (maggiore di 0) che un individuo sano venga infettato a seguito di un incontro con un contagiato asintomatico o sintomatico;
    static double symptomaticity = 0.5; //probabilità che un contagiato sviluppi sintomi
    static double Lethality = 0.5;  //probabilità che un malato sintomatico muoia
    static int recoveryTime = 7;    //numero giorni trascorsi tra contagio e guarigione
}
