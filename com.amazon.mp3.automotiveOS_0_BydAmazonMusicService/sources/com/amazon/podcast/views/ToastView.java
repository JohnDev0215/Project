package com.amazon.podcast.views;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.onboarding.ToolTipHandler;

/* JADX INFO: loaded from: classes5.dex */
public final class ToastView {
    private final Context context;
    private final String navBarEvent;
    private final Toast toast;
    private final ImageView toastImageView;
    private final TextView toastTextView;
    private final ToastViewType toastViewType;
    private final int TOAST_DISPLAY_DURATION = 2000;
    private final Handler handler = new Handler();

    public enum ToastViewType {
        DOWNLOAD,
        FOLLOW,
        PLAYLIST,
        MARK_AS_PLAYED,
        PTC_PLAYBACK_ERROR
    }

    public ToastView(final Context context, final ToastViewType toastViewType, final String navBarEvent) {
        this.context = context;
        this.toastViewType = toastViewType;
        this.navBarEvent = navBarEvent;
        Toast toast = new Toast(context);
        this.toast = toast;
        toast.setGravity(17, 0, 0);
        toast.setDuration(0);
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R$layout.toast_view, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.toast_text_view);
        this.toastTextView = textView;
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.toast_image_view);
        this.toastImageView = imageView;
        int i = AnonymousClass1.$SwitchMap$com$amazon$podcast$views$ToastView$ToastViewType[toastViewType.ordinal()];
        if (i == 1) {
            textView.setText(R$string.podcast_onboardng_download_toast);
            imageView.setImageResource(R$drawable.ic_download_complete);
        } else if (i == 2) {
            textView.setText(R$string.dmusic_following_text);
            imageView.setImageResource(R$drawable.ic_btn_followheart);
        } else if (i == 3) {
            textView.setText(R$string.podcast_onboardng_playlist_toast);
            imageView.setImageResource(R$drawable.ic_checkmark);
        } else if (i == 4) {
            textView.setText(R$string.podcast_onboardng_mark_as_played_toast);
            imageView.setImageResource(R$drawable.ic_action_doneinline);
        } else if (i == 5) {
            imageView.setImageResource(R$drawable.ic_alerts_warning);
        }
        toast.setView(viewInflate);
    }

    /* JADX INFO: renamed from: com.amazon.podcast.views.ToastView$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$podcast$views$ToastView$ToastViewType;

        static {
            int[] iArr = new int[ToastViewType.values().length];
            $SwitchMap$com$amazon$podcast$views$ToastView$ToastViewType = iArr;
            try {
                iArr[ToastViewType.DOWNLOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$podcast$views$ToastView$ToastViewType[ToastViewType.FOLLOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$podcast$views$ToastView$ToastViewType[ToastViewType.PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$podcast$views$ToastView$ToastViewType[ToastViewType.MARK_AS_PLAYED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$podcast$views$ToastView$ToastViewType[ToastViewType.PTC_PLAYBACK_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissListener() {
        String str;
        dismissToast();
        if (!ToolTipHandler.checkIfFlagNotExists(this.context, this.navBarEvent).booleanValue() || (str = this.navBarEvent) == null) {
            return;
        }
        ToolTipHandler.sendNavEvent(this.context, str);
        ToolTipHandler.insertFlag(this.context, this.navBarEvent);
    }

    public void showToast() {
        this.toast.show();
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.podcast.views.ToastView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.dismissListener();
            }
        }, 2000L);
    }

    public void dismissToast() {
        Toast toast = this.toast;
        if (toast == null || toast.getView().getWindowVisibility() != 0) {
            return;
        }
        this.toast.cancel();
    }

    public void setToastText(final String text) {
        TextView textView = this.toastTextView;
        if (textView == null) {
            return;
        }
        textView.setText(text);
    }
}
