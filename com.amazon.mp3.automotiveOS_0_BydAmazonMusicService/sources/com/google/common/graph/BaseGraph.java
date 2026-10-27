package com.google.common.graph;

import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
interface BaseGraph<N> extends SuccessorsFunction<N> {
    boolean isDirected();

    @Override // com.google.common.graph.SuccessorsFunction
    Set<N> successors(N n);
}
