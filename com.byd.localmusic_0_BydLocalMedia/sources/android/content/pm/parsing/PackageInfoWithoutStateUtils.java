package android.content.pm.parsing;

import android.apex.ApexInfo;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FallbackCategoryProvider;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.PackageUserState;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.SELinuxUtil;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.content.pm.parsing.component.ComponentParseUtils;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedComponent;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedMainComponent;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedProvider;
import android.content.pm.parsing.component.ParsedService;
import android.os.Environment;
import android.os.UserHandle;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.util.Collections;
import java.util.Set;
import libcore.util.EmptyArray;

/* JADX INFO: loaded from: classes.dex */
public class PackageInfoWithoutStateUtils {
    private static int flag(boolean z, int i) {
        if (z) {
            return i;
        }
        return 0;
    }

    public static PackageInfo generate(ParsingPackageRead parsingPackageRead, int[] iArr, int i, long j, long j2, Set<String> set, PackageUserState packageUserState, int i2) {
        return generateWithComponents(parsingPackageRead, iArr, i, j, j2, set, packageUserState, i2, null);
    }

    public static PackageInfo generate(ParsingPackageRead parsingPackageRead, ApexInfo apexInfo, int i) {
        return generateWithComponents(parsingPackageRead, EmptyArray.INT, i, 0L, 0L, Collections.emptySet(), new PackageUserState(), UserHandle.getCallingUserId(), apexInfo);
    }

    private static PackageInfo generateWithComponents(ParsingPackageRead parsingPackageRead, int[] iArr, int i, long j, long j2, Set<String> set, PackageUserState packageUserState, int i2, ApexInfo apexInfo) {
        PackageInfo packageInfoGenerateWithoutComponents;
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        ApplicationInfo applicationInfoGenerateApplicationInfo = generateApplicationInfo(parsingPackageRead, i, packageUserState, i2);
        if (applicationInfoGenerateApplicationInfo == null || (packageInfoGenerateWithoutComponents = generateWithoutComponents(parsingPackageRead, iArr, i, j, j2, set, packageUserState, i2, apexInfo, applicationInfoGenerateApplicationInfo)) == null) {
            return null;
        }
        if ((i & 1) != 0 && (size5 = parsingPackageRead.getActivities().size()) > 0) {
            ActivityInfo[] activityInfoArr = new ActivityInfo[size5];
            int i3 = 0;
            for (int i4 = 0; i4 < size5; i4++) {
                ParsedActivity parsedActivity = parsingPackageRead.getActivities().get(i4);
                if (ComponentParseUtils.isMatch(packageUserState, false, parsingPackageRead.isEnabled(), parsedActivity, i) && !PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(parsedActivity.getName())) {
                    activityInfoArr[i3] = generateActivityInfo(parsingPackageRead, parsedActivity, i, packageUserState, applicationInfoGenerateApplicationInfo, i2);
                    i3++;
                }
            }
            packageInfoGenerateWithoutComponents.activities = (ActivityInfo[]) ArrayUtils.trimToSize(activityInfoArr, i3);
        }
        if ((i & 2) != 0 && (size4 = parsingPackageRead.getReceivers().size()) > 0) {
            ActivityInfo[] activityInfoArr2 = new ActivityInfo[size4];
            int i5 = 0;
            for (int i6 = 0; i6 < size4; i6++) {
                ParsedActivity parsedActivity2 = parsingPackageRead.getReceivers().get(i6);
                if (ComponentParseUtils.isMatch(packageUserState, false, parsingPackageRead.isEnabled(), parsedActivity2, i)) {
                    activityInfoArr2[i5] = generateActivityInfo(parsingPackageRead, parsedActivity2, i, packageUserState, applicationInfoGenerateApplicationInfo, i2);
                    i5++;
                }
            }
            packageInfoGenerateWithoutComponents.receivers = (ActivityInfo[]) ArrayUtils.trimToSize(activityInfoArr2, i5);
        }
        if ((i & 4) != 0 && (size3 = parsingPackageRead.getServices().size()) > 0) {
            ServiceInfo[] serviceInfoArr = new ServiceInfo[size3];
            int i7 = 0;
            for (int i8 = 0; i8 < size3; i8++) {
                ParsedService parsedService = parsingPackageRead.getServices().get(i8);
                if (ComponentParseUtils.isMatch(packageUserState, false, parsingPackageRead.isEnabled(), parsedService, i)) {
                    serviceInfoArr[i7] = generateServiceInfo(parsingPackageRead, parsedService, i, packageUserState, applicationInfoGenerateApplicationInfo, i2);
                    i7++;
                }
            }
            packageInfoGenerateWithoutComponents.services = (ServiceInfo[]) ArrayUtils.trimToSize(serviceInfoArr, i7);
        }
        if ((i & 8) != 0 && (size2 = parsingPackageRead.getProviders().size()) > 0) {
            ProviderInfo[] providerInfoArr = new ProviderInfo[size2];
            int i9 = 0;
            for (int i10 = 0; i10 < size2; i10++) {
                ParsedProvider parsedProvider = parsingPackageRead.getProviders().get(i10);
                if (ComponentParseUtils.isMatch(packageUserState, false, parsingPackageRead.isEnabled(), parsedProvider, i)) {
                    providerInfoArr[i9] = generateProviderInfo(parsingPackageRead, parsedProvider, i, packageUserState, applicationInfoGenerateApplicationInfo, i2);
                    i9++;
                }
            }
            packageInfoGenerateWithoutComponents.providers = (ProviderInfo[]) ArrayUtils.trimToSize(providerInfoArr, i9);
        }
        if ((i & 16) != 0 && (size = parsingPackageRead.getInstrumentations().size()) > 0) {
            packageInfoGenerateWithoutComponents.instrumentation = new InstrumentationInfo[size];
            for (int i11 = 0; i11 < size; i11++) {
                packageInfoGenerateWithoutComponents.instrumentation[i11] = generateInstrumentationInfo(parsingPackageRead.getInstrumentations().get(i11), parsingPackageRead, i, i2);
            }
        }
        return packageInfoGenerateWithoutComponents;
    }

    public static PackageInfo generateWithoutComponents(ParsingPackageRead parsingPackageRead, int[] iArr, int i, long j, long j2, Set<String> set, PackageUserState packageUserState, int i2, ApexInfo apexInfo, ApplicationInfo applicationInfo) {
        if (checkUseInstalled(parsingPackageRead, packageUserState, i)) {
            return generateWithoutComponentsUnchecked(parsingPackageRead, iArr, i, j, j2, set, packageUserState, i2, apexInfo, applicationInfo);
        }
        return null;
    }

    public static PackageInfo generateWithoutComponentsUnchecked(ParsingPackageRead parsingPackageRead, int[] iArr, int i, long j, long j2, Set<String> set, PackageUserState packageUserState, int i2, ApexInfo apexInfo, ApplicationInfo applicationInfo) {
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.packageName = parsingPackageRead.getPackageName();
        packageInfo.splitNames = parsingPackageRead.getSplitNames();
        packageInfo.versionCode = parsingPackageRead.getVersionCode();
        packageInfo.versionCodeMajor = parsingPackageRead.getVersionCodeMajor();
        packageInfo.baseRevisionCode = parsingPackageRead.getBaseRevisionCode();
        packageInfo.splitRevisionCodes = parsingPackageRead.getSplitRevisionCodes();
        packageInfo.versionName = parsingPackageRead.getVersionName();
        packageInfo.sharedUserId = parsingPackageRead.getSharedUserId();
        packageInfo.sharedUserLabel = parsingPackageRead.getSharedUserLabel();
        packageInfo.applicationInfo = applicationInfo;
        packageInfo.installLocation = parsingPackageRead.getInstallLocation();
        if ((packageInfo.applicationInfo.flags & 1) != 0 || (packageInfo.applicationInfo.flags & 128) != 0) {
            packageInfo.requiredForAllUsers = parsingPackageRead.isRequiredForAllUsers();
        }
        packageInfo.restrictedAccountType = parsingPackageRead.getRestrictedAccountType();
        packageInfo.requiredAccountType = parsingPackageRead.getRequiredAccountType();
        packageInfo.overlayTarget = parsingPackageRead.getOverlayTarget();
        packageInfo.targetOverlayableName = parsingPackageRead.getOverlayTargetName();
        packageInfo.overlayCategory = parsingPackageRead.getOverlayCategory();
        packageInfo.overlayPriority = parsingPackageRead.getOverlayPriority();
        packageInfo.mOverlayIsStatic = parsingPackageRead.isOverlayIsStatic();
        packageInfo.compileSdkVersion = parsingPackageRead.getCompileSdkVersion();
        packageInfo.compileSdkVersionCodename = parsingPackageRead.getCompileSdkVersionCodeName();
        packageInfo.firstInstallTime = j;
        packageInfo.lastUpdateTime = j2;
        if ((i & 256) != 0) {
            packageInfo.gids = iArr;
        }
        if ((i & 16384) != 0) {
            int size = parsingPackageRead.getConfigPreferences().size();
            if (size > 0) {
                packageInfo.configPreferences = new ConfigurationInfo[size];
                parsingPackageRead.getConfigPreferences().toArray(packageInfo.configPreferences);
            }
            int size2 = parsingPackageRead.getReqFeatures().size();
            if (size2 > 0) {
                packageInfo.reqFeatures = new FeatureInfo[size2];
                parsingPackageRead.getReqFeatures().toArray(packageInfo.reqFeatures);
            }
            int size3 = parsingPackageRead.getFeatureGroups().size();
            if (size3 > 0) {
                packageInfo.featureGroups = new FeatureGroupInfo[size3];
                parsingPackageRead.getFeatureGroups().toArray(packageInfo.featureGroups);
            }
        }
        if ((i & 4096) != 0) {
            int size4 = ArrayUtils.size(parsingPackageRead.getPermissions());
            if (size4 > 0) {
                packageInfo.permissions = new PermissionInfo[size4];
                for (int i3 = 0; i3 < size4; i3++) {
                    packageInfo.permissions[i3] = generatePermissionInfo(parsingPackageRead.getPermissions().get(i3), i);
                }
            }
            int size5 = parsingPackageRead.getRequestedPermissions().size();
            if (size5 > 0) {
                packageInfo.requestedPermissions = new String[size5];
                packageInfo.requestedPermissionsFlags = new int[size5];
                for (int i4 = 0; i4 < size5; i4++) {
                    String str = parsingPackageRead.getRequestedPermissions().get(i4);
                    packageInfo.requestedPermissions[i4] = str;
                    int[] iArr2 = packageInfo.requestedPermissionsFlags;
                    iArr2[i4] = iArr2[i4] | 1;
                    if (set != null && set.contains(str)) {
                        int[] iArr3 = packageInfo.requestedPermissionsFlags;
                        iArr3[i4] = iArr3[i4] | 2;
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
        PackageParser.SigningDetails signingDetails = parsingPackageRead.getSigningDetails();
        if ((i & 64) != 0) {
            if (signingDetails.hasPastSigningCertificates()) {
                packageInfo.signatures = new Signature[1];
                packageInfo.signatures[0] = signingDetails.pastSigningCertificates[0];
            } else if (signingDetails.hasSignatures()) {
                int length = signingDetails.signatures.length;
                packageInfo.signatures = new Signature[length];
                System.arraycopy(signingDetails.signatures, 0, packageInfo.signatures, 0, length);
            }
        }
        if ((134217728 & i) != 0) {
            if (signingDetails != PackageParser.SigningDetails.UNKNOWN) {
                packageInfo.signingInfo = new SigningInfo(signingDetails);
            } else {
                packageInfo.signingInfo = null;
            }
        }
        return packageInfo;
    }

    public static ApplicationInfo generateApplicationInfo(ParsingPackageRead parsingPackageRead, int i, PackageUserState packageUserState, int i2) {
        if (parsingPackageRead != null && checkUseInstalled(parsingPackageRead, packageUserState, i)) {
            return generateApplicationInfoUnchecked(parsingPackageRead, i, packageUserState, i2);
        }
        return null;
    }

    public static ApplicationInfo generateApplicationInfoUnchecked(ParsingPackageRead parsingPackageRead, int i, PackageUserState packageUserState, int i2) {
        ApplicationInfo appInfoWithoutState = parsingPackageRead.toAppInfoWithoutState();
        appInfoWithoutState.initForUser(i2);
        if ((i & 128) == 0) {
            appInfoWithoutState.metaData = null;
        }
        if ((i & 1024) == 0) {
            appInfoWithoutState.sharedLibraryFiles = null;
            appInfoWithoutState.sharedLibraryInfos = null;
        }
        if (!PackageParser.sCompatibilityModeEnabled) {
            appInfoWithoutState.disableCompatibilityMode();
        }
        appInfoWithoutState.flags |= flag(packageUserState.stopped, 2097152) | flag(packageUserState.installed, 8388608) | flag(packageUserState.suspended, 1073741824);
        appInfoWithoutState.privateFlags |= flag(packageUserState.instantApp, 128) | flag(packageUserState.virtualPreload, 65536) | flag(packageUserState.hidden, 1);
        if (packageUserState.enabled == 1) {
            appInfoWithoutState.enabled = true;
        } else if (packageUserState.enabled == 4) {
            appInfoWithoutState.enabled = (i & 32768) != 0;
        } else if (packageUserState.enabled == 2 || packageUserState.enabled == 3) {
            appInfoWithoutState.enabled = false;
        }
        appInfoWithoutState.enabledSetting = packageUserState.enabled;
        if (appInfoWithoutState.category == -1) {
            appInfoWithoutState.category = packageUserState.categoryHint;
        }
        if (appInfoWithoutState.category == -1) {
            appInfoWithoutState.category = FallbackCategoryProvider.getFallbackCategory(appInfoWithoutState.packageName);
        }
        appInfoWithoutState.seInfoUser = SELinuxUtil.assignSeinfoUser(packageUserState);
        appInfoWithoutState.resourceDirs = packageUserState.getAllOverlayPaths();
        return appInfoWithoutState;
    }

    public static ActivityInfo generateActivityInfo(ParsingPackageRead parsingPackageRead, ParsedActivity parsedActivity, int i, PackageUserState packageUserState, ApplicationInfo applicationInfo, int i2) {
        if (parsedActivity == null || !checkUseInstalled(parsingPackageRead, packageUserState, i)) {
            return null;
        }
        if (applicationInfo == null) {
            applicationInfo = generateApplicationInfo(parsingPackageRead, i, packageUserState, i2);
        }
        if (applicationInfo == null) {
            return null;
        }
        return generateActivityInfoUnchecked(parsedActivity, applicationInfo);
    }

    public static ActivityInfo generateActivityInfoUnchecked(ParsedActivity parsedActivity, ApplicationInfo applicationInfo) {
        ActivityInfo activityInfo = new ActivityInfo();
        assignSharedFieldsForComponentInfo(activityInfo, parsedActivity);
        activityInfo.targetActivity = parsedActivity.getTargetActivity();
        activityInfo.processName = parsedActivity.getProcessName();
        activityInfo.exported = parsedActivity.isExported();
        activityInfo.theme = parsedActivity.getTheme();
        activityInfo.uiOptions = parsedActivity.getUiOptions();
        activityInfo.parentActivityName = parsedActivity.getParentActivityName();
        activityInfo.permission = parsedActivity.getPermission();
        activityInfo.taskAffinity = parsedActivity.getTaskAffinity();
        activityInfo.flags = parsedActivity.getFlags();
        activityInfo.privateFlags = parsedActivity.getPrivateFlags();
        activityInfo.launchMode = parsedActivity.getLaunchMode();
        activityInfo.documentLaunchMode = parsedActivity.getDocumentLaunchMode();
        activityInfo.maxRecents = parsedActivity.getMaxRecents();
        activityInfo.configChanges = parsedActivity.getConfigChanges();
        activityInfo.softInputMode = parsedActivity.getSoftInputMode();
        activityInfo.persistableMode = parsedActivity.getPersistableMode();
        activityInfo.lockTaskLaunchMode = parsedActivity.getLockTaskLaunchMode();
        activityInfo.screenOrientation = parsedActivity.getScreenOrientation();
        activityInfo.resizeMode = parsedActivity.getResizeMode();
        Float maxAspectRatio = parsedActivity.getMaxAspectRatio();
        activityInfo.maxAspectRatio = maxAspectRatio != null ? maxAspectRatio.floatValue() : 0.0f;
        Float minAspectRatio = parsedActivity.getMinAspectRatio();
        activityInfo.minAspectRatio = minAspectRatio != null ? minAspectRatio.floatValue() : 0.0f;
        activityInfo.supportsSizeChanges = parsedActivity.getSupportsSizeChanges();
        activityInfo.requestedVrComponent = parsedActivity.getRequestedVrComponent();
        activityInfo.rotationAnimation = parsedActivity.getRotationAnimation();
        activityInfo.colorMode = parsedActivity.getColorMode();
        activityInfo.windowLayout = parsedActivity.getWindowLayout();
        activityInfo.metaData = parsedActivity.getMetaData();
        activityInfo.applicationInfo = applicationInfo;
        return activityInfo;
    }

    public static ActivityInfo generateActivityInfo(ParsingPackageRead parsingPackageRead, ParsedActivity parsedActivity, int i, PackageUserState packageUserState, int i2) {
        return generateActivityInfo(parsingPackageRead, parsedActivity, i, packageUserState, null, i2);
    }

    public static ServiceInfo generateServiceInfo(ParsingPackageRead parsingPackageRead, ParsedService parsedService, int i, PackageUserState packageUserState, ApplicationInfo applicationInfo, int i2) {
        if (parsedService == null || !checkUseInstalled(parsingPackageRead, packageUserState, i)) {
            return null;
        }
        if (applicationInfo == null) {
            applicationInfo = generateApplicationInfo(parsingPackageRead, i, packageUserState, i2);
        }
        if (applicationInfo == null) {
            return null;
        }
        return generateServiceInfoUnchecked(parsedService, applicationInfo);
    }

    public static ServiceInfo generateServiceInfoUnchecked(ParsedService parsedService, ApplicationInfo applicationInfo) {
        ServiceInfo serviceInfo = new ServiceInfo();
        assignSharedFieldsForComponentInfo(serviceInfo, parsedService);
        serviceInfo.exported = parsedService.isExported();
        serviceInfo.flags = parsedService.getFlags();
        serviceInfo.metaData = parsedService.getMetaData();
        serviceInfo.permission = parsedService.getPermission();
        serviceInfo.processName = parsedService.getProcessName();
        serviceInfo.mForegroundServiceType = parsedService.getForegroundServiceType();
        serviceInfo.applicationInfo = applicationInfo;
        return serviceInfo;
    }

    public static ServiceInfo generateServiceInfo(ParsingPackageRead parsingPackageRead, ParsedService parsedService, int i, PackageUserState packageUserState, int i2) {
        return generateServiceInfo(parsingPackageRead, parsedService, i, packageUserState, null, i2);
    }

    public static ProviderInfo generateProviderInfo(ParsingPackageRead parsingPackageRead, ParsedProvider parsedProvider, int i, PackageUserState packageUserState, ApplicationInfo applicationInfo, int i2) {
        if (parsedProvider == null || !checkUseInstalled(parsingPackageRead, packageUserState, i)) {
            return null;
        }
        if (applicationInfo == null) {
            applicationInfo = generateApplicationInfo(parsingPackageRead, i, packageUserState, i2);
        }
        if (applicationInfo == null) {
            return null;
        }
        return generateProviderInfoUnchecked(parsedProvider, i, applicationInfo);
    }

    public static ProviderInfo generateProviderInfoUnchecked(ParsedProvider parsedProvider, int i, ApplicationInfo applicationInfo) {
        ProviderInfo providerInfo = new ProviderInfo();
        assignSharedFieldsForComponentInfo(providerInfo, parsedProvider);
        providerInfo.exported = parsedProvider.isExported();
        providerInfo.flags = parsedProvider.getFlags();
        providerInfo.processName = parsedProvider.getProcessName();
        providerInfo.authority = parsedProvider.getAuthority();
        providerInfo.isSyncable = parsedProvider.isSyncable();
        providerInfo.readPermission = parsedProvider.getReadPermission();
        providerInfo.writePermission = parsedProvider.getWritePermission();
        providerInfo.grantUriPermissions = parsedProvider.isGrantUriPermissions();
        providerInfo.forceUriPermissions = parsedProvider.isForceUriPermissions();
        providerInfo.multiprocess = parsedProvider.isMultiProcess();
        providerInfo.initOrder = parsedProvider.getInitOrder();
        providerInfo.uriPermissionPatterns = parsedProvider.getUriPermissionPatterns();
        providerInfo.pathPermissions = parsedProvider.getPathPermissions();
        providerInfo.metaData = parsedProvider.getMetaData();
        if ((i & 2048) == 0) {
            providerInfo.uriPermissionPatterns = null;
        }
        providerInfo.applicationInfo = applicationInfo;
        return providerInfo;
    }

    public static ProviderInfo generateProviderInfo(ParsingPackageRead parsingPackageRead, ParsedProvider parsedProvider, int i, PackageUserState packageUserState, int i2) {
        return generateProviderInfo(parsingPackageRead, parsedProvider, i, packageUserState, null, i2);
    }

    public static InstrumentationInfo generateInstrumentationInfo(ParsedInstrumentation parsedInstrumentation, ParsingPackageRead parsingPackageRead, int i, int i2) {
        if (parsedInstrumentation == null) {
            return null;
        }
        InstrumentationInfo instrumentationInfo = new InstrumentationInfo();
        assignSharedFieldsForPackageItemInfo(instrumentationInfo, parsedInstrumentation);
        instrumentationInfo.targetPackage = parsedInstrumentation.getTargetPackage();
        instrumentationInfo.targetProcesses = parsedInstrumentation.getTargetProcesses();
        instrumentationInfo.handleProfiling = parsedInstrumentation.isHandleProfiling();
        instrumentationInfo.functionalTest = parsedInstrumentation.isFunctionalTest();
        instrumentationInfo.sourceDir = parsingPackageRead.getBaseCodePath();
        instrumentationInfo.publicSourceDir = parsingPackageRead.getBaseCodePath();
        instrumentationInfo.splitNames = parsingPackageRead.getSplitNames();
        instrumentationInfo.splitSourceDirs = parsingPackageRead.getSplitCodePaths();
        instrumentationInfo.splitPublicSourceDirs = parsingPackageRead.getSplitCodePaths();
        instrumentationInfo.splitDependencies = parsingPackageRead.getSplitDependencies();
        instrumentationInfo.dataDir = getDataDir(parsingPackageRead, i2).getAbsolutePath();
        instrumentationInfo.deviceProtectedDataDir = getDeviceProtectedDataDir(parsingPackageRead, i2).getAbsolutePath();
        instrumentationInfo.credentialProtectedDataDir = getCredentialProtectedDataDir(parsingPackageRead, i2).getAbsolutePath();
        if ((i & 128) == 0) {
            return instrumentationInfo;
        }
        instrumentationInfo.metaData = parsedInstrumentation.getMetaData();
        return instrumentationInfo;
    }

    public static PermissionInfo generatePermissionInfo(ParsedPermission parsedPermission, int i) {
        if (parsedPermission == null) {
            return null;
        }
        PermissionInfo permissionInfo = new PermissionInfo(parsedPermission.getBackgroundPermission());
        assignSharedFieldsForPackageItemInfo(permissionInfo, parsedPermission);
        permissionInfo.group = parsedPermission.getGroup();
        permissionInfo.requestRes = parsedPermission.getRequestRes();
        permissionInfo.protectionLevel = parsedPermission.getProtectionLevel();
        permissionInfo.descriptionRes = parsedPermission.getDescriptionRes();
        permissionInfo.flags = parsedPermission.getFlags();
        if ((i & 128) == 0) {
            return permissionInfo;
        }
        permissionInfo.metaData = parsedPermission.getMetaData();
        return permissionInfo;
    }

    public static PermissionGroupInfo generatePermissionGroupInfo(ParsedPermissionGroup parsedPermissionGroup, int i) {
        if (parsedPermissionGroup == null) {
            return null;
        }
        PermissionGroupInfo permissionGroupInfo = new PermissionGroupInfo(parsedPermissionGroup.getRequestDetailResourceId(), parsedPermissionGroup.getBackgroundRequestResourceId(), parsedPermissionGroup.getBackgroundRequestDetailResourceId());
        assignSharedFieldsForPackageItemInfo(permissionGroupInfo, parsedPermissionGroup);
        permissionGroupInfo.descriptionRes = parsedPermissionGroup.getDescriptionRes();
        permissionGroupInfo.priority = parsedPermissionGroup.getPriority();
        permissionGroupInfo.requestRes = parsedPermissionGroup.getRequestRes();
        permissionGroupInfo.flags = parsedPermissionGroup.getFlags();
        if ((i & 128) == 0) {
            return permissionGroupInfo;
        }
        permissionGroupInfo.metaData = parsedPermissionGroup.getMetaData();
        return permissionGroupInfo;
    }

    private static void assignSharedFieldsForComponentInfo(ComponentInfo componentInfo, ParsedMainComponent parsedMainComponent) {
        assignSharedFieldsForPackageItemInfo(componentInfo, parsedMainComponent);
        componentInfo.descriptionRes = parsedMainComponent.getDescriptionRes();
        componentInfo.directBootAware = parsedMainComponent.isDirectBootAware();
        componentInfo.enabled = parsedMainComponent.isEnabled();
        componentInfo.splitName = parsedMainComponent.getSplitName();
    }

    private static void assignSharedFieldsForPackageItemInfo(PackageItemInfo packageItemInfo, ParsedComponent parsedComponent) {
        packageItemInfo.nonLocalizedLabel = ComponentParseUtils.getNonLocalizedLabel(parsedComponent);
        packageItemInfo.icon = ComponentParseUtils.getIcon(parsedComponent);
        packageItemInfo.banner = parsedComponent.getBanner();
        packageItemInfo.labelRes = parsedComponent.getLabelRes();
        packageItemInfo.logo = parsedComponent.getLogo();
        packageItemInfo.name = parsedComponent.getName();
        packageItemInfo.packageName = parsedComponent.getPackageName();
    }

    public static int appInfoFlags(ParsingPackageRead parsingPackageRead) {
        return flag(parsingPackageRead.isAnyDensity(), 8192) | flag(parsingPackageRead.isExternalStorage(), 262144) | flag(parsingPackageRead.isBaseHardwareAccelerated(), 536870912) | flag(parsingPackageRead.isAllowBackup(), 32768) | flag(parsingPackageRead.isKillAfterRestore(), 65536) | flag(parsingPackageRead.isRestoreAnyVersion(), 131072) | flag(parsingPackageRead.isFullBackupOnly(), 67108864) | flag(parsingPackageRead.isPersistent(), 8) | flag(parsingPackageRead.isDebuggable(), 2) | flag(parsingPackageRead.isVmSafeMode(), 16384) | flag(parsingPackageRead.isHasCode(), 4) | flag(parsingPackageRead.isAllowTaskReparenting(), 32) | flag(parsingPackageRead.isAllowClearUserData(), 64) | flag(parsingPackageRead.isLargeHeap(), 1048576) | flag(parsingPackageRead.isUsesCleartextTraffic(), 134217728) | flag(parsingPackageRead.isSupportsRtl(), 4194304) | flag(parsingPackageRead.isTestOnly(), 256) | flag(parsingPackageRead.isMultiArch(), Integer.MIN_VALUE) | flag(parsingPackageRead.isExtractNativeLibs(), 268435456) | flag(parsingPackageRead.isGame(), 33554432) | flag(parsingPackageRead.isSupportsSmallScreens(), 512) | flag(parsingPackageRead.isSupportsNormalScreens(), 1024) | flag(parsingPackageRead.isSupportsLargeScreens(), 2048) | flag(parsingPackageRead.isSupportsExtraLargeScreens(), 524288) | flag(parsingPackageRead.isResizeable(), 4096);
    }

    public static int appInfoPrivateFlags(ParsingPackageRead parsingPackageRead) {
        int iFlag = flag(parsingPackageRead.isStaticSharedLibrary(), 16384) | flag(parsingPackageRead.isOverlay(), 268435456) | flag(parsingPackageRead.isIsolatedSplitLoading(), 32768) | flag(parsingPackageRead.isHasDomainUrls(), 16) | flag(parsingPackageRead.isProfileableByShell(), 8388608) | flag(parsingPackageRead.isBackupInForeground(), 8192) | flag(parsingPackageRead.isUseEmbeddedDex(), 33554432) | flag(parsingPackageRead.isDefaultToDeviceProtectedStorage(), 32) | flag(parsingPackageRead.isDirectBootAware(), 64) | flag(parsingPackageRead.isPartiallyDirectBootAware(), 256) | flag(parsingPackageRead.isAllowClearUserDataOnFailedRestore(), 67108864) | flag(parsingPackageRead.isAllowAudioPlaybackCapture(), 134217728) | flag(parsingPackageRead.isRequestLegacyExternalStorage(), 536870912) | flag(parsingPackageRead.isUsesNonSdkApi(), 4194304) | flag(parsingPackageRead.isHasFragileUserData(), 16777216) | flag(parsingPackageRead.isCantSaveState(), 2) | flag(parsingPackageRead.isResizeableActivityViaSdkVersion(), 4096) | flag(parsingPackageRead.isAllowNativeHeapPointerTagging(), Integer.MIN_VALUE);
        Boolean resizeableActivity = parsingPackageRead.getResizeableActivity();
        return resizeableActivity != null ? resizeableActivity.booleanValue() ? iFlag | 1024 : iFlag | 2048 : iFlag;
    }

    private static boolean checkUseInstalled(ParsingPackageRead parsingPackageRead, PackageUserState packageUserState, int i) {
        return packageUserState.isAvailable(i);
    }

    public static File getDataDir(ParsingPackageRead parsingPackageRead, int i) {
        if ("android".equals(parsingPackageRead.getPackageName())) {
            return Environment.getDataSystemDirectory();
        }
        if (parsingPackageRead.isDefaultToDeviceProtectedStorage()) {
            return getDeviceProtectedDataDir(parsingPackageRead, i);
        }
        return getCredentialProtectedDataDir(parsingPackageRead, i);
    }

    public static File getDeviceProtectedDataDir(ParsingPackageRead parsingPackageRead, int i) {
        return Environment.getDataUserDePackageDirectory(parsingPackageRead.getVolumeUuid(), i, parsingPackageRead.getPackageName());
    }

    public static File getCredentialProtectedDataDir(ParsingPackageRead parsingPackageRead, int i) {
        return Environment.getDataUserCePackageDirectory(parsingPackageRead.getVolumeUuid(), i, parsingPackageRead.getPackageName());
    }
}
