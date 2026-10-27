package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.di1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AppLanguagePromoDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class q6 {
    public final Activity a;
    public final bi1 b;
    public final PropertyModel c;
    public final PropertyModel d;
    public b e;
    public final d f;

    /* JADX INFO: compiled from: AppLanguagePromoDialog.java */
    public static class a extends RecyclerView.a0 implements View.OnClickListener {
        public final RadioButton A;
        public final TextView y;
        public final TextView z;

        public a(View view) {
            super(view);
            this.y = (TextView) view.findViewById(R$id.primary_language_name);
            this.z = (TextView) view.findViewById(R$id.secondary_language_name);
            RadioButton radioButton = (RadioButton) view.findViewById(R$id.app_language_prompt_radiobutton);
            this.A = radioButton;
            view.setOnClickListener(this);
            radioButton.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            b bVar = (b) this.w;
            int iF = f();
            if (iF == -1) {
                return;
            }
            bVar.j = bVar.D(iF);
            bVar.o();
            View viewFindViewById = view.getRootView().findViewById(R$id.positive_button);
            if (viewFindViewById != null) {
                viewFindViewById.sendAccessibilityEvent(8);
            }
        }
    }

    /* JADX INFO: compiled from: AppLanguagePromoDialog.java */
    public static class b extends RecyclerView.e<RecyclerView.a0> {
        public final ArrayList<f51> h;
        public final ArrayList<f51> i;
        public f51 j;
        public boolean k;

        public b(LinkedHashSet linkedHashSet, LinkedHashSet linkedHashSet2, f51 f51Var) {
            this.h = new ArrayList<>(linkedHashSet);
            this.i = new ArrayList<>(linkedHashSet2);
            this.j = f51Var;
        }

        public final f51 D(int i) {
            ArrayList<f51> arrayList = this.h;
            if (i < arrayList.size()) {
                return arrayList.get(i);
            }
            if (i > arrayList.size()) {
                return this.i.get((i - arrayList.size()) - 1);
            }
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int k() {
            int size = this.h.size() + 1;
            return this.k ? size + this.i.size() : size;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int m(int i) {
            if (i != this.h.size()) {
                return 0;
            }
            return this.k ? 1 : 2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final void t(RecyclerView.a0 a0Var, int i) {
            if (m(i) == 0) {
                f51 f51VarD = D(i);
                a aVar = (a) a0Var;
                boolean zEquals = f51VarD.equals(this.j);
                RadioButton radioButton = aVar.A;
                radioButton.setChecked(zEquals);
                String str = f51VarD.b;
                radioButton.setContentDescription(str);
                boolean zEquals2 = TextUtils.equals(f51VarD.a, null);
                TextView textView = aVar.z;
                TextView textView2 = aVar.y;
                String str2 = f51VarD.c;
                if (zEquals2) {
                    textView2.setText(str);
                    textView.setText(str2);
                } else {
                    textView2.setText(str2);
                    textView.setText(str);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
            if (i == 0) {
                return new a(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.app_language_prompt_row, (ViewGroup) recyclerView, false));
            }
            if (i == 1) {
                return new e(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.language_ask_prompt_row_separator, (ViewGroup) recyclerView, false));
            }
            if (i != 2) {
                return null;
            }
            return new c(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.app_language_prompt_more_languages, (ViewGroup) recyclerView, false));
        }
    }

    /* JADX INFO: compiled from: AppLanguagePromoDialog.java */
    public static class c extends RecyclerView.a0 implements View.OnClickListener {
        public c(View view) {
            super(view);
            view.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            b bVar = (b) this.w;
            bVar.k = true;
            ArrayList<f51> arrayList = bVar.h;
            bVar.e.f(arrayList.size(), 1);
            bVar.q(arrayList.size(), bVar.i.size() + 1);
        }
    }

    /* JADX INFO: compiled from: AppLanguagePromoDialog.java */
    @FunctionalInterface
    public interface d {
    }

    /* JADX INFO: compiled from: AppLanguagePromoDialog.java */
    public static class e extends RecyclerView.a0 {
        public e(View view) {
            super(view);
        }
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [T, ym2] */
    public q6(AppCompatActivity appCompatActivity, zp1 zp1Var, qh1 qh1Var) {
        this.a = appCompatActivity;
        bi1 bi1Var = (bi1) zp1Var.get();
        this.b = bi1Var;
        this.f = qh1Var;
        Resources resources = appCompatActivity.getResources();
        v62[] v62VarArr = di1.B;
        PropertyModel.b bVar = new PropertyModel.b(v62VarArr);
        PropertyModel.l<di1.a> lVar = di1.a;
        bVar.e(lVar, new ym2(bi1Var, new ko0(12, this)));
        bVar.d(di1.c, resources, R$string.languages_srp_title);
        bVar.d(di1.j, resources, R$string.ok);
        bVar.d(di1.m, resources, R$string.cancel);
        bVar.c(di1.u, 1);
        this.c = bVar.a();
        HashMap mapE = PropertyModel.e(v62VarArr);
        ?? ym2Var = new ym2(bi1Var, new dm0(11, this));
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = ym2Var;
        this.d = C0175b.p(mapE, lVar, gVar, mapE);
    }
}
