package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorActionViewLayout;
import org.chromium.chrome.browser.tasks.tab_management.d1;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.c;
import org.chromium.ui.modelutil.d;
import org.chromium.ui.modelutil.h;

/* JADX INFO: compiled from: TabSelectionEditorMenuAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j33 implements c.a<h<PropertyModel, v62>, d1, v62> {
    public static void b(PropertyModel propertyModel, k33 k33Var, v62 v62Var) {
        if (v62Var == e33.e) {
            g(propertyModel, k33Var);
            return;
        }
        if (v62Var == e33.g) {
            d(propertyModel, k33Var);
            return;
        }
        if (v62Var == e33.l) {
            if (propertyModel.k(e33.f)) {
                g(propertyModel, k33Var);
            }
            d(propertyModel, k33Var);
            return;
        }
        PropertyModel.j jVar = e33.d;
        if (v62Var == jVar || v62Var == e33.j) {
            int iH = propertyModel.h(jVar);
            PropertyModel.r<Drawable> rVar = e33.j;
            Drawable drawable = (Drawable) propertyModel.j(rVar);
            k33Var.b.b.p(rVar, drawable);
            Button button = k33Var.c;
            if (button == null || !k33Var.e) {
                return;
            }
            Drawable drawable2 = iH == 0 ? drawable : null;
            if (iH != 1) {
                drawable = null;
            }
            button.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable2, (Drawable) null, drawable, (Drawable) null);
            return;
        }
        PropertyModel.n nVar = e33.k;
        if (v62Var == nVar) {
            boolean zK = propertyModel.k(nVar);
            k33Var.f = zK;
            k33Var.b.b.l(nVar, zK);
            Button button2 = k33Var.c;
            if (button2 != null) {
                button2.setEnabled(zK);
                return;
            }
            return;
        }
        PropertyModel.r<ColorStateList> rVar2 = e33.m;
        if (v62Var == rVar2) {
            ColorStateList colorStateList = (ColorStateList) propertyModel.j(rVar2);
            Button button3 = k33Var.c;
            if (button3 != null) {
                button3.setTextColor(colorStateList);
                return;
            }
            return;
        }
        PropertyModel.r<ColorStateList> rVar3 = e33.n;
        if (v62Var == rVar3) {
            k33Var.a((ColorStateList) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.r<Runnable> rVar4 = e33.o;
        if (v62Var == rVar4) {
            k33Var.j = (Runnable) propertyModel.j(rVar4);
            Button button4 = k33Var.c;
            if (button4 != null) {
                button4.setOnClickListener(new gs(11, k33Var));
                return;
            }
            return;
        }
        PropertyModel.h hVar = e33.p;
        if (v62Var == hVar) {
            k33Var.g = propertyModel.k(hVar);
            return;
        }
        PropertyModel.r<Callback<List<Integer>>> rVar5 = e33.q;
        if (v62Var == rVar5) {
            k33Var.k = (Callback) propertyModel.j(rVar5);
        }
    }

    public static void c(h hVar, d1 d1Var, int i, int i2, v62 v62Var) {
        for (int i3 = i; i3 < i + i2; i3++) {
            PropertyModel propertyModel = (PropertyModel) hVar.get(i3);
            PropertyModel propertyModel2 = (PropertyModel) hVar.get(i3);
            PropertyModel.j jVar = e33.a;
            k33 k33Var = (k33) d1Var.f.get(Integer.valueOf(propertyModel2.h(jVar)));
            if (v62Var == null) {
                int iH = propertyModel.h(e33.b);
                int iH2 = propertyModel.h(e33.c);
                k33Var.getClass();
                boolean z = true;
                boolean z2 = iH2 == 0 || iH2 == 2;
                k33Var.d = z2;
                if (iH2 != 1 && iH2 != 2) {
                    z = false;
                }
                k33Var.e = z;
                if ((z2 || z) && iH != 0) {
                    Button button = (Button) LayoutInflater.from(k33Var.a).inflate(R$layout.tab_selection_editor_action_view, (ViewGroup) null);
                    k33Var.c = button;
                    button.setId(k33Var.b.b.h(jVar));
                    if (k33Var.e && !k33Var.d) {
                        k33Var.c.setCompoundDrawablePadding(0);
                    }
                }
                for (v62 v62Var2 : e33.r) {
                    b(propertyModel, k33Var, v62Var2);
                }
            } else {
                b(propertyModel, k33Var, v62Var);
            }
        }
    }

    public static void d(PropertyModel propertyModel, k33 k33Var) {
        Integer num = (Integer) propertyModel.j(e33.g);
        int iH = propertyModel.h(e33.l);
        k33Var.getClass();
        String quantityString = (num == null || iH <= 0) ? null : k33Var.a.getResources().getQuantityString(num.intValue(), iH, Integer.valueOf(iH));
        k33Var.b.b.p(e33.i, quantityString);
        Button button = k33Var.c;
        if (button != null) {
            button.setContentDescription(quantityString);
        }
    }

    public static void g(PropertyModel propertyModel, k33 k33Var) {
        int iH = propertyModel.k(e33.f) ? propertyModel.h(e33.l) : -1;
        int iH2 = propertyModel.h(e33.e);
        Context context = k33Var.a;
        String quantityString = iH >= 0 ? context.getResources().getQuantityString(iH2, iH, Integer.valueOf(iH)) : context.getResources().getString(iH2);
        k33Var.b.b.p(e33.h, quantityString);
        Button button = k33Var.c;
        if (button != null) {
            if (k33Var.d) {
                button.setText(quantityString);
                return;
            }
            button.setText("");
            k33Var.c.setMinWidth(0);
            k33Var.c.setMinimumWidth(0);
        }
    }

    @Override // org.chromium.ui.modelutil.c.a
    public final void a(d dVar, int i, int i2, Object obj) {
        int i3;
        h hVar = (h) dVar;
        d1 d1Var = (d1) obj;
        int i4 = i;
        while (true) {
            i3 = i + i2;
            if (i4 >= i3) {
                break;
            }
            PropertyModel propertyModel = (PropertyModel) hVar.get(i4);
            PropertyModel.j jVar = e33.a;
            int iH = propertyModel.h(jVar);
            d1Var.getClass();
            HashMap mapE = PropertyModel.e(e33.s);
            int i5 = zj.a;
            PropertyModel.d dVar2 = new PropertyModel.d();
            dVar2.a = iH;
            mapE.put(jVar, dVar2);
            xa1 xa1Var = new xa1(0, new PropertyModel(mapE));
            d1Var.f.put(Integer.valueOf(iH), new k33(d1Var.e, xa1Var));
            d1Var.j.t(xa1Var);
            i4++;
        }
        c(hVar, d1Var, i, i2, null);
        while (i < i3) {
            k33 k33Var = (k33) d1Var.f.get(Integer.valueOf(((PropertyModel) hVar.get(i)).h(e33.a)));
            Button button = k33Var.c;
            TabSelectionEditorActionViewLayout tabSelectionEditorActionViewLayout = d1Var.i;
            if (button == null) {
                tabSelectionEditorActionViewLayout.setHasMenuOnlyItems(true);
            } else {
                tabSelectionEditorActionViewLayout.e.add(k33Var);
                tabSelectionEditorActionViewLayout.measure(TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, Integer.MIN_VALUE);
            }
            i++;
        }
    }

    @Override // org.chromium.ui.modelutil.c.a
    public final /* bridge */ /* synthetic */ void e(d dVar, int i, Object obj, Object obj2, int i2) {
        c((h) dVar, (d1) obj, i, i2, (v62) obj2);
    }

    @Override // org.chromium.ui.modelutil.c.a
    public final void f(d dVar, int i, int i2, Object obj) {
        d1 d1Var = (d1) obj;
        if (((h) dVar).size() != 0) {
            throw new IllegalArgumentException("Partial removal of items is not supported");
        }
        d1Var.f.clear();
        d1Var.j.clear();
        TabSelectionEditorActionViewLayout tabSelectionEditorActionViewLayout = d1Var.i;
        tabSelectionEditorActionViewLayout.a();
        tabSelectionEditorActionViewLayout.e.clear();
        tabSelectionEditorActionViewLayout.k = false;
        tabSelectionEditorActionViewLayout.g.setVisibility(8);
        tabSelectionEditorActionViewLayout.measure(TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, Integer.MIN_VALUE);
    }
}
