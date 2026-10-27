package com.amazon.music.nautilus;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class UpdateService {
    private final UpdateServiceRunnableFactory runnableFactory;

    UpdateService(UpdateServiceRunnableFactory updateServiceRunnableFactory) {
        this.runnableFactory = (UpdateServiceRunnableFactory) Validate.notNull(updateServiceRunnableFactory);
    }

    public synchronized void update() {
        this.runnableFactory.buildUpdateServiceRunnable().run();
    }
}
