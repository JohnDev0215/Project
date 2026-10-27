package com.amazon.ui.foundations.styles;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/ui/foundations/styles/LinkStyle;", "", "statefulStyle", "Lcom/amazon/ui/foundations/styles/StatefulStyle;", "showUnderlineWhenClick", "", "(Lcom/amazon/ui/foundations/styles/StatefulStyle;Ljava/lang/Boolean;)V", "getShowUnderlineWhenClick", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getStatefulStyle", "()Lcom/amazon/ui/foundations/styles/StatefulStyle;", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LinkStyle {
    private final Boolean showUnderlineWhenClick;
    private final StatefulStyle statefulStyle;

    public LinkStyle(StatefulStyle statefulStyle, Boolean bool) {
        Intrinsics.checkNotNullParameter(statefulStyle, "statefulStyle");
        this.statefulStyle = statefulStyle;
        this.showUnderlineWhenClick = bool;
    }

    public final StatefulStyle getStatefulStyle() {
        return this.statefulStyle;
    }

    public /* synthetic */ LinkStyle(StatefulStyle statefulStyle, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(statefulStyle, (i & 2) != 0 ? Boolean.FALSE : bool);
    }

    public final Boolean getShowUnderlineWhenClick() {
        return this.showUnderlineWhenClick;
    }
}
