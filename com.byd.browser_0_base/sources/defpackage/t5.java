package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import defpackage.l5;
import java.util.Objects;

/* JADX INFO: compiled from: AnimationLooper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class t5 {
    public final Animatable b;
    public boolean d;
    public final Handler a = new Handler();
    public final a c = new a();

    /* JADX INFO: compiled from: AnimationLooper.java */
    public class a extends l5.a {
        public a() {
        }

        @Override // l5.a
        public final void b(Drawable drawable) {
            t5 t5Var = t5.this;
            Handler handler = t5Var.a;
            Animatable animatable = t5Var.b;
            Objects.requireNonNull(animatable);
            handler.post(new i22(11, animatable));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public t5(Drawable drawable) {
        this.b = (Animatable) drawable;
    }
}
