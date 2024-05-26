package kr.co.company.bus_arrival_info.controller;
import java.io.IOException;
import java.net.URLEncoder;


import java.net.URL;
import java.net.URLEncoder;

public class GenUrl {
    private static String EncodingKey = "zblpVQX%2B75IpUWic%2BfeIY7TaV1DCNu8qOPWmVR2AUqYKrsB%2BNM6wYv1pjWczB0%2FK2TNlTq%2FOmaZ67dSEImlQeQ%3D%3D";
    private static String DecodingKey = "zblpVQX+75IpUWic+feIY7TaV1DCNu8qOPWmVR2AUqYKrsB+NM6wYv1pjWczB0/K2TNlTq/OmaZ67dSEImlQeQ==";

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

    public static URL generate(String tmX, String tmY, String radius) throws IOException
    {
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/stationinfo/getStationByPos"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + EncodingKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("tmX","UTF-8") + "=" + URLEncoder.encode(tmX, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("tmY","UTF-8") + "=" + URLEncoder.encode(tmY, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode(radius, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        return url;
    }
}
