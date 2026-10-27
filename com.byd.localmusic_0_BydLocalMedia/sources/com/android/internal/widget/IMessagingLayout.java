package com.android.internal.widget;

import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public interface IMessagingLayout {
    Context getContext();

    ArrayList<MessagingGroup> getMessagingGroups();

    MessagingLinearLayout getMessagingLinearLayout();

    void setMessagingClippingDisabled(boolean z);
}
