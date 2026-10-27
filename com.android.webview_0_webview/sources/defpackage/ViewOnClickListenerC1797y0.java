package defpackage;

import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import java.util.Objects;

/* JADX INFO: renamed from: y0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnClickListenerC1797y0 implements View.OnClickListener {
    public final /* synthetic */ SearchView s;

    public ViewOnClickListenerC1797y0(SearchView searchView) {
        this.s = searchView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        SearchView searchView = this.s;
        if (view == searchView.L) {
            searchView.u(false);
            searchView.H.requestFocus();
            searchView.H.a(true);
            return;
        }
        if (view == searchView.N) {
            if (!TextUtils.isEmpty(searchView.H.getText())) {
                searchView.H.setText("");
                searchView.H.requestFocus();
                searchView.H.a(true);
                return;
            } else {
                if (searchView.d0) {
                    searchView.clearFocus();
                    searchView.u(true);
                    return;
                }
                return;
            }
        }
        if (view == searchView.M) {
            searchView.q();
        } else if (view == searchView.O) {
            Objects.requireNonNull(searchView);
        } else if (view == searchView.H) {
            searchView.n();
        }
    }
}
