package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: rB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractViewOnClickListenerC1423rB implements AdapterView.OnItemClickListener, PopupWindow.OnDismissListener, View.OnClickListener {
    public String A;
    public int B;
    public TextView C;
    public TextView D;
    public ListView E;
    public LinearLayout F;
    public View G;
    public int H;
    public boolean I;
    public final Context s;
    public final TextSuggestionHost t;
    public final View u;
    public WindowAndroid v;
    public Activity w;
    public DisplayMetrics x;
    public PopupWindow y;
    public LinearLayout z;

    public AbstractViewOnClickListenerC1423rB(Context context, TextSuggestionHost textSuggestionHost, WindowAndroid windowAndroid, View view) {
        this.s = context;
        this.t = textSuggestionHost;
        this.v = windowAndroid;
        this.u = view;
        PopupWindow popupWindow = new PopupWindow();
        this.y = popupWindow;
        popupWindow.setWidth(-2);
        this.y.setHeight(-2);
        this.y.setBackgroundDrawable(AbstractC1623us.c(this.s.getResources(), AbstractC0317Rm.X0));
        this.y.setElevation(this.s.getResources().getDimensionPixelSize(AbstractC0299Qm.m5));
        this.y.setInputMethodMode(2);
        this.y.setFocusable(true);
        this.y.setClippingEnabled(false);
        this.y.setOnDismissListener(this);
        LayoutInflater layoutInflater = (LayoutInflater) this.s.getSystemService("layout_inflater");
        this.z = (LinearLayout) layoutInflater.inflate(AbstractC0425Xm.Z0, (ViewGroup) null);
        this.H = this.s.getResources().getDimensionPixelSize(AbstractC0299Qm.n5);
        ListView listView = (ListView) this.z.findViewById(AbstractC0371Um.B4);
        this.E = listView;
        listView.setDivider(null);
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(AbstractC0425Xm.b1, (ViewGroup) null);
        this.F = linearLayout;
        this.E.addFooterView(linearLayout, null, false);
        this.E.setAdapter((ListAdapter) new C1369qB(this, null));
        this.E.setOnItemClickListener(this);
        this.G = this.z.findViewById(AbstractC0371Um.R0);
        TextView textView = (TextView) this.z.findViewById(AbstractC0371Um.E);
        this.C = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) this.z.findViewById(AbstractC0371Um.J0);
        this.D = textView2;
        textView2.setOnClickListener(this);
        this.y.setContentView(this.z);
    }

    public abstract void a(int i);

    public abstract Object b(int i);

    public abstract SpannableString c(int i);

    public abstract int d();

    /* JADX WARN: Removed duplicated region for block: B:14:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(double r6, double r8, java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractViewOnClickListenerC1423rB.e(double, double, java.lang.String):void");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != this.C) {
            if (view == this.D) {
                TextSuggestionHost textSuggestionHost = this.t;
                N.MCBTtv2g(textSuggestionHost.s, textSuggestionHost);
                this.I = true;
                this.y.dismiss();
                return;
            }
            return;
        }
        Intent intent = new Intent("com.android.settings.USER_DICTIONARY_INSERT");
        intent.putExtra("word", this.A);
        intent.setFlags(intent.getFlags() | 268435456);
        this.s.startActivity(intent);
        TextSuggestionHost textSuggestionHost2 = this.t;
        N.MpJ8AQhr(textSuggestionHost2.s, textSuggestionHost2, this.A);
        this.I = true;
        this.y.dismiss();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.t.O(this.I);
        this.I = false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        if (i >= this.B) {
            return;
        }
        a(i);
        this.I = true;
        this.y.dismiss();
    }
}
