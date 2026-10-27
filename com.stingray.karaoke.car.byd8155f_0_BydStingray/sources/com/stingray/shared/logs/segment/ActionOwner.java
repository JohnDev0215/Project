package com.stingray.shared.logs.segment;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/shared/logs/segment/ActionOwner;", "", "<init>", "(Ljava/lang/String;I)V", "HOST", "COMPANION", "DEEPLINK", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ActionOwner {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ ActionOwner[] $VALUES;
    public static final ActionOwner HOST = new ActionOwner("HOST", 0);
    public static final ActionOwner COMPANION = new ActionOwner("COMPANION", 1);
    public static final ActionOwner DEEPLINK = new ActionOwner("DEEPLINK", 2);

    private static final /* synthetic */ ActionOwner[] $values() {
        return new ActionOwner[]{HOST, COMPANION, DEEPLINK};
    }

    static {
        ActionOwner[] actionOwnerArr$values = $values();
        $VALUES = actionOwnerArr$values;
        $ENTRIES = r.e(actionOwnerArr$values);
    }

    private ActionOwner(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static ActionOwner valueOf(String str) {
        return (ActionOwner) Enum.valueOf(ActionOwner.class, str);
    }

    public static ActionOwner[] values() {
        return (ActionOwner[]) $VALUES.clone();
    }
}
