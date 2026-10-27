package b.h.e.c;

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
        public final C0033c[] f2411a;

        public b(C0033c[] c0033cArr) {
            this.f2411a = c0033cArr;
        }

        public C0033c[] a() {
            return this.f2411a;
        }
    }

    /* JADX INFO: renamed from: b.h.e.c.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class C0033c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f2412a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2413b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2414c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f2415d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2416e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2417f;

        public C0033c(String str, int i2, boolean z, String str2, int i3, int i4) {
            this.f2412a = str;
            this.f2413b = i2;
            this.f2414c = z;
            this.f2415d = str2;
            this.f2416e = i3;
            this.f2417f = i4;
        }

        public String a() {
            return this.f2412a;
        }

        public int b() {
            return this.f2417f;
        }

        public int c() {
            return this.f2416e;
        }

        public String d() {
            return this.f2415d;
        }

        public int e() {
            return this.f2413b;
        }

        public boolean f() {
            return this.f2414c;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class d implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.h.j.a f2418a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f2419b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2420c;

        public d(b.h.j.a aVar, int i2, int i3) {
            this.f2418a = aVar;
            this.f2420c = i2;
            this.f2419b = i3;
        }

        public int a() {
            return this.f2420c;
        }

        public b.h.j.a b() {
            return this.f2418a;
        }

        public int c() {
            return this.f2419b;
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
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), b.h.c.FontFamily);
        String string = typedArrayObtainAttributes.getString(b.h.c.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(b.h.c.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(b.h.c.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(b.h.c.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(b.h.c.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(b.h.c.FontFamily_fontProviderFetchTimeout, 500);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                g(xmlPullParser);
            }
            return new d(new b.h.j.a(string, string2, string3, c(resources, resourceId)), integer, integer2);
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
        return new b((C0033c[]) arrayList.toArray(new C0033c[arrayList.size()]));
    }

    public static C0033c f(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), b.h.c.FontFamilyFont);
        int i2 = b.h.c.FontFamilyFont_fontWeight;
        if (!typedArrayObtainAttributes.hasValue(i2)) {
            i2 = b.h.c.FontFamilyFont_android_fontWeight;
        }
        int i3 = typedArrayObtainAttributes.getInt(i2, 400);
        int i4 = b.h.c.FontFamilyFont_fontStyle;
        if (!typedArrayObtainAttributes.hasValue(i4)) {
            i4 = b.h.c.FontFamilyFont_android_fontStyle;
        }
        boolean z = 1 == typedArrayObtainAttributes.getInt(i4, 0);
        int i5 = b.h.c.FontFamilyFont_ttcIndex;
        if (!typedArrayObtainAttributes.hasValue(i5)) {
            i5 = b.h.c.FontFamilyFont_android_ttcIndex;
        }
        int i6 = b.h.c.FontFamilyFont_fontVariationSettings;
        if (!typedArrayObtainAttributes.hasValue(i6)) {
            i6 = b.h.c.FontFamilyFont_android_fontVariationSettings;
        }
        String string = typedArrayObtainAttributes.getString(i6);
        int i7 = typedArrayObtainAttributes.getInt(i5, 0);
        int i8 = b.h.c.FontFamilyFont_font;
        if (!typedArrayObtainAttributes.hasValue(i8)) {
            i8 = b.h.c.FontFamilyFont_android_font;
        }
        int resourceId = typedArrayObtainAttributes.getResourceId(i8, 0);
        String string2 = typedArrayObtainAttributes.getString(i8);
        typedArrayObtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            g(xmlPullParser);
        }
        return new C0033c(string2, i3, z, string, i7, resourceId);
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
