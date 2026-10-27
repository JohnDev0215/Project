package androidx.compose.ui.node;

import C0.U;
import D0.c;
import E0.F;
import E0.H;
import E0.k0;
import F0.InterfaceC0201f;
import F0.InterfaceC0222p0;
import F0.R0;
import F0.S0;
import F0.W0;
import F0.d1;
import S0.InterfaceC0701m;
import S0.InterfaceC0702n;
import T0.z;
import a1.b;
import a1.k;
import h0.InterfaceC1340b;
import h0.f;
import j0.InterfaceC1533a;
import kotlin.Metadata;
import l0.InterfaceC1716g;
import n0.InterfaceC1897z;
import q7.InterfaceC2194h;
import u0.InterfaceC2487a;
import v0.InterfaceC2532b;
import y0.InterfaceC2736n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001:\u0001\u0002ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0003À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/Owner;", "", "E0/F", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Owner {
    InterfaceC0201f getAccessibilityManager();

    InterfaceC1340b getAutofill();

    f getAutofillTree();

    InterfaceC0222p0 getClipboardManager();

    InterfaceC2194h getCoroutineContext();

    b getDensity();

    InterfaceC1533a getDragAndDropManager();

    InterfaceC1716g getFocusOwner();

    InterfaceC0702n getFontFamilyResolver();

    InterfaceC0701m getFontLoader();

    InterfaceC1897z getGraphicsContext();

    InterfaceC2487a getHapticFeedBack();

    InterfaceC2532b getInputModeManager();

    k getLayoutDirection();

    c getModifierLocalManager();

    U getPlacementScope();

    InterfaceC2736n getPointerIconService();

    F getRoot();

    H getSharedDrawScope();

    boolean getShowLayoutBounds();

    k0 getSnapshotObserver();

    R0 getSoftwareKeyboardController();

    z getTextInputService();

    S0 getTextToolbar();

    W0 getViewConfiguration();

    d1 getWindowInfo();

    void setShowLayoutBounds(boolean z6);
}
