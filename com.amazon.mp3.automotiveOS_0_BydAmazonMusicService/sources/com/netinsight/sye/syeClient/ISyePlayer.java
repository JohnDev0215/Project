package com.netinsight.sye.syeClient;

import a.b.a.a.e.g;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.perf.plugin.util.AsmString;
import com.netinsight.sye.syeClient.audio.ISyeAudioPreferences;
import com.netinsight.sye.syeClient.audio.ISyeAudioTrack;
import com.netinsight.sye.syeClient.closedCaptions.CCType;
import com.netinsight.sye.syeClient.closedCaptions.SyeDTVCCSettings;
import com.netinsight.sye.syeClient.drm.SyeDRMKey;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerState;
import com.netinsight.sye.syeClient.generated.enums.cEA608.ChannelIndex;
import com.netinsight.sye.syeClient.generated.enums.cEA708.ServiceIndex;
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
import com.netinsight.sye.syeClient.timeshift.ISyeThumbnail;
import com.netinsight.sye.syeClient.video.ISyeVideoPreferences;
import com.netinsight.sye.syeClient.video.SyeVideoStreamInfo;
import com.netinsight.sye.syeClient.view.ISyeDisplaySettings;
import com.netinsight.sye.syeClient.view.ISyePlayerView;
import com.netinsight.sye.syeClient.view.SyeClosedCaptionView;
import com.netinsight.sye.syeClient.view.SyeVideoSurfaceView;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000°\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\fH&¢\u0006\u0004\b\n\u0010\u000eJ\u001f\u0010\u0011\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH&¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0011\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH&¢\u0006\u0004\b\u0011\u0010\u0013J\u001f\u0010\u0015\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u000fH&¢\u0006\u0004\b\u0015\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u000fH&¢\u0006\u0004\b\u0015\u0010\u0013J\u000f\u0010\u0016\u001a\u00020\u0005H&¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0005H&¢\u0006\u0004\b\u0018\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0005H&¢\u0006\u0004\b\u0019\u0010\u0017J\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001a\u001a\u00020\u000fH&¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u001eH&¢\u0006\u0004\b \u0010!J\u001f\u0010$\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH&¢\u0006\u0004\b$\u0010%J\u0017\u0010(\u001a\u00020\u00052\u0006\u0010'\u001a\u00020&H&¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\u0005H&¢\u0006\u0004\b*\u0010\u0017J\u0017\u0010-\u001a\u00020\u00052\u0006\u0010,\u001a\u00020+H&¢\u0006\u0004\b-\u0010.J\u0017\u00101\u001a\u00020\u00052\u0006\u00100\u001a\u00020/H&¢\u0006\u0004\b1\u00102J\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u000203H&¢\u0006\u0004\b5\u00106J\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u000203H&¢\u0006\u0004\b7\u00106J\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u000208H&¢\u0006\u0004\b5\u00109J\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u000208H&¢\u0006\u0004\b7\u00109J\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020:H&¢\u0006\u0004\b5\u0010;J\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u00020:H&¢\u0006\u0004\b7\u0010;J\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020<H&¢\u0006\u0004\b5\u0010=J\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u00020<H&¢\u0006\u0004\b7\u0010=J\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020>H&¢\u0006\u0004\b5\u0010?J\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u00020>H&¢\u0006\u0004\b7\u0010?J\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020@H&¢\u0006\u0004\b5\u0010AJ\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u00020@H&¢\u0006\u0004\b7\u0010AJ\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020BH&¢\u0006\u0004\b5\u0010CJ\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u00020BH&¢\u0006\u0004\b7\u0010CJ\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020DH&¢\u0006\u0004\b5\u0010EJ\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u00020DH&¢\u0006\u0004\b7\u0010EJ\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020FH&¢\u0006\u0004\b5\u0010GJ\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u00020FH&¢\u0006\u0004\b7\u0010GJ\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020HH&¢\u0006\u0004\b5\u0010IJ\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u00020HH&¢\u0006\u0004\b7\u0010IJ\u0017\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020JH&¢\u0006\u0004\b5\u0010KJ\u0017\u00107\u001a\u00020\u00052\u0006\u00104\u001a\u00020JH&¢\u0006\u0004\b7\u0010KJ\u0017\u0010N\u001a\u00020\u00052\u0006\u0010M\u001a\u00020LH&¢\u0006\u0004\bN\u0010OJ\u0019\u0010S\u001a\u0004\u0018\u00010R2\u0006\u0010Q\u001a\u00020PH&¢\u0006\u0004\bS\u0010TJ\u001f\u0010W\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020U2\u0006\u0010V\u001a\u00020RH&¢\u0006\u0004\bW\u0010XJ\u0017\u0010Y\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020UH&¢\u0006\u0004\bY\u0010ZJ\u0017\u0010Y\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020[H&¢\u0006\u0004\bY\u0010\\J\u0017\u0010Y\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020]H&¢\u0006\u0004\bY\u0010^J\u0017\u0010Y\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020_H&¢\u0006\u0004\bY\u0010`J\u0017\u0010a\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020UH&¢\u0006\u0004\ba\u0010ZJ\u0017\u0010a\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020[H&¢\u0006\u0004\ba\u0010\\J\u0017\u0010a\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020_H&¢\u0006\u0004\ba\u0010`J\u0017\u0010a\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020]H&¢\u0006\u0004\ba\u0010^J\u0017\u0010Y\u001a\u00020\u00052\u0006\u0010c\u001a\u00020bH&¢\u0006\u0004\bY\u0010dJ\u0017\u0010a\u001a\u00020\u00052\u0006\u0010c\u001a\u00020bH&¢\u0006\u0004\ba\u0010dR\u0018\u0010g\u001a\u0004\u0018\u00010\b8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\be\u0010fR\u0018\u0010j\u001a\u0004\u0018\u00010/8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\bh\u0010iR\u001c\u0010m\u001a\b\u0012\u0004\u0012\u00020+0\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\bk\u0010lR\u001c\u0010s\u001a\u00020n8&@&X¦\u000e¢\u0006\f\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR\u001c\u0010y\u001a\u00020t8&@&X¦\u000e¢\u0006\f\u001a\u0004\bu\u0010v\"\u0004\bw\u0010xR\u0018\u0010}\u001a\u0004\u0018\u00010z8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b{\u0010|R\u001a\u0010\u0081\u0001\u001a\u0004\u0018\u00010~8&@&X¦\u0004¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001R\"\u0010\u0087\u0001\u001a\u00030\u0082\u00018&@&X¦\u000e¢\u0006\u0010\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001\"\u0006\b\u0085\u0001\u0010\u0086\u0001R\"\u0010\u008d\u0001\u001a\u00030\u0088\u00018&@&X¦\u000e¢\u0006\u0010\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001\"\u0006\b\u008b\u0001\u0010\u008c\u0001R\"\u0010\u008f\u0001\u001a\u00030\u008e\u00018&@&X¦\u000e¢\u0006\u0010\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001\"\u0006\b\u0091\u0001\u0010\u0092\u0001R\"\u0010\u0098\u0001\u001a\u00030\u0093\u00018&@&X¦\u000e¢\u0006\u0010\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u0006\b\u0096\u0001\u0010\u0097\u0001R\u001b\u0010\u009b\u0001\u001a\u0004\u0018\u00010+8&@&X¦\u0004¢\u0006\b\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001R\u001c\u0010\u009f\u0001\u001a\u0005\u0018\u00010\u009c\u00018&@&X¦\u0004¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u001f\u0010¢\u0001\u001a\t\u0012\u0005\u0012\u00030 \u00010\u00028&@&X¦\u0004¢\u0006\u0007\u001a\u0005\b¡\u0001\u0010lR\u001c\u0010¦\u0001\u001a\u0005\u0018\u00010£\u00018&@&X¦\u0004¢\u0006\b\u001a\u0006\b¤\u0001\u0010¥\u0001R\u001e\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020/0\u00028&@&X¦\u0004¢\u0006\u0007\u001a\u0005\b§\u0001\u0010l¨\u0006©\u0001"}, d2 = {"Lcom/netinsight/sye/syeClient/ISyePlayer;", "", "", "Lcom/netinsight/sye/syeClient/drm/SyeDRMKey;", "keys", "", "addDecryptionKeys", "(Ljava/util/List;)V", "", "channelId", "playFromLive", "(Ljava/lang/String;)V", "Lcom/netinsight/sye/syeClient/SyePlayRequest;", "request", "(Lcom/netinsight/sye/syeClient/SyePlayRequest;)V", "", "utcTimePosMillis", "playFromUtcTime", "(Ljava/lang/String;J)V", "(Lcom/netinsight/sye/syeClient/SyePlayRequest;J)V", "offset", "playWithOffset", "playResume", "()V", "pause", AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "utcTimeMillis", "Lcom/netinsight/sye/syeClient/timeshift/ISyeThumbnail;", "getThumbnail", "(J)Lcom/netinsight/sye/syeClient/timeshift/ISyeThumbnail;", "", "durationmMillis", "cacheThumbnailsFromLive", "(I)V", "startIntervalUtcMillis", "endIntervalUtcMillis", "cacheThumbnailsForInterval", "(JJ)V", "Lcom/netinsight/sye/syeClient/SyeSystem;", "syeSystem", "updateSyeSystem", "(Lcom/netinsight/sye/syeClient/SyeSystem;)V", "teardown", "Lcom/netinsight/sye/syeClient/generated/enums/cEA608/ChannelIndex;", "ccChannelIndex", "selectClosedCaptionsChannel", "(Lcom/netinsight/sye/syeClient/generated/enums/cEA608/ChannelIndex;)V", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/ServiceIndex;", "ccServiceIndex", "selectClosedCaptionsService", "(Lcom/netinsight/sye/syeClient/generated/enums/cEA708/ServiceIndex;)V", "Lcom/netinsight/sye/syeClient/playerListeners/IStatusListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "(Lcom/netinsight/sye/syeClient/playerListeners/IStatusListener;)V", "removeListener", "Lcom/netinsight/sye/syeClient/playerListeners/IErrorListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/IErrorListener;)V", "Lcom/netinsight/sye/syeClient/playerListeners/IStateChangeListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/IStateChangeListener;)V", "Lcom/netinsight/sye/syeClient/playerListeners/IEgressInfoListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/IEgressInfoListener;)V", "Lcom/netinsight/sye/syeClient/playerListeners/IAudioTrackListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/IAudioTrackListener;)V", "Lcom/netinsight/sye/syeClient/playerListeners/IVideoTrackListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/IVideoTrackListener;)V", "Lcom/netinsight/sye/syeClient/playerListeners/IClosedCaptionListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/IClosedCaptionListener;)V", "Lcom/netinsight/sye/syeClient/playerListeners/IDTVClosedCaptionListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/IDTVClosedCaptionListener;)V", "Lcom/netinsight/sye/syeClient/playerListeners/INotificationListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/INotificationListener;)V", "Lcom/netinsight/sye/syeClient/playerListeners/ITimelineListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/ITimelineListener;)V", "Lcom/netinsight/sye/syeClient/playerListeners/ITeardownListener;", "(Lcom/netinsight/sye/syeClient/playerListeners/ITeardownListener;)V", "Lcom/netinsight/sye/syeClient/ISyePlayerSynchronizationCallback;", "callback", "synchronize", "(Lcom/netinsight/sye/syeClient/ISyePlayerSynchronizationCallback;)V", "Landroid/view/View;", "view", "Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings;", "getDisplaySettings", "(Landroid/view/View;)Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings;", "Landroid/view/TextureView;", "settings", "setDisplaySettings", "(Landroid/view/TextureView;Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings;)V", "attach", "(Landroid/view/TextureView;)V", "Landroid/view/SurfaceView;", "(Landroid/view/SurfaceView;)V", "Lcom/netinsight/sye/syeClient/view/ISyePlayerView;", "(Lcom/netinsight/sye/syeClient/view/ISyePlayerView;)V", "Lcom/netinsight/sye/syeClient/view/SyeVideoSurfaceView;", "(Lcom/netinsight/sye/syeClient/view/SyeVideoSurfaceView;)V", "detach", "Lcom/netinsight/sye/syeClient/view/SyeClosedCaptionView;", "ccView", "(Lcom/netinsight/sye/syeClient/view/SyeClosedCaptionView;)V", "getCurrentChannelId", "()Ljava/lang/String;", "currentChannelId", "getSelectedClosedCaptionsService", "()Lcom/netinsight/sye/syeClient/generated/enums/cEA708/ServiceIndex;", "selectedClosedCaptionsService", "getAvailableClosedCaptionsChannels", "()Ljava/util/List;", "availableClosedCaptionsChannels", "Lcom/netinsight/sye/syeClient/audio/ISyeAudioPreferences;", "getAudioPreferences", "()Lcom/netinsight/sye/syeClient/audio/ISyeAudioPreferences;", "setAudioPreferences", "(Lcom/netinsight/sye/syeClient/audio/ISyeAudioPreferences;)V", "audioPreferences", "Lcom/netinsight/sye/syeClient/closedCaptions/SyeDTVCCSettings;", "getDTVCCSettings", "()Lcom/netinsight/sye/syeClient/closedCaptions/SyeDTVCCSettings;", "setDTVCCSettings", "(Lcom/netinsight/sye/syeClient/closedCaptions/SyeDTVCCSettings;)V", "DTVCCSettings", "Lcom/netinsight/sye/syeClient/ISyeMetrics;", "getMetrics", "()Lcom/netinsight/sye/syeClient/ISyeMetrics;", "metrics", "La/b/a/a/e/g;", "getAudioStreamInfo", "()La/b/a/a/e/g;", "audioStreamInfo", "", "getAudioVolume", "()F", "setAudioVolume", "(F)V", "audioVolume", "Lcom/netinsight/sye/syeClient/closedCaptions/CCType;", "getSelectedCCType", "()Lcom/netinsight/sye/syeClient/closedCaptions/CCType;", "setSelectedCCType", "(Lcom/netinsight/sye/syeClient/closedCaptions/CCType;)V", "selectedCCType", "", "isClosedCaptionsEnabled", "()Z", "setClosedCaptionsEnabled", "(Z)V", "Lcom/netinsight/sye/syeClient/video/ISyeVideoPreferences;", "getVideoPreferences", "()Lcom/netinsight/sye/syeClient/video/ISyeVideoPreferences;", "setVideoPreferences", "(Lcom/netinsight/sye/syeClient/video/ISyeVideoPreferences;)V", "videoPreferences", "getSelectedClosedCaptionsChannel", "()Lcom/netinsight/sye/syeClient/generated/enums/cEA608/ChannelIndex;", "selectedClosedCaptionsChannel", "Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerState;", "getPlayerState", "()Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerState;", "playerState", "Lcom/netinsight/sye/syeClient/audio/ISyeAudioTrack;", "getAvailableAudioTracks", "availableAudioTracks", "Lcom/netinsight/sye/syeClient/video/SyeVideoStreamInfo;", "getVideoStreamInfo", "()Lcom/netinsight/sye/syeClient/video/SyeVideoStreamInfo;", "videoStreamInfo", "getAvailableClosedCaptionsServices", "availableClosedCaptionsServices", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyePlayer {
    void addDecryptionKeys(List<SyeDRMKey> keys);

    void addListener(IAudioTrackListener listener);

    void addListener(IClosedCaptionListener listener);

    void addListener(IDTVClosedCaptionListener listener);

    void addListener(IEgressInfoListener listener);

    void addListener(IErrorListener listener);

    void addListener(INotificationListener listener);

    void addListener(IStateChangeListener listener);

    void addListener(IStatusListener listener);

    void addListener(ITeardownListener listener);

    void addListener(ITimelineListener listener);

    void addListener(IVideoTrackListener listener);

    void attach(SurfaceView view);

    void attach(TextureView view);

    void attach(ISyePlayerView view);

    void attach(SyeClosedCaptionView ccView);

    void attach(SyeVideoSurfaceView view);

    void cacheThumbnailsForInterval(long startIntervalUtcMillis, long endIntervalUtcMillis);

    void cacheThumbnailsFromLive(int durationmMillis);

    void detach(SurfaceView view);

    void detach(TextureView view);

    void detach(ISyePlayerView view);

    void detach(SyeClosedCaptionView ccView);

    void detach(SyeVideoSurfaceView view);

    ISyeAudioPreferences getAudioPreferences();

    g getAudioStreamInfo();

    float getAudioVolume();

    List<ISyeAudioTrack> getAvailableAudioTracks();

    List<ChannelIndex> getAvailableClosedCaptionsChannels();

    List<ServiceIndex> getAvailableClosedCaptionsServices();

    String getCurrentChannelId();

    SyeDTVCCSettings getDTVCCSettings();

    ISyeDisplaySettings getDisplaySettings(View view);

    ISyeMetrics getMetrics();

    SyePlayerState getPlayerState();

    CCType getSelectedCCType();

    ChannelIndex getSelectedClosedCaptionsChannel();

    ServiceIndex getSelectedClosedCaptionsService();

    ISyeThumbnail getThumbnail(long utcTimeMillis);

    ISyeVideoPreferences getVideoPreferences();

    SyeVideoStreamInfo getVideoStreamInfo();

    boolean isClosedCaptionsEnabled();

    void pause();

    void playFromLive(SyePlayRequest request);

    void playFromLive(String channelId);

    void playFromUtcTime(SyePlayRequest request, long utcTimePosMillis);

    void playFromUtcTime(String channelId, long utcTimePosMillis);

    void playResume();

    void playWithOffset(SyePlayRequest request, long offset);

    void playWithOffset(String channelId, long offset);

    void removeListener(IAudioTrackListener listener);

    void removeListener(IClosedCaptionListener listener);

    void removeListener(IDTVClosedCaptionListener listener);

    void removeListener(IEgressInfoListener listener);

    void removeListener(IErrorListener listener);

    void removeListener(INotificationListener listener);

    void removeListener(IStateChangeListener listener);

    void removeListener(IStatusListener listener);

    void removeListener(ITeardownListener listener);

    void removeListener(ITimelineListener listener);

    void removeListener(IVideoTrackListener listener);

    void selectClosedCaptionsChannel(ChannelIndex ccChannelIndex);

    void selectClosedCaptionsService(ServiceIndex ccServiceIndex);

    void setAudioPreferences(ISyeAudioPreferences iSyeAudioPreferences);

    void setAudioVolume(float f);

    void setClosedCaptionsEnabled(boolean z);

    void setDTVCCSettings(SyeDTVCCSettings syeDTVCCSettings);

    void setDisplaySettings(TextureView view, ISyeDisplaySettings settings);

    void setSelectedCCType(CCType cCType);

    void setVideoPreferences(ISyeVideoPreferences iSyeVideoPreferences);

    void stop();

    void synchronize(ISyePlayerSynchronizationCallback callback);

    void teardown();

    void updateSyeSystem(SyeSystem syeSystem);
}
