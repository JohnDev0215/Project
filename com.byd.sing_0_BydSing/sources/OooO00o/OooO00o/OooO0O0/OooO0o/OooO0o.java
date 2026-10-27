package OooO00o.OooO00o.OooO0o0.OooO0o;

import android.media.AudioRecord;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.w;
import com.loostone.libbottom.util.permission.PermissionActivity;
import java.util.concurrent.TimeUnit;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f481a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Integer[] f482b = {48000, 44100, 16000};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final kotlin.a<OooO0o> f483c = kotlin.d.a(LazyThreadSafetyMode.SYNCHRONIZED, OooO00o.f485OooO00o);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f484d;
    public Thread e;
    public byte[] f;
    public AudioRecord g;
    public w.d<String> h;
    public int i = 1;

    public static final class OooO00o extends Lambda implements kotlin.jvm.b.a<OooO0o> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO00o f485OooO00o = new OooO00o();

        public OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO0o invoke() {
            return new OooO0o();
        }
    }

    public static final class a {
        static {
            j.d(new PropertyReference1Impl(j.a(a.class), "instance", "getInstance()Lcom/loostone/libtuning/util/MyAudioRecord;"));
        }

        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }

        public final OooO0o a() {
            return (OooO0o) OooO0o.f483c.getValue();
        }
    }

    public final class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ OooO0o f486b;

        public b(OooO0o oooO0o) {
            h.d(oooO0o, "this$0");
            this.f486b = oooO0o;
        }

        @Override // java.lang.Runnable
        public void run() {
            byte[] bArr;
            while (this.f486b.g != null && this.f486b.e != null && !Thread.currentThread().isInterrupted() && this.f486b.f != null) {
                try {
                    AudioRecord audioRecord = this.f486b.g;
                    if (audioRecord != null) {
                        byte[] bArr2 = this.f486b.f;
                        if (bArr2 == null || (bArr = this.f486b.f) == null) {
                            h.m("buffer");
                            throw null;
                        }
                        audioRecord.read(bArr2, 0, bArr.length);
                    }
                } catch (IllegalStateException e) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.d("MyAudioRecord", h.i("ProcessVoice read, error :: ", e));
                    return;
                }
            }
        }
    }

    public static final class c extends w.d<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f487b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ OooO0o f488c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(int i, OooO0o oooO0o) {
            super(null);
            this.f487b = i;
            this.f488c = oooO0o;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground() {
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.b("MyAudioRecord", h.i("try ReCreateAudioRecord, count = ", Integer.valueOf(this.f487b)));
            OooO0o oooO0o = this.f488c;
            oooO0o.g = oooO0o.h();
            if (this.f488c.g == null) {
                bVar.d("MyAudioRecord", "Cannot instantiate VoiceRecorder");
                OooO0o oooO0o2 = this.f488c;
                int i = oooO0o2.i;
                oooO0o2.i = i + 1;
                oooO0o2.c(i);
                return "Cannot instantiate VoiceRecorder, retry.";
            }
            AudioRecord audioRecord = this.f488c.g;
            if (audioRecord != null) {
                audioRecord.startRecording();
            }
            this.f488c.e = new Thread(new b(this.f488c));
            Thread thread = this.f488c.e;
            if (thread != null) {
                thread.start();
            }
            bVar.b("MyAudioRecord", "ReCreateAudioRecord success");
            return "ReCreateAudioRecord success.";
        }
    }

    public static final class d implements OooO00o.OooO00o.OooO0O0.OooO0o0.e.a {
        public d() {
        }

        @Override // OooO00o.OooO00o.OooO0O0.OooO0o0.e.a
        public void a() {
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.e("MyAudioRecord", h.i("start onPermissionGranted, isStart = ", Boolean.valueOf(OooO0o.this.k())));
            if (OooO0o.this.k()) {
                return;
            }
            OooO0o.this.g(true);
            bVar.e("MyAudioRecord", " ------> start Global AudioRecord");
            OooO0o oooO0o = OooO0o.this;
            oooO0o.g = oooO0o.h();
            if (OooO0o.this.g == null) {
                bVar.d("MyAudioRecord", "Cannot instantiate VoiceRecorder");
                OooO0o.this.i = 1;
                OooO0o oooO0o2 = OooO0o.this;
                int i = oooO0o2.i;
                oooO0o2.i = i + 1;
                oooO0o2.c(i);
                return;
            }
            AudioRecord audioRecord = OooO0o.this.g;
            if (audioRecord != null) {
                audioRecord.startRecording();
            }
            OooO0o.this.e = new Thread(new b(OooO0o.this));
            Thread thread = OooO0o.this.e;
            if (thread == null) {
                return;
            }
            thread.start();
        }

        @Override // OooO00o.OooO00o.OooO0O0.OooO0o0.e.a
        public void b(String[] strArr) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e("MyAudioRecord", " ------> onPermissionReject");
        }
    }

    public final void c(int i) {
        if (i > 10) {
            return;
        }
        w.d<String> dVar = this.h;
        if (dVar != null) {
            ThreadUtils.d(dVar);
            this.h = null;
        }
        c cVar = new c(i, this);
        this.h = cVar;
        ThreadUtils.i(cVar, 500L, TimeUnit.MILLISECONDS);
    }

    public final void g(boolean z) {
        this.f484d = z;
    }

    public final AudioRecord h() {
        Integer[] numArr = f482b;
        int length = numArr.length;
        int i = 0;
        while (i < length) {
            int iIntValue = numArr[i].intValue();
            i++;
            int minBufferSize = AudioRecord.getMinBufferSize(iIntValue, 12, 2);
            if (minBufferSize != -2) {
                AudioRecord audioRecord = new AudioRecord(1, iIntValue, 12, 2, minBufferSize);
                if (audioRecord.getState() == 1) {
                    this.f = new byte[minBufferSize];
                    return audioRecord;
                }
                audioRecord.release();
            }
        }
        return null;
    }

    public final boolean k() {
        return this.f484d;
    }

    public final void n() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("MyAudioRecord", " will start audio record");
        PermissionActivity.f3800OooO00o.OooO00o(new String[]{"android.permission.RECORD_AUDIO"}, new d());
    }

    public final void p() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("MyAudioRecord", h.i(" ------> stop Global AudioRecord, isStart = ", Boolean.valueOf(this.f484d)));
        Thread thread = this.e;
        if (thread != null) {
            thread.interrupt();
        }
        this.e = null;
        AudioRecord audioRecord = this.g;
        if (audioRecord != null) {
            audioRecord.release();
        }
        this.g = null;
        this.i = 10;
        w.d<String> dVar = this.h;
        if (dVar != null) {
            ThreadUtils.d(dVar);
            this.h = null;
        }
        this.f484d = false;
    }
}
