package F1;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;

/* JADX INFO: renamed from: F1.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0254m implements T0.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2342a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2343b;
    public int c;

    public /* synthetic */ C0254m(int i7) {
        this.f2342a = i7;
    }

    @Override // T0.s
    public int a(int i7) {
        if (i7 >= 0 && i7 <= this.c) {
            K.X.y(i7, this.f2343b, i7);
        }
        return i7;
    }

    @Override // T0.s
    public int b(int i7) {
        if (i7 >= 0 && i7 <= this.f2343b) {
            K.X.x(i7, this.c, i7);
        }
        return i7;
    }

    public int c() {
        int i7 = this.c;
        if (i7 == 2) {
            return 10;
        }
        if (i7 == 5) {
            return 11;
        }
        if (i7 == 29) {
            return 12;
        }
        if (i7 != 42) {
            return i7 != 22 ? i7 != 23 ? 0 : 15 : TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        }
        return 16;
    }

    public void d(s3.U u4) {
        View view = u4.f22219a;
        this.f2343b = view.getLeft();
        this.c = view.getTop();
        view.getRight();
        view.getBottom();
    }

    public String toString() {
        switch (this.f2342a) {
            case 7:
                int i7 = this.f2343b;
                if (i7 <= 0) {
                    return "";
                }
                return "CueBarStartTime: " + i7 + ", CueBarDuration: " + (this.c - i7) + ", ";
            default:
                return super.toString();
        }
    }

    public /* synthetic */ C0254m(int i7, int i10, int i11) {
        this.f2342a = i11;
        this.f2343b = i7;
        this.c = i10;
    }
}
