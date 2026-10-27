package f1;

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
    public static final Map<String, Lock> f4827a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final File f4828b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Lock f4829c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f4830d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public FileChannel f4831e;

    public a(String str, File file, boolean z7) {
        File file2 = new File(file, str + ".lck");
        this.f4828b = file2;
        this.f4829c = a(file2.getAbsolutePath());
        this.f4830d = z7;
    }

    public static Lock a(String str) {
        Lock reentrantLock;
        Map<String, Lock> map = f4827a;
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
        this.f4829c.lock();
        if (this.f4830d) {
            try {
                FileChannel channel = new FileOutputStream(this.f4828b).getChannel();
                this.f4831e = channel;
                channel.lock();
            } catch (IOException e8) {
                throw new IllegalStateException("Unable to grab copy lock.", e8);
            }
        }
    }

    public void c() {
        FileChannel fileChannel = this.f4831e;
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException unused) {
            }
        }
        this.f4829c.unlock();
    }
}
