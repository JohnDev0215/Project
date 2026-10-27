package com.byd.vrassistant.bean.jsonparse;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class InteractionBean {
    int id;
    List<String> interaction_type;
    String text;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public List<String> getInteraction_type() {
        return this.interaction_type;
    }

    public void setInteraction_type(List<String> list) {
        this.interaction_type = list;
    }

    public String toString() {
        return "InteractionBean{id=" + this.id + ", text='" + this.text + "', interaction_type=" + this.interaction_type + '}';
    }
}
