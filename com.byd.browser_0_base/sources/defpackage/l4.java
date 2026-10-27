package defpackage;

import android.widget.SearchView;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: AllPasswordsBottomSheetView.java */
/* JADX INFO: loaded from: classes.dex */
public final class l4 implements SearchView.OnQueryTextListener {
    public final /* synthetic */ Callback a;

    public l4(Callback callback) {
        this.a = callback;
    }

    @Override // android.widget.SearchView.OnQueryTextListener
    public final boolean onQueryTextChange(String str) {
        this.a.onResult(str);
        return true;
    }

    @Override // android.widget.SearchView.OnQueryTextListener
    public final boolean onQueryTextSubmit(String str) {
        return false;
    }
}
