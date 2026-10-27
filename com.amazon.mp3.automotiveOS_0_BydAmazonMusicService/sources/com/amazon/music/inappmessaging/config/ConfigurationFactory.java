package com.amazon.music.inappmessaging.config;

import com.amazon.music.marketplace.Marketplace;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ConfigurationFactory {
    private static final String APP_CONFIG_ID = "arn:aws:remote-config:us-west-2:959793350288:appConfig:a9mu41c8";
    private static long DEFAULT_LATENCY_LIMIT = 0;
    private static final String ENDPOINT_URL_KEY = "iam_endpoint_url";
    private static final String LATENCY_LIMIT_KEY = "iam_latency_limit";
    private static Map<String, String> endpointsByMarketplace;
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) ConfigurationFactory.class);
    private static String NA_ENDPOINT = "https://268mhmtxhj.execute-api.us-east-1.amazonaws.com";
    private static String EU_ENDPOINT = "https://n8sniab5rg.execute-api.eu-west-1.amazonaws.com";
    private static String FE_ENDPOINT = "https://hqsifuaqy4.execute-api.us-west-2.amazonaws.com";
    private static String GAMMA_ENDPOINT = "https://a6f3wfjt7a.execute-api.us-west-2.amazonaws.com";

    static {
        HashMap map = new HashMap();
        endpointsByMarketplace = map;
        map.put(Marketplace.USA.getObfuscatedId(), NA_ENDPOINT);
        endpointsByMarketplace.put(Marketplace.UK.getObfuscatedId(), EU_ENDPOINT);
        endpointsByMarketplace.put(Marketplace.GERMANY.getObfuscatedId(), EU_ENDPOINT);
        endpointsByMarketplace.put(Marketplace.FRANCE.getObfuscatedId(), EU_ENDPOINT);
        endpointsByMarketplace.put(Marketplace.ITALY.getObfuscatedId(), EU_ENDPOINT);
        endpointsByMarketplace.put(Marketplace.SPAIN.getObfuscatedId(), EU_ENDPOINT);
        endpointsByMarketplace.put(Marketplace.JAPAN.getObfuscatedId(), FE_ENDPOINT);
        endpointsByMarketplace.put(Marketplace.ROW_FE.getObfuscatedId(), FE_ENDPOINT);
        endpointsByMarketplace.put(Marketplace.ROW_NA.getObfuscatedId(), NA_ENDPOINT);
        endpointsByMarketplace.put(Marketplace.ROE_EU.getObfuscatedId(), EU_ENDPOINT);
        DEFAULT_LATENCY_LIMIT = 10000L;
    }

    public Configuration create(final String marketplace, final boolean shouldUseGammaOverride) throws IllegalStateException {
        long j = DEFAULT_LATENCY_LIMIT;
        String str = shouldUseGammaOverride ? GAMMA_ENDPOINT : endpointsByMarketplace.get(marketplace);
        if (str == null) {
            String str2 = "Marketplace " + marketplace + " not supported for IAM";
            LOG.error(str2);
            throw new IllegalStateException(str2);
        }
        return new Configuration(Long.valueOf(j), str, shouldUseGammaOverride);
    }
}
