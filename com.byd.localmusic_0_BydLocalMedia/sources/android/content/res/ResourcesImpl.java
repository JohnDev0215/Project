package android.content.res;

import android.animation.Animator;
import android.animation.StateListAnimator;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.FontResourcesParser;
import android.content.res.Resources;
import android.content.res.XmlBlock;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.icu.text.PluralRules;
import android.os.Build;
import android.os.LocaleList;
import android.os.Process;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.DisplayAdjustments;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.android.internal.util.GrowingArrayUtils;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ResourcesImpl {
    private static final boolean DEBUG_CONFIG = false;
    private static final boolean DEBUG_LOAD = false;
    private static final int ID_OTHER = 16777220;
    static final String TAG = "Resources";
    static final String TAG_PRELOAD = "Resources.preload";
    private static final boolean TRACE_FOR_MISS_PRELOAD = false;
    private static final boolean TRACE_FOR_PRELOAD = false;
    private static final int XML_BLOCK_CACHE_SIZE = 4;
    private static int sPreloadTracingNumLoadedDrawables;
    private static boolean sPreloaded;
    private static final LongSparseArray<Drawable.ConstantState>[] sPreloadedDrawables;
    final AssetManager mAssets;
    private final DisplayAdjustments mDisplayAdjustments;
    private PluralRules mPluralRule;
    private long mPreloadTracingPreloadStartTime;
    private long mPreloadTracingStartBitmapCount;
    private long mPreloadTracingStartBitmapSize;
    private boolean mPreloading;
    public static final boolean TRACE_FOR_DETAILED_PRELOAD = SystemProperties.getBoolean("debug.trace_resource_preload", false);
    private static final Object sSync = new Object();
    private static final LongSparseArray<Drawable.ConstantState> sPreloadedColorDrawables = new LongSparseArray<>();
    private static final LongSparseArray<ConstantState<ComplexColor>> sPreloadedComplexColors = new LongSparseArray<>();
    private final Object mAccessLock = new Object();
    private final Configuration mTmpConfig = new Configuration();
    private final DrawableCache mDrawableCache = new DrawableCache();
    private final DrawableCache mColorDrawableCache = new DrawableCache();
    private final ConfigurationBoundResourceCache<ComplexColor> mComplexColorCache = new ConfigurationBoundResourceCache<>();
    private final ConfigurationBoundResourceCache<Animator> mAnimatorCache = new ConfigurationBoundResourceCache<>();
    private final ConfigurationBoundResourceCache<StateListAnimator> mStateListAnimatorCache = new ConfigurationBoundResourceCache<>();
    private final ThreadLocal<LookupStack> mLookupStack = ThreadLocal.withInitial(new Supplier() { // from class: android.content.res.-$$Lambda$ResourcesImpl$h3PTRX185BeQl8SVC2_w9arp5Og
        @Override // java.util.function.Supplier
        public final Object get() {
            return ResourcesImpl.lambda$new$0();
        }
    });
    private int mLastCachedXmlBlockIndex = -1;
    private final int[] mCachedXmlBlockCookies = new int[4];
    private final String[] mCachedXmlBlockFiles = new String[4];
    private final XmlBlock[] mCachedXmlBlocks = new XmlBlock[4];
    private final DisplayMetrics mMetrics = new DisplayMetrics();
    private final Configuration mConfiguration = new Configuration();

    static {
        LongSparseArray<Drawable.ConstantState>[] longSparseArrayArr = new LongSparseArray[2];
        sPreloadedDrawables = longSparseArrayArr;
        longSparseArrayArr[0] = new LongSparseArray<>();
        sPreloadedDrawables[1] = new LongSparseArray<>();
    }

    static /* synthetic */ LookupStack lambda$new$0() {
        return new LookupStack();
    }

    public ResourcesImpl(AssetManager assetManager, DisplayMetrics displayMetrics, Configuration configuration, DisplayAdjustments displayAdjustments) {
        this.mAssets = assetManager;
        this.mMetrics.setToDefaults();
        this.mDisplayAdjustments = displayAdjustments;
        this.mConfiguration.setToDefaults();
        updateConfiguration(configuration, displayMetrics, displayAdjustments.getCompatibilityInfo());
    }

    public DisplayAdjustments getDisplayAdjustments() {
        return this.mDisplayAdjustments;
    }

    public AssetManager getAssets() {
        return this.mAssets;
    }

    DisplayMetrics getDisplayMetrics() {
        return this.mMetrics;
    }

    Configuration getConfiguration() {
        return this.mConfiguration;
    }

    Configuration[] getSizeConfigurations() {
        return this.mAssets.getSizeConfigurations();
    }

    CompatibilityInfo getCompatibilityInfo() {
        return this.mDisplayAdjustments.getCompatibilityInfo();
    }

    private PluralRules getPluralRule() {
        PluralRules pluralRules;
        synchronized (sSync) {
            if (this.mPluralRule == null) {
                this.mPluralRule = PluralRules.forLocale(this.mConfiguration.getLocales().get(0));
            }
            pluralRules = this.mPluralRule;
        }
        return pluralRules;
    }

    void getValue(int i, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        if (this.mAssets.getResourceValue(i, 0, typedValue, z)) {
            return;
        }
        throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(i));
    }

    void getValueForDensity(int i, int i2, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        if (this.mAssets.getResourceValue(i, i2, typedValue, z)) {
            return;
        }
        throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(i));
    }

    void getValue(String str, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        int identifier = getIdentifier(str, TypedValues.Custom.S_STRING, null);
        if (identifier != 0) {
            getValue(identifier, typedValue, z);
            return;
        }
        throw new Resources.NotFoundException("String resource name " + str);
    }

    int getIdentifier(String str, String str2, String str3) {
        if (str == null) {
            throw new NullPointerException("name is null");
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return this.mAssets.getResourceIdentifier(str, str2, str3);
        }
    }

    String getResourceName(int i) throws Resources.NotFoundException {
        String resourceName = this.mAssets.getResourceName(i);
        if (resourceName != null) {
            return resourceName;
        }
        throw new Resources.NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(i));
    }

    String getResourcePackageName(int i) throws Resources.NotFoundException {
        String resourcePackageName = this.mAssets.getResourcePackageName(i);
        if (resourcePackageName != null) {
            return resourcePackageName;
        }
        throw new Resources.NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(i));
    }

    String getResourceTypeName(int i) throws Resources.NotFoundException {
        String resourceTypeName = this.mAssets.getResourceTypeName(i);
        if (resourceTypeName != null) {
            return resourceTypeName;
        }
        throw new Resources.NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(i));
    }

    String getResourceEntryName(int i) throws Resources.NotFoundException {
        String resourceEntryName = this.mAssets.getResourceEntryName(i);
        if (resourceEntryName != null) {
            return resourceEntryName;
        }
        throw new Resources.NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(i));
    }

    String getLastResourceResolution() throws Resources.NotFoundException {
        String lastResourceResolution = this.mAssets.getLastResourceResolution();
        if (lastResourceResolution != null) {
            return lastResourceResolution;
        }
        throw new Resources.NotFoundException("Associated AssetManager hasn't resolved a resource");
    }

    CharSequence getQuantityText(int i, int i2) throws Resources.NotFoundException {
        PluralRules pluralRule = getPluralRule();
        double d = i2;
        CharSequence resourceBagText = this.mAssets.getResourceBagText(i, attrForQuantityCode(pluralRule.select(d)));
        if (resourceBagText != null) {
            return resourceBagText;
        }
        CharSequence resourceBagText2 = this.mAssets.getResourceBagText(i, ID_OTHER);
        if (resourceBagText2 != null) {
            return resourceBagText2;
        }
        throw new Resources.NotFoundException("Plural resource ID #0x" + Integer.toHexString(i) + " quantity=" + i2 + " item=" + pluralRule.select(d));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int attrForQuantityCode(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = 4
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case 101272: goto L38;
                case 110182: goto L2d;
                case 115276: goto L22;
                case 3343967: goto L17;
                case 3735208: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L42
        Lc:
            java.lang.String r0 = "zero"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = 0
            goto L43
        L17:
            java.lang.String r0 = "many"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = r1
            goto L43
        L22:
            java.lang.String r0 = "two"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = r3
            goto L43
        L2d:
            java.lang.String r0 = "one"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = r4
            goto L43
        L38:
            java.lang.String r0 = "few"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = r2
            goto L43
        L42:
            r5 = -1
        L43:
            if (r5 == 0) goto L61
            if (r5 == r4) goto L5d
            if (r5 == r3) goto L59
            if (r5 == r2) goto L55
            if (r5 == r1) goto L51
            r5 = 16777220(0x1000004, float:2.3509898E-38)
            return r5
        L51:
            r5 = 16777225(0x1000009, float:2.3509912E-38)
            return r5
        L55:
            r5 = 16777224(0x1000008, float:2.350991E-38)
            return r5
        L59:
            r5 = 16777223(0x1000007, float:2.3509907E-38)
            return r5
        L5d:
            r5 = 16777222(0x1000006, float:2.3509904E-38)
            return r5
        L61:
            r5 = 16777221(0x1000005, float:2.35099E-38)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.res.ResourcesImpl.attrForQuantityCode(java.lang.String):int");
    }

    AssetFileDescriptor openRawResourceFd(int i, TypedValue typedValue) throws Resources.NotFoundException {
        getValue(i, typedValue, true);
        try {
            return this.mAssets.openNonAssetFd(typedValue.assetCookie, typedValue.string.toString());
        } catch (Exception e) {
            throw new Resources.NotFoundException("File " + typedValue.string.toString() + " from resource ID #0x" + Integer.toHexString(i), e);
        }
    }

    InputStream openRawResource(int i, TypedValue typedValue) throws Resources.NotFoundException {
        getValue(i, typedValue, true);
        try {
            return this.mAssets.openNonAsset(typedValue.assetCookie, typedValue.string.toString(), 2);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("File ");
            sb.append(typedValue.string == null ? "(null)" : typedValue.string.toString());
            sb.append(" from resource ID #0x");
            sb.append(Integer.toHexString(i));
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(sb.toString());
            notFoundException.initCause(e);
            throw notFoundException;
        }
    }

    ConfigurationBoundResourceCache<Animator> getAnimatorCache() {
        return this.mAnimatorCache;
    }

    ConfigurationBoundResourceCache<StateListAnimator> getStateListAnimatorCache() {
        return this.mStateListAnimatorCache;
    }

    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics, CompatibilityInfo compatibilityInfo) {
        long j;
        int i;
        int i2;
        Locale firstMatchWithEnglishSupported;
        Trace.traceBegin(8192L, "ResourcesImpl#updateConfiguration");
        try {
            synchronized (this.mAccessLock) {
                if (compatibilityInfo != null) {
                    try {
                        try {
                            this.mDisplayAdjustments.setCompatibilityInfo(compatibilityInfo);
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        j = 8192;
                        Trace.traceEnd(j);
                        throw th;
                    }
                }
                if (displayMetrics != null) {
                    this.mMetrics.setTo(displayMetrics);
                }
                this.mDisplayAdjustments.getCompatibilityInfo().applyToDisplayMetrics(this.mMetrics);
                int iCalcConfigChanges = calcConfigChanges(configuration);
                LocaleList locales = this.mConfiguration.getLocales();
                if (locales.isEmpty()) {
                    locales = LocaleList.getDefault();
                    this.mConfiguration.setLocales(locales);
                }
                if ((iCalcConfigChanges & 4) != 0 && locales.size() > 1) {
                    String[] nonSystemLocales = this.mAssets.getNonSystemLocales();
                    if (LocaleList.isPseudoLocalesOnly(nonSystemLocales)) {
                        nonSystemLocales = this.mAssets.getLocales();
                        if (LocaleList.isPseudoLocalesOnly(nonSystemLocales)) {
                            nonSystemLocales = null;
                        }
                    }
                    if (nonSystemLocales != null && (firstMatchWithEnglishSupported = locales.getFirstMatchWithEnglishSupported(nonSystemLocales)) != null && firstMatchWithEnglishSupported != locales.get(0)) {
                        this.mConfiguration.setLocales(new LocaleList(firstMatchWithEnglishSupported, locales));
                    }
                }
                if (this.mConfiguration.densityDpi != 0) {
                    this.mMetrics.densityDpi = this.mConfiguration.densityDpi;
                    this.mMetrics.density = this.mConfiguration.densityDpi * 0.00625f;
                }
                this.mMetrics.scaledDensity = this.mMetrics.density * (this.mConfiguration.fontScale != 0.0f ? this.mConfiguration.fontScale : 1.0f);
                if (this.mMetrics.widthPixels >= this.mMetrics.heightPixels) {
                    i = this.mMetrics.widthPixels;
                    i2 = this.mMetrics.heightPixels;
                } else {
                    i = this.mMetrics.heightPixels;
                    i2 = this.mMetrics.widthPixels;
                }
                this.mAssets.setConfiguration(this.mConfiguration.mcc, this.mConfiguration.mnc, adjustLanguageTag(this.mConfiguration.getLocales().get(0).toLanguageTag()), this.mConfiguration.orientation, this.mConfiguration.touchscreen, this.mConfiguration.densityDpi, this.mConfiguration.keyboard, (this.mConfiguration.keyboardHidden == 1 && this.mConfiguration.hardKeyboardHidden == 2) ? 3 : this.mConfiguration.keyboardHidden, this.mConfiguration.navigation, i, i2, this.mConfiguration.smallestScreenWidthDp, this.mConfiguration.screenWidthDp, this.mConfiguration.screenHeightDp, this.mConfiguration.screenLayout, this.mConfiguration.uiMode, this.mConfiguration.colorMode, Build.VERSION.RESOURCES_SDK_INT);
                this.mDrawableCache.onConfigurationChange(iCalcConfigChanges);
                this.mColorDrawableCache.onConfigurationChange(iCalcConfigChanges);
                this.mComplexColorCache.onConfigurationChange(iCalcConfigChanges);
                this.mAnimatorCache.onConfigurationChange(iCalcConfigChanges);
                this.mStateListAnimatorCache.onConfigurationChange(iCalcConfigChanges);
                flushLayoutCache();
            }
            synchronized (sSync) {
                if (this.mPluralRule != null) {
                    this.mPluralRule = PluralRules.forLocale(this.mConfiguration.getLocales().get(0));
                }
            }
            Trace.traceEnd(8192L);
        } catch (Throwable th3) {
            th = th3;
            j = 8192;
        }
    }

    public int calcConfigChanges(Configuration configuration) {
        if (configuration == null) {
            return -1;
        }
        this.mTmpConfig.setTo(configuration);
        int i = configuration.densityDpi;
        if (i == 0) {
            i = this.mMetrics.noncompatDensityDpi;
        }
        this.mDisplayAdjustments.getCompatibilityInfo().applyToConfiguration(i, this.mTmpConfig);
        if (this.mTmpConfig.getLocales().isEmpty()) {
            this.mTmpConfig.setLocales(LocaleList.getDefault());
        }
        return this.mConfiguration.updateFrom(this.mTmpConfig);
    }

    private static String adjustLanguageTag(String str) {
        String strSubstring;
        int iIndexOf = str.indexOf(45);
        if (iIndexOf == -1) {
            strSubstring = "";
        } else {
            String strSubstring2 = str.substring(0, iIndexOf);
            strSubstring = str.substring(iIndexOf);
            str = strSubstring2;
        }
        return Locale.adjustLanguageCode(str) + strSubstring;
    }

    public void flushLayoutCache() {
        synchronized (this.mCachedXmlBlocks) {
            Arrays.fill(this.mCachedXmlBlockCookies, 0);
            Arrays.fill(this.mCachedXmlBlockFiles, (Object) null);
            XmlBlock[] xmlBlockArr = this.mCachedXmlBlocks;
            for (int i = 0; i < 4; i++) {
                XmlBlock xmlBlock = xmlBlockArr[i];
                if (xmlBlock != null) {
                    xmlBlock.close();
                }
            }
            Arrays.fill(xmlBlockArr, (Object) null);
        }
    }

    public void clearAllCaches() {
        synchronized (this.mAccessLock) {
            this.mDrawableCache.clear();
            this.mColorDrawableCache.clear();
            this.mComplexColorCache.clear();
            this.mAnimatorCache.clear();
            this.mStateListAnimatorCache.clear();
            flushLayoutCache();
        }
    }

    Drawable loadDrawable(Resources resources, TypedValue typedValue, int i, int i2, Resources.Theme theme) throws Throwable {
        String resourceName;
        long j;
        DrawableCache drawableCache;
        boolean z;
        Drawable.ConstantState constantState;
        Drawable drawableLoadDrawableForCookie;
        Drawable.ConstantState constantState2;
        String resourceName2;
        Drawable drawableCache2;
        boolean z2 = i2 == 0 || typedValue.density == this.mMetrics.densityDpi;
        if (i2 > 0 && typedValue.density > 0 && typedValue.density != 65535) {
            if (typedValue.density == i2) {
                typedValue.density = this.mMetrics.densityDpi;
            } else {
                typedValue.density = (typedValue.density * this.mMetrics.densityDpi) / i2;
            }
        }
        try {
            if (typedValue.type >= 28 && typedValue.type <= 31) {
                DrawableCache drawableCache3 = this.mColorDrawableCache;
                j = typedValue.data;
                drawableCache = drawableCache3;
                z = true;
            } else {
                DrawableCache drawableCache4 = this.mDrawableCache;
                j = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
                drawableCache = drawableCache4;
                z = false;
            }
            if (!this.mPreloading && z2 && (drawableCache2 = drawableCache.getInstance(j, resources, theme)) != null) {
                drawableCache2.setChangingConfigurations(typedValue.changingConfigurations);
                return drawableCache2;
            }
            if (z) {
                constantState = sPreloadedColorDrawables.get(j);
            } else {
                constantState = sPreloadedDrawables[this.mConfiguration.getLayoutDirection()].get(j);
            }
            if (constantState != null) {
                if (TRACE_FOR_DETAILED_PRELOAD && (i >>> 24) == 1 && Process.myUid() != 0 && (resourceName2 = getResourceName(i)) != null) {
                    Log.d(TAG_PRELOAD, "Hit preloaded FW drawable #" + Integer.toHexString(i) + " " + resourceName2);
                }
                drawableLoadDrawableForCookie = constantState.newDrawable(resources);
            } else if (z) {
                drawableLoadDrawableForCookie = new ColorDrawable(typedValue.data);
            } else {
                drawableLoadDrawableForCookie = loadDrawableForCookie(resources, typedValue, i, i2);
            }
            boolean z3 = drawableLoadDrawableForCookie instanceof DrawableContainer;
            boolean z4 = drawableLoadDrawableForCookie != null && drawableLoadDrawableForCookie.canApplyTheme();
            if (z4 && theme != null) {
                drawableLoadDrawableForCookie = drawableLoadDrawableForCookie.mutate();
                drawableLoadDrawableForCookie.applyTheme(theme);
                drawableLoadDrawableForCookie.clearMutated();
            }
            Drawable drawable = drawableLoadDrawableForCookie;
            if (drawable == null) {
                return drawable;
            }
            drawable.setChangingConfigurations(typedValue.changingConfigurations);
            if (!z2) {
                return drawable;
            }
            cacheDrawable(typedValue, z, drawableCache, theme, z4, j, drawable);
            return (!z3 || (constantState2 = drawable.getConstantState()) == null) ? drawable : constantState2.newDrawable(resources);
        } catch (Exception e) {
            try {
                resourceName = getResourceName(i);
            } catch (Resources.NotFoundException unused) {
                resourceName = "(missing name)";
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException("Drawable " + resourceName + " with resource ID #0x" + Integer.toHexString(i), e);
            notFoundException.setStackTrace(new StackTraceElement[0]);
            throw notFoundException;
        }
    }

    private void cacheDrawable(TypedValue typedValue, boolean z, DrawableCache drawableCache, Resources.Theme theme, boolean z2, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return;
        }
        if (this.mPreloading) {
            int changingConfigurations = constantState.getChangingConfigurations();
            if (z) {
                if (verifyPreloadConfig(changingConfigurations, 0, typedValue.resourceId, AttrFactory.DRAWABLE)) {
                    sPreloadedColorDrawables.put(j, constantState);
                    return;
                }
                return;
            } else {
                if (verifyPreloadConfig(changingConfigurations, 8192, typedValue.resourceId, AttrFactory.DRAWABLE)) {
                    if ((changingConfigurations & 8192) == 0) {
                        sPreloadedDrawables[0].put(j, constantState);
                        sPreloadedDrawables[1].put(j, constantState);
                        return;
                    } else {
                        sPreloadedDrawables[this.mConfiguration.getLayoutDirection()].put(j, constantState);
                        return;
                    }
                }
                return;
            }
        }
        synchronized (this.mAccessLock) {
            drawableCache.put(j, theme, constantState, z2);
        }
    }

    private boolean verifyPreloadConfig(int i, int i2, int i3, String str) {
        String resourceName;
        if ((i & (-1073745921) & (~i2)) == 0) {
            return true;
        }
        try {
            resourceName = getResourceName(i3);
        } catch (Resources.NotFoundException unused) {
            resourceName = "?";
        }
        Log.w(TAG, "Preloaded " + str + " resource #0x" + Integer.toHexString(i3) + " (" + resourceName + ") that varies with configuration!!");
        return false;
    }

    private Drawable decodeImageDrawable(AssetManager.AssetInputStream assetInputStream, Resources resources, TypedValue typedValue) {
        try {
            return ImageDecoder.decodeDrawable(new ImageDecoder.AssetInputStreamSource(assetInputStream, resources, typedValue), new ImageDecoder.OnHeaderDecodedListener() { // from class: android.content.res.-$$Lambda$ResourcesImpl$99dm2ENnzo9b0SIUjUj2Kl3pi90
                @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                    imageDecoder.setAllocator(1);
                }
            });
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006d A[Catch: all -> 0x0123, TryCatch #1 {all -> 0x0123, blocks: (B:19:0x0068, B:20:0x006d, B:21:0x0081), top: B:58:0x0049 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.drawable.Drawable loadDrawableForCookie(android.content.res.Resources r21, android.util.TypedValue r22, int r23, int r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 409
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.res.ResourcesImpl.loadDrawableForCookie(android.content.res.Resources, android.util.TypedValue, int, int):android.graphics.drawable.Drawable");
    }

    private Drawable loadColorOrXmlDrawable(Resources resources, TypedValue typedValue, int i, int i2, String str) {
        try {
            return new ColorStateListDrawable(loadColorStateList(resources, typedValue, i, null));
        } catch (Resources.NotFoundException e) {
            try {
                return loadXmlDrawable(resources, typedValue, i, i2, str);
            } catch (Exception unused) {
                throw e;
            }
        }
    }

    private Drawable loadXmlDrawable(Resources resources, TypedValue typedValue, int i, int i2, String str) throws XmlPullParserException, IOException {
        XmlResourceParser xmlResourceParserLoadXmlResourceParser = loadXmlResourceParser(str, i, typedValue.assetCookie, AttrFactory.DRAWABLE);
        try {
            Drawable drawableCreateFromXmlForDensity = Drawable.createFromXmlForDensity(resources, xmlResourceParserLoadXmlResourceParser, i2, null);
            if (xmlResourceParserLoadXmlResourceParser != null) {
                xmlResourceParserLoadXmlResourceParser.close();
            }
            return drawableCreateFromXmlForDensity;
        } catch (Throwable th) {
            if (xmlResourceParserLoadXmlResourceParser != null) {
                try {
                    xmlResourceParserLoadXmlResourceParser.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public Typeface loadFont(Resources resources, TypedValue typedValue, int i) {
        if (typedValue.string == null) {
            throw new Resources.NotFoundException("Resource \"" + getResourceName(i) + "\" (" + Integer.toHexString(i) + ") is not a Font: " + typedValue);
        }
        String string = typedValue.string.toString();
        if (!string.startsWith("res/")) {
            return null;
        }
        Typeface typefaceFindFromCache = Typeface.findFromCache(this.mAssets, string);
        if (typefaceFindFromCache != null) {
            return typefaceFindFromCache;
        }
        Trace.traceBegin(8192L, string);
        try {
            try {
                if (!string.endsWith("xml")) {
                    return new Typeface.Builder(this.mAssets, string, false, typedValue.assetCookie).build();
                }
                FontResourcesParser.FamilyResourceEntry familyResourceEntry = FontResourcesParser.parse(loadXmlResourceParser(string, i, typedValue.assetCookie, "font"), resources);
                if (familyResourceEntry == null) {
                    return null;
                }
                return Typeface.createFromResources(familyResourceEntry, this.mAssets, string);
            } catch (IOException e) {
                Log.e(TAG, "Failed to read xml resource " + string, e);
                return null;
            } catch (XmlPullParserException e2) {
                Log.e(TAG, "Failed to parse xml resource " + string, e2);
                return null;
            }
        } finally {
            Trace.traceEnd(8192L);
        }
    }

    private ComplexColor loadComplexColorFromName(Resources resources, Resources.Theme theme, TypedValue typedValue, int i) {
        long j = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        ConfigurationBoundResourceCache<ComplexColor> configurationBoundResourceCache = this.mComplexColorCache;
        ComplexColor configurationBoundResourceCache2 = configurationBoundResourceCache.getInstance(j, resources, theme);
        if (configurationBoundResourceCache2 != null) {
            return configurationBoundResourceCache2;
        }
        ConstantState<ComplexColor> constantState = sPreloadedComplexColors.get(j);
        if (constantState != null) {
            configurationBoundResourceCache2 = constantState.newInstance2(resources, theme);
        }
        if (configurationBoundResourceCache2 == null) {
            configurationBoundResourceCache2 = loadComplexColorForCookie(resources, typedValue, i, theme);
        }
        if (configurationBoundResourceCache2 != null) {
            configurationBoundResourceCache2.setBaseChangingConfigurations(typedValue.changingConfigurations);
            if (this.mPreloading) {
                if (verifyPreloadConfig(configurationBoundResourceCache2.getChangingConfigurations(), 0, typedValue.resourceId, "color")) {
                    sPreloadedComplexColors.put(j, configurationBoundResourceCache2.getConstantState());
                }
            } else {
                configurationBoundResourceCache.put(j, theme, configurationBoundResourceCache2.getConstantState());
            }
        }
        return configurationBoundResourceCache2;
    }

    ComplexColor loadComplexColor(Resources resources, TypedValue typedValue, int i, Resources.Theme theme) {
        long j = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        if (typedValue.type >= 28 && typedValue.type <= 31) {
            return getColorStateListFromInt(typedValue, j);
        }
        String string = typedValue.string.toString();
        if (string.endsWith(".xml")) {
            try {
                return loadComplexColorFromName(resources, theme, typedValue, i);
            } catch (Exception e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("File " + string + " from complex color resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            }
        }
        throw new Resources.NotFoundException("File " + string + " from drawable resource ID #0x" + Integer.toHexString(i) + ": .xml extension required");
    }

    ColorStateList loadColorStateList(Resources resources, TypedValue typedValue, int i, Resources.Theme theme) throws Resources.NotFoundException {
        long j = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        if (typedValue.type >= 28 && typedValue.type <= 31) {
            return getColorStateListFromInt(typedValue, j);
        }
        ComplexColor complexColorLoadComplexColorFromName = loadComplexColorFromName(resources, theme, typedValue, i);
        if (complexColorLoadComplexColorFromName != null && (complexColorLoadComplexColorFromName instanceof ColorStateList)) {
            return (ColorStateList) complexColorLoadComplexColorFromName;
        }
        throw new Resources.NotFoundException("Can't find ColorStateList from drawable resource ID #0x" + Integer.toHexString(i));
    }

    private ColorStateList getColorStateListFromInt(TypedValue typedValue, long j) {
        ConstantState<ComplexColor> constantState = sPreloadedComplexColors.get(j);
        if (constantState != null) {
            return (ColorStateList) constantState.newInstance2();
        }
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(typedValue.data);
        if (this.mPreloading && verifyPreloadConfig(typedValue.changingConfigurations, 0, typedValue.resourceId, "color")) {
            sPreloadedComplexColors.put(j, colorStateListValueOf.getConstantState());
        }
        return colorStateListValueOf;
    }

    private ComplexColor loadComplexColorForCookie(Resources resources, TypedValue typedValue, int i, Resources.Theme theme) {
        int next;
        if (typedValue.string == null) {
            throw new UnsupportedOperationException("Can't convert to ComplexColor: type=0x" + typedValue.type);
        }
        String string = typedValue.string.toString();
        ComplexColor complexColorCreateFromXmlInner = null;
        Trace.traceBegin(8192L, string);
        if (string.endsWith(".xml")) {
            try {
                XmlResourceParser xmlResourceParserLoadXmlResourceParser = loadXmlResourceParser(string, i, typedValue.assetCookie, "ComplexColor");
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlResourceParserLoadXmlResourceParser);
                do {
                    next = xmlResourceParserLoadXmlResourceParser.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xmlResourceParserLoadXmlResourceParser.getName();
                if (name.equals("gradient")) {
                    complexColorCreateFromXmlInner = GradientColor.createFromXmlInner(resources, xmlResourceParserLoadXmlResourceParser, attributeSetAsAttributeSet, theme);
                } else if (name.equals("selector")) {
                    complexColorCreateFromXmlInner = ColorStateList.createFromXmlInner(resources, xmlResourceParserLoadXmlResourceParser, attributeSetAsAttributeSet, theme);
                }
                xmlResourceParserLoadXmlResourceParser.close();
                Trace.traceEnd(8192L);
                return complexColorCreateFromXmlInner;
            } catch (Exception e) {
                Trace.traceEnd(8192L);
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("File " + string + " from ComplexColor resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            }
        }
        Trace.traceEnd(8192L);
        throw new Resources.NotFoundException("File " + string + " from drawable resource ID #0x" + Integer.toHexString(i) + ": .xml extension required");
    }

    XmlResourceParser loadXmlResourceParser(String str, int i, int i2, String str2) throws Resources.NotFoundException {
        if (i != 0) {
            try {
                synchronized (this.mCachedXmlBlocks) {
                    int[] iArr = this.mCachedXmlBlockCookies;
                    String[] strArr = this.mCachedXmlBlockFiles;
                    XmlBlock[] xmlBlockArr = this.mCachedXmlBlocks;
                    int length = strArr.length;
                    for (int i3 = 0; i3 < length; i3++) {
                        if (iArr[i3] == i2 && strArr[i3] != null && strArr[i3].equals(str)) {
                            return xmlBlockArr[i3].newParser(i);
                        }
                    }
                    XmlBlock xmlBlockOpenXmlBlockAsset = this.mAssets.openXmlBlockAsset(i2, str);
                    if (xmlBlockOpenXmlBlockAsset != null) {
                        int i4 = (this.mLastCachedXmlBlockIndex + 1) % length;
                        this.mLastCachedXmlBlockIndex = i4;
                        XmlBlock xmlBlock = xmlBlockArr[i4];
                        if (xmlBlock != null) {
                            xmlBlock.close();
                        }
                        iArr[i4] = i2;
                        strArr[i4] = str;
                        xmlBlockArr[i4] = xmlBlockOpenXmlBlockAsset;
                        return xmlBlockOpenXmlBlockAsset.newParser(i);
                    }
                }
            } catch (Exception e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("File " + str + " from xml type " + str2 + " resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            }
        }
        throw new Resources.NotFoundException("File " + str + " from xml type " + str2 + " resource ID #0x" + Integer.toHexString(i));
    }

    public final void startPreloading() {
        synchronized (sSync) {
            if (sPreloaded) {
                throw new IllegalStateException("Resources already preloaded");
            }
            sPreloaded = true;
            this.mPreloading = true;
            this.mConfiguration.densityDpi = DisplayMetrics.DENSITY_DEVICE;
            updateConfiguration(null, null, null);
            if (TRACE_FOR_DETAILED_PRELOAD) {
                this.mPreloadTracingPreloadStartTime = SystemClock.uptimeMillis();
                this.mPreloadTracingStartBitmapSize = Bitmap.sPreloadTracingTotalBitmapsSize;
                this.mPreloadTracingStartBitmapCount = Bitmap.sPreloadTracingNumInstantiatedBitmaps;
                Log.d(TAG_PRELOAD, "Preload starting");
            }
        }
    }

    void finishPreloading() {
        if (this.mPreloading) {
            if (TRACE_FOR_DETAILED_PRELOAD) {
                long jUptimeMillis = SystemClock.uptimeMillis() - this.mPreloadTracingPreloadStartTime;
                long j = Bitmap.sPreloadTracingTotalBitmapsSize - this.mPreloadTracingStartBitmapSize;
                Log.d(TAG_PRELOAD, "Preload finished, " + (((long) Bitmap.sPreloadTracingNumInstantiatedBitmaps) - this.mPreloadTracingStartBitmapCount) + " bitmaps of " + j + " bytes in " + jUptimeMillis + " ms");
            }
            this.mPreloading = false;
            flushLayoutCache();
        }
    }

    static int getAttributeSetSourceResId(AttributeSet attributeSet) {
        if (attributeSet == null || !(attributeSet instanceof XmlBlock.Parser)) {
            return 0;
        }
        return ((XmlBlock.Parser) attributeSet).getSourceResId();
    }

    LongSparseArray<Drawable.ConstantState> getPreloadedDrawables() {
        return sPreloadedDrawables[0];
    }

    ThemeImpl newThemeImpl() {
        return new ThemeImpl();
    }

    ThemeImpl newThemeImpl(Resources.ThemeKey themeKey) {
        ThemeImpl themeImpl = new ThemeImpl();
        themeImpl.mKey.setTo(themeKey);
        themeImpl.rebase();
        return themeImpl;
    }

    public class ThemeImpl {
        private final AssetManager mAssets;
        private final long mTheme;
        private final Resources.ThemeKey mKey = new Resources.ThemeKey();
        private int mThemeResId = 0;

        ThemeImpl() {
            AssetManager assetManager = ResourcesImpl.this.mAssets;
            this.mAssets = assetManager;
            this.mTheme = assetManager.createTheme();
        }

        protected void finalize() throws Throwable {
            super.finalize();
            this.mAssets.releaseTheme(this.mTheme);
        }

        Resources.ThemeKey getKey() {
            return this.mKey;
        }

        long getNativeTheme() {
            return this.mTheme;
        }

        int getAppliedStyleResId() {
            return this.mThemeResId;
        }

        void applyStyle(int i, boolean z) {
            synchronized (this.mKey) {
                this.mAssets.applyStyleToTheme(this.mTheme, i, z);
                this.mThemeResId = i;
                this.mKey.append(i, z);
            }
        }

        void setTo(ThemeImpl themeImpl) {
            synchronized (this.mKey) {
                synchronized (themeImpl.mKey) {
                    this.mAssets.setThemeTo(this.mTheme, themeImpl.mAssets, themeImpl.mTheme);
                    this.mThemeResId = themeImpl.mThemeResId;
                    this.mKey.setTo(themeImpl.getKey());
                }
            }
        }

        TypedArray obtainStyledAttributes(Resources.Theme theme, AttributeSet attributeSet, int[] iArr, int i, int i2) {
            TypedArray typedArrayObtain;
            synchronized (this.mKey) {
                typedArrayObtain = TypedArray.obtain(theme.getResources(), iArr.length);
                XmlBlock.Parser parser = (XmlBlock.Parser) attributeSet;
                this.mAssets.applyStyle(this.mTheme, i, i2, parser, iArr, typedArrayObtain.mDataAddress, typedArrayObtain.mIndicesAddress);
                typedArrayObtain.mTheme = theme;
                typedArrayObtain.mXml = parser;
            }
            return typedArrayObtain;
        }

        TypedArray resolveAttributes(Resources.Theme theme, int[] iArr, int[] iArr2) {
            TypedArray typedArrayObtain;
            synchronized (this.mKey) {
                int length = iArr2.length;
                if (iArr == null || length != iArr.length) {
                    throw new IllegalArgumentException("Base attribute values must the same length as attrs");
                }
                typedArrayObtain = TypedArray.obtain(theme.getResources(), length);
                this.mAssets.resolveAttrs(this.mTheme, 0, 0, iArr, iArr2, typedArrayObtain.mData, typedArrayObtain.mIndices);
                typedArrayObtain.mTheme = theme;
                typedArrayObtain.mXml = null;
            }
            return typedArrayObtain;
        }

        boolean resolveAttribute(int i, TypedValue typedValue, boolean z) {
            boolean themeValue;
            synchronized (this.mKey) {
                themeValue = this.mAssets.getThemeValue(this.mTheme, i, typedValue, z);
            }
            return themeValue;
        }

        int[] getAllAttributes() {
            return this.mAssets.getStyleAttributes(getAppliedStyleResId());
        }

        int getChangingConfigurations() {
            int iActivityInfoConfigNativeToJava;
            synchronized (this.mKey) {
                iActivityInfoConfigNativeToJava = ActivityInfo.activityInfoConfigNativeToJava(AssetManager.nativeThemeGetChangingConfigurations(this.mTheme));
            }
            return iActivityInfoConfigNativeToJava;
        }

        public void dump(int i, String str, String str2) {
            synchronized (this.mKey) {
                this.mAssets.dumpTheme(this.mTheme, i, str, str2);
            }
        }

        String[] getTheme() {
            String[] strArr;
            synchronized (this.mKey) {
                int i = this.mKey.mCount;
                int i2 = i * 2;
                strArr = new String[i2];
                int i3 = 0;
                int i4 = i - 1;
                while (i3 < i2) {
                    int i5 = this.mKey.mResId[i4];
                    boolean z = this.mKey.mForce[i4];
                    try {
                        strArr[i3] = ResourcesImpl.this.getResourceName(i5);
                    } catch (Resources.NotFoundException unused) {
                        strArr[i3] = Integer.toHexString(i3);
                    }
                    strArr[i3 + 1] = z ? "forced" : "not forced";
                    i3 += 2;
                    i4--;
                }
            }
            return strArr;
        }

        void rebase() {
            synchronized (this.mKey) {
                AssetManager.nativeThemeClear(this.mTheme);
                for (int i = 0; i < this.mKey.mCount; i++) {
                    this.mAssets.applyStyleToTheme(this.mTheme, this.mKey.mResId[i], this.mKey.mForce[i]);
                }
            }
        }

        public int[] getAttributeResolutionStack(int i, int i2, int i3) {
            int[] attributeResolutionStack;
            synchronized (this.mKey) {
                attributeResolutionStack = this.mAssets.getAttributeResolutionStack(this.mTheme, i, i2, i3);
            }
            return attributeResolutionStack;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class LookupStack {
        private int[] mIds;
        private int mSize;

        private LookupStack() {
            this.mIds = new int[4];
            this.mSize = 0;
        }

        public void push(int i) {
            this.mIds = GrowingArrayUtils.append(this.mIds, this.mSize, i);
            this.mSize++;
        }

        public boolean contains(int i) {
            for (int i2 = 0; i2 < this.mSize; i2++) {
                if (this.mIds[i2] == i) {
                    return true;
                }
            }
            return false;
        }

        public void pop() {
            this.mSize--;
        }
    }
}
