package defpackage;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import org.chromium.chrome.browser.history.HistoryActivity;
import org.chromium.chrome.browser.settings.SettingsActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bp0 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Fragment f;

    public /* synthetic */ bp0(int i, Fragment fragment) {
        this.e = i;
        this.f = fragment;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Fragment fragment = this.f;
        switch (i) {
            case 0:
                eq0 eq0Var = fragment.T;
                eq0Var.i.b(fragment.h);
                fragment.h = null;
                break;
            default:
                SettingsActivity settingsActivity = SettingsActivity.P;
                FragmentActivity fragmentActivityD = fragment.D();
                Intent intent = new Intent();
                intent.setClass(fragmentActivityD, HistoryActivity.class);
                intent.putExtra("org.chromium.chrome.browser.incognito_mode", false);
                fragmentActivityD.startActivity(intent);
                break;
        }
    }
}
