package androidx.appcompat.app;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.app.ActionBar;

/* JADX INFO: loaded from: classes.dex */
class NavItemSelectedListener implements AdapterView.OnItemSelectedListener {
    public final ActionBar.OnNavigationListener e;

    public NavItemSelectedListener(ActionBar.OnNavigationListener onNavigationListener) {
        this.e = onNavigationListener;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ActionBar.OnNavigationListener onNavigationListener = this.e;
        if (onNavigationListener != null) {
            onNavigationListener.a();
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }
}
