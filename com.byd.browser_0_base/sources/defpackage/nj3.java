package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: UpdateStatusProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nj3 {
    public final org.chromium.base.b<Callback<c>> a = new org.chromium.base.b<>();
    public final b b = new b(new l10(26, this));
    public final oj3 c = new oj3();
    public c d;
    public boolean e;

    /* JADX INFO: compiled from: UpdateStatusProvider.java */
    public static final class a {
        public static final nj3 a = new nj3();
    }

    /* JADX INFO: compiled from: UpdateStatusProvider.java */
    public static final class b extends i9<c> {
        public final Runnable h;
        public c i;

        public b(l10 l10Var) {
            this.h = l10Var;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0040  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00d6  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x00d9  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0105  */
        @Override // defpackage.i9
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final nj3.c b() {
            /*
                Method dump skipped, instruction units count: 316
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: nj3.b.b():java.lang.Object");
        }

        @Override // defpackage.i9
        public final void l(c cVar) {
            this.i = cVar;
            PostTask.c(rh3.a, this.h);
        }
    }

    /* JADX INFO: compiled from: UpdateStatusProvider.java */
    public static final class c {
        public int a;
        public String b;
        public String c;
        public String d;
        public boolean e;

        public c() {
        }

        public c(c cVar) {
            this.a = cVar.a;
            this.b = cVar.b;
            this.c = cVar.c;
            this.d = cVar.d;
            this.e = cVar.e;
        }
    }

    public final void a(Context context) {
        c cVar = this.d;
        if (cVar == null || cVar.a != 1 || TextUtils.isEmpty(cVar.b)) {
            return;
        }
        try {
            oj3 oj3Var = this.c;
            ye3 ye3Var = oj3Var.a;
            ye3Var.getClass();
            s62 s62Var = new s62();
            ye3Var.a.b(new zc0(21, s62Var));
            s62Var.g(new td2(5, oj3Var));
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.d.b)));
        } catch (ActivityNotFoundException unused) {
        }
    }
}
