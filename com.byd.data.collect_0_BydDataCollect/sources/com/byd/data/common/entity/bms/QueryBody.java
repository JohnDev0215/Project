package com.byd.data.common.entity.bms;

import com.byd.data.common.entiry.cloud.config.CountryBean;
import com.byd.data.common.g.a;
import com.byd.data.common.http.ApiRetrofit;
import com.byd.data.common.server.b;
import com.byd.data.common.tbox.TBoxDevice;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class QueryBody implements Serializable {
    public String countryCode;
    public String iccid;
    public String mccmnc;
    public String powerType;
    public String transport;
    public String vehicleCode;
    public String vin = a.i().f().i();

    public QueryBody() {
        CountryBean countryBeanG = ApiRetrofit.h().g();
        if (countryBeanG != null) {
            this.countryCode = Integer.toString(countryBeanG.countryCode);
        }
        b bVarE = b.e();
        this.vehicleCode = Integer.toString(bVarE.o);
        TBoxDevice tBoxDeviceJ = a.i().j();
        if (tBoxDeviceJ != null) {
            this.mccmnc = Integer.toString(tBoxDeviceJ.z());
            this.iccid = tBoxDeviceJ.y();
        }
        this.powerType = "" + ((int) bVarE.f3458c);
        this.transport = "" + ((int) bVarE.i());
    }
}
