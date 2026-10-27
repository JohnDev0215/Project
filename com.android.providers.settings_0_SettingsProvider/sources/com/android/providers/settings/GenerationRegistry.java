package com.android.providers.settings;

import android.os.UserManager;
import android.util.MemoryIntArray;
import android.util.Slog;
import android.util.SparseIntArray;
import com.android.internal.annotations.GuardedBy;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class GenerationRegistry {

    @GuardedBy({"mLock"})
    private MemoryIntArray mBackingStore;

    @GuardedBy({"mLock"})
    private final SparseIntArray mKeyToIndexMap = new SparseIntArray();
    private final Object mLock;

    public GenerationRegistry(Object obj) {
        this.mLock = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0026 A[Catch: all -> 0x0028, DONT_GENERATE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0009, B:8:0x0011, B:12:0x0026, B:11:0x001c), top: B:17:0x0003, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void incrementGeneration(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            android.util.MemoryIntArray r1 = r3.getBackingStoreLocked()     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L26
            android.util.SparseIntArray r2 = r3.mKeyToIndexMap     // Catch: java.io.IOException -> L1b java.lang.Throwable -> L28
            int r4 = getKeyIndexLocked(r4, r2, r1)     // Catch: java.io.IOException -> L1b java.lang.Throwable -> L28
            if (r4 < 0) goto L26
            int r2 = r1.get(r4)     // Catch: java.io.IOException -> L1b java.lang.Throwable -> L28
            int r2 = r2 + 1
            r1.set(r4, r2)     // Catch: java.io.IOException -> L1b java.lang.Throwable -> L28
            goto L26
        L1b:
            r4 = move-exception
            java.lang.String r1 = "GenerationRegistry"
            java.lang.String r2 = "Error updating generation id"
            android.util.Slog.e(r1, r2, r4)     // Catch: java.lang.Throwable -> L28
            r3.destroyBackingStore()     // Catch: java.lang.Throwable -> L28
        L26:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L28
            return
        L28:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L28
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.GenerationRegistry.incrementGeneration(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0030 A[Catch: all -> 0x0032, DONT_GENERATE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0009, B:8:0x0011, B:12:0x0030, B:11:0x0026), top: B:17:0x0003, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addGenerationData(android.os.Bundle r4, int r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            android.util.MemoryIntArray r1 = r3.getBackingStoreLocked()     // Catch: java.lang.Throwable -> L32
            if (r1 == 0) goto L30
            android.util.SparseIntArray r2 = r3.mKeyToIndexMap     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L32
            int r5 = getKeyIndexLocked(r5, r2, r1)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L32
            if (r5 < 0) goto L30
            java.lang.String r2 = "_track_generation"
            r4.putParcelable(r2, r1)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L32
            java.lang.String r2 = "_generation_index"
            r4.putInt(r2, r5)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L32
            java.lang.String r2 = "_generation"
            int r5 = r1.get(r5)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L32
            r4.putInt(r2, r5)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L32
            goto L30
        L25:
            r4 = move-exception
            java.lang.String r5 = "GenerationRegistry"
            java.lang.String r1 = "Error adding generation data"
            android.util.Slog.e(r5, r1, r4)     // Catch: java.lang.Throwable -> L32
            r3.destroyBackingStore()     // Catch: java.lang.Throwable -> L32
        L30:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L32
            return
        L32:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L32
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.GenerationRegistry.addGenerationData(android.os.Bundle, int):void");
    }

    public void onUserRemoved(int i) {
        synchronized (this.mLock) {
            MemoryIntArray backingStoreLocked = getBackingStoreLocked();
            if (backingStoreLocked != null && this.mKeyToIndexMap.size() > 0) {
                try {
                    resetSlotForKeyLocked(SettingsProvider.makeKey(2, i), this.mKeyToIndexMap, backingStoreLocked);
                    resetSlotForKeyLocked(SettingsProvider.makeKey(1, i), this.mKeyToIndexMap, backingStoreLocked);
                } catch (IOException e) {
                    Slog.e("GenerationRegistry", "Error cleaning up for user", e);
                    destroyBackingStore();
                }
            }
        }
    }

    @GuardedBy({"mLock"})
    private MemoryIntArray getBackingStoreLocked() {
        if (this.mBackingStore == null) {
            try {
                this.mBackingStore = new MemoryIntArray((UserManager.getMaxSupportedUsers() * 2) + 14);
            } catch (IOException e) {
                Slog.e("GenerationRegistry", "Error creating generation tracker", e);
            }
        }
        return this.mBackingStore;
    }

    private void destroyBackingStore() {
        MemoryIntArray memoryIntArray = this.mBackingStore;
        if (memoryIntArray != null) {
            try {
                memoryIntArray.close();
            } catch (IOException e) {
                Slog.e("GenerationRegistry", "Cannot close generation memory array", e);
            }
            this.mBackingStore = null;
        }
    }

    private static void resetSlotForKeyLocked(int i, SparseIntArray sparseIntArray, MemoryIntArray memoryIntArray) throws IOException {
        int i2 = sparseIntArray.get(i, -1);
        if (i2 >= 0) {
            sparseIntArray.delete(i);
            memoryIntArray.set(i2, 0);
        }
    }

    private static int getKeyIndexLocked(int i, SparseIntArray sparseIntArray, MemoryIntArray memoryIntArray) throws IOException {
        int iFindNextEmptyIndex = sparseIntArray.get(i, -1);
        if (iFindNextEmptyIndex < 0) {
            iFindNextEmptyIndex = findNextEmptyIndex(memoryIntArray);
            if (iFindNextEmptyIndex >= 0) {
                memoryIntArray.set(iFindNextEmptyIndex, 1);
                sparseIntArray.append(i, iFindNextEmptyIndex);
            } else {
                Slog.e("GenerationRegistry", "Could not allocate generation index");
            }
        }
        return iFindNextEmptyIndex;
    }

    private static int findNextEmptyIndex(MemoryIntArray memoryIntArray) throws IOException {
        int size = memoryIntArray.size();
        for (int i = 0; i < size; i++) {
            if (memoryIntArray.get(i) == 0) {
                return i;
            }
        }
        return -1;
    }
}
