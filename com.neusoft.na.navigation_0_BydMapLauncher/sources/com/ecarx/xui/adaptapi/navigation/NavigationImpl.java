package com.ecarx.xui.adaptapi.navigation;

import android.content.Context;
import com.ecarx.xui.adaptapi.navigation.dr.DrAutoByFwkImpl;
import com.ecarx.xui.adaptapi.navigation.dr.DrAutoByMap;
import com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByFwk;
import com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap;
import com.ecarx.xui.adaptapi.navigation.speed.ISpeed;
import com.ecarx.xui.adaptapi.navigation.speed.SpeedImpl;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationImpl implements INavigation {
    private Context mContext;

    public NavigationImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.INavigation
    public ISpeed getSpeedInfo() {
        return new SpeedImpl();
    }

    @Override // com.ecarx.xui.adaptapi.navigation.INavigation
    public IDrAutoByFwk getDrAutoByFwk() {
        return new DrAutoByFwkImpl();
    }

    @Override // com.ecarx.xui.adaptapi.navigation.INavigation
    public IDrAutoByMap getDrAutoByMap() {
        return new DrAutoByMap(this.mContext);
    }
}
