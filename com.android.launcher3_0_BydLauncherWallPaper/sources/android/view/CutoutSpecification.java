package android.view;

import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextUtils;
import android.util.Log;
import android.util.PathParser;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class CutoutSpecification {
    private static final String BIND_LEFT_CUTOUT_MARKER = "@bind_left_cutout";
    private static final String BIND_RIGHT_CUTOUT_MARKER = "@bind_right_cutout";
    private static final String BOTTOM_MARKER = "@bottom";
    private static final String CENTER_VERTICAL_MARKER = "@center_vertical";
    private static final String CUTOUT_MARKER = "@cutout";
    private static final boolean DEBUG = false;
    private static final String DP_MARKER = "@dp";
    private static final String LEFT_MARKER = "@left";
    private static final char MARKER_START_CHAR = '@';
    private static final int MINIMAL_ACCEPTABLE_PATH_LENGTH = 5;
    private static final String RIGHT_MARKER = "@right";
    private static final String TAG = "CutoutSpecification";
    private final Rect mBottomBound;
    private final Insets mInsets;
    private final Rect mLeftBound;
    private final Path mPath;
    private final Rect mRightBound;
    private final Rect mTopBound;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0017, code lost:
    
        if (r6 != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x001c, code lost:
    
        return 80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
    
        return 48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000a, code lost:
    
        if (r6 != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int decideWhichEdge(boolean r4, boolean r5, boolean r6) {
        /*
            r0 = 3
            r1 = 5
            r2 = 48
            r3 = 80
            if (r4 == 0) goto L10
            if (r5 == 0) goto Ld
            if (r6 == 0) goto L1b
            goto L19
        Ld:
            if (r6 == 0) goto L15
            goto L1c
        L10:
            if (r5 == 0) goto L17
            if (r6 == 0) goto L15
            goto L1c
        L15:
            r0 = r1
            goto L1c
        L17:
            if (r6 == 0) goto L1b
        L19:
            r0 = r2
            goto L1c
        L1b:
            r0 = r3
        L1c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.CutoutSpecification.decideWhichEdge(boolean, boolean, boolean):int");
    }

    private CutoutSpecification(Parser parser) {
        this.mPath = parser.mPath;
        this.mLeftBound = parser.mLeftBound;
        this.mTopBound = parser.mTopBound;
        this.mRightBound = parser.mRightBound;
        this.mBottomBound = parser.mBottomBound;
        this.mInsets = parser.mInsets;
    }

    public Path getPath() {
        return this.mPath;
    }

    public Rect getLeftBound() {
        return this.mLeftBound;
    }

    public Rect getTopBound() {
        return this.mTopBound;
    }

    public Rect getRightBound() {
        return this.mRightBound;
    }

    public Rect getBottomBound() {
        return this.mBottomBound;
    }

    public Rect getSafeInset() {
        return this.mInsets.toRect();
    }

    public static class Parser {
        private Rect mBottomBound;
        private final float mDensity;
        private final int mDisplayHeight;
        private final int mDisplayWidth;
        private boolean mInDp;
        private Insets mInsets;
        private boolean mIsCloserToStartSide;
        private final boolean mIsShortEdgeOnTop;
        private boolean mIsTouchShortEdgeEnd;
        private boolean mIsTouchShortEdgeStart;
        private Rect mLeftBound;
        private Path mPath;
        private Rect mRightBound;
        private int mSafeInsetBottom;
        private int mSafeInsetLeft;
        private int mSafeInsetRight;
        private int mSafeInsetTop;
        private Rect mTopBound;
        private final Rect mTmpRect = new Rect();
        private final RectF mTmpRectF = new RectF();
        private boolean mPositionFromLeft = false;
        private boolean mPositionFromRight = false;
        private boolean mPositionFromBottom = false;
        private boolean mPositionFromCenterVertical = false;
        private boolean mBindLeftCutout = false;
        private boolean mBindRightCutout = false;
        private boolean mBindBottomCutout = false;
        private final Matrix mMatrix = new Matrix();

        public Parser(float f, int i, int i2) {
            this.mDensity = f;
            this.mDisplayWidth = i;
            this.mDisplayHeight = i2;
            this.mIsShortEdgeOnTop = i < i2;
        }

        private void computeBoundsRectAndAddToRegion(Path path, Region region, Rect rect) {
            this.mTmpRectF.setEmpty();
            path.computeBounds(this.mTmpRectF, false);
            this.mTmpRectF.round(rect);
            region.op(rect, Region.Op.UNION);
        }

        private void resetStatus(StringBuilder sb) {
            sb.setLength(0);
            this.mPositionFromBottom = false;
            this.mPositionFromLeft = false;
            this.mPositionFromRight = false;
            this.mPositionFromCenterVertical = false;
            this.mBindLeftCutout = false;
            this.mBindRightCutout = false;
            this.mBindBottomCutout = false;
        }

        private void translateMatrix() {
            float f;
            float f2 = 0.0f;
            if (this.mPositionFromRight) {
                f = this.mDisplayWidth;
            } else {
                f = this.mPositionFromLeft ? 0.0f : this.mDisplayWidth / 2.0f;
            }
            if (this.mPositionFromBottom) {
                f2 = this.mDisplayHeight;
            } else if (this.mPositionFromCenterVertical) {
                f2 = this.mDisplayHeight / 2.0f;
            }
            this.mMatrix.reset();
            if (this.mInDp) {
                Matrix matrix = this.mMatrix;
                float f3 = this.mDensity;
                matrix.postScale(f3, f3);
            }
            this.mMatrix.postTranslate(f, f2);
        }

        private int computeSafeInsets(int i, Rect rect) {
            if (i == 3 && rect.right > 0 && rect.right < this.mDisplayWidth) {
                return rect.right;
            }
            if (i == 48 && rect.bottom > 0 && rect.bottom < this.mDisplayHeight) {
                return rect.bottom;
            }
            if (i == 5 && rect.left > 0) {
                int i2 = rect.left;
                int i3 = this.mDisplayWidth;
                if (i2 < i3) {
                    return i3 - rect.left;
                }
            }
            if (i != 80 || rect.top <= 0) {
                return 0;
            }
            int i4 = rect.top;
            int i5 = this.mDisplayHeight;
            if (i4 < i5) {
                return i5 - rect.top;
            }
            return 0;
        }

        private void setSafeInset(int i, int i2) {
            if (i == 3) {
                this.mSafeInsetLeft = i2;
                return;
            }
            if (i == 48) {
                this.mSafeInsetTop = i2;
            } else if (i == 5) {
                this.mSafeInsetRight = i2;
            } else if (i == 80) {
                this.mSafeInsetBottom = i2;
            }
        }

        private int getSafeInset(int i) {
            if (i == 3) {
                return this.mSafeInsetLeft;
            }
            if (i == 48) {
                return this.mSafeInsetTop;
            }
            if (i == 5) {
                return this.mSafeInsetRight;
            }
            if (i == 80) {
                return this.mSafeInsetBottom;
            }
            return 0;
        }

        private Rect onSetEdgeCutout(boolean z, boolean z2, Rect rect) {
            int iDecideWhichEdge;
            if (z2) {
                iDecideWhichEdge = CutoutSpecification.decideWhichEdge(this.mIsShortEdgeOnTop, true, z);
            } else {
                boolean z3 = this.mIsTouchShortEdgeStart;
                iDecideWhichEdge = (z3 && this.mIsTouchShortEdgeEnd) ? CutoutSpecification.decideWhichEdge(this.mIsShortEdgeOnTop, false, z) : (z3 || this.mIsTouchShortEdgeEnd) ? CutoutSpecification.decideWhichEdge(this.mIsShortEdgeOnTop, true, this.mIsCloserToStartSide) : CutoutSpecification.decideWhichEdge(this.mIsShortEdgeOnTop, z2, z);
            }
            int safeInset = getSafeInset(iDecideWhichEdge);
            int iComputeSafeInsets = computeSafeInsets(iDecideWhichEdge, rect);
            if (safeInset < iComputeSafeInsets) {
                setSafeInset(iDecideWhichEdge, iComputeSafeInsets);
            }
            return new Rect(rect);
        }

        private void setEdgeCutout(Path path) {
            boolean z = this.mBindRightCutout;
            if (z && this.mRightBound == null) {
                this.mRightBound = onSetEdgeCutout(false, !this.mIsShortEdgeOnTop, this.mTmpRect);
            } else {
                boolean z2 = this.mBindLeftCutout;
                if (z2 && this.mLeftBound == null) {
                    this.mLeftBound = onSetEdgeCutout(true, !this.mIsShortEdgeOnTop, this.mTmpRect);
                } else {
                    boolean z3 = this.mBindBottomCutout;
                    if (z3 && this.mBottomBound == null) {
                        this.mBottomBound = onSetEdgeCutout(false, this.mIsShortEdgeOnTop, this.mTmpRect);
                    } else if (z3 || z2 || z || this.mTopBound != null) {
                        return;
                    } else {
                        this.mTopBound = onSetEdgeCutout(true, this.mIsShortEdgeOnTop, this.mTmpRect);
                    }
                }
            }
            Path path2 = this.mPath;
            if (path2 != null) {
                path2.addPath(path);
            } else {
                this.mPath = path;
            }
        }

        private void parseSvgPathSpec(Region region, String str) {
            if (TextUtils.length(str) < CutoutSpecification.MINIMAL_ACCEPTABLE_PATH_LENGTH) {
                Log.e(CutoutSpecification.TAG, "According to SVG definition, it shouldn't happen");
                return;
            }
            str.trim();
            translateMatrix();
            Path pathCreatePathFromPathData = PathParser.createPathFromPathData(str);
            pathCreatePathFromPathData.transform(this.mMatrix);
            computeBoundsRectAndAddToRegion(pathCreatePathFromPathData, region, this.mTmpRect);
            if (this.mTmpRect.isEmpty()) {
                return;
            }
            if (this.mIsShortEdgeOnTop) {
                this.mIsTouchShortEdgeStart = this.mTmpRect.top <= 0;
                this.mIsTouchShortEdgeEnd = this.mTmpRect.bottom >= this.mDisplayHeight;
                this.mIsCloserToStartSide = this.mTmpRect.centerY() < this.mDisplayHeight / 2;
            } else {
                this.mIsTouchShortEdgeStart = this.mTmpRect.left <= 0;
                this.mIsTouchShortEdgeEnd = this.mTmpRect.right >= this.mDisplayWidth;
                this.mIsCloserToStartSide = this.mTmpRect.centerX() < this.mDisplayWidth / 2;
            }
            setEdgeCutout(pathCreatePathFromPathData);
        }

        private void parseSpecWithoutDp(String str) {
            int i;
            Region regionObtain = Region.obtain();
            StringBuilder sb = null;
            int i2 = 0;
            while (true) {
                int iIndexOf = str.indexOf(64, i2);
                if (iIndexOf == -1) {
                    break;
                }
                if (sb == null) {
                    sb = new StringBuilder(str.length());
                }
                sb.append((CharSequence) str, i2, iIndexOf);
                if (str.startsWith(CutoutSpecification.LEFT_MARKER, iIndexOf)) {
                    if (!this.mPositionFromRight) {
                        this.mPositionFromLeft = true;
                    }
                    i = iIndexOf + 5;
                } else if (str.startsWith(CutoutSpecification.RIGHT_MARKER, iIndexOf)) {
                    if (!this.mPositionFromLeft) {
                        this.mPositionFromRight = true;
                    }
                    i = iIndexOf + 6;
                } else if (str.startsWith(CutoutSpecification.BOTTOM_MARKER, iIndexOf)) {
                    parseSvgPathSpec(regionObtain, sb.toString());
                    i = iIndexOf + 7;
                    resetStatus(sb);
                    this.mBindBottomCutout = true;
                    this.mPositionFromBottom = true;
                } else if (str.startsWith(CutoutSpecification.CENTER_VERTICAL_MARKER, iIndexOf)) {
                    parseSvgPathSpec(regionObtain, sb.toString());
                    i = iIndexOf + 16;
                    resetStatus(sb);
                    this.mPositionFromCenterVertical = true;
                } else if (str.startsWith(CutoutSpecification.CUTOUT_MARKER, iIndexOf)) {
                    parseSvgPathSpec(regionObtain, sb.toString());
                    i = iIndexOf + 7;
                    resetStatus(sb);
                } else if (str.startsWith(CutoutSpecification.BIND_LEFT_CUTOUT_MARKER, iIndexOf)) {
                    this.mBindBottomCutout = false;
                    this.mBindRightCutout = false;
                    this.mBindLeftCutout = true;
                    i = iIndexOf + 17;
                } else if (str.startsWith(CutoutSpecification.BIND_RIGHT_CUTOUT_MARKER, iIndexOf)) {
                    this.mBindBottomCutout = false;
                    this.mBindLeftCutout = false;
                    this.mBindRightCutout = true;
                    i = iIndexOf + 18;
                } else {
                    i = iIndexOf + 1;
                }
                i2 = i;
            }
            if (sb == null) {
                parseSvgPathSpec(regionObtain, str);
            } else {
                sb.append((CharSequence) str, i2, str.length());
                parseSvgPathSpec(regionObtain, sb.toString());
            }
            regionObtain.recycle();
        }

        public CutoutSpecification parse(String str) {
            Objects.requireNonNull(str);
            int iLastIndexOf = str.lastIndexOf(CutoutSpecification.DP_MARKER);
            this.mInDp = iLastIndexOf != -1;
            if (iLastIndexOf != -1) {
                str = str.substring(0, iLastIndexOf) + str.substring(iLastIndexOf + 3);
            }
            parseSpecWithoutDp(str);
            this.mInsets = Insets.of(this.mSafeInsetLeft, this.mSafeInsetTop, this.mSafeInsetRight, this.mSafeInsetBottom);
            return new CutoutSpecification(this);
        }
    }
}
