package m;

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
import n.j;
import o.e0;
import o.q;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: SupportMenuInflater.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends MenuInflater {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?>[] f3750a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class<?>[] f3751b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object[] f3752c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object[] f3753d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f3754e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f3755f;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Class<?>[] f3756a = {MenuItem.class};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f3757b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f3758c;

        public a(Object obj, String str) {
            this.f3757b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f3758c = cls.getMethod(str, f3756a);
            } catch (Exception e3) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e3);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f3758c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f3758c.invoke(this.f3757b, menuItem)).booleanValue();
                }
                this.f3758c.invoke(this.f3757b, menuItem);
                return true;
            } catch (Exception e3) {
                throw new RuntimeException(e3);
            }
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public class b {
        public l0.b A;
        public CharSequence B;
        public CharSequence C;
        public ColorStateList D = null;
        public PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Menu f3759a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3760b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3761c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3762d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3763e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f3764f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f3765g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f3766h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f3767i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f3768j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public CharSequence f3769k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public CharSequence f3770l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f3771m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public char f3772n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f3773o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public char f3774p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f3775q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f3776r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public boolean f3777s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public boolean f3778t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public boolean f3779u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public int f3780v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public int f3781w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public String f3782x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public String f3783y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public String f3784z;

        public b(Menu menu) {
            this.f3759a = menu;
            h();
        }

        public void a() {
            this.f3766h = true;
            i(this.f3759a.add(this.f3760b, this.f3767i, this.f3768j, this.f3769k));
        }

        public SubMenu b() {
            this.f3766h = true;
            SubMenu subMenuAddSubMenu = this.f3759a.addSubMenu(this.f3760b, this.f3767i, this.f3768j, this.f3769k);
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
            return this.f3766h;
        }

        public final <T> T e(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, g.this.f3754e.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e3) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e3);
                return null;
            }
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.f3754e.obtainStyledAttributes(attributeSet, R$styleable.MenuGroup);
            this.f3760b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MenuGroup_android_id, 0);
            this.f3761c = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_menuCategory, 0);
            this.f3762d = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_orderInCategory, 0);
            this.f3763e = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_checkableBehavior, 0);
            this.f3764f = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_visible, true);
            this.f3765g = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            e0 e0VarU = e0.u(g.this.f3754e, attributeSet, R$styleable.MenuItem);
            this.f3767i = e0VarU.n(R$styleable.MenuItem_android_id, 0);
            this.f3768j = (e0VarU.k(R$styleable.MenuItem_android_menuCategory, this.f3761c) & (-65536)) | (e0VarU.k(R$styleable.MenuItem_android_orderInCategory, this.f3762d) & 65535);
            this.f3769k = e0VarU.p(R$styleable.MenuItem_android_title);
            this.f3770l = e0VarU.p(R$styleable.MenuItem_android_titleCondensed);
            this.f3771m = e0VarU.n(R$styleable.MenuItem_android_icon, 0);
            this.f3772n = c(e0VarU.o(R$styleable.MenuItem_android_alphabeticShortcut));
            this.f3773o = e0VarU.k(R$styleable.MenuItem_alphabeticModifiers, 4096);
            this.f3774p = c(e0VarU.o(R$styleable.MenuItem_android_numericShortcut));
            this.f3775q = e0VarU.k(R$styleable.MenuItem_numericModifiers, 4096);
            int i3 = R$styleable.MenuItem_android_checkable;
            if (e0VarU.s(i3)) {
                this.f3776r = e0VarU.a(i3, false) ? 1 : 0;
            } else {
                this.f3776r = this.f3763e;
            }
            this.f3777s = e0VarU.a(R$styleable.MenuItem_android_checked, false);
            this.f3778t = e0VarU.a(R$styleable.MenuItem_android_visible, this.f3764f);
            this.f3779u = e0VarU.a(R$styleable.MenuItem_android_enabled, this.f3765g);
            this.f3780v = e0VarU.k(R$styleable.MenuItem_showAsAction, -1);
            this.f3784z = e0VarU.o(R$styleable.MenuItem_android_onClick);
            this.f3781w = e0VarU.n(R$styleable.MenuItem_actionLayout, 0);
            this.f3782x = e0VarU.o(R$styleable.MenuItem_actionViewClass);
            String strO = e0VarU.o(R$styleable.MenuItem_actionProviderClass);
            this.f3783y = strO;
            boolean z3 = strO != null;
            if (z3 && this.f3781w == 0 && this.f3782x == null) {
                this.A = (l0.b) e(strO, g.f3751b, g.this.f3753d);
            } else {
                if (z3) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = e0VarU.p(R$styleable.MenuItem_contentDescription);
            this.C = e0VarU.p(R$styleable.MenuItem_tooltipText);
            int i4 = R$styleable.MenuItem_iconTintMode;
            if (e0VarU.s(i4)) {
                this.E = q.e(e0VarU.k(i4, -1), this.E);
            } else {
                this.E = null;
            }
            int i5 = R$styleable.MenuItem_iconTint;
            if (e0VarU.s(i5)) {
                this.D = e0VarU.c(i5);
            } else {
                this.D = null;
            }
            e0VarU.w();
            this.f3766h = false;
        }

        public void h() {
            this.f3760b = 0;
            this.f3761c = 0;
            this.f3762d = 0;
            this.f3763e = 0;
            this.f3764f = true;
            this.f3765g = true;
        }

        public final void i(MenuItem menuItem) {
            boolean z3 = false;
            menuItem.setChecked(this.f3777s).setVisible(this.f3778t).setEnabled(this.f3779u).setCheckable(this.f3776r >= 1).setTitleCondensed(this.f3770l).setIcon(this.f3771m);
            int i3 = this.f3780v;
            if (i3 >= 0) {
                menuItem.setShowAsAction(i3);
            }
            if (this.f3784z != null) {
                if (g.this.f3754e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.this.getRealOwner(), this.f3784z));
            }
            if (this.f3776r >= 2) {
                if (menuItem instanceof n.i) {
                    ((n.i) menuItem).s(true);
                } else if (menuItem instanceof j) {
                    ((j) menuItem).h(true);
                }
            }
            String str = this.f3782x;
            if (str != null) {
                menuItem.setActionView((View) e(str, g.f3750a, g.this.f3752c));
                z3 = true;
            }
            int i4 = this.f3781w;
            if (i4 > 0) {
                if (z3) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i4);
                }
            }
            l0.b bVar = this.A;
            if (bVar != null) {
                l0.h.a(menuItem, bVar);
            }
            l0.h.c(menuItem, this.B);
            l0.h.g(menuItem, this.C);
            l0.h.b(menuItem, this.f3772n, this.f3773o);
            l0.h.f(menuItem, this.f3774p, this.f3775q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                l0.h.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                l0.h.d(menuItem, colorStateList);
            }
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f3750a = clsArr;
        f3751b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.f3754e = context;
        Object[] objArr = {context};
        this.f3752c = objArr;
        this.f3753d = objArr;
    }

    public final Object findRealOwner(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? findRealOwner(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    public Object getRealOwner() {
        if (this.f3755f == null) {
            this.f3755f = findRealOwner(this.f3754e);
        }
        return this.f3755f;
    }

    @Override // android.view.MenuInflater
    public void inflate(int i3, Menu menu) {
        if (!(menu instanceof f0.a)) {
            super.inflate(i3, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f3754e.getResources().getLayout(i3);
                    parseMenu(layout, Xml.asAttributeSet(layout), menu);
                } catch (XmlPullParserException e3) {
                    throw new InflateException("Error inflating menu XML", e3);
                }
            } catch (IOException e4) {
                throw new InflateException("Error inflating menu XML", e4);
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
        boolean z3 = false;
        boolean z4 = false;
        while (!z3) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z4 && name2.equals(str)) {
                        str = null;
                        z4 = false;
                    } else if (name2.equals("group")) {
                        bVar.h();
                    } else if (name2.equals("item")) {
                        if (!bVar.d()) {
                            l0.b bVar2 = bVar.A;
                            if (bVar2 == null || !bVar2.a()) {
                                bVar.a();
                            } else {
                                bVar.b();
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z3 = true;
                    }
                }
            } else if (!z4) {
                String name3 = xmlPullParser.getName();
                if (name3.equals("group")) {
                    bVar.f(attributeSet);
                } else if (name3.equals("item")) {
                    bVar.g(attributeSet);
                } else if (name3.equals("menu")) {
                    parseMenu(xmlPullParser, attributeSet, bVar.b());
                } else {
                    str = name3;
                    z4 = true;
                }
            }
            eventType = xmlPullParser.next();
        }
    }
}
