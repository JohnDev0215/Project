package defpackage;

import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gd implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ BaseCustomTabActivity f;

    public /* synthetic */ gd(BaseCustomTabActivity baseCustomTabActivity, int i) {
        this.e = i;
        this.f = baseCustomTabActivity;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        BaseCustomTabActivity baseCustomTabActivity = this.f;
        switch (i) {
            case 0:
                return baseCustomTabActivity.f1;
            case 1:
                baseCustomTabActivity.getClass();
                return Boolean.FALSE;
            default:
                return baseCustomTabActivity.b1;
        }
    }
}
