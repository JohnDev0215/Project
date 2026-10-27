package com.byd.systemsettings.security;

import android.content.Context;
import android.security.IKeyChainService;
import android.security.KeyChain;
import b.b.g.m.d.a;
import b.b.g.p.d;
import b.b.m.e.g;
import b.b.m.g.b;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CredentialListPresenter extends g<CredentialListPager> {
    private static final String TAG = "CredentialListPresenter";
    private Context mContext;
    private d settings;

    public void getInstalledBydCert(final int i, final int i2) {
        b.a(new Runnable() { // from class: com.byd.systemsettings.security.CredentialListPresenter.1
            @Override // java.lang.Runnable
            public void run() {
                Map<String, List<a>> mapS = CredentialListPresenter.this.settings.s(CredentialListPresenter.this.mContext.getApplicationContext());
                if (CredentialListPresenter.this.getV() != null) {
                    ((CredentialListPager) CredentialListPresenter.this.getV()).sendMessage(i, i2, mapS);
                }
            }
        });
    }

    public void init(Context context) {
        this.mContext = context;
        this.settings = d.g.a();
    }

    public void setUserSelectable(final a aVar, final boolean z) {
        b.a(new Runnable() { // from class: com.byd.systemsettings.security.CredentialListPresenter.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    KeyChain.KeyChainConnection keyChainConnectionJ = d.g.a().J();
                    if (keyChainConnectionJ != null) {
                        IKeyChainService service = keyChainConnectionJ.getService();
                        if (z) {
                            service.installCaCertificate(aVar.c().getEncoded());
                        } else {
                            service.deleteCaCertificate(aVar.b());
                        }
                        b.b.m.c.a.a(CredentialListPresenter.TAG, "setUserSelectable:" + z);
                    }
                } catch (Exception e2) {
                    b.b.m.c.a.a(CredentialListPresenter.TAG, "setUserSelectable Exception:" + z + ",Exception:" + e2);
                }
            }
        });
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
    }
}
