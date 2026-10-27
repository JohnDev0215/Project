package defpackage;

import android.view.View;
import org.chromium.components.messages.R$dimen;

/* JADX INFO: compiled from: MessageDimens.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ug1 {
    public final View a;
    public boolean b;

    public ug1(View view) {
        this.a = view;
    }

    public final int a() {
        View view = this.a;
        if (view.isLaidOut()) {
            return view.getHeight();
        }
        if (!this.b) {
            b();
        }
        return view.getMeasuredHeight();
    }

    public final void b() {
        View view = this.a;
        view.measure(View.MeasureSpec.makeMeasureSpec(Math.min(view.getRootView().getWidth(), view.getResources().getDimensionPixelSize(R$dimen.message_max_width)), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.b = true;
    }
}
