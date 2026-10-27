package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import defpackage.lx;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;
import org.chromium.chrome.browser.ui.signin.f;
import org.chromium.chrome.browser.ui.signin.h;
import org.chromium.chrome.browser.ui.signin.k;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.base.CoreAccountId;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ux2 implements Callback {
    public final /* synthetic */ SyncConsentFragmentBase e;
    public final /* synthetic */ View f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ ux2(SyncConsentFragmentBase syncConsentFragmentBase, View view, boolean z) {
        this.e = syncConsentFragmentBase;
        this.f = view;
        this.g = z;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        AccountInfo accountInfo = (AccountInfo) obj;
        SyncConsentFragmentBase syncConsentFragmentBase = this.e;
        if (accountInfo == null) {
            syncConsentFragmentBase.h0 = false;
            syncConsentFragmentBase.Z.b().g(new px2(syncConsentFragmentBase, 2));
            return;
        }
        lx lxVar = syncConsentFragmentBase.d0;
        CoreAccountId id = accountInfo.getId();
        TextView textView = (TextView) this.f;
        View[] viewArr = new View[1];
        View view = syncConsentFragmentBase.c0;
        if (view == null) {
            view = syncConsentFragmentBase.b0;
        }
        viewArr[0] = view;
        int i = ((lx.a) lxVar.b.get(textView)).a;
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList();
        lx.a(viewArr[0], arrayList2);
        for (View view2 : arrayList2) {
            if (view2 instanceof TextView) {
                int i2 = ((lx.a) lxVar.b.get((TextView) view2)).a;
                if (i2 != 0) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
        }
        Object obj2 = ThreadUtils.a;
        if (a8.j == null) {
            a8.j = new a8();
        }
        a8 a8Var = a8.j;
        a8Var.getClass();
        int[] iArr = new int[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        N.MswahTi8(a8Var, Profile.d(), id, 0, iArr, i);
        if (!(syncConsentFragmentBase.e >= 7)) {
            syncConsentFragmentBase.h0 = false;
            return;
        }
        Context contextX0 = syncConsentFragmentBase.x0();
        syncConsentFragmentBase.J();
        syncConsentFragmentBase.p0 = new f(new h(contextX0, syncConsentFragmentBase.o0), oj2.k(Profile.d()).c("google.services.last_username"), syncConsentFragmentBase.f0, new k(syncConsentFragmentBase, this.g));
    }
}
