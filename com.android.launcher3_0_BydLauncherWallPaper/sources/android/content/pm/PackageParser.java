package android.content.pm;

import android.Manifest;
import android.apex.ApexInfo;
import android.app.ActivityTaskManager;
import android.app.ActivityThread;
import android.app.ResourcesManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.split.DefaultSplitAssetLoader;
import android.content.pm.split.SplitAssetDependencyLoader;
import android.content.pm.split.SplitAssetLoader;
import android.content.pm.split.SplitDependencyLoader;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.provider.SettingsStringUtil;
import android.security.keystore.KeyProperties;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.PackageUtils;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.apk.ApkSignatureVerifier;
import android.view.ViewGroup;
import com.android.internal.R;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import libcore.io.IoUtils;
import libcore.util.EmptyArray;
import libcore.util.HexEncoding;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class PackageParser {
    public static final String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";
    public static final String ANDROID_RESOURCES = "http://schemas.android.com/apk/res/android";
    public static final String APEX_FILE_EXTENSION = ".apex";
    public static final String APK_FILE_EXTENSION = ".apk";
    public static final Set<String> CHILD_PACKAGE_TAGS;
    public static final boolean DEBUG_BACKUP = false;
    public static final boolean DEBUG_JAR = false;
    public static final boolean DEBUG_PARSER = false;
    private static final int DEFAULT_MIN_SDK_VERSION = 1;
    public static final float DEFAULT_PRE_O_MAX_ASPECT_RATIO = 1.86f;
    public static final float DEFAULT_PRE_Q_MIN_ASPECT_RATIO = 1.333f;
    public static final float DEFAULT_PRE_Q_MIN_ASPECT_RATIO_WATCH = 1.0f;
    private static final int DEFAULT_TARGET_SDK_VERSION = 0;
    public static final boolean LOG_PARSE_TIMINGS = Build.IS_DEBUGGABLE;
    public static final int LOG_PARSE_TIMINGS_THRESHOLD_MS = 100;
    public static final boolean LOG_UNSAFE_BROADCASTS = false;
    public static final String METADATA_ACTIVITY_WINDOW_LAYOUT_AFFINITY = "android.activity_window_layout_affinity";
    public static final String METADATA_MAX_ASPECT_RATIO = "android.max_aspect";
    public static final String METADATA_SUPPORTS_SIZE_CHANGES = "android.supports_size_changes";
    public static final String MNT_EXPAND = "/mnt/expand/";
    public static final boolean MULTI_PACKAGE_APK_ENABLED;
    public static final NewPermissionInfo[] NEW_PERMISSIONS;
    public static final int PARSE_CHATTY = Integer.MIN_VALUE;
    public static final int PARSE_COLLECT_CERTIFICATES = 32;
    public static final int PARSE_DEFAULT_INSTALL_LOCATION = -1;
    public static final int PARSE_DEFAULT_TARGET_SANDBOX = 1;
    public static final int PARSE_ENFORCE_CODE = 64;
    public static final int PARSE_EXTERNAL_STORAGE = 8;
    public static final int PARSE_IGNORE_PROCESSES = 2;
    public static final int PARSE_IS_SYSTEM_DIR = 16;
    public static final int PARSE_MUST_BE_APK = 1;
    private static final String PROPERTY_CHILD_PACKAGES_ENABLED = "persist.sys.child_packages_enabled";
    private static final int RECREATE_ON_CONFIG_CHANGES_MASK = 3;
    public static final boolean RIGID_PARSER = false;
    public static final Set<String> SAFE_BROADCASTS;
    public static final String[] SDK_CODENAMES;
    public static final int SDK_VERSION;
    private static final String TAG = "PackageParser";
    public static final String TAG_ADOPT_PERMISSIONS = "adopt-permissions";
    public static final String TAG_APPLICATION = "application";
    public static final String TAG_ATTRIBUTION = "attribution";
    public static final String TAG_COMPATIBLE_SCREENS = "compatible-screens";
    public static final String TAG_EAT_COMMENT = "eat-comment";
    public static final String TAG_FEATURE_GROUP = "feature-group";
    public static final String TAG_INSTRUMENTATION = "instrumentation";
    public static final String TAG_KEY_SETS = "key-sets";
    public static final String TAG_MANIFEST = "manifest";
    public static final String TAG_ORIGINAL_PACKAGE = "original-package";
    public static final String TAG_OVERLAY = "overlay";
    public static final String TAG_PACKAGE = "package";
    public static final String TAG_PACKAGE_VERIFIER = "package-verifier";
    public static final String TAG_PERMISSION = "permission";
    public static final String TAG_PERMISSION_GROUP = "permission-group";
    public static final String TAG_PERMISSION_TREE = "permission-tree";
    public static final String TAG_PROFILEABLE = "profileable";
    public static final String TAG_PROTECTED_BROADCAST = "protected-broadcast";
    public static final String TAG_QUERIES = "queries";
    public static final String TAG_RESTRICT_UPDATE = "restrict-update";
    public static final String TAG_SUPPORTS_INPUT = "supports-input";
    public static final String TAG_SUPPORT_SCREENS = "supports-screens";
    public static final String TAG_USES_CONFIGURATION = "uses-configuration";
    public static final String TAG_USES_FEATURE = "uses-feature";
    public static final String TAG_USES_GL_TEXTURE = "uses-gl-texture";
    public static final String TAG_USES_PERMISSION = "uses-permission";
    public static final String TAG_USES_PERMISSION_SDK_23 = "uses-permission-sdk-23";
    public static final String TAG_USES_PERMISSION_SDK_M = "uses-permission-sdk-m";
    public static final String TAG_USES_SDK = "uses-sdk";
    public static final String TAG_USES_SPLIT = "uses-split";
    public static boolean sCompatibilityModeEnabled;
    public static final Comparator<String> sSplitNameComparator;
    public static boolean sUseRoundIcon;

    @Deprecated
    public String mArchiveSourcePath;
    private File mCacheDir;
    public Callback mCallback;
    private DisplayMetrics mMetrics;
    private boolean mOnlyCoreApps;
    public int mParseError = 1;
    private ParsePackageItemArgs mParseInstrumentationArgs;
    public String[] mSeparateProcesses;

    public interface Callback {
        boolean hasFeature(String str);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ParseFlags {
    }

    public static int getActivityConfigChanges(int i, int i2) {
        return i | ((~i2) & 3);
    }

    static {
        MULTI_PACKAGE_APK_ENABLED = Build.IS_DEBUGGABLE && SystemProperties.getBoolean(PROPERTY_CHILD_PACKAGES_ENABLED, false);
        ArraySet arraySet = new ArraySet();
        CHILD_PACKAGE_TAGS = arraySet;
        arraySet.add(TAG_APPLICATION);
        arraySet.add(TAG_COMPATIBLE_SCREENS);
        arraySet.add(TAG_EAT_COMMENT);
        arraySet.add(TAG_FEATURE_GROUP);
        arraySet.add(TAG_INSTRUMENTATION);
        arraySet.add(TAG_SUPPORT_SCREENS);
        arraySet.add(TAG_SUPPORTS_INPUT);
        arraySet.add(TAG_USES_CONFIGURATION);
        arraySet.add(TAG_USES_FEATURE);
        arraySet.add(TAG_USES_GL_TEXTURE);
        arraySet.add(TAG_USES_PERMISSION);
        arraySet.add(TAG_USES_PERMISSION_SDK_23);
        arraySet.add(TAG_USES_PERMISSION_SDK_M);
        arraySet.add(TAG_USES_SDK);
        ArraySet arraySet2 = new ArraySet();
        SAFE_BROADCASTS = arraySet2;
        arraySet2.add(Intent.ACTION_BOOT_COMPLETED);
        NEW_PERMISSIONS = new NewPermissionInfo[]{new NewPermissionInfo(Manifest.permission.WRITE_EXTERNAL_STORAGE, 4, 0), new NewPermissionInfo(Manifest.permission.READ_PHONE_STATE, 4, 0)};
        SDK_VERSION = Build.VERSION.SDK_INT;
        SDK_CODENAMES = Build.VERSION.ACTIVE_CODENAMES;
        sCompatibilityModeEnabled = true;
        sUseRoundIcon = false;
        sSplitNameComparator = new SplitNameComparator();
    }

    public static class NewPermissionInfo {
        public final int fileVersion;
        public final String name;
        public final int sdkVersion;

        public NewPermissionInfo(String str, int i, int i2) {
            this.name = str;
            this.sdkVersion = i;
            this.fileVersion = i2;
        }
    }

    static class ParsePackageItemArgs {
        final int bannerRes;
        final int iconRes;
        final int labelRes;
        final int logoRes;
        final int nameRes;
        final String[] outError;
        final Package owner;
        final int roundIconRes;
        TypedArray sa;
        String tag;

        ParsePackageItemArgs(Package r1, String[] strArr, int i, int i2, int i3, int i4, int i5, int i6) {
            this.owner = r1;
            this.outError = strArr;
            this.nameRes = i;
            this.labelRes = i2;
            this.iconRes = i3;
            this.logoRes = i5;
            this.bannerRes = i6;
            this.roundIconRes = i4;
        }
    }

    public static class ParseComponentArgs extends ParsePackageItemArgs {
        final int descriptionRes;
        final int enabledRes;
        int flags;
        final int processRes;
        final String[] sepProcesses;

        public ParseComponentArgs(Package r1, String[] strArr, int i, int i2, int i3, int i4, int i5, int i6, String[] strArr2, int i7, int i8, int i9) {
            super(r1, strArr, i, i2, i3, i4, i5, i6);
            this.sepProcesses = strArr2;
            this.processRes = i7;
            this.descriptionRes = i8;
            this.enabledRes = i9;
        }
    }

    public static class PackageLite {
        public final String baseCodePath;
        public final int baseRevisionCode;
        public final String codePath;
        public final String[] configForSplit;
        public final boolean coreApp;
        public final boolean debuggable;
        public final boolean extractNativeLibs;
        public final int installLocation;
        public final boolean[] isFeatureSplits;
        public final boolean isolatedSplits;
        public final boolean multiArch;
        public final String packageName;
        public final String[] splitCodePaths;
        public final String[] splitNames;
        public final int[] splitRevisionCodes;
        public final boolean use32bitAbi;
        public final String[] usesSplitNames;
        public final VerifierInfo[] verifiers;
        public final int versionCode;
        public final int versionCodeMajor;

        public PackageLite(String str, ApkLite apkLite, String[] strArr, boolean[] zArr, String[] strArr2, String[] strArr3, String[] strArr4, int[] iArr) {
            this.packageName = apkLite.packageName;
            this.versionCode = apkLite.versionCode;
            this.versionCodeMajor = apkLite.versionCodeMajor;
            this.installLocation = apkLite.installLocation;
            this.verifiers = apkLite.verifiers;
            this.splitNames = strArr;
            this.isFeatureSplits = zArr;
            this.usesSplitNames = strArr2;
            this.configForSplit = strArr3;
            this.codePath = str;
            this.baseCodePath = apkLite.codePath;
            this.splitCodePaths = strArr4;
            this.baseRevisionCode = apkLite.revisionCode;
            this.splitRevisionCodes = iArr;
            this.coreApp = apkLite.coreApp;
            this.debuggable = apkLite.debuggable;
            this.multiArch = apkLite.multiArch;
            this.use32bitAbi = apkLite.use32bitAbi;
            this.extractNativeLibs = apkLite.extractNativeLibs;
            this.isolatedSplits = apkLite.isolatedSplits;
        }

        public List<String> getAllCodePaths() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.baseCodePath);
            if (!ArrayUtils.isEmpty(this.splitCodePaths)) {
                Collections.addAll(arrayList, this.splitCodePaths);
            }
            return arrayList;
        }
    }

    public static class ApkLite {
        public final String codePath;
        public final String configForSplit;
        public final boolean coreApp;
        public final boolean debuggable;
        public final boolean extractNativeLibs;
        public final int installLocation;
        public boolean isFeatureSplit;
        public final boolean isSplitRequired;
        public final boolean isolatedSplits;
        public final int minSdkVersion;
        public final boolean multiArch;
        public final boolean overlayIsStatic;
        public final int overlayPriority;
        public final String packageName;
        public final boolean profilableByShell;
        public final int revisionCode;
        public final SigningDetails signingDetails;
        public final String splitName;
        public final String targetPackageName;
        public final int targetSdkVersion;
        public final boolean use32bitAbi;
        public final boolean useEmbeddedDex;
        public final String usesSplitName;
        public final VerifierInfo[] verifiers;
        public final int versionCode;
        public final int versionCodeMajor;

        public ApkLite(String str, String str2, String str3, boolean z, String str4, String str5, boolean z2, int i, int i2, int i3, int i4, List<VerifierInfo> list, SigningDetails signingDetails, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, String str6, boolean z11, int i5, int i6, int i7) {
            this.codePath = str;
            this.packageName = str2;
            this.splitName = str3;
            this.isFeatureSplit = z;
            this.configForSplit = str4;
            this.usesSplitName = str5;
            this.versionCode = i;
            this.versionCodeMajor = i2;
            this.revisionCode = i3;
            this.installLocation = i4;
            this.signingDetails = signingDetails;
            this.verifiers = (VerifierInfo[]) list.toArray(new VerifierInfo[list.size()]);
            this.coreApp = z3;
            this.debuggable = z4;
            this.profilableByShell = z5;
            this.multiArch = z6;
            this.use32bitAbi = z7;
            this.useEmbeddedDex = z8;
            this.extractNativeLibs = z9;
            this.isolatedSplits = z10;
            this.isSplitRequired = z2;
            this.targetPackageName = str6;
            this.overlayIsStatic = z11;
            this.overlayPriority = i5;
            this.minSdkVersion = i6;
            this.targetSdkVersion = i7;
        }

        public long getLongVersionCode() {
            return PackageInfo.composeLongVersionCode(this.versionCodeMajor, this.versionCode);
        }
    }

    private static class CachedComponentArgs {
        ParseComponentArgs mActivityAliasArgs;
        ParseComponentArgs mActivityArgs;
        ParseComponentArgs mProviderArgs;
        ParseComponentArgs mServiceArgs;

        private CachedComponentArgs() {
        }
    }

    public PackageParser() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mMetrics = displayMetrics;
        displayMetrics.setToDefaults();
    }

    public void setSeparateProcesses(String[] strArr) {
        this.mSeparateProcesses = strArr;
    }

    public void setOnlyCoreApps(boolean z) {
        this.mOnlyCoreApps = z;
    }

    public void setDisplayMetrics(DisplayMetrics displayMetrics) {
        this.mMetrics = displayMetrics;
    }

    public void setCacheDir(File file) {
        this.mCacheDir = file;
    }

    public static final class CallbackImpl implements Callback {
        private final PackageManager mPm;

        public CallbackImpl(PackageManager packageManager) {
            this.mPm = packageManager;
        }

        @Override // android.content.pm.PackageParser.Callback
        public boolean hasFeature(String str) {
            return this.mPm.hasSystemFeature(str);
        }
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public static final boolean isApkFile(File file) {
        return isApkPath(file.getName());
    }

    public static boolean isApkPath(String str) {
        return str.endsWith(APK_FILE_EXTENSION);
    }

    private static boolean checkUseInstalledOrHidden(int i, PackageUserState packageUserState, ApplicationInfo applicationInfo) {
        int i2 = 536870912 & i;
        if (i2 == 0 && !packageUserState.installed && applicationInfo != null && applicationInfo.hiddenUntilInstalled) {
            return false;
        }
        if (!packageUserState.isAvailable(i)) {
            if (applicationInfo == null || !applicationInfo.isSystemApp()) {
                return false;
            }
            if ((i & PackageManager.MATCH_KNOWN_PACKAGES) == 0 && i2 == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAvailable(PackageUserState packageUserState) {
        return checkUseInstalledOrHidden(0, packageUserState, null);
    }

    public static PackageInfo generatePackageInfo(Package r10, int[] iArr, int i, long j, long j2, Set<String> set, PackageUserState packageUserState) {
        return generatePackageInfo(r10, iArr, i, j, j2, set, packageUserState, UserHandle.getCallingUserId());
    }

    public static PackageInfo generatePackageInfo(Package r11, int[] iArr, int i, long j, long j2, Set<String> set, PackageUserState packageUserState, int i2) {
        return generatePackageInfo(r11, null, iArr, i, j, j2, set, packageUserState, i2);
    }

    public static PackageInfo generatePackageInfo(Package r11, ApexInfo apexInfo, int i) {
        return generatePackageInfo(r11, apexInfo, EmptyArray.INT, i, 0L, 0L, Collections.emptySet(), new PackageUserState(), UserHandle.getCallingUserId());
    }

    private static PackageInfo generatePackageInfo(Package r16, ApexInfo apexInfo, int[] iArr, int i, long j, long j2, Set<String> set, PackageUserState packageUserState, int i2) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        if (!checkUseInstalledOrHidden(i, packageUserState, r16.applicationInfo) || !r16.isMatch(i)) {
            return null;
        }
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.packageName = r16.packageName;
        packageInfo.splitNames = r16.splitNames;
        packageInfo.versionCode = r16.mVersionCode;
        packageInfo.versionCodeMajor = r16.mVersionCodeMajor;
        packageInfo.baseRevisionCode = r16.baseRevisionCode;
        packageInfo.splitRevisionCodes = r16.splitRevisionCodes;
        packageInfo.versionName = r16.mVersionName;
        packageInfo.sharedUserId = r16.mSharedUserId;
        packageInfo.sharedUserLabel = r16.mSharedUserLabel;
        packageInfo.applicationInfo = generateApplicationInfo(r16, i, packageUserState, i2);
        packageInfo.installLocation = r16.installLocation;
        packageInfo.isStub = r16.isStub;
        packageInfo.coreApp = r16.coreApp;
        if ((packageInfo.applicationInfo.flags & 1) != 0 || (packageInfo.applicationInfo.flags & 128) != 0) {
            packageInfo.requiredForAllUsers = r16.mRequiredForAllUsers;
        }
        packageInfo.restrictedAccountType = r16.mRestrictedAccountType;
        packageInfo.requiredAccountType = r16.mRequiredAccountType;
        packageInfo.overlayTarget = r16.mOverlayTarget;
        packageInfo.targetOverlayableName = r16.mOverlayTargetName;
        packageInfo.overlayCategory = r16.mOverlayCategory;
        packageInfo.overlayPriority = r16.mOverlayPriority;
        packageInfo.mOverlayIsStatic = r16.mOverlayIsStatic;
        packageInfo.compileSdkVersion = r16.mCompileSdkVersion;
        packageInfo.compileSdkVersionCodename = r16.mCompileSdkVersionCodename;
        packageInfo.firstInstallTime = j;
        packageInfo.lastUpdateTime = j2;
        if ((i & 256) != 0) {
            packageInfo.gids = iArr;
        }
        if ((i & 16384) != 0) {
            int size6 = r16.configPreferences != null ? r16.configPreferences.size() : 0;
            if (size6 > 0) {
                packageInfo.configPreferences = new ConfigurationInfo[size6];
                r16.configPreferences.toArray(packageInfo.configPreferences);
            }
            int size7 = r16.reqFeatures != null ? r16.reqFeatures.size() : 0;
            if (size7 > 0) {
                packageInfo.reqFeatures = new FeatureInfo[size7];
                r16.reqFeatures.toArray(packageInfo.reqFeatures);
            }
            int size8 = r16.featureGroups != null ? r16.featureGroups.size() : 0;
            if (size8 > 0) {
                packageInfo.featureGroups = new FeatureGroupInfo[size8];
                r16.featureGroups.toArray(packageInfo.featureGroups);
            }
        }
        if ((i & 1) != 0 && (size5 = r16.activities.size()) > 0) {
            ActivityInfo[] activityInfoArr = new ActivityInfo[size5];
            int i3 = 0;
            for (int i4 = 0; i4 < size5; i4++) {
                Activity activity = r16.activities.get(i4);
                if (packageUserState.isMatch(activity.info, i) && !PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(activity.className)) {
                    activityInfoArr[i3] = generateActivityInfo(activity, i, packageUserState, i2);
                    i3++;
                }
            }
            packageInfo.activities = (ActivityInfo[]) ArrayUtils.trimToSize(activityInfoArr, i3);
        }
        if ((i & 2) != 0 && (size4 = r16.receivers.size()) > 0) {
            ActivityInfo[] activityInfoArr2 = new ActivityInfo[size4];
            int i5 = 0;
            for (int i6 = 0; i6 < size4; i6++) {
                Activity activity2 = r16.receivers.get(i6);
                if (packageUserState.isMatch(activity2.info, i)) {
                    activityInfoArr2[i5] = generateActivityInfo(activity2, i, packageUserState, i2);
                    i5++;
                }
            }
            packageInfo.receivers = (ActivityInfo[]) ArrayUtils.trimToSize(activityInfoArr2, i5);
        }
        if ((i & 4) != 0 && (size3 = r16.services.size()) > 0) {
            ServiceInfo[] serviceInfoArr = new ServiceInfo[size3];
            int i7 = 0;
            for (int i8 = 0; i8 < size3; i8++) {
                Service service = r16.services.get(i8);
                if (packageUserState.isMatch(service.info, i)) {
                    serviceInfoArr[i7] = generateServiceInfo(service, i, packageUserState, i2);
                    i7++;
                }
            }
            packageInfo.services = (ServiceInfo[]) ArrayUtils.trimToSize(serviceInfoArr, i7);
        }
        if ((i & 8) != 0 && (size2 = r16.providers.size()) > 0) {
            ProviderInfo[] providerInfoArr = new ProviderInfo[size2];
            int i9 = 0;
            for (int i10 = 0; i10 < size2; i10++) {
                Provider provider = r16.providers.get(i10);
                if (packageUserState.isMatch(provider.info, i)) {
                    providerInfoArr[i9] = generateProviderInfo(provider, i, packageUserState, i2);
                    i9++;
                }
            }
            packageInfo.providers = (ProviderInfo[]) ArrayUtils.trimToSize(providerInfoArr, i9);
        }
        if ((i & 16) != 0 && (size = r16.instrumentation.size()) > 0) {
            packageInfo.instrumentation = new InstrumentationInfo[size];
            for (int i11 = 0; i11 < size; i11++) {
                packageInfo.instrumentation[i11] = generateInstrumentationInfo(r16.instrumentation.get(i11), i);
            }
        }
        if ((i & 4096) != 0) {
            int size9 = r16.permissions.size();
            if (size9 > 0) {
                packageInfo.permissions = new PermissionInfo[size9];
                for (int i12 = 0; i12 < size9; i12++) {
                    packageInfo.permissions[i12] = generatePermissionInfo(r16.permissions.get(i12), i);
                }
            }
            int size10 = r16.requestedPermissions.size();
            if (size10 > 0) {
                packageInfo.requestedPermissions = new String[size10];
                packageInfo.requestedPermissionsFlags = new int[size10];
                for (int i13 = 0; i13 < size10; i13++) {
                    String str = r16.requestedPermissions.get(i13);
                    packageInfo.requestedPermissions[i13] = str;
                    int[] iArr2 = packageInfo.requestedPermissionsFlags;
                    iArr2[i13] = iArr2[i13] | 1;
                    if (set != null && set.contains(str)) {
                        int[] iArr3 = packageInfo.requestedPermissionsFlags;
                        iArr3[i13] = iArr3[i13] | 2;
                    }
                }
            }
        }
        if (apexInfo != null) {
            File file = new File(apexInfo.modulePath);
            packageInfo.applicationInfo.sourceDir = file.getPath();
            packageInfo.applicationInfo.publicSourceDir = file.getPath();
            if (apexInfo.isFactory) {
                packageInfo.applicationInfo.flags |= 1;
            } else {
                packageInfo.applicationInfo.flags &= -2;
            }
            if (apexInfo.isActive) {
                packageInfo.applicationInfo.flags |= 8388608;
            } else {
                packageInfo.applicationInfo.flags &= -8388609;
            }
            packageInfo.isApex = true;
        }
        if ((i & 64) != 0) {
            if (r16.mSigningDetails.hasPastSigningCertificates()) {
                packageInfo.signatures = new Signature[1];
                packageInfo.signatures[0] = r16.mSigningDetails.pastSigningCertificates[0];
            } else if (r16.mSigningDetails.hasSignatures()) {
                int length = r16.mSigningDetails.signatures.length;
                packageInfo.signatures = new Signature[length];
                System.arraycopy(r16.mSigningDetails.signatures, 0, packageInfo.signatures, 0, length);
            }
        }
        if ((134217728 & i) != 0) {
            if (r16.mSigningDetails != SigningDetails.UNKNOWN) {
                packageInfo.signingInfo = new SigningInfo(r16.mSigningDetails);
            } else {
                packageInfo.signingInfo = null;
            }
        }
        return packageInfo;
    }

    private static class SplitNameComparator implements Comparator<String> {
        private SplitNameComparator() {
        }

        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            if (str == null) {
                return -1;
            }
            if (str2 == null) {
                return 1;
            }
            return str.compareTo(str2);
        }
    }

    public static PackageLite parsePackageLite(File file, int i) throws PackageParserException {
        if (file.isDirectory()) {
            return parseClusterPackageLite(file, i);
        }
        return parseMonolithicPackageLite(file, i);
    }

    private static PackageLite parseMonolithicPackageLite(File file, int i) throws PackageParserException {
        Trace.traceBegin(262144L, "parseApkLite");
        ApkLite apkLite = parseApkLite(file, i);
        String absolutePath = file.getAbsolutePath();
        Trace.traceEnd(262144L);
        return new PackageLite(absolutePath, apkLite, null, null, null, null, null, null);
    }

    static PackageLite parseClusterPackageLite(File file, int i) throws PackageParserException {
        String[] strArr;
        boolean[] zArr;
        String[] strArr2;
        String[] strArr3;
        String[] strArr4;
        int[] iArr;
        File[] fileArrListFiles = file.listFiles();
        if (ArrayUtils.isEmpty(fileArrListFiles)) {
            throw new PackageParserException(-100, "No packages found in split");
        }
        if (fileArrListFiles.length == 1 && fileArrListFiles[0].isDirectory()) {
            return parseClusterPackageLite(fileArrListFiles[0], i);
        }
        Trace.traceBegin(262144L, "parseApkLite");
        ArrayMap arrayMap = new ArrayMap();
        int i2 = 0;
        String str = null;
        for (File file2 : fileArrListFiles) {
            if (isApkFile(file2)) {
                ApkLite apkLite = parseApkLite(file2, i);
                if (str == null) {
                    str = apkLite.packageName;
                    i2 = apkLite.versionCode;
                } else {
                    if (!str.equals(apkLite.packageName)) {
                        throw new PackageParserException(-101, "Inconsistent package " + apkLite.packageName + " in " + file2 + "; expected " + str);
                    }
                    if (i2 != apkLite.versionCode) {
                        throw new PackageParserException(-101, "Inconsistent version " + apkLite.versionCode + " in " + file2 + "; expected " + i2);
                    }
                }
                if (arrayMap.put(apkLite.splitName, apkLite) != null) {
                    throw new PackageParserException(-101, "Split name " + apkLite.splitName + " defined more than once; most recent was " + file2);
                }
            }
        }
        Trace.traceEnd(262144L);
        ApkLite apkLite2 = (ApkLite) arrayMap.remove(null);
        if (apkLite2 == null) {
            throw new PackageParserException(-101, "Missing base APK in " + file);
        }
        int size = arrayMap.size();
        if (size > 0) {
            boolean[] zArr2 = new boolean[size];
            String[] strArr5 = new String[size];
            String[] strArr6 = new String[size];
            String[] strArr7 = new String[size];
            int[] iArr2 = new int[size];
            String[] strArr8 = (String[]) arrayMap.keySet().toArray(new String[size]);
            Arrays.sort(strArr8, sSplitNameComparator);
            for (int i3 = 0; i3 < size; i3++) {
                ApkLite apkLite3 = (ApkLite) arrayMap.get(strArr8[i3]);
                strArr5[i3] = apkLite3.usesSplitName;
                zArr2[i3] = apkLite3.isFeatureSplit;
                strArr6[i3] = apkLite3.configForSplit;
                strArr7[i3] = apkLite3.codePath;
                iArr2[i3] = apkLite3.revisionCode;
            }
            strArr = strArr8;
            strArr2 = strArr5;
            strArr3 = strArr6;
            strArr4 = strArr7;
            iArr = iArr2;
            zArr = zArr2;
        } else {
            strArr = null;
            zArr = null;
            strArr2 = null;
            strArr3 = null;
            strArr4 = null;
            iArr = null;
        }
        return new PackageLite(file.getAbsolutePath(), apkLite2, strArr, zArr, strArr2, strArr3, strArr4, iArr);
    }

    public Package parsePackage(File file, int i, boolean z) throws PackageParserException {
        if (file.isDirectory()) {
            return parseClusterPackage(file, i);
        }
        return parseMonolithicPackage(file, i);
    }

    public Package parsePackage(File file, int i) throws PackageParserException {
        return parsePackage(file, i, false);
    }

    private Package parseClusterPackage(File file, int i) throws PackageParserException {
        SplitAssetLoader defaultSplitAssetLoader;
        PackageLite clusterPackageLite = parseClusterPackageLite(file, 0);
        if (this.mOnlyCoreApps && !clusterPackageLite.coreApp) {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "Not a coreApp: " + file);
        }
        SparseArray<int[]> sparseArrayCreateDependenciesFromPackage = null;
        if (clusterPackageLite.isolatedSplits && !ArrayUtils.isEmpty(clusterPackageLite.splitNames)) {
            try {
                sparseArrayCreateDependenciesFromPackage = SplitAssetDependencyLoader.createDependenciesFromPackage(clusterPackageLite);
                defaultSplitAssetLoader = new SplitAssetDependencyLoader(clusterPackageLite, sparseArrayCreateDependenciesFromPackage, i);
            } catch (SplitDependencyLoader.IllegalDependencyException e) {
                throw new PackageParserException(-101, e.getMessage());
            }
        } else {
            defaultSplitAssetLoader = new DefaultSplitAssetLoader(clusterPackageLite, i);
        }
        try {
            AssetManager baseAssetManager = defaultSplitAssetLoader.getBaseAssetManager();
            File file2 = new File(clusterPackageLite.baseCodePath);
            Package baseApk = parseBaseApk(file2, baseAssetManager, i);
            if (baseApk == null) {
                throw new PackageParserException(-100, "Failed to parse base APK: " + file2);
            }
            if (!ArrayUtils.isEmpty(clusterPackageLite.splitNames)) {
                int length = clusterPackageLite.splitNames.length;
                baseApk.splitNames = clusterPackageLite.splitNames;
                baseApk.splitCodePaths = clusterPackageLite.splitCodePaths;
                baseApk.splitRevisionCodes = clusterPackageLite.splitRevisionCodes;
                baseApk.splitFlags = new int[length];
                baseApk.splitPrivateFlags = new int[length];
                baseApk.applicationInfo.splitNames = baseApk.splitNames;
                baseApk.applicationInfo.splitDependencies = sparseArrayCreateDependenciesFromPackage;
                baseApk.applicationInfo.splitClassLoaderNames = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    parseSplitApk(baseApk, i2, defaultSplitAssetLoader.getSplitAssetManager(i2), i);
                }
            }
            baseApk.setCodePath(clusterPackageLite.codePath);
            baseApk.setUse32bitAbi(clusterPackageLite.use32bitAbi);
            return baseApk;
        } finally {
            IoUtils.closeQuietly(defaultSplitAssetLoader);
        }
    }

    public Package parseMonolithicPackage(File file, int i) throws PackageParserException {
        PackageLite monolithicPackageLite = parseMonolithicPackageLite(file, i);
        if (this.mOnlyCoreApps && !monolithicPackageLite.coreApp) {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "Not a coreApp: " + file);
        }
        DefaultSplitAssetLoader defaultSplitAssetLoader = new DefaultSplitAssetLoader(monolithicPackageLite, i);
        try {
            try {
                Package baseApk = parseBaseApk(file, defaultSplitAssetLoader.getBaseAssetManager(), i);
                baseApk.setCodePath(file.getCanonicalPath());
                baseApk.setUse32bitAbi(monolithicPackageLite.use32bitAbi);
                return baseApk;
            } catch (IOException e) {
                throw new PackageParserException(-102, "Failed to get path: " + file, e);
            }
        } finally {
            IoUtils.closeQuietly(defaultSplitAssetLoader);
        }
    }

    private Package parseBaseApk(File file, AssetManager assetManager, int i) throws Throwable {
        String absolutePath = file.getAbsolutePath();
        XmlResourceParser xmlResourceParser = null;
        String strSubstring = absolutePath.startsWith(MNT_EXPAND) ? absolutePath.substring(12, absolutePath.indexOf(47, 12)) : null;
        this.mParseError = 1;
        this.mArchiveSourcePath = file.getAbsolutePath();
        try {
            try {
                int iFindCookieForPath = assetManager.findCookieForPath(absolutePath);
                if (iFindCookieForPath == 0) {
                    throw new PackageParserException(-101, "Failed adding asset path: " + absolutePath);
                }
                XmlResourceParser xmlResourceParserOpenXmlResourceParser = assetManager.openXmlResourceParser(iFindCookieForPath, ANDROID_MANIFEST_FILENAME);
                try {
                    Resources resources = new Resources(assetManager, this.mMetrics, null);
                    String[] strArr = new String[1];
                    Package baseApk = parseBaseApk(absolutePath, resources, xmlResourceParserOpenXmlResourceParser, i, strArr);
                    if (baseApk == null) {
                        throw new PackageParserException(this.mParseError, absolutePath + " (at " + xmlResourceParserOpenXmlResourceParser.getPositionDescription() + "): " + strArr[0]);
                    }
                    baseApk.setVolumeUuid(strSubstring);
                    baseApk.setApplicationVolumeUuid(strSubstring);
                    baseApk.setBaseCodePath(absolutePath);
                    baseApk.setSigningDetails(SigningDetails.UNKNOWN);
                    IoUtils.closeQuietly(xmlResourceParserOpenXmlResourceParser);
                    return baseApk;
                } catch (PackageParserException e) {
                    throw e;
                } catch (Exception e2) {
                    e = e2;
                    throw new PackageParserException(-102, "Failed to read manifest from " + absolutePath, e);
                } catch (Throwable th) {
                    th = th;
                    xmlResourceParser = xmlResourceParserOpenXmlResourceParser;
                    IoUtils.closeQuietly(xmlResourceParser);
                    throw th;
                }
            } catch (PackageParserException e3) {
                throw e3;
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void parseSplitApk(Package r12, int i, AssetManager assetManager, int i2) throws Throwable {
        String str = r12.splitCodePaths[i];
        this.mParseError = 1;
        this.mArchiveSourcePath = str;
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                int iFindCookieForPath = assetManager.findCookieForPath(str);
                if (iFindCookieForPath == 0) {
                    throw new PackageParserException(-101, "Failed adding asset path: " + str);
                }
                XmlResourceParser xmlResourceParserOpenXmlResourceParser = assetManager.openXmlResourceParser(iFindCookieForPath, ANDROID_MANIFEST_FILENAME);
                try {
                    String[] strArr = new String[1];
                    if (parseSplitApk(r12, new Resources(assetManager, this.mMetrics, null), xmlResourceParserOpenXmlResourceParser, i2, i, strArr) == null) {
                        throw new PackageParserException(this.mParseError, str + " (at " + xmlResourceParserOpenXmlResourceParser.getPositionDescription() + "): " + strArr[0]);
                    }
                    IoUtils.closeQuietly(xmlResourceParserOpenXmlResourceParser);
                } catch (PackageParserException e) {
                } catch (Exception e2) {
                    e = e2;
                    throw new PackageParserException(-102, "Failed to read manifest from " + str, e);
                } catch (Throwable th) {
                    th = th;
                    xmlResourceParser = xmlResourceParserOpenXmlResourceParser;
                    IoUtils.closeQuietly(xmlResourceParser);
                    throw th;
                }
            } catch (PackageParserException e3) {
                throw e3;
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007c, code lost:
    
        if (r4 != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007e, code lost:
    
        r14[0] = "<manifest> does not contain an <application>";
        r8.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_EMPTY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0086, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.content.pm.PackageParser.Package parseSplitApk(android.content.pm.PackageParser.Package r9, android.content.res.Resources r10, android.content.res.XmlResourceParser r11, int r12, int r13, java.lang.String[] r14) throws org.xmlpull.v1.XmlPullParserException, android.content.pm.PackageParser.PackageParserException, java.io.IOException {
        /*
            r8 = this;
            parsePackageSplitNames(r11, r11)
            r0 = 0
            r8.mParseInstrumentationArgs = r0
            int r1 = r11.getDepth()
            r2 = 1
            r3 = 0
            r4 = r3
        Ld:
            int r5 = r11.next()
            if (r5 == r2) goto L7c
            r6 = 3
            if (r5 != r6) goto L1c
            int r7 = r11.getDepth()
            if (r7 <= r1) goto L7c
        L1c:
            if (r5 == r6) goto Ld
            r6 = 4
            if (r5 != r6) goto L22
            goto Ld
        L22:
            java.lang.String r5 = r11.getName()
            java.lang.String r6 = "application"
            boolean r5 = r5.equals(r6)
            java.lang.String r6 = "PackageParser"
            if (r5 == 0) goto L44
            if (r4 == 0) goto L3b
            java.lang.String r5 = "<manifest> has more than one <application>"
            android.util.Slog.w(r6, r5)
            com.android.internal.util.XmlUtils.skipCurrentTag(r11)
            goto Ld
        L3b:
            boolean r4 = r8.parseSplitApplication(r9, r10, r11, r12, r13, r14)
            if (r4 != 0) goto L42
            return r0
        L42:
            r4 = r2
            goto Ld
        L44:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "Unknown element under <manifest>: "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = r11.getName()
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = " at "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = r8.mArchiveSourcePath
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = " "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = r11.getPositionDescription()
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r5 = r5.toString()
            android.util.Slog.w(r6, r5)
            com.android.internal.util.XmlUtils.skipCurrentTag(r11)
            goto Ld
        L7c:
            if (r4 != 0) goto L86
            java.lang.String r10 = "<manifest> does not contain an <application>"
            r14[r3] = r10
            r10 = -109(0xffffffffffffff93, float:NaN)
            r8.mParseError = r10
        L86:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseSplitApk(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, int, int, java.lang.String[]):android.content.pm.PackageParser$Package");
    }

    public static ArraySet<PublicKey> toSigningKeys(Signature[] signatureArr) throws CertificateException {
        ArraySet<PublicKey> arraySet = new ArraySet<>(signatureArr.length);
        for (Signature signature : signatureArr) {
            arraySet.add(signature.getPublicKey());
        }
        return arraySet;
    }

    public static void collectCertificates(Package r3, boolean z) throws PackageParserException {
        collectCertificatesInternal(r3, z);
        int size = r3.childPackages != null ? r3.childPackages.size() : 0;
        for (int i = 0; i < size; i++) {
            r3.childPackages.get(i).mSigningDetails = r3.mSigningDetails;
        }
    }

    private static void collectCertificatesInternal(Package r5, boolean z) throws PackageParserException {
        r5.mSigningDetails = SigningDetails.UNKNOWN;
        Trace.traceBegin(262144L, "collectCertificates");
        try {
            collectCertificates(r5, new File(r5.baseCodePath), z);
            if (!ArrayUtils.isEmpty(r5.splitCodePaths)) {
                for (int i = 0; i < r5.splitCodePaths.length; i++) {
                    collectCertificates(r5, new File(r5.splitCodePaths[i]), z);
                }
            }
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    private static void collectCertificates(Package r2, File file, boolean z) throws PackageParserException {
        SigningDetails signingDetailsVerify;
        String absolutePath = file.getAbsolutePath();
        int minimumSignatureSchemeVersionForTargetSdk = ApkSignatureVerifier.getMinimumSignatureSchemeVersionForTargetSdk(r2.applicationInfo.targetSdkVersion);
        if (r2.applicationInfo.isStaticSharedLibrary()) {
            minimumSignatureSchemeVersionForTargetSdk = 2;
        }
        if (z) {
            signingDetailsVerify = ApkSignatureVerifier.unsafeGetCertsWithoutVerification(absolutePath, 1);
        } else {
            signingDetailsVerify = ApkSignatureVerifier.verify(absolutePath, minimumSignatureSchemeVersionForTargetSdk);
        }
        if (r2.mSigningDetails == SigningDetails.UNKNOWN) {
            r2.mSigningDetails = signingDetailsVerify;
        } else if (!Signature.areExactMatch(r2.mSigningDetails.signatures, signingDetailsVerify.signatures)) {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES, absolutePath + " has mismatched certificates");
        }
    }

    private static AssetManager newConfiguredAssetManager() {
        AssetManager assetManager = new AssetManager();
        assetManager.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Build.VERSION.RESOURCES_SDK_INT);
        return assetManager;
    }

    public static ApkLite parseApkLite(File file, int i) throws PackageParserException {
        return parseApkLiteInner(file, null, null, i);
    }

    public static ApkLite parseApkLite(FileDescriptor fileDescriptor, String str, int i) throws PackageParserException {
        return parseApkLiteInner(null, fileDescriptor, str, i);
    }

    private static ApkLite parseApkLiteInner(File file, FileDescriptor fileDescriptor, String str, int i) throws Throwable {
        ApkAssets apkAssetsLoadFromPath;
        XmlResourceParser xmlResourceParserOpenXml;
        SigningDetails signingDetails;
        String absolutePath = fileDescriptor != null ? str : file.getAbsolutePath();
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                try {
                    if (fileDescriptor != null) {
                        apkAssetsLoadFromPath = ApkAssets.loadFromFd(fileDescriptor, str, 0, null);
                    } else {
                        apkAssetsLoadFromPath = ApkAssets.loadFromPath(absolutePath);
                    }
                    try {
                        try {
                            xmlResourceParserOpenXml = apkAssetsLoadFromPath.openXml(ANDROID_MANIFEST_FILENAME);
                        } catch (IOException | RuntimeException | XmlPullParserException e) {
                            e = e;
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (IOException unused) {
                    throw new PackageParserException(-100, "Failed to parse " + absolutePath);
                }
            } catch (Throwable th2) {
                th = th2;
                apkAssetsLoadFromPath = null;
            }
        } catch (IOException | RuntimeException | XmlPullParserException e2) {
            e = e2;
            apkAssetsLoadFromPath = null;
        }
        try {
            try {
                if ((i & 32) != 0) {
                    Package r4 = new Package((String) null);
                    boolean z = (i & 16) != 0;
                    Trace.traceBegin(262144L, "collectCertificates");
                    try {
                        collectCertificates(r4, file, z);
                        Trace.traceEnd(262144L);
                        signingDetails = r4.mSigningDetails;
                    } catch (Throwable th3) {
                        Trace.traceEnd(262144L);
                        throw th3;
                    }
                } else {
                    signingDetails = SigningDetails.UNKNOWN;
                }
                ApkLite apkLite = parseApkLite(absolutePath, xmlResourceParserOpenXml, xmlResourceParserOpenXml, signingDetails);
                IoUtils.closeQuietly(xmlResourceParserOpenXml);
                if (apkAssetsLoadFromPath != null) {
                    try {
                        apkAssetsLoadFromPath.close();
                    } catch (Throwable unused2) {
                    }
                }
                return apkLite;
            } catch (Throwable th4) {
                th = th4;
                xmlResourceParser = xmlResourceParserOpenXml;
                IoUtils.closeQuietly(xmlResourceParser);
                if (apkAssetsLoadFromPath != null) {
                    try {
                        apkAssetsLoadFromPath.close();
                    } catch (Throwable unused3) {
                    }
                }
                throw th;
            }
        } catch (IOException | RuntimeException | XmlPullParserException e3) {
            e = e3;
            xmlResourceParser = xmlResourceParserOpenXml;
            Slog.w(TAG, "Failed to parse " + absolutePath, e);
            throw new PackageParserException(-102, "Failed to parse " + absolutePath, e);
        }
    }

    public static String validateName(String str, boolean z, boolean z2) {
        int length = str.length();
        boolean z3 = false;
        boolean z4 = true;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z')) {
                z4 = false;
            } else if (z4 || ((cCharAt < '0' || cCharAt > '9') && cCharAt != '_')) {
                if (cCharAt != '.') {
                    return "bad character '" + cCharAt + "'";
                }
                z3 = true;
                z4 = true;
            }
        }
        if (z2 && !FileUtils.isValidExtFilename(str)) {
            return "Invalid filename";
        }
        if (z3 || !z) {
            return null;
        }
        return "must have at least one '.' separator";
    }

    @Deprecated
    public static Pair<String, String> parsePackageSplitNames(XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, PackageParserException, IOException {
        int next;
        String strValidateName;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "No start tag found");
        }
        if (!xmlPullParser.getName().equals(TAG_MANIFEST)) {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "No <manifest> tag");
        }
        String strIntern = null;
        String attributeValue = attributeSet.getAttributeValue(null, "package");
        if (!"android".equals(attributeValue) && (strValidateName = validateName(attributeValue, true, true)) != null) {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Invalid manifest package: " + strValidateName);
        }
        String attributeValue2 = attributeSet.getAttributeValue(null, "split");
        if (attributeValue2 == null) {
            strIntern = attributeValue2;
        } else if (attributeValue2.length() != 0) {
            String strValidateName2 = validateName(attributeValue2, false, false);
            if (strValidateName2 != null) {
                throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Invalid manifest split: " + strValidateName2);
            }
            strIntern = attributeValue2;
        }
        String strIntern2 = attributeValue.intern();
        if (strIntern != null) {
            strIntern = strIntern.intern();
        }
        return Pair.create(strIntern2, strIntern);
    }

    /* JADX WARN: Code restructure failed: missing block: B:133:0x0276, code lost:
    
        if (checkRequiredSystemProperties(r9, r3) != false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0278, code lost:
    
        android.util.Slog.i(android.content.pm.PackageParser.TAG, "Skipping target and overlay pair " + r6 + " and " + r34 + ": overlay ignored due to required system property: " + r9 + " with value: " + r3);
        r28 = null;
        r29 = false;
        r30 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02b5, code lost:
    
        r28 = r6;
        r30 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02d4, code lost:
    
        return new android.content.pm.PackageParser.ApkLite(r34, r1.first, r1.second, r10, r11, r18, r13, r14, r15, r16, r17, r12, r37, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.PackageParser.ApkLite parseApkLite(java.lang.String r34, org.xmlpull.v1.XmlPullParser r35, android.util.AttributeSet r36, android.content.pm.PackageParser.SigningDetails r37) throws org.xmlpull.v1.XmlPullParserException, android.content.pm.PackageParser.PackageParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 725
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseApkLite(java.lang.String, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.pm.PackageParser$SigningDetails):android.content.pm.PackageParser$ApkLite");
    }

    private boolean parseBaseApkChild(Package r10, Resources resources, XmlResourceParser xmlResourceParser, int i, String[] strArr) throws XmlPullParserException, IOException {
        String attributeValue = xmlResourceParser.getAttributeValue(null, "package");
        if (validateName(attributeValue, true, false) != null) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME;
            return false;
        }
        if (attributeValue.equals(r10.packageName)) {
            String str = "Child package name cannot be equal to parent package name: " + r10.packageName;
            Slog.w(TAG, str);
            strArr[0] = str;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        if (r10.hasChildPackage(attributeValue)) {
            String str2 = "Duplicate child package:" + attributeValue;
            Slog.w(TAG, str2);
            strArr[0] = str2;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        Package r2 = new Package(attributeValue);
        r2.mVersionCode = r10.mVersionCode;
        r2.baseRevisionCode = r10.baseRevisionCode;
        r2.mVersionName = r10.mVersionName;
        r2.applicationInfo.targetSdkVersion = r10.applicationInfo.targetSdkVersion;
        r2.applicationInfo.minSdkVersion = r10.applicationInfo.minSdkVersion;
        Package baseApkCommon = parseBaseApkCommon(r2, CHILD_PACKAGE_TAGS, resources, xmlResourceParser, i, strArr);
        if (baseApkCommon == null) {
            return false;
        }
        if (r10.childPackages == null) {
            r10.childPackages = new ArrayList<>();
        }
        r10.childPackages.add(baseApkCommon);
        baseApkCommon.parentPackage = r10;
        return true;
    }

    private Package parseBaseApk(String str, Resources resources, XmlResourceParser xmlResourceParser, int i, String[] strArr) throws XmlPullParserException, IOException {
        try {
            Pair<String, String> packageSplitNames = parsePackageSplitNames(xmlResourceParser, xmlResourceParser);
            String str2 = packageSplitNames.first;
            String str3 = packageSplitNames.second;
            if (!TextUtils.isEmpty(str3)) {
                strArr[0] = "Expected base APK, but found split " + str3;
                this.mParseError = PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME;
                return null;
            }
            Package r1 = new Package(str2);
            TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifest);
            r1.mVersionCode = typedArrayObtainAttributes.getInteger(1, 0);
            r1.mVersionCodeMajor = typedArrayObtainAttributes.getInteger(11, 0);
            r1.applicationInfo.setVersionCode(r1.getLongVersionCode());
            r1.baseRevisionCode = typedArrayObtainAttributes.getInteger(5, 0);
            r1.mVersionName = typedArrayObtainAttributes.getNonConfigurationString(2, 0);
            if (r1.mVersionName != null) {
                r1.mVersionName = r1.mVersionName.intern();
            }
            r1.coreApp = xmlResourceParser.getAttributeBooleanValue(null, "coreApp", false);
            if (typedArrayObtainAttributes.getBoolean(6, false)) {
                r1.applicationInfo.privateFlags |= 32768;
            }
            r1.mCompileSdkVersion = typedArrayObtainAttributes.getInteger(9, 0);
            r1.applicationInfo.compileSdkVersion = r1.mCompileSdkVersion;
            r1.mCompileSdkVersionCodename = typedArrayObtainAttributes.getNonConfigurationString(10, 0);
            if (r1.mCompileSdkVersionCodename != null) {
                r1.mCompileSdkVersionCodename = r1.mCompileSdkVersionCodename.intern();
            }
            r1.applicationInfo.compileSdkVersionCodename = r1.mCompileSdkVersionCodename;
            typedArrayObtainAttributes.recycle();
            return parseBaseApkCommon(r1, null, resources, xmlResourceParser, i, strArr);
        } catch (PackageParserException unused) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME;
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:255:0x0657, code lost:
    
        r20 = r1;
        r14 = r2;
        r22 = r3;
        r1 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x065d, code lost:
    
        if (r16 != false) goto L260;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0665, code lost:
    
        if (r28.instrumentation.size() != 0) goto L260;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x0667, code lost:
    
        r3 = 0;
        r33[0] = "<manifest> does not contain an <application> or <instrumentation>";
        r27.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_EMPTY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x0671, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x0672, code lost:
    
        r2 = android.content.pm.PackageParser.NEW_PERMISSIONS.length;
        r12 = r1;
        r1 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x0677, code lost:
    
        if (r1 >= r2) goto L346;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x0679, code lost:
    
        r4 = android.content.pm.PackageParser.NEW_PERMISSIONS[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x0683, code lost:
    
        if (r28.applicationInfo.targetSdkVersion < r4.sdkVersion) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x068e, code lost:
    
        if (r28.requestedPermissions.contains(r4.name) != false) goto L349;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x0690, code lost:
    
        if (r12 != null) goto L270;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x0692, code lost:
    
        r12 = new java.lang.StringBuilder(128);
        r12.append(r28.packageName);
        r12.append(": compat added ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x06a4, code lost:
    
        r12.append(' ');
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x06a9, code lost:
    
        r12.append(r4.name);
        r28.requestedPermissions.add(r4.name);
        r28.implicitPermissions.add(r4.name);
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x06bc, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x06bf, code lost:
    
        if (r12 == null) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x06c1, code lost:
    
        android.util.Slog.i(android.content.pm.PackageParser.TAG, r12.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x06c8, code lost:
    
        r1 = android.app.ActivityThread.getPermissionManager().getSplitPermissions();
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x06d0, code lost:
    
        r2 = r1.size();
        r4 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x06d5, code lost:
    
        if (r4 >= r2) goto L350;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x06d7, code lost:
    
        r5 = r1.get(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x06e5, code lost:
    
        if (r28.applicationInfo.targetSdkVersion >= r5.getTargetSdk()) goto L351;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x06f1, code lost:
    
        if (r28.requestedPermissions.contains(r5.getSplitPermission()) != false) goto L283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:283:0x06f4, code lost:
    
        r5 = r5.getNewPermissions();
        r6 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x06fd, code lost:
    
        if (r6 >= r5.size()) goto L352;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x06ff, code lost:
    
        r8 = r5.get(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x070b, code lost:
    
        if (r28.requestedPermissions.contains(r8) != false) goto L355;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x070d, code lost:
    
        r28.requestedPermissions.add(r8);
        r28.implicitPermissions.add(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x0717, code lost:
    
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x071a, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x071d, code lost:
    
        if (r22 < 0) goto L295;
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x071f, code lost:
    
        if (r22 <= 0) goto L296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x0726, code lost:
    
        if (r28.applicationInfo.targetSdkVersion < 4) goto L296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x0728, code lost:
    
        r28.applicationInfo.flags |= 512;
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x0730, code lost:
    
        if (r14 == 0) goto L298;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x0732, code lost:
    
        r28.applicationInfo.flags |= 1024;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x073a, code lost:
    
        if (r20 < 0) goto L302;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x073c, code lost:
    
        if (r20 <= 0) goto L303;
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x0743, code lost:
    
        if (r28.applicationInfo.targetSdkVersion < 4) goto L303;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x0745, code lost:
    
        r28.applicationInfo.flags |= 2048;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x074d, code lost:
    
        if (r17 < 0) goto L307;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x074f, code lost:
    
        if (r17 <= 0) goto L308;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x0757, code lost:
    
        if (r28.applicationInfo.targetSdkVersion < 9) goto L308;
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x0759, code lost:
    
        r28.applicationInfo.flags |= 524288;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0762, code lost:
    
        if (r18 < 0) goto L312;
     */
    /* JADX WARN: Code restructure failed: missing block: B:309:0x0764, code lost:
    
        if (r18 <= 0) goto L313;
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x076b, code lost:
    
        if (r28.applicationInfo.targetSdkVersion < 4) goto L313;
     */
    /* JADX WARN: Code restructure failed: missing block: B:312:0x076d, code lost:
    
        r28.applicationInfo.flags |= 4096;
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x0775, code lost:
    
        if (r19 < 0) goto L317;
     */
    /* JADX WARN: Code restructure failed: missing block: B:314:0x0777, code lost:
    
        if (r19 <= 0) goto L318;
     */
    /* JADX WARN: Code restructure failed: missing block: B:316:0x077e, code lost:
    
        if (r28.applicationInfo.targetSdkVersion < 4) goto L318;
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x0780, code lost:
    
        r28.applicationInfo.flags |= 8192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:319:0x078e, code lost:
    
        if (r28.applicationInfo.usesCompatibilityMode() == false) goto L321;
     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x0790, code lost:
    
        adjustPackageToBeUnresizeableAndUnpipable(r28);
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x0793, code lost:
    
        return r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:322:0x0794, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x0799, code lost:
    
        throw r0.rethrowFromSystemServer();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01e8, code lost:
    
        r33[0] = "<overlay> priority must be between 0 and 9999";
        r27.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01f0, code lost:
    
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:150:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x03f8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.content.pm.PackageParser.Package parseBaseApkCommon(android.content.pm.PackageParser.Package r28, java.util.Set<java.lang.String> r29, android.content.res.Resources r30, android.content.res.XmlResourceParser r31, int r32, java.lang.String[] r33) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1946
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseBaseApkCommon(android.content.pm.PackageParser$Package, java.util.Set, android.content.res.Resources, android.content.res.XmlResourceParser, int, java.lang.String[]):android.content.pm.PackageParser$Package");
    }

    public static boolean checkRequiredSystemProperties(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                return true;
            }
            Slog.w(TAG, "Disabling overlay - incomplete property :'" + str + "=" + str2 + "' - require both requiredSystemPropertyName AND requiredSystemPropertyValue to be specified.");
            return false;
        }
        String[] strArrSplit = str.split(SmsManager.REGEX_PREFIX_DELIMITER);
        String[] strArrSplit2 = str2.split(SmsManager.REGEX_PREFIX_DELIMITER);
        if (strArrSplit.length != strArrSplit2.length) {
            Slog.w(TAG, "Disabling overlay - property :'" + str + "=" + str2 + "' - require both requiredSystemPropertyName AND requiredSystemPropertyValue lists to have the same size.");
            return false;
        }
        for (int i = 0; i < strArrSplit.length; i++) {
            if (!TextUtils.equals(SystemProperties.get(strArrSplit[i]), strArrSplit2[i])) {
                return false;
            }
        }
        return true;
    }

    private void adjustPackageToBeUnresizeableAndUnpipable(Package r3) {
        for (Activity activity : r3.activities) {
            activity.info.resizeMode = 0;
            activity.info.flags &= -4194305;
        }
    }

    private static boolean matchTargetCode(String[] strArr, String str) {
        int iIndexOf = str.indexOf(46);
        if (iIndexOf != -1) {
            str = str.substring(0, iIndexOf);
        }
        return ArrayUtils.contains(strArr, str);
    }

    public static int computeTargetSdkVersion(int i, String str, String[] strArr, String[] strArr2) {
        if (str == null) {
            return i;
        }
        if (matchTargetCode(strArr, str)) {
            return 10000;
        }
        if (strArr.length > 0) {
            strArr2[0] = "Requires development platform " + str + " (current platform is any of " + Arrays.toString(strArr) + ")";
            return -1;
        }
        strArr2[0] = "Requires development platform " + str + " but this is a release platform.";
        return -1;
    }

    public static int computeMinSdkVersion(int i, String str, int i2, String[] strArr, String[] strArr2) {
        if (str == null) {
            if (i <= i2) {
                return i;
            }
            strArr2[0] = "Requires newer sdk version #" + i + " (current version is #" + i2 + ")";
            return -1;
        }
        if (matchTargetCode(strArr, str)) {
            return 10000;
        }
        if (strArr.length > 0) {
            strArr2[0] = "Requires development platform " + str + " (current platform is any of " + Arrays.toString(strArr) + ")";
        } else {
            strArr2[0] = "Requires development platform " + str + " but this is a release platform.";
        }
        return -1;
    }

    private FeatureInfo parseUsesFeature(Resources resources, AttributeSet attributeSet) {
        FeatureInfo featureInfo = new FeatureInfo();
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.AndroidManifestUsesFeature);
        featureInfo.name = typedArrayObtainAttributes.getNonResourceString(0);
        featureInfo.version = typedArrayObtainAttributes.getInt(3, 0);
        if (featureInfo.name == null) {
            featureInfo.reqGlEsVersion = typedArrayObtainAttributes.getInt(1, 0);
        }
        if (typedArrayObtainAttributes.getBoolean(2, true)) {
            featureInfo.flags |= 1;
        }
        typedArrayObtainAttributes.recycle();
        return featureInfo;
    }

    private boolean parseUsesStaticLibrary(Package r9, Resources resources, XmlResourceParser xmlResourceParser, String[] strArr) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestUsesStaticLibrary);
        String nonResourceString = typedArrayObtainAttributes.getNonResourceString(0);
        int i = typedArrayObtainAttributes.getInt(1, -1);
        String nonResourceString2 = typedArrayObtainAttributes.getNonResourceString(2);
        typedArrayObtainAttributes.recycle();
        if (nonResourceString == null || i < 0 || nonResourceString2 == null) {
            strArr[0] = "Bad uses-static-library declaration name: " + nonResourceString + " version: " + i + " certDigest" + nonResourceString2;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            XmlUtils.skipCurrentTag(xmlResourceParser);
            return false;
        }
        if (r9.usesStaticLibraries != null && r9.usesStaticLibraries.contains(nonResourceString)) {
            strArr[0] = "Depending on multiple versions of static library " + nonResourceString;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            XmlUtils.skipCurrentTag(xmlResourceParser);
            return false;
        }
        String strIntern = nonResourceString.intern();
        String lowerCase = nonResourceString2.replace(SettingsStringUtil.DELIMITER, "").toLowerCase();
        String[] additionalCertificates = EmptyArray.STRING;
        if (r9.applicationInfo.targetSdkVersion >= 27) {
            additionalCertificates = parseAdditionalCertificates(resources, xmlResourceParser, strArr);
            if (additionalCertificates == null) {
                return false;
            }
        } else {
            XmlUtils.skipCurrentTag(xmlResourceParser);
        }
        String[] strArr2 = new String[additionalCertificates.length + 1];
        strArr2[0] = lowerCase;
        System.arraycopy(additionalCertificates, 0, strArr2, 1, additionalCertificates.length);
        r9.usesStaticLibraries = ArrayUtils.add(r9.usesStaticLibraries, strIntern);
        r9.usesStaticLibrariesVersions = ArrayUtils.appendLong(r9.usesStaticLibrariesVersions, i, true);
        r9.usesStaticLibrariesCertDigests = (String[][]) ArrayUtils.appendElement(String[].class, r9.usesStaticLibrariesCertDigests, strArr2, true);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0076, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String[] parseAdditionalCertificates(android.content.res.Resources r7, android.content.res.XmlResourceParser r8, java.lang.String[] r9) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r6 = this;
            java.lang.String[] r0 = libcore.util.EmptyArray.STRING
            int r1 = r8.getDepth()
        L6:
            int r2 = r8.next()
            r3 = 1
            if (r2 == r3) goto L76
            r3 = 3
            if (r2 != r3) goto L16
            int r4 = r8.getDepth()
            if (r4 <= r1) goto L76
        L16:
            if (r2 == r3) goto L6
            r3 = 4
            if (r2 != r3) goto L1c
            goto L6
        L1c:
            java.lang.String r2 = r8.getName()
            java.lang.String r3 = "additional-certificate"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L72
            int[] r2 = com.android.internal.R.styleable.AndroidManifestAdditionalCertificate
            android.content.res.TypedArray r2 = r7.obtainAttributes(r8, r2)
            r3 = 0
            java.lang.String r4 = r2.getNonResourceString(r3)
            r2.recycle()
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L5d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "Bad additional-certificate declaration with empty certDigest:"
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.StringBuilder r7 = r7.append(r4)
            java.lang.String r7 = r7.toString()
            r9[r3] = r7
            r7 = -108(0xffffffffffffff94, float:NaN)
            r6.mParseError = r7
            com.android.internal.util.XmlUtils.skipCurrentTag(r8)
            r2.recycle()
            r6 = 0
            return r6
        L5d:
            java.lang.String r2 = ":"
            java.lang.String r3 = ""
            java.lang.String r2 = r4.replace(r2, r3)
            java.lang.String r2 = r2.toLowerCase()
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            java.lang.Object[] r0 = com.android.internal.util.ArrayUtils.appendElement(r3, r0, r2)
            java.lang.String[] r0 = (java.lang.String[]) r0
            goto L6
        L72:
            com.android.internal.util.XmlUtils.skipCurrentTag(r8)
            goto L6
        L76:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseAdditionalCertificates(android.content.res.Resources, android.content.res.XmlResourceParser, java.lang.String[]):java.lang.String[]");
    }

    private boolean parseUsesPermission(Package r7, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        Callback callback;
        Callback callback2;
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestUsesPermission);
        String nonResourceString = typedArrayObtainAttributes.getNonResourceString(0);
        TypedValue typedValuePeekValue = typedArrayObtainAttributes.peekValue(1);
        int i = (typedValuePeekValue == null || typedValuePeekValue.type < 16 || typedValuePeekValue.type > 31) ? 0 : typedValuePeekValue.data;
        String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(2, 0);
        String nonConfigurationString2 = typedArrayObtainAttributes.getNonConfigurationString(3, 0);
        typedArrayObtainAttributes.recycle();
        XmlUtils.skipCurrentTag(xmlResourceParser);
        if (nonResourceString == null) {
            return true;
        }
        if (i != 0 && i < Build.VERSION.RESOURCES_SDK_INT) {
            return true;
        }
        if (nonConfigurationString != null && (callback2 = this.mCallback) != null && !callback2.hasFeature(nonConfigurationString)) {
            return true;
        }
        if (nonConfigurationString2 != null && (callback = this.mCallback) != null && callback.hasFeature(nonConfigurationString2)) {
            return true;
        }
        if (r7.requestedPermissions.indexOf(nonResourceString) == -1) {
            r7.requestedPermissions.add(nonResourceString.intern());
        } else {
            Slog.w(TAG, "Ignoring duplicate uses-permissions/uses-permissions-sdk-m: " + nonResourceString + " in package: " + r7.packageName + " at: " + xmlResourceParser.getPositionDescription());
        }
        return true;
    }

    public static String buildClassName(String str, CharSequence charSequence, String[] strArr) {
        if (charSequence == null || charSequence.length() <= 0) {
            strArr[0] = "Empty class name in package " + str;
            return null;
        }
        String string = charSequence.toString();
        if (string.charAt(0) == '.') {
            return str + string;
        }
        if (string.indexOf(46) >= 0) {
            return string;
        }
        return str + '.' + string;
    }

    private static String buildCompoundName(String str, CharSequence charSequence, String str2, String[] strArr) {
        String string = charSequence.toString();
        char cCharAt = string.charAt(0);
        if (str != null && cCharAt == ':') {
            if (string.length() < 2) {
                strArr[0] = "Bad " + str2 + " name " + string + " in package " + str + ": must be at least two characters";
                return null;
            }
            String strValidateName = validateName(string.substring(1), false, false);
            if (strValidateName != null) {
                strArr[0] = "Invalid " + str2 + " name " + string + " in package " + str + ": " + strValidateName;
                return null;
            }
            return str + string;
        }
        String strValidateName2 = validateName(string, true, false);
        if (strValidateName2 == null || "system".equals(string)) {
            return string;
        }
        strArr[0] = "Invalid " + str2 + " name " + string + " in package " + str + ": " + strValidateName2;
        return null;
    }

    public static String buildProcessName(String str, String str2, CharSequence charSequence, int i, String[] strArr, String[] strArr2) {
        if ((i & 2) != 0 && !"system".equals(charSequence)) {
            return str2 != null ? str2 : str;
        }
        if (strArr != null) {
            for (int length = strArr.length - 1; length >= 0; length--) {
                String str3 = strArr[length];
                if (str3.equals(str) || str3.equals(str2) || str3.equals(charSequence)) {
                    return str;
                }
            }
        }
        return (charSequence == null || charSequence.length() <= 0) ? str2 : TextUtils.safeIntern(buildCompoundName(str, charSequence, "process", strArr2));
    }

    public static String buildTaskAffinityName(String str, String str2, CharSequence charSequence, String[] strArr) {
        if (charSequence == null) {
            return str2;
        }
        if (charSequence.length() <= 0) {
            return null;
        }
        return buildCompoundName(str, charSequence, "taskAffinity", strArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x01e3, code lost:
    
        if (r5.keySet().removeAll(r7.keySet()) == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01e5, code lost:
    
        r21[0] = "Package" + r18.packageName + " AndroidManifext.xml 'key-set' and 'public-key' names must be distinct.";
        r17.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0204, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0205, code lost:
    
        r18.mKeySetMapping = new android.util.ArrayMap<>();
        r2 = r7.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0218, code lost:
    
        if (r2.hasNext() == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x021a, code lost:
    
        r4 = (java.util.Map.Entry) r2.next();
        r7 = (java.lang.String) r4.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0232, code lost:
    
        if (((android.util.ArraySet) r4.getValue()).size() != 0) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0234, code lost:
    
        android.util.Slog.w(android.content.pm.PackageParser.TAG, "Package" + r18.packageName + " AndroidManifext.xml 'key-set' " + r7 + " has no valid associated 'public-key'. Not including in package's defined key-sets.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x025d, code lost:
    
        if (r8.contains(r7) == false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x025f, code lost:
    
        android.util.Slog.w(android.content.pm.PackageParser.TAG, "Package" + r18.packageName + " AndroidManifext.xml 'key-set' " + r7 + " contained improper 'public-key' tags. Not including in package's defined key-sets.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0284, code lost:
    
        r18.mKeySetMapping.put(r7, new android.util.ArraySet<>());
        r4 = ((android.util.ArraySet) r4.getValue()).iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x029c, code lost:
    
        if (r4.hasNext() == false) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x029e, code lost:
    
        r18.mKeySetMapping.get(r7).add((java.security.PublicKey) r5.get((java.lang.String) r4.next()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x02c0, code lost:
    
        if (r18.mKeySetMapping.keySet().containsAll(r6) == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x02c2, code lost:
    
        r18.mUpgradeKeySets = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x02c5, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x02c6, code lost:
    
        r21[0] = "Package" + r18.packageName + " AndroidManifext.xml does not define all 'upgrade-key-set's .";
        r17.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x02e5, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean parseKeySets(android.content.pm.PackageParser.Package r18, android.content.res.Resources r19, android.content.res.XmlResourceParser r20, java.lang.String[] r21) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 742
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseKeySets(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, java.lang.String[]):boolean");
    }

    private boolean parsePermissionGroup(Package r21, int i, Resources resources, XmlResourceParser xmlResourceParser, String[] strArr) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestPermissionGroup);
        PermissionGroup permissionGroup = new PermissionGroup(r21, typedArrayObtainAttributes.getResourceId(12, 0), typedArrayObtainAttributes.getResourceId(9, 0), typedArrayObtainAttributes.getResourceId(10, 0));
        if (!parsePackageItemInfo(r21, permissionGroup.info, strArr, "<permission-group>", typedArrayObtainAttributes, true, 2, 0, 1, 8, 5, 7)) {
            typedArrayObtainAttributes.recycle();
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        permissionGroup.info.descriptionRes = typedArrayObtainAttributes.getResourceId(4, 0);
        permissionGroup.info.requestRes = typedArrayObtainAttributes.getResourceId(11, 0);
        permissionGroup.info.flags = typedArrayObtainAttributes.getInt(6, 0);
        permissionGroup.info.priority = typedArrayObtainAttributes.getInt(3, 0);
        typedArrayObtainAttributes.recycle();
        if (!parseAllMetaData(resources, xmlResourceParser, "<permission-group>", permissionGroup, strArr)) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        r21.permissionGroups.add(permissionGroup);
        return true;
    }

    private boolean parsePermission(Package r21, Resources resources, XmlResourceParser xmlResourceParser, String[] strArr) throws XmlPullParserException, IOException {
        String nonResourceString;
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestPermission);
        if (!typedArrayObtainAttributes.hasValue(10)) {
            nonResourceString = null;
        } else if ("android".equals(r21.packageName)) {
            nonResourceString = typedArrayObtainAttributes.getNonResourceString(10);
        } else {
            Slog.w(TAG, r21.packageName + " defines a background permission. Only the 'android' package can do that.");
            nonResourceString = null;
        }
        Permission permission = new Permission(r21, nonResourceString);
        if (!parsePackageItemInfo(r21, permission.info, strArr, "<permission>", typedArrayObtainAttributes, true, 2, 0, 1, 9, 6, 8)) {
            typedArrayObtainAttributes.recycle();
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        permission.info.group = typedArrayObtainAttributes.getNonResourceString(4);
        if (permission.info.group != null) {
            permission.info.group = permission.info.group.intern();
        }
        permission.info.descriptionRes = typedArrayObtainAttributes.getResourceId(5, 0);
        permission.info.requestRes = typedArrayObtainAttributes.getResourceId(11, 0);
        permission.info.protectionLevel = typedArrayObtainAttributes.getInt(3, 0);
        permission.info.flags = typedArrayObtainAttributes.getInt(7, 0);
        if (!permission.info.isRuntime() || !"android".equals(permission.info.packageName)) {
            permission.info.flags &= -5;
            permission.info.flags &= -9;
        } else if ((permission.info.flags & 4) != 0 && (permission.info.flags & 8) != 0) {
            throw new IllegalStateException("Permission cannot be both soft and hard restricted: " + permission.info.name);
        }
        typedArrayObtainAttributes.recycle();
        if (permission.info.protectionLevel == -1) {
            strArr[0] = "<permission> does not specify protectionLevel";
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        permission.info.protectionLevel = PermissionInfo.fixProtectionLevel(permission.info.protectionLevel);
        if (permission.info.getProtectionFlags() != 0 && (permission.info.protectionLevel & 4096) == 0 && (permission.info.protectionLevel & 8192) == 0 && (permission.info.protectionLevel & 15) != 2) {
            strArr[0] = "<permission>  protectionLevel specifies a non-instant flag but is not based on signature type";
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        if (!parseAllMetaData(resources, xmlResourceParser, "<permission>", permission, strArr)) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        r21.permissions.add(permission);
        return true;
    }

    private boolean parsePermissionTree(Package r20, Resources resources, XmlResourceParser xmlResourceParser, String[] strArr) throws XmlPullParserException, IOException {
        Permission permission = new Permission(r20, (String) null);
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestPermissionTree);
        if (!parsePackageItemInfo(r20, permission.info, strArr, "<permission-tree>", typedArrayObtainAttributes, true, 2, 0, 1, 5, 3, 4)) {
            typedArrayObtainAttributes.recycle();
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        typedArrayObtainAttributes.recycle();
        int iIndexOf = permission.info.name.indexOf(46);
        if (iIndexOf > 0) {
            iIndexOf = permission.info.name.indexOf(46, iIndexOf + 1);
        }
        if (iIndexOf < 0) {
            strArr[0] = "<permission-tree> name has less than three segments: " + permission.info.name;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        permission.info.descriptionRes = 0;
        permission.info.requestRes = 0;
        permission.info.protectionLevel = 0;
        permission.tree = true;
        if (!parseAllMetaData(resources, xmlResourceParser, "<permission-tree>", permission, strArr)) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        r20.permissions.add(permission);
        return true;
    }

    private Instrumentation parseInstrumentation(Package r12, Resources resources, XmlResourceParser xmlResourceParser, String[] strArr) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestInstrumentation);
        if (this.mParseInstrumentationArgs == null) {
            ParsePackageItemArgs parsePackageItemArgs = new ParsePackageItemArgs(r12, strArr, 2, 0, 1, 8, 6, 7);
            this.mParseInstrumentationArgs = parsePackageItemArgs;
            parsePackageItemArgs.tag = "<instrumentation>";
        }
        this.mParseInstrumentationArgs.sa = typedArrayObtainAttributes;
        Instrumentation instrumentation = new Instrumentation(this.mParseInstrumentationArgs, new InstrumentationInfo());
        if (strArr[0] != null) {
            typedArrayObtainAttributes.recycle();
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return null;
        }
        String nonResourceString = typedArrayObtainAttributes.getNonResourceString(3);
        instrumentation.info.targetPackage = nonResourceString != null ? nonResourceString.intern() : null;
        String nonResourceString2 = typedArrayObtainAttributes.getNonResourceString(9);
        instrumentation.info.targetProcesses = nonResourceString2 != null ? nonResourceString2.intern() : null;
        instrumentation.info.handleProfiling = typedArrayObtainAttributes.getBoolean(4, false);
        instrumentation.info.functionalTest = typedArrayObtainAttributes.getBoolean(5, false);
        typedArrayObtainAttributes.recycle();
        if (instrumentation.info.targetPackage == null) {
            strArr[0] = "<instrumentation> does not specify targetPackage";
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return null;
        }
        if (!parseAllMetaData(resources, xmlResourceParser, "<instrumentation>", instrumentation, strArr)) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return null;
        }
        r12.instrumentation.add(instrumentation);
        return instrumentation;
    }

    /* JADX WARN: Code restructure failed: missing block: B:275:0x05c5, code lost:
    
        r11[r12] = "Bad static-library declaration name: " + r1 + " version: " + r4;
        r28.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
        com.android.internal.util.XmlUtils.skipCurrentTag(r31);
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x05eb, code lost:
    
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:312:0x06e6, code lost:
    
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x06ef, code lost:
    
        if (android.text.TextUtils.isEmpty(r7.staticSharedLibName) == false) goto L315;
     */
    /* JADX WARN: Code restructure failed: missing block: B:314:0x06f1, code lost:
    
        r7.activities.add(generateAppDetailsHiddenActivity(r7, r32, r11, r7.baseHardwareAccelerated));
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x06fe, code lost:
    
        if (r19 == 0) goto L317;
     */
    /* JADX WARN: Code restructure failed: missing block: B:316:0x0700, code lost:
    
        java.util.Collections.sort(r7.activities, android.content.pm.$$Lambda$PackageParser$uLALhADCNBfQqT5iKncTHUpGpAQ.INSTANCE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x0707, code lost:
    
        if (r20 == 0) goto L319;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x0709, code lost:
    
        java.util.Collections.sort(r7.receivers, android.content.pm.$$Lambda$PackageParser$ehhikUMJSPpfInd3RyG_1UjVg.INSTANCE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:319:0x0710, code lost:
    
        if (r22 == 0) goto L321;
     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x0712, code lost:
    
        java.util.Collections.sort(r7.services, android.content.pm.$$Lambda$PackageParser$uOtYPiYoY_Cq_SQl58yxmUK2oo.INSTANCE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x0719, code lost:
    
        setMaxAspectRatio(r29);
        setMinAspectRatio(r29);
        setSupportsSizeChanges(r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:322:0x0726, code lost:
    
        if (hasDomainURLs(r29) == false) goto L324;
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x0728, code lost:
    
        r7.applicationInfo.privateFlags |= 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x0731, code lost:
    
        r7.applicationInfo.privateFlags &= -17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x0739, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:349:?, code lost:
    
        return true;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v33, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r0v38, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r0v44, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r0v48, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r10v37 */
    /* JADX WARN: Type inference failed for: r10v38, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r10v61 */
    /* JADX WARN: Type inference failed for: r10v62 */
    /* JADX WARN: Type inference failed for: r10v63 */
    /* JADX WARN: Type inference failed for: r10v64 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r19v1 */
    /* JADX WARN: Type inference failed for: r19v10 */
    /* JADX WARN: Type inference failed for: r19v11 */
    /* JADX WARN: Type inference failed for: r19v12 */
    /* JADX WARN: Type inference failed for: r19v13 */
    /* JADX WARN: Type inference failed for: r19v14 */
    /* JADX WARN: Type inference failed for: r19v15 */
    /* JADX WARN: Type inference failed for: r19v2 */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r19v4 */
    /* JADX WARN: Type inference failed for: r19v5 */
    /* JADX WARN: Type inference failed for: r19v6 */
    /* JADX WARN: Type inference failed for: r1v101 */
    /* JADX WARN: Type inference failed for: r1v102 */
    /* JADX WARN: Type inference failed for: r1v104 */
    /* JADX WARN: Type inference failed for: r1v109 */
    /* JADX WARN: Type inference failed for: r1v110 */
    /* JADX WARN: Type inference failed for: r1v112 */
    /* JADX WARN: Type inference failed for: r1v84 */
    /* JADX WARN: Type inference failed for: r1v85 */
    /* JADX WARN: Type inference failed for: r1v87 */
    /* JADX WARN: Type inference failed for: r1v94 */
    /* JADX WARN: Type inference failed for: r1v95 */
    /* JADX WARN: Type inference failed for: r1v97 */
    /* JADX WARN: Type inference failed for: r20v10 */
    /* JADX WARN: Type inference failed for: r20v11 */
    /* JADX WARN: Type inference failed for: r20v12 */
    /* JADX WARN: Type inference failed for: r20v13 */
    /* JADX WARN: Type inference failed for: r20v14 */
    /* JADX WARN: Type inference failed for: r20v15 */
    /* JADX WARN: Type inference failed for: r20v2 */
    /* JADX WARN: Type inference failed for: r20v3 */
    /* JADX WARN: Type inference failed for: r20v4 */
    /* JADX WARN: Type inference failed for: r20v5 */
    /* JADX WARN: Type inference failed for: r20v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean parseBaseApplication(android.content.pm.PackageParser.Package r29, android.content.res.Resources r30, android.content.res.XmlResourceParser r31, int r32, java.lang.String[] r33) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1851
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseBaseApplication(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, int, java.lang.String[]):boolean");
    }

    private static boolean hasDomainURLs(Package r9) {
        if (r9 != null && r9.activities != null) {
            ArrayList<Activity> arrayList = r9.activities;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ArrayList<II> arrayList2 = arrayList.get(i).intents;
                if (arrayList2 != 0) {
                    int size2 = arrayList2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ActivityIntentInfo activityIntentInfo = (ActivityIntentInfo) arrayList2.get(i2);
                        if (activityIntentInfo.hasAction("android.intent.action.VIEW") && activityIntentInfo.hasAction("android.intent.action.VIEW") && (activityIntentInfo.hasDataScheme(IntentFilter.SCHEME_HTTP) || activityIntentInfo.hasDataScheme(IntentFilter.SCHEME_HTTPS))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:90:0x0221, code lost:
    
        return r14;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v26, types: [android.content.pm.ComponentInfo] */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v34 */
    /* JADX WARN: Type inference failed for: r0v35 */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.content.pm.PackageParser$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean parseSplitApplication(android.content.pm.PackageParser.Package r21, android.content.res.Resources r22, android.content.res.XmlResourceParser r23, int r24, int r25, java.lang.String[] r26) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseSplitApplication(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, int, int, java.lang.String[]):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean parsePackageItemInfo(Package r1, PackageItemInfo packageItemInfo, String[] strArr, String str, TypedArray typedArray, boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
        if (typedArray == null) {
            strArr[0] = str + " does not contain any attributes";
            return false;
        }
        String nonConfigurationString = typedArray.getNonConfigurationString(i, 0);
        if (nonConfigurationString != null) {
            String strBuildClassName = buildClassName(r1.applicationInfo.packageName, nonConfigurationString, strArr);
            if (PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(strBuildClassName)) {
                strArr[0] = str + " invalid android:name";
                return false;
            }
            packageItemInfo.name = strBuildClassName;
            if (strBuildClassName == null) {
                return false;
            }
        } else if (z) {
            strArr[0] = str + " does not specify android:name";
            return false;
        }
        int resourceId = sUseRoundIcon ? typedArray.getResourceId(i4, 0) : 0;
        if (resourceId != 0) {
            packageItemInfo.icon = resourceId;
            packageItemInfo.nonLocalizedLabel = null;
        } else {
            int resourceId2 = typedArray.getResourceId(i3, 0);
            if (resourceId2 != 0) {
                packageItemInfo.icon = resourceId2;
                packageItemInfo.nonLocalizedLabel = null;
            }
        }
        int resourceId3 = typedArray.getResourceId(i5, 0);
        if (resourceId3 != 0) {
            packageItemInfo.logo = resourceId3;
        }
        int resourceId4 = typedArray.getResourceId(i6, 0);
        if (resourceId4 != 0) {
            packageItemInfo.banner = resourceId4;
        }
        TypedValue typedValuePeekValue = typedArray.peekValue(i2);
        if (typedValuePeekValue != null) {
            int i7 = typedValuePeekValue.resourceId;
            packageItemInfo.labelRes = i7;
            if (i7 == 0) {
                packageItemInfo.nonLocalizedLabel = typedValuePeekValue.coerceToString();
            }
        }
        packageItemInfo.packageName = r1.packageName;
        return true;
    }

    private Activity generateAppDetailsHiddenActivity(Package r4, int i, String[] strArr, boolean z) {
        Activity activity = new Activity(r4, PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME, new ActivityInfo());
        activity.owner = r4;
        activity.setPackageName(r4.packageName);
        activity.info.theme = 16973909;
        activity.info.exported = true;
        activity.info.name = PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME;
        activity.info.processName = r4.applicationInfo.processName;
        activity.info.uiOptions = activity.info.applicationInfo.uiOptions;
        activity.info.taskAffinity = buildTaskAffinityName(r4.packageName, r4.packageName, ":app_details", strArr);
        activity.info.enabled = true;
        activity.info.launchMode = 0;
        activity.info.documentLaunchMode = 0;
        activity.info.maxRecents = ActivityTaskManager.getDefaultAppRecentsLimitStatic();
        activity.info.configChanges = getActivityConfigChanges(0, 0);
        activity.info.softInputMode = 0;
        activity.info.persistableMode = 1;
        activity.info.screenOrientation = -1;
        activity.info.resizeMode = 4;
        activity.info.lockTaskLaunchMode = 0;
        activity.info.directBootAware = false;
        activity.info.rotationAnimation = -1;
        activity.info.colorMode = 0;
        if (z) {
            activity.info.flags |= 512;
        }
        return activity;
    }

    /* JADX WARN: Code restructure failed: missing block: B:224:0x05fb, code lost:
    
        resolveWindowLayout(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x05fe, code lost:
    
        if (r11 != false) goto L231;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0600, code lost:
    
        r0 = r8.info;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x0608, code lost:
    
        if (r8.intents.size() <= 0) goto L229;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x060a, code lost:
    
        r9 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x060d, code lost:
    
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x060e, code lost:
    
        r0.exported = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0610, code lost:
    
        return r8;
     */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.content.pm.PackageParser.Activity parseActivity(android.content.pm.PackageParser.Package r22, android.content.res.Resources r23, android.content.res.XmlResourceParser r24, int r25, java.lang.String[] r26, android.content.pm.PackageParser.CachedComponentArgs r27, boolean r28, boolean r29) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1553
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseActivity(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, int, java.lang.String[], android.content.pm.PackageParser$CachedComponentArgs, boolean, boolean):android.content.pm.PackageParser$Activity");
    }

    private void setActivityResizeMode(ActivityInfo activityInfo, TypedArray typedArray, Package r7) {
        boolean z = (r7.applicationInfo.privateFlags & 3072) != 0;
        if (typedArray.hasValue(40) || z) {
            if (typedArray.getBoolean(40, (r7.applicationInfo.privateFlags & 1024) != 0)) {
                activityInfo.resizeMode = 2;
                return;
            } else {
                activityInfo.resizeMode = 0;
                return;
            }
        }
        if ((r7.applicationInfo.privateFlags & 4096) != 0) {
            activityInfo.resizeMode = 1;
            return;
        }
        if (activityInfo.isFixedOrientationPortrait()) {
            activityInfo.resizeMode = 6;
            return;
        }
        if (activityInfo.isFixedOrientationLandscape()) {
            activityInfo.resizeMode = 5;
        } else if (activityInfo.isFixedOrientation()) {
            activityInfo.resizeMode = 7;
        } else {
            activityInfo.resizeMode = 4;
        }
    }

    private void setMaxAspectRatio(Package r4) {
        float f = r4.applicationInfo.targetSdkVersion < 26 ? 1.86f : 0.0f;
        if (r4.applicationInfo.maxAspectRatio != 0.0f) {
            f = r4.applicationInfo.maxAspectRatio;
        } else if (r4.mAppMetaData != null && r4.mAppMetaData.containsKey(METADATA_MAX_ASPECT_RATIO)) {
            f = r4.mAppMetaData.getFloat(METADATA_MAX_ASPECT_RATIO, f);
        }
        for (Activity activity : r4.activities) {
            if (!activity.hasMaxAspectRatio()) {
                activity.setMaxAspectRatio(activity.metaData != null ? activity.metaData.getFloat(METADATA_MAX_ASPECT_RATIO, f) : f);
            }
        }
    }

    private void setMinAspectRatio(Package r4) {
        float f;
        float f2 = 0.0f;
        if (r4.applicationInfo.minAspectRatio != 0.0f) {
            f = r4.applicationInfo.minAspectRatio;
        } else {
            if (r4.applicationInfo.targetSdkVersion < 29) {
                Callback callback = this.mCallback;
                f2 = (callback == null || !callback.hasFeature(PackageManager.FEATURE_WATCH)) ? 1.333f : 1.0f;
            }
            f = f2;
        }
        for (Activity activity : r4.activities) {
            if (!activity.hasMinAspectRatio()) {
                activity.setMinAspectRatio(f);
            }
        }
    }

    private void setSupportsSizeChanges(Package r6) {
        boolean z = r6.mAppMetaData != null && r6.mAppMetaData.getBoolean(METADATA_SUPPORTS_SIZE_CHANGES, false);
        for (Activity activity : r6.activities) {
            if (z || (activity.metaData != null && activity.metaData.getBoolean(METADATA_SUPPORTS_SIZE_CHANGES, false))) {
                activity.info.supportsSizeChanges = true;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseLayout(android.content.res.Resources r17, android.util.AttributeSet r18, android.content.pm.PackageParser.Activity r19) {
        /*
            r16 = this;
            int[] r0 = com.android.internal.R.styleable.AndroidManifestLayout
            r1 = r17
            r2 = r18
            android.content.res.TypedArray r0 = r1.obtainAttributes(r2, r0)
            r1 = 3
            int r2 = r0.getType(r1)
            r3 = 5
            r4 = 6
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            r6 = 1
            r7 = -1
            if (r2 != r4) goto L1d
            float r1 = r0.getFraction(r1, r6, r6, r5)
            r10 = r1
            goto L27
        L1d:
            if (r2 != r3) goto L26
            int r1 = r0.getDimensionPixelSize(r1, r7)
            r9 = r1
            r10 = r5
            goto L28
        L26:
            r10 = r5
        L27:
            r9 = r7
        L28:
            r1 = 4
            int r2 = r0.getType(r1)
            if (r2 != r4) goto L34
            float r5 = r0.getFraction(r1, r6, r6, r5)
            goto L3d
        L34:
            if (r2 != r3) goto L3d
            int r1 = r0.getDimensionPixelSize(r1, r7)
            r11 = r1
            r12 = r5
            goto L3f
        L3d:
            r12 = r5
            r11 = r7
        L3f:
            r1 = 0
            r2 = 17
            int r13 = r0.getInt(r1, r2)
            int r14 = r0.getDimensionPixelSize(r6, r7)
            r1 = 2
            int r15 = r0.getDimensionPixelSize(r1, r7)
            r0.recycle()
            r0 = r19
            android.content.pm.ActivityInfo r0 = r0.info
            android.content.pm.ActivityInfo$WindowLayout r1 = new android.content.pm.ActivityInfo$WindowLayout
            r8 = r1
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            r0.windowLayout = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseLayout(android.content.res.Resources, android.util.AttributeSet, android.content.pm.PackageParser$Activity):void");
    }

    private void resolveWindowLayout(Activity activity) {
        if (activity.metaData == null || !activity.metaData.containsKey(METADATA_ACTIVITY_WINDOW_LAYOUT_AFFINITY)) {
            return;
        }
        ActivityInfo activityInfo = activity.info;
        if (activityInfo.windowLayout == null || activityInfo.windowLayout.windowLayoutAffinity == null) {
            String string = activity.metaData.getString(METADATA_ACTIVITY_WINDOW_LAYOUT_AFFINITY);
            if (activityInfo.windowLayout == null) {
                activityInfo.windowLayout = new ActivityInfo.WindowLayout(-1, -1.0f, -1, -1.0f, 0, -1, -1);
            }
            activityInfo.windowLayout.windowLayoutAffinity = string;
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private Activity parseActivityAlias(Package r27, Resources resources, XmlResourceParser xmlResourceParser, int i, String[] strArr, CachedComponentArgs cachedComponentArgs) throws XmlPullParserException, IOException {
        Activity activity;
        int i2;
        String[] strArr2;
        XmlResourceParser xmlResourceParser2;
        Resources resources2;
        ActivityIntentInfo activityIntentInfo;
        int i3;
        XmlResourceParser xmlResourceParser3 = xmlResourceParser;
        String[] strArr3 = strArr;
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser3, R.styleable.AndroidManifestActivityAlias);
        String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(7, 1024);
        if (nonConfigurationString == null) {
            strArr3[0] = "<activity-alias> does not specify android:targetActivity";
            typedArrayObtainAttributes.recycle();
            return null;
        }
        String strBuildClassName = buildClassName(r27.applicationInfo.packageName, nonConfigurationString, strArr3);
        if (strBuildClassName == null) {
            typedArrayObtainAttributes.recycle();
            return null;
        }
        if (cachedComponentArgs.mActivityAliasArgs == null) {
            cachedComponentArgs.mActivityAliasArgs = new ParseComponentArgs(r27, strArr, 2, 0, 1, 11, 8, 10, this.mSeparateProcesses, 0, 6, 4);
            cachedComponentArgs.mActivityAliasArgs.tag = "<activity-alias>";
        }
        cachedComponentArgs.mActivityAliasArgs.sa = typedArrayObtainAttributes;
        cachedComponentArgs.mActivityAliasArgs.flags = i;
        int size = r27.activities.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                activity = null;
                break;
            }
            activity = r27.activities.get(i4);
            if (strBuildClassName.equals(activity.info.name)) {
                break;
            }
            i4++;
        }
        if (activity == null) {
            strArr3[0] = "<activity-alias> target activity " + strBuildClassName + " not found in manifest";
            typedArrayObtainAttributes.recycle();
            return null;
        }
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.targetActivity = strBuildClassName;
        activityInfo.configChanges = activity.info.configChanges;
        activityInfo.flags = activity.info.flags;
        activityInfo.privateFlags = activity.info.privateFlags;
        activityInfo.icon = activity.info.icon;
        activityInfo.logo = activity.info.logo;
        activityInfo.banner = activity.info.banner;
        activityInfo.labelRes = activity.info.labelRes;
        activityInfo.nonLocalizedLabel = activity.info.nonLocalizedLabel;
        activityInfo.launchMode = activity.info.launchMode;
        activityInfo.lockTaskLaunchMode = activity.info.lockTaskLaunchMode;
        activityInfo.processName = activity.info.processName;
        if (activityInfo.descriptionRes == 0) {
            activityInfo.descriptionRes = activity.info.descriptionRes;
        }
        activityInfo.screenOrientation = activity.info.screenOrientation;
        activityInfo.taskAffinity = activity.info.taskAffinity;
        activityInfo.theme = activity.info.theme;
        activityInfo.softInputMode = activity.info.softInputMode;
        activityInfo.uiOptions = activity.info.uiOptions;
        activityInfo.parentActivityName = activity.info.parentActivityName;
        activityInfo.maxRecents = activity.info.maxRecents;
        activityInfo.windowLayout = activity.info.windowLayout;
        activityInfo.resizeMode = activity.info.resizeMode;
        activityInfo.maxAspectRatio = activity.info.maxAspectRatio;
        activityInfo.minAspectRatio = activity.info.minAspectRatio;
        activityInfo.supportsSizeChanges = activity.info.supportsSizeChanges;
        activityInfo.requestedVrComponent = activity.info.requestedVrComponent;
        activityInfo.directBootAware = activity.info.directBootAware;
        Activity activity2 = new Activity(cachedComponentArgs.mActivityAliasArgs, activityInfo);
        if (strArr3[0] != null) {
            typedArrayObtainAttributes.recycle();
            return null;
        }
        boolean zHasValue = typedArrayObtainAttributes.hasValue(5);
        if (zHasValue) {
            activity2.info.exported = typedArrayObtainAttributes.getBoolean(5, false);
        }
        int i5 = 3;
        String nonConfigurationString2 = typedArrayObtainAttributes.getNonConfigurationString(3, 0);
        if (nonConfigurationString2 != null) {
            activity2.info.permission = nonConfigurationString2.length() > 0 ? nonConfigurationString2.toString().intern() : null;
        }
        String nonConfigurationString3 = typedArrayObtainAttributes.getNonConfigurationString(9, 1024);
        if (nonConfigurationString3 != null) {
            String strBuildClassName2 = buildClassName(activity2.info.packageName, nonConfigurationString3, strArr3);
            if (strArr3[0] == null) {
                activity2.info.parentActivityName = strBuildClassName2;
            } else {
                Log.e(TAG, "Activity alias " + activity2.info.name + " specified invalid parentActivityName " + nonConfigurationString3);
                strArr3[0] = null;
            }
        }
        int i6 = 1;
        boolean z = (activity2.info.flags & 1048576) != 0;
        typedArrayObtainAttributes.recycle();
        if (strArr3[0] != null) {
            return null;
        }
        int depth = xmlResourceParser.getDepth();
        while (true) {
            int next = xmlResourceParser.next();
            if (next == i6 || (next == i5 && xmlResourceParser.getDepth() <= depth)) {
                break;
            }
            if (next != i5) {
                if (next != 4) {
                    if (xmlResourceParser.getName().equals("intent-filter")) {
                        ActivityIntentInfo activityIntentInfo2 = new ActivityIntentInfo(activity2);
                        i2 = depth;
                        strArr2 = strArr3;
                        xmlResourceParser2 = xmlResourceParser3;
                        if (!parseIntent(resources, xmlResourceParser, true, true, activityIntentInfo2, strArr)) {
                            return null;
                        }
                        if (activityIntentInfo2.countActions() == 0) {
                            Slog.w(TAG, "No actions in intent filter at " + this.mArchiveSourcePath + " " + xmlResourceParser.getPositionDescription());
                            activityIntentInfo = activityIntentInfo2;
                        } else {
                            activity2.order = Math.max(activityIntentInfo2.getOrder(), activity2.order);
                            activityIntentInfo = activityIntentInfo2;
                            activity2.intents.add(activityIntentInfo);
                        }
                        if (z) {
                            i3 = 1;
                        } else {
                            i3 = isImplicitlyExposedIntent(activityIntentInfo) ? 2 : 0;
                        }
                        activityIntentInfo.setVisibilityToInstantApp(i3);
                        if (activityIntentInfo.isVisibleToInstantApp()) {
                            activity2.info.flags |= 1048576;
                        }
                        if (activityIntentInfo.isImplicitlyVisibleToInstantApp()) {
                            activity2.info.flags |= 2097152;
                        }
                    } else {
                        i2 = depth;
                        strArr2 = strArr3;
                        xmlResourceParser2 = xmlResourceParser3;
                        if (xmlResourceParser.getName().equals("meta-data")) {
                            resources2 = resources;
                            Bundle metaData = parseMetaData(resources2, xmlResourceParser2, activity2.metaData, strArr2);
                            activity2.metaData = metaData;
                            if (metaData == null) {
                                return null;
                            }
                        } else {
                            resources2 = resources;
                            Slog.w(TAG, "Unknown element under <activity-alias>: " + xmlResourceParser.getName() + " at " + this.mArchiveSourcePath + " " + xmlResourceParser.getPositionDescription());
                            XmlUtils.skipCurrentTag(xmlResourceParser);
                        }
                    }
                    strArr3 = strArr2;
                    xmlResourceParser3 = xmlResourceParser2;
                    depth = i2;
                    i5 = 3;
                } else {
                    continue;
                }
            }
            i6 = 1;
        }
        if (!zHasValue) {
            activity2.info.exported = activity2.intents.size() > 0;
        }
        return activity2;
    }

    private Provider parseProvider(Package r23, Resources resources, XmlResourceParser xmlResourceParser, int i, String[] strArr, CachedComponentArgs cachedComponentArgs) throws XmlPullParserException, IOException {
        TypedArray typedArray;
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestProvider);
        if (cachedComponentArgs.mProviderArgs == null) {
            typedArray = typedArrayObtainAttributes;
            cachedComponentArgs.mProviderArgs = new ParseComponentArgs(r23, strArr, 2, 0, 1, 19, 15, 17, this.mSeparateProcesses, 8, 14, 6);
            cachedComponentArgs.mProviderArgs.tag = "<provider>";
        } else {
            typedArray = typedArrayObtainAttributes;
        }
        TypedArray typedArray2 = typedArray;
        cachedComponentArgs.mProviderArgs.sa = typedArray2;
        cachedComponentArgs.mProviderArgs.flags = i;
        Provider provider = new Provider(cachedComponentArgs.mProviderArgs, new ProviderInfo());
        if (strArr[0] != null) {
            typedArray2.recycle();
            return null;
        }
        provider.info.exported = typedArray2.getBoolean(7, r23.applicationInfo.targetSdkVersion < 17);
        String nonConfigurationString = typedArray2.getNonConfigurationString(10, 0);
        provider.info.isSyncable = typedArray2.getBoolean(11, false);
        String nonConfigurationString2 = typedArray2.getNonConfigurationString(3, 0);
        String nonConfigurationString3 = typedArray2.getNonConfigurationString(4, 0);
        if (nonConfigurationString3 == null) {
            nonConfigurationString3 = nonConfigurationString2;
        }
        if (nonConfigurationString3 == null) {
            provider.info.readPermission = r23.applicationInfo.permission;
        } else {
            provider.info.readPermission = nonConfigurationString3.length() > 0 ? nonConfigurationString3.toString().intern() : null;
        }
        String nonConfigurationString4 = typedArray2.getNonConfigurationString(5, 0);
        if (nonConfigurationString4 != null) {
            nonConfigurationString2 = nonConfigurationString4;
        }
        if (nonConfigurationString2 == null) {
            provider.info.writePermission = r23.applicationInfo.permission;
        } else {
            provider.info.writePermission = nonConfigurationString2.length() > 0 ? nonConfigurationString2.toString().intern() : null;
        }
        provider.info.grantUriPermissions = typedArray2.getBoolean(13, false);
        provider.info.forceUriPermissions = typedArray2.getBoolean(22, false);
        provider.info.multiprocess = typedArray2.getBoolean(9, false);
        provider.info.initOrder = typedArray2.getInt(12, 0);
        provider.info.splitName = typedArray2.getNonConfigurationString(21, 0);
        provider.info.flags = 0;
        if (typedArray2.getBoolean(16, false)) {
            provider.info.flags |= 1073741824;
        }
        provider.info.directBootAware = typedArray2.getBoolean(18, false);
        if (provider.info.directBootAware) {
            r23.applicationInfo.privateFlags |= 256;
        }
        boolean z = typedArray2.getBoolean(20, false);
        if (z) {
            provider.info.flags |= 1048576;
            r23.visibleToInstantApps = true;
        }
        typedArray2.recycle();
        if ((r23.applicationInfo.privateFlags & 2) != 0 && provider.info.processName == r23.packageName) {
            strArr[0] = "Heavy-weight applications can not have providers in main process";
            return null;
        }
        if (nonConfigurationString == null) {
            strArr[0] = "<provider> does not include authorities attribute";
            return null;
        }
        if (nonConfigurationString.length() <= 0) {
            strArr[0] = "<provider> has empty authorities attribute";
            return null;
        }
        provider.info.authority = nonConfigurationString.intern();
        if (parseProviderTags(resources, xmlResourceParser, z, provider, strArr)) {
            return provider;
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x027f, code lost:
    
        return true;
     */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean parseProviderTags(android.content.res.Resources r20, android.content.res.XmlResourceParser r21, boolean r22, android.content.pm.PackageParser.Provider r23, java.lang.String[] r24) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseProviderTags(android.content.res.Resources, android.content.res.XmlResourceParser, boolean, android.content.pm.PackageParser$Provider, java.lang.String[]):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x0207, code lost:
    
        if (r11 != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0209, code lost:
    
        r0 = r8.info;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0211, code lost:
    
        if (r8.intents.size() <= 0) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0213, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0215, code lost:
    
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0216, code lost:
    
        r0.exported = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0218, code lost:
    
        return r8;
     */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.content.pm.PackageParser.Service parseService(android.content.pm.PackageParser.Package r22, android.content.res.Resources r23, android.content.res.XmlResourceParser r24, int r25, java.lang.String[] r26, android.content.pm.PackageParser.CachedComponentArgs r27) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 537
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseService(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, int, java.lang.String[], android.content.pm.PackageParser$CachedComponentArgs):android.content.pm.PackageParser$Service");
    }

    private boolean isImplicitlyExposedIntent(IntentInfo intentInfo) {
        return intentInfo.hasCategory(Intent.CATEGORY_BROWSABLE) || intentInfo.hasAction(Intent.ACTION_SEND) || intentInfo.hasAction(Intent.ACTION_SENDTO) || intentInfo.hasAction(Intent.ACTION_SEND_MULTIPLE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0077, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean parseAllMetaData(android.content.res.Resources r6, android.content.res.XmlResourceParser r7, java.lang.String r8, android.content.pm.PackageParser.Component<?> r9, java.lang.String[] r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r5 = this;
            int r0 = r7.getDepth()
        L4:
            int r1 = r7.next()
            r2 = 1
            if (r1 == r2) goto L77
            r3 = 3
            if (r1 != r3) goto L14
            int r4 = r7.getDepth()
            if (r4 <= r0) goto L77
        L14:
            if (r1 == r3) goto L4
            r2 = 4
            if (r1 != r2) goto L1a
            goto L4
        L1a:
            java.lang.String r1 = r7.getName()
            java.lang.String r2 = "meta-data"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L33
            android.os.Bundle r1 = r9.metaData
            android.os.Bundle r1 = r5.parseMetaData(r6, r7, r1, r10)
            r9.metaData = r1
            if (r1 != 0) goto L4
            r5 = 0
            return r5
        L33:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown element under "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r2 = ": "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r7.getName()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = " at "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r5.mArchiveSourcePath
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = " "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r7.getPositionDescription()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "PackageParser"
            android.util.Slog.w(r2, r1)
            com.android.internal.util.XmlUtils.skipCurrentTag(r7)
            goto L4
        L77:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseAllMetaData(android.content.res.Resources, android.content.res.XmlResourceParser, java.lang.String, android.content.pm.PackageParser$Component, java.lang.String[]):boolean");
    }

    private Bundle parseMetaData(Resources resources, XmlResourceParser xmlResourceParser, Bundle bundle, String[] strArr) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestMetaData);
        if (bundle == null) {
            bundle = new Bundle();
        }
        String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(0, 0);
        if (nonConfigurationString == null) {
            strArr[0] = "<meta-data> requires an android:name attribute";
            typedArrayObtainAttributes.recycle();
            return null;
        }
        String strIntern = nonConfigurationString.intern();
        TypedValue typedValuePeekValue = typedArrayObtainAttributes.peekValue(2);
        if (typedValuePeekValue != null && typedValuePeekValue.resourceId != 0) {
            bundle.putInt(strIntern, typedValuePeekValue.resourceId);
        } else {
            TypedValue typedValuePeekValue2 = typedArrayObtainAttributes.peekValue(1);
            if (typedValuePeekValue2 != null) {
                if (typedValuePeekValue2.type == 3) {
                    CharSequence charSequenceCoerceToString = typedValuePeekValue2.coerceToString();
                    bundle.putString(strIntern, charSequenceCoerceToString != null ? charSequenceCoerceToString.toString() : null);
                } else if (typedValuePeekValue2.type == 18) {
                    bundle.putBoolean(strIntern, typedValuePeekValue2.data != 0);
                } else if (typedValuePeekValue2.type >= 16 && typedValuePeekValue2.type <= 31) {
                    bundle.putInt(strIntern, typedValuePeekValue2.data);
                } else if (typedValuePeekValue2.type == 4) {
                    bundle.putFloat(strIntern, typedValuePeekValue2.getFloat());
                } else {
                    Slog.w(TAG, "<meta-data> only supports string, integer, float, color, boolean, and resource reference types: " + xmlResourceParser.getName() + " at " + this.mArchiveSourcePath + " " + xmlResourceParser.getPositionDescription());
                }
            } else {
                strArr[0] = "<meta-data> requires an android:value or android:resource attribute";
                bundle = null;
            }
        }
        typedArrayObtainAttributes.recycle();
        XmlUtils.skipCurrentTag(xmlResourceParser);
        return bundle;
    }

    private static VerifierInfo parseVerifier(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        String attributeValue = null;
        String attributeValue2 = null;
        for (int i = 0; i < attributeCount; i++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i);
            if (attributeNameResource == 16842755) {
                attributeValue = attributeSet.getAttributeValue(i);
            } else if (attributeNameResource == 16843686) {
                attributeValue2 = attributeSet.getAttributeValue(i);
            }
        }
        if (attributeValue == null || attributeValue.length() == 0) {
            Slog.i(TAG, "verifier package name was null; skipping");
            return null;
        }
        PublicKey publicKey = parsePublicKey(attributeValue2);
        if (publicKey == null) {
            Slog.i(TAG, "Unable to parse verifier public key for " + attributeValue);
            return null;
        }
        return new VerifierInfo(attributeValue, publicKey);
    }

    public static final PublicKey parsePublicKey(String str) {
        if (str == null) {
            Slog.w(TAG, "Could not parse null public key");
            return null;
        }
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(str, 0));
            try {
                return KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA).generatePublic(x509EncodedKeySpec);
            } catch (NoSuchAlgorithmException unused) {
                Slog.wtf(TAG, "Could not parse public key: RSA KeyFactory not included in build");
                try {
                    return KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_EC).generatePublic(x509EncodedKeySpec);
                } catch (NoSuchAlgorithmException unused2) {
                    Slog.wtf(TAG, "Could not parse public key: EC KeyFactory not included in build");
                    try {
                        return KeyFactory.getInstance("DSA").generatePublic(x509EncodedKeySpec);
                    } catch (NoSuchAlgorithmException unused3) {
                        Slog.wtf(TAG, "Could not parse public key: DSA KeyFactory not included in build");
                        return null;
                    } catch (InvalidKeySpecException unused4) {
                        return null;
                    }
                } catch (InvalidKeySpecException unused5) {
                    return KeyFactory.getInstance("DSA").generatePublic(x509EncodedKeySpec);
                }
            } catch (InvalidKeySpecException unused6) {
                return KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_EC).generatePublic(x509EncodedKeySpec);
            }
        } catch (IllegalArgumentException unused7) {
            Slog.w(TAG, "Could not parse verifier public key; invalid Base64");
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a2, code lost:
    
        r22[0] = "No value supplied for <android:name>";
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a4, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bd, code lost:
    
        r22[0] = "No value supplied for <android:name>";
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00bf, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01a7, code lost:
    
        r21.hasDefault = r21.hasCategory(android.content.Intent.CATEGORY_DEFAULT);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01af, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean parseIntent(android.content.res.Resources r17, android.content.res.XmlResourceParser r18, boolean r19, boolean r20, android.content.pm.PackageParser.IntentInfo r21, java.lang.String[] r22) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseIntent(android.content.res.Resources, android.content.res.XmlResourceParser, boolean, boolean, android.content.pm.PackageParser$IntentInfo, java.lang.String[]):boolean");
    }

    public static final class SigningDetails implements Parcelable {
        private static final int PAST_CERT_EXISTS = 0;
        public final Signature[] pastSigningCertificates;
        public final ArraySet<PublicKey> publicKeys;
        public final int signatureSchemeVersion;
        public final Signature[] signatures;
        public static final SigningDetails UNKNOWN = new SigningDetails(null, 0, null, null);
        public static final Parcelable.Creator<SigningDetails> CREATOR = new Parcelable.Creator<SigningDetails>() { // from class: android.content.pm.PackageParser.SigningDetails.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SigningDetails createFromParcel(Parcel parcel) {
                if (parcel.readBoolean()) {
                    return SigningDetails.UNKNOWN;
                }
                return new SigningDetails(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SigningDetails[] newArray(int i) {
                return new SigningDetails[i];
            }
        };

        public @interface CertCapabilities {
            public static final int AUTH = 16;
            public static final int INSTALLED_DATA = 1;
            public static final int PERMISSION = 4;
            public static final int ROLLBACK = 8;
            public static final int SHARED_USER_ID = 2;
        }

        public @interface SignatureSchemeVersion {
            public static final int JAR = 1;
            public static final int SIGNING_BLOCK_V2 = 2;
            public static final int SIGNING_BLOCK_V3 = 3;
            public static final int SIGNING_BLOCK_V4 = 4;
            public static final int UNKNOWN = 0;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SigningDetails(Signature[] signatureArr, int i, ArraySet<PublicKey> arraySet, Signature[] signatureArr2) {
            this.signatures = signatureArr;
            this.signatureSchemeVersion = i;
            this.publicKeys = arraySet;
            this.pastSigningCertificates = signatureArr2;
        }

        public SigningDetails(Signature[] signatureArr, int i, Signature[] signatureArr2) throws CertificateException {
            this(signatureArr, i, PackageParser.toSigningKeys(signatureArr), signatureArr2);
        }

        public SigningDetails(Signature[] signatureArr, int i) throws CertificateException {
            this(signatureArr, i, null);
        }

        public SigningDetails(SigningDetails signingDetails) {
            if (signingDetails != null) {
                Signature[] signatureArr = signingDetails.signatures;
                if (signatureArr != null) {
                    this.signatures = (Signature[]) signatureArr.clone();
                } else {
                    this.signatures = null;
                }
                this.signatureSchemeVersion = signingDetails.signatureSchemeVersion;
                this.publicKeys = new ArraySet<>((ArraySet) signingDetails.publicKeys);
                Signature[] signatureArr2 = signingDetails.pastSigningCertificates;
                if (signatureArr2 != null) {
                    this.pastSigningCertificates = (Signature[]) signatureArr2.clone();
                    return;
                } else {
                    this.pastSigningCertificates = null;
                    return;
                }
            }
            this.signatures = null;
            this.signatureSchemeVersion = 0;
            this.publicKeys = null;
            this.pastSigningCertificates = null;
        }

        public SigningDetails mergeLineageWith(SigningDetails signingDetails) {
            SigningDetails descendantOrSelf;
            if (!hasPastSigningCertificates()) {
                return (signingDetails.hasPastSigningCertificates() && signingDetails.hasAncestorOrSelf(this)) ? signingDetails : this;
            }
            if (!signingDetails.hasPastSigningCertificates() || (descendantOrSelf = getDescendantOrSelf(signingDetails)) == null) {
                return this;
            }
            if (descendantOrSelf == this) {
                return mergeLineageWithAncestorOrSelf(signingDetails);
            }
            return signingDetails.mergeLineageWithAncestorOrSelf(this);
        }

        private SigningDetails mergeLineageWithAncestorOrSelf(SigningDetails signingDetails) {
            int i;
            int i2;
            int length = this.pastSigningCertificates.length - 1;
            int length2 = signingDetails.pastSigningCertificates.length - 1;
            if (length >= 0 && length2 >= 0) {
                ArrayList arrayList = new ArrayList();
                while (length >= 0 && !this.pastSigningCertificates[length].equals(signingDetails.pastSigningCertificates[length2])) {
                    arrayList.add(new Signature(this.pastSigningCertificates[length]));
                    length--;
                }
                if (length < 0) {
                    return this;
                }
                boolean z = false;
                while (true) {
                    i = length - 1;
                    Signature signature = this.pastSigningCertificates[length];
                    i2 = length2 - 1;
                    Signature signature2 = signingDetails.pastSigningCertificates[length2];
                    Signature signature3 = new Signature(signature);
                    int flags = signature2.getFlags() & signature.getFlags();
                    if (signature.getFlags() != flags) {
                        signature3.setFlags(flags);
                        z = true;
                    }
                    arrayList.add(signature3);
                    if (i < 0 || i2 < 0 || !this.pastSigningCertificates[i].equals(signingDetails.pastSigningCertificates[i2])) {
                        break;
                    }
                    length = i;
                    length2 = i2;
                }
                if (i >= 0 && i2 >= 0) {
                    return this;
                }
                while (i2 >= 0) {
                    arrayList.add(new Signature(signingDetails.pastSigningCertificates[i2]));
                    i2--;
                }
                while (i >= 0) {
                    arrayList.add(new Signature(this.pastSigningCertificates[i]));
                    i--;
                }
                if (arrayList.size() == this.pastSigningCertificates.length && !z) {
                    return this;
                }
                Collections.reverse(arrayList);
                try {
                    return new SigningDetails(new Signature[]{new Signature(this.signatures[0])}, this.signatureSchemeVersion, (Signature[]) arrayList.toArray(new Signature[0]));
                } catch (CertificateException e) {
                    Slog.e(PackageParser.TAG, "Caught an exception creating the merged lineage: ", e);
                }
            }
            return this;
        }

        public boolean hasCommonAncestor(SigningDetails signingDetails) {
            if (!hasPastSigningCertificates()) {
                return signingDetails.hasAncestorOrSelf(this);
            }
            if (signingDetails.hasPastSigningCertificates()) {
                return getDescendantOrSelf(signingDetails) != null;
            }
            return hasAncestorOrSelf(signingDetails);
        }

        private SigningDetails getDescendantOrSelf(SigningDetails signingDetails) {
            if (!hasAncestorOrSelf(signingDetails)) {
                if (!signingDetails.hasAncestor(this)) {
                    return null;
                }
                signingDetails = this;
                this = signingDetails;
            }
            int length = this.pastSigningCertificates.length - 1;
            int length2 = signingDetails.pastSigningCertificates.length - 1;
            while (length >= 0 && !this.pastSigningCertificates[length].equals(signingDetails.pastSigningCertificates[length2])) {
                length--;
            }
            if (length < 0) {
                return null;
            }
            do {
                length--;
                length2--;
                if (length < 0 || length2 < 0) {
                    break;
                }
            } while (this.pastSigningCertificates[length].equals(signingDetails.pastSigningCertificates[length2]));
            if (length < 0 || length2 < 0) {
                return this;
            }
            return null;
        }

        public boolean hasSignatures() {
            Signature[] signatureArr = this.signatures;
            return signatureArr != null && signatureArr.length > 0;
        }

        public boolean hasPastSigningCertificates() {
            Signature[] signatureArr = this.pastSigningCertificates;
            return signatureArr != null && signatureArr.length > 0;
        }

        public boolean hasAncestorOrSelf(SigningDetails signingDetails) {
            SigningDetails signingDetails2 = UNKNOWN;
            if (this == signingDetails2 || signingDetails == signingDetails2) {
                return false;
            }
            Signature[] signatureArr = signingDetails.signatures;
            if (signatureArr.length > 1) {
                return signaturesMatchExactly(signingDetails);
            }
            return hasCertificate(signatureArr[0]);
        }

        public boolean hasAncestor(SigningDetails signingDetails) {
            SigningDetails signingDetails2 = UNKNOWN;
            if (this != signingDetails2 && signingDetails != signingDetails2 && hasPastSigningCertificates() && signingDetails.signatures.length == 1) {
                int i = 0;
                while (true) {
                    Signature[] signatureArr = this.pastSigningCertificates;
                    if (i >= signatureArr.length - 1) {
                        break;
                    }
                    if (signatureArr[i].equals(signingDetails.signatures[0])) {
                        return true;
                    }
                    i++;
                }
            }
            return false;
        }

        public boolean checkCapability(SigningDetails signingDetails, int i) {
            SigningDetails signingDetails2 = UNKNOWN;
            if (this == signingDetails2 || signingDetails == signingDetails2) {
                return false;
            }
            Signature[] signatureArr = signingDetails.signatures;
            if (signatureArr.length > 1) {
                return signaturesMatchExactly(signingDetails);
            }
            return hasCertificate(signatureArr[0], i);
        }

        public boolean checkCapabilityRecover(SigningDetails signingDetails, int i) throws CertificateException {
            SigningDetails signingDetails2 = UNKNOWN;
            if (signingDetails == signingDetails2 || this == signingDetails2) {
                return false;
            }
            if (!hasPastSigningCertificates() || signingDetails.signatures.length != 1) {
                return Signature.areEffectiveMatch(signingDetails.signatures, this.signatures);
            }
            int i2 = 0;
            while (true) {
                Signature[] signatureArr = this.pastSigningCertificates;
                if (i2 >= signatureArr.length) {
                    return false;
                }
                if (Signature.areEffectiveMatch(signingDetails.signatures[0], signatureArr[i2]) && this.pastSigningCertificates[i2].getFlags() == i) {
                    return true;
                }
                i2++;
            }
        }

        public boolean hasCertificate(Signature signature) {
            return hasCertificateInternal(signature, 0);
        }

        public boolean hasCertificate(Signature signature, int i) {
            return hasCertificateInternal(signature, i);
        }

        public boolean hasCertificate(byte[] bArr) {
            return hasCertificate(new Signature(bArr));
        }

        private boolean hasCertificateInternal(Signature signature, int i) {
            if (this == UNKNOWN) {
                return false;
            }
            if (hasPastSigningCertificates()) {
                int i2 = 0;
                while (true) {
                    Signature[] signatureArr = this.pastSigningCertificates;
                    if (i2 >= signatureArr.length - 1) {
                        break;
                    }
                    if (signatureArr[i2].equals(signature) && (i == 0 || (this.pastSigningCertificates[i2].getFlags() & i) == i)) {
                        break;
                    }
                    i2++;
                }
                return true;
            }
            Signature[] signatureArr2 = this.signatures;
            return signatureArr2.length == 1 && signatureArr2[0].equals(signature);
        }

        public boolean checkCapability(String str, int i) {
            if (this == UNKNOWN) {
                return false;
            }
            if (hasSha256Certificate(str == null ? null : HexEncoding.decode(str, false), i)) {
                return true;
            }
            return PackageUtils.computeSignaturesSha256Digest(PackageUtils.computeSignaturesSha256Digests(this.signatures)).equals(str);
        }

        public boolean hasSha256Certificate(byte[] bArr) {
            return hasSha256CertificateInternal(bArr, 0);
        }

        public boolean hasSha256Certificate(byte[] bArr, int i) {
            return hasSha256CertificateInternal(bArr, i);
        }

        private boolean hasSha256CertificateInternal(byte[] bArr, int i) {
            if (this == UNKNOWN) {
                return false;
            }
            if (hasPastSigningCertificates()) {
                int i2 = 0;
                while (true) {
                    Signature[] signatureArr = this.pastSigningCertificates;
                    if (i2 >= signatureArr.length - 1) {
                        break;
                    }
                    if (Arrays.equals(bArr, PackageUtils.computeSha256DigestBytes(signatureArr[i2].toByteArray())) && (i == 0 || (this.pastSigningCertificates[i2].getFlags() & i) == i)) {
                        break;
                    }
                    i2++;
                }
                return true;
            }
            Signature[] signatureArr2 = this.signatures;
            if (signatureArr2.length == 1) {
                return Arrays.equals(bArr, PackageUtils.computeSha256DigestBytes(signatureArr2[0].toByteArray()));
            }
            return false;
        }

        public boolean signaturesMatchExactly(SigningDetails signingDetails) {
            return Signature.areExactMatch(this.signatures, signingDetails.signatures);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            boolean z = UNKNOWN == this;
            parcel.writeBoolean(z);
            if (z) {
                return;
            }
            parcel.writeTypedArray(this.signatures, i);
            parcel.writeInt(this.signatureSchemeVersion);
            parcel.writeArraySet(this.publicKeys);
            parcel.writeTypedArray(this.pastSigningCertificates, i);
        }

        protected SigningDetails(Parcel parcel) {
            ClassLoader classLoader = Object.class.getClassLoader();
            this.signatures = (Signature[]) parcel.createTypedArray(Signature.CREATOR);
            this.signatureSchemeVersion = parcel.readInt();
            this.publicKeys = parcel.readArraySet(classLoader);
            this.pastSigningCertificates = (Signature[]) parcel.createTypedArray(Signature.CREATOR);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SigningDetails)) {
                return false;
            }
            SigningDetails signingDetails = (SigningDetails) obj;
            if (this.signatureSchemeVersion != signingDetails.signatureSchemeVersion || !Signature.areExactMatch(this.signatures, signingDetails.signatures)) {
                return false;
            }
            ArraySet<PublicKey> arraySet = this.publicKeys;
            if (arraySet != null) {
                if (!arraySet.equals(signingDetails.publicKeys)) {
                    return false;
                }
            } else if (signingDetails.publicKeys != null) {
                return false;
            }
            if (!Arrays.equals(this.pastSigningCertificates, signingDetails.pastSigningCertificates)) {
                return false;
            }
            int i = 0;
            while (true) {
                Signature[] signatureArr = this.pastSigningCertificates;
                if (i >= signatureArr.length) {
                    return true;
                }
                if (signatureArr[i].getFlags() != signingDetails.pastSigningCertificates[i].getFlags()) {
                    return false;
                }
                i++;
            }
        }

        public int hashCode() {
            int iHashCode = ((Arrays.hashCode(this.signatures) * 31) + this.signatureSchemeVersion) * 31;
            ArraySet<PublicKey> arraySet = this.publicKeys;
            return ((iHashCode + (arraySet != null ? arraySet.hashCode() : 0)) * 31) + Arrays.hashCode(this.pastSigningCertificates);
        }

        public static class Builder {
            private Signature[] mPastSigningCertificates;
            private int mSignatureSchemeVersion = 0;
            private Signature[] mSignatures;

            public Builder setSignatures(Signature[] signatureArr) {
                this.mSignatures = signatureArr;
                return this;
            }

            public Builder setSignatureSchemeVersion(int i) {
                this.mSignatureSchemeVersion = i;
                return this;
            }

            public Builder setPastSigningCertificates(Signature[] signatureArr) {
                this.mPastSigningCertificates = signatureArr;
                return this;
            }

            private void checkInvariants() {
                if (this.mSignatures == null) {
                    throw new IllegalStateException("SigningDetails requires the current signing certificates.");
                }
            }

            public SigningDetails build() throws CertificateException {
                checkInvariants();
                return new SigningDetails(this.mSignatures, this.mSignatureSchemeVersion, this.mPastSigningCertificates);
            }
        }
    }

    public static final class Package implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Package>() { // from class: android.content.pm.PackageParser.Package.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Package createFromParcel(Parcel parcel) {
                return new Package(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Package[] newArray(int i) {
                return new Package[i];
            }
        };
        public final ArrayList<Activity> activities;
        public ApplicationInfo applicationInfo;
        public String baseCodePath;
        public boolean baseHardwareAccelerated;
        public int baseRevisionCode;
        public ArrayList<Package> childPackages;
        public String codePath;
        public ArrayList<ConfigurationInfo> configPreferences;
        public boolean coreApp;
        public String cpuAbiOverride;
        public ArrayList<FeatureGroupInfo> featureGroups;
        public final ArrayList<String> implicitPermissions;
        public int installLocation;
        public final ArrayList<Instrumentation> instrumentation;
        public boolean isStub;
        public ArrayList<String> libraryNames;
        public ArrayList<String> mAdoptPermissions;
        public Bundle mAppMetaData;
        public int mCompileSdkVersion;
        public String mCompileSdkVersionCodename;
        public Object mExtras;
        public ArrayMap<String, ArraySet<PublicKey>> mKeySetMapping;
        public long[] mLastPackageUsageTimeInMills;
        public ArrayList<String> mOriginalPackages;
        public String mOverlayCategory;
        public boolean mOverlayIsStatic;
        public int mOverlayPriority;
        public String mOverlayTarget;
        public String mOverlayTargetName;
        public int mPreferredOrder;
        public String mRealPackage;
        public String mRequiredAccountType;
        public boolean mRequiredForAllUsers;
        public String mRestrictedAccountType;
        public String mSharedUserId;
        public int mSharedUserLabel;
        public SigningDetails mSigningDetails;
        public ArraySet<String> mUpgradeKeySets;
        public int mVersionCode;
        public int mVersionCodeMajor;
        public String mVersionName;
        public String manifestPackageName;
        public String packageName;
        public Package parentPackage;
        public final ArrayList<PermissionGroup> permissionGroups;
        public final ArrayList<Permission> permissions;
        public ArrayList<ActivityIntentInfo> preferredActivityFilters;
        public ArrayList<String> protectedBroadcasts;
        public final ArrayList<Provider> providers;
        public final ArrayList<Activity> receivers;
        public ArrayList<FeatureInfo> reqFeatures;
        public final ArrayList<String> requestedPermissions;
        public byte[] restrictUpdateHash;
        public final ArrayList<Service> services;
        public String[] splitCodePaths;
        public int[] splitFlags;
        public String[] splitNames;
        public int[] splitPrivateFlags;
        public int[] splitRevisionCodes;
        public String staticSharedLibName;
        public long staticSharedLibVersion;
        public boolean use32bitAbi;
        public ArrayList<String> usesLibraries;
        public String[] usesLibraryFiles;
        public ArrayList<SharedLibraryInfo> usesLibraryInfos;
        public ArrayList<String> usesOptionalLibraries;
        public ArrayList<String> usesStaticLibraries;
        public String[][] usesStaticLibrariesCertDigests;
        public long[] usesStaticLibrariesVersions;
        public boolean visibleToInstantApps;
        public String volumeUuid;

        public boolean canHaveOatDir() {
            return true;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean isForwardLocked() {
            return false;
        }

        public long getLongVersionCode() {
            return PackageInfo.composeLongVersionCode(this.mVersionCodeMajor, this.mVersionCode);
        }

        public Package(String str) {
            this.applicationInfo = new ApplicationInfo();
            this.permissions = new ArrayList<>(0);
            this.permissionGroups = new ArrayList<>(0);
            this.activities = new ArrayList<>(0);
            this.receivers = new ArrayList<>(0);
            this.providers = new ArrayList<>(0);
            this.services = new ArrayList<>(0);
            this.instrumentation = new ArrayList<>(0);
            this.requestedPermissions = new ArrayList<>();
            this.implicitPermissions = new ArrayList<>();
            this.staticSharedLibName = null;
            this.staticSharedLibVersion = 0L;
            this.libraryNames = null;
            this.usesLibraries = null;
            this.usesStaticLibraries = null;
            this.usesStaticLibrariesVersions = null;
            this.usesStaticLibrariesCertDigests = null;
            this.usesOptionalLibraries = null;
            this.usesLibraryFiles = null;
            this.usesLibraryInfos = null;
            this.preferredActivityFilters = null;
            this.mOriginalPackages = null;
            this.mRealPackage = null;
            this.mAdoptPermissions = null;
            this.mAppMetaData = null;
            this.mSigningDetails = SigningDetails.UNKNOWN;
            this.mPreferredOrder = 0;
            this.mLastPackageUsageTimeInMills = new long[8];
            this.configPreferences = null;
            this.reqFeatures = null;
            this.featureGroups = null;
            this.packageName = str;
            this.manifestPackageName = str;
            this.applicationInfo.packageName = str;
            this.applicationInfo.uid = -1;
        }

        public void setApplicationVolumeUuid(String str) {
            UUID uuidConvert = StorageManager.convert(str);
            this.applicationInfo.volumeUuid = str;
            this.applicationInfo.storageUuid = uuidConvert;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).applicationInfo.volumeUuid = str;
                    this.childPackages.get(i).applicationInfo.storageUuid = uuidConvert;
                }
            }
        }

        public void setApplicationInfoCodePath(String str) {
            this.applicationInfo.setCodePath(str);
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).applicationInfo.setCodePath(str);
                }
            }
        }

        @Deprecated
        public void setApplicationInfoResourcePath(String str) {
            this.applicationInfo.setResourcePath(str);
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).applicationInfo.setResourcePath(str);
                }
            }
        }

        @Deprecated
        public void setApplicationInfoBaseResourcePath(String str) {
            this.applicationInfo.setBaseResourcePath(str);
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).applicationInfo.setBaseResourcePath(str);
                }
            }
        }

        public void setApplicationInfoBaseCodePath(String str) {
            this.applicationInfo.setBaseCodePath(str);
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).applicationInfo.setBaseCodePath(str);
                }
            }
        }

        public List<String> getChildPackageNames() {
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList == null) {
                return null;
            }
            int size = arrayList.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList2.add(this.childPackages.get(i).packageName);
            }
            return arrayList2;
        }

        public boolean hasChildPackage(String str) {
            ArrayList<Package> arrayList = this.childPackages;
            int size = arrayList != null ? arrayList.size() : 0;
            for (int i = 0; i < size; i++) {
                if (this.childPackages.get(i).packageName.equals(str)) {
                    return true;
                }
            }
            return false;
        }

        public void setApplicationInfoSplitCodePaths(String[] strArr) {
            this.applicationInfo.setSplitCodePaths(strArr);
        }

        @Deprecated
        public void setApplicationInfoSplitResourcePaths(String[] strArr) {
            this.applicationInfo.setSplitResourcePaths(strArr);
        }

        public void setSplitCodePaths(String[] strArr) {
            this.splitCodePaths = strArr;
        }

        public void setCodePath(String str) {
            this.codePath = str;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).codePath = str;
                }
            }
        }

        public void setBaseCodePath(String str) {
            this.baseCodePath = str;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).baseCodePath = str;
                }
            }
        }

        public void setSigningDetails(SigningDetails signingDetails) {
            this.mSigningDetails = signingDetails;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).mSigningDetails = signingDetails;
                }
            }
        }

        public void setVolumeUuid(String str) {
            this.volumeUuid = str;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).volumeUuid = str;
                }
            }
        }

        public void setApplicationInfoFlags(int i, int i2) {
            ApplicationInfo applicationInfo = this.applicationInfo;
            int i3 = applicationInfo.flags;
            int i4 = ~i;
            int i5 = i & i2;
            applicationInfo.flags = (i3 & i4) | i5;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i6 = 0; i6 < size; i6++) {
                    this.childPackages.get(i6).applicationInfo.flags = (this.applicationInfo.flags & i4) | i5;
                }
            }
        }

        public void setUse32bitAbi(boolean z) {
            this.use32bitAbi = z;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    this.childPackages.get(i).use32bitAbi = z;
                }
            }
        }

        public boolean isLibrary() {
            return (this.staticSharedLibName == null && ArrayUtils.isEmpty(this.libraryNames)) ? false : true;
        }

        public List<String> getAllCodePaths() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.baseCodePath);
            if (!ArrayUtils.isEmpty(this.splitCodePaths)) {
                Collections.addAll(arrayList, this.splitCodePaths);
            }
            return arrayList;
        }

        public List<String> getAllCodePathsExcludingResourceOnly() {
            ArrayList arrayList = new ArrayList();
            if ((this.applicationInfo.flags & 4) != 0) {
                arrayList.add(this.baseCodePath);
            }
            if (!ArrayUtils.isEmpty(this.splitCodePaths)) {
                int i = 0;
                while (true) {
                    String[] strArr = this.splitCodePaths;
                    if (i >= strArr.length) {
                        break;
                    }
                    if ((this.splitFlags[i] & 4) != 0) {
                        arrayList.add(strArr[i]);
                    }
                    i++;
                }
            }
            return arrayList;
        }

        public void setPackageName(String str) {
            this.packageName = str;
            this.applicationInfo.packageName = str;
            for (int size = this.permissions.size() - 1; size >= 0; size--) {
                this.permissions.get(size).setPackageName(str);
            }
            for (int size2 = this.permissionGroups.size() - 1; size2 >= 0; size2--) {
                this.permissionGroups.get(size2).setPackageName(str);
            }
            for (int size3 = this.activities.size() - 1; size3 >= 0; size3--) {
                this.activities.get(size3).setPackageName(str);
            }
            for (int size4 = this.receivers.size() - 1; size4 >= 0; size4--) {
                this.receivers.get(size4).setPackageName(str);
            }
            for (int size5 = this.providers.size() - 1; size5 >= 0; size5--) {
                this.providers.get(size5).setPackageName(str);
            }
            for (int size6 = this.services.size() - 1; size6 >= 0; size6--) {
                this.services.get(size6).setPackageName(str);
            }
            for (int size7 = this.instrumentation.size() - 1; size7 >= 0; size7--) {
                this.instrumentation.get(size7).setPackageName(str);
            }
        }

        public boolean hasComponentClassName(String str) {
            for (int size = this.activities.size() - 1; size >= 0; size--) {
                if (str.equals(this.activities.get(size).className)) {
                    return true;
                }
            }
            for (int size2 = this.receivers.size() - 1; size2 >= 0; size2--) {
                if (str.equals(this.receivers.get(size2).className)) {
                    return true;
                }
            }
            for (int size3 = this.providers.size() - 1; size3 >= 0; size3--) {
                if (str.equals(this.providers.get(size3).className)) {
                    return true;
                }
            }
            for (int size4 = this.services.size() - 1; size4 >= 0; size4--) {
                if (str.equals(this.services.get(size4).className)) {
                    return true;
                }
            }
            for (int size5 = this.instrumentation.size() - 1; size5 >= 0; size5--) {
                if (str.equals(this.instrumentation.get(size5).className)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isExternal() {
            return this.applicationInfo.isExternal();
        }

        public boolean isOem() {
            return this.applicationInfo.isOem();
        }

        public boolean isVendor() {
            return this.applicationInfo.isVendor();
        }

        public boolean isProduct() {
            return this.applicationInfo.isProduct();
        }

        public boolean isSystemExt() {
            return this.applicationInfo.isSystemExt();
        }

        public boolean isOdm() {
            return this.applicationInfo.isOdm();
        }

        public boolean isPrivileged() {
            return this.applicationInfo.isPrivilegedApp();
        }

        public boolean isSystem() {
            return this.applicationInfo.isSystemApp();
        }

        public boolean isUpdatedSystemApp() {
            return this.applicationInfo.isUpdatedSystemApp();
        }

        public boolean isMatch(int i) {
            if ((i & 1048576) != 0) {
                return isSystem();
            }
            return true;
        }

        public long getLatestPackageUseTimeInMills() {
            long jMax = 0;
            for (long j : this.mLastPackageUsageTimeInMills) {
                jMax = Math.max(jMax, j);
            }
            return jMax;
        }

        public long getLatestForegroundPackageUseTimeInMills() {
            int[] iArr = {0, 2};
            long jMax = 0;
            for (int i = 0; i < 2; i++) {
                jMax = Math.max(jMax, this.mLastPackageUsageTimeInMills[iArr[i]]);
            }
            return jMax;
        }

        public String toString() {
            return "Package{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.packageName + "}";
        }

        public Package(Parcel parcel) {
            this.applicationInfo = new ApplicationInfo();
            ArrayList<Permission> arrayList = new ArrayList<>(0);
            this.permissions = arrayList;
            ArrayList<PermissionGroup> arrayList2 = new ArrayList<>(0);
            this.permissionGroups = arrayList2;
            ArrayList<Activity> arrayList3 = new ArrayList<>(0);
            this.activities = arrayList3;
            ArrayList<Activity> arrayList4 = new ArrayList<>(0);
            this.receivers = arrayList4;
            ArrayList<Provider> arrayList5 = new ArrayList<>(0);
            this.providers = arrayList5;
            ArrayList<Service> arrayList6 = new ArrayList<>(0);
            this.services = arrayList6;
            ArrayList<Instrumentation> arrayList7 = new ArrayList<>(0);
            this.instrumentation = arrayList7;
            ArrayList<String> arrayList8 = new ArrayList<>();
            this.requestedPermissions = arrayList8;
            ArrayList<String> arrayList9 = new ArrayList<>();
            this.implicitPermissions = arrayList9;
            this.staticSharedLibName = null;
            this.staticSharedLibVersion = 0L;
            this.libraryNames = null;
            this.usesLibraries = null;
            this.usesStaticLibraries = null;
            this.usesStaticLibrariesVersions = null;
            this.usesStaticLibrariesCertDigests = null;
            this.usesOptionalLibraries = null;
            this.usesLibraryFiles = null;
            this.usesLibraryInfos = null;
            this.preferredActivityFilters = null;
            this.mOriginalPackages = null;
            this.mRealPackage = null;
            this.mAdoptPermissions = null;
            this.mAppMetaData = null;
            this.mSigningDetails = SigningDetails.UNKNOWN;
            this.mPreferredOrder = 0;
            this.mLastPackageUsageTimeInMills = new long[8];
            this.configPreferences = null;
            this.reqFeatures = null;
            this.featureGroups = null;
            ClassLoader classLoader = Object.class.getClassLoader();
            this.packageName = parcel.readString().intern();
            this.manifestPackageName = parcel.readString();
            this.splitNames = parcel.readStringArray();
            this.volumeUuid = parcel.readString();
            this.codePath = parcel.readString();
            this.baseCodePath = parcel.readString();
            this.splitCodePaths = parcel.readStringArray();
            this.baseRevisionCode = parcel.readInt();
            this.splitRevisionCodes = parcel.createIntArray();
            this.splitFlags = parcel.createIntArray();
            this.splitPrivateFlags = parcel.createIntArray();
            this.baseHardwareAccelerated = parcel.readInt() == 1;
            ApplicationInfo applicationInfo = (ApplicationInfo) parcel.readParcelable(classLoader);
            this.applicationInfo = applicationInfo;
            if (applicationInfo.permission != null) {
                ApplicationInfo applicationInfo2 = this.applicationInfo;
                applicationInfo2.permission = applicationInfo2.permission.intern();
            }
            parcel.readParcelableList(arrayList, classLoader);
            fixupOwner(arrayList);
            parcel.readParcelableList(arrayList2, classLoader);
            fixupOwner(arrayList2);
            parcel.readParcelableList(arrayList3, classLoader);
            fixupOwner(arrayList3);
            parcel.readParcelableList(arrayList4, classLoader);
            fixupOwner(arrayList4);
            parcel.readParcelableList(arrayList5, classLoader);
            fixupOwner(arrayList5);
            parcel.readParcelableList(arrayList6, classLoader);
            fixupOwner(arrayList6);
            parcel.readParcelableList(arrayList7, classLoader);
            fixupOwner(arrayList7);
            parcel.readStringList(arrayList8);
            internStringArrayList(arrayList8);
            parcel.readStringList(arrayList9);
            internStringArrayList(arrayList9);
            ArrayList<String> arrayListCreateStringArrayList = parcel.createStringArrayList();
            this.protectedBroadcasts = arrayListCreateStringArrayList;
            internStringArrayList(arrayListCreateStringArrayList);
            this.parentPackage = (Package) parcel.readParcelable(classLoader);
            ArrayList<Package> arrayList10 = new ArrayList<>();
            this.childPackages = arrayList10;
            parcel.readParcelableList(arrayList10, classLoader);
            if (this.childPackages.size() == 0) {
                this.childPackages = null;
            }
            String string = parcel.readString();
            this.staticSharedLibName = string;
            if (string != null) {
                this.staticSharedLibName = string.intern();
            }
            this.staticSharedLibVersion = parcel.readLong();
            ArrayList<String> arrayListCreateStringArrayList2 = parcel.createStringArrayList();
            this.libraryNames = arrayListCreateStringArrayList2;
            internStringArrayList(arrayListCreateStringArrayList2);
            ArrayList<String> arrayListCreateStringArrayList3 = parcel.createStringArrayList();
            this.usesLibraries = arrayListCreateStringArrayList3;
            internStringArrayList(arrayListCreateStringArrayList3);
            ArrayList<String> arrayListCreateStringArrayList4 = parcel.createStringArrayList();
            this.usesOptionalLibraries = arrayListCreateStringArrayList4;
            internStringArrayList(arrayListCreateStringArrayList4);
            this.usesLibraryFiles = parcel.readStringArray();
            this.usesLibraryInfos = parcel.createTypedArrayList(SharedLibraryInfo.CREATOR);
            int i = parcel.readInt();
            if (i > 0) {
                ArrayList<String> arrayList11 = new ArrayList<>(i);
                this.usesStaticLibraries = arrayList11;
                parcel.readStringList(arrayList11);
                internStringArrayList(this.usesStaticLibraries);
                long[] jArr = new long[i];
                this.usesStaticLibrariesVersions = jArr;
                parcel.readLongArray(jArr);
                this.usesStaticLibrariesCertDigests = new String[i][];
                for (int i2 = 0; i2 < i; i2++) {
                    this.usesStaticLibrariesCertDigests[i2] = parcel.createStringArray();
                }
            }
            ArrayList<ActivityIntentInfo> arrayList12 = new ArrayList<>();
            this.preferredActivityFilters = arrayList12;
            parcel.readParcelableList(arrayList12, classLoader);
            if (this.preferredActivityFilters.size() == 0) {
                this.preferredActivityFilters = null;
            }
            this.mOriginalPackages = parcel.createStringArrayList();
            this.mRealPackage = parcel.readString();
            this.mAdoptPermissions = parcel.createStringArrayList();
            this.mAppMetaData = parcel.readBundle();
            this.mVersionCode = parcel.readInt();
            this.mVersionCodeMajor = parcel.readInt();
            String string2 = parcel.readString();
            this.mVersionName = string2;
            if (string2 != null) {
                this.mVersionName = string2.intern();
            }
            String string3 = parcel.readString();
            this.mSharedUserId = string3;
            if (string3 != null) {
                this.mSharedUserId = string3.intern();
            }
            this.mSharedUserLabel = parcel.readInt();
            this.mSigningDetails = (SigningDetails) parcel.readParcelable(classLoader);
            this.mPreferredOrder = parcel.readInt();
            ArrayList<ConfigurationInfo> arrayList13 = new ArrayList<>();
            this.configPreferences = arrayList13;
            parcel.readParcelableList(arrayList13, classLoader);
            if (this.configPreferences.size() == 0) {
                this.configPreferences = null;
            }
            ArrayList<FeatureInfo> arrayList14 = new ArrayList<>();
            this.reqFeatures = arrayList14;
            parcel.readParcelableList(arrayList14, classLoader);
            if (this.reqFeatures.size() == 0) {
                this.reqFeatures = null;
            }
            ArrayList<FeatureGroupInfo> arrayList15 = new ArrayList<>();
            this.featureGroups = arrayList15;
            parcel.readParcelableList(arrayList15, classLoader);
            if (this.featureGroups.size() == 0) {
                this.featureGroups = null;
            }
            this.installLocation = parcel.readInt();
            this.coreApp = parcel.readInt() == 1;
            this.mRequiredForAllUsers = parcel.readInt() == 1;
            this.mRestrictedAccountType = parcel.readString();
            this.mRequiredAccountType = parcel.readString();
            this.mOverlayTarget = parcel.readString();
            this.mOverlayTargetName = parcel.readString();
            this.mOverlayCategory = parcel.readString();
            this.mOverlayPriority = parcel.readInt();
            this.mOverlayIsStatic = parcel.readInt() == 1;
            this.mCompileSdkVersion = parcel.readInt();
            this.mCompileSdkVersionCodename = parcel.readString();
            this.mUpgradeKeySets = parcel.readArraySet(classLoader);
            this.mKeySetMapping = readKeySetMapping(parcel);
            this.cpuAbiOverride = parcel.readString();
            this.use32bitAbi = parcel.readInt() == 1;
            this.restrictUpdateHash = parcel.createByteArray();
            this.visibleToInstantApps = parcel.readInt() == 1;
        }

        private static void internStringArrayList(List<String> list) {
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.set(i, list.get(i).intern());
                }
            }
        }

        public void fixupOwner(List<? extends Component<?>> list) {
            if (list != null) {
                for (Component<?> component : list) {
                    component.owner = this;
                    if (component instanceof Activity) {
                        ((Activity) component).info.applicationInfo = this.applicationInfo;
                    } else if (component instanceof Service) {
                        ((Service) component).info.applicationInfo = this.applicationInfo;
                    } else if (component instanceof Provider) {
                        ((Provider) component).info.applicationInfo = this.applicationInfo;
                    }
                }
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.packageName);
            parcel.writeString(this.manifestPackageName);
            parcel.writeStringArray(this.splitNames);
            parcel.writeString(this.volumeUuid);
            parcel.writeString(this.codePath);
            parcel.writeString(this.baseCodePath);
            parcel.writeStringArray(this.splitCodePaths);
            parcel.writeInt(this.baseRevisionCode);
            parcel.writeIntArray(this.splitRevisionCodes);
            parcel.writeIntArray(this.splitFlags);
            parcel.writeIntArray(this.splitPrivateFlags);
            parcel.writeInt(this.baseHardwareAccelerated ? 1 : 0);
            parcel.writeParcelable(this.applicationInfo, i);
            parcel.writeParcelableList(this.permissions, i);
            parcel.writeParcelableList(this.permissionGroups, i);
            parcel.writeParcelableList(this.activities, i);
            parcel.writeParcelableList(this.receivers, i);
            parcel.writeParcelableList(this.providers, i);
            parcel.writeParcelableList(this.services, i);
            parcel.writeParcelableList(this.instrumentation, i);
            parcel.writeStringList(this.requestedPermissions);
            parcel.writeStringList(this.implicitPermissions);
            parcel.writeStringList(this.protectedBroadcasts);
            parcel.writeParcelable(this.parentPackage, i);
            parcel.writeParcelableList(this.childPackages, i);
            parcel.writeString(this.staticSharedLibName);
            parcel.writeLong(this.staticSharedLibVersion);
            parcel.writeStringList(this.libraryNames);
            parcel.writeStringList(this.usesLibraries);
            parcel.writeStringList(this.usesOptionalLibraries);
            parcel.writeStringArray(this.usesLibraryFiles);
            parcel.writeTypedList(this.usesLibraryInfos);
            if (ArrayUtils.isEmpty(this.usesStaticLibraries)) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(this.usesStaticLibraries.size());
                parcel.writeStringList(this.usesStaticLibraries);
                parcel.writeLongArray(this.usesStaticLibrariesVersions);
                for (String[] strArr : this.usesStaticLibrariesCertDigests) {
                    parcel.writeStringArray(strArr);
                }
            }
            parcel.writeParcelableList(this.preferredActivityFilters, i);
            parcel.writeStringList(this.mOriginalPackages);
            parcel.writeString(this.mRealPackage);
            parcel.writeStringList(this.mAdoptPermissions);
            parcel.writeBundle(this.mAppMetaData);
            parcel.writeInt(this.mVersionCode);
            parcel.writeInt(this.mVersionCodeMajor);
            parcel.writeString(this.mVersionName);
            parcel.writeString(this.mSharedUserId);
            parcel.writeInt(this.mSharedUserLabel);
            parcel.writeParcelable(this.mSigningDetails, i);
            parcel.writeInt(this.mPreferredOrder);
            parcel.writeParcelableList(this.configPreferences, i);
            parcel.writeParcelableList(this.reqFeatures, i);
            parcel.writeParcelableList(this.featureGroups, i);
            parcel.writeInt(this.installLocation);
            parcel.writeInt(this.coreApp ? 1 : 0);
            parcel.writeInt(this.mRequiredForAllUsers ? 1 : 0);
            parcel.writeString(this.mRestrictedAccountType);
            parcel.writeString(this.mRequiredAccountType);
            parcel.writeString(this.mOverlayTarget);
            parcel.writeString(this.mOverlayTargetName);
            parcel.writeString(this.mOverlayCategory);
            parcel.writeInt(this.mOverlayPriority);
            parcel.writeInt(this.mOverlayIsStatic ? 1 : 0);
            parcel.writeInt(this.mCompileSdkVersion);
            parcel.writeString(this.mCompileSdkVersionCodename);
            parcel.writeArraySet(this.mUpgradeKeySets);
            writeKeySetMapping(parcel, this.mKeySetMapping);
            parcel.writeString(this.cpuAbiOverride);
            parcel.writeInt(this.use32bitAbi ? 1 : 0);
            parcel.writeByteArray(this.restrictUpdateHash);
            parcel.writeInt(this.visibleToInstantApps ? 1 : 0);
        }

        private static void writeKeySetMapping(Parcel parcel, ArrayMap<String, ArraySet<PublicKey>> arrayMap) {
            if (arrayMap == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = arrayMap.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; i++) {
                parcel.writeString(arrayMap.keyAt(i));
                ArraySet<PublicKey> arraySetValueAt = arrayMap.valueAt(i);
                if (arraySetValueAt == null) {
                    parcel.writeInt(-1);
                } else {
                    int size2 = arraySetValueAt.size();
                    parcel.writeInt(size2);
                    for (int i2 = 0; i2 < size2; i2++) {
                        parcel.writeSerializable(arraySetValueAt.valueAt(i2));
                    }
                }
            }
        }

        private static ArrayMap<String, ArraySet<PublicKey>> readKeySetMapping(Parcel parcel) {
            int i = parcel.readInt();
            if (i == -1) {
                return null;
            }
            ArrayMap<String, ArraySet<PublicKey>> arrayMap = new ArrayMap<>();
            for (int i2 = 0; i2 < i; i2++) {
                String string = parcel.readString();
                int i3 = parcel.readInt();
                if (i3 == -1) {
                    arrayMap.put(string, null);
                } else {
                    ArraySet<PublicKey> arraySet = new ArraySet<>(i3);
                    for (int i4 = 0; i4 < i3; i4++) {
                        arraySet.add((PublicKey) parcel.readSerializable());
                    }
                    arrayMap.put(string, arraySet);
                }
            }
            return arrayMap;
        }
    }

    public static abstract class Component<II extends IntentInfo> {
        public final String className;
        ComponentName componentName;
        String componentShortName;
        public final ArrayList<II> intents;
        public Bundle metaData;
        public int order;
        public Package owner;

        public Component(Package r1, ArrayList<II> arrayList, String str) {
            this.owner = r1;
            this.intents = arrayList;
            this.className = str;
        }

        public Component(Package r1) {
            this.owner = r1;
            this.intents = null;
            this.className = null;
        }

        public Component(ParsePackageItemArgs parsePackageItemArgs, PackageItemInfo packageItemInfo) {
            this.owner = parsePackageItemArgs.owner;
            this.intents = new ArrayList<>(0);
            if (PackageParser.parsePackageItemInfo(parsePackageItemArgs.owner, packageItemInfo, parsePackageItemArgs.outError, parsePackageItemArgs.tag, parsePackageItemArgs.sa, true, parsePackageItemArgs.nameRes, parsePackageItemArgs.labelRes, parsePackageItemArgs.iconRes, parsePackageItemArgs.roundIconRes, parsePackageItemArgs.logoRes, parsePackageItemArgs.bannerRes)) {
                this.className = packageItemInfo.name;
            } else {
                this.className = null;
            }
        }

        public Component(ParseComponentArgs parseComponentArgs, ComponentInfo componentInfo) {
            String nonResourceString;
            this((ParsePackageItemArgs) parseComponentArgs, (PackageItemInfo) componentInfo);
            if (parseComponentArgs.outError[0] != null) {
                return;
            }
            if (parseComponentArgs.processRes != 0) {
                if (this.owner.applicationInfo.targetSdkVersion >= 8) {
                    nonResourceString = parseComponentArgs.sa.getNonConfigurationString(parseComponentArgs.processRes, 1024);
                } else {
                    nonResourceString = parseComponentArgs.sa.getNonResourceString(parseComponentArgs.processRes);
                }
                componentInfo.processName = PackageParser.buildProcessName(this.owner.applicationInfo.packageName, this.owner.applicationInfo.processName, nonResourceString, parseComponentArgs.flags, parseComponentArgs.sepProcesses, parseComponentArgs.outError);
            }
            if (parseComponentArgs.descriptionRes != 0) {
                componentInfo.descriptionRes = parseComponentArgs.sa.getResourceId(parseComponentArgs.descriptionRes, 0);
            }
            componentInfo.enabled = parseComponentArgs.sa.getBoolean(parseComponentArgs.enabledRes, true);
        }

        public Component(Component<II> component) {
            this.owner = component.owner;
            this.intents = component.intents;
            this.className = component.className;
            this.componentName = component.componentName;
            this.componentShortName = component.componentShortName;
        }

        public ComponentName getComponentName() {
            ComponentName componentName = this.componentName;
            if (componentName != null) {
                return componentName;
            }
            if (this.className != null) {
                this.componentName = new ComponentName(this.owner.applicationInfo.packageName, this.className);
            }
            return this.componentName;
        }

        protected Component(Parcel parcel) {
            this.className = parcel.readString();
            this.metaData = parcel.readBundle();
            this.intents = createIntentsList(parcel);
            this.owner = null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.className);
            parcel.writeBundle(this.metaData);
            writeIntentsList(this.intents, parcel, i);
        }

        private static void writeIntentsList(ArrayList<? extends IntentInfo> arrayList, Parcel parcel, int i) {
            if (arrayList == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = arrayList.size();
            parcel.writeInt(size);
            if (size > 0) {
                parcel.writeString(arrayList.get(0).getClass().getName());
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.get(i2).writeIntentInfoToParcel(parcel, i);
                }
            }
        }

        private static <T extends IntentInfo> ArrayList<T> createIntentsList(Parcel parcel) {
            int i = parcel.readInt();
            if (i == -1) {
                return null;
            }
            if (i == 0) {
                return new ArrayList<>(0);
            }
            String string = parcel.readString();
            try {
                Constructor<?> constructor = Class.forName(string).getConstructor(Parcel.class);
                ViewGroup.ChildListForAutoFillOrContentCapture childListForAutoFillOrContentCapture = (ArrayList<T>) new ArrayList(i);
                for (int i2 = 0; i2 < i; i2++) {
                    childListForAutoFillOrContentCapture.add((IntentInfo) constructor.newInstance(parcel));
                }
                return childListForAutoFillOrContentCapture;
            } catch (ReflectiveOperationException unused) {
                throw new AssertionError("Unable to construct intent list for: " + string);
            }
        }

        public void appendComponentShortName(StringBuilder sb) {
            ComponentName.appendShortString(sb, this.owner.applicationInfo.packageName, this.className);
        }

        public void printComponentShortName(PrintWriter printWriter) {
            ComponentName.printShortString(printWriter, this.owner.applicationInfo.packageName, this.className);
        }

        public void setPackageName(String str) {
            this.componentName = null;
            this.componentShortName = null;
        }
    }

    public static final class Permission extends Component<IntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Permission>() { // from class: android.content.pm.PackageParser.Permission.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Permission createFromParcel(Parcel parcel) {
                return new Permission(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Permission[] newArray(int i) {
                return new Permission[i];
            }
        };
        public PermissionGroup group;
        public final PermissionInfo info;
        public boolean tree;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Permission(Package r1, String str) {
            super(r1);
            this.info = new PermissionInfo(str);
        }

        public Permission(Package r1, PermissionInfo permissionInfo) {
            super(r1);
            this.info = permissionInfo;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String str) {
            super.setPackageName(str);
            this.info.packageName = str;
        }

        public String toString() {
            return "Permission{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.info.name + "}";
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.info, i);
            parcel.writeInt(this.tree ? 1 : 0);
            parcel.writeParcelable(this.group, i);
        }

        public boolean isAppOp() {
            return this.info.isAppOp();
        }

        private Permission(Parcel parcel) {
            super(parcel);
            ClassLoader classLoader = Object.class.getClassLoader();
            PermissionInfo permissionInfo = (PermissionInfo) parcel.readParcelable(classLoader);
            this.info = permissionInfo;
            if (permissionInfo.group != null) {
                permissionInfo.group = permissionInfo.group.intern();
            }
            this.tree = parcel.readInt() == 1;
            this.group = (PermissionGroup) parcel.readParcelable(classLoader);
        }
    }

    public static final class PermissionGroup extends Component<IntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<PermissionGroup>() { // from class: android.content.pm.PackageParser.PermissionGroup.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PermissionGroup createFromParcel(Parcel parcel) {
                return new PermissionGroup(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PermissionGroup[] newArray(int i) {
                return new PermissionGroup[i];
            }
        };
        public final PermissionGroupInfo info;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public PermissionGroup(Package r1, int i, int i2, int i3) {
            super(r1);
            this.info = new PermissionGroupInfo(i, i2, i3);
        }

        public PermissionGroup(Package r1, PermissionGroupInfo permissionGroupInfo) {
            super(r1);
            this.info = permissionGroupInfo;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String str) {
            super.setPackageName(str);
            this.info.packageName = str;
        }

        public String toString() {
            return "PermissionGroup{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.info.name + "}";
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.info, i);
        }

        private PermissionGroup(Parcel parcel) {
            super(parcel);
            this.info = (PermissionGroupInfo) parcel.readParcelable(Object.class.getClassLoader());
        }
    }

    private static boolean copyNeeded(int i, Package r4, PackageUserState packageUserState, Bundle bundle, int i2) {
        if (i2 != 0) {
            return true;
        }
        if (packageUserState.enabled != 0) {
            if (r4.applicationInfo.enabled != (packageUserState.enabled == 1)) {
                return true;
            }
        }
        if (packageUserState.suspended != ((r4.applicationInfo.flags & 1073741824) != 0) || !packageUserState.installed || packageUserState.hidden || packageUserState.stopped || packageUserState.instantApp != r4.applicationInfo.isInstantApp()) {
            return true;
        }
        if ((i & 128) != 0 && (bundle != null || r4.mAppMetaData != null)) {
            return true;
        }
        int i3 = i & 1024;
        if (i3 == 0 || r4.usesLibraryFiles == null) {
            return ((i3 == 0 || r4.usesLibraryInfos == null) && r4.staticSharedLibName == null) ? false : true;
        }
        return true;
    }

    public static ApplicationInfo generateApplicationInfo(Package r1, int i, PackageUserState packageUserState) {
        return generateApplicationInfo(r1, i, packageUserState, UserHandle.getCallingUserId());
    }

    private static void updateApplicationInfo(ApplicationInfo applicationInfo, int i, PackageUserState packageUserState) {
        if (!sCompatibilityModeEnabled) {
            applicationInfo.disableCompatibilityMode();
        }
        if (packageUserState.installed) {
            applicationInfo.flags |= 8388608;
        } else {
            applicationInfo.flags &= -8388609;
        }
        if (packageUserState.suspended) {
            applicationInfo.flags |= 1073741824;
        } else {
            applicationInfo.flags &= -1073741825;
        }
        if (packageUserState.instantApp) {
            applicationInfo.privateFlags |= 128;
        } else {
            applicationInfo.privateFlags &= -129;
        }
        if (packageUserState.virtualPreload) {
            applicationInfo.privateFlags |= 65536;
        } else {
            applicationInfo.privateFlags &= -65537;
        }
        if (packageUserState.hidden) {
            applicationInfo.privateFlags |= 1;
        } else {
            applicationInfo.privateFlags &= -2;
        }
        if (packageUserState.enabled == 1) {
            applicationInfo.enabled = true;
        } else if (packageUserState.enabled == 4) {
            applicationInfo.enabled = (i & 32768) != 0;
        } else if (packageUserState.enabled == 2 || packageUserState.enabled == 3) {
            applicationInfo.enabled = false;
        }
        applicationInfo.enabledSetting = packageUserState.enabled;
        if (applicationInfo.category == -1) {
            applicationInfo.category = packageUserState.categoryHint;
        }
        if (applicationInfo.category == -1) {
            applicationInfo.category = FallbackCategoryProvider.getFallbackCategory(applicationInfo.packageName);
        }
        applicationInfo.seInfoUser = SELinuxUtil.assignSeinfoUser(packageUserState);
        applicationInfo.resourceDirs = packageUserState.getAllOverlayPaths();
        applicationInfo.icon = (!sUseRoundIcon || applicationInfo.roundIconRes == 0) ? applicationInfo.iconRes : applicationInfo.roundIconRes;
    }

    public static ApplicationInfo generateApplicationInfo(Package r2, int i, PackageUserState packageUserState, int i2) {
        ApplicationInfo applicationInfo = null;
        if (r2 == null) {
            return null;
        }
        if (checkUseInstalledOrHidden(i, packageUserState, r2.applicationInfo) && r2.isMatch(i)) {
            if (!copyNeeded(i, r2, packageUserState, null, i2) && ((32768 & i) == 0 || packageUserState.enabled != 4)) {
                updateApplicationInfo(r2.applicationInfo, i, packageUserState);
                return r2.applicationInfo;
            }
            applicationInfo = new ApplicationInfo(r2.applicationInfo);
            applicationInfo.initForUser(i2);
            if ((i & 128) != 0) {
                applicationInfo.metaData = r2.mAppMetaData;
            }
            if ((i & 1024) != 0) {
                applicationInfo.sharedLibraryFiles = r2.usesLibraryFiles;
                applicationInfo.sharedLibraryInfos = r2.usesLibraryInfos;
            }
            if (packageUserState.stopped) {
                applicationInfo.flags |= 2097152;
            } else {
                applicationInfo.flags &= -2097153;
            }
            updateApplicationInfo(applicationInfo, i, packageUserState);
        }
        return applicationInfo;
    }

    public static ApplicationInfo generateApplicationInfo(ApplicationInfo applicationInfo, int i, PackageUserState packageUserState, int i2) {
        if (applicationInfo == null || !checkUseInstalledOrHidden(i, packageUserState, applicationInfo)) {
            return null;
        }
        ApplicationInfo applicationInfo2 = new ApplicationInfo(applicationInfo);
        applicationInfo2.initForUser(i2);
        if (packageUserState.stopped) {
            applicationInfo2.flags |= 2097152;
        } else {
            applicationInfo2.flags &= -2097153;
        }
        updateApplicationInfo(applicationInfo2, i, packageUserState);
        return applicationInfo2;
    }

    public static final PermissionInfo generatePermissionInfo(Permission permission, int i) {
        if (permission == null) {
            return null;
        }
        if ((i & 128) == 0) {
            return permission.info;
        }
        PermissionInfo permissionInfo = new PermissionInfo(permission.info);
        permissionInfo.metaData = permission.metaData;
        return permissionInfo;
    }

    public static final PermissionGroupInfo generatePermissionGroupInfo(PermissionGroup permissionGroup, int i) {
        if (permissionGroup == null) {
            return null;
        }
        if ((i & 128) == 0) {
            return permissionGroup.info;
        }
        PermissionGroupInfo permissionGroupInfo = new PermissionGroupInfo(permissionGroup.info);
        permissionGroupInfo.metaData = permissionGroup.metaData;
        return permissionGroupInfo;
    }

    public static final class Activity extends Component<ActivityIntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Activity>() { // from class: android.content.pm.PackageParser.Activity.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Activity createFromParcel(Parcel parcel) {
                return new Activity(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Activity[] newArray(int i) {
                return new Activity[i];
            }
        };
        public final ActivityInfo info;
        private boolean mHasMaxAspectRatio;
        private boolean mHasMinAspectRatio;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasMaxAspectRatio() {
            return this.mHasMaxAspectRatio;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasMinAspectRatio() {
            return this.mHasMinAspectRatio;
        }

        Activity(Package r3, String str, ActivityInfo activityInfo) {
            super(r3, new ArrayList(0), str);
            this.info = activityInfo;
            activityInfo.applicationInfo = r3.applicationInfo;
        }

        public Activity(ParseComponentArgs parseComponentArgs, ActivityInfo activityInfo) {
            super(parseComponentArgs, (ComponentInfo) activityInfo);
            this.info = activityInfo;
            activityInfo.applicationInfo = parseComponentArgs.owner.applicationInfo;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String str) {
            super.setPackageName(str);
            this.info.packageName = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMaxAspectRatio(float f) {
            if (this.info.resizeMode == 2 || this.info.resizeMode == 1) {
                return;
            }
            if (f >= 1.0f || f == 0.0f) {
                this.info.maxAspectRatio = f;
                this.mHasMaxAspectRatio = true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMinAspectRatio(float f) {
            if (this.info.resizeMode == 2 || this.info.resizeMode == 1) {
                return;
            }
            if (f >= 1.0f || f == 0.0f) {
                this.info.minAspectRatio = f;
                this.mHasMinAspectRatio = true;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Activity{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.info, i | 2);
            parcel.writeBoolean(this.mHasMaxAspectRatio);
            parcel.writeBoolean(this.mHasMinAspectRatio);
        }

        private Activity(Parcel parcel) {
            super(parcel);
            this.info = (ActivityInfo) parcel.readParcelable(Object.class.getClassLoader());
            this.mHasMaxAspectRatio = parcel.readBoolean();
            this.mHasMinAspectRatio = parcel.readBoolean();
            for (II ii : this.intents) {
                ii.activity = this;
                this.order = Math.max(ii.getOrder(), this.order);
            }
            if (this.info.permission != null) {
                ActivityInfo activityInfo = this.info;
                activityInfo.permission = activityInfo.permission.intern();
            }
        }
    }

    public static final ActivityInfo generateActivityInfo(Activity activity, int i, PackageUserState packageUserState, int i2) {
        if (activity == null || !checkUseInstalledOrHidden(i, packageUserState, activity.owner.applicationInfo)) {
            return null;
        }
        if (!copyNeeded(i, activity.owner, packageUserState, activity.metaData, i2)) {
            updateApplicationInfo(activity.info.applicationInfo, i, packageUserState);
            return activity.info;
        }
        ActivityInfo activityInfo = new ActivityInfo(activity.info);
        activityInfo.metaData = activity.metaData;
        activityInfo.applicationInfo = generateApplicationInfo(activity.owner, i, packageUserState, i2);
        return activityInfo;
    }

    public static final ActivityInfo generateActivityInfo(ActivityInfo activityInfo, int i, PackageUserState packageUserState, int i2) {
        if (activityInfo == null || !checkUseInstalledOrHidden(i, packageUserState, activityInfo.applicationInfo)) {
            return null;
        }
        ActivityInfo activityInfo2 = new ActivityInfo(activityInfo);
        activityInfo2.applicationInfo = generateApplicationInfo(activityInfo2.applicationInfo, i, packageUserState, i2);
        return activityInfo2;
    }

    public static final class Service extends Component<ServiceIntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Service>() { // from class: android.content.pm.PackageParser.Service.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Service createFromParcel(Parcel parcel) {
                return new Service(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Service[] newArray(int i) {
                return new Service[i];
            }
        };
        public final ServiceInfo info;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Service(ParseComponentArgs parseComponentArgs, ServiceInfo serviceInfo) {
            super(parseComponentArgs, (ComponentInfo) serviceInfo);
            this.info = serviceInfo;
            serviceInfo.applicationInfo = parseComponentArgs.owner.applicationInfo;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String str) {
            super.setPackageName(str);
            this.info.packageName = str;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Service{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.info, i | 2);
        }

        private Service(Parcel parcel) {
            super(parcel);
            this.info = (ServiceInfo) parcel.readParcelable(Object.class.getClassLoader());
            for (II ii : this.intents) {
                ii.service = this;
                this.order = Math.max(ii.getOrder(), this.order);
            }
            if (this.info.permission != null) {
                ServiceInfo serviceInfo = this.info;
                serviceInfo.permission = serviceInfo.permission.intern();
            }
        }
    }

    public static final ServiceInfo generateServiceInfo(Service service, int i, PackageUserState packageUserState, int i2) {
        if (service == null || !checkUseInstalledOrHidden(i, packageUserState, service.owner.applicationInfo)) {
            return null;
        }
        if (!copyNeeded(i, service.owner, packageUserState, service.metaData, i2)) {
            updateApplicationInfo(service.info.applicationInfo, i, packageUserState);
            return service.info;
        }
        ServiceInfo serviceInfo = new ServiceInfo(service.info);
        serviceInfo.metaData = service.metaData;
        serviceInfo.applicationInfo = generateApplicationInfo(service.owner, i, packageUserState, i2);
        return serviceInfo;
    }

    public static final class Provider extends Component<ProviderIntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Provider>() { // from class: android.content.pm.PackageParser.Provider.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Provider createFromParcel(Parcel parcel) {
                return new Provider(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Provider[] newArray(int i) {
                return new Provider[i];
            }
        };
        public final ProviderInfo info;
        public boolean syncable;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Provider(ParseComponentArgs parseComponentArgs, ProviderInfo providerInfo) {
            super(parseComponentArgs, (ComponentInfo) providerInfo);
            this.info = providerInfo;
            providerInfo.applicationInfo = parseComponentArgs.owner.applicationInfo;
            this.syncable = false;
        }

        public Provider(Provider provider) {
            super(provider);
            this.info = provider.info;
            this.syncable = provider.syncable;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String str) {
            super.setPackageName(str);
            this.info.packageName = str;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Provider{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.info, i | 2);
            parcel.writeInt(this.syncable ? 1 : 0);
        }

        private Provider(Parcel parcel) {
            super(parcel);
            this.info = (ProviderInfo) parcel.readParcelable(Object.class.getClassLoader());
            this.syncable = parcel.readInt() == 1;
            Iterator it = this.intents.iterator();
            while (it.hasNext()) {
                ((ProviderIntentInfo) it.next()).provider = this;
            }
            if (this.info.readPermission != null) {
                ProviderInfo providerInfo = this.info;
                providerInfo.readPermission = providerInfo.readPermission.intern();
            }
            if (this.info.writePermission != null) {
                ProviderInfo providerInfo2 = this.info;
                providerInfo2.writePermission = providerInfo2.writePermission.intern();
            }
            if (this.info.authority != null) {
                ProviderInfo providerInfo3 = this.info;
                providerInfo3.authority = providerInfo3.authority.intern();
            }
        }
    }

    public static final ProviderInfo generateProviderInfo(Provider provider, int i, PackageUserState packageUserState, int i2) {
        if (provider == null || !checkUseInstalledOrHidden(i, packageUserState, provider.owner.applicationInfo)) {
            return null;
        }
        if (!copyNeeded(i, provider.owner, packageUserState, provider.metaData, i2) && ((i & 2048) != 0 || provider.info.uriPermissionPatterns == null)) {
            updateApplicationInfo(provider.info.applicationInfo, i, packageUserState);
            return provider.info;
        }
        ProviderInfo providerInfo = new ProviderInfo(provider.info);
        providerInfo.metaData = provider.metaData;
        if ((i & 2048) == 0) {
            providerInfo.uriPermissionPatterns = null;
        }
        providerInfo.applicationInfo = generateApplicationInfo(provider.owner, i, packageUserState, i2);
        return providerInfo;
    }

    public static final class Instrumentation extends Component<IntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Instrumentation>() { // from class: android.content.pm.PackageParser.Instrumentation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Instrumentation createFromParcel(Parcel parcel) {
                return new Instrumentation(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Instrumentation[] newArray(int i) {
                return new Instrumentation[i];
            }
        };
        public final InstrumentationInfo info;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Instrumentation(ParsePackageItemArgs parsePackageItemArgs, InstrumentationInfo instrumentationInfo) {
            super(parsePackageItemArgs, instrumentationInfo);
            this.info = instrumentationInfo;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String str) {
            super.setPackageName(str);
            this.info.packageName = str;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Instrumentation{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.info, i);
        }

        private Instrumentation(Parcel parcel) {
            super(parcel);
            InstrumentationInfo instrumentationInfo = (InstrumentationInfo) parcel.readParcelable(Object.class.getClassLoader());
            this.info = instrumentationInfo;
            if (instrumentationInfo.targetPackage != null) {
                instrumentationInfo.targetPackage = instrumentationInfo.targetPackage.intern();
            }
            if (instrumentationInfo.targetProcesses != null) {
                instrumentationInfo.targetProcesses = instrumentationInfo.targetProcesses.intern();
            }
        }
    }

    public static final InstrumentationInfo generateInstrumentationInfo(Instrumentation instrumentation, int i) {
        if (instrumentation == null) {
            return null;
        }
        if ((i & 128) == 0) {
            return instrumentation.info;
        }
        InstrumentationInfo instrumentationInfo = new InstrumentationInfo(instrumentation.info);
        instrumentationInfo.metaData = instrumentation.metaData;
        return instrumentationInfo;
    }

    public static abstract class IntentInfo extends IntentFilter {
        public int banner;
        public boolean hasDefault;
        public int icon;
        public int labelRes;
        public int logo;
        public CharSequence nonLocalizedLabel;
        public int preferred;

        protected IntentInfo() {
        }

        protected IntentInfo(Parcel parcel) {
            super(parcel);
            this.hasDefault = parcel.readInt() == 1;
            this.labelRes = parcel.readInt();
            this.nonLocalizedLabel = parcel.readCharSequence();
            this.icon = parcel.readInt();
            this.logo = parcel.readInt();
            this.banner = parcel.readInt();
            this.preferred = parcel.readInt();
        }

        public void writeIntentInfoToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.hasDefault ? 1 : 0);
            parcel.writeInt(this.labelRes);
            parcel.writeCharSequence(this.nonLocalizedLabel);
            parcel.writeInt(this.icon);
            parcel.writeInt(this.logo);
            parcel.writeInt(this.banner);
            parcel.writeInt(this.preferred);
        }
    }

    public static final class ActivityIntentInfo extends IntentInfo {
        public Activity activity;

        public ActivityIntentInfo(Activity activity) {
            this.activity = activity;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("ActivityIntentInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            this.activity.appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        public ActivityIntentInfo(Parcel parcel) {
            super(parcel);
        }
    }

    public static final class ServiceIntentInfo extends IntentInfo {
        public Service service;

        public ServiceIntentInfo(Service service) {
            this.service = service;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("ServiceIntentInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            this.service.appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        public ServiceIntentInfo(Parcel parcel) {
            super(parcel);
        }
    }

    public static final class ProviderIntentInfo extends IntentInfo {
        public Provider provider;

        public ProviderIntentInfo(Provider provider) {
            this.provider = provider;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("ProviderIntentInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            this.provider.appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        public ProviderIntentInfo(Parcel parcel) {
            super(parcel);
        }
    }

    public static void setCompatibilityModeEnabled(boolean z) {
        sCompatibilityModeEnabled = z;
    }

    public static void readConfigUseRoundIcon(Resources resources) {
        if (resources != null) {
            sUseRoundIcon = resources.getBoolean(R.bool.config_useRoundIcon);
            return;
        }
        try {
            ApplicationInfo applicationInfo = ActivityThread.getPackageManager().getApplicationInfo("android", 0, UserHandle.myUserId());
            Resources system = Resources.getSystem();
            sUseRoundIcon = ResourcesManager.getInstance().getResources(null, null, null, applicationInfo.resourceDirs, applicationInfo.sharedLibraryFiles, 0, null, system.getCompatibilityInfo(), system.getClassLoader(), null).getBoolean(R.bool.config_useRoundIcon);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static class PackageParserException extends Exception {
        public final int error;

        public PackageParserException(int i, String str) {
            super(str);
            this.error = i;
        }

        public PackageParserException(int i, String str, Throwable th) {
            super(str, th);
            this.error = i;
        }
    }
}
