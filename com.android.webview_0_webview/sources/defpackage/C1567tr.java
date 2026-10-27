package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: renamed from: tr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1567tr extends K3 {
    public static final String[] s0 = {"Default", "Enabled", "Disabled"};
    public Map p0 = new HashMap();
    public C1457rr q0;
    public Context r0;

    public static void g0(C1567tr c1567tr, View view, int i) {
        Objects.requireNonNull(c1567tr);
        TextView textView = (TextView) view.findViewById(AbstractC0371Um.w1);
        if (i == 0) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(AbstractC0317Rm.I0, 0, 0, 0);
        }
    }

    @Override // defpackage.K3
    public void B(Context context) {
        super.B(context);
        this.r0 = context;
    }

    @Override // defpackage.K3
    public View E(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(AbstractC0425Xm.k0, (ViewGroup) null);
    }

    @Override // defpackage.K3
    public void P(View view, Bundle bundle) {
        ((Activity) this.r0).setTitle("WebView Flags");
        ListView listView = (ListView) view.findViewById(AbstractC0371Um.A1);
        ((TextView) view.findViewById(AbstractC0371Um.z1)).setText("By enabling these features, you could lose app data or compromise your security or privacy. Enabled features apply to WebViews across all apps on the device.");
        if (AbstractC0106Fq.b(this.r0.getPackageName())) {
            this.p0 = AbstractC0106Fq.a(this.r0.getPackageName());
        }
        C0124Gq[] c0124GqArr = AbstractC0214Lq.a;
        C0124Gq[] c0124GqArr2 = new C0124Gq[c0124GqArr.length];
        int i = 0;
        for (C0124Gq c0124Gq : c0124GqArr) {
            if (this.p0.containsKey(c0124Gq.a)) {
                c0124GqArr2[i] = c0124Gq;
                i++;
            }
        }
        for (C0124Gq c0124Gq2 : c0124GqArr) {
            if (!this.p0.containsKey(c0124Gq2.a)) {
                c0124GqArr2[i] = c0124Gq2;
                i++;
            }
        }
        C1457rr c1457rr = new C1457rr(this, c0124GqArr2);
        this.q0 = c1457rr;
        listView.setAdapter((ListAdapter) c1457rr);
        ((Button) view.findViewById(AbstractC0371Um.h3)).setOnClickListener(new View.OnClickListener(this) { // from class: or
            public final C1567tr s;

            {
                this.s = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                C1567tr c1567tr = this.s;
                c1567tr.p0.clear();
                c1567tr.q0.notifyDataSetChanged();
                c1567tr.h0();
            }
        });
    }

    public final void h0() {
        ServiceConnectionC1512sr serviceConnectionC1512sr = new ServiceConnectionC1512sr(this, null);
        Intent intent = new Intent();
        intent.setClassName(serviceConnectionC1512sr.a.r0.getPackageName(), "org.chromium.android_webview.services.DeveloperUiService");
        if (serviceConnectionC1512sr.a.r0.bindService(intent, serviceConnectionC1512sr, 1)) {
            return;
        }
        Ts.a("WebViewDevTools", "Failed to bind to Developer UI service", new Object[0]);
    }
}
