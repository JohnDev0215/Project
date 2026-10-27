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
        private final C0015c[] f1215a;

        public b(C0015c[] c0015cArr) {
            this.f1215a = c0015cArr;
        }

        public C0015c[] a() {
            return this.f1215a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.content.c.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class C0015c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f1216a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1217b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1218c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f1219d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f1220e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f1221f;

        public C0015c(String str, int i2, boolean z, String str2, int i3, int i4) {
            this.f1216a = str;
            this.f1217b = i2;
            this.f1218c = z;
            this.f1219d = str2;
            this.f1220e = i3;
            this.f1221f = i4;
        }

        public String a() {
            return this.f1216a;
        }

        public int b() {
            return this.f1221f;
        }

        public int c() {
            return this.f1220e;
        }

        public String d() {
            return this.f1219d;
        }

        public int e() {
            return this.f1217b;
        }

        public boolean f() {
            return this.f1218c;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class d implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b.g.h.a f1222a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f1223b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f1224c;

        public d(b.g.h.a aVar, int i2, int i3) {
            this.f1222a = aVar;
            this.f1224c = i2;
            this.f1223b = i3;
        }

        public int a() {
            return this.f1224c;
        }

        public b.g.h.a b() {
            return this.f1222a;
        }

        public int c() {
            return this.f1223b;
        }
    }

    private static int a(TypedArray typedArray, int i2) {
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

    private static a d(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return e(xmlPullParser, resources);
        }
        g(xmlPullParser);
        return null;
    }

    private static a e(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), b.g.c.FontFamily);
        String string = typedArrayObtainAttributes.getString(b.g.c.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(b.g.c.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(b.g.c.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(b.g.c.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(b.g.c.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(b.g.c.FontFamily_fontProviderFetchTimeout, 500);
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
        return new b((C0015c[]) arrayList.toArray(new C0015c[arrayList.size()]));
    }

    private static C0015c f(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), b.g.c.FontFamilyFont);
        int i2 = b.g.c.FontFamilyFont_fontWeight;
        if (!typedArrayObtainAttributes.hasValue(i2)) {
            i2 = b.g.c.FontFamilyFont_android_fontWeight;
        }
        int i3 = typedArrayObtainAttributes.getInt(i2, 400);
        int i4 = b.g.c.FontFamilyFont_fontStyle;
        if (!typedArrayObtainAttributes.hasValue(i4)) {
            i4 = b.g.c.FontFamilyFont_android_fontStyle;
        }
        boolean z = 1 == typedArrayObtainAttributes.getInt(i4, 0);
        int i5 = b.g.c.FontFamilyFont_ttcIndex;
        if (!typedArrayObtainAttributes.hasValue(i5)) {
            i5 = b.g.c.FontFamilyFont_android_ttcIndex;
        }
        int i6 = b.g.c.FontFamilyFont_fontVariationSettings;
        if (!typedArrayObtainAttributes.hasValue(i6)) {
            i6 = b.g.c.FontFamilyFont_android_fontVariationSettings;
        }
        String string = typedArrayObtainAttributes.getString(i6);
        int i7 = typedArrayObtainAttributes.getInt(i5, 0);
        int i8 = b.g.c.FontFamilyFont_font;
        if (!typedArrayObtainAttributes.hasValue(i8)) {
            i8 = b.g.c.FontFamilyFont_android_font;
        }
        int resourceId = typedArrayObtainAttributes.getResourceId(i8, 0);
        String string2 = typedArrayObtainAttributes.getString(i8);
        typedArrayObtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            g(xmlPullParser);
        }
        return new C0015c(string2, i3, z, string, i7, resourceId);
    }

    private static void g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
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

    private static List<byte[]> h(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }
}
