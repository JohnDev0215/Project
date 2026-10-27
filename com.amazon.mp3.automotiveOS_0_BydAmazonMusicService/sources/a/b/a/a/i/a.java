package a.b.a.a.i;

import com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo;
import com.netinsight.sye.syeClient.audio.ISyeAudioTrack;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerState;
import com.netinsight.sye.syeClient.generated.enums.cEA608.ChannelIndex;
import com.netinsight.sye.syeClient.generated.enums.cEA708.ServiceIndex;
import com.netinsight.sye.syeClient.notification.ISyeNotificationMessage;
import com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener;
import com.netinsight.sye.syeClient.playerListeners.IClosedCaptionListener;
import com.netinsight.sye.syeClient.playerListeners.IDTVClosedCaptionListener;
import com.netinsight.sye.syeClient.playerListeners.IEgressInfoListener;
import com.netinsight.sye.syeClient.playerListeners.IErrorListener;
import com.netinsight.sye.syeClient.playerListeners.INotificationListener;
import com.netinsight.sye.syeClient.playerListeners.IStateChangeListener;
import com.netinsight.sye.syeClient.playerListeners.IStatusListener;
import com.netinsight.sye.syeClient.playerListeners.ITeardownListener;
import com.netinsight.sye.syeClient.playerListeners.ITimelineListener;
import com.netinsight.sye.syeClient.playerListeners.IVideoTrackListener;
import com.netinsight.sye.syeClient.status.SyeFrontendInfo;
import com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo;
import com.netinsight.sye.syeClient.video.ISyeVideoStreamInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class a implements IStatusListener, IErrorListener, IStateChangeListener, IEgressInfoListener, IAudioTrackListener, IVideoTrackListener, IClosedCaptionListener, IDTVClosedCaptionListener, INotificationListener, ITeardownListener, ITimelineListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<IStatusListener> f69a = Collections.synchronizedList(new ArrayList());
    public final List<IErrorListener> b = Collections.synchronizedList(new ArrayList());
    public final List<IStateChangeListener> c = Collections.synchronizedList(new ArrayList());
    public final List<IEgressInfoListener> d = Collections.synchronizedList(new ArrayList());
    public final List<IAudioTrackListener> e = Collections.synchronizedList(new ArrayList());
    public final List<IVideoTrackListener> f = Collections.synchronizedList(new ArrayList());
    public final List<IClosedCaptionListener> g = Collections.synchronizedList(new ArrayList());
    public final List<IDTVClosedCaptionListener> h = Collections.synchronizedList(new ArrayList());
    public final List<INotificationListener> i = Collections.synchronizedList(new ArrayList());
    public final List<ITimelineListener> j = Collections.synchronizedList(new ArrayList());
    public final List<ITeardownListener> k = Collections.synchronizedList(new ArrayList());

    public final void a(IErrorListener iErrorListener) {
        this.b.add(iErrorListener);
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener
    public void onAudioStreamChange(ISyeAudioStreamInfo iSyeAudioStreamInfo) {
        List<IAudioTrackListener> audioTrackListeners = this.e;
        Intrinsics.checkExpressionValueIsNotNull(audioTrackListeners, "audioTrackListeners");
        synchronized (audioTrackListeners) {
            Iterator<IAudioTrackListener> it = this.e.iterator();
            while (it.hasNext()) {
                it.next().onAudioStreamChange(iSyeAudioStreamInfo);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener
    public void onAvailableAudioTracks(List<? extends ISyeAudioTrack> list) {
        List<IAudioTrackListener> audioTrackListeners = this.e;
        Intrinsics.checkExpressionValueIsNotNull(audioTrackListeners, "audioTrackListeners");
        synchronized (audioTrackListeners) {
            Iterator<IAudioTrackListener> it = this.e.iterator();
            while (it.hasNext()) {
                it.next().onAvailableAudioTracks(list);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IClosedCaptionListener
    public void onAvailableClosedCaptionChannels(List<? extends ChannelIndex> list) {
        List<IClosedCaptionListener> closedCaptionListeners = this.g;
        Intrinsics.checkExpressionValueIsNotNull(closedCaptionListeners, "closedCaptionListeners");
        synchronized (closedCaptionListeners) {
            Iterator<IClosedCaptionListener> it = this.g.iterator();
            while (it.hasNext()) {
                it.next().onAvailableClosedCaptionChannels(list);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IDTVClosedCaptionListener
    public void onAvailableDTVClosedCaptionServices(List<? extends ServiceIndex> list) {
        List<IDTVClosedCaptionListener> dtvClosedCaptionListeners = this.h;
        Intrinsics.checkExpressionValueIsNotNull(dtvClosedCaptionListeners, "dtvClosedCaptionListeners");
        synchronized (dtvClosedCaptionListeners) {
            Iterator<IDTVClosedCaptionListener> it = this.h.iterator();
            while (it.hasNext()) {
                it.next().onAvailableDTVClosedCaptionServices(list);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IEgressInfoListener
    public void onEgressAllocated(int i) {
        List<IEgressInfoListener> egressInfoListeners = this.d;
        Intrinsics.checkExpressionValueIsNotNull(egressInfoListeners, "egressInfoListeners");
        synchronized (egressInfoListeners) {
            Iterator<IEgressInfoListener> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().onEgressAllocated(i);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IEgressInfoListener
    public void onEgressContact(int i) {
        List<IEgressInfoListener> egressInfoListeners = this.d;
        Intrinsics.checkExpressionValueIsNotNull(egressInfoListeners, "egressInfoListeners");
        synchronized (egressInfoListeners) {
            Iterator<IEgressInfoListener> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().onEgressContact(i);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IErrorListener
    public void onError(SyePlayerError syePlayerError, String str) {
        List<IErrorListener> errorListeners = this.b;
        Intrinsics.checkExpressionValueIsNotNull(errorListeners, "errorListeners");
        synchronized (errorListeners) {
            Iterator<IErrorListener> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().onError(syePlayerError, str);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IErrorListener
    public void onErrorRetry(SyePlayerError syePlayerError, String str, int i) {
        List<IErrorListener> errorListeners = this.b;
        Intrinsics.checkExpressionValueIsNotNull(errorListeners, "errorListeners");
        synchronized (errorListeners) {
            Iterator<IErrorListener> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().onErrorRetry(syePlayerError, str, i);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IStatusListener
    public void onFrontendError(SyePlayerError syePlayerError, SyeFrontendInfo syeFrontendInfo, String str) {
        List<IStatusListener> statusListeners = this.f69a;
        Intrinsics.checkExpressionValueIsNotNull(statusListeners, "statusListeners");
        synchronized (statusListeners) {
            Iterator<IStatusListener> it = this.f69a.iterator();
            while (it.hasNext()) {
                it.next().onFrontendError(syePlayerError, syeFrontendInfo, str);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IStatusListener
    public void onFrontendSuccess(SyeFrontendInfo syeFrontendInfo) {
        List<IStatusListener> statusListeners = this.f69a;
        Intrinsics.checkExpressionValueIsNotNull(statusListeners, "statusListeners");
        synchronized (statusListeners) {
            Iterator<IStatusListener> it = this.f69a.iterator();
            while (it.hasNext()) {
                it.next().onFrontendSuccess(syeFrontendInfo);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.INotificationListener
    public void onNotificationMessage(ISyeNotificationMessage iSyeNotificationMessage) {
        List<INotificationListener> notificationListeners = this.i;
        Intrinsics.checkExpressionValueIsNotNull(notificationListeners, "notificationListeners");
        synchronized (notificationListeners) {
            Iterator<INotificationListener> it = this.i.iterator();
            while (it.hasNext()) {
                it.next().onNotificationMessage(iSyeNotificationMessage);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IStateChangeListener
    public void onStateChange(SyePlayerState syePlayerState, SyePlayerState syePlayerState2) {
        List<IStateChangeListener> stateChangeListeners = this.c;
        Intrinsics.checkExpressionValueIsNotNull(stateChangeListeners, "stateChangeListeners");
        synchronized (stateChangeListeners) {
            Iterator<IStateChangeListener> it = this.c.iterator();
            while (it.hasNext()) {
                it.next().onStateChange(syePlayerState, syePlayerState2);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IStatusListener
    public void onStreamingError(SyePlayerError syePlayerError, String str) {
        List<IStatusListener> statusListeners = this.f69a;
        Intrinsics.checkExpressionValueIsNotNull(statusListeners, "statusListeners");
        synchronized (statusListeners) {
            Iterator<IStatusListener> it = this.f69a.iterator();
            while (it.hasNext()) {
                it.next().onStreamingError(syePlayerError, str);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.ITeardownListener
    public void onTeardown() {
        List<ITeardownListener> tearDownListeners = this.k;
        Intrinsics.checkExpressionValueIsNotNull(tearDownListeners, "tearDownListeners");
        synchronized (tearDownListeners) {
            Iterator<ITeardownListener> it = this.k.iterator();
            while (it.hasNext()) {
                it.next().onTeardown();
            }
        }
        List<ITeardownListener> tearDownListeners2 = this.k;
        Intrinsics.checkExpressionValueIsNotNull(tearDownListeners2, "tearDownListeners");
        synchronized (tearDownListeners2) {
            this.k.clear();
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IEgressInfoListener
    public void onTimeToFirstFrame(int i, int i2) {
        List<IEgressInfoListener> egressInfoListeners = this.d;
        Intrinsics.checkExpressionValueIsNotNull(egressInfoListeners, "egressInfoListeners");
        synchronized (egressInfoListeners) {
            Iterator<IEgressInfoListener> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().onTimeToFirstFrame(i, i2);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.ITimelineListener
    public void onTimelineUpdate(ISyeTimelineInfo iSyeTimelineInfo) {
        List<ITimelineListener> timelineListeners = this.j;
        Intrinsics.checkExpressionValueIsNotNull(timelineListeners, "timelineListeners");
        synchronized (timelineListeners) {
            Iterator<ITimelineListener> it = this.j.iterator();
            while (it.hasNext()) {
                it.next().onTimelineUpdate(iSyeTimelineInfo);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IVideoTrackListener
    public void onVideoStreamChange(ISyeVideoStreamInfo iSyeVideoStreamInfo) {
        List<IVideoTrackListener> videoTrackListeners = this.f;
        Intrinsics.checkExpressionValueIsNotNull(videoTrackListeners, "videoTrackListeners");
        synchronized (videoTrackListeners) {
            Iterator<IVideoTrackListener> it = this.f.iterator();
            while (it.hasNext()) {
                it.next().onVideoStreamChange(iSyeVideoStreamInfo);
            }
        }
    }
}
