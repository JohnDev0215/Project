package com.amazon.music.alps.liveevent;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.ContentUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventDetailDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/alps/liveevent/LiveEventDetailDestination;", "Lcom/amazon/music/destination/core/Destination;", ContentUtils.KEY_EVENT_ID, "", "ref", ParserUtil.TAG_QUERY_PARAM_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEventId", "()Ljava/lang/String;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LiveEventDetailDestination extends Destination {
    private final String eventId;

    public final String getEventId() {
        return this.eventId;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveEventDetailDestination(String eventId, String str, String str2) {
        super(str, str2);
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        this.eventId = eventId;
    }
}
