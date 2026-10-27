package com.byd.auto_emanual;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;

/* JADX INFO: loaded from: classes.dex */
public interface IStopOrGo {
    Activity getSelf();

    AlertDialog getStopOrGoDialog();

    boolean isForeground();

    void setStopOrGoDialog(AlertDialog dialog);

    void showStopOrGoDialog(boolean showStopOrGo);
}
