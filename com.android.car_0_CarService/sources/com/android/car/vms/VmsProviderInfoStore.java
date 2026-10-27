package com.android.car.vms;

import android.util.ArrayMap;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
class VmsProviderInfoStore {
    private final Object mLock = new Object();
    private final ArrayMap<InfoWrapper, Integer> mProvidersIds = new ArrayMap<>();
    private final ArrayList<InfoWrapper> mProvidersInfo = new ArrayList<>();

    VmsProviderInfoStore() {
    }

    private static class InfoWrapper {
        private final byte[] mInfo;

        InfoWrapper(byte[] info) {
            this.mInfo = info;
        }

        public byte[] getInfo() {
            return (byte[]) this.mInfo.clone();
        }

        public boolean equals(Object o) {
            if (!(o instanceof InfoWrapper)) {
                return false;
            }
            InfoWrapper p = (InfoWrapper) o;
            return Arrays.equals(this.mInfo, p.mInfo);
        }

        public int hashCode() {
            return Arrays.hashCode(this.mInfo);
        }
    }

    int getProviderId(byte[] providerInfo) {
        Integer providerId;
        InfoWrapper wrappedProviderInfo = new InfoWrapper(providerInfo);
        synchronized (this.mLock) {
            providerId = this.mProvidersIds.get(wrappedProviderInfo);
            if (providerId == null) {
                this.mProvidersInfo.add(wrappedProviderInfo);
                providerId = Integer.valueOf(this.mProvidersInfo.size());
                this.mProvidersIds.put(wrappedProviderInfo, providerId);
            }
        }
        return providerId.intValue();
    }

    byte[] getProviderInfo(int providerId) {
        byte[] info;
        synchronized (this.mLock) {
            if (providerId < 1) {
                info = null;
            } else {
                try {
                    if (providerId > this.mProvidersInfo.size()) {
                        info = null;
                    } else {
                        info = this.mProvidersInfo.get(providerId - 1).getInfo();
                    }
                } finally {
                }
            }
        }
        return info;
    }
}
