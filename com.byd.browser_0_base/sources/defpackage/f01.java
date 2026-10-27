package defpackage;

import android.view.inputmethod.InputContentInfo;

/* JADX INFO: compiled from: InputContentInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class f01 {
    public final b a;

    /* JADX INFO: compiled from: InputContentInfoCompat.java */
    public static final class a implements b {
        public final InputContentInfo a;

        public a(Object obj) {
            this.a = (InputContentInfo) obj;
        }

        public final Object a() {
            return this.a;
        }

        public final void b() {
            this.a.requestPermission();
        }
    }

    /* JADX INFO: compiled from: InputContentInfoCompat.java */
    public interface b {
    }

    public f01(a aVar) {
        this.a = aVar;
    }
}
