package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import defpackage.no2;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: SkinCompatResources.java */
/* JADX INFO: loaded from: classes3.dex */
public final class po2 {
    public static volatile po2 g;
    public Resources a;
    public no2.b d;
    public String b = "";
    public String c = "";
    public boolean e = true;
    public final ArrayList f = new ArrayList();

    public static int a(Context context, int i) {
        int iE;
        ColorStateList colorStateListA;
        po2 po2VarD = d();
        po2VarD.getClass();
        vo2 vo2Var = vo2.i;
        if (!vo2Var.d && (colorStateListA = vo2Var.a(i)) != null) {
            return colorStateListA.getDefaultColor();
        }
        no2.b bVar = po2VarD.d;
        if (bVar != null) {
            bVar.d();
        }
        return (po2VarD.e || (iE = po2VarD.e(context, i)) == 0) ? context.getResources().getColor(i, context.getTheme()) : po2VarD.a.getColor(iE);
    }

    public static ColorStateList b(Context context, int i) {
        int iE;
        ColorStateList colorStateListA;
        po2 po2VarD = d();
        po2VarD.getClass();
        vo2 vo2Var = vo2.i;
        if (!vo2Var.d && (colorStateListA = vo2Var.a(i)) != null) {
            return colorStateListA;
        }
        no2.b bVar = po2VarD.d;
        if (bVar != null) {
            bVar.c();
        }
        return (po2VarD.e || (iE = po2VarD.e(context, i)) == 0) ? context.getResources().getColorStateList(i, context.getTheme()) : po2VarD.a.getColorStateList(iE);
    }

    public static Drawable c(Context context, int i) {
        int iE;
        Drawable drawableB;
        ColorStateList colorStateListA;
        po2 po2VarD = d();
        po2VarD.getClass();
        vo2 vo2Var = vo2.i;
        if (!vo2Var.d && (colorStateListA = vo2Var.a(i)) != null) {
            return new ColorDrawable(colorStateListA.getDefaultColor());
        }
        if (!vo2Var.h && (drawableB = vo2Var.b(i)) != null) {
            return drawableB;
        }
        no2.b bVar = po2VarD.d;
        if (bVar != null) {
            bVar.e();
        }
        return (po2VarD.e || (iE = po2VarD.e(context, i)) == 0) ? context.getResources().getDrawable(i, context.getTheme()) : po2VarD.a.getDrawable(iE);
    }

    public static po2 d() {
        if (g == null) {
            synchronized (po2.class) {
                if (g == null) {
                    g = new po2();
                }
            }
        }
        return g;
    }

    public final int e(Context context, int i) {
        try {
            no2.b bVar = this.d;
            String strA = bVar != null ? bVar.a(i, context, this.c) : null;
            if (TextUtils.isEmpty(strA)) {
                strA = context.getResources().getResourceEntryName(i);
            }
            return this.a.getIdentifier(strA, context.getResources().getResourceTypeName(i), this.b);
        } catch (Exception unused) {
            return 0;
        }
    }

    public final void f(no2.b bVar) {
        this.a = no2.o.g.getResources();
        this.b = "";
        this.c = "";
        this.d = bVar;
        this.e = true;
        vo2 vo2Var = vo2.i;
        synchronized (vo2Var.b) {
            vo2Var.c.clear();
        }
        synchronized (vo2Var.f) {
            vo2Var.g.clear();
        }
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((hp2) it.next()).clear();
        }
    }

    public final void g(Resources resources, String str, String str2, no2.b bVar) {
        if (resources == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            f(bVar);
            return;
        }
        this.a = resources;
        this.b = str;
        this.c = str2;
        this.d = bVar;
        this.e = false;
        vo2 vo2Var = vo2.i;
        synchronized (vo2Var.b) {
            vo2Var.c.clear();
        }
        synchronized (vo2Var.f) {
            vo2Var.g.clear();
        }
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((hp2) it.next()).clear();
        }
    }
}
