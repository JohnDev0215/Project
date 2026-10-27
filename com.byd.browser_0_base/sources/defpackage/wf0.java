package defpackage;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import defpackage.tf0;
import defpackage.xf0;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;

/* JADX INFO: compiled from: EditorDropdownField.java */
/* JADX INFO: loaded from: classes.dex */
public final class wf0 implements yf0 {
    public final Context e;
    public final xf0 f;
    public final View g;
    public final Spinner h;
    public final View i;
    public final TextView j;
    public int k;
    public final be0 l;

    public wf0(Activity activity, ViewGroup viewGroup, xf0 xf0Var, tf0.b bVar, boolean z) {
        this.e = activity;
        this.f = xf0Var;
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.payment_request_editor_dropdown, viewGroup, false);
        this.g = viewInflate;
        ((TextView) viewInflate.findViewById(R$id.spinner_label)).setText(((!TextUtils.isEmpty(xf0Var.i)) && z) ? ((Object) xf0Var.m) + "*" : xf0Var.m);
        this.i = viewInflate.findViewById(R$id.spinner_underline);
        this.j = (TextView) viewInflate.findViewById(R$id.spinner_error);
        List<xf0.a> list = xf0Var.b;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add((CharSequence) ((Pair) list.get(i)).second);
        }
        if (this.f.o != null) {
            mu0 mu0Var = new mu0(activity, R$layout.multiline_spinner_item, R$id.spinner_item, arrayList, this.f.o.toString());
            this.l = mu0Var;
            mu0Var.setDropDownViewResource(R$layout.payment_request_dropdown_item);
        } else {
            be0 be0Var = new be0(R$layout.multiline_spinner_item, activity, arrayList);
            this.l = be0Var;
            be0Var.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        }
        int position = TextUtils.isEmpty(this.f.n) ? 0 : this.l.getPosition(this.f.n.toString());
        this.k = position;
        if (position < 0) {
            be0 be0Var2 = this.l;
            xf0 xf0Var2 = this.f;
            this.k = be0Var2.getPosition(xf0Var2.c.get(xf0Var2.n.toString()));
        }
        if (this.k < 0) {
            this.k = 0;
        }
        Spinner spinner = (Spinner) this.g.findViewById(R$id.spinner);
        this.h = spinner;
        spinner.setTag(this);
        spinner.setAdapter((SpinnerAdapter) this.l);
        spinner.setSelection(this.k);
        spinner.setOnItemSelectedListener(new uf0(this, bVar));
        spinner.setOnTouchListener(new vf0(this));
    }

    @Override // defpackage.yf0
    public final void a() {
        c(!isValid());
        t41 t41Var = t41.f;
        Spinner spinner = this.h;
        t41Var.e(spinner);
        ViewGroup viewGroup = (ViewGroup) spinner.getParent();
        if (viewGroup != null) {
            viewGroup.requestChildFocus(spinner, spinner);
        }
        spinner.sendAccessibilityEvent(8);
    }

    @Override // defpackage.yf0
    public final boolean b() {
        return !TextUtils.isEmpty(this.f.i);
    }

    @Override // defpackage.yf0
    public final void c(boolean z) {
        View selectedView = this.h.getSelectedView();
        if (selectedView == null || !(selectedView instanceof TextView)) {
            return;
        }
        View view = this.i;
        TextView textView = this.j;
        Context context = this.e;
        if (!z) {
            ((TextView) selectedView).setError(null);
            view.setBackgroundColor(context.getColor(R$color.modern_grey_600));
            textView.setText((CharSequence) null);
            textView.setVisibility(8);
            return;
        }
        el3 el3VarA = re3.a(context.getResources(), R$drawable.ic_error, context.getTheme());
        el3VarA.setBounds(0, 0, el3VarA.getIntrinsicWidth(), el3VarA.getIntrinsicHeight());
        xf0 xf0Var = this.f;
        ((TextView) selectedView).setError(xf0Var.l, el3VarA);
        view.setBackgroundColor(context.getColor(R$color.default_text_color_error));
        textView.setText(xf0Var.l);
        textView.setVisibility(0);
    }

    @Override // defpackage.yf0
    public final boolean isValid() {
        return this.f.d();
    }
}
