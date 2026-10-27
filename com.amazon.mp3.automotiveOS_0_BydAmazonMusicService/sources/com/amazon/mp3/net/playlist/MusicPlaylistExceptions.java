package com.amazon.mp3.net.playlist;

import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MusicPlaylistExceptions {
    private static final Map<String, Class<? extends PlaylistServiceException>> EXCEPTION_TYPE_MAP;

    public static class AccessDeniedException extends PlaylistServiceException {
    }

    public static class AccountNotFoundException extends PlaylistServiceException {
    }

    public static class DependencyException extends PlaylistServiceException {
    }

    public static class EntryIdNotFoundException extends PlaylistServiceException {
    }

    public static class ExpiredPageTokenException extends PlaylistServiceException {
    }

    public static class HiddenProfileException extends PlaylistServiceException {
    }

    public static class InvalidCheckpointException extends PlaylistServiceException {
    }

    public static class InvalidPageTokenException extends PlaylistServiceException {
    }

    public static class PermissionsException extends PlaylistServiceException {
    }

    public static class PlaylistFilteredException extends PlaylistServiceException {
    }

    public static class PlaylistFullException extends PlaylistServiceException {
    }

    public static class PlaylistNotFoundException extends PlaylistServiceException {
    }

    public static class PlaylistSuppressedException extends PlaylistServiceException {
    }

    public static class PlaylistVersionException extends PlaylistServiceException {
    }

    public static class PrimeTrackRestrictionsViolatedException extends PlaylistServiceException {
    }

    public static class TitleModerationDenialException extends PlaylistServiceException {
    }

    public static class TooManyPlaylistsException extends PlaylistServiceException {
    }

    public static class TrackIdNotFoundException extends PlaylistServiceException {
    }

    public static PlaylistServiceException getMappedException(String type, String message) {
        Class<? extends PlaylistServiceException> cls = EXCEPTION_TYPE_MAP.get(type);
        if (cls == null) {
            return null;
        }
        try {
            PlaylistServiceException playlistServiceExceptionNewInstance = cls.newInstance();
            playlistServiceExceptionNewInstance.setServiceErrorMessage(message);
            return playlistServiceExceptionNewInstance;
        } catch (Exception e) {
            Log.error("MusicPlaylistExceptions", "Unable to instantiate mapped PlaylistServiceException", e);
            return new PlaylistServiceException(type, message);
        }
    }

    public static class PlaylistServiceException extends ServiceException {
        private String mServiceErrorMessage;

        public void setServiceErrorMessage(String message) {
            this.mServiceErrorMessage = message;
        }

        public PlaylistServiceException() {
            this.mServiceErrorMessage = null;
        }

        public PlaylistServiceException(String message, String serviceErrorMessage) {
            super(message);
            this.mServiceErrorMessage = serviceErrorMessage;
        }
    }

    static {
        HashMap map = new HashMap();
        EXCEPTION_TYPE_MAP = map;
        map.put("com.amazon.musicplaylist.model#AccountNotFoundException", AccountNotFoundException.class);
        map.put("com.amazon.musicplaylist.model#TrackIdNotFoundException", TrackIdNotFoundException.class);
        map.put("com.amazon.musicplaylist.model#EntryIdNotFoundException", EntryIdNotFoundException.class);
        map.put("com.amazon.musicplaylist.model#InvalidCheckpointException", InvalidCheckpointException.class);
        map.put("com.amazon.musicplaylist.model#ExpiredPageTokenException", ExpiredPageTokenException.class);
        map.put("com.amazon.musicplaylist.model#InvalidPageTokenException", InvalidPageTokenException.class);
        map.put("com.amazon.musicplaylist.model#PlaylistFullException", PlaylistFullException.class);
        map.put("com.amazon.musicplaylist.model#PlaylistNotFoundException", PlaylistNotFoundException.class);
        map.put("com.amazon.musicplaylist.model#PlaylistVersionException", PlaylistVersionException.class);
        map.put("com.amazon.musicplaylist.model#TooManyPlaylistsException", TooManyPlaylistsException.class);
        map.put("com.amazon.musicplaylist.model#PermissionsException", PermissionsException.class);
        map.put("com.amazon.musicplaylist.model#PrimeTrackRestrictionsViolatedException", PrimeTrackRestrictionsViolatedException.class);
        map.put("com.amazon.musicplaylist.model#DependencyException", DependencyException.class);
        map.put("com.amazon.musicplaylist.model#HiddenProfileException", HiddenProfileException.class);
        map.put("com.amazon.musicplaylist.model#TitleModerationDenialException", TitleModerationDenialException.class);
        map.put("com.amazon.musicplaylist.model#PlaylistFilteredException", PlaylistFilteredException.class);
        map.put("com.amazon.musicplaylist.model#AccessDeniedError", AccessDeniedException.class);
        map.put("com.amazon.musicplaylist.model#PlaylistSuppressedError", PlaylistSuppressedException.class);
    }
}
