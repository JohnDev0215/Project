package com.byd.music.utils.log;

import android.content.Context;
import android.media.MediaMetrics;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes5.dex */
public class Logger {
    private static final String CHARSETNAME_UTF8 = "UTF-8";
    private static final String DEFAULT_TAG = "tag_auto";
    public static final boolean IS_WRITE_LOG = true;
    private static Context applicationContext = null;
    private static String mEngineVersion = "";
    private static String mPath;
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static boolean isLog = false;
    private static FileLogger fileLogger = new FileLogger();

    public static boolean isLog() {
        return isLog;
    }

    public static void setLog(boolean z) {
        FileLogger fileLogger2;
        isLog = z;
        if (!z || (fileLogger2 = fileLogger) == null) {
            return;
        }
        fileLogger2.isCheckedLogDir = false;
    }

    public static void setMaxNum(int i) {
        FileLogger fileLogger2 = fileLogger;
        if (fileLogger2 != null) {
            fileLogger2.MAX_LOG_NUMBER = i;
        }
    }

    public static void init(Context context, String str) {
        applicationContext = context.getApplicationContext();
        isLog = LogControlerUtil.isApkInDebug(context);
        mPath = str;
    }

    private static Object[] toParamsString(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        int length = objArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            objArr2[i2] = String.valueOf(objArr[i]);
            i++;
            i2++;
        }
        return objArr2;
    }

    public static void setEngineVersion(String str) {
        mEngineVersion = str;
    }

    public static void d(String str, String str2, Object... objArr) {
        Object[] paramsString = (isLog || fileLogger.isLog()) ? toParamsString(objArr) : null;
        if (isLog) {
            LoggerThread.getInstance().d(str, str2, paramsString);
        }
        if (fileLogger.isLog()) {
            LoggerThread.getInstance().write(str, str2, paramsString);
        }
    }

    public static void e(String str, String str2) {
        if (isLog) {
            LoggerThread.getInstance().e(str, str2, null, new Object[0]);
        }
        if (fileLogger.isLog() && LogControlerUtil.getInstance().isLogLow()) {
            LoggerThread.getInstance().write(str, str2, new Object[0]);
        }
    }

    public static void e(String str, String str2, Throwable th, Object... objArr) {
        Object[] paramsString = (isLog || fileLogger.isLog()) ? toParamsString(objArr) : null;
        if (isLog) {
            LoggerThread.getInstance().e(str, str2, th, paramsString);
        }
        if (fileLogger.isLog() && LogControlerUtil.getInstance().isLogLow()) {
            LoggerThread.getInstance().write(str, str2, paramsString);
        }
    }

    @Deprecated
    public static void D(String str, Object... objArr) {
        d(DEFAULT_TAG, str, objArr);
    }

    @Deprecated
    public static void E(String str, Throwable th, Object... objArr) {
        e(DEFAULT_TAG, str, th, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void msgFromParams(StringBuffer stringBuffer, String str, Object... objArr) {
        if (str == null) {
            return;
        }
        if (objArr == null) {
            stringBuffer.append(str);
            return;
        }
        String[] strArrSplit = str.split("\\{\\?\\}");
        int iMin = Math.min(strArrSplit.length, objArr.length);
        for (int i = 0; i < iMin; i++) {
            Object obj = objArr[i];
            stringBuffer.append(strArrSplit[i]);
            stringBuffer.append(obj);
        }
        while (iMin < strArrSplit.length) {
            stringBuffer.append(strArrSplit[iMin]);
            iMin++;
        }
    }

    public static void d(Class cls, String str, Object... objArr) {
        d(cls.getSimpleName(), str, objArr);
    }

    public static void pd(String str, Object... objArr) {
        d("pino", str, objArr);
    }

    public static void destory() {
        if (isLog || fileLogger.isLog()) {
            LoggerThread.getInstance().destory();
        }
    }

    public static void checkFile() {
        FileLogger fileLogger2 = fileLogger;
        if (fileLogger2 != null) {
            fileLogger2.checkLogFile();
        }
    }

    public static void setForceCheckFile() {
        FileLogger fileLogger2 = fileLogger;
        if (fileLogger2 != null) {
            fileLogger2.isCheckedLogDir = false;
        }
    }

    public static String getLogFileDir(Context context) {
        String str = mPath;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/BydAutoMap/bllog/demolog";
    }

    private static class FileLogger {
        private static final String FILE_NAME = "sdklog.log";
        private static final long MAX_FILE_SIZE = 20971520;
        private final int MAX_CACHE_SIZE;
        private int MAX_LOG_NUMBER;
        private StringBuffer bf;
        private String filePath;
        private boolean isCheckedLogDir;
        private boolean isLogFileExist;
        private long lastSaveLogTime;
        private File mCurrentLogFile;

        private FileLogger() {
            this.MAX_LOG_NUMBER = 10;
            this.filePath = null;
            this.isCheckedLogDir = false;
            this.isLogFileExist = false;
            this.bf = new StringBuffer();
            this.lastSaveLogTime = 0L;
            this.MAX_CACHE_SIZE = 204800;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void checkLogFile() {
            if (Logger.applicationContext == null) {
                return;
            }
            this.filePath = Logger.getLogFileDir(Logger.applicationContext);
            File file = new File(this.filePath);
            if (file.exists() && !file.isDirectory()) {
                file.delete();
            } else {
                file.mkdirs();
            }
            File file2 = new File(this.filePath);
            if (file2.exists() && file2.isDirectory()) {
                this.isLogFileExist = true;
                this.isCheckedLogDir = true;
            }
        }

        private boolean writeFile(byte[] bArr, boolean z) throws Throwable {
            FileOutputStream fileOutputStream;
            boolean z2 = false;
            if (Logger.applicationContext == null) {
                return false;
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    fileOutputStream = new FileOutputStream(this.mCurrentLogFile, z);
                } catch (Exception unused) {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    fileOutputStream.write(bArr);
                    z2 = true;
                    fileOutputStream.close();
                } catch (Exception unused2) {
                    fileOutputStream2 = fileOutputStream;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    return z2;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream2 = fileOutputStream;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            } catch (IOException unused4) {
            }
            return z2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void creteFile() throws Throwable {
            if (this.mCurrentLogFile == null) {
                this.mCurrentLogFile = new File(this.filePath, FILE_NAME);
            }
            boolean z = false;
            try {
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (this.mCurrentLogFile.exists()) {
                if (this.mCurrentLogFile.length() > MAX_FILE_SIZE) {
                    resetLogFiles();
                }
                if (z || TextUtils.isEmpty(Logger.mEngineVersion)) {
                }
                writeFile(Logger.mEngineVersion.getBytes(), true);
                return;
            }
            if (!this.mCurrentLogFile.getParentFile().exists()) {
                return;
            } else {
                this.mCurrentLogFile.createNewFile();
            }
            z = true;
            if (z) {
            }
        }

        private void resetLogFiles() throws IOException {
            String parent = this.mCurrentLogFile.getParent();
            String name = this.mCurrentLogFile.getName();
            File file = new File(parent, this.mCurrentLogFile.getName() + MediaMetrics.SEPARATOR + (this.MAX_LOG_NUMBER + 1));
            if (file.exists()) {
                file.delete();
            }
            for (int i = this.MAX_LOG_NUMBER; i > 0; i--) {
                File file2 = new File(parent, this.mCurrentLogFile.getName() + MediaMetrics.SEPARATOR + i);
                if (file2.exists()) {
                    file2.renameTo(new File(parent, this.mCurrentLogFile.getName() + MediaMetrics.SEPARATOR + (i + 1)));
                }
            }
            this.mCurrentLogFile.renameTo(new File(parent, this.mCurrentLogFile.getName() + MediaMetrics.SEPARATOR + 1));
            File file3 = new File(parent, name);
            this.mCurrentLogFile = file3;
            file3.createNewFile();
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void write(java.lang.String r4, long r5, java.lang.String r7, java.lang.String r8, java.lang.Object... r9) throws java.lang.Throwable {
            /*
                r3 = this;
                java.lang.StringBuffer r0 = r3.bf
                java.text.SimpleDateFormat r1 = com.byd.music.utils.log.Logger.access$700()
                java.util.Date r2 = new java.util.Date
                r2.<init>(r5)
                java.lang.String r5 = r1.format(r2)
                r0.append(r5)
                java.lang.String r5 = "##"
                r0.append(r5)
                java.lang.StringBuffer r5 = r3.bf
                java.lang.String r6 = "/"
                r5.append(r6)
                r5.append(r4)
                java.lang.String r4 = ":"
                r5.append(r4)
                java.lang.StringBuffer r4 = r3.bf
                java.lang.String r5 = "["
                r4.append(r5)
                r4.append(r7)
                java.lang.String r5 = "]"
                r4.append(r5)
                java.lang.StringBuffer r4 = r3.bf
                com.byd.music.utils.log.Logger.access$800(r4, r8, r9)
                java.lang.StringBuffer r4 = r3.bf
                java.lang.String r5 = "\r\n"
                r4.append(r5)
                java.lang.StringBuffer r4 = r3.bf
                int r4 = r4.length()
                r5 = 0
                r6 = 1
                r7 = 204800(0x32000, float:2.86986E-40)
                if (r4 < r7) goto L57
                java.lang.StringBuffer r4 = r3.bf
                java.lang.String r7 = "-------------！！！！警告！！！！警告 【log输出太频繁，请检查2秒内的log输出情况】-------------\r\n"
                r4.append(r7)
            L55:
                r4 = r6
                goto L66
            L57:
                long r7 = android.os.SystemClock.elapsedRealtime()
                long r0 = r3.lastSaveLogTime
                long r7 = r7 - r0
                r0 = 2000(0x7d0, double:9.88E-321)
                int r4 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r4 <= 0) goto L65
                goto L55
            L65:
                r4 = r5
            L66:
                if (r4 == 0) goto L87
                java.lang.StringBuffer r4 = r3.bf     // Catch: java.io.UnsupportedEncodingException -> L78
                java.lang.String r4 = r4.toString()     // Catch: java.io.UnsupportedEncodingException -> L78
                java.lang.String r7 = "UTF-8"
                byte[] r4 = r4.getBytes(r7)     // Catch: java.io.UnsupportedEncodingException -> L78
                r3.writeFile(r4, r6)     // Catch: java.io.UnsupportedEncodingException -> L78
                goto L7c
            L78:
                r4 = move-exception
                r4.printStackTrace()
            L7c:
                long r6 = android.os.SystemClock.elapsedRealtime()
                r3.lastSaveLogTime = r6
                java.lang.StringBuffer r4 = r3.bf
                r4.setLength(r5)
            L87:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.music.utils.log.Logger.FileLogger.write(java.lang.String, long, java.lang.String, java.lang.String, java.lang.Object[]):void");
        }

        public boolean isLog() {
            if (!this.isCheckedLogDir) {
                checkLogFile();
            }
            return this.isCheckedLogDir && this.isLogFileExist;
        }
    }

    private static class LoggerThread {
        private static LoggerThread instance = new LoggerThread();
        private volatile boolean isRunning;
        private final ConcurrentLinkedQueue<Runnable> mLogQueue = new ConcurrentLinkedQueue<>();
        private final Object locker = new Object();
        private volatile boolean isWait = false;

        public void destory() {
        }

        private LoggerThread() {
            initThread();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LoggerThread getInstance() {
            return instance;
        }

        public void d(final String str, final String str2, final Object... objArr) {
            this.mLogQueue.add(new Runnable() { // from class: com.byd.music.utils.log.Logger.LoggerThread.1
                @Override // java.lang.Runnable
                public void run() {
                    StringBuffer stringBuffer = new StringBuffer();
                    Logger.msgFromParams(stringBuffer, str2, objArr);
                    Log.d(str, stringBuffer.toString());
                }
            });
            notifyLock();
        }

        public void e(final String str, final String str2, final Throwable th, final Object... objArr) {
            this.mLogQueue.add(new Runnable() { // from class: com.byd.music.utils.log.Logger.LoggerThread.2
                @Override // java.lang.Runnable
                public void run() {
                    StringBuffer stringBuffer = new StringBuffer();
                    Logger.msgFromParams(stringBuffer, str2, objArr);
                    Log.e(str, stringBuffer.toString(), th);
                }
            });
            notifyLock();
        }

        public void write(final String str, final String str2, final Object... objArr) {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            final String name = Thread.currentThread().getName();
            this.mLogQueue.add(new Runnable() { // from class: com.byd.music.utils.log.Logger.LoggerThread.3
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    LoggerThread.this.setLowThreadPriority();
                    Logger.fileLogger.creteFile();
                    Logger.fileLogger.write(str, jCurrentTimeMillis, name, str2, objArr);
                }
            });
            notifyLock();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLowThreadPriority() {
            try {
                Process.setThreadPriority(19);
                Thread.currentThread().setPriority(1);
            } catch (Throwable unused) {
            }
        }

        private void initThread() {
            this.isRunning = true;
            new Thread(new Runnable() { // from class: com.byd.music.utils.log.Logger.LoggerThread.4
                @Override // java.lang.Runnable
                public void run() {
                    LoggerThread.this.setLowThreadPriority();
                    LoggerThread.this.loopWriteLog();
                }
            }, "LoggerThread").start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void loopWriteLog() {
            while (this.isRunning) {
                try {
                    executeTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void executeTask() throws InterruptedException {
            Runnable runnablePoll = this.mLogQueue.poll();
            if (runnablePoll != null) {
                try {
                    runnablePoll.run();
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            synchronized (this.locker) {
                this.isWait = true;
                this.locker.wait();
                this.isWait = false;
            }
        }

        private void notifyLock() {
            if (this.isWait) {
                try {
                    synchronized (this.locker) {
                        this.locker.notify();
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    public static void main(String[] strArr) throws Throwable {
        transtorLogTimeStamp(new File("C:\\Users\\xiangdong.wu\\Desktop\\autolog", "autolog.log"));
        transtorLogTimeStamp(new File("C:\\Users\\xiangdong.wu\\Desktop\\autolog", "autolog.log.1"));
        transtorLogTimeStamp(new File("C:\\Users\\xiangdong.wu\\Desktop\\autolog", "autolog.log.2"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.lang.StringBuffer] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00cf -> B:64:0x00d2). Please report as a decompilation issue!!! */
    private static void transtorLogTimeStamp(File file) throws Throwable {
        FileOutputStream fileOutputStream;
        ?? stringBuffer = 0;
        stringBuffer = 0;
        stringBuffer = 0;
        stringBuffer = 0;
        stringBuffer = 0;
        try {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                fileOutputStream = new FileOutputStream(new File(file.getParent(), file.getName() + ".new"));
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        try {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
                            stringBuffer = new StringBuffer();
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                int iIndexOf = line.indexOf("##/");
                                if (iIndexOf > 0) {
                                    try {
                                        long j = Long.parseLong(line.substring(0, iIndexOf));
                                        stringBuffer.append("[");
                                        stringBuffer.append(df.format(new Date(j)));
                                        stringBuffer.append("]");
                                        stringBuffer.append(line);
                                    } catch (Exception e2) {
                                        stringBuffer.append(line);
                                        e2.printStackTrace();
                                    }
                                } else {
                                    stringBuffer.append(line);
                                }
                                stringBuffer.append("\r\n");
                                if (stringBuffer.length() > 10000) {
                                    fileOutputStream.write(stringBuffer.toString().getBytes("UTF-8"));
                                    stringBuffer.setLength(0);
                                }
                            }
                            fileOutputStream.write(stringBuffer.toString().getBytes("UTF-8"));
                            fileOutputStream.flush();
                            try {
                                fileInputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            fileOutputStream.close();
                        } catch (Exception e4) {
                            e = e4;
                            stringBuffer = fileInputStream;
                            e.printStackTrace();
                            if (stringBuffer != 0) {
                                try {
                                    stringBuffer.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        stringBuffer = fileInputStream;
                        if (stringBuffer != 0) {
                            try {
                                stringBuffer.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                throw th;
                            } catch (IOException e7) {
                                e7.printStackTrace();
                                throw th;
                            }
                        }
                        throw th;
                    }
                } catch (Exception e8) {
                    e = e8;
                }
            } catch (Exception e9) {
                e = e9;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
