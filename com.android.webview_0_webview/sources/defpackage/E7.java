package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class E7 extends Drawable.ConstantState {
    public int a;
    public U7 b;
    public AnimatorSet c;
    public ArrayList d;
    public C0971j1 e;

    public E7(E7 e7, Drawable.Callback callback, Resources resources) {
        if (e7 != null) {
            this.a = e7.a;
            U7 u7 = e7.b;
            if (u7 != null) {
                Drawable.ConstantState constantState = u7.getConstantState();
                if (resources != null) {
                    this.b = (U7) constantState.newDrawable(resources);
                } else {
                    this.b = (U7) constantState.newDrawable();
                }
                U7 u72 = this.b;
                u72.mutate();
                this.b = u72;
                u72.setCallback(callback);
                this.b.setBounds(e7.b.getBounds());
                this.b.x = false;
            }
            ArrayList arrayList = e7.d;
            if (arrayList != null) {
                int size = arrayList.size();
                this.d = new ArrayList(size);
                this.e = new C0971j1(size);
                for (int i = 0; i < size; i++) {
                    Animator animator = (Animator) e7.d.get(i);
                    Animator animatorClone = animator.clone();
                    String str = (String) e7.e.get(animator);
                    animatorClone.setTarget(this.b.t.b.p.get(str));
                    this.d.add(animatorClone);
                    this.e.put(animatorClone, str);
                }
                if (this.c == null) {
                    this.c = new AnimatorSet();
                }
                this.c.playTogether(this.d);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.a;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }
}
