package com.byd.auto_emanual;

import com.byd.auto_emanual.fragment.ClassifyData;
import com.byd.auto_emanual.fragment.PageData;
import com.byd.auto_emanual.fragment.RecommendModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchHotRunnable extends ModelRunnable {
    public SearchHotRunnable(String path, String classify, RecommendModel.ModelCallback callback) {
        super(path, classify, callback);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    public PageData transformData(String resultData, String page) {
        List<ContentItem> list = (List) new Gson().fromJson(resultData, new TypeToken<List<ContentItem>>() { // from class: com.byd.auto_emanual.SearchHotRunnable.1
        }.getType());
        ClassifyData classifyData = new ClassifyData();
        classifyData.setContentItems(list);
        ArrayList arrayList = new ArrayList();
        arrayList.add(classifyData);
        return new PageData(page, arrayList);
    }
}
