package a.a.n;

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
import androidx.appcompat.widget.d0;
import androidx.appcompat.widget.v0;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class g extends MenuInflater {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Class<?>[] f78a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Class<?>[] f79b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Object[] f80c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Object[] f81d;
    Context e;
    private Object f;

    private static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Class<?>[] f82a = {MenuItem.class};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Object f83b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Method f84c;

        public a(Object obj, String str) {
            this.f83b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f84c = cls.getMethod(str, f82a);
            } catch (Exception e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f84c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f84c.invoke(this.f83b, menuItem)).booleanValue();
                }
                this.f84c.invoke(this.f83b, menuItem);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class b {
        a.g.k.b A;
        private CharSequence B;
        private CharSequence C;
        private ColorStateList D = null;
        private PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Menu f85a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f86b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f87c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f88d;
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
            this.f85a = menu;
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
                menuItem.setActionView((View) e(str, g.f78a, g.this.f80c));
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
            a.g.k.b bVar = this.A;
            if (bVar != null) {
                a.g.k.h.a(menuItem, bVar);
            }
            a.g.k.h.c(menuItem, this.B);
            a.g.k.h.g(menuItem, this.C);
            a.g.k.h.b(menuItem, this.n, this.o);
            a.g.k.h.f(menuItem, this.p, this.q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                a.g.k.h.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                a.g.k.h.d(menuItem, colorStateList);
            }
        }

        public void a() {
            this.h = true;
            i(this.f85a.add(this.f86b, this.i, this.j, this.k));
        }

        public SubMenu b() {
            this.h = true;
            SubMenu subMenuAddSubMenu = this.f85a.addSubMenu(this.f86b, this.i, this.j, this.k);
            i(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public boolean d() {
            return this.h;
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.e.obtainStyledAttributes(attributeSet, a.a.j.r1);
            this.f86b = typedArrayObtainStyledAttributes.getResourceId(a.a.j.t1, 0);
            this.f87c = typedArrayObtainStyledAttributes.getInt(a.a.j.v1, 0);
            this.f88d = typedArrayObtainStyledAttributes.getInt(a.a.j.w1, 0);
            this.e = typedArrayObtainStyledAttributes.getInt(a.a.j.x1, 0);
            this.f = typedArrayObtainStyledAttributes.getBoolean(a.a.j.u1, true);
            this.g = typedArrayObtainStyledAttributes.getBoolean(a.a.j.s1, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void g(AttributeSet attributeSet) {
            v0 v0VarU = v0.u(g.this.e, attributeSet, a.a.j.y1);
            this.i = v0VarU.n(a.a.j.B1, 0);
            this.j = (v0VarU.k(a.a.j.E1, this.f87c) & (-65536)) | (v0VarU.k(a.a.j.F1, this.f88d) & 65535);
            this.k = v0VarU.p(a.a.j.G1);
            this.l = v0VarU.p(a.a.j.H1);
            this.m = v0VarU.n(a.a.j.z1, 0);
            this.n = c(v0VarU.o(a.a.j.I1));
            this.o = v0VarU.k(a.a.j.P1, 4096);
            this.p = c(v0VarU.o(a.a.j.J1));
            this.q = v0VarU.k(a.a.j.T1, 4096);
            int i = a.a.j.K1;
            this.r = v0VarU.s(i) ? v0VarU.a(i, false) : this.e;
            this.s = v0VarU.a(a.a.j.C1, false);
            this.t = v0VarU.a(a.a.j.D1, this.f);
            this.u = v0VarU.a(a.a.j.A1, this.g);
            this.v = v0VarU.k(a.a.j.U1, -1);
            this.z = v0VarU.o(a.a.j.L1);
            this.w = v0VarU.n(a.a.j.M1, 0);
            this.x = v0VarU.o(a.a.j.O1);
            String strO = v0VarU.o(a.a.j.N1);
            this.y = strO;
            boolean z = strO != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (a.g.k.b) e(strO, g.f79b, g.this.f81d);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = v0VarU.p(a.a.j.Q1);
            this.C = v0VarU.p(a.a.j.V1);
            int i2 = a.a.j.S1;
            if (v0VarU.s(i2)) {
                this.E = d0.d(v0VarU.k(i2, -1), this.E);
            } else {
                this.E = null;
            }
            int i3 = a.a.j.R1;
            if (v0VarU.s(i3)) {
                this.D = v0VarU.c(i3);
            } else {
                this.D = null;
            }
            v0VarU.w();
            this.h = false;
        }

        public void h() {
            this.f86b = 0;
            this.f87c = 0;
            this.f88d = 0;
            this.e = 0;
            this.f = true;
            this.g = true;
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f78a = clsArr;
        f79b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.e = context;
        Object[] objArr = {context};
        this.f80c = objArr;
        this.f81d = objArr;
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
                            a.g.k.b bVar2 = bVar.A;
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
        if (!(menu instanceof a.g.e.a.a)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.e.getResources().getLayout(i);
                    parseMenu(layout, Xml.asAttributeSet(layout), menu);
                } catch (XmlPullParserException e) {
                    throw new InflateException("Error inflating menu XML", e);
                }
            } catch (IOException e2) {
                throw new InflateException("Error inflating menu XML", e2);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }
}
