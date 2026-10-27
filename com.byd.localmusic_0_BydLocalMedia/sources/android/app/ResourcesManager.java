package android.app;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageParser;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.content.res.CompatResources;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.ResourcesImpl;
import android.content.res.ResourcesKey;
import android.content.res.loader.ResourcesLoader;
import android.hardware.display.DisplayManagerGlobal;
import android.os.IBinder;
import android.os.Process;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.Slog;
import android.view.Display;
import android.view.DisplayAdjustments;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.IndentingPrintWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class ResourcesManager {
    private static final boolean DEBUG = false;
    static final String TAG = "ResourcesManager";
    private static ResourcesManager sResourcesManager;
    private CompatibilityInfo mResCompatibilityInfo;
    private final Configuration mResConfiguration = new Configuration();
    private final ArrayMap<ResourcesKey, WeakReference<ResourcesImpl>> mResourceImpls = new ArrayMap<>();
    private final ArrayList<WeakReference<Resources>> mResourceReferences = new ArrayList<>();
    private final ReferenceQueue<Resources> mResourcesReferencesQueue = new ReferenceQueue<>();
    private final ArrayMap<ApkKey, WeakReference<ApkAssets>> mCachedApkAssets = new ArrayMap<>();
    private final WeakHashMap<IBinder, ActivityResources> mActivityResourceReferences = new WeakHashMap<>();
    private final ArrayMap<Pair<Integer, DisplayAdjustments>, WeakReference<Display>> mAdjustedDisplays = new ArrayMap<>();
    private final UpdateHandler mUpdateCallbacks = new UpdateHandler();

    private static class ApkKey {
        public final boolean overlay;
        public final String path;
        public final boolean sharedLib;

        ApkKey(String str, boolean z, boolean z2) {
            this.path = str;
            this.sharedLib = z;
            this.overlay = z2;
        }

        public int hashCode() {
            return ((((this.path.hashCode() + 31) * 31) + Boolean.hashCode(this.sharedLib)) * 31) + Boolean.hashCode(this.overlay);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ApkKey)) {
                return false;
            }
            ApkKey apkKey = (ApkKey) obj;
            return this.path.equals(apkKey.path) && this.sharedLib == apkKey.sharedLib && this.overlay == apkKey.overlay;
        }
    }

    private class ApkAssetsSupplier {
        final ArrayMap<ApkKey, ApkAssets> mLocalCache;

        private ApkAssetsSupplier() {
            this.mLocalCache = new ArrayMap<>();
        }

        ApkAssets load(ApkKey apkKey) throws IOException {
            ApkAssets apkAssets = this.mLocalCache.get(apkKey);
            if (apkAssets != null) {
                return apkAssets;
            }
            ApkAssets apkAssetsLoadApkAssets = ResourcesManager.this.loadApkAssets(apkKey);
            this.mLocalCache.put(apkKey, apkAssetsLoadApkAssets);
            return apkAssetsLoadApkAssets;
        }
    }

    private static class ActivityResources {
        public final ArrayList<WeakReference<Resources>> activityResources;
        final ReferenceQueue<Resources> activityResourcesQueue;
        public final Configuration overrideConfig;

        private ActivityResources() {
            this.overrideConfig = new Configuration();
            this.activityResources = new ArrayList<>();
            this.activityResourcesQueue = new ReferenceQueue<>();
        }
    }

    public static ResourcesManager getInstance() {
        ResourcesManager resourcesManager;
        synchronized (ResourcesManager.class) {
            if (sResourcesManager == null) {
                sResourcesManager = new ResourcesManager();
            }
            resourcesManager = sResourcesManager;
        }
        return resourcesManager;
    }

    public void invalidatePath(String str) {
        WeakReference<ApkAssets> weakReferenceRemoveAt;
        synchronized (this) {
            int i = 0;
            for (int size = this.mResourceImpls.size() - 1; size >= 0; size--) {
                if (this.mResourceImpls.keyAt(size).isPathReferenced(str)) {
                    ResourcesImpl resourcesImpl = this.mResourceImpls.removeAt(size).get();
                    if (resourcesImpl != null) {
                        resourcesImpl.flushLayoutCache();
                    }
                    i++;
                }
            }
            Log.i(TAG, "Invalidated " + i + " asset managers that referenced " + str);
            for (int size2 = this.mCachedApkAssets.size() + (-1); size2 >= 0; size2--) {
                if (this.mCachedApkAssets.keyAt(size2).path.equals(str) && (weakReferenceRemoveAt = this.mCachedApkAssets.removeAt(size2)) != null && weakReferenceRemoveAt.get() != null) {
                    weakReferenceRemoveAt.get().close();
                }
            }
        }
    }

    public Configuration getConfiguration() {
        Configuration configuration;
        synchronized (this) {
            configuration = this.mResConfiguration;
        }
        return configuration;
    }

    DisplayMetrics getDisplayMetrics() {
        return getDisplayMetrics(0, DisplayAdjustments.DEFAULT_DISPLAY_ADJUSTMENTS);
    }

    protected DisplayMetrics getDisplayMetrics(int i, DisplayAdjustments displayAdjustments) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display adjustedDisplay = getAdjustedDisplay(i, displayAdjustments);
        if (adjustedDisplay != null) {
            adjustedDisplay.getMetrics(displayMetrics);
        } else {
            displayMetrics.setToDefaults();
        }
        return displayMetrics;
    }

    private static void applyNonDefaultDisplayMetricsToConfiguration(DisplayMetrics displayMetrics, Configuration configuration) {
        configuration.touchscreen = 1;
        configuration.densityDpi = displayMetrics.densityDpi;
        configuration.screenWidthDp = (int) (displayMetrics.widthPixels / displayMetrics.density);
        configuration.screenHeightDp = (int) (displayMetrics.heightPixels / displayMetrics.density);
        int iResetScreenLayout = Configuration.resetScreenLayout(configuration.screenLayout);
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            configuration.orientation = 2;
            configuration.screenLayout = Configuration.reduceScreenLayout(iResetScreenLayout, configuration.screenWidthDp, configuration.screenHeightDp);
        } else {
            configuration.orientation = 1;
            configuration.screenLayout = Configuration.reduceScreenLayout(iResetScreenLayout, configuration.screenHeightDp, configuration.screenWidthDp);
        }
        configuration.smallestScreenWidthDp = Math.min(configuration.screenWidthDp, configuration.screenHeightDp);
        configuration.compatScreenWidthDp = configuration.screenWidthDp;
        configuration.compatScreenHeightDp = configuration.screenHeightDp;
        configuration.compatSmallestScreenWidthDp = configuration.smallestScreenWidthDp;
    }

    public boolean applyCompatConfigurationLocked(int i, Configuration configuration) {
        CompatibilityInfo compatibilityInfo = this.mResCompatibilityInfo;
        if (compatibilityInfo == null || compatibilityInfo.supportsScreen()) {
            return false;
        }
        this.mResCompatibilityInfo.applyToConfiguration(i, configuration);
        return true;
    }

    private Display getAdjustedDisplay(int i, DisplayAdjustments displayAdjustments) {
        Display display;
        Pair<Integer, DisplayAdjustments> pairCreate = Pair.create(Integer.valueOf(i), displayAdjustments != null ? new DisplayAdjustments(displayAdjustments) : new DisplayAdjustments());
        synchronized (this) {
            WeakReference<Display> weakReference = this.mAdjustedDisplays.get(pairCreate);
            if (weakReference != null && (display = weakReference.get()) != null) {
                return display;
            }
            DisplayManagerGlobal displayManagerGlobal = DisplayManagerGlobal.getInstance();
            if (displayManagerGlobal == null) {
                return null;
            }
            Display compatibleDisplay = displayManagerGlobal.getCompatibleDisplay(i, pairCreate.second);
            if (compatibleDisplay != null) {
                this.mAdjustedDisplays.put(pairCreate, new WeakReference<>(compatibleDisplay));
            }
            return compatibleDisplay;
        }
    }

    public Display getAdjustedDisplay(int i, Resources resources) {
        synchronized (this) {
            DisplayManagerGlobal displayManagerGlobal = DisplayManagerGlobal.getInstance();
            if (displayManagerGlobal == null) {
                return null;
            }
            return displayManagerGlobal.getCompatibleDisplay(i, resources);
        }
    }

    private static String overlayPathToIdmapPath(String str) {
        return "/data/resource-cache/" + str.substring(1).replace('/', '@') + "@idmap";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ApkAssets loadApkAssets(ApkKey apkKey) throws IOException {
        ApkAssets apkAssetsLoadFromPath;
        synchronized (this) {
            WeakReference<ApkAssets> weakReference = this.mCachedApkAssets.get(apkKey);
            if (weakReference != null) {
                ApkAssets apkAssets = weakReference.get();
                if (apkAssets != null && apkAssets.isUpToDate()) {
                    return apkAssets;
                }
                this.mCachedApkAssets.remove(apkKey);
            }
            if (apkKey.overlay) {
                apkAssetsLoadFromPath = ApkAssets.loadOverlayFromPath(overlayPathToIdmapPath(apkKey.path), 0);
            } else {
                apkAssetsLoadFromPath = ApkAssets.loadFromPath(apkKey.path, apkKey.sharedLib ? 2 : 0);
            }
            synchronized (this) {
                this.mCachedApkAssets.put(apkKey, new WeakReference<>(apkAssetsLoadFromPath));
            }
            return apkAssetsLoadFromPath;
        }
    }

    private static ArrayList<ApkKey> extractApkKeys(ResourcesKey resourcesKey) {
        ArrayList<ApkKey> arrayList = new ArrayList<>();
        if (resourcesKey.mResDir != null) {
            arrayList.add(new ApkKey(resourcesKey.mResDir, false, false));
        }
        if (resourcesKey.mSplitResDirs != null) {
            for (String str : resourcesKey.mSplitResDirs) {
                arrayList.add(new ApkKey(str, false, false));
            }
        }
        if (resourcesKey.mLibDirs != null) {
            for (String str2 : resourcesKey.mLibDirs) {
                if (str2.endsWith(PackageParser.APK_FILE_EXTENSION)) {
                    arrayList.add(new ApkKey(str2, true, false));
                }
            }
        }
        if (resourcesKey.mOverlayDirs != null) {
            for (String str3 : resourcesKey.mOverlayDirs) {
                arrayList.add(new ApkKey(str3, false, true));
            }
        }
        return arrayList;
    }

    protected AssetManager createAssetManager(ResourcesKey resourcesKey) {
        return createAssetManager(resourcesKey, null);
    }

    private AssetManager createAssetManager(ResourcesKey resourcesKey, ApkAssetsSupplier apkAssetsSupplier) {
        ApkAssets apkAssetsLoad;
        AssetManager.Builder builder = new AssetManager.Builder();
        ArrayList<ApkKey> arrayListExtractApkKeys = extractApkKeys(resourcesKey);
        int size = arrayListExtractApkKeys.size();
        for (int i = 0; i < size; i++) {
            ApkKey apkKey = arrayListExtractApkKeys.get(i);
            if (apkAssetsSupplier != null) {
                try {
                    apkAssetsLoad = apkAssetsSupplier.load(apkKey);
                } catch (IOException e) {
                    if (apkKey.overlay) {
                        Log.w(TAG, String.format("failed to add overlay path '%s'", apkKey.path), e);
                    } else {
                        if (!apkKey.sharedLib) {
                            Log.e(TAG, String.format("failed to add asset path '%s'", apkKey.path), e);
                            return null;
                        }
                        Log.w(TAG, String.format("asset path '%s' does not exist or contains no resources", apkKey.path), e);
                    }
                }
            } else {
                apkAssetsLoad = loadApkAssets(apkKey);
            }
            builder.addApkAssets(apkAssetsLoad);
        }
        if (resourcesKey.mLoaders != null) {
            for (ResourcesLoader resourcesLoader : resourcesKey.mLoaders) {
                builder.addLoader(resourcesLoader);
            }
        }
        return builder.build();
    }

    private static <T> int countLiveReferences(Collection<WeakReference<T>> collection) {
        Iterator<WeakReference<T>> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            WeakReference<T> next = it.next();
            if ((next != null ? next.get() : null) != null) {
                i++;
            }
        }
        return i;
    }

    public void dump(String str, PrintWriter printWriter) {
        synchronized (this) {
            IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
            for (int i = 0; i < str.length() / 2; i++) {
                indentingPrintWriter.increaseIndent();
            }
            indentingPrintWriter.println("ResourcesManager:");
            indentingPrintWriter.increaseIndent();
            indentingPrintWriter.print("total apks: ");
            indentingPrintWriter.println(countLiveReferences(this.mCachedApkAssets.values()));
            indentingPrintWriter.print("resources: ");
            int iCountLiveReferences = countLiveReferences(this.mResourceReferences);
            Iterator<ActivityResources> it = this.mActivityResourceReferences.values().iterator();
            while (it.hasNext()) {
                iCountLiveReferences += countLiveReferences(it.next().activityResources);
            }
            indentingPrintWriter.println(iCountLiveReferences);
            indentingPrintWriter.print("resource impls: ");
            indentingPrintWriter.println(countLiveReferences(this.mResourceImpls.values()));
        }
    }

    private Configuration generateConfig(ResourcesKey resourcesKey, DisplayMetrics displayMetrics) {
        boolean z = resourcesKey.mDisplayId == 0;
        boolean zHasOverrideConfiguration = resourcesKey.hasOverrideConfiguration();
        if (!z || zHasOverrideConfiguration) {
            Configuration configuration = new Configuration(getConfiguration());
            if (!z) {
                applyNonDefaultDisplayMetricsToConfiguration(displayMetrics, configuration);
            }
            if (zHasOverrideConfiguration) {
                configuration.updateFrom(resourcesKey.mOverrideConfiguration);
            }
            return configuration;
        }
        return getConfiguration();
    }

    private ResourcesImpl createResourcesImpl(ResourcesKey resourcesKey, ApkAssetsSupplier apkAssetsSupplier) {
        DisplayAdjustments displayAdjustments = new DisplayAdjustments(resourcesKey.mOverrideConfiguration);
        displayAdjustments.setCompatibilityInfo(resourcesKey.mCompatInfo);
        AssetManager assetManagerCreateAssetManager = createAssetManager(resourcesKey, apkAssetsSupplier);
        if (assetManagerCreateAssetManager == null) {
            return null;
        }
        DisplayMetrics displayMetrics = getDisplayMetrics(resourcesKey.mDisplayId, displayAdjustments);
        return new ResourcesImpl(assetManagerCreateAssetManager, displayMetrics, generateConfig(resourcesKey, displayMetrics), displayAdjustments);
    }

    private ResourcesImpl findResourcesImplForKeyLocked(ResourcesKey resourcesKey) {
        WeakReference<ResourcesImpl> weakReference = this.mResourceImpls.get(resourcesKey);
        ResourcesImpl resourcesImpl = weakReference != null ? weakReference.get() : null;
        if (resourcesImpl == null || !resourcesImpl.getAssets().isUpToDate()) {
            return null;
        }
        return resourcesImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResourcesImpl findOrCreateResourcesImplForKeyLocked(ResourcesKey resourcesKey) {
        return findOrCreateResourcesImplForKeyLocked(resourcesKey, null);
    }

    private ResourcesImpl findOrCreateResourcesImplForKeyLocked(ResourcesKey resourcesKey, ApkAssetsSupplier apkAssetsSupplier) {
        ResourcesImpl resourcesImplFindResourcesImplForKeyLocked = findResourcesImplForKeyLocked(resourcesKey);
        if (resourcesImplFindResourcesImplForKeyLocked == null && (resourcesImplFindResourcesImplForKeyLocked = createResourcesImpl(resourcesKey, apkAssetsSupplier)) != null) {
            this.mResourceImpls.put(resourcesKey, new WeakReference<>(resourcesImplFindResourcesImplForKeyLocked));
        }
        return resourcesImplFindResourcesImplForKeyLocked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResourcesKey findKeyForResourceImplLocked(ResourcesImpl resourcesImpl) {
        int size = this.mResourceImpls.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                return null;
            }
            WeakReference<ResourcesImpl> weakReferenceValueAt = this.mResourceImpls.valueAt(i);
            if (resourcesImpl == (weakReferenceValueAt != null ? weakReferenceValueAt.get() : null)) {
                return this.mResourceImpls.keyAt(i);
            }
            i++;
        }
    }

    boolean isSameResourcesOverrideConfig(IBinder iBinder, Configuration configuration) {
        ActivityResources activityResources;
        synchronized (this) {
            if (iBinder != null) {
                try {
                    activityResources = this.mActivityResourceReferences.get(iBinder);
                } finally {
                }
            } else {
                activityResources = null;
            }
            boolean z = true;
            if (activityResources == null) {
                if (configuration != null) {
                    z = false;
                }
                return z;
            }
            if (!Objects.equals(activityResources.overrideConfig, configuration) && (configuration == null || activityResources.overrideConfig == null || configuration.diffPublicOnly(activityResources.overrideConfig) != 0)) {
                z = false;
            }
            return z;
        }
    }

    private ActivityResources getOrCreateActivityResourcesStructLocked(IBinder iBinder) {
        ActivityResources activityResources = this.mActivityResourceReferences.get(iBinder);
        if (activityResources != null) {
            return activityResources;
        }
        ActivityResources activityResources2 = new ActivityResources();
        this.mActivityResourceReferences.put(iBinder, activityResources2);
        return activityResources2;
    }

    private Resources findResourcesForActivityLocked(IBinder iBinder, ResourcesKey resourcesKey, ClassLoader classLoader) {
        ActivityResources orCreateActivityResourcesStructLocked = getOrCreateActivityResourcesStructLocked(iBinder);
        int size = orCreateActivityResourcesStructLocked.activityResources.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                return null;
            }
            Resources resources = orCreateActivityResourcesStructLocked.activityResources.get(i).get();
            ResourcesKey resourcesKeyFindKeyForResourceImplLocked = resources != null ? findKeyForResourceImplLocked(resources.getImpl()) : null;
            if (resourcesKeyFindKeyForResourceImplLocked != null && Objects.equals(resources.getClassLoader(), classLoader) && Objects.equals(resourcesKeyFindKeyForResourceImplLocked, resourcesKey)) {
                return resources;
            }
            i++;
        }
    }

    private Resources createResourcesForActivityLocked(IBinder iBinder, ClassLoader classLoader, ResourcesImpl resourcesImpl, CompatibilityInfo compatibilityInfo) {
        ActivityResources orCreateActivityResourcesStructLocked = getOrCreateActivityResourcesStructLocked(iBinder);
        cleanupReferences(orCreateActivityResourcesStructLocked.activityResources, orCreateActivityResourcesStructLocked.activityResourcesQueue);
        Resources compatResources = compatibilityInfo.needsCompatResources() ? new CompatResources(classLoader) : new Resources(classLoader);
        compatResources.setImpl(resourcesImpl);
        compatResources.setCallbacks(this.mUpdateCallbacks);
        orCreateActivityResourcesStructLocked.activityResources.add(new WeakReference<>(compatResources, orCreateActivityResourcesStructLocked.activityResourcesQueue));
        return compatResources;
    }

    private Resources createResourcesLocked(ClassLoader classLoader, ResourcesImpl resourcesImpl, CompatibilityInfo compatibilityInfo) {
        cleanupReferences(this.mResourceReferences, this.mResourcesReferencesQueue);
        Resources compatResources = compatibilityInfo.needsCompatResources() ? new CompatResources(classLoader) : new Resources(classLoader);
        compatResources.setImpl(resourcesImpl);
        compatResources.setCallbacks(this.mUpdateCallbacks);
        this.mResourceReferences.add(new WeakReference<>(compatResources, this.mResourcesReferencesQueue));
        return compatResources;
    }

    public Resources createBaseTokenResources(IBinder iBinder, String str, String[] strArr, String[] strArr2, String[] strArr3, int i, Configuration configuration, CompatibilityInfo compatibilityInfo, ClassLoader classLoader, List<ResourcesLoader> list) throws Throwable {
        long j;
        try {
            Trace.traceBegin(8192L, "ResourcesManager#createBaseActivityResources");
            try {
                ResourcesKey resourcesKey = new ResourcesKey(str, strArr, strArr2, strArr3, i, configuration != null ? new Configuration(configuration) : null, compatibilityInfo, list == null ? null : (ResourcesLoader[]) list.toArray(new ResourcesLoader[0]));
                ClassLoader systemClassLoader = classLoader != null ? classLoader : ClassLoader.getSystemClassLoader();
                synchronized (this) {
                    getOrCreateActivityResourcesStructLocked(iBinder);
                }
                updateResourcesForActivity(iBinder, configuration, i, false);
                rebaseKeyForActivity(iBinder, resourcesKey);
                synchronized (this) {
                    Resources resourcesFindResourcesForActivityLocked = findResourcesForActivityLocked(iBinder, resourcesKey, systemClassLoader);
                    if (resourcesFindResourcesForActivityLocked == null) {
                        try {
                            Resources resourcesCreateResources = createResources(iBinder, resourcesKey, systemClassLoader, null);
                            Trace.traceEnd(8192L);
                            return resourcesCreateResources;
                        } catch (Throwable th) {
                            th = th;
                            j = 8192;
                            Trace.traceEnd(j);
                            throw th;
                        }
                    }
                    Trace.traceEnd(8192L);
                    return resourcesFindResourcesForActivityLocked;
                }
            } catch (Throwable th2) {
                th = th2;
                j = 8192;
            }
        } catch (Throwable th3) {
            th = th3;
            j = 8192;
        }
    }

    private void rebaseKeyForActivity(IBinder iBinder, ResourcesKey resourcesKey) {
        synchronized (this) {
            ActivityResources orCreateActivityResourcesStructLocked = getOrCreateActivityResourcesStructLocked(iBinder);
            if (resourcesKey.hasOverrideConfiguration() && !orCreateActivityResourcesStructLocked.overrideConfig.equals(Configuration.EMPTY)) {
                Configuration configuration = new Configuration(orCreateActivityResourcesStructLocked.overrideConfig);
                configuration.updateFrom(resourcesKey.mOverrideConfiguration);
                resourcesKey.mOverrideConfiguration.setTo(configuration);
            }
        }
    }

    private static <T> void cleanupReferences(ArrayList<WeakReference<T>> arrayList, ReferenceQueue<T> referenceQueue) {
        Reference<? extends T> referencePoll = referenceQueue.poll();
        if (referencePoll == null) {
            return;
        }
        final HashSet hashSet = new HashSet();
        while (referencePoll != null) {
            hashSet.add(referencePoll);
            referencePoll = referenceQueue.poll();
        }
        ArrayUtils.unstableRemoveIf(arrayList, new Predicate() { // from class: android.app.-$$Lambda$ResourcesManager$JPMYJ3O5qlFN-c1356pr2ximEb0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ResourcesManager.lambda$cleanupReferences$0(hashSet, (WeakReference) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$cleanupReferences$0(HashSet hashSet, WeakReference weakReference) {
        return weakReference == null || hashSet.contains(weakReference);
    }

    private ApkAssetsSupplier createApkAssetsSupplierNotLocked(ResourcesKey resourcesKey) {
        Trace.traceBegin(8192L, "ResourcesManager#createApkAssetsSupplierNotLocked");
        try {
            ApkAssetsSupplier apkAssetsSupplier = new ApkAssetsSupplier();
            ArrayList<ApkKey> arrayListExtractApkKeys = extractApkKeys(resourcesKey);
            int size = arrayListExtractApkKeys.size();
            for (int i = 0; i < size; i++) {
                ApkKey apkKey = arrayListExtractApkKeys.get(i);
                try {
                    apkAssetsSupplier.load(apkKey);
                } catch (IOException e) {
                    Log.w(TAG, String.format("failed to preload asset path '%s'", apkKey.path), e);
                }
            }
            return apkAssetsSupplier;
        } finally {
            Trace.traceEnd(8192L);
        }
    }

    private Resources createResources(IBinder iBinder, ResourcesKey resourcesKey, ClassLoader classLoader, ApkAssetsSupplier apkAssetsSupplier) {
        synchronized (this) {
            ResourcesImpl resourcesImplFindOrCreateResourcesImplForKeyLocked = findOrCreateResourcesImplForKeyLocked(resourcesKey, apkAssetsSupplier);
            if (resourcesImplFindOrCreateResourcesImplForKeyLocked == null) {
                return null;
            }
            if (iBinder != null) {
                return createResourcesForActivityLocked(iBinder, classLoader, resourcesImplFindOrCreateResourcesImplForKeyLocked, resourcesKey.mCompatInfo);
            }
            return createResourcesLocked(classLoader, resourcesImplFindOrCreateResourcesImplForKeyLocked, resourcesKey.mCompatInfo);
        }
    }

    public Resources getResources(IBinder iBinder, String str, String[] strArr, String[] strArr2, String[] strArr3, int i, Configuration configuration, CompatibilityInfo compatibilityInfo, ClassLoader classLoader, List<ResourcesLoader> list) {
        try {
            Trace.traceBegin(8192L, "ResourcesManager#getResources");
            ResourcesKey resourcesKey = new ResourcesKey(str, strArr, strArr2, strArr3, i, configuration != null ? new Configuration(configuration) : null, compatibilityInfo, list == null ? null : (ResourcesLoader[]) list.toArray(new ResourcesLoader[0]));
            ClassLoader systemClassLoader = classLoader != null ? classLoader : ClassLoader.getSystemClassLoader();
            if (iBinder != null) {
                rebaseKeyForActivity(iBinder, resourcesKey);
            }
            return createResources(iBinder, resourcesKey, systemClassLoader, createApkAssetsSupplierNotLocked(resourcesKey));
        } finally {
            Trace.traceEnd(8192L);
        }
    }

    public void updateResourcesForActivity(IBinder iBinder, Configuration configuration, int i, boolean z) {
        ResourcesKey resourcesKeyRebaseActivityOverrideConfig;
        ResourcesImpl resourcesImplFindOrCreateResourcesImplForKeyLocked;
        try {
            Trace.traceBegin(8192L, "ResourcesManager#updateResourcesForActivity");
            synchronized (this) {
                ActivityResources orCreateActivityResourcesStructLocked = getOrCreateActivityResourcesStructLocked(iBinder);
                if (!Objects.equals(orCreateActivityResourcesStructLocked.overrideConfig, configuration) || z) {
                    Configuration configuration2 = new Configuration(orCreateActivityResourcesStructLocked.overrideConfig);
                    if (configuration != null) {
                        orCreateActivityResourcesStructLocked.overrideConfig.setTo(configuration);
                    } else {
                        orCreateActivityResourcesStructLocked.overrideConfig.unset();
                    }
                    int size = orCreateActivityResourcesStructLocked.activityResources.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        Resources resources = orCreateActivityResourcesStructLocked.activityResources.get(i2).get();
                        if (resources != null && (resourcesKeyRebaseActivityOverrideConfig = rebaseActivityOverrideConfig(resources, configuration2, configuration, i)) != null && (resourcesImplFindOrCreateResourcesImplForKeyLocked = findOrCreateResourcesImplForKeyLocked(resourcesKeyRebaseActivityOverrideConfig)) != null && resourcesImplFindOrCreateResourcesImplForKeyLocked != resources.getImpl()) {
                            resources.setImpl(resourcesImplFindOrCreateResourcesImplForKeyLocked);
                        }
                    }
                }
            }
        } finally {
            Trace.traceEnd(8192L);
        }
    }

    private ResourcesKey rebaseActivityOverrideConfig(Resources resources, Configuration configuration, Configuration configuration2, int i) {
        ResourcesKey resourcesKeyFindKeyForResourceImplLocked = findKeyForResourceImplLocked(resources.getImpl());
        if (resourcesKeyFindKeyForResourceImplLocked == null) {
            Slog.e(TAG, "can't find ResourcesKey for resources impl=" + resources.getImpl());
            return null;
        }
        Configuration configuration3 = new Configuration();
        if (configuration2 != null) {
            configuration3.setTo(configuration2);
        }
        if ((!configuration.equals(Configuration.EMPTY)) && resourcesKeyFindKeyForResourceImplLocked.hasOverrideConfiguration()) {
            configuration3.updateFrom(Configuration.generateDelta(configuration, resourcesKeyFindKeyForResourceImplLocked.mOverrideConfiguration));
        }
        return new ResourcesKey(resourcesKeyFindKeyForResourceImplLocked.mResDir, resourcesKeyFindKeyForResourceImplLocked.mSplitResDirs, resourcesKeyFindKeyForResourceImplLocked.mOverlayDirs, resourcesKeyFindKeyForResourceImplLocked.mLibDirs, i, configuration3, resourcesKeyFindKeyForResourceImplLocked.mCompatInfo, resourcesKeyFindKeyForResourceImplLocked.mLoaders);
    }

    public final boolean applyConfigurationToResources(Configuration configuration, CompatibilityInfo compatibilityInfo) {
        boolean zApplyConfigurationToResourcesLocked;
        synchronized (this) {
            zApplyConfigurationToResourcesLocked = applyConfigurationToResourcesLocked(configuration, compatibilityInfo, null);
        }
        return zApplyConfigurationToResourcesLocked;
    }

    public final boolean applyConfigurationToResourcesLocked(Configuration configuration, CompatibilityInfo compatibilityInfo) {
        return applyConfigurationToResourcesLocked(configuration, compatibilityInfo, null);
    }

    public final boolean applyConfigurationToResourcesLocked(Configuration configuration, CompatibilityInfo compatibilityInfo, DisplayAdjustments displayAdjustments) {
        try {
            Trace.traceBegin(8192L, "ResourcesManager#applyConfigurationToResourcesLocked");
            if (!this.mResConfiguration.isOtherSeqNewer(configuration) && compatibilityInfo == null) {
                return false;
            }
            int iUpdateFrom = this.mResConfiguration.updateFrom(configuration);
            this.mAdjustedDisplays.clear();
            DisplayMetrics displayMetrics = getDisplayMetrics();
            if (compatibilityInfo != null && (this.mResCompatibilityInfo == null || !this.mResCompatibilityInfo.equals(compatibilityInfo))) {
                this.mResCompatibilityInfo = compatibilityInfo;
                iUpdateFrom |= 3328;
            }
            if (displayAdjustments != null) {
                displayAdjustments.adjustGlobalAppMetrics(displayMetrics);
            }
            Resources.updateSystemConfiguration(configuration, displayMetrics, compatibilityInfo);
            ApplicationPackageManager.configurationChanged();
            Configuration configuration2 = new Configuration();
            for (int size = this.mResourceImpls.size() - 1; size >= 0; size--) {
                ResourcesKey resourcesKeyKeyAt = this.mResourceImpls.keyAt(size);
                WeakReference<ResourcesImpl> weakReferenceValueAt = this.mResourceImpls.valueAt(size);
                ResourcesImpl resourcesImpl = weakReferenceValueAt != null ? weakReferenceValueAt.get() : null;
                if (resourcesImpl != null) {
                    applyConfigurationToResourcesLocked(configuration, compatibilityInfo, configuration2, resourcesKeyKeyAt, resourcesImpl);
                } else {
                    this.mResourceImpls.removeAt(size);
                }
            }
            return iUpdateFrom != 0;
        } finally {
            Trace.traceEnd(8192L);
        }
    }

    private void applyConfigurationToResourcesLocked(Configuration configuration, CompatibilityInfo compatibilityInfo, Configuration configuration2, ResourcesKey resourcesKey, ResourcesImpl resourcesImpl) {
        configuration2.setTo(configuration);
        boolean zHasOverrideConfiguration = resourcesKey.hasOverrideConfiguration();
        if (zHasOverrideConfiguration) {
            configuration2.updateFrom(resourcesKey.mOverrideConfiguration);
        }
        DisplayAdjustments displayAdjustments = resourcesImpl.getDisplayAdjustments();
        if (compatibilityInfo != null) {
            DisplayAdjustments displayAdjustments2 = new DisplayAdjustments(displayAdjustments);
            displayAdjustments2.setCompatibilityInfo(compatibilityInfo);
            displayAdjustments = displayAdjustments2;
        }
        int i = resourcesKey.mDisplayId;
        if (i == 0) {
            displayAdjustments.setConfiguration(configuration2);
        }
        DisplayMetrics displayMetrics = getDisplayMetrics(i, displayAdjustments);
        if (i != 0) {
            applyNonDefaultDisplayMetricsToConfiguration(displayMetrics, configuration2);
            if (zHasOverrideConfiguration) {
                configuration2.updateFrom(resourcesKey.mOverrideConfiguration);
            }
        }
        resourcesImpl.updateConfiguration(configuration2, displayMetrics, compatibilityInfo);
    }

    public void appendLibAssetForMainAssetPath(String str, String str2) {
        appendLibAssetsForMainAssetPath(str, new String[]{str2});
    }

    public void appendLibAssetsForMainAssetPath(String str, String[] strArr) {
        synchronized (this) {
            ArrayMap<ResourcesImpl, ResourcesKey> arrayMap = new ArrayMap<>();
            int size = this.mResourceImpls.size();
            for (int i = 0; i < size; i++) {
                ResourcesKey resourcesKeyKeyAt = this.mResourceImpls.keyAt(i);
                WeakReference<ResourcesImpl> weakReferenceValueAt = this.mResourceImpls.valueAt(i);
                ResourcesImpl resourcesImpl = weakReferenceValueAt != null ? weakReferenceValueAt.get() : null;
                if (resourcesImpl != null && Objects.equals(resourcesKeyKeyAt.mResDir, str)) {
                    String[] strArr2 = resourcesKeyKeyAt.mLibDirs;
                    for (String str2 : strArr) {
                        strArr2 = (String[]) ArrayUtils.appendElement(String.class, strArr2, str2);
                    }
                    if (!Arrays.equals(strArr2, resourcesKeyKeyAt.mLibDirs)) {
                        arrayMap.put(resourcesImpl, new ResourcesKey(resourcesKeyKeyAt.mResDir, resourcesKeyKeyAt.mSplitResDirs, resourcesKeyKeyAt.mOverlayDirs, strArr2, resourcesKeyKeyAt.mDisplayId, resourcesKeyKeyAt.mOverrideConfiguration, resourcesKeyKeyAt.mCompatInfo, resourcesKeyKeyAt.mLoaders));
                    }
                }
            }
            redirectResourcesToNewImplLocked(arrayMap);
        }
    }

    final void applyNewResourceDirsLocked(ApplicationInfo applicationInfo, String[] strArr) throws Throwable {
        String[] strArr2;
        String[] strArr3;
        String str;
        int i;
        int i2;
        long j = 8192;
        try {
            Trace.traceBegin(8192L, "ResourcesManager#applyNewResourceDirsLocked");
            String baseCodePath = applicationInfo.getBaseCodePath();
            if (applicationInfo.uid == Process.myUid()) {
                strArr2 = applicationInfo.splitSourceDirs;
            } else {
                strArr2 = applicationInfo.splitPublicSourceDirs;
            }
            String[] strArr4 = (String[]) ArrayUtils.cloneOrNull(strArr2);
            String[] strArr5 = (String[]) ArrayUtils.cloneOrNull(applicationInfo.resourceDirs);
            ArrayMap<ResourcesImpl, ResourcesKey> arrayMap = new ArrayMap<>();
            int size = this.mResourceImpls.size();
            int i3 = 0;
            while (i3 < size) {
                ResourcesKey resourcesKeyKeyAt = this.mResourceImpls.keyAt(i3);
                WeakReference<ResourcesImpl> weakReferenceValueAt = this.mResourceImpls.valueAt(i3);
                ResourcesImpl resourcesImpl = weakReferenceValueAt != null ? weakReferenceValueAt.get() : null;
                if (resourcesImpl != null && (resourcesKeyKeyAt.mResDir == null || resourcesKeyKeyAt.mResDir.equals(baseCodePath) || ArrayUtils.contains(strArr, resourcesKeyKeyAt.mResDir))) {
                    try {
                        strArr3 = strArr5;
                        str = baseCodePath;
                        i = i3;
                        i2 = size;
                        arrayMap.put(resourcesImpl, new ResourcesKey(baseCodePath, strArr4, strArr5, resourcesKeyKeyAt.mLibDirs, resourcesKeyKeyAt.mDisplayId, resourcesKeyKeyAt.mOverrideConfiguration, resourcesKeyKeyAt.mCompatInfo, resourcesKeyKeyAt.mLoaders));
                        i3 = i + 1;
                        strArr5 = strArr3;
                        size = i2;
                        baseCodePath = str;
                        j = 8192;
                    } catch (Throwable th) {
                        th = th;
                        j = 8192;
                        Trace.traceEnd(j);
                        throw th;
                    }
                } else {
                    strArr3 = strArr5;
                    str = baseCodePath;
                    i = i3;
                    i2 = size;
                    i3 = i + 1;
                    strArr5 = strArr3;
                    size = i2;
                    baseCodePath = str;
                    j = 8192;
                }
            }
            redirectResourcesToNewImplLocked(arrayMap);
            Trace.traceEnd(8192L);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void redirectResourcesToNewImplLocked(ArrayMap<ResourcesImpl, ResourcesKey> arrayMap) {
        ResourcesKey resourcesKey;
        ResourcesKey resourcesKey2;
        if (arrayMap.isEmpty()) {
            return;
        }
        int size = this.mResourceReferences.size();
        int i = 0;
        while (true) {
            if (i < size) {
                WeakReference<Resources> weakReference = this.mResourceReferences.get(i);
                Resources resources = weakReference != null ? weakReference.get() : null;
                if (resources != null && (resourcesKey2 = arrayMap.get(resources.getImpl())) != null) {
                    ResourcesImpl resourcesImplFindOrCreateResourcesImplForKeyLocked = findOrCreateResourcesImplForKeyLocked(resourcesKey2);
                    if (resourcesImplFindOrCreateResourcesImplForKeyLocked == null) {
                        throw new Resources.NotFoundException("failed to redirect ResourcesImpl");
                    }
                    resources.setImpl(resourcesImplFindOrCreateResourcesImplForKeyLocked);
                }
                i++;
            } else {
                for (ActivityResources activityResources : this.mActivityResourceReferences.values()) {
                    int size2 = activityResources.activityResources.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        WeakReference<Resources> weakReference2 = activityResources.activityResources.get(i2);
                        Resources resources2 = weakReference2 != null ? weakReference2.get() : null;
                        if (resources2 != null && (resourcesKey = arrayMap.get(resources2.getImpl())) != null) {
                            ResourcesImpl resourcesImplFindOrCreateResourcesImplForKeyLocked2 = findOrCreateResourcesImplForKeyLocked(resourcesKey);
                            if (resourcesImplFindOrCreateResourcesImplForKeyLocked2 == null) {
                                throw new Resources.NotFoundException("failed to redirect ResourcesImpl");
                            }
                            resources2.setImpl(resourcesImplFindOrCreateResourcesImplForKeyLocked2);
                        }
                    }
                }
                return;
            }
        }
    }

    public boolean overrideTokenDisplayAdjustments(IBinder iBinder, Consumer<DisplayAdjustments> consumer) {
        synchronized (this) {
            ActivityResources activityResources = this.mActivityResourceReferences.get(iBinder);
            boolean z = false;
            if (activityResources == null) {
                return false;
            }
            ArrayList<WeakReference<Resources>> arrayList = activityResources.activityResources;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Resources resources = arrayList.get(size).get();
                if (resources != null) {
                    resources.overrideDisplayAdjustments(consumer);
                    z = true;
                }
            }
            return z;
        }
    }

    private class UpdateHandler implements Resources.UpdateCallbacks {
        private UpdateHandler() {
        }

        @Override // android.content.res.Resources.UpdateCallbacks
        public void onLoadersChanged(Resources resources, List<ResourcesLoader> list) {
            synchronized (ResourcesManager.this) {
                ResourcesKey resourcesKeyFindKeyForResourceImplLocked = ResourcesManager.this.findKeyForResourceImplLocked(resources.getImpl());
                if (resourcesKeyFindKeyForResourceImplLocked == null) {
                    throw new IllegalArgumentException("Cannot modify resource loaders of ResourcesImpl not registered with ResourcesManager");
                }
                resources.setImpl(ResourcesManager.this.findOrCreateResourcesImplForKeyLocked(new ResourcesKey(resourcesKeyFindKeyForResourceImplLocked.mResDir, resourcesKeyFindKeyForResourceImplLocked.mSplitResDirs, resourcesKeyFindKeyForResourceImplLocked.mOverlayDirs, resourcesKeyFindKeyForResourceImplLocked.mLibDirs, resourcesKeyFindKeyForResourceImplLocked.mDisplayId, resourcesKeyFindKeyForResourceImplLocked.mOverrideConfiguration, resourcesKeyFindKeyForResourceImplLocked.mCompatInfo, (ResourcesLoader[]) list.toArray(new ResourcesLoader[0]))));
            }
        }

        @Override // android.content.res.loader.ResourcesLoader.UpdateCallbacks
        public void onLoaderUpdated(ResourcesLoader resourcesLoader) {
            synchronized (ResourcesManager.this) {
                ArrayMap arrayMap = new ArrayMap();
                for (int size = ResourcesManager.this.mResourceImpls.size() - 1; size >= 0; size--) {
                    ResourcesKey resourcesKey = (ResourcesKey) ResourcesManager.this.mResourceImpls.keyAt(size);
                    WeakReference weakReference = (WeakReference) ResourcesManager.this.mResourceImpls.valueAt(size);
                    if (weakReference != null && weakReference.get() != null && ArrayUtils.contains(resourcesKey.mLoaders, resourcesLoader)) {
                        ResourcesManager.this.mResourceImpls.remove(resourcesKey);
                        arrayMap.put((ResourcesImpl) weakReference.get(), resourcesKey);
                    }
                }
                ResourcesManager.this.redirectResourcesToNewImplLocked(arrayMap);
            }
        }
    }
}
