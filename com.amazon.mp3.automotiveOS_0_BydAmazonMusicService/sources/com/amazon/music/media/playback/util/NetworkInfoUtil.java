package com.amazon.music.media.playback.util;

import com.amazon.music.platform.data.ConnectionType;
import com.amazon.music.platform.data.NetworkInfo;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.NetworkInfoProvider;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: NetworkInfoUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/media/playback/util/NetworkInfoUtil;", "", "()V", "logger", "Lorg/slf4j/Logger;", "getConnectionType", "Lcom/amazon/music/platform/data/ConnectionType;", "hasAnyInternetConnection", "", "isWifiConnected", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NetworkInfoUtil {
    public static final NetworkInfoUtil INSTANCE = new NetworkInfoUtil();
    private static final Logger logger;

    private NetworkInfoUtil() {
    }

    static {
        Logger logger2 = LoggerFactory.getLogger(NetworkInfoUtil.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(NetworkInfoUtil::class.java.simpleName)");
        logger = logger2;
    }

    @JvmStatic
    public static final ConnectionType getConnectionType() {
        NetworkInfo networkInfo;
        NetworkInfoProvider networkInfoProvider = (NetworkInfoProvider) Providers.INSTANCE.get(NetworkInfoProvider.class);
        if (networkInfoProvider == null) {
            logger.warn("NetworkInfoProvider provider is null");
        }
        if (networkInfoProvider == null || (networkInfo = networkInfoProvider.getNetworkInfo()) == null) {
            return null;
        }
        return networkInfo.getConnectionType();
    }

    @JvmStatic
    public static final boolean hasAnyInternetConnection() {
        ConnectionType connectionType = getConnectionType();
        return (connectionType == null || (connectionType instanceof ConnectionType.NoConnection)) ? false : true;
    }

    @JvmStatic
    public static final boolean isWifiConnected() {
        ConnectionType connectionType = getConnectionType();
        return connectionType != null && (connectionType instanceof ConnectionType.WiFiConnection);
    }
}
