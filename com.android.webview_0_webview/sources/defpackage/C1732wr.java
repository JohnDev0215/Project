package defpackage;

import android.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/* JADX INFO: renamed from: wr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1732wr extends ArrayAdapter {
    public final C1677vr[] s;
    public final /* synthetic */ C1787xr t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1732wr(C1787xr c1787xr, C1677vr[] c1677vrArr) {
        super(c1787xr.p0, AbstractC0425Xm.g1, c1677vrArr);
        this.t = c1787xr;
        this.s = c1677vrArr;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.t.m().inflate(AbstractC0425Xm.g1, (ViewGroup) null, true);
        }
        C1677vr c1677vr = this.s[i];
        TextView textView = (TextView) view.findViewById(R.id.text1);
        TextView textView2 = (TextView) view.findViewById(R.id.text2);
        textView.setText(c1677vr.a);
        textView2.setText(c1677vr.b);
        return view;
    }
}
