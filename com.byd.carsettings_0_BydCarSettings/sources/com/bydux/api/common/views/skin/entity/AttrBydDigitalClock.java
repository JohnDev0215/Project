package com.bydux.api.common.views.skin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bydux.api.common.views.ctrl.BydDigitalClock;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes3.dex */
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

    public AttrBydDigitalClock(String str) {
        this.mAttrName = str;
    }

    @Override // com.bydux.api.common.views.skin.entity.SkinAttr
    public void apply(View view) {
        if (view instanceof BydDigitalClock) {
            BydDigitalClock bydDigitalClock = (BydDigitalClock) view;
            if ("drawable".equals(this.attrValueTypeName) || SkinAttr.RES_TYPE_NAME_MIPMAP.equals(this.attrValueTypeName)) {
                Drawable drawable = "drawable".equals(this.attrValueTypeName) ? SkinManager.getInstance().getDrawable(this.attrValueRefId) : SkinManager.getInstance().getMipmapDrawable(this.attrValueRefId);
                if ("byd_dgclock_num0".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum0(drawable);
                    return;
                }
                if ("byd_dgclock_num1".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum1(drawable);
                    return;
                }
                if ("byd_dgclock_num2".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum2(drawable);
                    return;
                }
                if ("byd_dgclock_num3".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum3(drawable);
                    return;
                }
                if ("byd_dgclock_num4".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum4(drawable);
                    return;
                }
                if ("byd_dgclock_num5".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum5(drawable);
                    return;
                }
                if ("byd_dgclock_num6".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum6(drawable);
                    return;
                }
                if ("byd_dgclock_num7".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum7(drawable);
                    return;
                }
                if ("byd_dgclock_num8".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum8(drawable);
                    return;
                }
                if ("byd_dgclock_num9".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockNum9(drawable);
                    return;
                }
                if ("byd_dgclock_am".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockAm(drawable);
                } else if ("byd_dgclock_pm".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockPm(drawable);
                } else if ("byd_dgclock_colon".equals(this.mAttrName)) {
                    bydDigitalClock.setDigitalClockColon(drawable);
                }
            }
        }
    }
}
