package defpackage;

import android.view.View;
import androidx.appcompat.widget.SearchView;
import java.util.Objects;

/* JADX INFO: renamed from: w0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnFocusChangeListenerC1687w0 implements View.OnFocusChangeListener {
    public final /* synthetic */ SearchView a;

    public ViewOnFocusChangeListenerC1687w0(SearchView searchView) {
        this.a = searchView;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        Objects.requireNonNull(this.a);
    }
}
