package com.amplifyframework.core;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.AnalyticsCategory;
import com.amplifyframework.api.ApiCategory;
import com.amplifyframework.auth.AuthCategory;
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.core.plugin.Plugin;
import com.amplifyframework.datastore.DataStoreCategory;
import com.amplifyframework.devmenu.DeveloperMenu;
import com.amplifyframework.geo.GeoCategory;
import com.amplifyframework.hub.HubCategory;
import com.amplifyframework.logging.LoggingCategory;
import com.amplifyframework.predictions.PredictionsCategory;
import com.amplifyframework.storage.StorageCategory;
import com.amplifyframework.util.Empty;
import com.amplifyframework.util.Immutable;
import com.amplifyframework.util.UserAgent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class Amplify {
    private static final LinkedHashMap<CategoryType, Category<? extends Plugin<?>>> CATEGORIES;
    private static final AtomicBoolean CONFIGURATION_LOCK;
    private static final ExecutorService INITIALIZATION_POOL;
    public static final AnalyticsCategory Analytics = new AnalyticsCategory();
    public static final ApiCategory API = new ApiCategory();
    public static final AuthCategory Auth = new AuthCategory();
    public static final LoggingCategory Logging = new LoggingCategory();
    public static final StorageCategory Storage = new StorageCategory();
    public static final GeoCategory Geo = new GeoCategory();
    public static final HubCategory Hub = new HubCategory();
    public static final DataStoreCategory DataStore = new DataStoreCategory();
    public static final PredictionsCategory Predictions = new PredictionsCategory();

    private enum RegistryUpdateType {
        ADD,
        REMOVE
    }

    static {
        LinkedHashMap<CategoryType, Category<? extends Plugin<?>>> linkedHashMapBuildCategoriesMap = buildCategoriesMap();
        CATEGORIES = linkedHashMapBuildCategoriesMap;
        CONFIGURATION_LOCK = new AtomicBoolean(false);
        INITIALIZATION_POOL = Executors.newFixedThreadPool(linkedHashMapBuildCategoriesMap.size());
    }

    private Amplify() {
        throw new UnsupportedOperationException("No instances allowed.");
    }

    private static LinkedHashMap<CategoryType, Category<? extends Plugin<?>>> buildCategoriesMap() {
        LinkedHashMap<CategoryType, Category<? extends Plugin<?>>> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(CategoryType.AUTH, Auth);
        linkedHashMap.put(CategoryType.ANALYTICS, Analytics);
        linkedHashMap.put(CategoryType.API, API);
        linkedHashMap.put(CategoryType.LOGGING, Logging);
        linkedHashMap.put(CategoryType.STORAGE, Storage);
        linkedHashMap.put(CategoryType.GEO, Geo);
        linkedHashMap.put(CategoryType.HUB, Hub);
        linkedHashMap.put(CategoryType.DATASTORE, DataStore);
        linkedHashMap.put(CategoryType.PREDICTIONS, Predictions);
        return linkedHashMap;
    }

    public static Map<CategoryType, Category<? extends Plugin<?>>> getCategoriesMap() {
        return Immutable.of(CATEGORIES);
    }

    public static void configure(@NonNull Context context) throws AmplifyException {
        configure(AmplifyConfiguration.fromConfigFile(context), context);
    }

    public static void configure(@NonNull AmplifyConfiguration amplifyConfiguration, @NonNull Context context) throws AmplifyException {
        Objects.requireNonNull(amplifyConfiguration);
        Objects.requireNonNull(context);
        AtomicBoolean atomicBoolean = CONFIGURATION_LOCK;
        synchronized (atomicBoolean) {
            if (atomicBoolean.get()) {
                throw new AlreadyConfiguredException("Remove the duplicate call to `Amplify.configure()`.");
            }
            UserAgent.configure(amplifyConfiguration.getPlatformVersions());
            if (amplifyConfiguration.isDevMenuEnabled()) {
                DeveloperMenu.singletonInstance(context).enableDeveloperMenu();
            }
            for (Category<? extends Plugin<?>> category : CATEGORIES.values()) {
                if (category.getPlugins().size() > 0) {
                    category.configure(amplifyConfiguration.forCategoryType(category.getCategoryType()), context);
                    beginInitialization(category, context);
                }
            }
            CONFIGURATION_LOCK.set(true);
        }
    }

    private static void beginInitialization(@NonNull final Category<? extends Plugin<?>> category, @NonNull final Context context) {
        INITIALIZATION_POOL.execute(new Runnable() { // from class: com.amplifyframework.core.Amplify$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                category.initialize(context);
            }
        });
    }

    public static <P extends Plugin<?>> void addPlugin(@NonNull P p) throws AmplifyException {
        updatePluginRegistry(p, RegistryUpdateType.ADD);
    }

    public static <P extends Plugin<?>> void removePlugin(@NonNull P p) throws AmplifyException {
        updatePluginRegistry(p, RegistryUpdateType.REMOVE);
    }

    private static <P extends Plugin<?>> void updatePluginRegistry(P p, RegistryUpdateType registryUpdateType) throws AmplifyException {
        AtomicBoolean atomicBoolean = CONFIGURATION_LOCK;
        synchronized (atomicBoolean) {
            Category<? extends Plugin<?>> category = null;
            byte b = 0;
            if (atomicBoolean.get()) {
                throw new AlreadyConfiguredException("Do not add plugins after calling `Amplify.configure()`.");
            }
            if (Empty.check(p.getPluginKey())) {
                throw new AmplifyException("Plugin key was missing for + " + p.getClass().getSimpleName(), "This should never happen - contact the plugin developers to find out why this is.");
            }
            LinkedHashMap<CategoryType, Category<? extends Plugin<?>>> linkedHashMap = CATEGORIES;
            if (!linkedHashMap.containsKey(p.getCategoryType())) {
                throw new AmplifyException("Plugin category does not exist. ", "Verify that the library version is correct and supports the plugin's category.");
            }
            try {
                category = linkedHashMap.get(p.getCategoryType());
            } catch (ClassCastException unused) {
            }
            if (category == null) {
                throw new AmplifyException("A plugin is being added to the wrong category", AmplifyException.TODO_RECOVERY_SUGGESTION);
            }
            if (RegistryUpdateType.REMOVE.equals(registryUpdateType)) {
                category.removePlugin(p);
            } else {
                category.addPlugin(p);
            }
        }
    }

    public static final class AlreadyConfiguredException extends AmplifyException {
        private static final long serialVersionUID = 1;

        private AlreadyConfiguredException(@NonNull String str) {
            super("Amplify has already been configured.", str);
        }
    }
}
