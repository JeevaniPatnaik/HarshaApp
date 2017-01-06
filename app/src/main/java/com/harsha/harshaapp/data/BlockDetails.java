package com.harsha.harshaapp.data;

import com.harsha.harshaapp.bean.Block;

import java.util.ArrayList;

/**
 * Created by IRFAN on 29-12-2016.
 */

public class BlockDetails {


        static Block block1 = new Block(1, "PURI SADAR", "PS",1);

        static ArrayList<Block> arrBlock = new ArrayList<Block>();

        public static ArrayList<Block> getArrBlock(){
            arrBlock.add(block1);

            return arrBlock;
        }


}
