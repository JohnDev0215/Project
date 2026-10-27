package k0;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: compiled from: InputContentInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f7192a;

    /* JADX INFO: compiled from: InputContentInfoCompat.java */
    private static final class a implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final InputContentInfo f7193a;

        a(Object obj) {
            this.f7193a = (InputContentInfo) obj;
        }

        @Override // k0.c.b
        public void a() {
            this.f7193a.requestPermission();
        }

        @Override // k0.c.b
        public Uri b() {
            return this.f7193a.getLinkUri();
        }

        @Override // k0.c.b
        public ClipDescription c() {
            return this.f7193a.getDescription();
        }

        @Override // k0.c.b
        public Object d() {
            return this.f7193a;
        }

        @Override // k0.c.b
        public Uri e() {
            return this.f7193a.getContentUri();
        }
    }

    /* JADX INFO: compiled from: InputContentInfoCompat.java */
    private interface b {
        void a();

        Uri b();

        ClipDescription c();

        Object d();

        Uri e();
    }

    private c(b bVar) {
        this.f7192a = bVar;
    }

    public static c f(Object obj) {
        if (obj == null) {
            return null;
        }
        return new c(new a(obj));
    }

    public Uri a() {
        return this.f7192a.e();
    }

    public ClipDescription b() {
        return this.f7192a.c();
    }

    public Uri c() {
        return this.f7192a.b();
    }

    public void d() {
        this.f7192a.a();
    }

    public Object e() {
        return this.f7192a.d();
    }
}
