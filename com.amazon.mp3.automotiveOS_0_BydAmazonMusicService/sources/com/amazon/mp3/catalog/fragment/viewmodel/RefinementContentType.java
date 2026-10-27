package com.amazon.mp3.catalog.fragment.viewmodel;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementsModelBuilder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType;", "", "()V", "Group", "Refinement", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType$Refinement;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType$Group;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class RefinementContentType {
    public /* synthetic */ RefinementContentType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: RefinementsModelBuilder.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\bR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType$Refinement;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "isDefaultValue", "", "isSelected", "(Lcom/amazon/mp3/view/refinements/RefinementFilterType;ZZ)V", "()Z", "setSelected", "(Z)V", "getType", "()Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Refinement extends RefinementContentType {
        private final boolean isDefaultValue;
        private boolean isSelected;
        private final RefinementFilterType type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Refinement(RefinementFilterType type, boolean z, boolean z2) {
            super(null);
            Intrinsics.checkNotNullParameter(type, "type");
            this.type = type;
            this.isDefaultValue = z;
            this.isSelected = z2;
        }

        public /* synthetic */ Refinement(RefinementFilterType refinementFilterType, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(refinementFilterType, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2);
        }

        public final RefinementFilterType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: isDefaultValue, reason: from getter */
        public final boolean getIsDefaultValue() {
            return this.isDefaultValue;
        }

        /* JADX INFO: renamed from: isSelected, reason: from getter */
        public final boolean getIsSelected() {
            return this.isSelected;
        }

        public final void setSelected(boolean z) {
            this.isSelected = z;
        }
    }

    private RefinementContentType() {
    }

    /* JADX INFO: compiled from: RefinementsModelBuilder.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType$Group;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType;", "groupType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementGroupType;", "refinements", "", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType$Refinement;", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementGroupType;Ljava/util/List;)V", "getGroupType", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementGroupType;", "getRefinements", "()Ljava/util/List;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Group extends RefinementContentType {
        private final RefinementGroupType groupType;
        private final List<Refinement> refinements;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Group(RefinementGroupType groupType, List<Refinement> refinements) {
            super(null);
            Intrinsics.checkNotNullParameter(groupType, "groupType");
            Intrinsics.checkNotNullParameter(refinements, "refinements");
            this.groupType = groupType;
            this.refinements = refinements;
        }

        public final RefinementGroupType getGroupType() {
            return this.groupType;
        }

        public final List<Refinement> getRefinements() {
            return this.refinements;
        }
    }
}
