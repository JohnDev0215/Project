package com.amazon.mp3.store.html5.service;

import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.webview.StoreMessageHandler;

/* JADX INFO: loaded from: classes4.dex */
class StoreCacheServiceMessageHandler extends StoreMessageHandler {
    private StoreCacheService mService;

    public StoreCacheServiceMessageHandler(StoreCacheService service) {
        super(service);
        this.mService = service;
    }

    @Override // com.amazon.mp3.store.html5.webview.StoreMessageHandler
    public void handleCachedBroadcast(Message broadcast) {
        this.mService.onDoneCaching();
    }
}
