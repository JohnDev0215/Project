package android.content.pm.parsing;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageParser;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedAttribution;
import android.content.pm.parsing.component.ParsedComponent;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedIntentInfo;
import android.content.pm.parsing.component.ParsedMainComponent;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedProcess;
import android.content.pm.parsing.component.ParsedProvider;
import android.content.pm.parsing.component.ParsedService;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.Parcelling;
import java.security.PublicKey;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ParsingPackageImpl implements ParsingPackage, Parcelable {
    private static final String TAG = "PackageImpl";
    protected List<ParsedActivity> activities;
    protected List<String> adoptPermissions;
    private boolean allowAudioPlaybackCapture;
    private boolean allowBackup;
    private boolean allowClearUserData;
    private boolean allowClearUserDataOnFailedRestore;
    private boolean allowNativeHeapPointerTagging;
    private boolean allowTaskReparenting;
    private Boolean anyDensity;
    private String appComponentFactory;
    private List<ParsedAttribution> attributions;
    private int autoRevokePermissions;
    private String backupAgentName;
    private boolean backupInForeground;
    private int banner;
    protected String baseCodePath;
    private boolean baseHardwareAccelerated;
    private int baseRevisionCode;
    private boolean cantSaveState;
    private int category;
    private String classLoaderName;
    private String className;
    protected String codePath;
    private int compatibleWidthLimitDp;
    private int compileSdkVersion;
    private String compileSdkVersionCodeName;
    private List<ConfigurationInfo> configPreferences;
    private boolean crossProfile;
    private boolean debuggable;
    private boolean defaultToDeviceProtectedStorage;
    private int descriptionRes;
    private boolean directBootAware;
    private boolean enabled;
    private boolean externalStorage;
    private boolean extractNativeLibs;
    private List<FeatureGroupInfo> featureGroups;
    private boolean forceQueryable;
    private int fullBackupContent;
    private boolean fullBackupOnly;
    private boolean game;
    protected int gwpAsanMode;
    private boolean hasCode;
    private boolean hasDomainUrls;
    private boolean hasFragileUserData;
    private int iconRes;
    private List<String> implicitPermissions;
    private int installLocation;
    protected List<ParsedInstrumentation> instrumentations;
    private boolean isolatedSplitLoading;
    private Map<String, ArraySet<PublicKey>> keySetMapping;
    private boolean killAfterRestore;
    private int labelRes;
    private boolean largeHeap;
    private int largestWidthLimitDp;
    private List<String> libraryNames;
    private int logo;
    private String manageSpaceActivityName;
    private float maxAspectRatio;
    private Bundle metaData;
    private ArraySet<String> mimeGroups;
    private float minAspectRatio;
    private SparseIntArray minExtensionVersions;
    private int minSdkVersion;
    private boolean multiArch;
    private int networkSecurityConfigRes;
    private CharSequence nonLocalizedLabel;
    protected List<String> originalPackages;
    private boolean overlay;
    private String overlayCategory;
    private boolean overlayIsStatic;
    private int overlayPriority;
    private String overlayTarget;
    private String overlayTargetName;
    private Map<String, String> overlayables;
    protected String packageName;
    private boolean partiallyDirectBootAware;
    private String permission;
    protected List<ParsedPermissionGroup> permissionGroups;
    protected List<ParsedPermission> permissions;
    private boolean persistent;
    private List<Pair<String, ParsedIntentInfo>> preferredActivityFilters;
    private boolean preserveLegacyExternalStorage;
    private String processName;
    private Map<String, ParsedProcess> processes;
    private boolean profileableByShell;
    protected List<String> protectedBroadcasts;
    protected List<ParsedProvider> providers;
    private List<Intent> queriesIntents;
    private List<String> queriesPackages;
    private Set<String> queriesProviders;
    private String realPackage;
    protected List<ParsedActivity> receivers;
    private List<FeatureInfo> reqFeatures;
    private boolean requestLegacyExternalStorage;
    private List<String> requestedPermissions;
    private String requiredAccountType;
    private boolean requiredForAllUsers;
    private int requiresSmallestWidthDp;
    private Boolean resizeable;
    private Boolean resizeableActivity;
    private boolean resizeableActivityViaSdkVersion;
    private boolean restoreAnyVersion;
    private byte[] restrictUpdateHash;
    private String restrictedAccountType;
    private int roundIconRes;
    protected List<ParsedService> services;
    private String sharedUserId;
    private int sharedUserLabel;
    private PackageParser.SigningDetails signingDetails;
    private String[] splitClassLoaderNames;
    protected String[] splitCodePaths;
    private SparseArray<int[]> splitDependencies;
    private int[] splitFlags;
    private String[] splitNames;
    private int[] splitRevisionCodes;
    private String staticSharedLibName;
    private long staticSharedLibVersion;
    private boolean staticSharedLibrary;
    private Boolean supportsExtraLargeScreens;
    private Boolean supportsLargeScreens;
    private Boolean supportsNormalScreens;
    private boolean supportsRtl;
    private Boolean supportsSmallScreens;
    private int targetSandboxVersion;
    private int targetSdkVersion;
    private String taskAffinity;
    private boolean testOnly;
    private int theme;
    private int uiOptions;
    private Set<String> upgradeKeySets;
    private boolean use32BitAbi;
    private boolean useEmbeddedDex;
    private boolean usesCleartextTraffic;
    protected List<String> usesLibraries;
    private boolean usesNonSdkApi;
    protected List<String> usesOptionalLibraries;
    private List<String> usesStaticLibraries;
    private String[][] usesStaticLibrariesCertDigests;
    private long[] usesStaticLibrariesVersions;
    protected int versionCode;
    protected int versionCodeMajor;
    private String versionName;
    private boolean visibleToInstantApps;
    private boolean vmSafeMode;
    protected String volumeUuid;
    private String zygotePreloadName;
    public static Parcelling.BuiltIn.ForBoolean sForBoolean = (Parcelling.BuiltIn.ForBoolean) Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForBoolean.class);
    public static Parcelling.BuiltIn.ForInternedString sForInternedString = (Parcelling.BuiltIn.ForInternedString) Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForInternedString.class);
    public static Parcelling.BuiltIn.ForInternedStringArray sForInternedStringArray = (Parcelling.BuiltIn.ForInternedStringArray) Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForInternedStringArray.class);
    public static Parcelling.BuiltIn.ForInternedStringList sForInternedStringList = (Parcelling.BuiltIn.ForInternedStringList) Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForInternedStringList.class);
    public static Parcelling.BuiltIn.ForInternedStringValueMap sForInternedStringValueMap = (Parcelling.BuiltIn.ForInternedStringValueMap) Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForInternedStringValueMap.class);
    public static Parcelling.BuiltIn.ForStringSet sForStringSet = (Parcelling.BuiltIn.ForStringSet) Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForStringSet.class);
    public static Parcelling.BuiltIn.ForInternedStringSet sForInternedStringSet = (Parcelling.BuiltIn.ForInternedStringSet) Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForInternedStringSet.class);
    protected static ParsedIntentInfo.StringPairListParceler sForIntentInfoPairs = (ParsedIntentInfo.StringPairListParceler) Parcelling.Cache.getOrCreate(ParsedIntentInfo.StringPairListParceler.class);
    private static final Comparator<ParsedMainComponent> ORDER_COMPARATOR = new Comparator() { // from class: android.content.pm.parsing.-$$Lambda$ParsingPackageImpl$Cqa0PbeZxny7ldN4s157Oxm0C7A
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return Integer.compare(((ParsedMainComponent) obj2).getOrder(), ((ParsedMainComponent) obj).getOrder());
        }
    };
    public static final Parcelable.Creator<ParsingPackageImpl> CREATOR = new Parcelable.Creator<ParsingPackageImpl>() { // from class: android.content.pm.parsing.ParsingPackageImpl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsingPackageImpl createFromParcel(Parcel parcel) {
            return new ParsingPackageImpl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsingPackageImpl[] newArray(int i) {
            return new ParsingPackageImpl[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public /* bridge */ /* synthetic */ ParsingPackage asSplit(String[] strArr, String[] strArr2, int[] iArr, SparseArray sparseArray) {
        return asSplit(strArr, strArr2, iArr, (SparseArray<int[]>) sparseArray);
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public /* bridge */ /* synthetic */ ParsingPackage setProcesses(Map map) {
        return setProcesses((Map<String, ParsedProcess>) map);
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public /* bridge */ /* synthetic */ ParsingPackage setUpgradeKeySets(Set set) {
        return setUpgradeKeySets((Set<String>) set);
    }

    public ParsingPackageImpl(String str, String str2, String str3, TypedArray typedArray) {
        this.overlayables = Collections.emptyMap();
        this.libraryNames = Collections.emptyList();
        this.usesLibraries = Collections.emptyList();
        this.usesOptionalLibraries = Collections.emptyList();
        this.usesStaticLibraries = Collections.emptyList();
        this.configPreferences = Collections.emptyList();
        this.reqFeatures = Collections.emptyList();
        this.featureGroups = Collections.emptyList();
        this.originalPackages = Collections.emptyList();
        this.adoptPermissions = Collections.emptyList();
        this.requestedPermissions = Collections.emptyList();
        this.implicitPermissions = Collections.emptyList();
        this.upgradeKeySets = Collections.emptySet();
        this.keySetMapping = Collections.emptyMap();
        this.protectedBroadcasts = Collections.emptyList();
        this.activities = Collections.emptyList();
        this.receivers = Collections.emptyList();
        this.services = Collections.emptyList();
        this.providers = Collections.emptyList();
        this.attributions = Collections.emptyList();
        this.permissions = Collections.emptyList();
        this.permissionGroups = Collections.emptyList();
        this.instrumentations = Collections.emptyList();
        this.preferredActivityFilters = Collections.emptyList();
        this.processes = Collections.emptyMap();
        this.queriesIntents = Collections.emptyList();
        this.queriesPackages = Collections.emptyList();
        this.queriesProviders = Collections.emptySet();
        this.enabled = true;
        this.installLocation = -1;
        this.packageName = TextUtils.safeIntern(str);
        this.baseCodePath = str2;
        this.codePath = str3;
        if (typedArray != null) {
            this.versionCode = typedArray.getInteger(1, 0);
            this.versionCodeMajor = typedArray.getInteger(11, 0);
            setBaseRevisionCode(typedArray.getInteger(5, 0));
            setVersionName(typedArray.getNonConfigurationString(2, 0));
            setCompileSdkVersion(typedArray.getInteger(9, 0));
            setCompileSdkVersionCodename(typedArray.getNonConfigurationString(10, 0));
            setIsolatedSplitLoading(typedArray.getBoolean(6, false));
        }
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isSupportsSmallScreens() {
        Boolean bool = this.supportsSmallScreens;
        if (bool == null) {
            return this.targetSdkVersion >= 4;
        }
        return bool.booleanValue();
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isSupportsNormalScreens() {
        Boolean bool = this.supportsNormalScreens;
        return bool == null || bool.booleanValue();
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isSupportsLargeScreens() {
        Boolean bool = this.supportsLargeScreens;
        if (bool == null) {
            return this.targetSdkVersion >= 4;
        }
        return bool.booleanValue();
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isSupportsExtraLargeScreens() {
        Boolean bool = this.supportsExtraLargeScreens;
        if (bool == null) {
            return this.targetSdkVersion >= 9;
        }
        return bool.booleanValue();
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isResizeable() {
        Boolean bool = this.resizeable;
        if (bool == null) {
            return this.targetSdkVersion >= 4;
        }
        return bool.booleanValue();
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isAnyDensity() {
        Boolean bool = this.anyDensity;
        if (bool == null) {
            return this.targetSdkVersion >= 4;
        }
        return bool.booleanValue();
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl sortActivities() {
        Collections.sort(this.activities, ORDER_COMPARATOR);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl sortReceivers() {
        Collections.sort(this.receivers, ORDER_COMPARATOR);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl sortServices() {
        Collections.sort(this.services, ORDER_COMPARATOR);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public Object hideAsParsed() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addConfigPreference(ConfigurationInfo configurationInfo) {
        this.configPreferences = CollectionUtils.add(this.configPreferences, configurationInfo);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addReqFeature(FeatureInfo featureInfo) {
        this.reqFeatures = CollectionUtils.add(this.reqFeatures, featureInfo);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addFeatureGroup(FeatureGroupInfo featureGroupInfo) {
        this.featureGroups = CollectionUtils.add(this.featureGroups, featureGroupInfo);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addProtectedBroadcast(String str) {
        if (!this.protectedBroadcasts.contains(str)) {
            this.protectedBroadcasts = CollectionUtils.add(this.protectedBroadcasts, TextUtils.safeIntern(str));
        }
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addInstrumentation(ParsedInstrumentation parsedInstrumentation) {
        this.instrumentations = CollectionUtils.add(this.instrumentations, parsedInstrumentation);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addOriginalPackage(String str) {
        this.originalPackages = CollectionUtils.add(this.originalPackages, str);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackage addOverlayable(String str, String str2) {
        this.overlayables = CollectionUtils.add(this.overlayables, str, TextUtils.safeIntern(str2));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addAdoptPermission(String str) {
        this.adoptPermissions = CollectionUtils.add(this.adoptPermissions, TextUtils.safeIntern(str));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addPermission(ParsedPermission parsedPermission) {
        this.permissions = CollectionUtils.add(this.permissions, parsedPermission);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addPermissionGroup(ParsedPermissionGroup parsedPermissionGroup) {
        this.permissionGroups = CollectionUtils.add(this.permissionGroups, parsedPermissionGroup);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addRequestedPermission(String str) {
        this.requestedPermissions = CollectionUtils.add(this.requestedPermissions, TextUtils.safeIntern(str));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addImplicitPermission(String str) {
        this.implicitPermissions = CollectionUtils.add(this.implicitPermissions, TextUtils.safeIntern(str));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addKeySet(String str, PublicKey publicKey) {
        ArraySet<PublicKey> arraySet = this.keySetMapping.get(str);
        if (arraySet == null) {
            arraySet = new ArraySet<>();
        }
        arraySet.add(publicKey);
        this.keySetMapping = CollectionUtils.add(this.keySetMapping, str, arraySet);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addActivity(ParsedActivity parsedActivity) {
        this.activities = CollectionUtils.add(this.activities, parsedActivity);
        addMimeGroupsFromComponent(parsedActivity);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addReceiver(ParsedActivity parsedActivity) {
        this.receivers = CollectionUtils.add(this.receivers, parsedActivity);
        addMimeGroupsFromComponent(parsedActivity);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addService(ParsedService parsedService) {
        this.services = CollectionUtils.add(this.services, parsedService);
        addMimeGroupsFromComponent(parsedService);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addProvider(ParsedProvider parsedProvider) {
        this.providers = CollectionUtils.add(this.providers, parsedProvider);
        addMimeGroupsFromComponent(parsedProvider);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addAttribution(ParsedAttribution parsedAttribution) {
        this.attributions = CollectionUtils.add(this.attributions, parsedAttribution);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addLibraryName(String str) {
        this.libraryNames = CollectionUtils.add(this.libraryNames, TextUtils.safeIntern(str));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addUsesOptionalLibrary(String str) {
        this.usesOptionalLibraries = CollectionUtils.add(this.usesOptionalLibraries, TextUtils.safeIntern(str));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addUsesLibrary(String str) {
        this.usesLibraries = CollectionUtils.add(this.usesLibraries, TextUtils.safeIntern(str));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl removeUsesOptionalLibrary(String str) {
        this.usesOptionalLibraries = CollectionUtils.remove(this.usesOptionalLibraries, str);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addUsesStaticLibrary(String str) {
        this.usesStaticLibraries = CollectionUtils.add(this.usesStaticLibraries, TextUtils.safeIntern(str));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addUsesStaticLibraryVersion(long j) {
        this.usesStaticLibrariesVersions = ArrayUtils.appendLong(this.usesStaticLibrariesVersions, j, true);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addUsesStaticLibraryCertDigests(String[] strArr) {
        this.usesStaticLibrariesCertDigests = (String[][]) ArrayUtils.appendElement(String[].class, this.usesStaticLibrariesCertDigests, strArr, true);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addPreferredActivityFilter(String str, ParsedIntentInfo parsedIntentInfo) {
        this.preferredActivityFilters = CollectionUtils.add(this.preferredActivityFilters, Pair.create(str, parsedIntentInfo));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addQueriesIntent(Intent intent) {
        this.queriesIntents = CollectionUtils.add(this.queriesIntents, intent);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addQueriesPackage(String str) {
        this.queriesPackages = CollectionUtils.add(this.queriesPackages, TextUtils.safeIntern(str));
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl addQueriesProvider(String str) {
        this.queriesProviders = CollectionUtils.add(this.queriesProviders, str);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSupportsSmallScreens(int i) {
        if (i == 1) {
            return this;
        }
        this.supportsSmallScreens = Boolean.valueOf(i < 0);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSupportsNormalScreens(int i) {
        if (i == 1) {
            return this;
        }
        this.supportsNormalScreens = Boolean.valueOf(i < 0);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSupportsLargeScreens(int i) {
        if (i == 1) {
            return this;
        }
        this.supportsLargeScreens = Boolean.valueOf(i < 0);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSupportsExtraLargeScreens(int i) {
        if (i == 1) {
            return this;
        }
        this.supportsExtraLargeScreens = Boolean.valueOf(i < 0);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setResizeable(int i) {
        if (i == 1) {
            return this;
        }
        this.resizeable = Boolean.valueOf(i < 0);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setAnyDensity(int i) {
        if (i == 1) {
            return this;
        }
        this.anyDensity = Boolean.valueOf(i < 0);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl asSplit(String[] strArr, String[] strArr2, int[] iArr, SparseArray<int[]> sparseArray) {
        this.splitNames = strArr;
        this.splitCodePaths = strArr2;
        this.splitRevisionCodes = iArr;
        this.splitDependencies = sparseArray;
        int length = strArr.length;
        this.splitFlags = new int[length];
        this.splitClassLoaderNames = new String[length];
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSplitHasCode(int i, boolean z) {
        int i2;
        int[] iArr = this.splitFlags;
        if (z) {
            i2 = iArr[i] | 4;
        } else {
            i2 = iArr[i] & (-5);
        }
        iArr[i] = i2;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSplitClassLoaderName(int i, String str) {
        this.splitClassLoaderNames[i] = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setRequiredAccountType(String str) {
        this.requiredAccountType = TextUtils.nullIfEmpty(str);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setOverlayTarget(String str) {
        this.overlayTarget = TextUtils.safeIntern(str);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setVolumeUuid(String str) {
        this.volumeUuid = TextUtils.safeIntern(str);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setStaticSharedLibName(String str) {
        this.staticSharedLibName = TextUtils.safeIntern(str);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSharedUserId(String str) {
        this.sharedUserId = TextUtils.safeIntern(str);
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getProcessName() {
        String str = this.processName;
        return str != null ? str : this.packageName;
    }

    public String toString() {
        return "Package{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.packageName + "}";
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    @Deprecated
    public ApplicationInfo toAppInfoWithoutState() {
        ApplicationInfo appInfoWithoutStateWithoutFlags = toAppInfoWithoutStateWithoutFlags();
        appInfoWithoutStateWithoutFlags.flags = PackageInfoWithoutStateUtils.appInfoFlags(this);
        appInfoWithoutStateWithoutFlags.privateFlags = PackageInfoWithoutStateUtils.appInfoPrivateFlags(this);
        return appInfoWithoutStateWithoutFlags;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public ApplicationInfo toAppInfoWithoutStateWithoutFlags() {
        int i;
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.appComponentFactory = this.appComponentFactory;
        applicationInfo.backupAgentName = this.backupAgentName;
        applicationInfo.banner = this.banner;
        applicationInfo.category = this.category;
        applicationInfo.classLoaderName = this.classLoaderName;
        applicationInfo.className = this.className;
        applicationInfo.compatibleWidthLimitDp = this.compatibleWidthLimitDp;
        applicationInfo.compileSdkVersion = this.compileSdkVersion;
        applicationInfo.compileSdkVersionCodename = this.compileSdkVersionCodeName;
        applicationInfo.descriptionRes = this.descriptionRes;
        applicationInfo.enabled = this.enabled;
        applicationInfo.fullBackupContent = this.fullBackupContent;
        if (!PackageParser.sUseRoundIcon || (i = this.roundIconRes) == 0) {
            i = this.iconRes;
        }
        applicationInfo.icon = i;
        applicationInfo.iconRes = this.iconRes;
        applicationInfo.roundIconRes = this.roundIconRes;
        applicationInfo.installLocation = this.installLocation;
        applicationInfo.labelRes = this.labelRes;
        applicationInfo.largestWidthLimitDp = this.largestWidthLimitDp;
        applicationInfo.logo = this.logo;
        applicationInfo.manageSpaceActivityName = this.manageSpaceActivityName;
        applicationInfo.maxAspectRatio = this.maxAspectRatio;
        applicationInfo.metaData = this.metaData;
        applicationInfo.minAspectRatio = this.minAspectRatio;
        applicationInfo.minSdkVersion = this.minSdkVersion;
        applicationInfo.name = this.className;
        if (applicationInfo.name != null) {
            applicationInfo.name = applicationInfo.name.trim();
        }
        applicationInfo.networkSecurityConfigRes = this.networkSecurityConfigRes;
        applicationInfo.nonLocalizedLabel = this.nonLocalizedLabel;
        if (applicationInfo.nonLocalizedLabel != null) {
            applicationInfo.nonLocalizedLabel = applicationInfo.nonLocalizedLabel.toString().trim();
        }
        applicationInfo.packageName = this.packageName;
        applicationInfo.permission = this.permission;
        applicationInfo.processName = getProcessName();
        applicationInfo.requiresSmallestWidthDp = this.requiresSmallestWidthDp;
        applicationInfo.splitClassLoaderNames = this.splitClassLoaderNames;
        applicationInfo.splitDependencies = this.splitDependencies;
        applicationInfo.splitNames = this.splitNames;
        applicationInfo.storageUuid = StorageManager.convert(this.volumeUuid);
        applicationInfo.targetSandboxVersion = this.targetSandboxVersion;
        applicationInfo.targetSdkVersion = this.targetSdkVersion;
        applicationInfo.taskAffinity = this.taskAffinity;
        applicationInfo.theme = this.theme;
        applicationInfo.uiOptions = this.uiOptions;
        applicationInfo.volumeUuid = this.volumeUuid;
        applicationInfo.zygotePreloadName = this.zygotePreloadName;
        applicationInfo.crossProfile = isCrossProfile();
        applicationInfo.setGwpAsanMode(this.gwpAsanMode);
        applicationInfo.setBaseCodePath(this.baseCodePath);
        applicationInfo.setBaseResourcePath(this.baseCodePath);
        applicationInfo.setCodePath(this.codePath);
        applicationInfo.setResourcePath(this.codePath);
        applicationInfo.setSplitCodePaths(this.splitCodePaths);
        applicationInfo.setSplitResourcePaths(this.splitCodePaths);
        applicationInfo.setVersionCode(PackageInfo.composeLongVersionCode(this.versionCodeMajor, this.versionCode));
        return applicationInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        sForBoolean.parcel(this.supportsSmallScreens, parcel, i);
        sForBoolean.parcel(this.supportsNormalScreens, parcel, i);
        sForBoolean.parcel(this.supportsLargeScreens, parcel, i);
        sForBoolean.parcel(this.supportsExtraLargeScreens, parcel, i);
        sForBoolean.parcel(this.resizeable, parcel, i);
        sForBoolean.parcel(this.anyDensity, parcel, i);
        parcel.writeInt(this.versionCode);
        parcel.writeInt(this.versionCodeMajor);
        parcel.writeInt(this.baseRevisionCode);
        sForInternedString.parcel(this.versionName, parcel, i);
        parcel.writeInt(this.compileSdkVersion);
        parcel.writeString(this.compileSdkVersionCodeName);
        sForInternedString.parcel(this.packageName, parcel, i);
        parcel.writeString(this.realPackage);
        parcel.writeString(this.baseCodePath);
        parcel.writeBoolean(this.requiredForAllUsers);
        parcel.writeString(this.restrictedAccountType);
        parcel.writeString(this.requiredAccountType);
        sForInternedString.parcel(this.overlayTarget, parcel, i);
        parcel.writeString(this.overlayTargetName);
        parcel.writeString(this.overlayCategory);
        parcel.writeInt(this.overlayPriority);
        parcel.writeBoolean(this.overlayIsStatic);
        sForInternedStringValueMap.parcel(this.overlayables, parcel, i);
        sForInternedString.parcel(this.staticSharedLibName, parcel, i);
        parcel.writeLong(this.staticSharedLibVersion);
        sForInternedStringList.parcel(this.libraryNames, parcel, i);
        sForInternedStringList.parcel(this.usesLibraries, parcel, i);
        sForInternedStringList.parcel(this.usesOptionalLibraries, parcel, i);
        sForInternedStringList.parcel(this.usesStaticLibraries, parcel, i);
        parcel.writeLongArray(this.usesStaticLibrariesVersions);
        String[][] strArr = this.usesStaticLibrariesCertDigests;
        if (strArr == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(strArr.length);
            int i2 = 0;
            while (true) {
                String[][] strArr2 = this.usesStaticLibrariesCertDigests;
                if (i2 >= strArr2.length) {
                    break;
                }
                parcel.writeStringArray(strArr2[i2]);
                i2++;
            }
        }
        sForInternedString.parcel(this.sharedUserId, parcel, i);
        parcel.writeInt(this.sharedUserLabel);
        parcel.writeTypedList(this.configPreferences);
        parcel.writeTypedList(this.reqFeatures);
        parcel.writeTypedList(this.featureGroups);
        parcel.writeByteArray(this.restrictUpdateHash);
        parcel.writeStringList(this.originalPackages);
        sForInternedStringList.parcel(this.adoptPermissions, parcel, i);
        sForInternedStringList.parcel(this.requestedPermissions, parcel, i);
        sForInternedStringList.parcel(this.implicitPermissions, parcel, i);
        sForStringSet.parcel(this.upgradeKeySets, parcel, i);
        parcel.writeMap(this.keySetMapping);
        sForInternedStringList.parcel(this.protectedBroadcasts, parcel, i);
        parcel.writeTypedList(this.activities);
        parcel.writeTypedList(this.receivers);
        parcel.writeTypedList(this.services);
        parcel.writeTypedList(this.providers);
        parcel.writeTypedList(this.attributions);
        parcel.writeTypedList(this.permissions);
        parcel.writeTypedList(this.permissionGroups);
        parcel.writeTypedList(this.instrumentations);
        sForIntentInfoPairs.parcel(this.preferredActivityFilters, parcel, i);
        parcel.writeMap(this.processes);
        parcel.writeBundle(this.metaData);
        sForInternedString.parcel(this.volumeUuid, parcel, i);
        parcel.writeParcelable(this.signingDetails, i);
        parcel.writeString(this.codePath);
        parcel.writeBoolean(this.use32BitAbi);
        parcel.writeBoolean(this.visibleToInstantApps);
        parcel.writeBoolean(this.forceQueryable);
        parcel.writeParcelableList(this.queriesIntents, i);
        sForInternedStringList.parcel(this.queriesPackages, parcel, i);
        sForInternedStringSet.parcel(this.queriesProviders, parcel, i);
        parcel.writeString(this.appComponentFactory);
        parcel.writeString(this.backupAgentName);
        parcel.writeInt(this.banner);
        parcel.writeInt(this.category);
        parcel.writeString(this.classLoaderName);
        parcel.writeString(this.className);
        parcel.writeInt(this.compatibleWidthLimitDp);
        parcel.writeInt(this.descriptionRes);
        parcel.writeBoolean(this.enabled);
        parcel.writeBoolean(this.crossProfile);
        parcel.writeInt(this.fullBackupContent);
        parcel.writeInt(this.iconRes);
        parcel.writeInt(this.installLocation);
        parcel.writeInt(this.labelRes);
        parcel.writeInt(this.largestWidthLimitDp);
        parcel.writeInt(this.logo);
        parcel.writeString(this.manageSpaceActivityName);
        parcel.writeFloat(this.maxAspectRatio);
        parcel.writeFloat(this.minAspectRatio);
        parcel.writeInt(this.minSdkVersion);
        parcel.writeInt(this.networkSecurityConfigRes);
        parcel.writeCharSequence(this.nonLocalizedLabel);
        parcel.writeString(this.permission);
        parcel.writeString(this.processName);
        parcel.writeInt(this.requiresSmallestWidthDp);
        parcel.writeInt(this.roundIconRes);
        parcel.writeInt(this.targetSandboxVersion);
        parcel.writeInt(this.targetSdkVersion);
        parcel.writeString(this.taskAffinity);
        parcel.writeInt(this.theme);
        parcel.writeInt(this.uiOptions);
        parcel.writeString(this.zygotePreloadName);
        parcel.writeStringArray(this.splitClassLoaderNames);
        parcel.writeStringArray(this.splitCodePaths);
        parcel.writeSparseArray(this.splitDependencies);
        parcel.writeIntArray(this.splitFlags);
        parcel.writeStringArray(this.splitNames);
        parcel.writeIntArray(this.splitRevisionCodes);
        parcel.writeBoolean(this.externalStorage);
        parcel.writeBoolean(this.baseHardwareAccelerated);
        parcel.writeBoolean(this.allowBackup);
        parcel.writeBoolean(this.killAfterRestore);
        parcel.writeBoolean(this.restoreAnyVersion);
        parcel.writeBoolean(this.fullBackupOnly);
        parcel.writeBoolean(this.persistent);
        parcel.writeBoolean(this.debuggable);
        parcel.writeBoolean(this.vmSafeMode);
        parcel.writeBoolean(this.hasCode);
        parcel.writeBoolean(this.allowTaskReparenting);
        parcel.writeBoolean(this.allowClearUserData);
        parcel.writeBoolean(this.largeHeap);
        parcel.writeBoolean(this.usesCleartextTraffic);
        parcel.writeBoolean(this.supportsRtl);
        parcel.writeBoolean(this.testOnly);
        parcel.writeBoolean(this.multiArch);
        parcel.writeBoolean(this.extractNativeLibs);
        parcel.writeBoolean(this.game);
        sForBoolean.parcel(this.resizeableActivity, parcel, i);
        parcel.writeBoolean(this.staticSharedLibrary);
        parcel.writeBoolean(this.overlay);
        parcel.writeBoolean(this.isolatedSplitLoading);
        parcel.writeBoolean(this.hasDomainUrls);
        parcel.writeBoolean(this.profileableByShell);
        parcel.writeBoolean(this.backupInForeground);
        parcel.writeBoolean(this.useEmbeddedDex);
        parcel.writeBoolean(this.defaultToDeviceProtectedStorage);
        parcel.writeBoolean(this.directBootAware);
        parcel.writeBoolean(this.partiallyDirectBootAware);
        parcel.writeBoolean(this.resizeableActivityViaSdkVersion);
        parcel.writeBoolean(this.allowClearUserDataOnFailedRestore);
        parcel.writeBoolean(this.allowAudioPlaybackCapture);
        parcel.writeBoolean(this.requestLegacyExternalStorage);
        parcel.writeBoolean(this.usesNonSdkApi);
        parcel.writeBoolean(this.hasFragileUserData);
        parcel.writeBoolean(this.cantSaveState);
        parcel.writeBoolean(this.allowNativeHeapPointerTagging);
        parcel.writeInt(this.autoRevokePermissions);
        parcel.writeBoolean(this.preserveLegacyExternalStorage);
        parcel.writeArraySet(this.mimeGroups);
        parcel.writeInt(this.gwpAsanMode);
        parcel.writeSparseIntArray(this.minExtensionVersions);
    }

    public ParsingPackageImpl(Parcel parcel) {
        this.overlayables = Collections.emptyMap();
        this.libraryNames = Collections.emptyList();
        this.usesLibraries = Collections.emptyList();
        this.usesOptionalLibraries = Collections.emptyList();
        this.usesStaticLibraries = Collections.emptyList();
        this.configPreferences = Collections.emptyList();
        this.reqFeatures = Collections.emptyList();
        this.featureGroups = Collections.emptyList();
        this.originalPackages = Collections.emptyList();
        this.adoptPermissions = Collections.emptyList();
        this.requestedPermissions = Collections.emptyList();
        this.implicitPermissions = Collections.emptyList();
        this.upgradeKeySets = Collections.emptySet();
        this.keySetMapping = Collections.emptyMap();
        this.protectedBroadcasts = Collections.emptyList();
        this.activities = Collections.emptyList();
        this.receivers = Collections.emptyList();
        this.services = Collections.emptyList();
        this.providers = Collections.emptyList();
        this.attributions = Collections.emptyList();
        this.permissions = Collections.emptyList();
        this.permissionGroups = Collections.emptyList();
        this.instrumentations = Collections.emptyList();
        this.preferredActivityFilters = Collections.emptyList();
        this.processes = Collections.emptyMap();
        this.queriesIntents = Collections.emptyList();
        this.queriesPackages = Collections.emptyList();
        this.queriesProviders = Collections.emptySet();
        this.enabled = true;
        this.installLocation = -1;
        ClassLoader classLoader = Object.class.getClassLoader();
        this.supportsSmallScreens = sForBoolean.unparcel(parcel);
        this.supportsNormalScreens = sForBoolean.unparcel(parcel);
        this.supportsLargeScreens = sForBoolean.unparcel(parcel);
        this.supportsExtraLargeScreens = sForBoolean.unparcel(parcel);
        this.resizeable = sForBoolean.unparcel(parcel);
        this.anyDensity = sForBoolean.unparcel(parcel);
        this.versionCode = parcel.readInt();
        this.versionCodeMajor = parcel.readInt();
        this.baseRevisionCode = parcel.readInt();
        this.versionName = sForInternedString.unparcel(parcel);
        this.compileSdkVersion = parcel.readInt();
        this.compileSdkVersionCodeName = parcel.readString();
        this.packageName = sForInternedString.unparcel(parcel);
        this.realPackage = parcel.readString();
        this.baseCodePath = parcel.readString();
        this.requiredForAllUsers = parcel.readBoolean();
        this.restrictedAccountType = parcel.readString();
        this.requiredAccountType = parcel.readString();
        this.overlayTarget = sForInternedString.unparcel(parcel);
        this.overlayTargetName = parcel.readString();
        this.overlayCategory = parcel.readString();
        this.overlayPriority = parcel.readInt();
        this.overlayIsStatic = parcel.readBoolean();
        this.overlayables = sForInternedStringValueMap.unparcel(parcel);
        this.staticSharedLibName = sForInternedString.unparcel(parcel);
        this.staticSharedLibVersion = parcel.readLong();
        this.libraryNames = sForInternedStringList.unparcel(parcel);
        this.usesLibraries = sForInternedStringList.unparcel(parcel);
        this.usesOptionalLibraries = sForInternedStringList.unparcel(parcel);
        this.usesStaticLibraries = sForInternedStringList.unparcel(parcel);
        this.usesStaticLibrariesVersions = parcel.createLongArray();
        int i = parcel.readInt();
        if (i >= 0) {
            this.usesStaticLibrariesCertDigests = new String[i][];
            for (int i2 = 0; i2 < i; i2++) {
                this.usesStaticLibrariesCertDigests[i2] = sForInternedStringArray.unparcel(parcel);
            }
        }
        this.sharedUserId = sForInternedString.unparcel(parcel);
        this.sharedUserLabel = parcel.readInt();
        this.configPreferences = parcel.createTypedArrayList(ConfigurationInfo.CREATOR);
        this.reqFeatures = parcel.createTypedArrayList(FeatureInfo.CREATOR);
        this.featureGroups = parcel.createTypedArrayList(FeatureGroupInfo.CREATOR);
        this.restrictUpdateHash = parcel.createByteArray();
        this.originalPackages = parcel.createStringArrayList();
        this.adoptPermissions = sForInternedStringList.unparcel(parcel);
        this.requestedPermissions = sForInternedStringList.unparcel(parcel);
        this.implicitPermissions = sForInternedStringList.unparcel(parcel);
        this.upgradeKeySets = sForStringSet.unparcel(parcel);
        this.keySetMapping = parcel.readHashMap(classLoader);
        this.protectedBroadcasts = sForInternedStringList.unparcel(parcel);
        this.activities = parcel.createTypedArrayList(ParsedActivity.CREATOR);
        this.receivers = parcel.createTypedArrayList(ParsedActivity.CREATOR);
        this.services = parcel.createTypedArrayList(ParsedService.CREATOR);
        this.providers = parcel.createTypedArrayList(ParsedProvider.CREATOR);
        this.attributions = parcel.createTypedArrayList(ParsedAttribution.CREATOR);
        this.permissions = parcel.createTypedArrayList(ParsedPermission.CREATOR);
        this.permissionGroups = parcel.createTypedArrayList(ParsedPermissionGroup.CREATOR);
        this.instrumentations = parcel.createTypedArrayList(ParsedInstrumentation.CREATOR);
        this.preferredActivityFilters = sForIntentInfoPairs.unparcel(parcel);
        this.processes = parcel.readHashMap(classLoader);
        this.metaData = parcel.readBundle(classLoader);
        this.volumeUuid = sForInternedString.unparcel(parcel);
        this.signingDetails = (PackageParser.SigningDetails) parcel.readParcelable(classLoader);
        this.codePath = parcel.readString();
        this.use32BitAbi = parcel.readBoolean();
        this.visibleToInstantApps = parcel.readBoolean();
        this.forceQueryable = parcel.readBoolean();
        this.queriesIntents = parcel.createTypedArrayList(Intent.CREATOR);
        this.queriesPackages = sForInternedStringList.unparcel(parcel);
        this.queriesProviders = sForInternedStringSet.unparcel(parcel);
        this.appComponentFactory = parcel.readString();
        this.backupAgentName = parcel.readString();
        this.banner = parcel.readInt();
        this.category = parcel.readInt();
        this.classLoaderName = parcel.readString();
        this.className = parcel.readString();
        this.compatibleWidthLimitDp = parcel.readInt();
        this.descriptionRes = parcel.readInt();
        this.enabled = parcel.readBoolean();
        this.crossProfile = parcel.readBoolean();
        this.fullBackupContent = parcel.readInt();
        this.iconRes = parcel.readInt();
        this.installLocation = parcel.readInt();
        this.labelRes = parcel.readInt();
        this.largestWidthLimitDp = parcel.readInt();
        this.logo = parcel.readInt();
        this.manageSpaceActivityName = parcel.readString();
        this.maxAspectRatio = parcel.readFloat();
        this.minAspectRatio = parcel.readFloat();
        this.minSdkVersion = parcel.readInt();
        this.networkSecurityConfigRes = parcel.readInt();
        this.nonLocalizedLabel = parcel.readCharSequence();
        this.permission = parcel.readString();
        this.processName = parcel.readString();
        this.requiresSmallestWidthDp = parcel.readInt();
        this.roundIconRes = parcel.readInt();
        this.targetSandboxVersion = parcel.readInt();
        this.targetSdkVersion = parcel.readInt();
        this.taskAffinity = parcel.readString();
        this.theme = parcel.readInt();
        this.uiOptions = parcel.readInt();
        this.zygotePreloadName = parcel.readString();
        this.splitClassLoaderNames = parcel.createStringArray();
        this.splitCodePaths = parcel.createStringArray();
        this.splitDependencies = parcel.readSparseArray(classLoader);
        this.splitFlags = parcel.createIntArray();
        this.splitNames = parcel.createStringArray();
        this.splitRevisionCodes = parcel.createIntArray();
        this.externalStorage = parcel.readBoolean();
        this.baseHardwareAccelerated = parcel.readBoolean();
        this.allowBackup = parcel.readBoolean();
        this.killAfterRestore = parcel.readBoolean();
        this.restoreAnyVersion = parcel.readBoolean();
        this.fullBackupOnly = parcel.readBoolean();
        this.persistent = parcel.readBoolean();
        this.debuggable = parcel.readBoolean();
        this.vmSafeMode = parcel.readBoolean();
        this.hasCode = parcel.readBoolean();
        this.allowTaskReparenting = parcel.readBoolean();
        this.allowClearUserData = parcel.readBoolean();
        this.largeHeap = parcel.readBoolean();
        this.usesCleartextTraffic = parcel.readBoolean();
        this.supportsRtl = parcel.readBoolean();
        this.testOnly = parcel.readBoolean();
        this.multiArch = parcel.readBoolean();
        this.extractNativeLibs = parcel.readBoolean();
        this.game = parcel.readBoolean();
        this.resizeableActivity = sForBoolean.unparcel(parcel);
        this.staticSharedLibrary = parcel.readBoolean();
        this.overlay = parcel.readBoolean();
        this.isolatedSplitLoading = parcel.readBoolean();
        this.hasDomainUrls = parcel.readBoolean();
        this.profileableByShell = parcel.readBoolean();
        this.backupInForeground = parcel.readBoolean();
        this.useEmbeddedDex = parcel.readBoolean();
        this.defaultToDeviceProtectedStorage = parcel.readBoolean();
        this.directBootAware = parcel.readBoolean();
        this.partiallyDirectBootAware = parcel.readBoolean();
        this.resizeableActivityViaSdkVersion = parcel.readBoolean();
        this.allowClearUserDataOnFailedRestore = parcel.readBoolean();
        this.allowAudioPlaybackCapture = parcel.readBoolean();
        this.requestLegacyExternalStorage = parcel.readBoolean();
        this.usesNonSdkApi = parcel.readBoolean();
        this.hasFragileUserData = parcel.readBoolean();
        this.cantSaveState = parcel.readBoolean();
        this.allowNativeHeapPointerTagging = parcel.readBoolean();
        this.autoRevokePermissions = parcel.readInt();
        this.preserveLegacyExternalStorage = parcel.readBoolean();
        this.mimeGroups = parcel.readArraySet(classLoader);
        this.gwpAsanMode = parcel.readInt();
        this.minExtensionVersions = parcel.readSparseIntArray();
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getVersionCode() {
        return this.versionCode;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getVersionCodeMajor() {
        return this.versionCodeMajor;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getBaseRevisionCode() {
        return this.baseRevisionCode;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getVersionName() {
        return this.versionName;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getCompileSdkVersion() {
        return this.compileSdkVersion;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getCompileSdkVersionCodeName() {
        return this.compileSdkVersionCodeName;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getPackageName() {
        return this.packageName;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getRealPackage() {
        return this.realPackage;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getBaseCodePath() {
        return this.baseCodePath;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isRequiredForAllUsers() {
        return this.requiredForAllUsers;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getRestrictedAccountType() {
        return this.restrictedAccountType;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getRequiredAccountType() {
        return this.requiredAccountType;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getOverlayTarget() {
        return this.overlayTarget;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getOverlayTargetName() {
        return this.overlayTargetName;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getOverlayCategory() {
        return this.overlayCategory;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getOverlayPriority() {
        return this.overlayPriority;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isOverlayIsStatic() {
        return this.overlayIsStatic;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public Map<String, String> getOverlayables() {
        return this.overlayables;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getStaticSharedLibName() {
        return this.staticSharedLibName;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public long getStaticSharedLibVersion() {
        return this.staticSharedLibVersion;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getLibraryNames() {
        return this.libraryNames;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getUsesLibraries() {
        return this.usesLibraries;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getUsesOptionalLibraries() {
        return this.usesOptionalLibraries;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getUsesStaticLibraries() {
        return this.usesStaticLibraries;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public long[] getUsesStaticLibrariesVersions() {
        return this.usesStaticLibrariesVersions;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String[][] getUsesStaticLibrariesCertDigests() {
        return this.usesStaticLibrariesCertDigests;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getSharedUserId() {
        return this.sharedUserId;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getSharedUserLabel() {
        return this.sharedUserLabel;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<ConfigurationInfo> getConfigPreferences() {
        return this.configPreferences;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<FeatureInfo> getReqFeatures() {
        return this.reqFeatures;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<FeatureGroupInfo> getFeatureGroups() {
        return this.featureGroups;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public byte[] getRestrictUpdateHash() {
        return this.restrictUpdateHash;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getOriginalPackages() {
        return this.originalPackages;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getAdoptPermissions() {
        return this.adoptPermissions;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getRequestedPermissions() {
        return this.requestedPermissions;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getImplicitPermissions() {
        return this.implicitPermissions;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public Set<String> getUpgradeKeySets() {
        return this.upgradeKeySets;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public Map<String, ArraySet<PublicKey>> getKeySetMapping() {
        return this.keySetMapping;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getProtectedBroadcasts() {
        return this.protectedBroadcasts;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<ParsedActivity> getActivities() {
        return this.activities;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<ParsedActivity> getReceivers() {
        return this.receivers;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<ParsedService> getServices() {
        return this.services;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<ParsedProvider> getProviders() {
        return this.providers;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<ParsedAttribution> getAttributions() {
        return this.attributions;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<ParsedPermission> getPermissions() {
        return this.permissions;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<ParsedPermissionGroup> getPermissionGroups() {
        return this.permissionGroups;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<ParsedInstrumentation> getInstrumentations() {
        return this.instrumentations;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<Pair<String, ParsedIntentInfo>> getPreferredActivityFilters() {
        return this.preferredActivityFilters;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public Map<String, ParsedProcess> getProcesses() {
        return this.processes;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public Bundle getMetaData() {
        return this.metaData;
    }

    private void addMimeGroupsFromComponent(ParsedComponent parsedComponent) {
        for (int size = parsedComponent.getIntents().size() - 1; size >= 0; size--) {
            ParsedIntentInfo parsedIntentInfo = parsedComponent.getIntents().get(size);
            for (int iCountMimeGroups = parsedIntentInfo.countMimeGroups() - 1; iCountMimeGroups >= 0; iCountMimeGroups--) {
                this.mimeGroups = ArrayUtils.add(this.mimeGroups, parsedIntentInfo.getMimeGroup(iCountMimeGroups));
            }
        }
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public Set<String> getMimeGroups() {
        return this.mimeGroups;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getVolumeUuid() {
        return this.volumeUuid;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public PackageParser.SigningDetails getSigningDetails() {
        return this.signingDetails;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getCodePath() {
        return this.codePath;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isUse32BitAbi() {
        return this.use32BitAbi;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isVisibleToInstantApps() {
        return this.visibleToInstantApps;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isForceQueryable() {
        return this.forceQueryable;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<Intent> getQueriesIntents() {
        return this.queriesIntents;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public List<String> getQueriesPackages() {
        return this.queriesPackages;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public Set<String> getQueriesProviders() {
        return this.queriesProviders;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String[] getSplitClassLoaderNames() {
        return this.splitClassLoaderNames;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String[] getSplitCodePaths() {
        return this.splitCodePaths;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public SparseArray<int[]> getSplitDependencies() {
        return this.splitDependencies;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int[] getSplitFlags() {
        return this.splitFlags;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String[] getSplitNames() {
        return this.splitNames;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int[] getSplitRevisionCodes() {
        return this.splitRevisionCodes;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getAppComponentFactory() {
        return this.appComponentFactory;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getBackupAgentName() {
        return this.backupAgentName;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getBanner() {
        return this.banner;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getCategory() {
        return this.category;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getClassLoaderName() {
        return this.classLoaderName;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getClassName() {
        return this.className;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getCompatibleWidthLimitDp() {
        return this.compatibleWidthLimitDp;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getDescriptionRes() {
        return this.descriptionRes;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isCrossProfile() {
        return this.crossProfile;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getFullBackupContent() {
        return this.fullBackupContent;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getIconRes() {
        return this.iconRes;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getInstallLocation() {
        return this.installLocation;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getLabelRes() {
        return this.labelRes;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getLargestWidthLimitDp() {
        return this.largestWidthLimitDp;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getLogo() {
        return this.logo;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getManageSpaceActivityName() {
        return this.manageSpaceActivityName;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public float getMaxAspectRatio() {
        return this.maxAspectRatio;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public float getMinAspectRatio() {
        return this.minAspectRatio;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public SparseIntArray getMinExtensionVersions() {
        return this.minExtensionVersions;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getMinSdkVersion() {
        return this.minSdkVersion;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getNetworkSecurityConfigRes() {
        return this.networkSecurityConfigRes;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public CharSequence getNonLocalizedLabel() {
        return this.nonLocalizedLabel;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getPermission() {
        return this.permission;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getRequiresSmallestWidthDp() {
        return this.requiresSmallestWidthDp;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getRoundIconRes() {
        return this.roundIconRes;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getTargetSandboxVersion() {
        return this.targetSandboxVersion;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getTargetSdkVersion() {
        return this.targetSdkVersion;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getTaskAffinity() {
        return this.taskAffinity;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getTheme() {
        return this.theme;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getUiOptions() {
        return this.uiOptions;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public String getZygotePreloadName() {
        return this.zygotePreloadName;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isExternalStorage() {
        return this.externalStorage;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isBaseHardwareAccelerated() {
        return this.baseHardwareAccelerated;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isAllowBackup() {
        return this.allowBackup;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isKillAfterRestore() {
        return this.killAfterRestore;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isRestoreAnyVersion() {
        return this.restoreAnyVersion;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isFullBackupOnly() {
        return this.fullBackupOnly;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isPersistent() {
        return this.persistent;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isDebuggable() {
        return this.debuggable;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isVmSafeMode() {
        return this.vmSafeMode;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isHasCode() {
        return this.hasCode;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isAllowTaskReparenting() {
        return this.allowTaskReparenting;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isAllowClearUserData() {
        return this.allowClearUserData;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isLargeHeap() {
        return this.largeHeap;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isUsesCleartextTraffic() {
        return this.usesCleartextTraffic;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isSupportsRtl() {
        return this.supportsRtl;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isTestOnly() {
        return this.testOnly;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isMultiArch() {
        return this.multiArch;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isExtractNativeLibs() {
        return this.extractNativeLibs;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isGame() {
        return this.game;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public Boolean getResizeableActivity() {
        return this.resizeableActivity;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isStaticSharedLibrary() {
        return this.staticSharedLibrary;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isOverlay() {
        return this.overlay;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isIsolatedSplitLoading() {
        return this.isolatedSplitLoading;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isHasDomainUrls() {
        return this.hasDomainUrls;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isProfileableByShell() {
        return this.profileableByShell;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isBackupInForeground() {
        return this.backupInForeground;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isUseEmbeddedDex() {
        return this.useEmbeddedDex;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isDefaultToDeviceProtectedStorage() {
        return this.defaultToDeviceProtectedStorage;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isDirectBootAware() {
        return this.directBootAware;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getGwpAsanMode() {
        return this.gwpAsanMode;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isPartiallyDirectBootAware() {
        return this.partiallyDirectBootAware;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isResizeableActivityViaSdkVersion() {
        return this.resizeableActivityViaSdkVersion;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isAllowClearUserDataOnFailedRestore() {
        return this.allowClearUserDataOnFailedRestore;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isAllowAudioPlaybackCapture() {
        return this.allowAudioPlaybackCapture;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isRequestLegacyExternalStorage() {
        return this.requestLegacyExternalStorage;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isUsesNonSdkApi() {
        return this.usesNonSdkApi;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isHasFragileUserData() {
        return this.hasFragileUserData;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isCantSaveState() {
        return this.cantSaveState;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean isAllowNativeHeapPointerTagging() {
        return this.allowNativeHeapPointerTagging;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public int getAutoRevokePermissions() {
        return this.autoRevokePermissions;
    }

    @Override // android.content.pm.parsing.ParsingPackageRead
    public boolean hasPreserveLegacyExternalStorage() {
        return this.preserveLegacyExternalStorage;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setBaseRevisionCode(int i) {
        this.baseRevisionCode = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setCompileSdkVersion(int i) {
        this.compileSdkVersion = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setRequiredForAllUsers(boolean z) {
        this.requiredForAllUsers = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setOverlayPriority(int i) {
        this.overlayPriority = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setOverlayIsStatic(boolean z) {
        this.overlayIsStatic = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setStaticSharedLibVersion(long j) {
        this.staticSharedLibVersion = j;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSharedUserLabel(int i) {
        this.sharedUserLabel = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setRestrictUpdateHash(byte... bArr) {
        this.restrictUpdateHash = bArr;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setUpgradeKeySets(Set<String> set) {
        this.upgradeKeySets = set;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setProcesses(Map<String, ParsedProcess> map) {
        this.processes = map;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setMetaData(Bundle bundle) {
        this.metaData = bundle;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSigningDetails(PackageParser.SigningDetails signingDetails) {
        this.signingDetails = signingDetails;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setUse32BitAbi(boolean z) {
        this.use32BitAbi = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setVisibleToInstantApps(boolean z) {
        this.visibleToInstantApps = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setForceQueryable(boolean z) {
        this.forceQueryable = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setBanner(int i) {
        this.banner = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setCategory(int i) {
        this.category = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setCompatibleWidthLimitDp(int i) {
        this.compatibleWidthLimitDp = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setDescriptionRes(int i) {
        this.descriptionRes = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setCrossProfile(boolean z) {
        this.crossProfile = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setFullBackupContent(int i) {
        this.fullBackupContent = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setIconRes(int i) {
        this.iconRes = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setInstallLocation(int i) {
        this.installLocation = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setLabelRes(int i) {
        this.labelRes = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setLargestWidthLimitDp(int i) {
        this.largestWidthLimitDp = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setLogo(int i) {
        this.logo = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setMaxAspectRatio(float f) {
        this.maxAspectRatio = f;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setMinAspectRatio(float f) {
        this.minAspectRatio = f;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setMinExtensionVersions(SparseIntArray sparseIntArray) {
        this.minExtensionVersions = sparseIntArray;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setMinSdkVersion(int i) {
        this.minSdkVersion = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setNetworkSecurityConfigRes(int i) {
        this.networkSecurityConfigRes = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setNonLocalizedLabel(CharSequence charSequence) {
        this.nonLocalizedLabel = charSequence;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setRequiresSmallestWidthDp(int i) {
        this.requiresSmallestWidthDp = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setRoundIconRes(int i) {
        this.roundIconRes = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setTargetSandboxVersion(int i) {
        this.targetSandboxVersion = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setTargetSdkVersion(int i) {
        this.targetSdkVersion = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setTheme(int i) {
        this.theme = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setUiOptions(int i) {
        this.uiOptions = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setExternalStorage(boolean z) {
        this.externalStorage = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setBaseHardwareAccelerated(boolean z) {
        this.baseHardwareAccelerated = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setAllowBackup(boolean z) {
        this.allowBackup = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setKillAfterRestore(boolean z) {
        this.killAfterRestore = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setRestoreAnyVersion(boolean z) {
        this.restoreAnyVersion = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setFullBackupOnly(boolean z) {
        this.fullBackupOnly = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setPersistent(boolean z) {
        this.persistent = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setDebuggable(boolean z) {
        this.debuggable = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setVmSafeMode(boolean z) {
        this.vmSafeMode = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setHasCode(boolean z) {
        this.hasCode = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setAllowTaskReparenting(boolean z) {
        this.allowTaskReparenting = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setAllowClearUserData(boolean z) {
        this.allowClearUserData = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setLargeHeap(boolean z) {
        this.largeHeap = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setUsesCleartextTraffic(boolean z) {
        this.usesCleartextTraffic = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setSupportsRtl(boolean z) {
        this.supportsRtl = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setTestOnly(boolean z) {
        this.testOnly = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setMultiArch(boolean z) {
        this.multiArch = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setExtractNativeLibs(boolean z) {
        this.extractNativeLibs = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setGame(boolean z) {
        this.game = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setResizeableActivity(Boolean bool) {
        this.resizeableActivity = bool;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setStaticSharedLibrary(boolean z) {
        this.staticSharedLibrary = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setOverlay(boolean z) {
        this.overlay = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setIsolatedSplitLoading(boolean z) {
        this.isolatedSplitLoading = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setHasDomainUrls(boolean z) {
        this.hasDomainUrls = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setProfileableByShell(boolean z) {
        this.profileableByShell = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setBackupInForeground(boolean z) {
        this.backupInForeground = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setUseEmbeddedDex(boolean z) {
        this.useEmbeddedDex = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setDefaultToDeviceProtectedStorage(boolean z) {
        this.defaultToDeviceProtectedStorage = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setDirectBootAware(boolean z) {
        this.directBootAware = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setGwpAsanMode(int i) {
        this.gwpAsanMode = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setPartiallyDirectBootAware(boolean z) {
        this.partiallyDirectBootAware = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setResizeableActivityViaSdkVersion(boolean z) {
        this.resizeableActivityViaSdkVersion = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setAllowClearUserDataOnFailedRestore(boolean z) {
        this.allowClearUserDataOnFailedRestore = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setAllowAudioPlaybackCapture(boolean z) {
        this.allowAudioPlaybackCapture = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setRequestLegacyExternalStorage(boolean z) {
        this.requestLegacyExternalStorage = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setUsesNonSdkApi(boolean z) {
        this.usesNonSdkApi = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setHasFragileUserData(boolean z) {
        this.hasFragileUserData = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setCantSaveState(boolean z) {
        this.cantSaveState = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setAllowNativeHeapPointerTagging(boolean z) {
        this.allowNativeHeapPointerTagging = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setAutoRevokePermissions(int i) {
        this.autoRevokePermissions = i;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setPreserveLegacyExternalStorage(boolean z) {
        this.preserveLegacyExternalStorage = z;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setVersionName(String str) {
        this.versionName = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackage setCompileSdkVersionCodename(String str) {
        this.compileSdkVersionCodeName = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setProcessName(String str) {
        this.processName = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setRealPackage(String str) {
        this.realPackage = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setRestrictedAccountType(String str) {
        this.restrictedAccountType = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setOverlayTargetName(String str) {
        this.overlayTargetName = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setOverlayCategory(String str) {
        this.overlayCategory = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setAppComponentFactory(String str) {
        this.appComponentFactory = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setBackupAgentName(String str) {
        this.backupAgentName = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setClassLoaderName(String str) {
        this.classLoaderName = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setClassName(String str) {
        this.className = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setManageSpaceActivityName(String str) {
        this.manageSpaceActivityName = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setPermission(String str) {
        this.permission = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setTaskAffinity(String str) {
        this.taskAffinity = str;
        return this;
    }

    @Override // android.content.pm.parsing.ParsingPackage
    public ParsingPackageImpl setZygotePreloadName(String str) {
        this.zygotePreloadName = str;
        return this;
    }
}
