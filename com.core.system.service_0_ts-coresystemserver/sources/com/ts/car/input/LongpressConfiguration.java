package com.ts.car.input;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LongpressConfiguration {
    private static final int LONGPRESS_SPLIT = 500;
    private static final int MAX_TIME = 10;
    private int mKeyCode;
    private List<Integer> mScenes = new ArrayList();
    private int mLongpressSplit = LONGPRESS_SPLIT;
    private int mMaxtime = 10;

    public LongpressConfiguration(int keyCode) {
        this.mKeyCode = keyCode;
    }

    public void addScenes(int scene) {
        this.mScenes.add(Integer.valueOf(scene));
    }

    public List<Integer> getSceneList() {
        return this.mScenes;
    }

    public int getKeyCode() {
        return this.mKeyCode;
    }

    public int getLongpressSplit() {
        return this.mLongpressSplit;
    }

    public void setLongpressSplit(int longpressSplit) {
        this.mLongpressSplit = longpressSplit;
    }

    public int getMaxtime() {
        return this.mMaxtime;
    }

    public void setMaxtime(int maxtime) {
        this.mMaxtime = maxtime;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("LongpressConfiguration KeyCode: " + this.mKeyCode);
        sb.append(",");
        sb.append(" Scenes: " + this.mScenes);
        sb.append(",");
        sb.append(" LongpressSplit: " + this.mLongpressSplit);
        sb.append(",");
        sb.append(" Maxtime: " + this.mMaxtime);
        return sb.toString();
    }
}
