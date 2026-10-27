package com.amazon.music.library;

/* JADX INFO: loaded from: classes4.dex */
abstract class ThinLibraryRequest<TRequest> {
    private final TRequest mRequest;

    ThinLibraryRequest(TRequest trequest) {
        this.mRequest = trequest;
    }

    TRequest get() {
        return this.mRequest;
    }
}
