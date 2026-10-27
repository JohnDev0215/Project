package com.byd.systemsettings.btsetting.model;

import com.byd.mvp.model.BydData;
import m4.d;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayParams extends BydData {
    public String btMaC;
    public String name;
    public int state;

    public CarPlayParams(int i8, String str, String str2) {
        this.state = i8;
        this.name = str;
        this.btMaC = str2;
    }

    public String toString() {
        return "ProfileParams{state=" + this.state + ", dn=" + d.f(this.name) + ", btMaC=" + d.d(this.btMaC) + '}';
    }
}
