package com.amazon.music.connect.customerlookup;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerLookupEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001aB\u001b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/connect/customerlookup/CustomerLookupEvent;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/connect/customerlookup/CustomerLookupEvent$FragmentAction;", "fragmentAction", "Lcom/amazon/music/connect/customerlookup/CustomerLookupEvent$FragmentAction;", "getFragmentAction", "()Lcom/amazon/music/connect/customerlookup/CustomerLookupEvent$FragmentAction;", "profileUrl", "Ljava/lang/String;", "getProfileUrl", "()Ljava/lang/String;", "processed", "Z", "getProcessed", "()Z", "setProcessed", "(Z)V", "<init>", "(Lcom/amazon/music/connect/customerlookup/CustomerLookupEvent$FragmentAction;Ljava/lang/String;)V", "FragmentAction", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CustomerLookupEvent {
    private final FragmentAction fragmentAction;
    private boolean processed;
    private final String profileUrl;

    /* JADX INFO: compiled from: CustomerLookupEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/connect/customerlookup/CustomerLookupEvent$FragmentAction;", "", "(Ljava/lang/String;I)V", "FRAGMENT_RESUMED", "FRAGMENT_DESTROYED", "PROFILE_NAVIGATION", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum FragmentAction {
        FRAGMENT_RESUMED,
        FRAGMENT_DESTROYED,
        PROFILE_NAVIGATION
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerLookupEvent)) {
            return false;
        }
        CustomerLookupEvent customerLookupEvent = (CustomerLookupEvent) other;
        return this.fragmentAction == customerLookupEvent.fragmentAction && Intrinsics.areEqual(this.profileUrl, customerLookupEvent.profileUrl);
    }

    public int hashCode() {
        FragmentAction fragmentAction = this.fragmentAction;
        int iHashCode = (fragmentAction == null ? 0 : fragmentAction.hashCode()) * 31;
        String str = this.profileUrl;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "CustomerLookupEvent(fragmentAction=" + this.fragmentAction + ", profileUrl=" + ((Object) this.profileUrl) + ')';
    }

    public CustomerLookupEvent(FragmentAction fragmentAction, String str) {
        this.fragmentAction = fragmentAction;
        this.profileUrl = str;
    }

    public final FragmentAction getFragmentAction() {
        return this.fragmentAction;
    }

    public final String getProfileUrl() {
        return this.profileUrl;
    }

    public final boolean getProcessed() {
        return this.processed;
    }

    public final void setProcessed(boolean z) {
        this.processed = z;
    }
}
