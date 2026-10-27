package com.amazon.mp3.util.extensions;

import android.content.Context;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.util.ContentEnabilityDialogUtil;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.playback.capabilities.error.CapabilityProviderErrorHandler;
import com.amazon.music.platform.playback.capabilities.error.EligibilityError;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EligibilityThrowable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002\u001a\f\u0010\b\u001a\u0004\u0018\u00010\u0005*\u00020\t\u001a\u0012\u0010\n\u001a\u00020\u0001*\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u000b"}, d2 = {"showError", "", "context", "Landroid/content/Context;", "reason", "Lcom/amazon/mp3/prime/ContentUnavailableReason;", "entity", "Lcom/amazon/music/platform/data/entity/EntityItem;", "contentUnavailableReason", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "setupErrorHandling", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class EligibilityThrowableKt {
    public static final ContentUnavailableReason contentUnavailableReason(EligibilityThrowable eligibilityThrowable) {
        Intrinsics.checkNotNullParameter(eligibilityThrowable, "<this>");
        if (eligibilityThrowable instanceof EligibilityThrowable.UnexpectedFailure) {
            return ContentUnavailableReason.UNHANDLED_ERROR;
        }
        if (Intrinsics.areEqual(eligibilityThrowable, EligibilityThrowable.NoActiveSubscription.INSTANCE) ? true : Intrinsics.areEqual(eligibilityThrowable, EligibilityThrowable.MissingAppStateEligibility.INSTANCE)) {
            return ContentUnavailableReason.ACCOUNT_INVALID;
        }
        if (Intrinsics.areEqual(eligibilityThrowable, EligibilityThrowable.ContentUnavailable.INSTANCE) ? true : Intrinsics.areEqual(eligibilityThrowable, EligibilityThrowable.MissingContentEligibility.INSTANCE)) {
            return ContentUnavailableReason.TRACK_NO_LONGER_AVAILABLE;
        }
        if (Intrinsics.areEqual(eligibilityThrowable, EligibilityThrowable.NoNetwork.INSTANCE)) {
            return ContentUnavailableReason.NO_NETWORK;
        }
        if (Intrinsics.areEqual(eligibilityThrowable, EligibilityThrowable.FeatureDisabled.INSTANCE)) {
            return AccountDetailUtil.get().isUserInPrimeOnlyMarketplace() ? ContentUnavailableReason.CUSTOMER_NOT_PRIME : ContentUnavailableReason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION;
        }
        return null;
    }

    public static final void setupErrorHandling(final EligibilityThrowable eligibilityThrowable, final Context context) {
        Intrinsics.checkNotNullParameter(eligibilityThrowable, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        CapabilityProviderErrorHandler.INSTANCE.addErrorHandling(eligibilityThrowable, new Function2<EligibilityError, EntityItem, Unit>() { // from class: com.amazon.mp3.util.extensions.EligibilityThrowableKt.setupErrorHandling.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(EligibilityError eligibilityError, EntityItem entityItem) {
                invoke2(eligibilityError, entityItem);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(EligibilityError noName_0, EntityItem entityItem) {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                ContentUnavailableReason contentUnavailableReason = EligibilityThrowableKt.contentUnavailableReason(eligibilityThrowable);
                if (contentUnavailableReason == null) {
                    return;
                }
                EligibilityThrowableKt.showError(context, contentUnavailableReason, entityItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showError(Context context, ContentUnavailableReason contentUnavailableReason, EntityItem entityItem) {
        String strDisplayName;
        String str = "";
        if (entityItem != null && (strDisplayName = EntityItemKt.displayName(entityItem)) != null) {
            str = strDisplayName;
        }
        ContentEnabilityDialogUtil.getDialogForDisabledReason(context, contentUnavailableReason, null, str);
    }
}
