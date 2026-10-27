package defpackage;

import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import java.util.Objects;

/* JADX INFO: renamed from: z0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnKeyListenerC1852z0 implements View.OnKeyListener {
    public final /* synthetic */ SearchView a;

    public ViewOnKeyListenerC1852z0(SearchView searchView) {
        this.a = searchView;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Objects.requireNonNull(this.a);
        return false;
    }
}
