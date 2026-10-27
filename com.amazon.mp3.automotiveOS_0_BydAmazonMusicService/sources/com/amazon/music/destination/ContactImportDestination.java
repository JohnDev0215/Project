package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContactImportDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/destination/ContactImportDestination;", "Lcom/amazon/music/destination/core/Destination;", "path", "", "ref", ParserUtil.TAG_QUERY_PARAM_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "DMMDestination_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContactImportDestination extends Destination {
    private final String path;

    public final String getPath() {
        return this.path;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContactImportDestination(String path, String str, String str2) {
        super(str, str2);
        Intrinsics.checkNotNullParameter(path, "path");
        this.path = path;
    }
}
