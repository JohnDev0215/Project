package com.android.systemui.screenshot;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class ScreenshotActionChip extends FrameLayout {
    private static final String TAG = "ScreenshotActionChip";
    private ImageView mIcon;
    private int mIconColor;
    private TextView mText;

    public ScreenshotActionChip(Context context) {
        this(context, null);
    }

    public ScreenshotActionChip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScreenshotActionChip(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ScreenshotActionChip(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mIconColor = context.getColor(R.color.global_screenshot_button_icon);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        this.mIcon = (ImageView) findViewById(R.id.screenshot_action_chip_icon);
        this.mText = (TextView) findViewById(R.id.screenshot_action_chip_text);
    }

    void setIcon(Icon icon, boolean tint) {
        this.mIcon.setImageIcon(icon);
        if (tint) {
            return;
        }
        this.mIcon.setImageTintList(null);
    }

    void setText(CharSequence text) {
        this.mText.setText(text);
    }

    void setPendingIntent(final PendingIntent intent, final Runnable finisher) {
        setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.-$$Lambda$ScreenshotActionChip$YYmy_EMvNzh8dI0lg0-4ke5WanU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScreenshotActionChip.lambda$setPendingIntent$0(intent, finisher, view);
            }
        });
    }

    static /* synthetic */ void lambda$setPendingIntent$0(PendingIntent pendingIntent, Runnable runnable, View view) {
        try {
            pendingIntent.send();
            runnable.run();
        } catch (PendingIntent.CanceledException e) {
            Log.e(TAG, "Intent cancelled", e);
        }
    }
}
