package com.google.android.gms.cast.framework.media.widget;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzbw;
import com.google.android.gms.internal.cast.zzbz;
import com.google.android.gms.internal.cast.zzca;
import com.google.android.gms.internal.cast.zzcb;
import com.google.android.gms.internal.cast.zzcc;
import com.google.android.gms.internal.cast.zzkk;
import java.util.Timer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class ExpandedControllerActivity extends AppCompatActivity implements ControlButtonsContainer {
    private View zzB;
    private View zzC;
    private ImageView zzD;
    private TextView zzE;
    private TextView zzF;
    private TextView zzG;
    private TextView zzH;
    private com.google.android.gms.cast.framework.media.internal.zzb zzI;
    private UIMediaController zzJ;
    private SessionManager zzK;
    private boolean zzL;
    private boolean zzM;
    private Timer zzN;

    @Nullable
    private String zzO;

    @DrawableRes
    private int zzc;

    @DrawableRes
    private int zzd;

    @DrawableRes
    private int zze;

    @DrawableRes
    private int zzf;

    @DrawableRes
    private int zzg;

    @DrawableRes
    private int zzh;

    @DrawableRes
    private int zzi;

    @DrawableRes
    private int zzj;

    @DrawableRes
    private int zzk;

    @DrawableRes
    private int zzl;

    @ColorInt
    private int zzm;

    @ColorInt
    private int zzn;

    @ColorInt
    private int zzo;

    @ColorInt
    private int zzp;
    private int zzq;
    private int zzr;
    private int zzs;
    private int zzt;
    private TextView zzu;
    private SeekBar zzv;
    private CastSeekBar zzw;
    private ImageView zzx;
    private ImageView zzy;
    private int[] zzz;
    private final SessionManagerListener zza = new zzq(this, null);
    private final RemoteMediaClient.Listener zzb = new zzo(this, 0 == true ? 1 : 0);
    private ImageView[] zzA = new ImageView[4];

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public final RemoteMediaClient zzl() {
        CastSession currentCastSession = this.zzK.getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            return null;
        }
        return currentCastSession.getRemoteMediaClient();
    }

    private final void zzm(String str) {
        this.zzI.zzd(Uri.parse(str));
        this.zzC.setVisibility(8);
    }

    private final void zzn(View view, int i, int i2, UIMediaController uIMediaController) {
        ImageView imageView = (ImageView) view.findViewById(i);
        if (i2 == R.id.cast_button_type_empty) {
            imageView.setVisibility(4);
            return;
        }
        if (i2 == R.id.cast_button_type_play_pause_toggle) {
            imageView.setBackgroundResource(this.zzc);
            Drawable drawableZzb = zzr.zzb(this, this.zzq, this.zze);
            Drawable drawableZzb2 = zzr.zzb(this, this.zzq, this.zzd);
            Drawable drawableZzb3 = zzr.zzb(this, this.zzq, this.zzf);
            imageView.setImageDrawable(drawableZzb2);
            uIMediaController.bindImageViewToPlayPauseToggle(imageView, drawableZzb2, drawableZzb, drawableZzb3, null, false);
            return;
        }
        if (i2 == R.id.cast_button_type_skip_previous) {
            imageView.setBackgroundResource(this.zzc);
            imageView.setImageDrawable(zzr.zzb(this, this.zzq, this.zzg));
            imageView.setContentDescription(getResources().getString(R.string.cast_skip_prev));
            uIMediaController.bindViewToSkipPrev(imageView, 0);
            return;
        }
        if (i2 == R.id.cast_button_type_skip_next) {
            imageView.setBackgroundResource(this.zzc);
            imageView.setImageDrawable(zzr.zzb(this, this.zzq, this.zzh));
            imageView.setContentDescription(getResources().getString(R.string.cast_skip_next));
            uIMediaController.bindViewToSkipNext(imageView, 0);
            return;
        }
        if (i2 == R.id.cast_button_type_rewind_30_seconds) {
            imageView.setBackgroundResource(this.zzc);
            imageView.setImageDrawable(zzr.zzb(this, this.zzq, this.zzi));
            imageView.setContentDescription(getResources().getString(R.string.cast_rewind_30));
            uIMediaController.bindViewToRewind(imageView, 30000L);
            return;
        }
        if (i2 == R.id.cast_button_type_forward_30_seconds) {
            imageView.setBackgroundResource(this.zzc);
            imageView.setImageDrawable(zzr.zzb(this, this.zzq, this.zzj));
            imageView.setContentDescription(getResources().getString(R.string.cast_forward_30));
            uIMediaController.bindViewToForward(imageView, 30000L);
            return;
        }
        if (i2 == R.id.cast_button_type_mute_toggle) {
            imageView.setBackgroundResource(this.zzc);
            imageView.setImageDrawable(zzr.zzb(this, this.zzq, this.zzk));
            uIMediaController.bindImageViewToMuteToggle(imageView);
        } else if (i2 == R.id.cast_button_type_closed_caption) {
            imageView.setBackgroundResource(this.zzc);
            imageView.setImageDrawable(zzr.zzb(this, this.zzq, this.zzl));
            uIMediaController.bindViewToClosedCaption(imageView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzo(RemoteMediaClient remoteMediaClient) {
        MediaStatus mediaStatus;
        if (this.zzL || (mediaStatus = remoteMediaClient.getMediaStatus()) == null || remoteMediaClient.isBuffering()) {
            return;
        }
        this.zzG.setVisibility(8);
        this.zzH.setVisibility(8);
        AdBreakClipInfo currentAdBreakClip = mediaStatus.getCurrentAdBreakClip();
        if (currentAdBreakClip == null || currentAdBreakClip.getWhenSkippableInMs() == -1) {
            return;
        }
        if (!this.zzM) {
            zzk zzkVar = new zzk(this, remoteMediaClient);
            Timer timer = new Timer();
            this.zzN = timer;
            timer.scheduleAtFixedRate(zzkVar, 0L, 500L);
            this.zzM = true;
        }
        if (currentAdBreakClip.getWhenSkippableInMs() - remoteMediaClient.getApproximateAdBreakClipPositionMs() > 0.0f) {
            this.zzH.setVisibility(0);
            this.zzH.setText(getResources().getString(R.string.cast_expanded_controller_skip_ad_text, Integer.valueOf((int) Math.ceil(r10 / 1000.0f))));
            this.zzG.setClickable(false);
        } else {
            if (this.zzM) {
                this.zzN.cancel();
                this.zzM = false;
            }
            this.zzG.setVisibility(0);
            this.zzG.setClickable(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzp() {
        CastSession currentCastSession = this.zzK.getCurrentCastSession();
        if (currentCastSession != null) {
            String strZzf = currentCastSession.zzf();
            if (!TextUtils.isEmpty(strZzf)) {
                this.zzu.setText(getResources().getString(R.string.cast_casting_to_device, strZzf));
                return;
            }
        }
        this.zzu.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzq() {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        ActionBar supportActionBar;
        RemoteMediaClient remoteMediaClientZzl = zzl();
        if (remoteMediaClientZzl == null || !remoteMediaClientZzl.hasMediaSession() || (mediaInfo = remoteMediaClientZzl.getMediaInfo()) == null || (metadata = mediaInfo.getMetadata()) == null || (supportActionBar = getSupportActionBar()) == null) {
            return;
        }
        supportActionBar.setTitle(metadata.getString(MediaMetadata.KEY_TITLE));
        String strZza = com.google.android.gms.cast.framework.media.internal.zzq.zza(metadata);
        if (strZza != null) {
            supportActionBar.setSubtitle(strZza);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(23)
    public final void zzr() {
        MediaStatus mediaStatus;
        String string;
        Drawable drawable;
        Bitmap bitmap;
        Bitmap bitmapZza;
        RemoteMediaClient remoteMediaClientZzl = zzl();
        if (remoteMediaClientZzl == null || (mediaStatus = remoteMediaClientZzl.getMediaStatus()) == null) {
            return;
        }
        String imageUrl = null;
        if (!mediaStatus.isPlayingAd()) {
            this.zzH.setVisibility(8);
            this.zzG.setVisibility(8);
            this.zzB.setVisibility(8);
            if (PlatformVersion.isAtLeastJellyBeanMR1()) {
                this.zzy.setVisibility(8);
                this.zzy.setImageBitmap(null);
                return;
            }
            return;
        }
        if (PlatformVersion.isAtLeastJellyBeanMR1() && this.zzy.getVisibility() == 8 && (drawable = this.zzx.getDrawable()) != null && (drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null && (bitmapZza = zzr.zza(this, bitmap, 0.25f, 7.5f)) != null) {
            this.zzy.setImageBitmap(bitmapZza);
            this.zzy.setVisibility(0);
        }
        AdBreakClipInfo currentAdBreakClip = mediaStatus.getCurrentAdBreakClip();
        if (currentAdBreakClip != null) {
            String title = currentAdBreakClip.getTitle();
            imageUrl = currentAdBreakClip.getImageUrl();
            string = title;
        } else {
            string = null;
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            zzm(imageUrl);
        } else if (TextUtils.isEmpty(this.zzO)) {
            this.zzE.setVisibility(0);
            this.zzC.setVisibility(0);
            this.zzD.setVisibility(8);
        } else {
            zzm(this.zzO);
        }
        TextView textView = this.zzF;
        if (TextUtils.isEmpty(string)) {
            string = getResources().getString(R.string.cast_ad_label);
        }
        textView.setText(string);
        if (PlatformVersion.isAtLeastM()) {
            this.zzF.setTextAppearance(this.zzr);
        } else {
            this.zzF.setTextAppearance(this, this.zzr);
        }
        this.zzB.setVisibility(0);
        zzo(remoteMediaClientZzl);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    @NonNull
    public final ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        return this.zzA[i];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonSlotCount() {
        return 4;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonTypeAt(int i) throws IndexOutOfBoundsException {
        return this.zzz[i];
    }

    @NonNull
    @Deprecated
    public SeekBar getSeekBar() {
        return this.zzv;
    }

    @NonNull
    public TextView getStatusTextView() {
        return this.zzu;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    @NonNull
    public UIMediaController getUIMediaController() {
        return this.zzJ;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        SessionManager sessionManager = CastContext.getSharedInstance(this).getSessionManager();
        this.zzK = sessionManager;
        if (sessionManager.getCurrentCastSession() == null) {
            finish();
        }
        UIMediaController uIMediaController = new UIMediaController(this);
        this.zzJ = uIMediaController;
        uIMediaController.setPostRemoteMediaClientListener(this.zzb);
        setContentView(R.layout.cast_expanded_controller_activity);
        TypedArray typedArrayObtainStyledAttributes = obtainStyledAttributes(new int[]{androidx.appcompat.R.attr.selectableItemBackgroundBorderless});
        this.zzc = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        zzl zzlVar = null;
        TypedArray typedArrayObtainStyledAttributes2 = obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        this.zzq = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castButtonColor, 0);
        this.zzd = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castPlayButtonDrawable, 0);
        this.zze = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castPauseButtonDrawable, 0);
        this.zzf = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castStopButtonDrawable, 0);
        this.zzg = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSkipPreviousButtonDrawable, 0);
        this.zzh = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSkipNextButtonDrawable, 0);
        this.zzi = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castRewind30ButtonDrawable, 0);
        this.zzj = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castForward30ButtonDrawable, 0);
        this.zzk = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castMuteToggleButtonDrawable, 0);
        this.zzl = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castClosedCaptionsButtonDrawable, 0);
        int resourceId = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castControlButtons, 0);
        if (resourceId != 0) {
            TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(resourceId);
            Preconditions.checkArgument(typedArrayObtainTypedArray.length() == 4);
            this.zzz = new int[typedArrayObtainTypedArray.length()];
            for (int i = 0; i < typedArrayObtainTypedArray.length(); i++) {
                this.zzz[i] = typedArrayObtainTypedArray.getResourceId(i, 0);
            }
            typedArrayObtainTypedArray.recycle();
        } else {
            int i2 = R.id.cast_button_type_empty;
            this.zzz = new int[]{i2, i2, i2, i2};
        }
        this.zzp = typedArrayObtainStyledAttributes2.getColor(R.styleable.CastExpandedController_castExpandedControllerLoadingIndicatorColor, 0);
        this.zzm = getResources().getColor(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdLabelColor, 0));
        this.zzn = getResources().getColor(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdInProgressTextColor, 0));
        this.zzo = getResources().getColor(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdLabelTextColor, 0));
        this.zzr = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdLabelTextAppearance, 0);
        this.zzs = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdInProgressLabelTextAppearance, 0);
        this.zzt = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdInProgressText, 0);
        int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castDefaultAdPosterUrl, 0);
        if (resourceId2 != 0) {
            this.zzO = getApplicationContext().getResources().getString(resourceId2);
        }
        typedArrayObtainStyledAttributes2.recycle();
        View viewFindViewById = findViewById(R.id.expanded_controller_layout);
        UIMediaController uIMediaController2 = this.zzJ;
        this.zzx = (ImageView) viewFindViewById.findViewById(R.id.background_image_view);
        this.zzy = (ImageView) viewFindViewById.findViewById(R.id.blurred_background_image_view);
        View viewFindViewById2 = viewFindViewById.findViewById(R.id.background_place_holder_image_view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        uIMediaController2.zzb(this.zzx, new ImageHints(4, displayMetrics.widthPixels, displayMetrics.heightPixels), viewFindViewById2, new zzm(this, zzlVar));
        this.zzu = (TextView) viewFindViewById.findViewById(R.id.status_text);
        ProgressBar progressBar = (ProgressBar) viewFindViewById.findViewById(R.id.loading_indicator);
        Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
        int i3 = this.zzp;
        if (i3 != 0) {
            indeterminateDrawable.setColorFilter(i3, PorterDuff.Mode.SRC_IN);
        }
        uIMediaController2.bindViewToLoadingIndicator(progressBar);
        TextView textView = (TextView) viewFindViewById.findViewById(R.id.start_text);
        TextView textView2 = (TextView) viewFindViewById.findViewById(R.id.end_text);
        this.zzv = (SeekBar) viewFindViewById.findViewById(R.id.seek_bar);
        CastSeekBar castSeekBar = (CastSeekBar) viewFindViewById.findViewById(R.id.cast_seek_bar);
        this.zzw = castSeekBar;
        uIMediaController2.bindSeekBar(castSeekBar, 1000L);
        uIMediaController2.bindViewToUIController(textView, new zzcb(textView, uIMediaController2.zza()));
        uIMediaController2.bindViewToUIController(textView2, new zzbz(textView2, uIMediaController2.zza()));
        View viewFindViewById3 = viewFindViewById.findViewById(R.id.live_indicators);
        uIMediaController2.bindViewToUIController(viewFindViewById3, new zzca(viewFindViewById3, uIMediaController2.zza()));
        RelativeLayout relativeLayout = (RelativeLayout) viewFindViewById.findViewById(R.id.tooltip_container);
        zzbw zzccVar = new zzcc(relativeLayout, this.zzw, uIMediaController2.zza());
        uIMediaController2.bindViewToUIController(relativeLayout, zzccVar);
        uIMediaController2.zzf(zzccVar);
        ImageView[] imageViewArr = this.zzA;
        int i4 = R.id.button_0;
        imageViewArr[0] = (ImageView) viewFindViewById.findViewById(i4);
        ImageView[] imageViewArr2 = this.zzA;
        int i5 = R.id.button_1;
        imageViewArr2[1] = (ImageView) viewFindViewById.findViewById(i5);
        ImageView[] imageViewArr3 = this.zzA;
        int i6 = R.id.button_2;
        imageViewArr3[2] = (ImageView) viewFindViewById.findViewById(i6);
        ImageView[] imageViewArr4 = this.zzA;
        int i7 = R.id.button_3;
        imageViewArr4[3] = (ImageView) viewFindViewById.findViewById(i7);
        zzn(viewFindViewById, i4, this.zzz[0], uIMediaController2);
        zzn(viewFindViewById, i5, this.zzz[1], uIMediaController2);
        zzn(viewFindViewById, R.id.button_play_pause_toggle, R.id.cast_button_type_play_pause_toggle, uIMediaController2);
        zzn(viewFindViewById, i6, this.zzz[2], uIMediaController2);
        zzn(viewFindViewById, i7, this.zzz[3], uIMediaController2);
        View viewFindViewById4 = findViewById(R.id.ad_container);
        this.zzB = viewFindViewById4;
        this.zzD = (ImageView) viewFindViewById4.findViewById(R.id.ad_image_view);
        this.zzC = this.zzB.findViewById(R.id.ad_background_image_view);
        TextView textView3 = (TextView) this.zzB.findViewById(R.id.ad_label);
        this.zzF = textView3;
        textView3.setTextColor(this.zzo);
        this.zzF.setBackgroundColor(this.zzm);
        this.zzE = (TextView) this.zzB.findViewById(R.id.ad_in_progress_label);
        this.zzH = (TextView) findViewById(R.id.ad_skip_text);
        TextView textView4 = (TextView) findViewById(R.id.ad_skip_button);
        this.zzG = textView4;
        textView4.setOnClickListener(new zzi(this));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.drawable.quantum_ic_keyboard_arrow_down_white_36);
        }
        zzp();
        zzq();
        if (this.zzE != null && this.zzt != 0) {
            if (PlatformVersion.isAtLeastM()) {
                this.zzE.setTextAppearance(this.zzs);
            } else {
                this.zzE.setTextAppearance(getApplicationContext(), this.zzs);
            }
            this.zzE.setTextColor(this.zzn);
            this.zzE.setText(this.zzt);
        }
        com.google.android.gms.cast.framework.media.internal.zzb zzbVar = new com.google.android.gms.cast.framework.media.internal.zzb(getApplicationContext(), new ImageHints(-1, this.zzD.getWidth(), this.zzD.getHeight()));
        this.zzI = zzbVar;
        zzbVar.zzc(new zzh(this));
        com.google.android.gms.internal.cast.zzl.zzd(zzkk.CAF_EXPANDED_CONTROLLER);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.zzI.zza();
        UIMediaController uIMediaController = this.zzJ;
        if (uIMediaController != null) {
            uIMediaController.setPostRemoteMediaClientListener(null);
            this.zzJ.dispose();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return true;
        }
        finish();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        CastContext.getSharedInstance(this).getSessionManager().removeSessionManagerListener(this.zza, CastSession.class);
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        CastContext.getSharedInstance(this).getSessionManager().addSessionManagerListener(this.zza, CastSession.class);
        CastSession currentCastSession = CastContext.getSharedInstance(this).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || (!currentCastSession.isConnected() && !currentCastSession.isConnecting())) {
            finish();
        }
        RemoteMediaClient remoteMediaClientZzl = zzl();
        boolean z = true;
        if (remoteMediaClientZzl != null && remoteMediaClientZzl.hasMediaSession()) {
            z = false;
        }
        this.zzL = z;
        zzp();
        zzr();
        super.onResume();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() ^ 2;
            if (PlatformVersion.isAtLeastJellyBean()) {
                systemUiVisibility ^= 4;
            }
            if (PlatformVersion.isAtLeastKitKat()) {
                systemUiVisibility ^= 4096;
            }
            getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
            if (PlatformVersion.isAtLeastJellyBeanMR2()) {
                setImmersive(true);
            }
        }
    }
}
