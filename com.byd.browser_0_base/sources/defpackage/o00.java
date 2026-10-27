package defpackage;

import defpackage.m00;

/* JADX INFO: compiled from: CoroutineContext.kt */
/* JADX INFO: loaded from: classes.dex */
public final class o00 extends z41 implements nr0<Boolean, m00.b, Boolean> {
    public static final o00 f = new o00();

    public o00() {
        super(2);
    }

    @Override // defpackage.nr0
    public final Boolean d(Boolean bool, m00.b bVar) {
        return Boolean.valueOf(bool.booleanValue() || (bVar instanceof j00));
    }
}
