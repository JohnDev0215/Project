package androidx.constraintlayout.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class PriorityGoalRow extends ArrayRow {
    private static final boolean DEBUG = false;
    private static final float epsilon = 1.0E-4f;
    static Cache sCache = null;
    static RowVariable sRowVariable = new RowVariable();
    ArrayList<GoalVariable> goals;
    HashMap<Integer, GoalVariable> keyedGoals;
    Cache mCache;

    static class GoalVariable implements Comparable {
        float[] strengths = new float[8];
        int variableId;

        GoalVariable() {
        }

        public void reset() {
            Arrays.fill(this.strengths, 0.0f);
            this.variableId = -1;
        }

        public String toString() {
            return toString(PriorityGoalRow.sCache);
        }

        public String toString(Cache cache) {
            String result = "[ ";
            for (int i = 0; i < 8; i++) {
                result = result + this.strengths[i] + " ";
            }
            return result + "] " + cache.mIndexedVariables[this.variableId];
        }

        public void add(GoalVariable added) {
            for (int i = 0; i < 8; i++) {
                float[] fArr = this.strengths;
                fArr[i] = fArr[i] + added.strengths[i];
                float value = fArr[i];
                if (Math.abs(value) < PriorityGoalRow.epsilon) {
                    this.strengths[i] = 0.0f;
                }
            }
        }

        public final boolean isNegative() {
            for (int i = 7; i >= 0; i--) {
                float[] fArr = this.strengths;
                float value = fArr[i];
                if (value > 0.0f) {
                    return false;
                }
                if (fArr[i] < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isSmallerThan(GoalVariable variable) {
            for (int i = 7; i >= 0; i--) {
                float comparedValue = variable.strengths[i];
                float value = this.strengths[i];
                if (value != comparedValue) {
                    return value < comparedValue;
                }
            }
            return false;
        }

        public final boolean isNull() {
            for (int i = 0; i < 8; i++) {
                if (this.strengths[i] != 0.0f) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object o) {
            GoalVariable v = (GoalVariable) o;
            return this.variableId - v.variableId;
        }
    }

    static class RowVariable {
        float value;
        int variableId;

        RowVariable() {
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.Row
    public void clear() {
        int count = this.goals.size();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                GoalVariable v = this.goals.get(i);
                this.mCache.goalVariablePool.release(v);
            }
        }
        this.goals.clear();
        this.keyedGoals.clear();
        this.constantValue = 0.0f;
    }

    public PriorityGoalRow(Cache cache) {
        super(cache);
        this.goals = new ArrayList<>();
        this.keyedGoals = new HashMap<>();
        this.mCache = cache;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.Row
    public SolverVariable getPivotCandidate(LinearSystem system, boolean[] avoid) {
        sCache = this.mCache;
        GoalVariable pivot = null;
        int count = this.goals.size();
        for (int i = 0; i < count; i++) {
            GoalVariable v = this.goals.get(i);
            if (!avoid[v.variableId]) {
                if (pivot == null) {
                    if (v.isNegative()) {
                        pivot = v;
                    }
                } else if (v.isSmallerThan(pivot)) {
                    pivot = v;
                }
            }
        }
        if (pivot == null) {
            return null;
        }
        return this.mCache.mIndexedVariables[pivot.variableId];
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.Row
    public void addError(SolverVariable error) {
        GoalVariable goalVariable = this.mCache.goalVariablePool.acquire();
        if (goalVariable == null) {
            goalVariable = new GoalVariable();
        } else {
            goalVariable.reset();
        }
        goalVariable.strengths[error.strength] = 1.0f;
        goalVariable.variableId = error.id;
        this.goals.add(goalVariable);
        this.keyedGoals.put(Integer.valueOf(goalVariable.variableId), goalVariable);
        error.addToRow(this);
    }

    final GoalVariable create(GoalVariable key, SolverVariable error, float value) {
        GoalVariable goalVariable = new GoalVariable();
        for (int i = 0; i < 8; i++) {
            float strength = key.strengths[i];
            if (strength != 0.0f) {
                float v = value * strength;
                if (Math.abs(v) < epsilon) {
                    v = 0.0f;
                }
                goalVariable.strengths[i] = v;
            }
        }
        int i2 = error.id;
        goalVariable.variableId = i2;
        return goalVariable;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.Row
    public void updateFromRow(ArrayRow definition, boolean removeFromDefinition) {
        GoalVariable goalVariable = this.keyedGoals.get(Integer.valueOf(definition.variable.id));
        if (goalVariable == null) {
            return;
        }
        this.goals.remove(goalVariable);
        this.keyedGoals.remove(Integer.valueOf(goalVariable.variableId));
        this.mCache.goalVariablePool.release(goalVariable);
        int current = definition.variables.getHead();
        int currentSize = definition.variables.getCurrentSize();
        while (current != -1 && 0 < currentSize) {
            current = definition.variables.getVariable(sRowVariable, current);
            RowVariable v = sRowVariable;
            GoalVariable existing = find(v.variableId);
            float value = v.value;
            SolverVariable solverVariable = this.mCache.mIndexedVariables[v.variableId];
            if (existing == null) {
                GoalVariable added = create(goalVariable, solverVariable, value);
                this.goals.add(added);
                this.keyedGoals.put(Integer.valueOf(added.variableId), added);
                solverVariable.addToRow(this);
            } else {
                existing.add(create(goalVariable, solverVariable, value));
                if (existing.isNull()) {
                    this.goals.remove(existing);
                    this.keyedGoals.remove(Integer.valueOf(existing.variableId));
                    this.mCache.goalVariablePool.release(existing);
                    solverVariable.removeFromRow(this);
                }
            }
            this.constantValue += definition.constantValue * value;
        }
    }

    final GoalVariable find(int id) {
        return this.keyedGoals.get(Integer.valueOf(id));
    }

    @Override // androidx.constraintlayout.solver.ArrayRow
    public String toString() {
        Collections.sort(this.goals);
        String result = " goal -> (" + this.constantValue + ") : ";
        for (GoalVariable v : this.goals) {
            result = result + v.toString(this.mCache) + " ";
        }
        return result;
    }
}
