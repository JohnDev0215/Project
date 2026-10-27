package com.amplifyframework.rx;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.AnalyticsCategory;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.AmplifyConfiguration;
import com.amplifyframework.core.plugin.Plugin;
import com.amplifyframework.logging.LoggingCategory;

/* JADX INFO: loaded from: classes5.dex */
public final class RxAmplify {
    public static final AnalyticsCategory Analytics = new AnalyticsCategory();
    public static final RxApiCategoryBehavior API = new RxApiBinding();
    public static final RxAuthCategoryBehavior Auth = new RxAuthBinding();
    public static final LoggingCategory Logging = new LoggingCategory();
    public static final RxStorageCategoryBehavior Storage = new RxStorageBinding();
    public static final RxHubCategoryBehavior Hub = new RxHubBinding();
    public static final RxDataStoreCategoryBehavior DataStore = new RxDataStoreBinding();
    public static final RxPredictionsCategoryBehavior Predictions = new RxPredictionsBinding();

    private RxAmplify() {
    }

    public static void configure(@NonNull Context context) throws AmplifyException {
        Amplify.configure(context);
    }

    public static void configure(@NonNull AmplifyConfiguration amplifyConfiguration, @NonNull Context context) throws AmplifyException {
        Amplify.configure(amplifyConfiguration, context);
    }

    public static <P extends Plugin<?>> void addPlugin(@NonNull P p) throws AmplifyException {
        Amplify.addPlugin(p);
    }

    public static <P extends Plugin<?>> void removePlugin(@NonNull P p) throws AmplifyException {
        Amplify.removePlugin(p);
    }
}
