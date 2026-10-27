package android.graphics;

import android.content.res.AssetManager;
import android.content.res.FontResourcesParser;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.graphics.fonts.FontVariationAxis;
import android.graphics.fonts.SystemFonts;
import android.media.tv.TvContract;
import android.os.ParcelFileDescriptor;
import android.provider.FontRequest;
import android.provider.FontsContract;
import android.text.FontConfig;
import android.util.Base64;
import android.util.LongSparseArray;
import android.util.LruCache;
import android.util.SparseArray;
import com.android.internal.content.NativeLibraryHelper;
import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import libcore.util.NativeAllocationRegistry;

/* JADX INFO: loaded from: classes.dex */
public class Typeface {
    public static final int BOLD = 1;
    public static final int BOLD_ITALIC = 3;
    public static final Typeface DEFAULT;
    public static final Typeface DEFAULT_BOLD;
    private static final String DEFAULT_FAMILY = "sans-serif";
    public static final int ITALIC = 2;
    public static final Typeface MONOSPACE;
    public static final int NORMAL = 0;
    public static final int RESOLVE_BY_FONT_TABLE = -1;
    public static final Typeface SANS_SERIF;
    public static final Typeface SERIF;
    private static final int STYLE_ITALIC = 1;
    public static final int STYLE_MASK = 3;
    private static final int STYLE_NORMAL = 0;
    private static String TAG = "Typeface";
    static Typeface sDefaultTypeface;
    static Typeface[] sDefaults;
    static final Map<String, Typeface> sSystemFontMap;
    private int mStyle;
    private int[] mSupportedAxes;
    private int mWeight;
    public long native_instance;
    private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Typeface.class.getClassLoader(), nativeGetReleaseFunc());
    private static final LongSparseArray<SparseArray<Typeface>> sStyledTypefaceCache = new LongSparseArray<>(3);
    private static final Object sStyledCacheLock = new Object();
    private static final LongSparseArray<SparseArray<Typeface>> sWeightTypefaceCache = new LongSparseArray<>(3);
    private static final Object sWeightCacheLock = new Object();
    private static final LruCache<String, Typeface> sDynamicTypefaceCache = new LruCache<>(16);
    private static final Object sDynamicCacheLock = new Object();

    @Deprecated
    static final Map<String, FontFamily[]> sSystemFallbackMap = Collections.emptyMap();
    private static final int[] EMPTY_AXES = new int[0];

    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeCreateFromArray(long[] jArr, int i, int i2);

    private static native long nativeCreateFromTypeface(long j, int i);

    private static native long nativeCreateFromTypefaceWithExactStyle(long j, int i, boolean z);

    private static native long nativeCreateFromTypefaceWithVariation(long j, List<FontVariationAxis> list);

    private static native long nativeCreateWeightAlias(long j, int i);

    @CriticalNative
    private static native long nativeGetReleaseFunc();

    @CriticalNative
    private static native int nativeGetStyle(long j);

    private static native int[] nativeGetSupportedAxes(long j);

    @CriticalNative
    private static native int nativeGetWeight(long j);

    private static native void nativeRegisterGenericFamily(String str, long j);

    @CriticalNative
    private static native void nativeSetDefault(long j);

    static {
        HashMap map = new HashMap();
        initSystemDefaultTypefaces(map, SystemFonts.getRawSystemFallbackMap(), SystemFonts.getAliases());
        Map<String, Typeface> mapUnmodifiableMap = Collections.unmodifiableMap(map);
        sSystemFontMap = mapUnmodifiableMap;
        if (mapUnmodifiableMap.containsKey(DEFAULT_FAMILY)) {
            setDefault(mapUnmodifiableMap.get(DEFAULT_FAMILY));
        }
        String str = (String) null;
        Typeface typefaceCreate = create(str, 0);
        DEFAULT = typefaceCreate;
        Typeface typefaceCreate2 = create(str, 1);
        DEFAULT_BOLD = typefaceCreate2;
        SANS_SERIF = create(DEFAULT_FAMILY, 0);
        SERIF = create("serif", 0);
        MONOSPACE = create("monospace", 0);
        sDefaults = new Typeface[]{typefaceCreate, typefaceCreate2, create(str, 2), create(str, 3)};
        String[] strArr = {"serif", DEFAULT_FAMILY, "cursive", "fantasy", "monospace", "system-ui"};
        for (int i = 0; i < 6; i++) {
            String str2 = strArr[i];
            registerGenericFamilyNative(str2, (Typeface) map.get(str2));
        }
    }

    private static void setDefault(Typeface typeface) {
        sDefaultTypeface = typeface;
        nativeSetDefault(typeface.native_instance);
    }

    public int getWeight() {
        return this.mWeight;
    }

    public int getStyle() {
        return this.mStyle;
    }

    public final boolean isBold() {
        return (this.mStyle & 1) != 0;
    }

    public final boolean isItalic() {
        return (this.mStyle & 2) != 0;
    }

    public static Typeface createFromResources(FontResourcesParser.FamilyResourceEntry familyResourceEntry, AssetManager assetManager, String str) {
        Typeface typefaceBuild;
        FontFamily.Builder builder;
        int i;
        if (familyResourceEntry instanceof FontResourcesParser.ProviderResourceEntry) {
            FontResourcesParser.ProviderResourceEntry providerResourceEntry = (FontResourcesParser.ProviderResourceEntry) familyResourceEntry;
            List<List<String>> certs = providerResourceEntry.getCerts();
            ArrayList arrayList = new ArrayList();
            if (certs != null) {
                for (int i2 = 0; i2 < certs.size(); i2++) {
                    List<String> list = certs.get(i2);
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        arrayList2.add(Base64.decode(list.get(i3), 0));
                    }
                    arrayList.add(arrayList2);
                }
            }
            Typeface fontSync = FontsContract.getFontSync(new FontRequest(providerResourceEntry.getAuthority(), providerResourceEntry.getPackage(), providerResourceEntry.getQuery(), arrayList));
            return fontSync == null ? DEFAULT : fontSync;
        }
        Typeface typefaceFindFromCache = findFromCache(assetManager, str);
        if (typefaceFindFromCache != null) {
            return typefaceFindFromCache;
        }
        try {
            FontResourcesParser.FontFileResourceEntry[] entries = ((FontResourcesParser.FontFamilyFilesResourceEntry) familyResourceEntry).getEntries();
            int length = entries.length;
            builder = null;
            int i4 = 0;
            while (true) {
                i = 1;
                if (i4 >= length) {
                    break;
                }
                FontResourcesParser.FontFileResourceEntry fontFileResourceEntry = entries[i4];
                Font.Builder fontVariationSettings = new Font.Builder(assetManager, fontFileResourceEntry.getFileName(), false, 0).setTtcIndex(fontFileResourceEntry.getTtcIndex()).setFontVariationSettings(fontFileResourceEntry.getVariationSettings());
                if (fontFileResourceEntry.getWeight() != -1) {
                    fontVariationSettings.setWeight(fontFileResourceEntry.getWeight());
                }
                if (fontFileResourceEntry.getItalic() != -1) {
                    if (fontFileResourceEntry.getItalic() != 1) {
                        i = 0;
                    }
                    fontVariationSettings.setSlant(i);
                }
                if (builder == null) {
                    builder = new FontFamily.Builder(fontVariationSettings.build());
                } else {
                    builder.addFont(fontVariationSettings.build());
                }
                i4++;
            }
        } catch (IOException unused) {
            typefaceBuild = DEFAULT;
        } catch (IllegalArgumentException unused2) {
            return null;
        }
        if (builder == null) {
            return DEFAULT;
        }
        android.graphics.fonts.FontFamily fontFamilyBuild = builder.build();
        FontStyle fontStyle = new FontStyle(400, 0);
        Font font = fontFamilyBuild.getFont(0);
        int matchScore = fontStyle.getMatchScore(font.getStyle());
        while (i < fontFamilyBuild.getSize()) {
            Font font2 = fontFamilyBuild.getFont(i);
            int matchScore2 = fontStyle.getMatchScore(font2.getStyle());
            if (matchScore2 < matchScore) {
                font = font2;
                matchScore = matchScore2;
            }
            i++;
        }
        typefaceBuild = new CustomFallbackBuilder(fontFamilyBuild).setStyle(font.getStyle()).build();
        synchronized (sDynamicCacheLock) {
            sDynamicTypefaceCache.put(Builder.createAssetUid(assetManager, str, 0, null, -1, -1, DEFAULT_FAMILY), typefaceBuild);
        }
        return typefaceBuild;
    }

    public static Typeface findFromCache(AssetManager assetManager, String str) {
        synchronized (sDynamicCacheLock) {
            Typeface typeface = sDynamicTypefaceCache.get(Builder.createAssetUid(assetManager, str, 0, null, -1, -1, DEFAULT_FAMILY));
            if (typeface != null) {
                return typeface;
            }
            return null;
        }
    }

    public static final class Builder {
        public static final int BOLD_WEIGHT = 700;
        public static final int NORMAL_WEIGHT = 400;
        private final AssetManager mAssetManager;
        private String mFallbackFamilyName;
        private final Font.Builder mFontBuilder;
        private int mItalic;
        private final String mPath;
        private int mWeight;

        public Builder(File file) {
            this.mWeight = -1;
            this.mItalic = -1;
            this.mFontBuilder = new Font.Builder(file);
            this.mAssetManager = null;
            this.mPath = null;
        }

        public Builder(FileDescriptor fileDescriptor) {
            Font.Builder builder;
            this.mWeight = -1;
            this.mItalic = -1;
            try {
                builder = new Font.Builder(ParcelFileDescriptor.dup(fileDescriptor));
            } catch (IOException unused) {
                builder = null;
            }
            this.mFontBuilder = builder;
            this.mAssetManager = null;
            this.mPath = null;
        }

        public Builder(String str) {
            this.mWeight = -1;
            this.mItalic = -1;
            this.mFontBuilder = new Font.Builder(new File(str));
            this.mAssetManager = null;
            this.mPath = null;
        }

        public Builder(AssetManager assetManager, String str) {
            this(assetManager, str, true, 0);
        }

        public Builder(AssetManager assetManager, String str, boolean z, int i) {
            this.mWeight = -1;
            this.mItalic = -1;
            this.mFontBuilder = new Font.Builder(assetManager, str, z, i);
            this.mAssetManager = assetManager;
            this.mPath = str;
        }

        public Builder setWeight(int i) {
            this.mWeight = i;
            this.mFontBuilder.setWeight(i);
            return this;
        }

        public Builder setItalic(boolean z) {
            this.mItalic = z ? 1 : 0;
            this.mFontBuilder.setSlant(z ? 1 : 0);
            return this;
        }

        public Builder setTtcIndex(int i) {
            this.mFontBuilder.setTtcIndex(i);
            return this;
        }

        public Builder setFontVariationSettings(String str) {
            this.mFontBuilder.setFontVariationSettings(str);
            return this;
        }

        public Builder setFontVariationSettings(FontVariationAxis[] fontVariationAxisArr) {
            this.mFontBuilder.setFontVariationSettings(fontVariationAxisArr);
            return this;
        }

        public Builder setFallback(String str) {
            this.mFallbackFamilyName = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String createAssetUid(AssetManager assetManager, String str, int i, FontVariationAxis[] fontVariationAxisArr, int i2, int i3, String str2) {
            SparseArray<String> assignedPackageIdentifiers = assetManager.getAssignedPackageIdentifiers();
            StringBuilder sb = new StringBuilder();
            int size = assignedPackageIdentifiers.size();
            for (int i4 = 0; i4 < size; i4++) {
                sb.append(assignedPackageIdentifiers.valueAt(i4));
                sb.append(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            }
            sb.append(str);
            sb.append(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            sb.append(Integer.toString(i));
            sb.append(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            sb.append(Integer.toString(i2));
            sb.append(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            sb.append(Integer.toString(i3));
            sb.append("--");
            sb.append(str2);
            sb.append("--");
            if (fontVariationAxisArr != null) {
                for (FontVariationAxis fontVariationAxis : fontVariationAxisArr) {
                    sb.append(fontVariationAxis.getTag());
                    sb.append(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                    sb.append(Float.toString(fontVariationAxis.getStyleValue()));
                }
            }
            return sb.toString();
        }

        private Typeface resolveFallbackTypeface() {
            String str = this.mFallbackFamilyName;
            if (str == null) {
                return null;
            }
            Typeface systemDefaultTypeface = Typeface.getSystemDefaultTypeface(str);
            int i = this.mWeight;
            if (i == -1 && this.mItalic == -1) {
                return systemDefaultTypeface;
            }
            if (i == -1) {
                i = systemDefaultTypeface.mWeight;
            }
            int i2 = this.mItalic;
            boolean z = false;
            if (i2 != -1 ? i2 == 1 : (systemDefaultTypeface.mStyle & 2) != 0) {
                z = true;
            }
            return Typeface.createWeightStyle(systemDefaultTypeface, i, z);
        }

        public Typeface build() {
            String strCreateAssetUid;
            Font.Builder builder = this.mFontBuilder;
            if (builder == null) {
                return resolveFallbackTypeface();
            }
            try {
                Font fontBuild = builder.build();
                AssetManager assetManager = this.mAssetManager;
                if (assetManager == null) {
                    strCreateAssetUid = null;
                } else {
                    String str = this.mPath;
                    int ttcIndex = fontBuild.getTtcIndex();
                    FontVariationAxis[] axes = fontBuild.getAxes();
                    int i = this.mWeight;
                    int i2 = this.mItalic;
                    String str2 = this.mFallbackFamilyName;
                    if (str2 == null) {
                        str2 = Typeface.DEFAULT_FAMILY;
                    }
                    strCreateAssetUid = createAssetUid(assetManager, str, ttcIndex, axes, i, i2, str2);
                }
                if (strCreateAssetUid != null) {
                    synchronized (Typeface.sDynamicCacheLock) {
                        Typeface typeface = (Typeface) Typeface.sDynamicTypefaceCache.get(strCreateAssetUid);
                        if (typeface != null) {
                            return typeface;
                        }
                    }
                }
                android.graphics.fonts.FontFamily fontFamilyBuild = new FontFamily.Builder(fontBuild).build();
                int weight = this.mWeight;
                if (weight == -1) {
                    weight = fontBuild.getStyle().getWeight();
                }
                int slant = this.mItalic;
                if (slant == -1) {
                    slant = fontBuild.getStyle().getSlant();
                }
                CustomFallbackBuilder style = new CustomFallbackBuilder(fontFamilyBuild).setStyle(new FontStyle(weight, slant));
                String str3 = this.mFallbackFamilyName;
                if (str3 != null) {
                    style.setSystemFallback(str3);
                }
                Typeface typefaceBuild = style.build();
                if (strCreateAssetUid != null) {
                    synchronized (Typeface.sDynamicCacheLock) {
                        Typeface.sDynamicTypefaceCache.put(strCreateAssetUid, typefaceBuild);
                    }
                }
                return typefaceBuild;
            } catch (IOException | IllegalArgumentException unused) {
                return resolveFallbackTypeface();
            }
        }
    }

    public static final class CustomFallbackBuilder {
        private static final int MAX_CUSTOM_FALLBACK = 64;
        private String mFallbackName;
        private final ArrayList<android.graphics.fonts.FontFamily> mFamilies;
        private FontStyle mStyle;

        public static int getMaxCustomFallbackCount() {
            return 64;
        }

        public CustomFallbackBuilder(android.graphics.fonts.FontFamily fontFamily) {
            ArrayList<android.graphics.fonts.FontFamily> arrayList = new ArrayList<>();
            this.mFamilies = arrayList;
            this.mFallbackName = null;
            Preconditions.checkNotNull(fontFamily);
            arrayList.add(fontFamily);
        }

        public CustomFallbackBuilder setSystemFallback(String str) {
            Preconditions.checkNotNull(str);
            this.mFallbackName = str;
            return this;
        }

        public CustomFallbackBuilder setStyle(FontStyle fontStyle) {
            this.mStyle = fontStyle;
            return this;
        }

        public CustomFallbackBuilder addCustomFallback(android.graphics.fonts.FontFamily fontFamily) {
            Preconditions.checkNotNull(fontFamily);
            Preconditions.checkArgument(this.mFamilies.size() < getMaxCustomFallbackCount(), "Custom fallback limit exceeded(" + getMaxCustomFallbackCount() + ")");
            this.mFamilies.add(fontFamily);
            return this;
        }

        public Typeface build() {
            int size = this.mFamilies.size();
            android.graphics.fonts.FontFamily[] systemFallback = SystemFonts.getSystemFallback(this.mFallbackName);
            long[] jArr = new long[systemFallback.length + size];
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                jArr[i2] = this.mFamilies.get(i2).getNativePtr();
            }
            for (int i3 = 0; i3 < systemFallback.length; i3++) {
                jArr[i3 + size] = systemFallback[i3].getNativePtr();
            }
            FontStyle fontStyle = this.mStyle;
            int weight = fontStyle == null ? 400 : fontStyle.getWeight();
            FontStyle fontStyle2 = this.mStyle;
            if (fontStyle2 != null && fontStyle2.getSlant() != 0) {
                i = 1;
            }
            return new Typeface(Typeface.nativeCreateFromArray(jArr, weight, i));
        }
    }

    public static Typeface create(String str, int i) {
        return create(getSystemDefaultTypeface(str), i);
    }

    public static Typeface create(Typeface typeface, int i) {
        if ((i & (-4)) != 0) {
            i = 0;
        }
        if (typeface == null) {
            typeface = sDefaultTypeface;
        }
        if (typeface.mStyle == i) {
            return typeface;
        }
        long j = typeface.native_instance;
        synchronized (sStyledCacheLock) {
            LongSparseArray<SparseArray<Typeface>> longSparseArray = sStyledTypefaceCache;
            SparseArray<Typeface> sparseArray = longSparseArray.get(j);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>(4);
                longSparseArray.put(j, sparseArray);
            } else {
                Typeface typeface2 = sparseArray.get(i);
                if (typeface2 != null) {
                    return typeface2;
                }
            }
            Typeface typeface3 = new Typeface(nativeCreateFromTypeface(j, i));
            sparseArray.put(i, typeface3);
            return typeface3;
        }
    }

    public static Typeface create(Typeface typeface, int i, boolean z) {
        Preconditions.checkArgumentInRange(i, 0, 1000, TvContract.PreviewPrograms.COLUMN_WEIGHT);
        if (typeface == null) {
            typeface = sDefaultTypeface;
        }
        return createWeightStyle(typeface, i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Typeface createWeightStyle(Typeface typeface, int i, boolean z) {
        int i2 = (i << 1) | (z ? 1 : 0);
        synchronized (sWeightCacheLock) {
            LongSparseArray<SparseArray<Typeface>> longSparseArray = sWeightTypefaceCache;
            SparseArray<Typeface> sparseArray = longSparseArray.get(typeface.native_instance);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>(4);
                longSparseArray.put(typeface.native_instance, sparseArray);
            } else {
                Typeface typeface2 = sparseArray.get(i2);
                if (typeface2 != null) {
                    return typeface2;
                }
            }
            Typeface typeface3 = new Typeface(nativeCreateFromTypefaceWithExactStyle(typeface.native_instance, i, z));
            sparseArray.put(i2, typeface3);
            return typeface3;
        }
    }

    public static Typeface createFromTypefaceWithVariation(Typeface typeface, List<FontVariationAxis> list) {
        if (typeface == null) {
            typeface = DEFAULT;
        }
        return new Typeface(nativeCreateFromTypefaceWithVariation(typeface.native_instance, list));
    }

    public static Typeface defaultFromStyle(int i) {
        return sDefaults[i];
    }

    public static Typeface createFromAsset(AssetManager assetManager, String str) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(assetManager);
        Typeface typefaceBuild = new Builder(assetManager, str).build();
        if (typefaceBuild != null) {
            return typefaceBuild;
        }
        try {
            InputStream inputStreamOpen = assetManager.open(str);
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
            return DEFAULT;
        } catch (IOException unused) {
            throw new RuntimeException("Font asset not found " + str);
        }
    }

    private static String createProviderUid(String str, String str2) {
        return "provider:" + str + NativeLibraryHelper.CLEAR_ABI_OVERRIDE + str2;
    }

    public static Typeface createFromFile(File file) {
        Typeface typefaceBuild = new Builder(file).build();
        if (typefaceBuild != null) {
            return typefaceBuild;
        }
        if (!file.exists()) {
            throw new RuntimeException("Font asset not found " + file.getAbsolutePath());
        }
        return DEFAULT;
    }

    public static Typeface createFromFile(String str) {
        Preconditions.checkNotNull(str);
        return createFromFile(new File(str));
    }

    @Deprecated
    private static Typeface createFromFamilies(FontFamily[] fontFamilyArr) {
        long[] jArr = new long[fontFamilyArr.length];
        for (int i = 0; i < fontFamilyArr.length; i++) {
            jArr[i] = fontFamilyArr[i].mNativePtr;
        }
        return new Typeface(nativeCreateFromArray(jArr, -1, -1));
    }

    private static Typeface createFromFamilies(android.graphics.fonts.FontFamily[] fontFamilyArr) {
        long[] jArr = new long[fontFamilyArr.length];
        for (int i = 0; i < fontFamilyArr.length; i++) {
            jArr[i] = fontFamilyArr[i].getNativePtr();
        }
        return new Typeface(nativeCreateFromArray(jArr, -1, -1));
    }

    @Deprecated
    private static Typeface createFromFamiliesWithDefault(FontFamily[] fontFamilyArr, int i, int i2) {
        return createFromFamiliesWithDefault(fontFamilyArr, DEFAULT_FAMILY, i, i2);
    }

    @Deprecated
    private static Typeface createFromFamiliesWithDefault(FontFamily[] fontFamilyArr, String str, int i, int i2) {
        android.graphics.fonts.FontFamily[] systemFallback = SystemFonts.getSystemFallback(str);
        long[] jArr = new long[fontFamilyArr.length + systemFallback.length];
        for (int i3 = 0; i3 < fontFamilyArr.length; i3++) {
            jArr[i3] = fontFamilyArr[i3].mNativePtr;
        }
        for (int i4 = 0; i4 < systemFallback.length; i4++) {
            jArr[fontFamilyArr.length + i4] = systemFallback[i4].getNativePtr();
        }
        return new Typeface(nativeCreateFromArray(jArr, i, i2));
    }

    private Typeface(long j) {
        this.mStyle = 0;
        this.mWeight = 0;
        if (j == 0) {
            throw new RuntimeException("native typeface cannot be made");
        }
        this.native_instance = j;
        sRegistry.registerNativeAllocation(this, j);
        this.mStyle = nativeGetStyle(j);
        this.mWeight = nativeGetWeight(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Typeface getSystemDefaultTypeface(String str) {
        Typeface typeface = sSystemFontMap.get(str);
        return typeface == null ? DEFAULT : typeface;
    }

    public static void initSystemDefaultTypefaces(Map<String, Typeface> map, Map<String, android.graphics.fonts.FontFamily[]> map2, FontConfig.Alias[] aliasArr) {
        Typeface typeface;
        for (Map.Entry<String, android.graphics.fonts.FontFamily[]> entry : map2.entrySet()) {
            map.put(entry.getKey(), createFromFamilies(entry.getValue()));
        }
        for (FontConfig.Alias alias : aliasArr) {
            if (!map.containsKey(alias.getName()) && (typeface = map.get(alias.getToName())) != null) {
                int weight = alias.getWeight();
                if (weight != 400) {
                    typeface = new Typeface(nativeCreateWeightAlias(typeface.native_instance, weight));
                }
                map.put(alias.getName(), typeface);
            }
        }
    }

    private static void registerGenericFamilyNative(String str, Typeface typeface) {
        if (typeface != null) {
            nativeRegisterGenericFamily(str, typeface.native_instance);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Typeface typeface = (Typeface) obj;
        return this.mStyle == typeface.mStyle && this.native_instance == typeface.native_instance;
    }

    public int hashCode() {
        long j = this.native_instance;
        return ((527 + ((int) (j ^ (j >>> 32)))) * 31) + this.mStyle;
    }

    public boolean isSupportedAxes(int i) {
        if (this.mSupportedAxes == null) {
            synchronized (this) {
                if (this.mSupportedAxes == null) {
                    int[] iArrNativeGetSupportedAxes = nativeGetSupportedAxes(this.native_instance);
                    this.mSupportedAxes = iArrNativeGetSupportedAxes;
                    if (iArrNativeGetSupportedAxes == null) {
                        this.mSupportedAxes = EMPTY_AXES;
                    }
                }
            }
        }
        return Arrays.binarySearch(this.mSupportedAxes, i) >= 0;
    }
}
