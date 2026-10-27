package androidx.room.r;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: CopyLock.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<String, Lock> f1377a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f1378b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Lock f1379c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f1380d;
    private FileChannel e;

    public a(String str, File file, boolean z) {
        File file2 = new File(file, str + ".lck");
        this.f1378b = file2;
        this.f1379c = a(file2.getAbsolutePath());
        this.f1380d = z;
    }

    private static Lock a(String str) {
        Lock reentrantLock;
        Map<String, Lock> map = f1377a;
        synchronized (map) {
            reentrantLock = map.get(str);
            if (reentrantLock == null) {
                reentrantLock = new ReentrantLock();
                map.put(str, reentrantLock);
            }
        }
        return reentrantLock;
    }

    public void b() {
        this.f1379c.lock();
        if (this.f1380d) {
            try {
                FileChannel channel = new FileOutputStream(this.f1378b).getChannel();
                this.e = channel;
                channel.lock();
            } catch (IOException e) {
                throw new IllegalStateException("Unable to grab copy lock.", e);
            }
        }
    }

    public void c() {
        FileChannel fileChannel = this.e;
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException unused) {
            }
        }
        this.f1379c.unlock();
    }
}
