package com.apollographql.apollo3.internal;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0013\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003R\u001b\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/internal/AbortFlowException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "Lkotlinx/coroutines/flow/FlowCollector;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "", "checkOwnership", "Lkotlinx/coroutines/flow/FlowCollector;", "getOwner", "()Lkotlinx/coroutines/flow/FlowCollector;", "<init>", "(Lkotlinx/coroutines/flow/FlowCollector;)V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
final class AbortFlowException extends CancellationException {
    private final FlowCollector<?> owner;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbortFlowException(FlowCollector<?> owner) {
        super("Flow was aborted, no more elements needed");
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.owner = owner;
    }

    public final void checkOwnership(FlowCollector<?> owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (this.owner != owner) {
            throw this;
        }
    }
}
