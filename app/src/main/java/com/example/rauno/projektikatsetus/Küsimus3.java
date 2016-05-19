package com.example.rauno.projektikatsetus;
import java.util.Random;

public class Küsimus3 {
	double vastus = 0;
	String küsimus = "";
	Random random = new Random();
	
	Küsimus3(int tase) {
		int min = -10 - (tase * 2),
			max = 10 + (tase * 2);
		
		if(tase <= 3){
			määra_täisarv(min, max);
			
			for(int i = 0; i < tase + 1; i++){
				küsimus += " + ";
				määra_täisarv(min, max);
			}
		}else if (tase > 3 && tase < 5){			
			int üks = rand(2, 9),
				kaks = rand(2, 9),
				korrutis = üks * kaks;
			String korrutiss = " + "+Integer.toString(üks)+" * "+ Integer.toString(kaks);
			
			määra_täisarv(min, max);
			
			int korrutis_mitmendaks = ran(0, tase - 2 );
			for(int i = 0; i < tase - 1; i++){
				if( i == korrutis_mitmendaks){
					küsimus += korrutiss;
					vastus += korrutis;
				}else{
					küsimus += " + ";
					määra_täisarv(min, max);
				}
			}
		}else if(tase >= 5 && tase < 7){
			min = 1;
			max = 10 + ((tase - 5)*2);
			
			määra_komaga(min, max);
			küsimus += " + ";
			määra_komaga(min, max);
		}else if(tase > 6){
			min = -10 - (tase * 4);
			max = 10 + (tase * 4);
			määra_komaga(min, max);
	        
	        for(int i = 0; i < tase - 5 ; i = i+2){ // iga 2 taseme tagant hakkab juurde lisama	        	
	        	küsimus += " + ";
	        	määra_komaga(min, max);
	        }        
		}
	}
	
	public double getVastus() {
		return vastus;
	}
	public String getKüsimus() {
		return küsimus;
	}

	int rand(int min, int max){	//igavate arvudeta
		int n = 0;
		while(n == 0 || n== 1 || n== -1){			
			n = random.nextInt((max - min)+1) + min;
		}
		return n;
	}
	int ran(int min, int max){	//tavaline
		int n = random.nextInt((max - min)+1) + min;

		return n;
	}
	double randd(int min, int max){ //double jaoks, kahe komakohaga
        double üks = min + (max - min) * random.nextDouble();
		return Math.round(üks * 100.0) / 100.0;
	}
	void määra_täisarv(int min, int max){
		int a = rand(min, max);
		küsimus += Integer.toString(a);
		vastus += a;
	}
	void määra_komaga(int min, int max){
		double esimene = randd(min, max);        
        küsimus += Double.toString(esimene);
        vastus += esimene;
	}

}