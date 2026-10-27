package cerence.ark.assistant.common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class Logger {
    private static final int D = 3;
    private static final int E = 6;
    private static final String FILE_NAME_FORMAT = "%s_%s_%s_%s.txt";
    private static final Handler HANDLER;
    private static final int I = 4;
    private static final String LOG_FILE_DATE_FORMAT = "yyMMdd_HHmmss";
    private static final int MAX_LEN = 3500;
    private static final String TAG = "Logger";
    private static final int TAG_MAX_LEN = 35;
    private static final String TAG_PREFIX = "CA.";
    private static final int V = 2;
    private static final int W = 5;
    private static File sCurrentFile;
    private static LogFileLimiter sFileLimiter;
    private static final String[] T = {"VERBOSE", "DEBUG", "INFO", "WARN", ResultCodeEnum.ERROR};
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final String LOG_FILE_PREFIX = "log";
    private static final Pattern FILE_NAME_PATTERN = Pattern.compile(String.format(Locale.US, "^%s_.*_.*_\\d+_.*\\.txt$", LOG_FILE_PREFIX));
    private static final Map<String, SimpleDateFormat> DATE_FORMAT_MAP = new HashMap(16);
    private static Config sConfig = Config.builder(null).build();

    static {
        HandlerThread handlerThread = new HandlerThread("ca_logger");
        handlerThread.start();
        HANDLER = new Handler(handlerThread.getLooper());
    }

    public static void initialize(final Config config) {
        HANDLER.post(new Runnable() { // from class: cerence.ark.assistant.common.Logger$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Logger.lambda$initialize$0(config);
            }
        });
    }

    static /* synthetic */ void lambda$initialize$0(Config config) {
        if (config != null) {
            sConfig = config;
        }
        LogFileLimiter logFileLimiter = new LogFileLimiter(sConfig.getDir(), FILE_NAME_PATTERN, sConfig.getMaxLogSize());
        sFileLimiter = logFileLimiter;
        logFileLimiter.scanLogFiles();
        info(TAG, sConfig.toString());
    }

    private Logger() {
    }

    public static boolean hasLogLevel(int i) {
        return sConfig.getLogLevels().contains(Integer.valueOf(i));
    }

    public static void verbose(String str, String str2) {
        log(2, str, str2, null);
    }

    public static void debug(String str, String str2) {
        log(3, str, str2, null);
    }

    public static void info(String str, String str2) {
        log(4, str, str2, null);
    }

    public static void warn(String str, String str2) {
        log(5, str, str2, null);
    }

    public static void error(String str, String str2) {
        log(6, str, str2, null);
    }

    public static void error(String str, String str2, Throwable th) {
        log(6, str, str2, th);
    }

    private static void log(final int i, final String str, final String str2, final Throwable th) {
        if (sConfig.isLogEnable() && hasLogLevel(i)) {
            final Date date = new Date();
            Thread threadCurrentThread = Thread.currentThread();
            final String strStringFormat = stringFormat(" [%s(%s)]: ", threadCurrentThread.getName(), Long.valueOf(threadCurrentThread.getId()));
            HANDLER.post(new Runnable() { // from class: cerence.ark.assistant.common.Logger$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Logger.lambda$log$1(str, str2, th, i, date, strStringFormat);
                }
            });
        }
    }

    static /* synthetic */ void lambda$log$1(String str, String str2, Throwable th, int i, Date date, String str3) {
        String string = (TextUtils.isEmpty(str) ? new StringBuilder(TAG_PREFIX).append(TAG) : new StringBuilder(TAG_PREFIX).append(str)).toString();
        if (str2 == null) {
            str2 = "";
        }
        if (th != null) {
            str2 = str2 + LINE_SEP + getFullStackTrace(th);
        }
        if (sConfig.isLogConsoleEnable()) {
            printConsole(i, string, date, str3, str2);
        }
        if (sConfig.isLogFileEnable()) {
            printFile(i, string, date, str3, str2);
        }
    }

    private static void printConsole(int i, String str, Date date, String str2, String str3) {
        String str4 = getSafeDateFormat("HH:mm:ss.SSS ").format(date) + str2 + str3;
        int length = str4.length();
        int i2 = length / 3500;
        if (i2 <= 0) {
            Log.println(i, str, str4);
            return;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i4 + 3500;
            Log.println(i, str, str4.substring(i4, i5));
            i3++;
            i4 = i5;
        }
        if (i4 != length) {
            Log.println(i, str, str4.substring(i4, length));
        }
    }

    private static SimpleDateFormat getSafeDateFormat(String str) {
        Map<String, SimpleDateFormat> map = DATE_FORMAT_MAP;
        SimpleDateFormat simpleDateFormat = map.get(str);
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str, Locale.US);
        map.put(str, simpleDateFormat2);
        return simpleDateFormat2;
    }

    private static void printFile(int i, String str, Date date, String str2, String str3) {
        File file = sCurrentFile;
        if (file == null) {
            sCurrentFile = new File(getFilePath(getSafeDateFormat(LOG_FILE_DATE_FORMAT).format(date)));
        } else if (file.exists() && sCurrentFile.length() >= sConfig.getSizePerLogFile()) {
            sFileLimiter.addNewFile(sCurrentFile);
            sCurrentFile = new File(getFilePath(getSafeDateFormat(LOG_FILE_DATE_FORMAT).format(date)));
        }
        StringBuilder sb = new StringBuilder(T[i - 2] + "/" + str);
        int length = 35 - sb.length();
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(StringUtils.SPACE);
        }
        String str4 = getSafeDateFormat("HH:mm:ss.SSS ").format(date) + ((Object) sb) + str2 + str3 + LINE_SEP;
        if (sCurrentFile.exists()) {
            writeFile(sCurrentFile, str4);
            return;
        }
        File parentFile = sCurrentFile.getParentFile();
        if (!(parentFile != null && (!parentFile.exists() ? !parentFile.mkdirs() : !parentFile.isDirectory()))) {
            Log.e(TAG, "create dir failed");
            return;
        }
        try {
            if (sCurrentFile.createNewFile()) {
                writeFile(sCurrentFile, "************* Log Head ****************\nLog date           : " + getSafeDateFormat("yyyy_MM_dd HH:mm:ss.SSS ").format(date) + "\npid                : " + Process.myPid() + "\nDevice Manufacturer: " + Build.MANUFACTURER + "\nDevice Model       : " + Build.MODEL + "\nAndroid Version    : " + Build.VERSION.RELEASE + "\nAndroid SDK        : " + Build.VERSION.SDK_INT + "\nApp VersionName    : " + getAppVersionName() + "\nApp VersionCode    : " + getAppVersionCode() + "\n************* Log Head ****************\n\n");
                writeFile(sCurrentFile, str4);
            } else {
                Log.e(TAG, "create log file failed");
            }
        } catch (IOException e) {
            Log.e(TAG, stringFormat("create file [%s] failed", sCurrentFile.getAbsolutePath()), e);
        }
    }

    private static String getFilePath(String str) {
        return sConfig.getDir() + stringFormat(FILE_NAME_FORMAT, LOG_FILE_PREFIX, str, Integer.valueOf(Process.myPid()), sConfig.getProcessName());
    }

    private static void writeFile(File file, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            try {
                bufferedWriter.write(str);
                bufferedWriter.close();
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, stringFormat("write to log file [%s] failed", file.getAbsolutePath()), e);
        }
    }

    private static String stringFormat(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    private static String getFullStackTrace(Throwable th) {
        List<String> stackFrameList;
        ArrayList arrayList = new ArrayList();
        while (th != null && !arrayList.contains(th)) {
            arrayList.add(th);
            th = th.getCause();
        }
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList();
        int i = size - 1;
        List<String> stackFrameList2 = getStackFrameList((Throwable) arrayList.get(i));
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (size != 0) {
                stackFrameList = getStackFrameList((Throwable) arrayList.get(size - 1));
                removeCommonFrames(stackFrameList2, stackFrameList);
            } else {
                stackFrameList = stackFrameList2;
            }
            if (size == i) {
                arrayList2.add(((Throwable) arrayList.get(size)).toString());
            } else {
                arrayList2.add(" Caused by: " + ((Throwable) arrayList.get(size)).toString());
            }
            arrayList2.addAll(stackFrameList2);
            stackFrameList2 = stackFrameList;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next()).append(LINE_SEP);
        }
        return sb.toString();
    }

    private static List<String> getStackFrameList(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter((Writer) stringWriter, true));
        StringTokenizer stringTokenizer = new StringTokenizer(stringWriter.toString(), LINE_SEP);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            int iIndexOf = strNextToken.indexOf("at");
            if (iIndexOf != -1 && strNextToken.substring(0, iIndexOf).trim().isEmpty()) {
                arrayList.add(strNextToken);
                z = true;
            } else if (z) {
                break;
            }
        }
        return arrayList;
    }

    private static void removeCommonFrames(List<String> list, List<String> list2) {
        int size = list.size() - 1;
        for (int size2 = list2.size() - 1; size >= 0 && size2 >= 0; size2--) {
            if (list.get(size).equals(list2.get(size2))) {
                list.remove(size);
            }
            size--;
        }
    }

    private static String getAppVersionName() {
        if (sConfig.getContext() == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = sConfig.getContext().getPackageManager().getPackageInfo(sConfig.getContext().getPackageName(), 0);
            return packageInfo == null ? "" : packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static long getAppVersionCode() {
        if (sConfig.getContext() == null) {
            return -1L;
        }
        try {
            PackageInfo packageInfo = sConfig.getContext().getPackageManager().getPackageInfo(sConfig.getContext().getPackageName(), 0);
            if (packageInfo == null) {
                return -1L;
            }
            return packageInfo.getLongVersionCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static final class Config {
        private final Context mContext;
        private String mDir;
        private final boolean mLogConsoleEnable;
        private final boolean mLogEnable;
        private final boolean mLogFileEnable;
        private final Set<Integer> mLogLevels;
        private final long mMaxLogSize;
        private final String mProcessName;
        private final long mSizePerLogFile;

        private Config(Builder builder) {
            Context context = builder.mContext;
            this.mContext = context;
            this.mLogEnable = builder.mLogConsoleEnable || builder.mLogFileEnable;
            this.mLogConsoleEnable = builder.mLogConsoleEnable;
            this.mMaxLogSize = builder.mMaxLogSize;
            this.mSizePerLogFile = builder.mSizePerLogFile;
            this.mProcessName = getProcessName(context);
            if (TextUtils.isEmpty(builder.mDir)) {
                if (builder.mLogFileEnable) {
                    Log.w(Logger.TAG, "The log dir is empty");
                }
                this.mLogFileEnable = false;
            } else {
                this.mLogFileEnable = builder.mLogFileEnable;
                this.mDir = builder.mDir.endsWith(File.separator) ? builder.mDir : builder.mDir + File.separator;
            }
            this.mLogLevels = builder.mLogLevels;
        }

        public static Builder builder(Context context) {
            return new Builder(context);
        }

        public String getDir() {
            return this.mDir;
        }

        public boolean isLogEnable() {
            return this.mLogEnable;
        }

        public boolean isLogConsoleEnable() {
            return this.mLogConsoleEnable;
        }

        public boolean isLogFileEnable() {
            return this.mLogFileEnable;
        }

        public String getProcessName() {
            return this.mProcessName;
        }

        public Context getContext() {
            return this.mContext;
        }

        public long getMaxLogSize() {
            return this.mMaxLogSize;
        }

        public long getSizePerLogFile() {
            return this.mSizePerLogFile;
        }

        public Set<Integer> getLogLevels() {
            return this.mLogLevels;
        }

        public String toString() {
            return "Config: " + Logger.LINE_SEP + "processName      = " + this.mProcessName + Logger.LINE_SEP + "dir              = " + this.mDir + Logger.LINE_SEP + "logEnable        = " + this.mLogEnable + Logger.LINE_SEP + "logConsoleEnable = " + this.mLogConsoleEnable + Logger.LINE_SEP + "logFileEnable    = " + this.mLogFileEnable + Logger.LINE_SEP + "maxLogSize       = " + this.mMaxLogSize + Logger.LINE_SEP + "sizePerLogFile   = " + this.mSizePerLogFile + Logger.LINE_SEP + "logLevels        = " + this.mLogLevels;
        }

        private static String getProcessName(Context context) {
            String currentProcessNameByFile = getCurrentProcessNameByFile();
            if (TextUtils.isEmpty(currentProcessNameByFile)) {
                currentProcessNameByFile = getCurrentProcessName(context);
            }
            return !TextUtils.isEmpty(currentProcessNameByFile) ? currentProcessNameByFile.replace(":", "_") : EnvironmentCompat.MEDIA_UNKNOWN;
        }

        private static String getCurrentProcessNameByFile() {
            String strTrim = "";
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
                try {
                    strTrim = bufferedReader.readLine().trim();
                    bufferedReader.close();
                } finally {
                }
            } catch (Exception e) {
                Log.e(Logger.TAG, "getCurrentProcessNameByFile: get current process name failed.", e);
            }
            return strTrim;
        }

        private static String getCurrentProcessName(Context context) {
            ActivityManager activityManager;
            if (context == null) {
                Log.e(Logger.TAG, "getCurrentProcessName: context is null");
                return "";
            }
            try {
                activityManager = (ActivityManager) context.getSystemService("activity");
            } catch (Exception e) {
                Log.e(Logger.TAG, "getCurrentProcessName: get current process name exception.", e);
            }
            if (activityManager == null) {
                Log.e(Logger.TAG, "getCurrentProcessName: am is null");
                return "";
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null && !runningAppProcesses.isEmpty()) {
                int iMyPid = Process.myPid();
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo != null && runningAppProcessInfo.pid == iMyPid && runningAppProcessInfo.processName != null) {
                        return runningAppProcessInfo.processName;
                    }
                }
                Log.e(Logger.TAG, "getCurrentProcessName: cannot get current process name");
                return "";
            }
            Log.e(Logger.TAG, "getCurrentProcessName: process info list is empty");
            return "";
        }

        public static class Builder {
            private static final long DEFAULT_MAX_LOG_SIZE = 52428800;
            private static final long DEFAULT_SIZE_PER_LOG_FILE = 10485760;
            private static final long MB = 1048576;
            private final Context mContext;
            private String mDir;
            private boolean mLogConsoleEnable;
            private boolean mLogFileEnable;
            private Set<Integer> mLogLevels;
            private long mMaxLogSize;
            private long mSizePerLogFile;

            private Builder(Context context) {
                this.mLogConsoleEnable = true;
                this.mLogFileEnable = false;
                this.mMaxLogSize = DEFAULT_MAX_LOG_SIZE;
                this.mSizePerLogFile = DEFAULT_SIZE_PER_LOG_FILE;
                this.mLogLevels = new HashSet();
                this.mContext = context != null ? context.getApplicationContext() : null;
                this.mLogLevels.add(2);
                this.mLogLevels.add(3);
                this.mLogLevels.add(4);
                this.mLogLevels.add(5);
                this.mLogLevels.add(6);
            }

            public Builder setDir(String str) {
                this.mDir = str;
                return this;
            }

            public Builder setLogConsoleEnable(boolean z) {
                this.mLogConsoleEnable = z;
                return this;
            }

            public Builder setLogFileEnable(boolean z) {
                this.mLogFileEnable = z;
                return this;
            }

            public Builder setMaxLogSize(long j) {
                this.mMaxLogSize = j;
                return this;
            }

            public Builder setSizePerLogFile(long j) {
                if (j <= 0) {
                    j = DEFAULT_SIZE_PER_LOG_FILE;
                }
                this.mSizePerLogFile = j;
                return this;
            }

            public Builder setLogLevels(int... iArr) {
                if (iArr != null && iArr.length > 0) {
                    this.mLogLevels = new HashSet();
                    for (int i : iArr) {
                        this.mLogLevels.add(Integer.valueOf(i));
                    }
                }
                return this;
            }

            public Config build() {
                return new Config(this);
            }
        }
    }
}
