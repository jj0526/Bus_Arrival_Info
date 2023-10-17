package kr.co.company.bus_arrival_info.controller;
import java.io.IOException;
import java.net.URLEncoder;

import kr.co.company.bus_arrival_info.model.AuthenticationInfo;

import java.net.URL;
import java.net.URLEncoder;

public class GenUrl {
    private static String EncodingKey = AuthenticationInfo.getEncodingKey();
    private static String DecodingKey = AuthenticationInfo.getDecodingKey();

    // 정류장 조회 URL Generate
    public static URL generate(String stSrch) throws IOException
    {
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/stationinfo/getStationByName"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + EncodingKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("stSrch","UTF-8") + "=" + URLEncoder.encode(stSrch, "UTF-8")); /*정류소명 검색어*/
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        return url;
    }

    public static URL generate(String arsId, String busNum) throws IOException
    {
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + EncodingKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("arsId","UTF-8") + "=" + URLEncoder.encode(arsId, "UTF-8")); /*정류소명 검색어*/
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        return url;
    }
}
