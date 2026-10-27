package com.byd.music.view.homeMusic.lrc;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.bydux.api.common.tools.cmd.BydAdbHelper;

/* JADX INFO: loaded from: classes5.dex */
public class LrcEntry implements Comparable<LrcEntry> {
    public static final int GRAVITY_CENTER = 0;
    public static final int GRAVITY_LEFT = 1;
    public static final int GRAVITY_RIGHT = 2;
    private float offset = Float.MIN_VALUE;
    private String secondText;
    private StaticLayout staticLayout;
    private String text;
    private long time;

    public LrcEntry(long j, String str) {
        this.time = j;
        this.text = str;
    }

    LrcEntry(long j, String str, String str2) {
        this.time = j;
        this.text = str;
        this.secondText = str2;
    }

    void init(TextPaint textPaint, int i, int i2) {
        Layout.Alignment alignment;
        if (i2 == 1) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        } else if (i2 != 2) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        } else {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        }
        this.staticLayout = new StaticLayout(getShowText(), textPaint, i, alignment, 1.0f, 0.0f, false);
        this.offset = Float.MIN_VALUE;
    }

    long getTime() {
        return this.time;
    }

    StaticLayout getStaticLayout() {
        return this.staticLayout;
    }

    int getHeight() {
        StaticLayout staticLayout = this.staticLayout;
        if (staticLayout == null) {
            return 0;
        }
        return staticLayout.getHeight();
    }

    public float getOffset() {
        return this.offset;
    }

    public void setOffset(float f) {
        this.offset = f;
    }

    String getText() {
        return this.text;
    }

    void setSecondText(String str) {
        this.secondText = str;
    }

    private String getShowText() {
        if (!TextUtils.isEmpty(this.secondText)) {
            return this.text + BydAdbHelper.COMMAND_LINE_END + this.secondText;
        }
        return this.text;
    }

    @Override // java.lang.Comparable
    public int compareTo(LrcEntry lrcEntry) {
        if (lrcEntry == null) {
            return -1;
        }
        return (int) (this.time - lrcEntry.getTime());
    }
}
