package com.amazon.coral.retry.transiency;

/* JADX INFO: loaded from: classes2.dex */
public interface TransiencyStrategy {
    boolean isTransient(Exception exc);
}
