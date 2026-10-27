package com.byd.music.beans;

import com.byd.music.beans.RadioEpgDBBean_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.internal.CursorFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class RadioEpgDBBeanCursor extends Cursor<RadioEpgDBBean> {
    private static final RadioEpgDBBean_.RadioEpgDBBeanIdGetter ID_GETTER = RadioEpgDBBean_.__ID_GETTER;
    private static final int __ID_dabSid = RadioEpgDBBean_.dabSid.id;
    private static final int __ID_epgStartTime = RadioEpgDBBean_.epgStartTime.id;
    private static final int __ID_epgEndTime = RadioEpgDBBean_.epgEndTime.id;
    private static final int __ID_desc = RadioEpgDBBean_.desc.id;

    static final class Factory implements CursorFactory<RadioEpgDBBean> {
        Factory() {
        }

        @Override // io.objectbox.internal.CursorFactory
        public Cursor<RadioEpgDBBean> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new RadioEpgDBBeanCursor(transaction, j, boxStore);
        }
    }

    public RadioEpgDBBeanCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, RadioEpgDBBean_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(RadioEpgDBBean radioEpgDBBean) {
        return ID_GETTER.getId(radioEpgDBBean);
    }

    @Override // io.objectbox.Cursor
    public final long put(RadioEpgDBBean radioEpgDBBean) {
        String desc = radioEpgDBBean.getDesc();
        long jCollect313311 = collect313311(this.cursor, radioEpgDBBean.getId(), 3, desc != null ? __ID_desc : 0, desc, 0, null, 0, null, 0, null, __ID_dabSid, radioEpgDBBean.getDabSid(), __ID_epgStartTime, radioEpgDBBean.getEpgStartTime(), __ID_epgEndTime, radioEpgDBBean.getEpgEndTime(), 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        radioEpgDBBean.setId(jCollect313311);
        return jCollect313311;
    }
}
