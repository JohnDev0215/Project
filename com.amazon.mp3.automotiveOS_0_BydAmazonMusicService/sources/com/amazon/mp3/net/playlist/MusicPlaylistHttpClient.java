package com.amazon.mp3.net.playlist;

import com.amazon.mp3.net.CoralHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicPlaylistHttpClient extends CoralHttpClient {
    private static final List<Integer> VALID_STATUS_CODES = Arrays.asList(400, 403, 404, 500);

    public MusicPlaylistHttpClient() {
        setValidHttpStatusCodes(new HashSet(VALID_STATUS_CODES));
    }

    @Override // com.amazon.mp3.net.CoralHttpClient
    protected ServiceException getMappedException(String type, String message) {
        return MusicPlaylistExceptions.getMappedException(type, message);
    }
}
