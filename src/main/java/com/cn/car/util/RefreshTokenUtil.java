package com.cn.car.util;



import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cn.car.entity.RefreshToken;
import com.cn.car.entity.WeChatUserInfo;
@Component
public class RefreshTokenUtil {
	/**
     * 获取网页授权凭证
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
	private static Logger log = Logger.getLogger(RefreshTokenUtil.class);

    public static RefreshToken getRefreshToken(String appId, String appSecret, String code) {
        RefreshToken wat = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = HttpsRequestUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new RefreshToken();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取用户信息失败 errcode:{\""+errorCode+"\"} errmsg:{+\""+errorMsg+"\"}");
            }
        }
        return wat;
    }
    /**
     * 通过网页授权获取用户信息
     * 
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return WeChatUserInfo
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static WeChatUserInfo getWeChatUserInfo(String accessToken, String openId) {
        WeChatUserInfo weChatUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = HttpsRequestUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
            	weChatUserInfo = new WeChatUserInfo();
                // 用户的标识
            	weChatUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
            	weChatUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
            	weChatUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
            	weChatUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
            	weChatUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
            	weChatUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
            	weChatUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
            	weChatUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
            } catch (Exception e) {
            	weChatUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取用户信息失败 errcode:{\""+errorCode+"\"} errmsg:{+\""+errorMsg+"\"}");
            }
        }
        return weChatUserInfo;
    }
}
