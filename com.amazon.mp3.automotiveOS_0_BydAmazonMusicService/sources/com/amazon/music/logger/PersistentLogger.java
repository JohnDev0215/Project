package com.amazon.music.logger;

import android.os.Environment;
import android.os.Process;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes4.dex */
public class PersistentLogger {
    private static final String TAG = "PersistentLogger";
    private static final Thread sLoggingThread = new LoggingThread();
    private static final BlockingQueue<LogEntry> sLogEntries = new LinkedBlockingQueue();
    private static String sLogFilePath = "";
    private static String sSwapLogFilePath = "";
    static final CachingDateFormatter sCachingDateFormatter = new CachingDateFormatter("MM-dd HH:mm:ss.SSS", Locale.US);

    static class LogEntry {
        final char level;
        final String msg;
        final int processId;
        final String tag;
        final int threadId;
        final long timeInMillis;
        final Throwable tr;

        @VisibleForTesting
        LogEntry(char c, String str, String str2, Throwable th, long j, int i, int i2) {
            this.level = c;
            this.tag = str;
            this.msg = str2;
            this.tr = th;
            this.timeInMillis = j;
            this.processId = i;
            this.threadId = i2;
        }

        public LogEntry(char c, String str, String str2, Throwable th) {
            this(c, str, str2, th, System.currentTimeMillis(), Process.myPid(), Process.myTid());
        }

        public LogEntry(char c, String str, String str2) {
            this(c, str, str2, null);
        }

        String getFormattedMessage() {
            return String.format(Locale.US, "%s %5d %5d %s %s: %s%s", PersistentLogger.sCachingDateFormatter.format(this.timeInMillis), Integer.valueOf(this.processId), Integer.valueOf(this.threadId), Character.valueOf(this.level), this.tag, this.msg, "\n");
        }

        public List<String> toStringList() {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(getFormattedMessage());
            Throwable th = this.tr;
            if (th != null) {
                arrayList.add(Log.getStackTraceString(th));
                arrayList.add("\n");
            }
            return arrayList;
        }
    }

    static void initialize(AmazonMusicMarketLoggerConfig amazonMusicMarketLoggerConfig) {
        if (isExternalStorageAvailable()) {
            sLogFilePath = amazonMusicMarketLoggerConfig.getPersistentFilePath();
            sSwapLogFilePath = amazonMusicMarketLoggerConfig.getSwapFilePath();
            startLoggingThread();
        }
    }

    private static void startLoggingThread() {
        Thread.State state = Thread.State.NEW;
        Thread thread = sLoggingThread;
        if (state.equals(thread.getState())) {
            thread.start();
        }
    }

    private static boolean isExternalStorageAvailable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private static void addToLogEntries(LogEntry logEntry) {
        try {
            sLogEntries.add(logEntry);
        } catch (IllegalStateException e) {
            BlockingQueue<LogEntry> blockingQueue = sLogEntries;
            int size = blockingQueue.size();
            blockingQueue.clear();
            Log.e(TAG, "Add to persistent log queue failed. Dropped " + size + " unwritten logs.", e);
        }
    }

    private static final class LoggingThread extends Thread {
        private LoggingThread() {
            super(PersistentLogger.TAG);
            setDaemon(true);
            setPriority(3);
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            super.start();
            Log.d(PersistentLogger.TAG, "Logging thread started");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    appendLog((LogEntry) PersistentLogger.sLogEntries.take());
                } catch (Exception e) {
                    Log.e(PersistentLogger.TAG, "Unexpected exception when writing to persistent log: " + e.getMessage(), e);
                }
            }
        }

        private void appendLog(LogEntry logEntry) throws IOException {
            File file = new File(PersistentLogger.sLogFilePath);
            if (file.length() > 1048576) {
                PersistentLogger.swapFiles();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, ParserUtil.UTF_8));
                try {
                    List<String> stringList = logEntry.toStringList();
                    int size = stringList.size();
                    for (int i = 0; i < size; i++) {
                        bufferedWriter.append((CharSequence) stringList.get(i));
                    }
                    bufferedWriter.close();
                    fileOutputStream.close();
                } finally {
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static void log(char c, String str, String str2) {
        if (sLogFilePath.length() > 0) {
            addToLogEntries(new LogEntry(c, str, str2));
        }
    }

    public static void log(char c, String str, String str2, Throwable th) {
        if (sLogFilePath.length() > 0) {
            addToLogEntries(new LogEntry(c, str, str2, th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean swapFiles() {
        Log.i(TAG, "Rotating log files");
        return new File(sLogFilePath).renameTo(new File(sSwapLogFilePath));
    }
}
