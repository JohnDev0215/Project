package com.amazon.music.media.playback.concurrency;

import androidx.annotation.NonNull;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface ConcurrencyResolutionPolicy {
    public static final ConcurrencyResolutionPolicy MANUAL = new ConcurrencyResolutionPolicy() { // from class: com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy.1
        @Override // com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy
        public ConcurrentDeviceInfo autoResolve(@NonNull List<ConcurrentDeviceInfo> list) {
            return null;
        }
    };
    public static final ConcurrencyResolutionPolicy CHOOSE_FIRST = new ConcurrencyResolutionPolicy() { // from class: com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy.2
        @Override // com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy
        public ConcurrentDeviceInfo autoResolve(@NonNull List<ConcurrentDeviceInfo> list) {
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }
    };
    public static final ConcurrencyResolutionPolicy CHOOSE_OLDEST = new ConcurrencyResolutionPolicy() { // from class: com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy.3
        @Override // com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy
        public ConcurrentDeviceInfo autoResolve(@NonNull List<ConcurrentDeviceInfo> list) {
            ConcurrentDeviceInfo concurrentDeviceInfo = null;
            for (ConcurrentDeviceInfo concurrentDeviceInfo2 : list) {
                if (concurrentDeviceInfo == null || concurrentDeviceInfo2.getTimeStreamingBegan() < concurrentDeviceInfo.getTimeStreamingBegan()) {
                    concurrentDeviceInfo = concurrentDeviceInfo2;
                }
            }
            return concurrentDeviceInfo;
        }
    };
    public static final ConcurrencyResolutionPolicy AUTOMATIC_IF_SINGLE_CHOICE = new ConcurrencyResolutionPolicy() { // from class: com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy.4
        @Override // com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy
        public ConcurrentDeviceInfo autoResolve(@NonNull List<ConcurrentDeviceInfo> list) {
            if (list.size() == 1) {
                return list.get(0);
            }
            return null;
        }
    };

    ConcurrentDeviceInfo autoResolve(@NonNull List<ConcurrentDeviceInfo> list);
}
