package com.ecarx.xui.adaptapi.navigation;

import com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByFwk;
import com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap;
import com.ecarx.xui.adaptapi.navigation.speed.ISpeed;

/* JADX INFO: loaded from: classes2.dex */
public interface INavigation {
    IDrAutoByFwk getDrAutoByFwk();

    IDrAutoByMap getDrAutoByMap();

    ISpeed getSpeedInfo();
}
