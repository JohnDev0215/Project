package com.byd.music.beans;

import com.byd.music.beans.TrackRecordBean_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.internal.CursorFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class TrackRecordBeanCursor extends Cursor<TrackRecordBean> {
    private static final TrackRecordBean_.TrackRecordBeanIdGetter ID_GETTER = TrackRecordBean_.__ID_GETTER;
    private static final int __ID_track_id = TrackRecordBean_.track_id.id;
    private static final int __ID_duration = TrackRecordBean_.duration.id;
    private static final int __ID_played_secs = TrackRecordBean_.played_secs.id;
    private static final int __ID_started_at = TrackRecordBean_.started_at.id;
    private static final int __ID_ended_at = TrackRecordBean_.ended_at.id;
    private static final int __ID_play_type = TrackRecordBean_.play_type.id;
    private static final int __ID_rec_src = TrackRecordBean_.rec_src.id;
    private static final int __ID_rec_track = TrackRecordBean_.rec_track.id;
    private static final int __ID_play_source = TrackRecordBean_.play_source.id;
    private static final int __ID_album_title = TrackRecordBean_.album_title.id;
    private static final int __ID_track_title = TrackRecordBean_.track_title.id;
    private static final int __ID_imgUrl = TrackRecordBean_.imgUrl.id;
    private static final int __ID_album_id = TrackRecordBean_.album_id.id;
    private static final int __ID_content_type = TrackRecordBean_.content_type.id;
    private static final int __ID_totalDuration = TrackRecordBean_.totalDuration.id;

    static final class Factory implements CursorFactory<TrackRecordBean> {
        Factory() {
        }

        @Override // io.objectbox.internal.CursorFactory
        public Cursor<TrackRecordBean> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new TrackRecordBeanCursor(transaction, j, boxStore);
        }
    }

    public TrackRecordBeanCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, TrackRecordBean_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(TrackRecordBean trackRecordBean) {
        return ID_GETTER.getId(trackRecordBean);
    }

    @Override // io.objectbox.Cursor
    public final long put(TrackRecordBean trackRecordBean) {
        String rec_src = trackRecordBean.getRec_src();
        int i = rec_src != null ? __ID_rec_src : 0;
        String rec_track = trackRecordBean.getRec_track();
        int i2 = rec_track != null ? __ID_rec_track : 0;
        String play_source = trackRecordBean.getPlay_source();
        int i3 = play_source != null ? __ID_play_source : 0;
        String album_title = trackRecordBean.getAlbum_title();
        collect400000(this.cursor, 0L, 1, i, rec_src, i2, rec_track, i3, play_source, album_title != null ? __ID_album_title : 0, album_title);
        String track_title = trackRecordBean.getTrack_title();
        int i4 = track_title != null ? __ID_track_title : 0;
        String imgUrl = trackRecordBean.getImgUrl();
        collect313311(this.cursor, 0L, 0, i4, track_title, imgUrl != null ? __ID_imgUrl : 0, imgUrl, 0, null, 0, null, __ID_track_id, trackRecordBean.getTrack_id(), __ID_started_at, trackRecordBean.getStarted_at(), __ID_ended_at, trackRecordBean.getEnded_at(), __ID_play_type, trackRecordBean.getPlay_type(), __ID_content_type, trackRecordBean.getContent_type(), __ID_totalDuration, trackRecordBean.getTotalDuration(), 0, 0.0f, __ID_duration, trackRecordBean.getDuration());
        long jCollect313311 = collect313311(this.cursor, trackRecordBean.getId(), 2, 0, null, 0, null, 0, null, 0, null, __ID_album_id, trackRecordBean.getAlbum_id(), 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, __ID_played_secs, trackRecordBean.getPlayed_secs());
        trackRecordBean.setId(jCollect313311);
        return jCollect313311;
    }
}
