package android.graphics.fonts;

import android.graphics.FontListParser;
import android.graphics.fonts.FontCustomizationParser;
import android.text.FontConfig;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class SystemFonts {
    private static final String DEFAULT_FAMILY = "sans-serif";
    private static final String TAG = "SystemFonts";
    private static final FontConfig.Alias[] sAliases;
    private static final List<Font> sAvailableFonts;
    private static final Map<String, FontFamily[]> sSystemFallbackMap;

    private SystemFonts() {
    }

    public static Set<Font> getAvailableFonts() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(sAvailableFonts);
        return hashSet;
    }

    public static FontFamily[] getSystemFallback(String str) {
        FontFamily[] fontFamilyArr = sSystemFallbackMap.get(str);
        return fontFamilyArr == null ? sSystemFallbackMap.get(DEFAULT_FAMILY) : fontFamilyArr;
    }

    public static Map<String, FontFamily[]> getRawSystemFallbackMap() {
        return sSystemFallbackMap;
    }

    public static FontConfig.Alias[] getAliases() {
        return sAliases;
    }

    private static ByteBuffer mmap(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                FileChannel channel = fileInputStream.getChannel();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                fileInputStream.close();
                return map;
            } finally {
            }
        } catch (IOException unused) {
            return null;
        }
    }

    private static void pushFamilyToFallback(FontConfig.Family family, ArrayMap<String, ArrayList<FontFamily>> arrayMap, Map<String, ByteBuffer> map, ArrayList<Font> arrayList) {
        String languages = family.getLanguages();
        int variant = family.getVariant();
        ArrayList arrayList2 = new ArrayList();
        ArrayMap arrayMap2 = new ArrayMap();
        for (FontConfig.Font font : family.getFonts()) {
            String fallbackFor = font.getFallbackFor();
            if (fallbackFor == null) {
                arrayList2.add(font);
            } else {
                ArrayList arrayList3 = (ArrayList) arrayMap2.get(fallbackFor);
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                    arrayMap2.put(fallbackFor, arrayList3);
                }
                arrayList3.add(font);
            }
        }
        FontFamily fontFamilyCreateFontFamily = arrayList2.isEmpty() ? null : createFontFamily(family.getName(), arrayList2, languages, variant, map, arrayList);
        for (int i = 0; i < arrayMap.size(); i++) {
            ArrayList arrayList4 = (ArrayList) arrayMap2.get(arrayMap.keyAt(i));
            if (arrayList4 != null) {
                FontFamily fontFamilyCreateFontFamily2 = createFontFamily(family.getName(), arrayList4, languages, variant, map, arrayList);
                if (fontFamilyCreateFontFamily2 != null) {
                    arrayMap.valueAt(i).add(fontFamilyCreateFontFamily2);
                } else if (fontFamilyCreateFontFamily != null) {
                    arrayMap.valueAt(i).add(fontFamilyCreateFontFamily);
                }
            } else if (fontFamilyCreateFontFamily != null) {
                arrayMap.valueAt(i).add(fontFamilyCreateFontFamily);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0034 A[EXC_TOP_SPLITTER, PHI: r5
  0x0034: PHI (r5v2 java.nio.ByteBuffer) = (r5v1 java.nio.ByteBuffer), (r5v9 java.nio.ByteBuffer) binds: [B:9:0x0021, B:14:0x0031] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.fonts.FontFamily createFontFamily(java.lang.String r8, java.util.List<android.text.FontConfig.Font> r9, java.lang.String r10, int r11, java.util.Map<java.lang.String, java.nio.ByteBuffer> r12, java.util.ArrayList<android.graphics.fonts.Font> r13) {
        /*
            int r8 = r9.size()
            r0 = 0
            if (r8 != 0) goto L8
            return r0
        L8:
            r8 = 0
            r1 = r8
            r2 = r0
        Lb:
            int r3 = r9.size()
            if (r1 >= r3) goto L7f
            java.lang.Object r3 = r9.get(r1)
            android.text.FontConfig$Font r3 = (android.text.FontConfig.Font) r3
            java.lang.String r4 = r3.getFontName()
            java.lang.Object r5 = r12.get(r4)
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            if (r5 != 0) goto L34
            boolean r5 = r12.containsKey(r4)
            if (r5 == 0) goto L2a
            goto L75
        L2a:
            java.nio.ByteBuffer r5 = mmap(r4)
            r12.put(r4, r5)
            if (r5 != 0) goto L34
            goto L75
        L34:
            android.graphics.fonts.Font$Builder r6 = new android.graphics.fonts.Font$Builder     // Catch: java.io.IOException -> L78
            java.io.File r7 = new java.io.File     // Catch: java.io.IOException -> L78
            r7.<init>(r4)     // Catch: java.io.IOException -> L78
            r6.<init>(r5, r7, r10)     // Catch: java.io.IOException -> L78
            int r4 = r3.getWeight()     // Catch: java.io.IOException -> L78
            android.graphics.fonts.Font$Builder r4 = r6.setWeight(r4)     // Catch: java.io.IOException -> L78
            boolean r5 = r3.isItalic()     // Catch: java.io.IOException -> L78
            if (r5 == 0) goto L4e
            r5 = 1
            goto L4f
        L4e:
            r5 = r8
        L4f:
            android.graphics.fonts.Font$Builder r4 = r4.setSlant(r5)     // Catch: java.io.IOException -> L78
            int r5 = r3.getTtcIndex()     // Catch: java.io.IOException -> L78
            android.graphics.fonts.Font$Builder r4 = r4.setTtcIndex(r5)     // Catch: java.io.IOException -> L78
            android.graphics.fonts.FontVariationAxis[] r3 = r3.getAxes()     // Catch: java.io.IOException -> L78
            android.graphics.fonts.Font$Builder r3 = r4.setFontVariationSettings(r3)     // Catch: java.io.IOException -> L78
            android.graphics.fonts.Font r3 = r3.build()     // Catch: java.io.IOException -> L78
            r13.add(r3)
            if (r2 != 0) goto L72
            android.graphics.fonts.FontFamily$Builder r2 = new android.graphics.fonts.FontFamily$Builder
            r2.<init>(r3)
            goto L75
        L72:
            r2.addFont(r3)
        L75:
            int r1 = r1 + 1
            goto Lb
        L78:
            r8 = move-exception
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            r9.<init>(r8)
            throw r9
        L7f:
            if (r2 != 0) goto L82
            goto L86
        L82:
            android.graphics.fonts.FontFamily r0 = r2.build(r10, r11, r8)
        L86:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.graphics.fonts.SystemFonts.createFontFamily(java.lang.String, java.util.List, java.lang.String, int, java.util.Map, java.util.ArrayList):android.graphics.fonts.FontFamily");
    }

    private static void appendNamedFamily(FontConfig.Family family, HashMap<String, ByteBuffer> map, ArrayMap<String, ArrayList<FontFamily>> arrayMap, ArrayList<Font> arrayList) {
        String name = family.getName();
        FontFamily fontFamilyCreateFontFamily = createFontFamily(name, Arrays.asList(family.getFonts()), family.getLanguages(), family.getVariant(), map, arrayList);
        if (fontFamilyCreateFontFamily == null) {
            return;
        }
        ArrayList<FontFamily> arrayList2 = new ArrayList<>();
        arrayList2.add(fontFamilyCreateFontFamily);
        arrayMap.put(name, arrayList2);
    }

    public static FontConfig.Alias[] buildSystemFallback(String str, String str2, FontCustomizationParser.Result result, ArrayMap<String, FontFamily[]> arrayMap, ArrayList<Font> arrayList) {
        try {
            FontConfig fontConfig = FontListParser.parse(new FileInputStream(str), str2);
            HashMap map = new HashMap();
            FontConfig.Family[] families = fontConfig.getFamilies();
            ArrayMap arrayMap2 = new ArrayMap();
            for (FontConfig.Family family : families) {
                if (family.getName() != null) {
                    appendNamedFamily(family, map, arrayMap2, arrayList);
                }
            }
            for (int i = 0; i < result.mAdditionalNamedFamilies.size(); i++) {
                appendNamedFamily(result.mAdditionalNamedFamilies.get(i), map, arrayMap2, arrayList);
            }
            for (int i2 = 0; i2 < families.length; i2++) {
                FontConfig.Family family2 = families[i2];
                if (i2 == 0 || family2.getName() == null) {
                    pushFamilyToFallback(family2, arrayMap2, map, arrayList);
                }
            }
            for (int i3 = 0; i3 < arrayMap2.size(); i3++) {
                String str3 = (String) arrayMap2.keyAt(i3);
                List list = (List) arrayMap2.valueAt(i3);
                arrayMap.put(str3, (FontFamily[]) list.toArray(new FontFamily[list.size()]));
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(Arrays.asList(fontConfig.getAliases()));
            arrayList2.addAll(result.mAdditionalAliases);
            return (FontConfig.Alias[]) arrayList2.toArray(new FontConfig.Alias[arrayList2.size()]);
        } catch (IOException | XmlPullParserException e) {
            Log.e(TAG, "Failed initialize system fallbacks.", e);
            return (FontConfig.Alias[]) ArrayUtils.emptyArray(FontConfig.Alias.class);
        }
    }

    private static FontCustomizationParser.Result readFontCustomization(String str, String str2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                FontCustomizationParser.Result result = FontCustomizationParser.parse(fileInputStream, str2);
                fileInputStream.close();
                return result;
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            return new FontCustomizationParser.Result();
        } catch (XmlPullParserException e) {
            Log.e(TAG, "Failed to parse font customization XML", e);
            return new FontCustomizationParser.Result();
        }
    }

    static {
        ArrayMap arrayMap = new ArrayMap();
        ArrayList arrayList = new ArrayList();
        sAliases = buildSystemFallback("/system/etc/fonts.xml", "/system/fonts/", readFontCustomization("/product/etc/fonts_customization.xml", "/product/fonts/"), arrayMap, arrayList);
        sSystemFallbackMap = Collections.unmodifiableMap(arrayMap);
        sAvailableFonts = Collections.unmodifiableList(arrayList);
    }
}
