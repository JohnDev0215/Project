package spi;

import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class gj implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ NavController b;
    public final /* synthetic */ AppBarConfiguration c;

    public /* synthetic */ gj(NavController navController, AppBarConfiguration appBarConfiguration, int i) {
        this.a = i;
        this.b = navController;
        this.c = appBarConfiguration;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                NavigationUI.m41setupWithNavController$lambda2(this.b, this.c, view);
                break;
            default:
                NavigationUI.m40setupWithNavController$lambda1(this.b, this.c, view);
                break;
        }
    }
}
