package com.visualon.OSMPSubTitle.DataObject;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: loaded from: classes7.dex */
public class VOSubtitleInfo {
    public float rectBottom;
    public String rectBottomString;
    public float rectLeft;
    public String rectLeftString;
    public float rectRight;
    public String rectRightString;
    public float rectTop;
    public String rectTopString;
    public String jsonString = "";
    public int timeStamp = 0;
    public int maxDuration = -1;
    public boolean isForID3 = false;
    public Rect boundingBoxRect = null;
    public ArrayList<VOSubtitleInfoEntry> subtitleEntryList = new ArrayList<>();

    public int getTimeStamp() {
        return this.timeStamp;
    }

    private int getMaxDuration() {
        int i = 0;
        if (this.subtitleEntryList == null) {
            return 0;
        }
        while (true) {
            if (i >= this.subtitleEntryList.size()) {
                break;
            }
            int i2 = this.subtitleEntryList.get(i).duration;
            if (i2 == -1) {
                this.maxDuration = -1;
                break;
            }
            if (i2 > this.maxDuration) {
                this.maxDuration = i2;
            }
            i++;
        }
        return this.maxDuration;
    }

    public ArrayList<VOSubtitleInfoEntry> getSubtitleEntryList() {
        return this.subtitleEntryList;
    }

    public void sortByZIndex() {
        ArrayList<VOSubtitleInfoEntry> arrayList = this.subtitleEntryList;
        if (arrayList == null) {
            return;
        }
        Collections.sort(arrayList, new SortByZOrder());
    }

    private class SortByZOrder implements Comparator<VOSubtitleInfoEntry> {
        private SortByZOrder() {
        }

        @Override // java.util.Comparator
        public int compare(VOSubtitleInfoEntry vOSubtitleInfoEntry, VOSubtitleInfoEntry vOSubtitleInfoEntry2) {
            return Integer.valueOf(vOSubtitleInfoEntry.getSubtitleRectInfo().zOrder).compareTo(Integer.valueOf(vOSubtitleInfoEntry2.getSubtitleRectInfo().zOrder));
        }
    }
}
