package com.cn.car.util;

public class Test {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		String abnormal = "生产部机加", nodeName, option1 = "报废";
		if (abnormal.equals("生产部机加")) {
			System.out.println(abnormal);
			if (option1.equals("返修") || option1.equals("报废")) {
				System.out.println(option1);
				nodeName = "fork 5";
			}
		}
		if ((abnormal.equals("原材料") || abnormal.equals("标准件")
				|| abnormal.equals("外协加工件") || abnormal.equals("生产部机加")) && (option1.equals("特采") || option1.equals("退货"))) {
			if (option1.equals("特采")) {
				nodeName = "fork 9";
			} else if (option1.equals("退货")) {
				nodeName = "fork 7";
			}
		} else if (abnormal.equals("生产部机加") && (option1.equals("返修") || option1.equals("报废"))) {
			System.out.println(option1);
			nodeName = "fork 5";
			
		} else if (abnormal.equals("深圳厂机加") && option1.equals("特采")) {
			nodeName = "fork 8";
		} else if (abnormal.equals("深圳厂机加")
				&& (option1.equals("报废") || option1.equals("返修"))) {
			nodeName = "fork 5";
		} else if (abnormal.equals("装配部线材")) {
			nodeName = "fork 6";
		}
	}
}
