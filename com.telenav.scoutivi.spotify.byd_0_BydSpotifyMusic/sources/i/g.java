package i;

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
import androidx.appcompat.widget.b0;
import androidx.appcompat.widget.s0;
import e.j;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: SupportMenuInflater.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends MenuInflater {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final Class<?>[] f6781e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final Class<?>[] f6782f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object[] f6783a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object[] f6784b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Context f6785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Object f6786d;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    private static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final Class<?>[] f6787c = {MenuItem.class};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Object f6788a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Method f6789b;

        public a(Object obj, String str) {
            this.f6788a = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f6789b = cls.getMethod(str, f6787c);
            } catch (Exception e7) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e7);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f6789b.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f6789b.invoke(this.f6788a, menuItem)).booleanValue();
                }
                this.f6789b.invoke(this.f6788a, menuItem);
                return true;
            } catch (Exception e7) {
                throw new RuntimeException(e7);
            }
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    private class b {
        i0.b A;
        private CharSequence B;
        private CharSequence C;
        private ColorStateList D = null;
        private PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Menu f6790a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f6791b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f6792c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f6793d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f6794e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f6795f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f6796g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f6797h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f6798i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private int f6799j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private CharSequence f6800k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private CharSequence f6801l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private int f6802m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private char f6803n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private int f6804o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private char f6805p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private int f6806q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private int f6807r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        private boolean f6808s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        private boolean f6809t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        private boolean f6810u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        private int f6811v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        private int f6812w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        private String f6813x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        private String f6814y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        private String f6815z;

        public b(Menu menu) {
            this.f6790a = menu;
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
                Constructor<?> constructor = Class.forName(str, false, g.this.f6785c.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e7) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e7);
                return null;
            }
        }

        private void i(MenuItem menuItem) {
            boolean z6 = false;
            menuItem.setChecked(this.f6808s).setVisible(this.f6809t).setEnabled(this.f6810u).setCheckable(this.f6807r >= 1).setTitleCondensed(this.f6801l).setIcon(this.f6802m);
            int i7 = this.f6811v;
            if (i7 >= 0) {
                menuItem.setShowAsAction(i7);
            }
            if (this.f6815z != null) {
                if (g.this.f6785c.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.this.b(), this.f6815z));
            }
            if (this.f6807r >= 2) {
                if (menuItem instanceof androidx.appcompat.view.menu.g) {
                    ((androidx.appcompat.view.menu.g) menuItem).t(true);
                } else if (menuItem instanceof j.c) {
                    ((j.c) menuItem).h(true);
                }
            }
            String str = this.f6813x;
            if (str != null) {
                menuItem.setActionView((View) e(str, g.f6781e, g.this.f6783a));
                z6 = true;
            }
            int i8 = this.f6812w;
            if (i8 > 0) {
                if (z6) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i8);
                }
            }
            i0.b bVar = this.A;
            if (bVar != null) {
                i0.i.a(menuItem, bVar);
            }
            i0.i.c(menuItem, this.B);
            i0.i.g(menuItem, this.C);
            i0.i.b(menuItem, this.f6803n, this.f6804o);
            i0.i.f(menuItem, this.f6805p, this.f6806q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                i0.i.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                i0.i.d(menuItem, colorStateList);
            }
        }

        public void a() {
            this.f6797h = true;
            i(this.f6790a.add(this.f6791b, this.f6798i, this.f6799j, this.f6800k));
        }

        public SubMenu b() {
            this.f6797h = true;
            SubMenu subMenuAddSubMenu = this.f6790a.addSubMenu(this.f6791b, this.f6798i, this.f6799j, this.f6800k);
            i(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public boolean d() {
            return this.f6797h;
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.f6785c.obtainStyledAttributes(attributeSet, j.MenuGroup);
            this.f6791b = typedArrayObtainStyledAttributes.getResourceId(j.MenuGroup_android_id, 0);
            this.f6792c = typedArrayObtainStyledAttributes.getInt(j.MenuGroup_android_menuCategory, 0);
            this.f6793d = typedArrayObtainStyledAttributes.getInt(j.MenuGroup_android_orderInCategory, 0);
            this.f6794e = typedArrayObtainStyledAttributes.getInt(j.MenuGroup_android_checkableBehavior, 0);
            this.f6795f = typedArrayObtainStyledAttributes.getBoolean(j.MenuGroup_android_visible, true);
            this.f6796g = typedArrayObtainStyledAttributes.getBoolean(j.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            s0 s0VarU = s0.u(g.this.f6785c, attributeSet, j.MenuItem);
            this.f6798i = s0VarU.n(j.MenuItem_android_id, 0);
            this.f6799j = (s0VarU.k(j.MenuItem_android_menuCategory, this.f6792c) & (-65536)) | (s0VarU.k(j.MenuItem_android_orderInCategory, this.f6793d) & 65535);
            this.f6800k = s0VarU.p(j.MenuItem_android_title);
            this.f6801l = s0VarU.p(j.MenuItem_android_titleCondensed);
            this.f6802m = s0VarU.n(j.MenuItem_android_icon, 0);
            this.f6803n = c(s0VarU.o(j.MenuItem_android_alphabeticShortcut));
            this.f6804o = s0VarU.k(j.MenuItem_alphabeticModifiers, 4096);
            this.f6805p = c(s0VarU.o(j.MenuItem_android_numericShortcut));
            this.f6806q = s0VarU.k(j.MenuItem_numericModifiers, 4096);
            int i7 = j.MenuItem_android_checkable;
            if (s0VarU.s(i7)) {
                this.f6807r = s0VarU.a(i7, false) ? 1 : 0;
            } else {
                this.f6807r = this.f6794e;
            }
            this.f6808s = s0VarU.a(j.MenuItem_android_checked, false);
            this.f6809t = s0VarU.a(j.MenuItem_android_visible, this.f6795f);
            this.f6810u = s0VarU.a(j.MenuItem_android_enabled, this.f6796g);
            this.f6811v = s0VarU.k(j.MenuItem_showAsAction, -1);
            this.f6815z = s0VarU.o(j.MenuItem_android_onClick);
            this.f6812w = s0VarU.n(j.MenuItem_actionLayout, 0);
            this.f6813x = s0VarU.o(j.MenuItem_actionViewClass);
            String strO = s0VarU.o(j.MenuItem_actionProviderClass);
            this.f6814y = strO;
            boolean z6 = strO != null;
            if (z6 && this.f6812w == 0 && this.f6813x == null) {
                this.A = (i0.b) e(strO, g.f6782f, g.this.f6784b);
            } else {
                if (z6) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = s0VarU.p(j.MenuItem_contentDescription);
            this.C = s0VarU.p(j.MenuItem_tooltipText);
            int i8 = j.MenuItem_iconTintMode;
            if (s0VarU.s(i8)) {
                this.E = b0.c(s0VarU.k(i8, -1), this.E);
            } else {
                this.E = null;
            }
            int i9 = j.MenuItem_iconTint;
            if (s0VarU.s(i9)) {
                this.D = s0VarU.c(i9);
            } else {
                this.D = null;
            }
            s0VarU.w();
            this.f6797h = false;
        }

        public void h() {
            this.f6791b = 0;
            this.f6792c = 0;
            this.f6793d = 0;
            this.f6794e = 0;
            this.f6795f = true;
            this.f6796g = true;
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f6781e = clsArr;
        f6782f = clsArr;
    }

    public g(Context context) {
        super(context);
        this.f6785c = context;
        Object[] objArr = {context};
        this.f6783a = objArr;
        this.f6784b = objArr;
    }

    private Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    private void c(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
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
        boolean z6 = false;
        boolean z7 = false;
        while (!z6) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z7 && name2.equals(str)) {
                        str = null;
                        z7 = false;
                    } else if (name2.equals("group")) {
                        bVar.h();
                    } else if (name2.equals("item")) {
                        if (!bVar.d()) {
                            i0.b bVar2 = bVar.A;
                            if (bVar2 == null || !bVar2.a()) {
                                bVar.a();
                            } else {
                                bVar.b();
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z6 = true;
                    }
                }
            } else if (!z7) {
                String name3 = xmlPullParser.getName();
                if (name3.equals("group")) {
                    bVar.f(attributeSet);
                } else if (name3.equals("item")) {
                    bVar.g(attributeSet);
                } else if (name3.equals("menu")) {
                    c(xmlPullParser, attributeSet, bVar.b());
                } else {
                    str = name3;
                    z7 = true;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    Object b() {
        if (this.f6786d == null) {
            this.f6786d = a(this.f6785c);
        }
        return this.f6786d;
    }

    @Override // android.view.MenuInflater
    public void inflate(int i7, Menu menu) {
        if (!(menu instanceof b0.a)) {
            super.inflate(i7, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f6785c.getResources().getLayout(i7);
                    c(layout, Xml.asAttributeSet(layout), menu);
                } catch (IOException e7) {
                    throw new InflateException("Error inflating menu XML", e7);
                }
            } catch (XmlPullParserException e8) {
                throw new InflateException("Error inflating menu XML", e8);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }
}
