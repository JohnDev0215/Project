package com.visualon.OSMPSubTitle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.PointerIconCompat;
import com.visualon.OSMPSubTitle.DataObject.ImageStore;
import com.visualon.OSMPSubTitle.DataObject.JSSubTitleObject;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleDisPlayEffect;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleDisplayInfo;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleFontEffect;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleInfo;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleInfoEntry;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleStringInfo;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleTextInfoEntry;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleTextRowDescriptor;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleTextRowInfo;
import com.visualon.OSMPUtils.voLog;
import com.visualon.OSMPUtils.voOSPreviewSubtitleInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes7.dex */
public class VOSubtitleRender {
    private static final int SCALE_FOR_FONT_C_SIZE = 20000;
    private static final int SCALE_FOR_FONT_EM_SIZE = 30000;
    private static final int SCALE_FOR_FONT_PIXEL_SIZE = 10000;
    private static final int SCALE_FOR_FONT_SIZE = 1000;
    private static final String TAG = "VOSubtitleRender";
    private static Context sApplicationContext;
    private MessageHandler m_handlerEvent;
    private VOSubtitleInfo mSubtitleInfo = null;
    private ArrayList<VOSubtitleInfo> mSubtitleInfoArray = new ArrayList<>();
    private ViewGroup rlMain = null;
    private RelativeLayout llWindow1 = null;
    private int height = 0;
    private int width = 0;
    private float xyRate = 1.33f;
    private float fontSizeDefault = 0.0f;
    private TextOutLinesView textViewOfRows = null;
    private int heightOld = 0;
    private int widthOld = 0;
    private View vwSurface = null;
    private boolean mIsPreview = false;
    private final ReentrantLock mLock = new ReentrantLock();
    private boolean metadata_Arrive = false;
    private boolean m_enableDraw = false;
    private final int WM_CLEAR_WIDGET = PointerIconCompat.TYPE_CONTEXT_MENU;
    private Typeface fontTypeface = null;
    private Typeface fontTypefaceLast = null;
    private String nameLast = "";
    private VOSubTitleJsonParser mSubtitleJsonParser = new VOSubTitleJsonParser();
    private boolean DBG = false;

    class MessageHandler extends Handler {
        public MessageHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1001) {
                VOSubtitleRender.this.doClearSubtitle();
                return;
            }
            if (VOSubtitleRender.this.textViewOfRows != null) {
                VOSubtitleRender.this.textViewOfRows.reset();
            }
            VOSubtitleRender.this.createImageView((VOSubtitleInfo) message.obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doClearSubtitle() {
        if (voLog.enablePrintLog()) {
            voLog.d(TAG, "+doClearSubtitle", new Object[0]);
        }
        this.mLock.lock();
        try {
            if (this.mIsPreview) {
                return;
            }
            RelativeLayout relativeLayout = this.llWindow1;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(4);
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "llWindow1.setVisibility(View.INVISIBLE)", new Object[0]);
                }
            }
        } finally {
            this.mLock.unlock();
        }
    }

    private void checkPrevWindow() {
        RelativeLayout relativeLayout = this.llWindow1;
        if (relativeLayout != null) {
            if (relativeLayout.getParent() != this.rlMain) {
                this.llWindow1.removeAllViews();
                this.llWindow1 = null;
            } else if (this.llWindow1.getVisibility() != 0) {
                this.llWindow1.setVisibility(0);
            }
        }
    }

    public VOSubtitleRender() {
        this.m_handlerEvent = null;
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, " voSubTitleManager Construct", new Object[0]);
        }
        if (this.m_handlerEvent == null) {
            this.m_handlerEvent = new MessageHandler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        }
    }

    private void setSubtitleObjectNull() {
        this.mSubtitleInfo = null;
        invalidateView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Typeface fromName(String str) {
        Typeface typeface;
        Typeface typeface2 = this.fontTypeface;
        if (typeface2 != null) {
            return typeface2;
        }
        if (this.nameLast.equals(str) && (typeface = this.fontTypefaceLast) != null) {
            return typeface;
        }
        this.nameLast = str;
        int iMappingFontFamily = VOSubTitleJsonParser.mappingFontFamily(str);
        if (-1 == iMappingFontFamily) {
            Typeface customTypeface = getCustomTypeface(str);
            this.fontTypefaceLast = customTypeface;
            if (customTypeface == null) {
                this.fontTypefaceLast = Typeface.DEFAULT;
            }
            return this.fontTypefaceLast;
        }
        Typeface typefaceFromID = fromID(iMappingFontFamily);
        this.fontTypefaceLast = typefaceFromID;
        return typefaceFromID;
    }

    private Typeface fromID(int i) {
        switch (i) {
            case 0:
                return Typeface.DEFAULT;
            case 1:
            case 14:
                return Typeface.create("courier", 0);
            case 2:
            case 10:
            case 12:
                return Typeface.SERIF;
            case 3:
            case 15:
                return Typeface.create("helvetica", 0);
            case 4:
            case 16:
                return Typeface.create("arial", 0);
            case 5:
            case 17:
                return Typeface.create("casual", 0);
            case 6:
            case 18:
                return Typeface.create("cursive", 0);
            case 7:
            case 19:
                return Typeface.create("sans-serif-smallcaps", 0);
            case 8:
            case 20:
                return Typeface.MONOSPACE;
            case 9:
            case 11:
                return Typeface.SANS_SERIF;
            case 13:
                return Typeface.create("times new roman", 0);
            default:
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, " Font Style is " + i + ", ui set to default.", new Object[0]);
                }
                return Typeface.DEFAULT;
        }
    }

    private Typeface getCustomTypeface(String str) {
        Typeface typefaceCreateFromAsset = null;
        try {
            if (sApplicationContext.getAssets().open(str + ".ttf") != null) {
                typefaceCreateFromAsset = Typeface.createFromAsset(sApplicationContext.getAssets(), str + ".ttf");
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "OK> getCustomTypeface from Assets", new Object[0]);
                }
            } else if (voLog.enablePrintLog()) {
                voLog.i(TAG, "Fail> getCustomTypeface InputStreamis==null setFontName:" + str, new Object[0]);
            }
        } catch (IOException unused) {
            if (voLog.enablePrintLog()) {
                voLog.w(TAG, "CloseCaption no font in Assets", new Object[0]);
            }
        }
        return typefaceCreateFromAsset;
    }

    public int previewSubtitle(String str, Object obj) {
        View view;
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, " var previewSubtitle = " + str, new Object[0]);
        }
        this.mIsPreview = false;
        doClearSubtitle();
        if (obj instanceof voOSPreviewSubtitleInfo) {
            view = ((voOSPreviewSubtitleInfo) obj).getView();
        } else {
            view = (View) obj;
        }
        setSurfaceView(view);
        VOSubtitleInfo obj2SubtitleInfo = this.mSubtitleJsonParser.parseObj2SubtitleInfo(str);
        this.mSubtitleInfoArray.clear();
        this.mSubtitleInfoArray.add(obj2SubtitleInfo);
        this.mSubtitleInfo = obj2SubtitleInfo;
        this.mIsPreview = true;
        this.m_enableDraw = true;
        setXYRate(1.0f);
        createAndShowChildViews();
        TextOutLinesView textOutLinesView = this.textViewOfRows;
        if (textOutLinesView != null) {
            textOutLinesView.enableDraw(true);
        }
        checkPrevWindow();
        if (this.llWindow1 == null) {
            createAndShowChildViews();
        }
        invalidateView();
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, " previewSubtitle", new Object[0]);
        }
        return 0;
    }

    private void clearSubtitle(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            doClearSubtitle();
        } else {
            this.m_handlerEvent.sendEmptyMessage(PointerIconCompat.TYPE_CONTEXT_MENU);
        }
    }

    public void setMainLayout(ViewGroup viewGroup) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, " setMainLayout ,shoud call", new Object[0]);
        }
        clearSubtitle("setMainLayout");
        this.rlMain = viewGroup;
    }

    public void setSurfaceView(View view) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, " enter setSurfaceView as :" + view, new Object[0]);
        }
        if ((this.mIsPreview && view == null) || view == this.vwSurface) {
            return;
        }
        this.mIsPreview = false;
        clearSubtitle("setSurfaceView");
        this.vwSurface = view;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != null) {
                if (parent instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) this.vwSurface.getParent();
                    if (viewGroup != null) {
                        if (voLog.enablePrintLog()) {
                            voLog.i(TAG, " enter setSurfaceView setMainLayout ", new Object[0]);
                        }
                        setMainLayout(viewGroup);
                        if (this.mSubtitleInfoArray.size() > 0) {
                            requestDisplay();
                        }
                    }
                } else {
                    setMainLayout(null);
                }
            }
            if (sApplicationContext == null) {
                sApplicationContext = this.vwSurface.getContext().getApplicationContext();
                return;
            }
            return;
        }
        setMainLayout(null);
    }

    public void setXYRate(float f) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, " setXYRate+ ", new Object[0]);
        }
        invalidateView();
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, " setXYRate- ", new Object[0]);
        }
    }

    public int deposit(int i, int i2, int i3, int i4, int i5, byte[] bArr) {
        if (voLog.enablePrintLog()) {
            voLog.d(TAG, "Received deposit id=" + i + ", picType:" + i2 + ", w:" + i3 + ", h:" + i4 + ", size:" + i5, new Object[0]);
        }
        this.mSubtitleJsonParser.mImageStores.put(String.valueOf(i), new ImageStore(i, i3, i4, i5, i2, bArr));
        return 0;
    }

    public int withdraw(int i) {
        if (voLog.enablePrintLog()) {
            voLog.d(TAG, "Received withdraw id=" + i, new Object[0]);
        }
        this.mSubtitleJsonParser.mImageStores.remove(String.valueOf(i));
        return 0;
    }

    public int render(String str) {
        if (this.DBG) {
            Log.i(TAG, "var inputObject = " + str);
        } else if (voLog.enablePrintLog()) {
            voLog.i(TAG, "var inputObject = " + str, new Object[0]);
        }
        VOSubtitleInfo obj2SubtitleInfo = this.mSubtitleJsonParser.parseObj2SubtitleInfo(str);
        if (obj2SubtitleInfo != null) {
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "isForID3: " + obj2SubtitleInfo.isForID3, new Object[0]);
            }
            if (obj2SubtitleInfo.subtitleEntryList.size() < 1) {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "Received emptySubtitle", new Object[0]);
                }
                if (!(obj2SubtitleInfo.isForID3 ^ this.metadata_Arrive)) {
                    this.m_enableDraw = false;
                    clearSubtitle("render");
                }
            } else {
                this.metadata_Arrive = obj2SubtitleInfo.isForID3;
                this.m_enableDraw = true;
                handleRawData(obj2SubtitleInfo);
            }
        }
        return 0;
    }

    public boolean handleRawData(VOSubtitleInfo vOSubtitleInfo) {
        boolean z;
        this.mLock.lock();
        if (vOSubtitleInfo == null) {
            z = false;
        } else {
            try {
                this.mSubtitleInfoArray.clear();
                this.mSubtitleInfoArray.add(vOSubtitleInfo);
                requestDisplay();
                z = true;
            } finally {
                this.mLock.unlock();
            }
        }
        return z;
    }

    private void requestDisplay() {
        if (Looper.myLooper() == Looper.getMainLooper() && !this.metadata_Arrive) {
            TextOutLinesView textOutLinesView = this.textViewOfRows;
            if (textOutLinesView != null) {
                textOutLinesView.reset();
            }
            createImageView(this.mSubtitleInfoArray.get(0));
            return;
        }
        this.m_handlerEvent.sendMessageDelayed(this.m_handlerEvent.obtainMessage(1, 0, 0, this.mSubtitleInfoArray.get(0)), 10L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String reverseString(CharSequence charSequence) {
        return new StringBuilder(charSequence).reverse().toString();
    }

    private void invalidateView() {
        if (this.textViewOfRows != null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.textViewOfRows.invalidate();
            } else {
                this.textViewOfRows.postInvalidate();
            }
        }
    }

    public void checkLayoutChanged() {
        if (this.rlMain == null) {
            return;
        }
        View view = this.vwSurface;
        if (view != null) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup = null;
            if (parent != null && (parent instanceof ViewGroup)) {
                viewGroup = (ViewGroup) parent;
            }
            if (viewGroup != this.rlMain) {
                setMainLayout(viewGroup);
            }
        }
        if (checkxyRate()) {
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, " checkxyRate ", new Object[0]);
            }
            setXYRate(this.xyRate);
        }
    }

    private boolean checkxyRate() {
        TextOutLinesView textOutLinesView = this.textViewOfRows;
        if (textOutLinesView != null && this.llWindow1 != null && (textOutLinesView.getWidth() != this.llWindow1.getWidth() || this.textViewOfRows.getHeight() != this.llWindow1.getHeight())) {
            try {
                this.textViewOfRows.layout(0, 0, this.llWindow1.getWidth(), this.llWindow1.getHeight());
            } catch (Exception unused) {
            }
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, " textViewOfRows.getWidth()=" + this.textViewOfRows.getWidth() + ",textViewOfRows.getHeight()=" + this.textViewOfRows.getHeight(), new Object[0]);
            }
        }
        View view = this.vwSurface;
        if (view != null) {
            int width = view.getWidth();
            int height = this.vwSurface.getHeight();
            if (this.widthOld != width || this.heightOld != height) {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, " width_new=" + width + ",width_old=" + this.width + ";height_new=" + height + ",height_old=" + this.height, new Object[0]);
                }
                this.width = width;
                this.height = height;
                this.heightOld = height;
                this.widthOld = width;
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0328 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x02f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void createImageView(com.visualon.OSMPSubTitle.DataObject.VOSubtitleInfo r28) {
        /*
            Method dump skipped, instruction units count: 866
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPSubTitle.VOSubtitleRender.createImageView(com.visualon.OSMPSubTitle.DataObject.VOSubtitleInfo):void");
    }

    private void createAndShowChildViews() {
        if (sApplicationContext == null || this.mSubtitleInfo == null || this.rlMain == null) {
            return;
        }
        this.mLock.lock();
        try {
            if (this.llWindow1 == null) {
                this.llWindow1 = new RelativeLayout(sApplicationContext);
                this.rlMain.addView(this.llWindow1, new ViewGroup.LayoutParams(-1, -1));
                this.textViewOfRows = new TextOutLinesView(sApplicationContext);
                this.llWindow1.addView(this.textViewOfRows, new RelativeLayout.LayoutParams(-1, -1));
            }
            VOSubtitleInfo vOSubtitleInfo = this.mSubtitleInfo;
            if (vOSubtitleInfo != null && vOSubtitleInfo.subtitleEntryList != null) {
                for (int i = 0; i < this.mSubtitleInfo.subtitleEntryList.size(); i++) {
                    VOSubtitleDisplayInfo vOSubtitleDisplayInfo = this.mSubtitleInfo.subtitleEntryList.get(i).subtitleDispInfo;
                    if (vOSubtitleDisplayInfo.lineTextRowInfoList != null) {
                        for (int i2 = 0; i2 < vOSubtitleDisplayInfo.lineTextRowInfoList.size(); i2++) {
                            startAnimation(vOSubtitleDisplayInfo.lineTextRowInfoList.get(i2), this.llWindow1);
                        }
                    }
                }
                invalidateView();
                createImageView(null);
            }
        } finally {
            this.mLock.unlock();
        }
    }

    private void removeImageViews() {
        if (this.llWindow1 == null) {
            return;
        }
        invalidateView();
        while (true) {
            RelativeLayout relativeLayout = this.llWindow1;
            View viewFindViewWithTag = relativeLayout.findViewWithTag(relativeLayout);
            if (viewFindViewWithTag == null) {
                return;
            } else {
                this.llWindow1.removeView(viewFindViewWithTag);
            }
        }
    }

    private void startAnimation(VOSubtitleTextRowInfo vOSubtitleTextRowInfo, RelativeLayout relativeLayout) {
        int i = vOSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffect.effectType;
        if (i > 0) {
            if (i == 1) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration(vOSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffect.effectSpeed * 1000);
                relativeLayout.startAnimation(alphaAnimation);
            }
            VOSubtitleDisPlayEffect vOSubtitleDisPlayEffect = vOSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffect;
            if (vOSubtitleDisPlayEffect.effectType == 2) {
                int i2 = vOSubtitleDisPlayEffect.effectDirection;
                float f = i2 == 0 ? -this.width : 0.0f;
                if (i2 == 1) {
                    f = this.width;
                }
                float f2 = i2 == 2 ? -this.height : 0.0f;
                if (i2 == 3) {
                    f2 = this.height;
                }
                TranslateAnimation translateAnimation = new TranslateAnimation(f, 0.0f, f2, 0.0f);
                translateAnimation.setDuration(vOSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffect.effectSpeed * 1000);
                relativeLayout.startAnimation(translateAnimation);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int toRealX(int i) {
        return (this.width * i) / 100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int toRealY(int i) {
        return (this.height * i) / 100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFontSize(VOSubtitleTextInfoEntry vOSubtitleTextInfoEntry, int i) {
        float f;
        float f2 = vOSubtitleTextInfoEntry.stringInfo.fontInfo.fontSize;
        if (f2 < 1.0f) {
            return f2;
        }
        int i2 = (int) f2;
        if (i2 > 1000 && i2 < SCALE_FOR_FONT_PIXEL_SIZE) {
            f2 = ((i2 + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED) * 0.9f) / 100.0f;
        }
        if (i2 > SCALE_FOR_FONT_PIXEL_SIZE) {
            if (this.fontSizeDefault < 1.0f) {
                TextOutLinesView textOutLinesView = this.textViewOfRows;
                if (textOutLinesView != null) {
                    this.fontSizeDefault = textOutLinesView.getTextSize();
                } else {
                    this.fontSizeDefault = new Paint().getTextSize();
                }
                if (this.fontSizeDefault < 24.0f) {
                    this.fontSizeDefault = 24.0f;
                }
            }
            if (i2 > SCALE_FOR_FONT_EM_SIZE) {
                int i3 = (int) (this.fontSizeDefault * ((i2 - SCALE_FOR_FONT_EM_SIZE) / 100.0f));
                if (i <= 0) {
                    return 0.9f;
                }
                f = i3;
            } else if (i2 > SCALE_FOR_FONT_C_SIZE) {
                int i4 = (int) ((this.height / 12) * ((i2 - SCALE_FOR_FONT_C_SIZE) / 100.0f));
                if (i <= 0) {
                    return 0.9f;
                }
                f2 = i4 / i;
            } else {
                int i5 = i2 - SCALE_FOR_FONT_PIXEL_SIZE;
                if (i <= 0) {
                    return 0.9f;
                }
                f = i5;
            }
            return f / i;
        }
        return f2;
    }

    private class LinearLayoutShowBorder extends LinearLayout {
        int m_nWidth;
        Paint paintBorder;

        public LinearLayoutShowBorder(Context context) {
            super(context);
            this.m_nWidth = 0;
            this.paintBorder = new Paint();
        }

        public LinearLayoutShowBorder(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.m_nWidth = 0;
            this.paintBorder = new Paint();
        }

        public void setBorder(int i, int i2) {
            this.m_nWidth = i;
            this.paintBorder.setStrokeWidth(i);
            this.paintBorder.setColor(i2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (this.m_nWidth == 0) {
                return;
            }
            canvas.drawLine(0.0f, 0.0f, getWidth(), 0.0f, this.paintBorder);
            canvas.drawLine(0.0f, 0.0f, 0.0f, getHeight(), this.paintBorder);
            canvas.drawLine(getWidth(), 0.0f, getWidth(), getHeight(), this.paintBorder);
            canvas.drawLine(0.0f, getHeight(), getWidth(), getHeight(), this.paintBorder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Point StretchToMax(Point point, Point point2) {
        Point point3 = new Point(0, 0);
        float f = point.x / point.y;
        int i = point2.x;
        int i2 = point2.y;
        if (f > i / i2) {
            point3.x = i;
            point3.y = (int) (i / f);
        } else {
            point3.y = i2;
            point3.x = (int) (f * i2);
        }
        return point3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Point StretchByScale(Point point, double d) {
        Point point2 = new Point(0, 0);
        double d2 = d / 100.0d;
        point2.x = (int) (((double) point.x) * d2);
        point2.y = (int) (((double) point.y) * d2);
        return point2;
    }

    private static Rect scaleRect(Rect rect, float f) {
        Rect rect2 = new Rect(rect);
        int iWidth = rect2.width();
        int iHeight = rect2.height();
        int iCenterX = rect2.centerX();
        int iCenterY = rect2.centerY();
        float f2 = iWidth;
        if (f2 * f > 100.0f && iWidth > 0) {
            f = 100.0f / f2;
        }
        int i = (int) (f2 * f);
        int i2 = (int) (iHeight * f);
        int i3 = iCenterX - (i / 2);
        rect2.left = i3;
        int i4 = i3 + i;
        rect2.right = i4;
        if (i4 > 100) {
            rect2.right = 100;
            rect2.left = 100 - i;
        }
        if (rect2.left < 0) {
            rect2.left = 0;
            rect2.right = i;
        }
        int i5 = iCenterY - (i2 / 2);
        rect2.top = i5;
        int i6 = i5 + i2;
        rect2.bottom = i6;
        if (i6 > 100) {
            rect2.bottom = 100;
            rect2.top = 100 - i2;
        }
        if (rect2.top < 0) {
            rect2.top = 0;
            rect2.bottom = i2;
        }
        return rect2;
    }

    private class TextOutLinesView extends TextView {
        private boolean enableDraw;
        private ArrayList<Rect> mBlockWindowRectList;
        private int maxFontSize;
        private int nOldBottom;
        private int nOldLeft;
        private int nOldRight;
        private int nOldTop;
        private boolean oldBottomSet;
        private int xBegin;
        private int xBeginBoundBox;
        private int yBegin;
        private int yBeginBoundBox;

        public void enableDraw(boolean z) {
            this.enableDraw = z;
            setVisibility(z ? 0 : 8);
        }

        public void reset() {
            this.enableDraw = true;
            this.xBegin = 0;
            this.yBegin = 0;
            this.xBeginBoundBox = 0;
            this.yBeginBoundBox = 0;
            this.oldBottomSet = false;
            this.nOldBottom = -1;
            this.nOldTop = -1;
            this.nOldLeft = -1;
            this.nOldRight = -1;
            this.maxFontSize = 0;
            this.mBlockWindowRectList.clear();
        }

        public TextOutLinesView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.enableDraw = true;
            this.xBegin = 0;
            this.yBegin = 0;
            this.xBeginBoundBox = 0;
            this.yBeginBoundBox = 0;
            this.oldBottomSet = false;
            this.nOldBottom = -1;
            this.nOldTop = -1;
            this.nOldLeft = -1;
            this.nOldRight = -1;
            this.maxFontSize = 0;
            this.mBlockWindowRectList = new ArrayList<>();
        }

        public TextOutLinesView(Context context) {
            super(context);
            this.enableDraw = true;
            this.xBegin = 0;
            this.yBegin = 0;
            this.xBeginBoundBox = 0;
            this.yBeginBoundBox = 0;
            this.oldBottomSet = false;
            this.nOldBottom = -1;
            this.nOldTop = -1;
            this.nOldLeft = -1;
            this.nOldRight = -1;
            this.maxFontSize = 0;
            this.mBlockWindowRectList = new ArrayList<>();
        }

        private int setTextViewByTextInfo(VOSubtitleTextRowInfo vOSubtitleTextRowInfo, VOSubtitleTextInfoEntry vOSubtitleTextInfoEntry, Paint paint, Paint paint2) {
            float fontSize;
            paint.setColor(vOSubtitleTextInfoEntry.stringInfo.fontInfo.fontColor);
            VOSubtitleStringInfo vOSubtitleStringInfo = vOSubtitleTextInfoEntry.stringInfo;
            VOSubtitleFontEffect vOSubtitleFontEffect = vOSubtitleStringInfo.charEffect;
            int i = vOSubtitleFontEffect.isItalic ? 2 : 0;
            if (vOSubtitleFontEffect.isBold) {
                i |= 1;
            }
            Typeface typefaceCreate = Typeface.create(VOSubtitleRender.this.fromName(vOSubtitleStringInfo.fontInfo.fontName), i);
            paint.setFakeBoldText(i == 1 || i == 3);
            int i2 = i & 2;
            if (i2 != 0) {
                paint.setTextSkewX(-0.5f);
            }
            paint.setTypeface(typefaceCreate);
            if (paint2 != null) {
                paint2.setTypeface(typefaceCreate);
                paint2.setFakeBoldText((i & 1) != 0);
                if (i2 != 0) {
                    paint2.setTextSkewX(-0.5f);
                }
            }
            VOSubtitleTextRowDescriptor vOSubtitleTextRowDescriptor = vOSubtitleTextRowInfo.textRowDes;
            boolean z = vOSubtitleTextRowDescriptor.printDirection > 1;
            Rect rowRect = getRowRect(vOSubtitleTextRowDescriptor.dataBox.rect, false, vOSubtitleTextRowDescriptor.horizontalAlignment, vOSubtitleTextRowDescriptor.verticalAlignment);
            if (z) {
                int realX = VOSubtitleRender.this.toRealX(rowRect.left);
                int realX2 = VOSubtitleRender.this.toRealX(rowRect.right);
                String str = vOSubtitleTextInfoEntry.stringInfo.fontInfo.fontSizeStr;
                if (str.indexOf("px", str.length() - 2) == -1) {
                    int i3 = realX2 - realX;
                    fontSize = i3 * VOSubtitleRender.this.getFontSize(vOSubtitleTextInfoEntry, i3);
                } else {
                    fontSize = vOSubtitleTextInfoEntry.stringInfo.fontInfo.fontSize;
                }
                paint.setTextSize(fontSize);
                if (paint2 != null) {
                    paint2.setTextSize(fontSize);
                }
            } else {
                int realY = VOSubtitleRender.this.toRealY(rowRect.top);
                int realY2 = VOSubtitleRender.this.toRealY(rowRect.bottom);
                String str2 = vOSubtitleTextInfoEntry.stringInfo.fontInfo.fontSizeStr;
                if (str2.indexOf("px", str2.length() - 2) == -1) {
                    int i4 = realY2 - realY;
                    fontSize = i4 * VOSubtitleRender.this.getFontSize(vOSubtitleTextInfoEntry, i4);
                } else {
                    fontSize = vOSubtitleTextInfoEntry.stringInfo.fontInfo.fontSize;
                }
                paint.setTextSize(fontSize);
                if (paint2 != null) {
                    paint2.setTextSize(fontSize);
                }
            }
            int i5 = (int) fontSize;
            if (vOSubtitleTextInfoEntry.stringInfo.charEffect.hasUnderline) {
                paint.setUnderlineText(true);
            } else {
                paint.setUnderlineText(false);
            }
            VOSubtitleFontEffect vOSubtitleFontEffect2 = vOSubtitleTextInfoEntry.stringInfo.charEffect;
            int i6 = vOSubtitleFontEffect2.edgeType;
            if (i6 != 0) {
                int i7 = vOSubtitleFontEffect2.eddgeColor;
                if (i6 == 3) {
                    paint.setStrokeJoin(Paint.Join.ROUND);
                    paint.setStrokeWidth(i5 / 9 >= 2 ? r11 : 2);
                } else {
                    float f = i5 / 9.0f;
                    float f2 = f > 1.8f ? f : 1.8f;
                    if ((i6 == 2 || i6 == 1) && paint2 != null) {
                        paint2.setColor(i7);
                    }
                    if (i6 == 4) {
                        paint.setShadowLayer(f2, -2.0f, 2.0f, i7);
                    }
                    if (i6 == 5) {
                        paint.setShadowLayer(f2, 2.0f, 2.0f, i7);
                    }
                }
            }
            return i5;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0095  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void drawPicture(android.graphics.Canvas r18, com.visualon.OSMPSubTitle.DataObject.VOSubtitleDisplayInfo r19, android.graphics.Rect r20) {
            /*
                Method dump skipped, instruction units count: 418
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPSubTitle.VOSubtitleRender.TextOutLinesView.drawPicture(android.graphics.Canvas, com.visualon.OSMPSubTitle.DataObject.VOSubtitleDisplayInfo, android.graphics.Rect):void");
        }

        private void drawText(Canvas canvas, String str, float f, float f2, Paint paint) {
            canvas.drawText(str, f + this.xBegin + this.xBeginBoundBox, f2 + this.yBegin + this.yBeginBoundBox, paint);
        }

        private void drawRect(Canvas canvas, Rect rect, Paint paint, int i) {
            Rect rect2 = new Rect();
            int i2 = rect.left;
            int i3 = this.xBegin;
            int i4 = this.xBeginBoundBox;
            rect2.left = i2 + i3 + i4;
            rect2.right = rect.right + i3 + i4 + i;
            int i5 = rect.top;
            int i6 = this.yBegin;
            int i7 = this.yBeginBoundBox;
            rect2.top = i5 + i6 + i7;
            rect2.bottom = rect.bottom + i6 + i7;
            canvas.drawRect(rect2, paint);
        }

        private Rect getRowRect(Rect rect, boolean z, int i, int i2) {
            Rect rect2;
            Rect rect3 = new Rect(rect);
            if (VOSubtitleRender.this.mIsPreview) {
                return rect3;
            }
            if (i == -1 && i2 == -1 && VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect == null) {
                return rect3;
            }
            if (VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect != null) {
                rect2 = new Rect(VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.left, VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.top, VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.right, VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.bottom);
            } else {
                rect2 = new Rect(0, 0, 100, 100);
            }
            int iWidth = rect3.width();
            rect3.height();
            if (i == JSSubTitleObject.HAlignmentType.LEFT.getValue()) {
                int i3 = rect2.left;
                rect3.left = i3;
                if (z) {
                    rect3.right = i3 + iWidth;
                }
            } else if (i == JSSubTitleObject.HAlignmentType.CENTER.getValue()) {
                if (z) {
                    int i4 = rect2.left;
                    int i5 = i4 + (((rect2.right - i4) - iWidth) / 2);
                    rect3.left = i5;
                    rect3.right = i5 + iWidth;
                }
            } else if (i == JSSubTitleObject.HAlignmentType.RIGHT.getValue()) {
                int i6 = rect2.right;
                rect3.right = i6;
                if (z) {
                    rect3.left = i6 - iWidth;
                }
            }
            return rect3;
        }

        private int getFontMaxSize(VOSubtitleTextRowInfo vOSubtitleTextRowInfo, int i) {
            float fontSize;
            int i2 = 0;
            for (int i3 = 0; i3 < vOSubtitleTextRowInfo.segmentTextInfoList.size(); i3++) {
                VOSubtitleTextInfoEntry vOSubtitleTextInfoEntry = vOSubtitleTextRowInfo.segmentTextInfoList.get(i3);
                if (vOSubtitleTextInfoEntry.stringInfo.fontInfo.fontSizeStr.toLowerCase().indexOf("px") == -1) {
                    fontSize = VOSubtitleRender.this.getFontSize(vOSubtitleTextInfoEntry, i) * i;
                } else {
                    fontSize = vOSubtitleTextInfoEntry.stringInfo.fontInfo.fontSize;
                }
                int i4 = (int) fontSize;
                if (i4 > i2) {
                    i2 = i4;
                }
            }
            if (i2 > 0 && i2 < i) {
                i2 = i2 < (i * 3) / 4 ? (i2 * 4) / 3 : i;
            }
            return i2 <= 0 ? i : i2;
        }

        private int getHorizontalAlignment(VOSubtitleTextRowInfo vOSubtitleTextRowInfo) {
            int value = JSSubTitleObject.HAlignmentType.LEFT.getValue();
            if (vOSubtitleTextRowInfo != null) {
                value = vOSubtitleTextRowInfo.textRowDes.horizontalAlignment;
            }
            boolean unused = VOSubtitleRender.this.mIsPreview;
            return value;
        }

        private boolean isBoundingBoxEnabled() {
            if (VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect == null) {
                return false;
            }
            return (VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.top == 0 && VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.left == 0 && VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.right == 100 && VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.bottom == 100) ? false : true;
        }

        private boolean computeBoundBox(ArrayList<Rect> arrayList, int i, int i2) {
            Rect rect;
            this.xBeginBoundBox = 0;
            this.yBeginBoundBox = 0;
            if (VOSubtitleRender.this.mIsPreview || arrayList.isEmpty() || (i == -1 && i2 == -1 && VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect == null)) {
                return false;
            }
            Rect rect2 = new Rect(arrayList.get(0));
            for (int i3 = 1; i3 < arrayList.size(); i3++) {
                Rect rect3 = arrayList.get(i3);
                int i4 = rect3.top;
                if (i4 < rect2.top) {
                    rect2.top = i4;
                }
                int i5 = rect3.left;
                if (i5 < rect2.left) {
                    rect2.left = i5;
                }
                int i6 = rect3.bottom;
                if (i6 > rect2.bottom) {
                    rect2.bottom = i6;
                }
                int i7 = rect3.right;
                if (i7 > rect2.right) {
                    rect2.right = i7;
                }
            }
            if (VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect != null) {
                rect = new Rect(VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.left, VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.top, VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.right, VOSubtitleRender.this.mSubtitleInfo.boundingBoxRect.bottom);
            } else {
                rect = new Rect(0, 0, 100, 100);
            }
            Rect rect4 = new Rect(VOSubtitleRender.this.toRealX(rect.left), VOSubtitleRender.this.toRealY(rect.top), VOSubtitleRender.this.toRealX(rect.right), VOSubtitleRender.this.toRealY(rect.bottom));
            int iHeight = rect4.height();
            int iWidth = rect4.width();
            if (i == JSSubTitleObject.HAlignmentType.LEFT.getValue()) {
                this.xBeginBoundBox = rect4.left - rect2.left;
            } else if (i == JSSubTitleObject.HAlignmentType.CENTER.getValue()) {
                int i8 = rect2.right;
                int i9 = rect2.left;
                this.xBeginBoundBox = (((iWidth - (i8 - i9)) / 2) + rect4.left) - i9;
            } else if (i == JSSubTitleObject.HAlignmentType.RIGHT.getValue()) {
                this.xBeginBoundBox = rect4.right - rect2.right;
            }
            if (i2 == JSSubTitleObject.VAlignmentType.TOP.getValue()) {
                this.yBeginBoundBox = rect4.top - rect2.top;
            } else if (i2 == JSSubTitleObject.VAlignmentType.CENTER.getValue()) {
                int i10 = rect2.bottom;
                int i11 = rect2.top;
                this.yBeginBoundBox = (((iHeight - (i10 - i11)) / 2) + rect4.top) - i11;
            } else if (i2 == JSSubTitleObject.VAlignmentType.BOTTOM.getValue()) {
                this.yBeginBoundBox = rect4.bottom - rect2.bottom;
            } else if (isBoundingBoxEnabled()) {
                int i12 = rect4.top;
                int i13 = rect2.top;
                if (i12 > i13) {
                    this.yBeginBoundBox = i12 - i13;
                } else {
                    int i14 = rect4.bottom;
                    int i15 = rect2.bottom;
                    if (i14 < i15) {
                        this.yBeginBoundBox = i14 - i15;
                    }
                }
            }
            return false;
        }

        private void CheckRect(Rect rect, Rect rect2) {
            int i = rect.right;
            if (i == -1) {
                rect.right = rect2.right;
            } else {
                int i2 = rect2.right;
                if (i < i2) {
                    rect.right = i2;
                }
            }
            int i3 = rect.bottom;
            if (i3 == -1) {
                rect.bottom = rect2.bottom;
            } else {
                int i4 = rect2.bottom;
                if (i3 < i4) {
                    rect.bottom = i4;
                }
            }
            int i5 = rect.left;
            if (i5 == -1) {
                rect.left = rect2.left;
            } else {
                int i6 = rect2.left;
                if (i5 > i6) {
                    rect.left = i6;
                }
            }
            int i7 = rect.top;
            if (i7 == -1) {
                rect.top = rect2.top;
                return;
            }
            int i8 = rect2.top;
            if (i7 > i8) {
                rect.top = i8;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:47:0x0165  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void doRenderSubtitle(android.graphics.Canvas r14) {
            /*
                Method dump skipped, instruction units count: 431
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPSubTitle.VOSubtitleRender.TextOutLinesView.doRenderSubtitle(android.graphics.Canvas):void");
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas canvas) {
            if (canvas == null && voLog.enablePrintLog()) {
                voLog.e(VOSubtitleRender.TAG, "canvas can not be null onDraw!", new Object[0]);
            }
            doRenderSubtitle(canvas);
        }

        private void drawBlockTextInfo(Canvas canvas, VOSubtitleInfoEntry vOSubtitleInfoEntry, Rect rect, int i, int i2, int i3) {
            VOSubtitleDisplayInfo vOSubtitleDisplayInfo = vOSubtitleInfoEntry.subtitleDispInfo;
            if (i3 == 1) {
                drawPicture(canvas, vOSubtitleDisplayInfo, null);
            } else {
                drawPicture(canvas, vOSubtitleDisplayInfo, rect);
            }
            if (vOSubtitleDisplayInfo.lineTextRowInfoList == null) {
                return;
            }
            for (int i4 = 0; i4 < vOSubtitleDisplayInfo.lineTextRowInfoList.size(); i4++) {
                drawLineTextRowInfo(canvas, vOSubtitleDisplayInfo.lineTextRowInfoList.get(i4), rect, i2, i3, vOSubtitleInfoEntry.getSubtitleRectInfo().rect);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:67:0x014e A[LOOP:1: B:65:0x0146->B:67:0x014e, LOOP_END] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void drawLineTextRowInfo(android.graphics.Canvas r17, com.visualon.OSMPSubTitle.DataObject.VOSubtitleTextRowInfo r18, android.graphics.Rect r19, int r20, int r21, android.graphics.Rect r22) {
            /*
                Method dump skipped, instruction units count: 374
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPSubTitle.VOSubtitleRender.TextOutLinesView.drawLineTextRowInfo(android.graphics.Canvas, com.visualon.OSMPSubTitle.DataObject.VOSubtitleTextRowInfo, android.graphics.Rect, int, int, android.graphics.Rect):void");
        }

        class DrawingLineInfo {
            int alignmentH;
            int nRightOld;
            int nTopOld;
            int rWinRectLeft;
            int rWinRectRight;
            int winRectBottom;
            int winRectTop;

            DrawingLineInfo() {
            }
        }

        private ArrayList<String> reConcatenateWithoutNewLine(String str) {
            ArrayList<String> arrayList = new ArrayList<>();
            String[] strArrSplit = str.split("\n");
            for (int i = 0; i < strArrSplit.length; i++) {
                if (strArrSplit[i].length() > 1) {
                    arrayList.add(strArrSplit[i]);
                } else if (strArrSplit[i].length() == 1) {
                    if (arrayList.size() > 0) {
                        String str2 = arrayList.get(arrayList.size() - 1);
                        if (str2.length() < 32) {
                            arrayList.set(arrayList.size() - 1, str2 + strArrSplit[i]);
                        } else {
                            arrayList.add(strArrSplit[i]);
                        }
                    } else {
                        arrayList.add(strArrSplit[i]);
                    }
                }
            }
            return arrayList;
        }

        /* JADX WARN: Removed duplicated region for block: B:139:0x03d4  */
        /* JADX WARN: Removed duplicated region for block: B:150:0x0446  */
        /* JADX WARN: Removed duplicated region for block: B:152:0x0449  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x046b  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x025f  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x0263 A[PHI: r2
  0x0263: PHI (r2v13 int) = (r2v12 int), (r2v36 int) binds: [B:93:0x0284, B:85:0x025d] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void drawSegmentTextInfo(android.graphics.Canvas r34, com.visualon.OSMPSubTitle.DataObject.VOSubtitleTextRowInfo r35, android.graphics.Rect r36, int r37, int r38, int r39, int r40, com.visualon.OSMPSubTitle.VOSubtitleRender.TextOutLinesView.DrawingLineInfo r41) {
            /*
                Method dump skipped, instruction units count: 1189
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPSubTitle.VOSubtitleRender.TextOutLinesView.drawSegmentTextInfo(android.graphics.Canvas, com.visualon.OSMPSubTitle.DataObject.VOSubtitleTextRowInfo, android.graphics.Rect, int, int, int, int, com.visualon.OSMPSubTitle.VOSubtitleRender$TextOutLinesView$DrawingLineInfo):void");
        }

        private void drawBackground(Canvas canvas, VOSubtitleInfo vOSubtitleInfo, ArrayList<Rect> arrayList, int i, int i2) {
            boolean z;
            for (int i3 = 0; i3 < vOSubtitleInfo.subtitleEntryList.size(); i3++) {
                VOSubtitleInfoEntry vOSubtitleInfoEntry = vOSubtitleInfo.subtitleEntryList.get(i3);
                VOSubtitleDisplayInfo vOSubtitleDisplayInfo = vOSubtitleInfoEntry.subtitleDispInfo;
                Paint paint = new Paint();
                paint.setTextAlign(Paint.Align.LEFT);
                paint.setColor(vOSubtitleInfoEntry.subtitleRectInfo.color);
                if (paint.getAlpha() != 0) {
                    if (vOSubtitleDisplayInfo.lineTextRowInfoList != null) {
                        z = false;
                        for (int i4 = 0; i4 < vOSubtitleDisplayInfo.lineTextRowInfoList.size(); i4++) {
                            VOSubtitleTextRowInfo vOSubtitleTextRowInfo = vOSubtitleDisplayInfo.lineTextRowInfoList.get(i4);
                            if (vOSubtitleTextRowInfo != null && vOSubtitleTextRowInfo.segmentTextInfoList != null) {
                                int i5 = 0;
                                while (true) {
                                    if (i5 >= vOSubtitleTextRowInfo.segmentTextInfoList.size()) {
                                        break;
                                    }
                                    if (vOSubtitleTextRowInfo.segmentTextInfoList.get(i5) != null && vOSubtitleTextRowInfo.segmentTextInfoList.get(i5).text != null && vOSubtitleTextRowInfo.segmentTextInfoList.get(i5).text.length() > 0) {
                                        z = true;
                                        break;
                                    }
                                    i5++;
                                }
                            }
                        }
                    } else {
                        z = false;
                    }
                    if (z && (arrayList.get(i3).left != -1 || arrayList.get(i3).top != -1 || arrayList.get(i3).bottom != -1 || arrayList.get(i3).right != -1)) {
                        Rect rowRect = getRowRect(vOSubtitleInfoEntry.subtitleRectInfo.rect, false, vOSubtitleInfoEntry.horizontalAlignment, vOSubtitleInfoEntry.verticalAlignment);
                        int realX = VOSubtitleRender.this.toRealX(rowRect.left);
                        int realX2 = VOSubtitleRender.this.toRealX(rowRect.right);
                        if (realX2 < realX) {
                            realX2 = realX;
                        }
                        int realY = VOSubtitleRender.this.toRealY(rowRect.top);
                        int realY2 = VOSubtitleRender.this.toRealY(rowRect.bottom);
                        Rect rect = new Rect();
                        int i6 = arrayList.get(i3).left;
                        rect.left = i6;
                        if (i6 < 0) {
                            rect.left = 0;
                        }
                        rect.right = arrayList.get(i3).right;
                        int i7 = arrayList.get(i3).top;
                        rect.top = i7;
                        if (i7 < 0) {
                            rect.top = 0;
                        }
                        int i8 = arrayList.get(i3).bottom;
                        rect.bottom = i8;
                        if (rect.left > realX) {
                            rect.left = realX;
                        }
                        if (rect.top > realY) {
                            rect.top = realY;
                        }
                        if (rect.right < realX2) {
                            rect.right = realX2;
                        }
                        if (i8 < realY2) {
                            rect.bottom = realY2;
                        }
                        if (i < 0) {
                            rect.bottom += i;
                            rect.top += i;
                        }
                        drawRect(canvas, rect, paint, i2);
                    }
                }
            }
        }
    }

    public void setSubtitleTrim(String str) {
        this.mSubtitleJsonParser.setSubtitleTrim(str);
    }

    public void setTypeface(Typeface typeface) {
        this.fontTypeface = typeface;
        invalidateView();
    }

    public void reset() {
        this.mLock.lock();
        try {
            setSubtitleTrim("");
            TextOutLinesView textOutLinesView = this.textViewOfRows;
            if (textOutLinesView != null) {
                textOutLinesView.reset();
            }
            this.m_enableDraw = false;
            this.mIsPreview = false;
            this.mSubtitleInfoArray.clear();
            this.mSubtitleInfo = null;
            clearSubtitle("reset");
        } finally {
            this.mLock.unlock();
        }
    }
}
