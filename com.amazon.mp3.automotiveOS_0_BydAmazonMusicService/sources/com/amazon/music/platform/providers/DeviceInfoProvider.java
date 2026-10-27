package com.amazon.music.platform.providers;

import com.amazon.music.platform.data.DeviceIdentity;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: DeviceInfoProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0012\u0010\u0010\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0012\u0010\u001c\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\rR\u0012\u0010\u001e\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\r¨\u0006 "}, d2 = {"Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "deviceIdentity", "Lcom/amazon/music/platform/data/DeviceIdentity;", "getDeviceIdentity", "()Lcom/amazon/music/platform/data/DeviceIdentity;", "locales", "", "", "getLocales", "()Ljava/util/List;", "manufacturer", "getManufacturer", "()Ljava/lang/String;", "model", "getModel", "osVersion", "getOsVersion", "screenDensity", "", "getScreenDensity", "()F", "screenHeight", "", "getScreenHeight", "()I", "screenWidth", "getScreenWidth", "timeZone", "getTimeZone", "userAgent", "getUserAgent", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface DeviceInfoProvider extends InterfaceProvider {
    DeviceIdentity getDeviceIdentity();

    List<String> getLocales();

    String getManufacturer();

    String getModel();

    String getOsVersion();

    float getScreenDensity();

    int getScreenHeight();

    int getScreenWidth();

    String getTimeZone();

    String getUserAgent();
}
