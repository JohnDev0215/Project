package com.google.android.material.internal;

import android.graphics.Outline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes6.dex */
@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CircularBorderDrawableLollipop extends CircularBorderDrawable {
    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        copyBounds(this.rect);
        outline.setOval(this.rect);
    }
}
