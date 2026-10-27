package androidx.core.view.accessibility;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f548c;
    private final b d;
    private final int e;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public a(int i, b bVar, int i2) {
        this.f548c = i;
        this.d = bVar;
        this.e = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(@NonNull View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f548c);
        this.d.G(this.e, bundle);
    }
}
