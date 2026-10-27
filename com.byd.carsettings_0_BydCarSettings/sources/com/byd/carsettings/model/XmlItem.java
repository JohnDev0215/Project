package com.byd.carsettings.model;

import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class XmlItem extends HashMap<String, String> {
    public List<XmlItem> list;

    public List<XmlItem> getList() {
        return this.list;
    }

    public void setList(List<XmlItem> list) {
        this.list = list;
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return "XmlItem{list=" + this.list + '}';
    }
}
