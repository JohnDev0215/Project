package com.byd.engineermode.db;

import com.byd.engineermode.bean.ElectricBean;
import com.byd.engineermode.bean.State;

/* JADX INFO: compiled from: Converters.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(State state) {
        return state.ordinal();
    }

    public static State b(int i) {
        return State.values()[i];
    }

    public static ElectricBean.Type c(int i) {
        return ElectricBean.Type.values()[i];
    }

    public static int d(ElectricBean.Type type) {
        return type.ordinal();
    }
}
