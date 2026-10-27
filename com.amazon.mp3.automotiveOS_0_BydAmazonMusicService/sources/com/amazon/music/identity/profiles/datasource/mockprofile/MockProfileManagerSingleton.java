package com.amazon.music.identity.profiles.datasource.mockprofile;

import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;

/* JADX INFO: compiled from: MockProfileManagerSingleton.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManagerSingleton;", "", "()V", "initialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "mockProfileManager", "Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManager;", "get", "initialize", "", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MockProfileManagerSingleton {
    public static final MockProfileManagerSingleton INSTANCE = new MockProfileManagerSingleton();
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static MockProfileManager mockProfileManager;

    private MockProfileManagerSingleton() {
    }

    public final synchronized void initialize() {
        if (!initialized.getAndSet(true)) {
            mockProfileManager = new MockProfileManager();
        }
    }

    public final synchronized MockProfileManager get() {
        if (!initialized.get()) {
            initialize();
        }
        return mockProfileManager;
    }
}
