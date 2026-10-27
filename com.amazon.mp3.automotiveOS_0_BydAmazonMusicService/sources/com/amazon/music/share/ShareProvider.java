package com.amazon.music.share;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ContextThemeWrapper;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.featuregaing.ShareFeatureGating;
import com.amazon.music.share.menu.ShareableContent;
import com.amazon.music.share.menu.SharingFragment;
import com.amazon.music.share.menu.SharingFragmentV2;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ShareProvider {
    private static final String TAG = "ShareProvider";
    private final Context mContext;
    protected boolean mCustomShareSupported;
    private final String mDomainName;
    private final String mImageUrl;
    private boolean mIncludeChooserDetailMetrics;
    private final String mRefMarker;
    private final int mShareTextId;

    public enum ShareProviderType {
        ALBUM,
        ARTIST,
        PLAYLIST,
        STATION,
        TRACK,
        USER_PLAYLIST,
        CUSTOMER_PROFILE,
        VIDEO,
        PODCAST_SHOW,
        PODCAST_EPISODE,
        PODCAST_BITE,
        INVITE
    }

    protected List<Bitmap> getBackgroundBitmaps() {
        return null;
    }

    protected abstract String getEntityId();

    protected abstract EntityIdType getEntityIdType();

    protected String getEntityTypeString() {
        return null;
    }

    protected abstract Uri getLink();

    protected List<String> getLyrics() {
        return null;
    }

    protected abstract String getMessage();

    protected PublishSubject<Integer> getOpenLyricsCardSelectionFragmentSubject() {
        return null;
    }

    protected String getPrivateEntityId() {
        return null;
    }

    protected abstract ShareProviderType getShareProviderType();

    protected abstract String getSubject();

    protected String getSubtitle() {
        return null;
    }

    protected String getTitle() {
        return null;
    }

    protected ShareProvider(Context context, String str, int i) {
        this(context, str, i, null, null);
    }

    protected ShareProvider(Context context, String str, int i, String str2, String str3) {
        this.mIncludeChooserDetailMetrics = true;
        this.mContext = context;
        this.mDomainName = str;
        this.mShareTextId = i;
        if (str3 == null) {
            this.mRefMarker = "dm_sh_" + new SharingGuidGenerator(25).next();
        } else {
            this.mRefMarker = "dm_sh_" + str3;
        }
        this.mImageUrl = str2;
    }

    protected String getImageUrl() {
        return this.mImageUrl;
    }

    protected EntityType getEntityType() {
        try {
            return EntityType.valueOf(getShareProviderType().toString());
        } catch (Exception unused) {
            return EntityType.UNKNOWN;
        }
    }

    protected Uri appendRefMarker(Uri uri) {
        return uri.buildUpon().appendQueryParameter("ref", this.mRefMarker).build();
    }

    protected Uri getBaseUrl() {
        return new Uri.Builder().scheme("https").authority(this.mDomainName).build();
    }

    protected String getString(int i) {
        return this.mContext.getString(i);
    }

    protected Context getContext() {
        return this.mContext;
    }

    public void startShare() {
        startShare(null);
    }

    public void startShare(PageType pageType) {
        if (this.mCustomShareSupported && ShareFeatureGating.IS_CUSTOM_SHARE_MENU_ENABLED.isEnabled()) {
            startCustomShare(pageType);
        } else {
            startDefaultShare(pageType);
        }
    }

    public void startDefaultShare(PageType pageType) {
        Intent intentCreateChooser;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", getMessage());
        emitSelectShareEvent(pageType);
        if (this.mIncludeChooserDetailMetrics) {
            Intent intent2 = new Intent(this.mContext, (Class<?>) ShareIntentReceiver.class);
            intent2.putExtra(Contexts.OpenShareLink.entityIdType, getEntityIdType().toString());
            intent2.putExtra(Contexts.OpenShareLink.entityId, getEntityId());
            intent2.putExtra(Contexts.OpenShareLink.entityType, getEntityType().toString());
            intent2.putExtra("REF_MARKER", this.mRefMarker);
            intentCreateChooser = Intent.createChooser(intent, this.mContext.getString(this.mShareTextId), PendingIntent.getBroadcast(this.mContext, 0, intent2, 201326592).getIntentSender());
        } else {
            intentCreateChooser = Intent.createChooser(intent, this.mContext.getString(this.mShareTextId));
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SHARE).withEntityIdType(getEntityIdType()).withEntityId(getEntityId()).withEntityType(EntityType.valueOf(getEntityType().toString())).withRefMarker(this.mRefMarker).build());
        }
        this.mContext.startActivity(intentCreateChooser);
    }

    public void startCustomShare(PageType pageType) {
        emitSelectShareEvent(pageType);
        final FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            Log.w(TAG, "Error displaying custom share menu. Fragment null");
            startDefaultShare(null);
            return;
        }
        try {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.music.share.ShareProvider$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$startCustomShare$0(fragmentManager);
                }
            });
        } catch (Exception e) {
            Log.w(TAG, "Error displaying custom Share Sheet;" + e.getMessage());
            startDefaultShare(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startCustomShare$0(FragmentManager fragmentManager) {
        if (ShareFeatureGating.IS_CUSTOM_SHARE_SHEET_V2_ENABLED.isEnabled()) {
            showSharingFragmentV2(fragmentManager);
        } else {
            showSharingFragment(fragmentManager);
        }
    }

    private void showSharingFragment(FragmentManager fragmentManager) {
        SharingFragment sharingFragment = new SharingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.amazon.music.share.menu.ShareableContent", createShareableContent());
        sharingFragment.setArguments(bundle);
        sharingFragment.show(fragmentManager, "com.amazon.music.share.menu.ShareSheetTag");
    }

    private void showSharingFragmentV2(FragmentManager fragmentManager) {
        SharingFragmentV2 sharingFragmentV2 = new SharingFragmentV2();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.amazon.music.share.menu.ShareableContent", createShareableContent());
        sharingFragmentV2.setArguments(bundle);
        sharingFragmentV2.show(fragmentManager, "com.amazon.music.share.menu.ShareSheetV2");
        sharingFragmentV2.openLyricsCardSelectionFragmentSubject(getOpenLyricsCardSelectionFragmentSubject());
    }

    @Nullable
    private FragmentManager getFragmentManager() {
        Context context = this.mContext;
        if (context instanceof FragmentActivity) {
            return ((FragmentActivity) context).getSupportFragmentManager();
        }
        if (context instanceof ContextThemeWrapper) {
            return ((FragmentActivity) ((ContextThemeWrapper) context).getBaseContext()).getSupportFragmentManager();
        }
        return null;
    }

    private void emitSelectShareEvent(PageType pageType) {
        UiClickEvent.Builder builderWithRefMarker = UiClickEvent.builder(ActionType.SELECT_SHARE).withEntityIdType(getEntityIdType()).withEntityId(getEntityId()).withEntityType(EntityType.valueOf(getEntityType().toString())).withRefMarker(this.mRefMarker);
        if (pageType != null) {
            builderWithRefMarker.withPageType(pageType);
        }
        MetricsHolder.getManager().handleEvent(builderWithRefMarker.build());
    }

    private ShareableContent createShareableContent() {
        ShareableContent shareableContent = new ShareableContent();
        shareableContent.setContentType(getEntityTypeString());
        shareableContent.setRef(this.mRefMarker);
        shareableContent.setTitle(getTitle());
        shareableContent.setSubtitle(getSubtitle());
        shareableContent.setSubject(getSubject());
        shareableContent.setMessage(getMessage());
        shareableContent.setEntityId(getEntityId());
        shareableContent.setPrivateEntityId(getPrivateEntityId());
        shareableContent.setEntityIdType(EntityIdType.valueOf(getEntityIdType().toString()));
        shareableContent.setEntityType(getEntityType());
        shareableContent.setShareTextId(Integer.valueOf(this.mShareTextId));
        shareableContent.setLyrics(getLyrics());
        if (isValidUrl(getImageUrl())) {
            shareableContent.setImage(getImageUrl());
        }
        shareableContent.setUrl(getLink().toString());
        shareableContent.setShareCardsBackgroundBitmaps(getBackgroundBitmaps());
        return shareableContent;
    }

    public static boolean isValidUrl(String str) {
        if (str == null) {
            return false;
        }
        try {
            new URL(str).toURI();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
