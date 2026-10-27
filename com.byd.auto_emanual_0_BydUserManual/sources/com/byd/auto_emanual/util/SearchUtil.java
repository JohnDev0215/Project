package com.byd.auto_emanual.util;

import android.content.Context;
import android.text.TextUtils;
import com.byd.auto_emanual.ClassifyDbUtil;
import com.byd.auto_emanual.ContentItem;
import com.byd.auto_emanual.fragment.ClassifyData;
import com.byd.auto_emanual.fragment.NormalDataAdapter;
import com.byd.auto_emanual.search.LevelBase;
import com.byd.auto_emanual.search.LevelOne;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchUtil {
    public static ClassifyData searchByVoidId(Context context, String id) {
        String strQueryHeaderByVoiceId = ClassifyDbUtil.getInstance(context).queryHeaderByVoiceId(id);
        ClassifyData classifyData = new ClassifyData();
        if (!TextUtils.isEmpty(strQueryHeaderByVoiceId)) {
            List<ClassifyData> listQueryByKey = ClassifyDbUtil.getInstance(context).queryByKey(strQueryHeaderByVoiceId);
            classifyData.setContentItems(new ArrayList());
            if (!ListUtil.isNullOrEmpty(listQueryByKey)) {
                List<ContentItem> contentItems = listQueryByKey.get(0).getContentItems();
                if (!ListUtil.isNullOrEmpty(contentItems)) {
                    classifyData.getContentItems().addAll(contentItems);
                    classifyData.setClassify(strQueryHeaderByVoiceId);
                }
            }
        }
        ClassifyData classifyDataSearchByVoicedId = searchByVoicedId(id);
        if (!ListUtil.isNullOrEmpty(classifyDataSearchByVoicedId.getContentItems())) {
            if (classifyData.getContentItems() == null) {
                classifyData.setContentItems(classifyDataSearchByVoicedId.getContentItems());
                classifyData.setClassify(classifyDataSearchByVoicedId.getClassify());
            } else {
                classifyData.getContentItems().addAll(classifyDataSearchByVoicedId.getContentItems());
            }
        }
        return classifyData;
    }

    public static ClassifyData searchByKey(Context context, String key) {
        List<ClassifyData> listQueryByKey = ClassifyDbUtil.getInstance(context).queryByKey(key);
        ClassifyData classifyDataSearchByKey = searchByKey(key);
        ClassifyData classifyData = new ClassifyData();
        classifyData.setContentItems(new ArrayList());
        if (!ListUtil.isNullOrEmpty(listQueryByKey)) {
            List<ContentItem> contentItems = listQueryByKey.get(0).getContentItems();
            if (!ListUtil.isNullOrEmpty(contentItems)) {
                classifyData.getContentItems().addAll(contentItems);
                classifyData.setClassify(key);
            }
        }
        if (!ListUtil.isNullOrEmpty(classifyDataSearchByKey.getContentItems())) {
            classifyData.getContentItems().addAll(classifyDataSearchByKey.getContentItems());
            classifyData.setClassify(key);
        }
        return classifyData;
    }

    private static ClassifyData searchByKey(String key) {
        ArrayList<LevelBase> arrayList = new ArrayList();
        Iterator<LevelOne> it = NormalDataAdapter.menuData.iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().searchByKey(key));
        }
        ClassifyData classifyData = new ClassifyData();
        ArrayList arrayList2 = new ArrayList();
        for (LevelBase levelBase : arrayList) {
            ContentItem contentItem = new ContentItem();
            contentItem.setHeader(levelBase.getName());
            contentItem.setPath(levelBase.getHref());
            contentItem.setContentItemType(ContentItem.ContentItemType.HTML);
            arrayList2.add(contentItem);
        }
        classifyData.setClassify(key);
        classifyData.setContentItems(arrayList2);
        return classifyData;
    }

    private static ClassifyData searchByVoicedId(String id) {
        ArrayList<LevelBase> arrayList = new ArrayList();
        Iterator<LevelOne> it = NormalDataAdapter.menuData.iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().searchByVoiceId(id));
        }
        ClassifyData classifyData = new ClassifyData();
        ArrayList arrayList2 = new ArrayList();
        for (LevelBase levelBase : arrayList) {
            ContentItem contentItem = new ContentItem();
            contentItem.setHeader(levelBase.getName());
            contentItem.setPath(levelBase.getHref());
            contentItem.setContentItemType(ContentItem.ContentItemType.HTML);
            arrayList2.add(contentItem);
        }
        if (arrayList.size() > 0) {
            classifyData.setClassify(((LevelBase) arrayList.get(0)).getName());
        }
        classifyData.setContentItems(arrayList2);
        return classifyData;
    }
}
