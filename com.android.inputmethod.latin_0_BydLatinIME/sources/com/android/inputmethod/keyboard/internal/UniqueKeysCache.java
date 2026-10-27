package com.android.inputmethod.keyboard.internal;

import com.android.inputmethod.keyboard.Key;
import java.util.HashMap;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class UniqueKeysCache {

    @Nonnull
    public static final UniqueKeysCache NO_CACHE = new UniqueKeysCache() { // from class: com.android.inputmethod.keyboard.internal.UniqueKeysCache.1
        @Override // com.android.inputmethod.keyboard.internal.UniqueKeysCache
        public void clear() {
        }

        @Override // com.android.inputmethod.keyboard.internal.UniqueKeysCache
        public Key getUniqueKey(Key key) {
            return key;
        }

        @Override // com.android.inputmethod.keyboard.internal.UniqueKeysCache
        public void setEnabled(boolean enabled) {
        }
    };

    public abstract void clear();

    @Nonnull
    public abstract Key getUniqueKey(@Nonnull Key key);

    public abstract void setEnabled(boolean enabled);

    @Nonnull
    public static UniqueKeysCache newInstance() {
        return new UniqueKeysCacheImpl();
    }

    private static final class UniqueKeysCacheImpl extends UniqueKeysCache {
        private final HashMap<Key, Key> mCache = new HashMap<>();
        private boolean mEnabled;

        UniqueKeysCacheImpl() {
        }

        @Override // com.android.inputmethod.keyboard.internal.UniqueKeysCache
        public void setEnabled(final boolean enabled) {
            this.mEnabled = enabled;
        }

        @Override // com.android.inputmethod.keyboard.internal.UniqueKeysCache
        public void clear() {
            this.mCache.clear();
        }

        @Override // com.android.inputmethod.keyboard.internal.UniqueKeysCache
        public Key getUniqueKey(final Key key) {
            if (!this.mEnabled) {
                return key;
            }
            Key key2 = this.mCache.get(key);
            if (key2 != null) {
                return key2;
            }
            this.mCache.put(key, key);
            return key;
        }
    }
}
