package com.byd.btsetting.model;

import b.b.m.g.f;
import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayParams extends BydData {
    public String btMaC;
    public String name;
    public int state;

    public CarPlayParams(int i, String str, String str2) {
        this.state = i;
        this.name = str;
        this.btMaC = str2;
    }

    public String toString() {
        return "ProfileParams{state=" + this.state + ", dn=" + f.g(this.name) + ", btMaC=" + f.e(this.btMaC) + '}';
    }
}
