package p;

import android.os.StatFs;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class w90 {
    public os1 a;
    public c81 b;
    public double c;
    public long d;
    public long e;
    public f70 f;

    public final z02 a() {
        long blockCountLong;
        os1 os1Var = this.a;
        if (os1Var == null) {
            throw new IllegalStateException("directory == null".toString());
        }
        double d = this.c;
        if (d > 0.0d) {
            try {
                File fileE = os1Var.e();
                fileE.mkdir();
                StatFs statFs = new StatFs(fileE.getAbsolutePath());
                blockCountLong = (long) (d * statFs.getBlockCountLong() * statFs.getBlockSizeLong());
                long j = this.d;
                long j2 = this.e;
                if (j > j2) {
                    throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j2 + " is less than minimum " + j + '.');
                }
                if (blockCountLong < j) {
                    blockCountLong = j;
                } else if (blockCountLong > j2) {
                    blockCountLong = j2;
                }
            } catch (Exception unused) {
                blockCountLong = this.d;
            }
        } else {
            blockCountLong = 0;
        }
        return new z02(blockCountLong, os1Var, this.b, this.f);
    }
}
