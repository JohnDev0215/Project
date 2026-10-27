package com.google.common.graph;

import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public interface Network<N, E> extends SuccessorsFunction<N> {
    boolean isDirected();

    @Override // com.google.common.graph.SuccessorsFunction
    Set<N> successors(N n);
}
