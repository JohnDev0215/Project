package com.cerence.assistant.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class OneCloudConfig {
    private final String AUTH_URL;
    private final String CACERT;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private final String CLOUD_JSON_NAME = "cloud_services.json";
    private final int CONNECTION_TIMEOUT;
    private final String FQDN;
    private final int IDLE_SESSION_TIMEOUT;
    private final int OAUTH_CONNECTION_TIMEOUT;
    private final String SCOPE;

    public OneCloudConfig(String str) throws JSONException, IOException {
        String folderPath = getFolderPath(str);
        JSONObject grpcJson = getGrpcJson(folderPath + "cloud_services.json");
        this.FQDN = grpcJson.getString("url");
        this.CACERT = folderPath + grpcJson.getString("ssl_certificate_bundle");
        this.CONNECTION_TIMEOUT = grpcJson.getInt("connection_timeout");
        this.IDLE_SESSION_TIMEOUT = grpcJson.getInt("idle_session_timeout");
        this.CLIENT_ID = grpcJson.getJSONObject("authorization").getString("client_id");
        this.CLIENT_SECRET = grpcJson.getJSONObject("authorization").getString("client_secret");
        this.AUTH_URL = grpcJson.getJSONObject("authorization").getString("url");
        this.SCOPE = grpcJson.getJSONObject("authorization").getString("scope");
        this.OAUTH_CONNECTION_TIMEOUT = grpcJson.getJSONObject("authorization").getJSONObject("adapter_params").getInt("connect_timeout");
    }

    private JSONObject getGrpcJson(String str) throws JSONException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
        }
        JSONArray jSONArray = new JSONObject(sb.toString()).getJSONArray("cloud_services");
        JSONObject jSONObject = null;
        for (int i = 0; i <= jSONArray.length(); i++) {
            jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.getString("type").equals("grpc")) {
                break;
            }
        }
        return jSONObject;
    }

    private String getFolderPath(String str) {
        String[] list = new File(str).list();
        if (list != null) {
            for (String str2 : list) {
                if (str2.matches("asr_[a-z]{3}-[A-Z]{3}")) {
                    return str + File.separator + str2 + File.separator + "json" + File.separator;
                }
            }
        }
        return "";
    }

    public String getCaCert() {
        return this.CACERT;
    }

    public String getSCOPE() {
        return this.SCOPE;
    }

    public String getAUTH_URL() {
        return this.AUTH_URL;
    }

    public String getCLIENT_SECRET() {
        return this.CLIENT_SECRET;
    }

    public String getCLIENT_ID() {
        return this.CLIENT_ID;
    }

    public String getFQDN() {
        return this.FQDN;
    }

    public int getCONNECTION_TIMEOUT() {
        return this.CONNECTION_TIMEOUT;
    }

    public int getIDLE_SESSION_TIMEOUT() {
        return this.IDLE_SESSION_TIMEOUT;
    }

    public int getOAUTH_CONNECTION_TIMEOUT() {
        return this.OAUTH_CONNECTION_TIMEOUT;
    }
}
