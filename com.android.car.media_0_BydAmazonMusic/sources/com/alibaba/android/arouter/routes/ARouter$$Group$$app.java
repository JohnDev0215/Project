package com.alibaba.android.arouter.routes;

import android.car.XCarRoute;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import java.util.Map;
import spi.dd;
import spi.o0;
import spi.p0;
import spi.va;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Group$$app implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        RouteType routeType = RouteType.PROVIDER;
        map.put(XCarRoute.CAR_APPLICATION_STUB, RouteMeta.build(routeType, o0.class, XCarRoute.CAR_APPLICATION_STUB, "app", null, -1, Integer.MIN_VALUE));
        map.put(XCarRoute.CAR_MEDIA_SERVICE, RouteMeta.build(routeType, p0.class, XCarRoute.CAR_MEDIA_SERVICE, "app", null, -1, Integer.MIN_VALUE));
        map.put(XCarRoute.CAR_MEDIA_STATE_LISTENER, RouteMeta.build(routeType, dd.class, XCarRoute.CAR_MEDIA_STATE_LISTENER, "app", null, -1, Integer.MIN_VALUE));
        map.put(XCarRoute.CAR_TEST_SETTING, RouteMeta.build(routeType, va.class, XCarRoute.CAR_TEST_SETTING, "app", null, -1, Integer.MIN_VALUE));
    }
}
