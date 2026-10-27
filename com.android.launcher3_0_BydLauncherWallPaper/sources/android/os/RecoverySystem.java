package android.os;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.os.IRecoverySystemProgressListener;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.euicc.EuiccManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import libcore.io.Streams;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;

/* JADX INFO: loaded from: classes2.dex */
public class RecoverySystem {
    private static final String ACTION_EUICC_FACTORY_RESET = "com.android.internal.action.EUICC_FACTORY_RESET";
    private static final String ACTION_EUICC_REMOVE_INVISIBLE_SUBSCRIPTIONS = "com.android.internal.action.EUICC_REMOVE_INVISIBLE_SUBSCRIPTIONS";
    public static final File BLOCK_MAP_FILE;
    private static final long DEFAULT_EUICC_FACTORY_RESET_TIMEOUT_MILLIS = 30000;
    private static final long DEFAULT_EUICC_REMOVING_INVISIBLE_PROFILES_TIMEOUT_MILLIS = 45000;
    private static final File DEFAULT_KEYSTORE = new File("/system/etc/security/otacerts.zip");
    private static final String LAST_INSTALL_PATH = "last_install";
    private static final String LAST_PREFIX = "last_";
    private static final File LOG_FILE;
    private static final int LOG_FILE_MAX_LENGTH = 65536;
    private static final long MAX_EUICC_FACTORY_RESET_TIMEOUT_MILLIS = 60000;
    private static final long MAX_EUICC_REMOVING_INVISIBLE_PROFILES_TIMEOUT_MILLIS = 90000;
    private static final long MIN_EUICC_FACTORY_RESET_TIMEOUT_MILLIS = 5000;
    private static final long MIN_EUICC_REMOVING_INVISIBLE_PROFILES_TIMEOUT_MILLIS = 15000;
    private static final String PACKAGE_NAME_EUICC_DATA_MANAGEMENT_CALLBACK = "android";
    private static final long PUBLISH_PROGRESS_INTERVAL_MS = 500;
    private static final File RECOVERY_DIR;
    private static final String TAG = "RecoverySystem";
    public static final File UNCRYPT_PACKAGE_FILE;
    public static final File UNCRYPT_STATUS_FILE;
    private static final Object sRequestLock;
    private final IRecoverySystem mService;

    public interface ProgressListener {
        void onProgress(int i);
    }

    static {
        File file = new File("/cache/recovery");
        RECOVERY_DIR = file;
        LOG_FILE = new File(file, "log");
        BLOCK_MAP_FILE = new File(file, "block.map");
        UNCRYPT_PACKAGE_FILE = new File(file, "uncrypt_file");
        UNCRYPT_STATUS_FILE = new File(file, "uncrypt_status");
        sRequestLock = new Object();
    }

    private static HashSet<X509Certificate> getTrustedCerts(File file) throws GeneralSecurityException, IOException {
        HashSet<X509Certificate> hashSet = new HashSet<>();
        if (file == null) {
            file = DEFAULT_KEYSTORE;
        }
        ZipFile zipFile = new ZipFile(file);
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                InputStream inputStream = zipFile.getInputStream(enumerationEntries.nextElement());
                try {
                    hashSet.add((X509Certificate) certificateFactory.generateCertificate(inputStream));
                    inputStream.close();
                } finally {
                }
            }
            return hashSet;
        } finally {
            zipFile.close();
        }
    }

    public static void verifyPackage(File file, ProgressListener progressListener, File file2) throws GeneralSecurityException, IOException {
        long length = file.length();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            boolean z = false;
            if (progressListener != null) {
                progressListener.onProgress(0);
            }
            randomAccessFile.seek(length - 6);
            byte[] bArr = new byte[6];
            randomAccessFile.readFully(bArr);
            if (bArr[2] != -1 || bArr[3] != -1) {
                throw new SignatureException("no signature in file (no footer)");
            }
            int i = (bArr[4] & 255) | ((bArr[5] & 255) << 8);
            int i2 = ((bArr[1] & 255) << 8) | (bArr[0] & 255);
            int i3 = i + 22;
            byte[] bArr2 = new byte[i3];
            randomAccessFile.seek(length - ((long) i3));
            randomAccessFile.readFully(bArr2);
            if (bArr2[0] != 80 || bArr2[1] != 75 || bArr2[2] != 5 || bArr2[3] != 6) {
                throw new SignatureException("no signature in file (bad footer)");
            }
            for (int i4 = 4; i4 < i3 - 3; i4++) {
                if (bArr2[i4] == 80 && bArr2[i4 + 1] == 75 && bArr2[i4 + 2] == 5) {
                    if (bArr2[i4 + 3] == 6) {
                        throw new SignatureException("EOCD marker found after start of EOCD");
                    }
                }
            }
            PKCS7 pkcs7 = new PKCS7(new ByteArrayInputStream(bArr2, i3 - i2, i2));
            X509Certificate[] certificates = pkcs7.getCertificates();
            if (certificates == null || certificates.length == 0) {
                throw new SignatureException("signature contains no certificates");
            }
            PublicKey publicKey = certificates[0].getPublicKey();
            SignerInfo[] signerInfos = pkcs7.getSignerInfos();
            if (signerInfos == null || signerInfos.length == 0) {
                throw new SignatureException("signature contains no signedData");
            }
            SignerInfo signerInfo = signerInfos[0];
            Iterator<X509Certificate> it = getTrustedCerts(file2 == null ? DEFAULT_KEYSTORE : file2).iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getPublicKey().equals(publicKey)) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!z) {
                throw new SignatureException("signature doesn't match any trusted key");
            }
            randomAccessFile.seek(0L);
            SignerInfo signerInfoVerify = pkcs7.verify(signerInfo, new InputStream(length, i, jCurrentTimeMillis, randomAccessFile, progressListener) { // from class: android.os.RecoverySystem.1
                long lastPublishTime;
                long toRead;
                final /* synthetic */ int val$commentSize;
                final /* synthetic */ long val$fileLen;
                final /* synthetic */ ProgressListener val$listenerForInner;
                final /* synthetic */ RandomAccessFile val$raf;
                final /* synthetic */ long val$startTimeMillis;
                long soFar = 0;
                int lastPercent = 0;

                {
                    this.val$fileLen = length;
                    this.val$commentSize = i;
                    this.val$startTimeMillis = jCurrentTimeMillis;
                    this.val$raf = randomAccessFile;
                    this.val$listenerForInner = progressListener;
                    this.toRead = (length - ((long) i)) - 2;
                    this.lastPublishTime = jCurrentTimeMillis;
                }

                @Override // java.io.InputStream
                public int read() throws IOException {
                    throw new UnsupportedOperationException();
                }

                @Override // java.io.InputStream
                public int read(byte[] bArr3, int i5, int i6) throws IOException {
                    if (this.soFar >= this.toRead || Thread.currentThread().isInterrupted()) {
                        return -1;
                    }
                    long j = this.soFar;
                    long j2 = ((long) i6) + j;
                    long j3 = this.toRead;
                    if (j2 > j3) {
                        i6 = (int) (j3 - j);
                    }
                    int i7 = this.val$raf.read(bArr3, i5, i6);
                    this.soFar += (long) i7;
                    if (this.val$listenerForInner != null) {
                        long jCurrentTimeMillis2 = System.currentTimeMillis();
                        int i8 = (int) ((this.soFar * 100) / this.toRead);
                        if (i8 > this.lastPercent && jCurrentTimeMillis2 - this.lastPublishTime > RecoverySystem.PUBLISH_PROGRESS_INTERVAL_MS) {
                            this.lastPercent = i8;
                            this.lastPublishTime = jCurrentTimeMillis2;
                            this.val$listenerForInner.onProgress(i8);
                        }
                    }
                    return i7;
                }
            });
            boolean zInterrupted = Thread.interrupted();
            if (progressListener != null) {
                progressListener.onProgress(100);
            }
            if (zInterrupted) {
                throw new SignatureException("verification was interrupted");
            }
            if (signerInfoVerify == null) {
                throw new SignatureException("signature digest verification failed");
            }
            randomAccessFile.close();
            if (!readAndVerifyPackageCompatibilityEntry(file)) {
                throw new SignatureException("package compatibility verification failed");
            }
        } catch (Throwable th) {
            randomAccessFile.close();
            throw th;
        }
    }

    private static boolean verifyPackageCompatibility(InputStream inputStream) throws IOException {
        long size;
        ArrayList arrayList = new ArrayList();
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                size = nextEntry.getSize();
                if (size > 2147483647L || size < 0) {
                    break;
                }
                byte[] bArr = new byte[(int) size];
                Streams.readFully(zipInputStream, bArr);
                arrayList.add(new String(bArr, StandardCharsets.UTF_8));
            } else {
                if (arrayList.isEmpty()) {
                    throw new IOException("no entries found in the compatibility file");
                }
                return VintfObject.verify((String[]) arrayList.toArray(new String[arrayList.size()])) == 0;
            }
        }
        throw new IOException("invalid entry size (" + size + ") in the compatibility file");
    }

    private static boolean readAndVerifyPackageCompatibilityEntry(File file) throws IOException {
        ZipFile zipFile = new ZipFile(file);
        try {
            ZipEntry entry = zipFile.getEntry("compatibility.zip");
            if (entry != null) {
                boolean zVerifyPackageCompatibility = verifyPackageCompatibility(zipFile.getInputStream(entry));
                zipFile.close();
                return zVerifyPackageCompatibility;
            }
            zipFile.close();
            return true;
        } catch (Throwable th) {
            try {
                zipFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @SystemApi
    public static boolean verifyPackageCompatibility(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            boolean zVerifyPackageCompatibility = verifyPackageCompatibility(fileInputStream);
            fileInputStream.close();
            return zVerifyPackageCompatibility;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @SystemApi
    public static void processPackage(Context context, File file, ProgressListener progressListener, Handler handler) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        if (canonicalPath.startsWith("/data/")) {
            RecoverySystem recoverySystem = (RecoverySystem) context.getSystemService("recovery");
            AnonymousClass2 anonymousClass2 = null;
            if (progressListener != null) {
                if (handler == null) {
                    handler = new Handler(context.getMainLooper());
                }
                anonymousClass2 = new AnonymousClass2(handler, progressListener);
            }
            if (!recoverySystem.uncrypt(canonicalPath, anonymousClass2)) {
                throw new IOException("process package failed");
            }
        }
    }

    /* JADX INFO: renamed from: android.os.RecoverySystem$2, reason: invalid class name */
    class AnonymousClass2 extends IRecoverySystemProgressListener.Stub {
        int lastProgress = 0;
        long lastPublishTime = System.currentTimeMillis();
        final /* synthetic */ ProgressListener val$listener;
        final /* synthetic */ Handler val$progressHandler;

        AnonymousClass2(Handler handler, ProgressListener progressListener) {
            this.val$progressHandler = handler;
            this.val$listener = progressListener;
        }

        @Override // android.os.IRecoverySystemProgressListener
        public void onProgress(final int i) {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            this.val$progressHandler.post(new Runnable() { // from class: android.os.RecoverySystem.2.1
                @Override // java.lang.Runnable
                public void run() {
                    if (i <= AnonymousClass2.this.lastProgress || jCurrentTimeMillis - AnonymousClass2.this.lastPublishTime <= RecoverySystem.PUBLISH_PROGRESS_INTERVAL_MS) {
                        return;
                    }
                    AnonymousClass2.this.lastProgress = i;
                    AnonymousClass2.this.lastPublishTime = jCurrentTimeMillis;
                    AnonymousClass2.this.val$listener.onProgress(i);
                }
            });
        }
    }

    @SystemApi
    public static void processPackage(Context context, File file, ProgressListener progressListener) throws IOException {
        processPackage(context, file, progressListener, null);
    }

    public static void installPackage(Context context, File file) throws IOException {
        installPackage(context, file, false);
    }

    @SystemApi
    public static void installPackage(Context context, File file, boolean z) throws IOException {
        synchronized (sRequestLock) {
            LOG_FILE.delete();
            File file2 = UNCRYPT_PACKAGE_FILE;
            file2.delete();
            String canonicalPath = file.getCanonicalPath();
            Log.w(TAG, "!!! REBOOTING TO INSTALL " + canonicalPath + " !!!");
            boolean zEndsWith = canonicalPath.endsWith("_s.zip");
            if (canonicalPath.startsWith("/data/")) {
                if (z) {
                    if (!BLOCK_MAP_FILE.exists()) {
                        Log.e(TAG, "Package claimed to have been processed but failed to find the block map file.");
                        throw new IOException("Failed to find block map file");
                    }
                } else {
                    FileWriter fileWriter = new FileWriter(file2);
                    try {
                        fileWriter.write(canonicalPath + "\n");
                        fileWriter.close();
                        if (!file2.setReadable(true, false) || !file2.setWritable(true, false)) {
                            Log.e(TAG, "Error setting permission for " + file2);
                        }
                        BLOCK_MAP_FILE.delete();
                    } catch (Throwable th) {
                        fileWriter.close();
                        throw th;
                    }
                }
                canonicalPath = "@/cache/recovery/block.map";
            }
            String str = ("--update_package=" + canonicalPath + "\n") + ("--locale=" + Locale.getDefault().toLanguageTag() + "\n");
            if (zEndsWith) {
                str = str + "--security\n";
            }
            if (!((RecoverySystem) context.getSystemService("recovery")).setupBcb(str)) {
                throw new IOException("Setup BCB failed");
            }
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            String str2 = PowerManager.REBOOT_RECOVERY_UPDATE;
            if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK) && ((DisplayManager) context.getSystemService(DisplayManager.class)).getDisplay(0).getState() != 2) {
                str2 = PowerManager.REBOOT_RECOVERY_UPDATE + ",quiescent";
            }
            powerManager.reboot(str2);
            throw new IOException("Reboot failed (no permissions?)");
        }
    }

    @SystemApi
    public static void prepareForUnattendedUpdate(Context context, String str, IntentSender intentSender) throws IOException {
        Objects.requireNonNull(str, "updateToken == null");
        if (!((RecoverySystem) context.getSystemService("recovery")).requestLskf(str, intentSender)) {
            throw new IOException("preparation for update failed");
        }
    }

    @SystemApi
    public static void clearPrepareForUnattendedUpdate(Context context) throws IOException {
        if (!((RecoverySystem) context.getSystemService("recovery")).clearLskf()) {
            throw new IOException("could not reset unattended update state");
        }
    }

    @SystemApi
    public static void rebootAndApply(Context context, String str, String str2) throws IOException {
        Objects.requireNonNull(str, "updateToken == null");
        if (!((RecoverySystem) context.getSystemService("recovery")).rebootWithLskf(str, str2)) {
            throw new IOException("system not prepared to apply update");
        }
    }

    @SystemApi
    public static void scheduleUpdateOnBoot(Context context, File file) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        boolean zEndsWith = canonicalPath.endsWith("_s.zip");
        if (canonicalPath.startsWith("/data/")) {
            canonicalPath = "@/cache/recovery/block.map";
        }
        String str = ("--update_package=" + canonicalPath + "\n") + ("--locale=" + Locale.getDefault().toLanguageTag() + "\n");
        if (zEndsWith) {
            str = str + "--security\n";
        }
        if (!((RecoverySystem) context.getSystemService("recovery")).setupBcb(str)) {
            throw new IOException("schedule update on boot failed");
        }
    }

    @SystemApi
    public static void cancelScheduledUpdate(Context context) throws IOException {
        if (!((RecoverySystem) context.getSystemService("recovery")).clearBcb()) {
            throw new IOException("cancel scheduled update failed");
        }
    }

    public static void rebootWipeUserData(Context context) throws IOException {
        rebootWipeUserData(context, false, context.getPackageName(), false, false);
    }

    public static void rebootWipeUserData(Context context, String str) throws IOException {
        rebootWipeUserData(context, false, str, false, false);
    }

    public static void rebootWipeUserData(Context context, boolean z) throws IOException {
        rebootWipeUserData(context, z, context.getPackageName(), false, false);
    }

    public static void rebootWipeUserData(Context context, boolean z, String str, boolean z2) throws IOException {
        rebootWipeUserData(context, z, str, z2, false);
    }

    public static void rebootWipeUserData(Context context, boolean z, String str, boolean z2, boolean z3) throws IOException {
        UserManager userManager = (UserManager) context.getSystemService("user");
        if (!z2 && userManager.hasUserRestriction(UserManager.DISALLOW_FACTORY_RESET)) {
            throw new SecurityException("Wiping data is not allowed for this user.");
        }
        final ConditionVariable conditionVariable = new ConditionVariable();
        Intent intent = new Intent(Intent.ACTION_MASTER_CLEAR_NOTIFICATION);
        intent.addFlags(285212672);
        context.sendOrderedBroadcastAsUser(intent, UserHandle.SYSTEM, Manifest.permission.MASTER_CLEAR, new BroadcastReceiver() { // from class: android.os.RecoverySystem.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent2) {
                conditionVariable.open();
            }
        }, null, 0, null, null);
        conditionVariable.block();
        EuiccManager euiccManager = (EuiccManager) context.getSystemService(EuiccManager.class);
        if (z3) {
            wipeEuiccData(context, "android");
        } else {
            removeEuiccInvisibleSubs(context, euiccManager);
        }
        bootCommand(context, z ? "--shutdown_after" : null, "--wipe_data", TextUtils.isEmpty(str) ? null : "--reason=" + sanitizeArg(str + SmsManager.REGEX_PREFIX_DELIMITER + DateFormat.format("yyyy-MM-ddTHH:mm:ssZ", System.currentTimeMillis()).toString()), "--locale=" + Locale.getDefault().toLanguageTag());
    }

    public static boolean wipeEuiccData(Context context, String str) {
        if (Settings.Global.getInt(context.getContentResolver(), Settings.Global.EUICC_PROVISIONED, 0) == 0) {
            Log.d(TAG, "Skipping eUICC wipe/retain as it is not provisioned");
            return true;
        }
        EuiccManager euiccManager = (EuiccManager) context.getSystemService(Context.EUICC_SERVICE);
        if (euiccManager == null || !euiccManager.isEnabled()) {
            return false;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: android.os.RecoverySystem.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (RecoverySystem.ACTION_EUICC_FACTORY_RESET.equals(intent.getAction())) {
                    if (getResultCode() != 0) {
                        Log.e(RecoverySystem.TAG, "Error wiping euicc data, Detailed code = " + intent.getIntExtra(EuiccManager.EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE, 0));
                    } else {
                        Log.d(RecoverySystem.TAG, "Successfully wiped euicc data.");
                        atomicBoolean.set(true);
                    }
                    countDownLatch.countDown();
                }
            }
        };
        Intent intent = new Intent(ACTION_EUICC_FACTORY_RESET);
        intent.setPackage(str);
        PendingIntent broadcastAsUser = PendingIntent.getBroadcastAsUser(context, 0, intent, 134217728, UserHandle.SYSTEM);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_EUICC_FACTORY_RESET);
        HandlerThread handlerThread = new HandlerThread("euiccWipeFinishReceiverThread");
        handlerThread.start();
        context.getApplicationContext().registerReceiver(broadcastReceiver, intentFilter, null, new Handler(handlerThread.getLooper()));
        euiccManager.eraseSubscriptions(broadcastAsUser);
        try {
            try {
                long j = Settings.Global.getLong(context.getContentResolver(), Settings.Global.EUICC_FACTORY_RESET_TIMEOUT_MILLIS, 30000L);
                if (j < 5000) {
                    j = 5000;
                } else if (j > 60000) {
                    j = 60000;
                }
                if (!countDownLatch.await(j, TimeUnit.MILLISECONDS)) {
                    Log.e(TAG, "Timeout wiping eUICC data.");
                } else {
                    context.getApplicationContext().unregisterReceiver(broadcastReceiver);
                    return atomicBoolean.get();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Log.e(TAG, "Wiping eUICC data interrupted", e);
            }
            return false;
        } finally {
            context.getApplicationContext().unregisterReceiver(broadcastReceiver);
        }
    }

    private static void removeEuiccInvisibleSubs(Context context, EuiccManager euiccManager) {
        if (Settings.Global.getInt(context.getContentResolver(), Settings.Global.EUICC_PROVISIONED, 0) == 0) {
            Log.i(TAG, "Skip removing eUICC invisible profiles as it is not provisioned.");
            return;
        }
        if (euiccManager == null || !euiccManager.isEnabled()) {
            Log.i(TAG, "Skip removing eUICC invisible profiles as eUICC manager is not available.");
            return;
        }
        List<SubscriptionInfo> availableSubscriptionInfoList = ((SubscriptionManager) context.getSystemService(SubscriptionManager.class)).getAvailableSubscriptionInfoList();
        if (availableSubscriptionInfoList == null || availableSubscriptionInfoList.isEmpty()) {
            Log.i(TAG, "Skip removing eUICC invisible profiles as no available profiles found.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (SubscriptionInfo subscriptionInfo : availableSubscriptionInfoList) {
            if (subscriptionInfo.isEmbedded() && subscriptionInfo.getGroupUuid() != null && subscriptionInfo.isOpportunistic()) {
                arrayList.add(subscriptionInfo);
            }
        }
        removeEuiccInvisibleSubs(context, arrayList, euiccManager);
    }

    private static boolean removeEuiccInvisibleSubs(Context context, List<SubscriptionInfo> list, EuiccManager euiccManager) {
        if (list == null || list.isEmpty()) {
            Log.i(TAG, "There are no eUICC invisible profiles needed to be removed.");
            return true;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: android.os.RecoverySystem.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (RecoverySystem.ACTION_EUICC_REMOVE_INVISIBLE_SUBSCRIPTIONS.equals(intent.getAction())) {
                    if (getResultCode() != 0) {
                        Log.e(RecoverySystem.TAG, "Error removing euicc opportunistic profile, Detailed code = " + intent.getIntExtra(EuiccManager.EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE, 0));
                    } else {
                        Log.e(RecoverySystem.TAG, "Successfully remove euicc opportunistic profile.");
                        atomicInteger.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }
        };
        Intent intent = new Intent(ACTION_EUICC_REMOVE_INVISIBLE_SUBSCRIPTIONS);
        intent.setPackage("android");
        PendingIntent broadcastAsUser = PendingIntent.getBroadcastAsUser(context, 0, intent, 134217728, UserHandle.SYSTEM);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_EUICC_REMOVE_INVISIBLE_SUBSCRIPTIONS);
        HandlerThread handlerThread = new HandlerThread("euiccRemovingSubsReceiverThread");
        handlerThread.start();
        context.getApplicationContext().registerReceiver(broadcastReceiver, intentFilter, null, new Handler(handlerThread.getLooper()));
        for (SubscriptionInfo subscriptionInfo : list) {
            Log.i(TAG, "Remove invisible subscription " + subscriptionInfo.getSubscriptionId() + " from card " + subscriptionInfo.getCardId());
            euiccManager.createForCardId(subscriptionInfo.getCardId()).deleteSubscription(subscriptionInfo.getSubscriptionId(), broadcastAsUser);
        }
        try {
            long j = Settings.Global.getLong(context.getContentResolver(), Settings.Global.EUICC_REMOVING_INVISIBLE_PROFILES_TIMEOUT_MILLIS, DEFAULT_EUICC_REMOVING_INVISIBLE_PROFILES_TIMEOUT_MILLIS);
            if (j < MIN_EUICC_REMOVING_INVISIBLE_PROFILES_TIMEOUT_MILLIS) {
                j = 15000;
            } else if (j > MAX_EUICC_REMOVING_INVISIBLE_PROFILES_TIMEOUT_MILLIS) {
                j = 90000;
            }
            if (!countDownLatch.await(j, TimeUnit.MILLISECONDS)) {
                Log.e(TAG, "Timeout removing invisible euicc profiles.");
                return false;
            }
            context.getApplicationContext().unregisterReceiver(broadcastReceiver);
            handlerThread.quit();
            return atomicInteger.get() == list.size();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.e(TAG, "Removing invisible euicc profiles interrupted", e);
            return false;
        } finally {
            context.getApplicationContext().unregisterReceiver(broadcastReceiver);
            handlerThread.quit();
        }
        context.getApplicationContext().unregisterReceiver(broadcastReceiver);
        handlerThread.quit();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0028 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void rebootPromptAndWipeUserData(android.content.Context r6, java.lang.String r7) throws java.io.IOException {
        /*
            java.lang.String r0 = "rescueparty"
            java.lang.String r1 = "RecoverySystem"
            r2 = 0
            r3 = 0
            java.lang.String r4 = "vold"
            android.os.IBinder r4 = android.os.ServiceManager.checkService(r4)     // Catch: java.lang.Exception -> L1f
            android.os.IVold r4 = android.os.IVold.Stub.asInterface(r4)     // Catch: java.lang.Exception -> L1f
            if (r4 == 0) goto L19
            boolean r5 = r4.needsCheckpoint()     // Catch: java.lang.Exception -> L20
            goto L26
        L19:
            java.lang.String r5 = "Failed to get vold"
            android.util.Log.w(r1, r5)     // Catch: java.lang.Exception -> L20
            goto L25
        L1f:
            r4 = r2
        L20:
            java.lang.String r5 = "Failed to check for checkpointing"
            android.util.Log.w(r1, r5)
        L25:
            r5 = r3
        L26:
            if (r5 == 0) goto L43
            r4.abortChanges(r0, r3)     // Catch: java.lang.Exception -> L31
            java.lang.String r7 = "Rescue Party requested wipe. Aborting update"
            android.util.Log.i(r1, r7)     // Catch: java.lang.Exception -> L31
            goto L42
        L31:
            java.lang.String r7 = "Rescue Party requested wipe. Rebooting instead."
            android.util.Log.i(r1, r7)
            java.lang.String r7 = "power"
            java.lang.Object r6 = r6.getSystemService(r7)
            android.os.PowerManager r6 = (android.os.PowerManager) r6
            r6.reboot(r0)
        L42:
            return
        L43:
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 != 0) goto L61
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "--reason="
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r7 = sanitizeArg(r7)
            java.lang.StringBuilder r7 = r0.append(r7)
            java.lang.String r7 = r7.toString()
            goto L62
        L61:
            r7 = r2
        L62:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "--locale="
            java.lang.StringBuilder r0 = r0.append(r1)
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 4
            java.lang.String[] r1 = new java.lang.String[r1]
            r1[r3] = r2
            r2 = 1
            java.lang.String r3 = "--prompt_and_wipe_data"
            r1[r2] = r3
            r2 = 2
            r1[r2] = r7
            r7 = 3
            r1[r7] = r0
            bootCommand(r6, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.RecoverySystem.rebootPromptAndWipeUserData(android.content.Context, java.lang.String):void");
    }

    public static void rebootWipeCache(Context context) throws IOException {
        rebootWipeCache(context, context.getPackageName());
    }

    public static void rebootWipeCache(Context context, String str) throws IOException {
        bootCommand(context, "--wipe_cache", !TextUtils.isEmpty(str) ? "--reason=" + sanitizeArg(str) : null, "--locale=" + Locale.getDefault().toLanguageTag());
    }

    @SystemApi
    public static void rebootWipeAb(Context context, File file, String str) throws IOException {
        bootCommand(context, "--wipe_ab", "--wipe_package=" + file.getCanonicalPath(), !TextUtils.isEmpty(str) ? "--reason=" + sanitizeArg(str) : null, "--locale=" + Locale.getDefault().toLanguageTag());
    }

    private static void bootCommand(Context context, String... strArr) throws IOException {
        LOG_FILE.delete();
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
                sb.append("\n");
            }
        }
        ((RecoverySystem) context.getSystemService("recovery")).rebootRecoveryWithCommand(sb.toString());
        throw new IOException("Reboot failed (no permissions?)");
    }

    public static String handleAftermath(Context context) {
        String textFile;
        String textFile2 = null;
        try {
            textFile = FileUtils.readTextFile(LOG_FILE, -65536, "...\n");
        } catch (FileNotFoundException unused) {
            Log.i(TAG, "No recovery log file");
            textFile = null;
        } catch (IOException e) {
            Log.e(TAG, "Error reading recovery log", e);
            textFile = null;
        }
        boolean zExists = BLOCK_MAP_FILE.exists();
        if (!zExists) {
            File file = UNCRYPT_PACKAGE_FILE;
            if (file.exists()) {
                try {
                    textFile2 = FileUtils.readTextFile(file, 0, null);
                } catch (IOException e2) {
                    Log.e(TAG, "Error reading uncrypt file", e2);
                }
                if (textFile2 != null && textFile2.startsWith("/data")) {
                    if (UNCRYPT_PACKAGE_FILE.delete()) {
                        Log.i(TAG, "Deleted: " + textFile2);
                    } else {
                        Log.e(TAG, "Can't delete: " + textFile2);
                    }
                }
            }
        }
        String[] list = RECOVERY_DIR.list();
        for (int i = 0; list != null && i < list.length; i++) {
            if (!list[i].startsWith(LAST_PREFIX) && !list[i].equals(LAST_INSTALL_PATH) && ((!zExists || !list[i].equals(BLOCK_MAP_FILE.getName())) && (!zExists || !list[i].equals(UNCRYPT_PACKAGE_FILE.getName())))) {
                recursiveDelete(new File(RECOVERY_DIR, list[i]));
            }
        }
        return textFile;
    }

    private static void recursiveDelete(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();
            for (int i = 0; list != null && i < list.length; i++) {
                recursiveDelete(new File(file, list[i]));
            }
        }
        if (!file.delete()) {
            Log.e(TAG, "Can't delete: " + file);
        } else {
            Log.i(TAG, "Deleted: " + file);
        }
    }

    private boolean uncrypt(String str, IRecoverySystemProgressListener iRecoverySystemProgressListener) {
        try {
            return this.mService.uncrypt(str, iRecoverySystemProgressListener);
        } catch (RemoteException unused) {
            return false;
        }
    }

    private boolean setupBcb(String str) {
        try {
            return this.mService.setupBcb(str);
        } catch (RemoteException unused) {
            return false;
        }
    }

    private boolean clearBcb() {
        try {
            return this.mService.clearBcb();
        } catch (RemoteException unused) {
            return false;
        }
    }

    private void rebootRecoveryWithCommand(String str) {
        try {
            this.mService.rebootRecoveryWithCommand(str);
        } catch (RemoteException unused) {
        }
    }

    private boolean requestLskf(String str, IntentSender intentSender) throws IOException {
        try {
            return this.mService.requestLskf(str, intentSender);
        } catch (RemoteException unused) {
            throw new IOException("could request update");
        }
    }

    private boolean clearLskf() throws IOException {
        try {
            return this.mService.clearLskf();
        } catch (RemoteException unused) {
            throw new IOException("could not clear LSKF");
        }
    }

    private boolean rebootWithLskf(String str, String str2) throws IOException {
        try {
            return this.mService.rebootWithLskf(str, str2);
        } catch (RemoteException unused) {
            throw new IOException("could not reboot for update");
        }
    }

    private static String sanitizeArg(String str) {
        return str.replace((char) 0, '?').replace('\n', '?');
    }

    public RecoverySystem() {
        this.mService = null;
    }

    public RecoverySystem(IRecoverySystem iRecoverySystem) {
        this.mService = iRecoverySystem;
    }
}
