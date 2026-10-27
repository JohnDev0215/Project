package com.byd.systemsettings.btsetting;

import com.byd.systemsettings.btsetting.view.pager.BtConnectedPager;
import p2.g;
import x3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtConnectedPresenter extends f<BtConnectedPager> {
    private static final String TAG = "BtConnectedPresenter";

    public void syncContactsState(final int i8) {
        a.a(TAG, "---syncContactsState---");
        g.a().g(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtConnectedPresenter.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.a(BtConnectedPresenter.TAG, "---syncContactsState---before getSyncContactsState--");
                    int iQ = w2.a.p().q();
                    a.a(BtConnectedPresenter.TAG, "---syncContactsState---after getSyncContactsState--syncContactsState: " + iQ);
                    if (BtConnectedPresenter.this.getV() != null) {
                        ((BtConnectedPager) BtConnectedPresenter.this.getV()).sendUIMessage(i8, Integer.valueOf(iQ));
                    }
                } catch (Exception e8) {
                    a.b(BtConnectedPresenter.TAG, "---syncContactsState---e: " + e8.toString());
                }
            }
        });
    }

    @Override // z3.f
    public void unbindV() {
        super.unbindV();
    }
}
