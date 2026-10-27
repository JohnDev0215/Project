package defpackage;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: renamed from: xr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1787xr extends K3 {
    public Context p0;

    @Override // defpackage.K3
    public void B(Context context) {
        super.B(context);
        this.p0 = context;
    }

    @Override // defpackage.K3
    public View E(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(AbstractC0425Xm.l0, (ViewGroup) null);
    }

    @Override // defpackage.K3
    public void P(View view, Bundle bundle) {
        ((Activity) this.p0).setTitle("WebView DevTools");
        PackageInfo packageInfoA = AbstractC0232Mr.a(this.p0);
        C1677vr[] c1677vrArr = {new C1677vr("WebView package", packageInfoA.packageName), new C1677vr("WebView version", String.format(Locale.US, "%s (%s)", packageInfoA.versionName, Integer.valueOf(packageInfoA.versionCode))), new C1677vr("Device info", String.format(Locale.US, "%s - %s", Build.MODEL, Build.FINGERPRINT))};
        ListView listView = (ListView) view.findViewById(AbstractC0371Um.k2);
        listView.setAdapter((ListAdapter) new C1732wr(this, c1677vrArr));
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(this) { // from class: ur
            public final C1787xr s;

            {
                this.s = this;
            }

            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView adapterView, View view2, int i, long j) {
                C1787xr c1787xr = this.s;
                Objects.requireNonNull(c1787xr);
                C1677vr c1677vr = (C1677vr) adapterView.getItemAtPosition(i);
                ((ClipboardManager) c1787xr.p0.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(c1677vr.a, c1677vr.b));
                Context context = c1787xr.p0;
                StringBuilder sbF = Z7.f("Copied ");
                sbF.append(c1677vr.a);
                C1602uP.a(context, sbF.toString(), 0).a.show();
                return true;
            }
        });
    }
}
