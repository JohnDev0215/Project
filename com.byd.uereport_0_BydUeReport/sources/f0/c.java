package f0;

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
        public final C0036c[] f4808a;

        public b(C0036c[] c0036cArr) {
            this.f4808a = c0036cArr;
        }

        public C0036c[] a() {
            return this.f4808a;
        }
    }

    /* JADX INFO: renamed from: f0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class C0036c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f4809a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4810b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4811c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f4812d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4813e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f4814f;

        public C0036c(String str, int i8, boolean z7, String str2, int i9, int i10) {
            this.f4809a = str;
            this.f4810b = i8;
            this.f4811c = z7;
            this.f4812d = str2;
            this.f4813e = i9;
            this.f4814f = i10;
        }

        public String a() {
            return this.f4809a;
        }

        public int b() {
            return this.f4814f;
        }

        public int c() {
            return this.f4813e;
        }

        public String d() {
            return this.f4812d;
        }

        public int e() {
            return this.f4810b;
        }

        public boolean f() {
            return this.f4811c;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class d implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final l0.a f4815a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f4816b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f4817c;

        public d(l0.a aVar, int i8, int i9) {
            this.f4815a = aVar;
            this.f4817c = i8;
            this.f4816b = i9;
        }

        public int a() {
            return this.f4817c;
        }

        public l0.a b() {
            return this.f4815a;
        }

        public int c() {
            return this.f4816b;
        }
    }

    public static int a(TypedArray typedArray, int i8) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i8);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i8, typedValue);
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

    public static List<List<byte[]>> c(Resources resources, int i8) {
        if (i8 == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i8);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(typedArrayObtainTypedArray, 0) == 1) {
                for (int i9 = 0; i9 < typedArrayObtainTypedArray.length(); i9++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i9, 0);
                    if (resourceId != 0) {
                        arrayList.add(h(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(h(resources.getStringArray(i8)));
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
            return new d(new l0.a(string, string2, string3, c(resources, resourceId)), integer, integer2);
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
        return new b((C0036c[]) arrayList.toArray(new C0036c[arrayList.size()]));
    }

    public static C0036c f(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.FontFamilyFont);
        int i8 = R$styleable.FontFamilyFont_fontWeight;
        if (!typedArrayObtainAttributes.hasValue(i8)) {
            i8 = R$styleable.FontFamilyFont_android_fontWeight;
        }
        int i9 = typedArrayObtainAttributes.getInt(i8, 400);
        int i10 = R$styleable.FontFamilyFont_fontStyle;
        if (!typedArrayObtainAttributes.hasValue(i10)) {
            i10 = R$styleable.FontFamilyFont_android_fontStyle;
        }
        boolean z7 = 1 == typedArrayObtainAttributes.getInt(i10, 0);
        int i11 = R$styleable.FontFamilyFont_ttcIndex;
        if (!typedArrayObtainAttributes.hasValue(i11)) {
            i11 = R$styleable.FontFamilyFont_android_ttcIndex;
        }
        int i12 = R$styleable.FontFamilyFont_fontVariationSettings;
        if (!typedArrayObtainAttributes.hasValue(i12)) {
            i12 = R$styleable.FontFamilyFont_android_fontVariationSettings;
        }
        String string = typedArrayObtainAttributes.getString(i12);
        int i13 = typedArrayObtainAttributes.getInt(i11, 0);
        int i14 = R$styleable.FontFamilyFont_font;
        if (!typedArrayObtainAttributes.hasValue(i14)) {
            i14 = R$styleable.FontFamilyFont_android_font;
        }
        int resourceId = typedArrayObtainAttributes.getResourceId(i14, 0);
        String string2 = typedArrayObtainAttributes.getString(i14);
        typedArrayObtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            g(xmlPullParser);
        }
        return new C0036c(string2, i9, z7, string, i13, resourceId);
    }

    public static void g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i8 = 1;
        while (i8 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i8++;
            } else if (next == 3) {
                i8--;
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
