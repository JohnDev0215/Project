package com.byd.auto_emanual.search;

import java.util.StringJoiner;
import org.apache.shiro.config.Ini;

/* JADX INFO: loaded from: classes.dex */
public class LevelThree extends LevelBase {
    public String toString() {
        return new StringJoiner(", ", LevelThree.class.getSimpleName() + Ini.SECTION_PREFIX, Ini.SECTION_SUFFIX).add("code='" + this.code + "'").add("href='" + this.href + "'").add("index='" + this.index + "'").add("name='" + this.name + "'").toString();
    }
}
