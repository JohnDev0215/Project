package com.byd.auto_emanual.search;

import com.byd.auto_emanual.util.ListUtil;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import org.apache.shiro.config.Ini;

/* JADX INFO: loaded from: classes.dex */
public class LevelTwo extends LevelBase {
    private List<LevelThree> contents;

    public List<LevelThree> getContents() {
        return this.contents;
    }

    public void setContents(List<LevelThree> contents) {
        this.contents = contents;
    }

    @Override // com.byd.auto_emanual.search.LevelBase
    public List<LevelBase> searchByKey(String key) {
        List<LevelBase> listSearchByKey = super.searchByKey(key);
        if (!ListUtil.isNullOrEmpty(this.contents)) {
            Iterator<LevelThree> it = this.contents.iterator();
            while (it.hasNext()) {
                listSearchByKey.addAll(it.next().searchByKey(key));
            }
        }
        return listSearchByKey;
    }

    @Override // com.byd.auto_emanual.search.LevelBase
    public List<LevelBase> searchByVoiceId(String id) {
        List<LevelBase> listSearchByVoiceId = super.searchByVoiceId(id);
        if (!ListUtil.isNullOrEmpty(this.contents)) {
            Iterator<LevelThree> it = this.contents.iterator();
            while (it.hasNext()) {
                listSearchByVoiceId.addAll(it.next().searchByVoiceId(id));
            }
        }
        return listSearchByVoiceId;
    }

    public String toString() {
        return new StringJoiner(", ", LevelTwo.class.getSimpleName() + Ini.SECTION_PREFIX, Ini.SECTION_SUFFIX).add("code='" + this.code + "'").add("href='" + this.href + "'").add("index='" + this.index + "'").add("name='" + this.name + "'").add("resourceCode='" + this.resourceCode + "'").add("contents=" + ListUtil.valueToString(this.contents)).toString();
    }
}
