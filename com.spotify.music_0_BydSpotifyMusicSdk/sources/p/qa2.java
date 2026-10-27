package p;

import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class qa2 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ SearchView e;

    public /* synthetic */ qa2(SearchView searchView, int i) {
        this.c = i;
        this.e = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        SearchView searchView = this.e;
        switch (i) {
            case 0:
                searchView.u();
                break;
            default:
                s40 s40Var = searchView.W;
                if (s40Var instanceof dh2) {
                    s40Var.b(null);
                }
                break;
        }
    }
}
