package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.widget.ListView;
import org.chromium.chrome.R$id;

/* JADX INFO: compiled from: AppMenu.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w6 implements View.OnLayoutChangeListener {
    public final /* synthetic */ x6 e;

    public w6(x6 x6Var) {
        this.e = x6Var;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        x6 x6Var = this.e;
        x6Var.k.removeOnLayoutChangeListener(this);
        x6Var.getClass();
        x6Var.q = new AnimatorSet();
        ListView listView = x6Var.k;
        AnimatorSet.Builder builderPlay = null;
        for (int i9 = 0; i9 < listView.getChildCount(); i9++) {
            Object tag = listView.getChildAt(i9).getTag(R$id.menu_item_enter_anim_id);
            if (tag != null) {
                if (builderPlay == null) {
                    builderPlay = x6Var.q.play((Animator) tag);
                } else {
                    builderPlay.with((Animator) tag);
                }
            }
        }
        x6Var.q.start();
    }
}
