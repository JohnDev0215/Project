package defpackage;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import defpackage.lk1;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.management.ManagementView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: ManagementPage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pb1 extends be {
    public final String k;

    public pb1(lk1.b bVar, Profile profile) {
        super(bVar);
        this.k = bVar.a().getResources().getString(R$string.management);
        ob1 ob1Var = new ob1(bVar, profile);
        ManagementView managementView = (ManagementView) LayoutInflater.from(bVar.a()).inflate(R$layout.enterprise_management, (ViewGroup) null);
        j.b(ob1Var.b, managementView, new bg(10));
        c(managementView);
    }

    @Override // defpackage.jk1
    public final String getTitle() {
        return this.k;
    }

    @Override // defpackage.jk1
    public final String k() {
        return "management";
    }
}
