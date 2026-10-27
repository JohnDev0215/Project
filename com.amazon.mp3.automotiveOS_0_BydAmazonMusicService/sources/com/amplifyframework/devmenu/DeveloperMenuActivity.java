package com.amplifyframework.devmenu;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.amplifyframework.core.R;
import com.amplifyframework.devmenu.DeveloperMenu;

/* JADX INFO: loaded from: classes5.dex */
public final class DeveloperMenuActivity extends FragmentActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dev_menu);
        findViewById(R.id.dev_layout).setFocusable(true);
        NavController navControllerFindNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController((Toolbar) findViewById(R.id.toolbar), navControllerFindNavController, new AppBarConfiguration.Builder(navControllerFindNavController.getGraph()).build());
        DeveloperMenu.singletonInstance(getApplicationContext()).setOnHideAction(new DeveloperMenu.HideAction() { // from class: com.amplifyframework.devmenu.DeveloperMenuActivity$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.devmenu.DeveloperMenu.HideAction
            public final void hideDeveloperMenu() {
                this.f$0.finish();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        DeveloperMenu.singletonInstance(getApplicationContext()).setVisible(true);
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        DeveloperMenu.singletonInstance(getApplicationContext()).setVisible(false);
        super.onStop();
    }
}
