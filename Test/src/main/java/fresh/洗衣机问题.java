package fresh;

import java.util.Arrays;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/13 8:44
 * @Description TODO
 */
public class 洗衣机问题 {
    public static void main(String[] args) {
        int[] machines = {1, 2, 3};
        findMinMoves(machines);
    }

    public static int findMinMoves(int[] machines) {

        int machineNums = machines.length;
        int allClothes = 0;
        for (int machine : machines) {
            allClothes += machine;
        }
        if (allClothes % machineNums != 0) return -1;//衣服不能均分
        int avg = allClothes / machineNums; // 每台洗衣机应该分得的衣服
        int lClothes = 0;
        int minMove = 0;
        for (int lMachines = 0; lMachines < machineNums; lMachines++) { //左边第几台机器
            int lNeed = lClothes - lMachines * avg; //左边缺或者多的衣服
            int rNeed = allClothes - (machineNums - lMachines - 1) * avg - lClothes - machines[lMachines];//减去当前机器之前包含自身的机器,以及右侧应该平均分配的所有衣服之和
            if (lNeed < 0 && rNeed < 0) {
                minMove = Math.max(Math.abs(lNeed) + Math.abs(rNeed), minMove);
            } else {
                minMove = Math.max(Math.max(Math.abs(lNeed), Math.abs(rNeed)), minMove);
            }

            lClothes += machines[lMachines];
        }
        return minMove;
    }
}
