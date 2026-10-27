package b.b.p;

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
import b.b.j;
import b.b.q.e0;
import b.b.q.q;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: SupportMenuInflater.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends MenuInflater {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?>[] f1551a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class<?>[] f1552b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object[] f1553c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object[] f1554d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f1555e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f1556f;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Class<?>[] f1557a = {MenuItem.class};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f1558b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f1559c;

        public a(Object obj, String str) {
            this.f1558b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f1559c = cls.getMethod(str, f1557a);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f1559c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f1559c.invoke(this.f1558b, menuItem)).booleanValue();
                }
                this.f1559c.invoke(this.f1558b, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public class b {
        public b.h.m.b A;
        public CharSequence B;
        public CharSequence C;
        public ColorStateList D = null;
        public PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Menu f1560a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1561b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1562c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1563d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1564e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1565f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1566g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1567h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1568i;
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

        public b(Menu menu) {
            this.f1560a = menu;
            h();
        }

        public void a() {
            this.f1567h = true;
            i(this.f1560a.add(this.f1561b, this.f1568i, this.j, this.k));
        }

        public SubMenu b() {
            this.f1567h = true;
            SubMenu subMenuAddSubMenu = this.f1560a.addSubMenu(this.f1561b, this.f1568i, this.j, this.k);
            i(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public final char c(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        public boolean d() {
            return this.f1567h;
        }

        public final <T> T e(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, g.this.f1555e.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.f1555e.obtainStyledAttributes(attributeSet, j.MenuGroup);
            this.f1561b = typedArrayObtainStyledAttributes.getResourceId(j.MenuGroup_android_id, 0);
            this.f1562c = typedArrayObtainStyledAttributes.getInt(j.MenuGroup_android_menuCategory, 0);
            this.f1563d = typedArrayObtainStyledAttributes.getInt(j.MenuGroup_android_orderInCategory, 0);
            this.f1564e = typedArrayObtainStyledAttributes.getInt(j.MenuGroup_android_checkableBehavior, 0);
            this.f1565f = typedArrayObtainStyledAttributes.getBoolean(j.MenuGroup_android_visible, true);
            this.f1566g = typedArrayObtainStyledAttributes.getBoolean(j.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            e0 e0VarU = e0.u(g.this.f1555e, attributeSet, j.MenuItem);
            this.f1568i = e0VarU.n(j.MenuItem_android_id, 0);
            this.j = (e0VarU.k(j.MenuItem_android_menuCategory, this.f1562c) & (-65536)) | (e0VarU.k(j.MenuItem_android_orderInCategory, this.f1563d) & 65535);
            this.k = e0VarU.p(j.MenuItem_android_title);
            this.l = e0VarU.p(j.MenuItem_android_titleCondensed);
            this.m = e0VarU.n(j.MenuItem_android_icon, 0);
            this.n = c(e0VarU.o(j.MenuItem_android_alphabeticShortcut));
            this.o = e0VarU.k(j.MenuItem_alphabeticModifiers, 4096);
            this.p = c(e0VarU.o(j.MenuItem_android_numericShortcut));
            this.q = e0VarU.k(j.MenuItem_numericModifiers, 4096);
            int i2 = j.MenuItem_android_checkable;
            if (e0VarU.s(i2)) {
                this.r = e0VarU.a(i2, false) ? 1 : 0;
            } else {
                this.r = this.f1564e;
            }
            this.s = e0VarU.a(j.MenuItem_android_checked, false);
            this.t = e0VarU.a(j.MenuItem_android_visible, this.f1565f);
            this.u = e0VarU.a(j.MenuItem_android_enabled, this.f1566g);
            this.v = e0VarU.k(j.MenuItem_showAsAction, -1);
            this.z = e0VarU.o(j.MenuItem_android_onClick);
            this.w = e0VarU.n(j.MenuItem_actionLayout, 0);
            this.x = e0VarU.o(j.MenuItem_actionViewClass);
            String strO = e0VarU.o(j.MenuItem_actionProviderClass);
            this.y = strO;
            boolean z = strO != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (b.h.m.b) e(strO, g.f1552b, g.this.f1554d);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = e0VarU.p(j.MenuItem_contentDescription);
            this.C = e0VarU.p(j.MenuItem_tooltipText);
            int i3 = j.MenuItem_iconTintMode;
            if (e0VarU.s(i3)) {
                this.E = q.e(e0VarU.k(i3, -1), this.E);
            } else {
                this.E = null;
            }
            int i4 = j.MenuItem_iconTint;
            if (e0VarU.s(i4)) {
                this.D = e0VarU.c(i4);
            } else {
                this.D = null;
            }
            e0VarU.w();
            this.f1567h = false;
        }

        public void h() {
            this.f1561b = 0;
            this.f1562c = 0;
            this.f1563d = 0;
            this.f1564e = 0;
            this.f1565f = true;
            this.f1566g = true;
        }

        public final void i(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
            int i2 = this.v;
            if (i2 >= 0) {
                menuItem.setShowAsAction(i2);
            }
            if (this.z != null) {
                if (g.this.f1555e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.this.getRealOwner(), this.z));
            }
            if (this.r >= 2) {
                if (menuItem instanceof b.b.p.j.i) {
                    ((b.b.p.j.i) menuItem).s(true);
                } else if (menuItem instanceof b.b.p.j.j) {
                    ((b.b.p.j.j) menuItem).h(true);
                }
            }
            String str = this.x;
            if (str != null) {
                menuItem.setActionView((View) e(str, g.f1551a, g.this.f1553c));
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
            b.h.m.b bVar = this.A;
            if (bVar != null) {
                b.h.m.h.a(menuItem, bVar);
            }
            b.h.m.h.c(menuItem, this.B);
            b.h.m.h.g(menuItem, this.C);
            b.h.m.h.b(menuItem, this.n, this.o);
            b.h.m.h.f(menuItem, this.p, this.q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                b.h.m.h.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                b.h.m.h.d(menuItem, colorStateList);
            }
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f1551a = clsArr;
        f1552b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.f1555e = context;
        Object[] objArr = {context};
        this.f1553c = objArr;
        this.f1554d = objArr;
    }

    public final Object findRealOwner(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? findRealOwner(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    public Object getRealOwner() {
        if (this.f1556f == null) {
            this.f1556f = findRealOwner(this.f1555e);
        }
        return this.f1556f;
    }

    @Override // android.view.MenuInflater
    public void inflate(int i2, Menu menu) {
        if (!(menu instanceof b.h.g.a.a)) {
            super.inflate(i2, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f1555e.getResources().getLayout(i2);
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

    public final void parseMenu(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
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
                            b.h.m.b bVar2 = bVar.A;
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
}
