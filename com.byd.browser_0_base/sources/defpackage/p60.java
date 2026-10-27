package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: DateOrderedListView.java */
/* JADX INFO: loaded from: classes.dex */
public final class p60 extends RecyclerView {
    public int P0;
    public final /* synthetic */ r60 Q0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p60(r60 r60Var, Context context) {
        super(context);
        this.Q0 = r60Var;
        this.P0 = 0;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        r60 r60Var = this.Q0;
        r60Var.k.c();
        int i = configuration.orientation;
        if (i == this.P0) {
            return;
        }
        this.P0 = i;
        r60Var.i.N();
    }
}
