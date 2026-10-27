package com.google.android.gms.cast.framework.media.uicontroller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.TracksChooserDialogFragment;
import com.google.android.gms.cast.framework.media.widget.CastSeekBar;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzay;
import com.google.android.gms.internal.cast.zzaz;
import com.google.android.gms.internal.cast.zzba;
import com.google.android.gms.internal.cast.zzbc;
import com.google.android.gms.internal.cast.zzbe;
import com.google.android.gms.internal.cast.zzbf;
import com.google.android.gms.internal.cast.zzbg;
import com.google.android.gms.internal.cast.zzbh;
import com.google.android.gms.internal.cast.zzbi;
import com.google.android.gms.internal.cast.zzbj;
import com.google.android.gms.internal.cast.zzbl;
import com.google.android.gms.internal.cast.zzbm;
import com.google.android.gms.internal.cast.zzbn;
import com.google.android.gms.internal.cast.zzbo;
import com.google.android.gms.internal.cast.zzbq;
import com.google.android.gms.internal.cast.zzbr;
import com.google.android.gms.internal.cast.zzbs;
import com.google.android.gms.internal.cast.zzbt;
import com.google.android.gms.internal.cast.zzbu;
import com.google.android.gms.internal.cast.zzbv;
import com.google.android.gms.internal.cast.zzbw;
import com.google.android.gms.internal.cast.zzbx;
import com.google.android.gms.internal.cast.zzby;
import com.google.android.gms.internal.cast.zzkk;
import com.google.android.gms.internal.cast.zzl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class UIMediaController implements RemoteMediaClient.Listener, SessionManagerListener<CastSession> {
    private static final Logger zzb = new Logger("UIMediaController");
    private final Activity zzc;

    @Nullable
    private final SessionManager zzd;

    @Nullable
    private RemoteMediaClient.Listener zzg;

    @Nullable
    private RemoteMediaClient zzh;
    private final Map zze = new HashMap();
    private final Set zzf = new HashSet();

    @VisibleForTesting
    zza zza = zza.zzf();

    public UIMediaController(@NonNull Activity activity) {
        this.zzc = activity;
        CastContext castContextZza = CastContext.zza(activity);
        zzl.zzd(zzkk.UI_MEDIA_CONTROLLER);
        SessionManager sessionManager = castContextZza != null ? castContextZza.getSessionManager() : null;
        this.zzd = sessionManager;
        if (sessionManager != null) {
            sessionManager.addSessionManagerListener(this, CastSession.class);
            zzh(sessionManager.getCurrentCastSession());
        }
    }

    private final void zzg() {
        if (isActive()) {
            this.zza.zza = null;
            Iterator it = this.zze.values().iterator();
            while (it.hasNext()) {
                Iterator it2 = ((List) it.next()).iterator();
                while (it2.hasNext()) {
                    ((UIController) it2.next()).onSessionEnded();
                }
            }
            Preconditions.checkNotNull(this.zzh);
            this.zzh.removeListener(this);
            this.zzh = null;
        }
    }

    private final void zzh(@Nullable Session session) {
        if (isActive() || session == null || !session.isConnected()) {
            return;
        }
        CastSession castSession = (CastSession) session;
        RemoteMediaClient remoteMediaClient = castSession.getRemoteMediaClient();
        this.zzh = remoteMediaClient;
        if (remoteMediaClient != null) {
            remoteMediaClient.addListener(this);
            Preconditions.checkNotNull(this.zza);
            this.zza.zza = castSession.getRemoteMediaClient();
            Iterator it = this.zze.values().iterator();
            while (it.hasNext()) {
                Iterator it2 = ((List) it.next()).iterator();
                while (it2.hasNext()) {
                    ((UIController) it2.next()).onSessionConnected(castSession);
                }
            }
            zzm();
        }
    }

    private final void zzi(int i, boolean z) {
        if (z) {
            Iterator it = this.zzf.iterator();
            while (it.hasNext()) {
                ((zzbw) it.next()).zzb(((long) i) + this.zza.zze());
            }
        }
    }

    private final void zzj() {
        Iterator it = this.zzf.iterator();
        while (it.hasNext()) {
            ((zzbw) it.next()).zza(false);
        }
    }

    private final void zzk(int i) {
        Iterator it = this.zzf.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else {
                ((zzbw) it.next()).zza(true);
            }
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        long jZze = ((long) i) + this.zza.zze();
        MediaSeekOptions.Builder builder = new MediaSeekOptions.Builder();
        builder.setPosition(jZze);
        builder.setIsSeekToInfinite(remoteMediaClient.isLiveStream() && this.zza.zzn(jZze));
        remoteMediaClient.seek(builder.build());
    }

    private final void zzl(View view, UIController uIController) {
        if (this.zzd == null) {
            return;
        }
        List arrayList = (List) this.zze.get(view);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.zze.put(view, arrayList);
        }
        arrayList.add(uIController);
        if (isActive()) {
            uIController.onSessionConnected((CastSession) Preconditions.checkNotNull(this.zzd.getCurrentCastSession()));
            zzm();
        }
    }

    private final void zzm() {
        Iterator it = this.zze.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((List) it.next()).iterator();
            while (it2.hasNext()) {
                ((UIController) it2.next()).onMediaStatusUpdated();
            }
        }
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(@NonNull ImageView imageView, int i, @DrawableRes int i2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(imageView, new zzbf(imageView, this.zzc, new ImageHints(i, 0, 0), i2, null, null));
    }

    @Deprecated
    public void bindImageViewToImageOfPreloadedItem(@NonNull ImageView imageView, int i, @DrawableRes int i2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(imageView, new zzbc(imageView, this.zzc, new ImageHints(i, 0, 0), i2));
    }

    public void bindImageViewToMuteToggle(@NonNull ImageView imageView) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        imageView.setOnClickListener(new zzb(this));
        zzl(imageView, new zzbl(imageView, this.zzc));
    }

    public void bindImageViewToPlayPauseToggle(@NonNull ImageView imageView, @NonNull Drawable drawable, @NonNull Drawable drawable2, @NonNull Drawable drawable3, @Nullable View view, boolean z) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl.zzd(zzkk.PAUSE_CONTROLLER);
        imageView.setOnClickListener(new zzc(this));
        zzl(imageView, new zzbm(imageView, this.zzc, drawable, drawable2, drawable3, view, z));
    }

    public void bindProgressBar(@NonNull ProgressBar progressBar) {
        bindProgressBar(progressBar, 1000L);
    }

    public void bindSeekBar(@NonNull SeekBar seekBar) {
        bindSeekBar(seekBar, 1000L);
    }

    public void bindTextViewToMetadataOfCurrentItem(@NonNull TextView textView, @NonNull String str) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        bindTextViewToMetadataOfCurrentItem(textView, Collections.singletonList(str));
    }

    public void bindTextViewToMetadataOfPreloadedItem(@NonNull TextView textView, @NonNull String str) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        bindTextViewToMetadataOfPreloadedItem(textView, Collections.singletonList(str));
    }

    public void bindTextViewToSmartSubtitle(@NonNull TextView textView) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(textView, new zzbt(textView));
    }

    public void bindTextViewToStreamDuration(@NonNull TextView textView) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(textView, new zzbu(textView, this.zzc.getString(R.string.cast_invalid_stream_duration_text), null));
    }

    public void bindTextViewToStreamPosition(@NonNull TextView textView, boolean z) {
        bindTextViewToStreamPosition(textView, z, 1000L);
    }

    public void bindViewToClosedCaption(@NonNull View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzk(this));
        zzl(view, new zzaz(view, this.zzc));
    }

    public void bindViewToForward(@NonNull View view, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzf(this, j));
        zzl(view, new zzba(view, this.zza));
    }

    public void bindViewToLaunchExpandedController(@NonNull View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzj(this));
        zzl(view, new zzbg(view));
    }

    public void bindViewToLoadingIndicator(@NonNull View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(view, new zzbh(view));
    }

    public void bindViewToRewind(@NonNull View view, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzg(this, j));
        zzl(view, new zzbo(view, this.zza));
    }

    public void bindViewToSkipNext(@NonNull View view, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzd(this));
        zzl(view, new zzbr(view, i));
    }

    public void bindViewToSkipPrev(@NonNull View view, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zze(this));
        zzl(view, new zzbs(view, i));
    }

    public void bindViewToUIController(@NonNull View view, @NonNull UIController uIController) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(view, uIController);
    }

    public void bindViewVisibilityToMediaSession(@NonNull View view, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(view, new zzby(view, i));
    }

    public void bindViewVisibilityToPreloadingEvent(@NonNull View view, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(view, new zzbx(view, i));
    }

    public void dispose() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzg();
        this.zze.clear();
        SessionManager sessionManager = this.zzd;
        if (sessionManager != null) {
            sessionManager.removeSessionManagerListener(this, CastSession.class);
        }
        this.zzg = null;
    }

    @Nullable
    public RemoteMediaClient getRemoteMediaClient() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzh;
    }

    public boolean isActive() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzh != null;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onAdBreakStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onAdBreakStatusUpdated();
        }
    }

    protected void onClosedCaptionClicked(@NonNull View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && (this.zzc instanceof FragmentActivity)) {
            TracksChooserDialogFragment tracksChooserDialogFragmentNewInstance = TracksChooserDialogFragment.newInstance();
            FragmentActivity fragmentActivity = (FragmentActivity) this.zzc;
            FragmentTransaction fragmentTransactionBeginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            Fragment fragmentFindFragmentByTag = fragmentActivity.getSupportFragmentManager().findFragmentByTag("TRACKS_CHOOSER_DIALOG_TAG");
            if (fragmentFindFragmentByTag != null) {
                fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
            }
            tracksChooserDialogFragmentNewInstance.show(fragmentTransactionBeginTransaction, "TRACKS_CHOOSER_DIALOG_TAG");
        }
    }

    protected void onForwardClicked(@NonNull View view, long j) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        if (!remoteMediaClient.zzq()) {
            remoteMediaClient.seek(remoteMediaClient.getApproximateStreamPosition() + j);
            return;
        }
        long approximateStreamPosition = remoteMediaClient.getApproximateStreamPosition();
        zza zzaVar = this.zza;
        remoteMediaClient.seek(Math.min(approximateStreamPosition + j, ((long) zzaVar.zzc()) + zzaVar.zze()));
    }

    protected void onLaunchExpandedControllerClicked(@NonNull View view) {
        CastMediaOptions castMediaOptions = CastContext.getSharedInstance(this.zzc).getCastOptions().getCastMediaOptions();
        if (castMediaOptions == null || TextUtils.isEmpty(castMediaOptions.getExpandedControllerActivityClassName())) {
            return;
        }
        ComponentName componentName = new ComponentName(this.zzc.getApplicationContext(), castMediaOptions.getExpandedControllerActivityClassName());
        Intent intent = new Intent();
        intent.setComponent(componentName);
        this.zzc.startActivity(intent);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onMetadataUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onMetadataUpdated();
        }
    }

    protected void onMuteToggleClicked(@NonNull ImageView imageView) {
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzc.getApplicationContext()).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            return;
        }
        try {
            currentCastSession.setMute(!currentCastSession.isMute());
        } catch (IOException | IllegalArgumentException e) {
            zzb.e("Unable to call CastSession.setMute(boolean).", e);
        }
    }

    protected void onPlayPauseToggleClicked(@NonNull ImageView imageView) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.togglePlayback();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onPreloadStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onPreloadStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onQueueStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onQueueStatusUpdated();
        }
    }

    protected void onRewindClicked(@NonNull View view, long j) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        if (!remoteMediaClient.zzq()) {
            remoteMediaClient.seek(remoteMediaClient.getApproximateStreamPosition() - j);
            return;
        }
        long approximateStreamPosition = remoteMediaClient.getApproximateStreamPosition();
        zza zzaVar = this.zza;
        remoteMediaClient.seek(Math.max(approximateStreamPosition - j, ((long) zzaVar.zzd()) + zzaVar.zze()));
    }

    protected void onSeekBarProgressChanged(@NonNull SeekBar seekBar, int i, boolean z) {
        zzi(i, z);
    }

    protected void onSeekBarStartTrackingTouch(@NonNull SeekBar seekBar) {
        if (this.zze.containsKey(seekBar)) {
            for (UIController uIController : (List) this.zze.get(seekBar)) {
                if (uIController instanceof zzbq) {
                    ((zzbq) uIController).zza(false);
                }
            }
        }
        zzj();
    }

    protected void onSeekBarStopTrackingTouch(@NonNull SeekBar seekBar) {
        if (this.zze.containsKey(seekBar)) {
            for (UIController uIController : (List) this.zze.get(seekBar)) {
                if (uIController instanceof zzbq) {
                    ((zzbq) uIController).zza(true);
                }
            }
        }
        zzk(seekBar.getProgress());
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onSendingRemoteMediaRequest() {
        Iterator it = this.zze.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((List) it.next()).iterator();
            while (it2.hasNext()) {
                ((UIController) it2.next()).onSendingRemoteMediaRequest();
            }
        }
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onSendingRemoteMediaRequest();
        }
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionEnded(@NonNull CastSession castSession, int i) {
        zzg();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionEnding(@NonNull CastSession castSession) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResumeFailed(@NonNull CastSession castSession, int i) {
        zzg();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResumed(@NonNull CastSession castSession, boolean z) {
        zzh(castSession);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResuming(@NonNull CastSession castSession, @NonNull String str) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStartFailed(@NonNull CastSession castSession, int i) {
        zzg();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarted(@NonNull CastSession castSession, @NonNull String str) {
        zzh(castSession);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarting(@NonNull CastSession castSession) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionSuspended(@NonNull CastSession castSession, int i) {
    }

    protected void onSkipNextClicked(@NonNull View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.queueNext(null);
    }

    protected void onSkipPrevClicked(@NonNull View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.queuePrev(null);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onStatusUpdated();
        }
    }

    public void setPostRemoteMediaClientListener(@Nullable RemoteMediaClient.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzg = listener;
    }

    public final zza zza() {
        return this.zza;
    }

    public final void zzb(ImageView imageView, ImageHints imageHints, View view, @Nullable zzbe zzbeVar) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(imageView, new zzbf(imageView, this.zzc, imageHints, 0, view, zzbeVar));
    }

    protected final void zzc(@NonNull CastSeekBar castSeekBar, int i, boolean z) {
        zzi(i, z);
    }

    protected final void zzd(@NonNull CastSeekBar castSeekBar) {
        zzj();
    }

    protected final void zze(@NonNull CastSeekBar castSeekBar) {
        zzk(castSeekBar.getProgress());
    }

    public final void zzf(zzbw zzbwVar) {
        this.zzf.add(zzbwVar);
    }

    public void bindProgressBar(@NonNull ProgressBar progressBar, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(progressBar, new zzbn(progressBar, j));
    }

    public void bindSeekBar(@NonNull SeekBar seekBar, long j) {
        zzl.zzd(zzkk.SEEK_CONTROLLER);
        Preconditions.checkMainThread("Must be called from the main thread.");
        seekBar.setOnSeekBarChangeListener(new zzi(this, seekBar));
        zzl(seekBar, new zzbq(seekBar, j, this.zza));
    }

    public void bindTextViewToStreamPosition(@NonNull TextView textView, boolean z, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzbv zzbvVar = new zzbv(textView, j, this.zzc.getString(R.string.cast_invalid_stream_position_text));
        if (z) {
            this.zzf.add(zzbvVar);
        }
        zzl(textView, zzbvVar);
    }

    public void bindImageViewToImageOfPreloadedItem(@NonNull ImageView imageView, @NonNull ImageHints imageHints, @DrawableRes int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(imageView, new zzbc(imageView, this.zzc, imageHints, i));
    }

    public void bindTextViewToMetadataOfCurrentItem(@NonNull TextView textView, @NonNull List<String> list) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(textView, new zzbj(textView, list));
    }

    public void bindTextViewToMetadataOfPreloadedItem(@NonNull TextView textView, @NonNull List<String> list) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(textView, new zzbi(textView, list));
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(@NonNull ImageView imageView, int i, @NonNull View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(imageView, new zzbf(imageView, this.zzc, new ImageHints(i, 0, 0), 0, view, null));
    }

    public void bindTextViewToStreamDuration(@NonNull TextView textView, @NonNull View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(textView, new zzbu(textView, this.zzc.getString(R.string.cast_invalid_stream_duration_text), view));
    }

    public void bindSeekBar(@NonNull CastSeekBar castSeekBar) {
        bindSeekBar(castSeekBar, 1000L);
    }

    public void bindImageViewToImageOfCurrentItem(@NonNull ImageView imageView, @NonNull ImageHints imageHints, @DrawableRes int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(imageView, new zzbf(imageView, this.zzc, imageHints, i, null, null));
    }

    public void bindSeekBar(@NonNull CastSeekBar castSeekBar, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl.zzd(zzkk.SEEK_CONTROLLER);
        castSeekBar.zzd = new zzh(this);
        zzl(castSeekBar, new zzay(castSeekBar, j, this.zza));
    }

    public void bindImageViewToImageOfCurrentItem(@NonNull ImageView imageView, @NonNull ImageHints imageHints, @NonNull View view) {
        zzb(imageView, imageHints, view, null);
    }
}
