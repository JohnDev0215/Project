package h3;

import android.view.View;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import java.util.Arrays;
import n2.U;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15495a = 2;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f15496b;
    public int c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f15497e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f15498f;

    public x(int i7) {
        this.c = i7;
        byte[] bArr = new byte[131];
        this.f15498f = bArr;
        bArr[2] = 1;
    }

    public void a(int i7, int i10, byte[] bArr) {
        if (this.f15496b) {
            int i11 = i10 - i7;
            byte[] bArr2 = (byte[]) this.f15498f;
            int length = bArr2.length;
            int i12 = this.f15497e;
            if (length < i12 + i11) {
                this.f15498f = Arrays.copyOf(bArr2, (i12 + i11) * 2);
            }
            System.arraycopy(bArr, i7, (byte[]) this.f15498f, this.f15497e, i11);
            this.f15497e += i11;
        }
    }

    public void b() {
        this.f15497e = this.f15496b ? ((T1.f) this.f15498f).g() : ((T1.f) this.f15498f).k();
    }

    public void c(View view, int i7) {
        if (this.f15496b) {
            this.f15497e = ((T1.f) this.f15498f).m() + ((T1.f) this.f15498f).b(view);
        } else {
            this.f15497e = ((T1.f) this.f15498f).e(view);
        }
        this.c = i7;
    }

    public void d(View view, int i7) {
        int iM = ((T1.f) this.f15498f).m();
        if (iM >= 0) {
            c(view, i7);
            return;
        }
        this.c = i7;
        if (!this.f15496b) {
            int iE = ((T1.f) this.f15498f).e(view);
            int iK = iE - ((T1.f) this.f15498f).k();
            this.f15497e = iE;
            if (iK > 0) {
                int iG = (((T1.f) this.f15498f).g() - Math.min(0, (((T1.f) this.f15498f).g() - iM) - ((T1.f) this.f15498f).b(view))) - (((T1.f) this.f15498f).c(view) + iE);
                if (iG < 0) {
                    this.f15497e -= Math.min(iK, -iG);
                    return;
                }
                return;
            }
            return;
        }
        int iG2 = (((T1.f) this.f15498f).g() - iM) - ((T1.f) this.f15498f).b(view);
        this.f15497e = ((T1.f) this.f15498f).g() - iG2;
        if (iG2 > 0) {
            int iC = this.f15497e - ((T1.f) this.f15498f).c(view);
            int iK2 = ((T1.f) this.f15498f).k();
            int iMin = iC - (Math.min(((T1.f) this.f15498f).e(view) - iK2, 0) + iK2);
            if (iMin < 0) {
                this.f15497e = Math.min(iG2, -iMin) + this.f15497e;
            }
        }
    }

    public boolean e(int i7) {
        if (!this.f15496b) {
            return false;
        }
        this.f15497e -= i7;
        this.f15496b = false;
        this.d = true;
        return true;
    }

    public void f(int i7) {
        this.f15496b |= i7 > 0;
        this.c += i7;
    }

    public void g() {
        switch (this.f15495a) {
            case 0:
                this.f15496b = false;
                this.d = false;
                break;
            default:
                this.c = -1;
                this.f15497e = Integer.MIN_VALUE;
                this.f15496b = false;
                this.d = false;
                break;
        }
    }

    public void h(int i7) {
        AbstractC1346c.j(!this.f15496b);
        boolean z6 = i7 == this.c;
        this.f15496b = z6;
        if (z6) {
            this.f15497e = 3;
            this.d = false;
        }
    }

    public String toString() {
        switch (this.f15495a) {
            case 2:
                StringBuilder sb = new StringBuilder("AnchorInfo{mPosition=");
                sb.append(this.c);
                sb.append(", mCoordinate=");
                sb.append(this.f15497e);
                sb.append(", mLayoutFromEnd=");
                sb.append(this.f15496b);
                sb.append(", mValid=");
                return AbstractC1347d.i(sb, this.d, '}');
            default:
                return super.toString();
        }
    }

    public x(U u4) {
        this.f15498f = u4;
    }

    public x() {
        g();
    }
}
