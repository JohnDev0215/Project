package com.byd.vrassistant.hint.bean;

import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Domain {
    List<Group> groups;
    String key;
    String name;

    public Domain() {
    }

    public Domain(String str, String str2, List<Group> list) {
        this.key = str;
        this.name = str2;
        this.groups = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public List<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(List<Group> list) {
        this.groups = list;
    }

    public String toString() {
        return "Domain{key='" + this.key + "', name='" + this.name + "', groups size=" + this.groups.size() + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Domain)) {
            return false;
        }
        Domain domain = (Domain) obj;
        return Objects.equals(this.key, domain.key) && Objects.equals(this.name, domain.name) && Objects.equals(this.groups, domain.groups);
    }

    public int hashCode() {
        return Objects.hash(this.key, this.name, this.groups);
    }
}
