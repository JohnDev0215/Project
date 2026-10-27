package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.SplineSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R$styleable;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class KeyAttributes extends Key {
    private String mTransitionEasing;
    private int mCurveFit = -1;
    private float mAlpha = Float.NaN;
    private float mElevation = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mTransitionPathRotate = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;
    private float mProgress = Float.NaN;

    public KeyAttributes() {
        this.mCustomConstraints = new HashMap<>();
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyAttribute));
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.mAlpha)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mProgress)) {
            hashSet.add("progress");
        }
        if (this.mCustomConstraints.size() > 0) {
            Iterator<String> it = this.mCustomConstraints.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setInterpolation(HashMap<String, Integer> map) {
        if (this.mCurveFit == -1) {
            return;
        }
        if (!Float.isNaN(this.mAlpha)) {
            map.put("alpha", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mElevation)) {
            map.put("elevation", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotation)) {
            map.put("rotation", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationX)) {
            map.put("rotationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationY)) {
            map.put("rotationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationX)) {
            map.put("translationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationY)) {
            map.put("translationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            map.put("translationZ", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            map.put("transitionPathRotate", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleX)) {
            map.put("scaleX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleY)) {
            map.put("scaleY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mProgress)) {
            map.put("progress", Integer.valueOf(this.mCurveFit));
        }
        if (this.mCustomConstraints.size() > 0) {
            Iterator<String> it = this.mCustomConstraints.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.mCurveFit));
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, SplineSet> map) {
        for (String str : map.keySet()) {
            SplineSet splineSet = map.get(str);
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.mCustomConstraints.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((SplineSet.CustomSet) splineSet).setPoint(this.mFramePosition, constraintAttribute);
                }
            } else {
                switch (str) {
                    case "alpha":
                        if (Float.isNaN(this.mAlpha)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mAlpha);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.mElevation)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mElevation);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.mRotation)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mRotation);
                            break;
                        }
                        break;
                    case "rotationX":
                        if (Float.isNaN(this.mRotationX)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mRotationX);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.mRotationY)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mRotationY);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.mTransitionPathRotate)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mTransitionPathRotate);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.mScaleX)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mScaleX);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.mScaleY)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mScaleY);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.mTranslationX)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mTranslationX);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.mTranslationY)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mTranslationY);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.mTranslationZ)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mTranslationZ);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.mProgress)) {
                            break;
                        } else {
                            splineSet.setPoint(this.mFramePosition, this.mProgress);
                            break;
                        }
                        break;
                    default:
                        Log.v("KeyAttributes", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            }
        }
    }

    private static class Loader {
        private static SparseIntArray mAttrMap;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyAttribute_android_alpha, 1);
            mAttrMap.append(R$styleable.KeyAttribute_android_elevation, 2);
            mAttrMap.append(R$styleable.KeyAttribute_android_rotation, 4);
            mAttrMap.append(R$styleable.KeyAttribute_android_rotationX, 5);
            mAttrMap.append(R$styleable.KeyAttribute_android_rotationY, 6);
            mAttrMap.append(R$styleable.KeyAttribute_android_scaleX, 7);
            mAttrMap.append(R$styleable.KeyAttribute_transitionPathRotate, 8);
            mAttrMap.append(R$styleable.KeyAttribute_transitionEasing, 9);
            mAttrMap.append(R$styleable.KeyAttribute_motionTarget, 10);
            mAttrMap.append(R$styleable.KeyAttribute_framePosition, 12);
            mAttrMap.append(R$styleable.KeyAttribute_curveFit, 13);
            mAttrMap.append(R$styleable.KeyAttribute_android_scaleY, 14);
            mAttrMap.append(R$styleable.KeyAttribute_android_translationX, 15);
            mAttrMap.append(R$styleable.KeyAttribute_android_translationY, 16);
            mAttrMap.append(R$styleable.KeyAttribute_android_translationZ, 17);
            mAttrMap.append(R$styleable.KeyAttribute_motionProgress, 18);
        }

        public static void read(KeyAttributes keyAttributes, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (mAttrMap.get(index)) {
                    case 1:
                        keyAttributes.mAlpha = typedArray.getFloat(index, keyAttributes.mAlpha);
                        break;
                    case 2:
                        keyAttributes.mElevation = typedArray.getDimension(index, keyAttributes.mElevation);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
                        break;
                    case 4:
                        keyAttributes.mRotation = typedArray.getFloat(index, keyAttributes.mRotation);
                        break;
                    case 5:
                        keyAttributes.mRotationX = typedArray.getFloat(index, keyAttributes.mRotationX);
                        break;
                    case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                        keyAttributes.mRotationY = typedArray.getFloat(index, keyAttributes.mRotationY);
                        break;
                    case 7:
                        keyAttributes.mScaleX = typedArray.getFloat(index, keyAttributes.mScaleX);
                        break;
                    case 8:
                        keyAttributes.mTransitionPathRotate = typedArray.getFloat(index, keyAttributes.mTransitionPathRotate);
                        break;
                    case 9:
                        keyAttributes.mTransitionEasing = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyAttributes.mTargetId);
                            keyAttributes.mTargetId = resourceId;
                            if (resourceId == -1) {
                                keyAttributes.mTargetString = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyAttributes.mTargetString = typedArray.getString(index);
                        } else {
                            keyAttributes.mTargetId = typedArray.getResourceId(index, keyAttributes.mTargetId);
                        }
                        break;
                    case 12:
                        keyAttributes.mFramePosition = typedArray.getInt(index, keyAttributes.mFramePosition);
                        break;
                    case 13:
                        keyAttributes.mCurveFit = typedArray.getInteger(index, keyAttributes.mCurveFit);
                        break;
                    case 14:
                        keyAttributes.mScaleY = typedArray.getFloat(index, keyAttributes.mScaleY);
                        break;
                    case 15:
                        keyAttributes.mTranslationX = typedArray.getDimension(index, keyAttributes.mTranslationX);
                        break;
                    case 16:
                        keyAttributes.mTranslationY = typedArray.getDimension(index, keyAttributes.mTranslationY);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            keyAttributes.mTranslationZ = typedArray.getDimension(index, keyAttributes.mTranslationZ);
                        }
                        break;
                    case 18:
                        keyAttributes.mProgress = typedArray.getFloat(index, keyAttributes.mProgress);
                        break;
                }
            }
        }
    }
}
