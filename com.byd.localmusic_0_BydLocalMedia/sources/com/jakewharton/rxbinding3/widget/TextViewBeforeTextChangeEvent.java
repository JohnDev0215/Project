package com.jakewharton.rxbinding3.widget;

import android.provider.Telephony;
import android.text.style.SuggestionSpan;
import android.view.textclassifier.ConversationAction;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextViewBeforeTextChangeEventObservable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/jakewharton/rxbinding3/widget/TextViewBeforeTextChangeEvent;", "", "view", "Landroid/widget/TextView;", "text", "", Telephony.BaseMmsColumns.START, "", "count", SuggestionSpan.SUGGESTION_SPAN_PICKED_AFTER, "(Landroid/widget/TextView;Ljava/lang/CharSequence;III)V", "getAfter", "()I", "getCount", "getStart", "getText", "()Ljava/lang/CharSequence;", "getView", "()Landroid/widget/TextView;", "component1", "component2", "component3", "component4", "component5", ConversationAction.TYPE_COPY, "equals", "", "other", "hashCode", "toString", "", "rxbinding_release"}, k = 1, mv = {1, 1, 15})
public final /* data */ class TextViewBeforeTextChangeEvent {
    private final int after;
    private final int count;
    private final int start;
    private final CharSequence text;
    private final TextView view;

    public static /* synthetic */ TextViewBeforeTextChangeEvent copy$default(TextViewBeforeTextChangeEvent textViewBeforeTextChangeEvent, TextView textView, CharSequence charSequence, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            textView = textViewBeforeTextChangeEvent.view;
        }
        if ((i4 & 2) != 0) {
            charSequence = textViewBeforeTextChangeEvent.text;
        }
        CharSequence charSequence2 = charSequence;
        if ((i4 & 4) != 0) {
            i = textViewBeforeTextChangeEvent.start;
        }
        int i5 = i;
        if ((i4 & 8) != 0) {
            i2 = textViewBeforeTextChangeEvent.count;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            i3 = textViewBeforeTextChangeEvent.after;
        }
        return textViewBeforeTextChangeEvent.copy(textView, charSequence2, i5, i6, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TextView getView() {
        return this.view;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CharSequence getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getStart() {
        return this.start;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getAfter() {
        return this.after;
    }

    public final TextViewBeforeTextChangeEvent copy(TextView view, CharSequence text, int start, int count, int after) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return new TextViewBeforeTextChangeEvent(view, text, start, count, after);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof TextViewBeforeTextChangeEvent) {
                TextViewBeforeTextChangeEvent textViewBeforeTextChangeEvent = (TextViewBeforeTextChangeEvent) other;
                if (Intrinsics.areEqual(this.view, textViewBeforeTextChangeEvent.view) && Intrinsics.areEqual(this.text, textViewBeforeTextChangeEvent.text)) {
                    if (this.start == textViewBeforeTextChangeEvent.start) {
                        if (this.count == textViewBeforeTextChangeEvent.count) {
                            if (this.after == textViewBeforeTextChangeEvent.after) {
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        TextView textView = this.view;
        int iHashCode = (textView != null ? textView.hashCode() : 0) * 31;
        CharSequence charSequence = this.text;
        return ((((((iHashCode + (charSequence != null ? charSequence.hashCode() : 0)) * 31) + this.start) * 31) + this.count) * 31) + this.after;
    }

    public String toString() {
        return "TextViewBeforeTextChangeEvent(view=" + this.view + ", text=" + this.text + ", start=" + this.start + ", count=" + this.count + ", after=" + this.after + ")";
    }

    public TextViewBeforeTextChangeEvent(TextView view, CharSequence text, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.view = view;
        this.text = text;
        this.start = i;
        this.count = i2;
        this.after = i3;
    }

    public final TextView getView() {
        return this.view;
    }

    public final CharSequence getText() {
        return this.text;
    }

    public final int getStart() {
        return this.start;
    }

    public final int getCount() {
        return this.count;
    }

    public final int getAfter() {
        return this.after;
    }
}
