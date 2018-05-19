package util;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMessage {
    public void send(String phone) throws IOException{
        String Mobiltext = "您有新的训练计划，请登录实验室管理系统查看";
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data = {new NameValuePair("Uid", "Scalpel"), new NameValuePair("Key", "d41d8cd98f00b204e980"),
                new NameValuePair("smsMob", phone), new NameValuePair("smsText", Mobiltext)};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        /*
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        */
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result);
        post.releaseConnection();
    }
}
