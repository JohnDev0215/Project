package android.os;

import android.content.pm.ApplicationInfo;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Process;
import android.util.Log;
import android.util.Pair;
import android.util.Slog;
import androidx.exifinterface.media.ExifInterface;
import com.android.internal.os.Zygote;
import com.android.internal.os.ZygoteConfig;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class ZygoteProcess {
    private static final String[] INVALID_USAP_FLAGS = {"--query-abi-list", "--get-pid", "--preload-default", "--preload-package", "--preload-app", "--start-child-zygote", "--set-api-blacklist-exemptions", "--hidden-api-log-sampling-rate", "--hidden-api-statslog-sampling-rate", "--invoke-with"};
    private static final String LOG_TAG = "ZygoteProcess";
    private static final String USAP_POOL_ENABLED_DEFAULT = "false";
    private static final int ZYGOTE_CONNECT_RETRY_DELAY_MS = 50;
    private static final int ZYGOTE_CONNECT_TIMEOUT_MS = 20000;
    static final int ZYGOTE_RETRY_MILLIS = 500;
    private List<String> mApiBlacklistExemptions;
    private int mHiddenApiAccessLogSampleRate;
    private int mHiddenApiAccessStatslogSampleRate;
    private boolean mIsFirstPropCheck;
    private long mLastPropCheckTimestamp;
    private final Object mLock;
    private boolean mUsapPoolEnabled;
    private final LocalSocketAddress mUsapPoolSecondarySocketAddress;
    private final LocalSocketAddress mUsapPoolSocketAddress;
    private final boolean mUsapPoolSupported;
    private final LocalSocketAddress mZygoteSecondarySocketAddress;
    private final LocalSocketAddress mZygoteSocketAddress;
    private ZygoteState primaryZygoteState;
    private ZygoteState secondaryZygoteState;

    private static boolean policySpecifiesUsapPoolLaunch(int i) {
        return (i & 5) == 1;
    }

    public ZygoteProcess() {
        this.mLock = new Object();
        this.mApiBlacklistExemptions = Collections.emptyList();
        this.mUsapPoolEnabled = false;
        this.mIsFirstPropCheck = true;
        this.mLastPropCheckTimestamp = 0L;
        this.mZygoteSocketAddress = new LocalSocketAddress(Zygote.PRIMARY_SOCKET_NAME, LocalSocketAddress.Namespace.RESERVED);
        this.mZygoteSecondarySocketAddress = new LocalSocketAddress(Zygote.SECONDARY_SOCKET_NAME, LocalSocketAddress.Namespace.RESERVED);
        this.mUsapPoolSocketAddress = new LocalSocketAddress(Zygote.USAP_POOL_PRIMARY_SOCKET_NAME, LocalSocketAddress.Namespace.RESERVED);
        this.mUsapPoolSecondarySocketAddress = new LocalSocketAddress(Zygote.USAP_POOL_SECONDARY_SOCKET_NAME, LocalSocketAddress.Namespace.RESERVED);
        this.mUsapPoolSupported = true;
    }

    public ZygoteProcess(LocalSocketAddress localSocketAddress, LocalSocketAddress localSocketAddress2) {
        this.mLock = new Object();
        this.mApiBlacklistExemptions = Collections.emptyList();
        this.mUsapPoolEnabled = false;
        this.mIsFirstPropCheck = true;
        this.mLastPropCheckTimestamp = 0L;
        this.mZygoteSocketAddress = localSocketAddress;
        this.mZygoteSecondarySocketAddress = localSocketAddress2;
        this.mUsapPoolSocketAddress = null;
        this.mUsapPoolSecondarySocketAddress = null;
        this.mUsapPoolSupported = false;
    }

    public LocalSocketAddress getPrimarySocketAddress() {
        return this.mZygoteSocketAddress;
    }

    private static class ZygoteState implements AutoCloseable {
        private final List<String> mAbiList;
        private boolean mClosed;
        final LocalSocketAddress mUsapSocketAddress;
        final DataInputStream mZygoteInputStream;
        final BufferedWriter mZygoteOutputWriter;
        private final LocalSocket mZygoteSessionSocket;
        final LocalSocketAddress mZygoteSocketAddress;

        private ZygoteState(LocalSocketAddress localSocketAddress, LocalSocketAddress localSocketAddress2, LocalSocket localSocket, DataInputStream dataInputStream, BufferedWriter bufferedWriter, List<String> list) {
            this.mZygoteSocketAddress = localSocketAddress;
            this.mUsapSocketAddress = localSocketAddress2;
            this.mZygoteSessionSocket = localSocket;
            this.mZygoteInputStream = dataInputStream;
            this.mZygoteOutputWriter = bufferedWriter;
            this.mAbiList = list;
        }

        static ZygoteState connect(LocalSocketAddress localSocketAddress, LocalSocketAddress localSocketAddress2) throws IOException {
            LocalSocket localSocket = new LocalSocket();
            if (localSocketAddress == null) {
                throw new IllegalArgumentException("zygoteSocketAddress can't be null");
            }
            try {
                localSocket.connect(localSocketAddress);
                DataInputStream dataInputStream = new DataInputStream(localSocket.getInputStream());
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(localSocket.getOutputStream()), 256);
                return new ZygoteState(localSocketAddress, localSocketAddress2, localSocket, dataInputStream, bufferedWriter, ZygoteProcess.getAbiList(bufferedWriter, dataInputStream));
            } catch (IOException e) {
                try {
                    localSocket.close();
                } catch (IOException unused) {
                }
                throw e;
            }
        }

        LocalSocket getUsapSessionSocket() throws IOException {
            LocalSocket localSocket = new LocalSocket();
            localSocket.connect(this.mUsapSocketAddress);
            return localSocket;
        }

        boolean matches(String str) {
            return this.mAbiList.contains(str);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            try {
                this.mZygoteSessionSocket.close();
            } catch (IOException e) {
                Log.e(ZygoteProcess.LOG_TAG, "I/O exception on routine close", e);
            }
            this.mClosed = true;
        }

        boolean isClosed() {
            return this.mClosed;
        }
    }

    public final Process.ProcessStartResult start(String str, String str2, int i, int i2, int[] iArr, int i3, int i4, int i5, String str3, String str4, String str5, String str6, String str7, String str8, int i6, boolean z, long[] jArr, Map<String, Pair<String, Long>> map, Map<String, Pair<String, Long>> map2, boolean z2, boolean z3, String[] strArr) {
        if (fetchUsapPoolEnabledPropWithMinInterval()) {
            informZygotesOfUsapPoolStatus();
        }
        try {
            return startViaZygote(str, str2, i, i2, iArr, i3, i4, i5, str3, str4, str5, str6, str7, false, str8, i6, z, jArr, map, map2, z2, z3, strArr);
        } catch (ZygoteStartFailedEx e) {
            Log.e(LOG_TAG, "Starting VM process through Zygote failed");
            throw new RuntimeException("Starting VM process through Zygote failed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> getAbiList(BufferedWriter bufferedWriter, DataInputStream dataInputStream) throws IOException {
        bufferedWriter.write("1");
        bufferedWriter.newLine();
        bufferedWriter.write("--query-abi-list");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(bArr);
        return Arrays.asList(new String(bArr, StandardCharsets.US_ASCII).split(","));
    }

    private Process.ProcessStartResult zygoteSendArgsAndGetResult(ZygoteState zygoteState, int i, ArrayList<String> arrayList) throws ZygoteStartFailedEx {
        for (String str : arrayList) {
            if (str.indexOf(10) >= 0) {
                throw new ZygoteStartFailedEx("Embedded newlines not allowed");
            }
            if (str.indexOf(13) >= 0) {
                throw new ZygoteStartFailedEx("Embedded carriage returns not allowed");
            }
        }
        String str2 = arrayList.size() + BydAdbHelper.COMMAND_LINE_END + String.join(BydAdbHelper.COMMAND_LINE_END, arrayList) + BydAdbHelper.COMMAND_LINE_END;
        if (shouldAttemptUsapLaunch(i, arrayList)) {
            try {
                return attemptUsapSendArgsAndGetResult(zygoteState, str2);
            } catch (IOException e) {
                Log.e(LOG_TAG, "IO Exception while communicating with USAP pool - " + e.getMessage());
            }
        }
        return attemptZygoteSendArgsAndGetResult(zygoteState, str2);
    }

    private Process.ProcessStartResult attemptZygoteSendArgsAndGetResult(ZygoteState zygoteState, String str) throws ZygoteStartFailedEx {
        try {
            BufferedWriter bufferedWriter = zygoteState.mZygoteOutputWriter;
            DataInputStream dataInputStream = zygoteState.mZygoteInputStream;
            bufferedWriter.write(str);
            bufferedWriter.flush();
            Process.ProcessStartResult processStartResult = new Process.ProcessStartResult();
            processStartResult.pid = dataInputStream.readInt();
            processStartResult.usingWrapper = dataInputStream.readBoolean();
            if (processStartResult.pid >= 0) {
                return processStartResult;
            }
            throw new ZygoteStartFailedEx("fork() failed");
        } catch (IOException e) {
            zygoteState.close();
            Log.e(LOG_TAG, "IO Exception while communicating with Zygote - " + e.toString());
            throw new ZygoteStartFailedEx(e);
        }
    }

    private Process.ProcessStartResult attemptUsapSendArgsAndGetResult(ZygoteState zygoteState, String str) throws ZygoteStartFailedEx, IOException {
        LocalSocket usapSessionSocket = zygoteState.getUsapSessionSocket();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(usapSessionSocket.getOutputStream()), 256);
            DataInputStream dataInputStream = new DataInputStream(usapSessionSocket.getInputStream());
            bufferedWriter.write(str);
            bufferedWriter.flush();
            Process.ProcessStartResult processStartResult = new Process.ProcessStartResult();
            processStartResult.pid = dataInputStream.readInt();
            processStartResult.usingWrapper = false;
            if (processStartResult.pid < 0) {
                throw new ZygoteStartFailedEx("USAP specialization failed");
            }
            if (usapSessionSocket != null) {
                usapSessionSocket.close();
            }
            return processStartResult;
        } catch (Throwable th) {
            if (usapSessionSocket != null) {
                try {
                    usapSessionSocket.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private boolean shouldAttemptUsapLaunch(int i, ArrayList<String> arrayList) {
        return this.mUsapPoolSupported && this.mUsapPoolEnabled && policySpecifiesUsapPoolLaunch(i) && commandSupportedByUsap(arrayList);
    }

    private static boolean commandSupportedByUsap(ArrayList<String> arrayList) {
        for (String str : arrayList) {
            for (String str2 : INVALID_USAP_FLAGS) {
                if (str.startsWith(str2)) {
                    return false;
                }
            }
            if (str.startsWith("--nice-name=") && Zygote.getWrapProperty(str.substring(12)) != null) {
                return false;
            }
        }
        return true;
    }

    private Process.ProcessStartResult startViaZygote(String str, String str2, int i, int i2, int[] iArr, int i3, int i4, int i5, String str3, String str4, String str5, String str6, String str7, boolean z, String str8, int i6, boolean z2, long[] jArr, Map<String, Pair<String, Long>> map, Map<String, Pair<String, Long>> map2, boolean z3, boolean z4, String[] strArr) throws ZygoteStartFailedEx {
        Process.ProcessStartResult processStartResultZygoteSendArgsAndGetResult;
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("--runtime-args");
        arrayList.add("--setuid=" + i);
        arrayList.add("--setgid=" + i2);
        arrayList.add("--runtime-flags=" + i3);
        if (i4 == 1) {
            arrayList.add("--mount-external-default");
        } else if (i4 == 2) {
            arrayList.add("--mount-external-read");
        } else if (i4 == 3) {
            arrayList.add("--mount-external-write");
        } else if (i4 == 6) {
            arrayList.add("--mount-external-full");
        } else if (i4 == 5) {
            arrayList.add("--mount-external-installer");
        } else if (i4 == 4) {
            arrayList.add("--mount-external-legacy");
        } else if (i4 == 7) {
            arrayList.add("--mount-external-pass-through");
        } else if (i4 == 8) {
            arrayList.add("--mount-external-android-writable");
        }
        arrayList.add("--target-sdk-version=" + i5);
        if (iArr != null && iArr.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("--setgroups=");
            int length = iArr.length;
            for (int i7 = 0; i7 < length; i7++) {
                if (i7 != 0) {
                    sb.append(',');
                }
                sb.append(iArr[i7]);
            }
            arrayList.add(sb.toString());
        }
        if (str2 != null) {
            arrayList.add("--nice-name=" + str2);
        }
        if (str3 != null) {
            arrayList.add("--seinfo=" + str3);
        }
        if (str5 != null) {
            arrayList.add("--instruction-set=" + str5);
        }
        if (str6 != null) {
            arrayList.add("--app-data-dir=" + str6);
        }
        if (str7 != null) {
            arrayList.add("--invoke-with");
            arrayList.add(str7);
        }
        if (z) {
            arrayList.add("--start-child-zygote");
        }
        if (str8 != null) {
            arrayList.add("--package-name=" + str8);
        }
        if (z2) {
            arrayList.add(Zygote.START_AS_TOP_APP_ARG);
        }
        if (map != null && map.size() > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Zygote.PKG_DATA_INFO_MAP);
            sb2.append("=");
            boolean z5 = false;
            for (Map.Entry<String, Pair<String, Long>> entry : map.entrySet()) {
                if (z5) {
                    sb2.append(',');
                }
                sb2.append(entry.getKey());
                sb2.append(',');
                sb2.append(entry.getValue().first);
                sb2.append(',');
                sb2.append(entry.getValue().second);
                z5 = true;
            }
            arrayList.add(sb2.toString());
        }
        if (map2 != null && map2.size() > 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(Zygote.WHITELISTED_DATA_INFO_MAP);
            sb3.append("=");
            boolean z6 = false;
            for (Map.Entry<String, Pair<String, Long>> entry2 : map2.entrySet()) {
                if (z6) {
                    sb3.append(',');
                }
                sb3.append(entry2.getKey());
                sb3.append(',');
                sb3.append(entry2.getValue().first);
                sb3.append(',');
                sb3.append(entry2.getValue().second);
                z6 = true;
            }
            arrayList.add(sb3.toString());
        }
        if (z4) {
            arrayList.add(Zygote.BIND_MOUNT_APP_STORAGE_DIRS);
        }
        if (z3) {
            arrayList.add(Zygote.BIND_MOUNT_APP_DATA_DIRS);
        }
        if (jArr != null && jArr.length > 0) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("--disabled-compat-changes=");
            int length2 = jArr.length;
            for (int i8 = 0; i8 < length2; i8++) {
                if (i8 != 0) {
                    sb4.append(',');
                }
                sb4.append(jArr[i8]);
            }
            arrayList.add(sb4.toString());
        }
        arrayList.add(str);
        if (strArr != null) {
            Collections.addAll(arrayList, strArr);
        }
        synchronized (this.mLock) {
            processStartResultZygoteSendArgsAndGetResult = zygoteSendArgsAndGetResult(openZygoteSocketIfNeeded(str4), i6, arrayList);
        }
        return processStartResultZygoteSendArgsAndGetResult;
    }

    private boolean fetchUsapPoolEnabledProp() {
        boolean z = this.mUsapPoolEnabled;
        if (!Zygote.getConfigurationProperty(ZygoteConfig.USAP_POOL_ENABLED, USAP_POOL_ENABLED_DEFAULT).isEmpty()) {
            this.mUsapPoolEnabled = Zygote.getConfigurationPropertyBoolean(ZygoteConfig.USAP_POOL_ENABLED, Boolean.valueOf(Boolean.parseBoolean(USAP_POOL_ENABLED_DEFAULT)));
        }
        boolean z2 = z != this.mUsapPoolEnabled;
        if (z2) {
            Log.i(LOG_TAG, "usapPoolEnabled = " + this.mUsapPoolEnabled);
        }
        return z2;
    }

    private boolean fetchUsapPoolEnabledPropWithMinInterval() {
        if (!this.mUsapPoolSupported) {
            return false;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (!this.mIsFirstPropCheck && jElapsedRealtime - this.mLastPropCheckTimestamp < 60000) {
            return false;
        }
        this.mIsFirstPropCheck = false;
        this.mLastPropCheckTimestamp = jElapsedRealtime;
        return fetchUsapPoolEnabledProp();
    }

    public void close() {
        ZygoteState zygoteState = this.primaryZygoteState;
        if (zygoteState != null) {
            zygoteState.close();
        }
        ZygoteState zygoteState2 = this.secondaryZygoteState;
        if (zygoteState2 != null) {
            zygoteState2.close();
        }
    }

    public void establishZygoteConnectionForAbi(String str) {
        try {
            synchronized (this.mLock) {
                openZygoteSocketIfNeeded(str);
            }
        } catch (ZygoteStartFailedEx e) {
            throw new RuntimeException("Unable to connect to zygote for abi: " + str, e);
        }
    }

    public int getZygotePid(String str) {
        int i;
        try {
            synchronized (this.mLock) {
                ZygoteState zygoteStateOpenZygoteSocketIfNeeded = openZygoteSocketIfNeeded(str);
                zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write("1");
                zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
                zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write("--get-pid");
                zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
                zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.flush();
                byte[] bArr = new byte[zygoteStateOpenZygoteSocketIfNeeded.mZygoteInputStream.readInt()];
                zygoteStateOpenZygoteSocketIfNeeded.mZygoteInputStream.readFully(bArr);
                i = Integer.parseInt(new String(bArr, StandardCharsets.US_ASCII));
            }
            return i;
        } catch (Exception e) {
            throw new RuntimeException("Failure retrieving pid", e);
        }
    }

    public void bootCompleted() {
        if (Build.SUPPORTED_32_BIT_ABIS.length > 0) {
            bootCompleted(Build.SUPPORTED_32_BIT_ABIS[0]);
        }
        if (Build.SUPPORTED_64_BIT_ABIS.length > 0) {
            bootCompleted(Build.SUPPORTED_64_BIT_ABIS[0]);
        }
    }

    private void bootCompleted(String str) {
        try {
            synchronized (this.mLock) {
                ZygoteState zygoteStateOpenZygoteSocketIfNeeded = openZygoteSocketIfNeeded(str);
                zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write("1\n--boot-completed\n");
                zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.flush();
                zygoteStateOpenZygoteSocketIfNeeded.mZygoteInputStream.readInt();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to inform zygote of boot_completed", e);
        }
    }

    public boolean setApiBlacklistExemptions(List<String> list) {
        boolean zMaybeSetApiBlacklistExemptions;
        synchronized (this.mLock) {
            this.mApiBlacklistExemptions = list;
            zMaybeSetApiBlacklistExemptions = maybeSetApiBlacklistExemptions(this.primaryZygoteState, true);
            if (zMaybeSetApiBlacklistExemptions) {
                zMaybeSetApiBlacklistExemptions = maybeSetApiBlacklistExemptions(this.secondaryZygoteState, true);
            }
        }
        return zMaybeSetApiBlacklistExemptions;
    }

    public void setHiddenApiAccessLogSampleRate(int i) {
        synchronized (this.mLock) {
            this.mHiddenApiAccessLogSampleRate = i;
            maybeSetHiddenApiAccessLogSampleRate(this.primaryZygoteState);
            maybeSetHiddenApiAccessLogSampleRate(this.secondaryZygoteState);
        }
    }

    public void setHiddenApiAccessStatslogSampleRate(int i) {
        synchronized (this.mLock) {
            this.mHiddenApiAccessStatslogSampleRate = i;
            maybeSetHiddenApiAccessStatslogSampleRate(this.primaryZygoteState);
            maybeSetHiddenApiAccessStatslogSampleRate(this.secondaryZygoteState);
        }
    }

    private boolean maybeSetApiBlacklistExemptions(ZygoteState zygoteState, boolean z) {
        if (zygoteState == null || zygoteState.isClosed()) {
            Slog.e(LOG_TAG, "Can't set API blacklist exemptions: no zygote connection");
            return false;
        }
        if (!z && this.mApiBlacklistExemptions.isEmpty()) {
            return true;
        }
        try {
            zygoteState.mZygoteOutputWriter.write(Integer.toString(this.mApiBlacklistExemptions.size() + 1));
            zygoteState.mZygoteOutputWriter.newLine();
            zygoteState.mZygoteOutputWriter.write("--set-api-blacklist-exemptions");
            zygoteState.mZygoteOutputWriter.newLine();
            for (int i = 0; i < this.mApiBlacklistExemptions.size(); i++) {
                zygoteState.mZygoteOutputWriter.write(this.mApiBlacklistExemptions.get(i));
                zygoteState.mZygoteOutputWriter.newLine();
            }
            zygoteState.mZygoteOutputWriter.flush();
            int i2 = zygoteState.mZygoteInputStream.readInt();
            if (i2 != 0) {
                Slog.e(LOG_TAG, "Failed to set API blacklist exemptions; status " + i2);
            }
            return true;
        } catch (IOException e) {
            Slog.e(LOG_TAG, "Failed to set API blacklist exemptions", e);
            this.mApiBlacklistExemptions = Collections.emptyList();
            return false;
        }
    }

    private void maybeSetHiddenApiAccessLogSampleRate(ZygoteState zygoteState) {
        if (zygoteState == null || zygoteState.isClosed() || this.mHiddenApiAccessLogSampleRate == -1) {
            return;
        }
        try {
            zygoteState.mZygoteOutputWriter.write(Integer.toString(1));
            zygoteState.mZygoteOutputWriter.newLine();
            zygoteState.mZygoteOutputWriter.write("--hidden-api-log-sampling-rate=" + this.mHiddenApiAccessLogSampleRate);
            zygoteState.mZygoteOutputWriter.newLine();
            zygoteState.mZygoteOutputWriter.flush();
            int i = zygoteState.mZygoteInputStream.readInt();
            if (i != 0) {
                Slog.e(LOG_TAG, "Failed to set hidden API log sampling rate; status " + i);
            }
        } catch (IOException e) {
            Slog.e(LOG_TAG, "Failed to set hidden API log sampling rate", e);
        }
    }

    private void maybeSetHiddenApiAccessStatslogSampleRate(ZygoteState zygoteState) {
        if (zygoteState == null || zygoteState.isClosed() || this.mHiddenApiAccessStatslogSampleRate == -1) {
            return;
        }
        try {
            zygoteState.mZygoteOutputWriter.write(Integer.toString(1));
            zygoteState.mZygoteOutputWriter.newLine();
            zygoteState.mZygoteOutputWriter.write("--hidden-api-statslog-sampling-rate=" + this.mHiddenApiAccessStatslogSampleRate);
            zygoteState.mZygoteOutputWriter.newLine();
            zygoteState.mZygoteOutputWriter.flush();
            int i = zygoteState.mZygoteInputStream.readInt();
            if (i != 0) {
                Slog.e(LOG_TAG, "Failed to set hidden API statslog sampling rate; status " + i);
            }
        } catch (IOException e) {
            Slog.e(LOG_TAG, "Failed to set hidden API statslog sampling rate", e);
        }
    }

    private void attemptConnectionToPrimaryZygote() throws IOException {
        ZygoteState zygoteState = this.primaryZygoteState;
        if (zygoteState == null || zygoteState.isClosed()) {
            ZygoteState zygoteStateConnect = ZygoteState.connect(this.mZygoteSocketAddress, this.mUsapPoolSocketAddress);
            this.primaryZygoteState = zygoteStateConnect;
            maybeSetApiBlacklistExemptions(zygoteStateConnect, false);
            maybeSetHiddenApiAccessLogSampleRate(this.primaryZygoteState);
        }
    }

    private void attemptConnectionToSecondaryZygote() throws IOException {
        ZygoteState zygoteState = this.secondaryZygoteState;
        if (zygoteState == null || zygoteState.isClosed()) {
            ZygoteState zygoteStateConnect = ZygoteState.connect(this.mZygoteSecondarySocketAddress, this.mUsapPoolSecondarySocketAddress);
            this.secondaryZygoteState = zygoteStateConnect;
            maybeSetApiBlacklistExemptions(zygoteStateConnect, false);
            maybeSetHiddenApiAccessLogSampleRate(this.secondaryZygoteState);
        }
    }

    private ZygoteState openZygoteSocketIfNeeded(String str) throws ZygoteStartFailedEx {
        try {
            attemptConnectionToPrimaryZygote();
            if (this.primaryZygoteState.matches(str)) {
                return this.primaryZygoteState;
            }
            if (this.mZygoteSecondarySocketAddress != null) {
                attemptConnectionToSecondaryZygote();
                if (this.secondaryZygoteState.matches(str)) {
                    return this.secondaryZygoteState;
                }
            }
            throw new ZygoteStartFailedEx("Unsupported zygote ABI: " + str);
        } catch (IOException e) {
            throw new ZygoteStartFailedEx("Error connecting to zygote", e);
        }
    }

    public boolean preloadApp(ApplicationInfo applicationInfo, String str) throws ZygoteStartFailedEx, IOException {
        boolean z;
        synchronized (this.mLock) {
            ZygoteState zygoteStateOpenZygoteSocketIfNeeded = openZygoteSocketIfNeeded(str);
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write(ExifInterface.GPS_MEASUREMENT_2D);
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write("--preload-app");
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            Parcel parcelObtain = Parcel.obtain();
            applicationInfo.writeToParcel(parcelObtain, 0);
            String strEncodeToString = Base64.getEncoder().encodeToString(parcelObtain.marshall());
            parcelObtain.recycle();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write(strEncodeToString);
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.flush();
            z = zygoteStateOpenZygoteSocketIfNeeded.mZygoteInputStream.readInt() == 0;
        }
        return z;
    }

    public boolean preloadPackageForAbi(String str, String str2, String str3, String str4, String str5) throws ZygoteStartFailedEx, IOException {
        boolean z;
        synchronized (this.mLock) {
            ZygoteState zygoteStateOpenZygoteSocketIfNeeded = openZygoteSocketIfNeeded(str5);
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write("5");
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write("--preload-package");
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write(str);
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write(str2);
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write(str3);
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write(str4);
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.flush();
            z = zygoteStateOpenZygoteSocketIfNeeded.mZygoteInputStream.readInt() == 0;
        }
        return z;
    }

    public boolean preloadDefault(String str) throws ZygoteStartFailedEx, IOException {
        boolean z;
        synchronized (this.mLock) {
            ZygoteState zygoteStateOpenZygoteSocketIfNeeded = openZygoteSocketIfNeeded(str);
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write("1");
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.write("--preload-default");
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.newLine();
            zygoteStateOpenZygoteSocketIfNeeded.mZygoteOutputWriter.flush();
            z = zygoteStateOpenZygoteSocketIfNeeded.mZygoteInputStream.readInt() == 0;
        }
        return z;
    }

    public static void waitForConnectionToZygote(String str) {
        waitForConnectionToZygote(new LocalSocketAddress(str, LocalSocketAddress.Namespace.RESERVED));
    }

    public static void waitForConnectionToZygote(LocalSocketAddress localSocketAddress) {
        for (int i = 400; i >= 0; i--) {
            try {
                ZygoteState.connect(localSocketAddress, null).close();
                return;
            } catch (IOException e) {
                Log.w(LOG_TAG, "Got error connecting to zygote, retrying. msg= " + e.getMessage());
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException unused) {
                }
            }
        }
        Slog.wtf(LOG_TAG, "Failed to connect to Zygote through socket " + localSocketAddress.getName());
    }

    private void informZygotesOfUsapPoolStatus() {
        String str = "1\n--usap-pool-enabled=" + this.mUsapPoolEnabled + BydAdbHelper.COMMAND_LINE_END;
        synchronized (this.mLock) {
            try {
                try {
                    attemptConnectionToPrimaryZygote();
                    this.primaryZygoteState.mZygoteOutputWriter.write(str);
                    this.primaryZygoteState.mZygoteOutputWriter.flush();
                    if (this.mZygoteSecondarySocketAddress != null) {
                        try {
                            attemptConnectionToSecondaryZygote();
                            try {
                                this.secondaryZygoteState.mZygoteOutputWriter.write(str);
                                this.secondaryZygoteState.mZygoteOutputWriter.flush();
                                this.secondaryZygoteState.mZygoteInputStream.readInt();
                            } catch (IOException e) {
                                throw new IllegalStateException("USAP pool state change cause an irrecoverable error", e);
                            }
                        } catch (IOException unused) {
                        }
                    }
                    try {
                        this.primaryZygoteState.mZygoteInputStream.readInt();
                    } catch (IOException e2) {
                        throw new IllegalStateException("USAP pool state change cause an irrecoverable error", e2);
                    }
                } catch (IOException e3) {
                    this.mUsapPoolEnabled = !this.mUsapPoolEnabled;
                    Log.w(LOG_TAG, "Failed to inform zygotes of USAP pool status: " + e3.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public ChildZygoteProcess startChildZygote(String str, String str2, int i, int i2, int[] iArr, int i3, String str3, String str4, String str5, String str6, int i4, int i5) {
        LocalSocketAddress localSocketAddress = new LocalSocketAddress(str + "/" + UUID.randomUUID().toString());
        try {
            return new ChildZygoteProcess(localSocketAddress, startViaZygote(str, str2, i, i2, iArr, i3, 0, 0, str3, str4, str6, null, null, true, null, 4, false, null, null, null, true, false, new String[]{Zygote.CHILD_ZYGOTE_SOCKET_NAME_ARG + localSocketAddress.getName(), Zygote.CHILD_ZYGOTE_ABI_LIST_ARG + str5, Zygote.CHILD_ZYGOTE_UID_RANGE_START + i4, Zygote.CHILD_ZYGOTE_UID_RANGE_END + i5}).pid);
        } catch (ZygoteStartFailedEx e) {
            throw new RuntimeException("Starting child-zygote through Zygote failed", e);
        }
    }
}
