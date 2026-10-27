package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class h02 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Activity f;

    public /* synthetic */ h02(Activity activity, int i) {
        this.e = i;
        this.f = activity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Activity activity = this.f;
        switch (i) {
            case 0:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.carsettings.ui.activity.AppDetailActivity"));
                intent.setAction("com.byd.carsettings.ui.activity.AppDetailActivity");
                intent.putExtra("app_package", activity.getPackageName());
                intent.putExtra("jump_to", "jump_permission");
                activity.startActivity(intent);
                break;
            default:
                ((hu0) hu0.a()).c(activity, activity.getString(R$string.help_context_incognito_learn_more), null, Profile.d().f(true));
                break;
        }
    }
}
