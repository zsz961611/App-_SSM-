package com.zsz.utils;

import java.math.BigDecimal;

/**
 * 
 * <p>Title: BigDecimalUtil</p>
 * <p>Description: BigDecimal工具类 </p>
 * BigDecimal：用于高精度计算小数，注意用于财务、银行等准确计算场合
 * @author MuGuijun 
 * @date 2017年6月7日 上午8:42:33
 */
public class BigDecimalUtil {

    /**
     * enum：关键字，用于定义枚举类型
     * 定义的此枚举类型为加，减，乘，除
     * BigDecimalOprations + - * /
     */
    enum BigDecimalOprations{
        add,subtract,multiply,divide
    }

    /**
     * OperationASMD + - * / add substract multiiply divide
     * @param numOne [String Integer Long Double Bigdecimal]
     * @param numTwo [String Integer Long Double Bigdecimal]
     * @param bigDecimalOpration:上面定义的枚举类型
     * @param scale :需要保留的小数位数
     * @param roundingMode：舍入模式
     * @return
     * @throws Exception
     */
    public static BigDecimal OperationASMD(Object numOne,Object numTwo,BigDecimalOprations bigDecimalOpration,int scale,int roundingMode) throws Exception{
        /*
         * BigDecimal.setScale()方法用于格式化小数点:
         * setScale(1)表示保留一位小数，默认用四舍五入方式 
		 * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3 
		 * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4 
		 * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
		 * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
		 * 具体参考Java API
         */
    	BigDecimal num1 = new BigDecimal(String.valueOf(numOne)).setScale(scale,roundingMode);
        BigDecimal num2 = new BigDecimal(String.valueOf(numTwo)).setScale(scale,roundingMode);
        switch (bigDecimalOpration){
        	//num1和num2相加之后精确到scale位数，舍入方式为roundingMode
            case add: return num1.add(num2).setScale(scale,roundingMode);
            case subtract: return num1.subtract(num2).setScale(scale,roundingMode);
            case multiply: return num1.multiply(num2).setScale(scale,roundingMode);
            case divide: return num1.divide(num2, scale, roundingMode);
        }
        return null;
    }


    /* Code Demo Exp */
    public static void main(String[] args){
        try {
            System.out.println(BigDecimalUtil.OperationASMD(36.23,23.369,BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD("36.23","23.369",BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36,23,BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36l,69l,BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(new BigDecimal(0.2635),new BigDecimal(2.3568),BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));


            System.out.println(BigDecimalUtil.OperationASMD(36.23,23.369,BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD("36.23","23.369",BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36,23,BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36l,69l,BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(new BigDecimal(0.2635),new BigDecimal(2.3568),BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));


            System.out.println(BigDecimalUtil.OperationASMD(36.23,23.369,BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD("36.23","23.369",BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36,23,BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36l,69l,BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(new BigDecimal(0.2635),new BigDecimal(2.3568),BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));


            System.out.println(BigDecimalUtil.OperationASMD(36.23,23.369,BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD("36.23","23.369",BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36,23,BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36l,69l,BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(new BigDecimal(0.235),new BigDecimal(0.5689),BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }







}