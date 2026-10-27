package com.alibaba.android.arouter.routes;

import android.car.XCar;
import android.car.XCarRoute;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import java.util.Map;
import spi.ad;
import spi.bd;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Group$$car_media implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        RouteType routeType = RouteType.PROVIDER;
        map.put(XCarRoute.CAR_MEDIA_CONTROL_SERVICE, RouteMeta.build(routeType, ad.class, XCarRoute.CAR_MEDIA_CONTROL_SERVICE, XCar.CAR_MEDIA_SERVICE, null, -1, Integer.MIN_VALUE));
        map.put(XCarRoute.CAR_MEDIA_SOURCE_SERVICE, RouteMeta.build(routeType, bd.class, XCarRoute.CAR_MEDIA_SOURCE_SERVICE, XCar.CAR_MEDIA_SERVICE, null, -1, Integer.MIN_VALUE));
    }
}
