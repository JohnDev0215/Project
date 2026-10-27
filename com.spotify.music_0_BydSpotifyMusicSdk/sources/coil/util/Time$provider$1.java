package coil.util;

import kotlin.jvm.internal.FunctionReferenceImpl;
import p.py0;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class Time$provider$1 extends FunctionReferenceImpl implements py0 {
    public static final Time$provider$1 k = new Time$provider$1();

    public Time$provider$1() {
        super(0, System.class, "currentTimeMillis", "currentTimeMillis()J", 0);
    }

    @Override // p.py0
    public final Object invoke() {
        return Long.valueOf(System.currentTimeMillis());
    }
}
