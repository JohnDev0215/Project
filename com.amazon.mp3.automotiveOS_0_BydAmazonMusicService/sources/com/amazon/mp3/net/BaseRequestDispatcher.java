package com.amazon.mp3.net;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.net.service.ServiceRequest;
import com.amazon.mp3.util.JSONMap;
import com.amazon.mp3.util.Log;
import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.amazonaws.http.HttpHeader;
import com.fasterxml.jackson.core.JsonFactory;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseRequestDispatcher {
    protected abstract HttpRequestBuilder createRequestBuilder(ServiceRequest requestType, JSONObject request);

    protected abstract JsonHttpClient getClient();

    protected abstract EndPointURL getEndPointURL(ServiceApi version);

    protected abstract String getServiceName();

    protected abstract String getServicePackage();

    protected abstract String getTag();

    protected void setUseHttpsOverride(HttpRequestBuilder builder) {
    }

    private static final class InvalidCoralRpcJsonException extends RuntimeException {
        private InvalidCoralRpcJsonException(String message) {
            super(message);
        }

        private InvalidCoralRpcJsonException(String message, Throwable t) {
            super(message, t);
        }
    }

    public final JSONObject dispatch(final ServiceRequest requestType, final JSONObject request) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            throw new IllegalStateException("Cannot dispatch any Service calls when cloud is not supported");
        }
        HttpRequestBuilder httpRequestBuilderCreateRequestBuilder = createRequestBuilder(requestType, request);
        JsonHttpClient client = getClient();
        client.execute(httpRequestBuilderCreateRequestBuilder);
        client.validateResponse();
        return client.getResult();
    }

    protected HttpRequestBuilder createAwsRequest(ServiceRequest operation, JSONObject body) {
        return createAwsRequest(operation, body, operation.getApiVersion());
    }

    private HttpRequestBuilder createAwsRequest(ServiceRequest operation, JSONObject body, ServiceApi version) {
        return createAwsRequest(createCoralRequestJSON(operation, body, version), version);
    }

    private HttpRequestBuilder createAwsRequest(final JSONObject coralRpcInput, final ServiceApi version) {
        AccountCredentialStorage.get();
        HashMap<String, String> map = new HashMap<>();
        buildAwsQueryArguments(coralRpcInput, map);
        HttpRequestBuilder httpRequestBuilderSignRequest = new RequestAuthorizer().signRequest(createBaseRequest(version).addHeader(HttpHeader.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF8").setMethod(1).setBody(HttpRequestBuilder.mapToQueryArguments(map)));
        setUseHttpsOverride(httpRequestBuilderSignRequest);
        return httpRequestBuilderSignRequest;
    }

    private void buildAwsQueryArguments(JSONObject coralRpcInput, HashMap<String, String> output) {
        String strGroup;
        try {
            if (coralRpcInput.has("Input") && !coralRpcInput.isNull("Input")) {
                buildAwsQueryArguments(coralRpcInput.getJSONObject("Input"), (String) null, output);
            }
            if (coralRpcInput.has(JsonDocumentFields.VERSION) && !coralRpcInput.isNull(JsonDocumentFields.VERSION)) {
                output.put(JsonDocumentFields.VERSION, coralRpcInput.getString(JsonDocumentFields.VERSION));
            }
            output.remove("__type");
            String string = coralRpcInput.getString("Operation");
            if (string != null && string.length() > 0) {
                Matcher matcher = Pattern.compile("(.+#)(.*)").matcher(string);
                if (matcher.find() && (strGroup = matcher.group(2)) != null) {
                    output.put("Operation", strGroup);
                }
            }
            if (!output.containsKey("Operation")) {
                throw new InvalidCoralRpcJsonException("Output must contain Operation element");
            }
            output.put("ContentType", JsonFactory.FORMAT_NAME_JSON);
        } catch (JSONException e) {
            Log.error(getTag(), e.getMessage());
            output.clear();
            throw new InvalidCoralRpcJsonException("JSONException was caught when building query", e);
        }
    }

    private void buildAwsQueryArguments(JSONArray elements, String keyRoot, HashMap<String, String> arguments) throws JSONException {
        if (elements == null || elements.length() == 0) {
            arguments.put(keyRoot, "");
            return;
        }
        int i = 0;
        while (i < elements.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(keyRoot);
            sb.append(".member.");
            int i2 = i + 1;
            sb.append(i2);
            String string = sb.toString();
            if (elements.isNull(i)) {
                Log.warning(getTag(), "element at " + i + " is null");
            } else {
                Object obj = elements.get(i);
                if (obj instanceof JSONMap) {
                    addMapToQuery(string, (JSONMap) obj, arguments);
                } else if (obj instanceof JSONArray) {
                    buildAwsQueryArguments((JSONArray) obj, string, arguments);
                } else if (obj instanceof JSONObject) {
                    buildAwsQueryArguments((JSONObject) obj, string, arguments);
                } else {
                    arguments.put(string, obj.toString());
                }
            }
            i = i2;
        }
    }

    private void buildAwsQueryArguments(JSONObject input, String keyRoot, HashMap<String, String> arguments) throws JSONException {
        JSONArray jSONArrayNames = input.names();
        if (jSONArrayNames == null || jSONArrayNames.length() == 0) {
            return;
        }
        for (int i = 0; i < jSONArrayNames.length(); i++) {
            String string = jSONArrayNames.getString(i);
            Object obj = input.get(string);
            if (keyRoot != null && keyRoot.length() > 0) {
                string = keyRoot + "." + string;
            }
            if (obj instanceof JSONMap) {
                addMapToQuery(string, (JSONMap) obj, arguments);
            } else if (obj instanceof JSONArray) {
                buildAwsQueryArguments((JSONArray) obj, string, arguments);
            } else if (obj instanceof JSONObject) {
                buildAwsQueryArguments((JSONObject) obj, string, arguments);
            } else {
                arguments.put(string, obj.toString());
            }
        }
    }

    private void addMapToQuery(String keyRoot, JSONMap input, HashMap<String, String> arguments) throws JSONException {
        JSONArray jSONArrayNames = input.names();
        if (jSONArrayNames == null || jSONArrayNames.length() == 0) {
            return;
        }
        int i = 0;
        while (i < jSONArrayNames.length()) {
            int i2 = i + 1;
            String str = String.format(Locale.US, "entry.%d", Integer.valueOf(i2));
            if (keyRoot != null && keyRoot.length() > 0) {
                str = keyRoot + "." + str;
            }
            String string = jSONArrayNames.getString(i);
            String string2 = input.get(string).toString();
            arguments.put(str + ".key", string);
            arguments.put(str + ".value", string2);
            i = i2;
        }
    }

    protected HttpRequestBuilder createCoralRpcRequest(final ServiceRequest requestType, final JSONObject requestBody) {
        return createCoralRpcRequest(requestType, requestBody, true);
    }

    protected HttpRequestBuilder createCoralRpcRequest(final ServiceRequest requestType, final JSONObject requestBody, final boolean addCustomerInfo) {
        String str = getServicePackage() + "." + getServiceName() + "." + requestType.getRequestName();
        HttpRequestBuilder httpRequestBuilderCreateBaseRequest = createBaseRequest(requestType.getApiVersion());
        httpRequestBuilderCreateBaseRequest.addHeader(HttpHeader.ACCEPT, "application/json").addHeader("Accept-Charset", "utf-8").addHeader(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8").addHeader("Connection", "keep-alive").addHeader("Content-Encoding", "amz-1.0").addHeader("x-amz-target", str);
        if (addCustomerInfo) {
            try {
                addCustomerInfo(requestBody);
            } catch (JSONException e) {
                Log.error(getTag(), "Unable to apply customer info", e);
            }
        }
        signRequest(httpRequestBuilderCreateBaseRequest, requestBody);
        return httpRequestBuilderCreateBaseRequest;
    }

    private void signRequest(HttpRequestBuilder requestBuilder, JSONObject requestBody) {
        setUseHttpsOverride(requestBuilder);
        requestBuilder.setMethod(1);
        requestBuilder.setBody(requestBody.toString());
        new RequestAuthorizer().signRequest(requestBuilder);
    }

    protected void addCustomerInfo(JSONObject object) throws JSONException {
        object.put("customerInfo", AccountCredentialUtil.get().buildCirrusCustomerInfo());
    }

    protected JSONObject createCoralRequestJSON(ServiceRequest request, final JSONObject body, final ServiceApi version) {
        String tag = getTag();
        Object[] objArr = new Object[3];
        objArr[0] = request.getRequestName();
        objArr[1] = body.toString();
        objArr[2] = version == null ? "NULL" : version.getPath();
        Log.debug(tag, "createCoralRequest: %s, %s, %s", objArr);
        try {
            String requestName = request.getRequestName();
            StringBuilder sb = new StringBuilder();
            sb.append(requestName.substring(0, 1).toUpperCase(Locale.US));
            sb.append((requestName + "Request").substring(1));
            String string = sb.toString();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__type", getServicePackage() + "#" + string);
            addCustomerInfo(jSONObject);
            JSONArray jSONArrayNames = body.names();
            if (jSONArrayNames != null) {
                for (int i = 0; i < jSONArrayNames.length(); i++) {
                    String string2 = jSONArrayNames.getString(i);
                    jSONObject.put(string2, body.get(string2));
                }
            }
            JSONObject jSONObjectPut = new JSONObject().put("Operation", getServicePackage() + "#" + requestName).put("Service", getServicePackage() + "#" + getServiceName()).put("Input", jSONObject);
            if (version != null) {
                jSONObjectPut.put(JsonDocumentFields.VERSION, version.getCode());
            }
            return jSONObjectPut;
        } catch (JSONException unused) {
            throw new RuntimeException("Unable to generate JSON");
        }
    }

    private HttpRequestBuilder createBaseRequest(ServiceApi version) {
        String strPort;
        String str;
        String strPath;
        EndPointURL endPointURL = getEndPointURL(version);
        String strProtocol = "";
        if (endPointURL != null) {
            String host = endPointURL.toHost();
            strPort = endPointURL.port();
            strPath = endPointURL.path();
            strProtocol = endPointURL.protocol();
            str = host;
        } else {
            strPort = "0";
            str = "";
            strPath = str;
        }
        Log.verbose(getTag(), "Calling %s - %s - %s - %s", strProtocol, str, strPath, strPort);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(str, Integer.parseInt(strPort), strPath);
        httpRequestBuilder.setUseHttps("https".equals(strProtocol));
        return httpRequestBuilder;
    }
}
