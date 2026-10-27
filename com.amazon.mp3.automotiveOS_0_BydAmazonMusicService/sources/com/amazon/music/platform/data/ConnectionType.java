package com.amazon.music.platform.data;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\t\n\u000bB\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/data/ConnectionType;", "", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DataConnection", "NoConnection", "WiFiConnection", "Lcom/amazon/music/platform/data/ConnectionType$DataConnection;", "Lcom/amazon/music/platform/data/ConnectionType$NoConnection;", "Lcom/amazon/music/platform/data/ConnectionType$WiFiConnection;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public abstract class ConnectionType {
    private final String name;

    public /* synthetic */ ConnectionType(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private ConnectionType(String str) {
        this.name = str;
    }

    /* JADX INFO: compiled from: NetworkInfo.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/data/ConnectionType$DataConnection;", "Lcom/amazon/music/platform/data/ConnectionType;", "", MediaTrack.ROLE_DESCRIPTION, "Ljava/lang/String;", "getDescription", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class DataConnection extends ConnectionType {
        private final String description;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DataConnection(String description) {
            super("Data", null);
            Intrinsics.checkNotNullParameter(description, "description");
            this.description = description;
            PlatformProviderUtilKt.freeze(this);
        }
    }

    /* JADX INFO: compiled from: NetworkInfo.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/ConnectionType$WiFiConnection;", "Lcom/amazon/music/platform/data/ConnectionType;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class WiFiConnection extends ConnectionType {
        public static final WiFiConnection INSTANCE;

        private WiFiConnection() {
            super("WiFi", null);
        }

        static {
            WiFiConnection wiFiConnection = new WiFiConnection();
            INSTANCE = wiFiConnection;
            PlatformProviderUtilKt.freeze(wiFiConnection);
        }
    }

    /* JADX INFO: compiled from: NetworkInfo.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/ConnectionType$NoConnection;", "Lcom/amazon/music/platform/data/ConnectionType;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class NoConnection extends ConnectionType {
        public static final NoConnection INSTANCE;

        private NoConnection() {
            super("None", null);
        }

        static {
            NoConnection noConnection = new NoConnection();
            INSTANCE = noConnection;
            PlatformProviderUtilKt.freeze(noConnection);
        }
    }
}
