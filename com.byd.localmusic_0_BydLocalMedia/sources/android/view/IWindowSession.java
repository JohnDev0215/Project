package android.view;

import android.content.ClipData;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.MergedConfiguration;
import android.view.DisplayCutout;
import android.view.IWindow;
import android.view.IWindowId;
import android.view.SurfaceControl;
import android.view.WindowManager;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IWindowSession extends IInterface {

    public static class Default implements IWindowSession {
        @Override // android.view.IWindowSession
        public int addToDisplay(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, Rect rect, Rect rect2, Rect rect3, DisplayCutout.ParcelableWrapper parcelableWrapper, InputChannel inputChannel, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowSession
        public int addToDisplayAsUser(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, int i4, Rect rect, Rect rect2, Rect rect3, DisplayCutout.ParcelableWrapper parcelableWrapper, InputChannel inputChannel, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowSession
        public int addToDisplayWithoutInputChannel(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, Rect rect, Rect rect2, InsetsState insetsState) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IWindowSession
        public void cancelDragAndDrop(IBinder iBinder, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void dragRecipientEntered(IWindow iWindow) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void dragRecipientExited(IWindow iWindow) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void finishDrawing(IWindow iWindow, SurfaceControl.Transaction transaction) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void finishMovingTask(IWindow iWindow) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void getDisplayFrame(IWindow iWindow, Rect rect) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public boolean getInTouchMode() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowSession
        public IWindowId getWindowId(IBinder iBinder) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowSession
        public void grantInputChannel(int i, SurfaceControl surfaceControl, IWindow iWindow, IBinder iBinder, int i2, int i3, InputChannel inputChannel) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void insetsModified(IWindow iWindow, InsetsState insetsState) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void onRectangleOnScreenRequested(IBinder iBinder, Rect rect) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public boolean outOfMemory(IWindow iWindow) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowSession
        public IBinder performDrag(IWindow iWindow, int i, SurfaceControl surfaceControl, int i2, float f, float f2, float f3, float f4, ClipData clipData) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowSession
        public boolean performHapticFeedback(int i, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowSession
        public void pokeDrawLock(IBinder iBinder) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void prepareToReplaceWindows(IBinder iBinder, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public int relayout(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, int i4, int i5, long j, Rect rect, Rect rect2, Rect rect3, Rect rect4, Rect rect5, DisplayCutout.ParcelableWrapper parcelableWrapper, MergedConfiguration mergedConfiguration, SurfaceControl surfaceControl, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr, Point point, SurfaceControl surfaceControl2) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowSession
        public void remove(IWindow iWindow) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void reparentDisplayContent(IWindow iWindow, SurfaceControl surfaceControl, int i) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void reportDropResult(IWindow iWindow, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void reportSystemGestureExclusionChanged(IWindow iWindow, List<Rect> list) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public Bundle sendWallpaperCommand(IBinder iBinder, String str, int i, int i2, int i3, Bundle bundle, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowSession
        public void setInTouchMode(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setInsets(IWindow iWindow, int i, Rect rect, Rect rect2, Region region) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setShouldZoomOutWallpaper(IBinder iBinder, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setTransparentRegion(IWindow iWindow, Region region) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setWallpaperDisplayOffset(IBinder iBinder, int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setWallpaperPosition(IBinder iBinder, float f, float f2, float f3, float f4) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void setWallpaperZoomOut(IBinder iBinder, float f) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public boolean startMovingTask(IWindow iWindow, float f, float f2) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowSession
        public void updateDisplayContentLocation(IWindow iWindow, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void updateInputChannel(IBinder iBinder, int i, SurfaceControl surfaceControl, int i2, Region region) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void updatePointerIcon(IWindow iWindow) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void updateTapExcludeRegion(IWindow iWindow, Region region) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void wallpaperCommandComplete(IBinder iBinder, Bundle bundle) throws RemoteException {
        }

        @Override // android.view.IWindowSession
        public void wallpaperOffsetsComplete(IBinder iBinder) throws RemoteException {
        }
    }

    int addToDisplay(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, Rect rect, Rect rect2, Rect rect3, DisplayCutout.ParcelableWrapper parcelableWrapper, InputChannel inputChannel, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException;

    int addToDisplayAsUser(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, int i4, Rect rect, Rect rect2, Rect rect3, DisplayCutout.ParcelableWrapper parcelableWrapper, InputChannel inputChannel, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException;

    int addToDisplayWithoutInputChannel(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, Rect rect, Rect rect2, InsetsState insetsState) throws RemoteException;

    void cancelDragAndDrop(IBinder iBinder, boolean z) throws RemoteException;

    void dragRecipientEntered(IWindow iWindow) throws RemoteException;

    void dragRecipientExited(IWindow iWindow) throws RemoteException;

    void finishDrawing(IWindow iWindow, SurfaceControl.Transaction transaction) throws RemoteException;

    void finishMovingTask(IWindow iWindow) throws RemoteException;

    void getDisplayFrame(IWindow iWindow, Rect rect) throws RemoteException;

    boolean getInTouchMode() throws RemoteException;

    IWindowId getWindowId(IBinder iBinder) throws RemoteException;

    void grantInputChannel(int i, SurfaceControl surfaceControl, IWindow iWindow, IBinder iBinder, int i2, int i3, InputChannel inputChannel) throws RemoteException;

    void insetsModified(IWindow iWindow, InsetsState insetsState) throws RemoteException;

    void onRectangleOnScreenRequested(IBinder iBinder, Rect rect) throws RemoteException;

    boolean outOfMemory(IWindow iWindow) throws RemoteException;

    IBinder performDrag(IWindow iWindow, int i, SurfaceControl surfaceControl, int i2, float f, float f2, float f3, float f4, ClipData clipData) throws RemoteException;

    boolean performHapticFeedback(int i, boolean z) throws RemoteException;

    void pokeDrawLock(IBinder iBinder) throws RemoteException;

    void prepareToReplaceWindows(IBinder iBinder, boolean z) throws RemoteException;

    int relayout(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, int i4, int i5, long j, Rect rect, Rect rect2, Rect rect3, Rect rect4, Rect rect5, DisplayCutout.ParcelableWrapper parcelableWrapper, MergedConfiguration mergedConfiguration, SurfaceControl surfaceControl, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr, Point point, SurfaceControl surfaceControl2) throws RemoteException;

    void remove(IWindow iWindow) throws RemoteException;

    void reparentDisplayContent(IWindow iWindow, SurfaceControl surfaceControl, int i) throws RemoteException;

    void reportDropResult(IWindow iWindow, boolean z) throws RemoteException;

    void reportSystemGestureExclusionChanged(IWindow iWindow, List<Rect> list) throws RemoteException;

    Bundle sendWallpaperCommand(IBinder iBinder, String str, int i, int i2, int i3, Bundle bundle, boolean z) throws RemoteException;

    void setInTouchMode(boolean z) throws RemoteException;

    void setInsets(IWindow iWindow, int i, Rect rect, Rect rect2, Region region) throws RemoteException;

    void setShouldZoomOutWallpaper(IBinder iBinder, boolean z) throws RemoteException;

    void setTransparentRegion(IWindow iWindow, Region region) throws RemoteException;

    void setWallpaperDisplayOffset(IBinder iBinder, int i, int i2) throws RemoteException;

    void setWallpaperPosition(IBinder iBinder, float f, float f2, float f3, float f4) throws RemoteException;

    void setWallpaperZoomOut(IBinder iBinder, float f) throws RemoteException;

    boolean startMovingTask(IWindow iWindow, float f, float f2) throws RemoteException;

    void updateDisplayContentLocation(IWindow iWindow, int i, int i2, int i3) throws RemoteException;

    void updateInputChannel(IBinder iBinder, int i, SurfaceControl surfaceControl, int i2, Region region) throws RemoteException;

    void updatePointerIcon(IWindow iWindow) throws RemoteException;

    void updateTapExcludeRegion(IWindow iWindow, Region region) throws RemoteException;

    void wallpaperCommandComplete(IBinder iBinder, Bundle bundle) throws RemoteException;

    void wallpaperOffsetsComplete(IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements IWindowSession {
        private static final String DESCRIPTOR = "android.view.IWindowSession";
        static final int TRANSACTION_addToDisplay = 1;
        static final int TRANSACTION_addToDisplayAsUser = 2;
        static final int TRANSACTION_addToDisplayWithoutInputChannel = 3;
        static final int TRANSACTION_cancelDragAndDrop = 17;
        static final int TRANSACTION_dragRecipientEntered = 18;
        static final int TRANSACTION_dragRecipientExited = 19;
        static final int TRANSACTION_finishDrawing = 11;
        static final int TRANSACTION_finishMovingTask = 31;
        static final int TRANSACTION_getDisplayFrame = 10;
        static final int TRANSACTION_getInTouchMode = 13;
        static final int TRANSACTION_getWindowId = 28;
        static final int TRANSACTION_grantInputChannel = 38;
        static final int TRANSACTION_insetsModified = 36;
        static final int TRANSACTION_onRectangleOnScreenRequested = 27;
        static final int TRANSACTION_outOfMemory = 7;
        static final int TRANSACTION_performDrag = 15;
        static final int TRANSACTION_performHapticFeedback = 14;
        static final int TRANSACTION_pokeDrawLock = 29;
        static final int TRANSACTION_prepareToReplaceWindows = 6;
        static final int TRANSACTION_relayout = 5;
        static final int TRANSACTION_remove = 4;
        static final int TRANSACTION_reparentDisplayContent = 33;
        static final int TRANSACTION_reportDropResult = 16;
        static final int TRANSACTION_reportSystemGestureExclusionChanged = 37;
        static final int TRANSACTION_sendWallpaperCommand = 25;
        static final int TRANSACTION_setInTouchMode = 12;
        static final int TRANSACTION_setInsets = 9;
        static final int TRANSACTION_setShouldZoomOutWallpaper = 22;
        static final int TRANSACTION_setTransparentRegion = 8;
        static final int TRANSACTION_setWallpaperDisplayOffset = 24;
        static final int TRANSACTION_setWallpaperPosition = 20;
        static final int TRANSACTION_setWallpaperZoomOut = 21;
        static final int TRANSACTION_startMovingTask = 30;
        static final int TRANSACTION_updateDisplayContentLocation = 34;
        static final int TRANSACTION_updateInputChannel = 39;
        static final int TRANSACTION_updatePointerIcon = 32;
        static final int TRANSACTION_updateTapExcludeRegion = 35;
        static final int TRANSACTION_wallpaperCommandComplete = 26;
        static final int TRANSACTION_wallpaperOffsetsComplete = 23;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "addToDisplay";
                case 2:
                    return "addToDisplayAsUser";
                case 3:
                    return "addToDisplayWithoutInputChannel";
                case 4:
                    return "remove";
                case 5:
                    return "relayout";
                case 6:
                    return "prepareToReplaceWindows";
                case 7:
                    return "outOfMemory";
                case 8:
                    return "setTransparentRegion";
                case 9:
                    return "setInsets";
                case 10:
                    return "getDisplayFrame";
                case 11:
                    return "finishDrawing";
                case 12:
                    return "setInTouchMode";
                case 13:
                    return "getInTouchMode";
                case 14:
                    return "performHapticFeedback";
                case 15:
                    return "performDrag";
                case 16:
                    return "reportDropResult";
                case 17:
                    return "cancelDragAndDrop";
                case 18:
                    return "dragRecipientEntered";
                case 19:
                    return "dragRecipientExited";
                case 20:
                    return "setWallpaperPosition";
                case 21:
                    return "setWallpaperZoomOut";
                case 22:
                    return "setShouldZoomOutWallpaper";
                case 23:
                    return "wallpaperOffsetsComplete";
                case 24:
                    return "setWallpaperDisplayOffset";
                case 25:
                    return "sendWallpaperCommand";
                case 26:
                    return "wallpaperCommandComplete";
                case 27:
                    return "onRectangleOnScreenRequested";
                case 28:
                    return "getWindowId";
                case 29:
                    return "pokeDrawLock";
                case 30:
                    return "startMovingTask";
                case 31:
                    return "finishMovingTask";
                case 32:
                    return "updatePointerIcon";
                case 33:
                    return "reparentDisplayContent";
                case 34:
                    return "updateDisplayContentLocation";
                case 35:
                    return "updateTapExcludeRegion";
                case 36:
                    return "insetsModified";
                case 37:
                    return "reportSystemGestureExclusionChanged";
                case 38:
                    return "grantInputChannel";
                case 39:
                    return "updateInputChannel";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWindowSession)) {
                return (IWindowSession) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    IWindow iWindowAsInterface = IWindow.Stub.asInterface(parcel.readStrongBinder());
                    int i3 = parcel.readInt();
                    WindowManager.LayoutParams layoutParamsCreateFromParcel = parcel.readInt() != 0 ? WindowManager.LayoutParams.CREATOR.createFromParcel(parcel) : null;
                    int i4 = parcel.readInt();
                    int i5 = parcel.readInt();
                    Rect rect = new Rect();
                    Rect rect2 = new Rect();
                    Rect rect3 = new Rect();
                    DisplayCutout.ParcelableWrapper parcelableWrapper = new DisplayCutout.ParcelableWrapper();
                    InputChannel inputChannel = new InputChannel();
                    InsetsState insetsState = new InsetsState();
                    int i6 = parcel.readInt();
                    InsetsSourceControl[] insetsSourceControlArr = i6 >= 0 ? new InsetsSourceControl[i6] : null;
                    int iAddToDisplay = addToDisplay(iWindowAsInterface, i3, layoutParamsCreateFromParcel, i4, i5, rect, rect2, rect3, parcelableWrapper, inputChannel, insetsState, insetsSourceControlArr);
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddToDisplay);
                    parcel2.writeInt(1);
                    rect.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect2.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect3.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    parcelableWrapper.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    inputChannel.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    insetsState.writeToParcel(parcel2, 1);
                    parcel2.writeTypedArray(insetsSourceControlArr, 1);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IWindow iWindowAsInterface2 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                    int i7 = parcel.readInt();
                    WindowManager.LayoutParams layoutParamsCreateFromParcel2 = parcel.readInt() != 0 ? WindowManager.LayoutParams.CREATOR.createFromParcel(parcel) : null;
                    int i8 = parcel.readInt();
                    int i9 = parcel.readInt();
                    int i10 = parcel.readInt();
                    Rect rect4 = new Rect();
                    Rect rect5 = new Rect();
                    Rect rect6 = new Rect();
                    DisplayCutout.ParcelableWrapper parcelableWrapper2 = new DisplayCutout.ParcelableWrapper();
                    InputChannel inputChannel2 = new InputChannel();
                    InsetsState insetsState2 = new InsetsState();
                    int i11 = parcel.readInt();
                    InsetsSourceControl[] insetsSourceControlArr2 = i11 >= 0 ? new InsetsSourceControl[i11] : null;
                    int iAddToDisplayAsUser = addToDisplayAsUser(iWindowAsInterface2, i7, layoutParamsCreateFromParcel2, i8, i9, i10, rect4, rect5, rect6, parcelableWrapper2, inputChannel2, insetsState2, insetsSourceControlArr2);
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddToDisplayAsUser);
                    parcel2.writeInt(1);
                    rect4.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect5.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect6.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    parcelableWrapper2.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    inputChannel2.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    insetsState2.writeToParcel(parcel2, 1);
                    parcel2.writeTypedArray(insetsSourceControlArr2, 1);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    IWindow iWindowAsInterface3 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                    int i12 = parcel.readInt();
                    WindowManager.LayoutParams layoutParamsCreateFromParcel3 = parcel.readInt() != 0 ? WindowManager.LayoutParams.CREATOR.createFromParcel(parcel) : null;
                    int i13 = parcel.readInt();
                    int i14 = parcel.readInt();
                    Rect rect7 = new Rect();
                    Rect rect8 = new Rect();
                    InsetsState insetsState3 = new InsetsState();
                    int iAddToDisplayWithoutInputChannel = addToDisplayWithoutInputChannel(iWindowAsInterface3, i12, layoutParamsCreateFromParcel3, i13, i14, rect7, rect8, insetsState3);
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddToDisplayWithoutInputChannel);
                    parcel2.writeInt(1);
                    rect7.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect8.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    insetsState3.writeToParcel(parcel2, 1);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    remove(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    IWindow iWindowAsInterface4 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                    int i15 = parcel.readInt();
                    WindowManager.LayoutParams layoutParamsCreateFromParcel4 = parcel.readInt() != 0 ? WindowManager.LayoutParams.CREATOR.createFromParcel(parcel) : null;
                    int i16 = parcel.readInt();
                    int i17 = parcel.readInt();
                    int i18 = parcel.readInt();
                    int i19 = parcel.readInt();
                    long j = parcel.readLong();
                    Rect rect9 = new Rect();
                    Rect rect10 = new Rect();
                    Rect rect11 = new Rect();
                    Rect rect12 = new Rect();
                    Rect rect13 = new Rect();
                    DisplayCutout.ParcelableWrapper parcelableWrapper3 = new DisplayCutout.ParcelableWrapper();
                    MergedConfiguration mergedConfiguration = new MergedConfiguration();
                    SurfaceControl surfaceControl = new SurfaceControl();
                    InsetsState insetsState4 = new InsetsState();
                    int i20 = parcel.readInt();
                    InsetsSourceControl[] insetsSourceControlArr3 = i20 >= 0 ? new InsetsSourceControl[i20] : null;
                    Point point = new Point();
                    SurfaceControl surfaceControl2 = new SurfaceControl();
                    InsetsSourceControl[] insetsSourceControlArr4 = insetsSourceControlArr3;
                    int iRelayout = relayout(iWindowAsInterface4, i15, layoutParamsCreateFromParcel4, i16, i17, i18, i19, j, rect9, rect10, rect11, rect12, rect13, parcelableWrapper3, mergedConfiguration, surfaceControl, insetsState4, insetsSourceControlArr4, point, surfaceControl2);
                    parcel2.writeNoException();
                    parcel2.writeInt(iRelayout);
                    parcel2.writeInt(1);
                    rect9.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect10.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect11.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect12.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect13.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    parcelableWrapper3.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    mergedConfiguration.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    surfaceControl.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    insetsState4.writeToParcel(parcel2, 1);
                    parcel2.writeTypedArray(insetsSourceControlArr4, 1);
                    parcel2.writeInt(1);
                    point.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    surfaceControl2.writeToParcel(parcel2, 1);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    prepareToReplaceWindows(parcel.readStrongBinder(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOutOfMemory = outOfMemory(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zOutOfMemory ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTransparentRegion(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Region.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInsets(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Region.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    IWindow iWindowAsInterface5 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                    Rect rect14 = new Rect();
                    getDisplayFrame(iWindowAsInterface5, rect14);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    rect14.writeToParcel(parcel2, 1);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishDrawing(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? SurfaceControl.Transaction.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInTouchMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean inTouchMode = getInTouchMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(inTouchMode ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPerformHapticFeedback = performHapticFeedback(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zPerformHapticFeedback ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder iBinderPerformDrag = performDrag(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? SurfaceControl.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readInt() != 0 ? ClipData.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iBinderPerformDrag);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportDropResult(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelDragAndDrop(parcel.readStrongBinder(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    dragRecipientEntered(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    dragRecipientExited(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWallpaperPosition(parcel.readStrongBinder(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWallpaperZoomOut(parcel.readStrongBinder(), parcel.readFloat());
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    setShouldZoomOutWallpaper(parcel.readStrongBinder(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    wallpaperOffsetsComplete(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWallpaperDisplayOffset(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle bundleSendWallpaperCommand = sendWallpaperCommand(parcel.readStrongBinder(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (bundleSendWallpaperCommand != null) {
                        parcel2.writeInt(1);
                        bundleSendWallpaperCommand.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    wallpaperCommandComplete(parcel.readStrongBinder(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRectangleOnScreenRequested(parcel.readStrongBinder(), parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    IWindowId windowId = getWindowId(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(windowId != null ? windowId.asBinder() : null);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    pokeDrawLock(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartMovingTask = startMovingTask(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartMovingTask ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishMovingTask(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePointerIcon(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    reparentDisplayContent(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? SurfaceControl.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateDisplayContentLocation(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateTapExcludeRegion(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Region.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    insetsModified(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? InsetsState.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportSystemGestureExclusionChanged(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.createTypedArrayList(Rect.CREATOR));
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    int i21 = parcel.readInt();
                    SurfaceControl surfaceControlCreateFromParcel = parcel.readInt() != 0 ? SurfaceControl.CREATOR.createFromParcel(parcel) : null;
                    IWindow iWindowAsInterface6 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder = parcel.readStrongBinder();
                    int i22 = parcel.readInt();
                    int i23 = parcel.readInt();
                    InputChannel inputChannel3 = new InputChannel();
                    grantInputChannel(i21, surfaceControlCreateFromParcel, iWindowAsInterface6, strongBinder, i22, i23, inputChannel3);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    inputChannel3.writeToParcel(parcel2, 1);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateInputChannel(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt() != 0 ? SurfaceControl.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? Region.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IWindowSession {
            public static IWindowSession sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.view.IWindowSession
            public int addToDisplay(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, Rect rect, Rect rect2, Rect rect3, DisplayCutout.ParcelableWrapper parcelableWrapper, InputChannel inputChannel, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (layoutParams != null) {
                        parcelObtain.writeInt(1);
                        layoutParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (insetsSourceControlArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(insetsSourceControlArr.length);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addToDisplay(iWindow, i, layoutParams, i2, i3, rect, rect2, rect3, parcelableWrapper, inputChannel, insetsState, insetsSourceControlArr);
                    }
                    parcelObtain2.readException();
                    int i4 = parcelObtain2.readInt();
                    if (parcelObtain2.readInt() != 0) {
                        rect.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        rect2.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        rect3.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        parcelableWrapper.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        inputChannel.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        insetsState.readFromParcel(parcelObtain2);
                    }
                    parcelObtain2.readTypedArray(insetsSourceControlArr, InsetsSourceControl.CREATOR);
                    return i4;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.IWindowSession
            public int addToDisplayAsUser(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, int i4, Rect rect, Rect rect2, Rect rect3, DisplayCutout.ParcelableWrapper parcelableWrapper, InputChannel inputChannel, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws Throwable {
                Parcel parcel;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (layoutParams != null) {
                        parcelObtain.writeInt(1);
                        layoutParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (insetsSourceControlArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(insetsSourceControlArr.length);
                    }
                    try {
                        if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            int iAddToDisplayAsUser = Stub.getDefaultImpl().addToDisplayAsUser(iWindow, i, layoutParams, i2, i3, i4, rect, rect2, rect3, parcelableWrapper, inputChannel, insetsState, insetsSourceControlArr);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                            return iAddToDisplayAsUser;
                        }
                        parcelObtain2.readException();
                        int i5 = parcelObtain2.readInt();
                        if (parcelObtain2.readInt() != 0) {
                            parcel = parcelObtain2;
                            try {
                                rect.readFromParcel(parcel);
                                parcel = parcel;
                            } catch (Throwable th) {
                                th = th;
                            }
                        } else {
                            parcel = parcelObtain2;
                        }
                        if (parcel.readInt() != 0) {
                            rect2.readFromParcel(parcel);
                        }
                        if (parcel.readInt() != 0) {
                            rect3.readFromParcel(parcel);
                        }
                        if (parcel.readInt() != 0) {
                            parcelableWrapper.readFromParcel(parcel);
                        }
                        if (parcel.readInt() != 0) {
                            inputChannel.readFromParcel(parcel);
                        }
                        if (parcel.readInt() != 0) {
                            insetsState.readFromParcel(parcel);
                        }
                        parcel.readTypedArray(insetsSourceControlArr, InsetsSourceControl.CREATOR);
                        parcel.recycle();
                        parcelObtain.recycle();
                        return i5;
                    } catch (Throwable th2) {
                        th = th2;
                        parcel = iWindow;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    parcel = parcelObtain2;
                }
                parcel.recycle();
                parcelObtain.recycle();
                throw th;
            }

            @Override // android.view.IWindowSession
            public int addToDisplayWithoutInputChannel(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, Rect rect, Rect rect2, InsetsState insetsState) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (layoutParams != null) {
                        parcelObtain.writeInt(1);
                        layoutParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        int iAddToDisplayWithoutInputChannel = Stub.getDefaultImpl().addToDisplayWithoutInputChannel(iWindow, i, layoutParams, i2, i3, rect, rect2, insetsState);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return iAddToDisplayWithoutInputChannel;
                    }
                    parcelObtain2.readException();
                    int i4 = parcelObtain2.readInt();
                    if (parcelObtain2.readInt() != 0) {
                        rect.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        rect2.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        insetsState.readFromParcel(parcelObtain2);
                    }
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return i4;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.view.IWindowSession
            public void remove(IWindow iWindow) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().remove(iWindow);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:29:0x00b8  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00c0  */
            /* JADX WARN: Removed duplicated region for block: B:34:0x00c8 A[Catch: all -> 0x013c, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            /* JADX WARN: Removed duplicated region for block: B:37:0x00d3 A[Catch: all -> 0x013c, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            /* JADX WARN: Removed duplicated region for block: B:40:0x00de A[Catch: all -> 0x013c, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            /* JADX WARN: Removed duplicated region for block: B:43:0x00e9 A[Catch: all -> 0x013c, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            /* JADX WARN: Removed duplicated region for block: B:46:0x00f4 A[Catch: all -> 0x013c, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            /* JADX WARN: Removed duplicated region for block: B:49:0x00ff A[Catch: all -> 0x013c, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            /* JADX WARN: Removed duplicated region for block: B:52:0x010a A[Catch: all -> 0x013c, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            /* JADX WARN: Removed duplicated region for block: B:55:0x0115 A[Catch: all -> 0x013c, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            /* JADX WARN: Removed duplicated region for block: B:58:0x0125 A[Catch: all -> 0x013c, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            /* JADX WARN: Removed duplicated region for block: B:61:0x0130 A[Catch: all -> 0x013c, TRY_LEAVE, TryCatch #2 {all -> 0x013c, blocks: (B:30:0x00bc, B:32:0x00c2, B:34:0x00c8, B:35:0x00cd, B:37:0x00d3, B:38:0x00d8, B:40:0x00de, B:41:0x00e3, B:43:0x00e9, B:44:0x00ee, B:46:0x00f4, B:47:0x00f9, B:49:0x00ff, B:50:0x0104, B:52:0x010a, B:53:0x010f, B:55:0x0115, B:56:0x011a, B:58:0x0125, B:59:0x012a, B:61:0x0130), top: B:76:0x00bc }] */
            @Override // android.view.IWindowSession
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public int relayout(android.view.IWindow r25, int r26, android.view.WindowManager.LayoutParams r27, int r28, int r29, int r30, int r31, long r32, android.graphics.Rect r34, android.graphics.Rect r35, android.graphics.Rect r36, android.graphics.Rect r37, android.graphics.Rect r38, android.view.DisplayCutout.ParcelableWrapper r39, android.util.MergedConfiguration r40, android.view.SurfaceControl r41, android.view.InsetsState r42, android.view.InsetsSourceControl[] r43, android.graphics.Point r44, android.view.SurfaceControl r45) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 333
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: android.view.IWindowSession.Stub.Proxy.relayout(android.view.IWindow, int, android.view.WindowManager$LayoutParams, int, int, int, int, long, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect, android.view.DisplayCutout$ParcelableWrapper, android.util.MergedConfiguration, android.view.SurfaceControl, android.view.InsetsState, android.view.InsetsSourceControl[], android.graphics.Point, android.view.SurfaceControl):int");
            }

            @Override // android.view.IWindowSession
            public void prepareToReplaceWindows(IBinder iBinder, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().prepareToReplaceWindows(iBinder, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public boolean outOfMemory(IWindow iWindow) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().outOfMemory(iWindow);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setTransparentRegion(IWindow iWindow, Region region) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (region != null) {
                        parcelObtain.writeInt(1);
                        region.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTransparentRegion(iWindow, region);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setInsets(IWindow iWindow, int i, Rect rect, Rect rect2, Region region) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rect2 != null) {
                        parcelObtain.writeInt(1);
                        rect2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (region != null) {
                        parcelObtain.writeInt(1);
                        region.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInsets(iWindow, i, rect, rect2, region);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void getDisplayFrame(IWindow iWindow, Rect rect) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDisplayFrame(iWindow, rect);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        rect.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void finishDrawing(IWindow iWindow, SurfaceControl.Transaction transaction) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (transaction != null) {
                        parcelObtain.writeInt(1);
                        transaction.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishDrawing(iWindow, transaction);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setInTouchMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInTouchMode(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public boolean getInTouchMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInTouchMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public boolean performHapticFeedback(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().performHapticFeedback(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public IBinder performDrag(IWindow iWindow, int i, SurfaceControl surfaceControl, int i2, float f, float f2, float f3, float f4, ClipData clipData) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (surfaceControl != null) {
                        parcelObtain.writeInt(1);
                        surfaceControl.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    parcelObtain.writeFloat(f3);
                    parcelObtain.writeFloat(f4);
                    if (clipData != null) {
                        parcelObtain.writeInt(1);
                        clipData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        IBinder iBinderPerformDrag = Stub.getDefaultImpl().performDrag(iWindow, i, surfaceControl, i2, f, f2, f3, f4, clipData);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return iBinderPerformDrag;
                    }
                    parcelObtain2.readException();
                    IBinder strongBinder = parcelObtain2.readStrongBinder();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return strongBinder;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.view.IWindowSession
            public void reportDropResult(IWindow iWindow, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportDropResult(iWindow, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void cancelDragAndDrop(IBinder iBinder, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelDragAndDrop(iBinder, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void dragRecipientEntered(IWindow iWindow) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dragRecipientEntered(iWindow);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void dragRecipientExited(IWindow iWindow) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dragRecipientExited(iWindow);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setWallpaperPosition(IBinder iBinder, float f, float f2, float f3, float f4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    parcelObtain.writeFloat(f3);
                    parcelObtain.writeFloat(f4);
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setWallpaperPosition(iBinder, f, f2, f3, f4);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setWallpaperZoomOut(IBinder iBinder, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setWallpaperZoomOut(iBinder, f);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setShouldZoomOutWallpaper(IBinder iBinder, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShouldZoomOutWallpaper(iBinder, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void wallpaperOffsetsComplete(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().wallpaperOffsetsComplete(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void setWallpaperDisplayOffset(IBinder iBinder, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWallpaperDisplayOffset(iBinder, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public Bundle sendWallpaperCommand(IBinder iBinder, String str, int i, int i2, int i3, Bundle bundle, boolean z) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    int i4 = 1;
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i4 = 0;
                    }
                    parcelObtain.writeInt(i4);
                    try {
                        if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Bundle bundleSendWallpaperCommand = Stub.getDefaultImpl().sendWallpaperCommand(iBinder, str, i, i2, i3, bundle, z);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                            return bundleSendWallpaperCommand;
                        }
                        parcelObtain2.readException();
                        Bundle bundleCreateFromParcel = parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return bundleCreateFromParcel;
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.view.IWindowSession
            public void wallpaperCommandComplete(IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().wallpaperCommandComplete(iBinder, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void onRectangleOnScreenRequested(IBinder iBinder, Rect rect) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRectangleOnScreenRequested(iBinder, rect);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public IWindowId getWindowId(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowId(iBinder);
                    }
                    parcelObtain2.readException();
                    return IWindowId.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void pokeDrawLock(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pokeDrawLock(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public boolean startMovingTask(IWindow iWindow, float f, float f2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startMovingTask(iWindow, f, f2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void finishMovingTask(IWindow iWindow) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishMovingTask(iWindow);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void updatePointerIcon(IWindow iWindow) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePointerIcon(iWindow);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void reparentDisplayContent(IWindow iWindow, SurfaceControl surfaceControl, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (surfaceControl != null) {
                        parcelObtain.writeInt(1);
                        surfaceControl.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reparentDisplayContent(iWindow, surfaceControl, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void updateDisplayContentLocation(IWindow iWindow, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateDisplayContentLocation(iWindow, i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void updateTapExcludeRegion(IWindow iWindow, Region region) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (region != null) {
                        parcelObtain.writeInt(1);
                        region.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateTapExcludeRegion(iWindow, region);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void insetsModified(IWindow iWindow, InsetsState insetsState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (insetsState != null) {
                        parcelObtain.writeInt(1);
                        insetsState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(36, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().insetsModified(iWindow, insetsState);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void reportSystemGestureExclusionChanged(IWindow iWindow, List<Rect> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(37, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().reportSystemGestureExclusionChanged(iWindow, list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowSession
            public void grantInputChannel(int i, SurfaceControl surfaceControl, IWindow iWindow, IBinder iBinder, int i2, int i3, InputChannel inputChannel) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (surfaceControl != null) {
                        parcelObtain.writeInt(1);
                        surfaceControl.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantInputChannel(i, surfaceControl, iWindow, iBinder, i2, i3, inputChannel);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        if (parcelObtain2.readInt() != 0) {
                            inputChannel.readFromParcel(parcelObtain2);
                        }
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.view.IWindowSession
            public void updateInputChannel(IBinder iBinder, int i, SurfaceControl surfaceControl, int i2, Region region) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (surfaceControl != null) {
                        parcelObtain.writeInt(1);
                        surfaceControl.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (region != null) {
                        parcelObtain.writeInt(1);
                        region.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateInputChannel(iBinder, i, surfaceControl, i2, region);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowSession iWindowSession) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWindowSession == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWindowSession;
            return true;
        }

        public static IWindowSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
