package com.alibaba.android.arouter.routes;

import android.car.XCar;
import android.car.XCarRoute;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import java.util.Map;
import spi.ad;
import spi.bd;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Providers$$caruilib implements IProviderGroup {
    @Override // com.alibaba.android.arouter.facade.template.IProviderGroup
    public void loadInto(Map<String, RouteMeta> map) {
        RouteType routeType = RouteType.PROVIDER;
        map.put("android.car.media.IXCarMediaControl", RouteMeta.build(routeType, ad.class, XCarRoute.CAR_MEDIA_CONTROL_SERVICE, XCar.CAR_MEDIA_SERVICE, null, -1, Integer.MIN_VALUE));
        map.put("android.car.media.IXCarMediaSource", RouteMeta.build(routeType, bd.class, XCarRoute.CAR_MEDIA_SOURCE_SERVICE, XCar.CAR_MEDIA_SERVICE, null, -1, Integer.MIN_VALUE));
    }
}
