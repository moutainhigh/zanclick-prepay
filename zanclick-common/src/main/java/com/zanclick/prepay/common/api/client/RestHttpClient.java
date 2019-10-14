package com.zanclick.prepay.common.api.client;

import com.alibaba.fastjson.JSONObject;
import com.zanclick.prepay.common.api.AsiaInfoHashMap;
import com.zanclick.prepay.common.api.AsiaInfoHeader;
import com.zanclick.prepay.common.api.sign.RSASignature;
import com.zanclick.prepay.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Set;

/**
 * 和商汇请求客户端
 *
 * @author Administrator
 * @date 2019-9-24 10:51:45
 */
@Slf4j
public class RestHttpClient {

    private static final String HTTPS_URL = StringUtils.getProperty(
            "config.properties", "HTTPS_URL");

    private static final String SIGN_PRIVATE_KEY = StringUtils.getProperty(
            "config.properties", "SIGN_PRIVATE_KEY");


    public static HttpClient getSSLHttpClient() throws Exception {

        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] arg0,
                                           String arg1)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0,
                                           String arg1)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                // TODO Auto-generated method stub
                return null;
            }

        };

        final X509HostnameVerifier DO_NOT_VERIFY = new X509HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }

            @Override
            public void verify(String arg0, SSLSocket arg1) throws IOException {
                // TODO Auto-generated method stub

            }

            @Override
            public void verify(String arg0, X509Certificate arg1)
                    throws SSLException {
                // TODO Auto-generated method stub

            }

            @Override
            public void verify(String arg0, String[] arg1, String[] arg2)
                    throws SSLException {
                // TODO Auto-generated method stub

            }
        };

        ctx.init(null, new TrustManager[]{tm}, null);
        SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(ctx, DO_NOT_VERIFY);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(ssf).build();
        return httpclient;
    }

    public static String post(AsiaInfoHeader header, String body,String url) throws Exception {
        log.error("能力回调:{},{},{}",JSONObject.toJSONString(header),body,HTTPS_URL+url);
        HttpClient httpclient = getSSLHttpClient();
        try {
            AsiaInfoHashMap head = AsiaInfoHashMap.toAsiaInfoHashMap(header);
            HttpPost postMethod = new HttpPost(HTTPS_URL+url);
            String content = RSASignature.getSignContent(RSASignature.getSortedMap(head)) + body;
            log.error("能力待签字符串:{}",content);
            log.error("能力待签秘钥:{}",SIGN_PRIVATE_KEY);
            log.error("能力请求头:{}",JSONObject.toJSONString(header));
            log.error("能力内容:{}",body);
            String signStr = RSASignature.sign(content, SIGN_PRIVATE_KEY);
            log.error("能力签名内容:{}",signStr);
            Set keys = head.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                String a = (String) it.next();
                postMethod.setHeader(a, head.get(a));
            }
            postMethod.setHeader("sign", signStr);
            StringEntity entity = new StringEntity(body, "application/json", "UTF-8");
            postMethod.setEntity(entity);
            HttpResponse response = httpclient.execute(postMethod);
            log.error("能力回调:{}",response.getAllHeaders());
            StatusLine respHttpStatus = response.getStatusLine();
            int status = respHttpStatus.getStatusCode();
            if (status == 200) {
                HttpEntity responseBody = response.getEntity();
                return EntityUtils.toString(responseBody, "UTF-8");
            } else {
                return "请求失败:" + status;
            }
        } catch (Exception e) {
            log.error("能力回调出错：{}",e);
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }


}
