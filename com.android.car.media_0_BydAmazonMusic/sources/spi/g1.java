package spi;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.android.car.ui.recyclerview.a;

/* JADX INFO: compiled from: CarUiImeSearchListItem.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g1 extends com.android.car.ui.recyclerview.a {
    public g1(a.EnumC0011a enumC0011a) {
        super(enumC0011a);
    }

    @Override // com.android.car.ui.recyclerview.a
    public final void b(@Nullable Drawable drawable) {
        if (!(drawable instanceof BitmapDrawable) && drawable != null) {
            throw new RuntimeException("icon should be of type BitmapDrawable");
        }
        this.a = drawable;
    }
}
