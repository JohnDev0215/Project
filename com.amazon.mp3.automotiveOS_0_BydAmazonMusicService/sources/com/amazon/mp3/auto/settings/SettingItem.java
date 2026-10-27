package com.amazon.mp3.auto.settings;

import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingItem.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0012\u001a\u00020\f¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/auto/settings/SettingItem;", "", "", "text", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "", "iconId", "I", "getIconId", "()I", "", "isChecked", "Z", "()Z", "setChecked", "(Z)V", Constants.ENABLE_DISABLE, "setEnabled", "<init>", "(Ljava/lang/String;IZZ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SettingItem {
    private final int iconId;
    private boolean isChecked;
    private boolean isEnabled;
    private final String text;

    public SettingItem(String text, int i, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.iconId = i;
        this.isChecked = z;
        this.isEnabled = z2;
    }

    public final String getText() {
        return this.text;
    }

    public final int getIconId() {
        return this.iconId;
    }

    /* JADX INFO: renamed from: isChecked, reason: from getter */
    public final boolean getIsChecked() {
        return this.isChecked;
    }

    public final void setChecked(boolean z) {
        this.isChecked = z;
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }
}
