package com.steven;

/**
 * @ClassName Action
 * @Description TODO
 * @Author Steven
 * @Date 2019-6-19 16:55
 * @Version 1.0
 */
public class Action {
    public static void main(String[] args) {
        //439
//        double[] steel = {0.0097,0.28,0.29,0.027,0.003,17.29};
//        double[] eTi = {-0.165,-0.026,-0.043,-0.06,-0.27,0.016,0.048,-1.24};
//        double[] eN = {0.13,0.049,-0.02,0.059,0.007,-0.045,-0.593,0.051};
//
//        MathUtils.getSolution(steel, eTi, eN, 1873, 0, 0.5, 1000, 0, 0.05, 1000, 0.000001, "E:\\solution_1873K.csv");
//        MathUtils.getSolution(steel, eTi, eN, 1774, 0, 0.5, 1000, 0, 0.05, 1000, 0.000001, "E:\\solution_1774K.csv");
//        MathUtils.getSolution(steel, eTi, eN, 1740, 0, 0.5, 1000, 0, 0.05, 1000, 0.000001, "E:\\solution_1740K.csv");

        //441
        //C,Si,Mn,P,S,Cr,Nb
        double[] steel = {0.008,0.41,0.12,0.018,0.001,18.2,0.43};
        //C,Si,Mn,P,S,Cr,Nb,Ti,N
        double[] eTi = {-0.165,-0.026,-0.043,-0.06,-0.27,0.016,0,0.048,-1.24};
        double[] eN = {0.13,0.049,-0.02,0.059,0.007,-0.045,-0.0679,-0.593,0.051};

        MathUtils.getSolution(steel, eTi, eN, 1683, 0, 0.5, 1000, 0, 0.05, 1000, 0.000001, "E:\\solution_441_1683K.csv");

    }


}
