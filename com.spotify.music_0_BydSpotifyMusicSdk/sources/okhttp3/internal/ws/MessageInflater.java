package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Inflater;
import p.fo;
import p.m41;

/* JADX INFO: loaded from: classes.dex */
public final class MessageInflater implements Closeable {
    public final boolean c;
    public final fo e;
    public final Inflater k;
    public final m41 l;

    public MessageInflater(boolean z) {
        this.c = z;
        fo foVar = new fo();
        this.e = foVar;
        Inflater inflater = new Inflater(true);
        this.k = inflater;
        this.l = new m41(foVar, inflater);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.l.close();
    }
}
