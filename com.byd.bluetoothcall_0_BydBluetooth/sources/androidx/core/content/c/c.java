package androidx.core.content.c;

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
        private final C0024c[] f1232a;

        public b(C0024c[] c0024cArr) {
            this.f1232a = c0024cArr;
        }

        public C0024c[] a() {
            return this.f1232a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.content.c.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class C0024c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f1233a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1234b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1235c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f1236d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f1237e;
        private int f;

        public C0024c(String str, int i, boolean z, String str2, int i2, int i3) {
            this.f1233a = str;
            this.f1234b = i;
            this.f1235c = z;
            this.f1236d = str2;
            this.f1237e = i2;
            this.f = i3;
        }

        public String a() {
            return this.f1233a;
        }

        public int b() {
            return this.f;
        }

        public int c() {
            return this.f1237e;
        }

        public String d() {
            return this.f1236d;
        }

        public int e() {
            return this.f1234b;
        }

        public boolean f() {
            return this.f1235c;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class d implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b.g.h.a f1238a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f1239b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f1240c;

        public d(b.g.h.a aVar, int i, int i2) {
            this.f1238a = aVar;
            this.f1240c = i;
            this.f1239b = i2;
        }

        public int a() {
            return this.f1240c;
        }

        public b.g.h.a b() {
            return this.f1238a;
        }

        public int c() {
            return this.f1239b;
        }
    }

    private static int a(TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i, typedValue);
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

    public static List<List<byte[]>> c(Resources resources, int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(typedArrayObtainTypedArray, 0) == 1) {
                for (int i2 = 0; i2 < typedArrayObtainTypedArray.length(); i2++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i2, 0);
                    if (resourceId != 0) {
                        arrayList.add(h(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(h(resources.getStringArray(i)));
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    private static a d(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return e(xmlPullParser, resources);
        }
        g(xmlPullParser);
        return null;
    }

    private static a e(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), b.g.c.f2179e);
        String string = typedArrayObtainAttributes.getString(b.g.c.f);
        String string2 = typedArrayObtainAttributes.getString(b.g.c.j);
        String string3 = typedArrayObtainAttributes.getString(b.g.c.k);
        int resourceId = typedArrayObtainAttributes.getResourceId(b.g.c.g, 0);
        int integer = typedArrayObtainAttributes.getInteger(b.g.c.h, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(b.g.c.i, 500);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                g(xmlPullParser);
            }
            return new d(new b.g.h.a(string, string2, string3, c(resources, resourceId)), integer, integer2);
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
        return new b((C0024c[]) arrayList.toArray(new C0024c[arrayList.size()]));
    }

    private static C0024c f(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), b.g.c.l);
        int i = b.g.c.u;
        if (!typedArrayObtainAttributes.hasValue(i)) {
            i = b.g.c.n;
        }
        int i2 = typedArrayObtainAttributes.getInt(i, 400);
        int i3 = b.g.c.s;
        if (!typedArrayObtainAttributes.hasValue(i3)) {
            i3 = b.g.c.o;
        }
        boolean z = 1 == typedArrayObtainAttributes.getInt(i3, 0);
        int i4 = b.g.c.v;
        if (!typedArrayObtainAttributes.hasValue(i4)) {
            i4 = b.g.c.p;
        }
        int i5 = b.g.c.t;
        if (!typedArrayObtainAttributes.hasValue(i5)) {
            i5 = b.g.c.q;
        }
        String string = typedArrayObtainAttributes.getString(i5);
        int i6 = typedArrayObtainAttributes.getInt(i4, 0);
        int i7 = b.g.c.r;
        if (!typedArrayObtainAttributes.hasValue(i7)) {
            i7 = b.g.c.m;
        }
        int resourceId = typedArrayObtainAttributes.getResourceId(i7, 0);
        String string2 = typedArrayObtainAttributes.getString(i7);
        typedArrayObtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            g(xmlPullParser);
        }
        return new C0024c(string2, i2, z, string, i6, resourceId);
    }

    private static void g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = 1;
        while (i > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }

    private static List<byte[]> h(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }
}
