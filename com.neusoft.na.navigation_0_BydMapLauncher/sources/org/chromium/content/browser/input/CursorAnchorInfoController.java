package org.chromium.content.browser.input;

import android.graphics.Matrix;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.chromium.content.browser.RenderCoordinates;

/* JADX INFO: loaded from: classes3.dex */
final class CursorAnchorInfoController {

    @Nullable
    private final ComposingTextDelegate mComposingTextDelegate;

    @Nullable
    private float[] mCompositionCharacterBounds;
    private boolean mHasCoordinateInfo;
    private boolean mHasInsertionMarker;
    private boolean mHasPendingImmediateRequest;

    @Nullable
    private InputMethodManagerWrapper mInputMethodManagerWrapper;
    private float mInsertionMarkerBottom;
    private float mInsertionMarkerHorizontal;
    private float mInsertionMarkerTop;
    private boolean mIsEditable;
    private boolean mIsInsertionMarkerVisible;

    @Nullable
    private CursorAnchorInfo mLastCursorAnchorInfo;
    private boolean mMonitorModeEnabled;
    private float mScale;
    private float mTranslationX;
    private float mTranslationY;

    @Nonnull
    private final ViewDelegate mViewDelegate;

    @Nonnull
    private final Matrix mMatrix = new Matrix();

    @Nonnull
    private final int[] mViewOrigin = new int[2];

    @Nonnull
    private final CursorAnchorInfo.Builder mCursorAnchorInfoBuilder = new CursorAnchorInfo.Builder();

    public interface ComposingTextDelegate {
        int getComposingTextEnd();

        int getComposingTextStart();

        int getSelectionEnd();

        int getSelectionStart();

        CharSequence getText();
    }

    public interface ViewDelegate {
        void getLocationOnScreen(View view, int[] iArr);
    }

    private CursorAnchorInfoController(InputMethodManagerWrapper inputMethodManagerWrapper, ComposingTextDelegate composingTextDelegate, ViewDelegate viewDelegate) {
        this.mInputMethodManagerWrapper = inputMethodManagerWrapper;
        this.mComposingTextDelegate = composingTextDelegate;
        this.mViewDelegate = viewDelegate;
    }

    public static CursorAnchorInfoController create(InputMethodManagerWrapper inputMethodManagerWrapper, ComposingTextDelegate composingTextDelegate) {
        return new CursorAnchorInfoController(inputMethodManagerWrapper, composingTextDelegate, new ViewDelegate() { // from class: org.chromium.content.browser.input.CursorAnchorInfoController.1
            @Override // org.chromium.content.browser.input.CursorAnchorInfoController.ViewDelegate
            public void getLocationOnScreen(View view, int[] iArr) {
                view.getLocationOnScreen(iArr);
            }
        });
    }

    public void setInputMethodManagerWrapperForTest(InputMethodManagerWrapper inputMethodManagerWrapper) {
        this.mInputMethodManagerWrapper = inputMethodManagerWrapper;
    }

    public static CursorAnchorInfoController createForTest(InputMethodManagerWrapper inputMethodManagerWrapper, ComposingTextDelegate composingTextDelegate, ViewDelegate viewDelegate) {
        return new CursorAnchorInfoController(inputMethodManagerWrapper, composingTextDelegate, viewDelegate);
    }

    public void invalidateLastCursorAnchorInfo() {
        if (this.mIsEditable) {
            this.mLastCursorAnchorInfo = null;
        }
    }

    public void setCompositionCharacterBounds(float[] fArr) {
        if (this.mIsEditable && !Arrays.equals(fArr, this.mCompositionCharacterBounds)) {
            this.mLastCursorAnchorInfo = null;
            this.mCompositionCharacterBounds = fArr;
        }
    }

    public void onUpdateFrameInfo(@Nonnull RenderCoordinates renderCoordinates, boolean z, boolean z2, float f, float f2, float f3, @Nonnull View view) {
        if (this.mIsEditable) {
            this.mViewDelegate.getLocationOnScreen(view, this.mViewOrigin);
            float deviceScaleFactor = renderCoordinates.getDeviceScaleFactor();
            float f4 = this.mViewOrigin[0];
            float contentOffsetYPix = r1[1] + renderCoordinates.getContentOffsetYPix();
            if (!this.mHasCoordinateInfo || deviceScaleFactor != this.mScale || f4 != this.mTranslationX || contentOffsetYPix != this.mTranslationY || z != this.mHasInsertionMarker || z2 != this.mIsInsertionMarkerVisible || f != this.mInsertionMarkerHorizontal || f2 != this.mInsertionMarkerTop || f3 != this.mInsertionMarkerBottom) {
                this.mLastCursorAnchorInfo = null;
                this.mHasCoordinateInfo = true;
                this.mScale = deviceScaleFactor;
                this.mTranslationX = f4;
                this.mTranslationY = contentOffsetYPix;
                this.mHasInsertionMarker = z;
                this.mIsInsertionMarkerVisible = z2;
                this.mInsertionMarkerHorizontal = f;
                this.mInsertionMarkerTop = f2;
                this.mInsertionMarkerBottom = f3;
            }
            if (this.mHasPendingImmediateRequest || (this.mMonitorModeEnabled && this.mLastCursorAnchorInfo == null)) {
                updateCursorAnchorInfo(view);
            }
        }
    }

    public void resetMonitoringState() {
        this.mMonitorModeEnabled = false;
        this.mHasPendingImmediateRequest = false;
    }

    public void focusedNodeChanged(boolean z) {
        this.mIsEditable = z;
        this.mCompositionCharacterBounds = null;
        this.mHasCoordinateInfo = false;
        this.mLastCursorAnchorInfo = null;
    }

    public boolean onRequestCursorUpdates(int i, View view) {
        if (!this.mIsEditable) {
            return false;
        }
        this.mMonitorModeEnabled = (i & 2) != 0;
        if ((i & 1) != 0) {
            this.mHasPendingImmediateRequest = true;
            updateCursorAnchorInfo(view);
        }
        return true;
    }

    private void updateCursorAnchorInfo(View view) {
        if (this.mHasCoordinateInfo) {
            if (this.mLastCursorAnchorInfo == null) {
                this.mCursorAnchorInfoBuilder.reset();
                CharSequence text = this.mComposingTextDelegate.getText();
                int selectionStart = this.mComposingTextDelegate.getSelectionStart();
                int selectionEnd = this.mComposingTextDelegate.getSelectionEnd();
                int composingTextStart = this.mComposingTextDelegate.getComposingTextStart();
                int composingTextEnd = this.mComposingTextDelegate.getComposingTextEnd();
                if (text != null && composingTextStart >= 0 && composingTextEnd <= text.length()) {
                    this.mCursorAnchorInfoBuilder.setComposingText(composingTextStart, text.subSequence(composingTextStart, composingTextEnd));
                    float[] fArr = this.mCompositionCharacterBounds;
                    if (fArr != null) {
                        int length = fArr.length / 4;
                        for (int i = 0; i < length; i++) {
                            int i2 = i * 4;
                            this.mCursorAnchorInfoBuilder.addCharacterBounds(composingTextStart + i, fArr[i2], fArr[i2 + 1], fArr[i2 + 2], fArr[i2 + 3], 1);
                        }
                    }
                }
                this.mCursorAnchorInfoBuilder.setSelectionRange(selectionStart, selectionEnd);
                Matrix matrix = this.mMatrix;
                float f = this.mScale;
                matrix.setScale(f, f);
                this.mMatrix.postTranslate(this.mTranslationX, this.mTranslationY);
                this.mCursorAnchorInfoBuilder.setMatrix(this.mMatrix);
                if (this.mHasInsertionMarker) {
                    CursorAnchorInfo.Builder builder = this.mCursorAnchorInfoBuilder;
                    float f2 = this.mInsertionMarkerHorizontal;
                    float f3 = this.mInsertionMarkerTop;
                    float f4 = this.mInsertionMarkerBottom;
                    builder.setInsertionMarkerLocation(f2, f3, f4, f4, this.mIsInsertionMarkerVisible ? 1 : 2);
                }
                this.mLastCursorAnchorInfo = this.mCursorAnchorInfoBuilder.build();
            }
            InputMethodManagerWrapper inputMethodManagerWrapper = this.mInputMethodManagerWrapper;
            if (inputMethodManagerWrapper != null) {
                inputMethodManagerWrapper.updateCursorAnchorInfo(view, this.mLastCursorAnchorInfo);
            }
            this.mHasPendingImmediateRequest = false;
        }
    }
}
