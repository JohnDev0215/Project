package com.amazon.avod.http;

import java.io.IOException;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackAuthProvider extends PrimeVideoLegacyQOSHackAuthProvider {

    public static class InvalidSessionContextException extends IOException {
    }

    @Nonnull
    Map<String, String> getHeaders(@Nonnull Map<String, String> map) throws InvalidSessionContextException;
}
