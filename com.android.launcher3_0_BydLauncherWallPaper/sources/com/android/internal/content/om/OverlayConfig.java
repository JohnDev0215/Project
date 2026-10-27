package com.android.internal.content.om;

import android.content.pm.PackagePartitions;
import android.content.pm.parsing.ParsingPackageRead;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.content.om.OverlayConfig;
import com.android.internal.content.om.OverlayConfigParser;
import com.android.internal.content.om.OverlayScanner;
import com.android.internal.util.Preconditions;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes4.dex */
public class OverlayConfig {
    public static final int DEFAULT_PRIORITY = Integer.MAX_VALUE;
    static final String TAG = "OverlayConfig";
    private static OverlayConfig sInstance;
    private static final Comparator<OverlayConfigParser.ParsedConfiguration> sStaticOverlayComparator = new Comparator() { // from class: com.android.internal.content.om.-$$Lambda$OverlayConfig$yZZziiIBWixRU9pDr42pe4QBWR0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return OverlayConfig.lambda$static$0((OverlayConfigParser.ParsedConfiguration) obj, (OverlayConfigParser.ParsedConfiguration) obj2);
        }
    };
    private final ArrayMap<String, Configuration> mConfigurations = new ArrayMap<>();

    public interface PackageProvider {
        void forEachPackage(BiConsumer<ParsingPackageRead, Boolean> biConsumer);
    }

    private static native String[] createIdmap(String str, String[] strArr, String[] strArr2, boolean z);

    public static final class Configuration {
        public final int configIndex;
        public final OverlayConfigParser.ParsedConfiguration parsedConfig;

        public Configuration(OverlayConfigParser.ParsedConfiguration parsedConfiguration, int i) {
            this.parsedConfig = parsedConfiguration;
            this.configIndex = i;
        }
    }

    static /* synthetic */ int lambda$static$0(OverlayConfigParser.ParsedConfiguration parsedConfiguration, OverlayConfigParser.ParsedConfiguration parsedConfiguration2) {
        OverlayScanner.ParsedOverlayInfo parsedOverlayInfo = parsedConfiguration.parsedInfo;
        OverlayScanner.ParsedOverlayInfo parsedOverlayInfo2 = parsedConfiguration2.parsedInfo;
        Preconditions.checkArgument(parsedOverlayInfo.isStatic && parsedOverlayInfo2.isStatic, "attempted to sort non-static overlay");
        if (!parsedOverlayInfo.targetPackageName.equals(parsedOverlayInfo2.targetPackageName)) {
            return parsedOverlayInfo.targetPackageName.compareTo(parsedOverlayInfo2.targetPackageName);
        }
        int i = parsedOverlayInfo.priority - parsedOverlayInfo2.priority;
        return i == 0 ? parsedOverlayInfo.path.compareTo(parsedOverlayInfo2.path) : i;
    }

    public OverlayConfig(final File file, Supplier<OverlayScanner> supplier, PackageProvider packageProvider) {
        ArrayList arrayList;
        ArrayList arrayList2;
        int i = 1;
        Preconditions.checkArgument((supplier == null) != (packageProvider == null), "scannerFactory and packageProvider cannot be both null or both non-null");
        if (file == null) {
            arrayList = new ArrayList(PackagePartitions.getOrderedPartitions(new Function() { // from class: com.android.internal.content.om.-$$Lambda$hGsk_nEEC82H21r64I2GYTL3dqo
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new OverlayConfigParser.OverlayPartition((PackagePartitions.SystemPartition) obj);
                }
            }));
        } else {
            arrayList = new ArrayList(PackagePartitions.getOrderedPartitions(new Function() { // from class: com.android.internal.content.om.-$$Lambda$OverlayConfig$4kC39m5BXQdCsnIP_12_hroW7w4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return OverlayConfig.lambda$new$1(file, (PackagePartitions.SystemPartition) obj);
                }
            }));
        }
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        ArrayList<OverlayScanner.ParsedOverlayInfo> overlayPackageInfos = null;
        while (i2 < size) {
            OverlayConfigParser.OverlayPartition overlayPartition = (OverlayConfigParser.OverlayPartition) arrayList.get(i2);
            OverlayScanner overlayScanner = supplier == null ? null : supplier.get();
            ArrayList<OverlayConfigParser.ParsedConfiguration> configurations = OverlayConfigParser.getConfigurations(overlayPartition, overlayScanner);
            if (configurations != null) {
                arrayList3.addAll(configurations);
                i3 = i;
            } else {
                if (supplier != null) {
                    arrayList2 = new ArrayList(overlayScanner.getAllParsedInfos());
                } else {
                    overlayPackageInfos = overlayPackageInfos == null ? getOverlayPackageInfos(packageProvider) : overlayPackageInfos;
                    arrayList2 = new ArrayList(overlayPackageInfos);
                    for (int size2 = arrayList2.size() - i; size2 >= 0; size2--) {
                        if (!overlayPartition.containsFile(((OverlayScanner.ParsedOverlayInfo) arrayList2.get(size2)).path)) {
                            arrayList2.remove(size2);
                        }
                    }
                }
                ArrayList arrayList4 = new ArrayList();
                int size3 = arrayList2.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    OverlayScanner.ParsedOverlayInfo parsedOverlayInfo = (OverlayScanner.ParsedOverlayInfo) arrayList2.get(i4);
                    if (parsedOverlayInfo.isStatic) {
                        arrayList4.add(new OverlayConfigParser.ParsedConfiguration(parsedOverlayInfo.packageName, true, false, overlayPartition.policy, parsedOverlayInfo));
                    }
                }
                arrayList4.sort(sStaticOverlayComparator);
                arrayList3.addAll(arrayList4);
            }
            i2++;
            i = 1;
        }
        if (i3 == 0) {
            arrayList3.sort(sStaticOverlayComparator);
        }
        int size4 = arrayList3.size();
        for (int i5 = 0; i5 < size4; i5++) {
            OverlayConfigParser.ParsedConfiguration parsedConfiguration = (OverlayConfigParser.ParsedConfiguration) arrayList3.get(i5);
            this.mConfigurations.put(parsedConfiguration.packageName, new Configuration(parsedConfiguration, i5));
        }
    }

    static /* synthetic */ OverlayConfigParser.OverlayPartition lambda$new$1(File file, PackagePartitions.SystemPartition systemPartition) {
        return new OverlayConfigParser.OverlayPartition(new File(file, systemPartition.getFolder().getPath()), systemPartition);
    }

    public static OverlayConfig getZygoteInstance() {
        Trace.traceBegin(67108864L, "OverlayConfig#getZygoteInstance");
        try {
            return new OverlayConfig(null, new Supplier() { // from class: com.android.internal.content.om.-$$Lambda$tHmjS9Skk84paTvwLk9hTP8TuLM
                @Override // java.util.function.Supplier
                public final Object get() {
                    return new OverlayScanner();
                }
            }, null);
        } finally {
            Trace.traceEnd(67108864L);
        }
    }

    public static OverlayConfig initializeSystemInstance(PackageProvider packageProvider) {
        Trace.traceBegin(67108864L, "OverlayConfig#initializeSystemInstance");
        try {
            sInstance = new OverlayConfig(null, null, packageProvider);
            Trace.traceEnd(67108864L);
            return sInstance;
        } catch (Throwable th) {
            Trace.traceEnd(67108864L);
            throw th;
        }
    }

    public static OverlayConfig getSystemInstance() {
        OverlayConfig overlayConfig = sInstance;
        if (overlayConfig != null) {
            return overlayConfig;
        }
        throw new IllegalStateException("System instance not initialized");
    }

    public Configuration getConfiguration(String str) {
        return this.mConfigurations.get(str);
    }

    public boolean isEnabled(String str) {
        Configuration configuration = this.mConfigurations.get(str);
        if (configuration == null) {
            return false;
        }
        return configuration.parsedConfig.enabled;
    }

    public boolean isMutable(String str) {
        Configuration configuration = this.mConfigurations.get(str);
        if (configuration == null) {
            return true;
        }
        return configuration.parsedConfig.mutable;
    }

    public int getPriority(String str) {
        Configuration configuration = this.mConfigurations.get(str);
        if (configuration == null) {
            return Integer.MAX_VALUE;
        }
        return configuration.configIndex;
    }

    private ArrayList<Configuration> getSortedOverlays() {
        ArrayList<Configuration> arrayList = new ArrayList<>();
        int size = this.mConfigurations.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(this.mConfigurations.valueAt(i));
        }
        arrayList.sort(Comparator.comparingInt(new ToIntFunction() { // from class: com.android.internal.content.om.-$$Lambda$OverlayConfig$8i80MSZuac6hlhDs_xmkCyG6b0w
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((OverlayConfig.Configuration) obj).configIndex;
            }
        }));
        return arrayList;
    }

    private static ArrayList<OverlayScanner.ParsedOverlayInfo> getOverlayPackageInfos(PackageProvider packageProvider) {
        final ArrayList<OverlayScanner.ParsedOverlayInfo> arrayList = new ArrayList<>();
        packageProvider.forEachPackage(new BiConsumer() { // from class: com.android.internal.content.om.-$$Lambda$OverlayConfig$YsRgav_0U6ag8BY7ZGunECCx0GA
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                OverlayConfig.lambda$getOverlayPackageInfos$3(arrayList, (ParsingPackageRead) obj, (Boolean) obj2);
            }
        });
        return arrayList;
    }

    static /* synthetic */ void lambda$getOverlayPackageInfos$3(ArrayList arrayList, ParsingPackageRead parsingPackageRead, Boolean bool) {
        if (parsingPackageRead.getOverlayTarget() == null || !bool.booleanValue()) {
            return;
        }
        arrayList.add(new OverlayScanner.ParsedOverlayInfo(parsingPackageRead.getPackageName(), parsingPackageRead.getOverlayTarget(), parsingPackageRead.getTargetSdkVersion(), parsingPackageRead.isOverlayIsStatic(), parsingPackageRead.getOverlayPriority(), new File(parsingPackageRead.getBaseCodePath())));
    }

    public static class IdmapInvocation {
        public final boolean enforceOverlayable;
        public final ArrayList<String> overlayPaths = new ArrayList<>();
        public final String policy;

        IdmapInvocation(boolean z, String str) {
            this.enforceOverlayable = z;
            this.policy = str;
        }

        public String toString() {
            return getClass().getSimpleName() + String.format("{enforceOverlayable=%s, policy=%s, overlayPaths=[%s]}", Boolean.valueOf(this.enforceOverlayable), this.policy, String.join(", ", this.overlayPaths));
        }
    }

    public ArrayList<IdmapInvocation> getImmutableFrameworkOverlayIdmapInvocations() {
        ArrayList<IdmapInvocation> arrayList = new ArrayList<>();
        ArrayList<Configuration> sortedOverlays = getSortedOverlays();
        int size = sortedOverlays.size();
        for (int i = 0; i < size; i++) {
            Configuration configuration = sortedOverlays.get(i);
            if (!configuration.parsedConfig.mutable && configuration.parsedConfig.enabled && "android".equals(configuration.parsedConfig.parsedInfo.targetPackageName)) {
                boolean z = configuration.parsedConfig.parsedInfo.targetSdkVersion >= 29;
                IdmapInvocation idmapInvocation = null;
                if (!arrayList.isEmpty()) {
                    IdmapInvocation idmapInvocation2 = arrayList.get(arrayList.size() - 1);
                    if (idmapInvocation2.enforceOverlayable == z && idmapInvocation2.policy.equals(configuration.parsedConfig.policy)) {
                        idmapInvocation = idmapInvocation2;
                    }
                }
                if (idmapInvocation == null) {
                    idmapInvocation = new IdmapInvocation(z, configuration.parsedConfig.policy);
                    arrayList.add(idmapInvocation);
                }
                idmapInvocation.overlayPaths.add(configuration.parsedConfig.parsedInfo.path.getAbsolutePath());
            }
        }
        return arrayList;
    }

    public String[] createImmutableFrameworkIdmapsInZygote() {
        ArrayList arrayList = new ArrayList();
        ArrayList<IdmapInvocation> immutableFrameworkOverlayIdmapInvocations = getImmutableFrameworkOverlayIdmapInvocations();
        int size = immutableFrameworkOverlayIdmapInvocations.size();
        for (int i = 0; i < size; i++) {
            IdmapInvocation idmapInvocation = immutableFrameworkOverlayIdmapInvocations.get(i);
            String[] strArrCreateIdmap = createIdmap("/system/framework/framework-res.apk", (String[]) idmapInvocation.overlayPaths.toArray(new String[0]), new String[]{"public", idmapInvocation.policy}, idmapInvocation.enforceOverlayable);
            if (strArrCreateIdmap == null) {
                Log.w(TAG, "'idmap2 create-multiple' failed: no mutable=\"false\" overlays targeting \"android\" will be loaded");
                return new String[0];
            }
            arrayList.addAll(Arrays.asList(strArrCreateIdmap));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }
}
