package a.b.a.a.e;

import a.a.a.a.a.c.e;
import android.media.MediaFormat;
import com.netinsight.sye.syeClient.audio.SyeAudioSample;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f51a;
    public final /* synthetic */ MediaFormat b;
    public final /* synthetic */ int c;
    public final /* synthetic */ String d;
    public final /* synthetic */ SyeAudioSample e;
    public final /* synthetic */ Function1 f;

    public e(f fVar, MediaFormat mediaFormat, int i, String str, SyeAudioSample syeAudioSample, Function1 function1) {
        this.f51a = fVar;
        this.b = mediaFormat;
        this.c = i;
        this.d = str;
        this.e = syeAudioSample;
        this.f = function1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            MediaFormat mediaFormat = this.b;
            int i = this.c;
            f fVar = this.f51a;
            a.a.a.a.a.c cVarA = this.f51a.a(this.b, this.d, this.e, new a.a.a.a.a.f(mediaFormat, i, fVar.g, fVar.e));
            cVarA.a("InputBufferThread", new a.a.a.a.a.d(cVarA));
            cVarA.a("OutputBufferThread", new a.a.a.a.a.e(cVarA));
            cVarA.m.b();
            cVarA.a("OutputQueueThread", cVarA.new e());
            this.f51a.c.add(cVarA);
        } catch (a.b.a.a.j.a unused) {
            this.f.invoke(this.e.toString());
        }
    }
}
