package defpackage;

import J.N;
import android.content.Context;
import defpackage.fc;
import defpackage.qj;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;

/* JADX INFO: compiled from: ProxyNativeTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class e72 extends fk1 {
    public long f;

    @Override // defpackage.fk1
    public final int c(Context context, a73 a73Var, du duVar) {
        return 0;
    }

    @Override // defpackage.fk1
    public final void d(Context context, a73 a73Var, fc.a aVar) {
        this.f = N.MFWoHdRt(this, a73Var.a, a73Var.b.getString("serialized_task_extras"), new gp(this, 4, aVar));
        if (qj.a().g()) {
            N.Mh0pzgZH(this.f, this, Profile.d());
        } else {
            N.MgNysWkl(this.f, this, ProfileKey.a());
            qj.a().b(new a());
        }
    }

    @Override // defpackage.fk1
    public final boolean e() {
        return true;
    }

    @Override // defpackage.fk1
    public final boolean f(a73 a73Var) {
        long j = this.f;
        if (j == 0) {
            return false;
        }
        boolean zMQTaGJay = N.MQTaGJay(j, this);
        long j2 = this.f;
        if (j2 != 0) {
            N.MJHnuE5A(j2, this);
            this.f = 0L;
        }
        return zMQTaGJay;
    }

    /* JADX INFO: compiled from: ProxyNativeTask.java */
    public class a implements qj.a {
        public a() {
        }

        @Override // qj.a
        public final void b() {
            e72 e72Var = e72.this;
            long j = e72Var.f;
            if (j == 0) {
                return;
            }
            N.MlPuxSGY(j, e72Var, Profile.d());
        }

        @Override // qj.a
        public final void a() {
        }
    }
}
