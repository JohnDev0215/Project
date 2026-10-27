package com.android.inputmethod.compat;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.inputmethod.CursorAnchorInfo;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CursorAnchorInfoCompatWrapper {
    public static final int FLAG_HAS_INVISIBLE_REGION = 2;
    public static final int FLAG_HAS_VISIBLE_REGION = 1;
    public static final int FLAG_IS_RTL = 4;

    CursorAnchorInfoCompatWrapper() {
    }

    @Nullable
    public static CursorAnchorInfoCompatWrapper wrap(@Nullable final CursorAnchorInfo instance) {
        if (BuildCompatUtils.EFFECTIVE_SDK_INT >= 21 && instance != null) {
            return new RealWrapper(instance);
        }
        return null;
    }

    public int getSelectionStart() {
        throw new UnsupportedOperationException("not supported.");
    }

    public int getSelectionEnd() {
        throw new UnsupportedOperationException("not supported.");
    }

    public CharSequence getComposingText() {
        throw new UnsupportedOperationException("not supported.");
    }

    public int getComposingTextStart() {
        throw new UnsupportedOperationException("not supported.");
    }

    public Matrix getMatrix() {
        throw new UnsupportedOperationException("not supported.");
    }

    public RectF getCharacterBounds(final int index) {
        throw new UnsupportedOperationException("not supported.");
    }

    public int getCharacterBoundsFlags(final int index) {
        throw new UnsupportedOperationException("not supported.");
    }

    public float getInsertionMarkerBaseline() {
        throw new UnsupportedOperationException("not supported.");
    }

    public float getInsertionMarkerBottom() {
        throw new UnsupportedOperationException("not supported.");
    }

    public float getInsertionMarkerHorizontal() {
        throw new UnsupportedOperationException("not supported.");
    }

    public float getInsertionMarkerTop() {
        throw new UnsupportedOperationException("not supported.");
    }

    public int getInsertionMarkerFlags() {
        throw new UnsupportedOperationException("not supported.");
    }

    private static final class RealWrapper extends CursorAnchorInfoCompatWrapper {

        @Nonnull
        private final CursorAnchorInfo mInstance;

        public RealWrapper(@Nonnull final CursorAnchorInfo info) {
            this.mInstance = info;
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public int getSelectionStart() {
            return this.mInstance.getSelectionStart();
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public int getSelectionEnd() {
            return this.mInstance.getSelectionEnd();
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public CharSequence getComposingText() {
            return this.mInstance.getComposingText();
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public int getComposingTextStart() {
            return this.mInstance.getComposingTextStart();
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public Matrix getMatrix() {
            return this.mInstance.getMatrix();
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public RectF getCharacterBounds(final int index) {
            return this.mInstance.getCharacterBounds(index);
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public int getCharacterBoundsFlags(final int index) {
            return this.mInstance.getCharacterBoundsFlags(index);
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public float getInsertionMarkerBaseline() {
            return this.mInstance.getInsertionMarkerBaseline();
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public float getInsertionMarkerBottom() {
            return this.mInstance.getInsertionMarkerBottom();
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public float getInsertionMarkerHorizontal() {
            return this.mInstance.getInsertionMarkerHorizontal();
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public float getInsertionMarkerTop() {
            return this.mInstance.getInsertionMarkerTop();
        }

        @Override // com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper
        public int getInsertionMarkerFlags() {
            return this.mInstance.getInsertionMarkerFlags();
        }
    }
}
