package spi;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import androidx.annotation.StyleRes;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class u4 extends Dialog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u4(@NotNull Context context, @StyleRes int i) {
        super(context, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchTouchEvent(@NotNull MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        ct.a(e.c("dispatchTouchEvent CreatedDialog MotionEvent action : ", ev.getAction()), new Object[0]);
        return super.dispatchTouchEvent(ev);
    }
}
