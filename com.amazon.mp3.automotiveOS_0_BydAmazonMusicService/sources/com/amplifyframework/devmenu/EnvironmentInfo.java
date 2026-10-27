package com.amplifyframework.devmenu;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Resources;
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.plugin.Plugin;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class EnvironmentInfo {
    private static final String DEV_ENV_INFO_FILE_NAME = "localenvinfo";
    private final Map<String, String> devEnvironmentItems;

    public EnvironmentInfo() {
        HashMap map = new HashMap();
        this.devEnvironmentItems = map;
        map.put("nodejsVersion", "Node.js Version");
        map.put("npmVersion", "NPM Version");
        map.put("amplifyCliVersion", "Amplify CLI Version");
        map.put("androidStudioVersion", "Android Studio Version");
        map.put("osName", "OS");
        map.put("osVersion", "OS Version");
    }

    public String getPluginVersions() {
        StringBuilder sb = new StringBuilder();
        Iterator<Category<? extends Plugin<?>>> it = Amplify.getCategoriesMap().values().iterator();
        while (it.hasNext()) {
            sb.append(getCategoryPluginVersions(it.next()));
        }
        String string = sb.toString();
        return string.isEmpty() ? "No plugins added." : string;
    }

    public String getDeveloperEnvironmentInfo(@NonNull Context context) throws AmplifyException {
        Objects.requireNonNull(context);
        try {
            JSONObject jsonResource = Resources.readJsonResource(context.getApplicationContext(), DEV_ENV_INFO_FILE_NAME);
            StringBuilder sb = new StringBuilder();
            for (String str : this.devEnvironmentItems.keySet()) {
                if (jsonResource.has(str)) {
                    try {
                        sb.append(this.devEnvironmentItems.get(str) + ": " + jsonResource.getString(str) + "\n");
                    } catch (JSONException e) {
                        throw new AmplifyException("Error reading the developer environment information.", e, "Check that localenvinfo.json is properly formatted");
                    }
                }
            }
            return sb.toString();
        } catch (Resources.ResourceLoadingException e2) {
            throw new AmplifyException("Failed to find localenvinfo.", e2, "Please ensure it is present in your project.");
        }
    }

    private String getCategoryPluginVersions(Category<?> category) {
        StringBuilder sb = new StringBuilder();
        for (P p : category.getPlugins()) {
            sb.append(p.getPluginKey() + ": " + p.getVersion() + "\n");
        }
        return sb.toString();
    }
}
