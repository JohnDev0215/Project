package com.amazon.mp3.push;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.service.metrics.InstallSource;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.TimeZoneOffset;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.push.BuildType;
import com.amazon.music.push.DeviceInfo;
import com.amazon.music.push.InstallType;
import com.amazon.music.push.PlatformType;
import com.amazon.music.push.PushMessagingClient;

/* JADX INFO: loaded from: classes4.dex */
public class PushInitializer {
    private static final String TAG = "PushInitializer";
    private final AccountCredentialStorage mAccountCredentialStorage;
    private final AccountDetailUtil mAccountDetailUtil;
    private final Context mContext;
    private final DeviceAttributes mDeviceAttributes;
    private final PushMessagingClient mPushMessagingClient;

    public PushInitializer(Context context) {
        this(context, new PushMessagingClient(), AccountCredentialStorage.get(context), AccountDetailUtil.get(context), new DeviceAttributes(context));
    }

    @VisibleForTesting
    PushInitializer(final Context context, final PushMessagingClient pushMessagingClient, final AccountCredentialStorage accountCredentialStorage, final AccountDetailUtil accountDetailUtil, final DeviceAttributes deviceAttributes) {
        this.mContext = context.getApplicationContext();
        this.mPushMessagingClient = pushMessagingClient;
        this.mAccountCredentialStorage = accountCredentialStorage;
        this.mAccountDetailUtil = accountDetailUtil;
        this.mDeviceAttributes = deviceAttributes;
    }

    public void initializePush() {
        InstallType installType;
        String str = TAG;
        Log.d(str, "Attempting to initialize Push Messaging");
        if (PlatformUtil.isFireOS()) {
            Log.d(str, "Not initializing Push client for Fire OS devices");
            return;
        }
        BuildType buildType = BuildType.PROD;
        if (InstallSource.isAppPreloaded(this.mContext)) {
            installType = InstallType.PRELOAD;
        } else {
            installType = InstallType.DOWNLOAD;
        }
        PushMessagingClient.builder(buildType, PlatformType.ANDROID, installType, AccountManagerSingleton.get()).configureForNewSession(this.mContext);
        setDeviceInfo();
    }

    private void setDeviceInfo() {
        this.mPushMessagingClient.setDeviceInfo(new DeviceInfo(null, this.mAccountCredentialStorage.getDeviceId(), this.mAccountCredentialStorage.getDeviceType(), new TimeZoneOffset().toString(), this.mAccountDetailUtil.getHomeMarketPlace(), AccountDetailUtil.getMusicTerritoryOfResidence(), this.mDeviceAttributes.getAppVersion(), Boolean.valueOf(PlatformUtil.isFireOS())), this.mContext);
    }
}
