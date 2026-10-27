package com.byd.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.internal.view.SupportMenu;
import com.byd.imageedit.DressUpImageView;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class TextDressDrawable extends BaseDressDrawable {
    private LinkedList<String> contents;
    private Object extras;
    private int maxCharNum;
    private Random random;
    private String text;
    private int textColor;
    private int textOffset;
    private Rect textRect;
    private float textSize;

    public TextDressDrawable(DressUpImageView dressUpImageView) {
        this(dressUpImageView, "Hello");
    }

    public TextDressDrawable(DressUpImageView dressUpImageView, String str) {
        this(dressUpImageView, str, 48, 16, 20);
    }

    public TextDressDrawable(DressUpImageView dressUpImageView, String str, int i, int i2, int i3) {
        super(dressUpImageView, str);
        this.textColor = SupportMenu.CATEGORY_MASK;
        this.random = new Random();
        this.contents = new LinkedList<>();
        this.text = str;
        float f = i;
        this.textSize = f;
        this.maxCharNum = i2;
        this.textOffset = i3;
        this.paint.setTextSize(f);
        this.paint.setColor(this.textColor);
        this.textRect = new Rect();
        convertContent(this.contents);
        changeBound();
    }

    private void calcTextBound() {
        int length = this.text.length();
        Rect rect = new Rect();
        if (length <= this.maxCharNum) {
            Paint paint = this.paint;
            String str = this.text;
            paint.getTextBounds(str, 0, str.length(), rect);
            this.textRect.set(0, 0, rect.width(), rect.height());
            return;
        }
        int size = this.contents.size();
        int i = 0;
        int i2 = 0;
        for (String str2 : this.contents) {
            this.paint.getTextBounds(str2, 0, str2.length(), rect);
            int iWidth = rect.width();
            int iHeight = rect.height();
            if (i < iWidth) {
                i = iWidth;
            }
            if (i2 < iHeight) {
                i2 = iHeight;
            }
        }
        this.textRect.set(0, 0, i, i2 * size);
    }

    private void changeBound() {
        calcTextBound();
        do {
            if (this.textRect.width() <= this.viewWidth && this.textRect.height() <= this.viewHeight) {
                break;
            }
            float f = this.textSize - 3.0f;
            this.textSize = f;
            this.paint.setTextSize(f);
            calcTextBound();
        } while (this.textSize >= 10.0f);
        if (this.bound.width() != 0 || this.bound.height() != 0) {
            int iCenterX = this.bound.centerX();
            int iCenterY = this.bound.centerY();
            int iCenterX2 = this.textRect.centerX();
            int iCenterY2 = this.textRect.centerY();
            Rect rect = this.bound;
            rect.left = iCenterX - iCenterX2;
            rect.right = iCenterX + iCenterX2;
            rect.top = iCenterY - iCenterY2;
            rect.bottom = iCenterY + iCenterY2;
            checkBoundOffset();
            return;
        }
        int iWidth = this.textRect.width();
        int iHeight = this.textRect.height();
        int iNextInt = this.imageRect.left + this.random.nextInt(this.viewWidth - iWidth);
        int iNextInt2 = this.imageRect.top + this.random.nextInt(this.viewHeight - iHeight);
        Rect rect2 = this.bound;
        rect2.left = iNextInt;
        rect2.right = iWidth + iNextInt;
        rect2.top = iNextInt2;
        rect2.bottom = iNextInt2 + iHeight;
        checkBoundOffset();
        setBounds(this.bound);
    }

    private void checkBoundOffset() {
        int i = this.textOffset;
        if (i <= 0) {
            return;
        }
        this.bound.inset(-i, -i);
    }

    private int findSpaceChar(int i, int i2) {
        while (i2 > i) {
            char cCharAt = this.text.charAt(i2);
            if (' ' == cCharAt || '\n' == cCharAt) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    private int getTextBaseLine(int i) {
        Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
        float f = fontMetrics.bottom;
        return (int) (((i / 2.0f) + ((f - fontMetrics.top) / 2.0f)) - f);
    }

    public void convertContent(LinkedList<String> linkedList) {
        int iOffsetByCodePoints;
        linkedList.clear();
        if (TextUtils.isEmpty(this.text)) {
            return;
        }
        int length = this.text.length();
        int i = 0;
        int iCodePointCount = this.text.codePointCount(0, length);
        Log.e("m_tag", "convertContent : " + length + " " + iCodePointCount);
        if (iCodePointCount <= this.maxCharNum) {
            linkedList.add(this.text);
            return;
        }
        while (i < length) {
            try {
                iOffsetByCodePoints = this.text.offsetByCodePoints(i, this.maxCharNum);
            } catch (Exception unused) {
                iOffsetByCodePoints = length;
            }
            if (iOffsetByCodePoints >= length) {
                linkedList.add(this.text.substring(i));
                Log.e("m_tag", "last-->start : " + i + " end : " + length);
                return;
            }
            int iFindSpaceChar = findSpaceChar(i, iOffsetByCodePoints);
            if (iFindSpaceChar > 0) {
                int i2 = iFindSpaceChar + 1;
                linkedList.add(this.text.substring(i, i2));
                Log.e("m_tag", "-->start : " + i + " end : " + i2 + " " + this.maxCharNum);
                i = i2;
            } else {
                linkedList.add(this.text.substring(i, iOffsetByCodePoints));
                Log.e("m_tag", "start : " + i + " end : " + iOffsetByCodePoints + " " + this.maxCharNum);
                i = iOffsetByCodePoints;
            }
        }
    }

    @Override // com.byd.drawable.BaseDressDrawable
    protected void drawContent(Canvas canvas) {
        this.paint.setColor(this.textColor);
        this.paint.setAlpha(this.alpha);
        this.paint.setStyle(Paint.Style.FILL);
        int iCenterX = this.center.x - this.textRect.centerX();
        int iHeight = this.bound.height() / this.contents.size();
        int textBaseLine = getTextBaseLine(iHeight);
        Iterator<String> it = this.contents.iterator();
        while (it.hasNext()) {
            canvas.drawText(it.next(), iCenterX, this.bound.top + textBaseLine, this.paint);
            textBaseLine += iHeight;
        }
    }

    public Object getExtras() {
        return this.extras;
    }

    public int getMaxCharNum() {
        return this.maxCharNum;
    }

    public String getText() {
        return this.text;
    }

    public int getTextColor() {
        return this.textColor;
    }

    @Override // com.byd.drawable.BaseDressDrawable
    public void onScale(float f) {
        float f2 = this.textSize * f;
        int i = this.controllerRadius;
        if (f2 < i) {
            f2 = i;
        }
        this.paint.setTextSize(f2);
        calcTextBound();
        if (!this.controllMode || (this.textRect.width() <= this.viewWidth && this.textRect.height() <= this.viewHeight)) {
            this.textSize = f2;
        } else {
            this.paint.setTextSize(this.textSize);
            calcTextBound();
        }
        int iWidth = this.textRect.width() / 2;
        int iHeight = this.textRect.height() / 2;
        int iCenterX = this.bound.centerX();
        int iCenterY = this.bound.centerY();
        this.bound.set(iCenterX - iWidth, iCenterY - iHeight, iCenterX + iWidth, iCenterY + iHeight);
        checkBoundOffset();
        setBounds(this.bound);
    }

    public void setContents(LinkedList<String> linkedList) {
        this.contents = linkedList;
        changeBound();
        this.imageView.invalidate();
    }

    public void setExtras(Object obj) {
        this.extras = obj;
    }

    public void setMaxCharNum(int i) {
        this.maxCharNum = i;
    }

    public void setText(String str) {
        setTextNotInvalidate(str);
        convertContent(this.contents);
        changeBound();
        this.imageView.invalidate();
    }

    public void setTextColor(int i) {
        this.textColor = i;
        this.imageView.invalidate();
    }

    public void setTextColorNotInvalidate(int i) {
        this.textColor = i;
    }

    public void setTextNotInvalidate(String str) {
        if (str == null) {
            str = "";
        }
        this.text = str;
    }

    public void setTextOffset(int i) {
        this.textOffset = i;
    }

    public void setTextSize(float f) {
        this.textSize = f;
    }
}
