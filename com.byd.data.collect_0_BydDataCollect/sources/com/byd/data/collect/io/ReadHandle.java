package com.byd.data.collect.io;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.byd.data.collect.server.i;
import com.byd.data.common.d.a;
import com.byd.data.common.i.e;
import com.byd.data.common.i.g;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import okio.BufferedSource;
import okio.Okio;

/* JADX INFO: loaded from: classes.dex */
public class ReadHandle extends BaseReadWrite {

    /* JADX INFO: renamed from: c */
    private final String f3159c;

    /* JADX INFO: renamed from: d */
    private final String f3160d;

    /* JADX INFO: renamed from: e */
    private ReentrantReadWriteLock.ReadLock f3161e;

    public ReadHandle(com.byd.data.common.server.e.a aVar, ReentrantReadWriteLock reentrantReadWriteLock) {
        super(aVar, reentrantReadWriteLock);
        this.f3159c = "ReadHandle";
        this.f3160d = "call_back_key";
    }

    /* JADX INFO: renamed from: e */
    public /* synthetic */ void f(File[] fileArr, String str, com.byd.data.common.server.e.c cVar, File file, Integer num) {
        h(file, num.intValue(), fileArr.length, str, cVar);
    }

    private void h(File file, int i2, int i3, String str, com.byd.data.common.server.e.c cVar) {
        com.byd.data.common.server.e.a aVar;
        ReentrantReadWriteLock.ReadLock readLock;
        String name = file.getName();
        if (cVar.l() == null || (aVar = this.f3148a) == null || aVar.a() == null || this.f3161e == null || name.length() < 5) {
            cVar.f(a.g.DESTROY);
            return;
        }
        boolean zEquals = TextUtils.equals(name, str);
        d.a.a.f.b.b.a.h("ReadHandle", "fileName =" + name + ", lastWrite= " + str + " , isReadLock =" + zEquals);
        if (zEquals) {
            try {
                this.f3161e.lock();
            } finally {
                readLock = this.f3161e;
                if (readLock != null && zEquals) {
                    readLock.unlock();
                }
            }
        }
        if (!file.exists()) {
            if (readLock == null || !zEquals) {
                return;
            } else {
                return;
            }
        }
        i(file, i2, i3, cVar);
        ReentrantReadWriteLock.ReadLock readLock2 = this.f3161e;
        if (readLock2 == null || !zEquals) {
            return;
        }
        readLock2.unlock();
    }

    private void i(File file, int i2, int i3, com.byd.data.common.server.e.c cVar) {
        j(file, i2, i3, cVar);
        try {
            BufferedSource bufferedSourceC = Okio.c(Okio.i(file));
            try {
                bufferedSourceC.t(Okio.h(cVar.l()));
                bufferedSourceC.close();
            } finally {
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void j(File file, int i2, int i3, com.byd.data.common.server.e.c cVar) {
        boolean z = i2 == 0;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(file.getName().length() + 6 + (z ? 2 : 0));
        if (z) {
            byteBufferAllocate.putChar((char) (i3 & 65535));
        }
        String name = file.getName();
        byteBufferAllocate.putChar((char) (name.length() & 65535)).put(name.getBytes(StandardCharsets.UTF_8)).putInt((int) file.length());
        cVar.c(byteBufferAllocate.array());
    }

    @Override // com.byd.data.collect.io.BaseReadWrite
    String a() {
        return "read_file";
    }

    @Override // com.byd.data.collect.io.BaseReadWrite
    boolean b(Message message) {
        d.a.a.f.b.b.a.h("ReadHandle", "handlerMessage is begin-----");
        Serializable serializable = message.getData().getSerializable("call_back_key");
        Object obj = message.obj;
        if ((obj instanceof String) && (serializable instanceof com.byd.data.common.server.e.c)) {
            String str = (String) obj;
            File file = new File(str);
            com.byd.data.common.server.e.c cVar = (com.byd.data.common.server.e.c) serializable;
            boolean z = (file.isDirectory() && file.getName().endsWith("gb_file")) || str.startsWith("gb_history");
            String strB = g.b(z ? "gb_file_name_key" : "gb_extra_file_name_key");
            if (file.isDirectory()) {
                g(file, strB, cVar);
            } else if (str.length() > 5) {
                File fileM = i.o(this.f3148a.a()).m(z);
                if (fileM == null || !fileM.exists()) {
                    cVar.f(a.g.FILE_NOT_EXIST);
                } else {
                    h(new File(fileM.getPath() + File.separator + str), 0, 1, strB, cVar);
                }
            }
        }
        return true;
    }

    @Override // com.byd.data.collect.io.BaseReadWrite
    void c(ReentrantReadWriteLock reentrantReadWriteLock) {
        this.f3161e = reentrantReadWriteLock.readLock();
    }

    public void g(File file, final String str, final com.byd.data.common.server.e.c cVar) {
        com.byd.data.common.server.e.a aVar = this.f3148a;
        if (aVar == null || aVar.a() == null || this.f3161e == null) {
            cVar.f(a.g.DESTROY);
            return;
        }
        if (!file.exists()) {
            cVar.f(a.g.FILE_NOT_EXIST);
            return;
        }
        final File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            cVar.f(a.g.FILE_NOT_EXIST);
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Stream.of((Object[]) fileArrListFiles).forEachOrdered(e.a(new BiConsumer() { // from class: com.byd.data.collect.io.b
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f3172a.f(fileArrListFiles, str, cVar, (File) obj, (Integer) obj2);
            }
        }));
        d.a.a.f.b.b.a.h("ReadHandle", file.getPath() + ",total thread pool wasteTime  = " + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    public void k(String str, com.byd.data.common.server.e.c cVar) {
        Handler handler = this.f3149b;
        if (handler == null) {
            d.a.a.f.b.b.a.h("ReadHandle", "sendReadMsg handle is null");
            return;
        }
        Message messageObtainMessage = handler.obtainMessage();
        messageObtainMessage.obj = str;
        messageObtainMessage.getData().putSerializable("call_back_key", cVar);
        this.f3149b.sendMessage(messageObtainMessage);
    }
}
