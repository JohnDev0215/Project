package ts.car.service.upgrade.data;

/* JADX INFO: loaded from: classes.dex */
public class UpgradeRequest {
    private int mTrigger;
    private String mUrl;

    public UpgradeRequest() {
    }

    public UpgradeRequest(int trigger, String url) {
        setTrigger(trigger);
        setUrl(url);
    }

    public void setTrigger(int trigger) {
        this.mTrigger = trigger;
    }

    public int getTrigger() {
        return this.mTrigger;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getUrl() {
        return this.mUrl;
    }
}
