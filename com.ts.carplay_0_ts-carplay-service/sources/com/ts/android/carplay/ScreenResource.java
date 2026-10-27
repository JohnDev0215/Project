package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayResourceManager;

/* JADX INFO: loaded from: classes.dex */
public final class ScreenResource extends CarPlayResourceManager.Resource {
    public ScreenResource() {
        this.mNative = CarPlayNative.getInstance();
    }

    @Override // com.ts.android.carplay.CarPlayResourceManager.Resource
    public int acquire(CarPlayResourceManager.ResourceChange resourceChange) throws Exception {
        if (this.mNative != null) {
            return this.mNative.acquireResource(1, resourceChange);
        }
        throw new Exception("mNative is null");
    }

    @Override // com.ts.android.carplay.CarPlayResourceManager.Resource
    public int available() throws Exception {
        if (this.mNative != null) {
            return this.mNative.availableResource(1);
        }
        throw new Exception("mNative is null");
    }

    @Override // com.ts.android.carplay.CarPlayResourceManager.Resource
    public int release(int transfer) throws Exception {
        return 0;
    }

    public static final class Change extends CarPlayResourceManager.ResourceChange {
        public Change() {
        }

        public Change(int transferType, int transferPriority, int takeConstraint, int borrowOrUnborrowConstraint, String borrowID) {
            super(transferType, transferPriority, takeConstraint, borrowOrUnborrowConstraint, borrowID);
        }
    }
}
