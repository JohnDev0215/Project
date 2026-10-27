package defpackage;

import J.N;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.di1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LanguageAskPrompt.java */
/* JADX INFO: loaded from: classes.dex */
public final class e51 implements di1.a {
    public bi1 e;
    public HashSet<String> f;
    public HashSet<String> g;

    /* JADX INFO: compiled from: LanguageAskPrompt.java */
    public class a extends RecyclerView.a0 implements View.OnClickListener {
        public final CheckBox A;
        public final ImageView B;
        public String C;
        public HashSet<String> D;
        public final TextView y;
        public final TextView z;

        public a(View view) {
            super(view);
            view.setOnClickListener(this);
            this.y = (TextView) view.findViewById(R$id.ui_language_representation);
            this.z = (TextView) view.findViewById(R$id.native_language_representation);
            CheckBox checkBox = (CheckBox) view.findViewById(R$id.language_ask_checkbox);
            this.A = checkBox;
            this.B = (ImageView) view.findViewById(R$id.device_language_icon);
            checkBox.setOnCheckedChangeListener(new d51(this));
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.A.setChecked(!r0.isChecked());
        }
    }

    /* JADX INFO: compiled from: LanguageAskPrompt.java */
    public class b extends RecyclerView.e<RecyclerView.a0> {
        public final ArrayList h = new ArrayList();
        public final ArrayList i = new ArrayList();
        public final HashSet<String> j;

        public b(e51 e51Var, HashSet hashSet) {
            this.j = hashSet;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int k() {
            return this.i.size() + this.h.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int m(int i) {
            return i == this.h.size() ? 1 : 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final void t(RecyclerView.a0 a0Var, int i) {
            if (m(i) == 0) {
                ArrayList arrayList = this.h;
                f51 f51Var = i < arrayList.size() ? (f51) arrayList.get(i) : i > arrayList.size() ? (f51) this.i.get((i - arrayList.size()) - 1) : null;
                if (f51Var != null) {
                    a aVar = (a) a0Var;
                    aVar.y.setText(f51Var.b);
                    aVar.z.setText(f51Var.c);
                    String str = f51Var.a;
                    aVar.C = str;
                    HashSet<String> hashSet = this.j;
                    aVar.D = hashSet;
                    aVar.A.setChecked(hashSet.contains(str));
                    aVar.B.setVisibility(LocaleUtils.getDefaultLocaleString().equals(str) ? 0 : 4);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
            if (i == 0) {
                return new a(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.language_ask_prompt_row, (ViewGroup) recyclerView, false));
            }
            if (i == 1) {
                return new d(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.language_ask_prompt_row_separator, (ViewGroup) recyclerView, false));
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: LanguageAskPrompt.java */
    public class c extends RecyclerView.q {
        public final RecyclerView e;
        public final ImageView f;
        public final ImageView g;

        public c(RecyclerView recyclerView, ImageView imageView, ImageView imageView2) {
            this.e = recyclerView;
            this.f = imageView;
            this.g = imageView2;
            recyclerView.setOnScrollListener(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public final void a(int i, RecyclerView recyclerView) {
            RecyclerView recyclerView2 = this.e;
            boolean zCanScrollVertically = recyclerView2.canScrollVertically(-1);
            ImageView imageView = this.f;
            if (zCanScrollVertically) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
            boolean zCanScrollVertically2 = recyclerView2.canScrollVertically(1);
            ImageView imageView2 = this.g;
            if (zCanScrollVertically2) {
                imageView2.setVisibility(0);
            } else {
                imageView2.setVisibility(8);
            }
        }
    }

    /* JADX INFO: compiled from: LanguageAskPrompt.java */
    public class d extends RecyclerView.a0 {
        public d(View view) {
            super(view);
        }
    }

    @Override // di1.a
    public final void b(int i) {
        if (i == 1) {
            oj2.U(1, 2, "Translate.ExplicitLanguageAsk.Event");
        } else {
            oj2.U(2, 2, "Translate.ExplicitLanguageAsk.Event");
        }
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 1) {
            this.e.b(2, propertyModel);
            return;
        }
        TreeSet<String> treeSet = new TreeSet(this.f);
        treeSet.removeAll(this.g);
        for (String str : treeSet) {
            N.Me60Lv4_(str, true);
            N.MC39_Sil(str, true);
        }
        HashSet<String> hashSet = new HashSet(this.g);
        hashSet.removeAll(this.f);
        for (String str2 : hashSet) {
            N.Me60Lv4_(str2, false);
            N.MC39_Sil(str2, false);
        }
        this.e.b(1, propertyModel);
    }
}
