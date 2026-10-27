package com.google.common.collect;

import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes6.dex */
public final class Queues {
    private Queues() {
    }

    public static <E> ArrayDeque<E> newArrayDeque() {
        return new ArrayDeque<>();
    }

    public static <E> ConcurrentLinkedQueue<E> newConcurrentLinkedQueue() {
        return new ConcurrentLinkedQueue<>();
    }

    public static <E> LinkedBlockingQueue<E> newLinkedBlockingQueue() {
        return new LinkedBlockingQueue<>();
    }
}
