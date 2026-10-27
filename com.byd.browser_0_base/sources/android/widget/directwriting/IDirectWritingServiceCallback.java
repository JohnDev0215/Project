package android.widget.directwriting;

import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public interface IDirectWritingServiceCallback extends IInterface {
    public static final String DESCRIPTOR = "android.widget.directwriting.IDirectWritingServiceCallback";
    public static final int VERSION = 1;

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            }
        }
    }

    void bindEditIn(float f, float f2);

    int getBaseLine();

    int getBottom();

    PointF getCursorLocation(int i);

    int getHeight();

    int getImeOptions();

    int getInputType();

    int getLeft();

    int getLineAscent(int i);

    int getLineBaseline(int i);

    int getLineBottom(int i);

    int getLineCount();

    int getLineDescent(int i);

    int getLineEnd(int i);

    int getLineForOffset(int i);

    int getLineHeight();

    int getLineHeightByIndex(int i);

    float getLineMax(int i);

    int getLineMaxIncludePadding(int i);

    int getLineStart(int i);

    int getLineTop(int i);

    int getLineVisibleEnd(int i);

    int getOffsetForPosition(float f, float f2);

    int getPaddingBottom();

    int getPaddingEnd();

    int getPaddingStart();

    int getPaddingTop();

    int getParagraphDirection(int i);

    float getPrimaryHorizontal(int i);

    String getPrivateImeOptions();

    int getRight();

    int getScrollY();

    int getSelectionEnd();

    int getSelectionStart();

    CharSequence getText();

    Rect getTextAreaRect(int i);

    int getTop();

    int getVersion();

    int getWidth();

    int length();

    void onAppPrivateCommand(String str, Bundle bundle);

    void onEditorAction(int i);

    void onExtraCommand(String str, Bundle bundle);

    void onFinishRecognition();

    void onTextViewExtraCommand(String str, Bundle bundle);

    void semForceHideSoftInput();

    void setSelection(int i);

    void setText(CharSequence charSequence);

    void setTextSelection(CharSequence charSequence, int i);

    public static class Default implements IDirectWritingServiceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getBaseLine() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getBottom() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public PointF getCursorLocation(int i) {
            return null;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getHeight() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getImeOptions() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getInputType() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLeft() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineAscent(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineBaseline(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineBottom(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineCount() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineDescent(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineEnd(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineForOffset(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineHeight() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineHeightByIndex(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public float getLineMax(int i) {
            return 0.0f;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineMaxIncludePadding(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineStart(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineTop(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getLineVisibleEnd(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getOffsetForPosition(float f, float f2) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getPaddingBottom() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getPaddingEnd() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getPaddingStart() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getPaddingTop() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getParagraphDirection(int i) {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public float getPrimaryHorizontal(int i) {
            return 0.0f;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public String getPrivateImeOptions() {
            return null;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getRight() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getScrollY() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getSelectionEnd() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getSelectionStart() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public CharSequence getText() {
            return null;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public Rect getTextAreaRect(int i) {
            return null;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getTop() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getVersion() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int getWidth() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public int length() {
            return 0;
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void onFinishRecognition() {
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void semForceHideSoftInput() {
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void onEditorAction(int i) {
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void setSelection(int i) {
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void setText(CharSequence charSequence) {
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void bindEditIn(float f, float f2) {
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void onAppPrivateCommand(String str, Bundle bundle) {
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void onExtraCommand(String str, Bundle bundle) {
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void onTextViewExtraCommand(String str, Bundle bundle) {
        }

        @Override // android.widget.directwriting.IDirectWritingServiceCallback
        public void setTextSelection(CharSequence charSequence, int i) {
        }
    }

    public static abstract class Stub extends Binder implements IDirectWritingServiceCallback {
        static final int TRANSACTION_bindEditIn = 11;
        static final int TRANSACTION_getBaseLine = 54;
        static final int TRANSACTION_getBottom = 51;
        static final int TRANSACTION_getCursorLocation = 91;
        static final int TRANSACTION_getHeight = 41;
        static final int TRANSACTION_getImeOptions = 102;
        static final int TRANSACTION_getInputType = 103;
        static final int TRANSACTION_getLeft = 49;
        static final int TRANSACTION_getLineAscent = 83;
        static final int TRANSACTION_getLineBaseline = 80;
        static final int TRANSACTION_getLineBottom = 78;
        static final int TRANSACTION_getLineCount = 53;
        static final int TRANSACTION_getLineDescent = 84;
        static final int TRANSACTION_getLineEnd = 76;
        static final int TRANSACTION_getLineForOffset = 74;
        static final int TRANSACTION_getLineHeight = 52;
        static final int TRANSACTION_getLineHeightByIndex = 81;
        static final int TRANSACTION_getLineMax = 73;
        static final int TRANSACTION_getLineMaxIncludePadding = 82;
        static final int TRANSACTION_getLineStart = 75;
        static final int TRANSACTION_getLineTop = 77;
        static final int TRANSACTION_getLineVisibleEnd = 79;
        static final int TRANSACTION_getOffsetForPosition = 33;
        static final int TRANSACTION_getPaddingBottom = 46;
        static final int TRANSACTION_getPaddingEnd = 47;
        static final int TRANSACTION_getPaddingStart = 44;
        static final int TRANSACTION_getPaddingTop = 45;
        static final int TRANSACTION_getParagraphDirection = 71;
        static final int TRANSACTION_getPrimaryHorizontal = 72;
        static final int TRANSACTION_getPrivateImeOptions = 101;
        static final int TRANSACTION_getRight = 48;
        static final int TRANSACTION_getScrollY = 43;
        static final int TRANSACTION_getSelectionEnd = 32;
        static final int TRANSACTION_getSelectionStart = 31;
        static final int TRANSACTION_getText = 34;
        static final int TRANSACTION_getTextAreaRect = 85;
        static final int TRANSACTION_getTop = 50;
        static final int TRANSACTION_getVersion = 1;
        static final int TRANSACTION_getWidth = 42;
        static final int TRANSACTION_length = 35;
        static final int TRANSACTION_onAppPrivateCommand = 112;
        static final int TRANSACTION_onEditorAction = 111;
        static final int TRANSACTION_onExtraCommand = 901;
        static final int TRANSACTION_onFinishRecognition = 2;
        static final int TRANSACTION_onTextViewExtraCommand = 902;
        static final int TRANSACTION_semForceHideSoftInput = 113;
        static final int TRANSACTION_setSelection = 23;
        static final int TRANSACTION_setText = 22;
        static final int TRANSACTION_setTextSelection = 21;

        public static class Proxy implements IDirectWritingServiceCallback {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void bindEditIn(float f, float f2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getBaseLine() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(54, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getBottom() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(51, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public PointF getCursorLocation(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(91, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (PointF) _Parcel.readTypedObject(parcelObtain2, PointF.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getHeight() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(41, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getImeOptions() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(102, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getInputType() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(103, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IDirectWritingServiceCallback.DESCRIPTOR;
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLeft() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(49, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineAscent(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(83, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineBaseline(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(80, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineBottom(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(78, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineCount() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(53, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineDescent(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(84, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineEnd(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(76, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineForOffset(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(74, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineHeight() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(52, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineHeightByIndex(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(81, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public float getLineMax(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(73, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineMaxIncludePadding(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(82, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineStart(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(75, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineTop(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(77, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getLineVisibleEnd(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(79, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getOffsetForPosition(float f, float f2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    this.mRemote.transact(33, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getPaddingBottom() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(46, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getPaddingEnd() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(47, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getPaddingStart() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(44, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getPaddingTop() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(45, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getParagraphDirection(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(71, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public float getPrimaryHorizontal(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(72, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public String getPrivateImeOptions() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(101, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getRight() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(48, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getScrollY() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(43, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getSelectionEnd() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(32, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getSelectionStart() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(31, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public CharSequence getText() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(34, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (CharSequence) _Parcel.readTypedObject(parcelObtain2, TextUtils.CHAR_SEQUENCE_CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public Rect getTextAreaRect(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(85, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (Rect) _Parcel.readTypedObject(parcelObtain2, Rect.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getTop() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(50, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getVersion() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int getWidth() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(42, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public int length() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(35, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void onAppPrivateCommand(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(112, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void onEditorAction(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(111, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void onExtraCommand(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(901, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void onFinishRecognition() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void onTextViewExtraCommand(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(902, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void semForceHideSoftInput() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(113, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void setSelection(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(23, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void setText(CharSequence charSequence) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.mRemote.transact(22, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.widget.directwriting.IDirectWritingServiceCallback
            public void setTextSelection(CharSequence charSequence, int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDirectWritingServiceCallback.DESCRIPTOR);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(21, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IDirectWritingServiceCallback.DESCRIPTOR);
        }

        public static IDirectWritingServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IDirectWritingServiceCallback.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IDirectWritingServiceCallback)) ? new Proxy(iBinder) : (IDirectWritingServiceCallback) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDirectWritingServiceCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDirectWritingServiceCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int version = getVersion();
                parcel2.writeNoException();
                parcel2.writeInt(version);
            } else if (i == 2) {
                onFinishRecognition();
                parcel2.writeNoException();
            } else if (i == 11) {
                bindEditIn(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
            } else if (i == 91) {
                PointF cursorLocation = getCursorLocation(parcel.readInt());
                parcel2.writeNoException();
                _Parcel.writeTypedObject(parcel2, cursorLocation, 1);
            } else if (i == 901) {
                String string = parcel.readString();
                Bundle bundle = (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR);
                onExtraCommand(string, bundle);
                parcel2.writeNoException();
                _Parcel.writeTypedObject(parcel2, bundle, 1);
            } else if (i != 902) {
                switch (i) {
                    case 21:
                        setTextSelection((CharSequence) _Parcel.readTypedObject(parcel, TextUtils.CHAR_SEQUENCE_CREATOR), parcel.readInt());
                        parcel2.writeNoException();
                        break;
                    case 22:
                        setText((CharSequence) _Parcel.readTypedObject(parcel, TextUtils.CHAR_SEQUENCE_CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 23:
                        setSelection(parcel.readInt());
                        parcel2.writeNoException();
                        break;
                    default:
                        switch (i) {
                            case 31:
                                int selectionStart = getSelectionStart();
                                parcel2.writeNoException();
                                parcel2.writeInt(selectionStart);
                                break;
                            case 32:
                                int selectionEnd = getSelectionEnd();
                                parcel2.writeNoException();
                                parcel2.writeInt(selectionEnd);
                                break;
                            case 33:
                                int offsetForPosition = getOffsetForPosition(parcel.readFloat(), parcel.readFloat());
                                parcel2.writeNoException();
                                parcel2.writeInt(offsetForPosition);
                                break;
                            case 34:
                                CharSequence text = getText();
                                parcel2.writeNoException();
                                if (text == null) {
                                    parcel2.writeInt(0);
                                } else {
                                    parcel2.writeInt(1);
                                    TextUtils.writeToParcel(text, parcel2, 1);
                                }
                                break;
                            case 35:
                                int length = length();
                                parcel2.writeNoException();
                                parcel2.writeInt(length);
                                break;
                            default:
                                switch (i) {
                                    case 41:
                                        int height = getHeight();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(height);
                                        break;
                                    case 42:
                                        int width = getWidth();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(width);
                                        break;
                                    case 43:
                                        int scrollY = getScrollY();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(scrollY);
                                        break;
                                    case 44:
                                        int paddingStart = getPaddingStart();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(paddingStart);
                                        break;
                                    case 45:
                                        int paddingTop = getPaddingTop();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(paddingTop);
                                        break;
                                    case 46:
                                        int paddingBottom = getPaddingBottom();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(paddingBottom);
                                        break;
                                    case 47:
                                        int paddingEnd = getPaddingEnd();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(paddingEnd);
                                        break;
                                    case 48:
                                        int right = getRight();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(right);
                                        break;
                                    case 49:
                                        int left = getLeft();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(left);
                                        break;
                                    case 50:
                                        int top = getTop();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(top);
                                        break;
                                    case 51:
                                        int bottom = getBottom();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(bottom);
                                        break;
                                    case 52:
                                        int lineHeight = getLineHeight();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(lineHeight);
                                        break;
                                    case 53:
                                        int lineCount = getLineCount();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(lineCount);
                                        break;
                                    case 54:
                                        int baseLine = getBaseLine();
                                        parcel2.writeNoException();
                                        parcel2.writeInt(baseLine);
                                        break;
                                    default:
                                        switch (i) {
                                            case 71:
                                                int paragraphDirection = getParagraphDirection(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(paragraphDirection);
                                                break;
                                            case 72:
                                                float primaryHorizontal = getPrimaryHorizontal(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeFloat(primaryHorizontal);
                                                break;
                                            case 73:
                                                float lineMax = getLineMax(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeFloat(lineMax);
                                                break;
                                            case 74:
                                                int lineForOffset = getLineForOffset(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineForOffset);
                                                break;
                                            case 75:
                                                int lineStart = getLineStart(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineStart);
                                                break;
                                            case 76:
                                                int lineEnd = getLineEnd(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineEnd);
                                                break;
                                            case 77:
                                                int lineTop = getLineTop(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineTop);
                                                break;
                                            case 78:
                                                int lineBottom = getLineBottom(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineBottom);
                                                break;
                                            case 79:
                                                int lineVisibleEnd = getLineVisibleEnd(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineVisibleEnd);
                                                break;
                                            case 80:
                                                int lineBaseline = getLineBaseline(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineBaseline);
                                                break;
                                            case 81:
                                                int lineHeightByIndex = getLineHeightByIndex(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineHeightByIndex);
                                                break;
                                            case 82:
                                                int lineMaxIncludePadding = getLineMaxIncludePadding(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineMaxIncludePadding);
                                                break;
                                            case 83:
                                                int lineAscent = getLineAscent(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineAscent);
                                                break;
                                            case 84:
                                                int lineDescent = getLineDescent(parcel.readInt());
                                                parcel2.writeNoException();
                                                parcel2.writeInt(lineDescent);
                                                break;
                                            case 85:
                                                Rect textAreaRect = getTextAreaRect(parcel.readInt());
                                                parcel2.writeNoException();
                                                _Parcel.writeTypedObject(parcel2, textAreaRect, 1);
                                                break;
                                            default:
                                                switch (i) {
                                                    case 101:
                                                        String privateImeOptions = getPrivateImeOptions();
                                                        parcel2.writeNoException();
                                                        parcel2.writeString(privateImeOptions);
                                                        break;
                                                    case 102:
                                                        int imeOptions = getImeOptions();
                                                        parcel2.writeNoException();
                                                        parcel2.writeInt(imeOptions);
                                                        break;
                                                    case 103:
                                                        int inputType = getInputType();
                                                        parcel2.writeNoException();
                                                        parcel2.writeInt(inputType);
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case 111:
                                                                onEditorAction(parcel.readInt());
                                                                parcel2.writeNoException();
                                                                break;
                                                            case 112:
                                                                onAppPrivateCommand(parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                                                                parcel2.writeNoException();
                                                                break;
                                                            case 113:
                                                                semForceHideSoftInput();
                                                                parcel2.writeNoException();
                                                                break;
                                                            default:
                                                                return super.onTransact(i, parcel, parcel2, i2);
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
            } else {
                String string2 = parcel.readString();
                Bundle bundle2 = (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR);
                onTextViewExtraCommand(string2, bundle2);
                parcel2.writeNoException();
                _Parcel.writeTypedObject(parcel2, bundle2, 1);
            }
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }
    }
}
