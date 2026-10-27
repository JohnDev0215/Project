package defpackage;

import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Tw extends Rw {
    public final List e;
    public final Callback f;
    public boolean g;

    public Tw(Rw... rwArr) {
        super(3);
        this.g = true;
        this.e = new ArrayList();
        this.f = new Callback(this) { // from class: Sw
            public final Tw a;

            {
                this.a = this;
            }

            @Override // org.chromium.base.Callback
            public void a(Object obj) {
                this.a.e();
            }
        };
        for (Rw rw : rwArr) {
            c(rw);
        }
        super.b(Integer.valueOf(d()));
    }

    @Override // defpackage.Rw
    public void b(Integer num) {
        if (this.g) {
            throw new IllegalStateException("Calling set on the composed delegate is not allowed.");
        }
        super.b(num);
    }

    public void c(Rw rw) {
        this.e.add(rw);
        Callback callback = this.f;
        rw.d.f(callback);
        Object obj = rw.c;
        if (obj != null) {
            rw.b.post(new Lu(rw, obj, callback));
        }
    }

    public final int d() {
        int i = 0;
        boolean z = false;
        while (true) {
            boolean z2 = true;
            if (i >= this.e.size()) {
                return z ? 1 : 3;
            }
            int iIntValue = ((Integer) ((Rw) this.e.get(i)).c).intValue();
            if (iIntValue == 2) {
                return 2;
            }
            if (iIntValue != 1) {
                z2 = false;
            }
            z |= z2;
            i++;
        }
    }

    public final /* synthetic */ void e() {
        super.b(Integer.valueOf(d()));
    }
}
