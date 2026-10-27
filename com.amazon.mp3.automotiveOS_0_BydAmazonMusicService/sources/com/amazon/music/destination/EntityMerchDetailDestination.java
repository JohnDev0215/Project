package com.amazon.music.destination;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityMerchDetailDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/music/destination/EntityMerchDetailDestination;", "Lcom/amazon/music/destination/core/Destination;", "merchId", "", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, ContextMappingConstants.ENTITY_ID, "ref", ParserUtil.TAG_QUERY_PARAM_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEntityId", "()Ljava/lang/String;", "getEntityType", "getMerchId", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityMerchDetailDestination extends Destination {
    private final String entityId;
    private final String entityType;
    private final String merchId;

    public final String getMerchId() {
        return this.merchId;
    }

    public final String getEntityType() {
        return this.entityType;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EntityMerchDetailDestination(String merchId, String str, String str2, String str3, String str4) {
        super(str3, str4);
        Intrinsics.checkNotNullParameter(merchId, "merchId");
        this.merchId = merchId;
        this.entityType = str;
        this.entityId = str2;
    }
}
