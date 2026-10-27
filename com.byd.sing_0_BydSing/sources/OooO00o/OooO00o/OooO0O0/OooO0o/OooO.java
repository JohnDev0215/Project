package OooO00o.OooO00o.OooO0o0.OooO0o;

import android.media.AudioTrack;
import kotlin.LazyThreadSafetyMode;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
public final class OooO {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f471a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final kotlin.a<OooO> f472b = d.a(LazyThreadSafetyMode.SYNCHRONIZED, OooO00o.f475OooO00o);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f473c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Thread f474d;
    public byte[] e;
    public AudioTrack f;

    public static final class OooO00o extends Lambda implements kotlin.jvm.b.a<OooO> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO00o f475OooO00o = new OooO00o();

        public OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO invoke() {
            return new OooO();
        }
    }

    public static final class a {
        static {
            j.d(new PropertyReference1Impl(j.a(a.class), "instance", "getInstance()Lcom/loostone/libtuning/util/MyAudioTrack;"));
        }

        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }

        public final OooO a() {
            return (OooO) OooO.f472b.getValue();
        }
    }

    public final class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ OooO f476b;

        public b(OooO oooO) {
            h.d(oooO, "this$0");
            this.f476b = oooO;
        }

        @Override // java.lang.Runnable
        public void run() {
            byte[] bArr;
            while (this.f476b.f != null && this.f476b.f474d != null && !Thread.currentThread().isInterrupted() && this.f476b.e != null) {
                try {
                    AudioTrack audioTrack = this.f476b.f;
                    if (audioTrack != null) {
                        byte[] bArr2 = this.f476b.e;
                        if (bArr2 == null || (bArr = this.f476b.e) == null) {
                            h.m("buffer");
                            throw null;
                        }
                        audioTrack.write(bArr2, 0, bArr.length);
                    }
                } catch (IllegalStateException e) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.d("MyAudioTrack", h.i("ProcessVoice write, error :: ", e));
                    return;
                }
            }
        }
    }

    public final AudioTrack c() {
        int minBufferSize = AudioTrack.getMinBufferSize(48000, 12, 2);
        if (minBufferSize < 0) {
            return null;
        }
        this.e = new byte[minBufferSize * 2];
        return new AudioTrack(1, 48000, 12, 2, 19200, 1);
    }

    public final void f() {
        if (this.f473c) {
            return;
        }
        this.f473c = true;
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e("MyAudioTrack", " ------> start Global AudioTrack");
        AudioTrack audioTrackC = c();
        this.f = audioTrackC;
        if (audioTrackC == null) {
            bVar.d("MyAudioTrack", "can not create audio track");
        }
        AudioTrack audioTrack = this.f;
        if (audioTrack != null) {
            audioTrack.play();
        }
        Thread thread = new Thread(new b(this));
        this.f474d = thread;
        thread.start();
    }

    public final void g() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("MyAudioTrack", h.i(" ------> stop Global AudioTrack, isStart = ", Boolean.valueOf(this.f473c)));
        Thread thread = this.f474d;
        if (thread != null) {
            thread.interrupt();
        }
        this.f474d = null;
        AudioTrack audioTrack = this.f;
        if (audioTrack != null) {
            audioTrack.release();
        }
        this.f = null;
        this.f473c = false;
    }
}
