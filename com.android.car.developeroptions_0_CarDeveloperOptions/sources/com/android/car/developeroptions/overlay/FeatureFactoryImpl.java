package com.android.car.developeroptions.overlay;

import android.app.AppGlobals;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.UserManager;
import androidx.annotation.Keep;
import com.android.car.developeroptions.accounts.AccountFeatureProvider;
import com.android.car.developeroptions.accounts.AccountFeatureProviderImpl;
import com.android.car.developeroptions.applications.ApplicationFeatureProvider;
import com.android.car.developeroptions.applications.ApplicationFeatureProviderImpl;
import com.android.car.developeroptions.aware.AwareFeatureProvider;
import com.android.car.developeroptions.aware.AwareFeatureProviderImpl;
import com.android.car.developeroptions.bluetooth.BluetoothFeatureProvider;
import com.android.car.developeroptions.bluetooth.BluetoothFeatureProviderImpl;
import com.android.car.developeroptions.connecteddevice.dock.DockUpdaterFeatureProviderImpl;
import com.android.car.developeroptions.core.instrumentation.SettingsMetricsFeatureProvider;
import com.android.car.developeroptions.dashboard.DashboardFeatureProvider;
import com.android.car.developeroptions.dashboard.DashboardFeatureProviderImpl;
import com.android.car.developeroptions.dashboard.suggestions.SuggestionFeatureProvider;
import com.android.car.developeroptions.dashboard.suggestions.SuggestionFeatureProviderImpl;
import com.android.car.developeroptions.enterprise.EnterprisePrivacyFeatureProvider;
import com.android.car.developeroptions.enterprise.EnterprisePrivacyFeatureProviderImpl;
import com.android.car.developeroptions.fuelgauge.PowerUsageFeatureProvider;
import com.android.car.developeroptions.fuelgauge.PowerUsageFeatureProviderImpl;
import com.android.car.developeroptions.gestures.AssistGestureFeatureProvider;
import com.android.car.developeroptions.gestures.AssistGestureFeatureProviderImpl;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardFeatureProvider;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardFeatureProviderImpl;
import com.android.car.developeroptions.localepicker.LocaleFeatureProvider;
import com.android.car.developeroptions.localepicker.LocaleFeatureProviderImpl;
import com.android.car.developeroptions.panel.PanelFeatureProvider;
import com.android.car.developeroptions.panel.PanelFeatureProviderImpl;
import com.android.car.developeroptions.search.SearchFeatureProvider;
import com.android.car.developeroptions.search.SearchFeatureProviderImpl;
import com.android.car.developeroptions.security.SecurityFeatureProvider;
import com.android.car.developeroptions.security.SecurityFeatureProviderImpl;
import com.android.car.developeroptions.slices.SlicesFeatureProvider;
import com.android.car.developeroptions.slices.SlicesFeatureProviderImpl;
import com.android.car.developeroptions.users.UserFeatureProvider;
import com.android.car.developeroptions.users.UserFeatureProviderImpl;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class FeatureFactoryImpl extends FeatureFactory {
    private AccountFeatureProvider mAccountFeatureProvider;
    private ApplicationFeatureProvider mApplicationFeatureProvider;
    private AssistGestureFeatureProvider mAssistGestureFeatureProvider;
    private AwareFeatureProvider mAwareFeatureProvider;
    private BluetoothFeatureProvider mBluetoothFeatureProvider;
    private ContextualCardFeatureProvider mContextualCardFeatureProvider;
    private DashboardFeatureProviderImpl mDashboardFeatureProvider;
    private DockUpdaterFeatureProvider mDockUpdaterFeatureProvider;
    private EnterprisePrivacyFeatureProvider mEnterprisePrivacyFeatureProvider;
    private LocaleFeatureProvider mLocaleFeatureProvider;
    private MetricsFeatureProvider mMetricsFeatureProvider;
    private PanelFeatureProvider mPanelFeatureProvider;
    private PowerUsageFeatureProvider mPowerUsageFeatureProvider;
    private SearchFeatureProvider mSearchFeatureProvider;
    private SecurityFeatureProvider mSecurityFeatureProvider;
    private SlicesFeatureProvider mSlicesFeatureProvider;
    private SuggestionFeatureProvider mSuggestionFeatureProvider;
    private UserFeatureProvider mUserFeatureProvider;

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public SupportFeatureProvider getSupportFeatureProvider(Context context) {
        return null;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public SurveyFeatureProvider getSurveyFeatureProvider(Context context) {
        return null;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public MetricsFeatureProvider getMetricsFeatureProvider() {
        if (this.mMetricsFeatureProvider == null) {
            this.mMetricsFeatureProvider = new SettingsMetricsFeatureProvider();
        }
        return this.mMetricsFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public PowerUsageFeatureProvider getPowerUsageFeatureProvider(Context context) {
        if (this.mPowerUsageFeatureProvider == null) {
            this.mPowerUsageFeatureProvider = new PowerUsageFeatureProviderImpl(context.getApplicationContext());
        }
        return this.mPowerUsageFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public DashboardFeatureProvider getDashboardFeatureProvider(Context context) {
        if (this.mDashboardFeatureProvider == null) {
            this.mDashboardFeatureProvider = new DashboardFeatureProviderImpl(context.getApplicationContext());
        }
        return this.mDashboardFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public DockUpdaterFeatureProvider getDockUpdaterFeatureProvider() {
        if (this.mDockUpdaterFeatureProvider == null) {
            this.mDockUpdaterFeatureProvider = new DockUpdaterFeatureProviderImpl();
        }
        return this.mDockUpdaterFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public ApplicationFeatureProvider getApplicationFeatureProvider(Context context) {
        if (this.mApplicationFeatureProvider == null) {
            Context applicationContext = context.getApplicationContext();
            this.mApplicationFeatureProvider = new ApplicationFeatureProviderImpl(applicationContext, applicationContext.getPackageManager(), AppGlobals.getPackageManager(), (DevicePolicyManager) applicationContext.getSystemService("device_policy"));
        }
        return this.mApplicationFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public LocaleFeatureProvider getLocaleFeatureProvider() {
        if (this.mLocaleFeatureProvider == null) {
            this.mLocaleFeatureProvider = new LocaleFeatureProviderImpl();
        }
        return this.mLocaleFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public EnterprisePrivacyFeatureProvider getEnterprisePrivacyFeatureProvider(Context context) {
        if (this.mEnterprisePrivacyFeatureProvider == null) {
            Context applicationContext = context.getApplicationContext();
            this.mEnterprisePrivacyFeatureProvider = new EnterprisePrivacyFeatureProviderImpl(applicationContext, (DevicePolicyManager) applicationContext.getSystemService("device_policy"), applicationContext.getPackageManager(), UserManager.get(applicationContext), (ConnectivityManager) applicationContext.getSystemService("connectivity"), applicationContext.getResources());
        }
        return this.mEnterprisePrivacyFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public SearchFeatureProvider getSearchFeatureProvider() {
        if (this.mSearchFeatureProvider == null) {
            this.mSearchFeatureProvider = new SearchFeatureProviderImpl();
        }
        return this.mSearchFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public SecurityFeatureProvider getSecurityFeatureProvider() {
        if (this.mSecurityFeatureProvider == null) {
            this.mSecurityFeatureProvider = new SecurityFeatureProviderImpl();
        }
        return this.mSecurityFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public SuggestionFeatureProvider getSuggestionFeatureProvider(Context context) {
        if (this.mSuggestionFeatureProvider == null) {
            this.mSuggestionFeatureProvider = new SuggestionFeatureProviderImpl(context.getApplicationContext());
        }
        return this.mSuggestionFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public UserFeatureProvider getUserFeatureProvider(Context context) {
        if (this.mUserFeatureProvider == null) {
            this.mUserFeatureProvider = new UserFeatureProviderImpl(context.getApplicationContext());
        }
        return this.mUserFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public AssistGestureFeatureProvider getAssistGestureFeatureProvider() {
        if (this.mAssistGestureFeatureProvider == null) {
            this.mAssistGestureFeatureProvider = new AssistGestureFeatureProviderImpl();
        }
        return this.mAssistGestureFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public SlicesFeatureProvider getSlicesFeatureProvider() {
        if (this.mSlicesFeatureProvider == null) {
            this.mSlicesFeatureProvider = new SlicesFeatureProviderImpl();
        }
        return this.mSlicesFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public AccountFeatureProvider getAccountFeatureProvider() {
        if (this.mAccountFeatureProvider == null) {
            this.mAccountFeatureProvider = new AccountFeatureProviderImpl();
        }
        return this.mAccountFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public PanelFeatureProvider getPanelFeatureProvider() {
        if (this.mPanelFeatureProvider == null) {
            this.mPanelFeatureProvider = new PanelFeatureProviderImpl();
        }
        return this.mPanelFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public ContextualCardFeatureProvider getContextualCardFeatureProvider(Context context) {
        if (this.mContextualCardFeatureProvider == null) {
            this.mContextualCardFeatureProvider = new ContextualCardFeatureProviderImpl(context.getApplicationContext());
        }
        return this.mContextualCardFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public BluetoothFeatureProvider getBluetoothFeatureProvider(Context context) {
        if (this.mBluetoothFeatureProvider == null) {
            this.mBluetoothFeatureProvider = new BluetoothFeatureProviderImpl(context.getApplicationContext());
        }
        return this.mBluetoothFeatureProvider;
    }

    @Override // com.android.car.developeroptions.overlay.FeatureFactory
    public AwareFeatureProvider getAwareFeatureProvider() {
        if (this.mAwareFeatureProvider == null) {
            this.mAwareFeatureProvider = new AwareFeatureProviderImpl();
        }
        return this.mAwareFeatureProvider;
    }
}
