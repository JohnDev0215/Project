package androidx.appcompat.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.J;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public final class a {
    public static ColorStateList a(@NonNull Context context, @ColorRes int i) {
        return androidx.core.content.a.c(context, i);
    }

    @Nullable
    public static Drawable b(@NonNull Context context, @DrawableRes int i) {
        return J.h().j(context, i);
    }
}
