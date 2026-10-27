package p;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class kh2 {
    public CharSequence A;
    public CharSequence B;
    public final /* synthetic */ lh2 E;
    public final Menu a;
    public boolean h;
    public int i;
    public int j;
    public CharSequence k;
    public CharSequence l;
    public int m;
    public char n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public char f143p;
    public int q;
    public int r;
    public boolean s;
    public boolean t;
    public boolean u;
    public int v;
    public int w;
    public String x;
    public String y;
    public yi1 z;
    public ColorStateList C = null;
    public PorterDuff.Mode D = null;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public boolean f = true;
    public boolean g = true;

    public kh2(lh2 lh2Var, Menu menu) {
        this.E = lh2Var;
        this.a = menu;
    }

    public final Object a(String str, Class[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, this.E.c.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public final void b(MenuItem menuItem) {
        boolean z = false;
        menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
        int i = this.v;
        if (i >= 0) {
            menuItem.setShowAsAction(i);
        }
        String str = this.y;
        lh2 lh2Var = this.E;
        if (str != null) {
            if (lh2Var.c.isRestricted()) {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
            if (lh2Var.d == null) {
                lh2Var.d = lh2.a(lh2Var.c);
            }
            Object obj = lh2Var.d;
            String str2 = this.y;
            jh2 jh2Var = new jh2();
            jh2Var.a = obj;
            Class<?> cls = obj.getClass();
            try {
                jh2Var.b = cls.getMethod(str2, jh2.c);
                menuItem.setOnMenuItemClickListener(jh2Var);
            } catch (Exception e) {
                StringBuilder sbP = u4.p("Couldn't resolve menu item onClick handler ", str2, " in class ");
                sbP.append(cls.getName());
                InflateException inflateException = new InflateException(sbP.toString());
                inflateException.initCause(e);
                throw inflateException;
            }
        }
        if (this.r >= 2) {
            if (menuItem instanceof xi1) {
                xi1 xi1Var = (xi1) menuItem;
                xi1Var.x = (xi1Var.x & (-5)) | 4;
            } else if (menuItem instanceof dj1) {
                dj1 dj1Var = (dj1) menuItem;
                try {
                    Method method = dj1Var.d;
                    mh2 mh2Var = dj1Var.c;
                    if (method == null) {
                        dj1Var.d = mh2Var.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                    }
                    dj1Var.d.invoke(mh2Var, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }
        String str3 = this.x;
        if (str3 != null) {
            menuItem.setActionView((View) a(str3, lh2.e, lh2Var.a));
            z = true;
        }
        int i2 = this.w;
        if (i2 > 0 && !z) {
            menuItem.setActionView(i2);
        }
        yi1 yi1Var = this.z;
        if (yi1Var != null && (menuItem instanceof mh2)) {
            ((mh2) menuItem).a(yi1Var);
        }
        CharSequence charSequence = this.A;
        boolean z2 = menuItem instanceof mh2;
        if (z2) {
            ((mh2) menuItem).setContentDescription(charSequence);
        } else {
            vi1.h(menuItem, charSequence);
        }
        CharSequence charSequence2 = this.B;
        if (z2) {
            ((mh2) menuItem).setTooltipText(charSequence2);
        } else {
            vi1.m(menuItem, charSequence2);
        }
        char c = this.n;
        int i3 = this.o;
        if (z2) {
            ((mh2) menuItem).setAlphabeticShortcut(c, i3);
        } else {
            vi1.g(menuItem, c, i3);
        }
        char c2 = this.f143p;
        int i4 = this.q;
        if (z2) {
            ((mh2) menuItem).setNumericShortcut(c2, i4);
        } else {
            vi1.k(menuItem, c2, i4);
        }
        PorterDuff.Mode mode = this.D;
        if (mode != null) {
            if (z2) {
                ((mh2) menuItem).setIconTintMode(mode);
            } else {
                vi1.j(menuItem, mode);
            }
        }
        ColorStateList colorStateList = this.C;
        if (colorStateList != null) {
            if (z2) {
                ((mh2) menuItem).setIconTintList(colorStateList);
            } else {
                vi1.i(menuItem, colorStateList);
            }
        }
    }
}
