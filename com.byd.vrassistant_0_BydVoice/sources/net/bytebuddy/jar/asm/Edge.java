package net.bytebuddy.jar.asm;

/* JADX INFO: loaded from: classes3.dex */
class Edge {
    static final int EXCEPTION = Integer.MAX_VALUE;
    static final int NORMAL = 0;
    int info;
    Edge next;
    Label successor;

    Edge() {
    }
}
