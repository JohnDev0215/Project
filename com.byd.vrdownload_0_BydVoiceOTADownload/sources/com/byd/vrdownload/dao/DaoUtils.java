package com.byd.vrdownload.dao;

import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrdownload.utils.LogUtils;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class DaoUtils {
    private static final String TAG = "DaoUtils";

    public static void maintainDatabase(Maintainer<LanguageUpdate> maintainer) {
        Iterator<LanguageUpdate> it = maintainer.iterator();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = 0;
        while (it.hasNext()) {
            try {
                maintainer.onRefreshedValidData(it.next());
                j++;
            } finally {
                LogUtils.i(TAG, "maintainDatabase: " + (System.currentTimeMillis() - jCurrentTimeMillis));
                LogUtils.i(TAG, "maintainDatabase: " + j);
                maintainer.onFinishMaintain();
            }
        }
    }
}
