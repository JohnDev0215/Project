package c.a.a.h.b;

import android.content.Context;
import com.byd.common.constants.ResultCodeConsts;

/* JADX INFO: compiled from: UpdateBenchCycleTestState.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends c.a.a.h.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2244a = "d";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2245b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.a.g.i f2246c;

    public d(Context context, c.a.a.g.i iVar) {
        this.f2245b = context;
        this.f2246c = iVar;
    }

    @Override // c.a.a.h.a.a
    public int b(int i2) {
        c.a.b.d.e.c(f2244a, "curJudgeState = " + i2 + ", msg = " + a(i2));
        if (i2 != 111) {
            if (i2 != 201) {
                if (i2 != 1000) {
                    return i2;
                }
                return 110;
            }
        } else if (!this.f2246c.G()) {
            return 200;
        }
        return 1002;
    }

    @Override // c.a.a.h.a.a
    public void c(c.a.a.h.a.b bVar) {
        int i2 = bVar.f2227a;
        c.a.b.d.e.c(f2244a, "nextJudgeState = " + i2 + ", msg = " + a(i2));
        if (i2 == 110) {
            this.f2246c.v0();
            return;
        }
        if (i2 == 112) {
            e(1001);
            this.f2246c.c(0);
            return;
        }
        if (i2 == 200) {
            this.f2246c.E("otaModel", 1);
            this.f2246c.D();
            return;
        }
        if (i2 == 202) {
            this.f2246c.q0(ResultCodeConsts.NormalCodeEnum.FAIL_OTA_PACKAGES_NONEXISTENT, false);
            e(1001);
        } else if (i2 == 1001) {
            this.f2246c.V();
        } else {
            if (i2 != 1002) {
                return;
            }
            this.f2246c.m(true, "UpdateBenchCycleTestState startActualUpgrade");
            this.f2246c.t0();
            this.f2246c.w0();
        }
    }

    @Override // c.a.a.h.a.a
    public void d(int i2) {
    }
}
