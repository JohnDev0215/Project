package b.b.g;

import androidx.fragment.app.FragmentActivity;
import com.byd.systemsettings.system.PrivacyPolicyDialog;

/* JADX INFO: compiled from: DxJumpService.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements b.b.a.e.b {
    @Override // b.b.a.e.b
    public void a(FragmentActivity fragmentActivity) {
        PrivacyPolicyDialog.newInstance(1).show(fragmentActivity.getSupportFragmentManager(), "PrivacyPolicyDialog");
    }

    @Override // b.b.a.e.b
    public void b(FragmentActivity fragmentActivity) {
        PrivacyPolicyDialog.newInstance(2).show(fragmentActivity.getSupportFragmentManager(), "PrivacyPolicyDialog");
    }
}
