package com.spotify.pinpairing.pinpairingimpl;

import com.spotify.base.java.logging.Logger;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements SingleOnSubscribe {
    public final /* synthetic */ int c;
    public final /* synthetic */ c e;
    public final /* synthetic */ Request k;

    public /* synthetic */ b(c cVar, Request request, int i) {
        this.c = i;
        this.e = cVar;
        this.k = request;
    }

    @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
    public final void subscribe(SingleEmitter singleEmitter) {
        int i = this.c;
        c cVar = this.e;
        Request request = this.k;
        switch (i) {
            case 0:
                cVar.getClass();
                try {
                    Response responseB = cVar.b.d(request).b();
                    if (responseB.i()) {
                        singleEmitter.onSuccess(responseB);
                    } else {
                        IOException iOException = new IOException(String.format("HTTP request returned status %d (%s).", Integer.valueOf(responseB.l), responseB.k));
                        Logger.c(iOException, "Start flow request failed with an HTTP error.", new Object[0]);
                        singleEmitter.onError(iOException);
                    }
                } catch (IOException e) {
                    if (singleEmitter.isDisposed()) {
                        return;
                    }
                    singleEmitter.onError(e);
                    return;
                }
                break;
            default:
                cVar.getClass();
                try {
                    Response responseB2 = cVar.b.d(request).b();
                    if (responseB2.i()) {
                        singleEmitter.onSuccess(responseB2);
                    } else {
                        int i2 = responseB2.l;
                        if (i2 == 400) {
                            singleEmitter.onError(cVar.b(responseB2.o));
                        } else if (i2 < 500 || i2 > 599) {
                            IOException iOException2 = new IOException(String.format("HTTP request returned status %d (%s).", Integer.valueOf(i2), responseB2.k));
                            Logger.c(iOException2, "Polling request failed with an HTTP error.", new Object[0]);
                            singleEmitter.onError(iOException2);
                        } else {
                            Logger.b("Polling request failed because the server is temporarily unavailable.", new Object[0]);
                            singleEmitter.onError(new RetryException());
                        }
                    }
                } catch (IOException e2) {
                    if (singleEmitter.isDisposed()) {
                        return;
                    }
                    singleEmitter.onError(e2);
                    return;
                }
                break;
        }
    }
}
