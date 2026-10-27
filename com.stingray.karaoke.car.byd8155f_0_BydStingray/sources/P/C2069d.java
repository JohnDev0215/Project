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
import h6.f;
import h6.n;
import java.io.IOException;
import n.AbstractC1850a;
import org.xmlpull.v1.XmlPullParserException;
import q.MenuC2151h;
import r.AbstractC2225M;

/* JADX INFO: renamed from: p.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2069d extends MenuInflater {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Class[] f20632e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Class[] f20633f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f20634a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f20635b;
    public final Context c;
    public Object d;

    static {
        Class[] clsArr = {Context.class};
        f20632e = clsArr;
        f20633f = clsArr;
    }

    public C2069d(Context context) {
        super(context);
        this.c = context;
        Object[] objArr = {context};
        this.f20634a = objArr;
        this.f20635b = objArr;
    }

    public static Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [p.d] */
    /* JADX WARN: Type inference failed for: r3v15, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v58 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public final void b(XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        ?? r42;
        int i7;
        ?? r62;
        ColorStateList colorStateList;
        int resourceId;
        C2068c c2068c = new C2068c(this, menu);
        int eventType = xmlResourceParser.getEventType();
        while (true) {
            r42 = 1;
            i7 = 2;
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
        boolean z6 = false;
        boolean z10 = false;
        String str = null;
        while (!z6) {
            if (eventType == r42) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != i7) {
                if (eventType != 3) {
                    r62 = r42;
                    z6 = z6;
                } else {
                    String name2 = xmlResourceParser.getName();
                    if (z10 && name2.equals(str)) {
                        r62 = r42;
                        z10 = false;
                        str = null;
                    } else {
                        if (name2.equals("group")) {
                            c2068c.f20611b = 0;
                            c2068c.c = 0;
                            c2068c.d = 0;
                            c2068c.f20612e = 0;
                            c2068c.f20613f = r42;
                            c2068c.f20614g = r42;
                        } else if (name2.equals("item")) {
                            if (!c2068c.f20615h) {
                                c2068c.f20615h = r42;
                                c2068c.b(c2068c.f20610a.add(c2068c.f20611b, c2068c.f20616i, c2068c.f20617j, c2068c.f20618k));
                            }
                        } else if (name2.equals("menu")) {
                            ?? r63 = r42;
                            z6 = r63 == true ? 1 : 0;
                            r62 = r63;
                        }
                        r62 = r42;
                        z6 = z6;
                    }
                }
                eventType = xmlResourceParser.next();
                r42 = r62;
                i7 = 2;
                z6 = z6;
                z10 = z10;
            } else {
                if (!z10) {
                    String name3 = xmlResourceParser.getName();
                    boolean zEquals = name3.equals("group");
                    C2069d c2069d = c2068c.f20609D;
                    if (zEquals) {
                        ?? ObtainStyledAttributes = c2069d.c.obtainStyledAttributes(attributeSet, AbstractC1850a.f19361m);
                        c2068c.f20611b = ObtainStyledAttributes.getResourceId(r42, 0);
                        c2068c.c = ObtainStyledAttributes.getInt(3, 0);
                        c2068c.d = ObtainStyledAttributes.getInt(4, 0);
                        c2068c.f20612e = ObtainStyledAttributes.getInt(5, 0);
                        c2068c.f20613f = ObtainStyledAttributes.getBoolean(2, r42);
                        c2068c.f20614g = ObtainStyledAttributes.getBoolean(0, r42);
                        ObtainStyledAttributes.recycle();
                    } else {
                        if (name3.equals("item")) {
                            Context context = c2069d.c;
                            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC1850a.f19362n);
                            c2068c.f20616i = typedArrayObtainStyledAttributes.getResourceId(2, 0);
                            c2068c.f20617j = (typedArrayObtainStyledAttributes.getInt(5, c2068c.c) & (-65536)) | (typedArrayObtainStyledAttributes.getInt(6, c2068c.d) & 65535);
                            c2068c.f20618k = typedArrayObtainStyledAttributes.getText(7);
                            c2068c.l = typedArrayObtainStyledAttributes.getText(8);
                            c2068c.f20619m = typedArrayObtainStyledAttributes.getResourceId(0, 0);
                            String string = typedArrayObtainStyledAttributes.getString(9);
                            c2068c.f20620n = string == null ? (char) 0 : string.charAt(0);
                            c2068c.o = typedArrayObtainStyledAttributes.getInt(16, 4096);
                            String string2 = typedArrayObtainStyledAttributes.getString(10);
                            c2068c.f20621p = string2 == null ? (char) 0 : string2.charAt(0);
                            c2068c.f20622q = typedArrayObtainStyledAttributes.getInt(20, 4096);
                            if (typedArrayObtainStyledAttributes.hasValue(11)) {
                                c2068c.f20623r = typedArrayObtainStyledAttributes.getBoolean(11, false) ? 1 : 0;
                            } else {
                                c2068c.f20623r = c2068c.f20612e;
                            }
                            c2068c.f20624s = typedArrayObtainStyledAttributes.getBoolean(3, false);
                            c2068c.f20625t = typedArrayObtainStyledAttributes.getBoolean(4, c2068c.f20613f);
                            c2068c.f20626u = typedArrayObtainStyledAttributes.getBoolean(1, c2068c.f20614g);
                            c2068c.f20627v = typedArrayObtainStyledAttributes.getInt(21, -1);
                            c2068c.f20630y = typedArrayObtainStyledAttributes.getString(12);
                            c2068c.f20628w = typedArrayObtainStyledAttributes.getResourceId(13, 0);
                            c2068c.f20629x = typedArrayObtainStyledAttributes.getString(15);
                            String string3 = typedArrayObtainStyledAttributes.getString(14);
                            boolean z11 = string3 != null;
                            if (z11 && c2068c.f20628w == 0 && c2068c.f20629x == null) {
                                if (c2068c.a(string3, f20633f, c2069d.f20635b) != null) {
                                    throw new ClassCastException();
                                }
                            } else if (z11) {
                                n.t("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                            }
                            c2068c.f20631z = typedArrayObtainStyledAttributes.getText(17);
                            c2068c.f20607A = typedArrayObtainStyledAttributes.getText(22);
                            if (typedArrayObtainStyledAttributes.hasValue(19)) {
                                c2068c.f20608C = AbstractC2225M.b(typedArrayObtainStyledAttributes.getInt(19, -1), c2068c.f20608C);
                            } else {
                                c2068c.f20608C = null;
                            }
                            if (typedArrayObtainStyledAttributes.hasValue(18)) {
                                if (!typedArrayObtainStyledAttributes.hasValue(18) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(18, 0)) == 0 || (colorStateList = f.h(context, resourceId)) == null) {
                                    colorStateList = typedArrayObtainStyledAttributes.getColorStateList(18);
                                }
                                c2068c.B = colorStateList;
                            } else {
                                c2068c.B = null;
                            }
                            typedArrayObtainStyledAttributes.recycle();
                            c2068c.f20615h = false;
                            r62 = 1;
                        } else if (name3.equals("menu")) {
                            r62 = 1;
                            c2068c.f20615h = true;
                            SubMenu subMenuAddSubMenu = c2068c.f20610a.addSubMenu(c2068c.f20611b, c2068c.f20616i, c2068c.f20617j, c2068c.f20618k);
                            c2068c.b(subMenuAddSubMenu.getItem());
                            b(xmlResourceParser, attributeSet, subMenuAddSubMenu);
                        } else {
                            r62 = 1;
                            str = name3;
                            z10 = true;
                        }
                        eventType = xmlResourceParser.next();
                        r42 = r62;
                        i7 = 2;
                        z6 = z6;
                        z10 = z10;
                    }
                }
                r62 = r42;
                z6 = z6;
            }
            eventType = xmlResourceParser.next();
            r42 = r62;
            i7 = 2;
            z6 = z6;
            z10 = z10;
        }
    }

    @Override // android.view.MenuInflater
    public final void inflate(int i7, Menu menu) {
        if (!(menu instanceof MenuC2151h)) {
            super.inflate(i7, menu);
            return;
        }
        XmlResourceParser layout = null;
        boolean z6 = false;
        try {
            try {
                layout = this.c.getResources().getLayout(i7);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(layout);
                if (menu instanceof MenuC2151h) {
                    MenuC2151h menuC2151h = (MenuC2151h) menu;
                    if (!menuC2151h.f21059n) {
                        menuC2151h.s();
                        z6 = true;
                    }
                }
                b(layout, attributeSetAsAttributeSet, menu);
                if (z6) {
                    ((MenuC2151h) menu).r();
                }
                layout.close();
            } catch (IOException e10) {
                throw new InflateException("Error inflating menu XML", e10);
            } catch (XmlPullParserException e11) {
                throw new InflateException("Error inflating menu XML", e11);
            }
        } catch (Throwable th) {
            if (z6) {
                ((MenuC2151h) menu).r();
            }
            if (layout != null) {
                layout.close();
            }
            throw th;
        }
    }
}
