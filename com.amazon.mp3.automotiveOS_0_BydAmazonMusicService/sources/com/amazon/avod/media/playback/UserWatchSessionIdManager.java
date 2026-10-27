package com.amazon.avod.media.playback;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class UserWatchSessionIdManager {
    private final Object mMutex;
    private final Map<String, String> mTitleIdToUserWatchSessionIdMap;

    private static class SingletonHolder {
        private static volatile UserWatchSessionIdManager sInstance = new UserWatchSessionIdManager();

        private SingletonHolder() {
        }
    }

    private UserWatchSessionIdManager() {
        this.mMutex = new Object();
        this.mTitleIdToUserWatchSessionIdMap = new HashMap();
    }

    public static UserWatchSessionIdManager getInstance() {
        return SingletonHolder.sInstance;
    }

    public void set(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, "titleId");
        Preconditions.checkNotNull(str2, "userWatchSessionId");
        synchronized (this.mMutex) {
            DLog.devf("UserWatchSessionIdManager: set(%s, %s)", str, str2);
            this.mTitleIdToUserWatchSessionIdMap.put(str, str2);
        }
    }

    @Nonnull
    public String getOrCreate(@Nonnull String str) {
        Preconditions.checkNotNull(str, "titleId");
        synchronized (this.mMutex) {
            if (this.mTitleIdToUserWatchSessionIdMap.containsKey(str)) {
                String str2 = this.mTitleIdToUserWatchSessionIdMap.get(str);
                DLog.devf("UserWatchSessionIdManager: getOrCreate(%s): returned %s", str, str2);
                return str2;
            }
            String string = UUID.randomUUID().toString();
            this.mTitleIdToUserWatchSessionIdMap.put(str, string);
            DLog.devf("UserWatchSessionIdManager: getOrCreate(%s): created %s", str, string);
            return string;
        }
    }

    public void remove(@Nonnull String str) {
        Preconditions.checkNotNull(str, "titleId");
        synchronized (this.mMutex) {
            DLog.devf("UserWatchSessionIdManager: remove(%s)", str);
            this.mTitleIdToUserWatchSessionIdMap.remove(str);
        }
    }
}
