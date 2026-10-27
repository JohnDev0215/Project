package defpackage;

import android.graphics.Bitmap;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: compiled from: PasswordCheckIconHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yw1 {
    public final LargeIconBridge a;
    public final int b;

    /* JADX INFO: compiled from: PasswordCheckIconHelper.java */
    public static class a {
        public final String a;
        public final Bitmap b;
        public final int c;
        public final boolean d;

        public a(String str, Bitmap bitmap, int i, boolean z) {
            this.a = str;
            this.b = bitmap;
            this.c = i;
            this.d = z;
        }
    }

    public yw1(LargeIconBridge largeIconBridge, int i) {
        this.a = largeIconBridge;
        this.b = i;
    }
}
