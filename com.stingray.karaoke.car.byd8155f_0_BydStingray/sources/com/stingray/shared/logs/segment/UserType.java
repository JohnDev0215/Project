package com.stingray.shared.logs.segment;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/stingray/shared/logs/segment/UserType;", "", "<init>", "(Ljava/lang/String;I)V", "GUEST", "REGISTERED", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class UserType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ UserType[] $VALUES;
    public static final UserType GUEST = new UserType("GUEST", 0);
    public static final UserType REGISTERED = new UserType("REGISTERED", 1);

    private static final /* synthetic */ UserType[] $values() {
        return new UserType[]{GUEST, REGISTERED};
    }

    static {
        UserType[] userTypeArr$values = $values();
        $VALUES = userTypeArr$values;
        $ENTRIES = r.e(userTypeArr$values);
    }

    private UserType(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static UserType valueOf(String str) {
        return (UserType) Enum.valueOf(UserType.class, str);
    }

    public static UserType[] values() {
        return (UserType[]) $VALUES.clone();
    }
}
