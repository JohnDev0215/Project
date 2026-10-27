package defpackage;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.chromium.base.BuildInfo;
import org.chromium.base.PathUtils;

/* JADX INFO: renamed from: cr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0626cr {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                Ts.a("VariationsUtils", "Failed to close " + closeable, new Object[0]);
            }
        }
    }

    public static void b(String str) {
        if (BuildInfo.b()) {
            Ts.d("VariationsUtils", str, new Object[0]);
        }
    }

    public static long c(String str, long j) {
        Es esE = Es.e();
        if (!esE.h(str)) {
            return j;
        }
        try {
            return TimeUnit.SECONDS.toMillis(Long.parseLong(esE.f(str)));
        } catch (NumberFormatException e) {
            Ts.a("VariationsUtils", Z7.c("Invalid value for flag ", str), e);
            return j;
        }
    }

    public static File d() {
        return new File(PathUtils.getDataDirectory(), "variations_seed_new");
    }

    public static File e() {
        return new File(PathUtils.getDataDirectory(), "variations_seed");
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x009b: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:55:0x009b */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0040 A[Catch: all -> 0x009a, ParseException -> 0x009d, IOException -> 0x009f, TryCatch #5 {all -> 0x009a, blocks: (B:7:0x0010, B:8:0x0014, B:13:0x001f, B:18:0x002a, B:23:0x0035, B:28:0x0040, B:33:0x004b, B:39:0x0057, B:41:0x0070, B:43:0x0079, B:48:0x0085, B:49:0x008a, B:42:0x0073, B:66:0x00a9, B:71:0x00ca), top: B:78:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.C1574ty f(java.io.File r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC0626cr.f(java.io.File):ty");
    }

    public static void g() {
        File fileE = e();
        File fileD = d();
        if (fileD.renameTo(fileE)) {
            return;
        }
        Ts.a("VariationsUtils", "Failed to replace old seed " + fileE + " with new seed " + fileD, new Object[0]);
    }

    public static void h() {
        long time = new Date().getTime();
        File file = new File(PathUtils.getDataDirectory(), "variations_stamp");
        try {
            if (file.createNewFile()) {
                return;
            }
            file.setLastModified(time);
        } catch (IOException unused) {
            Ts.a("VariationsUtils", "Failed to write " + file, new Object[0]);
        }
    }

    public static boolean i(FileOutputStream fileOutputStream, C1574ty c1574ty) {
        try {
            C0515as c0515as = (C0515as) ((AbstractC1891zl) C0571bs.DEFAULT_INSTANCE.c(EnumC0047Cl.NEW_BUILDER, null, null));
            c0515as.f(c1574ty.a);
            c0515as.d(c1574ty.b);
            long j = c1574ty.c;
            c0515as.b();
            C0571bs c0571bs = (C0571bs) c0515as.t;
            c0571bs.bitField0_ |= 32;
            c0571bs.date_ = j;
            boolean z = c1574ty.d;
            c0515as.b();
            C0571bs c0571bs2 = (C0571bs) c0515as.t;
            c0571bs2.bitField0_ |= 8;
            c0571bs2.isGzipCompressed_ = z;
            byte[] bArr = c1574ty.e;
            c0515as.e(AbstractC0731el.h(bArr, 0, bArr.length));
            AbstractC0065Dl abstractC0065DlA = c0515as.a();
            if (!abstractC0065DlA.h()) {
                throw new C1452rm();
            }
            ((C0571bs) abstractC0065DlA).b(fileOutputStream);
            return true;
        } catch (IOException e) {
            Ts.a("VariationsUtils", "Failed writing seed file: " + e.getMessage(), new Object[0]);
            return false;
        } finally {
            a(fileOutputStream);
        }
    }
}
