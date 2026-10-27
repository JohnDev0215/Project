package kotlinx.coroutines.flow;

import p.fz0;
import p.ry0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class e {
    public static final ry0 a = new ry0() { // from class: kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultKeySelector$1
        @Override // p.ry0
        public final Object invoke(Object obj) {
            return obj;
        }
    };
    public static final fz0 b = new fz0() { // from class: kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultAreEquivalent$1
        @Override // p.fz0
        public final Object g(Object obj, Object obj2) {
            return Boolean.valueOf(v03.b(obj, obj2));
        }
    };
}
