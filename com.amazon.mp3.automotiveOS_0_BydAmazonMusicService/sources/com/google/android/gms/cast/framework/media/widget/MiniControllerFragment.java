package com.google.android.gms.cast.framework.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzkk;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class MiniControllerFragment extends Fragment implements ControlButtonsContainer {
    private static final Logger zza = new Logger("MiniControllerFragment");
    private boolean zzb;
    private int zzc;
    private int zzd;
    private TextView zze;
    private int zzf;
    private int zzg;

    @ColorInt
    private int zzh;
    private int zzi;
    private int[] zzj;
    private ImageView[] zzk = new ImageView[3];
    private int zzl;

    @DrawableRes
    private int zzm;

    @DrawableRes
    private int zzn;

    @DrawableRes
    private int zzo;

    @DrawableRes
    private int zzp;

    @DrawableRes
    private int zzq;

    @DrawableRes
    private int zzr;

    @DrawableRes
    private int zzs;

    @DrawableRes
    private int zzt;

    @DrawableRes
    private int zzu;

    @DrawableRes
    private int zzv;

    @DrawableRes
    private int zzw;

    @DrawableRes
    private int zzx;

    @Nullable
    private UIMediaController zzy;

    private final void zza(UIMediaController uIMediaController, RelativeLayout relativeLayout, int i, int i2) {
        ImageView imageView = (ImageView) relativeLayout.findViewById(i);
        int i3 = this.zzj[i2];
        if (i3 == R.id.cast_button_type_empty) {
            imageView.setVisibility(4);
            return;
        }
        if (i3 == R.id.cast_button_type_custom) {
            return;
        }
        if (i3 == R.id.cast_button_type_play_pause_toggle) {
            int i4 = this.zzm;
            int i5 = this.zzn;
            int i6 = this.zzo;
            if (this.zzl == 1) {
                i4 = this.zzp;
                i5 = this.zzq;
                i6 = this.zzr;
            }
            Drawable drawableZzc = zzr.zzc(getContext(), this.zzi, i4);
            Drawable drawableZzc2 = zzr.zzc(getContext(), this.zzi, i5);
            Drawable drawableZzc3 = zzr.zzc(getContext(), this.zzi, i6);
            imageView.setImageDrawable(drawableZzc2);
            ProgressBar progressBar = new ProgressBar(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(8, i);
            layoutParams.addRule(6, i);
            layoutParams.addRule(5, i);
            layoutParams.addRule(7, i);
            layoutParams.addRule(15);
            progressBar.setLayoutParams(layoutParams);
            progressBar.setVisibility(8);
            Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
            int i7 = this.zzh;
            if (i7 != 0 && indeterminateDrawable != null) {
                indeterminateDrawable.setColorFilter(i7, PorterDuff.Mode.SRC_IN);
            }
            relativeLayout.addView(progressBar);
            uIMediaController.bindImageViewToPlayPauseToggle(imageView, drawableZzc, drawableZzc2, drawableZzc3, progressBar, true);
            return;
        }
        if (i3 == R.id.cast_button_type_skip_previous) {
            imageView.setImageDrawable(zzr.zzc(getContext(), this.zzi, this.zzs));
            imageView.setContentDescription(getResources().getString(R.string.cast_skip_prev));
            uIMediaController.bindViewToSkipPrev(imageView, 0);
            return;
        }
        if (i3 == R.id.cast_button_type_skip_next) {
            imageView.setImageDrawable(zzr.zzc(getContext(), this.zzi, this.zzt));
            imageView.setContentDescription(getResources().getString(R.string.cast_skip_next));
            uIMediaController.bindViewToSkipNext(imageView, 0);
            return;
        }
        if (i3 == R.id.cast_button_type_rewind_30_seconds) {
            imageView.setImageDrawable(zzr.zzc(getContext(), this.zzi, this.zzu));
            imageView.setContentDescription(getResources().getString(R.string.cast_rewind_30));
            uIMediaController.bindViewToRewind(imageView, 30000L);
        } else if (i3 == R.id.cast_button_type_forward_30_seconds) {
            imageView.setImageDrawable(zzr.zzc(getContext(), this.zzi, this.zzv));
            imageView.setContentDescription(getResources().getString(R.string.cast_forward_30));
            uIMediaController.bindViewToForward(imageView, 30000L);
        } else if (i3 == R.id.cast_button_type_mute_toggle) {
            imageView.setImageDrawable(zzr.zzc(getContext(), this.zzi, this.zzw));
            uIMediaController.bindImageViewToMuteToggle(imageView);
        } else if (i3 == R.id.cast_button_type_closed_caption) {
            imageView.setImageDrawable(zzr.zzc(getContext(), this.zzi, this.zzx));
            uIMediaController.bindViewToClosedCaption(imageView);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    @NonNull
    public final ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        return this.zzk[i];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonSlotCount() {
        return 3;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonTypeAt(int i) throws IndexOutOfBoundsException {
        return this.zzj[i];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    @Nullable
    public UIMediaController getUIMediaController() {
        return this.zzy;
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        UIMediaController uIMediaController = new UIMediaController(getActivity());
        this.zzy = uIMediaController;
        View viewInflate = layoutInflater.inflate(R.layout.cast_mini_controller, viewGroup);
        viewInflate.setVisibility(8);
        uIMediaController.bindViewVisibilityToMediaSession(viewInflate, 8);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.container_current);
        int i = this.zzf;
        if (i != 0) {
            relativeLayout.setBackgroundResource(i);
        }
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.icon_view);
        TextView textView = (TextView) viewInflate.findViewById(R.id.title_view);
        if (this.zzc != 0) {
            textView.setTextAppearance(getActivity(), this.zzc);
        }
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.subtitle_view);
        this.zze = textView2;
        if (this.zzd != 0) {
            textView2.setTextAppearance(getActivity(), this.zzd);
        }
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        if (this.zzg != 0) {
            ((LayerDrawable) progressBar.getProgressDrawable()).setColorFilter(this.zzg, PorterDuff.Mode.SRC_IN);
        }
        uIMediaController.bindTextViewToMetadataOfCurrentItem(textView, MediaMetadata.KEY_TITLE);
        uIMediaController.bindTextViewToSmartSubtitle(this.zze);
        uIMediaController.bindProgressBar(progressBar);
        uIMediaController.bindViewToLaunchExpandedController(relativeLayout);
        if (this.zzb) {
            uIMediaController.bindImageViewToImageOfCurrentItem(imageView, new ImageHints(2, getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_width), getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_height)), R.drawable.cast_album_art_placeholder);
        } else {
            imageView.setVisibility(8);
        }
        ImageView[] imageViewArr = this.zzk;
        int i2 = R.id.button_0;
        imageViewArr[0] = (ImageView) relativeLayout.findViewById(i2);
        ImageView[] imageViewArr2 = this.zzk;
        int i3 = R.id.button_1;
        imageViewArr2[1] = (ImageView) relativeLayout.findViewById(i3);
        ImageView[] imageViewArr3 = this.zzk;
        int i4 = R.id.button_2;
        imageViewArr3[2] = (ImageView) relativeLayout.findViewById(i4);
        zza(uIMediaController, relativeLayout, i2, 0);
        zza(uIMediaController, relativeLayout, i3, 1);
        zza(uIMediaController, relativeLayout, i4, 2);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        UIMediaController uIMediaController = this.zzy;
        if (uIMediaController != null) {
            uIMediaController.dispose();
            this.zzy = null;
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet, @Nullable Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        if (this.zzj == null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CastMiniController, R.attr.castMiniControllerStyle, R.style.CastMiniController);
            this.zzb = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CastMiniController_castShowImageThumbnail, true);
            this.zzc = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castTitleTextAppearance, 0);
            this.zzd = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSubtitleTextAppearance, 0);
            this.zzf = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castBackground, 0);
            int color = typedArrayObtainStyledAttributes.getColor(R.styleable.CastMiniController_castProgressBarColor, 0);
            this.zzg = color;
            this.zzh = typedArrayObtainStyledAttributes.getColor(R.styleable.CastMiniController_castMiniControllerLoadingIndicatorColor, color);
            this.zzi = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castButtonColor, 0);
            int i = R.styleable.CastMiniController_castPlayButtonDrawable;
            this.zzm = typedArrayObtainStyledAttributes.getResourceId(i, 0);
            int i2 = R.styleable.CastMiniController_castPauseButtonDrawable;
            this.zzn = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            int i3 = R.styleable.CastMiniController_castStopButtonDrawable;
            this.zzo = typedArrayObtainStyledAttributes.getResourceId(i3, 0);
            this.zzp = typedArrayObtainStyledAttributes.getResourceId(i, 0);
            this.zzq = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            this.zzr = typedArrayObtainStyledAttributes.getResourceId(i3, 0);
            this.zzs = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSkipPreviousButtonDrawable, 0);
            this.zzt = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSkipNextButtonDrawable, 0);
            this.zzu = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castRewind30ButtonDrawable, 0);
            this.zzv = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castForward30ButtonDrawable, 0);
            this.zzw = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castMuteToggleButtonDrawable, 0);
            this.zzx = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castClosedCaptionsButtonDrawable, 0);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castControlButtons, 0);
            if (resourceId != 0) {
                TypedArray typedArrayObtainTypedArray = context.getResources().obtainTypedArray(resourceId);
                Preconditions.checkArgument(typedArrayObtainTypedArray.length() == 3);
                this.zzj = new int[typedArrayObtainTypedArray.length()];
                for (int i4 = 0; i4 < typedArrayObtainTypedArray.length(); i4++) {
                    this.zzj[i4] = typedArrayObtainTypedArray.getResourceId(i4, 0);
                }
                typedArrayObtainTypedArray.recycle();
                if (this.zzb) {
                    this.zzj[0] = R.id.cast_button_type_empty;
                }
                this.zzl = 0;
                for (int i5 : this.zzj) {
                    if (i5 != R.id.cast_button_type_empty) {
                        this.zzl++;
                    }
                }
            } else {
                zza.w("Unable to read attribute castControlButtons.", new Object[0]);
                int i6 = R.id.cast_button_type_empty;
                this.zzj = new int[]{i6, i6, i6};
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        com.google.android.gms.internal.cast.zzl.zzd(zzkk.CAF_MINI_CONTROLLER);
    }
}
