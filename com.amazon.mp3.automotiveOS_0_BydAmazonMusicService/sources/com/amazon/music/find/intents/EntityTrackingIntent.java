package com.amazon.music.find.intents;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityTrackingIntent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/find/intents/EntityTrackingIntent;", "", "()V", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityTrackingIntent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String HANDLE_ENTITY_VISIT = Intrinsics.stringPlus(EntityTrackingIntent.class.getName(), ".HANDLE_ENTITY_VISIT");
    private static final String EXTRA_ENTITY_VISIT_INFO = Intrinsics.stringPlus(EntityTrackingIntent.class.getName(), ".EXTRA_ENTITY_VISIT_INFO");
    private static final String HANDLE_ENTITY_REFRESH = Intrinsics.stringPlus(EntityTrackingIntent.class.getName(), ".HANDLE_ENTITY_REFRESH");
    private static final String EXTRA_ENTITY_REFRESH_INFO = Intrinsics.stringPlus(EntityTrackingIntent.class.getName(), ".EXTRA_ENTITY_REFRESH_INFO");

    public static final String getEXTRA_ENTITY_REFRESH_INFO() {
        return INSTANCE.getEXTRA_ENTITY_REFRESH_INFO();
    }

    public static final String getEXTRA_ENTITY_VISIT_INFO() {
        return INSTANCE.getEXTRA_ENTITY_VISIT_INFO();
    }

    public static final String getHANDLE_ENTITY_REFRESH() {
        return INSTANCE.getHANDLE_ENTITY_REFRESH();
    }

    public static final String getHANDLE_ENTITY_VISIT() {
        return INSTANCE.getHANDLE_ENTITY_VISIT();
    }

    /* JADX INFO: compiled from: EntityTrackingIntent.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u0007R\u001c\u0010\u000e\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/find/intents/EntityTrackingIntent$Companion;", "", "()V", "EXTRA_ENTITY_REFRESH_INFO", "", "getEXTRA_ENTITY_REFRESH_INFO$annotations", "getEXTRA_ENTITY_REFRESH_INFO", "()Ljava/lang/String;", "EXTRA_ENTITY_VISIT_INFO", "getEXTRA_ENTITY_VISIT_INFO$annotations", "getEXTRA_ENTITY_VISIT_INFO", "HANDLE_ENTITY_REFRESH", "getHANDLE_ENTITY_REFRESH$annotations", "getHANDLE_ENTITY_REFRESH", "HANDLE_ENTITY_VISIT", "getHANDLE_ENTITY_VISIT$annotations", "getHANDLE_ENTITY_VISIT", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getEXTRA_ENTITY_REFRESH_INFO$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void getEXTRA_ENTITY_VISIT_INFO$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void getHANDLE_ENTITY_REFRESH$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void getHANDLE_ENTITY_VISIT$annotations() {
        }

        private Companion() {
        }

        public final String getHANDLE_ENTITY_VISIT() {
            return EntityTrackingIntent.HANDLE_ENTITY_VISIT;
        }

        public final String getEXTRA_ENTITY_VISIT_INFO() {
            return EntityTrackingIntent.EXTRA_ENTITY_VISIT_INFO;
        }

        public final String getHANDLE_ENTITY_REFRESH() {
            return EntityTrackingIntent.HANDLE_ENTITY_REFRESH;
        }

        public final String getEXTRA_ENTITY_REFRESH_INFO() {
            return EntityTrackingIntent.EXTRA_ENTITY_REFRESH_INFO;
        }
    }
}
