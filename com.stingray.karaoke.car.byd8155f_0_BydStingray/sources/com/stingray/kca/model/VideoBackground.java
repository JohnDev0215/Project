package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJF\u0010\u000b\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/stingray/kca/model/VideoBackground;", "", "", "id", "", "Lcom/stingray/kca/model/VideoBackgroundResource;", "videoBackgroundFiles", "textColor", "wipeColor", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lcom/stingray/kca/model/VideoBackground;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class VideoBackground {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13232a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f13233b;
    public final String c;
    public final String d;

    public VideoBackground() {
        this(null, null, null, null, 15, null);
    }

    public final VideoBackground copy(@o(name = "id") String id, @o(name = "videoBackgroundFiles") List<VideoBackgroundResource> videoBackgroundFiles, @o(name = "textColor") String textColor, @o(name = "wipeColor") String wipeColor) {
        return new VideoBackground(id, videoBackgroundFiles, textColor, wipeColor);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoBackground)) {
            return false;
        }
        VideoBackground videoBackground = (VideoBackground) obj;
        return m.a(this.f13232a, videoBackground.f13232a) && m.a(this.f13233b, videoBackground.f13233b) && m.a(this.c, videoBackground.c) && m.a(this.d, videoBackground.d);
    }

    public final int hashCode() {
        String str = this.f13232a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        List list = this.f13233b;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        String str2 = this.c;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.d;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        return "VideoBackground(id=" + this.f13232a + ", videoBackgroundFiles=" + this.f13233b + ", textColor=" + this.c + ", wipeColor=" + this.d + ")";
    }

    public VideoBackground(@o(name = "id") String str, @o(name = "videoBackgroundFiles") List<VideoBackgroundResource> list, @o(name = "textColor") String str2, @o(name = "wipeColor") String str3) {
        this.f13232a = str;
        this.f13233b = list;
        this.c = str2;
        this.d = str3;
    }

    public /* synthetic */ VideoBackground(String str, List list, String str2, String str3, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : list, (i7 & 4) != 0 ? null : str2, (i7 & 8) != 0 ? null : str3);
    }
}
