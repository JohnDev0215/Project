package com.byd.auto_emanual.fragment;

import com.byd.auto_emanual.ContentItem;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ClassifyData {
    private String modelType;
    private String modelTypeName;
    private List<ContentItem> newbieList;

    public String getClassify() {
        return this.modelTypeName;
    }

    public void setClassify(String classify) {
        this.modelTypeName = classify;
    }

    public String getModelType() {
        return this.modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getModelTypeName() {
        return this.modelTypeName;
    }

    public void setModelTypeName(String modelTypeName) {
        this.modelTypeName = modelTypeName;
    }

    public List<ContentItem> getContentItems() {
        return this.newbieList;
    }

    public void setContentItems(List<ContentItem> contentItems) {
        this.newbieList = contentItems;
    }

    public List<ContentItem> getNewbieList() {
        return this.newbieList;
    }

    public void setNewbieList(List<ContentItem> newbieList) {
        this.newbieList = newbieList;
    }

    public ContentItem findByTitle(String title) {
        for (ContentItem contentItem : this.newbieList) {
            if (title.equals(contentItem.getHeader())) {
                return contentItem;
            }
        }
        return this.newbieList.get(0);
    }

    public int getIndexByHeader(String header) {
        for (int i = 0; i < this.newbieList.size(); i++) {
            if (header.equals(this.newbieList.get(i).getHeader())) {
                return i;
            }
        }
        return 0;
    }

    public ContentItem getByPosition(int position) {
        return this.newbieList.get(position);
    }

    public int getContentItemSize() {
        List<ContentItem> list = this.newbieList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
