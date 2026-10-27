package com.byd.data.collect.io;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.byd.data.common.d.a;
import com.byd.data.common.i.g;
import com.byd.data.common.sqlite.o;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import okio.BufferedSink;
import okio.Okio;

/* JADX INFO: loaded from: classes.dex */
public class WriteHandler extends BaseReadWrite {

    /* JADX INFO: renamed from: c */
    private final String f3164c;

    /* JADX INFO: renamed from: d */
    private volatile boolean f3165d;

    /* JADX INFO: renamed from: e */
    private ReentrantReadWriteLock.WriteLock f3166e;

    /* JADX INFO: renamed from: f */
    private int f3167f;

    /* JADX INFO: renamed from: g */
    private int f3168g;

    /* JADX INFO: renamed from: h */
    private int f3169h;

    /* JADX INFO: renamed from: i */
    private BufferedSink f3170i;
    private BufferedSink j;
    private int k;
    private int l;

    public WriteHandler(com.byd.data.common.server.e.a aVar, ReentrantReadWriteLock reentrantReadWriteLock) {
        super(aVar, reentrantReadWriteLock);
        this.f3164c = "WriteHandler";
        this.f3165d = false;
    }

    private void e(boolean z, File file) throws IOException {
        String name = file.getName();
        long jCurrentTimeMillis = System.currentTimeMillis();
        file.createNewFile();
        o.f().e().f(z, file, jCurrentTimeMillis);
        g.e(z ? "gb_file_name_key" : "gb_extra_file_name_key", name);
    }

    private boolean f(boolean z, File file, byte[] bArr) {
        BufferedSink bufferedSinkB = z ? this.f3170i : this.j;
        int length = z ? this.k : this.l;
        if (length == 0 && file.length() > length) {
            d.a.a.f.b.b.a.h("WriteHandler", "first file size is zero recalculate =" + file.length());
            length = (int) file.length();
        }
        int length2 = bArr.length + length;
        if (bufferedSinkB == null) {
            bufferedSinkB = Okio.b(Okio.a(file));
        }
        bufferedSinkB.f(bArr);
        boolean z2 = length2 >= 10485760;
        int i2 = this.f3169h;
        if (((i2 > 0 && i2 % 10 == 0) || z2) && z2) {
            bufferedSinkB.close();
        }
        if (z) {
            if (z2) {
                bufferedSinkB = null;
            }
            this.f3170i = bufferedSinkB;
            this.k = z2 ? 0 : length2;
            if (z2) {
                int i3 = this.f3167f;
                this.f3167f = i3 >= 999 ? 0 : i3 + 1;
            }
        } else {
            if (z2) {
                bufferedSinkB = null;
            }
            this.j = bufferedSinkB;
            this.l = z2 ? 0 : length2;
            if (z2) {
                int i4 = this.f3168g;
                this.f3168g = i4 >= 999 ? 0 : i4 + 1;
            }
        }
        if (z2 && file.length() < (10485760 - bArr.length) - 10) {
            d.a.a.f.b.b.a.h("WriteHandler", String.format("isG=%1s, fileSize= %2d, totalSize = %3d", Boolean.valueOf(z), Long.valueOf(file.length()), Integer.valueOf(length2)));
        }
        return z2;
    }

    private String g(boolean z) {
        String str = z ? "gb_history" : "gb_ex";
        int i2 = z ? this.f3167f : this.f3168g;
        StringBuffer stringBuffer = new StringBuffer(4 + str.length() + com.byd.data.common.i.c.f(999));
        stringBuffer.append(str);
        stringBuffer.append(i2 < 10 ? "0" : "");
        stringBuffer.append(i2);
        stringBuffer.append(".txt");
        return stringBuffer.toString();
    }

    private void h() {
        String strB = g.b("gb_file_name_key");
        String strB2 = g.b("gb_extra_file_name_key");
        if (!TextUtils.isEmpty(strB)) {
            this.f3167f = Integer.parseInt(strB.substring(10, strB.length() - 4));
            File file = new File(this.f3148a.m(true).getPath() + File.separator + strB);
            this.k = (int) (file.exists() ? file.length() : 0L);
        }
        if (!TextUtils.isEmpty(strB2)) {
            this.f3168g = Integer.parseInt(strB2.substring(5, strB2.length() - 4));
            File file2 = new File(this.f3148a.m(false).getPath() + File.separator + strB2);
            this.l = (int) (file2.exists() ? file2.length() : 0L);
        }
        d.a.a.f.b.b.a.d("WriteHandler", "initSpName gb_file_id =" + this.f3167f + ", gb_extension_id =" + this.f3168g);
    }

    private void i(boolean z, File file, byte[] bArr) throws IOException {
        if (!f(z, file, bArr) || file.getParentFile() == null) {
            return;
        }
        File file2 = new File(file.getParentFile().getPath() + File.separator + g(z));
        if (file2.exists() && file2.isFile()) {
            file2.delete();
        }
        e(z, file2);
    }

    private void k(byte[] bArr) throws IOException {
        ReentrantReadWriteLock.WriteLock writeLock = this.f3166e;
        if (writeLock == null || this.f3148a == null) {
            return;
        }
        writeLock.lock();
        boolean z = bArr[2] != a.EnumC0065a.GB_EXTRA_FILE_DATA.a();
        File fileM = this.f3148a.m(z);
        File file = new File(fileM.getPath() + File.separator + g(z));
        if (!file.exists()) {
            e(z, file);
        }
        if (z) {
            int i2 = this.f3169h;
            this.f3169h = i2 < Integer.MAX_VALUE ? i2 + 1 : 0;
        }
        i(z, file, bArr);
        this.f3166e.unlock();
    }

    @Override // com.byd.data.collect.io.BaseReadWrite
    String a() {
        return "write_file";
    }

    @Override // com.byd.data.collect.io.BaseReadWrite
    boolean b(Message message) {
        Object obj = message.obj;
        if (!(obj instanceof byte[])) {
            return true;
        }
        try {
            k((byte[]) obj);
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    @Override // com.byd.data.collect.io.BaseReadWrite
    void c(ReentrantReadWriteLock reentrantReadWriteLock) {
        this.f3166e = reentrantReadWriteLock.writeLock();
        h();
    }

    public void j(byte[] bArr, boolean z) {
        Handler handler = this.f3149b;
        if (handler == null) {
            d.a.a.f.b.b.a.d("WriteHandler", "pushToStack writeThread is null");
            return;
        }
        Message messageObtainMessage = handler.obtainMessage();
        messageObtainMessage.obj = bArr;
        if (z) {
            this.f3169h = 9;
        }
        this.f3149b.sendMessage(messageObtainMessage);
    }
}
