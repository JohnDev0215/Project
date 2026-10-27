package com.neusoft.na.byd.presentation;

import android.util.Log;
import com.byd.bydnaviapi.AbsBYDAutoNaviListener;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import kotlin.Metadata;

/* JADX INFO: compiled from: MapPresentationView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\bH\u0016¨\u0006\r"}, d2 = {"com/neusoft/na/byd/presentation/MapPresentationView$bydAutoNaviListener$1", "Lcom/byd/bydnaviapi/AbsBYDAutoNaviListener;", "isConnected", "", "isBinding", "", "onINSNavDisplayMode", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "", "onInsUiChanged", "uiState", "onMapFocusChanged", "mapType", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MapPresentationView$bydAutoNaviListener$1 extends AbsBYDAutoNaviListener {
    final /* synthetic */ MapPresentationView this$0;

    MapPresentationView$bydAutoNaviListener$1(MapPresentationView mapPresentationView) {
        this.this$0 = mapPresentationView;
    }

    @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
    public void onMapFocusChanged(int mapType) {
        Log.i("MapPresentationView", "[onMapFocusChanged] --> mapType == " + mapType);
        super.onMapFocusChanged(mapType);
        MapPresentationView mapPresentationView = this.this$0;
        if (mapType == 0) {
            mapType = 1;
        }
        mapPresentationView.mapType = mapType;
        if (this.this$0.hasMapFocus()) {
            this.this$0.startDisplayConfig();
            return;
        }
        this.this$0.mapFocusChanged2Navigation = true;
        if (this.this$0.localDisplayMode == 2) {
            this.this$0.startPresentationClose(1);
        }
    }

    @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
    public void onINSNavDisplayMode(int value) {
        Log.i("MapPresentationView", "[onINSNavDisplayMode] --> localDisplayMode == " + this.this$0.localDisplayMode + ", value == " + value);
        super.onINSNavDisplayMode(value);
        if (this.this$0.hasMapFocus()) {
            this.this$0.switchDisplayMode(value);
        }
    }

    @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
    public void onInsUiChanged(int uiState) {
        Log.i("MapPresentationView", "[onInsUiChanged] --> uiState == " + uiState);
        super.onInsUiChanged(uiState);
        this.this$0.cacheUiState = uiState;
        if (!this.this$0.hasMapFocus()) {
            if (uiState == 1 || uiState == 3) {
                Log.i("MapPresentationView", "other navigation app has mapType and it is visible, so neusoft navigation close presentation.");
                this.this$0.startPresentationClose(1);
                return;
            }
            return;
        }
        if ((this.this$0.localDisplayMode == 2 && uiState == 6) || (this.this$0.localDisplayMode == 1 && uiState == 7)) {
            Log.i("MapPresentationView", "close display");
            this.this$0.startPresentationClose(uiState == 6 ? 2 : 1);
        }
    }

    @Override // android.hardware.IBYDAutoListener
    public void isConnected(boolean isBinding) {
        super.isConnected(isBinding);
        Log.i("MapPresentationView", "bydAutoNaviListener --> isConnected == " + isBinding);
    }
}
