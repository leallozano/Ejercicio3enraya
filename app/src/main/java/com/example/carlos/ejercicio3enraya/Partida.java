package com.example.carlos.ejercicio3enraya;

import java.util.Random;

/**
 * Created by Carlos on 5/28/2017.
 */

public class Partida {

    public Partida (int dificultad) {
        this.dificultad=dificultad;

        //this.jugador=jugador;
        jugador=1;

        casillas = new int [9];
        for(int i=1;i<9;i++) {
            casillas[i]=0;
        }
    }

    public boolean comprueba_casilla (int casilla) {
        if (casillas[casilla]!=0) {
            return false;
        }else {
            casillas[casilla]=jugador;
        }
        return true;
    }


    public int turno() {

        boolean empate=true;

        boolean ult_movimiento=true;

        for (int i=0;i<COMBINACIONES.length;i++){
            for(int pos:COMBINACIONES[i]) {

               // System.out.println("Valor en posición " + pos + " " + casillas[pos]);

                if (casillas[pos]!=jugador)ult_movimiento=false;

                if(casillas[pos]==0) {
                    empate=false;
                }

            } //cierre for anidado

            //System.out.println("--------------------------------------------------------");

            if (ult_movimiento)return jugador;
            ult_movimiento=true;

        } //cierre for principal

        if (empate) {
            return 3;
        }


        jugador++;
        if (jugador >2) {
            jugador=1;
        }
        return 0;
    }


    public int dosEnRaya(int jugador_en_turno){ //Video 53

        int casilla=-1;

        int cuantas_lleva=0;

        for (int i=0;i<COMBINACIONES.length;i++){

            for(int pos:COMBINACIONES[i]) {

                if (casillas[pos]==jugador_en_turno) cuantas_lleva++;

                if (casillas[pos]==0) casilla=pos;

            } //cierre for anidado

            if(cuantas_lleva==2 && casilla!=-1) return casilla;

            casilla=-1;
            cuantas_lleva=0;

        } //cierre for principal

        return -1;

    }


    public int ia () {

        int casilla;

        casilla=dosEnRaya(2);

        if(casilla!=-1) return casilla;

        if (dificultad>0) {

            casilla=dosEnRaya(1);

            if (casilla!=-1) return casilla;

        }

        if(dificultad==2) {


            if(casillas[4]==0 && (casillas[0]!=0 || casillas[2]!=0 || casillas[6]!=0 || casillas[8]!=0)) return 4;
            if(casillas[0]==0) return 0;
            if(casillas[2]==0) return 2;
            if(casillas[6]==0) return 6;
            if(casillas[8]==0) return 8;


        }

        Random casilla_azar = new Random();

        casilla=casilla_azar.nextInt(9);

        return casilla;

    }



    public final int dificultad;
    public int jugador;
    private int [] casillas;
    private final int [] [] COMBINACIONES={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



}
