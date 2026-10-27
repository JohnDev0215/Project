package com.amazon.weblab.mobile;

import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes5.dex */
public interface IMobileWeblabClient {
    void addWeblab(String str, String str2) throws TooManyRegisteredWeblabsException, IllegalArgumentException;

    String getDirectedId();

    IMobileWeblabClientAttributes getIMobileWeblabClientAttributes();

    String getMarketplace();

    String getSession();

    IMobileWeblab getWeblab(String str) throws IllegalStateException, IllegalArgumentException;

    void lockWeblab(String str, String str2) throws IllegalStateException, IllegalArgumentException;

    void setDirectedId(String str);

    void setSessionAndMarketplaceId(String str, String str2) throws IllegalArgumentException;

    void unlockWeblab(String str);

    boolean update() throws MobileWeblabException;

    Future<Boolean> updateAsync() throws RejectedExecutionException;
}
