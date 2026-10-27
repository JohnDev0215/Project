package y;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Base64;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: FontResourcesParserCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: y.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public interface InterfaceC0173a {
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class b implements InterfaceC0173a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c[] f9869a;

        public b(c[] cVarArr) {
            this.f9869a = cVarArr;
        }

        public c[] a() {
            return this.f9869a;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f9870a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f9871b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f9872c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f9873d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f9874e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f9875f;

        public c(String str, int i7, boolean z6, String str2, int i8, int i9) {
            this.f9870a = str;
            this.f9871b = i7;
            this.f9872c = z6;
            this.f9873d = str2;
            this.f9874e = i8;
            this.f9875f = i9;
        }

        public String a() {
            return this.f9870a;
        }

        public int b() {
            return this.f9875f;
        }

        public int c() {
            return this.f9874e;
        }

        public String d() {
            return this.f9873d;
        }

        public int e() {
            return this.f9871b;
        }

        public boolean f() {
            return this.f9872c;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class d implements InterfaceC0173a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e0.d f9876a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f9877b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f9878c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f9879d;

        public d(e0.d dVar, int i7, int i8, String str) {
            this.f9876a = dVar;
            this.f9878c = i7;
            this.f9877b = i8;
            this.f9879d = str;
        }

        public int a() {
            return this.f9878c;
        }

        public e0.d b() {
            return this.f9876a;
        }

        public String c() {
            return this.f9879d;
        }

        public int d() {
            return this.f9877b;
        }
    }

    private static int a(TypedArray typedArray, int i7) {
        return typedArray.getType(i7);
    }

    public static InterfaceC0173a b(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
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

    public static List<List<byte[]>> c(Resources resources, int i7) {
        if (i7 == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i7);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(typedArrayObtainTypedArray, 0) == 1) {
                for (int i8 = 0; i8 < typedArrayObtainTypedArray.length(); i8++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i8, 0);
                    if (resourceId != 0) {
                        arrayList.add(h(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(h(resources.getStringArray(i7)));
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    private static InterfaceC0173a d(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return e(xmlPullParser, resources);
        }
        g(xmlPullParser);
        return null;
    }

    private static InterfaceC0173a e(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), v.b.FontFamily);
        String string = typedArrayObtainAttributes.getString(v.b.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(v.b.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(v.b.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(v.b.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(v.b.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(v.b.FontFamily_fontProviderFetchTimeout, 500);
        String string4 = typedArrayObtainAttributes.getString(v.b.FontFamily_fontProviderSystemFontFamily);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                g(xmlPullParser);
            }
            return new d(new e0.d(string, string2, string3, c(resources, resourceId)), integer, integer2, string4);
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
        return new b((c[]) arrayList.toArray(new c[arrayList.size()]));
    }

    private static c f(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), v.b.FontFamilyFont);
        int i7 = v.b.FontFamilyFont_fontWeight;
        if (!typedArrayObtainAttributes.hasValue(i7)) {
            i7 = v.b.FontFamilyFont_android_fontWeight;
        }
        int i8 = typedArrayObtainAttributes.getInt(i7, 400);
        int i9 = v.b.FontFamilyFont_fontStyle;
        if (!typedArrayObtainAttributes.hasValue(i9)) {
            i9 = v.b.FontFamilyFont_android_fontStyle;
        }
        boolean z6 = 1 == typedArrayObtainAttributes.getInt(i9, 0);
        int i10 = v.b.FontFamilyFont_ttcIndex;
        if (!typedArrayObtainAttributes.hasValue(i10)) {
            i10 = v.b.FontFamilyFont_android_ttcIndex;
        }
        int i11 = v.b.FontFamilyFont_fontVariationSettings;
        if (!typedArrayObtainAttributes.hasValue(i11)) {
            i11 = v.b.FontFamilyFont_android_fontVariationSettings;
        }
        String string = typedArrayObtainAttributes.getString(i11);
        int i12 = typedArrayObtainAttributes.getInt(i10, 0);
        int i13 = v.b.FontFamilyFont_font;
        if (!typedArrayObtainAttributes.hasValue(i13)) {
            i13 = v.b.FontFamilyFont_android_font;
        }
        int resourceId = typedArrayObtainAttributes.getResourceId(i13, 0);
        String string2 = typedArrayObtainAttributes.getString(i13);
        typedArrayObtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            g(xmlPullParser);
        }
        return new c(string2, i8, z6, string, i12, resourceId);
    }

    private static void g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i7 = 1;
        while (i7 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i7++;
            } else if (next == 3) {
                i7--;
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
