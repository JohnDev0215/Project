package defpackage;

import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.kj;
import org.chromium.base.b;
import org.chromium.chrome.R$dimen;
import org.chromium.components.messages.MessageContainer;

/* JADX INFO: compiled from: MessageContainerCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sg1 implements kj.a {
    public MessageContainer e;
    public fj f;
    public final b<tg1> g = new b<>();

    public sg1(MessageContainer messageContainer, fj fjVar) {
        this.e = messageContainer;
        this.f = fjVar;
        fjVar.o(this);
    }

    public final int a() {
        if (this.f.s == 0) {
            return 0;
        }
        return (this.f.s - this.e.getResources().getDimensionPixelOffset(R$dimen.message_bubble_inset)) - this.e.getMessageShadowTopMargin();
    }

    public final void b() {
        if (this.e.getVisibility() != 0) {
            return;
        }
        CoordinatorLayout.e eVar = (CoordinatorLayout.e) this.e.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).topMargin = a();
        this.e.setLayoutParams(eVar);
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        b();
    }

    @Override // kj.a
    public final void n() {
        b();
    }
}
