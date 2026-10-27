package com.android.server.telecom;

import android.telecom.PhoneAccountSuggestion;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.server.telecom.-$$Lambda$yRlpl3dkbrkzu-O0fcR2P1LnNvk, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$yRlpl3dkbrkzuO0fcR2P1LnNvk implements Function {
    public static final /* synthetic */ $$Lambda$yRlpl3dkbrkzuO0fcR2P1LnNvk INSTANCE = new $$Lambda$yRlpl3dkbrkzuO0fcR2P1LnNvk();

    private /* synthetic */ $$Lambda$yRlpl3dkbrkzuO0fcR2P1LnNvk() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((PhoneAccountSuggestion) obj).getPhoneAccountHandle();
    }
}
