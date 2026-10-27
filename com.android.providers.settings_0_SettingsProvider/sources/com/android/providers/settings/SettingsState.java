package com.android.providers.settings;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Build;
import android.os.FileUtils;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AtomicFile;
import android.util.Base64;
import android.util.Slog;
import android.util.SparseIntArray;
import android.util.TimeUtils;
import android.util.Xml;
import android.util.proto.ProtoOutputStream;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.FrameworkStatsLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
final class SettingsState {

    @GuardedBy({"sLock"})
    private static Signature sSystemSignature;

    @GuardedBy({"mLock"})
    private final Context mContext;

    @GuardedBy({"mLock"})
    private boolean mDirty;
    private final Handler mHandler;

    @GuardedBy({"mLock"})
    private final List<HistoricalOperation> mHistoricalOperations;

    @GuardedBy({"mLock"})
    public final int mKey;

    @GuardedBy({"mLock"})
    private long mLastNotWrittenMutationTimeMillis;
    private final Object mLock;

    @GuardedBy({"mLock"})
    private final int mMaxBytesPerAppPackage;

    @GuardedBy({"mLock"})
    private int mNextHistoricalOpIdx;

    @GuardedBy({"mLock"})
    private long mNextId;

    @GuardedBy({"mLock"})
    private final ArrayMap<String, Integer> mPackageToMemoryUsage;

    @GuardedBy({"mLock"})
    private final File mStatePersistFile;

    @GuardedBy({"mLock"})
    private final String mStatePersistTag;

    @GuardedBy({"mLock"})
    private boolean mWriteScheduled;
    private static final Object sLock = new Object();

    @GuardedBy({"sLock"})
    private static final SparseIntArray sSystemUids = new SparseIntArray();
    private final Object mWriteLock = new Object();

    @GuardedBy({"mLock"})
    private final ArrayMap<String, Setting> mSettings = new ArrayMap<>();

    @GuardedBy({"mLock"})
    private final ArrayMap<String, String> mNamespaceBannedHashes = new ArrayMap<>();
    private final Setting mNullSetting = new Setting(this, null, null, false, null, null) { // from class: com.android.providers.settings.SettingsState.1
        @Override // com.android.providers.settings.SettingsState.Setting
        public boolean isNull() {
            return true;
        }
    };

    @GuardedBy({"mLock"})
    private int mVersion = -1;

    public static int getTypeFromKey(int i) {
        return i >>> 28;
    }

    public static int getUserIdFromKey(int i) {
        return i & 268435455;
    }

    public static int makeKey(int i, int i2) {
        return (i << 28) | i2;
    }

    static /* synthetic */ long access$408(SettingsState settingsState) {
        long j = settingsState.mNextId;
        settingsState.mNextId = 1 + j;
        return j;
    }

    public SettingsState(Context context, Object obj, File file, int i, int i2, Looper looper) {
        this.mContext = context;
        this.mLock = obj;
        this.mStatePersistFile = file;
        this.mStatePersistTag = "settings-" + getTypeFromKey(i) + "-" + getUserIdFromKey(i);
        this.mKey = i;
        this.mHandler = new MyHandler(looper);
        if (i2 == 20000) {
            this.mMaxBytesPerAppPackage = i2;
            this.mPackageToMemoryUsage = new ArrayMap<>();
        } else {
            this.mMaxBytesPerAppPackage = i2;
            this.mPackageToMemoryUsage = null;
        }
        this.mHistoricalOperations = Build.IS_DEBUGGABLE ? new ArrayList(20) : null;
        synchronized (this.mLock) {
            readStateSyncLocked();
        }
    }

    @GuardedBy({"mLock"})
    public int getVersionLocked() {
        return this.mVersion;
    }

    public Setting getNullSetting() {
        return this.mNullSetting;
    }

    @GuardedBy({"mLock"})
    public void setVersionLocked(int i) {
        if (i == this.mVersion) {
            return;
        }
        this.mVersion = i;
        scheduleWriteIfNeededLocked();
    }

    @GuardedBy({"mLock"})
    public void removeSettingsForPackageLocked(String str) {
        boolean z = false;
        for (int size = this.mSettings.size() - 1; size >= 0; size--) {
            String strKeyAt = this.mSettings.keyAt(size);
            if (!Settings.System.PUBLIC_SETTINGS.contains(strKeyAt) && !Settings.System.PRIVATE_SETTINGS.contains(strKeyAt) && str.equals(this.mSettings.valueAt(size).packageName)) {
                this.mSettings.removeAt(size);
                z = true;
            }
        }
        if (z) {
            scheduleWriteIfNeededLocked();
        }
    }

    @GuardedBy({"mLock"})
    public List<String> getSettingNamesLocked() {
        ArrayList arrayList = new ArrayList();
        int size = this.mSettings.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(this.mSettings.keyAt(i));
        }
        return arrayList;
    }

    @GuardedBy({"mLock"})
    public Setting getSettingLocked(String str) {
        if (TextUtils.isEmpty(str)) {
            return this.mNullSetting;
        }
        Setting setting = this.mSettings.get(str);
        if (setting != null) {
            return new Setting(setting);
        }
        return this.mNullSetting;
    }

    public boolean updateSettingLocked(String str, String str2, String str3, boolean z, String str4) {
        if (hasSettingLocked(str)) {
            return insertSettingLocked(str, str2, str3, z, str4);
        }
        return false;
    }

    @GuardedBy({"mLock"})
    public void resetSettingDefaultValueLocked(String str) {
        Setting settingLocked = getSettingLocked(str);
        if (settingLocked == null || settingLocked.isNull() || settingLocked.getDefaultValue() == null) {
            return;
        }
        String value = settingLocked.getValue();
        String defaultValue = settingLocked.getDefaultValue();
        Setting setting = new Setting(this, str, settingLocked.getValue(), null, settingLocked.getPackageName(), settingLocked.getTag(), false, settingLocked.getId());
        this.mSettings.put(str, setting);
        updateMemoryUsagePerPackageLocked(setting.getPackageName(), value, setting.getValue(), defaultValue, setting.getDefaultValue());
        scheduleWriteIfNeededLocked();
    }

    @GuardedBy({"mLock"})
    public boolean insertSettingOverrideableByRestoreLocked(String str, String str2, String str3, boolean z, String str4) {
        return insertSettingLocked(str, str2, str3, z, false, str4, true);
    }

    @GuardedBy({"mLock"})
    public boolean insertSettingLocked(String str, String str2, String str3, boolean z, String str4) {
        return insertSettingLocked(str, str2, str3, z, false, str4, false);
    }

    @GuardedBy({"mLock"})
    public boolean insertSettingLocked(String str, String str2, String str3, boolean z, boolean z2, String str4, boolean z3) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Setting setting = this.mSettings.get(str);
        String str5 = setting != null ? setting.value : null;
        String str6 = setting != null ? setting.defaultValue : null;
        if (setting != null) {
            if (!setting.update(str2, z, str4, str3, z2, z3)) {
                return false;
            }
        } else {
            Setting setting2 = new Setting(str, str2, z, str4, str3, z2);
            this.mSettings.put(str, setting2);
            setting = setting2;
        }
        FrameworkStatsLog.write(41, str, str2, setting.value, str5, str3, z, getUserIdFromKey(this.mKey), 1);
        addHistoricalOperationLocked("update", setting);
        updateMemoryUsagePerPackageLocked(str4, str5, str2, str6, setting.getDefaultValue());
        scheduleWriteIfNeededLocked();
        return true;
    }

    @GuardedBy({"mLock"})
    public boolean isNewConfigBannedLocked(String str, Map<String, String> map) {
        removeNullValueOldStyle(map);
        String str2 = this.mNamespaceBannedHashes.get(str);
        if (str2 == null) {
            return false;
        }
        return str2.equals(hashCode(map));
    }

    @GuardedBy({"mLock"})
    public void unbanAllConfigIfBannedConfigUpdatedLocked(String str) {
        if (this.mNamespaceBannedHashes.get(str) != null) {
            this.mNamespaceBannedHashes.clear();
            scheduleWriteIfNeededLocked();
        }
    }

    @GuardedBy({"mLock"})
    public void banConfigurationLocked(String str, Map<String, String> map) {
        if (str == null || map.isEmpty()) {
            return;
        }
        this.mNamespaceBannedHashes.put(str, hashCode(map));
    }

    @GuardedBy({"mLock"})
    public Set<String> getAllConfigPrefixesLocked() {
        HashSet hashSet = new HashSet();
        int size = this.mSettings.size();
        for (int i = 0; i < size; i++) {
            hashSet.add(this.mSettings.keyAt(i).split("/")[0] + "/");
        }
        return hashSet;
    }

    @GuardedBy({"mLock"})
    public List<String> setSettingsLocked(String str, Map<String, String> map, String str2) {
        String str3;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mSettings.keySet().size(); i++) {
            String strKeyAt = this.mSettings.keyAt(i);
            if (strKeyAt.startsWith(str) && !map.containsKey(strKeyAt)) {
                Setting settingRemove = this.mSettings.remove(strKeyAt);
                FrameworkStatsLog.write(41, strKeyAt, "", "", settingRemove.value, "", false, getUserIdFromKey(this.mKey), 2);
                addHistoricalOperationLocked("delete", settingRemove);
                arrayList.add(strKeyAt);
            }
        }
        for (String str4 : map.keySet()) {
            String str5 = map.get(str4);
            Setting setting = this.mSettings.get(str4);
            if (setting == null) {
                Setting setting2 = new Setting(this, str4, str5, false, str2, null);
                this.mSettings.put(str4, setting2);
                arrayList.add(str4);
                str3 = null;
                setting = setting2;
            } else if (setting.value != str5) {
                String str6 = setting.value;
                setting.update(str5, false, str2, null, true, false);
                arrayList.add(str4);
                str3 = str6;
            }
            FrameworkStatsLog.write(41, str4, str5, setting.value, str3, (String) null, false, getUserIdFromKey(this.mKey), 1);
            addHistoricalOperationLocked("update", setting);
        }
        if (!arrayList.isEmpty()) {
            scheduleWriteIfNeededLocked();
        }
        return arrayList;
    }

    public void persistSyncLocked() {
        this.mHandler.removeMessages(1);
        doWriteState();
    }

    @GuardedBy({"mLock"})
    public boolean deleteSettingLocked(String str) {
        if (TextUtils.isEmpty(str) || !hasSettingLocked(str)) {
            return false;
        }
        Setting settingRemove = this.mSettings.remove(str);
        FrameworkStatsLog.write(41, str, "", "", settingRemove.value, "", false, getUserIdFromKey(this.mKey), 2);
        updateMemoryUsagePerPackageLocked(settingRemove.packageName, settingRemove.value, null, settingRemove.defaultValue, null);
        addHistoricalOperationLocked("delete", settingRemove);
        scheduleWriteIfNeededLocked();
        return true;
    }

    @GuardedBy({"mLock"})
    public boolean resetSettingLocked(String str) {
        if (TextUtils.isEmpty(str) || !hasSettingLocked(str)) {
            return false;
        }
        Setting setting = this.mSettings.get(str);
        Setting setting2 = new Setting(setting);
        String value = setting.getValue();
        String defaultValue = setting.getDefaultValue();
        if (!setting.reset()) {
            return false;
        }
        updateMemoryUsagePerPackageLocked(setting.packageName, value, setting.getValue(), defaultValue, setting.getDefaultValue());
        addHistoricalOperationLocked("reset", setting2);
        scheduleWriteIfNeededLocked();
        return true;
    }

    @GuardedBy({"mLock"})
    public void destroyLocked(Runnable runnable) {
        this.mHandler.removeMessages(1);
        if (runnable != null) {
            if (this.mDirty) {
                this.mHandler.obtainMessage(1, runnable).sendToTarget();
            } else {
                runnable.run();
            }
        }
    }

    @GuardedBy({"mLock"})
    private void addHistoricalOperationLocked(String str, Setting setting) {
        if (this.mHistoricalOperations == null) {
            return;
        }
        HistoricalOperation historicalOperation = new HistoricalOperation(this, SystemClock.elapsedRealtime(), str, setting != null ? new Setting(setting) : null);
        if (this.mNextHistoricalOpIdx >= this.mHistoricalOperations.size()) {
            this.mHistoricalOperations.add(historicalOperation);
        } else {
            this.mHistoricalOperations.set(this.mNextHistoricalOpIdx, historicalOperation);
        }
        int i = this.mNextHistoricalOpIdx + 1;
        this.mNextHistoricalOpIdx = i;
        if (i >= 20) {
            this.mNextHistoricalOpIdx = 0;
        }
    }

    void dumpHistoricalOperations(ProtoOutputStream protoOutputStream, long j) {
        synchronized (this.mLock) {
            if (this.mHistoricalOperations == null) {
                return;
            }
            int size = this.mHistoricalOperations.size();
            for (int i = 0; i < size; i++) {
                int i2 = (this.mNextHistoricalOpIdx - 1) - i;
                if (i2 < 0) {
                    i2 += size;
                }
                HistoricalOperation historicalOperation = this.mHistoricalOperations.get(i2);
                long jStart = protoOutputStream.start(j);
                protoOutputStream.write(1112396529665L, historicalOperation.mTimestamp);
                protoOutputStream.write(1138166333442L, historicalOperation.mOperation);
                if (historicalOperation.mSetting != null) {
                    protoOutputStream.write(1138166333443L, historicalOperation.mSetting.getName());
                }
                protoOutputStream.end(jStart);
            }
        }
    }

    public void dumpHistoricalOperations(PrintWriter printWriter) {
        synchronized (this.mLock) {
            if (this.mHistoricalOperations == null) {
                return;
            }
            printWriter.println("Historical operations");
            int size = this.mHistoricalOperations.size();
            for (int i = 0; i < size; i++) {
                int i2 = (this.mNextHistoricalOpIdx - 1) - i;
                if (i2 < 0) {
                    i2 += size;
                }
                HistoricalOperation historicalOperation = this.mHistoricalOperations.get(i2);
                printWriter.print(TimeUtils.formatForLogging(historicalOperation.mTimestamp));
                printWriter.print(" ");
                printWriter.print(historicalOperation.mOperation);
                if (historicalOperation.mSetting != null) {
                    printWriter.print(" ");
                    printWriter.print(historicalOperation.mSetting.getName());
                }
                printWriter.println();
            }
            printWriter.println();
            printWriter.println();
        }
    }

    @GuardedBy({"mLock"})
    private void updateMemoryUsagePerPackageLocked(String str, String str2, String str3, String str4, String str5) {
        if (this.mMaxBytesPerAppPackage == -1 || "android".equals(str)) {
            return;
        }
        int length = (((str3 != null ? str3.length() : 0) + (str5 != null ? str5.length() : 0)) - (str2 != null ? str2.length() : 0)) - (str4 != null ? str4.length() : 0);
        Integer num = this.mPackageToMemoryUsage.get(str);
        if (num != null) {
            length += num.intValue();
        }
        int iMax = Math.max(length, 0);
        if (iMax > this.mMaxBytesPerAppPackage) {
            throw new IllegalStateException("You are adding too many system settings. You should stop using system settings for app specific data package: " + str);
        }
        this.mPackageToMemoryUsage.put(str, Integer.valueOf(iMax));
    }

    @GuardedBy({"mLock"})
    private boolean hasSettingLocked(String str) {
        return this.mSettings.indexOfKey(str) >= 0;
    }

    @GuardedBy({"mLock"})
    private void scheduleWriteIfNeededLocked() {
        if (this.mDirty) {
            return;
        }
        this.mDirty = true;
        writeStateAsyncLocked();
    }

    @GuardedBy({"mLock"})
    private void writeStateAsyncLocked() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.mWriteScheduled) {
            this.mHandler.removeMessages(1);
            long j = this.mLastNotWrittenMutationTimeMillis;
            if (jUptimeMillis - j >= 2000) {
                this.mHandler.obtainMessage(1).sendToTarget();
                return;
            }
            long jMin = Math.min(200L, Math.max((j + 2000) - jUptimeMillis, 0L));
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1), jMin);
            return;
        }
        this.mLastNotWrittenMutationTimeMillis = jUptimeMillis;
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1), 200L);
        this.mWriteScheduled = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doWriteState() {
        int i;
        ArrayMap arrayMap;
        ArrayMap arrayMap2;
        boolean z;
        FileOutputStream fileOutputStreamStartWrite;
        int i2;
        XmlSerializer xmlSerializer;
        synchronized (this.mLock) {
            i = this.mVersion;
            arrayMap = new ArrayMap(this.mSettings);
            arrayMap2 = new ArrayMap(this.mNamespaceBannedHashes);
            z = false;
            this.mDirty = false;
            this.mWriteScheduled = false;
        }
        synchronized (this.mWriteLock) {
            AtomicFile atomicFile = new AtomicFile(this.mStatePersistFile, this.mStatePersistTag);
            try {
                fileOutputStreamStartWrite = atomicFile.startWrite();
            } catch (Throwable th) {
                th = th;
                fileOutputStreamStartWrite = null;
            }
            try {
                XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
                xmlSerializerNewSerializer.setOutput(fileOutputStreamStartWrite, StandardCharsets.UTF_8.name());
                xmlSerializerNewSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                xmlSerializerNewSerializer.startDocument(null, Boolean.TRUE);
                xmlSerializerNewSerializer.startTag(null, "settings");
                xmlSerializerNewSerializer.attribute(null, "version", String.valueOf(i));
                int size = arrayMap.size();
                int i3 = 0;
                while (i3 < size) {
                    Setting setting = (Setting) arrayMap.valueAt(i3);
                    if (setting.isTransient()) {
                        i2 = i3;
                        xmlSerializer = xmlSerializerNewSerializer;
                    } else {
                        i2 = i3;
                        xmlSerializer = xmlSerializerNewSerializer;
                        writeSingleSetting(this.mVersion, xmlSerializerNewSerializer, setting.getId(), setting.getName(), setting.getValue(), setting.getDefaultValue(), setting.getPackageName(), setting.getTag(), setting.isDefaultFromSystem(), setting.isValuePreservedInRestore());
                    }
                    i3 = i2 + 1;
                    xmlSerializerNewSerializer = xmlSerializer;
                }
                XmlSerializer xmlSerializer2 = xmlSerializerNewSerializer;
                xmlSerializer2.endTag(null, "settings");
                xmlSerializer2.startTag(null, "namespaceHashes");
                for (int i4 = 0; i4 < arrayMap2.size(); i4++) {
                    String str = (String) arrayMap2.keyAt(i4);
                    writeSingleNamespaceHash(xmlSerializer2, str, (String) arrayMap2.get(str));
                }
                xmlSerializer2.endTag(null, "namespaceHashes");
                xmlSerializer2.endDocument();
                atomicFile.finishWrite(fileOutputStreamStartWrite);
                z = true;
            } catch (Throwable th2) {
                th = th2;
                try {
                    Slog.wtf("SettingsState", "Failed to write settings, restoring backup", th);
                    if (th instanceof IOException) {
                        logSettingsDirectoryInformation(atomicFile.getBaseFile());
                        if (th.getMessage().contains("Couldn't create directory")) {
                            Path path = atomicFile.getBaseFile().getParentFile().toPath();
                            try {
                                Files.createDirectories(path, new FileAttribute[0]);
                                Slog.i("SettingsState", "Successfully created " + path);
                            } catch (Throwable th3) {
                                Slog.e("SettingsState", "Failed to write " + path + " with Files.writeDirectories", th3);
                            }
                        }
                    }
                    atomicFile.failWrite(fileOutputStreamStartWrite);
                } finally {
                    IoUtils.closeQuietly(fileOutputStreamStartWrite);
                }
            }
        }
        if (z) {
            synchronized (this.mLock) {
                addHistoricalOperationLocked("persist", null);
            }
        }
    }

    private static void logSettingsDirectoryInformation(File file) {
        Slog.i("SettingsState", "directory info for directory/file " + file + " with stacktrace ");
        for (File parentFile = file.getParentFile(); parentFile != null; parentFile = parentFile.getParentFile()) {
            if (!parentFile.exists()) {
                Slog.i("SettingsState", "ancestor directory " + parentFile + " does not exist");
            } else {
                Slog.i("SettingsState", "ancestor directory " + parentFile + " exists");
                Slog.i("SettingsState", "ancestor directory " + parentFile + " permissions: r: " + parentFile.canRead() + " w: " + parentFile.canWrite() + " x: " + parentFile.canExecute());
                File parentFile2 = parentFile.getParentFile();
                if (parentFile2 != null) {
                    Slog.i("SettingsState", "ancestor's parent directory " + parentFile2 + " permissions: r: " + parentFile2.canRead() + " w: " + parentFile2.canWrite() + " x: " + parentFile2.canExecute());
                    return;
                }
                return;
            }
        }
    }

    static void writeSingleSetting(int i, XmlSerializer xmlSerializer, String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2) throws IOException {
        if (str == null || isBinary(str) || str2 == null || isBinary(str2) || str5 == null || isBinary(str5)) {
            return;
        }
        xmlSerializer.startTag(null, "setting");
        xmlSerializer.attribute(null, "id", str);
        xmlSerializer.attribute(null, "name", str2);
        setValueAttribute("value", "valueBase64", i, xmlSerializer, str3);
        xmlSerializer.attribute(null, "package", str5);
        if (str4 != null) {
            setValueAttribute("defaultValue", "defaultValueBase64", i, xmlSerializer, str4);
            xmlSerializer.attribute(null, "defaultSysSet", Boolean.toString(z));
            setValueAttribute("tag", "tagBase64", i, xmlSerializer, str6);
        }
        if (z2) {
            xmlSerializer.attribute(null, "preserve_in_restore", Boolean.toString(true));
        }
        xmlSerializer.endTag(null, "setting");
    }

    static void setValueAttribute(String str, String str2, int i, XmlSerializer xmlSerializer, String str3) throws IOException {
        if (i < 121) {
            if (str3 == null) {
                xmlSerializer.attribute(null, str, "null");
                return;
            } else {
                xmlSerializer.attribute(null, str, str3);
                return;
            }
        }
        if (str3 == null) {
            return;
        }
        if (isBinary(str3)) {
            xmlSerializer.attribute(null, str2, base64Encode(str3));
        } else {
            xmlSerializer.attribute(null, str, str3);
        }
    }

    private static void writeSingleNamespaceHash(XmlSerializer xmlSerializer, String str, String str2) throws IOException {
        if (str == null || str2 == null) {
            return;
        }
        xmlSerializer.startTag(null, "namespaceHash");
        xmlSerializer.attribute(null, "namespace", str);
        xmlSerializer.attribute(null, "bannedHash", str2);
        xmlSerializer.endTag(null, "namespaceHash");
    }

    private static String hashCode(Map<String, String> map) {
        return Integer.toString(map.hashCode());
    }

    private String getValueAttribute(XmlPullParser xmlPullParser, String str, String str2) {
        if (this.mVersion >= 121) {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                return attributeValue;
            }
            String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
            if (attributeValue2 != null) {
                return base64Decode(attributeValue2);
            }
            return null;
        }
        String attributeValue3 = xmlPullParser.getAttributeValue(null, str);
        if ("null".equals(attributeValue3)) {
            return null;
        }
        return attributeValue3;
    }

    @GuardedBy({"mLock"})
    private void readStateSyncLocked() throws IllegalStateException {
        try {
            if (parseStateFromXmlStreamLocked(new AtomicFile(this.mStatePersistFile).openRead())) {
                return;
            }
            File file = new File(this.mStatePersistFile.getAbsolutePath() + ".fallback");
            Slog.i("SettingsState", "Failed parsing settings file: " + this.mStatePersistFile + ", retrying with fallback file: " + file);
            try {
                if (parseStateFromXmlStreamLocked(new AtomicFile(file).openRead())) {
                    try {
                        FileUtils.copy(file, this.mStatePersistFile);
                        return;
                    } catch (IOException unused) {
                        return;
                    }
                }
                String str = "Failed parsing settings file: " + this.mStatePersistFile;
                Slog.wtf("SettingsState", str);
                throw new IllegalStateException(str);
            } catch (FileNotFoundException unused2) {
                String str2 = "No fallback file found for: " + this.mStatePersistFile;
                Slog.wtf("SettingsState", str2);
                throw new IllegalStateException(str2);
            }
        } catch (FileNotFoundException unused3) {
            Slog.w("SettingsState", "No settings state " + this.mStatePersistFile);
            logSettingsDirectoryInformation(this.mStatePersistFile);
            addHistoricalOperationLocked("initialize", null);
        }
    }

    @GuardedBy({"mLock"})
    private boolean parseStateFromXmlStreamLocked(FileInputStream fileInputStream) {
        boolean z;
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(fileInputStream, StandardCharsets.UTF_8.name());
            parseStateLocked(xmlPullParserNewPullParser);
            z = true;
        } catch (IOException | XmlPullParserException unused) {
            z = false;
        } catch (Throwable th) {
            IoUtils.closeQuietly(fileInputStream);
            throw th;
        }
        IoUtils.closeQuietly(fileInputStream);
        return z;
    }

    public static boolean stateFileExists(File file) {
        return new AtomicFile(file).exists();
    }

    private void parseStateLocked(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
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
                String name = xmlPullParser.getName();
                if (name.equals("settings")) {
                    parseSettingsLocked(xmlPullParser);
                } else if (name.equals("namespaceHashes")) {
                    parseNamespaceHash(xmlPullParser);
                }
            }
        }
    }

    @GuardedBy({"mLock"})
    private void parseSettingsLocked(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean z;
        String valueAttribute;
        String str = null;
        this.mVersion = Integer.parseInt(xmlPullParser.getAttributeValue(null, "version"));
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
                if (xmlPullParser.getName().equals("setting")) {
                    String attributeValue = xmlPullParser.getAttributeValue(str, "id");
                    String attributeValue2 = xmlPullParser.getAttributeValue(str, "name");
                    String valueAttribute2 = getValueAttribute(xmlPullParser, "value", "valueBase64");
                    String attributeValue3 = xmlPullParser.getAttributeValue(str, "package");
                    String valueAttribute3 = getValueAttribute(xmlPullParser, "defaultValue", "defaultValueBase64");
                    String attributeValue4 = xmlPullParser.getAttributeValue(str, "preserve_in_restore");
                    boolean z2 = attributeValue4 != null && Boolean.parseBoolean(attributeValue4);
                    if (valueAttribute3 != null) {
                        z = Boolean.parseBoolean(xmlPullParser.getAttributeValue(str, "defaultSysSet"));
                        valueAttribute = getValueAttribute(xmlPullParser, "tag", "tagBase64");
                    } else {
                        z = false;
                        valueAttribute = str;
                    }
                    this.mSettings.put(attributeValue2, new Setting(attributeValue2, valueAttribute2, valueAttribute3, attributeValue3, valueAttribute, z, attributeValue, z2));
                }
                str = null;
            }
        }
    }

    @GuardedBy({"mLock"})
    private void parseNamespaceHash(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
            if (next != 3 && next != 4 && xmlPullParser.getName().equals("namespaceHash")) {
                this.mNamespaceBannedHashes.put(xmlPullParser.getAttributeValue(null, "namespace"), xmlPullParser.getAttributeValue(null, "bannedHash"));
            }
        }
    }

    private static Map<String, String> removeNullValueOldStyle(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ("null".equals(entry.getValue())) {
                entry.setValue(null);
            }
        }
        return map;
    }

    private final class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            Runnable runnable = (Runnable) message.obj;
            SettingsState.this.doWriteState();
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    private class HistoricalOperation {
        final String mOperation;
        final Setting mSetting;
        final long mTimestamp;

        public HistoricalOperation(SettingsState settingsState, long j, String str, Setting setting) {
            this.mTimestamp = j;
            this.mOperation = str;
            this.mSetting = setting;
        }
    }

    class Setting {
        private boolean defaultFromSystem;
        private String defaultValue;
        private String id;
        private boolean isValuePreservedInRestore;
        private String name;
        private String packageName;
        private String tag;
        private String value;

        public boolean isNull() {
            return false;
        }

        public Setting(Setting setting) {
            this.name = setting.name;
            this.value = setting.value;
            this.defaultValue = setting.defaultValue;
            this.packageName = setting.packageName;
            this.id = setting.id;
            this.defaultFromSystem = setting.defaultFromSystem;
            this.tag = setting.tag;
            this.isValuePreservedInRestore = setting.isValuePreservedInRestore;
        }

        public Setting(SettingsState settingsState, String str, String str2, boolean z, String str3, String str4) {
            this(str, str2, z, str3, str4, false);
        }

        Setting(String str, String str2, boolean z, String str3, String str4, boolean z2) {
            this.name = str;
            update(str2, z, str3, str4, z2, true);
        }

        public Setting(SettingsState settingsState, String str, String str2, String str3, String str4, String str5, boolean z, String str6) {
            this(str, str2, str3, str4, str5, z, str6, false);
        }

        Setting(String str, String str2, String str3, String str4, String str5, boolean z, String str6, boolean z2) {
            SettingsState.this.mNextId = Math.max(SettingsState.this.mNextId, Long.parseLong(str6) + 1);
            init(str, "null".equals(str2) ? null : str2, str5, str3, str4, z, str6, z2);
        }

        private void init(String str, String str2, String str3, String str4, String str5, boolean z, String str6, boolean z2) {
            this.name = str;
            this.value = str2;
            this.tag = str3;
            this.defaultValue = str4;
            this.packageName = str5;
            this.id = str6;
            this.defaultFromSystem = z;
            this.isValuePreservedInRestore = z2;
        }

        public String getName() {
            return this.name;
        }

        public int getKey() {
            return SettingsState.this.mKey;
        }

        public String getValue() {
            return this.value;
        }

        public String getTag() {
            return this.tag;
        }

        public String getDefaultValue() {
            return this.defaultValue;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public boolean isDefaultFromSystem() {
            return this.defaultFromSystem;
        }

        public boolean isValuePreservedInRestore() {
            return this.isValuePreservedInRestore;
        }

        public String getId() {
            return this.id;
        }

        public boolean reset() {
            return update(this.defaultValue, false, this.packageName, null, true, true, true);
        }

        public boolean isTransient() {
            if (SettingsState.getTypeFromKey(getKey()) != 0) {
                return false;
            }
            return ArrayUtils.contains(Settings.Global.TRANSIENT_SETTINGS, getName());
        }

        public boolean update(String str, boolean z, String str2, String str3, boolean z2, boolean z3) {
            return update(str, z, str2, str3, z2, z3, false);
        }

        private boolean update(String str, boolean z, String str2, String str3, boolean z2, boolean z3, boolean z4) {
            String str4;
            boolean z5;
            boolean z6;
            boolean z7;
            boolean zShouldPreserveSetting;
            String str5 = str;
            String str6 = null;
            if ("null".equals(str)) {
                str5 = null;
            }
            boolean z8 = (z2 || isNull() || !SettingsState.isSystemPackage(SettingsState.this.mContext, str2)) ? false : true;
            boolean z9 = z8 ? true : z;
            String str7 = this.defaultValue;
            boolean z10 = this.defaultFromSystem;
            if (z9) {
                if (Objects.equals(str5, str7) || (z10 && !z8)) {
                    str6 = str3;
                } else {
                    if (str5 == null) {
                        z10 = false;
                    } else {
                        str6 = str3;
                    }
                    str7 = str5;
                }
                if (!z10 && str5 != null && z8) {
                    z7 = z4;
                    str4 = str7;
                    z5 = true;
                    z6 = z3;
                }
                zShouldPreserveSetting = shouldPreserveSetting(z6, z7, str2, str5);
                if (!Objects.equals(str5, this.value) && Objects.equals(str4, this.defaultValue) && Objects.equals(str2, this.packageName) && Objects.equals(str6, this.tag) && z5 == this.defaultFromSystem && zShouldPreserveSetting == this.isValuePreservedInRestore) {
                    return false;
                }
                init(this.name, str5, str6, str4, str2, z5, String.valueOf(SettingsState.access$408(SettingsState.this)), zShouldPreserveSetting);
                return true;
            }
            str6 = str3;
            str4 = str7;
            z5 = z10;
            z6 = z3;
            z7 = z4;
            zShouldPreserveSetting = shouldPreserveSetting(z6, z7, str2, str5);
            if (!Objects.equals(str5, this.value)) {
            }
            init(this.name, str5, str6, str4, str2, z5, String.valueOf(SettingsState.access$408(SettingsState.this)), zShouldPreserveSetting);
            return true;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("Setting{name=");
            sb.append(this.name);
            sb.append(" value=");
            sb.append(this.value);
            if (this.defaultValue != null) {
                str = " default=" + this.defaultValue;
            } else {
                str = "";
            }
            sb.append(str);
            sb.append(" packageName=");
            sb.append(this.packageName);
            sb.append(" tag=");
            sb.append(this.tag);
            sb.append(" defaultFromSystem=");
            sb.append(this.defaultFromSystem);
            sb.append("}");
            return sb.toString();
        }

        private boolean shouldPreserveSetting(boolean z, boolean z2, String str, String str2) {
            if (z2) {
                return false;
            }
            if (str2 != null && str2.equals(this.value) && "android".equals(str)) {
                return false;
            }
            return this.isValuePreservedInRestore || !z;
        }
    }

    public static boolean isBinary(String str) {
        if (str == null) {
            throw null;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (!((cCharAt >= ' ' && cCharAt <= 55295) || (cCharAt >= 57344 && cCharAt <= 65533))) {
                return true;
            }
        }
        return false;
    }

    private static String base64Encode(String str) {
        return Base64.encodeToString(toBytes(str), 2);
    }

    private static String base64Decode(String str) {
        return fromBytes(Base64.decode(str, 0));
    }

    private static byte[] toBytes(String str) {
        byte[] bArr = new byte[str.length() * 2];
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            int i3 = i + 1;
            bArr[i] = (byte) (cCharAt >> '\b');
            i = i3 + 1;
            bArr[i3] = (byte) cCharAt;
        }
        return bArr;
    }

    private static String fromBytes(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length / 2);
        int length = bArr.length - 1;
        for (int i = 0; i < length; i += 2) {
            stringBuffer.append((char) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255)));
        }
        return stringBuffer.toString();
    }

    public static boolean isSystemPackage(Context context, String str) {
        int callingUid = Binder.getCallingUid();
        return isSystemPackage(context, str, callingUid, UserHandle.getUserId(callingUid));
    }

    public static boolean isSystemPackage(Context context, String str, int i, int i2) {
        int appId;
        synchronized (sLock) {
            if ("android".equals(str)) {
                return true;
            }
            if (!"com.android.shell".equals(str) && !"root".equals(str)) {
                if (i != -1 && (appId = UserHandle.getAppId(i)) < 10000) {
                    sSystemUids.put(appId, appId);
                    return true;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    try {
                        int packageUidAsUser = context.getPackageManager().getPackageUidAsUser(str, 0, i2);
                        if (UserHandle.getAppId(packageUidAsUser) < 10000) {
                            sSystemUids.put(packageUidAsUser, packageUidAsUser);
                            return true;
                        }
                        if (sSystemUids.indexOfKey(packageUidAsUser) >= 0) {
                            return true;
                        }
                        if (str.equals(context.getPackageManager().getSetupWizardPackageName())) {
                            sSystemUids.put(packageUidAsUser, packageUidAsUser);
                            return true;
                        }
                        try {
                            PackageInfo packageInfoAsUser = context.getPackageManager().getPackageInfoAsUser(str, 64, i2);
                            if ((packageInfoAsUser.applicationInfo.flags & 8) != 0 && (packageInfoAsUser.applicationInfo.flags & 1) != 0) {
                                sSystemUids.put(packageUidAsUser, packageUidAsUser);
                                return true;
                            }
                            if (sSystemSignature == null) {
                                try {
                                    sSystemSignature = context.getPackageManager().getPackageInfoAsUser("android", 64, 0).signatures[0];
                                } catch (PackageManager.NameNotFoundException unused) {
                                    return false;
                                }
                            }
                            if (!sSystemSignature.equals(packageInfoAsUser.signatures[0])) {
                                return false;
                            }
                            sSystemUids.put(packageUidAsUser, packageUidAsUser);
                            return true;
                        } catch (PackageManager.NameNotFoundException unused2) {
                            return false;
                        }
                    } catch (PackageManager.NameNotFoundException unused3) {
                        return false;
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
            return false;
        }
    }
}
