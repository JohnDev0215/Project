package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
public class DirectedIdParameter {
    private final DirectedIdRetriever directedIdRetriever;

    public DirectedIdParameter() {
        this(new MAPDirectedIdRetriever());
    }

    DirectedIdParameter(DirectedIdRetriever directedIdRetriever) {
        this.directedIdRetriever = directedIdRetriever;
    }
}
