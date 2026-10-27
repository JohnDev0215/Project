package com.byd.auto_emanual;

import com.byd.auto_emanual.fragment.ClassifyData;
import com.byd.auto_emanual.fragment.PageData;
import com.byd.auto_emanual.fragment.RecommendModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StartModelRunnable extends ModelRunnable {
    public StartModelRunnable(String path, String classify, RecommendModel.ModelCallback callback) {
        super(path, classify, callback);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    public PageData transformData(String resultData, String page) {
        return new PageData(page, (List) new Gson().fromJson(resultData, new TypeToken<List<ClassifyData>>() { // from class: com.byd.auto_emanual.StartModelRunnable.1
        }.getType()));
    }
}
