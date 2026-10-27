package com.amazon.music.signin;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;
import rx.subjects.ReplaySubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/* JADX INFO: loaded from: classes4.dex */
public class RxSignInTaskIdStream {
    private SerializedSubject signInTaskIdStream = new SerializedSubject(ReplaySubject.create());

    protected RxSignInTaskIdStream() {
    }

    protected void sendTaskId(@NonNull SignInTaskId signInTaskId) {
        Validate.notNull(signInTaskId, "Cannot send null SignInTaskId to the stream.", new Object[0]);
        this.signInTaskIdStream.onNext(signInTaskId);
    }

    protected void onError(@NonNull Throwable th) {
        this.signInTaskIdStream.onError(th);
    }

    protected void onCompleted() {
        this.signInTaskIdStream.onCompleted();
    }

    public Subject<SignInTaskId, SignInTaskId> toObservable() {
        return this.signInTaskIdStream;
    }
}
