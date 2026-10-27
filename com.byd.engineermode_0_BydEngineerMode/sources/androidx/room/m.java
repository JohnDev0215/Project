package androidx.room;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: compiled from: SQLiteCopyOpenHelper.java */
/* JADX INFO: loaded from: classes.dex */
class m implements b.m.a.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f1360a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1361b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final File f1362c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f1363d;
    private final b.m.a.c e;
    private a f;
    private boolean g;

    m(Context context, String str, File file, int i, b.m.a.c cVar) {
        this.f1360a = context;
        this.f1361b = str;
        this.f1362c = file;
        this.f1363d = i;
        this.e = cVar;
    }

    private void d(File file) throws IOException {
        ReadableByteChannel channel;
        if (this.f1361b != null) {
            channel = Channels.newChannel(this.f1360a.getAssets().open(this.f1361b));
        } else {
            if (this.f1362c == null) {
                throw new IllegalStateException("copyFromAssetPath and copyFromFile == null!");
            }
            channel = new FileInputStream(this.f1362c).getChannel();
        }
        File fileCreateTempFile = File.createTempFile("room-copy-helper", ".tmp", this.f1360a.getCacheDir());
        fileCreateTempFile.deleteOnExit();
        androidx.room.r.d.a(channel, new FileOutputStream(fileCreateTempFile).getChannel());
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Failed to create directories for " + file.getAbsolutePath());
        }
        if (fileCreateTempFile.renameTo(file)) {
            return;
        }
        throw new IOException("Failed to move intermediate file (" + fileCreateTempFile.getAbsolutePath() + ") to destination (" + file.getAbsolutePath() + ").");
    }

    private void f() {
        String strC = c();
        File databasePath = this.f1360a.getDatabasePath(strC);
        a aVar = this.f;
        androidx.room.r.a aVar2 = new androidx.room.r.a(strC, this.f1360a.getFilesDir(), aVar == null || aVar.j);
        try {
            aVar2.b();
            if (!databasePath.exists()) {
                try {
                    d(databasePath);
                    aVar2.c();
                    return;
                } catch (IOException e) {
                    throw new RuntimeException("Unable to copy database file.", e);
                }
            }
            if (this.f == null) {
                aVar2.c();
                return;
            }
            try {
                int iC = androidx.room.r.c.c(databasePath);
                int i = this.f1363d;
                if (iC == i) {
                    aVar2.c();
                    return;
                }
                if (this.f.a(iC, i)) {
                    aVar2.c();
                    return;
                }
                if (this.f1360a.deleteDatabase(strC)) {
                    try {
                        d(databasePath);
                    } catch (IOException e2) {
                        Log.w("ROOM", "Unable to copy database file.", e2);
                    }
                } else {
                    Log.w("ROOM", "Failed to delete database file (" + strC + ") for a copy destructive migration.");
                }
                aVar2.c();
                return;
            } catch (IOException e3) {
                Log.w("ROOM", "Unable to read database version.", e3);
                aVar2.c();
                return;
            }
        } catch (Throwable th) {
            aVar2.c();
            throw th;
        }
        aVar2.c();
        throw th;
    }

    @Override // b.m.a.c
    public void a(boolean z) {
        this.e.a(z);
    }

    @Override // b.m.a.c
    public synchronized b.m.a.b b() {
        if (!this.g) {
            f();
            this.g = true;
        }
        return this.e.b();
    }

    @Override // b.m.a.c
    public String c() {
        return this.e.c();
    }

    void e(a aVar) {
        this.f = aVar;
    }
}
