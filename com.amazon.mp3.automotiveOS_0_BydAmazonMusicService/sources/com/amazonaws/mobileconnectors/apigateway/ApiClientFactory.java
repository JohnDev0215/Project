package com.amazonaws.mobileconnectors.apigateway;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.Signer;
import com.amazonaws.mobileconnectors.apigateway.annotation.Service;
import java.lang.reflect.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public class ApiClientFactory {
    private static final String AMAZON_API_GATEWAY_SERVICE_NAME = "execute-api";
    private static final Pattern ENDPOINT_PATTERN = Pattern.compile("^https?://\\w+.execute-api.([a-z0-9-]+).amazonaws.com/.*");
    private String apiKey;
    private ClientConfiguration clientConfiguration;
    private String endpoint;
    private AWSCredentialsProvider provider;
    private String regionOverride;

    public ApiClientFactory endpoint(String str) {
        this.endpoint = str;
        return this;
    }

    public ApiClientFactory apiKey(String str) {
        this.apiKey = str;
        return this;
    }

    public ApiClientFactory region(String str) {
        this.regionOverride = str;
        return this;
    }

    public ApiClientFactory clientConfiguration(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
        return this;
    }

    public ApiClientFactory credentialsProvider(AWSCredentialsProvider aWSCredentialsProvider) {
        this.provider = aWSCredentialsProvider;
        return this;
    }

    public <T> T build(Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Missing API class");
        }
        String endpoint = getEndpoint(cls);
        if (endpoint == null) {
            throw new IllegalArgumentException("Missing endpoint information");
        }
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, getHandler(endpoint, getApiName(cls))));
    }

    ApiClientHandler getHandler(String str, String str2) {
        Signer signer = this.provider == null ? null : getSigner(getRegion(str));
        ClientConfiguration clientConfiguration = this.clientConfiguration;
        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }
        return new ApiClientHandler(str, str2, signer, this.provider, this.apiKey, clientConfiguration);
    }

    String getEndpoint(Class<?> cls) {
        Service service = (Service) cls.getAnnotation(Service.class);
        if (service == null) {
            throw new IllegalArgumentException("Can't find annotation Service");
        }
        String str = this.endpoint;
        return str == null ? service.endpoint() : str;
    }

    String getApiName(Class<?> cls) {
        return cls.getSimpleName();
    }

    Signer getSigner(String str) {
        AWS4Signer aWS4Signer = new AWS4Signer();
        aWS4Signer.setServiceName(AMAZON_API_GATEWAY_SERVICE_NAME);
        aWS4Signer.setRegionName(str);
        return aWS4Signer;
    }

    String getRegion(String str) {
        String str2 = this.regionOverride;
        if (str2 != null) {
            return str2;
        }
        Matcher matcher = ENDPOINT_PATTERN.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Region isn't specified and can't be deduced from endpoint.");
    }
}
