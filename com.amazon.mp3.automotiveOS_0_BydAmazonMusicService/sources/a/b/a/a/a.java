package a.b.a.a;

import a.b.a.a.e.b;
import a.b.a.a.e.h;
import a.b.a.a.g.e;
import a.b.a.a.n.b;
import a.b.a.a.o.g.a;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import com.google.firebase.perf.plugin.util.AsmString;
import com.netinsight.sye.syeClient.ISyeMetrics;
import com.netinsight.sye.syeClient.ISyePlayer;
import com.netinsight.sye.syeClient.ISyePlayerSynchronizationCallback;
import com.netinsight.sye.syeClient.SyePlayRequest;
import com.netinsight.sye.syeClient.SyePlayerConfig;
import com.netinsight.sye.syeClient.SyeSystem;
import com.netinsight.sye.syeClient.audio.ISyeAudioPreferences;
import com.netinsight.sye.syeClient.audio.ISyeAudioTrack;
import com.netinsight.sye.syeClient.audio.SyeAudioCodec;
import com.netinsight.sye.syeClient.audio.SyeAudioPreferences;
import com.netinsight.sye.syeClient.audio.SyeAudioSample;
import com.netinsight.sye.syeClient.closedCaptions.CCType;
import com.netinsight.sye.syeClient.closedCaptions.SyeDTVCCSettings;
import com.netinsight.sye.syeClient.drm.SyeDRMKey;
import com.netinsight.sye.syeClient.generated.enums.AudioCodecProfile;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerState;
import com.netinsight.sye.syeClient.generated.enums.cEA608.ChannelIndex;
import com.netinsight.sye.syeClient.generated.enums.cEA708.ServiceIndex;
import com.netinsight.sye.syeClient.internal.DatafulJsonString;
import com.netinsight.sye.syeClient.internal.IBazPlayerQueryWrapper;
import com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper;
import com.netinsight.sye.syeClient.internal.JNISyeDRMKeys;
import com.netinsight.sye.syeClient.internal.JNISyeSystem;
import com.netinsight.sye.syeClient.internal.NativeSyeAPI;
import com.netinsight.sye.syeClient.internal.NativeSyePlayer;
import com.netinsight.sye.syeClient.notification.SyeNotificationMessage;
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
import com.netinsight.sye.syeClient.timeshift.ISyeThumbnail;
import com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo;
import com.netinsight.sye.syeClient.video.ISyeVideoPreferences;
import com.netinsight.sye.syeClient.video.SyeVideoPreferences;
import com.netinsight.sye.syeClient.video.SyeVideoSample;
import com.netinsight.sye.syeClient.video.SyeVideoStreamInfo;
import com.netinsight.sye.syeClient.view.ISyeDisplaySettings;
import com.netinsight.sye.syeClient.view.ISyePlayerView;
import com.netinsight.sye.syeClient.view.SyeClosedCaptionView;
import com.netinsight.sye.syeClient.view.SyeDisplaySettings;
import com.netinsight.sye.syeClient.view.SyeVideoSurfaceView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a implements ISyePlayer, a.InterfaceC0023a, b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f17a = 1;
    public final int b;
    public final String c;
    public final a.b.a.a.i.b d;
    public NativeSyePlayer e;
    public final a.b.a.a.o.g.a f;
    public final a.b.a.a.e.f g;
    public final a.b.a.a.g.a h;
    public volatile boolean i;
    public volatile boolean j;
    public final a.b.a.a.i.a k;
    public final Handler l;
    public final ExecutorService m;
    public final Thread n;
    public final Callable<Long> o;
    public final boolean p;
    public SyePlayRequest q;
    public final a.b.a.a.e.b r;
    public final Object s;
    public boolean t;

    /* JADX INFO: renamed from: a.b.a.a.a$a, reason: collision with other inner class name */
    public static final class RunnableC0001a implements Runnable {
        public RunnableC0001a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            while (!a.this.i) {
                long jLongValue = 10;
                a aVar = a.this;
                try {
                    Object obj = aVar.m.submit(aVar.o).get();
                    Intrinsics.checkExpressionValueIsNotNull(obj, "future.get()");
                    jLongValue = ((Number) obj).longValue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e2) {
                    e2.printStackTrace();
                }
                try {
                    Thread.sleep(jLongValue / ((long) 1000));
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
                if (a.this.j) {
                    try {
                        a.b.a.a.i.b bVar = a.this.d;
                        Thread threadCurrentThread = Thread.currentThread();
                        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
                        String name = threadCurrentThread.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name, "Thread.currentThread().name");
                        bVar.a(name, "Poll thread pausing");
                        synchronized (a.this.s) {
                            a.this.s.wait();
                        }
                        a.b.a.a.i.b bVar2 = a.this.d;
                        Thread threadCurrentThread2 = Thread.currentThread();
                        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread2, "Thread.currentThread()");
                        String name2 = threadCurrentThread2.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name2, "Thread.currentThread().name");
                        bVar2.a(name2, "Poll thread resumed");
                    } catch (InterruptedException unused2) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            a.b.a.a.i.b bVar3 = a.this.d;
            Thread threadCurrentThread3 = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread3, "Thread.currentThread()");
            String name3 = threadCurrentThread3.getName();
            Intrinsics.checkExpressionValueIsNotNull(name3, "Thread.currentThread().name");
            bVar3.a(name3, "Poll thread exiting...");
        }
    }

    public static final class a0 extends b {
        public final /* synthetic */ SyePlayRequest d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a0(SyePlayRequest syePlayRequest, String str) {
            super(str);
            this.d = syePlayRequest;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            String channelId = this.d.getChannelId();
            nativeSyePlayer.getClass();
            nativeSyePlayer.playFromLive(nativeSyePlayer.f762a, channelId);
        }
    }

    public abstract class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f19a;

        public b(String str) {
            this.f19a = str;
        }

        public abstract void a();

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            aVar.d.c(aVar.c, this.f19a);
            a();
        }
    }

    public static final class b0 extends b {
        public final /* synthetic */ SyePlayRequest d;
        public final /* synthetic */ long e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b0(SyePlayRequest syePlayRequest, long j, String str) {
            super(str);
            this.d = syePlayRequest;
            this.e = j;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            String channelId = this.d.getChannelId();
            long j = this.e;
            nativeSyePlayer.getClass();
            nativeSyePlayer.playFromUtcTime(nativeSyePlayer.f762a, channelId, j);
        }
    }

    public final class c implements IBazPlayerQueryWrapper {

        /* JADX INFO: renamed from: a.b.a.a.a$c$a, reason: collision with other inner class name */
        public static final class C0002a extends Lambda implements Function1<a.b.a.a.o.e.b, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0002a f21a = new C0002a();

            public C0002a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public String invoke(a.b.a.a.o.e.b bVar) {
                return bVar.getResolution().toString();
            }
        }

        public c() {
        }

        @Override // com.netinsight.sye.syeClient.internal.IBazPlayerQueryWrapper
        public String getViewResolutions() {
            return SequencesKt___SequencesKt.joinToString$default(SequencesKt__SequencesKt.asSequence(a.this.f.b()), ",", null, null, 0, null, C0002a.f21a, 30, null);
        }
    }

    public static final class c0 extends b {
        public c0(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.playResume(nativeSyePlayer.f762a);
            }
        }
    }

    public final class d extends Handler implements ISyePlayerCallbackWrapper {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f22a;

        /* JADX INFO: renamed from: a.b.a.a.a$d$a, reason: collision with other inner class name */
        public static final /* synthetic */ class C0003a extends FunctionReference implements Function1<String, Unit> {
            public C0003a(a aVar) {
                super(1, aVar);
            }

            @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "onReportUnsupportedAudioFormat";
            }

            @Override // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(a.class);
            }

            @Override // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "onReportUnsupportedAudioFormat(Ljava/lang/String;)V";
            }

            @Override // kotlin.jvm.functions.Function1
            public Unit invoke(String str) {
                a aVar = (a) this.receiver;
                aVar.getClass();
                aVar.a(new a.b.a.a.c(aVar, str, "reportError from onReportUnsupportedAudioFormat"));
                return Unit.INSTANCE;
            }
        }

        public static final class b implements Runnable {
            public final /* synthetic */ a.b.a.a.e.g b;

            public b(a.b.a.a.e.g gVar) {
                this.b = gVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onAudioTrackChange");
                a.this.k.onAudioStreamChange(this.b);
            }
        }

        public static final class c implements Runnable {
            public final /* synthetic */ String b;
            public final /* synthetic */ List c;

            public c(String str, List list) {
                this.b = str;
                this.c = list;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.b(b.a.Audio, dVar.f22a, "onAudioTracks: " + this.b);
                a.this.k.onAvailableAudioTracks(this.c);
            }
        }

        /* JADX INFO: renamed from: a.b.a.a.a$d$d, reason: collision with other inner class name */
        public static final class RunnableC0004d implements Runnable {
            public final /* synthetic */ List b;

            public RunnableC0004d(List list) {
                this.b = list;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onAvailableClosedCaptionsChannels");
                a.this.k.onAvailableClosedCaptionChannels(this.b);
            }
        }

        public static final class e implements Runnable {
            public final /* synthetic */ String b;
            public final /* synthetic */ List c;

            public e(String str, List list) {
                this.b = str;
                this.c = list;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onAvailableClosedCaptionsServices " + this.b);
                a.this.k.onAvailableDTVClosedCaptionServices(this.c);
            }
        }

        public static final class f implements Runnable {
            public final /* synthetic */ int b;

            public f(int i) {
                this.b = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onEgressAllocated");
                a.this.k.onEgressAllocated(this.b);
            }
        }

        public static final class g implements Runnable {
            public final /* synthetic */ int b;

            public g(int i) {
                this.b = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onEgressContact");
                a.this.k.onEgressContact(this.b);
            }
        }

        public static final class h implements Runnable {
            public final /* synthetic */ String b;
            public final /* synthetic */ int c;

            public h(String str, int i) {
                this.b = str;
                this.c = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onError, Message: " + this.b);
                a.this.k.onError(SyePlayerError.INSTANCE.a(this.c), this.b);
            }
        }

        public static final class i implements Runnable {
            public final /* synthetic */ int b;
            public final /* synthetic */ String c;
            public final /* synthetic */ int d;

            public i(int i, String str, int i2) {
                this.b = i;
                this.c = str;
                this.d = i2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onErrorRetry(after " + this.b + " millis), Message: " + this.c);
                a.this.k.onErrorRetry(SyePlayerError.INSTANCE.a(this.d), this.c, this.b);
            }
        }

        public static final class j implements Runnable {
            public final /* synthetic */ int b;
            public final /* synthetic */ SyeFrontendInfo c;
            public final /* synthetic */ String d;

            public j(int i, SyeFrontendInfo syeFrontendInfo, String str) {
                this.b = i;
                this.c = syeFrontendInfo;
                this.d = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.b.a.a.i.b bVar = a.this.d;
                String str = dVar.f22a;
                StringBuilder sb = new StringBuilder();
                sb.append("onFrontendError, errorCode: ");
                SyePlayerError.Companion companion = SyePlayerError.INSTANCE;
                sb.append(companion.a(this.b));
                sb.append(" frontendInfo: ");
                sb.append(this.c);
                sb.append(" message: ");
                sb.append(this.d);
                bVar.c(str, sb.toString());
                if (this.c != null) {
                    a.this.k.onFrontendError(companion.a(this.b), this.c, this.d);
                }
            }
        }

        public static final class k implements Runnable {
            public final /* synthetic */ SyeFrontendInfo b;

            public k(SyeFrontendInfo syeFrontendInfo) {
                this.b = syeFrontendInfo;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onFrontendSuccess, frontendInfo: " + this.b);
                SyeFrontendInfo syeFrontendInfo = this.b;
                if (syeFrontendInfo != null) {
                    a.this.k.onFrontendSuccess(syeFrontendInfo);
                }
            }
        }

        public static final class l implements Runnable {
            public final /* synthetic */ SyeNotificationMessage b;

            public l(SyeNotificationMessage syeNotificationMessage) {
                this.b = syeNotificationMessage;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onNotificationMessage");
                a.this.k.onNotificationMessage(this.b);
            }
        }

        public static final class m implements Runnable {
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;

            public m(int i, int i2) {
                this.b = i;
                this.c = i2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.b.a.a.i.b bVar = a.this.d;
                String str = dVar.f22a;
                StringBuilder sb = new StringBuilder();
                sb.append("onStateChange, to : ");
                SyePlayerState.Companion companion = SyePlayerState.INSTANCE;
                sb.append(companion.a(this.b));
                bVar.c(str, sb.toString());
                a.this.k.onStateChange(companion.a(this.c), companion.a(this.b));
            }
        }

        public static final class n implements Runnable {
            public final /* synthetic */ int b;
            public final /* synthetic */ String c;

            public n(int i, String str) {
                this.b = i;
                this.c = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.b.a.a.i.b bVar = a.this.d;
                String str = dVar.f22a;
                StringBuilder sb = new StringBuilder();
                sb.append("onStreamingError, errorCode: ");
                SyePlayerError.Companion companion = SyePlayerError.INSTANCE;
                sb.append(companion.a(this.b));
                sb.append(" message: ");
                sb.append(this.c);
                bVar.c(str, sb.toString());
                a.this.k.onStreamingError(companion.a(this.b), this.c);
            }
        }

        public static final class o implements Runnable {
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;

            public o(int i, int i2) {
                this.b = i;
                this.c = i2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onTimeToFirstFrame");
                a.this.k.onTimeToFirstFrame(this.b, this.c);
            }
        }

        public static final class p implements Runnable {
            public final /* synthetic */ ISyeTimelineInfo b;

            public p(ISyeTimelineInfo iSyeTimelineInfo) {
                this.b = iSyeTimelineInfo;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ISyeTimelineInfo iSyeTimelineInfo = this.b;
                if (iSyeTimelineInfo != null) {
                    a.this.k.onTimelineUpdate(iSyeTimelineInfo);
                }
            }
        }

        public static final class q extends b {
            public final /* synthetic */ SyeVideoSample d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public q(SyeVideoSample syeVideoSample, String str) {
                super(str);
                this.d = syeVideoSample;
            }

            @Override // a.b.a.a.a.b
            public void a() {
                NativeSyePlayer nativeSyePlayer = a.this.e;
                if (nativeSyePlayer != null) {
                    nativeSyePlayer.a(0, this.d.toString());
                }
            }
        }

        public static final class r extends b {
            public final /* synthetic */ SyeVideoStreamInfo d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public r(SyeVideoStreamInfo syeVideoStreamInfo, String str) {
                super(str);
                this.d = syeVideoStreamInfo;
            }

            @Override // a.b.a.a.a.b
            public void a() {
                NativeSyePlayer nativeSyePlayer = a.this.e;
                if (nativeSyePlayer != null) {
                    nativeSyePlayer.a(0, this.d.toString());
                }
            }
        }

        public static final class s implements Runnable {
            public final /* synthetic */ SyeVideoStreamInfo b;

            public s(SyeVideoStreamInfo syeVideoStreamInfo) {
                this.b = syeVideoStreamInfo;
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                a.this.d.c(dVar.f22a, "onVideoStreamChange to " + this.b.getWidth() + 'x' + this.b.getHeight());
                a.this.k.onVideoStreamChange(this.b);
            }
        }

        public d(Looper looper) {
            super(looper);
            this.f22a = "CB_SyePlayer_" + a.this.a();
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onAudioSample(SyeAudioSample syeAudioSample) {
            try {
                a aVar = a.this;
                aVar.g.a(syeAudioSample, new C0003a(aVar));
            } catch (a.b.a.a.j.a e2) {
                a.this.d.b(this.f22a, "error in onAudioSample: " + e2.getMessage());
                a aVar2 = a.this;
                String string = syeAudioSample.toString();
                aVar2.getClass();
                aVar2.a(new a.b.a.a.c(aVar2, string, "reportError from onReportUnsupportedAudioFormat"));
            }
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onAudioStreamChange(String str) {
            a.b.a.a.k.e.a aVar;
            try {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String string = jSONObject.getString("language");
                    Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.getString(\"language\")");
                    String string2 = jSONObject.getString("languageDescription");
                    Intrinsics.checkExpressionValueIsNotNull(string2, "jsonObject.getString(\"languageDescription\")");
                    int i2 = jSONObject.getInt("codec");
                    if (i2 == 0) {
                        aVar = a.b.a.a.k.e.a.Undefined;
                    } else if (i2 == 1) {
                        aVar = a.b.a.a.k.e.a.AAC;
                    } else {
                        if (i2 != 2) {
                            throw new IllegalArgumentException("No AudioCodec with value " + i2);
                        }
                        aVar = a.b.a.a.k.e.a.EC3;
                    }
                    post(new b(new a.b.a.a.e.g(string, string2, a.b.a.a.n.a.a(aVar), a.b.a.a.n.a.a(AudioCodecProfile.INSTANCE.a(jSONObject.getInt("codecProfile"))), jSONObject.getInt("numChannels"), jSONObject.getInt("samplerate"))));
                } catch (JSONException e2) {
                    throw new RuntimeException(e2);
                }
            } catch (RuntimeException e3) {
                throw new a.b.a.a.j.a("onAudioTrackChange invalid json format " + e3.getMessage());
            }
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onAvailableAudioTracks(String str) {
            post(new c(str, a.b.a.a.e.h.f54a.a(str)));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onAvailableClosedCaptionsChannels(String str) {
            try {
                List list = (List) ChannelIndex.INSTANCE.a(str, new ArrayList());
                if (!list.isEmpty()) {
                    a.this.h.a();
                }
                post(new RunnableC0004d(list));
            } catch (JSONException e2) {
                throw new a.b.a.a.j.a("onAvailableClosedCaptionsChannels invalid json format: " + e2.getMessage());
            }
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onAvailableClosedCaptionsServices(String str) {
            try {
                List list = (List) ServiceIndex.INSTANCE.a(str, new ArrayList());
                if (!list.isEmpty()) {
                    a.this.h.a();
                }
                post(new e(str, list));
            } catch (JSONException e2) {
                throw new a.b.a.a.j.a("onAvailableClosedCaptionsChannels invalid json format: " + e2.getMessage());
            }
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onCEA708(String str) {
            try {
                a.this.h.a(new a.b.a.a.g.g.a(str));
            } catch (JSONException e2) {
                a.this.d.b(this.f22a, "error parsing 708 json: " + e2.getMessage());
                e2.printStackTrace();
            }
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onEgressAllocated(int i2) {
            post(new f(i2));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onEgressContact(int i2) {
            post(new g(i2));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onError(int i2, String str) {
            post(new h(str, i2));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onErrorRetry(int i2, String str, int i3) {
            post(new i(i3, str, i2));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onFrontendError(int i2, String str, String str2) {
            post(new j(i2, SyeFrontendInfo.INSTANCE.a(str), str2));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onFrontendSuccess(String str) {
            post(new k(SyeFrontendInfo.INSTANCE.a(str)));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onNotificationMessage(SyeNotificationMessage syeNotificationMessage) {
            post(new l(syeNotificationMessage));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onStateChange(int i2, int i3) {
            post(new m(i3, i2));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onStopInternal() {
            a.this.d.c(this.f22a, "onStopInternal");
            a.this.d();
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onStreamingError(int i2, String str) {
            post(new n(i2, str));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onTimeToFirstFrame(int i2, int i3) {
            post(new o(i2, i3));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onTimelineUpdate(String str) {
            a.b.a.a.k.d dVar;
            if (Intrinsics.areEqual(str, "null")) {
                dVar = null;
            } else {
                try {
                    dVar = new a.b.a.a.k.d(str);
                } catch (JSONException e2) {
                    throw new RuntimeException(e2);
                }
            }
            post(new p(dVar != null ? new a.b.a.a.m.b(dVar) : null));
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onVideoSample(SyeVideoSample syeVideoSample) {
            try {
                a.this.f.onVideoSample(syeVideoSample);
            } catch (a.b.a.a.j.a e2) {
                a.this.d.b(this.f22a, "error in onVideoSample: " + e2.getMessage());
                a.this.a(new q(syeVideoSample, "reportError from onVideoSample callback"));
            }
        }

        @Override // com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper
        public void onVideoStreamChange(String str) {
            try {
                SyeVideoStreamInfo syeVideoStreamInfoA = SyeVideoStreamInfo.INSTANCE.a(str);
                try {
                    a.this.f.a(syeVideoStreamInfoA);
                } catch (a.b.a.a.j.a e2) {
                    a.this.d.b(this.f22a, "start video failed: " + e2.getMessage());
                    a.this.a(new r(syeVideoStreamInfoA, "reportError from onVideoTrackChange callback"));
                }
                post(new s(syeVideoStreamInfoA));
            } catch (RuntimeException e3) {
                throw new a.b.a.a.j.a("onVideoTrackChange invalid json format " + e3.getMessage());
            }
        }
    }

    public static final class d0 extends b {
        public final /* synthetic */ SyePlayRequest d;
        public final /* synthetic */ long e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d0(SyePlayRequest syePlayRequest, long j, String str) {
            super(str);
            this.d = syePlayRequest;
            this.e = j;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            String channelId = this.d.getChannelId();
            long j = this.e;
            nativeSyePlayer.getClass();
            nativeSyePlayer.playWithOffset(nativeSyePlayer.f762a, channelId, j);
        }
    }

    public static final class e extends b {
        public final /* synthetic */ SyeDTVCCSettings d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(SyeDTVCCSettings syeDTVCCSettings, String str) {
            super(str);
            this.d = syeDTVCCSettings;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            SyeDTVCCSettings syeDTVCCSettings = this.d;
            nativeSyePlayer.getClass();
            nativeSyePlayer.setDTVCCSettings(nativeSyePlayer.f762a, syeDTVCCSettings);
        }
    }

    public static final class e0 extends h<Object> {
        public e0(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            SyePlayerState.Companion companion = SyePlayerState.INSTANCE;
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return companion.a(nativeSyePlayer.getPlayerState(nativeSyePlayer.f762a));
        }
    }

    public static final class f extends h<Object> {
        public f(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return nativeSyePlayer.getDTVCCSettings(nativeSyePlayer.f762a);
        }
    }

    public static final class f0<V> implements Callable<Long> {
        public f0() {
        }

        @Override // java.util.concurrent.Callable
        public Long call() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return Long.valueOf(nativeSyePlayer.poll(nativeSyePlayer.f762a));
        }
    }

    public final class g implements ISyePlayerSynchronizationCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Handler f40a;
        public final ISyePlayerSynchronizationCallback b;

        /* JADX INFO: renamed from: a.b.a.a.a$g$a, reason: collision with other inner class name */
        public static final class RunnableC0005a implements Runnable {
            public final /* synthetic */ long b;

            public RunnableC0005a(long j) {
                this.b = j;
            }

            @Override // java.lang.Runnable
            public final void run() {
                a aVar = a.this;
                aVar.d.c(aVar.c, "onOffset");
                g.this.b.onOffset(this.b);
            }
        }

        public g(Handler handler, ISyePlayerSynchronizationCallback iSyePlayerSynchronizationCallback) {
            this.f40a = handler;
            this.b = iSyePlayerSynchronizationCallback;
        }

        @Override // com.netinsight.sye.syeClient.ISyePlayerSynchronizationCallback
        public void onOffset(long j) {
            this.f40a.post(new RunnableC0005a(j));
        }
    }

    public static final class g0 extends b {
        public final /* synthetic */ ChannelIndex d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g0(ChannelIndex channelIndex, String str) {
            super(str);
            this.d = channelIndex;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.selectClosedCaptionsChannel(nativeSyePlayer.f762a, this.d.getValue());
            }
        }
    }

    public abstract class h<V> implements Callable<V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f42a;

        public h(String str) {
            this.f42a = str;
        }

        public abstract V a();

        @Override // java.util.concurrent.Callable
        public V call() {
            a aVar = a.this;
            aVar.d.c(aVar.c, this.f42a);
            if (a.this.e != null) {
                return a();
            }
            throw new a.b.a.a.j.a("SyePlayer was torn down!");
        }
    }

    public static final class h0 extends b {
        public final /* synthetic */ ServiceIndex d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h0(ServiceIndex serviceIndex, String str) {
            super(str);
            this.d = serviceIndex;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.selectClosedCaptionsService(nativeSyePlayer.f762a, this.d.getValue());
            }
        }
    }

    public static final class i extends b {
        public final /* synthetic */ List d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(List list, String str) {
            super(str);
            this.d = list;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.addDecryptionKeys(nativeSyePlayer.f762a, new JNISyeDRMKeys(this.d));
            }
        }
    }

    public static final class i0 extends b {
        public final /* synthetic */ CCType d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i0(CCType cCType, String str) {
            super(str);
            this.d = cCType;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            a aVar = a.this;
            aVar.d.c(aVar.c, "setting ClosedCaptionsType to " + this.d);
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            CCType cCType = this.d;
            nativeSyePlayer.getClass();
            nativeSyePlayer.selectClosedCaptionsType(nativeSyePlayer.f762a, cCType.getValue());
        }
    }

    public static final class j extends b {
        public final /* synthetic */ ISyeAudioPreferences d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j(ISyeAudioPreferences iSyeAudioPreferences, String str) {
            super(str);
            this.d = iSyeAudioPreferences;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            a aVar = a.this;
            aVar.d.a(aVar.c, "Updating preferences...");
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            SyeAudioPreferences syeAudioPreferences = new SyeAudioPreferences(this.d.getLanguage(), this.d.getCodec(), this.d.getChannels());
            nativeSyePlayer.getClass();
            nativeSyePlayer.setAudioPreferences(nativeSyePlayer.f762a, syeAudioPreferences);
        }
    }

    public static final class j0 extends h<Object> {
        public j0(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            nativeSyePlayer.getClass();
            return CCType.INSTANCE.a(nativeSyePlayer.getSelectedClosedCaptionsType(nativeSyePlayer.f762a));
        }
    }

    public static final class k extends h<Object> {
        public k(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return nativeSyePlayer.getAudioPreferences(nativeSyePlayer.f762a);
        }
    }

    public static final class k0 extends h<Object> {
        public k0(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            ChannelIndex.Companion companion = ChannelIndex.INSTANCE;
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return companion.a(nativeSyePlayer.getSelectedClosedCaptionsChannel(nativeSyePlayer.f762a));
        }
    }

    public static final class l extends h<Object> {
        public l(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            a.b.a.a.k.e.a aVar;
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            try {
                JSONObject jSONObject = new JSONObject(nativeSyePlayer.getAudioStreamInfo(nativeSyePlayer.f762a));
                String string = jSONObject.getString("language");
                Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.getString(\"language\")");
                String string2 = jSONObject.getString("languageDescription");
                Intrinsics.checkExpressionValueIsNotNull(string2, "jsonObject.getString(\"languageDescription\")");
                int i = jSONObject.getInt("codec");
                if (i == 0) {
                    aVar = a.b.a.a.k.e.a.Undefined;
                } else if (i == 1) {
                    aVar = a.b.a.a.k.e.a.AAC;
                } else {
                    if (i != 2) {
                        throw new IllegalArgumentException("No AudioCodec with value " + i);
                    }
                    aVar = a.b.a.a.k.e.a.EC3;
                }
                return new a.b.a.a.e.g(string, string2, a.b.a.a.n.a.a(aVar), a.b.a.a.n.a.a(AudioCodecProfile.INSTANCE.a(jSONObject.getInt("codecProfile"))), jSONObject.getInt("numChannels"), jSONObject.getInt("samplerate"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static final class l0 extends h<Object> {
        public l0(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            ServiceIndex.Companion companion = ServiceIndex.INSTANCE;
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return companion.a(nativeSyePlayer.getSelectedClosedCaptionsService(nativeSyePlayer.f762a));
        }
    }

    public static final class m extends h<Object> {
        public m(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            h.a aVar = a.b.a.a.e.h.f54a;
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return aVar.a(nativeSyePlayer.getAvailableAudioTracks(nativeSyePlayer.f762a));
        }
    }

    public static final class m0 extends b {
        public final /* synthetic */ ISyePlayerSynchronizationCallback d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m0(ISyePlayerSynchronizationCallback iSyePlayerSynchronizationCallback, String str) {
            super(str);
            this.d = iSyePlayerSynchronizationCallback;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            a aVar = a.this;
            g gVar = aVar.new g(aVar.l, this.d);
            nativeSyePlayer.getClass();
            nativeSyePlayer.synchronize(nativeSyePlayer.f762a, gVar);
        }
    }

    public static final class n extends h<Object> {
        public n(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            try {
                ChannelIndex.Companion companion = ChannelIndex.INSTANCE;
                NativeSyePlayer nativeSyePlayer = a.this.e;
                String availableClosedCaptionsChannels = nativeSyePlayer != null ? nativeSyePlayer.getAvailableClosedCaptionsChannels(nativeSyePlayer.f762a) : null;
                if (availableClosedCaptionsChannels == null) {
                    availableClosedCaptionsChannels = "";
                }
                return companion.a(availableClosedCaptionsChannels, new ArrayList());
            } catch (JSONException unused) {
                a aVar = a.this;
                return Integer.valueOf(aVar.d.e(aVar.c, "JSONArray error in getAvailableClosedCaptionsChannels()"));
            }
        }
    }

    public static final class n0 extends b {
        public n0(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.poll(nativeSyePlayer.f762a);
            }
        }
    }

    public static final class o extends h<Object> {
        public o(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            try {
                ServiceIndex.Companion companion = ServiceIndex.INSTANCE;
                NativeSyePlayer nativeSyePlayer = a.this.e;
                String availableClosedCaptionsServices = nativeSyePlayer != null ? nativeSyePlayer.getAvailableClosedCaptionsServices(nativeSyePlayer.f762a) : null;
                if (availableClosedCaptionsServices == null) {
                    availableClosedCaptionsServices = "";
                }
                return companion.a(availableClosedCaptionsServices, new ArrayList());
            } catch (JSONException unused) {
                a aVar = a.this;
                aVar.d.e(aVar.c, "JSONArray error in getAvailableClosedCaptionsServices()");
                return CollectionsKt__CollectionsKt.emptyList();
            }
        }
    }

    public static final class o0 extends b {
        public o0(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.teardown(nativeSyePlayer.f762a);
            }
            a aVar = a.this;
            aVar.e = null;
            aVar.l.getLooper().quitSafely();
        }
    }

    public static final class p extends b {
        public final /* synthetic */ long d;
        public final /* synthetic */ long e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p(long j, long j2, String str) {
            super(str);
            this.d = j;
            this.e = j2;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.cacheThumbnailsForInterval(nativeSyePlayer.f762a, this.d, this.e);
            }
        }
    }

    public static final class p0 extends b {
        public final /* synthetic */ SyeSystem d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p0(SyeSystem syeSystem, String str) {
            super(str);
            this.d = syeSystem;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.updateSyeSystem(nativeSyePlayer.f762a, new JNISyeSystem(this.d));
            }
        }
    }

    public static final class q extends b {
        public final /* synthetic */ int d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q(int i, String str) {
            super(str);
            this.d = i;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.cacheThumbnailsFromLive(nativeSyePlayer.f762a, this.d);
            }
        }
    }

    public static final class q0 extends b {
        public final /* synthetic */ ISyeVideoPreferences d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q0(ISyeVideoPreferences iSyeVideoPreferences, String str) {
            super(str);
            this.d = iSyeVideoPreferences;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            SyeVideoPreferences syeVideoPreferences = new SyeVideoPreferences(this.d.getMaxBitrate(), this.d.getMaxHeight());
            nativeSyePlayer.getClass();
            nativeSyePlayer.setVideoPreferences(nativeSyePlayer.f762a, syeVideoPreferences);
        }
    }

    public static final class r implements Runnable {
        public r() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            Iterator<a.b.a.a.o.e.b> itB = a.this.f.b();
            while (itB.hasNext()) {
                itB.next().getContainingView().setAlpha(0.0f);
            }
        }
    }

    public static final class r0 extends h<Object> {
        public r0(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return nativeSyePlayer.getVideoPreferences(nativeSyePlayer.f762a);
        }
    }

    public static final class s extends h<String> {
        public s(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public String a() {
            String currentChannelId;
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null && (currentChannelId = nativeSyePlayer.getCurrentChannelId(nativeSyePlayer.f762a)) != null) {
                if (currentChannelId.length() > 0) {
                    return currentChannelId;
                }
            }
            return null;
        }
    }

    public static final class s0 extends h<Object> {
        public s0(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            SyeVideoStreamInfo.Companion companion = SyeVideoStreamInfo.INSTANCE;
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return companion.a(nativeSyePlayer.getVideoStreamInfo(nativeSyePlayer.f762a));
        }
    }

    public static final class t extends h<Object> {
        public final /* synthetic */ long d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public t(long j, String str) {
            super(str);
            this.d = j;
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            a.b.a.a.k.c cVar;
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            DatafulJsonString thumbnail = nativeSyePlayer.getThumbnail(nativeSyePlayer.f762a, this.d);
            String str = thumbnail.jsonString;
            byte[][] bArr = thumbnail.byteArrays;
            if (Intrinsics.areEqual(str, "null")) {
                cVar = null;
            } else {
                try {
                    cVar = new a.b.a.a.k.c(str, bArr);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cVar != null) {
                return new a.b.a.a.m.a(cVar);
            }
            return null;
        }
    }

    public static final class u extends h<Object> {
        public u(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.h
        public Object a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer == null) {
                Intrinsics.throwNpe();
            }
            return nativeSyePlayer.getMetrics(nativeSyePlayer.f762a);
        }
    }

    public static final class v extends b {
        public v(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.stop(nativeSyePlayer.f762a);
            }
        }
    }

    public static final class w extends b {
        public final /* synthetic */ a.b.a.a.e.a c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public w(a aVar, a.b.a.a.e.a aVar2, String str) {
            super(str);
            this.c = aVar2;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            int[] iArr = this.c.c;
            ArrayList arrayList = new ArrayList(iArr.length);
            for (int i : iArr) {
                arrayList.add(i != 6 ? a.b.a.a.k.e.a.AAC : a.b.a.a.k.e.a.EC3);
            }
            List listDistinct = CollectionsKt___CollectionsKt.distinct(arrayList);
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listDistinct, 10));
            Iterator it = listDistinct.iterator();
            while (it.hasNext()) {
                arrayList2.add(Integer.valueOf(((a.b.a.a.k.e.a) it.next()).f));
            }
            NativeSyeAPI.setAudioCapabilities(CollectionsKt___CollectionsKt.toIntArray(arrayList2), this.c.e);
        }
    }

    public static final class x extends b {
        public final /* synthetic */ a.b.a.a.k.e.f.a d;
        public final /* synthetic */ String e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public x(a.b.a.a.k.e.f.a aVar, String str, String str2) {
            super(str2);
            this.d = aVar;
            this.e = str;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.a(this.d.e, this.e);
            }
        }
    }

    public static final class y extends b {
        public final /* synthetic */ a.b.a.a.k.e.f.b d;
        public final /* synthetic */ int e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public y(a.b.a.a.k.e.f.b bVar, int i, String str) {
            super(str);
            this.d = bVar;
            this.e = i;
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.reportWarning(nativeSyePlayer.f762a, this.d.c, String.valueOf(this.e));
            }
        }
    }

    public static final class z extends b {
        public z(String str) {
            super(str);
        }

        @Override // a.b.a.a.a.b
        public void a() {
            NativeSyePlayer nativeSyePlayer = a.this.e;
            if (nativeSyePlayer != null) {
                nativeSyePlayer.pause(nativeSyePlayer.f762a);
            }
        }
    }

    public a(SyeSystem syeSystem, SyePlayerConfig syePlayerConfig, IErrorListener iErrorListener, Context context) {
        long jLongValue;
        Object obj;
        int i2 = f17a;
        f17a = i2 + 1;
        this.b = i2;
        this.c = "SyePlayer_" + i2;
        this.d = a.b.a.a.i.b.d.a();
        this.k = new a.b.a.a.i.a();
        this.m = Executors.newSingleThreadExecutor();
        this.o = new f0();
        this.p = syePlayerConfig.getContinuePlaybackInBackground();
        this.s = new Object();
        HandlerThread handlerThread = new HandlerThread("SyePlayerCallbackThread_" + i2);
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        Intrinsics.checkExpressionValueIsNotNull(looper, "syePlayerCallbackHandlerThread.looper");
        this.l = new d(looper);
        try {
            Field declaredField = SyePlayerConfig.class.getDeclaredField("b");
            Intrinsics.checkExpressionValueIsNotNull(declaredField, "SyePlayerConfig::class.j…eclaredField(\"nativeRef\")");
            declaredField.setAccessible(true);
            obj = declaredField.get(syePlayerConfig);
        } catch (Exception e2) {
            e2.printStackTrace();
            jLongValue = 0;
        }
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Long");
        }
        jLongValue = ((Long) obj).longValue();
        long j2 = jLongValue;
        JNISyeSystem jNISyeSystem = new JNISyeSystem(syeSystem);
        Object obj2 = this.l;
        if (obj2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.netinsight.sye.syeClient.internal.ISyePlayerCallbackWrapper");
        }
        this.e = new NativeSyePlayer(jNISyeSystem, (ISyePlayerCallbackWrapper) obj2, j2, new c());
        a.b.a.a.e.f fVar = new a.b.a.a.e.f(this.b);
        this.g = fVar;
        fVar.a(syePlayerConfig.getDynamicAudioLatencyCompensation());
        a.b.a.a.e.b bVar = new a.b.a.a.e.b(context, this);
        this.r = bVar;
        a(bVar.a());
        a.b.a.a.o.g.b bVar2 = new a.b.a.a.o.g.b(this.b, this);
        this.f = bVar2;
        bVar2.b(syePlayerConfig.getSmoothFrameRendering());
        bVar2.a(syePlayerConfig.getRenderLatencyCompensation());
        this.h = new a.b.a.a.g.e();
        this.k.a(iErrorListener);
        Thread thread = new Thread(new RunnableC0001a(), "SyePlayer poll");
        this.n = thread;
        thread.start();
    }

    public final int a() {
        return this.b;
    }

    @Override // a.b.a.a.e.b.a
    public void a(a.b.a.a.e.a aVar) {
        this.d.a(this.c, "onAudioCapabilitiesChanged " + aVar);
        a(new w(this, aVar, "onAudioCapabilitiesChanged"));
    }

    @Override // a.b.a.a.o.g.a.InterfaceC0023a
    public void a(a.b.a.a.k.e.f.a aVar, String str) {
        a(new x(aVar, str, "SyeVideoManager reporting error '" + aVar.name() + "' with a value of: '" + str + '\''));
    }

    @Override // a.b.a.a.o.g.a.InterfaceC0023a
    public void a(a.b.a.a.k.e.f.b bVar, int i2) {
        a(new y(bVar, i2, "SyeVideoManager reporting warning '" + bVar.name() + "' with a value of: '" + i2 + '\''));
    }

    public final void a(SyePlayRequest syePlayRequest) {
        this.d.a(this.c, "ClearViewIfNeeded()");
        if (syePlayRequest.getIsShouldClearDisplay()) {
            this.d.a(this.c, "Clear View since Needed");
            new Handler(Looper.getMainLooper()).post(new r());
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addDecryptionKeys(List<SyeDRMKey> list) {
        a(new i(list, "addDecryptionKeys"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(IAudioTrackListener iAudioTrackListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.e.add(iAudioTrackListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void attach(SurfaceView surfaceView) {
        a(surfaceView);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void attach(TextureView textureView) {
        a(textureView);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void attach(ISyePlayerView iSyePlayerView) {
        iSyePlayerView.attachTo(this);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void attach(SyeClosedCaptionView syeClosedCaptionView) {
        this.h.b(syeClosedCaptionView);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void attach(SyeVideoSurfaceView syeVideoSurfaceView) {
        a(syeVideoSurfaceView);
    }

    public final void b() {
        a(new v(AsmString.METHOD_FIREBASE_PERF_TRACE_STOP));
    }

    public final void c() {
        this.d.c(this.c, "resumePollThread");
        this.j = false;
        synchronized (this.s) {
            this.s.notifyAll();
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void cacheThumbnailsForInterval(long j2, long j3) {
        a(new p(j2, j3, "cacheThumbnailsForInterval"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void cacheThumbnailsFromLive(int i2) {
        a(new q(i2, "cacheThumbnailsFromLive"));
    }

    public final void d() {
        this.h.stop();
        this.f.a();
        a.b.a.a.e.f fVar = this.g;
        synchronized (fVar) {
            fVar.b.a(fVar.f52a, "stopAudio");
            for (a.b.a.a.e.c cVar : fVar.c) {
                cVar.e();
                cVar.a();
            }
            fVar.c.clear();
            synchronized (fVar.d) {
                fVar.d.clear();
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void detach(SurfaceView surfaceView) {
        b(surfaceView);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void detach(TextureView textureView) {
        b(textureView);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void detach(ISyePlayerView iSyePlayerView) {
        iSyePlayerView.detachFrom(this);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void detach(SyeClosedCaptionView syeClosedCaptionView) {
        this.h.a(syeClosedCaptionView);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void detach(SyeVideoSurfaceView syeVideoSurfaceView) {
        b(syeVideoSurfaceView);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public ISyeAudioPreferences getAudioPreferences() {
        SyeAudioPreferences syeAudioPreferences = (SyeAudioPreferences) a(new k("getAudioPreferences"));
        return syeAudioPreferences != null ? syeAudioPreferences : new SyeAudioPreferences("", SyeAudioCodec.Undefined, 0);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public a.b.a.a.e.g getAudioStreamInfo() {
        return (a.b.a.a.e.g) a(new l("getAudioStreamInfo"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public float getAudioVolume() {
        return this.g.g;
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public List<ISyeAudioTrack> getAvailableAudioTracks() {
        List<ISyeAudioTrack> list = (List) a(new m("getAvailableAudioTracks"));
        return list != null ? list : CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public List<ChannelIndex> getAvailableClosedCaptionsChannels() {
        List<ChannelIndex> list = (List) a(new n("getAvailableClosedCaptionsChannels"));
        return list != null ? list : CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public List<ServiceIndex> getAvailableClosedCaptionsServices() {
        List<ServiceIndex> list = (List) a(new o("getAvailableClosedCaptionsServices"));
        return list != null ? list : CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public String getCurrentChannelId() {
        return (String) a(new s("getCurrentChannelId"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public SyeDTVCCSettings getDTVCCSettings() {
        SyeDTVCCSettings syeDTVCCSettings = (SyeDTVCCSettings) a(new f("getDTVCCSettings"));
        if (syeDTVCCSettings == null) {
            Intrinsics.throwNpe();
        }
        return syeDTVCCSettings;
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public ISyeDisplaySettings getDisplaySettings(View view) {
        a.b.a.a.o.e.b bVarA = a.b.a.a.o.e.a.b.a(view);
        if (bVarA != null) {
            return bVarA.getDisplaySettings();
        }
        return null;
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public ISyeMetrics getMetrics() {
        return (ISyeMetrics) a(new u("getMetrics"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public SyePlayerState getPlayerState() {
        return (SyePlayerState) a(new e0("getPlayerState"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public CCType getSelectedCCType() {
        CCType cCType = (CCType) a(new j0("getSelectedCCType"));
        if (cCType == null) {
            Intrinsics.throwNpe();
        }
        return cCType;
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public ChannelIndex getSelectedClosedCaptionsChannel() {
        return (ChannelIndex) a(new k0("getSelectedClosedCaptionsChannel"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public ServiceIndex getSelectedClosedCaptionsService() {
        return (ServiceIndex) a(new l0("getSelectedClosedCaptionsService"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public ISyeThumbnail getThumbnail(long j2) {
        return (ISyeThumbnail) a(new t(j2, "getThumbnail @time=" + j2));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public ISyeVideoPreferences getVideoPreferences() {
        SyeVideoPreferences syeVideoPreferences = (SyeVideoPreferences) a(new r0("getVideoPreferences"));
        if (syeVideoPreferences == null) {
            Intrinsics.throwNpe();
        }
        return syeVideoPreferences;
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public SyeVideoStreamInfo getVideoStreamInfo() {
        return (SyeVideoStreamInfo) a(new s0("getVideoStreamInfo"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public boolean isClosedCaptionsEnabled() {
        this.d.c(this.c, "isClosedCaptionsEnabled");
        a.b.a.a.g.a aVar = this.h;
        if (aVar != null) {
            return ((a.b.a.a.g.e) aVar).h;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.netinsight.sye.syeClient.closedCaptions.SyeClosedCaptionManager");
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void pause() {
        a(new z("pause"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void playFromLive(SyePlayRequest syePlayRequest) {
        this.q = syePlayRequest;
        if (syePlayRequest == null) {
            Intrinsics.throwNpe();
        }
        String channelId = syePlayRequest.getChannelId();
        SyePlayRequest syePlayRequest2 = this.q;
        if (syePlayRequest2 == null) {
            Intrinsics.throwNpe();
        }
        SyePlayRequest syePlayRequest3 = new SyePlayRequest(channelId, syePlayRequest2.getIsShouldClearDisplay());
        a(syePlayRequest3);
        a(new a0(syePlayRequest3, "playFromLive"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void playFromLive(String str) {
        SyePlayRequest syePlayRequest = new SyePlayRequest(str);
        this.q = syePlayRequest;
        String channelId = syePlayRequest.getChannelId();
        SyePlayRequest syePlayRequest2 = this.q;
        if (syePlayRequest2 == null) {
            Intrinsics.throwNpe();
        }
        SyePlayRequest syePlayRequest3 = new SyePlayRequest(channelId, syePlayRequest2.getIsShouldClearDisplay());
        a(syePlayRequest3);
        a(new a0(syePlayRequest3, "playFromLive"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void playFromUtcTime(SyePlayRequest syePlayRequest, long j2) {
        this.q = syePlayRequest;
        if (syePlayRequest == null) {
            Intrinsics.throwNpe();
        }
        String channelId = syePlayRequest.getChannelId();
        SyePlayRequest syePlayRequest2 = this.q;
        if (syePlayRequest2 == null) {
            Intrinsics.throwNpe();
        }
        SyePlayRequest syePlayRequest3 = new SyePlayRequest(channelId, syePlayRequest2.getIsShouldClearDisplay());
        a(syePlayRequest3);
        a(new b0(syePlayRequest3, j2, "playFromUtcTime(" + syePlayRequest3.getChannelId() + ',' + j2 + ')'));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void playFromUtcTime(String str, long j2) {
        SyePlayRequest syePlayRequest = new SyePlayRequest(str);
        this.q = syePlayRequest;
        String channelId = syePlayRequest.getChannelId();
        SyePlayRequest syePlayRequest2 = this.q;
        if (syePlayRequest2 == null) {
            Intrinsics.throwNpe();
        }
        SyePlayRequest syePlayRequest3 = new SyePlayRequest(channelId, syePlayRequest2.getIsShouldClearDisplay());
        a(syePlayRequest3);
        a(new b0(syePlayRequest3, j2, "playFromUtcTime(" + syePlayRequest3.getChannelId() + ',' + j2 + ')'));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void playResume() {
        a(new c0("playResume"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void playWithOffset(SyePlayRequest syePlayRequest, long j2) {
        this.q = syePlayRequest;
        if (syePlayRequest == null) {
            Intrinsics.throwNpe();
        }
        String channelId = syePlayRequest.getChannelId();
        SyePlayRequest syePlayRequest2 = this.q;
        if (syePlayRequest2 == null) {
            Intrinsics.throwNpe();
        }
        SyePlayRequest syePlayRequest3 = new SyePlayRequest(channelId, syePlayRequest2.getIsShouldClearDisplay());
        a(syePlayRequest3);
        a(new d0(syePlayRequest3, j2, "playWithOffset(" + syePlayRequest3.getChannelId() + ',' + j2 + ')'));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void playWithOffset(String str, long j2) {
        SyePlayRequest syePlayRequest = new SyePlayRequest(str);
        this.q = syePlayRequest;
        String channelId = syePlayRequest.getChannelId();
        SyePlayRequest syePlayRequest2 = this.q;
        if (syePlayRequest2 == null) {
            Intrinsics.throwNpe();
        }
        SyePlayRequest syePlayRequest3 = new SyePlayRequest(channelId, syePlayRequest2.getIsShouldClearDisplay());
        a(syePlayRequest3);
        a(new d0(syePlayRequest3, j2, "playWithOffset(" + syePlayRequest3.getChannelId() + ',' + j2 + ')'));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(IAudioTrackListener iAudioTrackListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.e.remove(iAudioTrackListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void selectClosedCaptionsChannel(ChannelIndex channelIndex) {
        a(new g0(channelIndex, "selectClosedCaptionsChannel: " + channelIndex.getValue()));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void selectClosedCaptionsService(ServiceIndex serviceIndex) {
        a(new h0(serviceIndex, "selectClosedCaptionsService: " + serviceIndex.getValue()));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void setAudioPreferences(ISyeAudioPreferences iSyeAudioPreferences) {
        a(new j(iSyeAudioPreferences, "setAudioPreferences"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void setAudioVolume(float f2) {
        a.b.a.a.e.f fVar = this.g;
        fVar.g = f2;
        Iterator<a.b.a.a.e.c> it = fVar.c.iterator();
        while (it.hasNext()) {
            it.next().a(f2);
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void setClosedCaptionsEnabled(boolean z2) {
        this.d.c(this.c, "setClosedCaptionsEnabled to" + z2);
        a.b.a.a.g.a aVar = this.h;
        if (aVar == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.netinsight.sye.syeClient.closedCaptions.SyeClosedCaptionManager");
        }
        a.b.a.a.g.e eVar = (a.b.a.a.g.e) aVar;
        eVar.h = z2;
        List<a.b.a.a.g.d> renderers = eVar.d;
        Intrinsics.checkExpressionValueIsNotNull(renderers, "renderers");
        synchronized (renderers) {
            for (Object obj : eVar.d) {
                if (obj instanceof View) {
                    e.b.a(a.b.a.a.g.e.b, (View) obj, z2);
                }
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void setDTVCCSettings(SyeDTVCCSettings syeDTVCCSettings) {
        a(new e(syeDTVCCSettings, "setDTVCCSettings"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void setDisplaySettings(TextureView textureView, ISyeDisplaySettings iSyeDisplaySettings) {
        a.b.a.a.o.e.b bVarA = a.b.a.a.o.e.a.b.a(textureView);
        if (bVarA != null) {
            bVarA.setDisplaySettings(iSyeDisplaySettings);
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void setSelectedCCType(CCType cCType) {
        a(new i0(cCType, "setSelectedCCType: " + cCType));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void setVideoPreferences(ISyeVideoPreferences iSyeVideoPreferences) {
        a(new q0(iSyeVideoPreferences, "setVideoPreferences"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void stop() {
        b();
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void synchronize(ISyePlayerSynchronizationCallback iSyePlayerSynchronizationCallback) {
        a(new m0(iSyePlayerSynchronizationCallback, "synchronize"));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void teardown() {
        this.d.c(this.c, "teardown");
        if (this.e != null) {
            this.d.c(this.c, "killPollThread");
            this.i = true;
            c();
            try {
                this.n.join();
                this.d.a(this.c, "pollThread destroyed");
                if (!this.t) {
                    d();
                    b();
                    a(new n0("last poll"));
                }
                this.g.getClass();
                a(new o0("teardown - delete native bazPlayer"));
                this.d.a(this.c, "API threadExecutor shutdown +");
                this.m.shutdown();
                this.d.a(this.c, "API threadExecutor shutdown -");
                this.k.onTeardown();
                a.b.a.a.o.e.a.f130a.clear();
                a.b.a.a.e.b bVar = this.r;
                BroadcastReceiver broadcastReceiver = bVar.b;
                if (broadcastReceiver != null) {
                    bVar.d.unregisterReceiver(broadcastReceiver);
                }
            } catch (InterruptedException unused) {
                throw new RuntimeException("Thread interrupted while waiting for " + this.n.getName() + " to join.");
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void updateSyeSystem(SyeSystem syeSystem) {
        a(new p0(syeSystem, "updateSyeSystem"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(View view) {
        Map<View, a.b.a.a.o.e.b> map = a.b.a.a.o.e.a.f130a;
        SyeDisplaySettings syeDisplaySettings = new SyeDisplaySettings();
        Map<View, a.b.a.a.o.e.b> cachedViews = a.b.a.a.o.e.a.f130a;
        a.b.a.a.o.e.b fVar = cachedViews.get(view);
        if (fVar == null) {
            if (view instanceof TextureView) {
                fVar = new a.b.a.a.o.e.g((TextureView) view, syeDisplaySettings);
            } else if ((view instanceof SurfaceView) && !(view instanceof SyeVideoSurfaceView)) {
                fVar = new a.b.a.a.o.e.f((SurfaceView) view, syeDisplaySettings);
            } else {
                if (!(view instanceof SyeVideoSurfaceView)) {
                    throw new a.b.a.a.j.a("Attempting to attach unsupported view type");
                }
                ((SyeVideoSurfaceView) view).setDisplaySettings(syeDisplaySettings);
                fVar = (a.b.a.a.o.e.b) view;
            }
            Intrinsics.checkExpressionValueIsNotNull(cachedViews, "cachedViews");
            cachedViews.put(view, fVar);
        }
        this.f.b(fVar);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(IClosedCaptionListener iClosedCaptionListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.g.add(iClosedCaptionListener);
    }

    public final void b(View view) {
        a.b.a.a.o.e.b bVarA = a.b.a.a.o.e.a.b.a(view);
        if (bVarA != null) {
            this.f.a(bVarA);
            Map<View, a.b.a.a.o.e.b> map = a.b.a.a.o.e.a.f130a;
            a.b.a.a.o.e.b bVar = map.get(view);
            if (bVar != null) {
                map.remove(view);
                bVar.teardown();
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(IClosedCaptionListener iClosedCaptionListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.g.remove(iClosedCaptionListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(IDTVClosedCaptionListener iDTVClosedCaptionListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.h.add(iDTVClosedCaptionListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(IDTVClosedCaptionListener iDTVClosedCaptionListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.h.remove(iDTVClosedCaptionListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(IEgressInfoListener iEgressInfoListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.d.add(iEgressInfoListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(IEgressInfoListener iEgressInfoListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.d.remove(iEgressInfoListener);
    }

    @Override // a.b.a.a.o.g.a.InterfaceC0023a
    public void a(long j2) {
        this.d.b(b.a.Video, this.c, "onFirstFrameRendered @ " + j2 + " us");
        this.d.a(this.c, "showViewIfCleared()");
        new Handler(Looper.getMainLooper()).post(new a.b.a.a.d(this));
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(IErrorListener iErrorListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.b.add(iErrorListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(IErrorListener iErrorListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.b.remove(iErrorListener);
    }

    public final void a(b bVar) {
        try {
            this.m.submit(bVar);
        } catch (NullPointerException unused) {
            this.d.e(this.c, bVar.f19a + " was not executed, null task");
        } catch (RejectedExecutionException unused2) {
            this.d.e(this.c, bVar.f19a + " was not executed, player is torn down");
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(INotificationListener iNotificationListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.i.add(iNotificationListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(INotificationListener iNotificationListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.i.remove(iNotificationListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(IStateChangeListener iStateChangeListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.c.add(iStateChangeListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(IStateChangeListener iStateChangeListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.c.remove(iStateChangeListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(IStatusListener iStatusListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.f69a.add(iStatusListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(IStatusListener iStatusListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.f69a.add(iStatusListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(ITeardownListener iTeardownListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.k.add(iTeardownListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(ITeardownListener iTeardownListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.k.remove(iTeardownListener);
    }

    public final <T> T a(h<?> hVar) {
        try {
            try {
                try {
                    try {
                        return this.m.submit(hVar).get();
                    } catch (a.b.a.a.j.a e2) {
                        this.d.b(this.c, "SyeException " + hVar.f42a + ' ' + e2.getLocalizedMessage());
                        e2.printStackTrace();
                        return null;
                    } catch (InterruptedException e3) {
                        this.d.b(this.c, "InterruptedException " + hVar.f42a + "  " + e3.getLocalizedMessage());
                        e3.printStackTrace();
                        return null;
                    } catch (ExecutionException e4) {
                        this.d.b(this.c, "ExecutionException " + hVar.f42a + "  " + e4.getLocalizedMessage());
                        e4.printStackTrace();
                        return null;
                    }
                } catch (RejectedExecutionException unused) {
                    this.d.e(this.c, hVar.f42a + " was not executed, player is torn down");
                    return null;
                }
            } catch (NullPointerException unused2) {
                this.d.e(this.c, hVar.f42a + " was not executed, null task");
                return null;
            }
        } catch (Throwable unused3) {
            return null;
        }
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(ITimelineListener iTimelineListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.j.add(iTimelineListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(ITimelineListener iTimelineListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.j.remove(iTimelineListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void addListener(IVideoTrackListener iVideoTrackListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.f.add(iVideoTrackListener);
    }

    @Override // com.netinsight.sye.syeClient.ISyePlayer
    public void removeListener(IVideoTrackListener iVideoTrackListener) {
        a.b.a.a.i.a aVar = this.k;
        aVar.getClass();
        aVar.f.remove(iVideoTrackListener);
    }
}
