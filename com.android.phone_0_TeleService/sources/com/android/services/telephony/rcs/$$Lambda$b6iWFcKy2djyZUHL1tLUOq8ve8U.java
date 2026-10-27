package com.android.services.telephony.rcs;

import com.android.internal.telephony.imsphone.ImsRegistrationCallbackHelper;
import com.android.services.telephony.rcs.RcsFeatureController;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: com.android.services.telephony.rcs.-$$Lambda$b6iWFcKy2djyZUHL1tLUOq8ve8U, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$b6iWFcKy2djyZUHL1tLUOq8ve8U implements RcsFeatureController.RegistrationHelperFactory {
    public static final /* synthetic */ $$Lambda$b6iWFcKy2djyZUHL1tLUOq8ve8U INSTANCE = new $$Lambda$b6iWFcKy2djyZUHL1tLUOq8ve8U();

    private /* synthetic */ $$Lambda$b6iWFcKy2djyZUHL1tLUOq8ve8U() {
    }

    @Override // com.android.services.telephony.rcs.RcsFeatureController.RegistrationHelperFactory
    public final ImsRegistrationCallbackHelper create(ImsRegistrationCallbackHelper.ImsRegistrationUpdate imsRegistrationUpdate, Executor executor) {
        return new ImsRegistrationCallbackHelper(imsRegistrationUpdate, executor);
    }
}
