package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bydux.api.common.views.ctrl.BydClock;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes2.dex */
public class AttrBydClock extends SkinAttr {
    private final String CLOCK_BACKGROUND = "byd_clock_background";
    private final String CLOCK_HOUR = "byd_clock_hour";
    private final String CLOCK_MINUTE = "byd_clock_minute";
    private final String CLOCK_SECOND = "byd_clock_second";
    private String mAttrName;

    public AttrBydClock(String attrName) {
        this.mAttrName = attrName;
    }

    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        Drawable mipmapDrawable;
        if (view instanceof BydClock) {
            BydClock bydClock = (BydClock) view;
            if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName) || "mipmap".equals(this.attrValueTypeName)) {
                if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName)) {
                    mipmapDrawable = SkinManager.getInstance().getDrawable(this.attrValueRefId);
                } else {
                    mipmapDrawable = SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                }
                if ("byd_clock_background".equals(this.mAttrName)) {
                    bydClock.setClockBackground(mipmapDrawable);
                    return;
                }
                if ("byd_clock_hour".equals(this.mAttrName)) {
                    bydClock.setClockHour(mipmapDrawable);
                } else if ("byd_clock_minute".equals(this.mAttrName)) {
                    bydClock.setClockMinute(mipmapDrawable);
                } else if ("byd_clock_second".equals(this.mAttrName)) {
                    bydClock.setClockSecond(mipmapDrawable);
                }
            }
        }
    }
}
