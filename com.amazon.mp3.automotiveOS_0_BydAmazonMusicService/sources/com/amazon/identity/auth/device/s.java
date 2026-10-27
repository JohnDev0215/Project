package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class s extends p.c {
    public final /* synthetic */ u c;
    public final /* synthetic */ p d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(p pVar, p.b bVar, u uVar) {
        super(bVar);
        this.d = pVar;
        this.c = uVar;
    }

    @Override // com.amazon.identity.auth.device.p.c
    public p.d a(kc kcVar) {
        p.a(this.d, kcVar, this.c);
        return p.b(this.d, kcVar, this.c) ? new p.d() : new p.d(MAPError.AccountError.SETTING_CREDENTIALS_FAILED, "An error occurred while setting the credentials. This occurred because either no AccountRegistrarAuthenticator was given, or account credentials were given instead of anonymous credentials", MAPAccountManager.RegistrationError.UNRECOGNIZED);
    }

    @Override // com.amazon.identity.auth.device.p.c
    public void a(RegisterDeviceErrorType registerDeviceErrorType) {
    }
}
