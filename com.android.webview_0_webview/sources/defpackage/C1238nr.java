package defpackage;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.Objects;

/* JADX INFO: renamed from: nr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1238nr extends K3 {
    public C1183mr p0;
    public C0035Br q0;
    public Context r0;

    public static void g0(View view, String str, String str2) {
        TextView textView = (TextView) view.findViewById(R.id.text1);
        TextView textView2 = (TextView) view.findViewById(R.id.text2);
        if (textView != null) {
            textView.setVisibility(0);
            textView.setText(str);
        } else {
            textView.setVisibility(8);
        }
        if (str2 == null) {
            textView2.setVisibility(8);
        } else {
            textView2.setVisibility(0);
            textView2.setText(str2);
        }
    }

    @Override // defpackage.K3
    public void B(Context context) {
        super.B(context);
        this.r0 = context;
    }

    @Override // defpackage.K3
    public void C(Bundle bundle) {
        super.C(bundle);
        if (!this.V) {
            this.V = true;
            if (!(this.f4J != null && this.B) || this.R) {
                return;
            }
            ((M3) this.f4J).e.invalidateOptionsMenu();
        }
    }

    @Override // defpackage.K3
    public void D(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(AbstractC0443Ym.a, menu);
    }

    @Override // defpackage.K3
    public View E(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(AbstractC0425Xm.j0, (ViewGroup) null);
    }

    @Override // defpackage.K3
    public void G() {
        this.q0.a();
        this.X = true;
    }

    @Override // defpackage.K3
    public boolean K(MenuItem menuItem) {
        if (menuItem.getItemId() != AbstractC0371Um.N2) {
            return false;
        }
        this.p0.b();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0058  */
    @Override // defpackage.K3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void L() {
        /*
            r4 = this;
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r1 = 1
            r4.X = r1
            Es r1 = defpackage.Es.e()
            java.lang.String r2 = "enable-crash-reporter-for-testing"
            boolean r1 = r1.h(r2)
            if (r1 != 0) goto L58
            android.content.Context r1 = r4.r0
            java.lang.String r1 = r1.getPackageName()
            boolean r1 = defpackage.AbstractC0106Fq.b(r1)
            r3 = 0
            if (r1 == 0) goto L35
            android.content.Context r1 = r4.r0
            java.lang.String r1 = r1.getPackageName()
            java.util.Map r1 = defpackage.AbstractC0106Fq.a(r1)
            java.util.HashMap r1 = (java.util.HashMap) r1
            java.lang.Object r1 = r1.get(r2)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r0.equals(r1)
            goto L36
        L35:
            r1 = 0
        L36:
            if (r1 == 0) goto L39
            goto L58
        L39:
            defpackage.AbstractC0178Jq.a()
            org.chromium.base.ThreadUtils.b()
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            java.util.Objects.requireNonNull(r4)
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L50
            Br r0 = r4.q0
            r0.a()
            goto L5d
        L50:
            Br r0 = r4.q0
            android.widget.TextView r0 = r0.a
            r0.setVisibility(r3)
            goto L5d
        L58:
            Br r0 = r4.q0
            r0.a()
        L5d:
            mr r0 = r4.p0
            r0.b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1238nr.L():void");
    }

    @Override // defpackage.K3
    public void P(View view, Bundle bundle) {
        ((Activity) this.r0).setTitle("WebView Crashes");
        this.p0 = new C1183mr(this, (TextView) view.findViewById(AbstractC0371Um.z0));
        ((ExpandableListView) view.findViewById(AbstractC0371Um.x0)).setAdapter(this.p0);
        Objects.requireNonNull(AbstractC0178Jq.a());
        AlertDialog.Builder builder = new AlertDialog.Builder(this.r0);
        builder.setTitle("Error Showing Crashes");
        builder.setMessage("Crash collection is not supported at the moment.");
        C0035Br c0035Br = new C0035Br((Activity) this.r0, AbstractC0371Um.s0, EnumC0017Ar.ERROR);
        c0035Br.a.setText("Crash collection is disabled. Tap for more info.");
        c0035Br.a.setOnClickListener(new ViewOnClickListenerC1897zr(builder.create()));
        this.q0 = c0035Br;
    }
}
