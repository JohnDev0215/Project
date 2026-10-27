package com.google.android.projection.logging;

import android.os.Process;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class Log {
    private static final Log INSTANCE = new Log();
    private static final int MAX_ERROR_LOGS_ENTRIES = 10;
    static final int MAX_LOGS_ENTRIES = 100;
    private final Object lock = new Object();
    private final ArrayDeque<LogEntry> stdLogBuffer = new ArrayDeque<>();
    private final ArrayDeque<LogEntry> errLogBuffer = new ArrayDeque<>();

    private Log() {
    }

    public static void v(String tag, String msg) {
        addLog(2, tag, msg);
    }

    public static void d(String tag, String msg) {
        addLog(3, tag, msg);
    }

    public static void i(String tag, String msg) {
        addLog(4, tag, msg);
    }

    public static void w(String tag, String msg) {
        addLog(5, tag, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        addLog(5, tag, msg, tr);
    }

    public static void e(String tag, String msg) {
        addLog(6, tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        addLog(6, tag, msg, tr);
    }

    public static void dumpRecentLogs(PrintWriter writer) {
        getInstance().internalDumpRecentLogs(writer);
    }

    private static void addLog(int severity, String tag, String message) {
        addLog(severity, tag, message, null);
    }

    private static void addLog(int severity, String tag, String message, Throwable exception) {
        getInstance().addLogEntry(LogEntry.create(severity, tag, message, exception));
    }

    private static Log getInstance() {
        return INSTANCE;
    }

    private void addLogEntry(LogEntry entry) {
        toLogcat(entry);
        synchronized (this.lock) {
            addLogEntryToRingBuffer(entry, this.stdLogBuffer, 100);
            if (entry.severity >= 5) {
                addLogEntryToRingBuffer(entry, this.errLogBuffer, 10);
            }
        }
    }

    private static void addLogEntryToRingBuffer(LogEntry entry, ArrayDeque<LogEntry> buffer, int maxSize) {
        while (buffer.size() >= maxSize) {
            buffer.removeFirst();
        }
        buffer.addLast(entry);
    }

    private void internalDumpRecentLogs(PrintWriter writer) {
        List<LogEntry> stdBuff;
        List<LogEntry> errBuff;
        synchronized (this.lock) {
            stdBuff = new ArrayList<>(this.stdLogBuffer);
            errBuff = new ArrayList<>(this.errLogBuffer);
        }
        writer.println();
        writer.println("*** Recent Logs: warnings and errors ***");
        internalDumpRecentLogs(writer, errBuff);
        writer.println();
        writer.println("*** Recent Logs: all ***");
        internalDumpRecentLogs(writer, stdBuff);
    }

    private static void internalDumpRecentLogs(PrintWriter writer, List<LogEntry> buffer) {
        for (LogEntry entry : buffer) {
            String time = new SimpleDateFormat("MM:dd HH:mm:ss.SSS", Locale.US).format(new Date(entry.timestamp));
            String thread = entry.mainThread ? " main" : " " + entry.threadId;
            String severity = severityToString(entry.severity);
            String msg = entry.exception == null ? entry.message : entry.message + '\n' + android.util.Log.getStackTraceString(entry.exception);
            writer.println(time + thread + " " + severity + " " + entry.tag + ": " + msg);
        }
    }

    private static String severityToString(int severity) {
        if (severity == 2) {
            return "V";
        }
        if (severity == 3) {
            return "D";
        }
        if (severity == 4) {
            return "I";
        }
        if (severity == 5) {
            return "W";
        }
        if (severity == 6) {
            return "E";
        }
        throw new IllegalArgumentException("Unexpected severity: " + severity);
    }

    private static void toLogcat(LogEntry entry) {
        int i = entry.severity;
        if (i == 2) {
            android.util.Log.v(entry.tag, entry.message);
            return;
        }
        if (i == 3) {
            android.util.Log.d(entry.tag, entry.message);
            return;
        }
        if (i == 4) {
            android.util.Log.i(entry.tag, entry.message);
            return;
        }
        if (i == 5) {
            if (entry.exception == null) {
                android.util.Log.w(entry.tag, entry.message);
                return;
            } else {
                android.util.Log.w(entry.tag, entry.message, entry.exception);
                return;
            }
        }
        if (i == 6) {
            if (entry.exception == null) {
                android.util.Log.e(entry.tag, entry.message);
                return;
            } else {
                android.util.Log.e(entry.tag, entry.message, entry.exception);
                return;
            }
        }
        throw new IllegalArgumentException("Unexpected severity: " + entry.severity);
    }

    private static class LogEntry {
        final Throwable exception;
        final boolean mainThread;
        final String message;
        final int severity;
        final String tag;
        final long threadId;
        final long timestamp;

        LogEntry(long timestamp, int severity, String tag, String message, long threadId, boolean isMainThread, Throwable ex) {
            this.timestamp = timestamp;
            this.severity = severity;
            this.tag = tag;
            this.message = message;
            this.threadId = threadId;
            this.mainThread = isMainThread;
            this.exception = ex;
        }

        static LogEntry create(int severity, String tag, String message, Throwable exception) {
            int tid = Process.myTid();
            int pid = Process.myPid();
            return new LogEntry(new Date().getTime(), severity, tag, message, tid, tid == pid, exception);
        }
    }
}
