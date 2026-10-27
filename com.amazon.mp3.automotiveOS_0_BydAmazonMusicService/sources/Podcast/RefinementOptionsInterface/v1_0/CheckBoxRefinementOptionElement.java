package Podcast.RefinementOptionsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CheckBoxRefinementOptionElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement");
    private String accessibilityLabel;
    private String defaultState;
    private String id;
    private List<Method> onApply;
    private List<Method> onDismiss;
    private List<Method> onItemSelected;
    private List<Method> onReset;
    private List<CheckBoxRefinementOptionItemElement> options;
    private String text;

    public static class Builder extends TemplateElement.Builder {
        protected String accessibilityLabel;
        protected String defaultState;
        protected String id;
        protected List<Method> onApply;
        protected List<Method> onDismiss;
        protected List<Method> onItemSelected;
        protected List<Method> onReset;
        protected List<CheckBoxRefinementOptionItemElement> options;
        protected String text;

        public CheckBoxRefinementOptionElement build() {
            CheckBoxRefinementOptionElement checkBoxRefinementOptionElement = new CheckBoxRefinementOptionElement();
            populate(checkBoxRefinementOptionElement);
            return checkBoxRefinementOptionElement;
        }

        protected void populate(CheckBoxRefinementOptionElement checkBoxRefinementOptionElement) {
            super.populate((TemplateElement) checkBoxRefinementOptionElement);
            checkBoxRefinementOptionElement.setOptions(this.options);
            checkBoxRefinementOptionElement.setDefaultState(this.defaultState);
            checkBoxRefinementOptionElement.setId(this.id);
            checkBoxRefinementOptionElement.setText(this.text);
            checkBoxRefinementOptionElement.setAccessibilityLabel(this.accessibilityLabel);
            checkBoxRefinementOptionElement.setOnReset(this.onReset);
            checkBoxRefinementOptionElement.setOnItemSelected(this.onItemSelected);
            checkBoxRefinementOptionElement.setOnDismiss(this.onDismiss);
            checkBoxRefinementOptionElement.setOnApply(this.onApply);
        }

        public Builder withAccessibilityLabel(String str) {
            this.accessibilityLabel = str;
            return this;
        }

        public Builder withDefaultState(String str) {
            this.defaultState = str;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withOnApply(List<Method> list) {
            this.onApply = list;
            return this;
        }

        public Builder withOnDismiss(List<Method> list) {
            this.onDismiss = list;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withOnReset(List<Method> list) {
            this.onReset = list;
            return this;
        }

        public Builder withOptions(List<CheckBoxRefinementOptionItemElement> list) {
            this.options = list;
            return this;
        }

        public Builder withText(String str) {
            this.text = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof CheckBoxRefinementOptionElement)) {
            return 1;
        }
        CheckBoxRefinementOptionElement checkBoxRefinementOptionElement = (CheckBoxRefinementOptionElement) sOAObject;
        List<CheckBoxRefinementOptionItemElement> options = getOptions();
        List<CheckBoxRefinementOptionItemElement> options2 = checkBoxRefinementOptionElement.getOptions();
        if (options != options2) {
            if (options == null) {
                return -1;
            }
            if (options2 == null) {
                return 1;
            }
            if (options instanceof Comparable) {
                int iCompareTo = ((Comparable) options).compareTo(options2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!options.equals(options2)) {
                int iHashCode = options.hashCode();
                int iHashCode2 = options2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String defaultState = getDefaultState();
        String defaultState2 = checkBoxRefinementOptionElement.getDefaultState();
        if (defaultState != defaultState2) {
            if (defaultState == null) {
                return -1;
            }
            if (defaultState2 == null) {
                return 1;
            }
            int iCompareTo2 = defaultState.compareTo(defaultState2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String id = getId();
        String id2 = checkBoxRefinementOptionElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo3 = id.compareTo(id2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String text = getText();
        String text2 = checkBoxRefinementOptionElement.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo4 = text.compareTo(text2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String accessibilityLabel = getAccessibilityLabel();
        String accessibilityLabel2 = checkBoxRefinementOptionElement.getAccessibilityLabel();
        if (accessibilityLabel != accessibilityLabel2) {
            if (accessibilityLabel == null) {
                return -1;
            }
            if (accessibilityLabel2 == null) {
                return 1;
            }
            int iCompareTo5 = accessibilityLabel.compareTo(accessibilityLabel2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<Method> onReset = getOnReset();
        List<Method> onReset2 = checkBoxRefinementOptionElement.getOnReset();
        if (onReset != onReset2) {
            if (onReset == null) {
                return -1;
            }
            if (onReset2 == null) {
                return 1;
            }
            if (onReset instanceof Comparable) {
                int iCompareTo6 = ((Comparable) onReset).compareTo(onReset2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!onReset.equals(onReset2)) {
                int iHashCode3 = onReset.hashCode();
                int iHashCode4 = onReset2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = checkBoxRefinementOptionElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!onItemSelected.equals(onItemSelected2)) {
                int iHashCode5 = onItemSelected.hashCode();
                int iHashCode6 = onItemSelected2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<Method> onDismiss = getOnDismiss();
        List<Method> onDismiss2 = checkBoxRefinementOptionElement.getOnDismiss();
        if (onDismiss != onDismiss2) {
            if (onDismiss == null) {
                return -1;
            }
            if (onDismiss2 == null) {
                return 1;
            }
            if (onDismiss instanceof Comparable) {
                int iCompareTo8 = ((Comparable) onDismiss).compareTo(onDismiss2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!onDismiss.equals(onDismiss2)) {
                int iHashCode7 = onDismiss.hashCode();
                int iHashCode8 = onDismiss2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        List<Method> onApply = getOnApply();
        List<Method> onApply2 = checkBoxRefinementOptionElement.getOnApply();
        if (onApply != onApply2) {
            if (onApply == null) {
                return -1;
            }
            if (onApply2 == null) {
                return 1;
            }
            if (onApply instanceof Comparable) {
                int iCompareTo9 = ((Comparable) onApply).compareTo(onApply2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!onApply.equals(onApply2)) {
                int iHashCode9 = onApply.hashCode();
                int iHashCode10 = onApply2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof CheckBoxRefinementOptionElement)) {
            return false;
        }
        CheckBoxRefinementOptionElement checkBoxRefinementOptionElement = (CheckBoxRefinementOptionElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOptions(), checkBoxRefinementOptionElement.getOptions()) && internalEqualityCheck(getDefaultState(), checkBoxRefinementOptionElement.getDefaultState()) && internalEqualityCheck(getId(), checkBoxRefinementOptionElement.getId()) && internalEqualityCheck(getText(), checkBoxRefinementOptionElement.getText()) && internalEqualityCheck(getAccessibilityLabel(), checkBoxRefinementOptionElement.getAccessibilityLabel()) && internalEqualityCheck(getOnReset(), checkBoxRefinementOptionElement.getOnReset()) && internalEqualityCheck(getOnItemSelected(), checkBoxRefinementOptionElement.getOnItemSelected()) && internalEqualityCheck(getOnDismiss(), checkBoxRefinementOptionElement.getOnDismiss()) && internalEqualityCheck(getOnApply(), checkBoxRefinementOptionElement.getOnApply());
    }

    public String getAccessibilityLabel() {
        return this.accessibilityLabel;
    }

    public String getDefaultState() {
        return this.defaultState;
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnApply() {
        return this.onApply;
    }

    public List<Method> getOnDismiss() {
        return this.onDismiss;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public List<Method> getOnReset() {
        return this.onReset;
    }

    public List<CheckBoxRefinementOptionItemElement> getOptions() {
        return this.options;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOptions(), getDefaultState(), getId(), getText(), getAccessibilityLabel(), getOnReset(), getOnItemSelected(), getOnDismiss(), getOnApply());
    }

    public void setAccessibilityLabel(String str) {
        this.accessibilityLabel = str;
    }

    public void setDefaultState(String str) {
        this.defaultState = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnApply(List<Method> list) {
        this.onApply = list;
    }

    public void setOnDismiss(List<Method> list) {
        this.onDismiss = list;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setOnReset(List<Method> list) {
        this.onReset = list;
    }

    public void setOptions(List<CheckBoxRefinementOptionItemElement> list) {
        this.options = list;
    }

    public void setText(String str) {
        this.text = str;
    }
}
