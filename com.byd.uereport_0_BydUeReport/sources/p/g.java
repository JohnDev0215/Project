package p;

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
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import q.j;
import r.e0;
import r.q;

/* JADX INFO: compiled from: SupportMenuInflater.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends MenuInflater {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?>[] f6916a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class<?>[] f6917b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object[] f6918c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object[] f6919d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f6920e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f6921f;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Class<?>[] f6922a = {MenuItem.class};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f6923b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f6924c;

        public a(Object obj, String str) {
            this.f6923b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f6924c = cls.getMethod(str, f6922a);
            } catch (Exception e8) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e8);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f6924c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f6924c.invoke(this.f6923b, menuItem)).booleanValue();
                }
                this.f6924c.invoke(this.f6923b, menuItem);
                return true;
            } catch (Exception e8) {
                throw new RuntimeException(e8);
            }
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public class b {
        public o0.b A;
        public CharSequence B;
        public CharSequence C;
        public ColorStateList D = null;
        public PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Menu f6925a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f6926b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f6927c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f6928d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f6929e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f6930f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f6931g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f6932h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f6933i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f6934j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public CharSequence f6935k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public CharSequence f6936l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f6937m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public char f6938n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f6939o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public char f6940p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f6941q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f6942r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public boolean f6943s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public boolean f6944t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public boolean f6945u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public int f6946v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public int f6947w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public String f6948x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public String f6949y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public String f6950z;

        public b(Menu menu) {
            this.f6925a = menu;
            h();
        }

        public void a() {
            this.f6932h = true;
            i(this.f6925a.add(this.f6926b, this.f6933i, this.f6934j, this.f6935k));
        }

        public SubMenu b() {
            this.f6932h = true;
            SubMenu subMenuAddSubMenu = this.f6925a.addSubMenu(this.f6926b, this.f6933i, this.f6934j, this.f6935k);
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
            return this.f6932h;
        }

        public final <T> T e(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, g.this.f6920e.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e8) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e8);
                return null;
            }
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.f6920e.obtainStyledAttributes(attributeSet, R$styleable.MenuGroup);
            this.f6926b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MenuGroup_android_id, 0);
            this.f6927c = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_menuCategory, 0);
            this.f6928d = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_orderInCategory, 0);
            this.f6929e = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_checkableBehavior, 0);
            this.f6930f = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_visible, true);
            this.f6931g = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            e0 e0VarU = e0.u(g.this.f6920e, attributeSet, R$styleable.MenuItem);
            this.f6933i = e0VarU.n(R$styleable.MenuItem_android_id, 0);
            this.f6934j = (e0VarU.k(R$styleable.MenuItem_android_menuCategory, this.f6927c) & (-65536)) | (e0VarU.k(R$styleable.MenuItem_android_orderInCategory, this.f6928d) & 65535);
            this.f6935k = e0VarU.p(R$styleable.MenuItem_android_title);
            this.f6936l = e0VarU.p(R$styleable.MenuItem_android_titleCondensed);
            this.f6937m = e0VarU.n(R$styleable.MenuItem_android_icon, 0);
            this.f6938n = c(e0VarU.o(R$styleable.MenuItem_android_alphabeticShortcut));
            this.f6939o = e0VarU.k(R$styleable.MenuItem_alphabeticModifiers, 4096);
            this.f6940p = c(e0VarU.o(R$styleable.MenuItem_android_numericShortcut));
            this.f6941q = e0VarU.k(R$styleable.MenuItem_numericModifiers, 4096);
            int i8 = R$styleable.MenuItem_android_checkable;
            if (e0VarU.s(i8)) {
                this.f6942r = e0VarU.a(i8, false) ? 1 : 0;
            } else {
                this.f6942r = this.f6929e;
            }
            this.f6943s = e0VarU.a(R$styleable.MenuItem_android_checked, false);
            this.f6944t = e0VarU.a(R$styleable.MenuItem_android_visible, this.f6930f);
            this.f6945u = e0VarU.a(R$styleable.MenuItem_android_enabled, this.f6931g);
            this.f6946v = e0VarU.k(R$styleable.MenuItem_showAsAction, -1);
            this.f6950z = e0VarU.o(R$styleable.MenuItem_android_onClick);
            this.f6947w = e0VarU.n(R$styleable.MenuItem_actionLayout, 0);
            this.f6948x = e0VarU.o(R$styleable.MenuItem_actionViewClass);
            String strO = e0VarU.o(R$styleable.MenuItem_actionProviderClass);
            this.f6949y = strO;
            boolean z7 = strO != null;
            if (z7 && this.f6947w == 0 && this.f6948x == null) {
                this.A = (o0.b) e(strO, g.f6917b, g.this.f6919d);
            } else {
                if (z7) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = e0VarU.p(R$styleable.MenuItem_contentDescription);
            this.C = e0VarU.p(R$styleable.MenuItem_tooltipText);
            int i9 = R$styleable.MenuItem_iconTintMode;
            if (e0VarU.s(i9)) {
                this.E = q.e(e0VarU.k(i9, -1), this.E);
            } else {
                this.E = null;
            }
            int i10 = R$styleable.MenuItem_iconTint;
            if (e0VarU.s(i10)) {
                this.D = e0VarU.c(i10);
            } else {
                this.D = null;
            }
            e0VarU.w();
            this.f6932h = false;
        }

        public void h() {
            this.f6926b = 0;
            this.f6927c = 0;
            this.f6928d = 0;
            this.f6929e = 0;
            this.f6930f = true;
            this.f6931g = true;
        }

        public final void i(MenuItem menuItem) {
            boolean z7 = false;
            menuItem.setChecked(this.f6943s).setVisible(this.f6944t).setEnabled(this.f6945u).setCheckable(this.f6942r >= 1).setTitleCondensed(this.f6936l).setIcon(this.f6937m);
            int i8 = this.f6946v;
            if (i8 >= 0) {
                menuItem.setShowAsAction(i8);
            }
            if (this.f6950z != null) {
                if (g.this.f6920e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.this.getRealOwner(), this.f6950z));
            }
            if (this.f6942r >= 2) {
                if (menuItem instanceof q.i) {
                    ((q.i) menuItem).s(true);
                } else if (menuItem instanceof j) {
                    ((j) menuItem).h(true);
                }
            }
            String str = this.f6948x;
            if (str != null) {
                menuItem.setActionView((View) e(str, g.f6916a, g.this.f6918c));
                z7 = true;
            }
            int i9 = this.f6947w;
            if (i9 > 0) {
                if (z7) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i9);
                }
            }
            o0.b bVar = this.A;
            if (bVar != null) {
                o0.h.a(menuItem, bVar);
            }
            o0.h.c(menuItem, this.B);
            o0.h.g(menuItem, this.C);
            o0.h.b(menuItem, this.f6938n, this.f6939o);
            o0.h.f(menuItem, this.f6940p, this.f6941q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                o0.h.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                o0.h.d(menuItem, colorStateList);
            }
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f6916a = clsArr;
        f6917b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.f6920e = context;
        Object[] objArr = {context};
        this.f6918c = objArr;
        this.f6919d = objArr;
    }

    public final Object findRealOwner(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? findRealOwner(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    public Object getRealOwner() {
        if (this.f6921f == null) {
            this.f6921f = findRealOwner(this.f6920e);
        }
        return this.f6921f;
    }

    @Override // android.view.MenuInflater
    public void inflate(int i8, Menu menu) {
        if (!(menu instanceof i0.a)) {
            super.inflate(i8, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f6920e.getResources().getLayout(i8);
                    parseMenu(layout, Xml.asAttributeSet(layout), menu);
                } catch (IOException e8) {
                    throw new InflateException("Error inflating menu XML", e8);
                }
            } catch (XmlPullParserException e9) {
                throw new InflateException("Error inflating menu XML", e9);
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
        boolean z7 = false;
        boolean z8 = false;
        while (!z7) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z8 && name2.equals(str)) {
                        str = null;
                        z8 = false;
                    } else if (name2.equals("group")) {
                        bVar.h();
                    } else if (name2.equals("item")) {
                        if (!bVar.d()) {
                            o0.b bVar2 = bVar.A;
                            if (bVar2 == null || !bVar2.a()) {
                                bVar.a();
                            } else {
                                bVar.b();
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z7 = true;
                    }
                }
            } else if (!z8) {
                String name3 = xmlPullParser.getName();
                if (name3.equals("group")) {
                    bVar.f(attributeSet);
                } else if (name3.equals("item")) {
                    bVar.g(attributeSet);
                } else if (name3.equals("menu")) {
                    parseMenu(xmlPullParser, attributeSet, bVar.b());
                } else {
                    str = name3;
                    z8 = true;
                }
            }
            eventType = xmlPullParser.next();
        }
    }
}
