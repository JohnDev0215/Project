package spi;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.main.R$color;
import com.android.car.ui.toolbar.g;
import java.util.Objects;
import java.util.function.Consumer;
import spi.v4;
import spi.v4.a;

/* JADX INFO: compiled from: TabBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qa<T extends v4.a> extends v4<T> {
    public final MediaItemMetadata h;
    public final Context i;
    public Consumer<qa<T>> j;
    public com.android.car.ui.toolbar.g k;

    public qa(@NonNull Context context, @NonNull Size size, @NonNull MediaItemMetadata mediaItemMetadata, @NonNull Consumer<qa<T>> consumer) {
        super(v4.b.NONE, size);
        this.i = context;
        this.h = mediaItemMetadata;
        CharSequence charSequenceF = mediaItemMetadata.f();
        g.b bVarA = com.android.car.ui.toolbar.g.a();
        bVarA.a = charSequenceF == null ? null : charSequenceF.toString();
        bVarA.c = new r8(this, consumer, 1);
        this.k = bVarA.a();
    }

    @Override // spi.v4
    public final void c(@Nullable Drawable drawable) {
        boolean z;
        if (h2.a(this.i).b() && (drawable instanceof BitmapDrawable)) {
            drawable.setColorFilter(new PorterDuffColorFilter(this.i.getColor(R$color.improper_image_refs_tint_color), PorterDuff.Mode.SRC_ATOP));
            z = false;
        } else {
            z = true;
        }
        com.android.car.ui.toolbar.g gVar = this.k;
        Objects.requireNonNull(gVar);
        g.b bVar = new g.b();
        bVar.b = drawable;
        bVar.d = z;
        this.k = bVar.a();
        Consumer<qa<T>> consumer = this.j;
        if (consumer != null) {
            consumer.accept(this);
        }
    }
}
