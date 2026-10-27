package io.reactivex.internal.fuseable;

/* JADX INFO: loaded from: classes7.dex */
public interface QueueFuseable<T> extends SimpleQueue<T> {
    int requestFusion(int i);
}
