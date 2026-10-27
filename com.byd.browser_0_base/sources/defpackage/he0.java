package defpackage;

import android.content.Context;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.R$dimen;

/* JADX INFO: compiled from: DropdownItemViewInfoListManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class he0 {
    public final Context a;
    public final ya1 b;
    public int c = 2;
    public int d = 0;
    public List<fe0> e = Collections.emptyList();
    public boolean f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;

    public he0(Context context, ya1 ya1Var) {
        this.a = context;
        this.b = ya1Var;
        this.g = context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_list_active_top_small_margin);
        this.h = context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_list_active_top_big_margin);
        this.i = context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_list_non_active_top_small_margin);
        this.j = context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_list_non_active_top_big_margin);
    }
}
