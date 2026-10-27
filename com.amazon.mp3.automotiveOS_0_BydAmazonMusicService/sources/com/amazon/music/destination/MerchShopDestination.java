package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;

/* JADX INFO: compiled from: MerchShopDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B%\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/destination/MerchShopDestination;", "Lcom/amazon/music/destination/core/Destination;", "category", "", "ref", ParserUtil.TAG_QUERY_PARAM_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "sequence", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "getName", "getSequence", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MerchShopDestination extends Destination {
    private final String category;
    private final String name;
    private final String sequence;

    public final String getCategory() {
        return this.category;
    }

    public final String getSequence() {
        return this.sequence;
    }

    public final String getName() {
        return this.name;
    }

    public MerchShopDestination(String str, String str2, String str3, String str4, String str5) {
        super(str4, str5);
        this.category = str;
        this.sequence = str2;
        this.name = str3;
    }

    public MerchShopDestination(String str, String str2, String str3) {
        this(str, null, null, str2, str3);
    }
}
