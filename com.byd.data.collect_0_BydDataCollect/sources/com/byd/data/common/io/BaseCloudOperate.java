package com.byd.data.common.io;

import android.annotation.SuppressLint;
import com.byd.data.common.c.d.c;
import com.byd.data.common.http.ApiRetrofit;
import com.byd.data.common.server.b;
import com.byd.data.common.server.e.a;
import com.byd.data.common.sqlite.CloudFileTable;
import com.byd.data.common.sqlite.o;
import f.a0;
import f.f0;
import f.z;
import h.d;
import h.f;
import h.t;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import okio.BufferedSink;
import okio.Okio;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseCloudOperate<T> implements c, f<T> {

    /* JADX INFO: renamed from: b */
    protected final String f3433b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c */
    protected a f3434c;

    /* JADX INFO: renamed from: d */
    protected byte f3435d;

    /* JADX INFO: renamed from: e */
    protected BufferedSink f3436e;

    /* JADX INFO: renamed from: f */
    protected File f3437f;

    public BaseCloudOperate(a aVar) {
        this.f3434c = aVar;
        i().b(e((byte) 1), e((byte) 2));
    }

    @SuppressLint({"DefaultLocale"})
    private boolean d(byte b2, File file, ByteBuffer byteBuffer, boolean z) {
        BufferedSink bufferedSinkB = this.f3436e;
        if (bufferedSinkB == null) {
            bufferedSinkB = Okio.b(Okio.a(file));
        }
        bufferedSinkB.g(byteBuffer.array(), 0, byteBuffer.position());
        boolean z2 = z | (file.length() + ((long) byteBuffer.position()) >= 524288);
        byte b3 = this.f3435d;
        if ((b3 > 0 && b3 % 10 == 0) || z2) {
            bufferedSinkB.flush();
            if (z2) {
                bufferedSinkB.close();
            }
        }
        if (z2) {
            bufferedSinkB = null;
        }
        this.f3436e = bufferedSinkB;
        if (z2 && file.length() < (524288 - byteBuffer.position()) - 10) {
            this.f3437f = null;
            com.byd.data.common.i.f.g(this.f3433b, String.format("type=%1s, fileSize= %2d", Byte.valueOf(b2), Long.valueOf(file.length())));
        }
        return z2;
    }

    private File f(String str, byte b2, long j, CloudFileTable cloudFileTable) {
        String strE = e(b2);
        File file = new File(strE);
        if (!file.exists() && !file.mkdirs()) {
            com.byd.data.common.i.f.k(this.f3433b, "mkdirs failure");
        }
        File fileL = l(strE, b2, str, j);
        if (!fileL.exists() && n(fileL.createNewFile(), str, b2, fileL)) {
            cloudFileTable.k(b2, fileL, j, str);
        }
        return fileL;
    }

    @Override // h.f
    public void A(d<T> dVar, t<T> tVar) {
        boolean zP = b.e().p();
        if (zP) {
            com.byd.data.common.i.f.b(this.f3433b, "onResponse =" + tVar);
        }
        if (tVar.b() != 200) {
            return;
        }
        f0 f0VarA = dVar.a().a();
        if (f0VarA instanceof a0) {
            f0 f0VarA2 = ((a0) f0VarA).l().get(r5.size() - 1).a();
            if (f0VarA2 instanceof com.byd.data.common.http.e.a) {
                com.byd.data.common.http.e.a aVar = (com.byd.data.common.http.e.a) f0VarA2;
                if (zP) {
                    com.byd.data.common.i.f.b(this.f3433b, "onResponse param =" + aVar.f3419c);
                }
                i().d(aVar.f3418b, aVar.f3419c, aVar.f3420d);
            }
        }
    }

    @Override // com.byd.data.common.c.d.c
    public void a(List<File> list) {
        ArrayList arrayList = new ArrayList((int) Math.ceil(((double) (Math.min(50, list.size()) * 4)) / 3.0d));
        int length = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            File file = list.get(i2);
            arrayList.add(a0.b.d(h(), file.getName(), f0.c(g(), file)));
            length = (int) (((long) length) + file.length());
            if (i2 == list.size() - 1 || arrayList.size() >= 50 || length >= 2097152) {
                o(arrayList, new com.byd.data.common.http.e.a(list, (i2 + 1) - arrayList.size(), arrayList.size()));
                arrayList.clear();
                length = 0;
            }
        }
    }

    protected void b(String str) {
    }

    public void c() {
        BufferedSink bufferedSink = this.f3436e;
        try {
            if (bufferedSink != null) {
                try {
                    bufferedSink.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            this.f3434c = null;
        } finally {
            this.f3436e = null;
        }
    }

    protected abstract String e(byte b2);

    protected z g() {
        return z.d("application/zip");
    }

    protected String h() {
        return "files";
    }

    protected CloudFileTable i() {
        return o.f().d();
    }

    protected byte j() {
        return (byte) 1;
    }

    @Override // h.f
    public void k(d<T> dVar, Throwable th) {
        com.byd.data.common.i.f.b(this.f3433b, "reqUpload onFailure= " + th);
    }

    protected File l(String str, byte b2, String str2, long j) {
        File file;
        if ((m(b2) && j() == 2) && (file = this.f3437f) != null && file.exists()) {
            return this.f3437f;
        }
        File file2 = new File(str, ((int) b.e().o) + "-" + com.byd.data.common.g.a.i().f().i() + "-" + j + ".data");
        this.f3437f = file2;
        return file2;
    }

    protected boolean m(byte b2) {
        return b2 == 1;
    }

    protected boolean n(boolean z, String str, byte b2, File file) {
        if (!z) {
            return false;
        }
        try {
            this.f3436e = Okio.b(Okio.a(file));
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    protected void o(List<a0.b> list, com.byd.data.common.http.e.a aVar) {
        com.byd.data.common.i.f.b(this.f3433b, "reqUpload begin files =" + list.size() + ",offset =" + aVar.f3419c);
    }

    protected boolean p() {
        b bVarE = b.e();
        if (bVarE.s() || !com.byd.data.common.g.a.i().j().x()) {
            return false;
        }
        int iD = com.byd.data.common.g.a.i().g().D(j());
        ApiRetrofit apiRetrofitH = ApiRetrofit.h();
        short s = apiRetrofitH.g().countryCode;
        if (bVarE.p()) {
            com.byd.data.common.i.f.b(this.f3433b, "configCc=" + iD + ",localCc=" + ((int) s));
        }
        return iD == s && !apiRetrofitH.l();
    }

    public void q() {
        BufferedSink bufferedSink = this.f3436e;
        if (bufferedSink == null) {
            return;
        }
        try {
            bufferedSink.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void r(ByteBuffer byteBuffer, String str, byte b2, long j, boolean z) {
        File fileF;
        CloudFileTable cloudFileTableI = i();
        if (this.f3434c == null || cloudFileTableI == null) {
            return;
        }
        try {
            fileF = f(str, b2, j, cloudFileTableI);
            byte b3 = this.f3435d;
            this.f3435d = (byte) (b3 >= 127 ? 0 : b3 + 1);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (d(b2, fileF, byteBuffer, z) && fileF.getParentFile() != null) {
            b(str);
            cloudFileTableI.u(b2, fileF);
            if (p()) {
                if (j() == 2) {
                    str = String.valueOf((int) b2);
                }
                cloudFileTableI.w(str, this);
            }
        }
    }
}
