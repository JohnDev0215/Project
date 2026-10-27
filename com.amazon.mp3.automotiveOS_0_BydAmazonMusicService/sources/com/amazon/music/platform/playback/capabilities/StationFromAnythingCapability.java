package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.playback.capabilities.reduce.ReducibleBoolean;
import kotlin.Metadata;

/* JADX INFO: compiled from: StationFromAnythingCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0004J,\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/StationFromAnythingCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "", "Lcom/amazon/music/platform/playback/capabilities/reduce/ReducibleBoolean;", "()V", "actionName", "", "getActionName", "()Ljava/lang/String;", "canPerformInternal", "Lcom/amazon/music/platform/Outcome;", "eligibilities", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class StationFromAnythingCapability extends PlayableEntityCapability<Boolean> implements ReducibleBoolean {
    private final String actionName = "StationFromAnything";

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Reducible
    public Outcome<Boolean> reduce(Outcome<? extends Boolean> outcome, Outcome<? extends Boolean> outcome2) {
        return ReducibleBoolean.DefaultImpls.reduce(this, outcome, outcome2);
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    public String getActionName() {
        return this.actionName;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008d  */
    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected com.amazon.music.platform.Outcome<java.lang.Boolean> canPerformInternal(java.util.Map<java.lang.String, ? extends com.amazon.music.platform.data.eligibility.Eligibility> r6, com.amazon.music.platform.data.entity.EntityItem r7) {
        /*
            r5 = this;
            java.lang.String r0 = "eligibilities"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "CONTENT"
            java.lang.Object r0 = r6.get(r0)
            java.lang.String r1 = "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.ContentEligibility"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            com.amazon.music.platform.data.eligibility.ContentEligibility r0 = (com.amazon.music.platform.data.eligibility.ContentEligibility) r0
            java.lang.String r1 = "PLAYMODES"
            java.lang.Object r6 = r6.get(r1)
            boolean r1 = r6 instanceof com.amazon.music.platform.data.eligibility.PlaymodeEligibility
            r2 = 0
            if (r1 == 0) goto L20
            com.amazon.music.platform.data.eligibility.PlaymodeEligibility r6 = (com.amazon.music.platform.data.eligibility.PlaymodeEligibility) r6
            goto L21
        L20:
            r6 = r2
        L21:
            if (r6 == 0) goto L31
            com.amazon.music.platform.Outcome$Success r7 = new com.amazon.music.platform.Outcome$Success
            boolean r6 = r6.isStationFromAnything()
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            r7.<init>(r6)
            return r7
        L31:
            com.amazon.music.platform.data.entity.EntityItemIdentifier r6 = r0.getContentEligibilityIdentifier()
            com.amazon.music.platform.data.entity.EntityId r0 = r6.getId()
            boolean r0 = r0 instanceof com.amazon.music.platform.data.identifiers.Asin
            com.amazon.music.platform.data.entity.EntityId r1 = r6.getId()
            boolean r1 = r1 instanceof com.amazon.music.platform.data.identifiers.PlaylistId
            com.amazon.music.platform.data.entity.EntityItemType r6 = r6.getType()
            boolean r6 = r6 instanceof com.amazon.music.platform.data.entity.CollectionType
            r3 = 1
            r4 = 0
            if (r6 == 0) goto L8d
            boolean r6 = r7 instanceof com.amazon.music.platform.data.entity.EntityItemCollection
            if (r6 == 0) goto L52
            com.amazon.music.platform.data.entity.EntityItemCollection r7 = (com.amazon.music.platform.data.entity.EntityItemCollection) r7
            goto L53
        L52:
            r7 = r2
        L53:
            if (r7 == 0) goto L88
            java.util.List r6 = r7.getItems()
            if (r6 == 0) goto L88
            boolean r7 = r6.isEmpty()
            if (r7 == 0) goto L63
        L61:
            r6 = r4
            goto L84
        L63:
            java.util.Iterator r6 = r6.iterator()
        L67:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L61
            java.lang.Object r7 = r6.next()
            com.amazon.music.platform.data.entity.EntityItem r7 = (com.amazon.music.platform.data.entity.EntityItem) r7
            com.amazon.music.platform.data.entity.EntityItemIdentifier r7 = r7.getEntityItemIdentifier()
            if (r7 == 0) goto L7e
            com.amazon.music.platform.data.entity.EntityId r7 = r7.getId()
            goto L7f
        L7e:
            r7 = r2
        L7f:
            boolean r7 = r7 instanceof com.amazon.music.platform.data.identifiers.Asin
            if (r7 == 0) goto L67
            r6 = r3
        L84:
            if (r6 != r3) goto L88
            r6 = r3
            goto L89
        L88:
            r6 = r4
        L89:
            if (r6 == 0) goto L8d
            r6 = r3
            goto L8e
        L8d:
            r6 = r4
        L8e:
            com.amazon.music.platform.Outcome$Success r7 = new com.amazon.music.platform.Outcome$Success
            if (r0 != 0) goto L98
            if (r1 != 0) goto L98
            if (r6 == 0) goto L97
            goto L98
        L97:
            r3 = r4
        L98:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)
            r7.<init>(r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.playback.capabilities.StationFromAnythingCapability.canPerformInternal(java.util.Map, com.amazon.music.platform.data.entity.EntityItem):com.amazon.music.platform.Outcome");
    }
}
