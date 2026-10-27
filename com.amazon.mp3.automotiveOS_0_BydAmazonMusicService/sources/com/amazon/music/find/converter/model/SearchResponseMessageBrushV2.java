package com.amazon.music.find.converter.model;

import com.amazon.music.search.SpellCorrections;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.musicensembleservice.brush.Entity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchResponseMessageBrushV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0006¢\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010(\u001a\u00020\fHÆ\u0003J\t\u0010)\u001a\u00020\fHÆ\u0003J\t\u0010*\u001a\u00020\u000fHÆ\u0003Ju\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0006HÆ\u0001J\u0013\u0010,\u001a\u00020\u00062\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015¨\u00062"}, d2 = {"Lcom/amazon/music/find/converter/model/SearchResponseMessageBrushV2;", "Lcom/amazon/musicensembleservice/brush/Entity;", "viewId", "", "secondaryLinkText", "noResultFound", "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "primaryMessage", "secondaryMessage", "primaryMessageFontStyle", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "secondaryMessageFontStyle", "spacerBetweenMessages", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "shouldCenterMessage", "(Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/styles/keys/FontStyleKey;Lcom/amazon/music/views/library/styles/keys/FontStyleKey;Lcom/amazon/music/views/library/styles/keys/SpacerKey;Z)V", "getNoResultFound", "()Z", "getPrimaryMessage", "()Ljava/lang/String;", "getPrimaryMessageFontStyle", "()Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "getSecondaryLinkText", "getSecondaryMessage", "getSecondaryMessageFontStyle", "getShouldCenterMessage", "getSpacerBetweenMessages", "()Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "getSpellCorrections", "()Lcom/amazon/music/search/SpellCorrections;", "getViewId", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchResponseMessageBrushV2 extends Entity {
    private final boolean noResultFound;
    private final String primaryMessage;
    private final FontStyleKey primaryMessageFontStyle;
    private final String secondaryLinkText;
    private final String secondaryMessage;
    private final FontStyleKey secondaryMessageFontStyle;
    private final boolean shouldCenterMessage;
    private final SpacerKey spacerBetweenMessages;
    private final SpellCorrections spellCorrections;
    private final String viewId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getViewId() {
        return this.viewId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getShouldCenterMessage() {
        return this.shouldCenterMessage;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSecondaryLinkText() {
        return this.secondaryLinkText;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getNoResultFound() {
        return this.noResultFound;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SpellCorrections getSpellCorrections() {
        return this.spellCorrections;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPrimaryMessage() {
        return this.primaryMessage;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSecondaryMessage() {
        return this.secondaryMessage;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final FontStyleKey getPrimaryMessageFontStyle() {
        return this.primaryMessageFontStyle;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final FontStyleKey getSecondaryMessageFontStyle() {
        return this.secondaryMessageFontStyle;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final SpacerKey getSpacerBetweenMessages() {
        return this.spacerBetweenMessages;
    }

    public final SearchResponseMessageBrushV2 copy(String viewId, String secondaryLinkText, boolean noResultFound, SpellCorrections spellCorrections, String primaryMessage, String secondaryMessage, FontStyleKey primaryMessageFontStyle, FontStyleKey secondaryMessageFontStyle, SpacerKey spacerBetweenMessages, boolean shouldCenterMessage) {
        Intrinsics.checkNotNullParameter(viewId, "viewId");
        Intrinsics.checkNotNullParameter(primaryMessageFontStyle, "primaryMessageFontStyle");
        Intrinsics.checkNotNullParameter(secondaryMessageFontStyle, "secondaryMessageFontStyle");
        Intrinsics.checkNotNullParameter(spacerBetweenMessages, "spacerBetweenMessages");
        return new SearchResponseMessageBrushV2(viewId, secondaryLinkText, noResultFound, spellCorrections, primaryMessage, secondaryMessage, primaryMessageFontStyle, secondaryMessageFontStyle, spacerBetweenMessages, shouldCenterMessage);
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchResponseMessageBrushV2)) {
            return false;
        }
        SearchResponseMessageBrushV2 searchResponseMessageBrushV2 = (SearchResponseMessageBrushV2) other;
        return Intrinsics.areEqual(this.viewId, searchResponseMessageBrushV2.viewId) && Intrinsics.areEqual(this.secondaryLinkText, searchResponseMessageBrushV2.secondaryLinkText) && this.noResultFound == searchResponseMessageBrushV2.noResultFound && Intrinsics.areEqual(this.spellCorrections, searchResponseMessageBrushV2.spellCorrections) && Intrinsics.areEqual(this.primaryMessage, searchResponseMessageBrushV2.primaryMessage) && Intrinsics.areEqual(this.secondaryMessage, searchResponseMessageBrushV2.secondaryMessage) && this.primaryMessageFontStyle == searchResponseMessageBrushV2.primaryMessageFontStyle && this.secondaryMessageFontStyle == searchResponseMessageBrushV2.secondaryMessageFontStyle && this.spacerBetweenMessages == searchResponseMessageBrushV2.spacerBetweenMessages && this.shouldCenterMessage == searchResponseMessageBrushV2.shouldCenterMessage;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    @Override // com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        int iHashCode = this.viewId.hashCode() * 31;
        String str = this.secondaryLinkText;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        boolean z = this.noResultFound;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode2 + r1) * 31;
        SpellCorrections spellCorrections = this.spellCorrections;
        int iHashCode3 = (i + (spellCorrections == null ? 0 : spellCorrections.hashCode())) * 31;
        String str2 = this.primaryMessage;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.secondaryMessage;
        int iHashCode5 = (((((((iHashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.primaryMessageFontStyle.hashCode()) * 31) + this.secondaryMessageFontStyle.hashCode()) * 31) + this.spacerBetweenMessages.hashCode()) * 31;
        boolean z2 = this.shouldCenterMessage;
        return iHashCode5 + (z2 ? 1 : z2);
    }

    public String toString() {
        return "SearchResponseMessageBrushV2(viewId=" + this.viewId + ", secondaryLinkText=" + ((Object) this.secondaryLinkText) + ", noResultFound=" + this.noResultFound + ", spellCorrections=" + this.spellCorrections + ", primaryMessage=" + ((Object) this.primaryMessage) + ", secondaryMessage=" + ((Object) this.secondaryMessage) + ", primaryMessageFontStyle=" + this.primaryMessageFontStyle + ", secondaryMessageFontStyle=" + this.secondaryMessageFontStyle + ", spacerBetweenMessages=" + this.spacerBetweenMessages + ", shouldCenterMessage=" + this.shouldCenterMessage + ')';
    }

    public final String getViewId() {
        return this.viewId;
    }

    public final String getSecondaryLinkText() {
        return this.secondaryLinkText;
    }

    public final boolean getNoResultFound() {
        return this.noResultFound;
    }

    public final SpellCorrections getSpellCorrections() {
        return this.spellCorrections;
    }

    public final String getPrimaryMessage() {
        return this.primaryMessage;
    }

    public final String getSecondaryMessage() {
        return this.secondaryMessage;
    }

    public final FontStyleKey getPrimaryMessageFontStyle() {
        return this.primaryMessageFontStyle;
    }

    public final FontStyleKey getSecondaryMessageFontStyle() {
        return this.secondaryMessageFontStyle;
    }

    public final SpacerKey getSpacerBetweenMessages() {
        return this.spacerBetweenMessages;
    }

    public final boolean getShouldCenterMessage() {
        return this.shouldCenterMessage;
    }

    public SearchResponseMessageBrushV2(String viewId, String str, boolean z, SpellCorrections spellCorrections, String str2, String str3, FontStyleKey primaryMessageFontStyle, FontStyleKey secondaryMessageFontStyle, SpacerKey spacerBetweenMessages, boolean z2) {
        Intrinsics.checkNotNullParameter(viewId, "viewId");
        Intrinsics.checkNotNullParameter(primaryMessageFontStyle, "primaryMessageFontStyle");
        Intrinsics.checkNotNullParameter(secondaryMessageFontStyle, "secondaryMessageFontStyle");
        Intrinsics.checkNotNullParameter(spacerBetweenMessages, "spacerBetweenMessages");
        this.viewId = viewId;
        this.secondaryLinkText = str;
        this.noResultFound = z;
        this.spellCorrections = spellCorrections;
        this.primaryMessage = str2;
        this.secondaryMessage = str3;
        this.primaryMessageFontStyle = primaryMessageFontStyle;
        this.secondaryMessageFontStyle = secondaryMessageFontStyle;
        this.spacerBetweenMessages = spacerBetweenMessages;
        this.shouldCenterMessage = z2;
    }
}
