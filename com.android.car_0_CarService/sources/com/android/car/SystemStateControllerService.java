package com.android.car;

import android.content.Context;
import android.content.res.Resources;
import com.android.car.audio.CarAudioService;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public class SystemStateControllerService implements CarServiceBase {
    private final CarAudioService mCarAudioService;
    private final ICarImpl mICarImpl;
    private final boolean mLockWhenMuting;

    public SystemStateControllerService(Context context, CarAudioService carAudioService, ICarImpl carImpl) {
        this.mCarAudioService = carAudioService;
        this.mICarImpl = carImpl;
        Resources res = context.getResources();
        this.mLockWhenMuting = res.getBoolean(R.bool.displayOffMuteLockAllAudio);
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
    }
}
