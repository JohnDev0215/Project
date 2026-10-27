package org.apache.shiro.session.mgt;

/* JADX INFO: loaded from: classes.dex */
public interface SessionValidationScheduler {
    void disableSessionValidation();

    void enableSessionValidation();

    boolean isEnabled();
}
