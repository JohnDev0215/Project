package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bydux.api.common.views.ctrl.BydDigitalClock;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class AttrBydDigitalClock extends SkinAttr {
    private String mAttrName;
    private final String DGCLOCK_NUM0 = "byd_dgclock_num0";
    private final String DGCLOCK_NUM1 = "byd_dgclock_num1";
    private final String DGCLOCK_NUM2 = "byd_dgclock_num2";
    private final String DGCLOCK_NUM3 = "byd_dgclock_num3";
    private final String DGCLOCK_NUM4 = "byd_dgclock_num4";
    private final String DGCLOCK_NUM5 = "byd_dgclock_num5";
    private final String DGCLOCK_NUM6 = "byd_dgclock_num6";
    private final String DGCLOCK_NUM7 = "byd_dgclock_num7";
    private final String DGCLOCK_NUM8 = "byd_dgclock_num8";
    private final String DGCLOCK_NUM9 = "byd_dgclock_num9";
    private final String DGCLOCK_AM = "byd_dgclock_am";
    private final String DGCLOCK_PM = "byd_dgclock_pm";
    private final String DGCLOCK_COLON = "byd_dgclock_colon";

    public AttrBydDigitalClock(String attrName) {
        this.mAttrName = attrName;
    }

    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        Drawable mipmapDrawable;
        if (view instanceof BydDigitalClock) {
            BydDigitalClock bydDigitalClock = (BydDigitalClock) view;
            if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName) || "mipmap".equals(this.attrValueTypeName)) {
                if (AttrFactory.DRAWABLE.equals(this.attrValueTypeName)) {
                    mipmapDrawable = SkinManager.getInstance().getDrawable(this.attrValueRefId);
                } else {
                    mipmapDrawable = SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                }
                if ("byd_dgclock_num0".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum0(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_num1".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum1(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_num2".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum2(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_num3".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum3(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_num4".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum4(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_num5".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum5(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_num6".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum6(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_num7".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum7(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_num8".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum8(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_num9".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum9(mipmapDrawable);
                    return;
                }
                if ("byd_dgclock_am".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockAm(mipmapDrawable);
                } else if ("byd_dgclock_pm".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockPm(mipmapDrawable);
                } else if ("byd_dgclock_colon".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockColon(mipmapDrawable);
                }
            }
        }
    }
}
