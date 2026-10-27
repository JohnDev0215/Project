package b.a.o;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.f0;
import androidx.appcompat.widget.q;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: SupportMenuInflater.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends MenuInflater {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Class<?>[] f2196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Class<?>[] f2197b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Object[] f2198c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Object[] f2199d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Context f2200e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Object f2201f;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    private static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Class<?>[] f2202a = {MenuItem.class};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Object f2203b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Method f2204c;

        public a(Object obj, String str) {
            this.f2203b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f2204c = cls.getMethod(str, f2202a);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f2204c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f2204c.invoke(this.f2203b, menuItem)).booleanValue();
                }
                this.f2204c.invoke(this.f2203b, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    private class b {
        b.g.k.b A;
        private CharSequence B;
        private CharSequence C;
        private ColorStateList D = null;
        private PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Menu f2205a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2206b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2207c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f2208d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f2209e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f2210f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f2211g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f2212h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f2213i;
        private int j;
        private CharSequence k;
        private CharSequence l;
        private int m;
        private char n;
        private int o;
        private char p;
        private int q;
        private int r;
        private boolean s;
        private boolean t;
        private boolean u;
        private int v;
        private int w;
        private String x;
        private String y;
        private String z;

        public b(Menu menu) {
            this.f2205a = menu;
            h();
        }

        private char c(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        private <T> T e(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, g.this.f2200e.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }

        private void i(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
            int i2 = this.v;
            if (i2 >= 0) {
                menuItem.setShowAsAction(i2);
            }
            if (this.z != null) {
                if (g.this.f2200e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.this.getRealOwner(), this.z));
            }
            if (this.r >= 2) {
                if (menuItem instanceof androidx.appcompat.view.menu.i) {
                    ((androidx.appcompat.view.menu.i) menuItem).s(true);
                } else if (menuItem instanceof j) {
                    ((j) menuItem).h(true);
                }
            }
            String str = this.x;
            if (str != null) {
                menuItem.setActionView((View) e(str, g.f2196a, g.this.f2198c));
                z = true;
            }
            int i3 = this.w;
            if (i3 > 0) {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i3);
                }
            }
            b.g.k.b bVar = this.A;
            if (bVar != null) {
                b.g.k.h.a(menuItem, bVar);
            }
            b.g.k.h.c(menuItem, this.B);
            b.g.k.h.g(menuItem, this.C);
            b.g.k.h.b(menuItem, this.n, this.o);
            b.g.k.h.f(menuItem, this.p, this.q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                b.g.k.h.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                b.g.k.h.d(menuItem, colorStateList);
            }
        }

        public void a() {
            this.f2212h = true;
            i(this.f2205a.add(this.f2206b, this.f2213i, this.j, this.k));
        }

        public SubMenu b() {
            this.f2212h = true;
            SubMenu subMenuAddSubMenu = this.f2205a.addSubMenu(this.f2206b, this.f2213i, this.j, this.k);
            i(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public boolean d() {
            return this.f2212h;
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.f2200e.obtainStyledAttributes(attributeSet, b.a.j.MenuGroup);
            this.f2206b = typedArrayObtainStyledAttributes.getResourceId(b.a.j.MenuGroup_android_id, 0);
            this.f2207c = typedArrayObtainStyledAttributes.getInt(b.a.j.MenuGroup_android_menuCategory, 0);
            this.f2208d = typedArrayObtainStyledAttributes.getInt(b.a.j.MenuGroup_android_orderInCategory, 0);
            this.f2209e = typedArrayObtainStyledAttributes.getInt(b.a.j.MenuGroup_android_checkableBehavior, 0);
            this.f2210f = typedArrayObtainStyledAttributes.getBoolean(b.a.j.MenuGroup_android_visible, true);
            this.f2211g = typedArrayObtainStyledAttributes.getBoolean(b.a.j.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            f0 f0VarU = f0.u(g.this.f2200e, attributeSet, b.a.j.MenuItem);
            this.f2213i = f0VarU.n(b.a.j.MenuItem_android_id, 0);
            this.j = (f0VarU.k(b.a.j.MenuItem_android_menuCategory, this.f2207c) & (-65536)) | (f0VarU.k(b.a.j.MenuItem_android_orderInCategory, this.f2208d) & 65535);
            this.k = f0VarU.p(b.a.j.MenuItem_android_title);
            this.l = f0VarU.p(b.a.j.MenuItem_android_titleCondensed);
            this.m = f0VarU.n(b.a.j.MenuItem_android_icon, 0);
            this.n = c(f0VarU.o(b.a.j.MenuItem_android_alphabeticShortcut));
            this.o = f0VarU.k(b.a.j.MenuItem_alphabeticModifiers, 4096);
            this.p = c(f0VarU.o(b.a.j.MenuItem_android_numericShortcut));
            this.q = f0VarU.k(b.a.j.MenuItem_numericModifiers, 4096);
            int i2 = b.a.j.MenuItem_android_checkable;
            if (f0VarU.s(i2)) {
                this.r = f0VarU.a(i2, false) ? 1 : 0;
            } else {
                this.r = this.f2209e;
            }
            this.s = f0VarU.a(b.a.j.MenuItem_android_checked, false);
            this.t = f0VarU.a(b.a.j.MenuItem_android_visible, this.f2210f);
            this.u = f0VarU.a(b.a.j.MenuItem_android_enabled, this.f2211g);
            this.v = f0VarU.k(b.a.j.MenuItem_showAsAction, -1);
            this.z = f0VarU.o(b.a.j.MenuItem_android_onClick);
            this.w = f0VarU.n(b.a.j.MenuItem_actionLayout, 0);
            this.x = f0VarU.o(b.a.j.MenuItem_actionViewClass);
            String strO = f0VarU.o(b.a.j.MenuItem_actionProviderClass);
            this.y = strO;
            boolean z = strO != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (b.g.k.b) e(strO, g.f2197b, g.this.f2199d);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = f0VarU.p(b.a.j.MenuItem_contentDescription);
            this.C = f0VarU.p(b.a.j.MenuItem_tooltipText);
            int i3 = b.a.j.MenuItem_iconTintMode;
            if (f0VarU.s(i3)) {
                this.E = q.e(f0VarU.k(i3, -1), this.E);
            } else {
                this.E = null;
            }
            int i4 = b.a.j.MenuItem_iconTint;
            if (f0VarU.s(i4)) {
                this.D = f0VarU.c(i4);
            } else {
                this.D = null;
            }
            f0VarU.w();
            this.f2212h = false;
        }

        public void h() {
            this.f2206b = 0;
            this.f2207c = 0;
            this.f2208d = 0;
            this.f2209e = 0;
            this.f2210f = true;
            this.f2211g = true;
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f2196a = clsArr;
        f2197b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.f2200e = context;
        Object[] objArr = {context};
        this.f2198c = objArr;
        this.f2199d = objArr;
    }

    private Object findRealOwner(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? findRealOwner(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    private void parseMenu(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        b bVar = new b(menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (!name.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got " + name);
                }
                eventType = xmlPullParser.next();
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        String str = null;
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z2 && name2.equals(str)) {
                        str = null;
                        z2 = false;
                    } else if (name2.equals("group")) {
                        bVar.h();
                    } else if (name2.equals("item")) {
                        if (!bVar.d()) {
                            b.g.k.b bVar2 = bVar.A;
                            if (bVar2 == null || !bVar2.a()) {
                                bVar.a();
                            } else {
                                bVar.b();
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z = true;
                    }
                }
            } else if (!z2) {
                String name3 = xmlPullParser.getName();
                if (name3.equals("group")) {
                    bVar.f(attributeSet);
                } else if (name3.equals("item")) {
                    bVar.g(attributeSet);
                } else if (name3.equals("menu")) {
                    parseMenu(xmlPullParser, attributeSet, bVar.b());
                } else {
                    str = name3;
                    z2 = true;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    Object getRealOwner() {
        if (this.f2201f == null) {
            this.f2201f = findRealOwner(this.f2200e);
        }
        return this.f2201f;
    }

    @Override // android.view.MenuInflater
    public void inflate(int i2, Menu menu) {
        if (!(menu instanceof b.g.e.a.a)) {
            super.inflate(i2, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f2200e.getResources().getLayout(i2);
                    parseMenu(layout, Xml.asAttributeSet(layout), menu);
                } catch (IOException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (XmlPullParserException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }
}
