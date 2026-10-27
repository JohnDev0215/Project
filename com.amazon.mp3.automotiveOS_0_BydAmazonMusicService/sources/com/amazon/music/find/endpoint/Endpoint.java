package com.amazon.music.find.endpoint;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Endpoint.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/find/endpoint/Endpoint;", "", "()V", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Endpoint {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: Endpoint.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\t"}, d2 = {"Lcom/amazon/music/find/endpoint/Endpoint$Companion;", "", "()V", "getMusicBaseEndpoint", "", "stage", "Lcom/amazon/music/find/endpoint/Stage;", "realm", "Lcom/amazon/music/find/endpoint/Realm;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: Endpoint.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[Stage.values().length];
                iArr[Stage.PROD.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[Realm.values().length];
                iArr2[Realm.NA.ordinal()] = 1;
                iArr2[Realm.EU.ordinal()] = 2;
                iArr2[Realm.FE.ordinal()] = 3;
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final String getMusicBaseEndpoint(Stage stage, Realm realm) {
            if (WhenMappings.$EnumSwitchMapping$0[stage.ordinal()] == 1) {
                int i = WhenMappings.$EnumSwitchMapping$1[realm.ordinal()];
                if (i == 1) {
                    return "https://music.amazon.com:443/NA/api/";
                }
                if (i == 2) {
                    return "https://music.amazon.com:443/EU/api/";
                }
                if (i == 3) {
                    return "https://music.amazon.com:443/FE/api/";
                }
                throw new IllegalArgumentException(Intrinsics.stringPlus("Not supported Realm ", realm.name()));
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("Not supported Stage ", stage.name()));
        }
    }
}
