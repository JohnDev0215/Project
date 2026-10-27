package kotlinx.coroutines.channels;

import kotlin.jvm.internal.Lambda;
import p.lq2;
import p.ry0;
import p.yp;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
final class ProduceKt$awaitClose$4$1 extends Lambda implements ry0 {
    final /* synthetic */ yp $cont;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProduceKt$awaitClose$4$1(zp zpVar) {
        super(1);
        this.$cont = zpVar;
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        yp ypVar = this.$cont;
        lq2 lq2Var = lq2.a;
        ((zp) ypVar).k(lq2Var);
        return lq2Var;
    }
}
