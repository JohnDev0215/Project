package com.byd.vrassistant.receiver;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.byd.vrassistant.eventbus.AudioEvent;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class MusicObserver extends ContentObserver {
    public static String TAG = "NEU_MusicObserver";
    AudioEvent audioEvent;

    public MusicObserver(Handler handler) {
        super(handler);
        this.audioEvent = new AudioEvent("", 0);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        EventBus.getDefault().postSticky(this.audioEvent);
    }
}
