package com.android.server;

import android.content.ComponentName;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.media.MediaMetrics;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.os.CarrierAssociatedAppEntry;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Process;
import android.os.SystemProperties;
import android.permission.PermissionManager;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Slog;
import android.util.SparseArray;
import android.util.TimingsTraceLog;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public class SystemConfig {
    private static final int ALLOW_ALL = -1;
    private static final int ALLOW_APP_CONFIGS = 8;
    private static final int ALLOW_ASSOCIATIONS = 128;
    private static final int ALLOW_FEATURES = 1;
    private static final int ALLOW_HIDDENAPI_WHITELISTING = 64;
    private static final int ALLOW_LIBS = 2;
    private static final int ALLOW_OEM_PERMISSIONS = 32;
    private static final int ALLOW_PERMISSIONS = 4;
    private static final int ALLOW_PRIVAPP_PERMISSIONS = 16;
    private static final String SKU_PROPERTY = "ro.boot.product.hardware.sku";
    static final String TAG = "SystemConfig";
    private static final String VENDOR_SKU_PROPERTY = "ro.boot.product.vendor.sku";
    static SystemConfig sInstance;
    int[] mGlobalGids;
    final SparseArray<ArraySet<String>> mSystemPermissions = new SparseArray<>();
    final ArrayList<PermissionManager.SplitPermissionInfo> mSplitPermissions = new ArrayList<>();
    final ArrayMap<String, SharedLibraryEntry> mSharedLibraries = new ArrayMap<>();
    final ArrayMap<String, FeatureInfo> mAvailableFeatures = new ArrayMap<>();
    final ArraySet<String> mUnavailableFeatures = new ArraySet<>();
    final ArrayMap<String, PermissionEntry> mPermissions = new ArrayMap<>();
    final ArraySet<String> mAllowInPowerSaveExceptIdle = new ArraySet<>();
    final ArraySet<String> mAllowInPowerSave = new ArraySet<>();
    final ArraySet<String> mAllowInDataUsageSave = new ArraySet<>();
    final ArraySet<String> mAllowUnthrottledLocation = new ArraySet<>();
    final ArraySet<String> mAllowIgnoreLocationSettings = new ArraySet<>();
    final ArraySet<String> mAllowImplicitBroadcasts = new ArraySet<>();
    final ArraySet<String> mLinkedApps = new ArraySet<>();
    final ArraySet<String> mSystemUserWhitelistedApps = new ArraySet<>();
    final ArraySet<String> mSystemUserBlacklistedApps = new ArraySet<>();
    final ArraySet<ComponentName> mDefaultVrComponents = new ArraySet<>();
    final ArraySet<ComponentName> mBackupTransportWhitelist = new ArraySet<>();
    final ArrayMap<String, ArrayMap<String, Boolean>> mPackageComponentEnabledState = new ArrayMap<>();
    final ArraySet<String> mHiddenApiPackageWhitelist = new ArraySet<>();
    final ArraySet<String> mDisabledUntilUsedPreinstalledCarrierApps = new ArraySet<>();
    final ArrayMap<String, List<CarrierAssociatedAppEntry>> mDisabledUntilUsedPreinstalledCarrierAssociatedApps = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mPrivAppPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mPrivAppDenyPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mVendorPrivAppPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mVendorPrivAppDenyPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mProductPrivAppPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mProductPrivAppDenyPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mSystemExtPrivAppPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mSystemExtPrivAppDenyPermissions = new ArrayMap<>();
    final ArrayMap<String, ArrayMap<String, Boolean>> mOemPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mAllowedAssociations = new ArrayMap<>();
    private final ArraySet<String> mBugreportWhitelistedPackages = new ArraySet<>();
    private final ArraySet<String> mAppDataIsolationWhitelistedApps = new ArraySet<>();
    private ArrayMap<String, Set<String>> mPackageToUserTypeWhitelist = new ArrayMap<>();
    private ArrayMap<String, Set<String>> mPackageToUserTypeBlacklist = new ArrayMap<>();
    private final ArraySet<String> mRollbackWhitelistedPackages = new ArraySet<>();
    private final ArraySet<String> mWhitelistedStagedInstallers = new ArraySet<>();
    private Map<String, Map<String, String>> mNamedActors = null;

    public static final class SharedLibraryEntry {
        public final String[] dependencies;
        public final String filename;
        public final String name;

        SharedLibraryEntry(String str, String str2, String[] strArr) {
            this.name = str;
            this.filename = str2;
            this.dependencies = strArr;
        }
    }

    public static final class PermissionEntry {
        public int[] gids;
        public final String name;
        public boolean perUser;

        PermissionEntry(String str, boolean z) {
            this.name = str;
            this.perUser = z;
        }
    }

    public static SystemConfig getInstance() {
        SystemConfig systemConfig;
        if (!isSystemProcess()) {
            Slog.wtf(TAG, "SystemConfig is being accessed by a process other than system_server.");
        }
        synchronized (SystemConfig.class) {
            if (sInstance == null) {
                sInstance = new SystemConfig();
            }
            systemConfig = sInstance;
        }
        return systemConfig;
    }

    public int[] getGlobalGids() {
        return this.mGlobalGids;
    }

    public SparseArray<ArraySet<String>> getSystemPermissions() {
        return this.mSystemPermissions;
    }

    public ArrayList<PermissionManager.SplitPermissionInfo> getSplitPermissions() {
        return this.mSplitPermissions;
    }

    public ArrayMap<String, SharedLibraryEntry> getSharedLibraries() {
        return this.mSharedLibraries;
    }

    public ArrayMap<String, FeatureInfo> getAvailableFeatures() {
        return this.mAvailableFeatures;
    }

    public ArrayMap<String, PermissionEntry> getPermissions() {
        return this.mPermissions;
    }

    public ArraySet<String> getAllowImplicitBroadcasts() {
        return this.mAllowImplicitBroadcasts;
    }

    public ArraySet<String> getAllowInPowerSaveExceptIdle() {
        return this.mAllowInPowerSaveExceptIdle;
    }

    public ArraySet<String> getAllowInPowerSave() {
        return this.mAllowInPowerSave;
    }

    public ArraySet<String> getAllowInDataUsageSave() {
        return this.mAllowInDataUsageSave;
    }

    public ArraySet<String> getAllowUnthrottledLocation() {
        return this.mAllowUnthrottledLocation;
    }

    public ArraySet<String> getAllowIgnoreLocationSettings() {
        return this.mAllowIgnoreLocationSettings;
    }

    public ArraySet<String> getLinkedApps() {
        return this.mLinkedApps;
    }

    public ArraySet<String> getSystemUserWhitelistedApps() {
        return this.mSystemUserWhitelistedApps;
    }

    public ArraySet<String> getSystemUserBlacklistedApps() {
        return this.mSystemUserBlacklistedApps;
    }

    public ArraySet<String> getHiddenApiWhitelistedApps() {
        return this.mHiddenApiPackageWhitelist;
    }

    public boolean isBydPackageForHiddenApis(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("([a-zA-Z_][a-zA-Z0-9_]*)+(.byd)+([.][a-zA-Z_][a-zA-Z0-9_]*)+").matcher(str).matches();
    }

    public ArraySet<ComponentName> getDefaultVrComponents() {
        return this.mDefaultVrComponents;
    }

    public ArraySet<ComponentName> getBackupTransportWhitelist() {
        return this.mBackupTransportWhitelist;
    }

    public ArrayMap<String, Boolean> getComponentsEnabledStates(String str) {
        return this.mPackageComponentEnabledState.get(str);
    }

    public ArraySet<String> getDisabledUntilUsedPreinstalledCarrierApps() {
        return this.mDisabledUntilUsedPreinstalledCarrierApps;
    }

    public ArrayMap<String, List<CarrierAssociatedAppEntry>> getDisabledUntilUsedPreinstalledCarrierAssociatedApps() {
        return this.mDisabledUntilUsedPreinstalledCarrierAssociatedApps;
    }

    public ArraySet<String> getPrivAppPermissions(String str) {
        return this.mPrivAppPermissions.get(str);
    }

    public ArraySet<String> getPrivAppDenyPermissions(String str) {
        return this.mPrivAppDenyPermissions.get(str);
    }

    public ArraySet<String> getVendorPrivAppPermissions(String str) {
        return this.mVendorPrivAppPermissions.get(str);
    }

    public ArraySet<String> getVendorPrivAppDenyPermissions(String str) {
        return this.mVendorPrivAppDenyPermissions.get(str);
    }

    public ArraySet<String> getProductPrivAppPermissions(String str) {
        return this.mProductPrivAppPermissions.get(str);
    }

    public ArraySet<String> getProductPrivAppDenyPermissions(String str) {
        return this.mProductPrivAppDenyPermissions.get(str);
    }

    public ArraySet<String> getSystemExtPrivAppPermissions(String str) {
        return this.mSystemExtPrivAppPermissions.get(str);
    }

    public ArraySet<String> getSystemExtPrivAppDenyPermissions(String str) {
        return this.mSystemExtPrivAppDenyPermissions.get(str);
    }

    public Map<String, Boolean> getOemPermissions(String str) {
        ArrayMap<String, Boolean> arrayMap = this.mOemPermissions.get(str);
        return arrayMap != null ? arrayMap : Collections.emptyMap();
    }

    public ArrayMap<String, ArraySet<String>> getAllowedAssociations() {
        return this.mAllowedAssociations;
    }

    public ArraySet<String> getBugreportWhitelistedPackages() {
        return this.mBugreportWhitelistedPackages;
    }

    public Set<String> getRollbackWhitelistedPackages() {
        return this.mRollbackWhitelistedPackages;
    }

    public Set<String> getWhitelistedStagedInstallers() {
        return this.mWhitelistedStagedInstallers;
    }

    public ArraySet<String> getAppDataIsolationWhitelistedApps() {
        return this.mAppDataIsolationWhitelistedApps;
    }

    public ArrayMap<String, Set<String>> getAndClearPackageToUserTypeWhitelist() {
        ArrayMap<String, Set<String>> arrayMap = this.mPackageToUserTypeWhitelist;
        this.mPackageToUserTypeWhitelist = new ArrayMap<>(0);
        return arrayMap;
    }

    public ArrayMap<String, Set<String>> getAndClearPackageToUserTypeBlacklist() {
        ArrayMap<String, Set<String>> arrayMap = this.mPackageToUserTypeBlacklist;
        this.mPackageToUserTypeBlacklist = new ArrayMap<>(0);
        return arrayMap;
    }

    public Map<String, Map<String, String>> getNamedActors() {
        Map<String, Map<String, String>> map = this.mNamedActors;
        return map != null ? map : Collections.emptyMap();
    }

    public SystemConfig(boolean z) throws Throwable {
        if (z) {
            Slog.w(TAG, "Constructing a test SystemConfig");
            readAllPermissions();
        } else {
            Slog.w(TAG, "Constructing an empty test SystemConfig");
        }
    }

    SystemConfig() {
        TimingsTraceLog timingsTraceLog = new TimingsTraceLog(TAG, 524288L);
        timingsTraceLog.traceBegin("readAllPermissions");
        try {
            readAllPermissions();
        } finally {
            timingsTraceLog.traceEnd();
        }
    }

    private void readAllPermissions() throws Throwable {
        readPermissions(Environment.buildPath(Environment.getRootDirectory(), "etc", "sysconfig"), -1);
        readPermissions(Environment.buildPath(Environment.getRootDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), -1);
        int i = Build.VERSION.FIRST_SDK_INT <= 27 ? 159 : 147;
        readPermissions(Environment.buildPath(Environment.getVendorDirectory(), "etc", "sysconfig"), i);
        readPermissions(Environment.buildPath(Environment.getVendorDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), i);
        String str = SystemProperties.get(VENDOR_SKU_PROPERTY, "");
        if (!str.isEmpty()) {
            String str2 = "sku_" + str;
            readPermissions(Environment.buildPath(Environment.getVendorDirectory(), "etc", "sysconfig", str2), i);
            readPermissions(Environment.buildPath(Environment.getVendorDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS, str2), i);
        }
        readPermissions(Environment.buildPath(Environment.getOdmDirectory(), "etc", "sysconfig"), i);
        readPermissions(Environment.buildPath(Environment.getOdmDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), i);
        String str3 = SystemProperties.get(SKU_PROPERTY, "");
        if (!str3.isEmpty()) {
            String str4 = "sku_" + str3;
            readPermissions(Environment.buildPath(Environment.getOdmDirectory(), "etc", "sysconfig", str4), i);
            readPermissions(Environment.buildPath(Environment.getOdmDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS, str4), i);
        }
        readPermissions(Environment.buildPath(Environment.getOemDirectory(), "etc", "sysconfig"), 161);
        readPermissions(Environment.buildPath(Environment.getOemDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), 161);
        readPermissions(Environment.buildPath(Environment.getProductDirectory(), "etc", "sysconfig"), -1);
        readPermissions(Environment.buildPath(Environment.getProductDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), -1);
        readPermissions(Environment.buildPath(Environment.getSystemExtDirectory(), "etc", "sysconfig"), -1);
        readPermissions(Environment.buildPath(Environment.getSystemExtDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), -1);
        if (isSystemProcess()) {
            for (File file : FileUtils.listFilesOrEmpty(Environment.getApexDirectory())) {
                if (!file.isFile() && !file.getPath().contains("@")) {
                    readPermissions(Environment.buildPath(file, "etc", DeviceConfig.NAMESPACE_PERMISSIONS), 2);
                }
            }
        }
    }

    public void readPermissions(File file, int i) throws Throwable {
        if (!file.exists() || !file.isDirectory()) {
            if (i == -1) {
                Slog.w(TAG, "No directory " + file + ", skipping");
                return;
            }
            return;
        }
        if (!file.canRead()) {
            Slog.w(TAG, "Directory " + file + " cannot be read");
            return;
        }
        File file2 = null;
        for (File file3 : file.listFiles()) {
            if (file3.isFile()) {
                if (file3.getPath().endsWith("etc/permissions/platform.xml")) {
                    file2 = file3;
                } else if (!file3.getPath().endsWith(".xml")) {
                    Slog.i(TAG, "Non-xml file " + file3 + " in " + file + " directory, ignoring");
                } else if (file3.canRead()) {
                    readPermissionsFromXml(file3, i);
                } else {
                    Slog.w(TAG, "Permissions library file " + file3 + " cannot be read");
                }
            }
        }
        if (file2 != null) {
            readPermissionsFromXml(file2, i);
        }
    }

    private void logNotAllowedInPartition(String str, File file, XmlPullParser xmlPullParser) {
        Slog.w(TAG, "<" + str + "> not allowed in partition of " + file + " at " + xmlPullParser.getPositionDescription());
    }

    /* JADX WARN: Not initialized variable reg: 24, insn: 0x0c9a: MOVE (r3 I:??[OBJECT, ARRAY]) = (r24 I:??[OBJECT, ARRAY]), block:B:441:0x0c99 */
    /* JADX WARN: Removed duplicated region for block: B:167:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x02c6 A[Catch: IOException -> 0x0c96, XmlPullParserException -> 0x0c98, all -> 0x0d21, TryCatch #1 {all -> 0x0d21, blocks: (B:434:0x0c54, B:169:0x028f, B:171:0x0295, B:174:0x02c1, B:172:0x02b8, B:173:0x02be, B:451:0x0cb1, B:454:0x0cba, B:175:0x02c6, B:177:0x02cc, B:179:0x02f4, B:178:0x02ef, B:180:0x02f9, B:182:0x0316, B:200:0x03c3, B:183:0x033c, B:185:0x0342, B:186:0x0367, B:188:0x036d, B:189:0x0392, B:191:0x039a, B:193:0x039e, B:194:0x03a5, B:196:0x03af, B:199:0x03c0, B:197:0x03ba, B:201:0x03c8, B:202:0x03fa, B:203:0x03fb, B:204:0x041e, B:205:0x041f, B:206:0x0428, B:208:0x042e, B:210:0x0456, B:209:0x0451, B:211:0x045b, B:213:0x0461, B:215:0x0489, B:214:0x0484, B:217:0x0490, B:219:0x0499, B:220:0x04c2, B:222:0x04ca, B:223:0x04f3, B:225:0x0505, B:226:0x050f, B:228:0x0532, B:227:0x052f, B:230:0x0539, B:232:0x053f, B:235:0x056b, B:233:0x0562, B:234:0x0568, B:237:0x0572, B:238:0x0577, B:240:0x0581, B:242:0x05a2, B:247:0x05c7, B:249:0x0607, B:251:0x0612, B:253:0x061d, B:254:0x0626, B:255:0x062f, B:257:0x0639, B:259:0x063f, B:262:0x066b, B:260:0x0662, B:261:0x0668, B:264:0x0672, B:268:0x0681, B:270:0x068d, B:274:0x06bc, B:276:0x06c6, B:277:0x06d0, B:280:0x0701, B:272:0x0692, B:278:0x06d9, B:279:0x06fe, B:282:0x0708, B:284:0x0711, B:290:0x0772, B:285:0x0736, B:287:0x073c, B:288:0x0769, B:289:0x076f, B:291:0x0776, B:294:0x077e, B:296:0x078a, B:301:0x07e2, B:298:0x07af, B:299:0x07d4, B:300:0x07df, B:303:0x07e8, B:305:0x07ee, B:308:0x081a, B:306:0x0811, B:307:0x0817, B:310:0x0821, B:312:0x0827, B:315:0x0853, B:313:0x084a, B:314:0x0850, B:317:0x085a, B:319:0x0860, B:322:0x088c, B:320:0x0883, B:321:0x0889, B:324:0x0893, B:326:0x089b, B:329:0x08c9, B:327:0x08c0, B:328:0x08c6, B:331:0x08d0, B:333:0x08d6, B:336:0x0902, B:334:0x08f9, B:335:0x08ff, B:338:0x0909, B:340:0x090f, B:343:0x093b, B:341:0x0932, B:342:0x0938, B:345:0x0942, B:347:0x0948, B:350:0x0974, B:348:0x096b, B:349:0x0971, B:352:0x097b, B:354:0x0981, B:357:0x09ad, B:355:0x09a4, B:356:0x09aa, B:359:0x09b4, B:361:0x09ba, B:364:0x09e6, B:362:0x09dd, B:363:0x09e3, B:366:0x09ed, B:368:0x09f3, B:371:0x0a21, B:369:0x0a18, B:370:0x0a1e, B:374:0x0a2a, B:379:0x0a4d, B:383:0x0a7a, B:381:0x0a72, B:377:0x0a3b, B:382:0x0a76, B:386:0x0a84, B:388:0x0a96, B:397:0x0afa, B:390:0x0abb, B:391:0x0ae0, B:393:0x0ae4, B:395:0x0aee, B:394:0x0ae8, B:396:0x0af7, B:400:0x0b02, B:401:0x0b07, B:404:0x0b14, B:406:0x0b1a, B:407:0x0b41, B:409:0x0b4a, B:410:0x0b73, B:412:0x0b79, B:413:0x0baa, B:415:0x0bb8, B:416:0x0bc2, B:418:0x0bc9, B:417:0x0bc6, B:421:0x0bd3, B:423:0x0bd9, B:424:0x0c00, B:425:0x0c09, B:428:0x0c13, B:430:0x0c1b, B:433:0x0c50, B:431:0x0c28, B:432:0x0c4d, B:436:0x0c8a, B:437:0x0c95), top: B:484:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02f9 A[Catch: IOException -> 0x0c96, XmlPullParserException -> 0x0c98, all -> 0x0d21, TryCatch #1 {all -> 0x0d21, blocks: (B:434:0x0c54, B:169:0x028f, B:171:0x0295, B:174:0x02c1, B:172:0x02b8, B:173:0x02be, B:451:0x0cb1, B:454:0x0cba, B:175:0x02c6, B:177:0x02cc, B:179:0x02f4, B:178:0x02ef, B:180:0x02f9, B:182:0x0316, B:200:0x03c3, B:183:0x033c, B:185:0x0342, B:186:0x0367, B:188:0x036d, B:189:0x0392, B:191:0x039a, B:193:0x039e, B:194:0x03a5, B:196:0x03af, B:199:0x03c0, B:197:0x03ba, B:201:0x03c8, B:202:0x03fa, B:203:0x03fb, B:204:0x041e, B:205:0x041f, B:206:0x0428, B:208:0x042e, B:210:0x0456, B:209:0x0451, B:211:0x045b, B:213:0x0461, B:215:0x0489, B:214:0x0484, B:217:0x0490, B:219:0x0499, B:220:0x04c2, B:222:0x04ca, B:223:0x04f3, B:225:0x0505, B:226:0x050f, B:228:0x0532, B:227:0x052f, B:230:0x0539, B:232:0x053f, B:235:0x056b, B:233:0x0562, B:234:0x0568, B:237:0x0572, B:238:0x0577, B:240:0x0581, B:242:0x05a2, B:247:0x05c7, B:249:0x0607, B:251:0x0612, B:253:0x061d, B:254:0x0626, B:255:0x062f, B:257:0x0639, B:259:0x063f, B:262:0x066b, B:260:0x0662, B:261:0x0668, B:264:0x0672, B:268:0x0681, B:270:0x068d, B:274:0x06bc, B:276:0x06c6, B:277:0x06d0, B:280:0x0701, B:272:0x0692, B:278:0x06d9, B:279:0x06fe, B:282:0x0708, B:284:0x0711, B:290:0x0772, B:285:0x0736, B:287:0x073c, B:288:0x0769, B:289:0x076f, B:291:0x0776, B:294:0x077e, B:296:0x078a, B:301:0x07e2, B:298:0x07af, B:299:0x07d4, B:300:0x07df, B:303:0x07e8, B:305:0x07ee, B:308:0x081a, B:306:0x0811, B:307:0x0817, B:310:0x0821, B:312:0x0827, B:315:0x0853, B:313:0x084a, B:314:0x0850, B:317:0x085a, B:319:0x0860, B:322:0x088c, B:320:0x0883, B:321:0x0889, B:324:0x0893, B:326:0x089b, B:329:0x08c9, B:327:0x08c0, B:328:0x08c6, B:331:0x08d0, B:333:0x08d6, B:336:0x0902, B:334:0x08f9, B:335:0x08ff, B:338:0x0909, B:340:0x090f, B:343:0x093b, B:341:0x0932, B:342:0x0938, B:345:0x0942, B:347:0x0948, B:350:0x0974, B:348:0x096b, B:349:0x0971, B:352:0x097b, B:354:0x0981, B:357:0x09ad, B:355:0x09a4, B:356:0x09aa, B:359:0x09b4, B:361:0x09ba, B:364:0x09e6, B:362:0x09dd, B:363:0x09e3, B:366:0x09ed, B:368:0x09f3, B:371:0x0a21, B:369:0x0a18, B:370:0x0a1e, B:374:0x0a2a, B:379:0x0a4d, B:383:0x0a7a, B:381:0x0a72, B:377:0x0a3b, B:382:0x0a76, B:386:0x0a84, B:388:0x0a96, B:397:0x0afa, B:390:0x0abb, B:391:0x0ae0, B:393:0x0ae4, B:395:0x0aee, B:394:0x0ae8, B:396:0x0af7, B:400:0x0b02, B:401:0x0b07, B:404:0x0b14, B:406:0x0b1a, B:407:0x0b41, B:409:0x0b4a, B:410:0x0b73, B:412:0x0b79, B:413:0x0baa, B:415:0x0bb8, B:416:0x0bc2, B:418:0x0bc9, B:417:0x0bc6, B:421:0x0bd3, B:423:0x0bd9, B:424:0x0c00, B:425:0x0c09, B:428:0x0c13, B:430:0x0c1b, B:433:0x0c50, B:431:0x0c28, B:432:0x0c4d, B:436:0x0c8a, B:437:0x0c95), top: B:484:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x041f A[Catch: IOException -> 0x0c96, XmlPullParserException -> 0x0c98, all -> 0x0d21, TryCatch #1 {all -> 0x0d21, blocks: (B:434:0x0c54, B:169:0x028f, B:171:0x0295, B:174:0x02c1, B:172:0x02b8, B:173:0x02be, B:451:0x0cb1, B:454:0x0cba, B:175:0x02c6, B:177:0x02cc, B:179:0x02f4, B:178:0x02ef, B:180:0x02f9, B:182:0x0316, B:200:0x03c3, B:183:0x033c, B:185:0x0342, B:186:0x0367, B:188:0x036d, B:189:0x0392, B:191:0x039a, B:193:0x039e, B:194:0x03a5, B:196:0x03af, B:199:0x03c0, B:197:0x03ba, B:201:0x03c8, B:202:0x03fa, B:203:0x03fb, B:204:0x041e, B:205:0x041f, B:206:0x0428, B:208:0x042e, B:210:0x0456, B:209:0x0451, B:211:0x045b, B:213:0x0461, B:215:0x0489, B:214:0x0484, B:217:0x0490, B:219:0x0499, B:220:0x04c2, B:222:0x04ca, B:223:0x04f3, B:225:0x0505, B:226:0x050f, B:228:0x0532, B:227:0x052f, B:230:0x0539, B:232:0x053f, B:235:0x056b, B:233:0x0562, B:234:0x0568, B:237:0x0572, B:238:0x0577, B:240:0x0581, B:242:0x05a2, B:247:0x05c7, B:249:0x0607, B:251:0x0612, B:253:0x061d, B:254:0x0626, B:255:0x062f, B:257:0x0639, B:259:0x063f, B:262:0x066b, B:260:0x0662, B:261:0x0668, B:264:0x0672, B:268:0x0681, B:270:0x068d, B:274:0x06bc, B:276:0x06c6, B:277:0x06d0, B:280:0x0701, B:272:0x0692, B:278:0x06d9, B:279:0x06fe, B:282:0x0708, B:284:0x0711, B:290:0x0772, B:285:0x0736, B:287:0x073c, B:288:0x0769, B:289:0x076f, B:291:0x0776, B:294:0x077e, B:296:0x078a, B:301:0x07e2, B:298:0x07af, B:299:0x07d4, B:300:0x07df, B:303:0x07e8, B:305:0x07ee, B:308:0x081a, B:306:0x0811, B:307:0x0817, B:310:0x0821, B:312:0x0827, B:315:0x0853, B:313:0x084a, B:314:0x0850, B:317:0x085a, B:319:0x0860, B:322:0x088c, B:320:0x0883, B:321:0x0889, B:324:0x0893, B:326:0x089b, B:329:0x08c9, B:327:0x08c0, B:328:0x08c6, B:331:0x08d0, B:333:0x08d6, B:336:0x0902, B:334:0x08f9, B:335:0x08ff, B:338:0x0909, B:340:0x090f, B:343:0x093b, B:341:0x0932, B:342:0x0938, B:345:0x0942, B:347:0x0948, B:350:0x0974, B:348:0x096b, B:349:0x0971, B:352:0x097b, B:354:0x0981, B:357:0x09ad, B:355:0x09a4, B:356:0x09aa, B:359:0x09b4, B:361:0x09ba, B:364:0x09e6, B:362:0x09dd, B:363:0x09e3, B:366:0x09ed, B:368:0x09f3, B:371:0x0a21, B:369:0x0a18, B:370:0x0a1e, B:374:0x0a2a, B:379:0x0a4d, B:383:0x0a7a, B:381:0x0a72, B:377:0x0a3b, B:382:0x0a76, B:386:0x0a84, B:388:0x0a96, B:397:0x0afa, B:390:0x0abb, B:391:0x0ae0, B:393:0x0ae4, B:395:0x0aee, B:394:0x0ae8, B:396:0x0af7, B:400:0x0b02, B:401:0x0b07, B:404:0x0b14, B:406:0x0b1a, B:407:0x0b41, B:409:0x0b4a, B:410:0x0b73, B:412:0x0b79, B:413:0x0baa, B:415:0x0bb8, B:416:0x0bc2, B:418:0x0bc9, B:417:0x0bc6, B:421:0x0bd3, B:423:0x0bd9, B:424:0x0c00, B:425:0x0c09, B:428:0x0c13, B:430:0x0c1b, B:433:0x0c50, B:431:0x0c28, B:432:0x0c4d, B:436:0x0c8a, B:437:0x0c95), top: B:484:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0428 A[Catch: IOException -> 0x0c96, XmlPullParserException -> 0x0c98, all -> 0x0d21, TryCatch #1 {all -> 0x0d21, blocks: (B:434:0x0c54, B:169:0x028f, B:171:0x0295, B:174:0x02c1, B:172:0x02b8, B:173:0x02be, B:451:0x0cb1, B:454:0x0cba, B:175:0x02c6, B:177:0x02cc, B:179:0x02f4, B:178:0x02ef, B:180:0x02f9, B:182:0x0316, B:200:0x03c3, B:183:0x033c, B:185:0x0342, B:186:0x0367, B:188:0x036d, B:189:0x0392, B:191:0x039a, B:193:0x039e, B:194:0x03a5, B:196:0x03af, B:199:0x03c0, B:197:0x03ba, B:201:0x03c8, B:202:0x03fa, B:203:0x03fb, B:204:0x041e, B:205:0x041f, B:206:0x0428, B:208:0x042e, B:210:0x0456, B:209:0x0451, B:211:0x045b, B:213:0x0461, B:215:0x0489, B:214:0x0484, B:217:0x0490, B:219:0x0499, B:220:0x04c2, B:222:0x04ca, B:223:0x04f3, B:225:0x0505, B:226:0x050f, B:228:0x0532, B:227:0x052f, B:230:0x0539, B:232:0x053f, B:235:0x056b, B:233:0x0562, B:234:0x0568, B:237:0x0572, B:238:0x0577, B:240:0x0581, B:242:0x05a2, B:247:0x05c7, B:249:0x0607, B:251:0x0612, B:253:0x061d, B:254:0x0626, B:255:0x062f, B:257:0x0639, B:259:0x063f, B:262:0x066b, B:260:0x0662, B:261:0x0668, B:264:0x0672, B:268:0x0681, B:270:0x068d, B:274:0x06bc, B:276:0x06c6, B:277:0x06d0, B:280:0x0701, B:272:0x0692, B:278:0x06d9, B:279:0x06fe, B:282:0x0708, B:284:0x0711, B:290:0x0772, B:285:0x0736, B:287:0x073c, B:288:0x0769, B:289:0x076f, B:291:0x0776, B:294:0x077e, B:296:0x078a, B:301:0x07e2, B:298:0x07af, B:299:0x07d4, B:300:0x07df, B:303:0x07e8, B:305:0x07ee, B:308:0x081a, B:306:0x0811, B:307:0x0817, B:310:0x0821, B:312:0x0827, B:315:0x0853, B:313:0x084a, B:314:0x0850, B:317:0x085a, B:319:0x0860, B:322:0x088c, B:320:0x0883, B:321:0x0889, B:324:0x0893, B:326:0x089b, B:329:0x08c9, B:327:0x08c0, B:328:0x08c6, B:331:0x08d0, B:333:0x08d6, B:336:0x0902, B:334:0x08f9, B:335:0x08ff, B:338:0x0909, B:340:0x090f, B:343:0x093b, B:341:0x0932, B:342:0x0938, B:345:0x0942, B:347:0x0948, B:350:0x0974, B:348:0x096b, B:349:0x0971, B:352:0x097b, B:354:0x0981, B:357:0x09ad, B:355:0x09a4, B:356:0x09aa, B:359:0x09b4, B:361:0x09ba, B:364:0x09e6, B:362:0x09dd, B:363:0x09e3, B:366:0x09ed, B:368:0x09f3, B:371:0x0a21, B:369:0x0a18, B:370:0x0a1e, B:374:0x0a2a, B:379:0x0a4d, B:383:0x0a7a, B:381:0x0a72, B:377:0x0a3b, B:382:0x0a76, B:386:0x0a84, B:388:0x0a96, B:397:0x0afa, B:390:0x0abb, B:391:0x0ae0, B:393:0x0ae4, B:395:0x0aee, B:394:0x0ae8, B:396:0x0af7, B:400:0x0b02, B:401:0x0b07, B:404:0x0b14, B:406:0x0b1a, B:407:0x0b41, B:409:0x0b4a, B:410:0x0b73, B:412:0x0b79, B:413:0x0baa, B:415:0x0bb8, B:416:0x0bc2, B:418:0x0bc9, B:417:0x0bc6, B:421:0x0bd3, B:423:0x0bd9, B:424:0x0c00, B:425:0x0c09, B:428:0x0c13, B:430:0x0c1b, B:433:0x0c50, B:431:0x0c28, B:432:0x0c4d, B:436:0x0c8a, B:437:0x0c95), top: B:484:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x045b A[Catch: IOException -> 0x0c96, XmlPullParserException -> 0x0c98, all -> 0x0d21, TryCatch #1 {all -> 0x0d21, blocks: (B:434:0x0c54, B:169:0x028f, B:171:0x0295, B:174:0x02c1, B:172:0x02b8, B:173:0x02be, B:451:0x0cb1, B:454:0x0cba, B:175:0x02c6, B:177:0x02cc, B:179:0x02f4, B:178:0x02ef, B:180:0x02f9, B:182:0x0316, B:200:0x03c3, B:183:0x033c, B:185:0x0342, B:186:0x0367, B:188:0x036d, B:189:0x0392, B:191:0x039a, B:193:0x039e, B:194:0x03a5, B:196:0x03af, B:199:0x03c0, B:197:0x03ba, B:201:0x03c8, B:202:0x03fa, B:203:0x03fb, B:204:0x041e, B:205:0x041f, B:206:0x0428, B:208:0x042e, B:210:0x0456, B:209:0x0451, B:211:0x045b, B:213:0x0461, B:215:0x0489, B:214:0x0484, B:217:0x0490, B:219:0x0499, B:220:0x04c2, B:222:0x04ca, B:223:0x04f3, B:225:0x0505, B:226:0x050f, B:228:0x0532, B:227:0x052f, B:230:0x0539, B:232:0x053f, B:235:0x056b, B:233:0x0562, B:234:0x0568, B:237:0x0572, B:238:0x0577, B:240:0x0581, B:242:0x05a2, B:247:0x05c7, B:249:0x0607, B:251:0x0612, B:253:0x061d, B:254:0x0626, B:255:0x062f, B:257:0x0639, B:259:0x063f, B:262:0x066b, B:260:0x0662, B:261:0x0668, B:264:0x0672, B:268:0x0681, B:270:0x068d, B:274:0x06bc, B:276:0x06c6, B:277:0x06d0, B:280:0x0701, B:272:0x0692, B:278:0x06d9, B:279:0x06fe, B:282:0x0708, B:284:0x0711, B:290:0x0772, B:285:0x0736, B:287:0x073c, B:288:0x0769, B:289:0x076f, B:291:0x0776, B:294:0x077e, B:296:0x078a, B:301:0x07e2, B:298:0x07af, B:299:0x07d4, B:300:0x07df, B:303:0x07e8, B:305:0x07ee, B:308:0x081a, B:306:0x0811, B:307:0x0817, B:310:0x0821, B:312:0x0827, B:315:0x0853, B:313:0x084a, B:314:0x0850, B:317:0x085a, B:319:0x0860, B:322:0x088c, B:320:0x0883, B:321:0x0889, B:324:0x0893, B:326:0x089b, B:329:0x08c9, B:327:0x08c0, B:328:0x08c6, B:331:0x08d0, B:333:0x08d6, B:336:0x0902, B:334:0x08f9, B:335:0x08ff, B:338:0x0909, B:340:0x090f, B:343:0x093b, B:341:0x0932, B:342:0x0938, B:345:0x0942, B:347:0x0948, B:350:0x0974, B:348:0x096b, B:349:0x0971, B:352:0x097b, B:354:0x0981, B:357:0x09ad, B:355:0x09a4, B:356:0x09aa, B:359:0x09b4, B:361:0x09ba, B:364:0x09e6, B:362:0x09dd, B:363:0x09e3, B:366:0x09ed, B:368:0x09f3, B:371:0x0a21, B:369:0x0a18, B:370:0x0a1e, B:374:0x0a2a, B:379:0x0a4d, B:383:0x0a7a, B:381:0x0a72, B:377:0x0a3b, B:382:0x0a76, B:386:0x0a84, B:388:0x0a96, B:397:0x0afa, B:390:0x0abb, B:391:0x0ae0, B:393:0x0ae4, B:395:0x0aee, B:394:0x0ae8, B:396:0x0af7, B:400:0x0b02, B:401:0x0b07, B:404:0x0b14, B:406:0x0b1a, B:407:0x0b41, B:409:0x0b4a, B:410:0x0b73, B:412:0x0b79, B:413:0x0baa, B:415:0x0bb8, B:416:0x0bc2, B:418:0x0bc9, B:417:0x0bc6, B:421:0x0bd3, B:423:0x0bd9, B:424:0x0c00, B:425:0x0c09, B:428:0x0c13, B:430:0x0c1b, B:433:0x0c50, B:431:0x0c28, B:432:0x0c4d, B:436:0x0c8a, B:437:0x0c95), top: B:484:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0570  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x057f  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0607 A[Catch: IOException -> 0x0c96, XmlPullParserException -> 0x0c98, all -> 0x0d21, TryCatch #1 {all -> 0x0d21, blocks: (B:434:0x0c54, B:169:0x028f, B:171:0x0295, B:174:0x02c1, B:172:0x02b8, B:173:0x02be, B:451:0x0cb1, B:454:0x0cba, B:175:0x02c6, B:177:0x02cc, B:179:0x02f4, B:178:0x02ef, B:180:0x02f9, B:182:0x0316, B:200:0x03c3, B:183:0x033c, B:185:0x0342, B:186:0x0367, B:188:0x036d, B:189:0x0392, B:191:0x039a, B:193:0x039e, B:194:0x03a5, B:196:0x03af, B:199:0x03c0, B:197:0x03ba, B:201:0x03c8, B:202:0x03fa, B:203:0x03fb, B:204:0x041e, B:205:0x041f, B:206:0x0428, B:208:0x042e, B:210:0x0456, B:209:0x0451, B:211:0x045b, B:213:0x0461, B:215:0x0489, B:214:0x0484, B:217:0x0490, B:219:0x0499, B:220:0x04c2, B:222:0x04ca, B:223:0x04f3, B:225:0x0505, B:226:0x050f, B:228:0x0532, B:227:0x052f, B:230:0x0539, B:232:0x053f, B:235:0x056b, B:233:0x0562, B:234:0x0568, B:237:0x0572, B:238:0x0577, B:240:0x0581, B:242:0x05a2, B:247:0x05c7, B:249:0x0607, B:251:0x0612, B:253:0x061d, B:254:0x0626, B:255:0x062f, B:257:0x0639, B:259:0x063f, B:262:0x066b, B:260:0x0662, B:261:0x0668, B:264:0x0672, B:268:0x0681, B:270:0x068d, B:274:0x06bc, B:276:0x06c6, B:277:0x06d0, B:280:0x0701, B:272:0x0692, B:278:0x06d9, B:279:0x06fe, B:282:0x0708, B:284:0x0711, B:290:0x0772, B:285:0x0736, B:287:0x073c, B:288:0x0769, B:289:0x076f, B:291:0x0776, B:294:0x077e, B:296:0x078a, B:301:0x07e2, B:298:0x07af, B:299:0x07d4, B:300:0x07df, B:303:0x07e8, B:305:0x07ee, B:308:0x081a, B:306:0x0811, B:307:0x0817, B:310:0x0821, B:312:0x0827, B:315:0x0853, B:313:0x084a, B:314:0x0850, B:317:0x085a, B:319:0x0860, B:322:0x088c, B:320:0x0883, B:321:0x0889, B:324:0x0893, B:326:0x089b, B:329:0x08c9, B:327:0x08c0, B:328:0x08c6, B:331:0x08d0, B:333:0x08d6, B:336:0x0902, B:334:0x08f9, B:335:0x08ff, B:338:0x0909, B:340:0x090f, B:343:0x093b, B:341:0x0932, B:342:0x0938, B:345:0x0942, B:347:0x0948, B:350:0x0974, B:348:0x096b, B:349:0x0971, B:352:0x097b, B:354:0x0981, B:357:0x09ad, B:355:0x09a4, B:356:0x09aa, B:359:0x09b4, B:361:0x09ba, B:364:0x09e6, B:362:0x09dd, B:363:0x09e3, B:366:0x09ed, B:368:0x09f3, B:371:0x0a21, B:369:0x0a18, B:370:0x0a1e, B:374:0x0a2a, B:379:0x0a4d, B:383:0x0a7a, B:381:0x0a72, B:377:0x0a3b, B:382:0x0a76, B:386:0x0a84, B:388:0x0a96, B:397:0x0afa, B:390:0x0abb, B:391:0x0ae0, B:393:0x0ae4, B:395:0x0aee, B:394:0x0ae8, B:396:0x0af7, B:400:0x0b02, B:401:0x0b07, B:404:0x0b14, B:406:0x0b1a, B:407:0x0b41, B:409:0x0b4a, B:410:0x0b73, B:412:0x0b79, B:413:0x0baa, B:415:0x0bb8, B:416:0x0bc2, B:418:0x0bc9, B:417:0x0bc6, B:421:0x0bd3, B:423:0x0bd9, B:424:0x0c00, B:425:0x0c09, B:428:0x0c13, B:430:0x0c1b, B:433:0x0c50, B:431:0x0c28, B:432:0x0c4d, B:436:0x0c8a, B:437:0x0c95), top: B:484:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0610  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0637  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0670  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0706  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0776 A[Catch: IOException -> 0x0c96, XmlPullParserException -> 0x0c98, all -> 0x0d21, TryCatch #1 {all -> 0x0d21, blocks: (B:434:0x0c54, B:169:0x028f, B:171:0x0295, B:174:0x02c1, B:172:0x02b8, B:173:0x02be, B:451:0x0cb1, B:454:0x0cba, B:175:0x02c6, B:177:0x02cc, B:179:0x02f4, B:178:0x02ef, B:180:0x02f9, B:182:0x0316, B:200:0x03c3, B:183:0x033c, B:185:0x0342, B:186:0x0367, B:188:0x036d, B:189:0x0392, B:191:0x039a, B:193:0x039e, B:194:0x03a5, B:196:0x03af, B:199:0x03c0, B:197:0x03ba, B:201:0x03c8, B:202:0x03fa, B:203:0x03fb, B:204:0x041e, B:205:0x041f, B:206:0x0428, B:208:0x042e, B:210:0x0456, B:209:0x0451, B:211:0x045b, B:213:0x0461, B:215:0x0489, B:214:0x0484, B:217:0x0490, B:219:0x0499, B:220:0x04c2, B:222:0x04ca, B:223:0x04f3, B:225:0x0505, B:226:0x050f, B:228:0x0532, B:227:0x052f, B:230:0x0539, B:232:0x053f, B:235:0x056b, B:233:0x0562, B:234:0x0568, B:237:0x0572, B:238:0x0577, B:240:0x0581, B:242:0x05a2, B:247:0x05c7, B:249:0x0607, B:251:0x0612, B:253:0x061d, B:254:0x0626, B:255:0x062f, B:257:0x0639, B:259:0x063f, B:262:0x066b, B:260:0x0662, B:261:0x0668, B:264:0x0672, B:268:0x0681, B:270:0x068d, B:274:0x06bc, B:276:0x06c6, B:277:0x06d0, B:280:0x0701, B:272:0x0692, B:278:0x06d9, B:279:0x06fe, B:282:0x0708, B:284:0x0711, B:290:0x0772, B:285:0x0736, B:287:0x073c, B:288:0x0769, B:289:0x076f, B:291:0x0776, B:294:0x077e, B:296:0x078a, B:301:0x07e2, B:298:0x07af, B:299:0x07d4, B:300:0x07df, B:303:0x07e8, B:305:0x07ee, B:308:0x081a, B:306:0x0811, B:307:0x0817, B:310:0x0821, B:312:0x0827, B:315:0x0853, B:313:0x084a, B:314:0x0850, B:317:0x085a, B:319:0x0860, B:322:0x088c, B:320:0x0883, B:321:0x0889, B:324:0x0893, B:326:0x089b, B:329:0x08c9, B:327:0x08c0, B:328:0x08c6, B:331:0x08d0, B:333:0x08d6, B:336:0x0902, B:334:0x08f9, B:335:0x08ff, B:338:0x0909, B:340:0x090f, B:343:0x093b, B:341:0x0932, B:342:0x0938, B:345:0x0942, B:347:0x0948, B:350:0x0974, B:348:0x096b, B:349:0x0971, B:352:0x097b, B:354:0x0981, B:357:0x09ad, B:355:0x09a4, B:356:0x09aa, B:359:0x09b4, B:361:0x09ba, B:364:0x09e6, B:362:0x09dd, B:363:0x09e3, B:366:0x09ed, B:368:0x09f3, B:371:0x0a21, B:369:0x0a18, B:370:0x0a1e, B:374:0x0a2a, B:379:0x0a4d, B:383:0x0a7a, B:381:0x0a72, B:377:0x0a3b, B:382:0x0a76, B:386:0x0a84, B:388:0x0a96, B:397:0x0afa, B:390:0x0abb, B:391:0x0ae0, B:393:0x0ae4, B:395:0x0aee, B:394:0x0ae8, B:396:0x0af7, B:400:0x0b02, B:401:0x0b07, B:404:0x0b14, B:406:0x0b1a, B:407:0x0b41, B:409:0x0b4a, B:410:0x0b73, B:412:0x0b79, B:413:0x0baa, B:415:0x0bb8, B:416:0x0bc2, B:418:0x0bc9, B:417:0x0bc6, B:421:0x0bd3, B:423:0x0bd9, B:424:0x0c00, B:425:0x0c09, B:428:0x0c13, B:430:0x0c1b, B:433:0x0c50, B:431:0x0c28, B:432:0x0c4d, B:436:0x0c8a, B:437:0x0c95), top: B:484:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:293:0x077c  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x07e6  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x081f  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0858  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0891  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x08ce  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0907  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0940  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0979  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x09b2  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x09eb  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0a26  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x0a7f  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x0aff  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0b0f  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0bce  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0c10  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0cc6  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x0cd2  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0cd9  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x0ce4  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0cea  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0cf5  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0d05  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0d16 A[LOOP:2: B:474:0x0d10->B:476:0x0d16, LOOP_END] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readPermissionsFromXml(java.io.File r28, int r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 3580
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.SystemConfig.readPermissionsFromXml(java.io.File, int):void");
    }

    private void addFeature(String str, int i) {
        if (PackageManager.FEATURE_WIFI_AWARE.equals(str) && !SystemProperties.getBoolean("ro.vendor.wlan.aware", true)) {
            Slog.w(TAG, "<" + str + "> not supported due to ro.vendor.wlan.aware is false");
            return;
        }
        FeatureInfo featureInfo = this.mAvailableFeatures.get(str);
        if (featureInfo == null) {
            FeatureInfo featureInfo2 = new FeatureInfo();
            featureInfo2.name = str;
            featureInfo2.version = i;
            this.mAvailableFeatures.put(str, featureInfo2);
            return;
        }
        featureInfo.version = Math.max(featureInfo.version, i);
    }

    private void removeFeature(String str) {
        if (this.mAvailableFeatures.remove(str) != null) {
            Slog.d(TAG, "Removed unavailable feature " + str);
        }
    }

    void readPermission(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        if (this.mPermissions.containsKey(str)) {
            throw new IllegalStateException("Duplicate permission definition for " + str);
        }
        PermissionEntry permissionEntry = new PermissionEntry(str, XmlUtils.readBooleanAttribute(xmlPullParser, "perUser", false));
        this.mPermissions.put(str, permissionEntry);
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
            if (next != 3 && next != 4) {
                if ("group".equals(xmlPullParser.getName())) {
                    String attributeValue = xmlPullParser.getAttributeValue(null, "gid");
                    if (attributeValue != null) {
                        permissionEntry.gids = ArrayUtils.appendInt(permissionEntry.gids, Process.getGidForName(attributeValue));
                    } else {
                        Slog.w(TAG, "<group> without gid at " + xmlPullParser.getPositionDescription());
                    }
                }
                XmlUtils.skipCurrentTag(xmlPullParser);
            }
        }
    }

    private void readPrivAppPermissions(XmlPullParser xmlPullParser, ArrayMap<String, ArraySet<String>> arrayMap, ArrayMap<String, ArraySet<String>> arrayMap2) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "package");
        if (TextUtils.isEmpty(attributeValue)) {
            Slog.w(TAG, "package is required for <privapp-permissions> in " + xmlPullParser.getPositionDescription());
            return;
        }
        ArraySet<String> arraySet = arrayMap.get(attributeValue);
        if (arraySet == null) {
            arraySet = new ArraySet<>();
        }
        ArraySet<String> arraySet2 = arrayMap2.get(attributeValue);
        int depth = xmlPullParser.getDepth();
        while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
            String name = xmlPullParser.getName();
            if ("permission".equals(name)) {
                String attributeValue2 = xmlPullParser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(attributeValue2)) {
                    Slog.w(TAG, "name is required for <permission> in " + xmlPullParser.getPositionDescription());
                } else {
                    arraySet.add(attributeValue2);
                }
            } else if ("deny-permission".equals(name)) {
                String attributeValue3 = xmlPullParser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(attributeValue3)) {
                    Slog.w(TAG, "name is required for <deny-permission> in " + xmlPullParser.getPositionDescription());
                } else {
                    if (arraySet2 == null) {
                        arraySet2 = new ArraySet<>();
                    }
                    arraySet2.add(attributeValue3);
                }
            }
        }
        arrayMap.put(attributeValue, arraySet);
        if (arraySet2 != null) {
            arrayMap2.put(attributeValue, arraySet2);
        }
    }

    private void readInstallInUserType(XmlPullParser xmlPullParser, Map<String, Set<String>> map, Map<String, Set<String>> map2) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "package");
        if (TextUtils.isEmpty(attributeValue)) {
            Slog.w(TAG, "package is required for <install-in-user-type> in " + xmlPullParser.getPositionDescription());
            return;
        }
        Set<String> arraySet = map.get(attributeValue);
        Set<String> arraySet2 = map2.get(attributeValue);
        int depth = xmlPullParser.getDepth();
        while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
            String name = xmlPullParser.getName();
            if ("install-in".equals(name)) {
                String attributeValue2 = xmlPullParser.getAttributeValue(null, "user-type");
                if (TextUtils.isEmpty(attributeValue2)) {
                    Slog.w(TAG, "user-type is required for <install-in-user-type> in " + xmlPullParser.getPositionDescription());
                } else {
                    if (arraySet == null) {
                        arraySet = new ArraySet<>();
                        map.put(attributeValue, arraySet);
                    }
                    arraySet.add(attributeValue2);
                }
            } else if ("do-not-install-in".equals(name)) {
                String attributeValue3 = xmlPullParser.getAttributeValue(null, "user-type");
                if (TextUtils.isEmpty(attributeValue3)) {
                    Slog.w(TAG, "user-type is required for <install-in-user-type> in " + xmlPullParser.getPositionDescription());
                } else {
                    if (arraySet2 == null) {
                        arraySet2 = new ArraySet<>();
                        map2.put(attributeValue, arraySet2);
                    }
                    arraySet2.add(attributeValue3);
                }
            } else {
                Slog.w(TAG, "unrecognized tag in <install-in-user-type> in " + xmlPullParser.getPositionDescription());
            }
        }
    }

    void readOemPermissions(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "package");
        if (TextUtils.isEmpty(attributeValue)) {
            Slog.w(TAG, "package is required for <oem-permissions> in " + xmlPullParser.getPositionDescription());
            return;
        }
        ArrayMap<String, Boolean> arrayMap = this.mOemPermissions.get(attributeValue);
        if (arrayMap == null) {
            arrayMap = new ArrayMap<>();
        }
        int depth = xmlPullParser.getDepth();
        while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
            String name = xmlPullParser.getName();
            if ("permission".equals(name)) {
                String attributeValue2 = xmlPullParser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(attributeValue2)) {
                    Slog.w(TAG, "name is required for <permission> in " + xmlPullParser.getPositionDescription());
                } else {
                    arrayMap.put(attributeValue2, Boolean.TRUE);
                }
            } else if ("deny-permission".equals(name)) {
                String attributeValue3 = xmlPullParser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(attributeValue3)) {
                    Slog.w(TAG, "name is required for <deny-permission> in " + xmlPullParser.getPositionDescription());
                } else {
                    arrayMap.put(attributeValue3, Boolean.FALSE);
                }
            }
        }
        this.mOemPermissions.put(attributeValue, arrayMap);
    }

    private void readSplitPermission(XmlPullParser xmlPullParser, File file) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "name");
        if (attributeValue == null) {
            Slog.w(TAG, "<split-permission> without name in " + file + " at " + xmlPullParser.getPositionDescription());
            XmlUtils.skipCurrentTag(xmlPullParser);
            return;
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "targetSdk");
        int i = 10001;
        if (!TextUtils.isEmpty(attributeValue2)) {
            try {
                i = Integer.parseInt(attributeValue2);
            } catch (NumberFormatException unused) {
                Slog.w(TAG, "<split-permission> targetSdk not an integer in " + file + " at " + xmlPullParser.getPositionDescription());
                XmlUtils.skipCurrentTag(xmlPullParser);
                return;
            }
        }
        int depth = xmlPullParser.getDepth();
        ArrayList arrayList = new ArrayList();
        while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
            if ("new-permission".equals(xmlPullParser.getName())) {
                String attributeValue3 = xmlPullParser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(attributeValue3)) {
                    Slog.w(TAG, "name is required for <new-permission> in " + xmlPullParser.getPositionDescription());
                } else {
                    arrayList.add(attributeValue3);
                }
            } else {
                XmlUtils.skipCurrentTag(xmlPullParser);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.mSplitPermissions.add(new PermissionManager.SplitPermissionInfo(attributeValue, arrayList, i));
    }

    private void readComponentOverrides(XmlPullParser xmlPullParser, File file) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "package");
        if (attributeValue == null) {
            Slog.w(TAG, "<component-override> without package in " + file + " at " + xmlPullParser.getPositionDescription());
            return;
        }
        String strIntern = attributeValue.intern();
        int depth = xmlPullParser.getDepth();
        while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
            if (CardEmulation.EXTRA_SERVICE_COMPONENT.equals(xmlPullParser.getName())) {
                String attributeValue2 = xmlPullParser.getAttributeValue(null, "class");
                String attributeValue3 = xmlPullParser.getAttributeValue(null, "enabled");
                if (attributeValue2 == null) {
                    Slog.w(TAG, "<component> without class in " + file + " at " + xmlPullParser.getPositionDescription());
                    return;
                }
                if (attributeValue3 == null) {
                    Slog.w(TAG, "<component> without enabled in " + file + " at " + xmlPullParser.getPositionDescription());
                    return;
                }
                if (attributeValue2.startsWith(MediaMetrics.SEPARATOR)) {
                    attributeValue2 = strIntern + attributeValue2;
                }
                String strIntern2 = attributeValue2.intern();
                ArrayMap<String, Boolean> arrayMap = this.mPackageComponentEnabledState.get(strIntern);
                if (arrayMap == null) {
                    arrayMap = new ArrayMap<>();
                    this.mPackageComponentEnabledState.put(strIntern, arrayMap);
                }
                arrayMap.put(strIntern2, Boolean.valueOf(!"false".equals(attributeValue3)));
            }
        }
    }

    private static boolean isSystemProcess() {
        return Process.myUid() == 1000;
    }
}
