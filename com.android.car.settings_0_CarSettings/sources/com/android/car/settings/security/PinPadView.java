package com.android.car.settings.security;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.car.settings.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class PinPadView extends GridLayout {
    private static final int LONG_CLICK_DELAY_MILLS = 100;
    static final int NUM_KEYS = 12;
    static final int[] PIN_PAD_DIGIT_KEYS = {R.id.key0, R.id.key1, R.id.key2, R.id.key3, R.id.key4, R.id.key5, R.id.key6, R.id.key7, R.id.key8, R.id.key9};
    private ImageButton mEnterKey;
    private final Runnable mOnBackspaceLongClick;
    private PinPadClickListener mOnClickListener;
    private final List<View> mPinKeys;

    public interface PinPadClickListener {
        void onBackspaceClick();

        void onDigitKeyClick(String str);

        void onEnterKeyClick();
    }

    public PinPadView(Context context) {
        super(context);
        this.mPinKeys = new ArrayList(12);
        this.mOnBackspaceLongClick = new Runnable() { // from class: com.android.car.settings.security.PinPadView.1
            @Override // java.lang.Runnable
            public void run() {
                if (PinPadView.this.mOnClickListener != null) {
                    PinPadView.this.mOnClickListener.onBackspaceClick();
                    PinPadView.this.getHandler().postDelayed(this, 100L);
                }
            }
        };
        init(null, 0, 0);
    }

    public PinPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPinKeys = new ArrayList(12);
        this.mOnBackspaceLongClick = new Runnable() { // from class: com.android.car.settings.security.PinPadView.1
            @Override // java.lang.Runnable
            public void run() {
                if (PinPadView.this.mOnClickListener != null) {
                    PinPadView.this.mOnClickListener.onBackspaceClick();
                    PinPadView.this.getHandler().postDelayed(this, 100L);
                }
            }
        };
        init(attrs, 0, 0);
    }

    public PinPadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mPinKeys = new ArrayList(12);
        this.mOnBackspaceLongClick = new Runnable() { // from class: com.android.car.settings.security.PinPadView.1
            @Override // java.lang.Runnable
            public void run() {
                if (PinPadView.this.mOnClickListener != null) {
                    PinPadView.this.mOnClickListener.onBackspaceClick();
                    PinPadView.this.getHandler().postDelayed(this, 100L);
                }
            }
        };
        init(attrs, defStyleAttr, 0);
    }

    public PinPadView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mPinKeys = new ArrayList(12);
        this.mOnBackspaceLongClick = new Runnable() { // from class: com.android.car.settings.security.PinPadView.1
            @Override // java.lang.Runnable
            public void run() {
                if (PinPadView.this.mOnClickListener != null) {
                    PinPadView.this.mOnClickListener.onBackspaceClick();
                    PinPadView.this.getHandler().postDelayed(this, 100L);
                }
            }
        };
        init(attrs, defStyleAttr, defStyleRes);
    }

    public void setPinPadClickListener(PinPadClickListener pinPadClickListener) {
        this.mOnClickListener = pinPadClickListener;
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (View key : this.mPinKeys) {
            key.setEnabled(enabled);
        }
    }

    public void setEnterKeyIcon(int drawableId) {
        this.mEnterKey.setImageResource(drawableId);
    }

    public void setEnterKeyImageTint(ColorStateList tint) {
        this.mEnterKey.setImageTintList(tint);
    }

    public void setEnterKeyEnabled(boolean enabled) {
        this.mEnterKey.setEnabled(enabled);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PinPadView, defStyleAttr, defStyleRes);
        inflater.inflate(typedArray.getResourceId(R.styleable.PinPadView_layout, R.layout.pin_pad_view), (ViewGroup) this, true);
        typedArray.recycle();
        for (int keyId : PIN_PAD_DIGIT_KEYS) {
            TextView key = (TextView) findViewById(keyId);
            final String digit = key.getTag().toString();
            key.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.security.-$$Lambda$PinPadView$rGOsEWCxAJNwkysGk-ynNZCWg78
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$init$0$PinPadView(digit, view);
                }
            });
            this.mPinKeys.add(key);
        }
        ImageButton backspace = (ImageButton) findViewById(R.id.key_backspace);
        backspace.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.car.settings.security.-$$Lambda$PinPadView$sGrzZBE16xqmjZlU8POt91gmwbk
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$init$1$PinPadView(view, motionEvent);
            }
        });
        this.mPinKeys.add(backspace);
        ImageButton imageButton = (ImageButton) findViewById(R.id.key_enter);
        this.mEnterKey = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.security.-$$Lambda$PinPadView$xj1ntXgevvJ1ckr1JOPKmfXlxBA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$init$2$PinPadView(view);
            }
        });
        this.mPinKeys.add(this.mEnterKey);
    }

    public /* synthetic */ void lambda$init$0$PinPadView(String digit, View v) {
        this.mOnClickListener.onDigitKeyClick(digit);
    }

    public /* synthetic */ boolean lambda$init$1$PinPadView(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            getHandler().post(this.mOnBackspaceLongClick);
            return false;
        }
        if (action != 1) {
            return false;
        }
        getHandler().removeCallbacks(this.mOnBackspaceLongClick);
        return false;
    }

    public /* synthetic */ void lambda$init$2$PinPadView(View v) {
        this.mOnClickListener.onEnterKeyClick();
    }
}
