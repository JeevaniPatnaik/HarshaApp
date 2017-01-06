package com.harsha.harshaapp.data;

import com.harsha.harshaapp.bean.State;

import java.util.*;
import java.util.ArrayList;

/**
 * Created by IRFAN on 29-12-2016.
 */

public class StateDetails {

        static State state1 = new State(1, "JK", "Jammu and Kashmir");
    static State state2 = new State(2, "HP", "Himachal Pradesh");
    static    State state3 = new State(3, "PN", "Punjab");
    static    State state4 = new State(4, "CH", "Chandigarh");
    static    State state5 = new State(5, "UT", "Uttarakhand");
    static    State state6 = new State(6, "HR", "Haryana");
    static    State state7 = new State(7, "DL", "Delhi");
    static    State state8 = new State(8, "RJ", "Rajasthan");
    static    State state9 = new State(9, "UP", "Uttar Pradesh");
    static    State state10 = new State(10, "BH", "BIHAR");
    static    State state11 = new State(11, "SK", "Sikkim");
    static    State state12 = new State(12, "ARP", "Arunachal Pradesh");
    static    State state13 = new State(13, "NG", "Nagaland");
    static    State state14 = new State(14, "MN", "Manipur");
    static    State state15 = new State(15, "MZ", "Mizoram");
    static    State state16 = new State(16, "TR", "Tripura");
    static    State state17 = new State(17, "MG", "Meghalaya");
    static    State state18 = new State(18, "AS", "Assam");
    static    State state19 = new State(19, "WB", "West Bangal");
    static    State state20 = new State(20, "JH", "Jharkhand");
    static    State state21 = new State(21, "OD", "Odisha");
    static    State state22 = new State(22, "CHH", "Chhattisgarh");
    static    State state23 = new State(23, "MP", "Madhya Pradesh");
    static    State state24 = new State(24, "GJ", "Gujarat");
    static    State state25 = new State(25, "DD", "Daman & Diu");
    static   State state26 = new State(26, "DN", "Dadra & Nagar Haveli");
    static   State state27 = new State(27, "MH", "Maharashtra");
    static   State state28 = new State(28, "AP", "Andhra Pradesh");
    static   State state29 = new State(29, "KR", "Karnataka");
    static   State state30 = new State(30, "GO", "Goa");
    static    State state31 = new State(31, "LD", "Lakshdweep");
    static    State state32 = new State(32, "KL", "Kerala");
    static    State state33 = new State(33, "TN", "Tamil Nadu");
    static    State state34 = new State(34, "PD", "Pondicherry");
    static    State state35 = new State(35, "AN", "Andaman & Nicobar Islands");
    static   State state36 = new State(36, "TL", "Telangana");

         static ArrayList<State> arrState = new ArrayList<State>();

    public static ArrayList<State> getArrState() {

        arrState.add(state1);
        arrState.add(state2);
        arrState.add(state2);
        arrState.add(state3);
        arrState.add(state4);
        arrState.add(state5);
        arrState.add(state6);
        arrState.add(state7);
        arrState.add(state8);
        arrState.add(state9);
        arrState.add(state10);
        arrState.add(state11);
        arrState.add(state12);
        arrState.add(state13);
        arrState.add(state14);
        arrState.add(state15);
        arrState.add(state16);
        arrState.add(state17);
        arrState.add(state18);
        arrState.add(state19);
        arrState.add(state20);
        arrState.add(state21);
        arrState.add(state22);
        arrState.add(state23);
        arrState.add(state24);
        arrState.add(state25);
        arrState.add(state26);
        arrState.add(state27);
        arrState.add(state28);
        arrState.add(state29);
        arrState.add(state30);
        arrState.add(state31);
        arrState.add(state32);
        arrState.add(state33);
        arrState.add(state34);
        arrState.add(state35);
        arrState.add(state36);

        return arrState;
    }

    public void setArrState(ArrayList<State> arrState) {
        this.arrState = arrState;
    }



}
