package io.sentry.instrumentation.file;

import io.sentry.C1489n1;
import io.sentry.F1;
import io.sentry.L1;
import io.sentry.U;
import io.sentry.W1;
import io.sentry.protocol.x;
import io.sentry.util.i;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final U f16557a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final File f16558b;
    public final F1 c;
    public W1 d = W1.OK;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f16559e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final L1 f16560f;

    public b(U u4, File file, F1 f12) {
        this.f16557a = u4;
        this.f16558b = file;
        this.c = f12;
        this.f16560f = new L1(f12, 0);
        C1489n1.s().f("FileIO");
    }

    public final void a(Closeable closeable) {
        try {
            try {
                closeable.close();
            } catch (IOException e10) {
                this.d = W1.INTERNAL_ERROR;
                U u4 = this.f16557a;
                if (u4 != null) {
                    u4.o(e10);
                }
                throw e10;
            }
        } finally {
            b();
        }
    }

    public final void b() {
        String str;
        List listEmptyList;
        U u4 = this.f16557a;
        if (u4 != null) {
            long j3 = this.f16559e;
            Charset charset = i.f16925a;
            if (-1000 >= j3 || j3 >= 1000) {
                StringCharacterIterator stringCharacterIterator = new StringCharacterIterator("kMGTPE");
                while (true) {
                    if (j3 > -999950 && j3 < 999950) {
                        break;
                    }
                    j3 /= 1000;
                    stringCharacterIterator.next();
                }
                str = String.format(Locale.ROOT, "%.1f %cB", Double.valueOf(j3 / 1000.0d), Character.valueOf(stringCharacterIterator.current()));
            } else {
                str = j3 + " B";
            }
            F1 f12 = this.c;
            File file = this.f16558b;
            if (file != null) {
                u4.e(file.getName() + " (" + str + ")");
                if (io.sentry.util.f.f16921a || f12.isSendDefaultPii()) {
                    u4.A(file.getAbsolutePath(), "file.path");
                }
            } else {
                u4.e(str);
            }
            u4.A(Long.valueOf(this.f16559e), "file.size");
            boolean zA = f12.getMainThreadChecker().a();
            u4.A(Boolean.valueOf(zA), "blocked_main_thread");
            if (zA) {
                L1 l12 = this.f16560f;
                l12.getClass();
                ArrayList arrayListA = l12.a(new Exception().getStackTrace(), false);
                if (arrayListA == null) {
                    listEmptyList = Collections.emptyList();
                } else {
                    ArrayList arrayList = new ArrayList(arrayListA.size());
                    for (Object obj : arrayListA) {
                        if (Boolean.TRUE.equals(((x) obj).f16798s)) {
                            arrayList.add(obj);
                        }
                    }
                    if (arrayList.isEmpty()) {
                        arrayList = new ArrayList(arrayListA.size());
                        for (Object obj2 : arrayListA) {
                            String str2 = ((x) obj2).f16794n;
                            if (str2 == null || (!str2.startsWith("sun.") && !str2.startsWith("java.") && !str2.startsWith("android.") && !str2.startsWith("com.android."))) {
                                arrayList.add(obj2);
                            }
                        }
                    }
                    listEmptyList = arrayList;
                }
                u4.A(listEmptyList, "call_stack");
            }
            u4.q(this.d);
        }
    }

    public final Object c(a aVar) {
        try {
            Object objCall = aVar.call();
            if (objCall instanceof Integer) {
                int iIntValue = ((Integer) objCall).intValue();
                if (iIntValue != -1) {
                    this.f16559e += (long) iIntValue;
                }
            } else if (objCall instanceof Long) {
                long jLongValue = ((Long) objCall).longValue();
                if (jLongValue != -1) {
                    this.f16559e += jLongValue;
                }
            }
            return objCall;
        } catch (IOException e10) {
            this.d = W1.INTERNAL_ERROR;
            U u4 = this.f16557a;
            if (u4 != null) {
                u4.o(e10);
            }
            throw e10;
        }
    }
}
