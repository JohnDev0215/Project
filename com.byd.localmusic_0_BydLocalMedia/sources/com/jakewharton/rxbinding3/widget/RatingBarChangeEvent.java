package com.jakewharton.rxbinding3.widget;

import android.view.textclassifier.ConversationAction;
import android.widget.RatingBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RatingBarRatingChangeEventObservable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/jakewharton/rxbinding3/widget/RatingBarChangeEvent;", "", "view", "Landroid/widget/RatingBar;", "rating", "", "fromUser", "", "(Landroid/widget/RatingBar;FZ)V", "getFromUser", "()Z", "getRating", "()F", "getView", "()Landroid/widget/RatingBar;", "component1", "component2", "component3", ConversationAction.TYPE_COPY, "equals", "other", "hashCode", "", "toString", "", "rxbinding_release"}, k = 1, mv = {1, 1, 15})
public final /* data */ class RatingBarChangeEvent {
    private final boolean fromUser;
    private final float rating;
    private final RatingBar view;

    public static /* synthetic */ RatingBarChangeEvent copy$default(RatingBarChangeEvent ratingBarChangeEvent, RatingBar ratingBar, float f, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            ratingBar = ratingBarChangeEvent.view;
        }
        if ((i & 2) != 0) {
            f = ratingBarChangeEvent.rating;
        }
        if ((i & 4) != 0) {
            z = ratingBarChangeEvent.fromUser;
        }
        return ratingBarChangeEvent.copy(ratingBar, f, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final RatingBar getView() {
        return this.view;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getFromUser() {
        return this.fromUser;
    }

    public final RatingBarChangeEvent copy(RatingBar view, float rating, boolean fromUser) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        return new RatingBarChangeEvent(view, rating, fromUser);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof RatingBarChangeEvent) {
                RatingBarChangeEvent ratingBarChangeEvent = (RatingBarChangeEvent) other;
                if (Intrinsics.areEqual(this.view, ratingBarChangeEvent.view) && Float.compare(this.rating, ratingBarChangeEvent.rating) == 0) {
                    if (this.fromUser == ratingBarChangeEvent.fromUser) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public int hashCode() {
        RatingBar ratingBar = this.view;
        int iHashCode = (((ratingBar != null ? ratingBar.hashCode() : 0) * 31) + Float.floatToIntBits(this.rating)) * 31;
        boolean z = this.fromUser;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "RatingBarChangeEvent(view=" + this.view + ", rating=" + this.rating + ", fromUser=" + this.fromUser + ")";
    }

    public RatingBarChangeEvent(RatingBar view, float f, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.view = view;
        this.rating = f;
        this.fromUser = z;
    }

    public final RatingBar getView() {
        return this.view;
    }

    public final float getRating() {
        return this.rating;
    }

    public final boolean getFromUser() {
        return this.fromUser;
    }
}
