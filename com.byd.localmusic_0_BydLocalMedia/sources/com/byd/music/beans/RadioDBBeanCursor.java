package com.byd.music.beans;

import com.byd.music.beans.RadioDBBean_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.internal.CursorFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class RadioDBBeanCursor extends Cursor<RadioDBBean> {
    private static final RadioDBBean_.RadioDBBeanIdGetter ID_GETTER = RadioDBBean_.__ID_GETTER;
    private static final int __ID_type = RadioDBBean_.type.id;
    private static final int __ID_radioFrequency = RadioDBBean_.radioFrequency.id;
    private static final int __ID_radioName = RadioDBBean_.radioName.id;
    private static final int __ID_DAB_SID = RadioDBBean_.DAB_SID.id;
    private static final int __ID_RDS_RT = RadioDBBean_.RDS_RT.id;

    static final class Factory implements CursorFactory<RadioDBBean> {
        Factory() {
        }

        @Override // io.objectbox.internal.CursorFactory
        public Cursor<RadioDBBean> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new RadioDBBeanCursor(transaction, j, boxStore);
        }
    }

    public RadioDBBeanCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, RadioDBBean_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(RadioDBBean radioDBBean) {
        return ID_GETTER.getId(radioDBBean);
    }

    @Override // io.objectbox.Cursor
    public final long put(RadioDBBean radioDBBean) {
        String radioName = radioDBBean.getRadioName();
        int i = radioName != null ? __ID_radioName : 0;
        String rds_rt = radioDBBean.getRDS_RT();
        long jCollect313311 = collect313311(this.cursor, radioDBBean.getId(), 3, i, radioName, rds_rt != null ? __ID_RDS_RT : 0, rds_rt, 0, null, 0, null, __ID_DAB_SID, radioDBBean.getDAB_SID(), __ID_type, radioDBBean.getType(), __ID_radioFrequency, radioDBBean.getRadioFrequency(), 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        radioDBBean.setId(jCollect313311);
        return jCollect313311;
    }
}
