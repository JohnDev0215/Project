package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: Animatable2Compat.java */
/* JADX INFO: loaded from: classes.dex */
public interface l5 extends Animatable {
    void a(a aVar);

    /* JADX INFO: compiled from: Animatable2Compat.java */
    public static abstract class a {
        public C0063a a;

        /* JADX INFO: renamed from: l5$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Animatable2Compat.java */
        public class C0063a extends Animatable2.AnimationCallback {
            public C0063a() {
            }

            @Override // android.graphics.drawable.Animatable2.AnimationCallback
            public final void onAnimationEnd(Drawable drawable) {
                a.this.b(drawable);
            }

            @Override // android.graphics.drawable.Animatable2.AnimationCallback
            public final void onAnimationStart(Drawable drawable) {
                a.this.getClass();
            }
        }

        public final Animatable2.AnimationCallback a() {
            if (this.a == null) {
                this.a = new C0063a();
            }
            return this.a;
        }

        public void b(Drawable drawable) {
        }
    }
}
