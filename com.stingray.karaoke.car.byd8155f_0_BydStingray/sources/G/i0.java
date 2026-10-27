package G;

import B2.C0073m;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.C1489n1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class i0 implements W2.g {
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f2583m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f2584n;
    public Object o;

    public i0(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArray = jSONObject.getJSONArray("upgrades");
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i7 = 0; i7 < length; i7++) {
            strArr[i7] = jSONArray.getString(i7);
        }
        this.f2584n = jSONObject.getString("sid");
        this.o = strArr;
        this.l = jSONObject.getLong("pingInterval");
        this.f2583m = jSONObject.getLong("pingTimeout");
    }

    public static final long a(i0 i0Var, long j3, long j10) {
        i0Var.getClass();
        if (j10 == 0) {
            return j3;
        }
        long j11 = 4;
        return (j3 / j11) + ((j10 / j11) * ((long) 3));
    }

    @Override // W2.g
    public B2.F f() {
        AbstractC1346c.j(this.l != -1);
        return new B2.w(0, this.l, (B2.x) this.f2584n);
    }

    @Override // W2.g
    public long g(C0073m c0073m) {
        long j3 = this.f2583m;
        if (j3 < 0) {
            return -1L;
        }
        long j10 = -(j3 + 2);
        this.f2583m = -1L;
        return j10;
    }

    @Override // W2.g
    public void i(long j3) {
        long[] jArr = (long[]) ((C1489n1) this.o).f16624m;
        this.f2583m = jArr[AbstractC1343A.e(jArr, j3, true)];
    }

    public i0(long j3, int i7) {
        AbstractC1346c.j(((x2.a) this.f2584n) == null);
        this.l = j3;
        this.f2583m = j3 + ((long) i7);
    }

    public i0(String str, byte[] bArr, long j3, long j10) {
        this.f2584n = str;
        this.o = bArr;
        this.l = j3;
        this.f2583m = j10;
    }
}
