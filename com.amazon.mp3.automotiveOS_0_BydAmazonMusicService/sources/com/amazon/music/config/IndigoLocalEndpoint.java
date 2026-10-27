package com.amazon.music.config;

import android.content.Context;
import com.amazon.music.config.IndigoConfiguration;
import java.util.HashMap;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
final class IndigoLocalEndpoint implements IndigoConfiguration.Endpoint {
    private static final Logger LOG = LoggerFactory.getLogger(IndigoLocalEndpoint.class.getSimpleName());
    private final Attributes mApplicationAttributes;
    private JSONObject mConfiguration;
    private final Context mContext;
    private final HashMap<String, String> mKeyValueMap = new HashMap<>(256);
    private final ConfigurationLoader mRemoteConfigLoader;

    public static String getStage() {
        return "prod";
    }

    IndigoLocalEndpoint(Context context, ConfigurationLoader configurationLoader, Attributes attributes) {
        this.mContext = context.getApplicationContext();
        this.mRemoteConfigLoader = configurationLoader;
        this.mApplicationAttributes = attributes;
        loadConfigurationPropertiesFile(true);
    }

    @Override // com.amazon.music.config.IndigoConfiguration.Endpoint
    public synchronized String getValue(String str) {
        return this.mKeyValueMap.get(str);
    }

    private void loadConfigurationPropertiesFile(boolean z) {
        if (z) {
            this.mConfiguration = this.mRemoteConfigLoader.loadFromDiskCache(ConfigManager.getCachedRemoteConfigFile(this.mContext));
        }
        if (this.mConfiguration == null) {
            this.mConfiguration = this.mApplicationAttributes.getDefaultConfigJSON();
            LOG.info("Attempted to load bundled local config");
        }
        if (this.mConfiguration == null) {
            throw new RuntimeException("Unable to load configuration file!");
        }
        reloadConfiguration();
    }

    private void reloadConfiguration() {
        applyConfiguration(buildConfigurationHierarchy(String.format("config.%s_%s", this.mApplicationAttributes.getLocaleLanguage(), this.mApplicationAttributes.getLocaleCountryCode())));
    }

    private Stack<JSONObject> buildConfigurationHierarchy(String str) {
        Stack<JSONObject> stack = new Stack<>();
        int iLastIndexOf = -1;
        do {
            if (iLastIndexOf != -1) {
                str = str.substring(0, iLastIndexOf);
            }
            try {
                stack.push(this.mConfiguration.getJSONObject(str));
            } catch (JSONException unused) {
            }
            iLastIndexOf = str.lastIndexOf(46);
        } while (iLastIndexOf > -1);
        return stack;
    }

    private void applyConfiguration(Stack<JSONObject> stack) {
        while (!stack.empty()) {
            mergeConfiguration(stack.pop());
        }
    }

    private void mergeConfiguration(JSONObject jSONObject) {
        JSONArray jSONArrayNames = jSONObject.names();
        if (jSONArrayNames != null) {
            for (int i = 0; i < jSONArrayNames.length(); i++) {
                try {
                    String string = jSONArrayNames.getString(i);
                    this.mKeyValueMap.put(string, jSONObject.getString(string));
                } catch (JSONException unused) {
                    throw new RuntimeException("Failed to merge configuration namespace!");
                }
            }
        }
    }
}
