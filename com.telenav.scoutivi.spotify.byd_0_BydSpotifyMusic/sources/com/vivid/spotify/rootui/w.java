package com.vivid.spotify.rootui;

import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: PageItem.java */
/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5512a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f5513b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<MediaBrowserCompat.MediaItem> f5514c = new ArrayList();

    private boolean a(List<MediaBrowserCompat.MediaItem> list, List<MediaBrowserCompat.MediaItem> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        for (int i7 = 0; i7 < list.size(); i7++) {
            if (!TextUtils.equals(list.get(i7).j(), list2.get(i7).j())) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        w wVar = (w) obj;
        return TextUtils.equals(this.f5512a, wVar.f5512a) && TextUtils.equals(this.f5513b, wVar.f5513b) && a(this.f5514c, wVar.f5514c);
    }

    public int hashCode() {
        return Objects.hash(this.f5512a, this.f5513b, this.f5514c);
    }
}
