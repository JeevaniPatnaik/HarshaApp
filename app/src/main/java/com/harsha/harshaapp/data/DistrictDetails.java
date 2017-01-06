package com.harsha.harshaapp.data;

import com.harsha.harshaapp.bean.District;

import java.util.ArrayList;

/**
 * Created by IRFAN on 29-12-2016.
 */

public class DistrictDetails {


        static District district1 = new District(1, "PURI", "P", 21);
        static District district2 = new District(2, "KHURDA", "k", 21);

        static ArrayList<District> arrDistrict = new ArrayList<District>();
        public static ArrayList<District> getArrDistrict() {

            arrDistrict.add(district1);
            arrDistrict.add(district2);

            return arrDistrict;
        }
}
