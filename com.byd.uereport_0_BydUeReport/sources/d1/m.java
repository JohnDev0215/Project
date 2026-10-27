package d1;

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
public class m implements h1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4414a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4415b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final File f4416c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4417d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h1.c f4418e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f4419f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4420g;

    public m(Context context, String str, File file, int i8, h1.c cVar) {
        this.f4414a = context;
        this.f4415b = str;
        this.f4416c = file;
        this.f4417d = i8;
        this.f4418e = cVar;
    }

    @Override // h1.c
    public void a(boolean z7) {
        this.f4418e.a(z7);
    }

    @Override // h1.c
    public synchronized h1.b b() {
        if (!this.f4420g) {
            f();
            this.f4420g = true;
        }
        return this.f4418e.b();
    }

    @Override // h1.c
    public String c() {
        return this.f4418e.c();
    }

    public final void d(File file) throws IOException {
        ReadableByteChannel channel;
        if (this.f4415b != null) {
            channel = Channels.newChannel(this.f4414a.getAssets().open(this.f4415b));
        } else {
            if (this.f4416c == null) {
                throw new IllegalStateException("copyFromAssetPath and copyFromFile == null!");
            }
            channel = new FileInputStream(this.f4416c).getChannel();
        }
        File fileCreateTempFile = File.createTempFile("room-copy-helper", ".tmp", this.f4414a.getCacheDir());
        fileCreateTempFile.deleteOnExit();
        f1.d.a(channel, new FileOutputStream(fileCreateTempFile).getChannel());
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Failed to create directories for " + file.getAbsolutePath());
        }
        if (fileCreateTempFile.renameTo(file)) {
            return;
        }
        throw new IOException("Failed to move intermediate file (" + fileCreateTempFile.getAbsolutePath() + ") to destination (" + file.getAbsolutePath() + ").");
    }

    public void e(a aVar) {
        this.f4419f = aVar;
    }

    public final void f() {
        String strC = c();
        File databasePath = this.f4414a.getDatabasePath(strC);
        a aVar = this.f4419f;
        f1.a aVar2 = new f1.a(strC, this.f4414a.getFilesDir(), aVar == null || aVar.f4343j);
        try {
            aVar2.b();
            if (!databasePath.exists()) {
                try {
                    d(databasePath);
                    aVar2.c();
                    return;
                } catch (IOException e8) {
                    throw new RuntimeException("Unable to copy database file.", e8);
                }
            }
            if (this.f4419f == null) {
                aVar2.c();
                return;
            }
            try {
                int iC = f1.c.c(databasePath);
                int i8 = this.f4417d;
                if (iC == i8) {
                    aVar2.c();
                    return;
                }
                if (this.f4419f.a(iC, i8)) {
                    aVar2.c();
                    return;
                }
                if (this.f4414a.deleteDatabase(strC)) {
                    try {
                        d(databasePath);
                    } catch (IOException e9) {
                        Log.w("ROOM", "Unable to copy database file.", e9);
                    }
                } else {
                    Log.w("ROOM", "Failed to delete database file (" + strC + ") for a copy destructive migration.");
                }
                aVar2.c();
                return;
            } catch (IOException e10) {
                Log.w("ROOM", "Unable to read database version.", e10);
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
}
