package spi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class pd extends qm {
    public pd(@NonNull com.bumptech.glide.a aVar, @NonNull sg sgVar, @NonNull sm smVar, @NonNull Context context) {
        super(aVar, sgVar, smVar, context);
    }

    @Override // spi.qm
    @NonNull
    @CheckResult
    public final nm j(@NonNull Class cls) {
        return new od(this.a, this, cls, this.b);
    }

    @Override // spi.qm
    @NonNull
    @CheckResult
    public final nm k() {
        return (od) j(Bitmap.class).a(qm.k);
    }

    @Override // spi.qm
    @NonNull
    @CheckResult
    public final nm l() {
        return (od) super.l();
    }

    @Override // spi.qm
    @NonNull
    @CheckResult
    public final nm n(@Nullable Drawable drawable) {
        return (od) l().H(drawable);
    }

    @Override // spi.qm
    @NonNull
    @CheckResult
    public final nm o(@Nullable String str) {
        return (od) super.o(str);
    }

    @Override // spi.qm
    public final void r(@NonNull tm tmVar) {
        if (!(tmVar instanceof nd)) {
            tmVar = new nd().B(tmVar);
        }
        super.r(tmVar);
    }
}
