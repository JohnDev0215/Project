package com.stingray.shared.logs.segment.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/stingray/shared/logs/segment/player/ContentCompletedReason;", "", "<init>", "(Ljava/lang/String;I)V", "COMPLETED", "SKIPPED", "STOPPED", "SONG_CHANGED", "REMOVED_FROM_QUEUE", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ContentCompletedReason {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ ContentCompletedReason[] $VALUES;
    public static final ContentCompletedReason COMPLETED = new ContentCompletedReason("COMPLETED", 0);
    public static final ContentCompletedReason SKIPPED = new ContentCompletedReason("SKIPPED", 1);
    public static final ContentCompletedReason STOPPED = new ContentCompletedReason("STOPPED", 2);
    public static final ContentCompletedReason SONG_CHANGED = new ContentCompletedReason("SONG_CHANGED", 3);
    public static final ContentCompletedReason REMOVED_FROM_QUEUE = new ContentCompletedReason("REMOVED_FROM_QUEUE", 4);

    private static final /* synthetic */ ContentCompletedReason[] $values() {
        return new ContentCompletedReason[]{COMPLETED, SKIPPED, STOPPED, SONG_CHANGED, REMOVED_FROM_QUEUE};
    }

    static {
        ContentCompletedReason[] contentCompletedReasonArr$values = $values();
        $VALUES = contentCompletedReasonArr$values;
        $ENTRIES = r.e(contentCompletedReasonArr$values);
    }

    private ContentCompletedReason(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static ContentCompletedReason valueOf(String str) {
        return (ContentCompletedReason) Enum.valueOf(ContentCompletedReason.class, str);
    }

    public static ContentCompletedReason[] values() {
        return (ContentCompletedReason[]) $VALUES.clone();
    }
}
