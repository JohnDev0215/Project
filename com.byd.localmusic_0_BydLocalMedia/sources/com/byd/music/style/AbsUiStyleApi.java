package com.byd.music.style;

import android.content.Context;
import android.content.res.Resources;
import com.bydux.api.common.views.skin.entity.AttrFactory;

/* JADX INFO: loaded from: classes5.dex */
abstract class AbsUiStyleApi implements UiStyleApi {
    abstract String getUiStylePrefixes();

    AbsUiStyleApi() {
    }

    @Override // com.byd.music.style.UiStyleApi
    public int getDrawableId(Context context, int i) {
        int identifier;
        Resources resources = context.getResources();
        try {
            identifier = resources.getIdentifier(getUiStylePrefixes() + resources.getResourceEntryName(i), AttrFactory.DRAWABLE, context.getPackageName());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return identifier != 0 ? identifier : i;
    }

    @Override // com.byd.music.style.UiStyleApi
    public int getColorId(Context context, int i) {
        int identifier;
        Resources resources = context.getResources();
        try {
            identifier = resources.getIdentifier(getUiStylePrefixes() + resources.getResourceEntryName(i), "color", context.getPackageName());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return identifier != 0 ? identifier : i;
    }

    @Override // com.byd.music.style.UiStyleApi
    public int getLayout(Context context, int i) {
        int identifier;
        Resources resources = context.getResources();
        try {
            identifier = resources.getIdentifier(getUiStylePrefixes() + resources.getResourceEntryName(i), "layout", context.getPackageName());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return identifier != 0 ? identifier : i;
    }
}
