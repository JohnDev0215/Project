package com.byd.music.beans;

import com.byd.music.pinyin.PinyinComparator;
import com.byd.music.pinyin.PinyinItem;
import com.byd.music.utils.Pinyin;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MusicSet implements PinyinItem {
    protected long id;
    protected int musicCount;
    protected String name;
    protected String pinyin;
    private List<LocalMusicBean> mAllMusics = new ArrayList();
    protected PinyinComparator pinyinComparator = new PinyinComparator();

    public MusicSet(long j, int i, String str) {
        this.id = j;
        this.musicCount = i;
        this.name = str;
        String prefix = Pinyin.getPrefix(str);
        this.pinyin = prefix != null ? prefix.toUpperCase() : "#";
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int getMusicCount() {
        return this.musicCount;
    }

    public void setMusicCount(int i) {
        this.musicCount = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void addAllMusic(List<LocalMusicBean> list) {
        this.mAllMusics.clear();
        list.sort(this.pinyinComparator);
        this.mAllMusics.addAll(list);
    }

    public void addAllMusicWithoutSort(List<LocalMusicBean> list) {
        this.mAllMusics.clear();
        this.mAllMusics.addAll(list);
    }

    public void removeMusic(LocalMusicBean localMusicBean) {
        this.mAllMusics.remove(localMusicBean);
    }

    public List<LocalMusicBean> getmAllMusics() {
        return this.mAllMusics;
    }

    public int allMusicSize() {
        return this.mAllMusics.size();
    }

    @Override // com.byd.music.pinyin.PinyinItem
    public String getItemPinyin() {
        return this.pinyin;
    }
}
