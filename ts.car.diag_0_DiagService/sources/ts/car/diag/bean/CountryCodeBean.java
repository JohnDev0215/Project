package ts.car.diag.bean;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class CountryCodeBean {
    private String code;
    private String name;

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "CountryCodeBean{name='" + this.name + "', code='" + this.code + "'}";
    }
}
