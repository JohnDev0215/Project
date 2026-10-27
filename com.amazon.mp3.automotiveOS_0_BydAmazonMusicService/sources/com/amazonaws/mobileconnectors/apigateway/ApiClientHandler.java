package com.amazonaws.mobileconnectors.apigateway;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.Signer;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.http.HttpRequest;
import com.amazonaws.http.HttpRequestFactory;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.http.UrlHttpClient;
import com.amazonaws.mobileconnectors.apigateway.annotation.Operation;
import com.amazonaws.mobileconnectors.apigateway.annotation.Parameter;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.IOUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.DateDeserializer;
import com.google.android.gms.actions.SearchIntents;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class ApiClientHandler implements InvocationHandler {
    private static final Gson GSON_WITH_DATE_FORMATTER = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer(new String[]{DateUtils.ISO8601_DATE_PATTERN, DateUtils.ALTERNATE_ISO8601_DATE_PATTERN, DateUtils.COMPRESSED_DATE_PATTERN, DateUtils.RFC822_DATE_PATTERN})).create();
    private static final int HTTP_RESPONSE_LAST_SUCCESS_STATUSCODE = 300;
    private static final int HTTP_RESPONSE_OK = 200;
    private final String apiKey;
    private final String apiName;
    private HttpClient client;
    private final ClientConfiguration clientConfiguration;
    private final String endpoint;
    private final AWSCredentialsProvider provider;
    private final HttpRequestFactory requestFactory = new HttpRequestFactory();
    private final Signer signer;

    ApiClientHandler(String str, String str2, Signer signer, AWSCredentialsProvider aWSCredentialsProvider, String str3, ClientConfiguration clientConfiguration) {
        this.endpoint = str;
        this.apiName = str2;
        this.signer = signer;
        this.provider = aWSCredentialsProvider;
        this.apiKey = str3;
        this.clientConfiguration = clientConfiguration;
        this.client = new UrlHttpClient(clientConfiguration);
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            if (isExecuteMethod(method)) {
                return new ApiResponse(this.client.execute(invokeExecuteMethod(objArr)));
            }
            return handleResponse(this.client.execute(createHttpRequest(method, objArr)), method);
        } catch (ApiClientException e) {
            throw e;
        } catch (Exception e2) {
            throw new ApiClientException(e2.getMessage() == null ? "" : e2.getMessage(), e2);
        }
    }

    HttpRequest createHttpRequest(Method method, Object[] objArr) {
        Request<?> requestBuildRequest = buildRequest(method, objArr);
        ExecutionContext executionContext = new ExecutionContext();
        String str = this.apiName;
        if (requestBuildRequest.getHeaders().containsKey(HttpHeader.USER_AGENT)) {
            str = str + " " + requestBuildRequest.getHeaders().get(HttpHeader.USER_AGENT);
        }
        executionContext.setContextUserAgent(str);
        return this.requestFactory.createHttpRequest(requestBuildRequest, this.clientConfiguration, executionContext);
    }

    Request<?> buildRequest(Method method, Object[] objArr) {
        Signer signer;
        Operation operation = (Operation) method.getAnnotation(Operation.class);
        if (operation == null) {
            throw new IllegalArgumentException("Method isn't annotated with Operation");
        }
        DefaultRequest defaultRequest = new DefaultRequest(this.apiName);
        defaultRequest.setResourcePath(operation.path());
        defaultRequest.setEndpoint(URI.create(this.endpoint));
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        int length = parameterAnnotations.length;
        String json = null;
        for (int i = 0; i < length; i++) {
            Annotation[] annotationArr = parameterAnnotations[i];
            if (annotationArr.length == 0) {
                if (json != null) {
                    throw new IllegalStateException("Can't have more than one Body");
                }
                Object obj = objArr[i];
                json = obj == null ? null : GSON_WITH_DATE_FORMATTER.toJson(obj);
            } else {
                int length2 = annotationArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length2) {
                        Annotation annotation = annotationArr[i2];
                        if (annotation instanceof Parameter) {
                            processParameter(defaultRequest, (Parameter) annotation, objArr[i]);
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
        boolean z = json != null;
        setHttpMethod(defaultRequest, operation.method(), z);
        if (z) {
            byte[] bytes = json.getBytes(StringUtils.UTF8);
            defaultRequest.setContent(new ByteArrayInputStream(bytes));
            defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, String.valueOf(bytes.length));
        }
        defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/json");
        defaultRequest.addHeader(HttpHeader.ACCEPT, "application/json");
        String str = this.apiKey;
        if (str != null) {
            defaultRequest.addHeader("x-api-key", str);
        }
        AWSCredentialsProvider aWSCredentialsProvider = this.provider;
        if (aWSCredentialsProvider != null && (signer = this.signer) != null) {
            signer.sign(defaultRequest, aWSCredentialsProvider.getCredentials());
        }
        return defaultRequest;
    }

    void processParameter(Request<?> request, Parameter parameter, Object obj) {
        String strName = parameter.name();
        String strLocation = parameter.location();
        if (obj == null) {
            return;
        }
        if ("header".equals(strLocation)) {
            request.addHeader(strName, String.valueOf(obj));
            return;
        }
        if ("path".equals(strLocation)) {
            request.setResourcePath(request.getResourcePath().replaceAll("\\{" + strName + "\\}", String.valueOf(obj)));
            return;
        }
        if (SearchIntents.EXTRA_QUERY.equals(strLocation)) {
            if (Map.class.isAssignableFrom(obj.getClass())) {
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    request.addParameter((String) entry.getKey(), String.valueOf(entry.getValue()));
                }
                return;
            }
            if (Collection.class.isAssignableFrom(obj.getClass())) {
                request.addParameter(strName, joinList((Collection) obj));
                return;
            } else {
                request.addParameter(strName, String.valueOf(obj));
                return;
            }
        }
        throw new IllegalArgumentException("unknown parameter location: " + strLocation);
    }

    void setHttpMethod(Request<?> request, String str, boolean z) {
        try {
            request.setHttpMethod(HttpMethodName.valueOf(str));
        } catch (IllegalArgumentException unused) {
            request.addHeader("X-HTTP-Method-Override", str);
            request.setHttpMethod(z ? HttpMethodName.POST : HttpMethodName.GET);
        }
    }

    Object handleResponse(HttpResponse httpResponse, Method method) throws Throwable {
        int statusCode = httpResponse.getStatusCode();
        InputStream content = httpResponse.getContent();
        if (statusCode >= 200 && statusCode < 300) {
            Class<?> returnType = method.getReturnType();
            if (returnType == Void.TYPE || content == null) {
                if (content == null) {
                    return null;
                }
                content.close();
                return null;
            }
            InputStreamReader inputStreamReader = new InputStreamReader(httpResponse.getContent(), StringUtils.UTF8);
            Object objFromJson = GSON_WITH_DATE_FORMATTER.fromJson(inputStreamReader, returnType);
            inputStreamReader.close();
            return objFromJson;
        }
        ApiClientException apiClientException = new ApiClientException(content == null ? "" : IOUtils.toString(content));
        apiClientException.setStatusCode(httpResponse.getStatusCode());
        apiClientException.setServiceName(this.apiName);
        String str = httpResponse.getHeaders().get("x-amzn-RequestId");
        if (str != null) {
            apiClientException.setRequestId(str);
            throw apiClientException;
        }
        throw apiClientException;
    }

    boolean isExecuteMethod(Method method) {
        return ((Operation) method.getAnnotation(Operation.class)) == null && "execute".equalsIgnoreCase(method.getName()) && method.getReturnType().isAssignableFrom(ApiResponse.class) && method.getParameterTypes().length == 1 && method.getParameterTypes()[0].isAssignableFrom(ApiRequest.class);
    }

    HttpRequest invokeExecuteMethod(Object[] objArr) {
        Signer signer;
        ExecutionContext executionContext = new ExecutionContext();
        Request<ApiResponse> request = ((ApiRequest) objArr[0]).getRequest();
        if (request.getEndpoint() == null) {
            request.setEndpoint(URI.create(this.endpoint));
        }
        String str = this.apiName;
        if (request.getHeaders().containsKey(HttpHeader.USER_AGENT)) {
            str = str + " " + request.getHeaders().get(HttpHeader.USER_AGENT);
        }
        executionContext.setContextUserAgent(str);
        String str2 = this.apiKey;
        if (str2 != null) {
            request.addHeader("x-api-key", str2);
        }
        AWSCredentialsProvider aWSCredentialsProvider = this.provider;
        if (aWSCredentialsProvider != null && (signer = this.signer) != null) {
            signer.sign(request, aWSCredentialsProvider.getCredentials());
        }
        return this.requestFactory.createHttpRequest(request, this.clientConfiguration, executionContext);
    }

    private String joinList(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object obj : collection) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            sb.append(obj);
        }
        return sb.toString();
    }

    void setClient(HttpClient httpClient) {
        this.client = httpClient;
    }
}
