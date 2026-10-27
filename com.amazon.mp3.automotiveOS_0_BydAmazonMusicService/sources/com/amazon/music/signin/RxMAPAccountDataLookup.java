package com.amazon.music.signin;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.music.authentication.AuthenticationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes4.dex */
class RxMAPAccountDataLookup {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) RxMAPAccountDataLookup.class);
    private final Context context;
    private Func1<MAPAccountIdResponse, MAPAccountDataResponse> getAccountDataFromMAP = new Func1<MAPAccountIdResponse, MAPAccountDataResponse>() { // from class: com.amazon.music.signin.RxMAPAccountDataLookup.1
        @Override // rx.functions.Func1
        public MAPAccountDataResponse call(@NonNull MAPAccountIdResponse mAPAccountIdResponse) {
            try {
                Validate.notNull(mAPAccountIdResponse, "accountIdResponse cannot be null", new Object[0]);
                return RxMAPAccountDataLookup.this.mapAccountDataLookup.getAccountDataFromMAP(RxMAPAccountDataLookup.this.context, mAPAccountIdResponse.getAccountId(), RxMAPAccountDataLookup.this.context.getPackageName(), new TokenManagement(RxMAPAccountDataLookup.this.context), CustomerAttributeStore.getInstance(RxMAPAccountDataLookup.this.context), DeviceDataStore.getInstance(RxMAPAccountDataLookup.this.context));
            } catch (DeviceDataStoreException | MAPCallbackErrorException | AuthenticationException | MAPEmptyReturnValueException | InterruptedException | ExecutionException | TimeoutException e) {
                RxMAPAccountDataLookup.LOG.error("getAccountDataFromMAP() failed", e);
                RxMAPAccountDataLookup.this.rxSignInTaskIdStream.onError(e);
                return null;
            }
        }
    };
    private final MAPAccountDataLookup mapAccountDataLookup;
    private final RxSignInTaskIdStream rxSignInTaskIdStream;

    protected RxMAPAccountDataLookup(@NonNull MAPAccountDataLookup mAPAccountDataLookup, @NonNull RxSignInTaskIdStream rxSignInTaskIdStream, @NonNull Context context) {
        Validate.notNull(mAPAccountDataLookup, "MAPAccountDataLookup cannot be null", new Object[0]);
        Validate.notNull(rxSignInTaskIdStream, "RxSignInTaskIdStream cannot be null", new Object[0]);
        Validate.notNull(context, "Context cannot be null", new Object[0]);
        this.mapAccountDataLookup = mAPAccountDataLookup;
        this.rxSignInTaskIdStream = rxSignInTaskIdStream;
        this.context = context;
    }

    protected Func1<MAPAccountIdResponse, MAPAccountDataResponse> getAccountDataFromMAP() {
        return this.getAccountDataFromMAP;
    }
}
