package org.chromium.chrome.browser.ui.autofill;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.c30;
import defpackage.rb2;
import defpackage.s9;
import java.util.List;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;

/* JADX INFO: compiled from: AuthenticatorOptionsAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends RecyclerView.e<RecyclerView.a0> {
    public final List<s9> h;
    public final b i;
    public final Context j;
    public int k;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.ui.autofill.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AuthenticatorOptionsAdapter.java */
    public class C0117a extends RecyclerView.a0 {
        public final ImageView A;
        public final RadioButton B;
        public final TextView y;
        public final TextView z;

        public C0117a(View view, b bVar) {
            super(view);
            this.y = (TextView) view.findViewById(R$id.authenticator_option_title);
            this.z = (TextView) view.findViewById(R$id.authenticator_option_description);
            this.A = (ImageView) view.findViewById(R$id.authenticator_option_icon);
            RadioButton radioButton = (RadioButton) view.findViewById(R$id.authenticator_option_radio_btn);
            this.B = radioButton;
            radioButton.setOnClickListener(new c30(this, 8, bVar));
        }
    }

    /* JADX INFO: compiled from: AuthenticatorOptionsAdapter.java */
    public interface b {
    }

    public a(Context context, List list, org.chromium.chrome.browser.ui.autofill.b bVar) {
        this.h = list;
        this.i = bVar;
        this.j = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.h.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i) {
        C0117a c0117a = (C0117a) a0Var;
        s9 s9Var = this.h.get(i);
        int iK = k();
        RadioButton radioButton = c0117a.B;
        ImageView imageView = c0117a.A;
        if (iK == 1) {
            radioButton.setVisibility(8);
            int i2 = s9Var.d;
            if (i2 != 0) {
                imageView.setVisibility(0);
                Context context = this.j;
                Resources resources = context.getResources();
                Resources.Theme theme = context.getTheme();
                ThreadLocal<TypedValue> threadLocal = rb2.a;
                imageView.setImageDrawable(resources.getDrawable(i2, theme));
            } else {
                imageView.setVisibility(8);
            }
        } else {
            imageView.setVisibility(8);
            radioButton.setVisibility(0);
            radioButton.setChecked(i == this.k);
        }
        c0117a.y.setText(s9Var.a);
        c0117a.z.setText(s9Var.c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        return new C0117a(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.authenticator_option, (ViewGroup) recyclerView, false), this.i);
    }
}
