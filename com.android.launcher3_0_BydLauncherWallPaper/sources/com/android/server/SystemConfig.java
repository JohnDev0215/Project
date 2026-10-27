package com.android.server;

import android.content.ComponentName;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
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
                } else if (!file3.canRead()) {
                    Slog.w(TAG, "Permissions library file " + file3 + " cannot be read");
                } else {
                    readPermissionsFromXml(file3, i);
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Not initialized variable reg: 24, insn: 0x0d92: MOVE (r3 I:??[OBJECT, ARRAY]) = (r24 I:??[OBJECT, ARRAY]), block:B:441:0x0d91 */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x02d0 A[Catch: IOException -> 0x0d8e, XmlPullParserException -> 0x0d90, all -> 0x0e19, TryCatch #7 {all -> 0x0e19, blocks: (B:434:0x0d46, B:169:0x0293, B:171:0x0299, B:174:0x02cb, B:172:0x02c2, B:173:0x02c8, B:451:0x0da9, B:454:0x0db2, B:175:0x02d0, B:177:0x02d6, B:179:0x0304, B:178:0x02ff, B:180:0x0309, B:182:0x0325, B:200:0x03e4, B:183:0x0351, B:185:0x0357, B:186:0x0382, B:188:0x0388, B:189:0x03b3, B:191:0x03bb, B:193:0x03bf, B:194:0x03c6, B:196:0x03d0, B:199:0x03e1, B:197:0x03db, B:201:0x03e9, B:202:0x0423, B:203:0x0424, B:204:0x044c, B:205:0x044d, B:206:0x0456, B:208:0x045c, B:210:0x048a, B:209:0x0485, B:211:0x048f, B:213:0x0495, B:215:0x04c3, B:214:0x04be, B:217:0x04ca, B:219:0x04d3, B:220:0x0502, B:222:0x050a, B:223:0x0539, B:225:0x054b, B:226:0x0555, B:228:0x057c, B:227:0x0579, B:230:0x0583, B:232:0x0589, B:235:0x05bb, B:233:0x05b2, B:234:0x05b8, B:237:0x05c2, B:238:0x05c7, B:240:0x05d1, B:242:0x05f4, B:247:0x061b, B:249:0x065f, B:251:0x066a, B:253:0x0675, B:254:0x067e, B:255:0x0687, B:257:0x0691, B:259:0x0697, B:262:0x06c9, B:260:0x06c0, B:261:0x06c6, B:264:0x06d0, B:268:0x06df, B:270:0x06eb, B:274:0x0720, B:276:0x072a, B:277:0x0734, B:280:0x076b, B:272:0x06f0, B:278:0x073d, B:279:0x0768, B:282:0x0772, B:284:0x077b, B:290:0x07ea, B:285:0x07a6, B:287:0x07ac, B:288:0x07e1, B:289:0x07e7, B:291:0x07ee, B:294:0x07f6, B:296:0x0802, B:301:0x0866, B:298:0x082d, B:299:0x0858, B:300:0x0863, B:303:0x086c, B:305:0x0872, B:308:0x08a4, B:306:0x089b, B:307:0x08a1, B:310:0x08ab, B:312:0x08b1, B:315:0x08e3, B:313:0x08da, B:314:0x08e0, B:317:0x08ea, B:319:0x08f0, B:322:0x0922, B:320:0x0919, B:321:0x091f, B:324:0x0929, B:326:0x0931, B:329:0x0965, B:327:0x095c, B:328:0x0962, B:331:0x096c, B:333:0x0972, B:336:0x09a4, B:334:0x099b, B:335:0x09a1, B:338:0x09ab, B:340:0x09b1, B:343:0x09e3, B:341:0x09da, B:342:0x09e0, B:345:0x09ea, B:347:0x09f0, B:350:0x0a22, B:348:0x0a19, B:349:0x0a1f, B:352:0x0a29, B:354:0x0a2f, B:357:0x0a61, B:355:0x0a58, B:356:0x0a5e, B:359:0x0a68, B:361:0x0a6e, B:364:0x0aa0, B:362:0x0a97, B:363:0x0a9d, B:366:0x0aa7, B:368:0x0aad, B:371:0x0ae1, B:369:0x0ad8, B:370:0x0ade, B:374:0x0aea, B:379:0x0b0c, B:383:0x0b3f, B:381:0x0b37, B:377:0x0afb, B:382:0x0b3b, B:386:0x0b49, B:388:0x0b5b, B:397:0x0bcb, B:390:0x0b86, B:391:0x0bb1, B:393:0x0bb5, B:395:0x0bbf, B:394:0x0bb9, B:396:0x0bc8, B:400:0x0bd3, B:401:0x0bd8, B:404:0x0be5, B:406:0x0beb, B:407:0x0c18, B:409:0x0c21, B:410:0x0c50, B:412:0x0c56, B:413:0x0c8f, B:415:0x0c9d, B:416:0x0ca7, B:418:0x0cae, B:417:0x0cab, B:421:0x0cb8, B:423:0x0cbe, B:424:0x0ceb, B:425:0x0cf4, B:428:0x0cff, B:430:0x0d07, B:433:0x0d42, B:431:0x0d14, B:432:0x0d3f, B:436:0x0d82, B:437:0x0d8d), top: B:488:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0309 A[Catch: IOException -> 0x0d8e, XmlPullParserException -> 0x0d90, all -> 0x0e19, TryCatch #7 {all -> 0x0e19, blocks: (B:434:0x0d46, B:169:0x0293, B:171:0x0299, B:174:0x02cb, B:172:0x02c2, B:173:0x02c8, B:451:0x0da9, B:454:0x0db2, B:175:0x02d0, B:177:0x02d6, B:179:0x0304, B:178:0x02ff, B:180:0x0309, B:182:0x0325, B:200:0x03e4, B:183:0x0351, B:185:0x0357, B:186:0x0382, B:188:0x0388, B:189:0x03b3, B:191:0x03bb, B:193:0x03bf, B:194:0x03c6, B:196:0x03d0, B:199:0x03e1, B:197:0x03db, B:201:0x03e9, B:202:0x0423, B:203:0x0424, B:204:0x044c, B:205:0x044d, B:206:0x0456, B:208:0x045c, B:210:0x048a, B:209:0x0485, B:211:0x048f, B:213:0x0495, B:215:0x04c3, B:214:0x04be, B:217:0x04ca, B:219:0x04d3, B:220:0x0502, B:222:0x050a, B:223:0x0539, B:225:0x054b, B:226:0x0555, B:228:0x057c, B:227:0x0579, B:230:0x0583, B:232:0x0589, B:235:0x05bb, B:233:0x05b2, B:234:0x05b8, B:237:0x05c2, B:238:0x05c7, B:240:0x05d1, B:242:0x05f4, B:247:0x061b, B:249:0x065f, B:251:0x066a, B:253:0x0675, B:254:0x067e, B:255:0x0687, B:257:0x0691, B:259:0x0697, B:262:0x06c9, B:260:0x06c0, B:261:0x06c6, B:264:0x06d0, B:268:0x06df, B:270:0x06eb, B:274:0x0720, B:276:0x072a, B:277:0x0734, B:280:0x076b, B:272:0x06f0, B:278:0x073d, B:279:0x0768, B:282:0x0772, B:284:0x077b, B:290:0x07ea, B:285:0x07a6, B:287:0x07ac, B:288:0x07e1, B:289:0x07e7, B:291:0x07ee, B:294:0x07f6, B:296:0x0802, B:301:0x0866, B:298:0x082d, B:299:0x0858, B:300:0x0863, B:303:0x086c, B:305:0x0872, B:308:0x08a4, B:306:0x089b, B:307:0x08a1, B:310:0x08ab, B:312:0x08b1, B:315:0x08e3, B:313:0x08da, B:314:0x08e0, B:317:0x08ea, B:319:0x08f0, B:322:0x0922, B:320:0x0919, B:321:0x091f, B:324:0x0929, B:326:0x0931, B:329:0x0965, B:327:0x095c, B:328:0x0962, B:331:0x096c, B:333:0x0972, B:336:0x09a4, B:334:0x099b, B:335:0x09a1, B:338:0x09ab, B:340:0x09b1, B:343:0x09e3, B:341:0x09da, B:342:0x09e0, B:345:0x09ea, B:347:0x09f0, B:350:0x0a22, B:348:0x0a19, B:349:0x0a1f, B:352:0x0a29, B:354:0x0a2f, B:357:0x0a61, B:355:0x0a58, B:356:0x0a5e, B:359:0x0a68, B:361:0x0a6e, B:364:0x0aa0, B:362:0x0a97, B:363:0x0a9d, B:366:0x0aa7, B:368:0x0aad, B:371:0x0ae1, B:369:0x0ad8, B:370:0x0ade, B:374:0x0aea, B:379:0x0b0c, B:383:0x0b3f, B:381:0x0b37, B:377:0x0afb, B:382:0x0b3b, B:386:0x0b49, B:388:0x0b5b, B:397:0x0bcb, B:390:0x0b86, B:391:0x0bb1, B:393:0x0bb5, B:395:0x0bbf, B:394:0x0bb9, B:396:0x0bc8, B:400:0x0bd3, B:401:0x0bd8, B:404:0x0be5, B:406:0x0beb, B:407:0x0c18, B:409:0x0c21, B:410:0x0c50, B:412:0x0c56, B:413:0x0c8f, B:415:0x0c9d, B:416:0x0ca7, B:418:0x0cae, B:417:0x0cab, B:421:0x0cb8, B:423:0x0cbe, B:424:0x0ceb, B:425:0x0cf4, B:428:0x0cff, B:430:0x0d07, B:433:0x0d42, B:431:0x0d14, B:432:0x0d3f, B:436:0x0d82, B:437:0x0d8d), top: B:488:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x044d A[Catch: IOException -> 0x0d8e, XmlPullParserException -> 0x0d90, all -> 0x0e19, TryCatch #7 {all -> 0x0e19, blocks: (B:434:0x0d46, B:169:0x0293, B:171:0x0299, B:174:0x02cb, B:172:0x02c2, B:173:0x02c8, B:451:0x0da9, B:454:0x0db2, B:175:0x02d0, B:177:0x02d6, B:179:0x0304, B:178:0x02ff, B:180:0x0309, B:182:0x0325, B:200:0x03e4, B:183:0x0351, B:185:0x0357, B:186:0x0382, B:188:0x0388, B:189:0x03b3, B:191:0x03bb, B:193:0x03bf, B:194:0x03c6, B:196:0x03d0, B:199:0x03e1, B:197:0x03db, B:201:0x03e9, B:202:0x0423, B:203:0x0424, B:204:0x044c, B:205:0x044d, B:206:0x0456, B:208:0x045c, B:210:0x048a, B:209:0x0485, B:211:0x048f, B:213:0x0495, B:215:0x04c3, B:214:0x04be, B:217:0x04ca, B:219:0x04d3, B:220:0x0502, B:222:0x050a, B:223:0x0539, B:225:0x054b, B:226:0x0555, B:228:0x057c, B:227:0x0579, B:230:0x0583, B:232:0x0589, B:235:0x05bb, B:233:0x05b2, B:234:0x05b8, B:237:0x05c2, B:238:0x05c7, B:240:0x05d1, B:242:0x05f4, B:247:0x061b, B:249:0x065f, B:251:0x066a, B:253:0x0675, B:254:0x067e, B:255:0x0687, B:257:0x0691, B:259:0x0697, B:262:0x06c9, B:260:0x06c0, B:261:0x06c6, B:264:0x06d0, B:268:0x06df, B:270:0x06eb, B:274:0x0720, B:276:0x072a, B:277:0x0734, B:280:0x076b, B:272:0x06f0, B:278:0x073d, B:279:0x0768, B:282:0x0772, B:284:0x077b, B:290:0x07ea, B:285:0x07a6, B:287:0x07ac, B:288:0x07e1, B:289:0x07e7, B:291:0x07ee, B:294:0x07f6, B:296:0x0802, B:301:0x0866, B:298:0x082d, B:299:0x0858, B:300:0x0863, B:303:0x086c, B:305:0x0872, B:308:0x08a4, B:306:0x089b, B:307:0x08a1, B:310:0x08ab, B:312:0x08b1, B:315:0x08e3, B:313:0x08da, B:314:0x08e0, B:317:0x08ea, B:319:0x08f0, B:322:0x0922, B:320:0x0919, B:321:0x091f, B:324:0x0929, B:326:0x0931, B:329:0x0965, B:327:0x095c, B:328:0x0962, B:331:0x096c, B:333:0x0972, B:336:0x09a4, B:334:0x099b, B:335:0x09a1, B:338:0x09ab, B:340:0x09b1, B:343:0x09e3, B:341:0x09da, B:342:0x09e0, B:345:0x09ea, B:347:0x09f0, B:350:0x0a22, B:348:0x0a19, B:349:0x0a1f, B:352:0x0a29, B:354:0x0a2f, B:357:0x0a61, B:355:0x0a58, B:356:0x0a5e, B:359:0x0a68, B:361:0x0a6e, B:364:0x0aa0, B:362:0x0a97, B:363:0x0a9d, B:366:0x0aa7, B:368:0x0aad, B:371:0x0ae1, B:369:0x0ad8, B:370:0x0ade, B:374:0x0aea, B:379:0x0b0c, B:383:0x0b3f, B:381:0x0b37, B:377:0x0afb, B:382:0x0b3b, B:386:0x0b49, B:388:0x0b5b, B:397:0x0bcb, B:390:0x0b86, B:391:0x0bb1, B:393:0x0bb5, B:395:0x0bbf, B:394:0x0bb9, B:396:0x0bc8, B:400:0x0bd3, B:401:0x0bd8, B:404:0x0be5, B:406:0x0beb, B:407:0x0c18, B:409:0x0c21, B:410:0x0c50, B:412:0x0c56, B:413:0x0c8f, B:415:0x0c9d, B:416:0x0ca7, B:418:0x0cae, B:417:0x0cab, B:421:0x0cb8, B:423:0x0cbe, B:424:0x0ceb, B:425:0x0cf4, B:428:0x0cff, B:430:0x0d07, B:433:0x0d42, B:431:0x0d14, B:432:0x0d3f, B:436:0x0d82, B:437:0x0d8d), top: B:488:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0456 A[Catch: IOException -> 0x0d8e, XmlPullParserException -> 0x0d90, all -> 0x0e19, TryCatch #7 {all -> 0x0e19, blocks: (B:434:0x0d46, B:169:0x0293, B:171:0x0299, B:174:0x02cb, B:172:0x02c2, B:173:0x02c8, B:451:0x0da9, B:454:0x0db2, B:175:0x02d0, B:177:0x02d6, B:179:0x0304, B:178:0x02ff, B:180:0x0309, B:182:0x0325, B:200:0x03e4, B:183:0x0351, B:185:0x0357, B:186:0x0382, B:188:0x0388, B:189:0x03b3, B:191:0x03bb, B:193:0x03bf, B:194:0x03c6, B:196:0x03d0, B:199:0x03e1, B:197:0x03db, B:201:0x03e9, B:202:0x0423, B:203:0x0424, B:204:0x044c, B:205:0x044d, B:206:0x0456, B:208:0x045c, B:210:0x048a, B:209:0x0485, B:211:0x048f, B:213:0x0495, B:215:0x04c3, B:214:0x04be, B:217:0x04ca, B:219:0x04d3, B:220:0x0502, B:222:0x050a, B:223:0x0539, B:225:0x054b, B:226:0x0555, B:228:0x057c, B:227:0x0579, B:230:0x0583, B:232:0x0589, B:235:0x05bb, B:233:0x05b2, B:234:0x05b8, B:237:0x05c2, B:238:0x05c7, B:240:0x05d1, B:242:0x05f4, B:247:0x061b, B:249:0x065f, B:251:0x066a, B:253:0x0675, B:254:0x067e, B:255:0x0687, B:257:0x0691, B:259:0x0697, B:262:0x06c9, B:260:0x06c0, B:261:0x06c6, B:264:0x06d0, B:268:0x06df, B:270:0x06eb, B:274:0x0720, B:276:0x072a, B:277:0x0734, B:280:0x076b, B:272:0x06f0, B:278:0x073d, B:279:0x0768, B:282:0x0772, B:284:0x077b, B:290:0x07ea, B:285:0x07a6, B:287:0x07ac, B:288:0x07e1, B:289:0x07e7, B:291:0x07ee, B:294:0x07f6, B:296:0x0802, B:301:0x0866, B:298:0x082d, B:299:0x0858, B:300:0x0863, B:303:0x086c, B:305:0x0872, B:308:0x08a4, B:306:0x089b, B:307:0x08a1, B:310:0x08ab, B:312:0x08b1, B:315:0x08e3, B:313:0x08da, B:314:0x08e0, B:317:0x08ea, B:319:0x08f0, B:322:0x0922, B:320:0x0919, B:321:0x091f, B:324:0x0929, B:326:0x0931, B:329:0x0965, B:327:0x095c, B:328:0x0962, B:331:0x096c, B:333:0x0972, B:336:0x09a4, B:334:0x099b, B:335:0x09a1, B:338:0x09ab, B:340:0x09b1, B:343:0x09e3, B:341:0x09da, B:342:0x09e0, B:345:0x09ea, B:347:0x09f0, B:350:0x0a22, B:348:0x0a19, B:349:0x0a1f, B:352:0x0a29, B:354:0x0a2f, B:357:0x0a61, B:355:0x0a58, B:356:0x0a5e, B:359:0x0a68, B:361:0x0a6e, B:364:0x0aa0, B:362:0x0a97, B:363:0x0a9d, B:366:0x0aa7, B:368:0x0aad, B:371:0x0ae1, B:369:0x0ad8, B:370:0x0ade, B:374:0x0aea, B:379:0x0b0c, B:383:0x0b3f, B:381:0x0b37, B:377:0x0afb, B:382:0x0b3b, B:386:0x0b49, B:388:0x0b5b, B:397:0x0bcb, B:390:0x0b86, B:391:0x0bb1, B:393:0x0bb5, B:395:0x0bbf, B:394:0x0bb9, B:396:0x0bc8, B:400:0x0bd3, B:401:0x0bd8, B:404:0x0be5, B:406:0x0beb, B:407:0x0c18, B:409:0x0c21, B:410:0x0c50, B:412:0x0c56, B:413:0x0c8f, B:415:0x0c9d, B:416:0x0ca7, B:418:0x0cae, B:417:0x0cab, B:421:0x0cb8, B:423:0x0cbe, B:424:0x0ceb, B:425:0x0cf4, B:428:0x0cff, B:430:0x0d07, B:433:0x0d42, B:431:0x0d14, B:432:0x0d3f, B:436:0x0d82, B:437:0x0d8d), top: B:488:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x048f A[Catch: IOException -> 0x0d8e, XmlPullParserException -> 0x0d90, all -> 0x0e19, TryCatch #7 {all -> 0x0e19, blocks: (B:434:0x0d46, B:169:0x0293, B:171:0x0299, B:174:0x02cb, B:172:0x02c2, B:173:0x02c8, B:451:0x0da9, B:454:0x0db2, B:175:0x02d0, B:177:0x02d6, B:179:0x0304, B:178:0x02ff, B:180:0x0309, B:182:0x0325, B:200:0x03e4, B:183:0x0351, B:185:0x0357, B:186:0x0382, B:188:0x0388, B:189:0x03b3, B:191:0x03bb, B:193:0x03bf, B:194:0x03c6, B:196:0x03d0, B:199:0x03e1, B:197:0x03db, B:201:0x03e9, B:202:0x0423, B:203:0x0424, B:204:0x044c, B:205:0x044d, B:206:0x0456, B:208:0x045c, B:210:0x048a, B:209:0x0485, B:211:0x048f, B:213:0x0495, B:215:0x04c3, B:214:0x04be, B:217:0x04ca, B:219:0x04d3, B:220:0x0502, B:222:0x050a, B:223:0x0539, B:225:0x054b, B:226:0x0555, B:228:0x057c, B:227:0x0579, B:230:0x0583, B:232:0x0589, B:235:0x05bb, B:233:0x05b2, B:234:0x05b8, B:237:0x05c2, B:238:0x05c7, B:240:0x05d1, B:242:0x05f4, B:247:0x061b, B:249:0x065f, B:251:0x066a, B:253:0x0675, B:254:0x067e, B:255:0x0687, B:257:0x0691, B:259:0x0697, B:262:0x06c9, B:260:0x06c0, B:261:0x06c6, B:264:0x06d0, B:268:0x06df, B:270:0x06eb, B:274:0x0720, B:276:0x072a, B:277:0x0734, B:280:0x076b, B:272:0x06f0, B:278:0x073d, B:279:0x0768, B:282:0x0772, B:284:0x077b, B:290:0x07ea, B:285:0x07a6, B:287:0x07ac, B:288:0x07e1, B:289:0x07e7, B:291:0x07ee, B:294:0x07f6, B:296:0x0802, B:301:0x0866, B:298:0x082d, B:299:0x0858, B:300:0x0863, B:303:0x086c, B:305:0x0872, B:308:0x08a4, B:306:0x089b, B:307:0x08a1, B:310:0x08ab, B:312:0x08b1, B:315:0x08e3, B:313:0x08da, B:314:0x08e0, B:317:0x08ea, B:319:0x08f0, B:322:0x0922, B:320:0x0919, B:321:0x091f, B:324:0x0929, B:326:0x0931, B:329:0x0965, B:327:0x095c, B:328:0x0962, B:331:0x096c, B:333:0x0972, B:336:0x09a4, B:334:0x099b, B:335:0x09a1, B:338:0x09ab, B:340:0x09b1, B:343:0x09e3, B:341:0x09da, B:342:0x09e0, B:345:0x09ea, B:347:0x09f0, B:350:0x0a22, B:348:0x0a19, B:349:0x0a1f, B:352:0x0a29, B:354:0x0a2f, B:357:0x0a61, B:355:0x0a58, B:356:0x0a5e, B:359:0x0a68, B:361:0x0a6e, B:364:0x0aa0, B:362:0x0a97, B:363:0x0a9d, B:366:0x0aa7, B:368:0x0aad, B:371:0x0ae1, B:369:0x0ad8, B:370:0x0ade, B:374:0x0aea, B:379:0x0b0c, B:383:0x0b3f, B:381:0x0b37, B:377:0x0afb, B:382:0x0b3b, B:386:0x0b49, B:388:0x0b5b, B:397:0x0bcb, B:390:0x0b86, B:391:0x0bb1, B:393:0x0bb5, B:395:0x0bbf, B:394:0x0bb9, B:396:0x0bc8, B:400:0x0bd3, B:401:0x0bd8, B:404:0x0be5, B:406:0x0beb, B:407:0x0c18, B:409:0x0c21, B:410:0x0c50, B:412:0x0c56, B:413:0x0c8f, B:415:0x0c9d, B:416:0x0ca7, B:418:0x0cae, B:417:0x0cab, B:421:0x0cb8, B:423:0x0cbe, B:424:0x0ceb, B:425:0x0cf4, B:428:0x0cff, B:430:0x0d07, B:433:0x0d42, B:431:0x0d14, B:432:0x0d3f, B:436:0x0d82, B:437:0x0d8d), top: B:488:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x05c0  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x05cf  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x068f  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x06ce  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x07ee A[Catch: IOException -> 0x0d8e, XmlPullParserException -> 0x0d90, all -> 0x0e19, TryCatch #7 {all -> 0x0e19, blocks: (B:434:0x0d46, B:169:0x0293, B:171:0x0299, B:174:0x02cb, B:172:0x02c2, B:173:0x02c8, B:451:0x0da9, B:454:0x0db2, B:175:0x02d0, B:177:0x02d6, B:179:0x0304, B:178:0x02ff, B:180:0x0309, B:182:0x0325, B:200:0x03e4, B:183:0x0351, B:185:0x0357, B:186:0x0382, B:188:0x0388, B:189:0x03b3, B:191:0x03bb, B:193:0x03bf, B:194:0x03c6, B:196:0x03d0, B:199:0x03e1, B:197:0x03db, B:201:0x03e9, B:202:0x0423, B:203:0x0424, B:204:0x044c, B:205:0x044d, B:206:0x0456, B:208:0x045c, B:210:0x048a, B:209:0x0485, B:211:0x048f, B:213:0x0495, B:215:0x04c3, B:214:0x04be, B:217:0x04ca, B:219:0x04d3, B:220:0x0502, B:222:0x050a, B:223:0x0539, B:225:0x054b, B:226:0x0555, B:228:0x057c, B:227:0x0579, B:230:0x0583, B:232:0x0589, B:235:0x05bb, B:233:0x05b2, B:234:0x05b8, B:237:0x05c2, B:238:0x05c7, B:240:0x05d1, B:242:0x05f4, B:247:0x061b, B:249:0x065f, B:251:0x066a, B:253:0x0675, B:254:0x067e, B:255:0x0687, B:257:0x0691, B:259:0x0697, B:262:0x06c9, B:260:0x06c0, B:261:0x06c6, B:264:0x06d0, B:268:0x06df, B:270:0x06eb, B:274:0x0720, B:276:0x072a, B:277:0x0734, B:280:0x076b, B:272:0x06f0, B:278:0x073d, B:279:0x0768, B:282:0x0772, B:284:0x077b, B:290:0x07ea, B:285:0x07a6, B:287:0x07ac, B:288:0x07e1, B:289:0x07e7, B:291:0x07ee, B:294:0x07f6, B:296:0x0802, B:301:0x0866, B:298:0x082d, B:299:0x0858, B:300:0x0863, B:303:0x086c, B:305:0x0872, B:308:0x08a4, B:306:0x089b, B:307:0x08a1, B:310:0x08ab, B:312:0x08b1, B:315:0x08e3, B:313:0x08da, B:314:0x08e0, B:317:0x08ea, B:319:0x08f0, B:322:0x0922, B:320:0x0919, B:321:0x091f, B:324:0x0929, B:326:0x0931, B:329:0x0965, B:327:0x095c, B:328:0x0962, B:331:0x096c, B:333:0x0972, B:336:0x09a4, B:334:0x099b, B:335:0x09a1, B:338:0x09ab, B:340:0x09b1, B:343:0x09e3, B:341:0x09da, B:342:0x09e0, B:345:0x09ea, B:347:0x09f0, B:350:0x0a22, B:348:0x0a19, B:349:0x0a1f, B:352:0x0a29, B:354:0x0a2f, B:357:0x0a61, B:355:0x0a58, B:356:0x0a5e, B:359:0x0a68, B:361:0x0a6e, B:364:0x0aa0, B:362:0x0a97, B:363:0x0a9d, B:366:0x0aa7, B:368:0x0aad, B:371:0x0ae1, B:369:0x0ad8, B:370:0x0ade, B:374:0x0aea, B:379:0x0b0c, B:383:0x0b3f, B:381:0x0b37, B:377:0x0afb, B:382:0x0b3b, B:386:0x0b49, B:388:0x0b5b, B:397:0x0bcb, B:390:0x0b86, B:391:0x0bb1, B:393:0x0bb5, B:395:0x0bbf, B:394:0x0bb9, B:396:0x0bc8, B:400:0x0bd3, B:401:0x0bd8, B:404:0x0be5, B:406:0x0beb, B:407:0x0c18, B:409:0x0c21, B:410:0x0c50, B:412:0x0c56, B:413:0x0c8f, B:415:0x0c9d, B:416:0x0ca7, B:418:0x0cae, B:417:0x0cab, B:421:0x0cb8, B:423:0x0cbe, B:424:0x0ceb, B:425:0x0cf4, B:428:0x0cff, B:430:0x0d07, B:433:0x0d42, B:431:0x0d14, B:432:0x0d3f, B:436:0x0d82, B:437:0x0d8d), top: B:488:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:293:0x07f4  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x086a  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x08a9  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x08e8  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0927  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x096a  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x09a9  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x09e8  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0a27  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x0a66  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0aa5  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0ae6  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x0b44  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x0bd0  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0be0  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0cb3  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0cfc  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0dbe  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x0dca  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0dd1  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x0ddc  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0de2  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0ded  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0dfd  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0e0e A[LOOP:2: B:474:0x0e08->B:476:0x0e0e, LOOP_END] */
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
            Method dump skipped, instruction units count: 3830
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
                if (attributeValue2.startsWith(".")) {
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
