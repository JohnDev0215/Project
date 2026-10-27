package com.byd.data.collect.server;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.byd.data.collect.io.CloudWriteHandler;
import com.byd.data.collect.io.ReadHandle;
import com.byd.data.collect.io.WriteHandler;
import com.byd.data.common.io.bms.BmsCloudWrite;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: FileOperator.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements com.byd.data.common.server.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile i f3252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3253b = "FileOperator";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3254c = 8;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f3255d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ReadHandle f3256e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private WriteHandler f3257f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private CloudWriteHandler f3258g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private BmsCloudWrite f3259h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ReentrantReadWriteLock f3260i;
    private String j;
    private c.a.b.j.c k;

    private i(Context context) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f3260i = reentrantReadWriteLock;
        this.f3255d = context;
        this.f3257f = new WriteHandler(this, reentrantReadWriteLock);
        this.f3256e = new ReadHandle(this, this.f3260i);
        this.f3258g = new CloudWriteHandler(this);
        this.f3259h = new BmsCloudWrite(this);
    }

    public static com.byd.data.common.server.e.a o(Context context) {
        if (f3252a == null) {
            synchronized (i.class) {
                if (f3252a == null) {
                    f3252a = new i(context);
                }
            }
        }
        return f3252a;
    }

    @Override // com.byd.data.common.server.e.a
    public Context a() {
        return this.f3255d;
    }

    @Override // com.byd.data.common.server.e.a
    public byte[] b(byte[] bArr, String str) {
        if (bArr.length == 0 || str == null || str.length() < 16) {
            return bArr;
        }
        if (this.k == null) {
            p(str);
        }
        return this.k.d(bArr);
    }

    @Override // com.byd.data.common.server.e.a
    public void c(ByteBuffer byteBuffer, String str, byte b2, long j, boolean z) {
        CloudWriteHandler cloudWriteHandler;
        if (byteBuffer == null || byteBuffer.position() < 20 || (cloudWriteHandler = this.f3258g) == null || this.f3255d == null) {
            return;
        }
        cloudWriteHandler.r(byteBuffer, str, b2, j, z);
    }

    @Override // com.byd.data.common.server.e.a
    public void d() {
        WriteHandler writeHandler = this.f3257f;
        if (writeHandler != null) {
            writeHandler.d();
            this.f3257f = null;
        }
        ReadHandle readHandle = this.f3256e;
        if (readHandle != null) {
            readHandle.d();
            this.f3256e = null;
        }
        BmsCloudWrite bmsCloudWrite = this.f3259h;
        if (bmsCloudWrite != null) {
            bmsCloudWrite.c();
        }
        this.f3255d = null;
        this.k = null;
        this.j = null;
    }

    @Override // com.byd.data.common.server.e.a
    public void e(ByteBuffer byteBuffer, byte b2, int i2, boolean z) {
        if (byteBuffer == null || this.f3259h == null || this.f3255d == null || byteBuffer.position() < 20) {
            return;
        }
        this.f3259h.r(byteBuffer, "", b2, i2, z);
    }

    @Override // com.byd.data.common.server.e.a
    public void f(ByteBuffer byteBuffer, int i2, long j, boolean z) {
        c(byteBuffer, String.valueOf(i2), (byte) 2, j, z);
    }

    @Override // com.byd.data.common.server.e.a
    public void g() {
        CloudWriteHandler cloudWriteHandler = this.f3258g;
        if (cloudWriteHandler == null) {
            return;
        }
        cloudWriteHandler.t();
    }

    @Override // com.byd.data.common.server.e.a
    public void h(byte[] bArr, boolean z) {
        if (bArr.length < 20 || this.f3257f == null || this.f3255d == null) {
            return;
        }
        if (m(true).exists()) {
            this.f3257f.j(bArr, z);
        } else {
            com.byd.data.common.i.f.d("FileOperator", "vdt partition (data_acquisition) not exist mkdirs failed");
        }
    }

    @Override // com.byd.data.common.server.e.a
    public String i() {
        if (!TextUtils.isEmpty(this.j)) {
            return this.j;
        }
        String str = SystemProperties.get("persist.vendor.bosch.cfg.diag.vin", "00000000000000000");
        if (str != null && str.startsWith("LC")) {
            this.j = str;
            com.byd.data.common.i.f.j("FileOperator", " vin valid =", str);
            if (this.k != null) {
                this.k = null;
            }
        }
        return str;
    }

    @Override // com.byd.data.common.server.e.a
    public byte[] j(byte[] bArr, String str) {
        if (bArr.length == 0) {
            return bArr;
        }
        if (this.k == null) {
            p(str);
        }
        return this.k.g(bArr);
    }

    @Override // com.byd.data.common.server.e.a
    public void k(String str, com.byd.data.common.server.e.c cVar) {
        if (this.f3256e == null || this.f3255d == null || str.length() <= 5) {
            return;
        }
        this.f3256e.k(str, cVar);
    }

    @Override // com.byd.data.common.server.e.a
    public void l() {
        CloudWriteHandler cloudWriteHandler = this.f3258g;
        if (cloudWriteHandler != null) {
            cloudWriteHandler.q();
        }
        BmsCloudWrite bmsCloudWrite = this.f3259h;
        if (bmsCloudWrite != null) {
            bmsCloudWrite.q();
        }
    }

    @Override // com.byd.data.common.server.e.a
    public File m(boolean z) {
        if (this.f3255d == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("app_");
        sb.append(z ? "gb_file" : "gb_extension_file");
        File file = new File(com.byd.data.common.g.a.i().h() + sb.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    @Override // com.byd.data.common.server.e.a
    public void n(boolean z, com.byd.data.common.server.e.c cVar) {
        if (this.f3256e == null || this.f3255d == null) {
            return;
        }
        this.f3256e.g(m(z), com.byd.data.common.i.g.b(z ? "gb_file_name_key" : "gb_extra_file_name_key"), cVar);
    }

    public void p(String str) {
        byte[] bArrA = new c.a.b.i.b(c.a.b.i.a.MD5).a(str.substring(str.length() - 16));
        byte[] bArr = new byte[8];
        byte[] bArr2 = new byte[8];
        for (int i2 = 0; i2 < 16; i2++) {
            byte b2 = (byte) (~bArrA[i2]);
            if (i2 < 8) {
                bArr[i2] = b2;
            } else {
                bArr2[i2 - 8] = b2;
            }
        }
        String strA = c.a.a.a.d.a(bArrA);
        byte[] bArrD = c.a.b.h.d(strA.getBytes(StandardCharsets.UTF_8)).d(bArr);
        byte[] bytes = strA.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length / 2;
        for (int i3 = 0; i3 < length; i3++) {
            byte b3 = bytes[i3];
            int length2 = (bytes.length - i3) - 1;
            bytes[i3] = bytes[length2];
            bytes[length2] = b3;
        }
        byte[] bArrD2 = c.a.b.h.d(bytes).d(bArr2);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
        byteBufferAllocate.put(bArrD, 0, 8);
        byte[] bArrArray = byteBufferAllocate.put(bArrD2, 0, 8).array();
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(16);
        byteBufferAllocate2.put(bArrD2, 8, 8);
        byte[] bArrArray2 = byteBufferAllocate2.put(bArrD, 8, 8).array();
        c.a.b.j.c cVar = new c.a.b.j.c(c.a.b.e.CBC, c.a.b.f.PKCS5Padding, bArrArray);
        this.k = cVar;
        cVar.p(bArrArray2);
    }
}
