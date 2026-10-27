package defpackage;

import android.util.Log;
import defpackage.ap3;
import defpackage.k00;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.chromium.base.Callback;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo.system.impl.WatcherImpl;

/* JADX INFO: compiled from: BlobReceiver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ef implements cf {
    public final OutputStream f;
    public long h;
    public long i;
    public y50 j;
    public Callback<Integer> k;
    public final ByteBuffer e = ByteBuffer.allocateDirect(65536);
    public final long g = 52428800;

    /* JADX INFO: compiled from: BlobReceiver.java */
    public class a implements ap3.a {
        public a() {
        }

        @Override // ap3.a
        public final void a(int i) {
            ef efVar = ef.this;
            if (efVar.k == null) {
                return;
            }
            if (i == 0) {
                efVar.t();
            } else {
                efVar.C(i, "Watcher reported error.");
            }
        }
    }

    public ef(FileOutputStream fileOutputStream) {
        this.f = fileOutputStream;
    }

    public final void C(int i, String str) {
        if (i == 0) {
            i = 3;
        }
        Log.w("cr_share", str);
        a8.s(this.f);
        this.k.onResult(Integer.valueOf(i));
        this.k = null;
    }

    @Override // defpackage.cf
    public final void K0(int i, long j) {
        if (this.k == null) {
            return;
        }
        t();
    }

    @Override // defpackage.cf
    public final void X(long j, long j2) {
        if (this.k == null) {
            return;
        }
        if (j2 > this.g) {
            C(8, "Stream exceeds permitted size");
            return;
        }
        this.h = j2;
        if (this.i < j2) {
            CoreImpl.a.a.getClass();
            new WatcherImpl().a(this.j, k00.a.c, new a());
            return;
        }
        try {
            this.f.close();
            this.k.onResult(0);
            this.k = null;
        } catch (IOException unused) {
            C(1, "Failed to close stream.");
        }
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        if (this.k == null) {
            return;
        }
        C(ki1Var.e, "Connection error detected.");
    }

    public final void t() {
        long jIntValue;
        long j;
        OutputStream outputStream = this.f;
        ByteBuffer byteBuffer = this.e;
        do {
            try {
                y50 y50Var = this.j;
                x50 x50Var = x50.c;
                ResultAnd resultAndQ = y50Var.q(byteBuffer);
                int i = resultAndQ.a;
                if (i == 17) {
                    return;
                }
                if (i != 0) {
                    C(i, "Failed to read from blob.");
                    return;
                }
                Integer num = (Integer) resultAndQ.b;
                if (num.intValue() <= 0) {
                    C(17, "No data available");
                    return;
                }
                try {
                    outputStream.write(byteBuffer.array(), byteBuffer.arrayOffset(), num.intValue());
                    jIntValue = this.i + ((long) num.intValue());
                    this.i = jIntValue;
                    j = this.h;
                } catch (IOException unused) {
                    C(15, "Failed to write to stream.");
                    return;
                }
            } catch (ki1 e) {
                C(e.e, "Failed to receive blob.");
                return;
            }
        } while (jIntValue < j);
        if (jIntValue != j) {
            C(11, "Received more bytes than expected size.");
            return;
        }
        try {
            outputStream.close();
            this.k.onResult(0);
            this.k = null;
        } catch (IOException unused2) {
            C(1, "Failed to close stream.");
        }
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
