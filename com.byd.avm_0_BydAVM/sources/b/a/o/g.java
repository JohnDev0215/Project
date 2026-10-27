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
import androidx.appcompat.widget.e0;
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
    static final Class<?>[] f1477a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Class<?>[] f1478b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Object[] f1479c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Object[] f1480d;
    Context e;
    private Object f;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    private static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Class<?>[] f1481a = {MenuItem.class};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Object f1482b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Method f1483c;

        public a(Object obj, String str) {
            this.f1482b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f1483c = cls.getMethod(str, f1481a);
            } catch (Exception e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f1483c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f1483c.invoke(this.f1482b, menuItem)).booleanValue();
                }
                this.f1483c.invoke(this.f1482b, menuItem);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
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
        private Menu f1484a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1485b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1486c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f1487d;
        private int e;
        private boolean f;
        private boolean g;
        private boolean h;
        private int i;
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
            this.f1484a = menu;
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
                Constructor<?> constructor = Class.forName(str, false, g.this.e.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e);
                return null;
            }
        }

        private void i(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
            int i = this.v;
            if (i >= 0) {
                menuItem.setShowAsAction(i);
            }
            if (this.z != null) {
                if (g.this.e.isRestricted()) {
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
                menuItem.setActionView((View) e(str, g.f1477a, g.this.f1479c));
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
            this.h = true;
            i(this.f1484a.add(this.f1485b, this.i, this.j, this.k));
        }

        public SubMenu b() {
            this.h = true;
            SubMenu subMenuAddSubMenu = this.f1484a.addSubMenu(this.f1485b, this.i, this.j, this.k);
            i(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public boolean d() {
            return this.h;
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.e.obtainStyledAttributes(attributeSet, b.a.j.t1);
            this.f1485b = typedArrayObtainStyledAttributes.getResourceId(b.a.j.v1, 0);
            this.f1486c = typedArrayObtainStyledAttributes.getInt(b.a.j.x1, 0);
            this.f1487d = typedArrayObtainStyledAttributes.getInt(b.a.j.y1, 0);
            this.e = typedArrayObtainStyledAttributes.getInt(b.a.j.z1, 0);
            this.f = typedArrayObtainStyledAttributes.getBoolean(b.a.j.w1, true);
            this.g = typedArrayObtainStyledAttributes.getBoolean(b.a.j.u1, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            e0 e0VarU = e0.u(g.this.e, attributeSet, b.a.j.A1);
            this.i = e0VarU.n(b.a.j.D1, 0);
            this.j = (e0VarU.k(b.a.j.G1, this.f1486c) & (-65536)) | (e0VarU.k(b.a.j.H1, this.f1487d) & 65535);
            this.k = e0VarU.p(b.a.j.I1);
            this.l = e0VarU.p(b.a.j.J1);
            this.m = e0VarU.n(b.a.j.B1, 0);
            this.n = c(e0VarU.o(b.a.j.K1));
            this.o = e0VarU.k(b.a.j.R1, 4096);
            this.p = c(e0VarU.o(b.a.j.L1));
            this.q = e0VarU.k(b.a.j.V1, 4096);
            int i = b.a.j.M1;
            if (e0VarU.s(i)) {
                this.r = e0VarU.a(i, false) ? 1 : 0;
            } else {
                this.r = this.e;
            }
            this.s = e0VarU.a(b.a.j.E1, false);
            this.t = e0VarU.a(b.a.j.F1, this.f);
            this.u = e0VarU.a(b.a.j.C1, this.g);
            this.v = e0VarU.k(b.a.j.W1, -1);
            this.z = e0VarU.o(b.a.j.N1);
            this.w = e0VarU.n(b.a.j.O1, 0);
            this.x = e0VarU.o(b.a.j.Q1);
            String strO = e0VarU.o(b.a.j.P1);
            this.y = strO;
            boolean z = strO != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (b.g.k.b) e(strO, g.f1478b, g.this.f1480d);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = e0VarU.p(b.a.j.S1);
            this.C = e0VarU.p(b.a.j.X1);
            int i2 = b.a.j.U1;
            if (e0VarU.s(i2)) {
                this.E = q.e(e0VarU.k(i2, -1), this.E);
            } else {
                this.E = null;
            }
            int i3 = b.a.j.T1;
            if (e0VarU.s(i3)) {
                this.D = e0VarU.c(i3);
            } else {
                this.D = null;
            }
            e0VarU.w();
            this.h = false;
        }

        public void h() {
            this.f1485b = 0;
            this.f1486c = 0;
            this.f1487d = 0;
            this.e = 0;
            this.f = true;
            this.g = true;
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f1477a = clsArr;
        f1478b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.e = context;
        Object[] objArr = {context};
        this.f1479c = objArr;
        this.f1480d = objArr;
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
        if (this.f == null) {
            this.f = findRealOwner(this.e);
        }
        return this.f;
    }

    @Override // android.view.MenuInflater
    public void inflate(int i, Menu menu) {
        if (!(menu instanceof b.g.e.a.a)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.e.getResources().getLayout(i);
                    parseMenu(layout, Xml.asAttributeSet(layout), menu);
                } catch (IOException e) {
                    throw new InflateException("Error inflating menu XML", e);
                }
            } catch (XmlPullParserException e2) {
                throw new InflateException("Error inflating menu XML", e2);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }
}
