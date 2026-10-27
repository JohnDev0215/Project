package com.amazon.video.sdk.avod.playbackclient.subtitle.views.util;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public class TransformationMethods {
    public static final TransformationMethod ALL_CAPS;
    public static final TransformationMethod NO_OP;

    private TransformationMethods() {
    }

    static {
        NO_OP = new NoOpTransformationMethod();
        ALL_CAPS = new AllCapsTransformationMethod();
    }

    private static class NoOpTransformationMethod implements TransformationMethod {
        @Override // android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            return charSequence;
        }

        @Override // android.text.method.TransformationMethod
        public void onFocusChanged(View view, CharSequence charSequence, boolean z, int i, Rect rect) {
        }

        private NoOpTransformationMethod() {
        }
    }

    private static class AllCapsTransformationMethod implements TransformationMethod {
        @Override // android.text.method.TransformationMethod
        public void onFocusChanged(View view, CharSequence charSequence, boolean z, int i, Rect rect) {
        }

        private AllCapsTransformationMethod() {
        }

        @Override // android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            return charSequence.toString().toUpperCase(view.getContext().getResources().getConfiguration().locale);
        }
    }
}
