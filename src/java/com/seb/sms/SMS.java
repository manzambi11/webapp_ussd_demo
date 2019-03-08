/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seb.sms;

import java.io.IOException;
 
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.EncodingUtil;

/**
 *
 * @author manzambi.n
 */
public class SMS {

    private String accessToken;
    private String BASE_URL = "https://api.smsmode.com/http/1.6/";

    public SMS(String accessToken) {
        super();
        this.accessToken = accessToken;
    }


    public void sendSMSUsingGet(String message, String destinataires, String emetteur, String optionStop) {
        try {
            String getURL = BASE_URL + "sendSMS.do";
            GetMethod httpMethod = new GetMethod(getURL);
            httpMethod.addRequestHeader("Content-Type", "plain/text; charset=ISO-8859-15");

            NameValuePair params[] = {new NameValuePair("accessToken", this.accessToken), //
                new NameValuePair("message", message), //
                new NameValuePair("numero", destinataires), //Destinataires séparés par une virgule
                new NameValuePair("emetteur", emetteur), //Paramètre optionnel, permet de gérer l'émetteur du SMS
                new NameValuePair("stop", optionStop) //Permet de gérer le STOP sms lors d'un envoi marketing (cf. documentation API HTTP)
        };

            httpMethod.setQueryString(EncodingUtil.formUrlEncode(params, "ISO-8859-15"));

            System.out.println(httpMethod.getURI() + "" + httpMethod.getQueryString());

            executeMethod(httpMethod);
        } catch (Exception e) {
            System.out.println("erreur:"+e.getMessage());
            e.printStackTrace();
        }
    }

    private void executeMethod(HttpMethod httpMethod) throws IOException, HttpException {
        HttpClient httpClient = new HttpClient();

        System.out.println(httpMethod);
        int codeReponse = httpClient.executeMethod(httpMethod);
        verifyReponse(httpMethod, codeReponse);
    }

    private void verifyReponse(HttpMethod httpMethod, int codeReponse) throws IOException {
        if (codeReponse == HttpStatus.SC_OK || codeReponse == HttpStatus.SC_ACCEPTED) {
            String result = new String(httpMethod.getResponseBody());
            System.out.println(result);
        }
    }

    private void manageError(Exception e) {
        e.printStackTrace();
        System.err.println("Erreur durant l'appel de l'API");
    }

    public void sendSMSUsingPost(String text, String destinataires, String emetteur, String optionStop) {
        try {
            String postURL = BASE_URL + "sendSMS.do";
            PostMethod httpMethod = new PostMethod(postURL);
            httpMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-15");

            NameValuePair data[] = {new NameValuePair("accessToken", this.accessToken), //
                new NameValuePair("message", text), //
                new NameValuePair("numero", destinataires), //Destinataires séparés par une virgule
                new NameValuePair("emetteur", emetteur), //Paramètre optionnel, permet de gérer l'émetteur du SMS
                new NameValuePair("stop", optionStop) //Permet de gérer le STOP sms lors d'un envoi marketing (cf. documentation API HTTP)
        };
            httpMethod.setRequestBody(data);

            System.out.println("///////////////////////");
            httpMethod.getRequestEntity().writeRequest(System.out);
            executeMethod(httpMethod);

        } catch (Exception e) {
            manageError(e);
        }
    }
    
    
    public static void main(String[] args) {
        String accessToken = "hrzW76G6b4VNUNbxydUx1joDG1CEXowm";
        String message = "Exemple+de+Test+SMS";
        String destinataires = "243810266281,243823112119"; //Destinataires séparés par une virgule
        String emetteur = "toto123"; //Paramètre optionnel, permet de gérer l'émetteur du SMS 
        String stopOption = "1"; //Permet de gérer le STOP sms lors d'un envoi marketing (cf. documentation API HTTP)

        SMS client = new SMS(accessToken);

        client.sendSMSUsingGet(message, destinataires, emetteur, stopOption);
        client.sendSMSUsingPost(message, destinataires, emetteur, stopOption);
    }
}
