package com.spotify.esperanto.esperantoimpl;

import com.spotify.esperanto.esperanto.Transport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NativeTransport implements Transport {
    private boolean isInvalidated;
    private final long nThis;

    /* JADX INFO: Access modifiers changed from: private */
    public final native void handleSingleSubscription(SingleEmitter<byte[]> singleEmitter, String str, String str2, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void handleStreamSubscription(ObservableEmitter<byte[]> observableEmitter, String str, String str2, byte[] bArr);

    @Override // com.spotify.esperanto.esperanto.Transport
    public Single<byte[]> callSingle(final String str, final String str2, final byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        Single<byte[]> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.spotify.esperanto.esperantoimpl.NativeTransport.callSingle.1
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter<byte[]> singleEmitter) {
                v03.j(singleEmitter, "emitter");
                if (!NativeTransport.this.isInvalidated) {
                    NativeTransport.this.handleSingleSubscription(singleEmitter, str, str2, bArr);
                    return;
                }
                singleEmitter.onError(new RuntimeException("callStream() subscription scheduled after invalidation: " + str + '.' + str2));
            }
        });
        v03.i(singleCreate, "override fun callSingle(…       }\n        })\n    }");
        return singleCreate;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public Observable<byte[]> callStream(final String str, final String str2, final byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        Observable<byte[]> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.spotify.esperanto.esperantoimpl.NativeTransport.callStream.1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter<byte[]> observableEmitter) {
                v03.j(observableEmitter, "emitter");
                if (!NativeTransport.this.isInvalidated) {
                    NativeTransport.this.handleStreamSubscription(observableEmitter, str, str2, bArr);
                    return;
                }
                observableEmitter.onError(new RuntimeException("callStream() subscription scheduled after invalidation: " + str + '.' + str2));
            }
        });
        v03.i(observableCreate, "override fun callStream(…       }\n        })\n    }");
        return observableCreate;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public native byte[] callSync(String str, String str2, byte[] bArr);

    public final native void destroy();

    public final void invalidate() {
        this.isInvalidated = true;
    }
}
