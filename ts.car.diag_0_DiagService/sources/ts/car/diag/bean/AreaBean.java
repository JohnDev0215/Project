package ts.car.diag.bean;

import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class AreaBean {
    private String areaCode;
    private List<CountryCodeBean> countryCodes;

    public String getAreaCode() {
        return this.areaCode;
    }

    public List<CountryCodeBean> getCountryCodes() {
        return this.countryCodes;
    }

    public void setAreaCode(String str) {
        this.areaCode = str;
    }

    public void setCountryCodes(List<CountryCodeBean> list) {
        this.countryCodes = list;
    }

    public String toString() {
        return "ItemsBean{areaCode='" + this.areaCode + "', countryCodes=" + this.countryCodes + '}';
    }
}
