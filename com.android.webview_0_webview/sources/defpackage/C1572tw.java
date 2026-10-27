package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.autofill.AutofillManager;
import java.util.ArrayList;

/* JADX INFO: renamed from: tw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1572tw {
    public static boolean g;
    public AutofillManager a;
    public boolean b;
    public C1462rw c;
    public boolean d;
    public boolean e;
    public ArrayList f;

    public C1572tw(Context context) {
        g = false;
        AutofillManager autofillManager = (AutofillManager) context.getSystemService(AutofillManager.class);
        this.a = autofillManager;
        boolean z = autofillManager == null || !autofillManager.isEnabled();
        this.e = z;
        if (z) {
            return;
        }
        C1462rw c1462rw = new C1462rw(this);
        this.c = c1462rw;
        this.a.registerCallback(c1462rw);
    }

    public final boolean a() {
        if (this.d) {
            Ts.f("AwAutofillManager", "Application attempted to call on a destroyed AutofillManagerWrapper", new Throwable());
        }
        return this.d;
    }

    public void b(View view, int i, Rect rect) {
        if (this.e) {
            Ts.f("AwAutofillManager", "Autofill is disabled: AutofillManager isn't available in given Context.", new Object[0]);
        } else {
            if (a()) {
                return;
            }
            this.a.notifyViewEntered(view, i, rect);
        }
    }

    public void c(View view, int i) {
        if (this.e || a()) {
            return;
        }
        this.a.notifyViewExited(view, i);
    }
}
