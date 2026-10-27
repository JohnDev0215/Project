package com.byd.data.common.present;

import android.text.TextUtils;
import com.byd.data.common.b.b;
import com.byd.data.common.c.a;
import com.byd.data.common.c.b;
import com.byd.data.common.entiry.cloud.config.BaseConfigResult;
import com.byd.data.common.http.ApiRetrofit;
import com.google.gson.n;
import h.d;
import h.f;
import h.t;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseCloudPresent<V extends b, T extends com.byd.data.common.b.b<P>, P extends BaseConfigResult> implements f<T>, a<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected String f3438b = getClass().getSimpleName() + com.byd.data.common.i.b.f3423a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected volatile byte f3439c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected boolean f3440d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected e.a.k.a f3441e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected P f3442f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected V f3443g;

    public BaseCloudPresent(V v) {
        this.f3443g = v;
        com.byd.data.common.i.f.g(this.f3438b, "construct hash =" + hashCode());
    }

    private void L() {
        if (this.f3442f.d(ApiRetrofit.h().g())) {
            this.f3442f.a(this);
        }
        com.byd.data.common.i.f.g(this.f3438b, "initData is end step = " + ((int) this.f3439c));
    }

    private void Q(T t, boolean z, int i2) {
        this.f3440d = z;
        P p = (P) t.getResult();
        this.f3442f = p;
        String str = p == null ? null : p.uuid;
        boolean z2 = (z || t.rebacekResult == 0) ? false : true;
        com.byd.data.common.i.f.g(this.f3438b, "parseConfigFile is begin isLocal =" + z + ",isReqFailure =" + z2 + ",type =" + i2 + ",uuidNew == " + str);
        if (z2 || TextUtils.isEmpty(str)) {
            this.f3439c = (byte) 6;
            if (z) {
                return;
            }
            I(i2);
            return;
        }
        this.f3439c = (byte) 5;
        L();
        this.f3439c = (byte) 6;
        if (z || N(i2, t.getUpdateIndex()) || t.json == null || t.getUpdateIndex() != 1) {
            return;
        }
        byte[] bytes = t.json.getBytes(StandardCharsets.UTF_8);
        U(bytes, i2);
        t.json = null;
        V v = this.f3443g;
        if ((v instanceof com.byd.data.common.c.c.a) && (bytes != null)) {
            v.y(bytes);
        }
    }

    private void U(byte[] bArr, int i2) {
        File fileA = com.byd.data.common.g.a.i().a(i2, true);
        if (fileA.exists()) {
            try {
                BufferedSink bufferedSinkB = Okio.b(Okio.e(fileA));
                try {
                    bufferedSinkB.f(bArr);
                    bufferedSinkB.close();
                } finally {
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // h.f
    public void A(d<T> dVar, t<T> tVar) {
        int iK = K();
        if (tVar.b() != 200) {
            T(iK);
        } else {
            Q(tVar.a(), false, iK);
        }
    }

    @Override // com.byd.data.common.c.a
    public short C() {
        P p = this.f3442f;
        if (p == null) {
            return (short) 0;
        }
        return p.countryCode;
    }

    @Override // com.byd.data.common.c.a
    public byte F() {
        if (O()) {
            com.byd.data.common.i.f.g(this.f3438b, "reqCloudConfig cloud switch is not available");
            this.f3439c = (byte) 3;
            return (byte) 3;
        }
        this.f3439c = (byte) 0;
        boolean zX = com.byd.data.common.g.a.i().j().x();
        boolean z = zX || com.byd.data.common.i.j.b.b();
        com.byd.data.common.i.f.g(this.f3438b, "reqCloudConfig  isNetworkAvailable =" + z + ",apn3Enable =" + zX);
        int iK = K();
        if (!z) {
            T(iK);
            return this.f3439c;
        }
        this.f3439c = (byte) 1;
        ApiRetrofit apiRetrofitH = ApiRetrofit.h();
        if (!apiRetrofitH.l()) {
            S(apiRetrofitH);
            return this.f3439c;
        }
        com.byd.data.common.i.f.g(this.f3438b, "retrofit noDomain(), return step = config_file_damage");
        this.f3439c = (byte) 4;
        return (byte) 4;
    }

    public void G() {
        com.byd.data.common.i.f.g(this.f3438b, "appNotifyVehicleHal");
        File file = new File("/data_acquisition/cloud/CanCollectEventConfig.conf");
        if (!file.exists()) {
            com.byd.data.common.i.f.g(this.f3438b, "文件不存在");
            return;
        }
        byte[] bArr = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                fileInputStream.read(bArr);
                fileInputStream.close();
            } finally {
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        V v = this.f3443g;
        if (v != null) {
            v.y(bArr);
            com.byd.data.common.i.f.g(this.f3438b, "注册了Can信号");
        }
    }

    protected abstract void H(T t);

    protected void I(int i2) {
        if (com.byd.data.common.g.a.i().c(i2, J())) {
            com.byd.data.common.i.f.g(this.f3438b, "delLocalCloudConfig is success");
        }
    }

    protected String J() {
        return "uuid";
    }

    protected abstract int K();

    protected boolean M() {
        return false;
    }

    protected boolean N(int i2, byte b2) {
        return false;
    }

    protected boolean O() {
        return com.byd.data.common.server.b.e().s();
    }

    public void P(Throwable th) {
        this.f3439c = (byte) 3;
        com.byd.data.common.i.f.d(this.f3438b, "onFailure req cloud configuration " + th.getMessage());
        T(K());
    }

    protected void R() {
        e.a.k.a aVar = this.f3441e;
        if (aVar == null) {
            return;
        }
        aVar.d();
    }

    protected abstract void S(ApiRetrofit apiRetrofit);

    public void T(int i2) {
        BufferedSource bufferedSourceC;
        this.f3439c = (byte) 3;
        File fileA = com.byd.data.common.g.a.i().a(i2, false);
        com.byd.data.common.i.f.g(this.f3438b, "useLocalConfig， localFile path=" + fileA.getPath());
        if (!fileA.exists() || fileA.isDirectory()) {
            this.f3439c = (byte) 4;
            com.byd.data.common.i.f.g(this.f3438b, "localFile not exist step =" + ((int) this.f3439c));
            return;
        }
        try {
            bufferedSourceC = Okio.c(Okio.i(fileA));
        } catch (n | IOException e2) {
            e2.printStackTrace();
        }
        try {
            String str = new String(bufferedSourceC.A(), StandardCharsets.UTF_8);
            if (TextUtils.isEmpty(str)) {
                this.f3439c = (byte) 4;
                com.byd.data.common.i.f.g(this.f3438b, "localFile json empty =" + ((int) this.f3439c));
                bufferedSourceC.close();
                return;
            }
            com.byd.data.common.b.b bVar = (com.byd.data.common.b.b) com.byd.data.common.server.b.e().b().j(str, ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
            H(bVar);
            Q(bVar, true, i2);
            if (i2 == 1) {
                G();
            }
            bufferedSourceC.close();
            com.byd.data.common.i.f.g(this.f3438b, "readLocalConfig apn3 or network invalid is end step =" + ((int) this.f3439c));
        } catch (Throwable th) {
            if (bufferedSourceC != null) {
                try {
                    bufferedSourceC.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void b(byte b2) {
        com.byd.data.common.i.f.g(this.f3438b, "CloudService invoke set step=" + ((int) b2) + ", preStep=" + ((int) this.f3439c));
        this.f3439c = b2;
    }

    public void d() {
        this.f3443g = null;
        R();
        this.f3441e = null;
        P p = this.f3442f;
        if (p != null) {
            p.e();
            this.f3442f = null;
        }
    }

    public boolean f() {
        P p;
        if (6 <= this.f3439c && (p = this.f3442f) != null && p.flagType != 0) {
            return true;
        }
        com.byd.data.common.i.f.g(this.f3438b, "isSatisfyCollect is false step=" + ((int) this.f3439c) + ",cloud=" + this.f3442f);
        P p2 = this.f3442f;
        if (p2 != null) {
            com.byd.data.common.i.f.h(this.f3438b, "isSatisfyCollect is false, cloud.flagType = ", Integer.valueOf(p2.flagType));
        }
        return false;
    }

    @Override // com.byd.data.common.c.a
    public boolean i() {
        return this.f3440d;
    }

    @Override // com.byd.data.common.c.a
    public byte j() {
        return this.f3439c;
    }

    @Override // h.f
    public void k(d<T> dVar, Throwable th) {
        P(th);
    }
}
