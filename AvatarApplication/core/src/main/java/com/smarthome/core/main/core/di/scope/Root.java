package com.smarthome.core.main.core.di.scope;

import java.lang.annotation.Documented;

import javax.inject.Qualifier;

/**
 * Qualifies dagger injected Android {android.view.View}s as the root one.
 * This could be the activity's, fragment's or even ViewPager or list item's root.
 */
@Documented
@Qualifier
public @interface Root {
}