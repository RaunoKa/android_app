package com.example.rauno.projektikatsetus;

import java.util.ArrayList;

/**
 * Created by Rauno on 31.03.2016.
 */
public class Alusta {
    int elusid = 4;

    double input = -1;
    int õiged = 0;
    int valed = 0;
    int järjest_vaja = 3;    //et jõuda järgmisele tasemele on vaja värjest nii palju vastuseid
    int järjest = 0;    //vale vastus nullib "järjest"(combo)
    int tase = 0;
    long aeg = 0;
    long start; //küsimuse küsimise aeg

    String kys;
    double vast;

    //list mängu tulemustega(küs, vastus, kulunud aeg jne)
    ArrayList<String> list = new ArrayList<String>();

    String getElusid(){
        return Integer.toString(elusid);
    }

    public Alusta() {
        mängi();
    }

    String kontrolli(String sisend){
        String välja;
        //lõpeta ajavõtt
        aeg = System.currentTimeMillis() - start;
        //õige vastus
        if(Double.parseDouble(sisend) == Math.round(vast * 100.0) / 100.0){
            õiged++;
            järjest++;
            välja = "Õige, tase: "+Integer.toString(tase)+"\nElusid: "+ elusid +"\nJärgmise tasemeni "+ (järjest_vaja - järjest + 1) + " õiget vastust";
            list.add(kys + "\t\t" + input + "\t\t "+ "ÕIGE" + "\t\t" + Long.toString(aeg));
        //vale vastus
        }else{
            elusid--;
            valed++;
            järjest = 0;
            välja = "Vale, tase:"+Integer.toString(tase)+"\nElusid: "+ elusid +"\nJärgmise tasemeni "+ (järjest_vaja - järjest + 1) + " õiget vastust";
            list.add(kys + "\t\t" + input + "\t\t "+ "VALE" + "\t\t" + Long.toString(aeg));

            if(elusid < 1){
                return "Mäng läbi!";
            }
        }

        return välja;
    }

    public void mängi(){
        if(järjest >= järjest_vaja){ tase++; järjest = 0;}
        //alusta ajavõtuga
        start = System.currentTimeMillis();

        Küsimus3 a = new Küsimus3(tase);

        kys = a.getKüsimus();
        vast = a.getVastus();
    }

    public String vormista_tulemus(){
        return "Jõudsid tasemele " + tase + "\nÕigeid vastuseid: " + õiged + "\nValesid vastuseid: "+
                valed + "\nKokku vastuseid: " + (õiged + valed);
    }
}
