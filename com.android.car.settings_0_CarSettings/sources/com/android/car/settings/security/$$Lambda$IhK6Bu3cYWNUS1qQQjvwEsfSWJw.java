package com.android.car.settings.security;

import com.android.car.settings.security.CheckLockWorker;

/* JADX INFO: renamed from: com.android.car.settings.security.-$$Lambda$IhK6Bu3cYWNUS1qQQjvwEsfSWJw, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class $$Lambda$IhK6Bu3cYWNUS1qQQjvwEsfSWJw implements CheckLockWorker.Listener {
    public final /* synthetic */ ConfirmLockPinPasswordFragment f$0;

    @Override // com.android.car.settings.security.CheckLockWorker.Listener
    public final void onCheckCompleted(boolean z, int i) {
        this.f$0.onCheckCompleted(z, i);
    }
}
