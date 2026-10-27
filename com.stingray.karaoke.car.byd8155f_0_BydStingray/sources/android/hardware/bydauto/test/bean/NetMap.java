package android.hardware.bydauto.test.bean;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NetMap {
    private ArrayList<Integer> ids;
    private int netSag;

    public NetMap() {
    }

    public ArrayList<Integer> getIds() {
        return this.ids;
    }

    public int getNetSag() {
        return this.netSag;
    }

    public void setIds(ArrayList<Integer> arrayList) {
        this.ids = arrayList;
    }

    public void setNetSag(int i7) {
        this.netSag = i7;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("NetMap{netSag=");
        stringBuffer.append(this.netSag);
        stringBuffer.append(", ids=");
        stringBuffer.append(this.ids);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public NetMap(int i7, ArrayList<Integer> arrayList) {
        this.netSag = i7;
        this.ids = arrayList;
    }
}
