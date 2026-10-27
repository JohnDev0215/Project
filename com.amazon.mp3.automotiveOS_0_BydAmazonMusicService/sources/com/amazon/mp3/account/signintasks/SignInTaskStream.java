package com.amazon.mp3.account.signintasks;

import com.amazon.music.signin.RxSignInTaskIdStream;
import com.amazon.music.signin.SignInTaskId;
import rx.subjects.Subject;

/* JADX INFO: loaded from: classes3.dex */
public class SignInTaskStream {
    private static SignInTaskStream signInTaskStream;
    private RxSignInTaskIdStream signInTaskIdStream;

    private SignInTaskStream() {
    }

    public static SignInTaskStream get() {
        SignInTaskStream signInTaskStream2 = signInTaskStream;
        if (signInTaskStream2 != null) {
            return signInTaskStream2;
        }
        SignInTaskStream signInTaskStream3 = new SignInTaskStream();
        signInTaskStream = signInTaskStream3;
        return signInTaskStream3;
    }

    public void setRxSubject(RxSignInTaskIdStream signInTaskIdStream) {
        this.signInTaskIdStream = signInTaskIdStream;
    }

    public Subject<SignInTaskId, SignInTaskId> getRxSubject() {
        if (signInFlowIsInProgress()) {
            return signInTaskStream.signInTaskIdStream.toObservable();
        }
        return null;
    }

    public void clearRxSubject() {
        this.signInTaskIdStream = null;
    }

    public boolean noSignInFlowIsInProgress() {
        return this.signInTaskIdStream == null || signInTaskStream == null;
    }

    public boolean signInFlowIsInProgress() {
        return (this.signInTaskIdStream == null || signInTaskStream == null) ? false : true;
    }
}
