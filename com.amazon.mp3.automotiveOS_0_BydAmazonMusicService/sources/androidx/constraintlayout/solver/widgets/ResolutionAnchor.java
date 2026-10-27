package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;

/* JADX INFO: loaded from: classes.dex */
public class ResolutionAnchor extends ResolutionNode {
    public static final int BARRIER_CONNECTION = 5;
    public static final int CENTER_CONNECTION = 2;
    public static final int CHAIN_CONNECTION = 4;
    public static final int DIRECT_CONNECTION = 1;
    public static final int MATCH_CONNECTION = 3;
    public static final int UNCONNECTED = 0;
    float computedValue;
    ConstraintAnchor myAnchor;
    float offset;
    private ResolutionAnchor opposite;
    private float oppositeOffset;
    float resolvedOffset;
    ResolutionAnchor resolvedTarget;
    ResolutionAnchor target;
    int type = 0;
    private ResolutionDimension dimension = null;
    private int dimensionMultiplier = 1;
    private ResolutionDimension oppositeDimension = null;
    private int oppositeDimensionMultiplier = 1;

    String sType(int i) {
        return i == 1 ? "DIRECT" : i == 2 ? "CENTER" : i == 3 ? "MATCH" : i == 4 ? "CHAIN" : i == 5 ? "BARRIER" : "UNCONNECTED";
    }

    public ResolutionAnchor(ConstraintAnchor constraintAnchor) {
        this.myAnchor = constraintAnchor;
    }

    @Override // androidx.constraintlayout.solver.widgets.ResolutionNode
    public void remove(ResolutionDimension resolutionDimension) {
        ResolutionDimension resolutionDimension2 = this.dimension;
        if (resolutionDimension2 == resolutionDimension) {
            this.dimension = null;
            this.offset = this.dimensionMultiplier;
        } else if (resolutionDimension2 == this.oppositeDimension) {
            this.oppositeDimension = null;
            this.oppositeOffset = this.oppositeDimensionMultiplier;
        }
        resolve();
    }

    public String toString() {
        if (this.state == 1) {
            if (this.resolvedTarget == this) {
                return "[" + this.myAnchor + ", RESOLVED: " + this.resolvedOffset + "]  type: " + sType(this.type);
            }
            return "[" + this.myAnchor + ", RESOLVED: " + this.resolvedTarget + ":" + this.resolvedOffset + "] type: " + sType(this.type);
        }
        return "{ " + this.myAnchor + " UNRESOLVED} type: " + sType(this.type);
    }

    public void resolve(ResolutionAnchor resolutionAnchor, float f) {
        int i = this.state;
        if (i == 0 || !(this.resolvedTarget == resolutionAnchor || this.resolvedOffset == f)) {
            this.resolvedTarget = resolutionAnchor;
            this.resolvedOffset = f;
            if (i == 1) {
                invalidate();
            }
            didResolve();
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ResolutionNode
    public void resolve() {
        int i;
        ResolutionAnchor resolutionAnchor;
        ResolutionAnchor resolutionAnchor2;
        ResolutionAnchor resolutionAnchor3;
        ResolutionAnchor resolutionAnchor4;
        ResolutionAnchor resolutionAnchor5;
        ResolutionAnchor resolutionAnchor6;
        float f;
        float width;
        float f2;
        ResolutionAnchor resolutionAnchor7;
        boolean z = true;
        if (this.state == 1 || (i = this.type) == 4) {
            return;
        }
        ResolutionDimension resolutionDimension = this.dimension;
        if (resolutionDimension != null) {
            if (resolutionDimension.state != 1) {
                return;
            } else {
                this.offset = this.dimensionMultiplier * resolutionDimension.value;
            }
        }
        ResolutionDimension resolutionDimension2 = this.oppositeDimension;
        if (resolutionDimension2 != null) {
            if (resolutionDimension2.state != 1) {
                return;
            } else {
                this.oppositeOffset = this.oppositeDimensionMultiplier * resolutionDimension2.value;
            }
        }
        if (i == 1 && ((resolutionAnchor7 = this.target) == null || resolutionAnchor7.state == 1)) {
            if (resolutionAnchor7 == null) {
                this.resolvedTarget = this;
                this.resolvedOffset = this.offset;
            } else {
                this.resolvedTarget = resolutionAnchor7.resolvedTarget;
                this.resolvedOffset = resolutionAnchor7.resolvedOffset + this.offset;
            }
            didResolve();
            return;
        }
        if (i != 2 || (resolutionAnchor4 = this.target) == null || resolutionAnchor4.state != 1 || (resolutionAnchor5 = this.opposite) == null || (resolutionAnchor6 = resolutionAnchor5.target) == null || resolutionAnchor6.state != 1) {
            if (i != 3 || (resolutionAnchor = this.target) == null || resolutionAnchor.state != 1 || (resolutionAnchor2 = this.opposite) == null || (resolutionAnchor3 = resolutionAnchor2.target) == null || resolutionAnchor3.state != 1) {
                if (i == 5) {
                    this.myAnchor.mOwner.resolve();
                    return;
                }
                return;
            }
            if (LinearSystem.getMetrics() != null) {
                LinearSystem.getMetrics().matchConnectionResolved++;
            }
            ResolutionAnchor resolutionAnchor8 = this.target;
            this.resolvedTarget = resolutionAnchor8.resolvedTarget;
            ResolutionAnchor resolutionAnchor9 = this.opposite;
            ResolutionAnchor resolutionAnchor10 = resolutionAnchor9.target;
            resolutionAnchor9.resolvedTarget = resolutionAnchor10.resolvedTarget;
            this.resolvedOffset = resolutionAnchor8.resolvedOffset + this.offset;
            resolutionAnchor9.resolvedOffset = resolutionAnchor10.resolvedOffset + resolutionAnchor9.offset;
            didResolve();
            this.opposite.didResolve();
            return;
        }
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().centerConnectionResolved++;
        }
        ResolutionAnchor resolutionAnchor11 = this.target;
        this.resolvedTarget = resolutionAnchor11.resolvedTarget;
        ResolutionAnchor resolutionAnchor12 = this.opposite;
        ResolutionAnchor resolutionAnchor13 = resolutionAnchor12.target;
        resolutionAnchor12.resolvedTarget = resolutionAnchor13.resolvedTarget;
        ConstraintAnchor.Type type = this.myAnchor.mType;
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
        int i2 = 0;
        if (type != type2 && type != ConstraintAnchor.Type.BOTTOM) {
            z = false;
        }
        if (z) {
            f = resolutionAnchor11.resolvedOffset - resolutionAnchor13.resolvedOffset;
        } else {
            f = resolutionAnchor13.resolvedOffset - resolutionAnchor11.resolvedOffset;
        }
        if (type == ConstraintAnchor.Type.LEFT || type == type2) {
            width = f - r2.mOwner.getWidth();
            f2 = this.myAnchor.mOwner.mHorizontalBiasPercent;
        } else {
            width = f - r2.mOwner.getHeight();
            f2 = this.myAnchor.mOwner.mVerticalBiasPercent;
        }
        int margin = this.myAnchor.getMargin();
        int margin2 = this.opposite.myAnchor.getMargin();
        if (this.myAnchor.getTarget() == this.opposite.myAnchor.getTarget()) {
            f2 = 0.5f;
            margin2 = 0;
        } else {
            i2 = margin;
        }
        float f3 = i2;
        float f4 = margin2;
        float f5 = (width - f3) - f4;
        if (z) {
            ResolutionAnchor resolutionAnchor14 = this.opposite;
            resolutionAnchor14.resolvedOffset = resolutionAnchor14.target.resolvedOffset + f4 + (f5 * f2);
            this.resolvedOffset = (this.target.resolvedOffset - f3) - (f5 * (1.0f - f2));
        } else {
            this.resolvedOffset = this.target.resolvedOffset + f3 + (f5 * f2);
            ResolutionAnchor resolutionAnchor15 = this.opposite;
            resolutionAnchor15.resolvedOffset = (resolutionAnchor15.target.resolvedOffset - f4) - (f5 * (1.0f - f2));
        }
        didResolve();
        this.opposite.didResolve();
    }

    public void setType(int i) {
        this.type = i;
    }

    @Override // androidx.constraintlayout.solver.widgets.ResolutionNode
    public void reset() {
        super.reset();
        this.target = null;
        this.offset = 0.0f;
        this.dimension = null;
        this.dimensionMultiplier = 1;
        this.oppositeDimension = null;
        this.oppositeDimensionMultiplier = 1;
        this.resolvedTarget = null;
        this.resolvedOffset = 0.0f;
        this.computedValue = 0.0f;
        this.opposite = null;
        this.oppositeOffset = 0.0f;
        this.type = 0;
    }

    public void update() {
        ConstraintAnchor target = this.myAnchor.getTarget();
        if (target == null) {
            return;
        }
        if (target.getTarget() == this.myAnchor) {
            this.type = 4;
            target.getResolutionNode().type = 4;
        }
        int margin = this.myAnchor.getMargin();
        ConstraintAnchor.Type type = this.myAnchor.mType;
        if (type == ConstraintAnchor.Type.RIGHT || type == ConstraintAnchor.Type.BOTTOM) {
            margin = -margin;
        }
        dependsOn(target.getResolutionNode(), margin);
    }

    public void dependsOn(int i, ResolutionAnchor resolutionAnchor, int i2) {
        this.type = i;
        this.target = resolutionAnchor;
        this.offset = i2;
        resolutionAnchor.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i) {
        this.target = resolutionAnchor;
        this.offset = i;
        resolutionAnchor.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i, ResolutionDimension resolutionDimension) {
        this.target = resolutionAnchor;
        resolutionAnchor.addDependent(this);
        this.dimension = resolutionDimension;
        this.dimensionMultiplier = i;
        resolutionDimension.addDependent(this);
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, float f) {
        this.opposite = resolutionAnchor;
        this.oppositeOffset = f;
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, int i, ResolutionDimension resolutionDimension) {
        this.opposite = resolutionAnchor;
        this.oppositeDimension = resolutionDimension;
        this.oppositeDimensionMultiplier = i;
    }

    void addResolvedValue(LinearSystem linearSystem) {
        SolverVariable solverVariable = this.myAnchor.getSolverVariable();
        ResolutionAnchor resolutionAnchor = this.resolvedTarget;
        if (resolutionAnchor == null) {
            linearSystem.addEquality(solverVariable, (int) (this.resolvedOffset + 0.5f));
        } else {
            linearSystem.addEquality(solverVariable, linearSystem.createObjectVariable(resolutionAnchor.myAnchor), (int) (this.resolvedOffset + 0.5f), 6);
        }
    }

    public float getResolvedValue() {
        return this.resolvedOffset;
    }
}
