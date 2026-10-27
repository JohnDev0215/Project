package a.b.a.a.e;

import a.a.a.a.a.c;
import com.netinsight.sye.syeClient.audio.SyeAudioSample;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class d implements c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f49a;
    public final /* synthetic */ Function1 b;
    public final /* synthetic */ SyeAudioSample c;

    public static final class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                c cVarPoll = d.this.f49a.c.poll();
                if (cVarPoll != null) {
                    cVarPoll.e();
                }
                c cVarPeek = d.this.f49a.c.peek();
                if (cVarPeek != null) {
                    cVarPeek.b();
                }
            } catch (a.b.a.a.j.a unused) {
                d dVar = d.this;
                dVar.b.invoke(dVar.c.toString());
            }
        }
    }

    public d(f fVar, Function1 function1, SyeAudioSample syeAudioSample) {
        this.f49a = fVar;
        this.b = function1;
        this.c = syeAudioSample;
    }

    @Override // a.a.a.a.a.c.a
    public void a() {
        this.f49a.f.post(new a());
    }
}
