package com.amazon.music.find.endpoint;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Realm.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/endpoint/Realm;", "", "(Ljava/lang/String;I)V", "NA", "FE", "EU", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum Realm {
    NA,
    FE,
    EU;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: Realm.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/endpoint/Realm$Companion;", "", "()V", "fromString", "Lcom/amazon/music/find/endpoint/Realm;", "string", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Realm fromString(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            int iHashCode = string.hashCode();
            if (iHashCode != 2224) {
                if (iHashCode != 2239) {
                    if (iHashCode == 2483 && string.equals("NA")) {
                        return Realm.NA;
                    }
                } else if (string.equals("FE")) {
                    return Realm.FE;
                }
            } else if (string.equals("EU")) {
                return Realm.EU;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("Invalid realm string ", string));
        }
    }
}
