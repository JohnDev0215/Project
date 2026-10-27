package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/* JADX INFO: renamed from: qB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1369qB extends BaseAdapter {
    public LayoutInflater s;
    public final /* synthetic */ AbstractViewOnClickListenerC1423rB t;

    public C1369qB(AbstractViewOnClickListenerC1423rB abstractViewOnClickListenerC1423rB, AbstractC1314pB abstractC1314pB) {
        this.t = abstractViewOnClickListenerC1423rB;
        this.s = (LayoutInflater) this.t.s.getSystemService("layout_inflater");
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.t.B;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.t.b(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (textView == null) {
            textView = (TextView) this.s.inflate(AbstractC0425Xm.a1, viewGroup, false);
        }
        textView.setText(this.t.c(i));
        return textView;
    }
}
