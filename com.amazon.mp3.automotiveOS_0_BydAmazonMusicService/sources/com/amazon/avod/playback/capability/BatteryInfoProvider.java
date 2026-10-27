package com.amazon.avod.playback.capability;

import android.content.Context;
import com.amazon.avod.battery.BatteryInfo;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class BatteryInfoProvider implements Provider<BatteryInfo> {
    private final Context mContext;

    public BatteryInfoProvider(@Nonnull Context context) {
        this.mContext = (Context) Preconditions.checkNotNull(context);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    @Nonnull
    public BatteryInfo get() {
        return BatteryInfo.getBatteryInfo(this.mContext);
    }
}
