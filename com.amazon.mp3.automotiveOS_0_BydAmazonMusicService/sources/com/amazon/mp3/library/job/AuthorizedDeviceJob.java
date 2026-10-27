package com.amazon.mp3.library.job;

import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes3.dex */
public class AuthorizedDeviceJob extends Job {
    @Override // com.amazon.mp3.service.job.Job
    public final int run() throws Exception {
        return DeviceAuthorizationManager.getInstance().autoAuthorize(getContext()) ? 1 : 3;
    }
}
