package com.byd.dilink_rse.strategyservice;

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

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkStrategyManager.class, singleton = true)
public class DiLinkStrategyManager implements IDiLinkStrategyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLinkCallbackHolders<IDiLinkStrategyListener, IStrategyListener> f2791a = new DiLinkCallbackHolders<IDiLinkStrategyListener, IStrategyListener>() { // from class: com.byd.dilink_rse.strategyservice.DiLinkStrategyManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IStrategyListener createInnerCallback(final IDiLinkStrategyListener iDiLinkStrategyListener) {
            return new IStrategyListener.Stub() { // from class: com.byd.dilink_rse.strategyservice.DiLinkStrategyManager.1.1
                @Keep
                public void onStrategyChange(String str, List<String> list) {
                    iDiLinkStrategyListener.onStrategyChange(str, list);
                }
            };
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private StrategyManager f2792b;

    @SuppressLint({"WrongConstant"})
    private StrategyManager a(Context context) {
        if (this.f2792b == null) {
            this.f2792b = (StrategyManager) context.getSystemService(StrategyConstants.STRATEGY_SERVICE);
        }
        return this.f2792b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(Context context, String str, List list, IStrategyListener iStrategyListener) {
        a(context).registerListener(str, list, iStrategyListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(Context context, String str, List list, IStrategyListener iStrategyListener) {
        a(context).unregisterListener(str, list, iStrategyListener);
    }

    @Override // com.byd.dilink.strategyservice.IDiLinkStrategyManager
    public StrategyValueHolder getStrategy(Context context, String str, List<String> list) {
        Strategy strategy = a(context).getStrategy(str, list);
        StrategyValueHolder strategyValueHolder = new StrategyValueHolder();
        strategyValueHolder.settingConfigs = strategy.configs;
        strategyValueHolder.permissions = strategy.permissions;
        strategyValueHolder.strategyJson = strategy.strategyData;
        return strategyValueHolder;
    }

    @Override // com.byd.dilink.strategyservice.IDiLinkStrategyManager
    public void registerListener(final Context context, final String str, final List<String> list, IDiLinkStrategyListener iDiLinkStrategyListener) {
        this.f2791a.addCallbackIgnoreRemoteException(iDiLinkStrategyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.strategyservice.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2799a.b(context, str, list, (IStrategyListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.strategyservice.IDiLinkStrategyManager
    public void setStrategy(Context context, String str, StrategyValueHolder strategyValueHolder) {
        Strategy strategy = new Strategy();
        strategy.strategyData = strategyValueHolder.strategyJson;
        strategy.configs = strategyValueHolder.settingConfigs;
        strategy.permissions = strategyValueHolder.permissions;
        a(context).setStrategy(str, strategy);
    }

    @Override // com.byd.dilink.strategyservice.IDiLinkStrategyManager
    public void unregisterListener(final Context context, final String str, final List<String> list, IDiLinkStrategyListener iDiLinkStrategyListener) {
        this.f2791a.removeCallbackIgnoreRemoteException(iDiLinkStrategyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.strategyservice.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2796a.e(context, str, list, (IStrategyListener) obj);
            }
        });
    }
}
