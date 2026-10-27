package com.android.services.telephony.rcs;

import android.content.Context;
import com.android.ims.FeatureConnector;
import com.android.services.telephony.rcs.RcsFeatureController;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: com.android.services.telephony.rcs.-$$Lambda$nxDW_ESATl7VlrjUJWNz_PbiGKw, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$nxDW_ESATl7VlrjUJWNz_PbiGKw implements RcsFeatureController.FeatureConnectorFactory {
    public static final /* synthetic */ $$Lambda$nxDW_ESATl7VlrjUJWNz_PbiGKw INSTANCE = new $$Lambda$nxDW_ESATl7VlrjUJWNz_PbiGKw();

    private /* synthetic */ $$Lambda$nxDW_ESATl7VlrjUJWNz_PbiGKw() {
    }

    @Override // com.android.services.telephony.rcs.RcsFeatureController.FeatureConnectorFactory
    public final FeatureConnector create(Context context, int i, FeatureConnector.Listener listener, Executor executor, String str) {
        return new FeatureConnector(context, i, listener, executor, str);
    }
}
