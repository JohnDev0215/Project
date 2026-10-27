package com.byd.music.view.homeMusic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.music.view.homeMusic.lrc.LrcView2;
import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BigMusicBaseView extends RelativeLayout {
    private static final String TAG = BigMusicBaseView.class.getSimpleName();
    protected LrcView2 mLrcView;
    protected TextView singerName;
    protected TextView songName;

    public void setPlayerTime(long j) {
    }

    public void setlrc(int i) {
    }

    public BigMusicBaseView(Context context) {
        super(context);
    }

    public BigMusicBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BigMusicBaseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void initLrc(String str) {
        this.mLrcView.loadLrc(new File(str.replace(".mp3", ".lrc")));
    }
}
