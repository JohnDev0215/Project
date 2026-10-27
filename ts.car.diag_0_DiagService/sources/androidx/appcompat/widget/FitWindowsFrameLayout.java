package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.E;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class FitWindowsFrameLayout extends FrameLayout implements E {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private E.a f273c;

    public FitWindowsFrameLayout(@NonNull Context context) {
        super(context);
    }

    @Override // androidx.appcompat.widget.E
    public void a(E.a aVar) {
        this.f273c = aVar;
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        E.a aVar = this.f273c;
        if (aVar != null) {
            aVar.a(rect);
        }
        return super.fitSystemWindows(rect);
    }

    public FitWindowsFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
