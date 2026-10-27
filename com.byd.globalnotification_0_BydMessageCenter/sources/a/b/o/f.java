package a.b.o;

import a.b.o.i.i;
import a.b.o.i.j;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class f extends MenuInflater {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Class<?>[] f107e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Class<?>[] f108f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f110b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f111c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f112d;

    public static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Class<?>[] f113c = {MenuItem.class};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f114a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Method f115b;

        public a(Object obj, String str) {
            this.f114a = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f115b = cls.getMethod(str, f113c);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f115b.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f115b.invoke(this.f114a, menuItem)).booleanValue();
                }
                this.f115b.invoke(this.f114a, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public class b {
        public a.h.k.b A;
        public CharSequence B;
        public CharSequence C;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Menu f116a;
        public boolean h;
        public int i;
        public int j;
        public CharSequence k;
        public CharSequence l;
        public int m;
        public char n;
        public int o;
        public char p;
        public int q;
        public int r;
        public boolean s;
        public boolean t;
        public boolean u;
        public int v;
        public int w;
        public String x;
        public String y;
        public String z;
        public ColorStateList D = null;
        public PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f117b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f118c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f119d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f120e = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f121f = true;
        public boolean g = true;

        public b(Menu menu) {
            this.f116a = menu;
        }

        public SubMenu a() {
            this.h = true;
            SubMenu subMenuAddSubMenu = this.f116a.addSubMenu(this.f117b, this.i, this.j, this.k);
            c(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public final <T> T b(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, f.this.f111c.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }

        public final void c(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
            int i = this.v;
            if (i >= 0) {
                menuItem.setShowAsAction(i);
            }
            if (this.z != null) {
                if (f.this.f111c.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                f fVar = f.this;
                if (fVar.f112d == null) {
                    fVar.f112d = fVar.findRealOwner(fVar.f111c);
                }
                menuItem.setOnMenuItemClickListener(new a(fVar.f112d, this.z));
            }
            if (this.r >= 2) {
                if (menuItem instanceof i) {
                    i iVar = (i) menuItem;
                    iVar.x = (iVar.x & (-5)) | 4;
                } else if (menuItem instanceof j) {
                    j jVar = (j) menuItem;
                    try {
                        if (jVar.f193e == null) {
                            jVar.f193e = jVar.f192d.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                        }
                        jVar.f193e.invoke(jVar.f192d, Boolean.TRUE);
                    } catch (Exception e2) {
                        Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e2);
                    }
                }
            }
            String str = this.x;
            if (str != null) {
                menuItem.setActionView((View) b(str, f.f107e, f.this.f109a));
                z = true;
            }
            int i2 = this.w;
            if (i2 > 0) {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i2);
                }
            }
            a.h.k.b bVar = this.A;
            if (bVar != null) {
                if (menuItem instanceof a.h.f.a.b) {
                    ((a.h.f.a.b) menuItem).b(bVar);
                } else {
                    Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
                }
            }
            CharSequence charSequence = this.B;
            boolean z2 = menuItem instanceof a.h.f.a.b;
            if (z2) {
                ((a.h.f.a.b) menuItem).setContentDescription(charSequence);
            } else {
                menuItem.setContentDescription(charSequence);
            }
            CharSequence charSequence2 = this.C;
            if (z2) {
                ((a.h.f.a.b) menuItem).setTooltipText(charSequence2);
            } else {
                menuItem.setTooltipText(charSequence2);
            }
            char c2 = this.n;
            int i3 = this.o;
            if (z2) {
                ((a.h.f.a.b) menuItem).setAlphabeticShortcut(c2, i3);
            } else {
                menuItem.setAlphabeticShortcut(c2, i3);
            }
            char c3 = this.p;
            int i4 = this.q;
            if (z2) {
                ((a.h.f.a.b) menuItem).setNumericShortcut(c3, i4);
            } else {
                menuItem.setNumericShortcut(c3, i4);
            }
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                if (z2) {
                    ((a.h.f.a.b) menuItem).setIconTintMode(mode);
                } else {
                    menuItem.setIconTintMode(mode);
                }
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                if (z2) {
                    ((a.h.f.a.b) menuItem).setIconTintList(colorStateList);
                } else {
                    menuItem.setIconTintList(colorStateList);
                }
            }
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f107e = clsArr;
        f108f = clsArr;
    }

    public f(Context context) {
        super(context);
        this.f111c = context;
        Object[] objArr = {context};
        this.f109a = objArr;
        this.f110b = objArr;
    }

    public final Object findRealOwner(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? findRealOwner(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    @Override // android.view.MenuInflater
    public void inflate(int i, Menu menu) {
        if (!(menu instanceof a.h.f.a.a)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f111c.getResources().getLayout(i);
                    parseMenu(layout, Xml.asAttributeSet(layout), menu);
                    layout.close();
                } catch (IOException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (XmlPullParserException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } catch (Throwable th) {
            if (layout != null) {
                layout.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:82:0x023c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void parseMenu(org.xmlpull.v1.XmlPullParser r17, android.util.AttributeSet r18, android.view.Menu r19) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 614
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.o.f.parseMenu(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }
}
