package b.b.e;

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
import androidx.appcompat.R$styleable;
import b.b.e.j.j;
import b.b.f.e0;
import b.b.f.q;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: SupportMenuInflater.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends MenuInflater {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?>[] f659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class<?>[] f660b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object[] f661c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object[] f662d;
    public Context e;
    public Object f;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Class<?>[] f663a = {MenuItem.class};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f664b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f665c;

        public a(Object obj, String str) {
            this.f664b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f665c = cls.getMethod(str, f663a);
            } catch (Exception e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f665c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f665c.invoke(this.f664b, menuItem)).booleanValue();
                }
                this.f665c.invoke(this.f664b, menuItem);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public class b {
        public b.g.j.b A;
        public CharSequence B;
        public CharSequence C;
        public ColorStateList D = null;
        public PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Menu f666a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f667b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f668c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f669d;
        public int e;
        public boolean f;
        public boolean g;
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

        public b(Menu menu) {
            this.f666a = menu;
            h();
        }

        public void a() {
            this.h = true;
            i(this.f666a.add(this.f667b, this.i, this.j, this.k));
        }

        public SubMenu b() {
            this.h = true;
            SubMenu subMenuAddSubMenu = this.f666a.addSubMenu(this.f667b, this.i, this.j, this.k);
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
            return this.h;
        }

        public final <T> T e(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, g.this.e.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e);
                return null;
            }
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.e.obtainStyledAttributes(attributeSet, R$styleable.MenuGroup);
            this.f667b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MenuGroup_android_id, 0);
            this.f668c = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_menuCategory, 0);
            this.f669d = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_orderInCategory, 0);
            this.e = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_checkableBehavior, 0);
            this.f = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_visible, true);
            this.g = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            e0 e0VarU = e0.u(g.this.e, attributeSet, R$styleable.MenuItem);
            this.i = e0VarU.n(R$styleable.MenuItem_android_id, 0);
            this.j = (e0VarU.k(R$styleable.MenuItem_android_menuCategory, this.f668c) & (-65536)) | (e0VarU.k(R$styleable.MenuItem_android_orderInCategory, this.f669d) & 65535);
            this.k = e0VarU.p(R$styleable.MenuItem_android_title);
            this.l = e0VarU.p(R$styleable.MenuItem_android_titleCondensed);
            this.m = e0VarU.n(R$styleable.MenuItem_android_icon, 0);
            this.n = c(e0VarU.o(R$styleable.MenuItem_android_alphabeticShortcut));
            this.o = e0VarU.k(R$styleable.MenuItem_alphabeticModifiers, 4096);
            this.p = c(e0VarU.o(R$styleable.MenuItem_android_numericShortcut));
            this.q = e0VarU.k(R$styleable.MenuItem_numericModifiers, 4096);
            int i = R$styleable.MenuItem_android_checkable;
            if (e0VarU.s(i)) {
                this.r = e0VarU.a(i, false) ? 1 : 0;
            } else {
                this.r = this.e;
            }
            this.s = e0VarU.a(R$styleable.MenuItem_android_checked, false);
            this.t = e0VarU.a(R$styleable.MenuItem_android_visible, this.f);
            this.u = e0VarU.a(R$styleable.MenuItem_android_enabled, this.g);
            this.v = e0VarU.k(R$styleable.MenuItem_showAsAction, -1);
            this.z = e0VarU.o(R$styleable.MenuItem_android_onClick);
            this.w = e0VarU.n(R$styleable.MenuItem_actionLayout, 0);
            this.x = e0VarU.o(R$styleable.MenuItem_actionViewClass);
            String strO = e0VarU.o(R$styleable.MenuItem_actionProviderClass);
            this.y = strO;
            boolean z = strO != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (b.g.j.b) e(strO, g.f660b, g.this.f662d);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = e0VarU.p(R$styleable.MenuItem_contentDescription);
            this.C = e0VarU.p(R$styleable.MenuItem_tooltipText);
            int i2 = R$styleable.MenuItem_iconTintMode;
            if (e0VarU.s(i2)) {
                this.E = q.e(e0VarU.k(i2, -1), this.E);
            } else {
                this.E = null;
            }
            int i3 = R$styleable.MenuItem_iconTint;
            if (e0VarU.s(i3)) {
                this.D = e0VarU.c(i3);
            } else {
                this.D = null;
            }
            e0VarU.w();
            this.h = false;
        }

        public void h() {
            this.f667b = 0;
            this.f668c = 0;
            this.f669d = 0;
            this.e = 0;
            this.f = true;
            this.g = true;
        }

        public final void i(MenuItem menuItem) {
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
                menuItem.setOnMenuItemClickListener(new a(g.this.b(), this.z));
            }
            if (this.r >= 2) {
                if (menuItem instanceof b.b.e.j.i) {
                    ((b.b.e.j.i) menuItem).t(true);
                } else if (menuItem instanceof j) {
                    ((j) menuItem).h(true);
                }
            }
            String str = this.x;
            if (str != null) {
                menuItem.setActionView((View) e(str, g.f659a, g.this.f661c));
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
            b.g.j.b bVar = this.A;
            if (bVar != null) {
                b.g.j.h.a(menuItem, bVar);
            }
            b.g.j.h.c(menuItem, this.B);
            b.g.j.h.g(menuItem, this.C);
            b.g.j.h.b(menuItem, this.n, this.o);
            b.g.j.h.f(menuItem, this.p, this.q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                b.g.j.h.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                b.g.j.h.d(menuItem, colorStateList);
            }
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f659a = clsArr;
        f660b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.e = context;
        Object[] objArr = {context};
        this.f661c = objArr;
        this.f662d = objArr;
    }

    public final Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    public Object b() {
        if (this.f == null) {
            this.f = a(this.e);
        }
        return this.f;
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
                            b.g.j.b bVar2 = bVar.A;
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
    public void inflate(int i, Menu menu) {
        if (!(menu instanceof b.g.d.a.a)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.e.getResources().getLayout(i);
                    c(layout, Xml.asAttributeSet(layout), menu);
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
