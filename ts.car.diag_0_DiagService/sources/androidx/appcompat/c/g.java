package androidx.appcompat.c;

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
import androidx.annotation.LayoutRes;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.C;
import androidx.appcompat.widget.U;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class g extends MenuInflater {
    static final Class<?>[] e;
    static final Class<?>[] f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object[] f175a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object[] f176b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Context f177c;
    private Object d;

    /* JADX INFO: compiled from: caradapter-bug */
    private static class a implements MenuItem.OnMenuItemClickListener {
        private static final Class<?>[] e = {MenuItem.class};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Object f178c;
        private Method d;

        public a(Object obj, String str) {
            this.f178c = obj;
            Class<?> cls = obj.getClass();
            try {
                this.d = cls.getMethod(str, e);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.d.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.d.invoke(this.f178c, menuItem)).booleanValue();
                }
                this.d.invoke(this.f178c, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class b {
        androidx.core.view.b A;
        private CharSequence B;
        private CharSequence C;
        private ColorStateList D = null;
        private PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Menu f179a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f180b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f181c;
        private int d;
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
            this.f179a = menu;
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
                Constructor<?> constructor = Class.forName(str, false, g.this.f177c.getClassLoader()).getConstructor(clsArr);
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
                if (g.this.f177c.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.this.b(), this.z));
            }
            if (this.r >= 2) {
                if (menuItem instanceof androidx.appcompat.view.menu.h) {
                    ((androidx.appcompat.view.menu.h) menuItem).r(true);
                } else if (menuItem instanceof androidx.appcompat.view.menu.i) {
                    ((androidx.appcompat.view.menu.i) menuItem).h(true);
                }
            }
            String str = this.x;
            if (str != null) {
                menuItem.setActionView((View) e(str, g.e, g.this.f175a));
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
            androidx.core.view.b bVar = this.A;
            if (bVar != null) {
                androidx.core.view.h.a(menuItem, bVar);
            }
            androidx.core.view.h.c(menuItem, this.B);
            androidx.core.view.h.g(menuItem, this.C);
            androidx.core.view.h.b(menuItem, this.n, this.o);
            androidx.core.view.h.f(menuItem, this.p, this.q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                androidx.core.view.h.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                androidx.core.view.h.d(menuItem, colorStateList);
            }
        }

        public void a() {
            this.h = true;
            i(this.f179a.add(this.f180b, this.i, this.j, this.k));
        }

        public SubMenu b() {
            this.h = true;
            SubMenu subMenuAddSubMenu = this.f179a.addSubMenu(this.f180b, this.i, this.j, this.k);
            i(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public boolean d() {
            return this.h;
        }

        public void f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.f177c.obtainStyledAttributes(attributeSet, R$styleable.MenuGroup);
            this.f180b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MenuGroup_android_id, 0);
            this.f181c = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_menuCategory, 0);
            this.d = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_orderInCategory, 0);
            this.e = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_checkableBehavior, 0);
            this.f = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_visible, true);
            this.g = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            U uT = U.t(g.this.f177c, attributeSet, R$styleable.MenuItem);
            this.i = uT.m(R$styleable.MenuItem_android_id, 0);
            this.j = (uT.j(R$styleable.MenuItem_android_menuCategory, this.f181c) & (-65536)) | (uT.j(R$styleable.MenuItem_android_orderInCategory, this.d) & 65535);
            this.k = uT.o(R$styleable.MenuItem_android_title);
            this.l = uT.o(R$styleable.MenuItem_android_titleCondensed);
            this.m = uT.m(R$styleable.MenuItem_android_icon, 0);
            this.n = c(uT.n(R$styleable.MenuItem_android_alphabeticShortcut));
            this.o = uT.j(R$styleable.MenuItem_alphabeticModifiers, 4096);
            this.p = c(uT.n(R$styleable.MenuItem_android_numericShortcut));
            this.q = uT.j(R$styleable.MenuItem_numericModifiers, 4096);
            if (uT.r(R$styleable.MenuItem_android_checkable)) {
                this.r = uT.a(R$styleable.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.r = this.e;
            }
            this.s = uT.a(R$styleable.MenuItem_android_checked, false);
            this.t = uT.a(R$styleable.MenuItem_android_visible, this.f);
            this.u = uT.a(R$styleable.MenuItem_android_enabled, this.g);
            this.v = uT.j(R$styleable.MenuItem_showAsAction, -1);
            this.z = uT.n(R$styleable.MenuItem_android_onClick);
            this.w = uT.m(R$styleable.MenuItem_actionLayout, 0);
            this.x = uT.n(R$styleable.MenuItem_actionViewClass);
            String strN = uT.n(R$styleable.MenuItem_actionProviderClass);
            this.y = strN;
            boolean z = strN != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (androidx.core.view.b) e(this.y, g.f, g.this.f176b);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = uT.o(R$styleable.MenuItem_contentDescription);
            this.C = uT.o(R$styleable.MenuItem_tooltipText);
            if (uT.r(R$styleable.MenuItem_iconTintMode)) {
                this.E = C.e(uT.j(R$styleable.MenuItem_iconTintMode, -1), this.E);
            } else {
                this.E = null;
            }
            if (uT.r(R$styleable.MenuItem_iconTint)) {
                this.D = uT.c(R$styleable.MenuItem_iconTint);
            } else {
                this.D = null;
            }
            uT.v();
            this.h = false;
        }

        public void h() {
            this.f180b = 0;
            this.f181c = 0;
            this.d = 0;
            this.e = 0;
            this.f = true;
            this.g = true;
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        e = clsArr;
        f = clsArr;
    }

    public g(Context context) {
        super(context);
        this.f177c = context;
        Object[] objArr = {context};
        this.f175a = objArr;
        this.f176b = objArr;
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
                            androidx.core.view.b bVar2 = bVar.A;
                            if (bVar2 == null || !bVar2.b()) {
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

    Object b() {
        if (this.d == null) {
            this.d = a(this.f177c);
        }
        return this.d;
    }

    @Override // android.view.MenuInflater
    public void inflate(@LayoutRes int i, Menu menu) {
        if (!(menu instanceof androidx.core.b.a.a)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f177c.getResources().getLayout(i);
                    c(layout, Xml.asAttributeSet(layout), menu);
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
