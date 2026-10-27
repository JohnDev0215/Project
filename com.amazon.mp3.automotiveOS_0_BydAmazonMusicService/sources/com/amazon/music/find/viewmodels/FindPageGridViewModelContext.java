package com.amazon.music.find.viewmodels;

import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.views.library.models.PageGridViewModelContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FindPageGridViewModelContext.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/find/viewmodels/FindPageGridViewModelContext;", "Lcom/amazon/music/views/library/models/PageGridViewModelContext;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "(Lcom/amazon/music/find/service/ExecutionContext;)V", "getExecutionContext", "()Lcom/amazon/music/find/service/ExecutionContext;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class FindPageGridViewModelContext implements PageGridViewModelContext {
    private final ExecutionContext executionContext;

    public static /* synthetic */ FindPageGridViewModelContext copy$default(FindPageGridViewModelContext findPageGridViewModelContext, ExecutionContext executionContext, int i, Object obj) {
        if ((i & 1) != 0) {
            executionContext = findPageGridViewModelContext.executionContext;
        }
        return findPageGridViewModelContext.copy(executionContext);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ExecutionContext getExecutionContext() {
        return this.executionContext;
    }

    public final FindPageGridViewModelContext copy(ExecutionContext executionContext) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        return new FindPageGridViewModelContext(executionContext);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FindPageGridViewModelContext) && Intrinsics.areEqual(this.executionContext, ((FindPageGridViewModelContext) other).executionContext);
    }

    public int hashCode() {
        return this.executionContext.hashCode();
    }

    public String toString() {
        return "FindPageGridViewModelContext(executionContext=" + this.executionContext + ')';
    }

    public FindPageGridViewModelContext(ExecutionContext executionContext) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        this.executionContext = executionContext;
    }

    public final ExecutionContext getExecutionContext() {
        return this.executionContext;
    }
}
