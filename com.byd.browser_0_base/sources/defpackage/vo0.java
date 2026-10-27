package defpackage;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Base64;
import android.util.Xml;
import androidx.core.R$styleable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: FontResourcesParserCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class vo0 {

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public interface a {
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class b implements a {
        public final c[] a;

        public b(c[] cVarArr) {
            this.a = cVarArr;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class c {
        public final int a;
        public final boolean b;
        public final String c;
        public final int d;
        public final int e;

        public c(int i, int i2, int i3, String str, String str2, boolean z) {
            this.a = i;
            this.b = z;
            this.c = str2;
            this.d = i2;
            this.e = i3;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class d implements a {
        public final po0 a;
        public final int b;
        public final int c;
        public final String d;

        public d(po0 po0Var, int i, int i2, String str) {
            this.a = po0Var;
            this.c = i;
            this.b = i2;
            this.d = str;
        }
    }

    public static a a(XmlResourceParser xmlResourceParser, Resources resources) {
        int next;
        do {
            next = xmlResourceParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        xmlResourceParser.require(2, null, "font-family");
        if (!xmlResourceParser.getName().equals("font-family")) {
            c(xmlResourceParser);
            return null;
        }
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.FontFamily);
        String string = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(R$styleable.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchTimeout, 500);
        String string4 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderSystemFontFamily);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlResourceParser.next() != 3) {
                c(xmlResourceParser);
            }
            return new d(new po0(string, string2, string3, b(resourceId, resources)), integer, integer2, string4);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlResourceParser.next() != 3) {
            if (xmlResourceParser.getEventType() == 2) {
                if (xmlResourceParser.getName().equals("font")) {
                    TypedArray typedArrayObtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.FontFamilyFont);
                    int i = R$styleable.FontFamilyFont_fontWeight;
                    if (!typedArrayObtainAttributes2.hasValue(i)) {
                        i = R$styleable.FontFamilyFont_android_fontWeight;
                    }
                    int i2 = typedArrayObtainAttributes2.getInt(i, 400);
                    int i3 = R$styleable.FontFamilyFont_fontStyle;
                    if (!typedArrayObtainAttributes2.hasValue(i3)) {
                        i3 = R$styleable.FontFamilyFont_android_fontStyle;
                    }
                    boolean z = 1 == typedArrayObtainAttributes2.getInt(i3, 0);
                    int i4 = R$styleable.FontFamilyFont_ttcIndex;
                    if (!typedArrayObtainAttributes2.hasValue(i4)) {
                        i4 = R$styleable.FontFamilyFont_android_ttcIndex;
                    }
                    int i5 = R$styleable.FontFamilyFont_fontVariationSettings;
                    if (!typedArrayObtainAttributes2.hasValue(i5)) {
                        i5 = R$styleable.FontFamilyFont_android_fontVariationSettings;
                    }
                    String string5 = typedArrayObtainAttributes2.getString(i5);
                    int i6 = typedArrayObtainAttributes2.getInt(i4, 0);
                    int i7 = R$styleable.FontFamilyFont_font;
                    if (!typedArrayObtainAttributes2.hasValue(i7)) {
                        i7 = R$styleable.FontFamilyFont_android_font;
                    }
                    int resourceId2 = typedArrayObtainAttributes2.getResourceId(i7, 0);
                    String string6 = typedArrayObtainAttributes2.getString(i7);
                    typedArrayObtainAttributes2.recycle();
                    while (xmlResourceParser.next() != 3) {
                        c(xmlResourceParser);
                    }
                    arrayList.add(new c(i2, i6, resourceId2, string6, string5, z));
                } else {
                    c(xmlResourceParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new b((c[]) arrayList.toArray(new c[0]));
    }

    public static List b(int i, Resources resources) {
        if (i == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (typedArrayObtainTypedArray.getType(0) == 1) {
                for (int i2 = 0; i2 < typedArrayObtainTypedArray.length(); i2++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i2, 0);
                    if (resourceId != 0) {
                        String[] stringArray = resources.getStringArray(resourceId);
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                }
            } else {
                String[] stringArray2 = resources.getStringArray(i);
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray2) {
                    arrayList3.add(Base64.decode(str2, 0));
                }
                arrayList.add(arrayList3);
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    public static void c(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        int i = 1;
        while (i > 0) {
            int next = xmlResourceParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }
}
