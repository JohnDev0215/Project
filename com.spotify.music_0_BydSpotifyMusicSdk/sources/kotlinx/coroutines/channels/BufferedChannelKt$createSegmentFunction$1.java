package kotlinx.coroutines.channels;

import kotlin.jvm.internal.FunctionReferenceImpl;
import p.er;
import p.fz0;
import p.ho;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class BufferedChannelKt$createSegmentFunction$1 extends FunctionReferenceImpl implements fz0 {
    public static final BufferedChannelKt$createSegmentFunction$1 k = new BufferedChannelKt$createSegmentFunction$1();

    public BufferedChannelKt$createSegmentFunction$1() {
        super(2, ho.class, "createSegment", "createSegment(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", 1);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        long jLongValue = ((Number) obj).longValue();
        er erVar = (er) obj2;
        er erVar2 = ho.a;
        a aVar = erVar.m;
        v03.h(aVar);
        return new er(jLongValue, erVar, aVar, 0);
    }
}
