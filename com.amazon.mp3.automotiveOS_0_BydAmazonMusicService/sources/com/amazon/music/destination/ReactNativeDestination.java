package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactNativeDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/music/destination/ReactNativeDestination;", "Lcom/amazon/music/destination/core/Destination;", "path", "", "presentationStyle", "ref", ParserUtil.TAG_QUERY_PARAM_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "getPresentationStyle", "getRef", "getTag", "DMMDestination_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ReactNativeDestination extends Destination {
    private final String path;
    private final String presentationStyle;
    private final String ref;
    private final String tag;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactNativeDestination(String path, String presentationStyle, String ref, String tag) {
        super(ref, tag);
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(presentationStyle, "presentationStyle");
        Intrinsics.checkNotNullParameter(ref, "ref");
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.path = path;
        this.presentationStyle = presentationStyle;
        this.ref = ref;
        this.tag = tag;
    }

    public final String getPath() {
        return this.path;
    }

    public final String getPresentationStyle() {
        return this.presentationStyle;
    }

    @Override // com.amazon.music.destination.core.Destination
    public String getRef() {
        return this.ref;
    }

    @Override // com.amazon.music.destination.core.Destination
    public String getTag() {
        return this.tag;
    }
}
