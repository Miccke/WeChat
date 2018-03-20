package com.cn.car.util;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

@Component
public class UrlEncodeUTF8 {
	/**
	 * URL编码（utf-8）
	 * @param source
	 * @return
	 */
    public static String urlEncodeUTF8(String url) {
        String result = url;
        try {
            result = java.net.URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
