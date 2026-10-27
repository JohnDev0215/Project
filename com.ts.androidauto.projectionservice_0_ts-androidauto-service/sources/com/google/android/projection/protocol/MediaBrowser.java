package com.google.android.projection.protocol;

import com.google.android.projection.proto.Protos;

/* JADX INFO: loaded from: classes.dex */
public class MediaBrowser implements CarServiceProvider, MediaBrowserCallbacks {
    private static final boolean DBG = true;
    private static final String TAG = "AHU.MEDIA_BROWSER";
    private final MediaBrowserListener mListener;
    private long mNativeMediaBrowser;

    public interface MediaBrowserListener {
        void onMediaListNode(MediaList mediaList, int i, int i2, MediaSong[] mediaSongArr);

        void onMediaRootNode(String str, MediaSource[] mediaSourceArr);

        void onMediaSongNode(MediaSong mediaSong, byte[] bArr, int i);

        void onMediaSourceNode(MediaSource mediaSource, int i, int i2, MediaList[] mediaListArr);
    }

    private native int nativeGetNode(String str, int i, boolean z);

    private native int nativeInit(int i, long j) throws IllegalStateException;

    private native int nativeReportAction(String str, int i);

    private native void nativeShutdown();

    public class MediaSource {
        public byte[] albumArt;
        public String name;
        public String path;

        public MediaSource() {
        }
    }

    public class MediaList {
        public byte[] albumArt;
        public String name;
        public String path;
        public int type;

        public MediaList() {
        }
    }

    public class MediaSong {
        public String album;
        public String artist;
        public String name;
        public String path;

        public MediaSong() {
        }
    }

    public MediaBrowser(MediaBrowserListener listener) {
        this.mListener = listener;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int id, long nativeGalReceiver) {
        if (nativeInit(id, nativeGalReceiver) == 0) {
            return DBG;
        }
        return false;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeMediaBrowser;
    }

    public int getNode(String path, int start, boolean getAlbumArt) {
        return nativeGetNode(path, start, getAlbumArt);
    }

    public int input(String path, int action) {
        return nativeReportAction(path, action);
    }

    @Override // com.google.android.projection.protocol.MediaBrowserCallbacks
    public int rootNodeCallback(String path, String[] sourcePaths, String[] sourceNames, byte[][] sourceAlbumArts) {
        if (sourcePaths == null || sourceNames == null || sourcePaths.length != sourceNames.length) {
            return Protos.MessageStatus.STATUS_INVALID_INPUT.getNumber();
        }
        if (this.mListener != null) {
            MediaSource[] sources = new MediaSource[sourcePaths.length];
            for (int i = 0; i < sourcePaths.length; i++) {
                sources[i] = new MediaSource();
                sources[i].path = sourcePaths[i];
                sources[i].name = sourceNames[i];
                if (sourceAlbumArts != null && i < sourceAlbumArts.length) {
                    sources[i].albumArt = sourceAlbumArts[i];
                }
            }
            this.mListener.onMediaRootNode(path, sources);
        }
        return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
    }

    @Override // com.google.android.projection.protocol.MediaBrowserCallbacks
    public int sourceNodeCallback(String path, String name, byte[] albumArt, int start, int total, String[] listPaths, String[] listNames, byte[][] listAlbumArts, int[] types) {
        if (listPaths == null || listNames == null || types == null || listPaths.length != listNames.length || listPaths.length != types.length) {
            return Protos.MessageStatus.STATUS_INVALID_INPUT.getNumber();
        }
        if (this.mListener != null) {
            MediaList[] lists = new MediaList[listPaths.length];
            for (int i = 0; i < listPaths.length; i++) {
                lists[i] = new MediaList();
                lists[i].path = listPaths[i];
                lists[i].name = listNames[i];
                lists[i].type = types[i];
                if (listAlbumArts != null && i < listAlbumArts.length) {
                    lists[i].albumArt = listAlbumArts[i];
                }
            }
            MediaSource source = new MediaSource();
            source.name = name;
            source.path = path;
            source.albumArt = albumArt;
            this.mListener.onMediaSourceNode(source, start, total, lists);
        }
        return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
    }

    @Override // com.google.android.projection.protocol.MediaBrowserCallbacks
    public int listNodeCallback(String path, String name, byte[] albumArt, int type, int start, int total, String[] songPaths, String[] songNames, String[] songArtists, String[] songAlbums) {
        if (songPaths == null || songNames == null || songArtists == null || songAlbums == null || songPaths.length != songNames.length || songPaths.length != songArtists.length || songPaths.length != songAlbums.length) {
            return Protos.MessageStatus.STATUS_INVALID_INPUT.getNumber();
        }
        if (this.mListener != null) {
            MediaSong[] songs = new MediaSong[songPaths.length];
            for (int i = 0; i < songPaths.length; i++) {
                songs[i] = new MediaSong();
                songs[i].path = songPaths[i];
                songs[i].name = songNames[i];
                songs[i].album = songAlbums[i];
                songs[i].artist = songArtists[i];
            }
            MediaList list = new MediaList();
            list.path = path;
            list.name = name;
            list.albumArt = albumArt;
            list.type = type;
            this.mListener.onMediaListNode(list, start, total, songs);
        }
        return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
    }

    @Override // com.google.android.projection.protocol.MediaBrowserCallbacks
    public int songNodeCallback(String path, String name, String album, String artist, byte[] albumArt, int durationSeconds) {
        if (this.mListener != null) {
            MediaSong song = new MediaSong();
            song.path = path;
            song.name = name;
            song.album = album;
            song.artist = artist;
            this.mListener.onMediaSongNode(song, albumArt, durationSeconds);
        }
        return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
    }
}
