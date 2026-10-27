package com.amazon.scrublib.models;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ContentType.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/amazon/scrublib/models/ContentType;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "TRANSCRIBED_CONTENT", "UNTRANSCRIBED_CONTENT", "MUSIC", "Companion", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum ContentType {
    TRANSCRIBED_CONTENT(0),
    UNTRANSCRIBED_CONTENT(1),
    MUSIC(2);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int id;

    ContentType(int i) {
        this.id = i;
    }

    public final int getId() {
        return this.id;
    }

    /* JADX INFO: compiled from: ContentType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/scrublib/models/ContentType$Companion;", "", "()V", "fromInt", "Lcom/amazon/scrublib/models/ContentType;", "value", "", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ContentType fromInt(int value) {
            for (ContentType contentType : ContentType.values()) {
                if (contentType.getId() == value) {
                    return contentType;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }
    }
}
