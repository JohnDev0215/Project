package com.apollographql.apollo3.internal;

import java.util.concurrent.CancellationException;
import p.fv0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class AbortFlowException extends CancellationException {
    private final fv0 owner;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbortFlowException(a aVar) {
        super("Flow was aborted, no more elements needed");
        v03.j(aVar, "owner");
        this.owner = aVar;
    }

    public final void a(a aVar) {
        v03.j(aVar, "owner");
        if (this.owner != aVar) {
            throw this;
        }
    }
}
