package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.music.alps.R$color;
import com.amazon.music.alps.R$string;
import com.amazon.music.alps.liveevent.LiveEventStatus;
import com.amazon.music.alps.liveevent.viewmodel.FlexibleTextContainerViewModel;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__IndentKt;

/* JADX INFO: compiled from: EventDescriptionConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\b\u0010\u000b\u001a\u00020\u0004H\u0007R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\u00048\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/EventDescriptionConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "data", "", "lineupGroupDescription", "afterglow", "descriptionIfPresent", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "locationIfPresent", "getPrimaryTextColor", "Landroid/content/Context;", "context", "Landroid/content/Context;", "primaryTextColor", "Ljava/lang/String;", "locationLabel", "<init>", "(Landroid/content/Context;)V", "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class EventDescriptionConverter implements SingleBaseModelConverter<LiveEvent> {
    private final Context context;
    private final String locationLabel;
    private String primaryTextColor;

    /* JADX INFO: compiled from: EventDescriptionConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LiveEventStatus.values().length];
            iArr[LiveEventStatus.AFTERGLOW.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public EventDescriptionConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        String string = context.getString(R$string.location);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.location)");
        this.locationLabel = string;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(LiveEvent data) {
        String strLineupGroupDescription;
        Intrinsics.checkNotNullParameter(data, "data");
        this.primaryTextColor = getPrimaryTextColor();
        if (WhenMappings.$EnumSwitchMapping$0[LiveEventStatus.INSTANCE.from(data.getStatus()).ordinal()] == 1) {
            strLineupGroupDescription = afterglow(data);
        } else {
            strLineupGroupDescription = lineupGroupDescription(data);
        }
        return new FlexibleTextContainerViewModel(null, strLineupGroupDescription, this.context.getString(R$string.read_more), this.context.getString(R$string.read_less), 9, 1, null);
    }

    private final String lineupGroupDescription(LiveEvent data) {
        return StringsKt__IndentKt.trimIndent("\n            " + locationIfPresent(data) + "\n            " + descriptionIfPresent(data) + "\n        ");
    }

    private final String afterglow(LiveEvent data) {
        return StringsKt__IndentKt.trimIndent("\n            " + descriptionIfPresent(data) + "\n        ");
    }

    @VisibleForTesting
    public final String locationIfPresent(LiveEvent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        String location = data.getLocation();
        if (location == null || location.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<p><b><font color=\"#");
        String str = this.primaryTextColor;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("primaryTextColor");
            str = null;
        }
        sb.append(str);
        sb.append("\">");
        sb.append(this.locationLabel);
        sb.append(": ");
        sb.append((Object) data.getLocation());
        sb.append("</b></p>");
        return sb.toString();
    }

    private final String descriptionIfPresent(LiveEvent data) {
        String description = data.getDescription();
        if (description == null || description.length() == 0) {
            return "";
        }
        return "<p>" + ((Object) data.getDescription()) + "</p>";
    }

    @VisibleForTesting
    public final String getPrimaryTextColor() {
        int color = ContextCompat.getColor(this.context, R$color.primary);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%X", Arrays.copyOf(new Object[]{Integer.valueOf(color)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        String strSubstring = str.substring(2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }
}
