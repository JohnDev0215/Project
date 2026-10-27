package com.android.systemui.qs;

/* JADX INFO: loaded from: classes.dex */
public class SceneModeIntroduceBean {
    private String content;
    private String name;

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SceneModeIntroduceBean(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
