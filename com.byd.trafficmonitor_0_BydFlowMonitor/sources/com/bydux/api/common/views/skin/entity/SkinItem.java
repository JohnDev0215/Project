package com.bydux.api.common.views.skin.entity;

import android.view.View;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SkinItem {
    public static final int ITEM_TAG_DEFAULT = -1;
    public View view;
    public long viewTag = -1;
    public List<SkinAttr> attrs = new ArrayList();

    public void apply() {
        if (ListUtils.isEmpty(this.attrs)) {
            return;
        }
        Iterator<SkinAttr> it = this.attrs.iterator();
        while (it.hasNext()) {
            it.next().apply(this.view);
        }
    }

    public void clean() {
        if (ListUtils.isEmpty(this.attrs)) {
            return;
        }
        this.attrs.clear();
    }

    public long getViewTag() {
        return this.viewTag;
    }

    public void setViewTag(int i2) {
        this.viewTag = i2;
    }

    public String toString() {
        return "SkinItem [view=" + this.view.getClass().getSimpleName() + ", attrs=" + this.attrs + "]";
    }
}
