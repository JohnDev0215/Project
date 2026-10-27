package com.amazon.mp3.video.stories;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.annotation.MainThread;
import com.amazon.mp3.video.stories.videostoryplayback.VideoPlayerImpl;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TransferSpeedManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0017\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u0006\u0010\u0003\u001a\u00020\u0002R\"\u0010\u0004\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR(\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006 "}, d2 = {"Lcom/amazon/mp3/video/stories/TransferSpeedManager;", "", "", "calculateAverageSpeedOverBufferingTime", "bufferStartTime", "J", "getBufferStartTime", "()J", "setBufferStartTime", "(J)V", "", "Lcom/amazon/mp3/video/stories/TransferSpeed;", "transferSpeeds", "Ljava/util/List;", "getTransferSpeeds", "()Ljava/util/List;", "setTransferSpeeds", "(Ljava/util/List;)V", "", "connectivityManager", "Lkotlin/Unit;", "getConnectivityManager", "()Lkotlin/Unit;", "setConnectivityManager", "(Lkotlin/Unit;)V", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl;", "videoPlayer", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TransferSpeedManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = TransferSpeedManager.class.getSimpleName();
    private static TransferSpeedManager sInstance;
    private long bufferStartTime;
    private Unit connectivityManager;
    private List<TransferSpeed> transferSpeeds;

    public TransferSpeedManager(Context context, final VideoPlayerImpl videoPlayer) {
        Unit unit;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(videoPlayer, "videoPlayer");
        this.transferSpeeds = new ArrayList();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            unit = null;
        } else {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() { // from class: com.amazon.mp3.video.stories.TransferSpeedManager$connectivityManager$1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    Intrinsics.checkNotNullParameter(network, "network");
                    super.onLost(network);
                    this.this$0.getTransferSpeeds().add(new TransferSpeed(0L, videoPlayer.getIsBuffering() ? System.currentTimeMillis() : -1L));
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    Intrinsics.checkNotNullParameter(network, "network");
                    Intrinsics.checkNotNullParameter(networkCapabilities, "networkCapabilities");
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    if (networkCapabilities.getLinkDownstreamBandwidthKbps() == 0) {
                        this.this$0.getTransferSpeeds().add(new TransferSpeed(0L, videoPlayer.getIsBuffering() ? System.currentTimeMillis() : -1L));
                    }
                }
            });
            unit = Unit.INSTANCE;
        }
        this.connectivityManager = unit;
    }

    /* JADX INFO: compiled from: TransferSpeedManager.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/video/stories/TransferSpeedManager$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "sInstance", "Lcom/amazon/mp3/video/stories/TransferSpeedManager;", "get", "context", "Landroid/content/Context;", "videoPlayer", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @MainThread
        public final TransferSpeedManager get(Context context, VideoPlayerImpl videoPlayer) {
            TransferSpeedManager transferSpeedManager;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(videoPlayer, "videoPlayer");
            if (TransferSpeedManager.sInstance != null) {
                transferSpeedManager = TransferSpeedManager.sInstance;
                if (transferSpeedManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    transferSpeedManager = null;
                }
            } else {
                transferSpeedManager = new TransferSpeedManager(context, videoPlayer);
            }
            TransferSpeedManager.sInstance = transferSpeedManager;
            TransferSpeedManager transferSpeedManager2 = TransferSpeedManager.sInstance;
            if (transferSpeedManager2 != null) {
                return transferSpeedManager2;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sInstance");
            return null;
        }
    }

    public final void setBufferStartTime(long j) {
        this.bufferStartTime = j;
    }

    public final List<TransferSpeed> getTransferSpeeds() {
        return this.transferSpeeds;
    }

    public final long calculateAverageSpeedOverBufferingTime() {
        long startTime;
        long startTime2;
        int size = this.transferSpeeds.size() - 1;
        float f = 0.0f;
        if (size >= 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = i + 1;
                if (i == this.transferSpeeds.size() - 1) {
                    startTime = System.currentTimeMillis();
                } else {
                    startTime = this.transferSpeeds.get(i3).getStartTime();
                }
                if (this.transferSpeeds.get(i).getStartTime() == -1) {
                    startTime2 = this.bufferStartTime;
                } else {
                    startTime2 = this.transferSpeeds.get(i).getStartTime();
                }
                float speed = (f * i2) + (this.transferSpeeds.get(i).getSpeed() * ((long) r5));
                i2 += (int) (startTime - startTime2);
                f = speed / i2;
                if (i == size) {
                    break;
                }
                i = i3;
            }
        }
        return (long) f;
    }
}
