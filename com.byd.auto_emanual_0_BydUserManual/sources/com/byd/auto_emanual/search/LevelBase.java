package com.byd.auto_emanual.search;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LevelBase {
    protected String code;
    protected String href;
    protected String index;
    protected String name;
    protected String resourceCode;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceCode() {
        return this.resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public List<LevelBase> searchByKey(String key) {
        ArrayList arrayList = new ArrayList();
        if ((this.name.contains(key) || this.name.toUpperCase().contains(key.toUpperCase()) || this.name.toLowerCase().contains(key.toLowerCase())) && !TextUtils.isEmpty(this.href)) {
            arrayList.add(this);
        }
        return arrayList;
    }

    public List<LevelBase> searchByVoiceId(String id) {
        ArrayList arrayList = new ArrayList();
        if (id.equals(this.resourceCode)) {
            arrayList.add(this);
        }
        return arrayList;
    }
}
