package android.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class ChangeText extends Transition {
    public static final int CHANGE_BEHAVIOR_IN = 2;
    public static final int CHANGE_BEHAVIOR_KEEP = 0;
    public static final int CHANGE_BEHAVIOR_OUT = 1;
    public static final int CHANGE_BEHAVIOR_OUT_IN = 3;
    private static final String LOG_TAG = "TextChange";
    private static final String PROPNAME_TEXT_COLOR = "android:textchange:textColor";
    private int mChangeBehavior = 0;
    private static final String PROPNAME_TEXT = "android:textchange:text";
    private static final String PROPNAME_TEXT_SELECTION_START = "android:textchange:textSelectionStart";
    private static final String PROPNAME_TEXT_SELECTION_END = "android:textchange:textSelectionEnd";
    private static final String[] sTransitionProperties = {PROPNAME_TEXT, PROPNAME_TEXT_SELECTION_START, PROPNAME_TEXT_SELECTION_END};

    public ChangeText setChangeBehavior(int i) {
        if (i >= 0 && i <= 3) {
            this.mChangeBehavior = i;
        }
        return this;
    }

    @Override // android.transition.Transition
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public int getChangeBehavior() {
        return this.mChangeBehavior;
    }

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof TextView) {
            TextView textView = (TextView) transitionValues.view;
            transitionValues.values.put(PROPNAME_TEXT, textView.getText());
            if (textView instanceof EditText) {
                transitionValues.values.put(PROPNAME_TEXT_SELECTION_START, Integer.valueOf(textView.getSelectionStart()));
                transitionValues.values.put(PROPNAME_TEXT_SELECTION_END, Integer.valueOf(textView.getSelectionEnd()));
            }
            if (this.mChangeBehavior > 0) {
                transitionValues.values.put(PROPNAME_TEXT_COLOR, Integer.valueOf(textView.getCurrentTextColor()));
            }
        }
    }

    @Override // android.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // android.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // android.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int iIntValue;
        int i;
        int iIntValue2;
        int i2;
        int i3;
        char c;
        CharSequence charSequence;
        int i4;
        int i5;
        int i6;
        ValueAnimator valueAnimatorOfFloat;
        ValueAnimator valueAnimatorOfInt;
        final int i7;
        Animator animator;
        final int i8;
        if (transitionValues == null || transitionValues2 == null || !(transitionValues.view instanceof TextView) || !(transitionValues2.view instanceof TextView)) {
            return null;
        }
        final TextView textView = (TextView) transitionValues2.view;
        Map<String, Object> map = transitionValues.values;
        Map<String, Object> map2 = transitionValues2.values;
        String str = map.get(PROPNAME_TEXT) != null ? (CharSequence) map.get(PROPNAME_TEXT) : "";
        String str2 = map2.get(PROPNAME_TEXT) != null ? (CharSequence) map2.get(PROPNAME_TEXT) : "";
        boolean z = textView instanceof EditText;
        if (z) {
            int iIntValue3 = map.get(PROPNAME_TEXT_SELECTION_START) != null ? ((Integer) map.get(PROPNAME_TEXT_SELECTION_START)).intValue() : -1;
            iIntValue = map.get(PROPNAME_TEXT_SELECTION_END) != null ? ((Integer) map.get(PROPNAME_TEXT_SELECTION_END)).intValue() : iIntValue3;
            int iIntValue4 = map2.get(PROPNAME_TEXT_SELECTION_START) != null ? ((Integer) map2.get(PROPNAME_TEXT_SELECTION_START)).intValue() : -1;
            i = iIntValue4;
            iIntValue2 = map2.get(PROPNAME_TEXT_SELECTION_END) != null ? ((Integer) map2.get(PROPNAME_TEXT_SELECTION_END)).intValue() : iIntValue4;
            i2 = iIntValue3;
        } else {
            iIntValue = -1;
            i = -1;
            iIntValue2 = -1;
            i2 = -1;
        }
        if (str.equals(str2)) {
            return null;
        }
        if (this.mChangeBehavior != 2) {
            textView.setText(str);
            if (z) {
                setSelection((EditText) textView, i2, iIntValue);
            }
        }
        if (this.mChangeBehavior == 0) {
            valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            final CharSequence charSequence2 = str;
            final CharSequence charSequence3 = str2;
            final int i9 = i;
            final int i10 = iIntValue2;
            valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: android.transition.ChangeText.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    if (charSequence2.equals(textView.getText())) {
                        textView.setText(charSequence3);
                        TextView textView2 = textView;
                        if (textView2 instanceof EditText) {
                            ChangeText.this.setSelection((EditText) textView2, i9, i10);
                        }
                    }
                }
            });
            i5 = iIntValue;
            charSequence = str;
            i3 = i2;
            i8 = 0;
        } else {
            int i11 = iIntValue;
            final int iIntValue5 = ((Integer) map.get(PROPNAME_TEXT_COLOR)).intValue();
            final int iIntValue6 = ((Integer) map2.get(PROPNAME_TEXT_COLOR)).intValue();
            int i12 = this.mChangeBehavior;
            if (i12 == 3 || i12 == 1) {
                ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(Color.alpha(iIntValue5), 0);
                valueAnimatorOfInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.transition.ChangeText.2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int iIntValue7 = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        textView.setTextColor((iIntValue5 & 16777215) | (iIntValue7 << 24));
                    }
                });
                final CharSequence charSequence4 = str;
                i3 = i2;
                c = 1;
                final CharSequence charSequence5 = str2;
                charSequence = str;
                i4 = 3;
                final int i13 = i;
                final int i14 = iIntValue2;
                i5 = i11;
                i6 = iIntValue6;
                valueAnimatorOfInt2.addListener(new AnimatorListenerAdapter() { // from class: android.transition.ChangeText.3
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator2) {
                        if (charSequence4.equals(textView.getText())) {
                            textView.setText(charSequence5);
                            TextView textView2 = textView;
                            if (textView2 instanceof EditText) {
                                ChangeText.this.setSelection((EditText) textView2, i13, i14);
                            }
                        }
                        textView.setTextColor(iIntValue6);
                    }
                });
                valueAnimatorOfFloat = valueAnimatorOfInt2;
            } else {
                i5 = i11;
                c = 1;
                i6 = iIntValue6;
                charSequence = str;
                i3 = i2;
                valueAnimatorOfFloat = null;
                i4 = 3;
            }
            int i15 = this.mChangeBehavior;
            if (i15 == i4 || i15 == 2) {
                int[] iArr = new int[2];
                iArr[0] = 0;
                iArr[c] = Color.alpha(i6);
                valueAnimatorOfInt = ValueAnimator.ofInt(iArr);
                i7 = i6;
                valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.transition.ChangeText.4
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int iIntValue7 = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        textView.setTextColor((i7 & 16777215) | (iIntValue7 << 24));
                    }
                });
                valueAnimatorOfInt.addListener(new AnimatorListenerAdapter() { // from class: android.transition.ChangeText.5
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator2) {
                        textView.setTextColor(i7);
                    }
                });
            } else {
                i7 = i6;
                valueAnimatorOfInt = null;
            }
            if (valueAnimatorOfFloat != null && valueAnimatorOfInt != null) {
                AnimatorSet animatorSet = new AnimatorSet();
                Animator[] animatorArr = new Animator[2];
                animatorArr[0] = valueAnimatorOfFloat;
                animatorArr[c] = valueAnimatorOfInt;
                animatorSet.playSequentially(animatorArr);
                animator = animatorSet;
            } else if (valueAnimatorOfFloat != null) {
                i8 = i7;
            } else {
                animator = valueAnimatorOfInt;
            }
            i8 = i7;
            final CharSequence charSequence6 = str2;
            final int i16 = i;
            final int i17 = iIntValue2;
            final CharSequence charSequence7 = charSequence;
            final int i18 = i3;
            final int i19 = i5;
            addListener(new TransitionListenerAdapter() { // from class: android.transition.ChangeText.6
                int mPausedColor = 0;

                @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                public void onTransitionPause(Transition transition) {
                    if (ChangeText.this.mChangeBehavior != 2) {
                        textView.setText(charSequence6);
                        TextView textView2 = textView;
                        if (textView2 instanceof EditText) {
                            ChangeText.this.setSelection((EditText) textView2, i16, i17);
                        }
                    }
                    if (ChangeText.this.mChangeBehavior > 0) {
                        this.mPausedColor = textView.getCurrentTextColor();
                        textView.setTextColor(i8);
                    }
                }

                @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                public void onTransitionResume(Transition transition) {
                    if (ChangeText.this.mChangeBehavior != 2) {
                        textView.setText(charSequence7);
                        TextView textView2 = textView;
                        if (textView2 instanceof EditText) {
                            ChangeText.this.setSelection((EditText) textView2, i18, i19);
                        }
                    }
                    if (ChangeText.this.mChangeBehavior > 0) {
                        textView.setTextColor(this.mPausedColor);
                    }
                }

                @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                }
            });
            return animator;
        }
        animator = valueAnimatorOfFloat;
        final CharSequence charSequence62 = str2;
        final int i162 = i;
        final int i172 = iIntValue2;
        final CharSequence charSequence72 = charSequence;
        final int i182 = i3;
        final int i192 = i5;
        addListener(new TransitionListenerAdapter() { // from class: android.transition.ChangeText.6
            int mPausedColor = 0;

            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition) {
                if (ChangeText.this.mChangeBehavior != 2) {
                    textView.setText(charSequence62);
                    TextView textView2 = textView;
                    if (textView2 instanceof EditText) {
                        ChangeText.this.setSelection((EditText) textView2, i162, i172);
                    }
                }
                if (ChangeText.this.mChangeBehavior > 0) {
                    this.mPausedColor = textView.getCurrentTextColor();
                    textView.setTextColor(i8);
                }
            }

            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition) {
                if (ChangeText.this.mChangeBehavior != 2) {
                    textView.setText(charSequence72);
                    TextView textView2 = textView;
                    if (textView2 instanceof EditText) {
                        ChangeText.this.setSelection((EditText) textView2, i182, i192);
                    }
                }
                if (ChangeText.this.mChangeBehavior > 0) {
                    textView.setTextColor(this.mPausedColor);
                }
            }

            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
            }
        });
        return animator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelection(EditText editText, int i, int i2) {
        if (i < 0 || i2 < 0) {
            return;
        }
        editText.setSelection(i, i2);
    }
}
