package com.amazonaws.mobileconnectors.remoteconfiguration.internal.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public class AndroidRemoteConfigurationFetcher implements RemoteConfigurationFetcher {
    private static final Pattern ARCUS_CLIENT_VERSION_PATTERN = Pattern.compile("[1-9][0-9]*\\.[0-9]+\\.[0-9]+");
    private static final int DEFAULT_CONNECT_TIMEOUT = 15000;
    private static final int DEFAULT_READ_TIMEOUT = 15000;
    private static final String GZIP_ENCODING = "gzip";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String HEADER_AMZ_TARGET = "X-Amz-Target";
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final int HTTP_OK_STATUS_CODE = 200;
    private static final String QUERY_CONFIGURATION_TARGET = "RemoteConfigurationDistributionService.QueryConfiguration";
    private static final int REMOTECONFIG_THREAD_ID = 72283;
    private static final String REQUEST_KEY_APP_CONFIG_ID = "appConfigId";
    private static final String REQUEST_KEY_CLIENT_ATTRIBUTES = "clientAttributes";
    private static final String REQUEST_KEY_LAST_SEEN_ENTITY_TAG = "lastSeenEntityTag";
    private static final String REQUEST_KEY_LOCAL_CONFIG_INSTANCE_ID = "localConfigurationInstanceId";
    private static final String RESPONSE_KEY_CONFIGURATION = "resultVariables";
    private static final String RESPONSE_KEY_ENTITY_TAG = "entityTag";
    private static final String RESPONSE_KEY_UPDATED = "updatedConfigurationAvailable";
    private static final String USER_AGENT_PREFIX = "Arcus-Android/";
    private static final String XAMZ_JSON_CONTENT_TYPE = "application/x-amz-json-1.1";
    private final Context mContext;
    private final URL mEndpoint;

    public AndroidRemoteConfigurationFetcher(Context context, URL url) {
        if (context == null) {
            throw new NullPointerException("The context may not be null.");
        }
        if (url == null) {
            throw new NullPointerException("The endpoint may not be null.");
        }
        this.mContext = context;
        this.mEndpoint = url;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.RemoteConfigurationFetcher
    public RemoteConfiguration fetch(String str, Attributes attributes, String str2, String str3) throws Throwable {
        if (str == null) {
            throw new NullPointerException("The App Configuration ID may not be null");
        }
        if (attributes == null) {
            throw new NullPointerException("The attributes may not be null");
        }
        if (!isNetworkAvailable()) {
            throw new NetworkException("There is no network connectivity.");
        }
        HttpURLConnection httpURLConnection = null;
        TrafficStats.setThreadStatsTag(REMOTECONFIG_THREAD_ID);
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(this.mEndpoint.openConnection()));
                try {
                    configureConnection(httpURLConnection2);
                    setHeaders(httpURLConnection2);
                    writeRequest(httpURLConnection2, buildRequestBody(str, attributes, str2, str3));
                    RemoteConfiguration remoteConfigurationBuildRemoteConfiguration = buildRemoteConfiguration(str, readResponse(httpURLConnection2));
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return remoteConfigurationBuildRemoteConfiguration;
                } catch (IOException e) {
                    e = e;
                    throw new NetworkException("Unable to open connection", e);
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    protected void configureConnection(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setUseCaches(false);
    }

    protected void setHeaders(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestProperty("Content-Type", XAMZ_JSON_CONTENT_TYPE);
        httpURLConnection.setRequestProperty(HEADER_AMZ_TARGET, QUERY_CONFIGURATION_TARGET);
        httpURLConnection.setRequestProperty(HEADER_ACCEPT_ENCODING, GZIP_ENCODING);
        httpURLConnection.setRequestProperty("User-Agent", getArcusUserAgent());
    }

    private String getArcusUserAgent() {
        Matcher matcher = ARCUS_CLIENT_VERSION_PATTERN.matcher("");
        return USER_AGENT_PREFIX + (matcher.find() ? matcher.group() : "1.3.x");
    }

    protected byte[] buildRequestBody(String str, Attributes attributes, String str2, String str3) {
        Map<String, Object> allAttributes;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(REQUEST_KEY_APP_CONFIG_ID, str);
            jSONObject.put(REQUEST_KEY_LAST_SEEN_ENTITY_TAG, str2);
            jSONObject.put(REQUEST_KEY_LOCAL_CONFIG_INSTANCE_ID, str3);
            if (attributes != null && (allAttributes = attributes.getAllAttributes()) != null) {
                jSONObject.put(REQUEST_KEY_CLIENT_ATTRIBUTES, new JSONObject((Map<?, ?>) allAttributes).toString());
            }
            return jSONObject.toString().getBytes(StandardCharsets.UTF_8);
        } catch (JSONException e) {
            throw new NetworkException("Error building request", e);
        }
    }

    protected void writeRequest(HttpURLConnection httpURLConnection, byte[] bArr) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
            } finally {
            }
        } catch (IOException e) {
            throw new NetworkException("Error writing the request", e);
        }
    }

    protected JSONObject readResponse(HttpURLConnection httpURLConnection) {
        InputStreamReader inputStreamReader;
        StringBuilder sb;
        try {
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                closeErrorStreamFromConnection(httpURLConnection);
                throw new NetworkException("Request unsuccessful. Received code " + responseCode);
            }
            try {
                InputStream inputStreamFromConnection = getInputStreamFromConnection(httpURLConnection);
                try {
                    inputStreamReader = new InputStreamReader(inputStreamFromConnection, StandardCharsets.UTF_8);
                    try {
                        sb = new StringBuilder();
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (inputStreamFromConnection != null) {
                            try {
                                inputStreamFromConnection.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
                while (true) {
                    int i = inputStreamReader.read();
                    if (i == -1) {
                        break;
                    }
                    sb.append((char) i);
                    throw th;
                }
                JSONObject jSONObject = new JSONObject(sb.toString());
                inputStreamReader.close();
                if (inputStreamFromConnection != null) {
                    inputStreamFromConnection.close();
                }
                return jSONObject;
            } catch (IOException e) {
                throw new NetworkException("Error reading response.", e);
            } catch (JSONException e2) {
                throw new NetworkException("Invalid response format.", e2);
            }
        } catch (IOException e3) {
            throw new NetworkException("Unable to get response code.", e3);
        }
    }

    private InputStream getInputStreamFromConnection(HttpURLConnection httpURLConnection) throws IOException {
        if (GZIP_ENCODING.equalsIgnoreCase(httpURLConnection.getHeaderField(HEADER_CONTENT_ENCODING))) {
            return new GZIPInputStream(httpURLConnection.getInputStream());
        }
        return httpURLConnection.getInputStream();
    }

    private void closeErrorStreamFromConnection(HttpURLConnection httpURLConnection) {
        try {
            InputStream errorStream = httpURLConnection.getErrorStream();
            if (errorStream != null) {
                errorStream.close();
            }
        } catch (IOException unused) {
        }
    }

    protected RemoteConfiguration buildRemoteConfiguration(String str, JSONObject jSONObject) {
        ConfigurationImpl configurationImpl;
        try {
            boolean z = jSONObject.getBoolean(RESPONSE_KEY_UPDATED);
            String string = jSONObject.getString(RESPONSE_KEY_ENTITY_TAG);
            try {
                configurationImpl = new ConfigurationImpl(jSONObject.getString(RESPONSE_KEY_CONFIGURATION), new Date());
            } catch (JSONException unused) {
                configurationImpl = null;
            }
            return new RemoteConfigurationImpl(configurationImpl, str, 2, string, z);
        } catch (JSONException e) {
            throw new NetworkException("Expected elements missing from the response", e);
        }
    }
}
