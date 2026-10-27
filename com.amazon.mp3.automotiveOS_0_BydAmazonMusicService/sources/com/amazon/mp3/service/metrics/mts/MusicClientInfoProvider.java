package com.amazon.mp3.service.metrics.mts;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.OtaUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.config.Configuration;
import com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider;
import com.amazon.music.metrics.mts.event.types.BrowseSubscriptionModeType;
import com.amazon.music.metrics.mts.event.types.PlaybackSource;
import com.amazon.music.subscription.BrowseMode;

/* JADX INFO: loaded from: classes4.dex */
public class MusicClientInfoProvider implements ClientInfoProvider {
    private final String mAppVersion;
    private final String mAssociateTag;
    private final Context mContext;
    private final int mDisplayHeight;
    private final int mDisplayWidth;
    private final String mEndpoint;
    private boolean mIsAppColdStart;
    private final String mKernelVersion;
    private final boolean mOtaState;
    private final String mStage;
    private final String mVersionCode;

    public MusicClientInfoProvider(final Context context, final boolean isAppColdStart) {
        this(context, AmazonApplication.getConfiguration(context), isAppColdStart);
    }

    public MusicClientInfoProvider(Context context, Configuration config, final boolean isAppColdStart) {
        ApplicationAttributes applicationAttributes = ApplicationAttributes.getInstance(context);
        this.mAssociateTag = new DeviceAttributes(context).getPreloadAssociateTagOrDefault();
        this.mContext = context.getApplicationContext();
        this.mAppVersion = applicationAttributes.getVersion();
        this.mVersionCode = String.valueOf(applicationAttributes.getVersionCode());
        this.mEndpoint = config.getEndpoint();
        this.mStage = config.getStage();
        this.mOtaState = OtaUtil.isEnabled();
        this.mKernelVersion = System.getProperty("os.version");
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        this.mDisplayHeight = displayMetrics.heightPixels;
        this.mDisplayWidth = displayMetrics.widthPixels;
        this.mIsAppColdStart = isAppColdStart;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getAppStage() {
        return this.mStage;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getAppVersion() {
        return this.mAppVersion;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getAssociateTag() {
        return this.mAssociateTag;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getBuild() {
        return this.mVersionCode;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getDeviceBuild() {
        return Build.DISPLAY;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getEndpoint() {
        return this.mEndpoint;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getFirmwareVersion() {
        return Build.VERSION.RELEASE;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getHardwareVersion() {
        return Build.HARDWARE;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getKernelVersion() {
        return this.mKernelVersion;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getModel() {
        return Build.MODEL;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public String getRemoteSource() {
        return getRemote();
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public int getScreenHeight() {
        return this.mDisplayHeight;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public int getScreenWidth() {
        return this.mDisplayWidth;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public boolean isOtaEnabled() {
        return this.mOtaState;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public BrowseSubscriptionModeType getBrowseSubscriptionMode() {
        return new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().equals(BrowseMode.Mode.PRIME) ? BrowseSubscriptionModeType.PRIME : BrowseSubscriptionModeType.UNLIMITED;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoProvider
    public boolean getIsAppColdStart() {
        return this.mIsAppColdStart;
    }

    private Context getContext() {
        return this.mContext;
    }

    private String getRemote() {
        if (ConnectivityUtil.isWifiOnlyDevice(getContext()) || ConnectivityUtil.isWifiConnected(getContext())) {
            return PlaybackSource.WIFI.getMetricValue(null);
        }
        return ConnectivityUtil.getCarrierName(getContext());
    }

    private DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
