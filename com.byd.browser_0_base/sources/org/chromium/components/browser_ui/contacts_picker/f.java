package org.chromium.components.browser_ui.contacts_picker;

import J.N;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.i9;
import defpackage.xx;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.chromium.components.browser_ui.contacts_picker.TopView;
import org.chromium.components.browser_ui.contacts_picker.c;

/* JADX INFO: compiled from: PickerAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class f extends RecyclerView.e<RecyclerView.a0> implements c.a, TopView.a {
    public static boolean q;
    public static boolean r;
    public static boolean s;
    public static boolean t;
    public static boolean u;
    public Context h;
    public PickerCategoryView i;
    public TopView j;
    public String k;
    public ContentResolver l;
    public ArrayList<xx> m;
    public String n;
    public boolean o;
    public ArrayList<Integer> p;

    /* JADX INFO: compiled from: PickerAdapter.java */
    public class a extends RecyclerView.a0 implements View.OnClickListener {
        public final TopView y;

        public a(TopView topView) {
            super(topView);
            this.y = topView;
            topView.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            TopView topView = this.y;
            topView.getClass();
            if (N.Mk6X8tWe("ContactsPickerSelectAll")) {
                topView.g.setChecked(!r0.isChecked());
            }
        }
    }

    public abstract void D(ArrayList<xx> arrayList);

    public final void E(ArrayList<xx> arrayList) {
        String strF = F();
        this.n = strF;
        boolean z = true;
        if (strF != null) {
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < arrayList.size(); i++) {
                List<String> list = arrayList.get(i).g;
                int i2 = 0;
                while (true) {
                    if (i2 >= list.size()) {
                        break;
                    }
                    if (TextUtils.equals(list.get(i2), strF)) {
                        arrayList2.add(Integer.valueOf(i));
                        break;
                    }
                    i2++;
                }
            }
            if (arrayList2.size() == 0) {
                z = false;
            } else {
                for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                    int iIntValue = ((Integer) arrayList2.get(i3)).intValue();
                    xx xxVar = arrayList.get(iIntValue);
                    xxVar.k = true;
                    arrayList.remove(iIntValue);
                    arrayList.add(i3, xxVar);
                }
            }
        }
        if (!z) {
            D(arrayList);
        }
        this.m = arrayList;
        TopView topView = this.j;
        if (topView != null) {
            topView.h.setText(NumberFormat.getInstance().format(arrayList.size()));
        }
        o();
    }

    public abstract String F();

    public final void G(String str) {
        if (str.equals("")) {
            ArrayList<Integer> arrayList = this.p;
            if (arrayList == null) {
                return;
            }
            arrayList.clear();
            this.p = null;
        } else {
            this.p = new ArrayList<>();
            Integer numValueOf = 0;
            String lowerCase = str.toLowerCase(Locale.getDefault());
            for (xx xxVar : this.m) {
                if (xxVar.f.toLowerCase(Locale.getDefault()).contains(lowerCase) || xxVar.e(q, s, t).toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    this.p.add(numValueOf);
                }
                numValueOf = Integer.valueOf(numValueOf.intValue() + 1);
            }
        }
        o();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        ArrayList<Integer> arrayList = this.p;
        if (arrayList != null) {
            return arrayList.size();
        }
        ArrayList<xx> arrayList2 = this.m;
        if (arrayList2 == null || arrayList2.size() == 0) {
            return 0;
        }
        return this.m.size() + (!this.o ? 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int m(int i) {
        return (i != 0 || this.o) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i) {
        ArrayList<Integer> arrayList;
        if (a0Var.j != 1) {
            return;
        }
        b bVar = (b) a0Var;
        boolean z = this.o;
        xx xxVar = (!z || (arrayList = this.p) == null) ? this.m.get(i - (!z ? 1 : 0)) : this.m.get(arrayList.get(i).intValue());
        bVar.B = xxVar;
        Drawable drawable = xxVar.l;
        ContactView contactView = bVar.A;
        if (drawable != null) {
            contactView.q(xxVar, ((BitmapDrawable) drawable).getBitmap());
            return;
        }
        Bitmap bitmapA = bVar.y.getIconCache().a.a(bVar.B.e);
        if (bitmapA == null && !xxVar.e.equals("-1")) {
            e eVar = new e(bVar.B.e, bVar.z, bVar);
            bVar.C = eVar;
            eVar.i = bVar.D;
            eVar.c(i9.e);
        }
        contactView.q(xxVar, bitmapA);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            ContactView contactView = (ContactView) LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.contact_view, (ViewGroup) recyclerView, false);
            contactView.setCategoryView(this.i);
            return new b(contactView, this.i, this.l, this.h.getResources().getDimensionPixelSize(R$dimen.contact_picker_icon_size));
        }
        TopView topView = (TopView) LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.top_view, (ViewGroup) recyclerView, false);
        this.j = topView;
        topView.setSiteString(this.k);
        TopView topView2 = this.j;
        PickerCategoryView pickerCategoryView = this.i;
        topView2.i = pickerCategoryView;
        topView2.o = this;
        if (pickerCategoryView.r) {
            topView2.g.setOnCheckedChangeListener(topView2);
        } else {
            topView2.f.setVisibility(8);
        }
        TopView topView3 = this.j;
        PickerCategoryView pickerCategoryView2 = this.i;
        topView3.j.setVisibility(pickerCategoryView2.s ? 0 : 8);
        topView3.k.setVisibility(pickerCategoryView2.v ? 0 : 8);
        topView3.l.setVisibility(pickerCategoryView2.t ? 0 : 8);
        topView3.m.setVisibility(pickerCategoryView2.u ? 0 : 8);
        topView3.n.setVisibility(pickerCategoryView2.w ? 0 : 8);
        this.i.setTopView(this.j);
        if (this.m != null) {
            this.j.h.setText(NumberFormat.getInstance().format(r5.size()));
        }
        return new a(this.j);
    }
}
