package com.byd.dilink30.strategyservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.strategyservice.IStrategyListener;
import android.strategyservice.Strategy;
import android.strategyservice.StrategyManager;
import androidx.annotation.Keep;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.strategyservice.IDiLinkStrategyListener;
import com.byd.dilink.strategyservice.IDiLinkStrategyManager;
import com.byd.dilink.strategyservice.StrategyConstants;
import com.byd.dilink.strategyservice.StrategyValueHolder;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkStrategyManager.class, singleton = true)
public class DiLinkStrategyManager implements IDiLinkStrategyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLinkCallbackHolders<IDiLinkStrategyListener, IStrategyListener> f1236a = new DiLinkCallbackHolders<IDiLinkStrategyListener, IStrategyListener>() { // from class: com.byd.dilink30.strategyservice.DiLinkStrategyManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IStrategyListener createInnerCallback(final IDiLinkStrategyListener iDiLinkStrategyListener) {
            return new IStrategyListener.Stub() { // from class: com.byd.dilink30.strategyservice.DiLinkStrategyManager.1.1
                @Keep
                public void onStrategyChange(String str, List<String> list) {
                    iDiLinkStrategyListener.onStrategyChange(str, list);
                }
            };
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private StrategyManager f1237b;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Context context, String str, List list, IStrategyListener iStrategyListener) {
        b(context).unregisterListener(str, list, iStrategyListener);
    }

    @SuppressLint({"WrongConstant"})
    private StrategyManager b(Context context) {
        if (this.f1237b == null) {
            this.f1237b = (StrategyManager) context.getSystemService(StrategyConstants.STRATEGY_SERVICE);
        }
        return this.f1237b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(Context context, String str, List list, IStrategyListener iStrategyListener) {
        b(context).registerListener(str, list, iStrategyListener);
    }

    @Override // com.byd.dilink.strategyservice.IDiLinkStrategyManager
    public StrategyValueHolder getStrategy(Context context, String str, List<String> list) {
        Strategy strategy = b(context).getStrategy(str, list);
        StrategyValueHolder strategyValueHolder = new StrategyValueHolder();
        strategyValueHolder.settingConfigs = strategy.configs;
        strategyValueHolder.permissions = strategy.permissions;
        strategyValueHolder.strategyJson = strategy.strategyData;
        return strategyValueHolder;
    }

    @Override // com.byd.dilink.strategyservice.IDiLinkStrategyManager
    public void registerListener(final Context context, final String str, final List<String> list, IDiLinkStrategyListener iDiLinkStrategyListener) {
        this.f1236a.addCallbackIgnoreRemoteException(iDiLinkStrategyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.strategyservice.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1244a.c(context, str, list, (IStrategyListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.strategyservice.IDiLinkStrategyManager
    public void setStrategy(Context context, String str, StrategyValueHolder strategyValueHolder) {
        Strategy strategy = new Strategy();
        strategy.strategyData = strategyValueHolder.strategyJson;
        strategy.configs = strategyValueHolder.settingConfigs;
        strategy.permissions = strategyValueHolder.permissions;
        b(context).setStrategy(str, strategy);
    }

    @Override // com.byd.dilink.strategyservice.IDiLinkStrategyManager
    public void unregisterListener(final Context context, final String str, final List<String> list, IDiLinkStrategyListener iDiLinkStrategyListener) {
        this.f1236a.removeCallbackIgnoreRemoteException(iDiLinkStrategyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.strategyservice.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1241a.a(context, str, list, (IStrategyListener) obj);
            }
        });
    }
}
