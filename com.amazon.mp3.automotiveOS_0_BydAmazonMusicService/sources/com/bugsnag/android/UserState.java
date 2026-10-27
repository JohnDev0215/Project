package com.bugsnag.android;

import com.bugsnag.android.StateEvent;
import com.bugsnag.android.internal.StateObserver;
import java.util.Iterator;

/* JADX INFO: compiled from: UserState.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nR$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004¨\u0006\u000b"}, d2 = {"Lcom/bugsnag/android/UserState;", "Lcom/bugsnag/android/BaseObservable;", "user", "Lcom/bugsnag/android/User;", "(Lcom/bugsnag/android/User;)V", "value", "getUser", "()Lcom/bugsnag/android/User;", "setUser", "emitObservableEvent", "", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class UserState extends BaseObservable {
    private User user;

    public UserState(User user) {
        this.user = user;
    }

    public final User getUser() {
        return this.user;
    }

    public final void setUser(User user) {
        this.user = user;
        emitObservableEvent();
    }

    public final void emitObservableEvent() {
        if (getObservers$bugsnag_android_core_release().isEmpty()) {
            return;
        }
        StateEvent.UpdateUser updateUser = new StateEvent.UpdateUser(this.user);
        Iterator<T> it = getObservers$bugsnag_android_core_release().iterator();
        while (it.hasNext()) {
            ((StateObserver) it.next()).onStateChange(updateUser);
        }
    }
}
