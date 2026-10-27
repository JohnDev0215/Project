package com.amazon.mp3.bottomnavbar;

import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class BottomNavigationBarContainer$$ExternalSyntheticLambda2 implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        UpsellUtil.emitPersistentUpgradeClickEventMetric();
    }
}
