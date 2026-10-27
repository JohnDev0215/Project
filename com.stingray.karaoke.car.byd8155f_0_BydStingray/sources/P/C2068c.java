package p;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import h2.AbstractC1347d;
import h6.n;
import java.lang.reflect.Constructor;
import q.MenuItemC2152i;

/* JADX INFO: renamed from: p.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2068c {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public CharSequence f20607A;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final /* synthetic */ C2069d f20609D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Menu f20610a;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f20615h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20616i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f20617j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f20618k;
    public CharSequence l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20619m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public char f20620n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public char f20621p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f20622q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f20623r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f20624s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f20625t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f20626u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f20627v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f20628w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f20629x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f20630y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public CharSequence f20631z;
    public ColorStateList B = null;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public PorterDuff.Mode f20608C = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20611b = 0;
    public int c = 0;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f20612e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f20613f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f20614g = true;

    public C2068c(C2069d c2069d, Menu menu) {
        this.f20609D = c2069d;
        this.f20610a = menu;
    }

    public final Object a(String str, Class[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, this.f20609D.c.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception e10) {
            n.u("SupportMenuInflater", "Cannot instantiate class: " + str, e10);
            return null;
        }
    }

    public final void b(MenuItem menuItem) {
        boolean z6 = false;
        menuItem.setChecked(this.f20624s).setVisible(this.f20625t).setEnabled(this.f20626u).setCheckable(this.f20623r >= 1).setTitleCondensed(this.l).setIcon(this.f20619m);
        int i7 = this.f20627v;
        if (i7 >= 0) {
            menuItem.setShowAsAction(i7);
        }
        String str = this.f20630y;
        C2069d c2069d = this.f20609D;
        if (str != null) {
            if (c2069d.c.isRestricted()) {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
            if (c2069d.d == null) {
                c2069d.d = C2069d.a(c2069d.c);
            }
            Object obj = c2069d.d;
            String str2 = this.f20630y;
            MenuItemOnMenuItemClickListenerC2067b menuItemOnMenuItemClickListenerC2067b = new MenuItemOnMenuItemClickListenerC2067b();
            menuItemOnMenuItemClickListenerC2067b.f20605a = obj;
            Class<?> cls = obj.getClass();
            try {
                menuItemOnMenuItemClickListenerC2067b.f20606b = cls.getMethod(str2, MenuItemOnMenuItemClickListenerC2067b.c);
                menuItem.setOnMenuItemClickListener(menuItemOnMenuItemClickListenerC2067b);
            } catch (Exception e10) {
                StringBuilder sbJ = AbstractC1347d.j("Couldn't resolve menu item onClick handler ", str2, " in class ");
                sbJ.append(cls.getName());
                InflateException inflateException = new InflateException(sbJ.toString());
                inflateException.initCause(e10);
                throw inflateException;
            }
        }
        if (this.f20623r >= 2 && (menuItem instanceof MenuItemC2152i)) {
            MenuItemC2152i menuItemC2152i = (MenuItemC2152i) menuItem;
            menuItemC2152i.f21086x = (menuItemC2152i.f21086x & (-5)) | 4;
        }
        String str3 = this.f20629x;
        if (str3 != null) {
            menuItem.setActionView((View) a(str3, C2069d.f20632e, c2069d.f20634a));
            z6 = true;
        }
        int i10 = this.f20628w;
        if (i10 > 0) {
            if (z6) {
                n.t("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            } else {
                menuItem.setActionView(i10);
            }
        }
        CharSequence charSequence = this.f20631z;
        boolean z10 = menuItem instanceof MenuItemC2152i;
        if (z10) {
            ((MenuItemC2152i) menuItem).e(charSequence);
        } else {
            menuItem.setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.f20607A;
        if (z10) {
            ((MenuItemC2152i) menuItem).g(charSequence2);
        } else {
            menuItem.setTooltipText(charSequence2);
        }
        char c = this.f20620n;
        int i11 = this.o;
        if (z10) {
            ((MenuItemC2152i) menuItem).setAlphabeticShortcut(c, i11);
        } else {
            menuItem.setAlphabeticShortcut(c, i11);
        }
        char c10 = this.f20621p;
        int i12 = this.f20622q;
        if (z10) {
            ((MenuItemC2152i) menuItem).setNumericShortcut(c10, i12);
        } else {
            menuItem.setNumericShortcut(c10, i12);
        }
        PorterDuff.Mode mode = this.f20608C;
        if (mode != null) {
            if (z10) {
                ((MenuItemC2152i) menuItem).setIconTintMode(mode);
            } else {
                menuItem.setIconTintMode(mode);
            }
        }
        ColorStateList colorStateList = this.B;
        if (colorStateList != null) {
            if (z10) {
                ((MenuItemC2152i) menuItem).setIconTintList(colorStateList);
            } else {
                menuItem.setIconTintList(colorStateList);
            }
        }
    }
}
