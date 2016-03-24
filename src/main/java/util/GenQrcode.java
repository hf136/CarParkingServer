package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by wyq on 2016/3/24.
 */
public class GenQrcode {

    public static String getQrcode(String qrcode){
        try {
            qrcode = URLEncoder.encode(qrcode, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String httpUrl = "http://apis.baidu.com/3023/qr/qrcode";
        String httpArg = "size=8&qr=" + qrcode;
        return request(httpUrl, httpArg);
    }

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // ÃÓ»ÎapikeyµΩHTTP header
            connection.setRequestProperty("apikey",  "400771e7779d035d95ef626f9e36ecec");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

