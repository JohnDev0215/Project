package com.jakewharton.rxbinding3.widget;

import android.view.View;
import android.view.textclassifier.ConversationAction;
import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdapterViewItemClickEventObservable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\r\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/jakewharton/rxbinding3/widget/AdapterViewItemClickEvent;", "", "view", "Landroid/widget/AdapterView;", "clickedView", "Landroid/view/View;", "position", "", "id", "", "(Landroid/widget/AdapterView;Landroid/view/View;IJ)V", "getClickedView", "()Landroid/view/View;", "getId", "()J", "getPosition", "()I", "getView", "()Landroid/widget/AdapterView;", "component1", "component2", "component3", "component4", ConversationAction.TYPE_COPY, "equals", "", "other", "hashCode", "toString", "", "rxbinding_release"}, k = 1, mv = {1, 1, 15})
public final /* data */ class AdapterViewItemClickEvent {
    private final View clickedView;
    private final long id;
    private final int position;
    private final AdapterView<?> view;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AdapterViewItemClickEvent copy$default(AdapterViewItemClickEvent adapterViewItemClickEvent, AdapterView adapterView, View view, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            adapterView = adapterViewItemClickEvent.view;
        }
        if ((i2 & 2) != 0) {
            view = adapterViewItemClickEvent.clickedView;
        }
        View view2 = view;
        if ((i2 & 4) != 0) {
            i = adapterViewItemClickEvent.position;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            j = adapterViewItemClickEvent.id;
        }
        return adapterViewItemClickEvent.copy(adapterView, view2, i3, j);
    }

    public final AdapterView<?> component1() {
        return this.view;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final View getClickedView() {
        return this.clickedView;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getId() {
        return this.id;
    }

    public final AdapterViewItemClickEvent copy(AdapterView<?> view, View clickedView, int position, long id) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        return new AdapterViewItemClickEvent(view, clickedView, position, id);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof AdapterViewItemClickEvent) {
                AdapterViewItemClickEvent adapterViewItemClickEvent = (AdapterViewItemClickEvent) other;
                if (Intrinsics.areEqual(this.view, adapterViewItemClickEvent.view) && Intrinsics.areEqual(this.clickedView, adapterViewItemClickEvent.clickedView)) {
                    if (this.position == adapterViewItemClickEvent.position) {
                        if (this.id == adapterViewItemClickEvent.id) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        AdapterView<?> adapterView = this.view;
        int iHashCode = (adapterView != null ? adapterView.hashCode() : 0) * 31;
        View view = this.clickedView;
        int iHashCode2 = (((iHashCode + (view != null ? view.hashCode() : 0)) * 31) + this.position) * 31;
        long j = this.id;
        return iHashCode2 + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "AdapterViewItemClickEvent(view=" + this.view + ", clickedView=" + this.clickedView + ", position=" + this.position + ", id=" + this.id + ")";
    }

    public AdapterViewItemClickEvent(AdapterView<?> view, View view2, int i, long j) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.view = view;
        this.clickedView = view2;
        this.position = i;
        this.id = j;
    }

    public final AdapterView<?> getView() {
        return this.view;
    }

    public final View getClickedView() {
        return this.clickedView;
    }

    public final int getPosition() {
        return this.position;
    }

    public final long getId() {
        return this.id;
    }
}
