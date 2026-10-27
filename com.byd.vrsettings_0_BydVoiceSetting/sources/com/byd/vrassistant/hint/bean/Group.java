package com.byd.vrassistant.hint.bean;

import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Group {
    List<String> items;
    String key;
    String name;

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<String> getItems() {
        return this.items;
    }

    public void setItems(List<String> list) {
        this.items = list;
    }

    public String toString() {
        return "Group{key='" + this.key + "', name='" + this.name + "', items size=" + this.items.size() + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Group)) {
            return false;
        }
        Group group = (Group) obj;
        return Objects.equals(this.key, group.key) && Objects.equals(this.name, group.name) && Objects.equals(this.items, group.items);
    }

    public int hashCode() {
        return Objects.hash(this.key, this.name, this.items);
    }
}
