package defpackage;

import J.N;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.directwriting.IDirectWritingServiceCallback;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* JADX INFO: compiled from: DirectWritingServiceCallback.java */
/* JADX INFO: loaded from: classes3.dex */
public class ga0 extends IDirectWritingServiceCallback.Stub {
    public static final /* synthetic */ int m = 0;
    public EditorInfo e;
    public int f;
    public int g;
    public String h;
    public Rect i;
    public Point j;
    public zv2 k;
    public final a l = new a(Looper.getMainLooper());

    /* JADX INFO: compiled from: DirectWritingServiceCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            float[] floatArray;
            String str;
            ga0 ga0Var = ga0.this;
            zv2 zv2Var = ga0Var.k;
            if (zv2Var == null) {
                return;
            }
            int i = message.what;
            if (i == 101) {
                ImeAdapterImpl imeAdapterImpl = ImeAdapterImpl.this;
                if (imeAdapterImpl.g()) {
                    N.M_V5g5ie(imeAdapterImpl.e, imeAdapterImpl);
                }
                zv2 zv2Var2 = ga0Var.k;
                int length = ga0Var.h.length();
                ImeAdapterImpl imeAdapterImpl2 = ImeAdapterImpl.this;
                if (imeAdapterImpl2.g()) {
                    N.MmtjCblb(imeAdapterImpl2.e, imeAdapterImpl2, 0, length);
                }
                ImeAdapterImpl.this.l((CharSequence) message.obj, message.arg1, true, 0);
                zv2 zv2Var3 = ga0Var.k;
                int i2 = message.arg1;
                ImeAdapterImpl imeAdapterImpl3 = ImeAdapterImpl.this;
                if (imeAdapterImpl3.g()) {
                    N.MmtjCblb(imeAdapterImpl3.e, imeAdapterImpl3, i2, i2);
                    return;
                }
                return;
            }
            if (i == 201) {
                ImeAdapterImpl.this.i(message.arg1);
                return;
            }
            if (i != 401) {
                if (i != 301) {
                    if (i != 302) {
                        return;
                    }
                    ImeAdapterImpl.this.e();
                    return;
                } else {
                    ImeAdapterImpl imeAdapterImpl4 = ImeAdapterImpl.this;
                    imeAdapterImpl4.E = true;
                    imeAdapterImpl4.o();
                    imeAdapterImpl4.E = false;
                    return;
                }
            }
            if (((String) message.obj).equals("recognition_info")) {
                Bundle data = message.getData();
                if (ga0Var.k == null) {
                    return;
                }
                String string = data.getString("gesture_type", "");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                String string2 = data.getString("text_alternative", "");
                xv2 xv2Var = new xv2();
                xv2Var.c = 0;
                if (string.equals("backspace") || string.equals("zigzag")) {
                    floatArray = data.getFloatArray("start_point");
                    xv2Var.e = ga0Var.A(data.getFloatArray("end_point"));
                    xv2Var.b = 0;
                } else {
                    if (string.equals("v_space")) {
                        floatArray = data.getFloatArray("lowest_point");
                        String string3 = data.getString("text_insertion", "");
                        str = TextUtils.isEmpty(string3) ? " " : string3;
                        int length2 = str.length();
                        short[] sArr = new short[length2];
                        for (int i3 = 0; i3 < length2; i3++) {
                            sArr[i3] = (short) str.charAt(i3);
                        }
                        mu2 mu2Var = new mu2();
                        mu2Var.b = sArr;
                        xv2Var.g = mu2Var;
                        xv2Var.b = 1;
                    } else if (string.equals("wedge_space")) {
                        floatArray = data.getFloatArray("highest_point");
                        String string4 = data.getString("text_insertion", "");
                        str = TextUtils.isEmpty(string4) ? " " : string4;
                        int length3 = str.length();
                        short[] sArr2 = new short[length3];
                        for (int i4 = 0; i4 < length3; i4++) {
                            sArr2[i4] = (short) str.charAt(i4);
                        }
                        mu2 mu2Var2 = new mu2();
                        mu2Var2.b = sArr2;
                        xv2Var.g = mu2Var2;
                        xv2Var.b = 1;
                    } else if (string.equals("u_type_remove_space") || string.equals("arch_type_remove_space")) {
                        floatArray = data.getFloatArray("start_point");
                        xv2Var.e = ga0Var.A(data.getFloatArray("end_point"));
                        xv2Var.b = 2;
                    } else {
                        if (!string.equals("i_type_functional")) {
                            if (TextUtils.isEmpty(string2)) {
                                Log.w("cr_DWCallbackImpl", "Skip handling unsupported gesture: ".concat(string));
                                return;
                            } else {
                                ImeAdapterImpl.this.l(string2, string2.length(), true, 0);
                                return;
                            }
                        }
                        floatArray = data.getFloatArray("center_point");
                        xv2Var.b = 3;
                    }
                }
                int i5 = xv2Var.b;
                if (i5 == 0) {
                    uv2.a(0);
                } else if (i5 == 1) {
                    uv2.a(1);
                } else if (i5 == 2) {
                    uv2.a(2);
                } else if (i5 == 3) {
                    uv2.a(3);
                }
                xv2Var.d = ga0Var.A(floatArray);
                int length4 = string2.length();
                short[] sArr3 = new short[length4];
                for (int i6 = 0; i6 < length4; i6++) {
                    sArr3[i6] = (short) string2.charAt(i6);
                }
                mu2 mu2Var3 = new mu2();
                mu2Var3.b = sArr3;
                xv2Var.f = mu2Var3;
                ((ImeAdapterImpl.a) ga0Var.k).b(-1, xv2Var);
            }
        }
    }

    public final org.chromium.gfx.mojom.Rect A(float[] fArr) {
        float f = fArr[0];
        Rect rect = this.i;
        float f2 = fArr[1];
        Rect rect2 = this.i;
        float[] fArr2 = {a8.o(f, rect.left, rect.right), a8.o(f2, rect2.top, rect2.bottom)};
        org.chromium.gfx.mojom.Rect rect3 = new org.chromium.gfx.mojom.Rect();
        rect3.b = (int) fArr2[0];
        rect3.c = (int) fArr2[1];
        rect3.d = 0;
        rect3.e = 0;
        return rect3;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getBaseLine() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getBottom() {
        Rect rect = this.i;
        if (rect != null) {
            return rect.bottom;
        }
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final PointF getCursorLocation(int i) {
        return this.j == null ? new PointF() : new PointF(this.j);
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getHeight() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getImeOptions() {
        EditorInfo editorInfo = this.e;
        if (editorInfo != null) {
            return editorInfo.imeOptions;
        }
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getInputType() {
        EditorInfo editorInfo = this.e;
        if (editorInfo != null) {
            return editorInfo.inputType;
        }
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLeft() {
        Rect rect = this.i;
        if (rect != null) {
            return rect.left;
        }
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineAscent(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineBaseline(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineBottom(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineCount() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineDescent(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineEnd(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineForOffset(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineHeight() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineHeightByIndex(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final float getLineMax(int i) {
        return 0.0f;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineMaxIncludePadding(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineStart(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineTop(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getLineVisibleEnd(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getOffsetForPosition(float f, float f2) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getPaddingBottom() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getPaddingEnd() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getPaddingStart() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getPaddingTop() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getParagraphDirection(int i) {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final float getPrimaryHorizontal(int i) {
        return 0.0f;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final String getPrivateImeOptions() {
        EditorInfo editorInfo = this.e;
        return editorInfo != null ? editorInfo.privateImeOptions : "";
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getRight() {
        Rect rect = this.i;
        if (rect != null) {
            return rect.right;
        }
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getScrollY() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getSelectionEnd() {
        return this.g;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getSelectionStart() {
        return this.f;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final CharSequence getText() {
        String str = this.h;
        return str == null ? "" : str;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final Rect getTextAreaRect(int i) {
        return new Rect(0, 0, 0, 0);
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getTop() {
        Rect rect = this.i;
        if (rect != null) {
            return rect.top;
        }
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getVersion() {
        return 1;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int getWidth() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final int length() {
        return 0;
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void onAppPrivateCommand(String str, Bundle bundle) {
        zv2 zv2Var;
        View viewA;
        InputMethodManager inputMethodManager;
        if (bundle == null || (zv2Var = this.k) == null || (viewA = ((ImeAdapterImpl.a) zv2Var).a()) == null || (inputMethodManager = (InputMethodManager) viewA.getContext().getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.sendAppPrivateCommand(viewA, str, bundle);
        if (bundle.getBoolean("showKeyboard")) {
            a aVar = this.l;
            aVar.sendMessage(aVar.obtainMessage(301));
        }
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void onEditorAction(int i) {
        a aVar = this.l;
        Message messageObtainMessage = aVar.obtainMessage(201);
        messageObtainMessage.arg1 = i;
        aVar.sendMessage(messageObtainMessage);
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void onTextViewExtraCommand(String str, Bundle bundle) {
        a aVar = this.l;
        Message messageObtainMessage = aVar.obtainMessage(401);
        messageObtainMessage.obj = str;
        messageObtainMessage.setData(bundle);
        aVar.sendMessage(messageObtainMessage);
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void semForceHideSoftInput() {
        a aVar = this.l;
        aVar.sendMessage(aVar.obtainMessage(302));
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void setTextSelection(CharSequence charSequence, int i) {
        a aVar = this.l;
        Message messageObtainMessage = aVar.obtainMessage(101);
        messageObtainMessage.obj = charSequence;
        messageObtainMessage.arg1 = i;
        aVar.sendMessage(messageObtainMessage);
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void onFinishRecognition() {
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void setSelection(int i) {
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void setText(CharSequence charSequence) {
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void bindEditIn(float f, float f2) {
    }

    @Override // android.widget.directwriting.IDirectWritingServiceCallback
    public final void onExtraCommand(String str, Bundle bundle) {
    }
}
