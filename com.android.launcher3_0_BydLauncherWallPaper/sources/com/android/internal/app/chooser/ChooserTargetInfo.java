package com.android.internal.app.chooser;

import android.service.chooser.ChooserTarget;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes4.dex */
public interface ChooserTargetInfo extends TargetInfo {
    ChooserTarget getChooserTarget();

    float getModifiedScore();

    default boolean isSimilar(ChooserTargetInfo chooserTargetInfo) {
        if (chooserTargetInfo == null) {
            return false;
        }
        ChooserTarget chooserTarget = getChooserTarget();
        ChooserTarget chooserTarget2 = chooserTargetInfo.getChooserTarget();
        return chooserTarget != null && chooserTarget2 != null && chooserTarget.getComponentName().equals(chooserTarget2.getComponentName()) && TextUtils.equals(getDisplayLabel(), chooserTargetInfo.getDisplayLabel()) && TextUtils.equals(getExtendedInfo(), chooserTargetInfo.getExtendedInfo());
    }
}
