package kotlinx.coroutines.flow;

import kotlin.jvm.internal.l;
import kotlin.jvm.internal.m;
import r5.a;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Zip.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$3$lambda$1<T> extends m implements a<T[]> {
    final /* synthetic */ FlowKt__ZipKt$combine$$inlined$unsafeFlow$3 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$unsafeFlow$3$lambda$1(FlowKt__ZipKt$combine$$inlined$unsafeFlow$3 flowKt__ZipKt$combine$$inlined$unsafeFlow$3) {
        super(0);
        this.this$0 = flowKt__ZipKt$combine$$inlined$unsafeFlow$3;
    }

    @Override // r5.a
    public final T[] invoke() {
        int length = this.this$0.$flows$inlined.length;
        l.k(0, "T?");
        return (T[]) new Object[length];
    }
}
