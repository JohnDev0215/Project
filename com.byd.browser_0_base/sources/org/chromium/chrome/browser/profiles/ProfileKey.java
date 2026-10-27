package org.chromium.chrome.browser.profiles;

import J.N;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.embedder_support.simple_factory_key.SimpleFactoryKeyHandle;

/* JADX INFO: loaded from: classes2.dex */
public class ProfileKey implements SimpleFactoryKeyHandle {
    public final boolean a;
    public long b;

    public ProfileKey(long j) {
        this.b = j;
        this.a = N.MdejbNQu(j);
    }

    public static ProfileKey a() {
        return (ProfileKey) N.MZXDYv9T();
    }

    @CalledByNative
    public static ProfileKey create(long j) {
        return new ProfileKey(j);
    }

    @CalledByNative
    public final long getNativePointer() {
        return this.b;
    }

    @Override // org.chromium.components.embedder_support.simple_factory_key.SimpleFactoryKeyHandle
    public final long getNativeSimpleFactoryKeyPointer() {
        return N.M1kMd8V5(this.b);
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.b = 0L;
    }
}
