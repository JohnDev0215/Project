package com.android.car.developeroptions.overlay;

import android.content.Context;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.accounts.AccountFeatureProvider;
import com.android.car.developeroptions.applications.ApplicationFeatureProvider;
import com.android.car.developeroptions.aware.AwareFeatureProvider;
import com.android.car.developeroptions.bluetooth.BluetoothFeatureProvider;
import com.android.car.developeroptions.dashboard.DashboardFeatureProvider;
import com.android.car.developeroptions.dashboard.suggestions.SuggestionFeatureProvider;
import com.android.car.developeroptions.enterprise.EnterprisePrivacyFeatureProvider;
import com.android.car.developeroptions.fuelgauge.PowerUsageFeatureProvider;
import com.android.car.developeroptions.gestures.AssistGestureFeatureProvider;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardFeatureProvider;
import com.android.car.developeroptions.localepicker.LocaleFeatureProvider;
import com.android.car.developeroptions.panel.PanelFeatureProvider;
import com.android.car.developeroptions.search.SearchFeatureProvider;
import com.android.car.developeroptions.security.SecurityFeatureProvider;
import com.android.car.developeroptions.slices.SlicesFeatureProvider;
import com.android.car.developeroptions.users.UserFeatureProvider;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public abstract class FeatureFactory {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "FeatureFactory";
    protected static Context sAppContext;
    protected static FeatureFactory sFactory;

    public abstract AccountFeatureProvider getAccountFeatureProvider();

    public abstract ApplicationFeatureProvider getApplicationFeatureProvider(Context context);

    public abstract AssistGestureFeatureProvider getAssistGestureFeatureProvider();

    public abstract AwareFeatureProvider getAwareFeatureProvider();

    public abstract BluetoothFeatureProvider getBluetoothFeatureProvider(Context context);

    public abstract ContextualCardFeatureProvider getContextualCardFeatureProvider(Context context);

    public abstract DashboardFeatureProvider getDashboardFeatureProvider(Context context);

    public abstract DockUpdaterFeatureProvider getDockUpdaterFeatureProvider();

    public abstract EnterprisePrivacyFeatureProvider getEnterprisePrivacyFeatureProvider(Context context);

    public abstract LocaleFeatureProvider getLocaleFeatureProvider();

    public abstract MetricsFeatureProvider getMetricsFeatureProvider();

    public abstract PanelFeatureProvider getPanelFeatureProvider();

    public abstract PowerUsageFeatureProvider getPowerUsageFeatureProvider(Context context);

    public abstract SearchFeatureProvider getSearchFeatureProvider();

    public abstract SecurityFeatureProvider getSecurityFeatureProvider();

    public abstract SlicesFeatureProvider getSlicesFeatureProvider();

    public abstract SuggestionFeatureProvider getSuggestionFeatureProvider(Context context);

    public abstract SupportFeatureProvider getSupportFeatureProvider(Context context);

    public abstract SurveyFeatureProvider getSurveyFeatureProvider(Context context);

    public abstract UserFeatureProvider getUserFeatureProvider(Context context);

    public static FeatureFactory getFactory(Context context) {
        FeatureFactory featureFactory = sFactory;
        if (featureFactory != null) {
            return featureFactory;
        }
        if (sAppContext == null) {
            sAppContext = context.getApplicationContext();
        }
        String string = context.getString(R.string.config_featureFactory);
        if (TextUtils.isEmpty(string)) {
            throw new UnsupportedOperationException("No feature factory configured");
        }
        try {
            FeatureFactory featureFactory2 = (FeatureFactory) context.getClassLoader().loadClass(string).newInstance();
            sFactory = featureFactory2;
            return featureFactory2;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new FactoryNotFoundException(e);
        }
    }

    public static Context getAppContext() {
        return sAppContext;
    }

    public static final class FactoryNotFoundException extends RuntimeException {
        public FactoryNotFoundException(Throwable th) {
            super("Unable to create factory. Did you misconfigure Proguard?", th);
        }
    }
}
