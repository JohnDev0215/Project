package lombok.javac.handlers;

import android.util.proto.ProtoStream;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ConfigurationKeys;
import lombok.Singular;
import lombok.core.AST;
import lombok.core.AnnotationValues;
import lombok.core.HandlerPriority;
import lombok.core.configuration.CheckerFrameworkVersion;
import lombok.core.handlers.HandlerUtil;
import lombok.experimental.SuperBuilder;
import lombok.javac.Javac;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import lombok.javac.handlers.HandleBuilder;
import lombok.javac.handlers.JavacHandlerUtil;
import lombok.javac.handlers.JavacSingularsRecipes;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/javac/handlers/HandleSuperBuilder.SCL.lombok */
@HandlerPriority(-1024)
public class HandleSuperBuilder extends JavacAnnotationHandler<SuperBuilder> {
    private static final String SELF_METHOD = "self";
    private static final String FILL_VALUES_METHOD_NAME = "$fillValuesFrom";
    private static final String STATIC_FILL_VALUES_METHOD_NAME = "$fillValuesFromInstanceIntoBuilder";
    private static final String INSTANCE_VARIABLE_NAME = "instance";
    private static final String BUILDER_VARIABLE_NAME = "b";
    private static /* synthetic */ int[] $SWITCH_TABLE$lombok$javac$handlers$JavacHandlerUtil$MemberExistsResult;

    static /* synthetic */ int[] $SWITCH_TABLE$lombok$javac$handlers$JavacHandlerUtil$MemberExistsResult() {
        int[] iArr = $SWITCH_TABLE$lombok$javac$handlers$JavacHandlerUtil$MemberExistsResult;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[JavacHandlerUtil.MemberExistsResult.valuesCustom().length];
        try {
            iArr2[JavacHandlerUtil.MemberExistsResult.EXISTS_BY_LOMBOK.ordinal()] = 2;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[JavacHandlerUtil.MemberExistsResult.EXISTS_BY_USER.ordinal()] = 3;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[JavacHandlerUtil.MemberExistsResult.NOT_EXISTS.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        $SWITCH_TABLE$lombok$javac$handlers$JavacHandlerUtil$MemberExistsResult = iArr2;
        return iArr2;
    }

    /* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/javac/handlers/HandleSuperBuilder$SuperBuilderJob.SCL.lombok */
    class SuperBuilderJob extends HandleBuilder.BuilderJob {
        JavacNode builderAbstractType;
        String builderAbstractClassName;
        JavacNode builderImplType;
        String builderImplClassName;
        List<JCTree.JCTypeParameter> builderTypeParams_;

        SuperBuilderJob() {
        }

        void init(AnnotationValues<SuperBuilder> annValues, SuperBuilder ann, JavacNode node) {
            AccessLevel accessLevel = AccessLevel.PUBLIC;
            this.accessInners = accessLevel;
            this.accessOuters = accessLevel;
            this.oldFluent = true;
            this.oldChain = true;
            this.builderMethodName = ann.builderMethodName();
            this.buildMethodName = ann.buildMethodName();
            this.toBuilder = ann.toBuilder();
            if (this.builderMethodName == null) {
                this.builderMethodName = "builder";
            }
            if (this.buildMethodName == null) {
                this.buildMethodName = "build";
            }
            this.builderClassName = getBuilderClassNameTemplate(node, null);
        }

        void setBuilderToImpl() {
            this.builderType = this.builderImplType;
            this.builderClassName = this.builderImplClassName;
            this.builderTypeParams = this.typeParams;
        }

        void setBuilderToAbstract() {
            this.builderType = this.builderAbstractType;
            this.builderClassName = this.builderAbstractClassName;
            this.builderTypeParams = this.builderTypeParams_;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x04d5, code lost:
    
        r2 = r0.replaceBuilderClassName(r0.name);
        r0.builderClassName = r2;
        r0.builderAbstractClassName = r2;
        r0.builderImplClassName = java.lang.String.valueOf(r0.builderAbstractClassName) + "Impl";
        r0.builderAbstractType = findInnerClass(r0, r0.builderClassName);
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x051c, code lost:
    
        if (r0.builderAbstractType != null) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x051f, code lost:
    
        r0.builderAbstractType = generateBuilderAbstractClass(r0, r27, r17, r0, r0);
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0.builderAbstractType.get(), r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0545, code lost:
    
        r0 = r0.builderAbstractType.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0562, code lost:
    
        if (r0.getModifiers().getFlags().contains(javax.lang.model.element.Modifier.STATIC) == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0575, code lost:
    
        if (r0.getModifiers().getFlags().contains(javax.lang.model.element.Modifier.ABSTRACT) != false) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0578, code lost:
    
        r11.addError("Existing Builder must be an abstract static inner class.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x057f, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0580, code lost:
    
        lombok.javac.handlers.JavacHandlerUtil.sanityCheckForMethodGeneratingAnnotationsOnBuilderClass(r0.builderAbstractType, r11);
        r0 = r0.builderFields.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0598, code lost:
    
        r0 = r0.next();
        r0 = r0.singularData;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x05ad, code lost:
    
        if (r0 != null) goto L240;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x05b3, code lost:
    
        r0 = r0.getSingularizer();
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x05bc, code lost:
    
        if (r0 != null) goto L241;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x05ce, code lost:
    
        if (r0.checkForAlreadyExistingNodesAndGenerateError(r0.builderAbstractType, r0) == false) goto L246;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x05d1, code lost:
    
        r0.singularData = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x05de, code lost:
    
        if (r0.hasNext() != false) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x05e1, code lost:
    
        r0.setBuilderToAbstract();
        generateBuilderFields(r0.builderType, r0.builderFields, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x05f7, code lost:
    
        if (r18 == false) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x05fa, code lost:
    
        r0 = r0.VarDef(r0.Modifiers(2), r0.toName("$lombokUnclean"), r0.TypeIdent(lombok.javac.Javac.CTC_BOOLEAN), null);
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectFieldAndMarkGenerated(r0.builderType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0631, code lost:
    
        if (r0.toBuilder == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0639, code lost:
    
        if (r27 == null) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x063c, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0640, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0641, code lost:
    
        r0 = generateFillValuesMethod(r0, r2, r0, r0);
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.builderType, r0);
        r0 = generateStaticFillValuesMethod(r0, r0.setterPrefix());
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.builderType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0680, code lost:
    
        if (r27 == null) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0683, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0687, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0688, code lost:
    
        r0 = generateAbstractSelfMethod(r0, r2, r0);
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.builderType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x06a5, code lost:
    
        if (r27 == null) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x06a8, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x06ac, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x06ad, code lost:
    
        r0 = generateAbstractBuildMethod(r0, r2, r0);
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.builderType, r0);
        r0 = r0.builderFields.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x06d4, code lost:
    
        generateSetterMethodsForBuilder(r0, r0.next(), r0, r0.setterPrefix());
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x06f8, code lost:
    
        if (r0.hasNext() != false) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x06fb, code lost:
    
        r0 = new java.util.ArrayList<>();
        r0 = r0.builderFields.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0713, code lost:
    
        r0 = r0.next().createdFields.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x072e, code lost:
    
        r0 = r0.next();
        r0.add(new lombok.core.handlers.InclusionExclusionUtils.Included<>(r0, null, true, false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0755, code lost:
    
        if (r0.hasNext() != false) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x075f, code lost:
    
        if (r0.hasNext() != false) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0762, code lost:
    
        r0 = r0.builderType;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x076c, code lost:
    
        if (r27 == null) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x076f, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0773, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0774, code lost:
    
        r0 = lombok.javac.handlers.HandleToString.createToString(r0, r0, true, r3, lombok.core.handlers.HandlerUtil.FieldAccess.ALWAYS_FIELD, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x077f, code lost:
    
        if (r0 == null) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0782, code lost:
    
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.builderType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x078e, code lost:
    
        if (r18 == false) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0791, code lost:
    
        r0 = generateCleanMethod(r0.builderFields, r0.builderType, r11);
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.builderType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x07c1, code lost:
    
        if ((r0.mods.flags & 1024) == 0) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x07c4, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x07c8, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x07c9, code lost:
    
        r32 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x07cd, code lost:
    
        if (r32 != false) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x07d0, code lost:
    
        r0.builderImplType = findInnerClass(r0, r0.builderImplClassName);
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x07e5, code lost:
    
        if (r0.builderImplType != null) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x07e8, code lost:
    
        r0.builderImplType = generateBuilderImplClass(r0);
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0.builderImplType.get(), r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0806, code lost:
    
        r0 = r0.builderImplType.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0823, code lost:
    
        if (r0.getModifiers().getFlags().contains(javax.lang.model.element.Modifier.STATIC) == false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0836, code lost:
    
        if (r0.getModifiers().getFlags().contains(javax.lang.model.element.Modifier.ABSTRACT) == false) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x0839, code lost:
    
        r11.addError("Existing BuilderImpl must be a non-abstract static inner class.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0840, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0841, code lost:
    
        lombok.javac.handlers.JavacHandlerUtil.sanityCheckForMethodGeneratingAnnotationsOnBuilderClass(r0.builderImplType, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x084a, code lost:
    
        r0 = lombok.javac.handlers.HandleConstructor.createConstructor(lombok.AccessLevel.PRIVATE, com.sun.tools.javac.util.List.nil(), r0.builderImplType, com.sun.tools.javac.util.List.nil(), false, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0861, code lost:
    
        if (r0 == null) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0864, code lost:
    
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.builderImplType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x086e, code lost:
    
        r0.setBuilderToImpl();
        r0 = generateSelfMethod(r0);
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.builderType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x089d, code lost:
    
        if (lombok.javac.handlers.JavacHandlerUtil.methodExists(r0.buildMethodName, r0.builderType, -1) != lombok.javac.handlers.JavacHandlerUtil.MemberExistsResult.NOT_EXISTS) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x08a0, code lost:
    
        r0 = generateBuildMethod(r0, r0);
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.builderType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x08c9, code lost:
    
        if (constructorExists(r0.parentType, r0.builderAbstractClassName) != false) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x08cc, code lost:
    
        r0.setBuilderToAbstract();
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x08d6, code lost:
    
        if (r27 == null) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x08d9, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x08dd, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x08de, code lost:
    
        generateBuilderBasedConstructor(r0, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x08e3, code lost:
    
        if (r32 != false) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x08e8, code lost:
    
        if (r14 == false) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x08fc, code lost:
    
        if (lombok.javac.handlers.JavacHandlerUtil.methodExists(r0.builderMethodName, r0.parentType, -1) == lombok.javac.handlers.JavacHandlerUtil.MemberExistsResult.NOT_EXISTS) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x08ff, code lost:
    
        r14 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0904, code lost:
    
        if (r14 == false) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0907, code lost:
    
        r0 = generateBuilderMethod(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x0911, code lost:
    
        if (r0 == null) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x0914, code lost:
    
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.parentType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x092a, code lost:
    
        if (r0.toBuilder == false) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0940, code lost:
    
        switch($SWITCH_TABLE$lombok$javac$handlers$JavacHandlerUtil$MemberExistsResult()[lombok.javac.handlers.JavacHandlerUtil.methodExists("toBuilder", r0.parentType, 0).ordinal()]) {
            case 1: goto L206;
            case 2: goto L209;
            case 3: goto L209;
            default: goto L209;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x095f, code lost:
    
        r0 = generateToBuilderMethod(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0969, code lost:
    
        if (r0 == null) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x096c, code lost:
    
        lombok.javac.handlers.JavacHandlerUtil.recursiveSetGeneratedBy(r0, r11);
        lombok.javac.handlers.JavacHandlerUtil.injectMethod(r0.parentType, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x097f, code lost:
    
        if (r20 == null) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0984, code lost:
    
        if (r14 == false) goto L248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x0987, code lost:
    
        r0 = r20.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x0991, code lost:
    
        r0.next().addWarning("@SuperBuilder will ignore the initializing expression entirely. If you want the initializing expression to serve as default, add @Builder.Default. If it is not supposed to be settable during building, make the field final.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x09ac, code lost:
    
        if (r0.hasNext() != false) goto L214;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x09af, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:?, code lost:
    
        return;
     */
    @Override // lombok.javac.JavacAnnotationHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handle(lombok.core.AnnotationValues<lombok.experimental.SuperBuilder> r9, com.sun.tools.javac.tree.JCTree.JCAnnotation r10, lombok.javac.JavacNode r11) {
        /*
            Method dump skipped, instruction units count: 2480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.javac.handlers.HandleSuperBuilder.handle(lombok.core.AnnotationValues, com.sun.tools.javac.tree.JCTree$JCAnnotation, lombok.javac.JavacNode):void");
    }

    private JavacNode generateBuilderAbstractClass(SuperBuilderJob job, JCTree.JCExpression superclassBuilderClass, List<JCTree.JCExpression> superclassTypeParams, String classGenericName, String builderGenericName) {
        JavacTreeMaker maker = job.parentType.getTreeMaker();
        JCTree.JCModifiers mods = maker.Modifiers(1033L);
        ListBuffer<JCTree.JCTypeParameter> allTypeParams = new ListBuffer<>();
        allTypeParams.appendList(JavacHandlerUtil.copyTypeParams(job.sourceNode, job.typeParams));
        JCTree.JCExpression annotatedClass = JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.typeParams);
        allTypeParams.append(maker.TypeParameter(job.toName(classGenericName), List.of(annotatedClass)));
        Name builderClassName = job.toName(job.builderClassName);
        ListBuffer<JCTree.JCExpression> typeParamsForBuilder = getTypeParamExpressions(job.typeParams, maker, job.sourceNode);
        typeParamsForBuilder.append(maker.Ident(job.toName(classGenericName)));
        typeParamsForBuilder.append(maker.Ident(job.toName(builderGenericName)));
        JCTree.JCTypeApply typeApply = maker.TypeApply(JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, builderClassName, false, List.nil()), typeParamsForBuilder.toList());
        allTypeParams.append(maker.TypeParameter(job.toName(builderGenericName), List.of(typeApply)));
        JCTree.JCTypeApply jCTypeApplyTypeApply = null;
        if (superclassBuilderClass != null) {
            ListBuffer<JCTree.JCExpression> typeParamsForBuilder2 = getTypeParamExpressions(superclassTypeParams, maker, job.sourceNode);
            typeParamsForBuilder2.append(maker.Ident(job.toName(classGenericName)));
            typeParamsForBuilder2.append(maker.Ident(job.toName(builderGenericName)));
            jCTypeApplyTypeApply = maker.TypeApply(superclassBuilderClass, typeParamsForBuilder2.toList());
        }
        JCTree.JCClassDecl builder = maker.ClassDef(mods, builderClassName, allTypeParams.toList(), jCTypeApplyTypeApply, List.nil(), List.nil());
        JavacHandlerUtil.recursiveSetGeneratedBy(builder, job.sourceNode);
        return JavacHandlerUtil.injectType(job.parentType, builder);
    }

    private JavacNode generateBuilderImplClass(SuperBuilderJob job) {
        JavacTreeMaker maker = job.getTreeMaker();
        JCTree.JCModifiers mods = maker.Modifiers(26L);
        JCTree.JCExpression extending = JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.toName(job.builderAbstractClassName), false, List.nil());
        JCTree.JCExpression annotatedClass = JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.typeParams);
        JCTree.JCExpression builderImplClassExpression = JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.toName(job.builderImplClassName), false, job.typeParams);
        ListBuffer<JCTree.JCExpression> typeParamsForBuilder = getTypeParamExpressions(job.typeParams, maker, job.sourceNode);
        typeParamsForBuilder.append(annotatedClass);
        typeParamsForBuilder.append(builderImplClassExpression);
        JCTree.JCClassDecl builder = maker.ClassDef(mods, job.toName(job.builderImplClassName), JavacHandlerUtil.copyTypeParams(job.parentType, job.typeParams), maker.TypeApply(extending, typeParamsForBuilder.toList()), List.nil(), List.nil());
        JavacHandlerUtil.recursiveSetGeneratedBy(builder, job.sourceNode);
        return JavacHandlerUtil.injectType(job.parentType, builder);
    }

    private void generateBuilderBasedConstructor(SuperBuilderJob job, boolean callBuilderBasedSuperConstructor) {
        JCTree.JCIdent jCIdentSelect;
        JCTree.JCStatement nullCheck;
        JavacTreeMaker maker = job.getTreeMaker();
        AccessLevel level = AccessLevel.PROTECTED;
        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        Name builderVariableName = job.toName("b");
        for (HandleBuilder.BuilderFieldData bfd : job.builderFields) {
            if (bfd.singularData != null && bfd.singularData.getSingularizer() != null) {
                bfd.singularData.getSingularizer().appendBuildCode(bfd.singularData, bfd.originalFieldNode, job.sourceNode, statements, bfd.builderFieldName, "b");
                jCIdentSelect = maker.Ident(bfd.singularData.getPluralName());
            } else {
                jCIdentSelect = maker.Select(maker.Ident(builderVariableName), bfd.builderFieldName);
            }
            JCTree.JCFieldAccess fieldInThis = maker.Select(maker.Ident(job.toName("this")), bfd.rawName);
            JCTree.JCExpressionStatement jCExpressionStatementExec = maker.Exec(maker.Assign(fieldInThis, jCIdentSelect));
            if (bfd.nameOfSetFlag != null) {
                JCTree.JCFieldAccess setField = maker.Select(maker.Ident(builderVariableName), bfd.nameOfSetFlag);
                JCTree.JCFieldAccess fieldInThis2 = maker.Select(maker.Ident(job.toName("this")), bfd.rawName);
                JCTree.JCExpression parentTypeRef = JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, List.nil());
                JCTree.JCAssign assignDefault = maker.Assign(fieldInThis2, maker.Apply(JavacHandlerUtil.typeParameterNames(maker, job.parentType.get().typarams), maker.Select(parentTypeRef, bfd.nameOfDefaultProvider), List.nil()));
                statements.append(maker.If(setField, jCExpressionStatementExec, maker.Exec(assignDefault)));
            } else {
                statements.append(jCExpressionStatementExec);
            }
            if (JavacHandlerUtil.hasNonNullAnnotations(bfd.originalFieldNode) && (nullCheck = JavacHandlerUtil.generateNullCheck(maker, bfd.originalFieldNode, job.sourceNode)) != null) {
                statements.append(nullCheck);
            }
        }
        List<JCTree.JCAnnotation> annsOnMethod = job.checkerFramework.generateSideEffectFree() ? List.of(maker.Annotation(JavacHandlerUtil.genTypeRef(job.parentType, CheckerFrameworkVersion.NAME__SIDE_EFFECT_FREE), List.nil())) : List.nil();
        JCTree.JCModifiers mods = maker.Modifiers(JavacHandlerUtil.toJavacModifier(level), annsOnMethod);
        ListBuffer<JCTree.JCVariableDecl> params = new ListBuffer<>();
        long flags = JavacHandlerUtil.addFinalIfNeeded(ProtoStream.FIELD_TYPE_FLOAT, job.getContext());
        ListBuffer<JCTree.JCExpression> typeParamsForBuilderParameter = getTypeParamExpressions(job.typeParams, maker, job.sourceNode);
        JCTree.JCWildcard wildcard = maker.Wildcard(maker.TypeBoundKind(BoundKind.UNBOUND), null);
        typeParamsForBuilderParameter.append(wildcard);
        JCTree.JCWildcard wildcard2 = maker.Wildcard(maker.TypeBoundKind(BoundKind.UNBOUND), null);
        typeParamsForBuilderParameter.append(wildcard2);
        JCTree.JCTypeApply paramType = maker.TypeApply(JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.getBuilderClassName(), false, List.nil()), typeParamsForBuilderParameter.toList());
        JCTree.JCVariableDecl param = maker.VarDef(maker.Modifiers(flags), builderVariableName, paramType, null);
        params.append(param);
        if (callBuilderBasedSuperConstructor) {
            JCTree.JCMethodInvocation callToSuperConstructor = maker.Apply(List.nil(), maker.Ident(job.toName("super")), List.of(maker.Ident(builderVariableName)));
            statements.prepend(maker.Exec(callToSuperConstructor));
        }
        JCTree.JCMethodDecl constr = JavacHandlerUtil.recursiveSetGeneratedBy(maker.MethodDef(mods, job.toName("<init>"), null, List.nil(), params.toList(), List.nil(), maker.Block(0L, statements.toList()), null), job.sourceNode);
        JavacHandlerUtil.injectMethod(job.parentType, constr, null, Javac.createVoidType(job.builderType.getSymbolTable(), Javac.CTC_VOID));
    }

    private JCTree.JCMethodDecl generateBuilderMethod(SuperBuilderJob job) {
        JavacTreeMaker maker = job.getTreeMaker();
        JCTree.JCBlock body = maker.Block(0L, List.of(maker.Return(maker.NewClass(null, List.nil(), JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.toName(job.builderImplClassName), false, job.typeParams), List.nil(), null))));
        int modifiers = 1 | 8;
        ListBuffer<JCTree.JCExpression> typeParameterNames = new ListBuffer<>();
        typeParameterNames.appendList(JavacHandlerUtil.typeParameterNames(maker, job.typeParams));
        JCTree.JCWildcard wildcard = maker.Wildcard(maker.TypeBoundKind(BoundKind.UNBOUND), null);
        typeParameterNames.append(wildcard);
        typeParameterNames.append(wildcard);
        List<JCTree.JCAnnotation> annsOnParamType = List.nil();
        if (job.checkerFramework.generateUnique()) {
            annsOnParamType = List.of(maker.Annotation(JavacHandlerUtil.genTypeRef(job.parentType, CheckerFrameworkVersion.NAME__UNIQUE), List.nil()));
        }
        JCTree.JCTypeApply returnType = maker.TypeApply(JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.toName(job.builderAbstractClassName), false, List.nil(), annsOnParamType), typeParameterNames.toList());
        List<JCTree.JCAnnotation> annsOnMethod = job.checkerFramework.generateSideEffectFree() ? List.of(maker.Annotation(JavacHandlerUtil.genTypeRef(job.parentType, CheckerFrameworkVersion.NAME__SIDE_EFFECT_FREE), List.nil())) : List.nil();
        JCTree.JCMethodDecl methodDef = maker.MethodDef(maker.Modifiers(modifiers, annsOnMethod), job.toName(job.builderMethodName), returnType, JavacHandlerUtil.copyTypeParams(job.sourceNode, job.typeParams), List.nil(), List.nil(), body, null);
        JavacHandlerUtil.createRelevantNonNullAnnotation(job.parentType, methodDef);
        return methodDef;
    }

    private JCTree.JCMethodDecl generateToBuilderMethod(SuperBuilderJob job) {
        JavacTreeMaker maker = job.getTreeMaker();
        JCTree.JCNewClass jCNewClassNewClass = maker.NewClass(null, List.nil(), JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.toName(job.builderImplClassName), false, job.typeParams), List.nil(), null);
        List<JCTree.JCExpression> methodArgs = List.of(maker.Ident(job.toName("this")));
        JCTree.JCMethodInvocation invokeFillMethod = maker.Apply(List.nil(), maker.Select(jCNewClassNewClass, job.toName(FILL_VALUES_METHOD_NAME)), methodArgs);
        JCTree.JCBlock body = maker.Block(0L, List.of(maker.Return(invokeFillMethod)));
        ListBuffer<JCTree.JCExpression> typeParameterNames = new ListBuffer<>();
        typeParameterNames.appendList(JavacHandlerUtil.typeParameterNames(maker, job.typeParams));
        JCTree.JCWildcard wildcard = maker.Wildcard(maker.TypeBoundKind(BoundKind.UNBOUND), null);
        typeParameterNames.append(wildcard);
        typeParameterNames.append(wildcard);
        JCTree.JCTypeApply returnType = maker.TypeApply(JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.toName(job.builderAbstractClassName), false, List.nil()), typeParameterNames.toList());
        List<JCTree.JCAnnotation> annsOnMethod = job.checkerFramework.generateSideEffectFree() ? List.of(maker.Annotation(JavacHandlerUtil.genTypeRef(job.parentType, CheckerFrameworkVersion.NAME__SIDE_EFFECT_FREE), List.nil())) : List.nil();
        JCTree.JCMethodDecl methodDef = maker.MethodDef(maker.Modifiers(1, annsOnMethod), job.toName("toBuilder"), returnType, List.nil(), List.nil(), List.nil(), body, null);
        JavacHandlerUtil.createRelevantNonNullAnnotation(job.parentType, methodDef);
        return methodDef;
    }

    private JCTree.JCMethodDecl generateFillValuesMethod(SuperBuilderJob job, boolean inherited, String builderGenericName, String classGenericName) {
        JavacTreeMaker maker = job.getTreeMaker();
        List<JCTree.JCAnnotation> annotations = List.nil();
        if (inherited) {
            JCTree.JCAnnotation overrideAnnotation = maker.Annotation(JavacHandlerUtil.genJavaLangTypeRef(job.builderType, "Override"), List.nil());
            annotations = List.of(overrideAnnotation);
        }
        JCTree.JCModifiers modifiers = maker.Modifiers(4L, annotations);
        Name name = job.toName(FILL_VALUES_METHOD_NAME);
        JCTree.JCIdent jCIdentIdent = maker.Ident(job.toName(builderGenericName));
        JCTree.JCVariableDecl param = maker.VarDef(maker.Modifiers(8589934608L), job.toName(INSTANCE_VARIABLE_NAME), maker.Ident(job.toName(classGenericName)), null);
        ListBuffer<JCTree.JCStatement> body = new ListBuffer<>();
        if (inherited) {
            JCTree.JCMethodInvocation callToSuper = maker.Apply(List.nil(), maker.Select(maker.Ident(job.toName("super")), name), List.of(maker.Ident(job.toName(INSTANCE_VARIABLE_NAME))));
            body.append(maker.Exec(callToSuper));
        }
        JCTree.JCExpression ref = JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.getBuilderClassName(), false, List.nil());
        JCTree.JCMethodInvocation callStaticFillValuesMethod = maker.Apply(List.nil(), maker.Select(ref, job.toName(STATIC_FILL_VALUES_METHOD_NAME)), List.of(maker.Ident(job.toName(INSTANCE_VARIABLE_NAME)), maker.Ident(job.toName("this"))));
        body.append(maker.Exec(callStaticFillValuesMethod));
        JCTree.JCReturn returnStatement = maker.Return(maker.Apply(List.nil(), maker.Ident(job.toName(SELF_METHOD)), List.nil()));
        body.append(returnStatement);
        JCTree.JCBlock bodyBlock = maker.Block(0L, body.toList());
        return maker.MethodDef(modifiers, name, jCIdentIdent, List.nil(), List.of(param), List.nil(), bodyBlock, null);
    }

    private JCTree.JCMethodDecl generateStaticFillValuesMethod(SuperBuilderJob job, String setterPrefix) {
        JavacTreeMaker maker = job.getTreeMaker();
        List<JCTree.JCAnnotation> annotations = List.nil();
        JCTree.JCModifiers modifiers = maker.Modifiers(10L, annotations);
        Name name = job.toName(STATIC_FILL_VALUES_METHOD_NAME);
        JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTreeTypeIdent = maker.TypeIdent(Javac.CTC_VOID);
        JCTree.JCVariableDecl paramInstance = maker.VarDef(maker.Modifiers(8589934608L), job.toName(INSTANCE_VARIABLE_NAME), JavacHandlerUtil.cloneSelfType(job.parentType), null);
        ListBuffer<JCTree.JCExpression> typeParamsForBuilderParameter = getTypeParamExpressions(job.typeParams, maker, job.sourceNode);
        JCTree.JCWildcard wildcard = maker.Wildcard(maker.TypeBoundKind(BoundKind.UNBOUND), null);
        typeParamsForBuilderParameter.append(wildcard);
        JCTree.JCWildcard wildcard2 = maker.Wildcard(maker.TypeBoundKind(BoundKind.UNBOUND), null);
        typeParamsForBuilderParameter.append(wildcard2);
        JCTree.JCTypeApply builderType = maker.TypeApply(JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, job.getBuilderClassName(), false, List.nil()), typeParamsForBuilderParameter.toList());
        JCTree.JCVariableDecl paramBuilder = maker.VarDef(maker.Modifiers(8589934608L), job.toName("b"), builderType, null);
        ListBuffer<JCTree.JCStatement> body = new ListBuffer<>();
        for (HandleBuilder.BuilderFieldData bfd : job.builderFields) {
            JCTree.JCExpressionStatement exec = createSetterCallWithInstanceValue(bfd, job, setterPrefix);
            body.append(exec);
        }
        JCTree.JCBlock bodyBlock = maker.Block(0L, body.toList());
        return maker.MethodDef(modifiers, name, jCPrimitiveTypeTreeTypeIdent, JavacHandlerUtil.copyTypeParams(job.builderType, job.typeParams), List.of(paramInstance, paramBuilder), List.nil(), bodyBlock, null);
    }

    private JCTree.JCExpressionStatement createSetterCallWithInstanceValue(HandleBuilder.BuilderFieldData bfd, SuperBuilderJob job, String setterPrefix) {
        JCTree.JCConditional jCConditionalConditional;
        JavacTreeMaker maker = job.getTreeMaker();
        JCTree.JCConditional[] jCConditionalArr = new JCTree.JCExpression[bfd.singularData == null ? 1 : 2];
        if (bfd.obtainVia == null || !bfd.obtainVia.field().isEmpty()) {
            for (int i = 0; i < jCConditionalArr.length; i++) {
                jCConditionalArr[i] = maker.Select(maker.Ident(job.toName(INSTANCE_VARIABLE_NAME)), bfd.obtainVia == null ? bfd.rawName : job.toName(bfd.obtainVia.field()));
            }
        } else if (bfd.obtainVia.isStatic()) {
            for (int i2 = 0; i2 < jCConditionalArr.length; i2++) {
                JCTree.JCExpression typeRef = JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.parentType, List.nil());
                jCConditionalArr[i2] = maker.Apply(List.nil(), maker.Select(typeRef, job.toName(bfd.obtainVia.method())), List.of(maker.Ident(job.toName(INSTANCE_VARIABLE_NAME))));
            }
        } else {
            for (int i3 = 0; i3 < jCConditionalArr.length; i3++) {
                jCConditionalArr[i3] = maker.Apply(List.nil(), maker.Select(maker.Ident(job.toName(INSTANCE_VARIABLE_NAME)), job.toName(bfd.obtainVia.method())), List.nil());
            }
        }
        if (bfd.singularData == null) {
            jCConditionalConditional = jCConditionalArr[0];
        } else {
            JCTree.JCBinary jCBinaryBinary = maker.Binary(Javac.CTC_EQUAL, jCConditionalArr[0], maker.Literal(Javac.CTC_BOT, null));
            JCTree.JCExpression emptyCollection = bfd.singularData.getSingularizer().getEmptyExpression(bfd.singularData.getTargetFqn(), maker, bfd.singularData, job.parentType, job.sourceNode);
            jCConditionalConditional = maker.Conditional(jCBinaryBinary, emptyCollection, jCConditionalArr[1]);
        }
        String setterName = HandlerUtil.buildAccessorName(setterPrefix, bfd.name.toString());
        JCTree.JCMethodInvocation apply = maker.Apply(List.nil(), maker.Select(maker.Ident(job.toName("b")), job.toName(setterName)), List.of(jCConditionalConditional));
        JCTree.JCExpressionStatement exec = maker.Exec(apply);
        return exec;
    }

    private JCTree.JCMethodDecl generateAbstractSelfMethod(SuperBuilderJob job, boolean override, String builderGenericName) {
        JavacTreeMaker maker = job.getTreeMaker();
        List<JCTree.JCAnnotation> annotations = List.nil();
        JCTree.JCAnnotation overrideAnnotation = override ? maker.Annotation(JavacHandlerUtil.genJavaLangTypeRef(job.builderType, "Override"), List.nil()) : null;
        JCTree.JCAnnotation rrAnnotation = job.checkerFramework.generateReturnsReceiver() ? maker.Annotation(JavacHandlerUtil.genTypeRef(job.builderType, CheckerFrameworkVersion.NAME__RETURNS_RECEIVER), List.nil()) : null;
        JCTree.JCAnnotation sefAnnotation = job.checkerFramework.generatePure() ? maker.Annotation(JavacHandlerUtil.genTypeRef(job.builderType, CheckerFrameworkVersion.NAME__PURE), List.nil()) : null;
        if (sefAnnotation != null) {
            annotations = annotations.prepend(sefAnnotation);
        }
        if (rrAnnotation != null) {
            annotations = annotations.prepend(rrAnnotation);
        }
        if (overrideAnnotation != null) {
            annotations = annotations.prepend(overrideAnnotation);
        }
        JCTree.JCModifiers modifiers = maker.Modifiers(1028L, annotations);
        Name name = job.toName(SELF_METHOD);
        return maker.MethodDef(modifiers, name, maker.Ident(job.toName(builderGenericName)), List.nil(), List.nil(), List.nil(), null, null);
    }

    private JCTree.JCMethodDecl generateSelfMethod(SuperBuilderJob job) {
        JavacTreeMaker maker = job.getTreeMaker();
        JCTree.JCAnnotation overrideAnnotation = maker.Annotation(JavacHandlerUtil.genJavaLangTypeRef(job.builderType, "Override"), List.nil());
        JCTree.JCAnnotation rrAnnotation = job.checkerFramework.generateReturnsReceiver() ? maker.Annotation(JavacHandlerUtil.genTypeRef(job.builderType, CheckerFrameworkVersion.NAME__RETURNS_RECEIVER), List.nil()) : null;
        JCTree.JCAnnotation sefAnnotation = job.checkerFramework.generatePure() ? maker.Annotation(JavacHandlerUtil.genTypeRef(job.builderType, CheckerFrameworkVersion.NAME__PURE), List.nil()) : null;
        List<JCTree.JCAnnotation> annsOnMethod = List.nil();
        if (sefAnnotation != null) {
            annsOnMethod = annsOnMethod.prepend(sefAnnotation);
        }
        if (rrAnnotation != null) {
            annsOnMethod = annsOnMethod.prepend(rrAnnotation);
        }
        JCTree.JCModifiers modifiers = maker.Modifiers(4L, annsOnMethod.prepend(overrideAnnotation));
        Name name = job.toName(SELF_METHOD);
        JCTree.JCExpression returnType = JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.builderType.up(), job.getBuilderClassName(), false, job.typeParams);
        JCTree.JCBlock body = maker.Block(0L, List.of(maker.Return(maker.Ident(job.toName("this")))));
        return maker.MethodDef(modifiers, name, returnType, List.nil(), List.nil(), List.nil(), body, null);
    }

    private JCTree.JCMethodDecl generateAbstractBuildMethod(SuperBuilderJob job, boolean override, String classGenericName) {
        JCTree.JCMethodDecl methodDef;
        JavacTreeMaker maker = job.getTreeMaker();
        List<JCTree.JCAnnotation> annotations = List.nil();
        if (override) {
            JCTree.JCAnnotation overrideAnnotation = maker.Annotation(JavacHandlerUtil.genJavaLangTypeRef(job.builderType, "Override"), List.nil());
            annotations = List.of(overrideAnnotation);
        }
        if (job.checkerFramework.generateSideEffectFree()) {
            annotations = annotations.prepend(maker.Annotation(JavacHandlerUtil.genTypeRef(job.builderType, CheckerFrameworkVersion.NAME__SIDE_EFFECT_FREE), List.nil()));
        }
        JCTree.JCModifiers modifiers = maker.Modifiers(1025L, annotations);
        Name name = job.toName(job.buildMethodName);
        JCTree.JCIdent jCIdentIdent = maker.Ident(job.toName(classGenericName));
        JCTree.JCVariableDecl recv = HandleBuilder.generateReceiver(job);
        if (recv != null && maker.hasMethodDefWithRecvParam()) {
            methodDef = maker.MethodDefWithRecvParam(modifiers, name, jCIdentIdent, List.nil(), recv, List.nil(), List.nil(), null, null);
        } else {
            methodDef = maker.MethodDef(modifiers, name, jCIdentIdent, List.nil(), List.nil(), List.nil(), null, null);
        }
        return methodDef;
    }

    private JCTree.JCMethodDecl generateBuildMethod(SuperBuilderJob job, List<JCTree.JCExpression> thrownExceptions) {
        JCTree.JCMethodDecl methodDef;
        JavacTreeMaker maker = job.getTreeMaker();
        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        List<JCTree.JCExpression> builderArg = List.of(maker.Ident(job.toName("this")));
        statements.append(maker.Return(maker.NewClass(null, List.nil(), JavacHandlerUtil.cloneSelfType(job.parentType), builderArg, null)));
        JCTree.JCBlock body = maker.Block(0L, statements.toList());
        JCTree.JCAnnotation overrideAnnotation = maker.Annotation(JavacHandlerUtil.genJavaLangTypeRef(job.builderType, "Override"), List.nil());
        List<JCTree.JCAnnotation> annsOnMethod = List.of(overrideAnnotation);
        if (job.checkerFramework.generateSideEffectFree()) {
            annsOnMethod = annsOnMethod.prepend(maker.Annotation(JavacHandlerUtil.genTypeRef(job.builderType, CheckerFrameworkVersion.NAME__SIDE_EFFECT_FREE), List.nil()));
        }
        JCTree.JCModifiers modifiers = maker.Modifiers(1L, annsOnMethod);
        JCTree.JCVariableDecl recv = HandleBuilder.generateReceiver(job);
        if (recv != null && maker.hasMethodDefWithRecvParam()) {
            methodDef = maker.MethodDefWithRecvParam(modifiers, job.toName(job.buildMethodName), JavacHandlerUtil.cloneSelfType(job.parentType), List.nil(), recv, List.nil(), thrownExceptions, body, null);
        } else {
            methodDef = maker.MethodDef(modifiers, job.toName(job.buildMethodName), JavacHandlerUtil.cloneSelfType(job.parentType), List.nil(), List.nil(), thrownExceptions, body, null);
        }
        JavacHandlerUtil.createRelevantNonNullAnnotation(job.builderType, methodDef);
        return methodDef;
    }

    private JCTree.JCMethodDecl generateCleanMethod(java.util.List<HandleBuilder.BuilderFieldData> builderFields, JavacNode type, JavacNode source) {
        JavacTreeMaker maker = type.getTreeMaker();
        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        for (HandleBuilder.BuilderFieldData bfd : builderFields) {
            if (bfd.singularData != null && bfd.singularData.getSingularizer() != null) {
                bfd.singularData.getSingularizer().appendCleaningCode(bfd.singularData, type, source, statements);
            }
        }
        statements.append(maker.Exec(maker.Assign(maker.Select(maker.Ident(type.toName("this")), type.toName("$lombokUnclean")), maker.Literal(Javac.CTC_BOOLEAN, 0))));
        JCTree.JCBlock body = maker.Block(0L, statements.toList());
        return maker.MethodDef(maker.Modifiers(1L), type.toName("$lombokClean"), maker.Type(Javac.createVoidType(type.getSymbolTable(), Javac.CTC_VOID)), List.nil(), List.nil(), List.nil(), body, null);
    }

    private void generateBuilderFields(JavacNode builderType, java.util.List<HandleBuilder.BuilderFieldData> builderFields, JavacNode source) {
        int len = builderFields.size();
        java.util.List<JavacNode> existing = new ArrayList<>();
        for (JavacNode child : builderType.down()) {
            if (child.getKind() == AST.Kind.FIELD) {
                existing.add(child);
            }
        }
        java.util.List<JCTree.JCVariableDecl> generated = new ArrayList<>();
        for (int i = len - 1; i >= 0; i--) {
            HandleBuilder.BuilderFieldData bfd = builderFields.get(i);
            if (bfd.singularData != null && bfd.singularData.getSingularizer() != null) {
                java.util.List<JavacNode> fields = bfd.singularData.getSingularizer().generateFields(bfd.singularData, builderType, source);
                for (JavacNode field : fields) {
                    generated.add(field.get());
                }
                bfd.createdFields.addAll(fields);
            } else {
                JavacNode field2 = null;
                JavacNode setFlag = null;
                for (JavacNode exists : existing) {
                    Name n = exists.get().name;
                    if (n.equals(bfd.builderFieldName)) {
                        field2 = exists;
                    }
                    if (n.equals(bfd.nameOfSetFlag)) {
                        setFlag = exists;
                    }
                }
                JavacTreeMaker maker = builderType.getTreeMaker();
                if (field2 == null) {
                    JCTree.JCModifiers mods = maker.Modifiers(2L);
                    JCTree.JCVariableDecl newField = maker.VarDef(mods, bfd.builderFieldName, JavacHandlerUtil.cloneType(maker, bfd.type, source), null);
                    field2 = JavacHandlerUtil.injectFieldAndMarkGenerated(builderType, newField);
                    generated.add(newField);
                }
                if (setFlag == null && bfd.nameOfSetFlag != null) {
                    JCTree.JCModifiers mods2 = maker.Modifiers(2L);
                    JCTree.JCVariableDecl newField2 = maker.VarDef(mods2, bfd.nameOfSetFlag, maker.TypeIdent(Javac.CTC_BOOLEAN), null);
                    JavacHandlerUtil.injectFieldAndMarkGenerated(builderType, newField2);
                    generated.add(newField2);
                }
                bfd.createdFields.add(field2);
            }
        }
        for (JCTree.JCVariableDecl gen : generated) {
            JavacHandlerUtil.recursiveSetGeneratedBy(gen, source);
        }
    }

    private void generateSetterMethodsForBuilder(final SuperBuilderJob job, HandleBuilder.BuilderFieldData fieldNode, final String builderGenericName, String setterPrefix) {
        boolean deprecate = JavacHandlerUtil.isFieldDeprecated(fieldNode.originalFieldNode);
        final JavacTreeMaker maker = job.getTreeMaker();
        JavacSingularsRecipes.ExpressionMaker returnTypeMaker = new JavacSingularsRecipes.ExpressionMaker() { // from class: lombok.javac.handlers.HandleSuperBuilder.1
            @Override // lombok.javac.handlers.JavacSingularsRecipes.ExpressionMaker
            public JCTree.JCExpression make() {
                return maker.Ident(job.toName(builderGenericName));
            }
        };
        JavacSingularsRecipes.StatementMaker returnStatementMaker = new JavacSingularsRecipes.StatementMaker() { // from class: lombok.javac.handlers.HandleSuperBuilder.2
            @Override // lombok.javac.handlers.JavacSingularsRecipes.StatementMaker
            public JCTree.JCStatement make() {
                return maker.Return(maker.Apply(List.nil(), maker.Ident(job.toName(HandleSuperBuilder.SELF_METHOD)), List.nil()));
            }
        };
        if (fieldNode.singularData == null || fieldNode.singularData.getSingularizer() == null) {
            generateSimpleSetterMethodForBuilder(job, deprecate, fieldNode.createdFields.get(0), fieldNode.name, fieldNode.nameOfSetFlag, returnTypeMaker.make(), returnStatementMaker.make(), fieldNode.annotations, fieldNode.originalFieldNode, setterPrefix);
        } else {
            fieldNode.singularData.getSingularizer().generateMethods(job.checkerFramework, fieldNode.singularData, deprecate, job.builderType, job.sourceNode, true, returnTypeMaker, returnStatementMaker, AccessLevel.PUBLIC);
        }
    }

    private void generateSimpleSetterMethodForBuilder(SuperBuilderJob job, boolean deprecate, JavacNode fieldNode, Name paramName, Name nameOfSetFlag, JCTree.JCExpression returnType, JCTree.JCStatement returnStatement, List<JCTree.JCAnnotation> annosOnParam, JavacNode originalFieldNode, String setterPrefix) {
        String setterName = HandlerUtil.buildAccessorName(setterPrefix, paramName.toString());
        Name setterName_ = job.builderType.toName(setterName);
        for (JavacNode child : job.builderType.down()) {
            if (child.getKind() == AST.Kind.METHOD) {
                JCTree.JCMethodDecl methodDecl = child.get();
                Name existingName = methodDecl.name;
                if (existingName.equals(setterName_) && !JavacHandlerUtil.isTolerate(fieldNode, methodDecl)) {
                    return;
                }
            }
        }
        JavacTreeMaker maker = fieldNode.getTreeMaker();
        List<JCTree.JCAnnotation> methodAnns = JavacHandlerUtil.findCopyableToSetterAnnotations(originalFieldNode);
        JCTree.JCMethodDecl newMethod = null;
        if (job.checkerFramework.generateCalledMethods() && maker.hasMethodDefWithRecvParam()) {
            JCTree.JCAnnotation ncAnno = maker.Annotation(JavacHandlerUtil.genTypeRef(job.sourceNode, CheckerFrameworkVersion.NAME__NOT_CALLED), List.of(maker.Literal(setterName.toString())));
            JCTree.JCClassDecl builderTypeNode = job.builderType.get();
            JCTree.JCExpression selfType = JavacHandlerUtil.namePlusTypeParamsToTypeReference(maker, job.builderType, builderTypeNode.typarams, List.of(ncAnno));
            JCTree.JCVariableDecl recv = maker.VarDef(maker.Modifiers(0L, List.nil()), job.toName("this"), selfType, null);
            newMethod = HandleSetter.createSetterWithRecv(1L, deprecate, fieldNode, maker, setterName, paramName, nameOfSetFlag, returnType, returnStatement, job.sourceNode, methodAnns, annosOnParam, recv);
        }
        if (newMethod == null) {
            newMethod = HandleSetter.createSetter(1L, deprecate, fieldNode, maker, setterName, paramName, nameOfSetFlag, returnType, returnStatement, job.sourceNode, methodAnns, annosOnParam);
        }
        if (job.checkerFramework.generateReturnsReceiver()) {
            List<JCTree.JCAnnotation> annotations = newMethod.mods.annotations;
            if (annotations == null) {
                annotations = List.nil();
            }
            JCTree.JCAnnotation anno = maker.Annotation(JavacHandlerUtil.genTypeRef(job.builderType, CheckerFrameworkVersion.NAME__RETURNS_RECEIVER), List.nil());
            JavacHandlerUtil.recursiveSetGeneratedBy(anno, job.sourceNode);
            newMethod.mods.annotations = annotations.prepend(anno);
        }
        JavacHandlerUtil.injectMethod(job.builderType, newMethod);
    }

    private void addObtainVia(HandleBuilder.BuilderFieldData bfd, JavacNode node) {
        for (JavacNode child : node.down()) {
            if (JavacHandlerUtil.annotationTypeMatches((Class<? extends Annotation>) Builder.ObtainVia.class, child)) {
                AnnotationValues<Builder.ObtainVia> ann = JavacHandlerUtil.createAnnotation(Builder.ObtainVia.class, child);
                bfd.obtainVia = (Builder.ObtainVia) ann.getInstance();
                bfd.obtainViaNode = child;
                JavacHandlerUtil.deleteAnnotationIfNeccessary(child, (Class<? extends Annotation>) Builder.ObtainVia.class);
                return;
            }
        }
    }

    private JavacSingularsRecipes.SingularData getSingularData(JavacNode node, String setterPrefix) {
        for (JavacNode child : node.down()) {
            if (JavacHandlerUtil.annotationTypeMatches((Class<? extends Annotation>) Singular.class, child)) {
                Name pluralName = node.getKind() == AST.Kind.FIELD ? JavacHandlerUtil.removePrefixFromField(node) : node.get().name;
                AnnotationValues<Singular> ann = JavacHandlerUtil.createAnnotation(Singular.class, child);
                Singular singularInstance = (Singular) ann.getInstance();
                JavacHandlerUtil.deleteAnnotationIfNeccessary(child, (Class<? extends Annotation>) Singular.class);
                String explicitSingular = singularInstance.value();
                if (explicitSingular.isEmpty()) {
                    if (Boolean.FALSE.equals(node.getAst().readConfiguration(ConfigurationKeys.SINGULAR_AUTO))) {
                        node.addError("The singular must be specified explicitly (e.g. @Singular(\"task\")) because auto singularization is disabled.");
                        explicitSingular = pluralName.toString();
                    } else {
                        explicitSingular = HandlerUtil.autoSingularize(pluralName.toString());
                        if (explicitSingular == null) {
                            node.addError("Can't singularize this name; please specify the singular explicitly (i.e. @Singular(\"sheep\"))");
                            explicitSingular = pluralName.toString();
                        }
                    }
                }
                Name singularName = node.toName(explicitSingular);
                JCTree.JCExpression type = node.get() instanceof JCTree.JCVariableDecl ? node.get().vartype : null;
                List<JCTree.JCExpression> typeArgs = List.nil();
                if (type instanceof JCTree.JCTypeApply) {
                    typeArgs = ((JCTree.JCTypeApply) type).arguments;
                    type = ((JCTree.JCTypeApply) type).clazz;
                }
                String name = type.toString();
                String targetFqn = JavacSingularsRecipes.get().toQualified(name);
                JavacSingularsRecipes.JavacSingularizer singularizer = JavacSingularsRecipes.get().getSingularizer(targetFqn, node);
                if (singularizer == null) {
                    node.addError("Lombok does not know how to create the singular-form builder methods for type '" + name + "'; they won't be generated.");
                    return null;
                }
                return new JavacSingularsRecipes.SingularData(child, singularName, pluralName, typeArgs, targetFqn, singularizer, singularInstance.ignoreNullCollections(), setterPrefix);
            }
        }
        return null;
    }

    private HashSet<String> gatherUsedTypeNames(List<JCTree.JCTypeParameter> typeParams, JCTree.JCClassDecl td) {
        HashSet<String> usedNames = new HashSet<>();
        for (JCTree.JCTypeParameter typeParam : typeParams) {
            usedNames.add(typeParam.getName().toString());
        }
        usedNames.add(td.name.toString());
        for (JCTree.JCVariableDecl jCVariableDecl : td.getMembers()) {
            if (jCVariableDecl.getKind() == Tree.Kind.VARIABLE && (jCVariableDecl instanceof JCTree.JCVariableDecl)) {
                JCTree.JCIdent type = jCVariableDecl.getType();
                if (type instanceof JCTree.JCIdent) {
                    usedNames.add(type.getName().toString());
                }
            }
        }
        return usedNames;
    }

    private String generateNonclashingNameFor(String classGenericName, HashSet<String> typeParamStrings) {
        if (!typeParamStrings.contains(classGenericName)) {
            return classGenericName;
        }
        int counter = 2;
        while (typeParamStrings.contains(String.valueOf(classGenericName) + counter)) {
            counter++;
        }
        return String.valueOf(classGenericName) + counter;
    }

    private JavacNode findInnerClass(JavacNode parent, String name) {
        for (JavacNode child : parent.down()) {
            if (child.getKind() == AST.Kind.TYPE) {
                JCTree.JCClassDecl td = child.get();
                if (td.name.contentEquals(name)) {
                    return child;
                }
            }
        }
        return null;
    }

    private ListBuffer<JCTree.JCExpression> getTypeParamExpressions(List<? extends JCTree> typeParams, JavacTreeMaker maker, JavacNode source) {
        ListBuffer<JCTree.JCExpression> typeParamsForBuilderParameter = new ListBuffer<>();
        Iterator it = typeParams.iterator();
        while (it.hasNext()) {
            JCTree.JCTypeApply jCTypeApply = (JCTree) it.next();
            if (jCTypeApply instanceof JCTree.JCTypeParameter) {
                typeParamsForBuilderParameter.append(maker.Ident(((JCTree.JCTypeParameter) jCTypeApply).getName()));
            } else if (jCTypeApply instanceof JCTree.JCIdent) {
                typeParamsForBuilderParameter.append(maker.Ident(((JCTree.JCIdent) jCTypeApply).getName()));
            } else if (jCTypeApply instanceof JCTree.JCFieldAccess) {
                typeParamsForBuilderParameter.append(copySelect(maker, (JCTree.JCFieldAccess) jCTypeApply));
            } else if (jCTypeApply instanceof JCTree.JCTypeApply) {
                typeParamsForBuilderParameter.append(JavacHandlerUtil.cloneType(maker, jCTypeApply, source));
            }
        }
        return typeParamsForBuilderParameter;
    }

    private JCTree.JCExpression copySelect(JavacTreeMaker maker, JCTree.JCFieldAccess typeParam) {
        java.util.List<Name> chainNames = new ArrayList<>();
        JCTree.JCFieldAccess expression = typeParam;
        while (expression != null) {
            if (expression instanceof JCTree.JCFieldAccess) {
                chainNames.add(expression.getIdentifier());
                expression = expression.getExpression();
            } else if (expression instanceof JCTree.JCIdent) {
                chainNames.add(((JCTree.JCIdent) expression).getName());
                expression = null;
            }
        }
        Collections.reverse(chainNames);
        JCTree.JCFieldAccess jCFieldAccessIdent = null;
        for (Name name : chainNames) {
            if (jCFieldAccessIdent == null) {
                jCFieldAccessIdent = maker.Ident(name);
            } else {
                jCFieldAccessIdent = maker.Select(jCFieldAccessIdent, name);
            }
        }
        return jCFieldAccessIdent;
    }

    private boolean constructorExists(JavacNode type, String builderClassName) {
        if (type != null && (type.get() instanceof JCTree.JCClassDecl)) {
            for (JCTree.JCMethodDecl jCMethodDecl : type.get().defs) {
                if (jCMethodDecl instanceof JCTree.JCMethodDecl) {
                    JCTree.JCMethodDecl md = jCMethodDecl;
                    String name = md.name.toString();
                    boolean matches = name.equals("<init>");
                    if (!JavacHandlerUtil.isTolerate(type, md) && matches && md.params != null && md.params.length() == 1) {
                        String typeName = ((JCTree.JCVariableDecl) md.params.get(0)).getType().toString();
                        int lastIndexOfDot = typeName.lastIndexOf(46);
                        if (lastIndexOfDot >= 0) {
                            typeName = typeName.substring(lastIndexOfDot + 1);
                        }
                        if ((String.valueOf(builderClassName) + "<?, ?>").equals(typeName)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }
}
