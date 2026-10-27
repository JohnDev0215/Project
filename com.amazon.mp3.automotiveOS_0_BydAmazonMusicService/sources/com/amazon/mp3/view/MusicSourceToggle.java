package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class MusicSourceToggle extends RelativeLayout {
    private Button mCloudToggleButton;
    private Button mDeviceToggleButton;
    private SourceChangedListener mListener;

    public enum Source {
        CLOUD,
        DEVICE
    }

    public interface SourceChangedListener {
        void onSourceToggled(Source source);
    }

    public MusicSourceToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setOnSourceChangedListener(SourceChangedListener listener) {
        this.mListener = listener;
    }

    public void setSource(Source source) {
        swapButtons(source);
    }

    public void setSource(String source) {
        swapButtons(source.equals("cirrus") ? Source.CLOUD : Source.DEVICE);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        int[] iArrChangeVisibilityForMeasurement = changeVisibilityForMeasurement();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setChildWidthAndVisibility(getMeasuredMaxChildWidth(), iArrChangeVisibilityForMeasurement);
    }

    private int[] changeVisibilityForMeasurement() {
        int childCount = getChildCount();
        int[] iArr = new int[childCount];
        for (int i = 0; i < childCount; i++) {
            iArr[i] = getChildAt(i).getVisibility();
            getChildAt(i).setVisibility(0);
        }
        return iArr;
    }

    private int getMeasuredMaxChildWidth() {
        int childCount = getChildCount();
        int iMax = 0;
        for (int i = 0; i < childCount; i++) {
            iMax = Math.max(iMax, getChildAt(i).getMeasuredWidth());
        }
        return iMax;
    }

    private void setChildWidthAndVisibility(int width, int[] visible) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            TextView textView = (TextView) childAt;
            TextPaint paint = textView.getPaint();
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            Drawable drawable = null;
            int length = compoundDrawables.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                Drawable drawable2 = compoundDrawables[i2];
                if (drawable2 != null) {
                    drawable = drawable2;
                    break;
                }
                i2++;
            }
            textView.setCompoundDrawablePadding((int) ((((width - paint.measureText(textView.getText().toString())) - (drawable != null ? drawable.getIntrinsicWidth() : 0.0f)) - getResources().getDimension(R.dimen.source_toggle_slider_padding)) / 2.0f));
            childAt.getLayoutParams().width = width;
            childAt.setVisibility(visible[i]);
        }
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.music_source_toggle, this);
        this.mCloudToggleButton = (Button) findViewById(R.id.CloudToggleButton);
        this.mDeviceToggleButton = (Button) findViewById(R.id.DeviceToggleButton);
        this.mCloudToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.MusicSourceToggle.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MusicSourceToggle.this.mListener != null) {
                    MusicSourceToggle.this.mListener.onSourceToggled(Source.DEVICE);
                }
                MusicSourceToggle.this.swapButtons(Source.DEVICE);
            }
        });
        this.mDeviceToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.MusicSourceToggle.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MusicSourceToggle.this.mListener != null) {
                    MusicSourceToggle.this.mListener.onSourceToggled(Source.CLOUD);
                }
                MusicSourceToggle.this.swapButtons(Source.CLOUD);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void swapButtons(Source showSource) {
        boolean z = showSource == Source.CLOUD;
        this.mDeviceToggleButton.setVisibility(z ? 8 : 0);
        this.mCloudToggleButton.setVisibility(z ? 0 : 8);
    }
}
