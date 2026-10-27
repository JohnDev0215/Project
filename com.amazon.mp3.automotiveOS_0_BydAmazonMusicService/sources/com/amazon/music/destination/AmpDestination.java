package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AmpDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/destination/AmpDestination;", "Lcom/amazon/music/destination/core/Destination;", "showId", "", "ref", ParserUtil.TAG_QUERY_PARAM_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getShowId", "()Ljava/lang/String;", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AmpDestination extends Destination {
    private final String showId;

    public /* synthetic */ AmpDestination(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, str3);
    }

    public final String getShowId() {
        return this.showId;
    }

    public AmpDestination(String str, String str2, String str3) {
        super(str2, str3);
        this.showId = str;
    }
}
