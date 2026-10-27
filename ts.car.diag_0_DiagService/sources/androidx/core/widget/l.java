package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public interface l {
    @Nullable
    PorterDuff.Mode b();

    void d(@Nullable PorterDuff.Mode mode);

    @Nullable
    ColorStateList e();

    void j(@Nullable ColorStateList colorStateList);
}
