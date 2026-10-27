package c0;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.R$styleable;
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
        public final C0006c[] f1791a;

        public b(C0006c[] c0006cArr) {
            this.f1791a = c0006cArr;
        }

        public C0006c[] a() {
            return this.f1791a;
        }
    }

    /* JADX INFO: renamed from: c0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class C0006c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1792a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1793b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1794c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1795d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1796e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1797f;

        public C0006c(String str, int i3, boolean z3, String str2, int i4, int i5) {
            this.f1792a = str;
            this.f1793b = i3;
            this.f1794c = z3;
            this.f1795d = str2;
            this.f1796e = i4;
            this.f1797f = i5;
        }

        public String a() {
            return this.f1792a;
        }

        public int b() {
            return this.f1797f;
        }

        public int c() {
            return this.f1796e;
        }

        public String d() {
            return this.f1795d;
        }

        public int e() {
            return this.f1793b;
        }

        public boolean f() {
            return this.f1794c;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class d implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final i0.a f1798a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1799b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1800c;

        public d(i0.a aVar, int i3, int i4) {
            this.f1798a = aVar;
            this.f1800c = i3;
            this.f1799b = i4;
        }

        public int a() {
            return this.f1800c;
        }

        public i0.a b() {
            return this.f1798a;
        }

        public int c() {
            return this.f1799b;
        }
    }

    public static int a(TypedArray typedArray, int i3) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i3);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i3, typedValue);
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

    public static List<List<byte[]>> c(Resources resources, int i3) {
        if (i3 == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i3);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(typedArrayObtainTypedArray, 0) == 1) {
                for (int i4 = 0; i4 < typedArrayObtainTypedArray.length(); i4++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i4, 0);
                    if (resourceId != 0) {
                        arrayList.add(h(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(h(resources.getStringArray(i3)));
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
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.FontFamily);
        String string = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(R$styleable.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchTimeout, 500);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                g(xmlPullParser);
            }
            return new d(new i0.a(string, string2, string3, c(resources, resourceId)), integer, integer2);
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
        return new b((C0006c[]) arrayList.toArray(new C0006c[arrayList.size()]));
    }

    public static C0006c f(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.FontFamilyFont);
        int i3 = R$styleable.FontFamilyFont_fontWeight;
        if (!typedArrayObtainAttributes.hasValue(i3)) {
            i3 = R$styleable.FontFamilyFont_android_fontWeight;
        }
        int i4 = typedArrayObtainAttributes.getInt(i3, 400);
        int i5 = R$styleable.FontFamilyFont_fontStyle;
        if (!typedArrayObtainAttributes.hasValue(i5)) {
            i5 = R$styleable.FontFamilyFont_android_fontStyle;
        }
        boolean z3 = 1 == typedArrayObtainAttributes.getInt(i5, 0);
        int i6 = R$styleable.FontFamilyFont_ttcIndex;
        if (!typedArrayObtainAttributes.hasValue(i6)) {
            i6 = R$styleable.FontFamilyFont_android_ttcIndex;
        }
        int i7 = R$styleable.FontFamilyFont_fontVariationSettings;
        if (!typedArrayObtainAttributes.hasValue(i7)) {
            i7 = R$styleable.FontFamilyFont_android_fontVariationSettings;
        }
        String string = typedArrayObtainAttributes.getString(i7);
        int i8 = typedArrayObtainAttributes.getInt(i6, 0);
        int i9 = R$styleable.FontFamilyFont_font;
        if (!typedArrayObtainAttributes.hasValue(i9)) {
            i9 = R$styleable.FontFamilyFont_android_font;
        }
        int resourceId = typedArrayObtainAttributes.getResourceId(i9, 0);
        String string2 = typedArrayObtainAttributes.getString(i9);
        typedArrayObtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            g(xmlPullParser);
        }
        return new C0006c(string2, i4, z3, string, i8, resourceId);
    }

    public static void g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i3 = 1;
        while (i3 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i3++;
            } else if (next == 3) {
                i3--;
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
