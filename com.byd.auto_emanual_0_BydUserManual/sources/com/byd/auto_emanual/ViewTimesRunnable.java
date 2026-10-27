package com.byd.auto_emanual;

import com.byd.auto_emanual.fragment.PageData;
import com.byd.auto_emanual.fragment.RecommendModel;
import com.byd.auto_emanual.util.MLog;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ViewTimesRunnable extends ModelRunnable {
    private static final String TAG = "ViewTimesRunnable";
    private String managerId;

    @Override // com.byd.auto_emanual.ModelRunnable
    public PageData transformData(String resultData, String classify) {
        return null;
    }

    private ViewTimesRunnable(String path, String classify, RecommendModel.ModelCallback callback) {
        super(path, classify, callback);
    }

    public ViewTimesRunnable(String path, String classify, RecommendModel.ModelCallback callback, String managerId) {
        this(path, classify, callback);
        this.managerId = managerId;
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    protected Map<String, String> createExtBody() {
        HashMap map = new HashMap();
        map.put("managerId", this.managerId);
        return map;
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    protected void loadStart() {
        MLog.v(TAG, "loadStart: managerId = " + this.managerId);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    protected void loadComplete() {
        MLog.v(TAG, "loadComplete managerId = " + this.managerId);
    }

    protected void handleData() {
        MLog.v(TAG, "handleData managerId = " + this.managerId);
    }
}
