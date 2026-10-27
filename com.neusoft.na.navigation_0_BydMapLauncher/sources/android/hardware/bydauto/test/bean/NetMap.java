package android.hardware.bydauto.test.bean;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NetMap {
    private ArrayList<Integer> ids;
    private int netSag;

    public NetMap() {
    }

    public NetMap(int i, ArrayList<Integer> arrayList) {
        this.netSag = i;
        this.ids = arrayList;
    }

    public int getNetSag() {
        return this.netSag;
    }

    public void setNetSag(int i) {
        this.netSag = i;
    }

    public ArrayList<Integer> getIds() {
        return this.ids;
    }

    public void setIds(ArrayList<Integer> arrayList) {
        this.ids = arrayList;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("NetMap{netSag=");
        stringBuffer.append(this.netSag);
        stringBuffer.append(", ids=").append(this.ids);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
