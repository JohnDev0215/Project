package com.loostone.libtuning.inf.extern;

/* JADX INFO: loaded from: classes.dex */
public interface IChannelInfo {
    IMicControl getMicControlService();

    IBaseKeyService getMicService();

    int getPowerStatus();

    int getVerifyStatus();

    boolean hasDongle();
}
