package com.alibaba.android.arouter.routes;

import android.car.XCarRoute;
import android.car.drivingstate.BydCarUxrManagerImpl;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Group$$x_car implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(XCarRoute.CAR_UXR_SERVICE, RouteMeta.build(RouteType.PROVIDER, BydCarUxrManagerImpl.class, XCarRoute.CAR_UXR_SERVICE, "x_car", null, -1, Integer.MIN_VALUE));
    }
}
