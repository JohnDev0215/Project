package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.StateModel;
import com.amazon.music.views.library.models.enums.IconType;
import com.amazon.music.views.library.models.enums.LinkNavigatorType;
import com.amazon.music.views.library.models.enums.TargetType;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkNavigatorModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u00012\u00020\u0002B{\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010%\u001a\u00020\f\u0012\b\b\u0002\u0010(\u001a\u00020\f\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b0\u00101R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\b\u0010\u0005\u001a\u0004\b\t\u0010\u0007R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\n\u0010\u0005\u001a\u0004\b\u000b\u0010\u0007R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\r\u0010\u000fR$\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0018\u001a\u00020\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010%\u001a\u00020\f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b%\u0010\u000e\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010'R\"\u0010(\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010\u000e\u001a\u0004\b)\u0010\u000f\"\u0004\b*\u0010'R$\u0010+\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010\u0005\u001a\u0004\b,\u0010\u0007\"\u0004\b-\u0010.¨\u00062"}, d2 = {"Lcom/amazon/music/views/library/models/LinkNavigatorModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/models/base/StateModel;", "", "text", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "target", "getTarget", "backgroundImageUrl", "getBackgroundImageUrl", "", Constants.ENABLE_DISABLE, "Z", "()Z", "Lcom/amazon/music/views/library/models/enums/TargetType;", "targetType", "Lcom/amazon/music/views/library/models/enums/TargetType;", "getTargetType", "()Lcom/amazon/music/views/library/models/enums/TargetType;", "setTargetType", "(Lcom/amazon/music/views/library/models/enums/TargetType;)V", "Lcom/amazon/music/views/library/models/enums/LinkNavigatorType;", "linkNavigatorType", "Lcom/amazon/music/views/library/models/enums/LinkNavigatorType;", "getLinkNavigatorType", "()Lcom/amazon/music/views/library/models/enums/LinkNavigatorType;", "setLinkNavigatorType", "(Lcom/amazon/music/views/library/models/enums/LinkNavigatorType;)V", "Lcom/amazon/music/views/library/models/enums/IconType;", "iconType", "Lcom/amazon/music/views/library/models/enums/IconType;", "getIconType", "()Lcom/amazon/music/views/library/models/enums/IconType;", "setIconType", "(Lcom/amazon/music/views/library/models/enums/IconType;)V", "isActive", "setActive", "(Z)V", "shouldShowNewBadge", "getShouldShowNewBadge", "setShouldShowNewBadge", "deemphasizedText", "getDeemphasizedText", "setDeemphasizedText", "(Ljava/lang/String;)V", "viewId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/music/views/library/models/enums/TargetType;Lcom/amazon/music/views/library/models/enums/LinkNavigatorType;Lcom/amazon/music/views/library/models/enums/IconType;ZZLjava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class LinkNavigatorModel extends BaseViewModel implements StateModel {
    private final String backgroundImageUrl;
    private String deemphasizedText;
    private IconType iconType;
    private boolean isActive;
    private final boolean isEnabled;
    private LinkNavigatorType linkNavigatorType;
    private boolean shouldShowNewBadge;
    private final String target;
    private TargetType targetType;
    private final String text;

    public final String getText() {
        return this.text;
    }

    public final String getTarget() {
        return this.target;
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public final TargetType getTargetType() {
        return this.targetType;
    }

    public final void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public /* synthetic */ LinkNavigatorModel(String str, String str2, String str3, String str4, boolean z, TargetType targetType, LinkNavigatorType linkNavigatorType, IconType iconType, boolean z2, boolean z3, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? true : z, (i & 32) != 0 ? null : targetType, (i & 64) != 0 ? LinkNavigatorType.MEDIUM : linkNavigatorType, (i & 128) != 0 ? IconType.NAV : iconType, (i & 256) != 0 ? false : z2, (i & 512) != 0 ? false : z3, (i & 1024) != 0 ? null : str5);
    }

    public final LinkNavigatorType getLinkNavigatorType() {
        return this.linkNavigatorType;
    }

    public final void setLinkNavigatorType(LinkNavigatorType linkNavigatorType) {
        Intrinsics.checkNotNullParameter(linkNavigatorType, "<set-?>");
        this.linkNavigatorType = linkNavigatorType;
    }

    public final IconType getIconType() {
        return this.iconType;
    }

    public final void setIconType(IconType iconType) {
        this.iconType = iconType;
    }

    /* JADX INFO: renamed from: isActive, reason: from getter */
    public boolean getIsActive() {
        return this.isActive;
    }

    @Override // com.amazon.music.views.library.models.base.StateModel
    public void setActive(boolean z) {
        this.isActive = z;
    }

    public final boolean getShouldShowNewBadge() {
        return this.shouldShowNewBadge;
    }

    public final void setShouldShowNewBadge(boolean z) {
        this.shouldShowNewBadge = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LinkNavigatorModel(String str, String text, String target, String str2, boolean z, TargetType targetType, LinkNavigatorType linkNavigatorType, IconType iconType, boolean z2, boolean z3, String str3) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(linkNavigatorType, "linkNavigatorType");
        this.text = text;
        this.target = target;
        this.backgroundImageUrl = str2;
        this.isEnabled = z;
        this.targetType = targetType;
        this.linkNavigatorType = linkNavigatorType;
        this.iconType = iconType;
        this.isActive = z2;
        this.shouldShowNewBadge = z3;
        this.deemphasizedText = str3;
    }

    public final String getDeemphasizedText() {
        return this.deemphasizedText;
    }

    public final void setDeemphasizedText(String str) {
        this.deemphasizedText = str;
    }
}
