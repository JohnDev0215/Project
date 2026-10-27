package B5;

import android.util.JsonWriter;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class G implements Closeable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f654m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f655n;
    public final Object o;

    public G(OutputStream outputStream) {
        this.l = 0;
        this.f654m = false;
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        this.o = bufferedWriter;
        this.f655n = new JsonWriter(bufferedWriter);
    }

    public void b() throws IOException {
        ((JsonWriter) this.f655n).name("batch").beginArray();
        this.f654m = false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.l) {
            case 0:
                ((JsonWriter) this.f655n).close();
                return;
            default:
                if (this.f654m) {
                    return;
                }
                this.f654m = true;
                C3.e eVar = (C3.e) this.o;
                synchronized (eVar) {
                    C3.b bVar = (C3.b) this.f655n;
                    int i7 = bVar.f976h - 1;
                    bVar.f976h = i7;
                    if (i7 == 0 && bVar.f974f) {
                        Q8.g gVar = C3.e.B;
                        eVar.y(bVar);
                    }
                    break;
                }
                return;
        }
    }

    public void g() throws IOException {
        if (!this.f654m) {
            throw new IOException("At least one payload must be provided.");
        }
        ((JsonWriter) this.f655n).endArray();
    }

    public void i(String str) throws IOException {
        ((JsonWriter) this.f655n).name("sentAt").value(D5.g.i(new Date())).name("writeKey").value(str).endObject();
    }

    public G(C3.e eVar, C3.b bVar) {
        this.l = 1;
        this.o = eVar;
        this.f655n = bVar;
    }
}
