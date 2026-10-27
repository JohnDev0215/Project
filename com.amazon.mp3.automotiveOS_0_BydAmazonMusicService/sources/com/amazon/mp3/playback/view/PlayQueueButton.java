package com.amazon.mp3.playback.view;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.util.extensions.ActivityManagerExtensions;
import com.amazon.mp3.view.ContentDisabledHelper;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class PlayQueueButton extends BaseButton {
    private CastingSessionManager mCastingManager;
    private PageType mPageType;
    private final View.OnClickListener mPlayerPlayQueueButtonClickListener;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        Context context = view.getContext();
        moveMainAppTaskToFront(context);
        Intent nowPlayingQueueIntent = NowPlayingQueueUtil.getNowPlayingQueueIntent(context);
        if (nowPlayingQueueIntent != null) {
            context.startActivity(nowPlayingQueueIntent);
        }
        sendUiClickEvent();
    }

    private void moveMainAppTaskToFront(Context context) {
        ActivityManager.AppTask appTask = null;
        for (ActivityManager.AppTask appTask2 : ((ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getAppTasks()) {
            Set<String> categories = ActivityManagerExtensions.getCategories(appTask2);
            if (categories != null && categories.contains("android.intent.category.LAUNCHER")) {
                appTask = appTask2;
            }
        }
        if (appTask != null) {
            appTask.moveToFront();
        }
    }

    public PlayQueueButton(final Context context) {
        super(context);
        this.mPlayerPlayQueueButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.PlayQueueButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0(view);
            }
        };
        init();
    }

    public PlayQueueButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.mPlayerPlayQueueButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.PlayQueueButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0(view);
            }
        };
        init();
    }

    public void setPageType(final PageType pageType) {
        this.mPageType = pageType;
    }

    private void init() {
        setOnClickListener(this.mPlayerPlayQueueButtonClickListener);
        this.mCastingManager = CastingSessionManager.getInstance();
    }

    @Override // com.amazon.ui.foundations.views.BaseButton, android.view.View
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        ContentDisabledHelper.setContentEnabled(this, enabled);
    }

    private void sendUiClickEvent() {
        UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(ActionType.GO_PLAY_QUEUE).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis());
        if (this.mPageType == PageType.NOW_PLAYING && AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            builderWithEventTime.withPageType(PageType.NOW_PLAYING_STAGE);
        } else {
            PageType pageType = this.mPageType;
            if (pageType != null) {
                builderWithEventTime.withPageType(pageType);
            }
        }
        MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
    }
}
