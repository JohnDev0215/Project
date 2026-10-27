package com.byd.data.common.entiry.cloud.config;

import com.byd.data.common.i.f;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseConfigResult<T> implements Serializable {
    public short countryCode;
    public byte flagType = 0;
    public int mccMnc;
    public String uuid;

    public abstract void a(T t);

    public boolean b() {
        return com.byd.data.common.server.b.e().p();
    }

    public boolean d(CountryBean countryBean) {
        f.g("BaseConfigResult", "isMatchCountry bean.countryCode = " + ((int) countryBean.countryCode) + ",countryCode =" + ((int) this.countryCode));
        return this.countryCode == countryBean.countryCode;
    }

    public abstract void e();
}
