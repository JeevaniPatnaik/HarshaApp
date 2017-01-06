package com.harsha.harshaapp.data;

import com.harsha.harshaapp.bean.Village;

import java.util.ArrayList;

/**
 * Created by IRFAN on 29-12-2016.
 */

public class VillageDetails {



        static Village village1 = new Village(1, "CHANDAN PUR", "CH", 1);

        static ArrayList<Village> arrVillage = new ArrayList<Village>();

        public static ArrayList<Village> getArrVillage(){
            arrVillage.add(village1);

            return arrVillage;
        }
}
