package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import defpackage.fh1;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.components.messages.MessageContainer;
import org.chromium.components.messages.b;

/* JADX INFO: compiled from: MessageAnimationCoordinator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hg1 implements cx2 {
    public fh1.a a;
    public fh1.a c;
    public eh1 d;
    public AnimatorSet f;
    public AnimatorSet g;
    public final MessageContainer h;
    public final Callback<Animator> i;
    public List<fh1.a> b = Arrays.asList(null, null);
    public AnimatorSet e = new AnimatorSet();

    /* JADX INFO: compiled from: MessageAnimationCoordinator.java */
    public class a extends vl {
        public final Runnable f;

        public a(Runnable runnable) {
            this.f = runnable;
        }

        @Override // defpackage.vl
        public final void b() {
            this.f.run();
        }
    }

    public hg1(MessageContainer messageContainer, jb1 jb1Var) {
        this.h = messageContainer;
        this.i = jb1Var;
    }

    public static void b(int i, fh1.a aVar) {
        oj2.U(((b) aVar.d).a(), 8, "Android.Messages.Stacking.".concat(i == 0 ? "InsertAtFront" : i == 1 ? "InsertAtBack" : i == 2 ? "PushToFront" : i == 3 ? "PushToBack" : i == 4 ? "RemoveFront" : "RemoveBack"));
    }

    public final void a(AnimatorSet animatorSet) {
        if (animatorSet == null) {
            ((jq) this.d).a();
            return;
        }
        eh1 eh1Var = this.d;
        Objects.requireNonNull(eh1Var);
        animatorSet.addListener(new a(new ve3(14, eh1Var)));
        this.i.onResult(animatorSet);
    }
}
