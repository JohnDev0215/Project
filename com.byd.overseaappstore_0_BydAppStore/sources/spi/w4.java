package spi;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class w4<T> implements rs<T> {
    public final int a;
    public final int b;

    @Nullable
    public jm c;

    public w4() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public w4(int i, int i2) {
        if (!tu.j(i, i2)) {
            throw new IllegalArgumentException(d.c("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: ", i, " and height: ", i2));
        }
        this.a = i;
        this.b = i2;
    }

    @Override // spi.rs
    public final void a(@NonNull tp tpVar) {
        tpVar.b(this.a, this.b);
    }

    @Override // spi.ug
    public final void b() {
    }

    @Override // spi.rs
    public final void d(@Nullable Drawable drawable) {
    }

    @Override // spi.rs
    public final void e(@Nullable jm jmVar) {
        this.c = jmVar;
    }

    @Override // spi.rs
    public final void f(@NonNull tp tpVar) {
    }

    @Override // spi.rs
    public final void g(@Nullable Drawable drawable) {
    }

    @Override // spi.rs
    @Nullable
    public final jm h() {
        return this.c;
    }

    @Override // spi.ug
    public final void onStart() {
    }

    @Override // spi.ug
    public final void onStop() {
    }
}
