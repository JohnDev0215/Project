package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

/* JADX INFO: loaded from: classes6.dex */
class SpringAnimation extends AnimationDriver {
    private int mCurrentLoop;
    private final PhysicsState mCurrentState;
    private double mDisplacementFromRestThreshold;
    private double mEndValue;
    private double mInitialVelocity;
    private int mIterations;
    private long mLastTime;
    private double mOriginalValue;
    private boolean mOvershootClampingEnabled;
    private double mRestSpeedThreshold;
    private double mSpringDamping;
    private double mSpringMass;
    private boolean mSpringStarted;
    private double mSpringStiffness;
    private double mStartValue;
    private double mTimeAccumulator;

    private static class PhysicsState {
        double position;
        double velocity;

        private PhysicsState() {
        }
    }

    SpringAnimation(ReadableMap readableMap) {
        PhysicsState physicsState = new PhysicsState();
        this.mCurrentState = physicsState;
        physicsState.velocity = readableMap.getDouble("initialVelocity");
        resetConfig(readableMap);
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void resetConfig(ReadableMap readableMap) {
        this.mSpringStiffness = readableMap.getDouble("stiffness");
        this.mSpringDamping = readableMap.getDouble("damping");
        this.mSpringMass = readableMap.getDouble("mass");
        this.mInitialVelocity = this.mCurrentState.velocity;
        this.mEndValue = readableMap.getDouble("toValue");
        this.mRestSpeedThreshold = readableMap.getDouble("restSpeedThreshold");
        this.mDisplacementFromRestThreshold = readableMap.getDouble("restDisplacementThreshold");
        this.mOvershootClampingEnabled = readableMap.getBoolean("overshootClamping");
        int i = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        this.mIterations = i;
        this.mHasFinished = i == 0;
        this.mCurrentLoop = 0;
        this.mTimeAccumulator = 0.0d;
        this.mSpringStarted = false;
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void runAnimationStep(long j) {
        long j2 = j / 1000000;
        if (!this.mSpringStarted) {
            if (this.mCurrentLoop == 0) {
                this.mOriginalValue = this.mAnimatedValue.mValue;
                this.mCurrentLoop = 1;
            }
            PhysicsState physicsState = this.mCurrentState;
            double d = this.mAnimatedValue.mValue;
            physicsState.position = d;
            this.mStartValue = d;
            this.mLastTime = j2;
            this.mTimeAccumulator = 0.0d;
            this.mSpringStarted = true;
        }
        advance((j2 - this.mLastTime) / 1000.0d);
        this.mLastTime = j2;
        this.mAnimatedValue.mValue = this.mCurrentState.position;
        if (isAtRest()) {
            int i = this.mIterations;
            if (i == -1 || this.mCurrentLoop < i) {
                this.mSpringStarted = false;
                this.mAnimatedValue.mValue = this.mOriginalValue;
                this.mCurrentLoop++;
                return;
            }
            this.mHasFinished = true;
        }
    }

    private double getDisplacementDistanceForState(PhysicsState physicsState) {
        return Math.abs(this.mEndValue - physicsState.position);
    }

    private boolean isAtRest() {
        return Math.abs(this.mCurrentState.velocity) <= this.mRestSpeedThreshold && (getDisplacementDistanceForState(this.mCurrentState) <= this.mDisplacementFromRestThreshold || this.mSpringStiffness == 0.0d);
    }

    private boolean isOvershooting() {
        if (this.mSpringStiffness > 0.0d) {
            double d = this.mStartValue;
            double d2 = this.mEndValue;
            if ((d < d2 && this.mCurrentState.position > d2) || (d > d2 && this.mCurrentState.position < d2)) {
                return true;
            }
        }
        return false;
    }

    private void advance(double d) {
        double dSin;
        double dSin2;
        if (isAtRest()) {
            return;
        }
        this.mTimeAccumulator += d <= 0.064d ? d : 0.064d;
        double d2 = this.mSpringDamping;
        double d3 = this.mSpringMass;
        double d4 = this.mSpringStiffness;
        double d5 = -this.mInitialVelocity;
        double dSqrt = d2 / (Math.sqrt(d4 * d3) * 2.0d);
        double dSqrt2 = Math.sqrt(d4 / d3);
        double dSqrt3 = Math.sqrt(1.0d - (dSqrt * dSqrt)) * dSqrt2;
        double d6 = this.mEndValue - this.mStartValue;
        double d7 = this.mTimeAccumulator;
        if (dSqrt < 1.0d) {
            double dExp = Math.exp((-dSqrt) * dSqrt2 * d7);
            double d8 = dSqrt * dSqrt2;
            double d9 = d5 + (d8 * d6);
            double d10 = d7 * dSqrt3;
            dSin2 = this.mEndValue - ((((d9 / dSqrt3) * Math.sin(d10)) + (Math.cos(d10) * d6)) * dExp);
            dSin = ((d8 * dExp) * (((Math.sin(d10) * d9) / dSqrt3) + (Math.cos(d10) * d6))) - (((Math.cos(d10) * d9) - ((dSqrt3 * d6) * Math.sin(d10))) * dExp);
        } else {
            double dExp2 = Math.exp((-dSqrt2) * d7);
            double d11 = this.mEndValue - (((((dSqrt2 * d6) + d5) * d7) + d6) * dExp2);
            dSin = dExp2 * ((d5 * ((d7 * dSqrt2) - 1.0d)) + (d7 * d6 * dSqrt2 * dSqrt2));
            dSin2 = d11;
        }
        PhysicsState physicsState = this.mCurrentState;
        physicsState.position = dSin2;
        physicsState.velocity = dSin;
        if (isAtRest() || (this.mOvershootClampingEnabled && isOvershooting())) {
            if (this.mSpringStiffness > 0.0d) {
                double d12 = this.mEndValue;
                this.mStartValue = d12;
                this.mCurrentState.position = d12;
            } else {
                double d13 = this.mCurrentState.position;
                this.mEndValue = d13;
                this.mStartValue = d13;
            }
            this.mCurrentState.velocity = 0.0d;
        }
    }
}
