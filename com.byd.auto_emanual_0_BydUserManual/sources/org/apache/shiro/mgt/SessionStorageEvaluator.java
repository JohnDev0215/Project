package org.apache.shiro.mgt;

import org.apache.shiro.subject.Subject;

/* JADX INFO: loaded from: classes.dex */
public interface SessionStorageEvaluator {
    boolean isSessionStorageEnabled(Subject subject);
}
