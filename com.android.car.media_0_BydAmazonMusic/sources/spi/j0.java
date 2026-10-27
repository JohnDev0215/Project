package spi;

import android.view.View;
import androidx.annotation.NonNull;
import com.android.car.media.common.MediaItemMetadata;
import java.util.Objects;

/* JADX INFO: compiled from: BrowseViewData.java */
/* JADX INFO: loaded from: classes.dex */
public final class j0 {
    public final MediaItemMetadata a;

    @NonNull
    public final a0 b;
    public final CharSequence c;
    public final View.OnClickListener d;
    public final boolean e;

    public j0(MediaItemMetadata mediaItemMetadata, @NonNull a0 a0Var, View.OnClickListener onClickListener, boolean z, boolean z2) {
        this.a = mediaItemMetadata;
        this.b = a0Var;
        this.c = null;
        this.d = onClickListener;
        this.e = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || j0.class != obj.getClass()) {
            return false;
        }
        j0 j0Var = (j0) obj;
        return Objects.equals(this.a, j0Var.a) && this.b == j0Var.b;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }

    public j0(@NonNull CharSequence charSequence, @NonNull a0 a0Var, View.OnClickListener onClickListener, boolean z, boolean z2) {
        this.c = charSequence;
        this.b = a0Var;
        this.a = null;
        this.d = onClickListener;
        this.e = z;
    }

    public j0(@NonNull a0 a0Var, View.OnClickListener onClickListener, boolean z, boolean z2) {
        this.c = null;
        this.a = null;
        this.b = a0Var;
        this.d = onClickListener;
        this.e = z;
    }
}
