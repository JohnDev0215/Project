package defpackage;

import android.util.Log;
import java.nio.ByteBuffer;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: PersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k02 implements vi2<ByteBuffer> {
    public final /* synthetic */ vi2 e;
    public final /* synthetic */ l02 f;

    public k02(l02 l02Var, vi2 vi2Var) {
        this.f = l02Var;
        this.e = vi2Var;
    }

    @Override // defpackage.vi2
    public final ByteBuffer get() {
        ByteBuffer byteBuffer = null;
        vi2 vi2Var = this.e;
        if (vi2Var != null) {
            try {
                TraceEvent traceEventH0 = TraceEvent.H0("PersistedTabData.Serialize", null);
                try {
                    ByteBuffer byteBuffer2 = (ByteBuffer) vi2Var.get();
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    byteBuffer = byteBuffer2;
                } catch (Throwable th) {
                    if (traceEventH0 != null) {
                        try {
                            traceEventH0.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            } catch (OutOfMemoryError e) {
                Log.e("cr_PTD", "Out of memory error when attempting to save PersistedTabData. Details: " + e.getMessage());
            }
            oj2.O("Tabs.PersistedTabData.Serialize." + this.f.i(), byteBuffer != null);
        }
        return byteBuffer;
    }

    @Override // defpackage.vi2
    public final void m() {
        this.e.m();
    }
}
