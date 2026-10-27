package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.E;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class FitWindowsLinearLayout extends LinearLayout implements E {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private E.a f274c;

    public FitWindowsLinearLayout(@NonNull Context context) {
        super(context);
    }

    @Override // androidx.appcompat.widget.E
    public void a(E.a aVar) {
        this.f274c = aVar;
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        E.a aVar = this.f274c;
        if (aVar != null) {
            aVar.a(rect);
        }
        return super.fitSystemWindows(rect);
    }

    public FitWindowsLinearLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
