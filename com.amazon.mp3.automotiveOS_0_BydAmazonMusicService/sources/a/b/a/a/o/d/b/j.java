package a.b.a.a.o.d.b;

import a.b.a.a.n.b;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f119a = -1;
    public final String b;
    public final a.b.a.a.i.b c;
    public long d;
    public long e;
    public long f;
    public boolean g;
    public final Function1<Long, Unit> h;

    public enum a {
        DROP,
        DECODE_ONLY,
        RENDER_IMMEDIATELY,
        RENDER_AT_PTS,
        RETRY_LATER
    }

    /* JADX WARN: Multi-variable type inference failed */
    public j(int i, Function1<? super Long, Unit> function1) {
        this.h = function1;
        String str = "VideoFrameAnalyzer_P" + i;
        this.b = str;
        a.b.a.a.i.b bVarA = a.b.a.a.i.b.d.a(str, b.a.Video);
        this.c = bVarA;
        this.g = true;
        bVarA.a(str, "Creating new frame analyzer");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.Pair<a.b.a.a.o.d.b.j.a, java.lang.Long> a(long r23, int r25, boolean r26, boolean r27, a.b.a.a.o.d.b.l r28) {
        /*
            Method dump skipped, instruction units count: 562
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.a.a.o.d.b.j.a(long, int, boolean, boolean, a.b.a.a.o.d.b.l):kotlin.Pair");
    }
}
