package io.sentry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class F0 implements Runnable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Charset f15837m = Charset.forName("UTF-8");
    public final F1 l;

    public F0(F1 f12) {
        this.l = f12;
    }

    public final Date a(File file) {
        F1 f12 = this.l;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), f15837m));
            try {
                String line = bufferedReader.readLine();
                f12.getLogger().k(EnumC1495p1.DEBUG, "Crash marker file has %s timestamp.", line);
                Date dateH = AbstractC1985B.h(line);
                bufferedReader.close();
                return dateH;
            } finally {
            }
        } catch (IOException e10) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Error reading the crash marker file.", e10);
            return null;
        } catch (IllegalArgumentException e11) {
            f12.getLogger().p(EnumC1495p1.ERROR, e11, "Error converting the crash timestamp.", new Object[0]);
            return null;
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Date dateA;
        F1 f12 = this.l;
        String cacheDirPath = f12.getCacheDirPath();
        if (cacheDirPath == null) {
            f12.getLogger().k(EnumC1495p1.INFO, "Cache dir is not set, not finalizing the previous session.", new Object[0]);
            return;
        }
        if (!f12.isEnableAutoSessionTracking()) {
            f12.getLogger().k(EnumC1495p1.DEBUG, "Session tracking is disabled, bailing from previous session finalizer.", new Object[0]);
            return;
        }
        io.sentry.cache.d envelopeDiskCache = f12.getEnvelopeDiskCache();
        if ((envelopeDiskCache instanceof io.sentry.cache.c) && !((io.sentry.cache.c) envelopeDiskCache).q()) {
            f12.getLogger().k(EnumC1495p1.WARNING, "Timed out waiting to flush previous session to its own file in session finalizer.", new Object[0]);
            return;
        }
        Charset charset = io.sentry.cache.c.f16489r;
        File file = new File(cacheDirPath, "previous_session.json");
        T serializer = f12.getSerializer();
        if (file.exists()) {
            f12.getLogger().k(EnumC1495p1.WARNING, "Current session is not ended, we'd need to end it.", new Object[0]);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), f15837m));
                try {
                    R1 r12 = (R1) serializer.a(bufferedReader, R1.class);
                    if (r12 == null) {
                        f12.getLogger().k(EnumC1495p1.ERROR, "Stream from path %s resulted in a null envelope.", file.getAbsolutePath());
                    } else {
                        File file2 = new File(f12.getCacheDirPath(), ".sentry-native/last_crash");
                        if (file2.exists()) {
                            f12.getLogger().k(EnumC1495p1.INFO, "Crash marker file exists, last Session is gonna be Crashed.", new Object[0]);
                            dateA = a(file2);
                            if (!file2.delete()) {
                                f12.getLogger().k(EnumC1495p1.ERROR, "Failed to delete the crash marker file. %s.", file2.getAbsolutePath());
                            }
                            r12.c(Q1.Crashed, null, true, null);
                        } else {
                            dateA = null;
                        }
                        if (r12.f15940y == null) {
                            r12.b(dateA);
                        }
                        E.f15834a.r(new C1489n1((io.sentry.protocol.t) null, f12.getSdkVersion(), C1474i1.b(serializer, r12)), new C1523x());
                    }
                    bufferedReader.close();
                } finally {
                }
            } catch (Throwable th) {
                f12.getLogger().s(EnumC1495p1.ERROR, "Error processing previous session.", th);
            }
            if (file.delete()) {
                return;
            }
            f12.getLogger().k(EnumC1495p1.WARNING, "Failed to delete the previous session file.", new Object[0]);
        }
    }
}
