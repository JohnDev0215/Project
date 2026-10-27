package com.alibaba.android.arouter.routes;

import android.car.XCarRoute;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import java.util.Map;
import spi.dd;
import spi.o0;
import spi.va;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Providers$$main implements IProviderGroup {
    @Override // com.alibaba.android.arouter.facade.template.IProviderGroup
    public void loadInto(Map<String, RouteMeta> map) {
        RouteType routeType = RouteType.PROVIDER;
        map.put("android.car.IXCarApplicationStub", RouteMeta.build(routeType, o0.class, XCarRoute.CAR_APPLICATION_STUB, "app", null, -1, Integer.MIN_VALUE));
        map.put("android.car.media.IXCarMediaStateListener", RouteMeta.build(routeType, dd.class, XCarRoute.CAR_MEDIA_STATE_LISTENER, "app", null, -1, Integer.MIN_VALUE));
        map.put("android.car.test.ITestViewControl", RouteMeta.build(routeType, va.class, XCarRoute.CAR_TEST_SETTING, "app", null, -1, Integer.MIN_VALUE));
    }
}
