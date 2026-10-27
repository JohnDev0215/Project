package android.hardware.bydauto.test.bean;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NetMap {
    private ArrayList<Integer> ids;
    private int netSag;

    public NetMap() {
    }

    public NetMap(int netSag, ArrayList<Integer> ids) {
        this.netSag = netSag;
        this.ids = ids;
    }

    public int getNetSag() {
        return this.netSag;
    }

    public void setNetSag(int netSag) {
        this.netSag = netSag;
    }

    public ArrayList<Integer> getIds() {
        return this.ids;
    }

    public void setIds(ArrayList<Integer> ids) {
        this.ids = ids;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("NetMap{");
        sb.append("netSag=");
        sb.append(this.netSag);
        sb.append(", ids=");
        sb.append(this.ids);
        sb.append('}');
        return sb.toString();
    }
}
