package com.netinsight.sye.syeClient.internal;

import com.netinsight.sye.syeClient.timeshift.ISyeThumbnailPreferences;
import com.netinsight.sye.syeClient.video.ISyeVideoLimitations;
import com.netinsight.sye.syeClient.video.SyeVideoCodecSupport;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: classes7.dex */
public final class NativeSyeAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f761a = new a();

    public static final class a {
    }

    @JvmStatic
    public static final native void fetchChannels(JNISyeSystem jNISyeSystem, IFetchChannelsRequestWrapper iFetchChannelsRequestWrapper);

    @JvmStatic
    public static final native long getLocalTimeMicros();

    @JvmStatic
    public static final native ISyeThumbnailPreferences getThumbnailPreferences();

    @JvmStatic
    public static final native void initialize(String str, String str2, String str3, String str4, String str5, IHTTPSessionFactoryWrapper iHTTPSessionFactoryWrapper, String str6);

    @JvmStatic
    public static final native String loadBuildTag();

    @JvmStatic
    public static final native String loadGitVersion();

    @JvmStatic
    public static final native int loadProtocolVersion();

    @JvmStatic
    public static final native void setAudioCapabilities(int[] iArr, int i);

    @JvmStatic
    public static final native void setThumbnailPreferences(ISyeThumbnailPreferences iSyeThumbnailPreferences);

    @JvmStatic
    public static final native void setVideoCapabilities(SyeVideoCodecSupport[] syeVideoCodecSupportArr);

    @JvmStatic
    public static final native void setVideoLimitations(ISyeVideoLimitations iSyeVideoLimitations);
}
