package com.byd.music.beans;

import com.byd.music.beans.LocalMusicBean_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.internal.CursorFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class LocalMusicBeanCursor extends Cursor<LocalMusicBean> {
    private static final LocalMusicBean_.LocalMusicBeanIdGetter ID_GETTER = LocalMusicBean_.__ID_GETTER;
    private static final int __ID_musicId = LocalMusicBean_.musicId.id;
    private static final int __ID_title = LocalMusicBean_.title.id;
    private static final int __ID_uri = LocalMusicBean_.uri.id;
    private static final int __ID_duration = LocalMusicBean_.duration.id;
    private static final int __ID_createTime = LocalMusicBean_.createTime.id;
    private static final int __ID_playTime = LocalMusicBean_.playTime.id;
    private static final int __ID_artist = LocalMusicBean_.artist.id;
    private static final int __ID_artistId = LocalMusicBean_.artistId.id;
    private static final int __ID_album = LocalMusicBean_.album.id;
    private static final int __ID_albumId = LocalMusicBean_.albumId.id;
    private static final int __ID_playing = LocalMusicBean_.playing.id;
    private static final int __ID_isCollect = LocalMusicBean_.isCollect.id;
    private static final int __ID_isLocal = LocalMusicBean_.isLocal.id;
    private static final int __ID_storageType = LocalMusicBean_.storageType.id;
    private static final int __ID_state = LocalMusicBean_.state.id;
    private static final int __ID_progress = LocalMusicBean_.progress.id;
    private static final int __ID_pinyin = LocalMusicBean_.pinyin.id;

    static final class Factory implements CursorFactory<LocalMusicBean> {
        Factory() {
        }

        @Override // io.objectbox.internal.CursorFactory
        public Cursor<LocalMusicBean> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new LocalMusicBeanCursor(transaction, j, boxStore);
        }
    }

    public LocalMusicBeanCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, LocalMusicBean_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(LocalMusicBean localMusicBean) {
        return ID_GETTER.getId(localMusicBean);
    }

    @Override // io.objectbox.Cursor
    public final long put(LocalMusicBean localMusicBean) {
        String title = localMusicBean.getTitle();
        int i = title != null ? __ID_title : 0;
        String uri = localMusicBean.getUri();
        int i2 = uri != null ? __ID_uri : 0;
        String artist = localMusicBean.getArtist();
        int i3 = artist != null ? __ID_artist : 0;
        String album = localMusicBean.getAlbum();
        collect400000(this.cursor, 0L, 1, i, title, i2, uri, i3, artist, album != null ? __ID_album : 0, album);
        String pinyin = localMusicBean.getPinyin();
        collect313311(this.cursor, 0L, 0, pinyin != null ? __ID_pinyin : 0, pinyin, 0, null, 0, null, 0, null, __ID_createTime, localMusicBean.getCreateTime(), __ID_playTime, localMusicBean.getPlayTime(), __ID_artistId, localMusicBean.getArtistId(), __ID_musicId, localMusicBean.getMusicId(), __ID_duration, localMusicBean.getDuration(), __ID_storageType, localMusicBean.getStorageType(), 0, 0.0f, 0, 0.0d);
        long jCollect313311 = collect313311(this.cursor, localMusicBean.getId(), 2, 0, null, 0, null, 0, null, 0, null, __ID_albumId, localMusicBean.getAlbumId(), __ID_state, localMusicBean.getState(), __ID_progress, localMusicBean.getProgress(), __ID_playing, localMusicBean.isPlaying() ? 1 : 0, __ID_isCollect, localMusicBean.isCollect() ? 1 : 0, __ID_isLocal, localMusicBean.isLocal() ? 1 : 0, 0, 0.0f, 0, 0.0d);
        localMusicBean.setId(jCollect313311);
        return jCollect313311;
    }
}
