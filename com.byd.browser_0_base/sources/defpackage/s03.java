package defpackage;

import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: TabGroupUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s03 extends j23 {
    public s03(a23 a23Var) {
        super(a23Var);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (!tabImpl.isIncognito() && navigationHandle.g) {
            if (navigationHandle.k || (navigationHandle.d & 255) == 5) {
                r03.b("IPH_TabGroupsQuicklyComparePages", tabImpl.getView(), null);
                r03.a.destroy();
            }
        }
    }
}
