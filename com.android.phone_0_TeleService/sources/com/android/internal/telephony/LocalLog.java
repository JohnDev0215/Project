package com.android.internal.telephony;

import android.os.SystemClock;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class LocalLog {
    private final Deque<String> mLog;
    private final int mMaxLines;
    private final boolean mUseLocalTimestamps;

    public LocalLog(int i) {
        this(i, true);
    }

    public LocalLog(int i, boolean z) {
        this.mMaxLines = Math.max(0, i);
        this.mLog = new ArrayDeque(this.mMaxLines);
        this.mUseLocalTimestamps = z;
    }

    public void log(String str) {
        if (this.mMaxLines <= 0) {
            return;
        }
        append(this.mUseLocalTimestamps ? String.format("%s - %s", LocalDateTime.now(), str) : String.format("%s / %s - %s", Long.valueOf(SystemClock.elapsedRealtime()), Instant.now(), str));
    }

    private synchronized void append(String str) {
        while (this.mLog.size() >= this.mMaxLines) {
            this.mLog.remove();
        }
        this.mLog.add(str);
    }

    public synchronized void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        dump(printWriter);
    }

    public synchronized void dump(PrintWriter printWriter) {
        dump("", printWriter);
    }

    public synchronized void dump(String str, PrintWriter printWriter) {
        Iterator<String> it = this.mLog.iterator();
        while (it.hasNext()) {
            printWriter.printf("%s%s\n", str, it.next());
        }
    }
}
