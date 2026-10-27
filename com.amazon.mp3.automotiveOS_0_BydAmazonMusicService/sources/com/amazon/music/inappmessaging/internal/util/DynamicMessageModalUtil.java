package com.amazon.music.inappmessaging.internal.util;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.arcus.ThreadUtils;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.Splash;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.internal.styling.HtmlParser;
import com.amazon.music.inappmessaging.internal.styling.SplashMigrationHTMLTransformer;
import com.amazon.music.inappmessaging.internal.styling.SplashMigrationTagHandler;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageModalUtil {
    private static final String CREATE_SUBSCRIPTION = "action:/createSubscription";
    private static final String DEEPLINK_QUERY_KEY = "\\?deeplink=";
    private static final String FTU_PAGE_SUBTYPE = "ftu";
    private static final String IAM_PAGE_TYPE = "inAppMessaging";
    private static final int IMAGE_BREAKPOINT_LG = 840;
    private static final int IMAGE_BREAKPOINT_SM = 480;
    private static final int IMAGE_BREAKPOINT_XL = 1025;
    private static final int IMAGE_BREAKPOINT_XS = 360;
    private static final int MIN_RETINA_DENSITY = 2;
    private static final String SIZE_LG = "LG";
    private static final String SIZE_SM = "SM";
    private static final String SIZE_XL = "XL";
    private static final String SIZE_XS = "XS";
    private static final String TAG = "DynamicMessageModalUtil";

    public static boolean isDisplayRetina(final float density) {
        return density >= 2.0f;
    }

    public static boolean isNightwingUser() {
        try {
            Set<User.Benefit> benefits = AccountManagerSingleton.get().getUser().getBenefits();
            return (!benefits.contains(User.Benefit.NIGHTWING) || benefits.contains(User.Benefit.PRIME_MUSIC_BROWSE) || (benefits.contains(User.Benefit.HAWKFIRE_BROWSE_AND_ADD) || benefits.contains(User.Benefit.HAWKFIRE_PLAYBACK_ACCESS))) ? false : true;
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return false;
        }
    }

    public static void postEventExecute(DynamicMessageEvent event) {
        if (event.getEvent().startsWith(CREATE_SUBSCRIPTION)) {
            ThreadUtils.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.music.inappmessaging.internal.util.DynamicMessageModalUtil$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicMessageModalUtil.lambda$postEventExecute$0();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$postEventExecute$0() {
        AccountManagerSingleton.get().updateUser();
    }

    public static void setHtmlText(final Context context, final TextView textView, final String htmlText, final String templateRefMarker, final String localContentId) {
        Spanned spannedFromHtml = Html.fromHtml(htmlText);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannedFromHtml);
        for (URLSpan uRLSpan : (URLSpan[]) spannableStringBuilder.getSpans(0, spannedFromHtml.length(), URLSpan.class)) {
            setUrlClickable(spannableStringBuilder, uRLSpan, DeeplinksManager.get(context), templateRefMarker, localContentId);
        }
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void setHtmlTextSplashMigration(final Context context, final TextView textView, final String htmlText, final String templateRefMarker, final String localContentId) {
        try {
            Spanned spannedBuildSpannedText = HtmlParser.buildSpannedText(SplashMigrationHTMLTransformer.retagHTML(sanitizeHtml(htmlText)), new SplashMigrationTagHandler());
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannedBuildSpannedText);
            for (URLSpan uRLSpan : (URLSpan[]) spannableStringBuilder.getSpans(0, spannedBuildSpannedText.length(), URLSpan.class)) {
                setUrlClickable(spannableStringBuilder, uRLSpan, DeeplinksManager.get(context), templateRefMarker, localContentId);
            }
            textView.setText(spannableStringBuilder);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception unused) {
            Log.e(TAG, "Failed to re-tag or handle html tag for the html string in the evergreen splash: " + htmlText);
        }
    }

    private static String sanitizeHtml(String htmlText) {
        return StringEscapeUtils.unescapeHtml3(htmlText.replace(";", "; "));
    }

    private static void setUrlClickable(final SpannableStringBuilder builder, final URLSpan urlSpan, final DeeplinksManager manager, final String templateRefMarker, final String localContentId) {
        int spanStart = builder.getSpanStart(urlSpan);
        int spanEnd = builder.getSpanEnd(urlSpan);
        int spanFlags = builder.getSpanFlags(urlSpan);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.amazon.music.inappmessaging.internal.util.DynamicMessageModalUtil.1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_HYPERLINK_CLICK, templateRefMarker, localContentId, urlSpan.getURL());
                try {
                    manager.handle(DynamicMessageModalUtil.getDeeplink(urlSpan.getURL()));
                } catch (Exception e) {
                    Log.e(DynamicMessageModalUtil.TAG, "Unable to handle provided deeplink: " + e.toString());
                }
            }
        };
        builder.removeSpan(urlSpan);
        builder.setSpan(clickableSpan, spanStart, spanEnd, spanFlags);
    }

    public static ButtonSize buildButtonSize(final ButtonSize buttonSize, final int buttonFontSize) {
        return buttonSize.copy(buttonSize.getHeight(), buttonSize.getWidth(), buttonSize.getPaddingHorizontal(), buttonSize.getPaddingVertical(), buttonSize.getMinWidth(), buttonSize.getCornerRadius(), Integer.valueOf(buttonFontSize), buttonSize.getTextTypeface(), buttonSize.getTextAllCaps(), buttonSize.getIconSize(), buttonSize.getIconPaddingLeft(), buttonSize.getIconPaddingRight());
    }

    public static ButtonStyle buildButtonStyle(final ButtonStyle buttonStyle, final int buttonBackgroundColor) {
        StatefulStyle statefulStyle = buttonStyle.getStatefulStyle();
        return buttonStyle.copy(statefulStyle.copy(statefulStyle.getColor(), statefulStyle.getPressedColor(), statefulStyle.getFocusedColor(), statefulStyle.getDisabledColor(), Integer.valueOf(buttonBackgroundColor), statefulStyle.getPressedBackgroundColor(), statefulStyle.getFocusedBackgroundColor(), statefulStyle.getDisabledBackgroundColor()), buttonStyle.getIconStatefulStyle(), buttonStyle.getInnerBorderColor(), buttonStyle.getInnerBorderSize());
    }

    public static void sendUIClickEvent(final ActionType actionType, final String blockRef, final String localContentId) {
        sendUIClickEvent(actionType, blockRef, localContentId, null);
    }

    public static void sendUIClickEvent(final ActionType actionType, final String blockRef, final String localContentId, final String url) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withBlockRef(blockRef).withUrl(url).withLocalContentId(localContentId).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
    }

    public static Uri getDeeplink(final String url) {
        return Uri.parse(url.trim().replaceFirst(DEEPLINK_QUERY_KEY, ""));
    }

    public static void splashDeeplinkListener(final Context context, final Uri buttonPrimaryLink, final String blockRef, final String localContentId, final Dialog dialogFragment) {
        DeeplinksManager deeplinksManager = DeeplinksManager.get(context);
        String queryParameter = buttonPrimaryLink.getQueryParameter("deeplink");
        String queryParameter2 = buttonPrimaryLink.getQueryParameter("dismiss");
        String queryParameter3 = buttonPrimaryLink.getQueryParameter("clickId");
        if (queryParameter3 != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.ACT_ON_IAM).withInteractionType(InteractionType.TAP).withBlockRef(blockRef).withEventTime(System.currentTimeMillis()).withLocalContentId(localContentId).build());
        }
        if (queryParameter2 != null && queryParameter2.equals("true")) {
            dialogDismissHandler(dialogFragment, blockRef, localContentId);
        }
        if (queryParameter != null && !StringUtils.isEmpty(queryParameter)) {
            buttonPrimaryLink = Uri.parse(queryParameter);
        }
        try {
            try {
                deeplinksManager.handleInternal(buttonPrimaryLink);
                if (queryParameter3 == null && queryParameter2 == null && queryParameter == null) {
                    MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.ACT_ON_IAM).withInteractionType(InteractionType.TAP).withBlockRef(blockRef).withEventTime(System.currentTimeMillis()).withLocalContentId(localContentId).build());
                    dialogFragment.dismiss();
                }
                if (queryParameter3 != null) {
                    dialogFragment.dismiss();
                }
            } catch (Exception unused) {
                DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.DEEPLINK_FAILURE).withErrorMessage("Unable to handle provided internal deeplink:" + buttonPrimaryLink).withTrigger(Trigger.APP_START.getName()).send();
            }
        } finally {
            dialogFragment.dismiss();
        }
    }

    public static void dialogDismissHandler(final Dialog dialogFragment, final String blockRef, final String localContentId) {
        sendUIClickEvent(ActionType.DISMISS_IAM, blockRef, localContentId);
        dialogFragment.dismiss();
    }

    public static String getThirdPartyHostedBackgroundImageSize(final int width, final int height, final float density, final boolean orientationPortrait) {
        String str = orientationPortrait ? "Portrait" : "Landscape";
        String str2 = density >= 2.0f ? "Retina" : "Nonretina";
        String str3 = SIZE_SM;
        String str4 = SIZE_XS;
        String str5 = (!orientationPortrait || width <= IMAGE_BREAKPOINT_XS || width > IMAGE_BREAKPOINT_SM) ? SIZE_XS : SIZE_SM;
        String str6 = SIZE_LG;
        if (orientationPortrait && width > IMAGE_BREAKPOINT_SM && width <= IMAGE_BREAKPOINT_LG) {
            str5 = SIZE_LG;
        }
        String str7 = SIZE_XL;
        if (orientationPortrait && width > IMAGE_BREAKPOINT_LG) {
            str5 = SIZE_XL;
        }
        if (orientationPortrait || height > IMAGE_BREAKPOINT_XS) {
            str4 = str5;
        }
        if (orientationPortrait || height <= IMAGE_BREAKPOINT_XS || height > IMAGE_BREAKPOINT_SM) {
            str3 = str4;
        }
        if (orientationPortrait || height <= IMAGE_BREAKPOINT_SM || height > IMAGE_BREAKPOINT_LG) {
            str6 = str3;
        }
        if (orientationPortrait || height <= IMAGE_BREAKPOINT_LG) {
            str7 = str6;
        }
        return "&merged_size=" + (str7 + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + str + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + str2);
    }

    public static String chooseSplashBackgroundImageUrl(final int width, final int height, final float density, final boolean orientationPortrait, final Splash template) {
        if (orientationPortrait && width >= IMAGE_BREAKPOINT_SM) {
            if (density >= 2.0f) {
                return template.getBackgroundImagePortraitLargeRetinaSource();
            }
            return template.getBackgroundImagePortraitLargeSource();
        }
        if (!orientationPortrait && height < IMAGE_BREAKPOINT_SM) {
            if (density >= 2.0f) {
                return template.getBackgroundImageLandscapeSmallRetinaSource();
            }
            return template.getBackgroundImageLandscapeSmallSource();
        }
        if (orientationPortrait) {
            if (density >= 2.0f) {
                return template.getBackgroundImagePortraitSmallRetinaSource();
            }
            return template.getBackgroundImagePortraitSmallSource();
        }
        if (density >= 2.0f) {
            return template.getBackgroundImageLandscapeLargeRetinaSource();
        }
        return template.getBackgroundImageLandscapeLargeSource();
    }

    public static boolean textContainsTimerToken(final String displayText) {
        return displayText.contains("$<timer>");
    }

    public static CountDownTimer countdownTimer(final TextView textView, final String text, final String endTime, final Resources resources) throws ParseException {
        return new CountDownTimer(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss zzz", Locale.ENGLISH).parse(endTime).getTime() - System.currentTimeMillis(), 1000L) { // from class: com.amazon.music.inappmessaging.internal.util.DynamicMessageModalUtil.2
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String string;
                Resources resources2;
                int i;
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                int days = (int) timeUnit.toDays(millisUntilFinished);
                if (days > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(days + " ");
                    if (days > 1) {
                        resources2 = resources;
                        i = R.string.days;
                    } else {
                        resources2 = resources;
                        i = R.string.day;
                    }
                    sb.append(resources2.getString(i));
                    string = sb.toString();
                } else {
                    string = String.format(Locale.ENGLISH, "%02d:%02d:%02d", Long.valueOf(timeUnit.toHours(millisUntilFinished) % 60), Long.valueOf(timeUnit.toMinutes(millisUntilFinished) % 60), Long.valueOf(timeUnit.toSeconds(millisUntilFinished) % 60));
                }
                textView.setText(Html.fromHtml(text.replace("$<timer>", string)));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                textView.setText("");
                textView.setVisibility(8);
            }
        };
    }
}
