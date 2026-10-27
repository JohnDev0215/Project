package org.apache.shiro.session;

/* JADX INFO: loaded from: classes.dex */
public interface SessionListener {
    void onExpiration(Session session);

    void onStart(Session session);

    void onStop(Session session);
}
