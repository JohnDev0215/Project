package org.libpag;

/* JADX INFO: loaded from: classes3.dex */
public class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ HardwareDecoder f4054a;

    public b(HardwareDecoder hardwareDecoder) {
        this.f4054a = hardwareDecoder;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f4054a.f4011c.stop();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.f4054a.f4011c.release();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        this.f4054a.f4011c = null;
        this.f4054a.f4010b.a();
    }
}
