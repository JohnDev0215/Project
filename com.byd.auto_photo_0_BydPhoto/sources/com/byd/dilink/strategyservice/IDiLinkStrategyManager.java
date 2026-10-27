package com.byd.dilink.strategyservice;

import android.content.Context;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkStrategyManager {
    StrategyValueHolder getStrategy(Context context, String str, List<String> list);

    void registerListener(Context context, String str, List<String> list, IDiLinkStrategyListener iDiLinkStrategyListener);

    void setStrategy(Context context, String str, StrategyValueHolder strategyValueHolder);

    void unregisterListener(Context context, String str, List<String> list, IDiLinkStrategyListener iDiLinkStrategyListener);
}
