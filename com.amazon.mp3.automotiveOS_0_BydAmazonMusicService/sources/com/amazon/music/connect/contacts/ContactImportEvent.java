package com.amazon.music.connect.contacts;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContactImportEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lcom/amazon/music/connect/contacts/ContactImportEvent;", "", "id", "", "(Ljava/lang/String;)V", "completionUrl", "(Ljava/lang/String;Ljava/lang/String;)V", "getCompletionUrl", "()Ljava/lang/String;", "getId", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContactImportEvent {
    private final String completionUrl;
    private final String id;

    public ContactImportEvent(String id, String str) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        this.completionUrl = str;
    }

    public final String getCompletionUrl() {
        return this.completionUrl;
    }

    public final String getId() {
        return this.id;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ContactImportEvent(String id) {
        this(id, null);
        Intrinsics.checkNotNullParameter(id, "id");
    }
}
