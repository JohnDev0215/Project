package defpackage;

import android.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/* JADX INFO: renamed from: rr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1457rr extends ArrayAdapter {
    public final /* synthetic */ C1567tr s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1457rr(C1567tr c1567tr, C0124Gq[] c0124GqArr) {
        super(c1567tr.r0, AbstractC0425Xm.e1, c0124GqArr);
        this.s = c1567tr;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.s.m().inflate(AbstractC0425Xm.e1, (ViewGroup) null);
        }
        TextView textView = (TextView) view.findViewById(AbstractC0371Um.w1);
        TextView textView2 = (TextView) view.findViewById(AbstractC0371Um.v1);
        Spinner spinner = (Spinner) view.findViewById(AbstractC0371Um.y1);
        C0124Gq c0124Gq = (C0124Gq) getItem(i);
        String str = c0124Gq.a;
        if (c0124Gq.c != null) {
            str = str + "=" + c0124Gq.c;
        }
        textView.setText(str);
        textView2.setText(c0124Gq.b);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.s.r0, AbstractC0425Xm.i0, C1567tr.s0);
        arrayAdapter.setDropDownViewResource(R.layout.select_dialog_singlechoice);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        Boolean bool = (Boolean) this.s.p0.get(c0124Gq.a);
        int i2 = bool == null ? 0 : bool.booleanValue() ? 1 : 2;
        spinner.setSelection(i2);
        spinner.setOnItemSelectedListener(new C1403qr(this.s, c0124Gq));
        C1567tr.g0(this.s, view, i2);
        return view;
    }
}
