package kotlinx.coroutines.flow.internal;

import kotlin.jvm.internal.FunctionReferenceImpl;
import p.c00;
import p.fv0;
import p.hz0;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class SafeCollectorKt$emitFun$1 extends FunctionReferenceImpl implements hz0 {
    public static final SafeCollectorKt$emitFun$1 k = new SafeCollectorKt$emitFun$1();

    public SafeCollectorKt$emitFun$1() {
        super(3, fv0.class, "emit", "emit(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    @Override // p.hz0
    public final Object e(Object obj, Object obj2, Object obj3) {
        return ((fv0) obj).b(obj2, (c00) obj3);
    }
}
