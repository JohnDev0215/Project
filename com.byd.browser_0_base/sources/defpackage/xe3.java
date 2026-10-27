package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: Tracker.java */
/* JADX INFO: loaded from: classes2.dex */
public interface xe3 {
    void a(String str);

    String b();

    void c(Callback<Boolean> callback);

    void d(String str, Runnable runnable);

    void dismissed(String str);

    void e(String str);

    int getTriggerState(String str);

    boolean isInitialized();

    void notifyEvent(String str);

    boolean shouldTriggerHelpUI(String str);

    boolean wouldTriggerHelpUI(String str);
}
