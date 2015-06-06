package com.blunderer.materialdesignlibrary.sample.composite;

/**
 * Created by anujsaluja on 6/5/15.
 */
public class NavigationDrawerWithViewPagerTabsFullScreenActivity
        extends NavigationDrawerWithViewPagerTabsFullHeightActivity {
    @Override
    public boolean overlayStatusBar() {
        return true;
    }
}
