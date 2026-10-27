package com.amazon.avod.ads.api.internal;

import com.amazon.avod.ads.api.AdException;
import com.amazon.avod.ads.api.AdInfoErrorCode;
import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.Creative;
import com.amazon.avod.ads.api.Duration;
import java.net.URI;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface AdNode {
    List<Creative> getDisplayableCreatives();

    boolean hasUnresolvedEntities();

    void resolveExternalEntities() throws AdException;

    void sendError(AdInfoErrorCode adInfoErrorCode, Duration duration, URI uri) throws AdNetworkException;

    void sendImpression(Duration duration, URI uri, String str) throws AdNetworkException;
}
