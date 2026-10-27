package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class L5 implements InterfaceC0696e5 {
    public final /* synthetic */ RecyclerView a;

    public L5(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public View a(int i) {
        return this.a.getChildAt(i);
    }

    public int b() {
        return this.a.getChildCount();
    }
}
