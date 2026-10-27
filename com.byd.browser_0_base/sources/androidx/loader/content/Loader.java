package androidx.loader.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public class Loader<D> {
    public final Context a;
    public boolean b = false;
    public boolean c = false;
    public boolean d = true;
    public boolean e = false;

    public final class a extends ContentObserver {
        public a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public final boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            Loader loader = Loader.this;
            if (loader.b) {
                loader.b();
            } else {
                loader.getClass();
            }
        }
    }

    public interface b<D> {
    }

    public interface c<D> {
    }

    public Loader(Context context) {
        this.a = context.getApplicationContext();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" id=0}");
        return sb.toString();
    }

    public void unregisterListener(c<D> cVar) {
        throw new IllegalStateException("No listener register");
    }

    public void unregisterOnLoadCanceledListener(b<D> bVar) {
        throw new IllegalStateException("No listener register");
    }

    public void b() {
    }

    public void a(D d) {
    }

    public void registerOnLoadCanceledListener(b<D> bVar) {
    }
}
