package com.amazon.music.inappmessaging.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.Ribbon;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageViewUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.gson.Gson;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: DynMsgRibbon.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J(\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\fJ\u0018\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/music/inappmessaging/ui/DynMsgRibbon;", "", "()V", "LOG", "Lorg/slf4j/Logger;", "RIBBON_JSON_KEY", "", "context", "Landroid/content/Context;", "displayDynMsgBroadcastReceiver", "Landroid/content/BroadcastReceiver;", "featureBannerContainer", "Landroidx/constraintlayout/widget/ConstraintLayout;", "gson", "Lcom/google/gson/Gson;", "localContentId", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "destroy", "", "init", "appContext", "viewGroup", "Landroid/view/ViewGroup;", "styles", "bannerContainer", "ribbonDeeplinkListener", "v", "Landroid/view/View;", "ribbon", "Lcom/amazon/music/inappmessaging/external/model/Ribbon;", "setupRibbonView", "DMMNotificationUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DynMsgRibbon {
    private final Logger LOG;
    private Context context;
    private final BroadcastReceiver displayDynMsgBroadcastReceiver;
    private ConstraintLayout featureBannerContainer;
    private String localContentId;
    private StyleSheet styleSheet;
    private final String RIBBON_JSON_KEY = "json";
    private final Gson gson = new Gson();

    public DynMsgRibbon() {
        Logger logger = LoggerFactory.getLogger((Class<?>) DynMsgRibbon.class);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(DynMsgRibbon::class.java)");
        this.LOG = logger;
        this.displayDynMsgBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.music.inappmessaging.ui.DynMsgRibbon$displayDynMsgBroadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    Ribbon ribbon = (Ribbon) this.this$0.gson.fromJson(extras.getString(this.this$0.RIBBON_JSON_KEY), Ribbon.class);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    DynMsgRibbon dynMsgRibbon = this.this$0;
                    Intrinsics.checkNotNullExpressionValue(ribbon, "ribbon");
                    dynMsgRibbon.setupRibbonView(ribbon);
                    DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_RENDER_LATENCY).withRenderLatencyMillis(Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)).withTemplate(ribbon.template).send();
                }
            }
        };
    }

    public final void init(Context appContext, ViewGroup viewGroup, StyleSheet styles, ConstraintLayout bannerContainer) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        Intrinsics.checkNotNullParameter(styles, "styles");
        this.styleSheet = styles;
        this.context = appContext.getApplicationContext();
        this.featureBannerContainer = bannerContainer;
        if (bannerContainer != null) {
            bannerContainer.setVisibility(8);
        }
        Context context = this.context;
        if (context != null) {
            context.registerReceiver(this.displayDynMsgBroadcastReceiver, new IntentFilter(InAppMessageBroadcasts.DISPLAY_NOTIFICATION_RIBBON));
        }
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        String lowerCase = string.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        this.localContentId = lowerCase;
    }

    public final void destroy() {
        Context context = this.context;
        if (context != null) {
            context.unregisterReceiver(this.displayDynMsgBroadcastReceiver);
        }
        this.featureBannerContainer = null;
        this.context = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupRibbonView(final Ribbon ribbon) {
        Context context;
        Resources resources;
        Drawable drawable;
        Context context2;
        Resources resources2;
        Drawable drawable2;
        ConstraintLayout constraintLayout = this.featureBannerContainer;
        if (constraintLayout == null) {
            this.LOG.error("Unable to set up DynMsgRibbon. Feature banner container view is not initialized.");
            DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.OTHER).withErrorMessage("Could not display ribbon due to null container").withTrigger(Trigger.APP_START.name()).send();
            return;
        }
        BaseButton baseButton = constraintLayout == null ? null : (BaseButton) constraintLayout.findViewById(R.id.ribbon_dismiss);
        ConstraintLayout constraintLayout2 = this.featureBannerContainer;
        BaseButton baseButton2 = constraintLayout2 == null ? null : (BaseButton) constraintLayout2.findViewById(R.id.ribbon_navigation_icon);
        ConstraintLayout constraintLayout3 = this.featureBannerContainer;
        TextView textView = constraintLayout3 == null ? null : (TextView) constraintLayout3.findViewById(R.id.ribbon_text_area);
        if (baseButton2 != null) {
            baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.DynMsgRibbon$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DynMsgRibbon.m2150setupRibbonView$lambda0(this.f$0, ribbon, view);
                }
            });
        }
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.DynMsgRibbon$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DynMsgRibbon.m2151setupRibbonView$lambda1(this.f$0, ribbon, view);
                }
            });
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet = null;
        }
        ButtonSize iconSize = styleSheet.getIconSize(IconSizeKey.SMALL);
        StyleSheet styleSheet2 = this.styleSheet;
        if (styleSheet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet2 = null;
        }
        ButtonStyle iconStyle = styleSheet2.getIconStyle(IconStyleKey.PRIMARY);
        if (iconSize != null && iconStyle != null) {
            if (baseButton != null && (context2 = this.context) != null && (resources2 = context2.getResources()) != null && (drawable2 = resources2.getDrawable(R.drawable.ic_action_cancel2)) != null) {
                new BaseButton.StyleBuilder().withSize(iconSize).withStyle(iconStyle).withIcon(drawable2).applyStyle(baseButton);
            }
            if (baseButton2 != null && (context = this.context) != null && (resources = context.getResources()) != null && (drawable = resources.getDrawable(R.drawable.ic_chevron_right)) != null) {
                new BaseButton.StyleBuilder().withSize(iconSize).withStyle(iconStyle).withIcon(drawable).applyStyle(baseButton2);
            }
        }
        if (baseButton != null) {
            baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.DynMsgRibbon$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DynMsgRibbon.m2152setupRibbonView$lambda6(ribbon, this, view);
                }
            });
        }
        String strStringPlus = Intrinsics.stringPlus("", ribbon.copy);
        if (ribbon.byline != null) {
            strStringPlus = strStringPlus + ' ' + ((Object) ribbon.byline);
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(Color.parseColor(ribbon.backgroundColor));
        if (textView != null) {
            StyleSheet styleSheet3 = this.styleSheet;
            if (styleSheet3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
                styleSheet3 = null;
            }
            FontStyle fontStyle = styleSheet3.getFontStyle(FontStyleKey.PRIMARY);
            if (fontStyle != null) {
                FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
            }
            textView.setText(strStringPlus);
            textView.setTextColor(Color.parseColor(ribbon.textColor));
        }
        ConstraintLayout constraintLayout4 = this.featureBannerContainer;
        if (constraintLayout4 != null) {
            constraintLayout4.setBackground(gradientDrawable);
        }
        ConstraintLayout constraintLayout5 = this.featureBannerContainer;
        if (constraintLayout5 != null) {
            constraintLayout5.setVisibility(0);
        }
        DynamicMessageViewUtil.sendUIContentViewEvent(ribbon.blockRef, this.localContentId, null, null, ContainerType.RIBBON);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupRibbonView$lambda-0, reason: not valid java name */
    public static final void m2150setupRibbonView$lambda0(DynMsgRibbon this$0, Ribbon ribbon, View v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ribbon, "$ribbon");
        Intrinsics.checkNotNullExpressionValue(v, "v");
        this$0.ribbonDeeplinkListener(v, ribbon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupRibbonView$lambda-1, reason: not valid java name */
    public static final void m2151setupRibbonView$lambda1(DynMsgRibbon this$0, Ribbon ribbon, View v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ribbon, "$ribbon");
        Intrinsics.checkNotNullExpressionValue(v, "v");
        this$0.ribbonDeeplinkListener(v, ribbon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupRibbonView$lambda-6, reason: not valid java name */
    public static final void m2152setupRibbonView$lambda6(Ribbon ribbon, DynMsgRibbon this$0, View view) {
        Intrinsics.checkNotNullParameter(ribbon, "$ribbon");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.DISMISS_IAM).withInteractionType(InteractionType.TAP).withBlockRef(ribbon.blockRef).withEventTime(System.currentTimeMillis()).withLocalContentId(this$0.localContentId).build());
        ConstraintLayout constraintLayout = this$0.featureBannerContainer;
        if (constraintLayout == null) {
            return;
        }
        constraintLayout.setVisibility(8);
    }

    private final void ribbonDeeplinkListener(View v, Ribbon ribbon) {
        ConstraintLayout constraintLayout;
        DeeplinksManager deeplinksManager = DeeplinksManager.get(v.getContext());
        Uri uri = Uri.parse(ribbon.primaryDeeplink);
        try {
            try {
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.ACT_ON_IAM).withInteractionType(InteractionType.TAP).withBlockRef(ribbon.blockRef).withEventTime(System.currentTimeMillis()).withLocalContentId(this.localContentId).build());
                deeplinksManager.handleInternal(uri);
                constraintLayout = this.featureBannerContainer;
                if (constraintLayout == null) {
                    return;
                }
            } catch (Exception e) {
                Logger logger = this.LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to handle provided internal deeplink ");
                sb.append(uri);
                sb.append(": ");
                e.printStackTrace();
                sb.append(Unit.INSTANCE);
                logger.error(sb.toString());
                constraintLayout = this.featureBannerContainer;
                if (constraintLayout == null) {
                    return;
                }
            }
            constraintLayout.setVisibility(8);
        } catch (Throwable th) {
            ConstraintLayout constraintLayout2 = this.featureBannerContainer;
            if (constraintLayout2 != null) {
                constraintLayout2.setVisibility(8);
            }
            throw th;
        }
    }
}
