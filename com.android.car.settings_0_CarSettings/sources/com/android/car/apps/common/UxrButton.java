package com.android.car.apps.common;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.FrameMetricsAggregator;
import com.android.car.ui.utils.CarUxRestrictionsUtil;

/* JADX INFO: loaded from: classes5.dex */
public class UxrButton extends TextView {
    private static final int[] STATE_UX_RESTRICTED = {R.attr.state_ux_restricted};
    private CarUxRestrictions mActiveCarUxRestrictions;
    private CarUxRestrictionsUtil mCarUxRestrictionsUtil;
    private final Handler mHandler;
    private final CarUxRestrictionsUtil.OnUxRestrictionsChangedListener mListener;
    private View.OnClickListener mOnClickListenerDelegate;
    private final View.OnClickListener mOnClickListenerWrapper;
    private int mRestrictions;

    public /* synthetic */ void lambda$new$0$UxrButton(View v) {
        if (this.mOnClickListenerDelegate == null) {
            return;
        }
        if (isRestricted()) {
            showBlockingMessage();
        } else {
            this.mOnClickListenerDelegate.onClick(v);
        }
    }

    public UxrButton(Context context) {
        super(context);
        this.mHandler = new Handler();
        this.mListener = new CarUxRestrictionsUtil.OnUxRestrictionsChangedListener() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$4vZrVQ9GNdlvDEvoCazryjcJtCM
            @Override // com.android.car.ui.utils.CarUxRestrictionsUtil.OnUxRestrictionsChangedListener
            public final void onRestrictionsChanged(CarUxRestrictions carUxRestrictions) {
                this.f$0.updateActiveCarUxRestrictions(carUxRestrictions);
            }
        };
        this.mOnClickListenerWrapper = new View.OnClickListener() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$dTw2eEg17luUAiCWY1oHlXEYIcg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0$UxrButton(view);
            }
        };
        init(context, null);
    }

    public UxrButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHandler = new Handler();
        this.mListener = new CarUxRestrictionsUtil.OnUxRestrictionsChangedListener() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$4vZrVQ9GNdlvDEvoCazryjcJtCM
            @Override // com.android.car.ui.utils.CarUxRestrictionsUtil.OnUxRestrictionsChangedListener
            public final void onRestrictionsChanged(CarUxRestrictions carUxRestrictions) {
                this.f$0.updateActiveCarUxRestrictions(carUxRestrictions);
            }
        };
        this.mOnClickListenerWrapper = new View.OnClickListener() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$dTw2eEg17luUAiCWY1oHlXEYIcg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0$UxrButton(view);
            }
        };
        init(context, attrs);
    }

    public UxrButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mHandler = new Handler();
        this.mListener = new CarUxRestrictionsUtil.OnUxRestrictionsChangedListener() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$4vZrVQ9GNdlvDEvoCazryjcJtCM
            @Override // com.android.car.ui.utils.CarUxRestrictionsUtil.OnUxRestrictionsChangedListener
            public final void onRestrictionsChanged(CarUxRestrictions carUxRestrictions) {
                this.f$0.updateActiveCarUxRestrictions(carUxRestrictions);
            }
        };
        this.mOnClickListenerWrapper = new View.OnClickListener() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$dTw2eEg17luUAiCWY1oHlXEYIcg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0$UxrButton(view);
            }
        };
        init(context, attrs);
    }

    public UxrButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mHandler = new Handler();
        this.mListener = new CarUxRestrictionsUtil.OnUxRestrictionsChangedListener() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$4vZrVQ9GNdlvDEvoCazryjcJtCM
            @Override // com.android.car.ui.utils.CarUxRestrictionsUtil.OnUxRestrictionsChangedListener
            public final void onRestrictionsChanged(CarUxRestrictions carUxRestrictions) {
                this.f$0.updateActiveCarUxRestrictions(carUxRestrictions);
            }
        };
        this.mOnClickListenerWrapper = new View.OnClickListener() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$dTw2eEg17luUAiCWY1oHlXEYIcg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0$UxrButton(view);
            }
        };
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mCarUxRestrictionsUtil = CarUxRestrictionsUtil.getInstance(context);
        super.setOnClickListener(this.mOnClickListenerWrapper);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UxrButton);
        try {
            this.mRestrictions = a.getInteger(R.styleable.UxrButton_carUxRestrictions, FrameMetricsAggregator.EVERY_DURATION);
        } finally {
            a.recycle();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isRestricted()) {
            mergeDrawableStates(drawableState, STATE_UX_RESTRICTED);
        }
        return drawableState;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener listener) {
        this.mOnClickListenerDelegate = listener;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mCarUxRestrictionsUtil.register(this.mListener);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mCarUxRestrictionsUtil.unregister(this.mListener);
    }

    public void setUxRestrictions(int uxRestrictions) {
        this.mRestrictions = uxRestrictions;
        this.mHandler.post(new Runnable() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$qH_HOPZBG2LeaM_Bb40ZKoJMIDQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setUxRestrictions$1$UxrButton();
            }
        });
    }

    public /* synthetic */ void lambda$setUxRestrictions$1$UxrButton() {
        refreshDrawableState();
    }

    private boolean isRestricted() {
        return CarUxRestrictionsUtil.isRestricted(this.mRestrictions, this.mActiveCarUxRestrictions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateActiveCarUxRestrictions(CarUxRestrictions carUxRestrictions) {
        this.mActiveCarUxRestrictions = carUxRestrictions;
        this.mHandler.post(new Runnable() { // from class: com.android.car.apps.common.-$$Lambda$UxrButton$BL2WnWrCYIh6plWovssiOG9ytlU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateActiveCarUxRestrictions$2$UxrButton();
            }
        });
    }

    public /* synthetic */ void lambda$updateActiveCarUxRestrictions$2$UxrButton() {
        refreshDrawableState();
    }

    protected void showBlockingMessage() {
        Toast.makeText(getContext(), R.string.restricted_while_driving, 0).show();
    }
}
