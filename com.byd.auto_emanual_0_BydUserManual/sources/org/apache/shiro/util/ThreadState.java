package org.apache.shiro.util;

/* JADX INFO: loaded from: classes.dex */
public interface ThreadState {
    void bind();

    void clear();

    void restore();
}
