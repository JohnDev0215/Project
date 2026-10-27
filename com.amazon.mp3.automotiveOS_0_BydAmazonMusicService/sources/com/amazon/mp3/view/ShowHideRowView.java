package com.amazon.mp3.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class ShowHideRowView extends FrameLayout {
    private View.OnClickListener mClickListener;
    private RelativeLayout mContainer;
    private ImageView mImageView;
    private Mode mMode;
    private TextView mTextView;
    private View.OnClickListener mToggleClickListener;
    private int mTrackCount;

    public enum Mode {
        SHOW,
        HIDE
    }

    public ShowHideRowView(final Context context) {
        super(context);
        this.mMode = Mode.SHOW;
        this.mClickListener = null;
        this.mToggleClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.view.ShowHideRowView.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                if (ShowHideRowView.this.mClickListener != null) {
                    ShowHideRowView.this.mClickListener.onClick(v);
                }
                Mode mode = ShowHideRowView.this.getMode();
                Mode mode2 = Mode.SHOW;
                if (mode == mode2) {
                    ShowHideRowView.this.setMode(Mode.HIDE);
                } else {
                    ShowHideRowView.this.setMode(mode2);
                }
            }
        };
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.show_hide_prime_row, this);
        this.mContainer = (RelativeLayout) findViewById(R.id.Container);
        this.mTextView = (TextView) findViewById(R.id.ShowHideTextView);
        this.mImageView = (ImageView) findViewById(R.id.ImageView);
        super.setOnClickListener(this.mToggleClickListener);
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
        updateView();
    }

    @Override // android.view.View
    public void setOnClickListener(final View.OnClickListener l) {
        this.mClickListener = l;
    }

    public Mode getMode() {
        return this.mMode;
    }

    public void setTrackCount(int count) {
        this.mTrackCount = count;
        updateView();
    }

    public int getTrackCount() {
        return this.mTrackCount;
    }

    private void updateView() {
        String string;
        Drawable drawable;
        Resources resources = getResources();
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$view$ShowHideRowView$Mode[this.mMode.ordinal()];
        if (i == 1) {
            string = resources.getString(R.string.dmusic_more_prime_songs_from_this_album, Integer.valueOf(this.mTrackCount));
            drawable = resources.getDrawable(R.drawable.prime_expand);
        } else if (i == 2) {
            string = resources.getString(R.string.dmusic_hide);
            drawable = resources.getDrawable(R.drawable.prime_expand);
        } else {
            throw new IllegalStateException("Invalid mode");
        }
        this.mImageView.setImageDrawable(drawable);
        if (this.mMode == Mode.HIDE) {
            this.mImageView.setRotation(180.0f);
        } else {
            this.mImageView.setRotation(0.0f);
        }
        ((FrameLayout.LayoutParams) this.mContainer.getLayoutParams()).gravity = 17;
        this.mTextView.setText(string);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.view.ShowHideRowView$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$view$ShowHideRowView$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$amazon$mp3$view$ShowHideRowView$Mode = iArr;
            try {
                iArr[Mode.SHOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$view$ShowHideRowView$Mode[Mode.HIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
