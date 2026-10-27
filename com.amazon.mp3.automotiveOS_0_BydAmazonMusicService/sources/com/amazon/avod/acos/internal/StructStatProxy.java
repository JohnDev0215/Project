package com.amazon.avod.acos.internal;

import android.annotation.SuppressLint;
import android.os.Build;
import android.system.Os;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class StructStatProxy {
    private static final AtomicBoolean sMissingOrBadImplementationReported = new AtomicBoolean(false);

    private StructStatProxy() {
    }

    @Nullable
    @SuppressLint({"NewApi"})
    public static StructStat getStructStat(@Nonnull String str) {
        Preconditions.checkNotNull(str, "path");
        StructStat structStat = null;
        try {
            android.system.StructStat structStatStat = Os.stat(str);
            if (structStatStat != null) {
                structStat = new StructStat(structStatStat.st_mode, structStatStat.st_uid, structStatStat.st_gid, structStatStat.st_dev);
            } else {
                reportMissingOrBadImplementation();
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            DLog.exceptionf(e, "Stat check failed for file at path: %s", DLog.maskString(str));
        }
        return structStat;
    }

    private static void reportMissingOrBadImplementation() {
        if (sMissingOrBadImplementationReported.getAndSet(true)) {
            return;
        }
        Profiler.reportCounterMetric(new SimpleCounterMetric("StructStat-MissingOrBadImplementation", (ImmutableList<String>) ImmutableList.of("Counter", "Api" + Build.VERSION.SDK_INT)));
        Preconditions2.failWeakly("Missing or bad implementation of StructStat - downloads may not function correctly", new Object[0]);
    }

    public static class StructStat {
        public final long st_dev;
        public final int st_gid;
        public final int st_mode;
        public final int st_uid;

        public StructStat(int i, int i2, int i3, long j) {
            this.st_mode = i;
            this.st_uid = i2;
            this.st_gid = i3;
            this.st_dev = j;
        }

        public String toString() {
            return MoreObjects.toStringHelper("stat").add("st_mode", Strings.padStart(Integer.toOctalString(this.st_mode), 7, '0')).add("st_uid", this.st_uid).add("st_gid", this.st_gid).add("st_dev", this.st_dev).toString();
        }
    }
}
