package com.bydux.api.common.views.skin.loader;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import com.bydux.api.common.views.skin.entity.DynamicAttr;
import com.bydux.api.common.views.skin.entity.SkinAttr;
import com.bydux.api.common.views.skin.entity.SkinItem;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SkinInflaterFactory implements LayoutInflater.Factory {
    private final String TAG = getClass().getSimpleName();
    private List<SkinItem> mSkinItems = new ArrayList();
    private long viewTag = -1;

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        if (!SkinManager.getInstance().getSkinEnable()) {
            return null;
        }
        BydLogHelper.logV(this.TAG, "onCreateView", "skin function enable");
        View viewCreateView = createView(context, name, attrs);
        if (viewCreateView == null) {
            return null;
        }
        parseSkinAttr(context, attrs, viewCreateView);
        return viewCreateView;
    }

    private View createView(Context context, String name, AttributeSet attrs) {
        View viewCreateView;
        try {
            if (name.indexOf(46) == -1) {
                View viewCreateView2 = ("View".equals(name) || "ViewStub".equals(name)) ? LayoutInflater.from(context).createView(name, "android.view.", attrs) : null;
                if (viewCreateView2 == null) {
                    viewCreateView2 = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }
                if (viewCreateView2 != null) {
                    return viewCreateView2;
                }
                viewCreateView = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
            } else {
                viewCreateView = LayoutInflater.from(context).createView(name, null, attrs);
            }
            return viewCreateView;
        } catch (Exception unused) {
            return null;
        }
    }

    private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            SkinAttr skinAttrIsSupportedAttr = AttrFactory.isSupportedAttr(attributeName);
            if (skinAttrIsSupportedAttr != null && attributeValue.startsWith("@")) {
                try {
                    int i2 = Integer.parseInt(attributeValue.substring(1));
                    SkinAttr skinAttr = AttrFactory.get(skinAttrIsSupportedAttr, attributeName, i2, context.getResources().getResourceEntryName(i2), context.getResources().getResourceTypeName(i2));
                    if (skinAttr != null) {
                        arrayList.add(skinAttr);
                    }
                } catch (Resources.NotFoundException | NumberFormatException unused) {
                }
            }
        }
        if (ListUtils.isEmpty(arrayList)) {
            return;
        }
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        skinItem.attrs = arrayList;
        skinItem.viewTag = this.viewTag;
        this.mSkinItems.add(skinItem);
        if (SkinManager.getInstance().isDefaultSkin()) {
            return;
        }
        skinItem.apply();
    }

    private void addSkinView(SkinItem item) {
        this.mSkinItems.add(item);
    }

    public void applySkin() {
        if (ListUtils.isEmpty(this.mSkinItems)) {
            return;
        }
        for (SkinItem skinItem : this.mSkinItems) {
            if (skinItem.view != null) {
                skinItem.apply();
            }
        }
    }

    public void clean() {
        if (ListUtils.isEmpty(this.mSkinItems)) {
            return;
        }
        for (SkinItem skinItem : this.mSkinItems) {
            if (skinItem.view != null) {
                skinItem.clean();
            }
        }
        this.mSkinItems.clear();
    }

    public void cleanFragmentItem(long tag) {
        if (ListUtils.isEmpty(this.mSkinItems)) {
            return;
        }
        int i = 0;
        while (i < this.mSkinItems.size()) {
            SkinItem skinItem = this.mSkinItems.get(i);
            if (skinItem != null && skinItem.view != null && tag != -1 && skinItem.getViewTag() == tag) {
                this.mSkinItems.remove(i);
                i--;
            }
            i++;
        }
    }

    public void clean(View view) {
        if (ListUtils.isEmpty(this.mSkinItems)) {
            return;
        }
        for (SkinItem skinItem : this.mSkinItems) {
            if (skinItem.view != null) {
                skinItem.clean();
            }
        }
        this.mSkinItems.clear();
    }

    public void dynamicAddSkinView(Context context, View view, List<DynamicAttr> attrList) {
        ArrayList arrayList = new ArrayList();
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        for (DynamicAttr dynamicAttr : attrList) {
            SkinAttr skinAttrIsSupportedAttr = AttrFactory.isSupportedAttr(dynamicAttr.attrName);
            if (skinAttrIsSupportedAttr != null) {
                int i = dynamicAttr.refResId;
                arrayList.add(AttrFactory.get(skinAttrIsSupportedAttr, dynamicAttr.attrName, i, context.getResources().getResourceEntryName(i), context.getResources().getResourceTypeName(i)));
            }
        }
        skinItem.attrs = arrayList;
        addSkinView(skinItem);
    }

    public void dynamicAddSkinView(Context context, View view, String attrName, int attrResId) {
        SkinAttr skinAttrIsSupportedAttr = AttrFactory.isSupportedAttr(attrName);
        if (skinAttrIsSupportedAttr == null) {
            return;
        }
        SkinAttr skinAttr = AttrFactory.get(skinAttrIsSupportedAttr, attrName, attrResId, context.getResources().getResourceEntryName(attrResId), context.getResources().getResourceTypeName(attrResId));
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        ArrayList arrayList = new ArrayList();
        arrayList.add(skinAttr);
        skinItem.attrs = arrayList;
        addSkinView(skinItem);
    }

    public void setViewTag(long viewTag) {
        this.viewTag = viewTag;
    }
}
