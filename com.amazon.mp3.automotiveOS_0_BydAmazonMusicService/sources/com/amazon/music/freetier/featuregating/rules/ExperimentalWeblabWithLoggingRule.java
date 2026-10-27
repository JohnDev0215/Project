package com.amazon.music.freetier.featuregating.rules;

import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.featuregate.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ExperimentalWeblabWithLoggingRule implements Rule {
    private static final Logger logger = LoggerFactory.getLogger(ExperimentalWeblabWithLoggingRule.class.getSimpleName());
    private final WeblabRule aaWeblabRule;
    private final WeblabRule abWeblabRule;

    public ExperimentalWeblabWithLoggingRule(WeblabRule weblabRule, WeblabRule weblabRule2) {
        this.aaWeblabRule = weblabRule;
        this.abWeblabRule = weblabRule2;
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        if (this.aaWeblabRule.getValue()) {
            logger.debug("Rule for AA Weblab {} is SATISFIED", this.aaWeblabRule.getWeblabName());
        } else {
            logger.debug("Rule for AA Weblab {} is NOT SATISFIED", this.aaWeblabRule.getWeblabName());
        }
        if (this.abWeblabRule.getValue()) {
            logger.debug("Rule for AB Weblab {} is SATISFIED", this.abWeblabRule.getWeblabName());
        } else {
            logger.debug("Rule for AB Weblab {} is NOT SATISFIED", this.abWeblabRule.getWeblabName());
        }
        return this.abWeblabRule.getValue();
    }
}
