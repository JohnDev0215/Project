package org.chromium.chrome.browser.app.feed.feedmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import defpackage.dl0;
import defpackage.o61;
import defpackage.ya1;
import defpackage.zf;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.SnackbarActivity;
import org.chromium.ui.modelutil.f;

/* JADX INFO: loaded from: classes.dex */
public class FeedManagementActivity extends SnackbarActivity implements dl0.b, dl0.a {
    @Override // org.chromium.chrome.browser.SnackbarActivity, org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int intExtra = getIntent().getIntExtra("feed_management_initiating_stream_type_extra", 0);
        ya1 ya1Var = new ya1();
        f fVar = new f(ya1Var);
        fVar.a(0, new o61(R$layout.feed_management_list_item), new zf(5));
        View viewInflate = LayoutInflater.from(this).inflate(R$layout.feed_management_activity, (ViewGroup) null);
        ((ListView) viewInflate.findViewById(R$id.feed_management_menu)).setAdapter((ListAdapter) fVar);
        new dl0(this, ya1Var, this, this, intExtra);
        setContentView(viewInflate);
        C1((Toolbar) findViewById(R$id.action_bar));
        B1().t(true);
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}
