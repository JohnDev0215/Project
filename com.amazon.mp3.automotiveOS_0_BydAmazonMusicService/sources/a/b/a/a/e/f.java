package a.b.a.a.e;

import a.b.a.a.n.b;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import com.netinsight.sye.syeClient.audio.SyeAudioSample;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f52a;
    public final a.b.a.a.i.b b;
    public final LinkedList<c> c = new LinkedList<>();
    public final LinkedList<a.a.a.a.a.a> d = new LinkedList<>();
    public boolean e = true;
    public final Handler f = new Handler(Looper.getMainLooper());
    public float g = 1.0f;
    public final int h;

    public f(int i) {
        this.h = i;
        String str = "SyeAudioManager_P" + i;
        this.f52a = str;
        this.b = a.b.a.a.i.b.d.a(str, b.a.Audio);
    }

    public final synchronized a.a.a.a.a.c a(MediaFormat mediaFormat, String str, SyeAudioSample syeAudioSample, a.a.a.a.a.f fVar) {
        a.a.a.a.a.c cVar;
        if (syeAudioSample.getNumChannels() == 0) {
            throw new a.b.a.a.j.a("No channels defined for output audio track");
        }
        try {
            cVar = new a.a.a.a.a.c(mediaFormat, str, fVar, this.h);
            synchronized (this.d) {
                cVar.a(this.d);
            }
            synchronized (this.d) {
                this.d.clear();
            }
        } catch (Exception e) {
            throw new a.b.a.a.j.a(e.getMessage());
        }
        return cVar;
    }

    public final void a(boolean z) {
        this.e = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0174 A[Catch: all -> 0x01ba, TryCatch #2 {, blocks: (B:4:0x0005, B:8:0x0011, B:13:0x0030, B:18:0x003a, B:20:0x0040, B:38:0x013c, B:43:0x015a, B:44:0x015b, B:45:0x015c, B:47:0x0174, B:49:0x0182, B:50:0x018d, B:55:0x019f, B:56:0x01a1, B:58:0x01a7, B:61:0x01aa, B:62:0x01ab, B:65:0x01ae, B:66:0x01b9, B:9:0x0014, B:10:0x002a, B:11:0x002b, B:12:0x002e, B:57:0x01a2, B:21:0x0041, B:23:0x0049, B:26:0x006a, B:28:0x00b8, B:31:0x00bd, B:34:0x00c5, B:36:0x00ee, B:37:0x0127, B:40:0x013e, B:41:0x0158), top: B:73:0x0005, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x019f A[Catch: all -> 0x01ba, TryCatch #2 {, blocks: (B:4:0x0005, B:8:0x0011, B:13:0x0030, B:18:0x003a, B:20:0x0040, B:38:0x013c, B:43:0x015a, B:44:0x015b, B:45:0x015c, B:47:0x0174, B:49:0x0182, B:50:0x018d, B:55:0x019f, B:56:0x01a1, B:58:0x01a7, B:61:0x01aa, B:62:0x01ab, B:65:0x01ae, B:66:0x01b9, B:9:0x0014, B:10:0x002a, B:11:0x002b, B:12:0x002e, B:57:0x01a2, B:21:0x0041, B:23:0x0049, B:26:0x006a, B:28:0x00b8, B:31:0x00bd, B:34:0x00c5, B:36:0x00ee, B:37:0x0127, B:40:0x013e, B:41:0x0158), top: B:73:0x0005, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void a(com.netinsight.sye.syeClient.audio.SyeAudioSample r17, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r18) {
        /*
            Method dump skipped, instruction units count: 445
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.a.a.e.f.a(com.netinsight.sye.syeClient.audio.SyeAudioSample, kotlin.jvm.functions.Function1):void");
    }
}
