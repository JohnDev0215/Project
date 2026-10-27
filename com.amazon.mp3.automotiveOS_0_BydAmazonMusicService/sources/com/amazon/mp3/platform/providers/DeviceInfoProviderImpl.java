package com.amazon.mp3.platform.providers;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.net.UserAgent;
import com.amazon.music.platform.data.DeviceIdentity;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import java.util.GregorianCalendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceInfoProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0014\u0010!\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0012R\u0014\u0010#\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0012¨\u0006%"}, d2 = {"Lcom/amazon/mp3/platform/providers/DeviceInfoProviderImpl;", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "deviceIdentity", "Lcom/amazon/music/platform/data/DeviceIdentity;", "getDeviceIdentity", "()Lcom/amazon/music/platform/data/DeviceIdentity;", "displayMetrics", "Landroid/util/DisplayMetrics;", "locales", "", "", "getLocales", "()Ljava/util/List;", "manufacturer", "getManufacturer", "()Ljava/lang/String;", "model", "getModel", "osVersion", "getOsVersion", "screenDensity", "", "getScreenDensity", "()F", "screenHeight", "", "getScreenHeight", "()I", "screenWidth", "getScreenWidth", "timeZone", "getTimeZone", "userAgent", "getUserAgent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceInfoProviderImpl implements DeviceInfoProvider {
    private final Context context;
    private final DisplayMetrics displayMetrics;
    private final String manufacturer;
    private final String model;
    private final String osVersion;
    private final float screenDensity;
    private final int screenHeight;
    private final int screenWidth;
    private final String userAgent;

    public DeviceInfoProviderImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        DisplayMetrics displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context\n            .app…          .displayMetrics");
        this.displayMetrics = displayMetrics;
        String RELEASE = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
        this.osVersion = RELEASE;
        String MANUFACTURER = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
        this.manufacturer = MANUFACTURER;
        String MODEL = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
        this.model = MODEL;
        String userAgent = UserAgent.getUserAgent();
        Intrinsics.checkNotNullExpressionValue(userAgent, "getUserAgent()");
        this.userAgent = userAgent;
        this.screenWidth = displayMetrics.widthPixels;
        this.screenHeight = displayMetrics.heightPixels;
        this.screenDensity = displayMetrics.density;
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public DeviceIdentity getDeviceIdentity() {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.context);
        String deviceId = accountCredentialStorage.getDeviceId();
        String deviceTypeId = accountCredentialStorage.getDeviceType();
        String centralDeviceTypeId = accountCredentialStorage.getCentralDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceId, "deviceId");
        Intrinsics.checkNotNullExpressionValue(deviceTypeId, "deviceTypeId");
        Intrinsics.checkNotNullExpressionValue(centralDeviceTypeId, "centralDeviceTypeId");
        return new DeviceIdentity(deviceId, deviceTypeId, centralDeviceTypeId);
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public String getOsVersion() {
        return this.osVersion;
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public String getModel() {
        return this.model;
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public List<String> getLocales() {
        return CollectionsKt__CollectionsJVMKt.listOf(this.context.getResources().getConfiguration().getLocales().get(0).toString());
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public String getTimeZone() {
        String id = new GregorianCalendar().getTimeZone().getID();
        Intrinsics.checkNotNullExpressionValue(id, "calendar.timeZone.id");
        return id;
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public String getUserAgent() {
        return this.userAgent;
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public int getScreenWidth() {
        return this.screenWidth;
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public int getScreenHeight() {
        return this.screenHeight;
    }

    @Override // com.amazon.music.platform.providers.DeviceInfoProvider
    public float getScreenDensity() {
        return this.screenDensity;
    }
}
