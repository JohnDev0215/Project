package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bydux.api.common.views.ctrl.BydClock;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes3.dex */
public class AttrBydClock extends SkinAttr {
    private final String CLOCK_BACKGROUND = "byd_clock_background";
    private final String CLOCK_HOUR = "byd_clock_hour";
    private final String CLOCK_MINUTE = "byd_clock_minute";
    private final String CLOCK_SECOND = "byd_clock_second";
    private String mAttrName;

    public AttrBydClock(String str) {
        this.mAttrName = str;
    }

    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if (view instanceof BydClock) {
            BydClock bydClock = (BydClock) view;
            if ("drawable".equals(this.attrValueTypeName) || SkinAttr.RES_TYPE_NAME_MIPMAP.equals(this.attrValueTypeName)) {
                Drawable drawable = "drawable".equals(this.attrValueTypeName) ? SkinManager.getInstance().getDrawable(this.attrValueRefId) : SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                if ("byd_clock_background".equals(this.mAttrName)) {
                    bydClock.setClockBackground(drawable);
                    return;
                }
                if ("byd_clock_hour".equals(this.mAttrName)) {
                    bydClock.setClockHour(drawable);
                } else if ("byd_clock_minute".equals(this.mAttrName)) {
                    bydClock.setClockMinute(drawable);
                } else if ("byd_clock_second".equals(this.mAttrName)) {
                    bydClock.setClockSecond(drawable);
                }
            }
        }
    }
}
