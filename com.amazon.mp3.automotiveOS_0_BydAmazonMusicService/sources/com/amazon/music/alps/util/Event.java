package com.amazon.music.alps.util;

import kotlin.Metadata;

/* JADX INFO: compiled from: Event.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\b\u0016\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0005\u001a\u00028\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R$\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00078\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/alps/util/Event;", "T", "", "getContentIfNotHandled", "()Ljava/lang/Object;", "content", "Ljava/lang/Object;", "", "<set-?>", "hasBeenHandled", "Z", "getHasBeenHandled", "()Z", "<init>", "(Ljava/lang/Object;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public class Event<T> {
    private final T content;
    private boolean hasBeenHandled;

    public Event(T t) {
        this.content = t;
    }

    public final T getContentIfNotHandled() {
        if (this.hasBeenHandled) {
            return null;
        }
        this.hasBeenHandled = true;
        return this.content;
    }
}
