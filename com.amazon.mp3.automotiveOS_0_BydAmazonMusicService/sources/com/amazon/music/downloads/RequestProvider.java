package com.amazon.music.downloads;

import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public interface RequestProvider {

    public interface RequestProviderCallback {
        void requestsSubmitted(Set<RequestItem> set);
    }

    void getRequests(RequestProviderCallback requestProviderCallback);
}
