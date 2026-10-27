package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: AppMenuHandlerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i7 implements h7, kr2, bx {
    public x6 e;
    public g7 f;
    public final ArrayList g = new ArrayList();
    public final ArrayList h = new ArrayList();
    public final View i;
    public final Context j;
    public final m7 k;
    public final e7 l;
    public final View m;
    public final q2 n;
    public final nw2<Rect> o;
    public Integer p;

    public i7(AppCompatActivity appCompatActivity, m7 m7Var, e7 e7Var, View view, q2 q2Var, View view2, fc2 fc2Var) {
        this.j = appCompatActivity;
        this.l = e7Var;
        this.k = m7Var;
        this.m = view;
        this.i = view2;
        this.o = fc2Var;
        this.n = q2Var;
        q2Var.f(this);
    }

    public final void a() {
        x6 x6Var = this.e;
        if (x6Var != null) {
            PopupWindow popupWindow = x6Var.j;
            if (popupWindow == null ? false : popupWindow.isShowing()) {
                this.e.c();
            }
        }
    }

    public final boolean b() {
        x6 x6Var = this.e;
        if (x6Var == null) {
            return false;
        }
        PopupWindow popupWindow = x6Var.j;
        return popupWindow == null ? false : popupWindow.isShowing();
    }

    public final void c(Integer num) {
        Integer num2 = this.p;
        if (num2 == null && num == null) {
            return;
        }
        if (num2 == null || !num2.equals(num)) {
            this.p = num;
            boolean z = num != null;
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                ((l7) it.next()).b(z);
            }
        }
    }

    public final boolean d() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.g;
            if (i >= arrayList.size()) {
                return true;
            }
            if (!((y6) arrayList.get(i)).g1()) {
                return false;
            }
            i++;
        }
    }

    @Override // defpackage.kr2
    public final void l() {
        a();
    }

    @Override // defpackage.bx
    public final void onConfigurationChanged(Configuration configuration) {
        a();
    }

    @Override // defpackage.kr2
    public final void j() {
    }
}
