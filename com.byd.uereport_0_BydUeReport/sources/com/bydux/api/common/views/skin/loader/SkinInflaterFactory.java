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

    private void addSkinView(SkinItem skinItem) {
        this.mSkinItems.add(skinItem);
    }

    private View createView(Context context, String str, AttributeSet attributeSet) {
        View viewCreateView;
        try {
            if (str.indexOf(46) == -1) {
                View viewCreateView2 = ("View".equals(str) || "ViewStub".equals(str)) ? LayoutInflater.from(context).createView(str, "android.view.", attributeSet) : null;
                if (viewCreateView2 == null) {
                    viewCreateView2 = LayoutInflater.from(context).createView(str, "android.widget.", attributeSet);
                }
                if (viewCreateView2 != null) {
                    return viewCreateView2;
                }
                viewCreateView = LayoutInflater.from(context).createView(str, "android.webkit.", attributeSet);
            } else {
                viewCreateView = LayoutInflater.from(context).createView(str, null, attributeSet);
            }
            return viewCreateView;
        } catch (Exception unused) {
            return null;
        }
    }

    private void parseSkinAttr(Context context, AttributeSet attributeSet, View view) {
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < attributeSet.getAttributeCount(); i8++) {
            String attributeName = attributeSet.getAttributeName(i8);
            String attributeValue = attributeSet.getAttributeValue(i8);
            SkinAttr skinAttrIsSupportedAttr = AttrFactory.isSupportedAttr(attributeName);
            if (skinAttrIsSupportedAttr != null && attributeValue.startsWith("@")) {
                try {
                    int i9 = Integer.parseInt(attributeValue.substring(1));
                    SkinAttr skinAttr = AttrFactory.get(skinAttrIsSupportedAttr, attributeName, i9, context.getResources().getResourceEntryName(i9), context.getResources().getResourceTypeName(i9));
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

    public void cleanFragmentItem(long j8) {
        if (ListUtils.isEmpty(this.mSkinItems)) {
            return;
        }
        int i8 = 0;
        while (i8 < this.mSkinItems.size()) {
            SkinItem skinItem = this.mSkinItems.get(i8);
            if (skinItem != null && skinItem.view != null && j8 != -1 && skinItem.getViewTag() == j8) {
                this.mSkinItems.remove(i8);
                i8--;
            }
            i8++;
        }
    }

    public void dynamicAddSkinView(Context context, View view, List<DynamicAttr> list) {
        ArrayList arrayList = new ArrayList();
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        for (DynamicAttr dynamicAttr : list) {
            SkinAttr skinAttrIsSupportedAttr = AttrFactory.isSupportedAttr(dynamicAttr.attrName);
            if (skinAttrIsSupportedAttr != null) {
                int i8 = dynamicAttr.refResId;
                arrayList.add(AttrFactory.get(skinAttrIsSupportedAttr, dynamicAttr.attrName, i8, context.getResources().getResourceEntryName(i8), context.getResources().getResourceTypeName(i8)));
            }
        }
        skinItem.attrs = arrayList;
        addSkinView(skinItem);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        if (!SkinManager.getInstance().getSkinEnable()) {
            return null;
        }
        BydLogHelper.logV(this.TAG, "onCreateView", "skin function enable");
        View viewCreateView = createView(context, str, attributeSet);
        if (viewCreateView == null) {
            return null;
        }
        parseSkinAttr(context, attributeSet, viewCreateView);
        return viewCreateView;
    }

    public void setViewTag(long j8) {
        this.viewTag = j8;
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

    public void dynamicAddSkinView(Context context, View view, String str, int i8) {
        SkinAttr skinAttrIsSupportedAttr = AttrFactory.isSupportedAttr(str);
        if (skinAttrIsSupportedAttr == null) {
            return;
        }
        SkinAttr skinAttr = AttrFactory.get(skinAttrIsSupportedAttr, str, i8, context.getResources().getResourceEntryName(i8), context.getResources().getResourceTypeName(i8));
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        ArrayList arrayList = new ArrayList();
        arrayList.add(skinAttr);
        skinItem.attrs = arrayList;
        addSkinView(skinItem);
    }
}
