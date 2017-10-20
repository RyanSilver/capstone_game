//package EYETeeCapstoneProject;

import java.util.*;

class tdata {

    public int tint = -1;
    public String ln = "";
    public String sn = "";

    public tdata(int a, String b, String c) {
        tint = a;
        ln = b;
        sn = c;
    }

    public String toString() {
        return "( tdata " + tint + " " + ln + " " + sn + " )";
    }
}
/**
 * keeps track of territory names in the form of strings 
 */
public class terrNames {

    public static String ALA = "ALA";
    public static int iALA = 1;
    public static String NWT = "NWT";
    public static int iNWT = iALA + 1;
    public static String ALB = "ALB";
    public static int iALB = iNWT + 1;
    public static String ONT = "ONT";
    public static int iONT = iALB + 1;
    public static String WUS = "WUS";
    public static int iWUS = iONT + 1;
    public static String EUS = "EUS";
    public static int iEUS = iWUS + 1;
    public static String CAM = "CAM";
    public static int iCAM = iEUS + 1;
    public static String QUE = "QUE";
    public static int iQUE = iCAM + 1;
    public static String GRE = "GRE";
    public static int iGRE = iQUE + 1;

    public static String ICE = "ICE";
    public static int iICE = iGRE + 1;
    public static String GBR = "GBR";
    public static int iGBR = iICE + 1;
    public static String NEU = "NEU";
    public static int iNEU = iGBR + 1;
    public static String WEU = "WEU";
    public static int iWEU = iNEU + 1;
    public static String SEU = "SEU";
    public static int iSEU = iWEU + 1;
    public static String SCA = "SCA";
    public static int iSCA = iSEU + 1;

    public static String UKR = "UKR";
    public static int iUKR = iSCA + 1;
    public static String AFG = "AFG";
    public static int iAFG = iUKR + 1;
    public static String URA = "URA";
    public static int iURA = iAFG + 1;
    public static String SIB = "SIB";
    public static int iSIB = iURA + 1;
    public static String YAK = "YAK";
    public static int iYAK = iSIB + 1;
    public static String KAM = "KAM";
    public static int iKAM = iYAK + 1;
    public static String IRK = "IRK";
    public static int iIRK = iKAM + 1;
    public static String MON = "MON";
    public static int iMON = iIRK + 1;
    public static String CHI = "CHI";
    public static int iCHI = iMON + 1;
    public static String MEA = "MEA";
    public static int iMEA = iCHI + 1;
    public static String IND = "IND";
    public static int iIND = iMEA + 1;
    public static String SIA = "SIA";
    public static int iSIA = iIND + 1;
    public static String JAP = "JAP";
    public static int iJAP = iSIA + 1;

    public static String VEN = "VEN";
    public static int iVEN = iJAP + 1;
    public static String PER = "PER";
    public static int iPER = iVEN + 1;
    public static String BRA = "BRA";
    public static int iBRA = iPER + 1;
    public static String ARG = "ARG";
    public static int iARG = iBRA + 1;

    public static String NAF = "NAF";
    public static int iNAF = iARG + 1;
    public static String EGY = "EGY";
    public static int iEGY = iNAF + 1;
    public static String EAF = "EAF";
    public static int iEAF = iEGY + 1;
    public static String CAF = "CAF";
    public static int iCAF = iEAF + 1;
    public static String SAF = "SAF";
    public static int iSAF = iCAF + 1;
    public static String MAD = "MAD";
    public static int iMAD = iSAF + 1;

    public static String INE = "INE";
    public static int iINE = iMAD + 1;
    public static String NGU = "NGU";
    public static int iNGU = iINE + 1;
    public static String WAU = "WAU";
    public static int iWAU = iNGU + 1;
    public static String EAU = "EAU";
    public static int iEAU = iWAU + 1;
    public static String NZE = "NZE";
    public static int iNZE = iEAU + 1;

    public static tdata[] terrs
            = {
                new tdata(iALA, "Alaska", ALA),
                new tdata(iNWT, "NorthwestTerritory", NWT),
                new tdata(iALB, "Alberta", ALB),
                new tdata(iONT, "Ontario", ONT),
                new tdata(iWUS, "WesternUnitedStates", WUS),
                new tdata(iEUS, "EasternUnitedStates", EUS),
                new tdata(iCAM, "CentralAmerica", CAM),
                new tdata(iQUE, "Quebec", QUE),
                new tdata(iGRE, "Greenland", GRE),
                new tdata(iICE, "Iceland", ICE),
                new tdata(iGBR, "GreatBritain", GBR),
                new tdata(iNEU, "NorthernEurope", NEU),
                new tdata(iWEU, "WesternEurope", WEU),
                new tdata(iSEU, "SouthernEurope", SEU),
                new tdata(iSCA, "Scandinavia", SCA),
                new tdata(iUKR, "Ukraine", UKR),
                new tdata(iAFG, "Afghanistan", AFG),
                new tdata(iURA, "Ural", URA),
                new tdata(iSIB, "Siberia", SIB),
                new tdata(iYAK, "Yakutsk", YAK),
                new tdata(iKAM, "Kamchatka", KAM),
                new tdata(iIRK, "Irkutsk", IRK),
                new tdata(iMON, "Mongolia", MON),
                new tdata(iCHI, "China", CHI),
                new tdata(iMEA, "MiddleEast", MEA),
                new tdata(iIND, "India", IND),
                new tdata(iSIA, "Siam", SIA),
                new tdata(iJAP, "Japan", JAP),
                new tdata(iVEN, "Venezuela", VEN),
                new tdata(iPER, "Peru", PER),
                new tdata(iBRA, "Brazil", BRA),
                new tdata(iARG, "Argentina", ARG),
                new tdata(iNAF, "NorthAfrica", NAF),
                new tdata(iEGY, "Egypt", EGY),
                new tdata(iEAF, "EastAfrica", EAF),
                new tdata(iCAF, "CentralAfrica", CAF),
                new tdata(iSAF, "SouthAfrica", SAF),
                new tdata(iMAD, "Madagascar", MAD),
                new tdata(iINE, "Indonesia", INE),
                new tdata(iNGU, "NewGuinea", NGU),
                new tdata(iWAU, "WesternAustralia", WAU),
                new tdata(iEAU, "EasternAustralia", EAU),
                new tdata(iNZE, "NewZealand", NZE)

            };

    public static int strToInt(String s) {
        int answer = -1;
        for (int i = 0; (i < terrs.length & answer == -1); i++) {
            if (terrs[i].sn.equals(s)) {
                answer = terrs[i].tint;
            }
        }
        return answer;
    }

    public static int longNameToInt(String s) {
        int answer = -1;
        for (int i = 0; (i < terrs.length & answer == -1); i++) {
            if (terrs[i].ln.equals(s)) {
                answer = terrs[i].tint;
            }
        }
        return answer;
    }

    public static String intToLongName(int t) {
        String answer = null;
        for (int i = 0; (i < terrs.length & answer == null); i++) {
            if (terrs[i].tint == t) {
                answer = terrs[i].ln;
            }
        }
        return answer;
    }

    public static String intToStr(int t) {
        String answer = null;
        for (int i = 0; (i < terrs.length & answer == null); i++) {
            if (terrs[i].tint == t) {
                answer = terrs[i].sn;
            }
        }
        return answer;
    }

    public static String getFirstTerritory() {
        return "ALA";
    }

    public static String getLastTerritory() {
        return "NZE";
    }

    public static String getTerritoryInfo(String a) {
        String ans = null;
        for (int i = 0; (i < terrs.length & ans == null); i++) {
            if (terrs[i].sn.equals(a)) {
                ans = "( TERRITORY_NODE " + terrs[i].sn + " " + terrs[i].ln + " )";
            }
        }
        return ans;
    }

    public static String getSuccessorTerritory(String a) {
        String ans = null;
        if (!a.equals(getLastTerritory())) {
            int n = strToInt(a);
            n++;
            ans = intToStr(n);
        }
        return ans;
    }

}
