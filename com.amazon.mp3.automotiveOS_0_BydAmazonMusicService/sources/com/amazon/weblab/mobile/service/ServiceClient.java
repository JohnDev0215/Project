package com.amazon.weblab.mobile.service;

import android.net.TrafficStats;
import android.net.Uri;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class ServiceClient {
    private static final Charset CHARSET_UTF_8 = Charset.forName(ParserUtil.UTF_8);
    private int mMaxNumOfRetries;
    private String mWeblabClientIdentifier;

    ServiceClient(int i, String str) {
        this.mMaxNumOfRetries = 0;
        if (i < 0) {
            throw new IllegalArgumentException("maxNumOfRetries can't be less than 0.");
        }
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("weblabClientIdentifier can't be null nor empty");
        }
        this.mMaxNumOfRetries = i;
        this.mWeblabClientIdentifier = str;
    }

    private void buildBaseUrl(Uri.Builder builder, String str) {
        builder.scheme("https");
        builder.encodedAuthority(str);
    }

    private String readPayload(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, CHARSET_UTF_8));
        try {
            char[] cArr = new char[1024];
            while (true) {
                int i = bufferedReader.read(cArr);
                if (i > 0) {
                    sb.append(cArr, 0, i);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (Throwable th) {
            bufferedReader.close();
            throw th;
        }
    }

    private ServiceResponse createResponse(HttpURLConnection httpURLConnection) throws IOException {
        return new ServiceResponse(httpURLConnection.getHeaderFields(), readPayload(httpURLConnection.getInputStream()), this.mWeblabClientIdentifier);
    }

    ServiceResponse invoke(String str, ServiceRequest serviceRequest) throws MobileWeblabException, IOException, UriTooLongException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("basePath can't be null.");
        }
        if (serviceRequest == null) {
            throw new IllegalArgumentException("request can't be null.");
        }
        Uri.Builder builder = new Uri.Builder();
        buildBaseUrl(builder, str);
        serviceRequest.appendParamToUrl(builder);
        int length = builder.toString().length();
        if (length >= 8190) {
            throw new UriTooLongException("Request line too long", length);
        }
        URL url = new URL(builder.toString());
        int threadStatsTag = TrafficStats.getThreadStatsTag();
        TrafficStats.setThreadStatsTag(87561);
        for (int i = 0; i <= this.mMaxNumOfRetries; i++) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
            try {
                httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
                for (Map.Entry<String, String> entry : serviceRequest.getHttpHeaders().entrySet()) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
                String strConstructPayload = serviceRequest.constructPayload();
                if (strConstructPayload != null) {
                    byte[] bytes = strConstructPayload.getBytes(CHARSET_UTF_8);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.getOutputStream().write(bytes);
                }
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 204) {
                    ServiceResponse serviceResponseCreateEmptyServiceResponse = ServiceResponse.createEmptyServiceResponse();
                    try {
                        if (httpURLConnection.getOutputStream() != null) {
                            httpURLConnection.getOutputStream().close();
                        }
                    } catch (IOException unused) {
                    }
                    try {
                        if (httpURLConnection.getInputStream() != null) {
                            httpURLConnection.getInputStream().close();
                        }
                    } catch (IOException unused2) {
                    }
                    try {
                        if (httpURLConnection.getErrorStream() != null) {
                            httpURLConnection.getErrorStream().close();
                        }
                    } catch (IOException unused3) {
                    }
                    httpURLConnection.disconnect();
                    TrafficStats.setThreadStatsTag(threadStatsTag);
                    return serviceResponseCreateEmptyServiceResponse;
                }
                if (responseCode == 200) {
                    ServiceResponse serviceResponseCreateResponse = createResponse(httpURLConnection);
                    try {
                        if (httpURLConnection.getOutputStream() != null) {
                            httpURLConnection.getOutputStream().close();
                        }
                    } catch (IOException unused4) {
                    }
                    try {
                        if (httpURLConnection.getInputStream() != null) {
                            httpURLConnection.getInputStream().close();
                        }
                    } catch (IOException unused5) {
                    }
                    try {
                        if (httpURLConnection.getErrorStream() != null) {
                            httpURLConnection.getErrorStream().close();
                        }
                    } catch (IOException unused6) {
                    }
                    httpURLConnection.disconnect();
                    TrafficStats.setThreadStatsTag(threadStatsTag);
                    return serviceResponseCreateResponse;
                }
                if (responseCode == 414) {
                    throw new UriTooLongException("Request too long", length);
                }
                if (responseCode == 429) {
                    throw new ThrottledServiceCallException("Request was throttled");
                }
                String payload = "Unknown";
                if (responseCode < 500 || responseCode > 599) {
                    if (httpURLConnection.getErrorStream() != null) {
                        payload = readPayload(httpURLConnection.getErrorStream());
                    }
                    throw new IOException(String.format("Service doesn't respond OK or No Content. Response message: %s. Detail: %s", httpURLConnection.getResponseMessage(), payload));
                }
                if (httpURLConnection.getErrorStream() != null) {
                    payload = readPayload(httpURLConnection.getErrorStream());
                }
                throw new InternalServerErrorException(String.format("Service doesn't respond OK or No Content. Response message: %s. Detail: %s", httpURLConnection.getResponseMessage(), payload));
            } catch (IOException e) {
                try {
                    if (i >= this.mMaxNumOfRetries) {
                        throw e;
                    }
                    if (httpURLConnection != null) {
                        try {
                            if (httpURLConnection.getOutputStream() != null) {
                                httpURLConnection.getOutputStream().close();
                            }
                        } catch (IOException unused7) {
                        }
                        try {
                            if (httpURLConnection.getInputStream() != null) {
                                httpURLConnection.getInputStream().close();
                            }
                        } catch (IOException unused8) {
                        }
                        try {
                            if (httpURLConnection.getErrorStream() != null) {
                                httpURLConnection.getErrorStream().close();
                            }
                        } catch (IOException unused9) {
                        }
                        httpURLConnection.disconnect();
                    }
                    TrafficStats.setThreadStatsTag(threadStatsTag);
                } catch (Throwable th) {
                    if (httpURLConnection != null) {
                        try {
                            if (httpURLConnection.getOutputStream() != null) {
                                httpURLConnection.getOutputStream().close();
                            }
                        } catch (IOException unused10) {
                        }
                        try {
                            if (httpURLConnection.getInputStream() != null) {
                                httpURLConnection.getInputStream().close();
                            }
                        } catch (IOException unused11) {
                        }
                        try {
                            if (httpURLConnection.getErrorStream() != null) {
                                httpURLConnection.getErrorStream().close();
                            }
                        } catch (IOException unused12) {
                        }
                        httpURLConnection.disconnect();
                    }
                    TrafficStats.setThreadStatsTag(threadStatsTag);
                    throw th;
                }
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new IOException("OkHttp client threw an ArrayIndexOutOfBoundsException", e2);
            }
        }
        throw new IOException("Exceeds max number of retries.");
    }
}
