package spi;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class k1<Z> implements rs<Z> {
    public jm a;

    @Override // spi.ug
    public final void b() {
    }

    @Override // spi.rs
    public void d(@Nullable Drawable drawable) {
    }

    @Override // spi.rs
    public void e(@Nullable jm jmVar) {
        this.a = jmVar;
    }

    @Override // spi.rs
    public void g(@Nullable Drawable drawable) {
    }

    @Override // spi.rs
    @Nullable
    public jm h() {
        return this.a;
    }

    @Override // spi.rs
    public void i(@Nullable Drawable drawable) {
    }

    @Override // spi.ug
    public void onStart() {
    }

    @Override // spi.ug
    public void onStop() {
    }
}
