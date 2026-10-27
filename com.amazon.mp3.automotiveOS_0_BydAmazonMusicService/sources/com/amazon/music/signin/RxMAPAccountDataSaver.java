package com.amazon.music.signin;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes4.dex */
class RxMAPAccountDataSaver {
    private final MAPAccountDataSaver mapAccountDataSaver;
    private final RxSignInTaskIdStream rxSignInTaskIdStream;
    private Func1<MAPAccountDataResponse, Void> saveAccountDataResponse = new Func1<MAPAccountDataResponse, Void>() { // from class: com.amazon.music.signin.RxMAPAccountDataSaver.1
        @Override // rx.functions.Func1
        public Void call(@NonNull MAPAccountDataResponse mAPAccountDataResponse) {
            try {
                Validate.notNull(mAPAccountDataResponse, "accountDataResponse cannot be null", new Object[0]);
                RxMAPAccountDataSaver.this.mapAccountDataSaver.saveAccountDataResponse(mAPAccountDataResponse);
                RxMAPAccountDataSaver.this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.ACCOUNT_DATA_SAVED);
                return null;
            } catch (Exception e) {
                RxMAPAccountDataSaver.this.rxSignInTaskIdStream.onError(e);
                return null;
            }
        }
    };

    protected RxMAPAccountDataSaver(@NonNull MAPAccountDataSaver mAPAccountDataSaver, @NonNull RxSignInTaskIdStream rxSignInTaskIdStream) {
        Validate.notNull(mAPAccountDataSaver, "mapAccountDataSaver cannot be null", new Object[0]);
        Validate.notNull(rxSignInTaskIdStream, "rxSignInTaskIdStream cannot be null", new Object[0]);
        this.mapAccountDataSaver = mAPAccountDataSaver;
        this.rxSignInTaskIdStream = rxSignInTaskIdStream;
    }

    protected Func1<MAPAccountDataResponse, Void> saveAccountDataResponse() {
        return this.saveAccountDataResponse;
    }
}
