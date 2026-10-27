package com.amplifyframework.core;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.AnalyticsCategoryConfiguration;
import com.amplifyframework.api.ApiCategoryConfiguration;
import com.amplifyframework.auth.AuthCategoryConfiguration;
import com.amplifyframework.core.Resources;
import com.amplifyframework.core.category.CategoryConfiguration;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.core.category.EmptyCategoryConfiguration;
import com.amplifyframework.datastore.DataStoreCategoryConfiguration;
import com.amplifyframework.geo.GeoCategoryConfiguration;
import com.amplifyframework.hub.HubCategoryConfiguration;
import com.amplifyframework.logging.LoggingCategoryConfiguration;
import com.amplifyframework.predictions.PredictionsCategoryConfiguration;
import com.amplifyframework.storage.StorageCategoryConfiguration;
import com.amplifyframework.util.Immutable;
import com.amplifyframework.util.UserAgent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class AmplifyConfiguration {
    private static final String DEFAULT_IDENTIFIER = "amplifyconfiguration";
    private final Map<String, CategoryConfiguration> categoryConfigurations;
    private final boolean devMenuEnabled;
    private final Map<UserAgent.Platform, String> platformVersions;

    @VisibleForTesting
    public AmplifyConfiguration(@NonNull Map<String, CategoryConfiguration> map) {
        this(map, new LinkedHashMap(), false);
    }

    @VisibleForTesting
    public AmplifyConfiguration(@NonNull Map<String, CategoryConfiguration> map, boolean z) {
        this(map, new LinkedHashMap(), z);
    }

    private AmplifyConfiguration(Map<String, CategoryConfiguration> map, Map<UserAgent.Platform, String> map2, boolean z) {
        HashMap map3 = new HashMap();
        this.categoryConfigurations = map3;
        map3.putAll(map);
        this.platformVersions = map2;
        this.devMenuEnabled = z;
    }

    @NonNull
    public static AmplifyConfiguration fromJson(@NonNull JSONObject jSONObject) throws AmplifyException {
        return builder(jSONObject).build();
    }

    @NonNull
    public static AmplifyConfiguration fromConfigFile(@NonNull Context context) throws AmplifyException {
        return builder(context).build();
    }

    @NonNull
    public static AmplifyConfiguration fromConfigFile(@NonNull Context context, @RawRes int i) throws AmplifyException {
        return builder(context, i).build();
    }

    @NonNull
    public Map<UserAgent.Platform, String> getPlatformVersions() {
        return Immutable.of(this.platformVersions);
    }

    public boolean isDevMenuEnabled() {
        return this.devMenuEnabled;
    }

    private static Map<String, CategoryConfiguration> configsFromJson(JSONObject jSONObject) throws AmplifyException {
        List<CategoryConfiguration> listAsList = Arrays.asList(new AnalyticsCategoryConfiguration(), new ApiCategoryConfiguration(), new AuthCategoryConfiguration(), new DataStoreCategoryConfiguration(), new GeoCategoryConfiguration(), new HubCategoryConfiguration(), new LoggingCategoryConfiguration(), new PredictionsCategoryConfiguration(), new StorageCategoryConfiguration());
        HashMap map = new HashMap();
        try {
            for (CategoryConfiguration categoryConfiguration : listAsList) {
                String configurationKey = categoryConfiguration.getCategoryType().getConfigurationKey();
                if (jSONObject.has(configurationKey)) {
                    categoryConfiguration.populateFromJSON(jSONObject.getJSONObject(configurationKey));
                    map.put(configurationKey, categoryConfiguration);
                }
            }
            return Immutable.of(map);
        } catch (JSONException e) {
            throw new AmplifyException("Could not parse amplifyconfiguration.json ", e, "Check any modifications made to the file.");
        }
    }

    @NonNull
    public CategoryConfiguration forCategoryType(@NonNull CategoryType categoryType) {
        CategoryConfiguration categoryConfiguration = this.categoryConfigurations.get(categoryType.getConfigurationKey());
        return categoryConfiguration == null ? EmptyCategoryConfiguration.forCategoryType(categoryType) : categoryConfiguration;
    }

    @NonNull
    public static Builder builder(@NonNull Context context) throws AmplifyException {
        try {
            return builder(context, Resources.getRawResourceId(context, DEFAULT_IDENTIFIER));
        } catch (Resources.ResourceLoadingException e) {
            throw new AmplifyException("Failed to load the amplifyconfiguration configuration file.", e, "Is there an Amplify configuration file present in your app project, under ./app/src/main/res/raw/amplifyconfiguration?");
        }
    }

    @NonNull
    public static Builder builder(@NonNull Context context, @RawRes int i) throws AmplifyException {
        Objects.requireNonNull(context);
        try {
            return builder(Resources.readJsonResourceFromId(context, i));
        } catch (Resources.ResourceLoadingException e) {
            throw new AmplifyException("Failed to read JSON from resource = " + i, e, "If you are attempting to load a custom configuration file, please ensure that it exists in your application project under app/src/main/res/raw/<YOUR_CUSTOM_CONFIG_FILE>.");
        }
    }

    @NonNull
    public static Builder builder(@NonNull JSONObject jSONObject) throws AmplifyException {
        Objects.requireNonNull(jSONObject);
        return new Builder(configsFromJson(jSONObject));
    }

    public static final class Builder {
        private final Map<String, CategoryConfiguration> categoryConfiguration;
        private boolean devMenuEnabled;
        private final Map<UserAgent.Platform, String> platformVersions;

        private Builder(Map<String, CategoryConfiguration> map) {
            this.devMenuEnabled = false;
            this.categoryConfiguration = map;
            this.platformVersions = new LinkedHashMap();
        }

        @NonNull
        public Builder addPlatform(@NonNull UserAgent.Platform platform, @NonNull String str) {
            if (!UserAgent.Platform.ANDROID.equals(platform)) {
                Map<UserAgent.Platform, String> map = this.platformVersions;
                Objects.requireNonNull(platform);
                Objects.requireNonNull(str);
                map.put(platform, str);
            }
            return this;
        }

        @NonNull
        public Builder devMenuEnabled(boolean z) {
            this.devMenuEnabled = z;
            return this;
        }

        @NonNull
        public AmplifyConfiguration build() {
            return new AmplifyConfiguration(this.categoryConfiguration, this.platformVersions, this.devMenuEnabled);
        }
    }
}
