package com.vivid.spotify.queue;

import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import java.util.Objects;

/* JADX INFO: compiled from: TypeQueueItem.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5383a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5384b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private MediaSessionCompat.QueueItem f5385c;

    public k(String str) {
        this.f5384b = str;
        this.f5383a = "type_title";
    }

    private boolean d(MediaSessionCompat.QueueItem queueItem, MediaSessionCompat.QueueItem queueItem2) {
        if (queueItem == null && queueItem2 == null) {
            return true;
        }
        return (queueItem == null || queueItem2 == null || queueItem.j() != queueItem2.j()) ? false : true;
    }

    public MediaSessionCompat.QueueItem a() {
        return this.f5385c;
    }

    public String b() {
        return this.f5384b;
    }

    public String c() {
        return this.f5383a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        return TextUtils.equals(this.f5384b, kVar.f5384b) && TextUtils.equals(this.f5383a, kVar.f5383a) && d(this.f5385c, kVar.f5385c);
    }

    public int hashCode() {
        return Objects.hash(this.f5384b, this.f5383a, this.f5385c);
    }

    public k(MediaSessionCompat.QueueItem queueItem) {
        this.f5385c = queueItem;
        this.f5383a = "type_queue_item";
    }
}
