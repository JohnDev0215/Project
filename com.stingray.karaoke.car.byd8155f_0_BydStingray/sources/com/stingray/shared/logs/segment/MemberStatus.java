package com.stingray.shared.logs.segment;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/stingray/shared/logs/segment/MemberStatus;", "", "<init>", "(Ljava/lang/String;I)V", "NOT_SUBSCRIBED", "SUBSCRIBED", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MemberStatus {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ MemberStatus[] $VALUES;
    public static final MemberStatus NOT_SUBSCRIBED = new MemberStatus("NOT_SUBSCRIBED", 0);
    public static final MemberStatus SUBSCRIBED = new MemberStatus("SUBSCRIBED", 1);

    private static final /* synthetic */ MemberStatus[] $values() {
        return new MemberStatus[]{NOT_SUBSCRIBED, SUBSCRIBED};
    }

    static {
        MemberStatus[] memberStatusArr$values = $values();
        $VALUES = memberStatusArr$values;
        $ENTRIES = r.e(memberStatusArr$values);
    }

    private MemberStatus(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static MemberStatus valueOf(String str) {
        return (MemberStatus) Enum.valueOf(MemberStatus.class, str);
    }

    public static MemberStatus[] values() {
        return (MemberStatus[]) $VALUES.clone();
    }
}
