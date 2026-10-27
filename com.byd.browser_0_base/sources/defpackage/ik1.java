package defpackage;

import android.content.Intent;
import android.util.Log;
import defpackage.y8;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: NativeInitializationController.java */
/* JADX INFO: loaded from: classes.dex */
public final class ik1 {
    public final jp a;
    public boolean b;
    public boolean c;
    public ArrayList d;
    public ArrayList e;
    public Boolean f;
    public boolean g;
    public boolean h;

    /* JADX INFO: compiled from: NativeInitializationController.java */
    public class a extends y8 {
        public a() {
        }
    }

    /* JADX INFO: compiled from: NativeInitializationController.java */
    public static class b {
        public final int a;
        public final int b;
        public final Intent c;

        public b(int i, int i2, Intent intent) {
            this.a = i;
            this.b = i2;
            this.c = intent;
        }
    }

    public ik1(jp jpVar) {
        this.a = jpVar;
    }

    public final void a() {
        Boolean bool;
        Object obj = ThreadUtils.a;
        if (this.g && (bool = this.f) != null && bool.booleanValue()) {
            jp jpVar = this.a;
            if (jpVar.d()) {
                return;
            }
            jpVar.Z();
        }
    }

    public final void b(boolean z) {
        Object obj = ThreadUtils.a;
        if (iv.e().g("disable-native-initialization")) {
            Log.i("cr_NIController", "Exit early and start Chrome without loading native library!");
            return;
        }
        boolean zA = gn0.a(this.a.P(), false);
        this.f = Boolean.FALSE;
        a aVar = new a();
        if (zA) {
            aVar.b++;
            b62.b().d();
            cl3 cl3Var = lp.a().c;
            String str = cl3Var.b;
            if (str != null) {
                PostTask.c(d73.j, new y8.a(str));
            } else {
                cl3Var.b = "";
                PostTask.c(d73.j, new y8.a(""));
            }
        }
        aVar.a = z;
        aVar.b++;
        new Thread(new qm0(8, aVar)).start();
    }

    public final void c() {
        jp jpVar = this.a;
        TraceEvent traceEventH0 = TraceEvent.H0("startNowAndProcessPendingItems", null);
        try {
            jpVar.j();
            ArrayList arrayList = this.d;
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    jpVar.n0((Intent) it.next());
                }
                this.d = null;
            }
            if (this.e != null) {
                for (int i = 0; i < this.e.size(); i++) {
                    b bVar = (b) this.e.get(i);
                    jpVar.x0(bVar.a, bVar.b, bVar.c);
                }
                this.e = null;
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
