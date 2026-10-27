package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.widget.Button;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;

/* JADX INFO: compiled from: TabSelectionEditorMenuItem.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k33 {
    public final Context a;
    public final xa1 b;
    public Button c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public ColorStateList i;
    public Runnable j;
    public Callback<List<Integer>> k;

    public k33(Context context, xa1 xa1Var) {
        this.a = context;
        this.b = xa1Var;
    }

    public final void a(ColorStateList colorStateList) {
        this.b.b.p(e33.n, bz.b(this.a, R$color.default_icon_color_secondary_tint_list));
        this.i = colorStateList;
        Button button = this.c;
        if (button == null || !this.h) {
            return;
        }
        button.setCompoundDrawableTintList(colorStateList);
    }
}
