package com.blunderer.materialdesignlibrary.sample.composite;

import android.content.Intent;

import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.Account;
import com.blunderer.materialdesignlibrary.sample.MainFragment;
import com.blunderer.materialdesignlibrary.sample.R;
import com.blunderer.materialdesignlibrary.sample.viewpagers.ViewPagerActivity;

/**
 * Modifications by anujsaluja on 5/30/15.
 *
 * Original Source Code Credit: Denis Mondon
 * GitHub: https://github.com/DenisMondon/material-design-library
 */
public class NavigationDrawerWithViewPagerTabsActivity
        extends com.blunderer.materialdesignlibrary.activities.NavigationDrawerWithViewPagerTabsActivity {

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        return null;
    }

    @Override
    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler() {
        return null;
    }

    @Override
    public void onNavigationDrawerAccountChange(Account account) {
    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        // Note: The order of adding Navigation items to ViewPagerHandler
        // should be the same as adding those to NavigationDrawer because we
        // use index of item that is clicked in NavigationDrawer to navigate
        // to corresponding View in ViewPager.
        return new NavigationDrawerTopHandler(this)
                .addSection(R.string.navigation)
                .addItem(getResources().getString(R.string.portfolio))
                .addItem(getResources().getString(R.string.watchlist))
                .addItem(getResources().getString(R.string.findstocks))
                .addItem(getResources().getString(R.string.topmovers))
                .addItem(getResources().getString(R.string.myaccount))
                .addItem(getResources().getString(R.string.cramerspicks))
                .addItem(getResources().getString(R.string.insidertrades))

                .addSection(R.string.activity)
                .addItem(R.string.start_activity, R.mipmap.ic_github,
                        new Intent(getApplicationContext(), ViewPagerActivity.class));
    }

    @Override
    public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return new NavigationDrawerBottomHandler(this)
                .addSettings(null)
                .addHelpAndFeedback(null);
    }

    @Override
    public boolean overlayActionBar() {
        return false;
    }

    @Override
    public boolean overlayStatusBar() {
        return false;
    }

    @Override
    public boolean replaceActionBarTitleByNavigationDrawerItemTitle() {
        return false;
    }

    @Override
    public int defaultNavigationDrawerItemSelectedPosition() {
        return 1;
    }

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        // Note: The order of adding Navigation items to ViewPagerHandler
        // should be the same as adding those to NavigationDrawer because we
        // use index of item that is clicked in NavigationDrawer to navigate
        // to corresponding View in ViewPager.

        // Prepare the NavigationDrawer to ViewPager item map
        addNavigationViewPagerMapping(getResources().getString(R.string.portfolio), 1);
        addNavigationViewPagerMapping(getResources().getString(R.string.watchlist), 2);
        addNavigationViewPagerMapping(getResources().getString(R.string.findstocks), 3);
        addNavigationViewPagerMapping(getResources().getString(R.string.topmovers), 4);
        addNavigationViewPagerMapping(getResources().getString(R.string.myaccount), 5);
        addNavigationViewPagerMapping(getResources().getString(R.string.cramerspicks), 6);
        addNavigationViewPagerMapping(getResources().getString(R.string.insidertrades), 7);

        return new ViewPagerHandler(this)
                .addPage(R.string.portfolio,
                        MainFragment.newInstance(getResources().getString(R.string.portfolio)))
                .addPage(R.string.watchlist,
                        MainFragment.newInstance(getResources().getString(R.string.watchlist)))
                .addPage(R.string.findstocks,
                        MainFragment.newInstance(getResources().getString(R.string.findstocks)))
                .addPage(R.string.topmovers,
                        MainFragment.newInstance(getResources().getString(R.string.topmovers)))
                .addPage(R.string.myaccount,
                        MainFragment.newInstance(getResources().getString(R.string.myaccount)))
                .addPage(R.string.cramerspicks,
                        MainFragment.newInstance(getResources().getString(R.string.cramerspicks)))
                .addPage(R.string.insidertrades,
                        MainFragment.newInstance(getResources().getString(R.string.insidertrades)));
    }

    @Override
    public boolean expandTabs() {
        return false;
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }
}
