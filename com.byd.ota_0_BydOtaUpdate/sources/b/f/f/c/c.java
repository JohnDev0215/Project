package b.f.f.c;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: FontResourcesParserCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public interface a {
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class b implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final C0021c[] f1615a;

        public b(C0021c[] c0021cArr) {
            this.f1615a = c0021cArr;
        }

        public C0021c[] a() {
            return this.f1615a;
        }
    }

    /* JADX INFO: renamed from: b.f.f.c.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class C0021c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1616a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1617b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1618c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1619d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1620e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1621f;

        public C0021c(String str, int i2, boolean z, String str2, int i3, int i4) {
            this.f1616a = str;
            this.f1617b = i2;
            this.f1618c = z;
            this.f1619d = str2;
            this.f1620e = i3;
            this.f1621f = i4;
        }

        public String a() {
            return this.f1616a;
        }

        public int b() {
            return this.f1621f;
        }

        public int c() {
            return this.f1620e;
        }

        public String d() {
            return this.f1619d;
        }

        public int e() {
            return this.f1617b;
        }

        public boolean f() {
            return this.f1618c;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class d implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.f.j.a f1622a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1624c;

        public d(b.f.j.a aVar, int i2, int i3) {
            this.f1622a = aVar;
            this.f1624c = i2;
            this.f1623b = i3;
        }

        public int a() {
            return this.f1624c;
        }

        public b.f.j.a b() {
            return this.f1622a;
        }

        public int c() {
            return this.f1623b;
        }
    }

    public static int a(TypedArray typedArray, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i2);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i2, typedValue);
        return typedValue.type;
    }

    public static a b(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return d(xmlPullParser, resources);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static List<List<byte[]>> c(Resources resources, int i2) {
        if (i2 == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i2);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(typedArrayObtainTypedArray, 0) == 1) {
                for (int i3 = 0; i3 < typedArrayObtainTypedArray.length(); i3++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i3, 0);
                    if (resourceId != 0) {
                        arrayList.add(h(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(h(resources.getStringArray(i2)));
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    public static a d(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return e(xmlPullParser, resources);
        }
        g(xmlPullParser);
        return null;
    }

    public static a e(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), b.f.d.f1554e);
        String string = typedArrayObtainAttributes.getString(b.f.d.f1555f);
        String string2 = typedArrayObtainAttributes.getString(b.f.d.j);
        String string3 = typedArrayObtainAttributes.getString(b.f.d.k);
        int resourceId = typedArrayObtainAttributes.getResourceId(b.f.d.f1556g, 0);
        int integer = typedArrayObtainAttributes.getInteger(b.f.d.f1557h, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(b.f.d.f1558i, 500);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                g(xmlPullParser);
            }
            return new d(new b.f.j.a(string, string2, string3, c(resources, resourceId)), integer, integer2);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("font")) {
                    arrayList.add(f(xmlPullParser, resources));
                } else {
                    g(xmlPullParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new b((C0021c[]) arrayList.toArray(new C0021c[arrayList.size()]));
    }

    public static C0021c f(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), b.f.d.l);
        int i2 = b.f.d.u;
        if (!typedArrayObtainAttributes.hasValue(i2)) {
            i2 = b.f.d.n;
        }
        int i3 = typedArrayObtainAttributes.getInt(i2, 400);
        int i4 = b.f.d.s;
        if (!typedArrayObtainAttributes.hasValue(i4)) {
            i4 = b.f.d.o;
        }
        boolean z = 1 == typedArrayObtainAttributes.getInt(i4, 0);
        int i5 = b.f.d.v;
        if (!typedArrayObtainAttributes.hasValue(i5)) {
            i5 = b.f.d.p;
        }
        int i6 = b.f.d.t;
        if (!typedArrayObtainAttributes.hasValue(i6)) {
            i6 = b.f.d.q;
        }
        String string = typedArrayObtainAttributes.getString(i6);
        int i7 = typedArrayObtainAttributes.getInt(i5, 0);
        int i8 = b.f.d.r;
        if (!typedArrayObtainAttributes.hasValue(i8)) {
            i8 = b.f.d.m;
        }
        int resourceId = typedArrayObtainAttributes.getResourceId(i8, 0);
        String string2 = typedArrayObtainAttributes.getString(i8);
        typedArrayObtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            g(xmlPullParser);
        }
        return new C0021c(string2, i3, z, string, i7, resourceId);
    }

    public static void g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i2 = 1;
        while (i2 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }

    public static List<byte[]> h(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }
}
