package com.amazon.avod.media.events;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.core.Framework;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.qahooks.QATestFeature;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.concurrent.NotThreadSafe;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AloysiusEventHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0015B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u0006\u0010\n\u001a\u00020\u0005R-\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\r0\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/amazon/avod/media/events/AloysiusEventHolder;", "Lcom/amazon/avod/media/events/MediaEventQueueListener;", "Lcom/amazon/avod/qahooks/QATestFeature;", "Lcom/amazon/avod/media/events/MediaEventQueue$PersistentMediaEvent;", NotificationCompat.CATEGORY_EVENT, "", "newEvent", "Landroid/content/Intent;", MAPAccountManager.KEY_INTENT, "handleIntent", "clearCache", "Ljava/util/concurrent/ConcurrentHashMap;", "", "", "mEventsByAppInstanceId$delegate", "Lkotlin/Lazy;", "getMEventsByAppInstanceId", "()Ljava/util/concurrent/ConcurrentHashMap;", "mEventsByAppInstanceId", "<init>", "()V", "Config", "ATVPlaybackQos_release"}, k = 1, mv = {1, 6, 0})
public final class AloysiusEventHolder implements MediaEventQueueListener, QATestFeature {
    public static final AloysiusEventHolder INSTANCE = new AloysiusEventHolder();

    /* JADX INFO: renamed from: mEventsByAppInstanceId$delegate, reason: from kotlin metadata */
    private static final Lazy mEventsByAppInstanceId = LazyKt__LazyJVMKt.lazy(new Function0<ConcurrentHashMap<String, List<MediaEventQueue.PersistentMediaEvent>>>() { // from class: com.amazon.avod.media.events.AloysiusEventHolder$mEventsByAppInstanceId$2
        @Override // kotlin.jvm.functions.Function0
        public final ConcurrentHashMap<String, List<MediaEventQueue.PersistentMediaEvent>> invoke() {
            return new ConcurrentHashMap<>();
        }
    });

    private AloysiusEventHolder() {
    }

    private final ConcurrentHashMap<String, List<MediaEventQueue.PersistentMediaEvent>> getMEventsByAppInstanceId() {
        return (ConcurrentHashMap) mEventsByAppInstanceId.getValue();
    }

    /* JADX INFO: compiled from: AloysiusEventHolder.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\u0005R2\u0010\u0003\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0012\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/avod/media/events/AloysiusEventHolder$Config;", "Lamazon/android/config/ConfigBase;", "()V", "mCacheAloysiusEventsInMemory", "Lamazon/android/config/ConfigurationValue;", "", "kotlin.jvm.PlatformType", "setCacheAloysiusEventsInMemory", "", "value", "shouldCacheAloysiusEventsInMemory", "ATVPlaybackQos_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Config extends ConfigBase {
        public static final Config INSTANCE;
        private static final ConfigurationValue<Boolean> mCacheAloysiusEventsInMemory;

        private Config() {
            super(null);
        }

        static {
            Config config = new Config();
            INSTANCE = config;
            mCacheAloysiusEventsInMemory = config.newBooleanConfigValue("cacheAloysiusEventsInMemory", false, ConfigType.DEBUG_OVERRIDES);
        }

        public final boolean shouldCacheAloysiusEventsInMemory() {
            if (Framework.isDebugConfigurationEnabled()) {
                Boolean value = mCacheAloysiusEventsInMemory.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "mCacheAloysiusEventsInMemory.value");
                if (value.booleanValue()) {
                    return true;
                }
            }
            return false;
        }

        public final void setCacheAloysiusEventsInMemory(boolean value) {
            mCacheAloysiusEventsInMemory.updateValue(Boolean.valueOf(value));
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventQueueListener
    public void newEvent(MediaEventQueue.PersistentMediaEvent event) {
        List<MediaEventQueue.PersistentMediaEvent> listPutIfAbsent;
        Intrinsics.checkNotNullParameter(event, "event");
        if (Config.INSTANCE.shouldCacheAloysiusEventsInMemory()) {
            ConcurrentHashMap<String, List<MediaEventQueue.PersistentMediaEvent>> mEventsByAppInstanceId2 = getMEventsByAppInstanceId();
            String appInstanceId = event.getAppInstanceId();
            List<MediaEventQueue.PersistentMediaEvent> arrayList = mEventsByAppInstanceId2.get(appInstanceId);
            if (arrayList == null && (listPutIfAbsent = mEventsByAppInstanceId2.putIfAbsent(appInstanceId, (arrayList = new ArrayList<>()))) != null) {
                arrayList = listPutIfAbsent;
            }
            arrayList.add(event);
        }
    }

    @Override // com.amazon.avod.qahooks.QATestFeature
    public void handleIntent(Intent intent) {
        String stringExtra = intent == null ? null : intent.getStringExtra("action");
        if (stringExtra != null) {
            int iHashCode = stringExtra.hashCode();
            if (iHashCode != -1298848381) {
                if (iHashCode != 94746189) {
                    if (iHashCode == 1671308008 && stringExtra.equals("disable")) {
                        DLog.logf("AloysiusEventHolder: Disabling caching of aloysius events");
                        Config.INSTANCE.setCacheAloysiusEventsInMemory(false);
                        return;
                    }
                } else if (stringExtra.equals("clear")) {
                    DLog.logf("AloysiusEventHolder: Clearing all aloysius events from cache");
                    clearCache();
                    return;
                }
            } else if (stringExtra.equals("enable")) {
                DLog.logf("AloysiusEventHolder: Enabling caching of aloysius events");
                Config.INSTANCE.setCacheAloysiusEventsInMemory(true);
                return;
            }
        }
        DLog.warnf(Intrinsics.stringPlus("AloysiusEventHolder: Unknown action: ", stringExtra));
    }

    public final void clearCache() {
        getMEventsByAppInstanceId().clear();
    }
}
