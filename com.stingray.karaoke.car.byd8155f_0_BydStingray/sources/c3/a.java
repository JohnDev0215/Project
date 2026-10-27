package C3;

import android.os.StatFs;
import java.io.File;
import r9.v;
import r9.z;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public z f967a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public v f968b;
    public double c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f969e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Z8.d f970f;

    public final h a() {
        long jW;
        z zVar = this.f967a;
        if (zVar == null) {
            throw new IllegalStateException("directory == null");
        }
        double d = this.c;
        if (d > 0.0d) {
            try {
                File fileF = zVar.f();
                fileF.mkdir();
                StatFs statFs = new StatFs(fileF.getAbsolutePath());
                jW = AbstractC2673a.w((long) (d * statFs.getBlockCountLong() * statFs.getBlockSizeLong()), this.d, this.f969e);
            } catch (Exception unused) {
                jW = this.d;
            }
        } else {
            jW = 0;
        }
        return new h(jW, this.f970f, this.f968b, zVar);
    }
}
