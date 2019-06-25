package com.steven;

import java.util.ArrayList;

/**
 * @ClassName MathUtils
 * @Description TODO
 * @Author Steven
 * @Date 2019-6-19 16:45
 * @Version 1.0
 */
public class MathUtils {
    /**
     * @Title getArray
     * @Description TODO
     * @Author Steven
     * @Date 2019-6-19 18:03
     * @Param [start 开始, end 结束, length 返回数组长度]
     * @Return double[]
     */
    public static double[] getArray(double start, double end, int length){
        // 建议length采用10的n次方
        double step = (end - start) / length;
        double[] result = new double[length];
        for(int i = 0; i < length; i++){
            result[i] = start + step * (i + 1);
        }
        return result;
    }

    /**
     * @Title f
     * @Description TODO
     * @Author Steven
     * @Date 2019-6-20 13:39
     * @Param [steel 除了Ti和N的钢液成分, eTi Ti对其它元素的活度相互作用系数, eN N对其它元素的活度相互作用系数, Ti Ti的成分, N N的成分]
     * @Return double
     */
    public static double func(double[] steel, double[] eTi, double[] eN, double temperature, double Ti, double N){
        //除了Ti和N的其它元素与相互作用系数乘积之和
        double sumTi = 0.0;
        double sumN = 0.0;
        for(int i = 0; i < steel.length; i++){
            sumTi = sumTi + steel[i] * eTi[i];
            sumN = sumN + steel[i] * eN[i];
        }
        sumTi = sumTi + eTi[eTi.length - 2] * Ti + eTi[eTi.length - 1] * N;
        sumN = sumN + eN[eN.length - 2] * Ti + eN[eN.length - 1] * N;

        //Ti与N反应平衡常数的10为底对数，lgK
        double lgK = -15780 / temperature + 5.63;

        //Ti在1873K下与其它温度活度系数的对数转换关系
        double coeff_Ti = 2557 / temperature - 0.365;
        //N在1873K下与其它温度活度系数的对数转换关系
        double coeff_N = 3280 / temperature - 0.75;

        return Math.log10(Ti) + Math.log10(N) + coeff_Ti * sumTi + coeff_N * sumN - lgK;
    }

    public static void getSolution(double[] steel, double[] eTi, double[] eN, double temperature, double startX, double endX, int partitionX, double startY, double endY, int  partitionY, double error,String logPath){
        double[] x = MathUtils.getArray(startX, endX, partitionX);
        String[] y = new String[x.length];

        for(int i = 0; i < x.length; i++) {
            double deltaFunc = 100;
            double startYTemp = startY;
            double endYTemp = endY;
            String resultY = "";

            while (deltaFunc > error) {
                double testY[] = MathUtils.getArray(startYTemp, endYTemp, partitionY);
                //上一个testY值计算得到的函数值
                double lastFunc = MathUtils.func(steel, eTi, eN, temperature, x[i], testY[0]);
                //用于判读是否有解
                boolean flag = false;

                for(int j = 1; j < testY.length; j++){
                    double nowFunc = MathUtils.func(steel, eTi, eN, temperature, x[i], testY[j]);
                    if(nowFunc * lastFunc < 0){
                        //一个大于0，一个小于0，解在两者之间
                        flag = true;
                        deltaFunc = Math.abs(nowFunc);
                        startYTemp = testY[j - 1];
                        endYTemp = testY[j];
                        resultY = endYTemp + "";
                        break;
                    }else{
                        lastFunc = nowFunc;
                    }
                }
                //本次x所对应y无解，跳出while循环
                if(!flag) break;
            }

            y[i] = resultY;
        }
        //输出x,y
        LogUtils.log(x, y, logPath);
    }

 }
