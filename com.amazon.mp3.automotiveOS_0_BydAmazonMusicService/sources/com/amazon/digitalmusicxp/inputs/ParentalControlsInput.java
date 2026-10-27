package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: ParentalControlsInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "hasExplicitLanguage", "Z", "getHasExplicitLanguage", "()Z", "<init>", "(Z)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ParentalControlsInput {
    private final boolean hasExplicitLanguage;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ParentalControlsInput) && this.hasExplicitLanguage == ((ParentalControlsInput) other).hasExplicitLanguage;
    }

    public int hashCode() {
        boolean z = this.hasExplicitLanguage;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "ParentalControlsInput(hasExplicitLanguage=" + this.hasExplicitLanguage + ')';
    }

    public ParentalControlsInput(boolean z) {
        this.hasExplicitLanguage = z;
        ExpectedFunctionsKt.freeze(this);
    }

    public final boolean getHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }
}
