package defpackage;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import org.chromium.components.browser_ui.settings.R$drawable;
import org.chromium.components.browser_ui.settings.R$id;

/* JADX INFO: compiled from: SearchUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vg2 {

    /* JADX INFO: compiled from: SearchUtils.java */
    public interface a {
    }

    public static void a(MenuItem menuItem, String str, Activity activity) {
        View viewJ0;
        SearchView searchView = (SearchView) menuItem.getActionView();
        ((ImageView) searchView.findViewById(R$id.search_close_btn)).setVisibility((str == null || str.equals("")) ? 8 : 0);
        if (activity != null) {
            if (str != null) {
                searchView.setBackground(AppCompatResources.a(activity, R$drawable.item_bg_search));
            } else {
                searchView.setBackground(null);
            }
            int i = str != null ? 8 : 0;
            Toolbar toolbar = (Toolbar) activity.findViewById(R$id.action_bar);
            if (toolbar == null || (viewJ0 = n3.j0(toolbar)) == null) {
                return;
            }
            viewJ0.setVisibility(i);
        }
    }
}
