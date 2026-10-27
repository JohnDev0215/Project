package defpackage;

import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class qx2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ SyncConsentFragmentBase f;

    public /* synthetic */ qx2(SyncConsentFragmentBase syncConsentFragmentBase, int i) {
        this.e = i;
        this.f = syncConsentFragmentBase;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0004. Please report as an issue. */
    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        SyncConsentFragmentBase syncConsentFragmentBase = this.f;
        switch (i) {
        }
        syncConsentFragmentBase.P0((List) obj);
    }
}
