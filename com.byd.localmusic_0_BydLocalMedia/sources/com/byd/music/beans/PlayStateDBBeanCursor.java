package com.byd.music.beans;

import com.byd.music.beans.PlayStateDBBean_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.internal.CursorFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PlayStateDBBeanCursor extends Cursor<PlayStateDBBean> {
    private static final PlayStateDBBean_.PlayStateDBBeanIdGetter ID_GETTER = PlayStateDBBean_.__ID_GETTER;
    private static final int __ID_playState = PlayStateDBBean_.playState.id;
    private static final int __ID_timeStamp = PlayStateDBBean_.timeStamp.id;

    static final class Factory implements CursorFactory<PlayStateDBBean> {
        Factory() {
        }

        @Override // io.objectbox.internal.CursorFactory
        public Cursor<PlayStateDBBean> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new PlayStateDBBeanCursor(transaction, j, boxStore);
        }
    }

    public PlayStateDBBeanCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, PlayStateDBBean_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(PlayStateDBBean playStateDBBean) {
        return ID_GETTER.getId(playStateDBBean);
    }

    @Override // io.objectbox.Cursor
    public final long put(PlayStateDBBean playStateDBBean) {
        long jCollect004000 = collect004000(this.cursor, playStateDBBean.getId(), 3, __ID_timeStamp, playStateDBBean.getTimeStamp(), __ID_playState, playStateDBBean.getPlayState() ? 1L : 0L, 0, 0L, 0, 0L);
        playStateDBBean.setId(jCollect004000);
        return jCollect004000;
    }
}
