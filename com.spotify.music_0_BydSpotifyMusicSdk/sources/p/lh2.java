package p;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SubMenu;
import com.spotify.playbacknative.AudioDriver;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class lh2 extends MenuInflater {
    public static final Class[] e;
    public static final Class[] f;
    public final Object[] a;
    public final Object[] b;
    public final Context c;
    public Object d;

    static {
        Class[] clsArr = {Context.class};
        e = clsArr;
        f = clsArr;
    }

    public lh2(Context context) {
        super(context);
        this.c = context;
        Object[] objArr = {context};
        this.a = objArr;
        this.b = objArr;
    }

    public static Object a(Context context) {
        return (!(context instanceof Activity) && (context instanceof ContextWrapper)) ? a(((ContextWrapper) context).getBaseContext()) : context;
    }

    public final void b(XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        int i;
        ColorStateList colorStateList;
        kh2 kh2Var = new kh2(this, menu);
        int eventType = xmlResourceParser.getEventType();
        while (true) {
            i = 2;
            if (eventType == 2) {
                String name = xmlResourceParser.getName();
                if (!name.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got ".concat(name));
                }
                eventType = xmlResourceParser.next();
            } else {
                eventType = xmlResourceParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z = false;
        boolean z2 = false;
        String str = null;
        while (!z) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != i) {
                if (eventType != 3) {
                    z = z;
                } else {
                    String name2 = xmlResourceParser.getName();
                    if (z2 && name2.equals(str)) {
                        z2 = false;
                        str = null;
                    } else {
                        if (name2.equals("group")) {
                            kh2Var.b = 0;
                            kh2Var.c = 0;
                            kh2Var.d = 0;
                            kh2Var.e = 0;
                            kh2Var.f = true;
                            kh2Var.g = true;
                        } else if (name2.equals("item")) {
                            if (!kh2Var.h) {
                                yi1 yi1Var = kh2Var.z;
                                if (yi1Var == null || !yi1Var.a.hasSubMenu()) {
                                    kh2Var.h = true;
                                    kh2Var.b(kh2Var.a.add(kh2Var.b, kh2Var.i, kh2Var.j, kh2Var.k));
                                } else {
                                    kh2Var.h = true;
                                    kh2Var.b(kh2Var.a.addSubMenu(kh2Var.b, kh2Var.i, kh2Var.j, kh2Var.k).getItem());
                                }
                            }
                        } else if (name2.equals("menu")) {
                            z = true;
                        }
                        z = z;
                    }
                }
                eventType = xmlResourceParser.next();
                i = 2;
                z = z;
                z2 = z2;
            } else {
                if (!z2) {
                    String name3 = xmlResourceParser.getName();
                    boolean zEquals = name3.equals("group");
                    lh2 lh2Var = kh2Var.E;
                    if (zEquals) {
                        TypedArray typedArrayObtainStyledAttributes = lh2Var.c.obtainStyledAttributes(attributeSet, yz1.f210p);
                        kh2Var.b = typedArrayObtainStyledAttributes.getResourceId(1, 0);
                        kh2Var.c = typedArrayObtainStyledAttributes.getInt(3, 0);
                        kh2Var.d = typedArrayObtainStyledAttributes.getInt(4, 0);
                        kh2Var.e = typedArrayObtainStyledAttributes.getInt(5, 0);
                        kh2Var.f = typedArrayObtainStyledAttributes.getBoolean(2, true);
                        kh2Var.g = typedArrayObtainStyledAttributes.getBoolean(0, true);
                        typedArrayObtainStyledAttributes.recycle();
                    } else {
                        if (name3.equals("item")) {
                            Context context = lh2Var.c;
                            v4 v4Var = new v4(context, context.obtainStyledAttributes(attributeSet, yz1.q));
                            kh2Var.i = v4Var.y(2, 0);
                            kh2Var.j = (v4Var.w(5, kh2Var.c) & (-65536)) | (v4Var.w(6, kh2Var.d) & AudioDriver.SPOTIFY_MAX_VOLUME);
                            kh2Var.k = v4Var.A(7);
                            kh2Var.l = v4Var.A(8);
                            kh2Var.m = v4Var.y(0, 0);
                            String strZ = v4Var.z(9);
                            kh2Var.n = strZ == null ? (char) 0 : strZ.charAt(0);
                            kh2Var.o = v4Var.w(16, 4096);
                            String strZ2 = v4Var.z(10);
                            kh2Var.f143p = strZ2 == null ? (char) 0 : strZ2.charAt(0);
                            kh2Var.q = v4Var.w(20, 4096);
                            if (v4Var.G(11)) {
                                kh2Var.r = v4Var.p(11, false) ? 1 : 0;
                            } else {
                                kh2Var.r = kh2Var.e;
                            }
                            kh2Var.s = v4Var.p(3, false);
                            kh2Var.t = v4Var.p(4, kh2Var.f);
                            kh2Var.u = v4Var.p(1, kh2Var.g);
                            kh2Var.v = v4Var.w(21, -1);
                            kh2Var.y = v4Var.z(12);
                            kh2Var.w = v4Var.y(13, 0);
                            kh2Var.x = v4Var.z(15);
                            String strZ3 = v4Var.z(14);
                            if (strZ3 != null && kh2Var.w == 0 && kh2Var.x == null) {
                                kh2Var.z = (yi1) kh2Var.a(strZ3, f, lh2Var.b);
                            } else {
                                kh2Var.z = null;
                            }
                            kh2Var.A = v4Var.A(17);
                            kh2Var.B = v4Var.A(22);
                            if (v4Var.G(19)) {
                                kh2Var.D = cb0.b(v4Var.w(19, -1), kh2Var.D);
                                colorStateList = null;
                            } else {
                                colorStateList = null;
                                kh2Var.D = null;
                            }
                            if (v4Var.G(18)) {
                                kh2Var.C = v4Var.q(18);
                            } else {
                                kh2Var.C = colorStateList;
                            }
                            v4Var.L();
                            kh2Var.h = false;
                        } else if (name3.equals("menu")) {
                            kh2Var.h = true;
                            SubMenu subMenuAddSubMenu = kh2Var.a.addSubMenu(kh2Var.b, kh2Var.i, kh2Var.j, kh2Var.k);
                            kh2Var.b(subMenuAddSubMenu.getItem());
                            b(xmlResourceParser, attributeSet, subMenuAddSubMenu);
                        } else {
                            str = name3;
                            z2 = true;
                        }
                        eventType = xmlResourceParser.next();
                        i = 2;
                        z = z;
                        z2 = z2;
                    }
                }
                z = z;
            }
            eventType = xmlResourceParser.next();
            i = 2;
            z = z;
            z2 = z2;
        }
    }

    @Override // android.view.MenuInflater
    public final void inflate(int i, Menu menu) {
        if (!(menu instanceof ih2)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.c.getResources().getLayout(i);
                    b(layout, Xml.asAttributeSet(layout), menu);
                    layout.close();
                } catch (XmlPullParserException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (IOException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } catch (Throwable th) {
            if (layout != null) {
                layout.close();
            }
            throw th;
        }
    }
}
