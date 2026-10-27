package com.byd.auto_emanual;

import com.byd.auto_emanual.fragment.ClassifyData;
import com.byd.auto_emanual.fragment.PageData;
import com.byd.auto_emanual.fragment.RecommendModel;
import com.byd.auto_emanual.util.ListUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecommendModelRunnable extends ModelRunnable {
    public RecommendModelRunnable(String path, String page, RecommendModel.ModelCallback callback) {
        super(path, page, callback);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    public PageData transformData(String resultData, String page) {
        List list = (List) new Gson().fromJson(resultData, new TypeToken<List<ClassifyData>>() { // from class: com.byd.auto_emanual.RecommendModelRunnable.1
        }.getType());
        if (ListUtil.isNullOrEmpty(list)) {
            return null;
        }
        return new PageData(page, list);
    }
}
