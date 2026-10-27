package kotlinx.coroutines.sync;

import kotlin.jvm.internal.FunctionReferenceImpl;
import p.fb2;
import p.fz0;
import p.gb2;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class SemaphoreImpl$addAcquireToQueue$createNewSegment$1 extends FunctionReferenceImpl implements fz0 {
    public static final SemaphoreImpl$addAcquireToQueue$createNewSegment$1 k = new SemaphoreImpl$addAcquireToQueue$createNewSegment$1();

    public SemaphoreImpl$addAcquireToQueue$createNewSegment$1() {
        super(2, fb2.class, "createSegment", "createSegment(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", 1);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        int i = fb2.a;
        return new gb2(((Number) obj).longValue(), (gb2) obj2, 0);
    }
}
