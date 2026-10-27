package defpackage;

import J.N;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class zm2 implements nw2 {
    public final /* synthetic */ jg1 e;
    public final /* synthetic */ PropertyModel f;
    public final /* synthetic */ long g;

    public /* synthetic */ zm2(jg1 jg1Var, PropertyModel propertyModel, long j) {
        this.e = jg1Var;
        this.f = propertyModel;
        this.g = j;
    }

    @Override // defpackage.nw2
    public final Object get() {
        long jMax;
        int iH = this.f.h(ng1.a);
        iq iqVar = (iq) this.e;
        long jMax2 = Math.max(iqVar.a, this.g);
        String strConcat = "autodismiss_duration_ms_".concat(z7.T(iH));
        sk skVar = bq.a;
        long jM37SqSAy = N.M37SqSAy("MessagesForAndroidInfrastructure", strConcat, -1);
        if (jM37SqSAy > 0) {
            jMax2 = Math.max(jM37SqSAy, jMax2);
        }
        boolean zC = qo.f().c();
        long j = iqVar.b;
        if (zC) {
            qo.f().getClass();
            jMax = Math.max(j, C0188h0.b().getRecommendedTimeoutMillis((int) jMax2, 7));
        } else {
            if (qo.f().c()) {
                jMax2 = Math.max(j, jMax2);
            }
            jMax = jMax2;
        }
        return Long.valueOf(jMax);
    }
}
