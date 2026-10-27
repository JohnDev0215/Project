package com.byd.music.db;

import android.app.Application;
import com.blankj.utilcode.util.FileUtils;
import com.byd.music.beans.MyObjectBox;
import com.byd.music.utils.LogUtil;
import io.objectbox.BoxStore;

/* JADX INFO: loaded from: classes5.dex */
public class BoxManager {
    private BoxStore boxStore;

    private static class InstanceHolder {
        private static final BoxManager INSTANCE = new BoxManager();

        private InstanceHolder() {
        }
    }

    public static BoxManager get() {
        return InstanceHolder.INSTANCE;
    }

    public void initAppDB(Application application) {
        try {
            this.boxStore = MyObjectBox.builder().androidContext(application).build();
        } catch (Throwable th) {
            LogUtil.e("BoxManager:initAppDB:数据库异常,清除数据,error = " + th);
            FileUtils.delete(application.getFilesDir());
            this.boxStore = MyObjectBox.builder().androidContext(application).build();
        }
    }

    public BoxStore getBoxStore() {
        return this.boxStore;
    }
}
