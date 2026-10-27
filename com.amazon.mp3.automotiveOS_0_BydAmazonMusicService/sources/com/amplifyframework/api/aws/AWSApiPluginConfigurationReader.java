package com.amplifyframework.api.aws;

import com.amazon.mp3.configuration.IntlConfiguration;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.AWSApiPluginConfiguration;
import com.amplifyframework.api.aws.ApiConfiguration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
final class AWSApiPluginConfigurationReader {
    private AWSApiPluginConfigurationReader() {
    }

    static AWSApiPluginConfiguration readFrom(JSONObject jSONObject) throws ApiException {
        if (jSONObject == null) {
            throw new ApiException("Null configuration JSON provided to AWS API plugin.", "Check that the content of the AWS API Plugin section of the amplifyconfiguration.json file hasn't been accidentally deleted.");
        }
        return parseConfigurationJson(jSONObject);
    }

    private static AWSApiPluginConfiguration parseConfigurationJson(JSONObject jSONObject) throws ApiException {
        AWSApiPluginConfiguration.Builder builder = AWSApiPluginConfiguration.builder();
        try {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                for (String str : ConfigKey.requiredKeys()) {
                    if (!jSONObject2.has(str)) {
                        throw new ApiException("Failed to parse configuration, missing required key: " + str, AmplifyException.TODO_RECOVERY_SUGGESTION);
                    }
                }
                ApiConfiguration.Builder builderAuthorizationType = ApiConfiguration.builder().endpointType(EndpointType.from(jSONObject2.getString(ConfigKey.ENDPOINT_TYPE.key()))).endpoint(jSONObject2.getString(ConfigKey.ENDPOINT.key())).region(jSONObject2.getString(ConfigKey.REGION.key())).authorizationType(AuthorizationType.from(jSONObject2.getString(ConfigKey.AUTHORIZATION_TYPE.key())));
                ConfigKey configKey = ConfigKey.API_KEY;
                if (jSONObject2.has(configKey.key())) {
                    builderAuthorizationType.apiKey(jSONObject2.getString(configKey.key()));
                }
                builder.addApi(next, builderAuthorizationType.build());
            }
            return builder.build();
        } catch (ApiException | JSONException e) {
            throw new ApiException("Failed to parse configuration JSON for AWS API Plugin", e, "Check amplifyconfiguration.json to make sure the AWS API configuration section hasn't been wrongly modified.");
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ENDPOINT_TYPE' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    static final class ConfigKey {
        private static final /* synthetic */ ConfigKey[] $VALUES;
        public static final ConfigKey API_KEY;
        public static final ConfigKey AUTHORIZATION_TYPE;
        public static final ConfigKey ENDPOINT;
        public static final ConfigKey ENDPOINT_TYPE;
        public static final ConfigKey REGION;
        private final Importance importance;
        private final String key;

        enum Importance {
            REQUIRED,
            OPTIONAL
        }

        public static ConfigKey valueOf(String str) {
            return (ConfigKey) Enum.valueOf(ConfigKey.class, str);
        }

        public static ConfigKey[] values() {
            return (ConfigKey[]) $VALUES.clone();
        }

        static {
            Importance importance = Importance.REQUIRED;
            ConfigKey configKey = new ConfigKey("ENDPOINT_TYPE", 0, "endpointType", importance);
            ENDPOINT_TYPE = configKey;
            ConfigKey configKey2 = new ConfigKey("ENDPOINT", 1, IntlConfiguration.ENDPOINT_T, importance);
            ENDPOINT = configKey2;
            ConfigKey configKey3 = new ConfigKey("REGION", 2, "region", importance);
            REGION = configKey3;
            ConfigKey configKey4 = new ConfigKey("AUTHORIZATION_TYPE", 3, "authorizationType", importance);
            AUTHORIZATION_TYPE = configKey4;
            ConfigKey configKey5 = new ConfigKey("API_KEY", 4, "apiKey", Importance.OPTIONAL);
            API_KEY = configKey5;
            $VALUES = new ConfigKey[]{configKey, configKey2, configKey3, configKey4, configKey5};
        }

        private ConfigKey(String str, int i, String str2, Importance importance) {
            this.key = str2;
            this.importance = importance;
        }

        String key() {
            return this.key;
        }

        boolean isRequired() {
            return Importance.REQUIRED.equals(this.importance);
        }

        static Set<String> requiredKeys() {
            HashSet hashSet = new HashSet();
            for (ConfigKey configKey : values()) {
                if (configKey.isRequired()) {
                    hashSet.add(configKey.key());
                }
            }
            return Collections.unmodifiableSet(hashSet);
        }

        static Set<String> optionalKeys() {
            HashSet hashSet = new HashSet();
            for (ConfigKey configKey : values()) {
                if (!configKey.isRequired()) {
                    hashSet.add(configKey.key());
                }
            }
            return Collections.unmodifiableSet(hashSet);
        }
    }
}
