package com.android.car.developeroptions.notification;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class ImportancePreference extends Preference {
    ImageButton alertButton;
    ImageButton blockButton;
    Context mContext;
    ArrayMap<ImageButton, Integer> mImageButtons;
    int mImportance;
    boolean mIsBlockable;
    boolean mIsConfigurable;
    ImageButton silenceButton;

    public ImportancePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mIsBlockable = true;
        this.mIsConfigurable = true;
        this.mImageButtons = new ArrayMap<>();
        init(context);
    }

    public ImportancePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsBlockable = true;
        this.mIsConfigurable = true;
        this.mImageButtons = new ArrayMap<>();
        init(context);
    }

    public ImportancePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsBlockable = true;
        this.mIsConfigurable = true;
        this.mImageButtons = new ArrayMap<>();
        init(context);
    }

    public ImportancePreference(Context context) {
        super(context);
        this.mIsBlockable = true;
        this.mIsConfigurable = true;
        this.mImageButtons = new ArrayMap<>();
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setLayoutResource(R.layout.notif_importance_preference);
    }

    public void setImportance(int i) {
        this.mImportance = i;
    }

    public void setBlockable(boolean z) {
        this.mIsBlockable = z;
    }

    public void setConfigurable(boolean z) {
        this.mIsConfigurable = z;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.itemView.findViewById(R.id.block);
        View viewFindViewById2 = preferenceViewHolder.itemView.findViewById(R.id.alert);
        View viewFindViewById3 = preferenceViewHolder.itemView.findViewById(R.id.silence);
        if (!this.mIsBlockable) {
            viewFindViewById.setVisibility(8);
            if (this.mImportance == 0) {
                this.mImportance = 2;
                callChangeListener(2);
            }
        }
        this.blockButton = (ImageButton) viewFindViewById.findViewById(R.id.block_icon);
        this.silenceButton = (ImageButton) viewFindViewById3.findViewById(R.id.silence_icon);
        this.alertButton = (ImageButton) viewFindViewById2.findViewById(R.id.alert_icon);
        this.mImageButtons.put(this.blockButton, Integer.valueOf(this.mContext.getColor(R.color.notification_block_color)));
        this.mImageButtons.put(this.silenceButton, Integer.valueOf(this.mContext.getColor(R.color.notification_silence_color)));
        this.mImageButtons.put(this.alertButton, Integer.valueOf(this.mContext.getColor(R.color.notification_alert_color)));
        int i = this.mImportance;
        if (i == 0) {
            colorizeImageButton(this.blockButton.getId());
            if (!this.mIsConfigurable) {
                viewFindViewById2.setVisibility(8);
                viewFindViewById3.setVisibility(8);
            }
        } else if (i == 1 || i == 2) {
            colorizeImageButton(this.silenceButton.getId());
            if (!this.mIsConfigurable) {
                viewFindViewById2.setVisibility(8);
                viewFindViewById.setVisibility(8);
            }
        } else {
            colorizeImageButton(this.alertButton.getId());
            if (!this.mIsConfigurable) {
                viewFindViewById.setVisibility(8);
                viewFindViewById3.setVisibility(8);
            }
        }
        this.blockButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ImportancePreference$_iqGRTiAB-y33HCh0UEUIh2MmII
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$ImportancePreference(view);
            }
        });
        this.silenceButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ImportancePreference$b2lrz9MCKKFfGucubWMVEfJqTOc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$1$ImportancePreference(view);
            }
        });
        this.alertButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ImportancePreference$Q1I1Cf8Aqy5LkGBdQj0HW3Vu1MA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$2$ImportancePreference(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onBindViewHolder$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onBindViewHolder$0$ImportancePreference(View view) {
        callChangeListener(0);
        colorizeImageButton(this.blockButton.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onBindViewHolder$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onBindViewHolder$1$ImportancePreference(View view) {
        callChangeListener(2);
        colorizeImageButton(this.silenceButton.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onBindViewHolder$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onBindViewHolder$2$ImportancePreference(View view) {
        callChangeListener(3);
        colorizeImageButton(this.alertButton.getId());
    }

    private void colorizeImageButton(int i) {
        if (this.mImageButtons != null) {
            for (int i2 = 0; i2 < this.mImageButtons.size(); i2++) {
                ImageButton imageButtonKeyAt = this.mImageButtons.keyAt(i2);
                int iIntValue = this.mImageButtons.valueAt(i2).intValue();
                if (imageButtonKeyAt != null) {
                    LayerDrawable layerDrawable = (LayerDrawable) imageButtonKeyAt.getDrawable();
                    Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.fore);
                    GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.back);
                    if (i == imageButtonKeyAt.getId()) {
                        drawableFindDrawableByLayerId.setTint(-1);
                        gradientDrawable.setColor(iIntValue);
                    } else {
                        drawableFindDrawableByLayerId.setTint(iIntValue);
                        gradientDrawable.setColor(0);
                    }
                }
            }
        }
    }
}
