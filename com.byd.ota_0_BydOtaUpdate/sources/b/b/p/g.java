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
    public static final Class<?>[] f880a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class<?>[] f881b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object[] f882c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object[] f883d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f884e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f885f;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Class<?>[] f886a = {MenuItem.class};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f887b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f888c;

        public a(Object obj, String str) {
            this.f887b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f888c = cls.getMethod(str, f886a);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f888c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f888c.invoke(this.f887b, menuItem)).booleanValue();
                }
                this.f888c.invoke(this.f887b, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public class b {
        public b.f.m.b A;
        public CharSequence B;
        public CharSequence C;
        public ColorStateList D = null;
        public PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Menu f889a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f890b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f891c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f892d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f893e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f894f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f895g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f896h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f897i;
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
            this.f889a = menu;
            h();
        }

        public void a() {
            this.f896h = true;
            i(this.f889a.add(this.f890b, this.f897i, this.j, this.k));
        }

        public SubMenu b() {
            this.f896h = true;
            SubMenu subMenuAddSubMenu = this.f889a.addSubMenu(this.f890b, this.f897i, this.j, this.k);
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
            return this.f896h;
        }

        public final <T> T e(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, g.this.f884e.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.f884e.obtainStyledAttributes(attributeSet, j.t1);
            this.f890b = typedArrayObtainStyledAttributes.getResourceId(j.v1, 0);
            this.f891c = typedArrayObtainStyledAttributes.getInt(j.x1, 0);
            this.f892d = typedArrayObtainStyledAttributes.getInt(j.y1, 0);
            this.f893e = typedArrayObtainStyledAttributes.getInt(j.z1, 0);
            this.f894f = typedArrayObtainStyledAttributes.getBoolean(j.w1, true);
            this.f895g = typedArrayObtainStyledAttributes.getBoolean(j.u1, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            e0 e0VarT = e0.t(g.this.f884e, attributeSet, j.A1);
            this.f897i = e0VarT.m(j.D1, 0);
            this.j = (e0VarT.j(j.G1, this.f891c) & (-65536)) | (e0VarT.j(j.H1, this.f892d) & 65535);
            this.k = e0VarT.o(j.I1);
            this.l = e0VarT.o(j.J1);
            this.m = e0VarT.m(j.B1, 0);
            this.n = c(e0VarT.n(j.K1));
            this.o = e0VarT.j(j.R1, 4096);
            this.p = c(e0VarT.n(j.L1));
            this.q = e0VarT.j(j.V1, 4096);
            int i2 = j.M1;
            if (e0VarT.r(i2)) {
                this.r = e0VarT.a(i2, false) ? 1 : 0;
            } else {
                this.r = this.f893e;
            }
            this.s = e0VarT.a(j.E1, false);
            this.t = e0VarT.a(j.F1, this.f894f);
            this.u = e0VarT.a(j.C1, this.f895g);
            this.v = e0VarT.j(j.W1, -1);
            this.z = e0VarT.n(j.N1);
            this.w = e0VarT.m(j.O1, 0);
            this.x = e0VarT.n(j.Q1);
            String strN = e0VarT.n(j.P1);
            this.y = strN;
            boolean z = strN != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (b.f.m.b) e(strN, g.f881b, g.this.f883d);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = e0VarT.o(j.S1);
            this.C = e0VarT.o(j.X1);
            int i3 = j.U1;
            if (e0VarT.r(i3)) {
                this.E = q.e(e0VarT.j(i3, -1), this.E);
            } else {
                this.E = null;
            }
            int i4 = j.T1;
            if (e0VarT.r(i4)) {
                this.D = e0VarT.c(i4);
            } else {
                this.D = null;
            }
            e0VarT.v();
            this.f896h = false;
        }

        public void h() {
            this.f890b = 0;
            this.f891c = 0;
            this.f892d = 0;
            this.f893e = 0;
            this.f894f = true;
            this.f895g = true;
        }

        public final void i(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
            int i2 = this.v;
            if (i2 >= 0) {
                menuItem.setShowAsAction(i2);
            }
            if (this.z != null) {
                if (g.this.f884e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.this.b(), this.z));
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
                menuItem.setActionView((View) e(str, g.f880a, g.this.f882c));
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
            b.f.m.b bVar = this.A;
            if (bVar != null) {
                b.f.m.h.a(menuItem, bVar);
            }
            b.f.m.h.c(menuItem, this.B);
            b.f.m.h.g(menuItem, this.C);
            b.f.m.h.b(menuItem, this.n, this.o);
            b.f.m.h.f(menuItem, this.p, this.q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                b.f.m.h.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                b.f.m.h.d(menuItem, colorStateList);
            }
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f880a = clsArr;
        f881b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.f884e = context;
        Object[] objArr = {context};
        this.f882c = objArr;
        this.f883d = objArr;
    }

    public final Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    public Object b() {
        if (this.f885f == null) {
            this.f885f = a(this.f884e);
        }
        return this.f885f;
    }

    public final void c(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
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
                            b.f.m.b bVar2 = bVar.A;
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
                    c(xmlPullParser, attributeSet, bVar.b());
                } else {
                    str = name3;
                    z2 = true;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    @Override // android.view.MenuInflater
    public void inflate(int i2, Menu menu) {
        if (!(menu instanceof b.f.h.a.a)) {
            super.inflate(i2, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f884e.getResources().getLayout(i2);
                    c(layout, Xml.asAttributeSet(layout), menu);
                } catch (XmlPullParserException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (IOException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }
}
